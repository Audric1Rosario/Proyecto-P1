package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logical.CheckListItem;
import logical.CheckListRenderer;
import logical.Paciente;
import logical.Vacuna;

import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class AgregarVacunas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	// Lista
	private JList<CheckListItem> lstVacuna;
	private ArrayList<Vacuna> vacunaNoPuesta;
	private ArrayList<Vacuna> vacunasPaciente;
	
	/* Esta ventana solo se abre desde la consulta. */
	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			AgregarVacunas dialog = new AgregarVacunas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public AgregarVacunas(ArrayList<Vacuna> delPaciente) {
		this.vacunasPaciente = delPaciente;	// Referenciar a esto.
		setTitle("Agregar vacunas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarVacunas.class.getResource("/image/caduceus.png")));
		setResizable(false);
		setBounds(100, 100, 480, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Vacunas sin recetar.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 454, 391);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		lstVacuna = new JList<CheckListItem>();
		scrollPane.setViewportView(lstVacuna);
		lstVacuna.setCellRenderer(new CheckListRenderer());
		lstVacuna.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstVacuna.addMouseListener(new MouseAdapter() {
	      @Override
	      public void mouseClicked(MouseEvent event) {
	        JList list = (JList) event.getSource();
	        int index = list.locationToIndex(event.getPoint());// Get index of item
	                                                           // clicked
	        CheckListItem item = (CheckListItem) list.getModel()
	            .getElementAt(index);
	        item.setSelected(!item.isSelected()); // Toggle selected state
	        list.repaint(list.getCellBounds(index, index));// Repaint cell
	      }
	    });
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Tomar los valores seleccionados.
						takeCheckBoxVacuna();
						dispose();
					}
				});
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
		}
		// Crear la lista con los checkbox.
		initCheckBoxVacuna();
	}
	
	private void initCheckBoxVacuna() {
		if (vacunasPaciente.size() == 0) {
			JOptionPane.showMessageDialog(null, "No hay vacunas en el sistema.", "Consultas", JOptionPane.WARNING_MESSAGE);
			dispose(); // Cerrar
			return;
		}
		
		vacunaNoPuesta = new ArrayList<Vacuna>();
		for (Vacuna vacuna : vacunasPaciente) {
			if (!vacuna.isHecha())
				vacunaNoPuesta.add(vacuna);
		}
		
		if (vacunaNoPuesta.size() == 0) {
			JOptionPane.showMessageDialog(null, "Este paciente ya tiene todas sus vacunas puestas.", "Consultas", JOptionPane.INFORMATION_MESSAGE);
			dispose(); // Cerrar
			return;
		}
		
		DefaultListModel<CheckListItem> model = new DefaultListModel<CheckListItem>();
		lstVacuna.setModel(model);
		for (Vacuna vacuna : vacunaNoPuesta) {
			model.addElement(new CheckListItem(vacuna.getNombre()));
		}
	}
	private void takeCheckBoxVacuna() {
		DefaultListModel<CheckListItem> model = (DefaultListModel<CheckListItem>) lstVacuna.getModel();
		int aux = 0; 
		while (aux < model.getSize()) {
			vacunaNoPuesta.get(aux).setHecha(model.getElementAt(aux).isSelected());					
			aux++;
		}
		
		for (Vacuna vacuna : vacunaNoPuesta) {
			int index = vacunasPaciente.indexOf(vacuna);
			if (index != -1) {
				vacunasPaciente.get(index).setHecha(vacuna.isHecha()); // Pasarlo a la referencia bien.
			}
		}
		JOptionPane.showMessageDialog(null, "Vacunas seleccionadas correctamente.", "Vacunas", JOptionPane.INFORMATION_MESSAGE);
		return;
	}
}


