package es.unizar.sisinf.grpV2_B.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import es.unizar.sisinf.grpV2_B.db.*;

public class biziDAO {
	
    private static String insertar = "INSERT INTO BiziStation (id, capacity, available, direction, localitation) VALUES(?,?,?,?,?)";
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
            PreparedStatement addEst = conn.PreparedStatement(insertar);
            ResultSet rs = lsEst.executeQuery();

            addEst.setString(1, estacion.getID());
	    addEst.setString(2, estacion.getCapacidad());
	    addEst.setString(3, estacion.getBicis());
	    addEst.setString(4, estacion.getDireccion());
	    addEst.setString(5, estacion.getLocalizacion());
		
            addEst.executeUpdate();

            rs.close();
            addEst.close();

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
