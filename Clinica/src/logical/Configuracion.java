package logical;

import java.io.Serializable;

public class Configuracion implements Serializable {	
	private static final long serialVersionUID = 1L;	
	private int maxCitasSist;
	private int maxDoctoresSist;
	private int controlada;
	private int regular;

	/* Clase lógica para los parámetros del sistema
	 * Esto es para guardar los parametros que define el usuario administrador principal
	 * cuando va a denotar los parámetros que sigue la clinica, como por ejemplo: 
	 * 1. ¿Cuál es la cantidad máxima de citas que puede tener un doctor de la clínica?
	 * 2. ¿Cuál es la cantidad de doctores máxima para el que una secretaria puede trabajar?
	 * 3. ¿Cuántos pacientes deben estar afectados por una enfermedad para que esta sea considerada:
	 *  	controlada, regular, grave o crítica?
	 */
	
	public Configuracion() {
		super();
		// Default
		// Usuarios.
		this.maxCitasSist = 15;		
		this.maxDoctoresSist = 5;
		
		// Enfermedades cant de pacientes.
		this.controlada = 10;
		this.regular = 20;
	}

	public int getMaxCitasSist() {
		return maxCitasSist;
	}
	
	public int getMaxDoctoresSist() {
		return maxDoctoresSist;
	}

	public int getControlada() {
		return controlada;
	}

	public int getRegular() {
		return regular;
	}

	public void setMaxCitasSist(int maxCitasSist) {
		this.maxCitasSist = maxCitasSist;
	}
	
	public void setMaxDoctoresSist(int maxDoctoresSist) {
		this.maxDoctoresSist = maxDoctoresSist;
	}

	public void setControlada(int controlada) {
		this.controlada = controlada;
	}

	public void setRegular(int regular) {
		this.regular = regular;
	}
}
