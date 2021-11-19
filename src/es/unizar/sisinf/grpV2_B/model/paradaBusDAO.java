package es.unizar.sisinf.grpV2_B.model;

import java.sql.*;
import org.postgis.*;
import org.postgresql.util.PGobject;

import java.util.LinkedList;
import java.util.List;

import es.unizar.sisinf.grpV2_B.db.PoolConnectionManager;

public class paradaBusDAO {

	private static String insert = "INSERT INTO BusStation(id,direction, lat, long) VALUES(?,?,?,?)";
	private static String findById = "SELECT id, direction, lat, long FROM BusStation WHERE id=?";
	private static String list = "SELECT * FROM BusStation";
	private static String existePoste = "SELECT COUNT(*) AS total FROM BusStation where id=?";

    // devuelve una parada segun el id
	public paradaBusVO obtenerParada(int id) throws SQLException {

		Connection conn = null;
		paradaBusVO parada = null;

		try {

			conn = PoolConnectionManager.getConnection();
			PreparedStatement st = conn.prepareStatement(findById);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			rs.next();
			parada = new paradaBusVO(rs.getInt("id"), rs.getString("direction"), rs.getDouble("lat"), rs.getDouble("long"));
			rs.close();
			st.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}

		return parada;

	}

    // devuelve una lista de paradas de bus
	public List<paradaBusVO> listar() throws SQLException {

		List<paradaBusVO> listaParadas = new LinkedList<paradaBusVO>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			PreparedStatement st = conn.prepareStatement(list);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				paradaBusVO parada = new paradaBusVO(rs.getInt("id"), rs.getString("direction"),
						rs.getDouble("lat"), rs.getDouble("long"));
				listaParadas.add(parada);
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

		return listaParadas;
	}

    // añade una parada de bus a la base de datos
	public void anyadir(paradaBusVO parada) throws SQLException {
		Connection conn = null;

		try {

			conn = PoolConnectionManager.getConnection();
			PreparedStatement st = conn.prepareStatement(insert);

			st.setInt(1, parada.getID());
			st.setString(2, parada.getDireccion());
			st.setDouble(3, parada.getLatitud()); 
			st.setDouble(4, parada.getLongitud()); 
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
	
	public boolean existeParada(int id) throws SQLException {
		Connection conn = null;

		try {

			conn = PoolConnectionManager.getConnection();
			PreparedStatement st = conn.prepareStatement(existePoste);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			rs.next();
			int total = rs.getInt("total");
			rs.close();
			st.close();
			
			return total == 1;

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}
		
		return false;
	} 
}