package es.unizar.sisinf.grpV2_B.model;

public class paradaTranviaDAO {
  
  private static String insertar = "INSERT INTO TramStation (id, name, way, direction, localitation VALUES(?,?,?,?,?))";
  private static String lista = "SELECT * FROM TramStation ORDER BY id ASC";
  private static String listaSentidos = "SELECT way FROM TramStation";
  private static String listaOrdenada = "SELECT * FROM TramStation WHERE way = ?"
  private static String idParada = "SELECT id FROM TramStation WHERE name = ? AND way = ?";
  
  public int idP(String nombre, String sentido) {
  	int identificador = null;
	Connection conn = null;
	try {

		conn = ConnectionManager.getConnection();
	    	conn.getConnection();
	   	PreparedStatement st = conn.PreparedStatement(idParada);

	    	st.setString(1,nombre);
		st.setString(2,sentido);
	    	ResultSet rs = st.executeQuery();

	    	identificador = rs.getString("id");
	    	rs.close();
	    	st.close();

	} catch(SQLException se) {
		se.printStackTrace();  

	} catch(Exception e) {
		e.printStackTrace(System.err); 
	} finally {
		ConnectionManager.releaseConnection(conn); 
	}

	return identificador;

  }
  
  public List<paradaTranviaVO> listarOrdenada(){

	List<paradaTranviaVO> listaParadas = new LinkedList<paradaTranviaVO>();
        Connection conn = null;

	try {
		conn = ConnectionManager.getConnection();
            	conn.getConnection();
		((org.postgresql.PGConnection)conn).addDataType("geometry",Class.forName("org.postgis.PGgeometry"));
            	PreparedStatement lsParadas = conn.PreparedStatement(lista);

            	ResultSet rs = lsParadas.executeQuery();

            	while(rs.next()){
                	paradaTranviaVO parada = new paradaTranviaVO(rs.getInt("id")
                                                    ,rs.getString("name")
                                                    ,rs.getString("way")
                                                    ,rs.getString("direction")
                                                    ,(PGgeometry)rs.getObject("localitation"));
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
  
  public List<String> listarSentidos() {
    List<String> listaSentidos = new LinkedList<String>();
       	Connection conn = null;

	try {
		conn = ConnectionManager.getConnection();
		conn.getConnection();
		PreparedStatement lsSentidos = conn.PreparedStatement(listaSentidos);

		ResultSet rs = lsParadas.executeQuery();

		while(rs.next()){
		String lS = rs.getString("way");
		listaSentidos.add(lS);
        }
    	rs.close();
    	lsSentidos.close();

	} catch(SQLException se) {
		se.printStackTrace();  

	} catch(Exception e) {
		e.printStackTrace(System.err); 
	} finally {
		ConnectionManager.releaseConnection(conn); 
	}

	return listaSentidos;
   
  }
  
  public List<paradaTranviaVO> listar(){

	List<paradaTranviaVO> listaParadas = new LinkedList<paradaTranviaVO>();
        Connection conn = null;

	try {
		conn = ConnectionManager.getConnection();
            	conn.getConnection();
            	PreparedStatement lsParadas = conn.PreparedStatement(lista);

            	ResultSet rs = lsParadas.executeQuery();

           	while(rs.next()){
                	paradaTranviaVO parada = new paradaTranviaVO(rs.getInt("id")
                                                    ,rs.getString("name")
                                                    ,rs.getString("way")
                                                    ,rs.getString("direction")
                                                    ,rs.getString("localitation"));
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
  
  
  public void anyadir(paradaTramVO parada){
        Connection conn = null;

        try {

            conn = ConnectionManager.getConnection();
            conn.getConnection();
            PreparedStatement addParada = conn.PreparedStatement(insertar);
            ResultSet rs = st.executeQuery();

            addParada.setString(1, parada.getID());
	    addParada.setString(2, parada.getNombre());
	    addParada.setString(3, parada.getSentido());
            addParada.setString(4, parada.getDireccion());
	    addParada.setString(5, parada.getLocalizacion());
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


// Obtener el id de la parada segun nombre y sentido
