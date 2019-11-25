package logical;

import java.io.Serializable;

public class Enfermedad implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String sintomas;
	private String diagnostico;
	
	public Enfermedad(String nombre, String sintomas, String diagnostico) {
		super();
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.diagnostico = diagnostico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	
	
	
	

}
