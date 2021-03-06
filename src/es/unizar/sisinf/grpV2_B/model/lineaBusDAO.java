package es.unizar.sisinf.grpV2_B.model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import es.unizar.sisinf.grpV2_B.db.PoolConnectionManager;

//Clase DAO para las líneas de autobús
public class lineaBusDAO {

	private static String insert = "INSERT INTO BusLine(id, name, way) VALUES(?,?,?)";
	private static String findByNameAndWay = "SELECT * FROM BusLine WHERE name=? AND way=?";
	private static String findById = "SELECT * FROM BusLine WHERE id=?";
	private static String list = "SELECT * FROM BusLine";
	private static String listWays = "SELECT way FROM BusLine WHERE name=?";
	private static String listarNombres = "SELECT DISTINCT name FROM BusLine ORDER BY name";

	// Devuelve el listado de las lineas de autobus
	public List<lineaBusVO> obtenerListado() throws SQLException {
		List<lineaBusVO> listaLineas = new LinkedList<lineaBusVO>();
		Connection conn = null;

		try {
			// Consultamos la DB para obtener las lineas
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

	// Obtener listado de nombres de línea sin repetidos
	public List<String> obtenerListaNombres() throws SQLException {
		List<String> lista = new LinkedList<String>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			// Consultamos la DB para obtener los nombres de línea
			PreparedStatement st = conn.prepareStatement(listarNombres);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				lista.add(rs.getString("name"));
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
	
	// Devuelve una lista de setidos según una línea
	public List<String> obtenerSentidos(String nombre) throws SQLException {
		List<String> lista = new LinkedList<String>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			// Consultamos la DB para obtener los sentidos de la línea
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

	// Devuelve una linea segun un nombre y sentido dado
	public lineaBusVO obtenerLinea(String nombre, String sentido) throws SQLException {

		Connection conn = null;
		lineaBusVO linea = null;
		try {
			conn = PoolConnectionManager.getConnection();
			// Consultamos la DB para obtener la información de la línea, según nombre y sentido
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

	// Devuelve una linea dado el id
	public lineaBusVO obtenerLineaId(int id) {

		Connection conn = null;
		lineaBusVO linea = null;
		try {

			conn = PoolConnectionManager.getConnection();
			// Consultamos la DB para obtener la información de la línea, según su id
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

	// Añade una nueva linea de autobús
	public void anyadir(lineaBusVO linea) {
		Connection conn = null;
		try {
			conn = PoolConnectionManager.getConnection();
			PreparedStatement st = conn.prepareStatement(insert);
			st.setInt(1, linea.getId());
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
