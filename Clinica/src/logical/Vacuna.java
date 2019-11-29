package logical;

import java.io.Serializable;
import java.util.Date;

public class Vacuna implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String tipo;
	private String enfermedadNombre;
	
	public Vacuna(String nombre, String tipo, String enfermedadNombre) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.enfermedadNombre = enfermedadNombre;
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
	
}
