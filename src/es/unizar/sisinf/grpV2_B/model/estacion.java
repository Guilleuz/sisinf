public class estacion {
    protected int id;
    protected String direccion;
    protected double longitud;
    protected double latitud;

    public estacion(int id, String dir, double lon, double lat) {
        this.id = id;
        this.direccion = dir;
        this.longitud = lon;
        this.latitud = lat;
    }

    // Métodos get
    public int getID() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    // Métodos set
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}
