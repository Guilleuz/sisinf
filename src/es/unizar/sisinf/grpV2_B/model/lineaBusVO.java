package es.unizar.sisinf.grpV2_B.model;

// Clase VO para las líneas de autobús
public class lineaBusVO{
    private int id;
    private String nombre;
    private String sentido;

    public lineaBusVO(int id, String nombre, String sentido) {
        this.id = id;
        this.nombre = nombre;
        this.sentido = sentido;
    }
    
    // Métodos get
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSentido() {
        return sentido;
    }

    // Métodos set

    public void setId(int id) {
        this.id = id;
     }

    public void setNombre(String nombre) {
       this.nombre = nombre;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }
}
