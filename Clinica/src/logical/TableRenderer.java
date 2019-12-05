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
			this.setBackground(new Color(73, 183, 245));
		} else if(table.getValueAt(row,columna).toString().equalsIgnoreCase("Nociva")){
			this.setBackground(new Color(245, 206, 73));
		} else if(table.getValueAt(row, columna).toString().equalsIgnoreCase("Epidemia")){
			this.setBackground(new Color(249, 149, 124));
		}
		return this;
	}
}
