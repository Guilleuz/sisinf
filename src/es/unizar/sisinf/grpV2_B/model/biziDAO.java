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
// Lista de todas las estaciones

// Introducir nueva estacion en la DB