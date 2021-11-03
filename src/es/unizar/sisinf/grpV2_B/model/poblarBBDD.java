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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class poblarBBDD {
	public static void main() {
		// Creamos el cliente para la conexión a la API
		Client client = ClientBuilder.newClient();
		
		// Paradas de autobús
		String consultaParadas = "https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/"
				+ "transporte-urbano/poste-autobus?fl=id%2C%20title%2C%20geometry&rf=html&srsname=wgs84&start=0&rows=50&distance=500";
		WebTarget target = client.target(consultaParadas);

		// Lanzamos la consulta
		String respuesta = target.request(MediaType.APPLICATION_JSON).get(String.class);
		JSONArray paradas = new JSONArray(respuesta);
		JSONObject parada;
		List<paradaBusVO> lista = new ArrayList<paradaBusVO>();
		for(int i = 0; i < paradas.length(); i++) {
			parada = paradas.getJSONObject(i);
			String id = parada.getString("id");
			String dir = paraga.getString("title");
		}
	}
}
