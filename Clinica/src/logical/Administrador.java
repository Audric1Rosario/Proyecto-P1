package logical;

public class Administrador extends Empleado {
	
	/*
	 * La variable autoridad, define el alcance del administrador, desde poder crear vacunas,
	 * enfermedades, pacientes, hasta poder manipular los usuarios y crear nuevos usuarios.
	 * 
	 * Con esto se definen las delimitaciones que tiene un administrador y el rango de acci�n que tiene.
	 * 
	 * Admin de nivel 1: Acceso total al sistema, controla todo lo que existe, puede crear otros administradores de nivel inferior.
	 * Admin de nivel 2: Acceso total parcial, solo puede llegar a crear usuarios de tipo doctor y secretario
	 * Admin de nivel 3: Acceso hasta las enfermedades, puede crear enfermedades y vacunas para estas.
	 * Admin de nivel 4: Acceso hasta la creaci�n de pacientes.
	 * 
	 * */
	
	private int autoridad;

	public Administrador(String idEmpleado, String nombre, String username, String password, String consultorio,
			int autoridad) {
		super(idEmpleado, nombre, username, password, consultorio);
		this.autoridad = autoridad;
	}

	public int getAutoridad() {
		return autoridad;
	}

	public void setAutoridad(int autoridad) {
		this.autoridad = autoridad;
	}
}
