public class paradaTrayectoVO {
    private int paradaAutobus;
    private int linea;
    private int orden;

    public paradaTrayectoVO(int parada, int linea, int orden) {
        this.paradaAutobus = parada;
        this.linea = linea;
        this.orden = orden;
    }

    // Métodos get
    public int getParada() {
        return paradaAutobus;
    }

    public int getLinea() {
        return linea;
    }

    public int getOrden() {
        return orden;
    }

    // Métodos set
    public void setLinea(int linea) {
        this.linea = linea;
    }
    
    public void setOrden(int orden) {
        this.orden = orden;
    }
}
