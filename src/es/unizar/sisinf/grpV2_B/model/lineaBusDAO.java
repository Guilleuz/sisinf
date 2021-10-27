package es.unizar.sisinf.grpV2_B.model;

import java.sql.*;
import java.util.*;
import java.lang.*;
import org.postgis.*;

import es.unizar.sisinf.grpV2_B.db.ConnectionManager;

public class lineaBusDAO{
    
    private static String insert = "INSERT INTO BusLine(id, name, way) VALUES(?,?,?)";
	private static String findByNameAndWay = "SELECT * FROM BusLine WHERE name=? AND way=?";
    private static String findById = "SELECT * FROM BusLine WHERE id=?";
    private static String list = "SELECT * FROM BusLine";
    private static String listWays = "SELECT way FROM BusLine WHERE name=?";
    
    public List<String> obtenerListado(){
        List<lineaBusVO> listaLineas = new LinkedList<lineaBusVO>();
        Connection conn = null;

		try {
			conn = ConnectionManager.getConnection();
            conn.getConnection();
            PreparedStatement st = conn.PreparedStatement(list);

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                lineaBusVO linea = new lineaBusVO(rs.getInt("id")
                                                    ,rs.getString("name")
                                                    ,rs.get("way"));
                listaLineas.add(linea);
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
		
		return listaLineas;
    }
    
    public List<String> obtenerSentidos(String nombre){
        List<String> lista = new LinkedList<String>();
        Connection conn = null;

		try {
			conn = ConnectionManager.getConnection();
            conn.getConnection();
            PreparedStatement st = conn.PreparedStatement(listWays);
            st.setString(1,nombre);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
		lista.add(rs.getString("way"));
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
		
		return lista;
       
    
    }
    
    public lineaBusVO obtenerLinea(String nombre, String sentido){
    
        Connection conn = null;
        lineaBusVO linea = null;
        try {
            conn = ConnectionManager.getConnection();
            conn.getConnection();
            PreparedStatement st = conn.PreparedStatement(findByNameAndWay);
            st.setString(1,nombre,sentido);
            st.setString(2,sentido);
            ResultSet rs = st.executeQuery();
            linea = new lineaBusVO(rs.getInt("id"),rs.getString("name"),rs.getString("way"));
            rs.close();
            st.close();

        } catch(SQLException se) {
			se.printStackTrace();  
		
		} catch(Exception e) {
			e.printStackTrace(System.err); 
		} finally {
			ConnectionManager.releaseConnection(conn); 
		}
		
		return linea;

    }
    
    public lineaBusVO obtenerLineaId(int id){
    
        Connection conn = null;
        lineaBusVO linea = null;
            try {

                conn = ConnectionManager.getConnection();
                conn.getConnection();
                PreparedStatement st = conn.PreparedStatement(findById);
                st.setString(1,id);
                ResultSet rs = st.executeQuery();
                linea = new lineaBusVO(rs.getInt("id")
                                                ,rs.getString("name")
                                                ,rs.getString("way"));
                rs.close();
                st.close();

            } catch(SQLException se) {
                se.printStackTrace();  
            
            } catch(Exception e) {
                e.printStackTrace(System.err); 
            } finally {
                ConnectionManager.releaseConnection(conn); 
            }
            
            return linea;
    }
    
    public void anyadir (lineaBusVO linea){
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            conn.getConnection();
            PreparedStatement st = conn.PreparedStatement(insert);
            st.setString(1, linea.getID());
            st.setString(2, linea.getNombre());
            st.setString(3, linea.getSentido());

            st.executeUpdate();

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



