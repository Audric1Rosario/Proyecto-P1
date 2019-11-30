package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import logical.Clinica;
import logical.Enfermedad;
import logical.Paciente;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.UIManager;
import java.awt.Color;

public class RegPaciente extends JDialog {

	private ArrayList<String> enfermedadesArr;  
	private ArrayList<String> enfermedadesSelec;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtLastname;
	private JTextField txtPhone;
	private JTextField txtCellphone;
	private JTextField txtSave;
	private JTextField txtCedula;
	private JTextField txtAddress;
	private JTextField txtEmail;
	
	// radio buttons
	JRadioButton rdbtnCasado;
	JRadioButton rdbtnSoltero;
	JRadioButton rdbtnMasculino;
	JRadioButton rdbtnFemenino;
	
	
	// variables logicas
	private String screenPath;
	private String sexo;
	private String civil;
	private ArrayList<Enfermedad> enfermo;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegPaciente dialog = new RegPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the dialog.
	 */
	public RegPaciente() {
		setTitle("Paciente");
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

		txtName = new JTextField();
		txtName.setBounds(60, 29, 188, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		{
			JLabel lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setBounds(256, 32, 77, 14);
			panel.add(lblApellidos);
		}
		{
			txtLastname = new JTextField();
			txtLastname.setBounds(316, 29, 188, 20);
			panel.add(txtLastname);
			txtLastname.setColumns(10);
		}
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

		JSpinner spnAge = new JSpinner();
		spnAge.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnAge.setBounds(278, 61, 36, 20);
		panel.add(spnAge);

		JLabel lblGrupoSanguineo = new JLabel("Grupo Sanguineo:");
		lblGrupoSanguineo.setBounds(324, 63, 146, 14);
		panel.add(lblGrupoSanguineo);

		JComboBox cbxBlood = new JComboBox();
		cbxBlood.setModel(new DefaultComboBoxModel(new String[] {"A", "A+", "A-", "B", "B+", "B-", "AB", "O", "O+", "O-"}));
		cbxBlood.setBounds(439, 61, 46, 20);
		panel.add(cbxBlood);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setBounds(10, 95, 90, 14);
		panel.add(lblTelefono);

		txtPhone = new JTextField();
		txtPhone.setBounds(60, 90, 163, 20);
		panel.add(txtPhone);
		txtPhone.setColumns(10);

		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(245, 90, 46, 14);
		panel.add(lblCelular);

		txtCellphone = new JTextField();
		txtCellphone.setBounds(294, 87, 176, 20);
		panel.add(txtCellphone);
		txtCellphone.setColumns(10);

		JLabel lblSeguro = new JLabel("Seguro:");
		lblSeguro.setBounds(10, 124, 46, 14);
		panel.add(lblSeguro);

		txtSave = new JTextField();
		txtSave.setBounds(60, 121, 163, 20);
		panel.add(txtSave);
		txtSave.setColumns(10);

		JLabel lblCedula = new JLabel("C\u00E9dula:");
		lblCedula.setBounds(245, 127, 46, 14);
		panel.add(lblCedula);

		txtCedula = new JTextField();
		txtCedula.setBounds(294, 121, 176, 20);
		panel.add(txtCedula);
		txtCedula.setColumns(10);

		JLabel lblSector = new JLabel("Sector:");
		lblSector.setBounds(10, 156, 46, 14);
		panel.add(lblSector);

		JComboBox cbxSector = new JComboBox();
		cbxSector.setModel(new DefaultComboBoxModel(new String[] {"Hig\u00FCey", "La Romana", "Monte Cristi", "Puerto Plata", "Santo Domingo", "Santiago de los Caballeros", "San Cristobal", "San Pedro de Macoris"}));
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

		JSpinner spnHeight = new JSpinner();
		spnHeight.setModel(new SpinnerNumberModel(new Float(1), new Float(1), null, new Float(1)));
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
		txtEmail.setBounds(70, 247, 263, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		screenPath = "/image/User.png";


		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(txtName.getText().equalsIgnoreCase(""))
							JOptionPane.showMessageDialog(null, "Nombre del Paciente vacio", "Notificación", JOptionPane.INFORMATION_MESSAGE);
						if(txtLastname.getText().equalsIgnoreCase(""))
							JOptionPane.showMessageDialog(null, "Apellido del Paciente vacio","Notificacion", JOptionPane.INFORMATION_MESSAGE);
						if(txtPhone.getText().equalsIgnoreCase(""))
							JOptionPane.showMessageDialog(null, "Telefono del Paciente vacio","Notificacion", JOptionPane.INFORMATION_MESSAGE);
						if(txtCellphone.getText().equalsIgnoreCase(""))
							JOptionPane.showMessageDialog(null, "Celular del Paciente vacio","Notificacion", JOptionPane.INFORMATION_MESSAGE);
						if(txtAddress.getText().equalsIgnoreCase(""))
							JOptionPane.showMessageDialog(null, "Direccion del Paciente vacio","Notificacion", JOptionPane.INFORMATION_MESSAGE);
							
					else { 
						Paciente aux = new Paciente( txtName.getText(), txtCedula.getText(), txtSave.getText(),Integer.valueOf(spnAge.getValue().toString()),
								civil, sexo, cbxBlood.getSelectedItem().toString(), Integer.valueOf(spnHeight.getValue().toString()), txtAddress.getText(),
								cbxSector.getSelectedItem().toString(),txtPhone.getText(), txtCellphone.getText(), txtEmail.getText(),enfermedadesSelec);
						Clinica.getInstance().addPaciente(aux);
						//arreglo para enfermedades
					

					}}});
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
		
		JList lstSistema = new JList();
		scrollPane.setViewportView(lstSistema);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(10, 11, 257, 153);
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_4.add(scrollPane_1, BorderLayout.CENTER);
		
		JList lstPaciente = new JList();
		scrollPane_1.setViewportView(lstPaciente);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setEnabled(false);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection = lstPaciente.getSelectedIndex();
					actualizar(true, selection);
				
			}
		});
		btnAgregar.setBounds(277, 97, 89, 23);
		panel_2.add(btnAgregar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setEnabled(false);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection = lstSistema.getSelectedIndex();
					actualizar(false, selection);
				
			}
		});
		btnRemover.setBounds(277, 131, 89, 23);
		panel_2.add(btnRemover);
	}

	private void actualizar(boolean razon, int index) {
		if (razon) { 
			enfermedadesSelec.add(enfermedadesArr.get(index));
			enfermedadesArr.remove(index);
			Collections.sort(enfermedadesSelec);

		} else {   
			enfermedadesArr.add(enfermedadesSelec.get(index));
			enfermedadesSelec.remove(index);
			Collections.sort(enfermedadesArr);
		}




	}
	
}
