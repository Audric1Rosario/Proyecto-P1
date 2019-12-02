package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logical.Administrador;
import logical.Clinica;
import logical.Enfermedad;
import logical.Paciente;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;

public class DatosPaciente extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtSearch;
	private ArrayList<Paciente> pacients; 
	private static DefaultTableModel model;
	private static Object[] informacion;
	private static Paciente auxiliar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DatosPaciente dialog = new DatosPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DatosPaciente() {
		setTitle("Paciente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatosPaciente.class.getResource("/image/caduceus.png")));
		setBounds(100, 100, 720, 480);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane))
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
					.addGap(18, 20, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setBounds(156, 42, 113, 23);
		panel_1.add(btnNewButton);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (auxiliar != null) {
					RegPaciente ventana = new RegPaciente();
				//	ventana.setModal(true);
					// ventana.setVisible(true);
				}
			}
		});
		btnModificar.setEnabled(false);
		btnModificar.setBounds(34, 42, 89, 23);
		panel_1.add(btnModificar);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			RegPaciente auxRegPaciente = new RegPaciente();
			auxRegPaciente.setVisible(true);
			}
		});
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(25, 11, 87, 35);
		panel.add(lblNombre);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(25, 37, 172, 20);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnBoton = new JButton("");
		btnBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Paciente paciente : Clinica.getInstance().getPacientes()) {
					if(paciente.getNombre().contains(txtSearch.getText())) {
						table.clearSelection();
						rellenarTabla(txtSearch.getText());
					}
				}
				
			}
		});
		btnBoton.setIcon(new ImageIcon(DatosPaciente.class.getResource("/image/magnifying-glass.png")));
		btnBoton.setBounds(199, 37, 49, 23);
		panel.add(btnBoton);
		
		table = new JTable();
		model = new DefaultTableModel();
		String [] ColumNames = {"ID","Nombre","Edad","Genero","Sangre","Telefono","Celular","Sector","Direccion"};
		model.setColumnIdentifiers(ColumNames);
		table.setModel(model);
		tabla();
		table.getTableHeader().setReorderingAllowed(false);
		
		
		
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	//tabla para ingresar datos	
	private static void tabla() {
			model.setRowCount(0);
			informacion = new Object[model.getColumnCount()];{
				
			for (Paciente patient: Clinica.getInstance().getPacientes()) {
				informacion[0] = patient.getIdPaciente(); 
				informacion[1] = patient.getNombre();
				informacion[2] = patient.getEdad();
				informacion[3] = patient.getTipoSangre();
				informacion[4] = patient.getTelefono();
				informacion[5] = patient.getCelular();
				informacion[6] = patient.getSector();
				informacion[7] = patient.getDireccion();
				model.addRow(informacion);
				
			}	
				
			}
		
		
		}

	public static void rellenarTabla(String nombre) {		
		model.setRowCount(0);
		informacion = new Object[model.getColumnCount()];

		if (!nombre.equals("")) { 
			ArrayList<Paciente> data = Clinica.getInstance().buscarPacienteByNombre(nombre);
			for (Paciente temporal : data) {
				informacion[0] = temporal.getIdPaciente(); 
				informacion[1] = temporal.getNombre();
				informacion[2] = temporal.getEdad();
				informacion[3] = temporal.getTipoSangre();
				informacion[4] = temporal.getTelefono();
				informacion[5] = temporal.getCelular();
				informacion[6] = temporal.getSector();
				informacion[7] = temporal.getDireccion();
				model.addRow(informacion);
			}
		} else {
			for (int i = 0; i < Clinica.getInstance().getPacientes().size(); i++) {
				informacion[0] = Clinica.getInstance().getPacientes().get(i).getIdPaciente();
				informacion[1] = Clinica.getInstance().getPacientes().get(i).getNombre();
				informacion[2] = Clinica.getInstance().getPacientes().get(i).getEdad();
				informacion[3] = Clinica.getInstance().getPacientes().get(i).getTipoSangre();
				model.addRow(informacion);
			}
		}
	}


}

