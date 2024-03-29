package logical;

import java.io.Serializable;
import java.util.Date;

public abstract class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String idEmpleado;
	protected String nombre;
	protected String username;
	protected String password;
	protected Date lastConnection;
	protected String imagePath;	// Esto es para salvar donde esta ubicada la imagen.
	
	public Empleado(String nombre, String username, String password) {
		super();
		int cantEmpleados = Clinica.getInstance().getEmpleados().size();
		if (cantEmpleados == 0)
			this.idEmpleado = "E-0"; 
		else {
			this.idEmpleado = "E-" + (Integer.valueOf(Clinica.getInstance().getEmpleados().get(cantEmpleados - 1).getIdEmpleado().substring(2)) + 1);
		}
		this.nombre = nombre;
		this.username = username;
		this.password = password;
		this.imagePath = "";
		this.lastConnection = new Date();
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Date getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}
}
