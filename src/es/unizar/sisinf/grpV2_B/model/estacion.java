package es.unizar.sisinf.grpV2_B.model;

import org.postgis.*;

// Clase estación, de la que heredarán el resto de estaciones o paradas
public class estacion {
    protected int id;
    protected String direccion;
    protected double latitud;
    protected double longitud;

    public estacion(int id, String dir, double lat, double longitud) {
        this.id = id;
        this.direccion = dir;
        this.latitud = lat;
        this.longitud = longitud;
    }

    // Métodos get
    public int getID() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getLatitud() {
        return latitud;
    }
    public double getLongitud() {
        return longitud;
    }
    
    // Métodos set

    public void setId(int id) {
        this.id = id;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalizacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
