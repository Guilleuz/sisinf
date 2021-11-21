package es.unizar.sisinf.grpV2_B.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import es.unizar.sisinf.grpV2_B.db.PoolConnectionManager;

public class paradaTrayectoDAO {

	private static String insertar = "INSERT INTO BusRoute (busStop, line, orden) VALUES(?,?,?)";
	private static String lista = "SELECT * FROM BusRoute WHERE line=? ORDER BY orden ASC";

	// Obtener un listado de todas las paradas a partir de un id de linea
	// (ordenadas)
	public List<paradaTrayectoVO> listarParadas(int id) throws SQLException {
		List<paradaTrayectoVO> listaParadas = new LinkedList<paradaTrayectoVO>();
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();

			PreparedStatement lsParadas = conn.prepareStatement(lista);
			lsParadas.setInt(1, id);
			ResultSet rs = lsParadas.executeQuery();

			while (rs.next()) {
				paradaTrayectoVO parada = new paradaTrayectoVO(rs.getInt("busStop"), rs.getInt("line"),
				rs.getInt("orden"));
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

	// Añadir, con nPoste, id linea y orden
	public void anyadir(paradaTrayectoVO parada) throws SQLException {
		Connection conn = null;

		try {

			conn = PoolConnectionManager.getConnection();
			PreparedStatement addParada = conn.prepareStatement(insertar);

			addParada.setInt(1, parada.getParada());
			addParada.setInt(2, parada.getLinea());
			addParada.setInt(3, parada.getOrden());
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
