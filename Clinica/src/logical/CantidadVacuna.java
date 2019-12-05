package logical;

public class CantidadVacuna {
	private Vacuna vacuna;
	private int cantPacientes;
	
	public CantidadVacuna(Vacuna vacuna) {
		super();
		this.vacuna = vacuna;
		this.cantPacientes = 0;
	}

	public Vacuna getVacuna() {
		return vacuna;
	}

	public int getCantPacientes() {
		return cantPacientes;
	}

	public void setVacuna(Vacuna vacuna) {
		this.vacuna = vacuna;
	}

	public void setCantPacientes(int cantPacientes) {
		this.cantPacientes = cantPacientes;
	}
	
	
}
