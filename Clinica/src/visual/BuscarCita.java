package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logical.Cita;
import logical.CitasRenderer;
import logical.Clinica;
import logical.Doctor;
import logical.Empleado;
import logical.Paciente;
import logical.Secretaria;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JList;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

public class BuscarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String screenPath;

	// Tabla...
	private JTable tableCitas;
	private DefaultTableModel model;
	private Object row[];
	private CitasRenderer citaRend;
	
	// Paneles
	private JPanel panelCrear;
	private JPanel panelDoctor;
	private JPanel panelBusqueda;
	private JPanel panelModificar;

	// Text Field
	private JTextField txtDoctor;
	private JTextField txtId;
	private JTextField txtPacienteBuscar;
	private JTextField txtPacienteNombre;
	private JTextField txtIdPaciente;

	// Date chooser.
	private JDateChooser dateCita;
	private JDayChooser dayChooserMod;
	
	// Botones
	private JButton btnMaxCitas;
	private JButton btnCrear;
	private JButton btnCerrar;
	private JButton btnBuscar;
	private JButton btnModificar;
	private JButton btnSuspender;
	private JButton btnConsultar;
	
	// Combo box.
	private JComboBox<String> cbxDoctores;

	// Spinner
	private JSpinner spnCitas;

	// Lista
	private JList<String> lstPacientes;
	
	// Label
	private JLabel lblBuscarPaciente;

	// Variables lógicas.
	private Empleado usuarioActual;
	private ArrayList<Doctor> doctoresSecretaria;
	private ArrayList<Cita> citasDoctor;	// Para la cita del doctor seleccionado
	private ArrayList<Paciente> pacientesSecretaria;
	private Doctor seleccionado;
	private boolean creando;
	private boolean modificando;
	private Cita seleccionada;
	
	
	private JTextField txtPacienteModificar;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			BuscarCita dialog = new BuscarCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public BuscarCita(Empleado usuario) {
		this.modificando = false;
		this.creando = false;
		this.seleccionado = null;
		this.usuarioActual = usuario;
		this.pacientesSecretaria = null;
		this.citasDoctor = new ArrayList<Cita>();
		setTitle("Citas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarCita.class.getResource("/image/caduceus.png")));
		setResizable(false);
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		panelBusqueda = new JPanel();
		panelBusqueda.setBorder(new TitledBorder(null, "Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBusqueda.setBounds(10, 11, 246, 72);
		contentPanel.add(panelBusqueda);
		panelBusqueda.setLayout(null);

		cbxDoctores = new JComboBox<String>();		
		cbxDoctores.addItemListener(new ItemListener() {
			// Listening if a new items of the combo box has been selected.
			public void itemStateChanged(ItemEvent event) {
				int index = cbxDoctores.getSelectedIndex();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "Error al escoger doctor", "Citas", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Rellenar los datos del doctor
				if (index > 0) {	// Significa que no esta el "<Seleccione>" en la lista
					txtDoctor.setText(doctoresSecretaria.get(index - 1).getNombre());
					txtId.setText(doctoresSecretaria.get(index - 1).getIdEmpleado());
					spnCitas.setValue(Integer.valueOf(doctoresSecretaria.get(index - 1).getNumCitasMax()));
					btnCrear.setEnabled(true);
					seleccionado = doctoresSecretaria.get(index - 1);
					rellenarTabla(doctoresSecretaria.get(index - 1));
				} else {
					btnCrear.setEnabled(false);
					txtDoctor.setText("");
					txtId.setText("");
					spnCitas.setValue(Integer.valueOf(1));
					rellenarTabla(null);				// Vaciar tabla

				}
			}
		});
		cbxDoctores.setEnabled(false);
		cbxDoctores.setBounds(10, 37, 226, 20);
		panelBusqueda.add(cbxDoctores);

		JLabel lblDoctor = new JLabel("Doctor");
		lblDoctor.setBounds(10, 18, 226, 14);
		panelBusqueda.add(lblDoctor);

		panelDoctor = new JPanel();
		panelDoctor.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDoctor.setBounds(10, 94, 246, 308);
		contentPanel.add(panelDoctor);
		panelDoctor.setLayout(null);

		JLabel lblUserImage = new JLabel("");
		lblUserImage.setBounds(55, 21, 134, 114);
		lblUserImage.setIcon(new ImageIcon(((new ImageIcon(BuscarCita.class.getResource("/image/userA.png"))).getImage()).getScaledInstance(lblUserImage.getWidth(), 
				lblUserImage.getHeight(), Image.SCALE_SMOOTH)));
		panelDoctor.add(lblUserImage);

		txtDoctor = new JTextField();
		txtDoctor.setEditable(false);
		txtDoctor.setBounds(31, 166, 184, 20);
		panelDoctor.add(txtDoctor);
		txtDoctor.setColumns(10);

		JLabel lblNombre = new JLabel("Doctor");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(31, 146, 184, 14);
		panelDoctor.add(lblNombre);

		JLabel lblIddoctor = new JLabel("Identificaci\u00F3n");
		lblIddoctor.setHorizontalAlignment(SwingConstants.CENTER);
		lblIddoctor.setBounds(31, 197, 184, 14);
		panelDoctor.add(lblIddoctor);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(31, 217, 184, 20);
		panelDoctor.add(txtId);

		JLabel lblMximaCantidadDe = new JLabel("M\u00E1xima cantidad de citas");
		lblMximaCantidadDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblMximaCantidadDe.setBounds(31, 249, 184, 14);
		panelDoctor.add(lblMximaCantidadDe);

		spnCitas = new JSpinner();
		spnCitas.setEnabled(false);
		spnCitas.setModel(new SpinnerNumberModel(1, 1, Clinica.getInstance().getOpcionesSistema().getMaxCitasSist(), 1));
		spnCitas.setBounds(31, 274, 134, 20);
		panelDoctor.add(spnCitas);

		btnMaxCitas = new JButton("");
		btnMaxCitas.setToolTipText("Cambiar");
		btnMaxCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usuarioActual instanceof Doctor) {
					if (btnMaxCitas.getIcon().toString().contains("change.png")) {
						spnCitas.setEnabled(true);
						btnMaxCitas.setIcon(new ImageIcon(BuscarCita.class.getResource("/image/check-mark.png")));
					} else {
						spnCitas.setEnabled(false);
						btnMaxCitas.setText("");
						btnMaxCitas.setIcon(new ImageIcon(BuscarCita.class.getResource("/image/change.png")));
						((Doctor)usuarioActual).setNumCitasMax(Integer.valueOf(spnCitas.getValue().toString()));
					}
				}
			}
		});
		btnMaxCitas.setEnabled(false);
		btnMaxCitas.setIcon(new ImageIcon(BuscarCita.class.getResource("/image/change.png")));
		btnMaxCitas.setBounds(175, 274, 40, 23);
		panelDoctor.add(btnMaxCitas);

		JPanel panelTabla = new JPanel();
		panelTabla.setBorder(new TitledBorder(null, "Citas en cola", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTabla.setBounds(266, 11, 438, 391);
		contentPanel.add(panelTabla);
		panelTabla.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelTabla.add(scrollPane, BorderLayout.CENTER);

		String headers[] = {"Doctor", "Paciente", "Fecha", "# de cola"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		citaRend = new CitasRenderer(2);
		tableCitas = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}};;
		tableCitas.setModel(model);
		tableCitas.setDefaultRenderer(Object.class, citaRend);
		tableCitas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				if (tableCitas.getSelectedRow() != -1) {					
					if (!modificando && !creando) {	// Si no está modificando ni creando.
						// Obtener la cita que se selecciona
						seleccionada = citasDoctor.get(tableCitas.getSelectedRow());
						if (seleccionada != null) {
							if (usuarioActual instanceof Secretaria) {
								btnSuspender.setEnabled(true);
								btnModificar.setEnabled(true);
							}
							
							if (usuarioActual instanceof Doctor) {
								btnConsultar.setEnabled(true);
							}						
						}
					}
				}
			}
		});
		tableCitas.getTableHeader().setResizingAllowed(false);
		tableCitas.getTableHeader().setReorderingAllowed(false); 
		scrollPane.setViewportView(tableCitas);

		panelCrear = new JPanel();
		panelCrear.setLayout(null);
		panelCrear.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Crear una cita", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCrear.setBounds(10, 94, 246, 308);
		contentPanel.add(panelCrear);
		panelCrear.setVisible(false);

		txtPacienteBuscar = new JTextField();
		txtPacienteBuscar.setEnabled(false);
		txtPacienteBuscar.setToolTipText("Nombre del paciente");
		txtPacienteBuscar.setBounds(10, 53, 162, 20);
		panelCrear.add(txtPacienteBuscar);
		txtPacienteBuscar.setColumns(10);

		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtPacienteBuscar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, escriba que paciente desea buscar.", "Citas", JOptionPane.WARNING_MESSAGE);
					return;
				}
				// Limpiar todo lo de abajo
				txtPacienteNombre.setText("");
				txtIdPaciente.setText("");
				dateCita.setDate(null);

				// Limpiar la lista de pacientes encontrados
				DefaultListModel<String> nlista = new DefaultListModel<String>();
				lstPacientes.setModel(nlista);
				nlista.clear();
				pacientesSecretaria = Clinica.getInstance().buscarPacienteByNombre(txtPacienteBuscar.getText());
				// Custom sort
				Collections.sort(pacientesSecretaria, (o1, o2) -> o1.getNombre().compareTo(o2.getNombre()));
				for (Paciente paciente : pacientesSecretaria) {
					// Sólo agregar a la lista si no tienen una cita.
					boolean listar = true;
					int aux = 0;
					while (aux < citasDoctor.size() && listar) {
						if (citasDoctor.get(aux).getIdPaciente().equalsIgnoreCase(paciente.getIdPaciente()))
							listar = false;
						aux++;
					}
					if (listar) 
						nlista.addElement(paciente.getNombre());
				}

			}
		});
		btnBuscar.setEnabled(false);
		btnBuscar.setToolTipText("Buscar paciente");
		btnBuscar.setIcon(new ImageIcon(BuscarCita.class.getResource("/image/magnifying-glass.png")));
		btnBuscar.setBounds(182, 52, 40, 23);
		panelCrear.add(btnBuscar);

		JPanel panel = new JPanel();
		panel.setBounds(10, 84, 226, 115);
		panelCrear.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane_1, BorderLayout.CENTER);

		lstPacientes = new JList<String>();
		lstPacientes.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (lstPacientes.getSelectedIndex() != -1 && creando) {
					Paciente paciente = pacientesSecretaria.get(lstPacientes.getSelectedIndex());
					txtPacienteNombre.setText(paciente.getNombre());
					txtIdPaciente.setText(paciente.getIdPaciente());
				}
			}
		});
		scrollPane_1.setViewportView(lstPacientes);

		txtPacienteNombre = new JTextField();
		txtPacienteNombre.setEditable(false);
		txtPacienteNombre.setBounds(31, 229, 184, 20);
		panelCrear.add(txtPacienteNombre);
		txtPacienteNombre.setColumns(10);

		dateCita = new JDateChooser();
		dateCita.setBounds(74, 277, 162, 20);
		panelCrear.add(dateCita);

		JLabel lblPaciente = new JLabel("Nombre");
		lblPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaciente.setBounds(31, 210, 184, 14);
		panelCrear.add(lblPaciente);

		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(10, 260, 54, 14);
		panelCrear.add(lblId);

		txtIdPaciente = new JTextField();
		txtIdPaciente.setText("Identificaci\u00F3n del paciente");
		txtIdPaciente.setEditable(false);
		txtIdPaciente.setColumns(10);
		txtIdPaciente.setBounds(10, 277, 54, 20);
		panelCrear.add(txtIdPaciente);

		lblBuscarPaciente = new JLabel("Buscar paciente");
		lblBuscarPaciente.setBounds(10, 28, 162, 14);
		panelCrear.add(lblBuscarPaciente);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setBounds(74, 260, 162, 14);
		panelCrear.add(lblFecha);
		setLocationRelativeTo(null);

		screenPath = "/image/UserA.png";
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCrear = new JButton("Crear");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Al crear la cita.
						if (usuarioActual instanceof Secretaria) {
							if (!creando && btnCrear.getText().equalsIgnoreCase("Crear")) { // Evaluar que no este creando y que sea aceptar
								crearCita(true);
							} else if (btnCrear.getText().equalsIgnoreCase("Aceptar")) {
								// Tomar los datos y crear una nueva cita.
								if (txtPacienteNombre.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Por favor, seleccione un paciente.", 
											"Citas", JOptionPane.WARNING_MESSAGE);
									return;
								}
								
								if (dateCita.getDate() == null) {
									JOptionPane.showMessageDialog(null, "Por favor, seleccione una fecha.",
											"Citas", JOptionPane.WARNING_MESSAGE);
									return;
								}
								
								if (seleccionado == null) {
									JOptionPane.showMessageDialog(null, "Error al crear cita.", 
											"Citas", JOptionPane.WARNING_MESSAGE);
									return;
								}

								SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
								if (dateCita.getDate().before(new Date()) || 
										formater.format(new Date()).equals(dateCita.getDate())) {
									JOptionPane.showMessageDialog(null, "Por favor, escoja un día mayor a la fecha.", "Citas",
											JOptionPane.WARNING_MESSAGE);
									return;
								}

								// Arreglo de citas para ese día
								ArrayList<Cita> esDia = new ArrayList<Cita>();
								if (citasDoctor.size() != 0) {
									for (Cita enCola : citasDoctor) {
										if (formater.format(enCola.getDiaConsulta()).equalsIgnoreCase(formater.format(dateCita.getDate()))) { // Si esta en el mismo día
											esDia.add(enCola);											
										} 
									}
								}

								if (seleccionado.getNumCitasMax() <= esDia.size()) {
									JOptionPane.showMessageDialog(null, "Este doctor no puede tener mas citas ese día.", "Citas",
											JOptionPane.WARNING_MESSAGE);
									return;
								}

								Cita nueva = new Cita(txtIdPaciente.getText(), seleccionado.getIdEmpleado(), new Date() ,dateCita.getDate(), esDia.size() + 1); 
								Clinica.getInstance().addCita(nueva);
								JOptionPane.showMessageDialog(null, "Cita creada exitosamente.", "Citas", JOptionPane.INFORMATION_MESSAGE);
								rellenarTabla(seleccionado);
								crearCita(false);								
								return;
							}

						}

					}
				});
				
				btnConsultar = new JButton("Ir a la consulta");
				btnConsultar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Si el doctor ya le puede ir a la consulta
						// Activar cuando miguel termine.
						/*
						RegConsulta ventana = new RegConsulta(seleccionada);
						ventana.setModal(true);
						ventana.setVisible(true);
						dispose(); */
						// Mientras tanto.
						btnConsultar.setEnabled(false);
						btnModificar.setEnabled(false);
						btnSuspender.setEnabled(false);
						tableCitas.clearSelection();
					}
				});
				btnConsultar.setEnabled(false);
				buttonPane.add(btnConsultar);
				btnCrear.setEnabled(false);
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Al cerrar o cancelar creación de cita.
						if (btnCerrar.getText().equalsIgnoreCase("Cerrar")) {
							dispose();
							return;
						} else {
							crearCita(false);
							creando = false;
						}
					}
				});

				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (btnModificar.getText().equalsIgnoreCase("Modificar")) {
							if (seleccionada != null) {
								Paciente paciente = Clinica.getInstance().buscarPacienteById(seleccionada.getIdPaciente());
								if (paciente != null) {
									panelModificar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Modificar: " + paciente.getIdPaciente(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
									txtPacienteModificar.setText(paciente.getNombre());								
									Calendar cal = Calendar.getInstance();
									cal.setTime(seleccionada.getDiaConsulta());
									//Calendar.getInstance();
									dayChooserMod.setDay(Calendar.DAY_OF_MONTH);
									
									btnModificar.setText("Aceptar");
									btnCerrar.setText("Volver");
									btnCrear.setEnabled(false);
									btnSuspender.setEnabled(false);
									btnConsultar.setEnabled(false);
									
									// Paneles
									panelModificar.setVisible(true);
									panelCrear.setVisible(false);
									panelDoctor.setVisible(false);
									
									modificando = true;
									
								} else {
									JOptionPane.showMessageDialog(null, "Error al modificar.", "Citas", JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Error al modificar.", "Citas", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							// Comparar
							Calendar cal = Calendar.getInstance();
							cal.setTime(new Date());
							int actual = Calendar.DAY_OF_MONTH;
							if (dayChooserMod.getDay() < actual) {
								JOptionPane.showMessageDialog(null, "Por favor, seleccione un día mayor al actual.", "Citas", JOptionPane.WARNING_MESSAGE);
								return;
							}
							cal.add(Calendar.DAY_OF_MONTH, dayChooserMod.getDay() - actual);
							Date modificada = cal.getTime();					
							seleccionada.setDiaConsulta(modificada);
							btnModificar.setText("Modificar");
							crearCita(false);	// Reiniciar parámetros
							rellenarTabla(seleccionado);	// Doctor seleccionado
							
							modificando = false;
						}
							
						return;
					}
				});
				btnModificar.setEnabled(false);
				buttonPane.add(btnModificar);

				btnSuspender = new JButton("Suspender");
				btnSuspender.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (seleccionada == null) {
							JOptionPane.showMessageDialog(null, "Error al borrar.", "Citas", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						int seleccion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar esta cita?", "Citas", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.ERROR_MESSAGE);
						
						switch (seleccion) {					
							
						case JOptionPane.NO_OPTION:
							JOptionPane.showMessageDialog(null, "La cita de " + seleccionada.getIdPaciente() + " se mantendrá en el sistema." ,
									"Citas", JOptionPane.INFORMATION_MESSAGE);
							return;
						case JOptionPane.CANCEL_OPTION:
							return;
						}
						
						// Proceder a eliminar.
						// Remover de las del doctor
						citasDoctor.remove(seleccionada);
						Clinica.getInstance().getCitas().remove(seleccionada);
						rellenarTabla(seleccionado);
						JOptionPane.showMessageDialog(null, "La cita de " + seleccionada.getIdPaciente() + " fue removida." ,
								"Citas", JOptionPane.INFORMATION_MESSAGE);
						seleccionada = null;	
						crearCita(false);
						return;
					}
				});
				btnSuspender.setEnabled(false);
				buttonPane.add(btnSuspender);
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}

		// Visibilidad segun el usuario.
		if (usuarioActual instanceof Doctor) {
			cbxDoctores.setEnabled(false);
			cbxDoctores.setModel(new DefaultComboBoxModel<String>(new String[] {usuarioActual.getNombre()}));
			txtDoctor.setText(usuarioActual.getNombre());
			txtId.setText(usuarioActual.getIdEmpleado());
			spnCitas.setValue(Integer.valueOf(((Doctor)usuarioActual).getNumCitasMax()));
			btnMaxCitas.setEnabled(true);
			btnCrear.setVisible(false);
			btnModificar.setVisible(false);
			btnSuspender.setVisible(false);
			panelBusqueda.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			seleccionado = (Doctor)usuarioActual;
			rellenarTabla(seleccionado);	// Si es la del doctor entonces pasarle null.
		}

		doctoresSecretaria = new ArrayList<Doctor>();
		if (usuarioActual instanceof Secretaria) {
			// Encontrar todos los doctores.
			Empleado buscar = null;
			DefaultComboBoxModel<String> eleDoctores = new DefaultComboBoxModel<String>();
			for (String doctor : ((Secretaria)usuarioActual).getIdDoctores()) {
				buscar = Clinica.getInstance().buscarEmpleadoById(doctor);
				if (buscar != null) {
					if (buscar instanceof Doctor) {
						doctoresSecretaria.add((Doctor)buscar);
						eleDoctores.addElement(buscar.getNombre());	// Guardar nombre.
					}
				}
			}
			eleDoctores.insertElementAt("<Seleccione>", 0);
			cbxDoctores.setModel(eleDoctores);
			cbxDoctores.setEnabled(true);			
			btnMaxCitas.setVisible(false);
			btnConsultar.setVisible(false);
		}
		cbxDoctores.setSelectedIndex(0);
		
		panelModificar = new JPanel();
		panelModificar.setLayout(null);
		panelModificar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Modificar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelModificar.setBounds(10, 94, 246, 308);
		contentPanel.add(panelModificar);
		
		dayChooserMod = new JDayChooser();
		dayChooserMod.setBounds(35, 104, 168, 133);
		panelModificar.add(dayChooserMod);
		
		txtPacienteModificar = new JTextField();
		txtPacienteModificar.setEditable(false);
		txtPacienteModificar.setBounds(10, 43, 226, 20);
		panelModificar.add(txtPacienteModificar);
		txtPacienteModificar.setColumns(10);
		
		JLabel lblNombre_1 = new JLabel("Nombre");
		lblNombre_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre_1.setBounds(10, 26, 226, 14);
		panelModificar.add(lblNombre_1);
		creando = false;
		crearCita(false);
	}

	private void rellenarTabla(Doctor doctor) {
		// Reiniciar tabla.
		model.setRowCount(0);
		if (doctor != null) {
			encontrarCitas(doctor);
			// Rellenar tabla de acuerdo a esto

			// Primero ordenar las citas en orden de fecha.
			Collections.sort(citasDoctor,  (o1, o2) -> o1.getDiaConsulta().compareTo(o2.getDiaConsulta()));

			// Crear un formateador
			SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");

			// Hacer tabla			
			row = new Object[model.getColumnCount()];
			for (Cita cita : citasDoctor) {
				row[0] = cita.getIdDoctor();
				row[1] = cita.getIdPaciente();
				row[2] = formateador.format(cita.getDiaConsulta());
				row[3] = String.valueOf(cita.getNumCola());
				model.addRow(row);
			}
		} else { // Clear
			// Sólo reiniciar
			citasDoctor = new ArrayList<Cita>();
		}

		return;
	}

	private void crearCita(boolean razon) {
		if (razon) {			
			// Paneles
			panelDoctor.setVisible(false);
			panelModificar.setVisible(false);
			// Panel de la creación de la cita.
			txtPacienteBuscar.setText("");
			txtPacienteBuscar.setEnabled(true);
			txtPacienteNombre.setText("");
			txtIdPaciente.setText("");
			txtPacienteModificar.setText("");
			btnBuscar.setEnabled(true);
			btnCrear.setText("Aceptar");
			btnCerrar.setText("Volver");
			btnCrear.setEnabled(true);
			btnSuspender.setEnabled(false);
			btnModificar.setEnabled(false);
			btnConsultar.setEnabled(false);
			// Vaciar lista
			lstPacientes.setModel(new DefaultListModel<String>());
			panelCrear.setVisible(true);
			
			dateCita.setDate(null);
		} else {	
			// Significa que esta volviendo al panel del doctor.
			// Desactivar
			panelCrear.setVisible(false);
			panelModificar.setVisible(false);
			
			txtPacienteBuscar.setText("");
			txtPacienteBuscar.setEnabled(false);
			txtPacienteNombre.setText("");
			txtIdPaciente.setText("");
			txtPacienteModificar.setText("");
			btnBuscar.setEnabled(false);
			btnCrear.setText("Crear");
			btnCerrar.setText("Cerrar");
			btnCrear.setEnabled(true);
			btnSuspender.setEnabled(false);
			btnModificar.setEnabled(false);
			btnConsultar.setEnabled(false);
			dateCita.setDate(null);
			
			// Volver a poner
			panelDoctor.setVisible(true);			
			rellenarTabla(seleccionado);
		}
		creando = razon;
		return;
	}

	private void encontrarCitas(Doctor doctor) {
		citasDoctor = new ArrayList<Cita>();
		for (Cita cita : Clinica.getInstance().getCitas()) {
			if (cita.getIdDoctor().equalsIgnoreCase(doctor.getIdEmpleado())) {
				citasDoctor.add(cita);
			}
		}
		return;
	}
}
