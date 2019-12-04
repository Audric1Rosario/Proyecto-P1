package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import logical.Administrador;
import logical.Clinica;
import logical.Doctor;
import logical.Empleado;
import logical.Secretaria;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JSpinner;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Usuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private static JTextField txtCantDoctores;


	// Variables de creación
	private static boolean esAdmin;	// Para saber si se presentaran los administradores o usuarios normales.
	private ArrayList<String> doctorId;
	private ArrayList<String> copyDoctor;
	private Empleado usuarioModificar;

	// Paneles
	private JPanel panelDoctor;
	private JPanel panelSecretaria;
	private JPanel panelAdmin;

	// Combo
	private JComboBox cbxTipoUsuario;

	// Spinner
	private JSpinner spnAutoridad;
	private JSpinner spnCitas;

	// Botones
	private JButton btnCrear;
	private JButton btnCerrar;
	private JButton btnModificar;
	private JButton btnDoctores;
	private JButton btnEliminar;


	// Table 
	private static JTable userTable;
	private static DefaultTableModel model;
	private static Object row[];


	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			Usuarios dialog = new Usuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public Usuarios(boolean esAdmin) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				clear();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/image/caduceus.png")));
		this.doctorId = new ArrayList<String>();
		this.copyDoctor = new ArrayList<String>();
		this.usuarioModificar = null;
		this.esAdmin = esAdmin;
		setTitle("Usuarios");
		setResizable(false);
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 466, 391);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(10, 21, 446, 359);
				panel.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel_1.add(scrollPane, BorderLayout.CENTER);

				userTable = new JTable();				
				userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
					public void valueChanged(ListSelectionEvent event) {
						if (userTable.getSelectedRow() >= 0 ) {
							btnModificar.setEnabled(true);
							btnEliminar.setEnabled(true);
						} else {
							btnModificar.setEnabled(false);
							btnEliminar.setEnabled(false);
						}
					}
				});

				model = new DefaultTableModel();

				String[] headers = {"ID-Usuario", "Nombre", "Ocupación", "Última conexión."};
				String[] headersM = {"ID-Usuario", "Nombre", "Username", "Autoridad"};

				if (!esAdmin)
					model.setColumnIdentifiers(headers);
				else
					model.setColumnIdentifiers(headersM);
				userTable.setModel(model);
				scrollPane.setViewportView(userTable);
			}
			userTable.getTableHeader().setResizingAllowed(false);
			userTable.getTableHeader().setReorderingAllowed(false);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Datos del usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(486, 11, 218, 391);
			contentPanel.add(panel);
			panel.setLayout(null);

			JLabel lblUserImage = new JLabel("");
			lblUserImage.setBounds(53, 23, 107, 98);
			lblUserImage.setIcon(new ImageIcon(((new ImageIcon(Usuarios.class.getResource("/image/userA.png"))).getImage()).getScaledInstance(lblUserImage.getWidth(),
					lblUserImage.getHeight(), Image.SCALE_SMOOTH)));
			panel.add(lblUserImage);

			txtName = new JTextField();
			txtName.setBounds(10, 150, 198, 20);
			panel.add(txtName);
			txtName.setColumns(10);

			txtUsername = new JTextField();
			txtUsername.setBounds(10, 199, 198, 20);
			panel.add(txtUsername);
			txtUsername.setColumns(10);

			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(10, 132, 198, 14);
			panel.add(lblNombre);

			JLabel lblrea = new JLabel("Nombre de usuario: ");
			lblrea.setBounds(10, 181, 198, 14);
			panel.add(lblrea);

			txtPassword = new JTextField();
			txtPassword.setBounds(10, 247, 198, 20);
			panel.add(txtPassword);
			txtPassword.setColumns(10);

			JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
			lblContrasea.setBounds(10, 230, 198, 14);
			panel.add(lblContrasea);

			JLabel lblTipoDeUsuario = new JLabel("Tipo de usuario: ");
			lblTipoDeUsuario.setBounds(10, 278, 198, 14);
			panel.add(lblTipoDeUsuario);

			cbxTipoUsuario = new JComboBox();
			cbxTipoUsuario.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (String.valueOf(cbxTipoUsuario.getSelectedItem()).equalsIgnoreCase("<Seleccione>")) {
						panelDoctor.setEnabled(false);
						panelDoctor.setVisible(false);
						panelSecretaria.setEnabled(false);
						panelSecretaria.setVisible(false);
						btnCrear.setEnabled(false);
					} else if (String.valueOf(cbxTipoUsuario.getSelectedItem()).equalsIgnoreCase("Doctor")) {
						panelDoctor.setEnabled(true);
						panelDoctor.setVisible(true);
						panelSecretaria.setEnabled(false);
						panelSecretaria.setVisible(false);
						btnCrear.setEnabled(true);
					} else if (String.valueOf(cbxTipoUsuario.getSelectedItem()).equalsIgnoreCase("Secretario/a")) {
						panelDoctor.setEnabled(false);
						panelDoctor.setVisible(false);
						panelSecretaria.setEnabled(true);
						panelSecretaria.setVisible(true);
						btnCrear.setEnabled(true);
					}
				}
			});
			cbxTipoUsuario.setToolTipText("");
			if (!esAdmin)
				cbxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Doctor", "Secretario/a"}));
			else
				cbxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"Administrador"}));

			if (esAdmin)
				cbxTipoUsuario.setEnabled(false);
			cbxTipoUsuario.setBounds(10, 296, 198, 20);
			panel.add(cbxTipoUsuario);

			panelSecretaria = new JPanel();
			panelSecretaria.setBounds(10, 327, 198, 53);
			panel.add(panelSecretaria);
			panelSecretaria.setLayout(null);

			txtCantDoctores = new JTextField();
			txtCantDoctores.setEditable(false);
			txtCantDoctores.setBounds(10, 16, 54, 20);
			panelSecretaria.add(txtCantDoctores);
			txtCantDoctores.setColumns(10);

			btnDoctores = new JButton("Seleccione");
			btnDoctores.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (btnModificar.getText().equalsIgnoreCase("Aceptar")) {
						doctorId = ((Secretaria)usuarioModificar).getIdDoctores();
						copyDoctor = new ArrayList<String>();
						// Crear copia
						copyDoctor.addAll(doctorId);
					} else {
						doctorId = new ArrayList<String>();						
					}

					SeleccionarDoctores ventana = new SeleccionarDoctores(btnModificar.getText().equalsIgnoreCase("Aceptar") ? usuarioModificar : null , doctorId);
					ventana.setModal(true);
					ventana.setVisible(true);
				}
			});
			btnDoctores.setToolTipText("Pulse para seleccionar los doctores para los que esta trabaja.");
			btnDoctores.setBounds(74, 15, 114, 23);
			panelSecretaria.add(btnDoctores);

			panelDoctor = new JPanel();
			panelDoctor.setBounds(10, 327, 198, 53);
			panel.add(panelDoctor);
			panelDoctor.setLayout(null);

			spnCitas = new JSpinner();
			spnCitas.setModel(new SpinnerNumberModel(1, 1, Clinica.getInstance().getOpcionesSistema().getMaxCitasSist(), 1));
			spnCitas.setBounds(10, 22, 178, 20);
			panelDoctor.add(spnCitas);

			JLabel lblCitasPorDa = new JLabel("Citas por d\u00EDa:");
			lblCitasPorDa.setBounds(10, 0, 188, 14);
			panelDoctor.add(lblCitasPorDa);

			panelAdmin = new JPanel();
			panelAdmin.setBounds(10, 327, 198, 53);
			panel.add(panelAdmin);
			panelAdmin.setLayout(null);

			spnAutoridad = new JSpinner();
			spnAutoridad.setModel(new SpinnerNumberModel(2, 2, 4, 1));
			spnAutoridad.setBounds(10, 22, 178, 20);
			panelAdmin.add(spnAutoridad);

			JLabel lblAutoridad = new JLabel("Autoridad");
			lblAutoridad.setBounds(10, 5, 178, 14);
			panelAdmin.add(lblAutoridad);

			panelDoctor.setEnabled(false);
			panelDoctor.setVisible(false);
			panelSecretaria.setEnabled(false);

			JLabel lblDoctoresParaLos = new JLabel("Doctores para los que trabaja.");
			lblDoctoresParaLos.setBounds(10, 0, 178, 14);
			panelSecretaria.add(lblDoctoresParaLos);
			panelSecretaria.setVisible(false);
			// Si no es admin
			if (!esAdmin) {
				panelAdmin.setEnabled(false);
				panelAdmin.setVisible(false);
			} else {
				panelAdmin.setEnabled(true);
				panelAdmin.setVisible(true);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clear();
						dispose();
					}
				});

				btnCrear = new JButton("Crear");
				btnCrear.setEnabled(false);
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Validar que este correcto:
						if (btnCrear.getText().equalsIgnoreCase("Crear")) {
							// Que no este vacío
							if (txtName.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
								return;
							}

							if (txtUsername.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre de usuario.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
								return;
							}

							if (txtPassword.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Por favor ingrese una contraseña.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
								return;
							}

							// Username no repetible 
							if (!Clinica.getInstance().verificarUsuario(txtUsername.getText())) {
								JOptionPane.showConfirmDialog(null, "Este nombre de usuario no está disponible.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
								return;
							}

							// Contraseña de entre 8 a 16 caracteres 
							if (txtPassword.getText().length() < 8 || txtPassword.getText().length() > 16) {
								JOptionPane.showConfirmDialog(null, "Por favor, digíte una contraseña de entre 8 a 16 caracteres.", "Advertencia", JOptionPane.WARNING_MESSAGE);
								return;
							}

							// Verificar que si la clase a crear es una secretaria, tenga al menos 1 doctor seleccionado.
							if (String.valueOf(cbxTipoUsuario.getSelectedItem()).equalsIgnoreCase("Secretario/a")) {
								if (doctorId.size() == 0)
								{
									JOptionPane.showConfirmDialog(null, "No es posible crear una secretaria que no esté trabajando para ningún doctor", "Advertencia.", JOptionPane.WARNING_MESSAGE);
									return;
								}
							}

							// Crear la clase
							Empleado nuevo = null;
							switch (String.valueOf(cbxTipoUsuario.getSelectedItem())) {
							case "Administrador":
								nuevo = new Administrador(txtName.getText(), txtUsername.getText(), txtPassword.getText(), Integer.valueOf(spnAutoridad.getValue().toString()));
								break;
							case "Doctor":
								nuevo = new Doctor(txtName.getText(), txtUsername.getText(), txtPassword.getText(), Integer.valueOf(spnCitas.getValue().toString()));
								break;
							case "Secretario/a":
								nuevo = new Secretaria(txtName.getText(), txtUsername.getText(), txtPassword.getText(), doctorId);
								break;
							}

							Clinica.getInstance().addEmpleado(nuevo);
							doctorId = new ArrayList<String>(); 	// Reiniciar arraylist doctores
							clear();
							rellenarTabla();						// Reiniciar la tabla.
						} else { // Volver
							btnModificar.setText("No modificado");
							clear();

						}
					}
				});
				buttonPane.add(btnCrear);

				if (esAdmin)
					btnCrear.setEnabled(true);

				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (btnModificar.getText().equalsIgnoreCase("Modificar")) { // Para modificar datos
							rellenarDatos();
							btnModificar.setText("Aceptar");
							btnCrear.setText("Volver");
							btnCrear.setEnabled(true);
						} else { // cambiar a crear nuevos datos

							if (usuarioModificar != null) {
								usuarioModificar.setNombre(txtName.getText());
								usuarioModificar.setUsername(txtUsername.getText());
								usuarioModificar.setPassword(txtPassword.getText());

								if (usuarioModificar instanceof Administrador) {
									((Administrador)usuarioModificar).setAutoridad(Integer.valueOf(spnAutoridad.getValue().toString()));
								} else if (usuarioModificar instanceof Doctor) {
									((Doctor)usuarioModificar).setNumCitasMax(Integer.valueOf(spnCitas.getValue().toString()));
								} else if (usuarioModificar instanceof Secretaria) {
									((Secretaria)usuarioModificar).setIdDoctores(doctorId);
								}
							}

							doctorId = new ArrayList<String>(); // borrar doctores
							clear();
							rellenarTabla();
							usuarioModificar = null; 		// Volver a reiniciar.
						}
					}


				});
				btnModificar.setEnabled(false);
				buttonPane.add(btnModificar);

				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (userTable.getSelectedRow() >= 0) {
							int ind = Clinica.getInstance().buscarIndexEmpleado(userTable.getValueAt(userTable.getSelectedRow(), 0).toString());
							Empleado empleado = Clinica.getInstance().getEmpleados().get(ind), comprobar;

							// Si es una secretaria, todos los doctores para los que esta trabajaba ya no tienen secretaria.
							if (empleado instanceof Secretaria) {
								for (String doctor : ((Secretaria)empleado).getIdDoctores()) {
									comprobar = Clinica.getInstance().buscarEmpleadoById(doctor);
									if (comprobar instanceof Doctor) {
										((Doctor)comprobar).setHasSecretaria(false);
									}
								}
							}
							// Si es un doctor entonces hay que eliminarlo de la lista de cada secretaria
							if (empleado instanceof Doctor) {
								// Si tiene una secretaria, buscarla y eliminarlo de su lista
								if (((Doctor)empleado).isHasSecretaria()) {
									Secretaria buscar = null;
									boolean encontrar = false;
									int aux = 0;
									// Esto es para poder encontrar la secretaria del doctor y luego poder quitarle el doctor
									while (!encontrar && aux < Clinica.getInstance().getEmpleados().size()) {
										if (Clinica.getInstance().getEmpleados().get(aux) instanceof Secretaria) {
											for (String doctor : ((Secretaria)Clinica.getInstance().getEmpleados().get(aux)).getIdDoctores()) {
												if (doctor.equalsIgnoreCase(empleado.getIdEmpleado())) {
													encontrar = true;
													buscar = ((Secretaria)Clinica.getInstance().getEmpleados().get(aux));
												}
											}
										}
										aux++;
									}

									if (buscar == null) {
										JOptionPane.showMessageDialog(null, "Error al eliminar.", "Usuarios", JOptionPane.ERROR_MESSAGE);
										return;
									} else {
										if (buscar.getIdDoctores().size() - 1 == 0) {

											int seleccion = JOptionPane.showConfirmDialog(null, "Si se elimina este doctor, la secretaria: (" + (buscar.getIdEmpleado()) + 
													") no tendrá para quien trabajar,\n y por lo tanto su cuenta será eliminada del sistema.\n\n ¿Está seguro de proceder?",
													"Usuarios", JOptionPane.YES_NO_OPTION,	JOptionPane.WARNING_MESSAGE);
											switch (seleccion) {
											case JOptionPane.YES_OPTION:
												// Eliminar la secre.
												Clinica.getInstance().getEmpleados().remove(buscar);
												
												break;

											case JOptionPane.NO_OPTION:
												JOptionPane.showMessageDialog(null, "El doctor (" + empleado.getIdEmpleado() + ") se mantendrá en el sistema.", "Usuarios",
														JOptionPane.INFORMATION_MESSAGE);
												return;	// Salir, ya que decidió eso

											default:
												return;	// Salir para no borrar nada.
											}										
										} else {
											int indexSecre = buscar.getIdDoctores().indexOf(empleado.getIdEmpleado());
											
											if (indexSecre == -1) {
												JOptionPane.showMessageDialog(null, "Error al borrar.", "Usuarios", JOptionPane.ERROR_MESSAGE);
												return;
											} else {
												buscar.getIdDoctores().remove(indexSecre);												
											}
										}
									}
									

								}
							}

							if (ind != -1)
								Clinica.getInstance().getEmpleados().remove(ind);
						}
						clear();
						rellenarTabla();
					}
				});
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		rellenarTabla();
	}

	public static JTextField getTxtCantDoctores() {
		return txtCantDoctores;
	}

	private void clear() {
		// Limpiar los doctores (si se estuvo modificando)
		if (doctorId != null) {
			if (doctorId.size() > 0) {
				// Limpiar todos los doctores seleccionados y volver a poner que no tienen secretaria
				if (!btnModificar.getText().equalsIgnoreCase("No modificado") && copyDoctor.size() > 0) {

					// Esto significa que al momento de modificar los doctores se decidió volver.
					for (int i = 0; i < doctorId.size(); i++) {
						boolean estaEnLista = false; 
						int aux = 0;
						while (aux < copyDoctor.size() && !estaEnLista) {							
							if (copyDoctor.get(aux).equalsIgnoreCase(doctorId.get(i)))
								estaEnLista = true;
							aux++;
						}

						if (!estaEnLista) {
							// Si no está en la lista anterior, devolver a su estado anterior
							((Doctor)Clinica.getInstance().buscarEmpleadoById(doctorId.get(i))).setHasSecretaria(false);
						}

					}
				} else {
					// Si al momento de crear se cancela o se cierra la ventana...
					for (String idEmpleado : doctorId) {
						((Doctor)Clinica.getInstance().buscarEmpleadoById(idEmpleado)).setHasSecretaria(false);;
					}

				}
			}
		}

		userTable.clearSelection();
		txtName.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
		txtCantDoctores.setText("");
		btnModificar.setText("Modificar");
		btnCrear.setText("Crear");

		// Botones
		btnDoctores.setEnabled(true);
		btnModificar.setEnabled(false);
		if (esAdmin)
			btnCrear.setEnabled(true);
		else
			btnCrear.setEnabled(false);
		btnEliminar.setEnabled(false);

		// Si no es admin.
		if (!esAdmin) {
			cbxTipoUsuario.setEnabled(true);
			cbxTipoUsuario.setSelectedIndex(0);
		}

		if (esAdmin)
		{
			spnAutoridad.setValue(Integer.valueOf("2"));
		} else {
			// Desactivar
			panelDoctor.setEnabled(false);
			panelDoctor.setVisible(false);
			panelSecretaria.setEnabled(false);
			panelSecretaria.setVisible(false);
			// Valor por defecto
			spnCitas.setValue(Integer.valueOf("1"));
		}

	}

	public static void rellenarTabla() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Empleado empleado : Clinica.getInstance().getEmpleados()) {
			row[0] = empleado.getIdEmpleado();
			row[1] = empleado.getNombre();

			if (esAdmin)
				row[2] = empleado.getUsername();
			else {
				if (empleado instanceof Doctor)
					row[2] = "Doctor";
				else if (empleado instanceof Secretaria)
					row[2] = "Secretario/a";
				else
					row[2] = "Error";
			}

			if (esAdmin) {
				if (empleado instanceof Administrador)
					row[3] = String.valueOf(((Administrador)empleado).getAutoridad());
				else
					row[3] = "";
			} else {
				row[3] = dateFormat.format(empleado.getLastConnection());
			}

			if (esAdmin && (empleado instanceof Administrador)) {
				// El administrador principal solo puede modificar otros administradores.
				if (((Administrador)empleado).getAutoridad() > 1)
					model.addRow(row);
			}

			if (!esAdmin && !(empleado instanceof Administrador))
				model.addRow(row);
		}
	}

	private void rellenarDatos() {
		int index = userTable.getSelectedRow();

		if (index != -1) {
			String userId = userTable.getValueAt(index, 0).toString();
			usuarioModificar = Clinica.getInstance().buscarEmpleadoById(userId);

			if (usuarioModificar != null) {
				txtName.setText(usuarioModificar.getNombre());
				txtUsername.setText(usuarioModificar.getUsername());
				txtPassword.setText(usuarioModificar.getPassword());

				if (esAdmin) {
					spnAutoridad.setValue(Integer.valueOf(((Administrador)usuarioModificar).getAutoridad()));

				} else {
					if (usuarioModificar instanceof Doctor) {
						spnCitas.setValue(Integer.valueOf(((Doctor)usuarioModificar).getNumCitasMax()));
						cbxTipoUsuario.setSelectedIndex(1);						
					} else if (usuarioModificar instanceof Secretaria) {
						txtCantDoctores.setText(String.valueOf(((Secretaria)usuarioModificar).getIdDoctores().size()));
						cbxTipoUsuario.setSelectedIndex(2);
					}
					cbxTipoUsuario.setEnabled(false);
				}

			}
		} else {
			JOptionPane.showMessageDialog(null, "Error de modificación de datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}

	}
}
