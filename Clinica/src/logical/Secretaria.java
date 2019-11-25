package logical;

import java.io.Serializable;
import java.util.ArrayList;

public class Secretaria extends Empleado implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> idDoctores;

	public Secretaria(String idEmpleado, String nombre, String username, String password) {
		super(nombre, username, password);
		this.idDoctores = new ArrayList<String>();
	}

	public ArrayList<String> getIdDoctores() {
		return idDoctores;
	}

	public void setIdDoctores(ArrayList<String> idDoctores) {
		this.idDoctores = idDoctores;
	}
	
	
}
