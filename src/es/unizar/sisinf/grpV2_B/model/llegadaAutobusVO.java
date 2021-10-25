public class llegadaAutobusVO {
    private int parada;
    private String linea;
    private String sentido;
    private String primero;
    private String segundo;

    public llegadaAutobusVO(int parada, String linea, String sentido, String primero, String segundo) {
        this.parada = parada;
        this.linea = linea;
        this.sentido = sentido;
        this.primero = primero;
        this.segundo = segundo;
    }

    // Métodos get
    public int getParada() {
        return parada;
    }

    public String getLinea() {
        return linea;
    }

    public String getSentido() {
        return sentido;
    }

    public String getPrimero() {
        return primero;
    }

    public String getSegundo() {
        return segundo;
    }


    // Métodos set
    public void setParada(int parada) {
        this.parada = parada;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    public void setPrimero(String primero) {
        this.primero = primero;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }
}
