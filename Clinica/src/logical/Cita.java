package logical;
import java.util.Date;

public class Cita {
	private String idPaciente;
	private String idDoctor;
	private Date solicitud; 
	private Date diaConsulta;
	private int numCola;
	
	public Cita(String idPaciente, String idDoctor, Date solicitud, Date diaConsulta, int numCola) {
		super();
		this.idPaciente = idPaciente;
		this.idDoctor = idDoctor;
		this.solicitud = solicitud;
		this.diaConsulta = diaConsulta;
		this.numCola = numCola;
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}

	public Date getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Date solicitud) {
		this.solicitud = solicitud;
	}

	public Date getDiaConsulta() {
		return diaConsulta;
	}

	public void setDiaConsulta(Date diaConsulta) {
		this.diaConsulta = diaConsulta;
	}

	public int getNumCola() {
		return numCola;
	}

	public void setNumCola(int numCola) {
		this.numCola = numCola;
	}
	
}
