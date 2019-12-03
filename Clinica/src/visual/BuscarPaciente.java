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
import java.text.ParseException;

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

	// Campos de texto
	private JTextField textFieldNombre2;
	private JTextField txtfieldGenero;
	private JTextField textFieldEdad;
	private JTextField textFieldSector;

	// Botones
	private JButton okButton;
	private JButton cancelButton;
	private JButton btnEliminar;

	// Variables logicas
	private Paciente paciente = null;
	private Empleado empleadoActual;
	private ArrayList<Paciente> pacientes;
	private boolean condicion;

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
	public BuscarPaciente(Empleado empleadoActual, Boolean buscar) {
		this.empleadoActual = empleadoActual;
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarPaciente.class.getResource("/image/caduceus.png")));
		setTitle("Buscar Paciente");
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);

		//System.out.println(Clinica.getInstance().getPacientes().size());
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 60, 500, 337);
		contentPanel.add(scrollPane);

		tabla = new JTable(){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}};;

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
			lblNombre.setBounds(10, 11, 123, 14);
			contentPanel.add(lblNombre);

			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(10, 29, 310, 20);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);

			JButton btnBuscarCliente = new JButton("");
			btnBuscarCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Clinica.getInstance().getPacientes().isEmpty()) {
						JOptionPane.showMessageDialog(null, "No existe ningun paciente", "Notificación", JOptionPane.INFORMATION_MESSAGE);
					}else if(Clinica.getInstance().buscarPacienteByNombre(textFieldNombre.getText()).isEmpty()) {
						JOptionPane.showMessageDialog(null, "No existe ningun paciente con ese nombre", "Notificación", JOptionPane.INFORMATION_MESSAGE);
					}else if(textFieldNombre.getText().equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null, "Campos del paciente vacios", "Notificación", JOptionPane.INFORMATION_MESSAGE);
					} else {
						condicion = true;
						pacientes = Clinica.getInstance().buscarPacienteByNombre(textFieldNombre.getText());
						loadTable(pacientes, null, condicion);

						if(!pacientes.isEmpty()) {
							tabla.addMouseListener(new MouseAdapter() {
								@Override
								public void mousePressed(MouseEvent e) {
									textFieldNombre2.setText(pacientes.get(tabla.getSelectedRow()).getNombre());
									textFieldEdad.setText(Integer.toString(pacientes.get(tabla.getSelectedRow()).getEdad()));
									textPaneDireccion.setText(pacientes.get(tabla.getSelectedRow()).getDireccion());
									textFieldSector.setText(pacientes.get(tabla.getSelectedRow()).getSector());
									txtfieldGenero.setText(pacientes.get(tabla.getSelectedRow()).getSexo());
									paciente = pacientes.get(tabla.getSelectedRow());
								}
							});
						}
					}
				}
			});
			btnBuscarCliente.setIcon(new ImageIcon(BuscarPaciente.class.getResource("/image/magnifying-glass.png")));
			btnBuscarCliente.setBounds(330, 29, 89, 23);
			contentPanel.add(btnBuscarCliente);

			JLabel lblIdentificacin = new JLabel("Identificaci\u00F3n:");
			lblIdentificacin.setBounds(442, 11, 113, 14);
			contentPanel.add(lblIdentificacin);

			textFieldIdentificacion = new JTextField();
			textFieldIdentificacion.setBounds(442, 29, 153, 20);
			contentPanel.add(textFieldIdentificacion);
			textFieldIdentificacion.setColumns(10);

			JButton btnBuscarIdentificacion = new JButton("");
			btnBuscarIdentificacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Clinica.getInstance().buscarPacienteById(textFieldIdentificacion.getText()) == null) {
						JOptionPane.showMessageDialog(null, "No existe ningun paciente con esa identificación", "Notificación", JOptionPane.INFORMATION_MESSAGE);
					}else if(textFieldIdentificacion.getText().equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null, "Campos del paciente vacios", "Notificación", JOptionPane.INFORMATION_MESSAGE);
					} else if (Clinica.getInstance().buscarPacienteById(textFieldIdentificacion.getText()) != null) {
						condicion = false;
						paciente = Clinica.getInstance().buscarPacienteById(textFieldIdentificacion.getText());
						loadTable(null, paciente, condicion);
						textFieldNombre2.setText(paciente.getNombre());
						textFieldEdad.setText(Integer.toString(paciente.getEdad()));
						textPaneDireccion.setText(paciente.getDireccion());
						textFieldSector.setText(paciente.getSector());
						txtfieldGenero.setText(paciente.getSexo());
					}
				}
			});
			btnBuscarIdentificacion.setIcon(new ImageIcon(BuscarPaciente.class.getResource("/image/magnifying-glass.png")));
			btnBuscarIdentificacion.setBounds(605, 26, 89, 23);
			contentPanel.add(btnBuscarIdentificacion);

			loadTable(Clinica.getInstance().getPacientes(), null, true);
			if(!Clinica.getInstance().getPacientes().isEmpty()) {
				tabla.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						textFieldNombre2.setText(Clinica.getInstance().getPacientes().get(tabla.getSelectedRow()).getNombre());
						textFieldEdad.setText(Integer.toString(Clinica.getInstance().getPacientes().get(tabla.getSelectedRow()).getEdad()));
						textPaneDireccion.setText(Clinica.getInstance().getPacientes().get(tabla.getSelectedRow()).getDireccion());
						textFieldSector.setText(Clinica.getInstance().getPacientes().get(tabla.getSelectedRow()).getSector());
						txtfieldGenero.setText(Clinica.getInstance().getPacientes().get(tabla.getSelectedRow()).getSexo());
						paciente = Clinica.getInstance().getPacientes().get(tabla.getSelectedRow());
					}
				});
			}

			{
				JPanel buttonPane = new JPanel();
				buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					okButton = new JButton("Buscar");
					if(buscar) {
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

					} else {
						okButton.setText("Modificar");
						okButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(paciente == null) {
									JOptionPane.showMessageDialog(null, "No se ha elejido ningún paciente", "Notificación", JOptionPane.INFORMATION_MESSAGE);
								}else if(paciente != null){
									RegPaciente cons = null;
									try {
										cons = new RegPaciente(paciente);
									} catch (ParseException e1) {
										e1.printStackTrace();
									}
									cons.setModal(true);
									cons.setVisible(true);
								}
							}
						});
					}

					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					cancelButton = new JButton("Cancelar");

					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					
					btnEliminar = new JButton("Eliminar");
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(paciente == null) {
								JOptionPane.showMessageDialog(null, "No se ha elejido ningún paciente.", "Notificación", JOptionPane.INFORMATION_MESSAGE);
							}else if(paciente != null){
								if(Clinica.getInstance().getPacientes().remove(paciente)) {
									JOptionPane.showMessageDialog(null, "Se ha removido el paciente exitosamente.", "Notificación", JOptionPane.INFORMATION_MESSAGE);
									loadTable(Clinica.getInstance().getPacientes(), null, true);
								} else {
									JOptionPane.showMessageDialog(null, "Ha ocurrido un problema removiendo el paciente.", "Notificación", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					});
					buttonPane.add(btnEliminar);
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
					if (buscar)
						btnEliminar.setVisible(false);
				}
			}
	}


	public static void loadTable(ArrayList<Paciente> pacientes, Paciente pacienteid, boolean condicion) {
		model.setRowCount(0);
		if(condicion) {
			fila = new Object[model.getColumnCount()];

			for (Paciente paciente : pacientes) {
				fila[0] = paciente.getNombre();
				fila[1] = paciente.getCedula();
				fila[2] = paciente.getSeguro();
				fila[3] = paciente.getSexo();

				model.addRow(fila);
			}

			tabla.setModel(model);
		} else if(!condicion) {
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
