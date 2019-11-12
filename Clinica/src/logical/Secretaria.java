package logical;

import java.util.ArrayList;

public class Secretaria extends Empleado {
	private ArrayList<String> idDoctores;

	public Secretaria(String idEmpleado, String nombre, String username, String password, String consultorio,
			ArrayList<String> idDoctores) {
		super(idEmpleado, nombre, username, password, consultorio);
		this.idDoctores = idDoctores;
	}

	public ArrayList<String> getIdDoctores() {
		return idDoctores;
	}

	public void setIdDoctores(ArrayList<String> idDoctores) {
		this.idDoctores = idDoctores;
	}
	
	
}
