package logical;

import java.io.Serializable;
import java.util.ArrayList;

public class Secretaria extends Empleado implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> idDoctores;

	public Secretaria(String nombre, String username, String password, ArrayList<String> idDoctores) {
		super(nombre, username, password);
		this.idDoctores = idDoctores;
	}

	public ArrayList<String> getIdDoctores() {
		return idDoctores;
	}

	public void setIdDoctores(ArrayList<String> idDoctores) {
		this.idDoctores = idDoctores;
	}
	
	
}
