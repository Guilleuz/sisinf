package es.unizar.sisinf.grpV2_B.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.unizar.sisinf.grp1.db.ConnectionManager;

public class UserFacade {
     private static String countByUserName = "SELECT count(*) cuenta FROM Usuario WHERE name = ?";
     private static String findByUserName = "SELECT * FROM Usuario WHERE name = ?";
     private static String updateDate = "UPDATE users set last_login = current_timestamp where name = ?";
    
     public boolean validateUser(UserVO user) {
     boolean result = false;
         
     try {
         // Abrimos la conexión e inicializamos los parámetros
         Connection conn = ConnectionManager.getConnection();

         PreparedStatement countPs = conn.prepareStatement(countByUserName);
         PreparedStatement findPs = conn.prepareStatement(findByUserName);
         PreparedStatement updatePs = conn.prepareStatement(updateDate);
         countPs.setString(1, user.getNombreUsuario());
         findPs.setString(1, user.getNombreUsuario());
         updatePs.setString(1, user.getNombreUsuario());
         
         // Ejecutamos la consulta
         ResultSet findRs = findPs.executeQuery();
         ResultSet countRs = countPs.executeQuery();
         
         // Leemos cuántos registros tenemos
         countRs.next();
         int n = countRs.getInt(1);
         System.out.println("Número de registros: " + n);
         
         // Si solo hay un resultado, la query es correcta
         if(n == 1) {
             // Comparamos contraseñas
             findRs.next();
             String dbpwd = findRs.getString("password");
                 if (dbpwd.contentEquals(user.getPassword())) {
                 updatePs.execute();
                 result = true;
                 }
         }
         
         // liberamos los recursos utilizados
         findRs.close();
         findPs.close();
         countRs.close();
         countPs.close();
         updatePs.close();
         PoolConnectionManager.releaseConnection(conn);
         
         } catch(SQLException se) {
            se.printStackTrace();
         
         } catch(Exception e) {
            e.printStackTrace(System.err);
         }
         
         return result;
     }
}
