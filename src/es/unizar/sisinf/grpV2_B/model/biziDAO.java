package es.unizar.sisinf.grpV2_B.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import es.unizar.sisinf.grpV2_B.db.*;

public class biziDAO {

    private static String lsEstaciones = "SELECT * FROM BiziStation ORDER BY id ASC";
    
    public List<biziVO> listar(){

		List<biziVO> listaEstaciones = new LinkedList<biziVO>();
        Connection conn = null;

		try {
			conn = ConnectionManager.getConnection();
            conn.getConnection();
            PreparedStatement lsEst = conn.PreparedStatement(lsEstaciones);

            ResultSet rs = lsEst.executeQuery();

            while(rs.next()){
                biziVO estacion = new biziVO(rs.getInt("id")
                                                    ,rs.getInt("capacity")
                                                    ,rs.getInt("available")
                                                    ,rs.getString("direction")
                                                    ,rs.getString("localitation")); // es localization :D
                listaParadas.add(parada);
            }
            rs.close();
            lsEst.close();

		} catch(SQLException se) {
			se.printStackTrace();  
		
		} catch(Exception e) {
			e.printStackTrace(System.err); 
		} finally {
			ConnectionManager.releaseConnection(conn); 
		}
		
		return listaEstaciones;
    }
    
    public void anyadir(biziVO estacion){
        Connection conn = null;

        try {
            conn = ConnectionManager.getConnection();
            conn.getConnection();
            PreparedStatement lsEst = conn.PreparedStatement(insert);
            ResultSet rs = lsEst.executeQuery();

            lsEst.setString(3, estacion.getID(), estacion.getCapacidad(), estacion.getBicis(), estacion.getDireccion(), estacion.getLocalizacion());
            lsEst.executeUpdate();

            rs.close();
            lsEst.close();

        } catch(SQLException se) {
            se.printStackTrace();  
        
        } catch(Exception e) {
            e.printStackTrace(System.err); 
        } finally {
            ConnectionManager.releaseConnection(conn); 
        }
    }
}


// Dado número de estacion -> nbicis disponibles (API)
