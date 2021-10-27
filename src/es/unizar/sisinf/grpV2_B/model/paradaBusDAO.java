package es.unizar.sisinf.grpV2_B.model;

import java.sql.*;
import org.postgis.*;
import org.postgresql.util.PGobject;

import java.util.LinkedList;
import java.util.List;

import es.unizar.sisinf.grpV2_B.db.PoolConnectionManager;

public class paradaBusDAO {

	private static String insert = "INSERT INTO BusStation(id,direction,localitation VALUES(?,?,?)";
	private static String findById = "SELECT * FROM BusStation WHERE id=?";
	private static String list = "SELECT * FROM BusStation";

    // devuelve una parada segun el id
	public paradaBusVO obtenerParada(int id) throws SQLException {

		Connection conn = null;
		paradaBusVO parada = null;

		try {

			conn = PoolConnectionManager.getConnection();
			((org.postgresql.PGConnection) conn).addDataType("geometry",
					(Class<? extends PGobject>) Class.forName("org.postgis.PGgeometry"));
			PreparedStatement st = conn.prepareStatement(findById);

			st.setString(1, Integer.toString(id));
			ResultSet rs = st.executeQuery();

			parada = new paradaBusVO(rs.getInt("id"), rs.getString("direction"), (PGgeometry) rs.getObject(3));
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
			((org.postgresql.PGConnection) conn).addDataType("geometry",
					(Class<? extends PGobject>) Class.forName("org.postgis.PGgeometry"));
			PreparedStatement st = conn.prepareStatement(list);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				paradaBusVO parada = new paradaBusVO(rs.getInt("id"), rs.getString("direction"),
						(PGgeometry) rs.getObject(3));
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
			ResultSet rs = st.executeQuery();

			st.setString(1, Integer.toString(parada.getID()));
			st.setString(2, parada.getDireccion());
			st.setObject(3, parada.getLocalizacion());

			st.executeUpdate();

			rs.close();
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