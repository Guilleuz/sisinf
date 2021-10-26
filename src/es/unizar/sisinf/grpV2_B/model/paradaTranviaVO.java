package es.unizar.sisinf.grpV2_B.model;

import org.postgis.*;

public class paradaTranviaVO extends estacion{
    private String nombre;
    private String sentido;

    // Constructor
    public paradaTranviaVO(int id, String nombre, String sentido, String dir, PGgeometry localizacion) {
        super(id, dir, localizacion);
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