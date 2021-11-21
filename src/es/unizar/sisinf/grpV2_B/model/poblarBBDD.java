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

import java.util.regex.*;

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
			url = new URL ("https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/poste-autobus.json"
					+ "?fl=id%2C%20title%2C%20geometry&rf=html&srsname=wgs84");
			
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
					+ "fl=id%2C%20title%2C%20anclajesDisponibles%2C%20geometry&rf=html&srsname=wgs84");
			
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
			
			
			// Líneas de autobús
			// URL de la consulta a la API
			url = new URL ("https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/linea-autobus.json?rf=html");
			
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
			
			respuesta = new JSONObject(res);
			JSONArray lineas = respuesta.getJSONArray("result");
			Pattern patron = Pattern.compile("^.*/([0-9]+|[NC][0-9]+|CI[0-9]+)$");
			int id = 0;
			for(int i = 0; i < lineas.length(); i++) {
				String linea = lineas.getString(i);
				Matcher m = patron.matcher(linea);
				String tokens[] = linea.split("/");
				String nombre = tokens[tokens.length - 1];
				if (m.matches() ) {
					System.out.println(linea);
					
					url = new URL ("https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/linea-autobus/" + nombre + ".json");
					
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
					
					respuesta = new JSONObject(res);
					JSONArray paradasLinea = respuesta.getJSONArray("result");
					JSONObject paradaLinea;
					
					// Obtenemos el nº de poste de la 1ª y la última parada del listado
					// La 1ª indicará uno de los sentidos, y la última el otro
					JSONObject paradaS1 = paradasLinea.getJSONObject(1);
					JSONObject paradaS2 = paradasLinea.getJSONObject(paradasLinea.length() - 1);
					String sentido1 = "Sentido 1";
					String sentido2 = "Sentido 2";
					if(paradaS1.has("description") && paradaS2.has("description")) {
						String poste1 = paradaS1.getString("description").split(" ")[1];
						String poste2 = paradaS2.getString("description").split(" ")[1];
						
						
						// Obtenemos el sentido de la línea en cada parada
						URL urlParada1 = new URL("http://www.zaragoza.es/api/recurso/urbanismo-infraestructuras/"
								+ "transporte-urbano/poste/tuzsa-" + poste1 + ".json?fl=destinos");
						URL urlParada2 = new URL("http://www.zaragoza.es/api/recurso/urbanismo-infraestructuras/"
								+ "transporte-urbano/poste/tuzsa-" + poste2 + ".json?fl=destinos");
						
						// Realizamos la primera consulta
						conn = (HttpURLConnection) urlParada1.openConnection();
						conn.setRequestMethod("GET");
						conn.connect();
						String resParada1 = "";
						scanner.close();
						scanner = new Scanner(urlParada1.openStream());
						while (scanner.hasNext()) {
					       resParada1 += scanner.nextLine();
					    }
						
						System.out.println(resParada1);
						JSONObject resParada = new JSONObject(resParada1);
						JSONArray destinos = resParada.getJSONArray("destinos");
						for (int j = 0; j < destinos.length(); j++) {
							if (destinos.getJSONObject(j).getString("linea").equals(nombre)) {
								sentido1 = destinos.getJSONObject(j).getString("destino");
								break;
							}
						}
						
						// Realizamos la segunda consulta
						conn = (HttpURLConnection) urlParada2.openConnection();
						conn.setRequestMethod("GET");
						conn.connect();
						String resParada2 = "";
						scanner.close();
						scanner = new Scanner(urlParada2.openStream());
						while (scanner.hasNext()) {
					       resParada2 += scanner.nextLine();
					    }
						
						resParada = new JSONObject(resParada2);
						 destinos = resParada.getJSONArray("destinos");
						for (int j = 0; j < destinos.length(); j++) {
							if (destinos.getJSONObject(j).getString("linea").equals(nombre)) {
								sentido2 = destinos.getJSONObject(j).getString("destino");
								break;
							}
						}
					}
					// Introducimos las lineas en la DB
					lineaBusDAO l = new lineaBusDAO();
					l.anyadir(new lineaBusVO(id, nombre, sentido1));
					l.anyadir(new lineaBusVO(id + 1, nombre, sentido2));

					/*
					int orden = 0;
					paradaTrayectoDAO p = new paradaTrayectoDAO();
					int idIN = id;
					for(int j = 0; j < paradasLinea.length(); j++) {
						paradaLinea = paradasLinea.getJSONObject(j);
						if (paradaLinea.has("description")) {
							int nPoste = 0;
							try {
								nPoste = Integer.valueOf(paradaLinea.getString("description").split(" ")[1]);;
							} catch (NumberFormatException ex){
			                    ex.printStackTrace();
			                }
							p.anyadir(new paradaTrayectoVO(nPoste, idIN, orden));
							orden++;
						}
						else idIN++;
					}*/
					
					id += 2;
				}
			}
			
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
