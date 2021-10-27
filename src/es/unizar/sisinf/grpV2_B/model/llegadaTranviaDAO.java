package es.unizar.sisinf.grpV2_B.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

public class llegadaTranviaDAO {

	// Dado el id de la parada, devuelve los tiempos de llegada
	public llegadaTranviaVO getLlegadas(int parada) {
		List<llegadaTranviaVO> lista = new ArrayList<llegadaTranviaVO>();

		// Consulta a realizar a la API
		String consultaAPI = "https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/parada-tranvia/"
				+ parada + "102?fl=destinos&rf=html&srsname=wgs84";
		// Creamos el cliente para la conexión a la API
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(consultaAPI);

		// Lanzamos la consulta
		String respuesta = target.request(MediaType.APPLICATION_JSON).get(String.class);

		// Creamos una lista de objetos JSON a partir de la respuesta
		JSONArray llegadas = new JSONArray(respuesta);
		JSONObject llegada;

		String primero = "Sin datos";
		String segundo = "Sin datos";

		// Comprobamos el número de llegadas (0,1 o 2) y obtenemos los tiempos
		switch (llegadas.length()) {
		case 1:
			llegada = llegadas.getJSONObject(0);
			primero = llegada.getString("minutos");
			primero += " minutos";
		case 2:
			llegada = llegadas.getJSONObject(1);
			segundo = llegada.getString("minutos");
			primero += " minutos";
		}

		return new llegadaTranviaVO(parada, primero, segundo);
	}
}
