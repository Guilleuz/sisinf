package es.unizar.sisinf.grpV2_B.model;

import java.sql.*;
import org.postgis.*;
import org.postgresql.util.PGobject;
import java.util.LinkedList;
import java.util.List;
import es.unizar.sisinf.grpV2_B.db.PoolConnectionManager;

public class paradaTranviaDAO {

	private static String insertar = "INSERT INTO TramStation (id, name, way, orden, direction, localization) VALUES(?,?,?,?,?,?))";
	private static String lista = "SELECT * FROM TramStation ORDER BY id ASC";
	private static String listaSentidos = "SELECT DISTINCT way FROM TramStation";
	private static String listaOrdenada = "SELECT id, name, way, orden, direction, localization::text FROM TramStation WHERE way = ? ORDER BY orden";
	private static String idParada = "SELECT id FROM TramStation WHERE name = ? AND way = ?";
	private static String info = "SELECT id, name, orden, way, direction, localization::text FROM TramStation WHERE id = ?";
	
	// devuelve id de parada segun nombre y sentido
	public int idParada(String nombre, String sentido) throws SQLException {
		int identificador = 0;
		Connection conn = null;
		try {

			conn = PoolConnectionManager.getConnection();
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

	// devuelve lista de paradas ordenada del trayecto en un sentido
	public List<paradaTranviaVO> listarOrdenada(String sentido) throws SQLException {

		List<paradaTranviaVO> listaParadas = new LinkedList<paradaTranviaVO>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			PreparedStatement lsParadas = conn.prepareStatement(listaOrdenada);
			lsParadas.setString(1, sentido);
			ResultSet rs = lsParadas.executeQuery();

			while (rs.next()) {
				paradaTranviaVO parada = new paradaTranviaVO(rs.getInt("id"), rs.getString("name"), rs.getString("way"), rs.getInt("orden"),
						rs.getString("direction"), new PGgeometry(rs.getString("localization")));
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

	// devuelve una lista de los sentidos
	public List<String> listarSentidos() throws SQLException {
		List<String> lista = new LinkedList<String>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			
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

	// devuelve lista de paradas de tranvia
	public List<paradaTranviaVO> listar() throws SQLException {

		List<paradaTranviaVO> listaParadas = new LinkedList<paradaTranviaVO>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			
			PreparedStatement lsParadas = conn.prepareStatement(lista);

			ResultSet rs = lsParadas.executeQuery();

			while (rs.next()) {
				paradaTranviaVO parada = new paradaTranviaVO(rs.getInt("id"), rs.getString("name"), rs.getString("way"), rs.getInt("orden"),
						rs.getString("direction"), (PGgeometry) rs.getObject("localization"));
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

	// añade parada de tranvia a la base de datos
	public void anyadir(paradaTranviaVO parada) throws SQLException {
		Connection conn = null;

		try {

			conn = PoolConnectionManager.getConnection();
			
			PreparedStatement addParada = conn.prepareStatement(insertar);
			ResultSet rs = addParada.executeQuery();

			addParada.setString(1, Integer.toString(parada.getID()));
			addParada.setString(2, parada.getNombre());
			addParada.setString(3, parada.getSentido());
			addParada.setString(4, Integer.toString(parada.getOrden()));
			addParada.setString(5, parada.getDireccion());
			addParada.setObject(6, parada.getLocalizacion());
			addParada.executeUpdate();

			rs.close();
			addParada.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}
	}

	public paradaTranviaVO infoTranvia(int id) {
		Connection conn = null;
		paradaTranviaVO estacion = null;

		try {
			conn = PoolConnectionManager.getConnection();
			
			PreparedStatement lsEst = conn.prepareStatement(info);
			lsEst.setInt(1, id);
			ResultSet rs = lsEst.executeQuery();
			rs.next();
			estacion = new paradaTranviaVO(rs.getInt("id"), rs.getString("name"), rs.getString("way"), 
								  rs.getInt("orden"), rs.getString("direction"), new PGgeometry(rs.getString("localization")));
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
}
