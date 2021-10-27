package es.unizar.sisinf.grpV2_B.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
            PreparedStatement st = conn.PreparedStatement(findById);

            st.setString(1,id);
            ResultSet rs = st.executeQuery();

            parada = new paradaBusVO(rs.getInt("id")
                                                ,rs.getString("direction")
                                                ,rs.get("localitation")); //duda geoometry type?
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
            PreparedStatement st = conn.PreparedStatement(list);

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                paradaBusVO parada = new paradaBusVO(rs.getInt("id")
                                                    ,rs.getString("direction")
                                                    ,rs.get("localitation"));//duda geoometry type?
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

            st.setString(3, parada.getID(), parada.getDireccion(), parada.getLocalizacion());
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