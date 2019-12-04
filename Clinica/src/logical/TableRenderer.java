package logical;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	private int columna ;

	public TableRenderer(int Colpatron)
	{
		this.columna = Colpatron;
	}

	@Override
	public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
	{        
		setBackground(Color.white);
		table.setForeground(Color.black);
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		if(table.getValueAt(row,columna).toString().equalsIgnoreCase("Bajo control")) {
			this.setBackground(Color.BLUE);
		} else if(table.getValueAt(row,columna).toString().equalsIgnoreCase("Nociva")){
			this.setBackground(Color.ORANGE);
		} else if(table.getValueAt(row, columna).toString().equalsIgnoreCase("Epidemia")){
			this.setBackground(Color.RED);
		}
		return this;
	}
}
