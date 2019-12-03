package logical;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
// Para manejo de ficheros:
import java.io.Serializable;


public class Clinica implements Serializable {
	private static final long serialVersionUID = 1L;
	/*
	 * Programado por: 
	 * Audric Rosario
	 *
	 **/
	private static Clinica instancia = null;
	private ArrayList<Paciente> pacientes;
	private ArrayList<Cita> citas;
	private ArrayList<Consulta> consultas;
	private ArrayList<Empleado> empleados;
	private ArrayList<Enfermedad> enfermedades;
	private ArrayList<Vacuna> vacunas;
	private Configuracion opcionesSistema;
	
	private Clinica() {
		super();
		// Inicializar 
		this.pacientes = new ArrayList<Paciente>();
		this.citas = new ArrayList<Cita>();
		this.consultas = new ArrayList<Consulta>();
		this.empleados = new ArrayList<Empleado>();
		this.enfermedades = new ArrayList<Enfermedad>();
		this.vacunas = new ArrayList<Vacuna>();
		this.opcionesSistema = new Configuracion();
	}

	// Getters
	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	public ArrayList<Cita> getCitas() {
		return citas;
	}

	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
	}

	public ArrayList<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(ArrayList<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}
	
	public Configuracion getOpcionesSistema() {
	    return opcionesSistema;
	}

	public void setOpcionesSistema(Configuracion opcionesSistema) {
	    this.opcionesSistema = opcionesSistema;
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
		boolean encontrado = false; 
		int aux = 0;
		while (aux < pacientes.size() && !encontrado) {
			if (pacientes.get(aux).getIdPaciente().equalsIgnoreCase(idPaciente)) {
				buscado = pacientes.get(aux);
				encontrado = true;
			}
			aux++;
		}
		return buscado;
	}

	public ArrayList<Paciente> buscarPacienteByNombre(String nombrePaciente) {
		ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
		for (Paciente paciente : pacientes) {
			if (paciente.getNombre().toLowerCase().contains(nombrePaciente.toLowerCase())) 
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
	/*
	public ArrayList<Empleado> buscarEmpleadoByNombreTipo(String aBuscar, String tipo) {
		ArrayList<Empleado> buscados = new ArrayList<Empleado>();
		boolean encontrado = false; int aux = 0;
		for (Empleado empleado : empleados) {
			if (empleado.getNombre().contains(aBuscar)) {
				switch (tipo) {
				case "doctor" :
					if (empleado instanceof Doctor)
						buscados.add(empleado);
					break;
				case "secretaria" :
					if (empleado instanceof Secretaria)
						buscados.add(empleado);
					break;
				case "administrador" :
					if (empleado instanceof Administrador)
						buscados.add(empleado);
				}
			}
		}
		return buscados;
	}*/

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

	public int buscarIndexEmpleado(String idEmpleado) {

		int index = 0; boolean encontrado = false; 

		while (!encontrado && index < empleados.size()) {
			if (empleados.get(index).getIdEmpleado().equalsIgnoreCase(idEmpleado)) {
				encontrado = true;
			}
			index++;
		}
		// Retornar -1 si el indice se salió de rango
		return encontrado ? index - 1 : -1;
	}

	// Buscar las vacunas
	public Vacuna buscarVacunaByNombre(String nombreVacuna) {
		Vacuna buscado = null;
		boolean encontrado = false; int aux = 0;
		while (!encontrado && aux < vacunas.size()) {
			if (vacunas.get(aux).getNombre().equalsIgnoreCase(nombreVacuna)) {
				buscado = vacunas.get(aux);
				encontrado = true;
			}
			aux++;
		}
		return buscado;
	}
	
	// Buscar las enfermedades:
	public ArrayList<Enfermedad> buscarEnfermedadByFilter(String nombreEnfermedad) {	// Filtro de nombre
		ArrayList<Enfermedad> enfermedadesBuscadas = new ArrayList<Enfermedad>();

		if (nombreEnfermedad.equals("")) {
			Collections.copy(enfermedadesBuscadas, enfermedades);
		} else {
			for (Enfermedad enfermedad : enfermedades) {
				if (enfermedad.getNombre().toLowerCase().contains(nombreEnfermedad.toLowerCase())) {
					enfermedadesBuscadas.add(enfermedad);
				}
			}
		}
		return enfermedadesBuscadas;
	}
	
	public Enfermedad buscarEnfermedadByNombre(String nombreEnfermedad) {			// Nombre exacto
		Enfermedad buscado = null;
		boolean encontrado = false; int aux = 0;
		while (!encontrado && aux < enfermedades.size()) {
			if (enfermedades.get(aux).getNombre().equalsIgnoreCase(nombreEnfermedad)) {
				buscado = enfermedades.get(aux);
				encontrado = true;
			}
			aux++;
		}
		return buscado;
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

	public void addEnfermedad(Enfermedad enfermedad) {
		enfermedades.add(enfermedad);
	}
	
	public void addVacuna(Vacuna vacuna) {
		vacunas.add(vacuna);		
	}
	// 3. Eliminar.
	public boolean delPaciente(String idPaciente) {
		Paciente buscado = buscarPacienteById(idPaciente);
		if (buscado != null) {
			pacientes.remove(buscado);
			return true;
		}

		return false;
	}

	public boolean delVacuna(String nombreVacuna) {
		Vacuna buscada = buscarVacunaByNombre(nombreVacuna);
		if (buscada != null) {
			vacunas.remove(buscada);
			return true;
		}
		return false;
	}
	
	// Esto solo usar cuando se busque la cita especifica en el programa.
	public void delCita(Cita cita) {
		citas.remove(cita);
	}

	// 4. Verificaciones.
	public boolean verificarUsuario(String nombreUsuario) {
		int aux = 0;
		boolean existe = false;
		while (!existe && aux < empleados.size()) {
			if (empleados.get(aux).getUsername().equalsIgnoreCase(nombreUsuario)) {
				existe = true;
			}
			aux++;
		}
		return !existe;
	}

	public boolean verificarEnfermedad(String nombreEnfermedad) {
		int aux = 0;
		boolean existe = false;
		while (!existe && aux < enfermedades.size()) {
			if (enfermedades.get(aux).getNombre().equalsIgnoreCase(nombreEnfermedad)) {
				existe = true;
			}
			aux++;
		}
		return !existe;
	}

	public boolean verificarVacuna(String nombreVacuna) {
		int aux = 0;
		boolean existe = false;
		while (!existe && aux < vacunas.size()) {
			if (vacunas.get(aux).getNombre().equalsIgnoreCase(nombreVacuna)) {
				existe = true;
			}
			aux++;
		}
		return !existe;
	}
}
