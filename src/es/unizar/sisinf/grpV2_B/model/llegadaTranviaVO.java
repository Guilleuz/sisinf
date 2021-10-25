public class llegadaTranviaVO {
    private int parada;
    private String primero;
    private String segundo;

    public llegadaTranviaVO(int parada, String primero, String segundo) {
        this.parada = parada;
        this.primero = primero;
        this.segundo = segundo;
    }

    // Métodos get
    public int getParada() {
        return parada;
    }

    public String getPrimero() {
        return primero;
    }

    public String getSegundo() {
        return segundo;
    }

    // Métodos set
    public void setPrimero(String primero) {
        this.primero = primero;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }

}