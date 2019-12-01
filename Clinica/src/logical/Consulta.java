package logical;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Consulta implements Serializable {
	private static final long serialVersionUID = 1L;
	private String idConsulta;
	private String idDoctor;
	private String idPaciente;
	private float costo;
	private Date fecha;
	private ArrayList<Enfermedad> enfermedades;
	private ArrayList<Vacuna> vacunas;
	
	public Consulta(String idConsulta, String idDoctor, String idPaciente, float costo, Date fecha) {
		super();
		this.idConsulta = idConsulta;
		this.idDoctor = idDoctor;
		this.idPaciente = idPaciente;
		this.costo = costo;
		this.fecha = fecha;
		enfermedades = new ArrayList<>();;
		vacunas = new ArrayList<>();
	}

	public String getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(String idConsulta) {
		this.idConsulta = idConsulta;
	}

	public String getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ArrayList<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(ArrayList<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
	}

	public void setVacunas(ArrayList<Vacuna> vacunas) {
		this.vacunas = vacunas;
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}
	
	
	
	
	
	
	
	

}
