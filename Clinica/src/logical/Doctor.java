package logical;

public class Doctor extends Empleado {
	private String area;
	private String ocupacion;
	private int numCitasMax;
	
	public Doctor(String idEmpleado, String nombre, String username, String password, String consultorio, String area,
			String ocupacion, int numCitasMax) {
		super(idEmpleado, nombre, username, password, consultorio);
		this.area = area;
		this.ocupacion = ocupacion;
		this.numCitasMax = numCitasMax;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public int getNumCitasMax() {
		return numCitasMax;
	}

	public void setNumCitasMax(int numCitasMax) {
		this.numCitasMax = numCitasMax;
	}
	
	
}
