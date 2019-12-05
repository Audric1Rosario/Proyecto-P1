package logical;

import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CitasRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	private int columna ;

	public CitasRenderer(int Colpatron)
	{
		this.columna = Colpatron;
	}

	@Override
	public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
	{        
		setBackground(Color.white);
		table.setForeground(Color.black);
		
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		SimpleDateFormat cambiar = new SimpleDateFormat("dd-MM-yyyy");
		Date obtener = null;
		try {
			obtener = cambiar.parse(table.getValueAt(row,columna).toString());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (obtener != null) {
			Date ahora = new Date();
			Date cerca = (Date) ahora.clone();
			cerca = sumarHorasFecha(cerca, 5);
			if (ahora.before(obtener)) {
				if (cerca.before(obtener))
					this.setBackground(new Color(73, 183, 245));
				else 
					this.setBackground(new Color(245, 206, 73));
			} else 
				this.setBackground(new Color(249, 149, 124));
		} else {
			this.setBackground(new Color(238, 242, 218));
		}
		return this;
	}

	// Suma o resta las horas recibidos a la fecha  
	private Date sumarHorasFecha(Date fecha, int dias){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe
		calendar.add(Calendar.DAY_OF_MONTH, dias);  // numero de horas a añadir, o restar en caso de horas<0
		return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
	}
}
