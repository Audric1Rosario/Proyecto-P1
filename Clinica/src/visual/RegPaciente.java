package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logical.Clinica;
import logical.Doctor;
import logical.Empleado;
import logical.Enfermedad;
import logical.Paciente;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.UIManager;
import java.awt.Color;

public class RegPaciente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtCellphone;
	private JTextField txtSave;
	private JTextField txtCedula;
	private JTextField txtAddress;
	private JTextField txtEmail;

	// Lista
	private JList<String> lstPaciente;
	private JList<String> lstSistema;

	// Radio buttons
	private JRadioButton rdbtnCasado;
	private JRadioButton rdbtnSoltero;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;

	// Combo box
	private JComboBox<String> cbxBlood;
	private JComboBox<String> cbxSector;

	// Botones:
	private JButton btnAceptar;
	private JButton cancelButton;
	private JButton btnAgregar;
	private JButton btnRemover;

	// Spinner
	private JSpinner spnHeight;
	private JSpinner spnAge;

	// variables logicas
	private String screenPath;
	private MaskFormatter mask;
	private Paciente pacienteModificar;
	private ArrayList<String> enfermedadesArr;
	private ArrayList<String> enfermedadesModi;
	private ArrayList<String> enfermedadesSelec;


	/**
	 * Launch the application.
	 *//*

	public static void main(String[] args) {
		try {
			RegPaciente dialog = new RegPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/


	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public RegPaciente(Paciente paciente) throws ParseException {
		this.pacienteModificar = paciente;
		setTitle("Registrar paciente.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegPaciente.class.getResource("/image/caduceus.png")));
		setBounds(100, 100, 778, 563);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.enfermedadesArr = new ArrayList<String>();
		this.enfermedadesSelec = new ArrayList<String>();
		this.enfermedadesModi = new ArrayList<String>();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 521, 280);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(15, 30, 58, 14);
			panel.add(lblNombre);
		}

		txtName = new JTextField();
		txtName.setBounds(77, 27, 424, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		{
			JLabel lblGenero = new JLabel("G\u00E9nero:");
			lblGenero.setBounds(15, 63, 58, 14);
			panel.add(lblGenero);
		}
		rdbtnMasculino = new JRadioButton("M");
		rdbtnMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMasculino.setSelected(true);
				rdbtnFemenino.setSelected(false);
			}
		});
		rdbtnMasculino.setBounds(77, 63, 46, 14);
		panel.add(rdbtnMasculino);
		rdbtnMasculino.setSelected(true);

		rdbtnFemenino = new JRadioButton("F");
		rdbtnFemenino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMasculino.setSelected(false);
				rdbtnFemenino.setSelected(true);
			}
		});
		rdbtnFemenino.setBounds(125, 63, 46, 14);

		panel.add(rdbtnFemenino);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(177, 63, 43, 14);
		panel.add(lblEdad);

		spnAge = new JSpinner();
		spnAge.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnAge.setBounds(230, 60, 69, 20);
		panel.add(spnAge);

		JLabel lblGrupoSanguineo = new JLabel("Grupo sangu\u00EDneo:");
		lblGrupoSanguineo.setBounds(309, 63, 113, 14);
		panel.add(lblGrupoSanguineo);

		cbxBlood = new JComboBox<String>();
		cbxBlood.setToolTipText("Tipo de sangre.");
		cbxBlood.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "A+", "A-", "B", "B+", "B-", "AB", "O", "O+", "O-"}));


		cbxBlood.setBounds(432, 60, 69, 20);
		panel.add(cbxBlood);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setBounds(15, 95, 58, 14);
		panel.add(lblTelefono);

		mask = new MaskFormatter("(###) ###-####");
		txtPhone = new JFormattedTextField(mask);
		txtPhone.setBounds(80, 92, 176, 20);
		panel.add(txtPhone);
		txtPhone.setColumns(10);

		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(275, 95, 46, 14);
		panel.add(lblCelular);

		txtCellphone = new JFormattedTextField(mask);
		txtCellphone.setBounds(325, 92, 176, 20);
		panel.add(txtCellphone);
		txtCellphone.setColumns(10);

		JLabel lblSeguro = new JLabel("Seguro:");
		lblSeguro.setBounds(15, 124, 58, 14);
		panel.add(lblSeguro);

		txtSave = new JTextField();
		txtSave.setBounds(80, 121, 176, 20);
		panel.add(txtSave);
		txtSave.setColumns(10);

		JLabel lblCedula = new JLabel("C\u00E9dula:");
		lblCedula.setBounds(275, 124, 46, 14);
		panel.add(lblCedula);

		txtCedula = new JTextField();
		txtCedula.setBounds(325, 123, 176, 20);
		panel.add(txtCedula);
		txtCedula.setColumns(10);

		JLabel lblSector = new JLabel("Sector:");
		lblSector.setBounds(15, 156, 58, 14);
		panel.add(lblSector);

		cbxSector = new JComboBox<String>();
		cbxSector.setModel(new DefaultComboBoxModel<String>(new String[] {"Hig\u00FCey", "La Romana", "Monte Cristi", "Puerto Plata", "Santo Domingo", "Santiago de los Caballeros", "San Cristobal", "San Pedro de Macoris"}));

		cbxSector.setBounds(80, 153, 301, 20);
		panel.add(cbxSector);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(15, 187, 58, 14);
		panel.add(lblDireccion);

		txtAddress = new JTextField();
		txtAddress.setBounds(80, 184, 393, 20);
		panel.add(txtAddress);
		txtAddress.setColumns(10);

		JLabel lblEstatura = new JLabel("Estatura:");
		lblEstatura.setBounds(15, 218, 58, 14);
		panel.add(lblEstatura);

		spnHeight = new JSpinner();
		spnHeight.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		spnHeight.setBounds(80, 215, 69, 20);
		panel.add(spnHeight);

		JLabel lblEstadoCivil = new JLabel("Estado Civil:");
		lblEstadoCivil.setBounds(180, 218, 71, 14);
		panel.add(lblEstadoCivil);

		rdbtnCasado = new JRadioButton("Casado");
		rdbtnCasado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnCasado.setSelected(true);
				rdbtnSoltero.setSelected(false);
			}
		});
		rdbtnCasado.setBounds(338, 218, 71, 14);
		panel.add(rdbtnCasado);

		rdbtnSoltero = new JRadioButton("Soltero");
		rdbtnSoltero.setSelected(true);
		rdbtnSoltero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnCasado.setSelected(false);
				rdbtnSoltero.setSelected(true);
			}
		});

		rdbtnSoltero.setBounds(258, 218, 83, 14);
		panel.add(rdbtnSoltero);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(15, 250, 58, 14);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(80, 247, 310, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);


		{
			JPanel btnCerrar = new JPanel();
			btnCerrar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			btnCerrar.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(btnCerrar, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(txtName.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Nombre del Paciente vacio", "Notificación", JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						if(txtPhone.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Telefono del Paciente vacio","Notificacion", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if(txtCellphone.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Celular del Paciente vacio","Notificacion", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if(txtAddress.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Direccion del Paciente vacio","Notificacion", JOptionPane.INFORMATION_MESSAGE);
							return;
						}					

						Enfermedad buscar;
						if (pacienteModificar == null) { 						
							Paciente aux = new Paciente( txtName.getText(), txtCedula.getText(), txtSave.getText(),Integer.valueOf(spnAge.getValue().toString()),
									rdbtnMasculino.isSelected() ? "M" : "F", rdbtnSoltero.isSelected() ? "Soltero" : "Casado", cbxBlood.getSelectedItem().toString(), 
											Float.valueOf(spnHeight.getValue().toString()), txtAddress.getText(), cbxSector.getSelectedItem().toString(),txtPhone.getText(),
											txtCellphone.getText(), txtEmail.getText(), enfermedadesSelec);
							Clinica.getInstance().addPaciente(aux);	

							JOptionPane.showMessageDialog(null, "Paciente agregado.","Notificación.", JOptionPane.INFORMATION_MESSAGE);
							// Saber a cuales enfermedades se le esta agregando...							
							for (String enfer : enfermedadesSelec) {
								buscar = Clinica.getInstance().buscarEnfermedadByNombre(enfer);
								buscar.setCantPacientes(buscar.getCantPacientes() + 1);  	// Sumar a la cantidad de pacientes.
							}

							clear();
							iniciarLista();
						} else {
							// Agregar los datos al paciente a modificar.
							pacienteModificar.setNombre(txtName.getText());
							pacienteModificar.setCedula(txtCedula.getText());
							pacienteModificar.setSeguro(txtSave.getText());
							pacienteModificar.setEdad(Integer.valueOf(spnAge.getValue().toString()));
							pacienteModificar.setSexo(rdbtnMasculino.isSelected() ? "M" : "F");
							pacienteModificar.setEstadoCivil(rdbtnSoltero.isSelected() ? "Soltero" : "Casado");
							pacienteModificar.setTipoSangre(cbxBlood.getSelectedItem().toString());
							pacienteModificar.setEstatura(Float.valueOf(spnHeight.getValue().toString()));
							pacienteModificar.setDireccion(txtAddress.getText());
							pacienteModificar.setSector(cbxSector.getSelectedItem().toString());
							pacienteModificar.setTelefono(txtPhone.getText());
							pacienteModificar.setCelular(txtCellphone.getText());
							pacienteModificar.setEmail(txtEmail.getText());

							// Saber a cuales enfermedades se les está quitando y sumando.					
							int index;
							for (String enfer : enfermedadesSelec) {
								if (enfermedadesModi.indexOf(enfer) != -1) { // Significa que se queda igual y se puede poner que no se modifico quitandolo
									index = enfermedadesModi.indexOf(enfer);
									enfermedadesModi.remove(index);
								} else {	// Se agrega por primera vez
									buscar = Clinica.getInstance().buscarEnfermedadByNombre(enfer);
									buscar.setCantPacientes(buscar.getCantPacientes() + 1);
								}
							}
							// Ahora lo que quede en modi, sacarlo.
							for (String enfer : enfermedadesModi) {
								buscar = Clinica.getInstance().buscarEnfermedadByNombre(enfer);
								buscar.setCantPacientes(buscar.getCantPacientes() - 1);
							}
							pacienteModificar.setEnfermedades(enfermedadesSelec);							
							BuscarPaciente.loadTable(Clinica.getInstance().buscarPacienteByNombre(pacienteModificar.getNombre()), pacienteModificar, false);							
							dispose();
							JOptionPane.showMessageDialog(null, "Paciente modificado.","Notificación.", JOptionPane.INFORMATION_MESSAGE);
						}


					}});
				btnAceptar.setActionCommand("OK");
				btnCerrar.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);

			}
			{
				cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				btnCerrar.add(cancelButton);
			}
		}

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Imagen de Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(541, 11, 211, 280);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel ImagenUsuario = new JLabel("Usuario");
		ImagenUsuario.setBounds(23, 38, 164, 152);
		panel_1.add(ImagenUsuario);
		ImagenUsuario.setIcon(new ImageIcon(((new ImageIcon(Login.class.getResource("/image/userA.png"))).getImage()).getScaledInstance(
				ImagenUsuario.getWidth(), ImagenUsuario.getHeight(), Image.SCALE_SMOOTH)));

		JButton btnSubirImagen = new JButton("");
		btnSubirImagen.setToolTipText("Subir foto");
		btnSubirImagen.setIcon(new ImageIcon(RegPaciente.class.getResource("/image/up-arrow.png")));
		btnSubirImagen.setBounds(48, 216, 115, 40);
		panel_1.add(btnSubirImagen);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 298, 742, 177);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades del sistema", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(430, 11, 302, 153);
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);

		lstSistema = new JList<String>();
		lstSistema.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstSistema.setModel(new DefaultListModel<String>());
		scrollPane.setViewportView(lstSistema);



		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(10, 11, 302, 153);
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_4.add(scrollPane_1, BorderLayout.CENTER);

		lstPaciente = new JList<String>();
		lstPaciente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(lstPaciente);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				actualizar(true, lstSistema.getSelectedIndex());

			}
		});
		btnAgregar.setBounds(328, 40, 89, 35);
		panel_2.add(btnAgregar);

		btnRemover = new JButton("Remover");
		btnRemover.setEnabled(false);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar(false, lstPaciente.getSelectedIndex());
			}
		});
		btnRemover.setBounds(328, 88, 89, 35);
		panel_2.add(btnRemover);

		// Sabemos que se esta modificando:
		if (pacienteModificar != null) {
			boolean encontrado; 
			int aux;
			// Título
			setTitle("Modificar paciente.");

			// Nombre
			txtName.setText(pacienteModificar.getNombre());
			txtName.setEditable(false);

			// Genero
			if (pacienteModificar.getSexo().equals("M")) {
				rdbtnMasculino.setSelected(true);
				rdbtnFemenino.setSelected(false);
			} else if (pacienteModificar.getSexo().equals("F")) {
				rdbtnMasculino.setSelected(false);
				rdbtnFemenino.setSelected(true);
			}

			// Edad
			spnAge.setValue(Integer.valueOf(pacienteModificar.getEdad()));

			// Teléfono
			txtPhone.setText(pacienteModificar.getTelefono());

			// Tipo de sangre
			encontrado = false; 
			aux = 0;
			while (aux < cbxBlood.getModel().getSize() && !encontrado) {
				if (pacienteModificar.getTipoSangre().equalsIgnoreCase(cbxBlood.getItemAt(aux).toString())) {
					encontrado = true;
					cbxBlood.setSelectedIndex(aux);
				}
				aux++;
			}

			// Celular
			txtCellphone.setText(pacienteModificar.getCelular());

			// Seguro
			txtSave.setText(pacienteModificar.getSeguro());

			// Cédula
			txtCedula.setText(pacienteModificar.getCedula());
			txtCedula.setEditable(false);

			// Sector
			encontrado = false; 
			aux = 0;
			while (aux < cbxSector.getModel().getSize() && !encontrado) {
				if (pacienteModificar.getSector().equalsIgnoreCase(cbxSector.getItemAt(aux).toString())) {
					encontrado = true;
					cbxSector.setSelectedIndex(aux);
				}
				aux++;
			}

			// Dirección
			txtAddress.setText(pacienteModificar.getDireccion());

			// Estatura
			spnHeight.setValue(Float.valueOf(pacienteModificar.getEstatura()));

			// Estado civil
			if (pacienteModificar.getEstadoCivil().equalsIgnoreCase("Soltero")) {
				rdbtnCasado.setSelected(false);
				rdbtnSoltero.setSelected(true);
			} else if (pacienteModificar.getEstadoCivil().equalsIgnoreCase("Casado")) {
				rdbtnCasado.setSelected(true);
				rdbtnSoltero.setSelected(false);
			}

			// Email
			txtEmail.setText(pacienteModificar.getEmail());

			// Rellenar las enfermedades que tiene el paciente hasta el momento.
			for (String data : pacienteModificar.getEnfermedades()) {
				// ya que verificar revisa si se puede listar, y permite saber si se puede crear.
				if (Clinica.getInstance().verificarEnfermedad(data) == false) {
					enfermedadesSelec.add(data);
					enfermedadesModi.add(data);
				}
			}

			// Rellenar las enfermedades que 
			for (Enfermedad enfer: Clinica.getInstance().getEnfermedades()) {
				if (enfer.isListar()) {
					if (enfermedadesSelec.indexOf(enfer.getNombre()) == -1) { // agregar si no se encuentra aquí
						enfermedadesArr.add(enfer.getNombre());
					}
				}
			}

			// Ordenar alfabéticamente
			Collections.sort(enfermedadesSelec);
			Collections.sort(enfermedadesArr);
			Collections.sort(enfermedadesModi);
			// Reiniciar listas
			reiniciarLista();

		} else if (pacienteModificar == null)
			iniciarLista();
	}

	// Sólo se usa al crear pacientes.
	private void iniciarLista() {
		// Borrar datos
		DefaultListModel<String> model = new DefaultListModel<String>();
		DefaultListModel<String> modelB = new DefaultListModel<String>();
		// Igualar a lista vacía para que se reinicie.
		lstPaciente.setModel(modelB);
		lstSistema.setModel(model);
		enfermedadesArr = new ArrayList<String>();
		enfermedadesSelec = new ArrayList<String>();

		for (Enfermedad enfer: Clinica.getInstance().getEnfermedades()) {
			if (enfer.isListar())
				enfermedadesArr.add(enfer.getNombre());
		}

		Collections.sort(enfermedadesArr);
		if (enfermedadesArr.size() == 0) {
			JOptionPane.showMessageDialog(null, "No hay enfermedades registradas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}

		// Llenar enfermedades.
		//model = (DefaultListModel<String>) lstSistema.getModel();
		for (String nombre : enfermedadesArr) {
			model.addElement(nombre);
		}

		btnAgregar.setEnabled(lstSistema.getModel().getSize() > 0);
		btnRemover.setEnabled(lstPaciente.getModel().getSize() > 0);

	}

	private void reiniciarLista() {
		// Borrar datos
		DefaultListModel<String> model = new DefaultListModel<String>();
		DefaultListModel<String> modelB = new DefaultListModel<String>();
		// Igualar a lista vacía para que se reinicie.
		lstPaciente.setModel(modelB);
		lstSistema.setModel(model);

		// Llenar enfermedades.
		for (String nombre : enfermedadesArr) {
			model.addElement(nombre);
		}

		for (String nombre : enfermedadesSelec) {
			modelB.addElement(nombre);
		}
		btnAgregar.setEnabled(lstSistema.getModel().getSize() > 0);
		btnRemover.setEnabled(lstPaciente.getModel().getSize() > 0);

	}

	private void actualizar(boolean razon, int index) {
		if (razon && index != -1) { 
			enfermedadesSelec.add(enfermedadesArr.get(index));
			enfermedadesArr.remove(index);
			Collections.sort(enfermedadesSelec);

		} else if (index != -1) {   
			enfermedadesArr.add(enfermedadesSelec.get(index));
			enfermedadesSelec.remove(index);
			Collections.sort(enfermedadesArr);
		}
		reiniciarLista();
	}

	private void clear() {
		// Limpiar todos los campos.
		txtName.setText(""); 
		((JFormattedTextField)txtPhone).setValue(null);
		txtPhone = new JFormattedTextField(mask);
		rdbtnMasculino.setSelected(true);
		rdbtnFemenino.setSelected(false);
		spnAge.setValue(Integer.valueOf(1));
		((JFormattedTextField)txtCellphone).setValue(null);
		txtSave.setText("");
		txtCedula.setText("");
		cbxSector.setSelectedIndex(0);
		txtAddress.setText("");
		spnHeight.setValue(Integer.valueOf(1));
		rdbtnCasado.setSelected(false);
		rdbtnSoltero.setSelected(true);
		txtEmail.setText("");
		return;
	}
}
