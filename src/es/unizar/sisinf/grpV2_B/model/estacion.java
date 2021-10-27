package es.unizar.sisinf.grpV2_B.model;

import org.postgis.*;

public class estacion {
    protected int id;
    protected String direccion;
    protected PGgeometry localizacion;

    public estacion(int id, String dir, PGgeometry localizacion) {
        this.id = id;
        this.direccion = dir;
        this.localizacion = localizacion;
    }

    // Métodos get
    public int getID() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public PGgeometry getLocalizacion() {
        return localizacion;
    }
    // Métodos set

    public void setId(int id) {
        this.id = id;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalizacion(PGgeometry localizacion) {
        this.localizacion = localizacion;
    }
}
