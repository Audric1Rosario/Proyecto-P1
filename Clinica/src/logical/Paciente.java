package logical;

import java.io.Serializable;
import java.util.ArrayList;

public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;
	private String idPaciente;
	private String nombre;
	private String cedula;
	private String seguro;
	private int edad;
	private String estadoCivil;
	private String sexo;
	private String tipoSangre;
	private float estatura;
	private String direccion;
	private String sector;
	private String telefono;
	private String celular;
	private String email;
	private ArrayList<String> enfermedades;
	private ArrayList<Consulta> historiaClinica;
	private ArrayList<Vacuna> todasLasVacunas;
	
	public Paciente(String nombre, String cedula, String seguro, int edad, String estadoCivil,
			String sexo, String tipoSangre, float estatura, String direccion, String sector, String telefono,
			String celular, String email, ArrayList<String> enfermedades) {
		super();
		int cantPacientes = Clinica.getInstance().getPacientes().size();
		if (cantPacientes == 0)
			this.idPaciente = "P-0";   // Si no hay pacientes registrados empezar desde 0
		else {
			this.idPaciente = "P-" + (Integer.valueOf(Clinica.getInstance().getPacientes().get(cantPacientes-1).getIdPaciente().substring(2)) + 1);
		}
		
		this.nombre = nombre;
		this.cedula = cedula;
		this.seguro = seguro;
		this.edad = edad;
		this.estadoCivil = estadoCivil;
		this.sexo = sexo;
		this.tipoSangre = tipoSangre;
		this.estatura = estatura;
		this.direccion = direccion;
		this.sector = sector;
		this.telefono = telefono;
		this.celular = celular;
		this.email = email;
		this.enfermedades = enfermedades;
		this.historiaClinica = new ArrayList<>();
		this.todasLasVacunas = (ArrayList<Vacuna>) Clinica.getInstance().getVacunas().clone();	 // Pasarle todas las vacunas de clínica
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getSeguro() {
		return seguro;
	}

	public void setSeguro(String seguro) {
		this.seguro = seguro;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public float getEstatura() {
		return estatura;
	}

	public void setEstatura(float estatura) {
		this.estatura = estatura;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<String> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(ArrayList<String> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public ArrayList<Consulta> getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(ArrayList<Consulta> historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	public ArrayList<Vacuna> getTodasLasVacunas() {
		return todasLasVacunas;
	}

	public void setTodasLasVacunas(ArrayList<Vacuna> todasLasVacunas) {
		this.todasLasVacunas = todasLasVacunas;
	}


}
