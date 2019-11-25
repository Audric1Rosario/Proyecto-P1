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
	private char sexo;
	private String tipoSangre;
	private float estatura;
	private String direccion;
	private String sector;
	private String telefono;
	private String celular;
	private String email;
	private ArrayList<Enfermedad> enfermedades;
	private String estado;
	private ArrayList<Consulta> historiaClinica;
	
	public Paciente(String idPaciente, String nombre, String cedula, String seguro, int edad, String estadoCivil,
			char sexo, String tipoSangre, float estatura, String direccion, String sector, String telefono,
			String celular, String email,  String estado) {
		super();
		this.idPaciente = idPaciente;
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
		enfermedades = new ArrayList<>();
		this.estado = estado;
		historiaClinica = new ArrayList<>();
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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
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

	public ArrayList<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(ArrayList<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ArrayList<Consulta> getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(ArrayList<Consulta> historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	

}
