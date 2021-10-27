package es.unizar.sisinf.grpV2_B.model;

import java.sql.*;
import java.util.*;
import java.lang.*;
import org.postgis.*;

import es.unizar.sisinf.grpV2_B.db.ConnectionManager;

public class paradaBusDAO{

    private static String insert = "INSERT INTO BusStation(id,direction,localitation VALUES(?,?,?)";
	private static String findById = "SELECT * FROM BusStation WHERE id=?";
    private static String list = "SELECT * FROM BusStation";
	
    
    public paradaBusVO obtenerParada(int id){

		Connection conn = null;
        paradaBusVO parada = null;

		try {

			conn = ConnectionManager.getConnection();
            conn.getConnection();
            ((org.postgresql.PGConnection)conn).addDataType("geometry",Class.forName("org.postgis.PGgeometry"));
            PreparedStatement st = conn.PreparedStatement(findById);

            st.setString(1,id);
            ResultSet rs = st.executeQuery();

            parada = new paradaBusVO(rs.getInt("id")
                                                ,rs.getString("direction")
                                                ,(PGgeometry)rs.getObject(3)); 
            rs.close();
            st.close();

		} catch(SQLException se) {
			se.printStackTrace();  
		
		} catch(Exception e) {
			e.printStackTrace(System.err); 
		} finally {
			ConnectionManager.releaseConnection(conn); 
		}
		
		return parada;

    }

    
    public List <paradaBusVO> listar(){

		List<paradaBusVO> listaParadas = new LinkedList<paradaBusVO>();
        Connection conn = null;

		try {
			conn = ConnectionManager.getConnection();
            conn.getConnection();
            ((org.postgresql.PGConnection)conn).addDataType("geometry",Class.forName("org.postgis.PGgeometry"));
            PreparedStatement st = conn.PreparedStatement(list);

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                paradaBusVO parada = new paradaBusVO(rs.getInt("id")
                                                    ,rs.getString("direction")
                                                    ,(PGgeometry)rs.getObject(3));
                listaParadas.add(parada);
            }
            rs.close();
            st.close();

		} catch(SQLException se) {
			se.printStackTrace();  
		
		} catch(Exception e) {
			e.printStackTrace(System.err); 
		} finally {
			ConnectionManager.releaseConnection(conn); 
		}
		
		return listaParadas;
    }
  
    public void anyadir(paradaBusVO parada){
        Connection conn = null;

        try {

            conn = ConnectionManager.getConnection();
            conn.getConnection();
            PreparedStatement st = conn.PreparedStatement(insert);
            ResultSet rs = st.executeQuery();

            st.setString(1, parada.getID());
            st.setString(2, parada.getDireccion());
            st.setString(3, parada.getLocalizacion());

            st.executeUpdate();

            rs.close();
            st.close();

        } catch(SQLException se) {
            se.printStackTrace();  
        
        } catch(Exception e) {
            e.printStackTrace(System.err); 
        } finally {
            ConnectionManager.releaseConnection(conn); 
        }
    }
}