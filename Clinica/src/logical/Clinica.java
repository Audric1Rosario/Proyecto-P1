package logical;

import java.sql.Date;
import java.util.ArrayList;

// Para manejo de ficheros:
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Clinica implements Serializable {
	private static final long serialVersionUID = 1L;
	/*
	 * Programado por: 
	 * Audric Rosario
	 *
	 **/
	private static Clinica instancia = null;
	private Empleado usuarioActual;
	private ArrayList<Paciente> pacientes;
	private ArrayList<Cita> citas;
	private ArrayList<Consulta> consultas;
	private ArrayList<Empleado> empleados;
	private ArrayList<Vacuna> vacunas;
	
	private Clinica() {
		super();
		// Inicializar 
		pacientes = new ArrayList<Paciente>();
		citas = new ArrayList<Cita>();
		consultas = new ArrayList<Consulta>();
		empleados = new ArrayList<Empleado>();
		vacunas = new ArrayList<Vacuna>();
		usuarioActual = null;
	}
	
	// Getters and setters (para tener en cuenta quien lo esta usando.
	public Empleado getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(Empleado usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	// 0. Crear una sola clase controladora
	// Patrón singleton
	private synchronized static void createInstance() {
		if (instancia == null) {
			instancia = new Clinica();
		}
	}
	
	public static Clinica getInstance() {
		if (instancia == null) 
			createInstance();
		return instancia;
	}
	
	public static void setInstance(Clinica data) {
		if (data != null)
			instancia = data;
	}
	
	// 1. Buscar.

	// Buscar pacientes
	public Paciente buscarPacienteById(String idPaciente) {
		Paciente buscado = null;
		boolean encontrado = false; int aux = 0;
		while (!encontrado && aux < pacientes.size()) {
			if (pacientes.get(aux).getIdPaciente().equalsIgnoreCase(idPaciente)) {
				encontrado = true;
				buscado = pacientes.get(aux);
			}
			aux++;
		}
		return buscado;
	}
	
	public ArrayList<Paciente> buscarPacienteByNombre(String nombrePaciente) {
		ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
		for (Paciente paciente : listaPacientes) {
			if (paciente.getNombre().contains(nombrePaciente)) 
				listaPacientes.add(paciente);
		}
		
		return listaPacientes;
	}
	
	// Buscar las citas
	public ArrayList<Cita> buscarCitaByDoctor(String idDoctor) {
		ArrayList<Cita> citasBuscadas = new ArrayList<Cita>();
		for (Cita cita : citas) {
			if (cita.getIdDoctor().equalsIgnoreCase(idDoctor)) {
				citas.add(cita);
			}
		}	
		return citasBuscadas;
	}
	
	public ArrayList<Cita> buscarCitasByDate(Date inicio, Date fin) {
		ArrayList<Cita> citasBuscadas = new ArrayList<Cita>();
		for (Cita cita : citas) {
			if (cita.getDiaConsulta().after(inicio) && cita.getDiaConsulta().before(fin)) {
				citasBuscadas.add(cita);
			}
		}
		return citasBuscadas;
	}
	
	// Buscar las consultas
	public Consulta buscarConsultaById(String idConsulta) {
		Consulta buscada = null;
		
		return buscada;
	}
	
	public ArrayList<Consulta> buscarConsultasByDate(Date inicio, Date fin) {
		ArrayList<Consulta> consultasBuscadas = new ArrayList<Consulta>();	
		for (Consulta consulta : consultas) {
			if (consulta.getFecha().after(inicio) && consulta.getFecha().before(fin)) {
				consultasBuscadas.add(consulta);
			}
		}
		return consultasBuscadas;
	}
	
	// Buscar los empleados
	public Empleado buscarEmpleadoById(String idEmpleado) {
		Empleado buscado = null;
		boolean encontrado = false; int aux = 0;
		while (!encontrado && aux < empleados.size()) {
			if (empleados.get(aux).getIdEmpleado().equalsIgnoreCase(idEmpleado)) {
				buscado = empleados.get(aux);
				encontrado = true;
			}
			aux++;
		}
		return buscado;
	}
	
	public Empleado buscarEmpleadoByUsername(String userEmpleado) {
		Empleado buscado = null;
		boolean encontrado = false; int aux = 0;
		while (!encontrado && aux < empleados.size()) {
			if (empleados.get(aux).getUsername().equalsIgnoreCase(userEmpleado)) {
				buscado = empleados.get(aux);
				encontrado = true;
			}
			aux++;
		}
		return buscado;
	}
	
	// Buscar las vacunas
	public ArrayList<Vacuna> buscarVacunaByTipo(String tipoVacuna) {
		ArrayList<Vacuna> vacunasBuscadas = new ArrayList<Vacuna>();
		for (Vacuna vacuna : vacunas) {
			if (vacuna.getTipo().equalsIgnoreCase(tipoVacuna)) {
				vacunasBuscadas.add(vacuna);
			}
		}
		
		return vacunasBuscadas;
	}
	
	// 2. Crear.
	public void addEmpleado(Empleado empleado) {
		empleados.add(empleado);
	}
	
	public void addPaciente(Paciente paciente) {
		pacientes.add(paciente);
	}
	
	public void addConsulta(Consulta consulta) {
		consultas.add(consulta);
	}
	
	public void addCita(Cita cita) {
		citas.add(cita);
	}
	
	// 3. Eliminar.
	public boolean delEmpleado(String idEmpleado) {
		Empleado buscado = buscarEmpleadoById(idEmpleado);
		
		if (buscado != null) {
			empleados.remove(buscado);
			return true;
		}
		
		return false;
	}
	
	public boolean delPaciente(String idPaciente) {
		Paciente buscado = buscarPacienteById(idPaciente);
		if (buscado != null) {
			pacientes.remove(buscado);
			return true;
		}
		
		return false;
	}
	
	// Esto solo usar cuando se busque la cita especifica en el programa.
	public void delCita(Cita cita) {
		citas.remove(cita);
	}
}
