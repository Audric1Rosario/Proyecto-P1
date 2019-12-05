package logical;

import java.io.Serializable;

public class Vacuna implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String tipo;
	private String enfermedadNombre;
	private String efectos;
	private boolean listar;		// Listar para el borrado lógico.
	private boolean hecha;		// Esta variable es para saber si el paciente tiene esta vacuna o no.
	
	public Vacuna(String nombre, String tipo, String enfermedadNombre, String efectos) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.enfermedadNombre = enfermedadNombre;
		this.efectos = efectos;
		this.listar = true;
		this.hecha = false;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public String getEnfermedadNombre() {
		return enfermedadNombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setEnfermedadNombre(String enfermedadNombre) {
		this.enfermedadNombre = enfermedadNombre;
	}

	public String getEfectos() {
		return efectos;
	}

	public void setEfectos(String efectos) {
		this.efectos = efectos;
	}

	public boolean isListar() {
		return listar;
	}

	public void setListar(boolean listar) {
		this.listar = listar;
	}

	public boolean isHecha() {
		return hecha;
	}

	public void setHecha(boolean hecha) {
		this.hecha = hecha;
	}
	
}
