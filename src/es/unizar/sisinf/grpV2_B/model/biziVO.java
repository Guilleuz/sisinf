public class biziVO extends estacion {
    private int capacidad;
    private int nBicicletas;

    // Constructor
    public biziVO(int id, int capacidad, int nBicis, String dir, double lon, double lat) {
        super(id, dir, lon, lat);
        this.capacidad = capacidad;
        this.nBicicletas = nBicis;
    }

    // Métodos get
    public int getCapacidad() {
        return capacidad;
    }

    public int getBicis() {
        return nBicicletas;
    }

    // Metodos Set
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setBicis(int nBicicletas) {
        this.nBicicletas = nBicicletas;
    }
};