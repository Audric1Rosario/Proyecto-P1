package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logical.Clinica;
import logical.Empleado;
import logical.Paciente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;

public class BuscarPaciente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textFieldIdentificacion;
	private static JTable tabla;
	private static DefaultTableModel model;
	private static Object[] fila;
	private JTextField textFieldNombre2;
	private JTextField txtfieldGenero;
	private JTextField textFieldEdad;
	private JTextField textFieldSector;
	
	// Variables logicas
	private Paciente paciente;
	private Empleado empleadoActual;

	/**
	 * Launch the application.
	 */ /*
	public static void main(String[] args) {
		try {
			BuscarPaciente dialog = new BuscarPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public BuscarPaciente(Empleado empleadoActual) {
		this.empleadoActual = empleadoActual;
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarPaciente.class.getResource("/image/caduceus.png")));
		setTitle("Buscar Paciente");
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 60, 500, 337);
		contentPanel.add(scrollPane);
		
		tabla = new JTable();
		
		String columns [] = {"Nombre", "Identificación", "Seguro", "Sexo"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		tabla.setModel(model);
		scrollPane.setViewportView(tabla);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(520, 61, 174, 337);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setBounds(10, 23, 69, 14);
		panel.add(lblNombre_1);
		
		textFieldNombre2 = new JTextField();
		textFieldNombre2.setEditable(false);
		textFieldNombre2.setBounds(10, 40, 154, 20);
		panel.add(textFieldNombre2);
		textFieldNombre2.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(10, 120, 69, 14);
		panel.add(lblEdad);
		
		JLabel lblGnero = new JLabel("G\u00E9nero:");
		lblGnero.setBounds(10, 71, 58, 14);
		panel.add(lblGnero);
		
		txtfieldGenero = new JTextField();
		txtfieldGenero.setEditable(false);
		txtfieldGenero.setBounds(10, 89, 154, 20);
		panel.add(txtfieldGenero);
		txtfieldGenero.setColumns(10);
		
		textFieldEdad = new JTextField();
		textFieldEdad.setEditable(false);
		textFieldEdad.setBounds(10, 142, 154, 20);
		panel.add(textFieldEdad);
		textFieldEdad.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(10, 173, 80, 14);
		panel.add(lblDireccin);
		
		JTextPane textPaneDireccion = new JTextPane();
		textPaneDireccion.setEditable(false);
		textPaneDireccion.setBounds(10, 191, 154, 76);
		panel.add(textPaneDireccion);
		
		JLabel lblSector = new JLabel("Sector:");
		lblSector.setBounds(10, 278, 69, 14);
		panel.add(lblSector);
		
		textFieldSector = new JTextField();
		textFieldSector.setEditable(false);
		textFieldSector.setBounds(10, 295, 154, 20);
		panel.add(textFieldSector);
		textFieldSector.setColumns(10);
		
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 72, 14);
		contentPanel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(10, 29, 310, 20);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JButton btnBuscarCliente = new JButton("");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldNombre.getText().equalsIgnoreCase("")) {
					 JOptionPane.showMessageDialog(null, "Campos del paciente vacios", "Notificación", JOptionPane.INFORMATION_MESSAGE);
				} else {
				ArrayList<Paciente> pacientes = Clinica.getInstance().buscarPacienteByNombre(textFieldNombre.getText());
				loadTable(pacientes, null);
				
				scrollPane.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						textFieldNombre2.setText(pacientes.get(tabla.getSelectedRow()).getNombre());
						textFieldEdad.setText(Integer.toString(pacientes.get(tabla.getSelectedRow()).getEdad()));
						textPaneDireccion.setText(pacientes.get(tabla.getSelectedRow()).getDireccion());
						textFieldSector.setText(pacientes.get(tabla.getSelectedRow()).getSector());
						paciente = pacientes.get(tabla.getSelectedRow());
					}
				});
				}
			}
		});
		btnBuscarCliente.setIcon(new ImageIcon(BuscarPaciente.class.getResource("/image/magnifying-glass.png")));
		btnBuscarCliente.setBounds(330, 29, 89, 23);
		contentPanel.add(btnBuscarCliente);
		
		JLabel lblIdentificacin = new JLabel("Identificaci\u00F3n:");
		lblIdentificacin.setBounds(442, 11, 89, 14);
		contentPanel.add(lblIdentificacin);
		
		textFieldIdentificacion = new JTextField();
		textFieldIdentificacion.setBounds(442, 29, 153, 20);
		contentPanel.add(textFieldIdentificacion);
		textFieldIdentificacion.setColumns(10);
		
		JButton btnBuscarIdentificacion = new JButton("");
		btnBuscarIdentificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldIdentificacion.getText().equalsIgnoreCase("")) {
					 JOptionPane.showMessageDialog(null, "Campos del paciente vacios", "Notificación", JOptionPane.INFORMATION_MESSAGE);
				} else {
					paciente = Clinica.getInstance().buscarPacienteById(textFieldIdentificacion.getText());
					loadTable(null, paciente);
					textFieldNombre2.setText(paciente.getNombre());
					textFieldEdad.setText(Integer.toString(paciente.getEdad()));
					textPaneDireccion.setText(paciente.getDireccion());
					textFieldSector.setText(paciente.getSector());
				}
			}
		});
		btnBuscarIdentificacion.setIcon(new ImageIcon(BuscarPaciente.class.getResource("/image/magnifying-glass.png")));
		btnBuscarIdentificacion.setBounds(605, 26, 89, 23);
		contentPanel.add(btnBuscarIdentificacion);
		
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Buscar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(paciente == null) {
							JOptionPane.showMessageDialog(null, "No se ha elejido ningún paciente", "Notificación", JOptionPane.INFORMATION_MESSAGE);
						}else if(paciente != null){
							BuscarConsulta cons = new BuscarConsulta(paciente);
							cons.setModal(true);
							cons.setVisible(true);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public static void loadTable(ArrayList<Paciente> pacientes, Paciente pacienteid) {
		model.setRowCount(0);
		if(pacienteid == null) {
			fila = new Object[model.getColumnCount()];
	
			for (Paciente paciente : pacientes) {
					fila[0] = paciente.getNombre();
					fila[1] = paciente.getCedula();
					fila[2] = paciente.getSeguro();
					fila[3] = paciente.getSexo();
	
					model.addRow(fila);
			}
	
			tabla.setModel(model);
		}
		
		if(pacienteid != null) {
			fila = new Object[model.getColumnCount()];
			
					fila[0] = pacienteid.getNombre();
					fila[1] = pacienteid.getCedula();
					fila[2] = pacienteid.getSeguro();
					fila[3] = pacienteid.getSexo();
	
					model.addRow(fila);
	
			tabla.setModel(model);
		}
	}
}
