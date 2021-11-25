package es.unizar.sisinf.grpV2_B.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import es.unizar.sisinf.grpV2_B.db.PoolConnectionManager;

// Clase DAO para la gestión de usuarios
public class usuarioDAO {
	private static String countByUserName = "SELECT count(*) cuenta FROM Usuario WHERE name = ?";
	private static String findByUserName = "SELECT * FROM Usuario WHERE name = ?";
	private static String insertar = "insert into Usuario (name, password) values (?, ?)";

	public int validateUser(usuarioVO user) {
		int result = 0;
		try {
			// Abrimos la conexión e inicializamos los parámetros
			Connection conn = PoolConnectionManager.getConnection();

			PreparedStatement countPs = conn.prepareStatement(countByUserName);
			PreparedStatement findPs = conn.prepareStatement(findByUserName);
			countPs.setString(1, user.getNombreUsuario());
			findPs.setString(1, user.getNombreUsuario());

			// Ejecutamos la consulta
			ResultSet findRs = findPs.executeQuery();
			ResultSet countRs = countPs.executeQuery();

			// Leemos cuántos registros tenemos
			countRs.next();
			int n = countRs.getInt(1);
			System.out.println("Número de registros: " + n);

			// Si solo hay un resultado, la query es correcta
			if (n == 1) {
				// Comparamos contraseñas
				findRs.next();
				String dbpwd = findRs.getString("password");
				System.out.println("Contraseñas: " + user.getPassword() + ":" + dbpwd);
				if (dbpwd.contentEquals(user.getPassword())) {
					result = 0; // Datos correctos
				}
				else result = 2;	 // Contraseña incorrecta
			}
			else result = 1; // Usuario no existe

			// liberamos los recursos utilizados
			findRs.close();
			findPs.close();
			countRs.close();
			countPs.close();
			PoolConnectionManager.releaseConnection(conn);

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		return result;
	}
	
	public void anyadir(usuarioVO user) {
		Connection conn = null;

		try {
			conn = PoolConnectionManager.getConnection();
			
			PreparedStatement addEst = conn.prepareStatement(insertar);
			addEst.setString(1, user.getNombreUsuario());
			addEst.setString(2, user.getPassword());

			addEst.executeUpdate();
			addEst.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}
	}
}
