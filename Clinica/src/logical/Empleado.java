package logical;

public abstract class Empleado {
	protected String idEmpleado;
	protected String nombre;
	protected String username;
	protected String password;
	protected String consultorio;
	
	public Empleado(String idEmpleado, String nombre, String username, String password, String consultorio) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.username = username;
		this.password = password;
		this.consultorio = consultorio;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(String consultorio) {
		this.consultorio = consultorio;
	}
	
	
}
