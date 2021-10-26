package es.unizar.sisinf.grpV2_B.model;

import org.postgis.*;

public class paradaBusVO extends estacion{

    public paradaBusVO(int id, String direccion, PGgeometry localizacion) {
        super(id, direccion, localizacion);
    }
    
    public int getNPoste() {
        return this.getID();
    }
}
