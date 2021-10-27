package es.unizar.sisinf.grpV2_B.model;

import java.util.ArrayList;
import java.util.List;
import org.json.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class llegadaAutobusDAO {

	// Dado un nPoste devolvemos un listado de llegadas
	public List<llegadaAutobusVO> getLlegadas(int nPoste) {
		List<llegadaAutobusVO> lista = new ArrayList<llegadaAutobusVO>();

		// Consulta a realizar a la API
		String consultaAPI = "https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/"
				+ "poste-autobus/tuzsa-" + nPoste + "?fl=destinos&rf=html&srsname=wgs84";
		// Creamos el cliente para la conexión a la API
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(consultaAPI);

		// Lanzamos la consulta
		String respuesta = target.request(MediaType.APPLICATION_JSON).get(String.class);

		// Creamos una lista de objetos JSON a partir de la respuesta
		JSONArray llegadas = new JSONArray(respuesta);
		JSONObject llegada;

		// Procesamos cada llegada y la añadimos a la lista
		for (int i = 0; i < llegadas.length(); i++) {
			llegada = llegadas.getJSONObject(i);
			String linea = llegada.getString("linea");
			String sentido = llegada.getString("destino");
			String primero = llegada.getString("primero");
			String segundo = llegada.getString("segundo");
			lista.add(new llegadaAutobusVO(nPoste, linea, sentido, primero, segundo));
		}

		return lista;
	}
}
