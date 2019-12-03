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
	private String sexo;
	private String civil;
	private Paciente pacienteModificar;
	private ArrayList<String> enfermedadesArr;  
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

		if (pacienteModificar != null) {
			this.civil = pacienteModificar.getEstadoCivil();
			this.sexo = pacienteModificar.getSexo();
		}		
		if (pacienteModificar != null)
			setTitle("Modificar paciente.");
		else 
			setTitle("Registrar paciente.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegPaciente.class.getResource("/image/caduceus.png")));
		setBounds(100, 100, 720, 563);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.enfermedadesArr = new ArrayList<String>();
		this.enfermedadesSelec = new ArrayList<String>();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 514, 273);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 26, 115, 26);
			panel.add(lblNombre);
		}

		/* Ejemplo de como llenar los campos para modificar los datos de un paciente */
		txtName = new JTextField();
		if (pacienteModificar != null)
		{
			txtName.setText(pacienteModificar.getNombre());
			txtName.setEditable(false);
		}
		txtName.setBounds(60, 29, 405, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		{
			JLabel lblGenero = new JLabel("G\u00E9nero:");
			lblGenero.setBounds(10, 58, 58, 26);
			panel.add(lblGenero);
		}

		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMasculino.setSelected(true);
				rdbtnFemenino.setSelected(false);

				sexo = "M";


			}
		});
		rdbtnMasculino.setBounds(54, 60, 96, 23);
		panel.add(rdbtnMasculino);
		rdbtnMasculino.setSelected(true);

		rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMasculino.setSelected(false);
				rdbtnFemenino.setSelected(true);

				sexo = "F";

			}
		});
		rdbtnFemenino.setBounds(146, 60, 83, 23);
		panel.add(rdbtnFemenino);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(235, 61, 72, 20);
		panel.add(lblEdad);

		spnAge = new JSpinner();
		spnAge.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnAge.setBounds(278, 61, 36, 20);
		panel.add(spnAge);

		JLabel lblGrupoSanguineo = new JLabel("Grupo Sanguineo:");
		lblGrupoSanguineo.setBounds(324, 63, 146, 14);
		panel.add(lblGrupoSanguineo);

		cbxBlood = new JComboBox<String>();
		cbxBlood.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "A+", "A-", "B", "B+", "B-", "AB", "O", "O+", "O-"}));

		if (pacienteModificar != null) {
			boolean encontrado = false; 
			int aux = 0;
			while (aux < cbxBlood.getModel().getSize() && !encontrado) {
				if (pacienteModificar.getTipoSangre().equalsIgnoreCase(cbxBlood.getItemAt(aux).toString())) {
					encontrado = true;
					cbxBlood.setSelectedIndex(aux);
				}
				aux++;
			}
		}

		cbxBlood.setBounds(419, 61, 46, 20);
		panel.add(cbxBlood);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setBounds(10, 95, 90, 14);
		panel.add(lblTelefono);

		MaskFormatter mask = new MaskFormatter("(###) ###-####");
		txtPhone = new JFormattedTextField(mask);
		txtPhone.setBounds(60, 90, 163, 20);
		if (pacienteModificar != null)
		{
			txtPhone.setText(pacienteModificar.getTelefono());
			txtPhone.setEditable(false);
		}
		panel.add(txtPhone);
		txtPhone.setColumns(10);

		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(245, 90, 46, 14);
		panel.add(lblCelular);

		MaskFormatter maskara = new MaskFormatter("(###) ###-####");
		txtCellphone = new JFormattedTextField(maskara);
		if (pacienteModificar != null)
			txtCellphone.setText(pacienteModificar.getCelular());
		txtCellphone.setBounds(294, 87, 176, 20);
		panel.add(txtCellphone);
		txtCellphone.setColumns(10);

		JLabel lblSeguro = new JLabel("Seguro:");
		lblSeguro.setBounds(10, 124, 46, 14);
		panel.add(lblSeguro);

		txtSave = new JTextField();
		if (pacienteModificar != null)
			txtSave.setText(pacienteModificar.getSeguro());
		txtSave.setBounds(60, 121, 163, 20);
		panel.add(txtSave);
		txtSave.setColumns(10);

		JLabel lblCedula = new JLabel("C\u00E9dula:");
		lblCedula.setBounds(245, 127, 46, 14);
		panel.add(lblCedula);

		txtCedula = new JTextField();
		if (pacienteModificar != null)
		{
			txtCedula.setText(pacienteModificar.getCedula());
			txtCedula.setEditable(false);
		}
		txtCedula.setBounds(294, 121, 176, 20);
		panel.add(txtCedula);
		txtCedula.setColumns(10);

		JLabel lblSector = new JLabel("Sector:");
		lblSector.setBounds(10, 156, 46, 14);
		panel.add(lblSector);

		cbxSector = new JComboBox<String>();
		cbxSector.setModel(new DefaultComboBoxModel<String>(new String[] {"Hig\u00FCey", "La Romana", "Monte Cristi", "Puerto Plata", "Santo Domingo", "Santiago de los Caballeros", "San Cristobal", "San Pedro de Macoris"}));
		if (pacienteModificar != null) {
			boolean encontrado = false; 
			int aux = 0;
			while (aux < cbxSector.getModel().getSize() && !encontrado) {
				if (pacienteModificar.getSector().equalsIgnoreCase(cbxSector.getItemAt(aux).toString())) {
					encontrado = true;
					cbxSector.setSelectedIndex(aux);
				}
				aux++;
			}
		}

		cbxSector.setBounds(70, 152, 300, 20);
		panel.add(cbxSector);

		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(10, 181, 70, 14);
		panel.add(lblDireccion);

		txtAddress = new JTextField();
		txtAddress.setBounds(70, 178, 395, 20);
		panel.add(txtAddress);
		txtAddress.setColumns(10);

		JLabel lblEstatura = new JLabel("Estatura");
		lblEstatura.setBounds(10, 212, 70, 14);
		panel.add(lblEstatura);

		spnHeight = new JSpinner();
		spnHeight.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		spnHeight.setBounds(60, 209, 46, 20);
		panel.add(spnHeight);

		JLabel lblEstadoCivil = new JLabel("Estado Civil:");
		lblEstadoCivil.setBounds(138, 212, 71, 14);
		panel.add(lblEstadoCivil);

		rdbtnCasado = new JRadioButton("Casado");
		rdbtnCasado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnCasado.setSelected(true);
				rdbtnSoltero.setSelected(false);

				civil = "Casado";
			}
		});
		rdbtnCasado.setBounds(209, 208, 71, 23);
		panel.add(rdbtnCasado);
		rdbtnCasado.setSelected(true);

		rdbtnSoltero = new JRadioButton("Soltero");
		rdbtnSoltero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnCasado.setSelected(false);
				rdbtnSoltero.setSelected(true);

				civil = "Soltero";
			}
		});
		rdbtnSoltero.setBounds(282, 208, 109, 23);
		panel.add(rdbtnSoltero);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 250, 46, 14);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		if (pacienteModificar != null)
			txtEmail.setText(pacienteModificar.getEmail());
		txtEmail.setBounds(70, 247, 263, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		screenPath = "/image/User.png";


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

						else { 

							/*
							 * public Paciente(String nombre, String cedula, String seguro, int edad, String estadoCivil,
								String sexo, String tipoSangre, float estatura, String direccion, String sector, String telefono,
								String celular, String email, ArrayList<String> enfermedades)
							 * */
							Paciente aux = new Paciente( txtName.getText(), txtCedula.getText(), txtSave.getText(),Integer.valueOf(spnAge.getValue().toString()),
									civil, sexo, cbxBlood.getSelectedItem().toString(), Integer.valueOf(spnHeight.getValue().toString()), txtAddress.getText(),
									cbxSector.getSelectedItem().toString(),txtPhone.getText(), txtCellphone.getText(), txtEmail.getText(), enfermedadesSelec);
							Clinica.getInstance().addPaciente(aux);		
							JOptionPane.showMessageDialog(null, "Paciente Agregado","Notificacion", JOptionPane.INFORMATION_MESSAGE);
							dispose();
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
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(529, 11, 165, 273);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel ImagenUsuario = new JLabel("Usuario");
		ImagenUsuario.setBounds(10, 40, 145, 127);
		panel_1.add(ImagenUsuario);
		ImagenUsuario.setIcon(new ImageIcon(((new ImageIcon(Login.class.getResource(screenPath))).getImage()).getScaledInstance(
				ImagenUsuario.getWidth(), ImagenUsuario.getHeight(), Image.SCALE_SMOOTH)));

		JButton btnSubirImagen = new JButton("Subir Imagen");
		btnSubirImagen.setBounds(28, 197, 115, 23);
		panel_1.add(btnSubirImagen);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 298, 684, 177);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades del sistema", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(374, 11, 257, 153);
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
		panel_4.setBounds(10, 11, 257, 153);
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
		btnAgregar.setBounds(277, 52, 89, 23);
		panel_2.add(btnAgregar);

		btnRemover = new JButton("Remover");
		btnRemover.setEnabled(false);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar(false, lstPaciente.getSelectedIndex());
			}
		});
		btnRemover.setBounds(277, 86, 89, 23);
		panel_2.add(btnRemover);

		if (pacienteModificar == null)
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

		for (Enfermedad enfer: Clinica.getInstance().getEnfermedades()) {
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

	/*
	private void textBox1_KeyPress(object sender, KeyPressEventArgs e)
	{
	    if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar) &&
	        (e.KeyChar != '.'))
	    {
	            e.Handled = true;
	    }

	    // only allow one decimal point
	    if ((e.KeyChar == '.') && ((sender as TextBox).Text.IndexOf('.') > -1))
	    {
	        e.Handled = true;
	    }
	}
	*/
}
