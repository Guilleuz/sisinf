package es.unizar.sisinf.grpV2_B.model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import es.unizar.sisinf.grpV2_B.db.*;
import org.json.*;
import org.postgis.PGgeometry;
import org.postgresql.util.PGobject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class poblarBBDD extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Creamos el cliente para la conexión a la API
		URL url;
		try {	
			// Paradas de autobús
			// URL de la consulta a la API
			url = new URL ("https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/"
					+ "transporte-urbano/poste-autobus.json?fl=id%2C%20title%2C%20geometry&rf=html&srsname=wgs84&start=0&rows=5000");
			
			// Realizamos la consulta
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			String res = "";
			Scanner scanner = new Scanner(url.openStream());
			while (scanner.hasNext()) {
		       res += scanner.nextLine();
		    }
			
			// Tratamos la respuesta JSON
			JSONObject respuesta = new JSONObject(res);
			JSONArray paradas = respuesta.getJSONArray("result");
			JSONObject parada;
			paradaBusDAO bus = new paradaBusDAO();
			/*for(int i = 0; i < paradas.length(); i++) {
				// Obtenemos los datos de cada parada y la almacenamos en la DB
				parada = paradas.getJSONObject(i);
				String nombrePoste = parada.getString("id");
				StringTokenizer tokens = new StringTokenizer(nombrePoste, "-");
				if(tokens.nextToken().equals("tuzsa")) {
					int id = 0;
					try {
						id = Integer.valueOf(tokens.nextToken());
					} catch (NumberFormatException ex){
	                    ex.printStackTrace();
	                }
					String dir = parada.getString("title");
					JSONObject punto = parada.getJSONObject("geometry");
					JSONArray coords = punto.getJSONArray("coordinates");
					double x = coords.getDouble(0);
					double y = coords.getDouble(1);
					bus.anyadir(new paradaBusVO(id, dir, x, y));
					System.out.printf("Introducida parada Bus: %d, %s, %f, %f\n", id, dir, x, y);
				}
			}*/
			
			// Estaciones Bizi
			// URL de la consulta a la API
			url = new URL ("https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/estacion-bicicleta.json?"
					+ "fl=id%2C%20title%2C%20anclajesDisponibles%2C%20geometry&rf=html&srsname=wgs84&start=0&rows=5000");
			
			// Realizamos la consulta
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			res = "";
			scanner.close();
			scanner = new Scanner(url.openStream());
			while (scanner.hasNext()) {
		       res += scanner.nextLine();
		    }
			
			// Tratamos la respuesta JSON
			respuesta = new JSONObject(res);
			JSONArray estaciones = respuesta.getJSONArray("result");
			JSONObject estacion;
			biziDAO bizi = new biziDAO();
			/*for(int i = 0; i < estaciones.length(); i++) {
				// Obtenemos los datos de cada parada y la almacenamos en la DB
				estacion = estaciones.getJSONObject(i);
				int id = 0;
				try {
					id = Integer.valueOf(estacion.getString("id"));
				} catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
				String dir = estacion.getString("title");
				int maxBicis = estacion.getInt("anclajesDisponibles");
				JSONObject punto = estacion.getJSONObject("geometry");
				JSONArray coords = punto.getJSONArray("coordinates");
				double x = coords.getDouble(0);
				double y = coords.getDouble(1);
				bizi.anyadir(new biziVO(id, maxBicis, 0, dir, x, y));
				System.out.printf("Introducida estación Bizi: %d, %s, maxBicis: %d, %f, %f\n", id, dir, maxBicis, x, y);
			}*/
			
			// Paradas de tranvía
			// URL de la consulta a la API
			url = new URL ("https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/parada-tranvia.json?"
					+ "fl=id%2C%20title%2C%20geometry&rf=html&srsname=wgs84&start=0&rows=500");
			
			// Realizamos la consulta
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			res = "";
			scanner.close();
			scanner = new Scanner(url.openStream());
			while (scanner.hasNext()) {
		       res += scanner.nextLine();
		    }
			
			// Tratamos la respuesta JSON
			respuesta = new JSONObject(res);
			JSONArray paradasT = respuesta.getJSONArray("result");
			JSONObject paradaT;
			paradaTranviaDAO tranvia = new paradaTranviaDAO();
			/*for(int i = 0; i < paradasT.length(); i++) {
				// Obtenemos los datos de cada parada y la almacenamos en la DB
				paradaT = paradasT.getJSONObject(i);
				int id = 0;
				try {
					id = Integer.valueOf(paradaT.getString("id"));
				} catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
				String sentido = "";
				if (id % 10 == 1) sentido = "Mago de Oz";
				else if (id % 10 == 2) sentido = "Avenida Academia";
				
				String dir = paradaT.getString("title");
				JSONObject punto = paradaT.getJSONObject("geometry");
				JSONArray coords = punto.getJSONArray("coordinates");
				double x = coords.getDouble(0);
				double y = coords.getDouble(1);
				tranvia.anyadir(new paradaTranviaVO(id, dir, sentido, id, dir, x, y));
				System.out.printf("Introducida parada tranvia: %d, %s, %s, %f, %f\n", id, dir, sentido, x, y);
			
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("index.html");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
