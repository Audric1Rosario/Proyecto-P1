package logical;

import java.io.Serializable;
import java.util.Date;

public class Enfermedad implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String sintomas;
	private String diagnostico;
	private Date fechaRegistro;
	private boolean isRevision;		// Para saber si está en revisión o no.
	private long cantPacientes;		// Cantidad de pacientes con esta enfermedad
	
	public Enfermedad(String nombre, String sintomas, String diagnostico) {
		super();
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.diagnostico = diagnostico;
		this.fechaRegistro = new Date();	 // El día que se registro esta enfermedad
		this.cantPacientes = 0;
		this.isRevision = false;			// Iniciar con que no está en revisión
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

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public long getCantPacientes() {
		return cantPacientes;
	}

	public void setCantPacientes(long cantPacientes) {
		this.cantPacientes = cantPacientes;
	}

	public boolean isRevision() {
		return isRevision;
	}

	public void setRevision(boolean isRevision) {
		this.isRevision = isRevision;
	}
}
