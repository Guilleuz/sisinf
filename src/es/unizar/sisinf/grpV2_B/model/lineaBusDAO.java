package es.unizar.sisinf.grpV2_B.model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import es.unizar.sisinf.grpV2_B.db.PoolConnectionManager;

public class lineaBusDAO {

	private static String insert = "INSERT INTO BusLine(id, name, way) VALUES(?,?,?)";
	private static String findByNameAndWay = "SELECT * FROM BusLine WHERE name=? AND way=?";
	private static String findById = "SELECT * FROM BusLine WHERE id=?";
	private static String list = "SELECT * FROM BusLine";
	private static String listWays = "SELECT way FROM BusLine WHERE name=?";

	// devuelve lista de lineas de autobus
	public List<lineaBusVO> obtenerListado() throws SQLException {
		List<lineaBusVO> listaLineas = new LinkedList<lineaBusVO>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			PreparedStatement st = conn.prepareStatement(list);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				lineaBusVO linea = new lineaBusVO(rs.getInt("id"), rs.getString("name"), rs.getString("way"));
				listaLineas.add(linea);
			}
			rs.close();
			st.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}

		return listaLineas;
	}

	// devuelve lista de lineas segun un sentido dado
	public List<String> obtenerSentidos(String nombre) throws SQLException {
		List<String> lista = new LinkedList<String>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement(listWays);
			st.setString(1, nombre);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				lista.add(rs.getString("way"));
			}
			rs.close();
			st.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}

		return lista;

	}

	// devuelve una linea segun un nombre y sentido dado
	public lineaBusVO obtenerLinea(String nombre, String sentido) throws SQLException {

		Connection conn = null;
		lineaBusVO linea = null;
		try {
			conn = PoolConnectionManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement(findByNameAndWay);
			st.setString(1, nombre);
			st.setString(2, sentido);
			ResultSet rs = st.executeQuery();
			rs.next();
			linea = new lineaBusVO(rs.getInt("id"), rs.getString("name"), rs.getString("way"));
			rs.close();
			st.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}

		return linea;

	}

	// devuelve una linea dado el id
	public lineaBusVO obtenerLineaId(int id) {

		Connection conn = null;
		lineaBusVO linea = null;
		try {

			conn = PoolConnectionManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement(findById);
			st.setString(1, Integer.toString(id));
			ResultSet rs = st.executeQuery();
			linea = new lineaBusVO(rs.getInt("id"), rs.getString("name"), rs.getString("way"));
			rs.close();
			st.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}

		return linea;
	}

	// añade una nueva linea de
	public void anyadir(lineaBusVO linea) {
		Connection conn = null;
		try {
			conn = PoolConnectionManager.getConnection();
			PreparedStatement st = conn.prepareStatement(insert);
			st.setString(1, Integer.toString(linea.getId()));
			st.setString(2, linea.getNombre());
			st.setString(3, linea.getSentido());

			st.executeUpdate();

			st.close();
		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}
	}

}
