package es.unizar.sisinf.grpV2_B.model;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

public class llegadaTranviaDAO {

	// Dado el id de la parada, devuelve los tiempos de llegada
	public llegadaTranviaVO getLlegadas(int parada) {

		// Consulta a realizar a la API
		URL url;
		try {
			url = new URL("https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/parada-tranvia/"
					+ parada + ".json?fl=destinos&rf=html&srsname=wgs84");
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

			String primero = "Sin datos";
			String segundo = "Sin datos";

			// Comprobamos el número de llegadas (0,1 o 2) y obtenemos los tiempos
			if(llegadas.length() > 0) {
				llegada = llegadas.getJSONObject(0);
				int minutos = llegada.getInt("minutos");
				primero = minutos + " minutos";
			}
			if(llegadas.length() > 1) {
				llegada = llegadas.getJSONObject(1);
				int minutos = llegada.getInt("minutos");
				segundo = minutos + " minutos";
			}

			return new llegadaTranviaVO(parada, primero, segundo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return new llegadaTranviaVO(parada, "Datos no disponibles", "Datos no disponibles");
	}
}
