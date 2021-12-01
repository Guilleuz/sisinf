package es.unizar.sisinf.grpV2_B.model;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

//Clase DAO para las llegadas de autobus
public class llegadaAutobusDAO {

	// Dado un nPoste devolvemos un listado de llegadas
	public List<llegadaAutobusVO> getLlegadas(int nPoste) {
		List<llegadaAutobusVO> lista = new ArrayList<llegadaAutobusVO>();

		URL url;
		try {
			// Consulta a realizar a la API
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
				Pattern patron = Pattern.compile("^[0-9]+ minutos\\.$");
				String primero = "0";
				String segundo = "0";
				String sentido = llegada.getString("destino");
				Matcher m = patron.matcher(llegada.getString("primero"));
				if (m.matches()) primero = llegada.getString("primero").split(" ")[0];
				
				m = patron.matcher(llegada.getString("segundo"));
				if (m.matches()) segundo = llegada.getString("segundo").split(" ")[0];
				lista.add(new llegadaAutobusVO(nPoste, linea, sentido, primero, segundo));
			}
			
			return lista;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
}
