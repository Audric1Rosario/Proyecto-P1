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
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class RegPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField lastname;
	private JTextField phone;
	private JTextField cellphone;
	private JTextField save;
	private JTextField ID;
	private JTextField address;
	private JTextField email;
	private String screenPath;
	
	ButtonGroup Generos = new ButtonGroup();
	ButtonGroup Relationship = new ButtonGroup();
	
	
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegPaciente.class.getResource("/image/caduceus.png")));
		setBounds(100, 100, 720, 480);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 514, 387);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 26, 115, 26);
			panel.add(lblNombre);
		}
		
		txtName = new JTextField();
		txtName.setBounds(60, 29, 186, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		{
			JLabel lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setBounds(256, 32, 77, 14);
			panel.add(lblApellidos);
		}
		{
			lastname = new JTextField();
			lastname.setBounds(316, 29, 195, 20);
			panel.add(lastname);
			lastname.setColumns(10);
		}
		{
			JLabel lblGenero = new JLabel("Genero:");
			lblGenero.setBounds(10, 58, 58, 26);
			panel.add(lblGenero);
		}
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBounds(54, 60, 96, 23);
		panel.add(rdbtnMasculino);
		
		JRadioButton rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.setBounds(146, 60, 83, 23);
		panel.add(rdbtnFemenino);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(235, 61, 72, 20);
		panel.add(lblEdad);
		
		JSpinner age = new JSpinner();
		age.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		age.setBounds(278, 61, 36, 20);
		panel.add(age);
		
		JLabel lblGrupoSanguineo = new JLabel("Grupo Sanguineo:");
		lblGrupoSanguineo.setBounds(324, 63, 146, 14);
		panel.add(lblGrupoSanguineo);
		
		JComboBox blood = new JComboBox();
		blood.setModel(new DefaultComboBoxModel(new String[] {"", "A", "A+", "A-", "B", "B+", "B-", "AB", "O", "O+", "O-"}));
		blood.setBounds(439, 61, 46, 20);
		panel.add(blood);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 95, 90, 14);
		panel.add(lblTelefono);
		
		phone = new JTextField();
		phone.setBounds(60, 90, 163, 20);
		panel.add(phone);
		phone.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(245, 90, 46, 14);
		panel.add(lblCelular);
		
		cellphone = new JTextField();
		cellphone.setBounds(294, 87, 176, 20);
		panel.add(cellphone);
		cellphone.setColumns(10);
		
		JLabel lblSeguro = new JLabel("Seguro:");
		lblSeguro.setBounds(10, 124, 46, 14);
		panel.add(lblSeguro);
		
		save = new JTextField();
		save.setBounds(60, 121, 163, 20);
		panel.add(save);
		save.setColumns(10);
		
		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(245, 127, 46, 14);
		panel.add(lblCedula);
		
		ID = new JTextField();
		ID.setBounds(294, 121, 176, 20);
		panel.add(ID);
		ID.setColumns(10);
		
		JLabel lblSector = new JLabel("Sector:");
		lblSector.setBounds(10, 156, 46, 14);
		panel.add(lblSector);
		
		JComboBox sector = new JComboBox();
		sector.setBounds(138, 153, 28, 20);
		panel.add(sector);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(10, 181, 70, 14);
		panel.add(lblDireccion);
		
		address = new JTextField();
		address.setBounds(70, 178, 395, 20);
		panel.add(address);
		address.setColumns(10);
		
		JLabel lblEstatura = new JLabel("Estatura");
		lblEstatura.setBounds(10, 212, 70, 14);
		panel.add(lblEstatura);
		
		JSpinner height = new JSpinner();
		height.setModel(new SpinnerNumberModel(new Float(1), new Float(1), null, new Float(1)));
		height.setBounds(60, 209, 46, 20);
		panel.add(height);
		
		JLabel lblEstadoCivil = new JLabel("Estado Civil:");
		lblEstadoCivil.setBounds(138, 212, 71, 14);
		panel.add(lblEstadoCivil);
		
		JRadioButton rdbtnCasado = new JRadioButton("Casado");
		rdbtnCasado.setBounds(209, 208, 71, 23);
		panel.add(rdbtnCasado);
		
		JRadioButton rdbtnSoltero = new JRadioButton("Soltero");
		rdbtnSoltero.setBounds(282, 208, 109, 23);
		panel.add(rdbtnSoltero);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 250, 46, 14);
		panel.add(lblEmail);
		
		email = new JTextField();
		email.setBounds(54, 247, 192, 20);
		panel.add(email);
		email.setColumns(10);
		
		JLabel lblEnfermedades = new JLabel("Enfermedades:");
		lblEnfermedades.setBounds(10, 289, 90, 14);
		panel.add(lblEnfermedades);
		
		JComboBox disease = new JComboBox();
		disease.setBounds(218, 286, 28, 20);
		panel.add(disease);
		screenPath = "/image/User.png";
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(txtName.getText().equalsIgnoreCase("")||lastname.getText().equalsIgnoreCase("")||phone.getText().equalsIgnoreCase("")||
						   cellphone.getText().equalsIgnoreCase("")||save.getText().equalsIgnoreCase("")||ID.getText().equalsIgnoreCase("")||
					       address.getText().equalsIgnoreCase("")||email.getText().equalsIgnoreCase("")
						    )
					        JOptionPane.showMessageDialog(null, "Campos del paciente vacios", "Notificación", JOptionPane.INFORMATION_MESSAGE);
					}
				});
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
		
		Generos.add(rdbtnMasculino);
		Generos.add(rdbtnFemenino);
		Relationship.add(rdbtnCasado);
		Relationship.add(rdbtnSoltero);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(529, 11, 165, 156);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel ImagenUsuario = new JLabel("Usuario");
		ImagenUsuario.setBounds(10, 11, 145, 127);
		panel_1.add(ImagenUsuario);
		ImagenUsuario.setIcon(new ImageIcon(((new ImageIcon(Login.class.getResource(screenPath))).getImage()).getScaledInstance(
				ImagenUsuario.getWidth(), ImagenUsuario.getHeight(), Image.SCALE_SMOOTH)));
		
		
	}
	
}
