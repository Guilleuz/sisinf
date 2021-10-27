package es.unizar.sisinf.grpV2_B.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import es.unizar.sisinf.grpV2_B.db.*;
import org.json.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class biziDAO {
	
    private static String insertar = "INSERT INTO BiziStation (id, capacity, available, direction, localitation) VALUES(?,?,?,?,?)";
    private static String lsEstaciones = "SELECT * FROM BiziStation ORDER BY id ASC";
    
    public List<biziVO> listar(){

	List<biziVO> listaEstaciones = new LinkedList<biziVO>();
        Connection conn = null;

	try {
		conn = ConnectionManager.getConnection();
           	conn.getConnection();
            	PreparedStatement lsEst = conn.PreparedStatement(lsEstaciones);

            	ResultSet rs = lsEst.executeQuery();

            	while(rs.next()){
                	biziVO estacion = new biziVO(rs.getInt("id")
                                                    ,rs.getInt("capacity")
                                                    ,rs.getInt("available")
                                                    ,rs.getString("direction")
                                                    ,rs.getString("localitation")); // es localization :D
                	listaParadas.add(parada);
	}
    	rs.close();
    	lsEst.close();

	} catch(SQLException se) {
		se.printStackTrace();  

	} catch(Exception e) {
		e.printStackTrace(System.err); 
	} finally {
		ConnectionManager.releaseConnection(conn); 
	}

	return listaEstaciones;
    }
    
    public void anyadir(biziVO estacion){
        Connection conn = null;

        try {
            conn = ConnectionManager.getConnection();
            conn.getConnection();
            PreparedStatement addEst = conn.PreparedStatement(insertar);
            ResultSet rs = lsEst.executeQuery();

            addEst.setString(1, estacion.getID());
	    addEst.setString(2, estacion.getCapacidad());
	    addEst.setString(3, estacion.getBicis());
	    addEst.setString(4, estacion.getDireccion());
	    addEst.setString(5, estacion.getLocalizacion());
		
            addEst.executeUpdate();

            rs.close();
            addEst.close();

        } catch(SQLException se) {
            se.printStackTrace();  
        
        } catch(Exception e) {
            e.printStackTrace(System.err); 
        } finally {
            ConnectionManager.releaseConnection(conn); 
        }
    }

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


// Dado número de estacion -> nbicis disponibles (API)
