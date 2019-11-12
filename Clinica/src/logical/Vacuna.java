package logical;

import java.util.Date;

public class Vacuna {
	private String nombre;
	private String tipo;
	private Date dia;
	
	public Vacuna(String nombre, String tipo, Date dia) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.dia = dia;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public Date getDia() {
		return dia;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}
	
}
