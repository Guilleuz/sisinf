package es.unizar.sisinf.grpV2_B.model;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.*;
import es.unizar.sisinf.grpV2_B.db.*;
import org.json.*;
import org.postgis.*;
import org.postgresql.util.PGobject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.*;

public class biziDAO {

	private static String insertar = "INSERT INTO BiziStation (id, capacity, available, direction, lat, long) VALUES(?,?,?,?,?,?)";
	private static String lsEstaciones = "SELECT id, capacity, available, direction, lat, long FROM BiziStation ORDER BY id ASC";
	private static String info = "SELECT id, capacity, available, direction, lat, long FROM BiziStation WHERE id=?";
	// ST_AsText(your_geom_column)
	// geom::text
	
	
	//SELECT ST_X(the_geom), ST_Y(the_geom), ST_AsText(the_geom) FROM myTable;
	// -> 0 , 03223, POINT((0,03223))
	
	// // geom::text -> WKT: 3238399FA12349

	// INSERT ST_GeomFromText(?)
	//3238399FA12349 -> POINT ((0,03223))
	
	//ST_AsEWKT(localization);

	

	//SELECT * ST_AsText(the_geom) FROM table2;

	// devuelve una lista de las estaciones bizi
	public List<biziVO> listar() throws SQLException {

		List<biziVO> listaEstaciones = new LinkedList<biziVO>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			
			//((org.postgresql.PGConnection)conn).addDataType("geometry",Class.forName("org.postgis.PGgeometry"));
			PreparedStatement lsEst = conn.prepareStatement(lsEstaciones);
			//((org.postgresql.PGConnection)conn).addDataType("geometry", (Class<? extends PGobject>) Class.forName("org.postgis.PGgeometry"));
			
			ResultSet rs = lsEst.executeQuery();


			while (rs.next()) {
				biziVO estacion = new biziVO(rs.getInt("id"), rs.getInt("capacity"), rs.getInt("available"), 
								  rs.getString("direction"), rs.getDouble("lat"), rs.getDouble("long"));
				listaEstaciones.add(estacion);
			}
			rs.close();
			lsEst.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}

		return listaEstaciones;
	}
	
	public biziVO infoBizi(int id) throws SQLException {

		Connection conn = null;
		biziVO estacion = null;

		try {
			conn = PoolConnectionManager.getConnection();
			
			PreparedStatement lsEst = conn.prepareStatement(info);
			lsEst.setInt(1, id);
			ResultSet rs = lsEst.executeQuery();
			rs.next();
			estacion = new biziVO(rs.getInt("id"), rs.getInt("capacity"), 0, 
								  rs.getString("direction"), rs.getDouble("lat"), rs.getDouble("long"));

			estacion.setBicis(getNumeroBicis(id));
			System.out.println(estacion.getID() + " " + estacion.getDireccion() + " " + estacion.getBicis());
			rs.close();
			lsEst.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}
		return estacion;
	}

	// añade una estacion bizi a la base de datos
	public void anyadir(biziVO estacion) throws SQLException {
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			
			PreparedStatement addEst = conn.prepareStatement(insertar);

			addEst.setInt(1, estacion.getID());
			addEst.setInt(2, estacion.getCapacidad());
			addEst.setInt(3, estacion.getBicis());
			addEst.setString(4, estacion.getDireccion());
			addEst.setDouble(5, estacion.getLatitud()); 
			addEst.setDouble(6, estacion.getLongitud()); 

			addEst.executeUpdate();
			addEst.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}
	}

	// Dado número de estacion, obtenemos de la API el número de bicis disponibles
	public int getNumeroBicis(int estacion) {
		String id = "" + estacion;
		id = new String(new char[3 - id.length()]).replace('\0', '0') + id;
		try {
			URL url = new URL("https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/estacion-bicicleta/" + id
					+ ".json?fl=bicisDisponibles&rf=html&srsname=wgs84");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			String res = "";
			Scanner scanner = new Scanner(url.openStream());
			while (scanner.hasNext()) {
		       res += scanner.nextLine();
		    }
			JSONObject nBicis = new JSONObject(res);
			scanner.close();
			return nBicis.getInt("bicisDisponibles");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		// Consulta a realizar a la API
		String consultaAPI = "https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/estacion-bicicleta/" + id
				+ "?fl=bicisDisponibles&rf=html&srsname=wgs84";
		// Creamos el cliente para la conexión a la API
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(consultaAPI);

		// Lanzamos la consulta
		String respuesta = target.request(MediaType.APPLICATION_JSON).get(String.class);

		JSONObject nBicis = new JSONObject(respuesta);
		return nBicis.getInt("bicisDisponibles");*/
		return 0;
	}
}


