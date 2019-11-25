package logical;

import java.io.Serializable;

public class Doctor extends Empleado implements Serializable {
	private static final long serialVersionUID = 1L;
	private int numCitasMax;
	
	public Doctor(String nombre, String username, String password, int numCitasMax) {
		super(nombre, username, password);
		this.numCitasMax = numCitasMax;
	}

	public int getNumCitasMax() {
		return numCitasMax;
	}

	public void setNumCitasMax(int numCitasMax) {
		this.numCitasMax = numCitasMax;
	}
	
}
