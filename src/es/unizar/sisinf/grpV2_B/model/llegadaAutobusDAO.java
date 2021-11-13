package es.unizar.sisinf.grpV2_B.model;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		URL url;
		try {
			url = new URL ("https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/"
					+ "poste-autobus/tuzsa-" + nPoste + ".json?fl=destinos&rf=html&srsname=wgs84");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			String res = "";
			Scanner scanner = new Scanner(url.openStream());
			while (scanner.hasNext()) {
		       res += scanner.nextLine();
		    }

			// Creamos una lista de objetos JSON a partir de la respuesta
			JSONObject respuesta = new JSONObject(res);
			JSONArray llegadas = respuesta.getJSONArray("destinos");
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
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return lista;
	}
}
