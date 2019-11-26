package logical;

import java.io.Serializable;

public class Doctor extends Empleado implements Serializable {
	private static final long serialVersionUID = 1L;
	private int numCitasMax;
	private boolean hasSecretaria;
	
	public Doctor(String nombre, String username, String password, int numCitasMax) {
		super(nombre, username, password);
		this.numCitasMax = numCitasMax;
		this.hasSecretaria = false;
	}

	public int getNumCitasMax() {
		return numCitasMax;
	}

	public void setNumCitasMax(int numCitasMax) {
		this.numCitasMax = numCitasMax;
	}

	public boolean isHasSecretaria() {
		return hasSecretaria;
	}

	public void setHasSecretaria(boolean hasSecretaria) {
		this.hasSecretaria = hasSecretaria;
	}
	
}
