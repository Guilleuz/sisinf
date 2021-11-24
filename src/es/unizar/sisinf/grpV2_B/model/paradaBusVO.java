package es.unizar.sisinf.grpV2_B.model;

import org.postgis.*;

//Clase VO para las paradas de autobús
public class paradaBusVO extends estacion{

    public paradaBusVO(int id, String direccion, double latitud, double longitud) {
        super(id, direccion, latitud, longitud);
    }
    
    public int getNPoste() {
        return this.getID();
    }
}
