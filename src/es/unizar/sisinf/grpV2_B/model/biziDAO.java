package es.unizar.sisinf.grpV2_B.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import es.unizar.sisinf.grpV2_B.db.*;
import org.json.*;
import org.postgis.PGgeometry;
import org.postgresql.util.PGobject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

public class biziDAO {

	private static String insertar = "INSERT INTO BiziStation (id, capacity, available, direction, localitation) VALUES(?,?,?,?,?)";
	private static String lsEstaciones = "SELECT * FROM BiziStation ORDER BY id ASC";

	// devuelve una lista de las estaciones bizi
	public List<biziVO> listar() throws SQLException {

		List<biziVO> listaEstaciones = new LinkedList<biziVO>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			
			PreparedStatement lsEst = conn.prepareStatement(lsEstaciones);
			((org.postgresql.PGConnection)conn).addDataType("geometry", (Class<? extends PGobject>) Class.forName("org.postgis.PGgeometry"));

			ResultSet rs = lsEst.executeQuery();

			while (rs.next()) {
				biziVO estacion = new biziVO(rs.getInt("id"), rs.getInt("capacity"), rs.getInt("available"), 
								  rs.getString("direction"), (PGgeometry) rs.getObject("localization"));
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

	public biziVO infoBizi(int id) {
		List<biziVO> listaEstaciones = listar();
		biziVO estacion;
		for(biziVO i : listaEstaciones){ 	
			if (i.getID() == id) {
				estacion = i;
				estacion.setBicis(getNumeroBicis(id));
				return estacion;
			}
		}
		
	}

	// añade una estacion bizi a la base de datos
	public void anyadir(biziVO estacion) throws SQLException {
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			
			PreparedStatement addEst = conn.prepareStatement(insertar);
			ResultSet rs = addEst.executeQuery();

			addEst.setString(1, Integer.toString(estacion.getID()));
			addEst.setString(2, Integer.toString(estacion.getCapacidad()));
			addEst.setString(3, Integer.toString(estacion.getBicis()));
			addEst.setString(4, estacion.getDireccion());
			addEst.setObject(5, estacion.getLocalizacion()); // Añadir tipo geometry

			addEst.executeUpdate();

			rs.close();
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

		// Consulta a realizar a la API
		String consultaAPI = "https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/estacion-bicicleta/" + id
				+ "?fl=bicisDisponibles&rf=html&srsname=wgs84";
		// Creamos el cliente para la conexión a la API
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(consultaAPI);

		// Lanzamos la consulta
		String respuesta = target.request(MediaType.APPLICATION_JSON).get(String.class);

		JSONObject nBicis = new JSONObject(respuesta);
		return nBicis.getInt("bicisDisponibles");
	}
}


