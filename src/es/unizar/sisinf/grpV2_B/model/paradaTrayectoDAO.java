package es.unizar.sisinf.grpV2_B.model;

public class paradaTrayectoDAO {

    private static String insertar = "INSERT INTO BusRoute (busStop, line, orden) VALUES(?,?,?)";
    private static String lista = "SELECT * FROM BusRoute WHERE id=? ORDER BY orden ASC";
        
    public List<paradaTrayectoVO> listarParadas(int id) {
        List<paradaTrayectoVO> listaParadas = new LinkedList<paradaTrayectoVO>();
        Connection conn = null;

	    try {
                conn = ConnectionManager.getConnection();
                conn.getConnection();
               
            	PreparedStatement lsParadas = conn.PreparedStatement(lista);
                lsParadas.setString(1, id);
            	ResultSet rs = lsParadas.executeQuery();

            	while(rs.next()){
                	paradaTrayectoVO parada = new paradaTrayectoVO(rs.getInt("busStop")
                                                    ,rs.getInt("line")
                                                    ,rs.getInt("orden"));
                	listaParadas.add(parada);
		        }
        rs.close();
        lsParadas.close();

	    } catch(SQLException se) {
		se.printStackTrace();  

	    } catch(Exception e) {
		e.printStackTrace(System.err); 
	    } finally {
		ConnectionManager.releaseConnection(conn); 
	    }

	    return listaParadas;
    }
        
    // Añadir, con nPoste, id linea y orden    
    public void anyadir(paradaTrayectoVO parada){
            Connection conn = null;

            try {

                conn = ConnectionManager.getConnection();
                conn.getConnection();
                PreparedStatement addParada = conn.PreparedStatement(insertar);
                ResultSet rs = st.executeQuery();

                addParada.setString(1, parada.getParada());
                addParada.setString(2, parada.getLinea());
                addParada.setString(3, parada.getOrden());
                addParada.executeUpdate();

                rs.close();
                addParada.close();

            } catch(SQLException se) {
                se.printStackTrace();  

            } catch(Exception e) {
                e.printStackTrace(System.err); 
            } finally {
                ConnectionManager.releaseConnection(conn); 
            }
        }
  
    
}

// Obtener un listado todas las paradas a partir de un id de linea (ordenadas)
