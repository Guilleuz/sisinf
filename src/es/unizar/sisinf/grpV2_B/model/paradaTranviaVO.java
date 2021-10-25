public class paradaTranviaVO extends estacion{
    private String nombre;
    private String sentido;

    // Constructor
    public paradaTranviaVO(int id, String nombre, String sentido, String dir, double lon, double lat) {
        super(id, dir, lon, lat);
        this.nombre = nombre;
        this.sentido = sentido;
    }

    // Métodos get
    public String getNombre() {
        return nombre;
    }
    public String getSentido() {
        return sentido;
    }

    // Métodos set
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setSentido(String sentido) {
        this.sentido = sentido;
    }
}