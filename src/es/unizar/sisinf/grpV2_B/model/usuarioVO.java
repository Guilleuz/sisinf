public class usuarioVO {
    private String nombreUsuario;
    private String password;
    public usuarioVO(String nombre, String password) {
        this.nombreUsuario = nombre;
        this.password = password;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public String getPassword() {
        return password;
    }

    
}
