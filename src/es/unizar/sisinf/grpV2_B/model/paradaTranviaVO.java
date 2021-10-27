package es.unizar.sisinf.grpV2_B.model;

import org.postgis.*;

public class paradaTranviaVO extends estacion {
	private String nombre;
	private String sentido;
	private int orden;

	// Constructor
	public paradaTranviaVO(int id, String nombre, String sentido, int orden, String dir, PGgeometry localizacion) {
		super(id, dir, localizacion);
		this.nombre = nombre;
		this.sentido = sentido;
		this.orden = orden;
	}

	// Métodos get
	public String getNombre() {
		return nombre;
	}

	public String getSentido() {
		return sentido;
	}

	public int getOrden() {
		return orden;
	}

	// Métodos set
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSentido(String sentido) {
		this.sentido = sentido;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}
}