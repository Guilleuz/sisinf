package es.unizar.sisinf.grpV2_B.model;

import java.sql.*;
import org.postgis.*;
import org.postgresql.util.PGobject;
import java.util.LinkedList;
import java.util.List;
import es.unizar.sisinf.grpV2_B.db.PoolConnectionManager;

//Clase DAO para las paradas de tranvía
public class paradaTranviaDAO {

	private static String insertar = "INSERT INTO TramStation (id, name, way, orden, direction, lat, long) VALUES(?,?,?,?,?,?,?)";
	private static String lista = "SELECT * FROM TramStation ORDER BY id ASC";
	private static String listaSentidos = "SELECT DISTINCT way FROM TramStation";
	private static String listaOrdenada = "SELECT id, name, way, orden, direction, lat, long FROM TramStation WHERE way = ? ORDER BY orden";
	private static String idParada = "SELECT id FROM TramStation WHERE name = ? AND way = ?";
	private static String info = "SELECT id, name, orden, way, direction, lat, long FROM TramStation WHERE id = ?";
	
	// Devuelve el id de la parada segun nombre y sentido
	public int idParada(String nombre, String sentido) throws SQLException {
		int identificador = 0;
		Connection conn = null;
		try {
			conn = PoolConnectionManager.getConnection();
			// Consultamos la DB para obtener el id según el nombre y el sentido
			PreparedStatement st = conn.prepareStatement(idParada);
			st.setString(1, nombre);
			st.setString(2, sentido);
			ResultSet rs = st.executeQuery();
			
			rs.next();
			identificador = rs.getInt("id");
			rs.close();
			st.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}

		return identificador;

	}

	// Devuelve la lista de paradas ordenada en un sentido determinado
	public List<paradaTranviaVO> listarOrdenada(String sentido) throws SQLException {

		List<paradaTranviaVO> listaParadas = new LinkedList<paradaTranviaVO>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			// Obtenemos el listado de paradas de la DB
			PreparedStatement lsParadas = conn.prepareStatement(listaOrdenada);
			lsParadas.setString(1, sentido);
			ResultSet rs = lsParadas.executeQuery();

			while (rs.next()) {
				paradaTranviaVO parada = new paradaTranviaVO(rs.getInt("id"), rs.getString("name"), rs.getString("way"), rs.getInt("orden"),
						rs.getString("direction"), rs.getDouble("lat"), rs.getDouble("long"));
				listaParadas.add(parada);
			}
			rs.close();
			lsParadas.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}

		return listaParadas;
	}

	// Devuelve una lista de los sentidos
	public List<String> listarSentidos() throws SQLException {
		List<String> lista = new LinkedList<String>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			// Obtenemos los sentidos de la DB
			PreparedStatement lsSentidos = conn.prepareStatement(listaSentidos);

			ResultSet rs = lsSentidos.executeQuery();

			while (rs.next()) {
				String lS = rs.getString("way");
				lista.add(lS);
			}
			rs.close();
			lsSentidos.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}

		return lista;

	}

	// Devuelve lista de paradas de tranvia
	public List<paradaTranviaVO> listar() throws SQLException {

		List<paradaTranviaVO> listaParadas = new LinkedList<paradaTranviaVO>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			// Obtenemos el listado de paradas completo de la DB
			PreparedStatement lsParadas = conn.prepareStatement(lista);
			ResultSet rs = lsParadas.executeQuery();

			while (rs.next()) {
				paradaTranviaVO parada = new paradaTranviaVO(rs.getInt("id"), rs.getString("name"), rs.getString("way"), rs.getInt("orden"),
						rs.getString("direction"), rs.getDouble("lat"), rs.getDouble("long"));
				listaParadas.add(parada);
			}
			rs.close();
			lsParadas.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}

		return listaParadas;
	}

	// Devuelve la información de una parada dado su id
	public paradaTranviaVO infoTranvia(int id) {
		Connection conn = null;
		paradaTranviaVO estacion = null;

		try {
			conn = PoolConnectionManager.getConnection();
			// Obtenemos la parada de la DB según su id
			PreparedStatement lsEst = conn.prepareStatement(info);
			lsEst.setInt(1, id);
			ResultSet rs = lsEst.executeQuery();
			rs.next();
			estacion = new paradaTranviaVO(rs.getInt("id"), rs.getString("name"), rs.getString("way"), 
								  rs.getInt("orden"), rs.getString("direction"), rs.getDouble("lat"), rs.getDouble("long"));
			rs.close();
			lsEst.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}
		return estacion;
	}

	// Añade una parada de tranvia a la base de datos
	public void anyadir(paradaTranviaVO parada) throws SQLException {
		Connection conn = null;

		try {

			conn = PoolConnectionManager.getConnection();
			
			PreparedStatement addParada = conn.prepareStatement(insertar);

			addParada.setInt(1, parada.getID());
			addParada.setString(2, parada.getNombre());
			addParada.setString(3, parada.getSentido());
			addParada.setInt(4, parada.getOrden());
			addParada.setString(5, parada.getDireccion());
			addParada.setDouble(6, parada.getLatitud()); 
			addParada.setDouble(7, parada.getLongitud()); 
			
			addParada.executeUpdate();
			addParada.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}
	}
}
