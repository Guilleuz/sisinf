public class paradaBusVO extends estacion{

    public paradaBusVO(int id, String direccion, double lat, double lon) {
        super(id, direccion, lon, lat);
    }
    
    public int getNPoste() {
        return this.getID();
    }
}
