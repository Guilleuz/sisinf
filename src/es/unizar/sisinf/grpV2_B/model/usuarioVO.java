package es.unizar.sisinf.grpV2_B.model;

// Clase VO para la gestión de usuarios
public class usuarioVO {
	private String nombreUsuario;
	private String password;

	public usuarioVO(String nombre, String password) {
		this.nombreUsuario = nombre;
		this.password = password;
	}

	// Métodos get
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	// Métodos set
	public String getPassword() {
		return password;
	}
}
