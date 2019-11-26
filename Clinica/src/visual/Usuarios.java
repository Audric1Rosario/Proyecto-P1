package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
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
import java.util.ArrayList;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.SpinnerNumberModel;

public class Usuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtCantDoctores;
	
	
	// Variables de creación
	private boolean esAdmin;	// Para saber si se presentaran los administradores o usuarios normales.
	private ArrayList<String> doctorId;
	
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
	
	// Table 
	private JTable userTable;
	private DefaultTableModel model;
	private Object row[];

	
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
		this.doctorId = new ArrayList<String>();
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
				model = new DefaultTableModel();
				
				String[] headers = {"ID-Usuario", "Nombre", "Username", "Última conexión."};
				String[] headersM = {"ID-Usuario", "Nombre", "Username", "Autoridad"};
				
				if (!esAdmin)
					model.setColumnIdentifiers(headers);
				else
					model.setColumnIdentifiers(headersM);
				userTable.setModel(model);
				scrollPane.setViewportView(userTable);
			}
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
			
			JButton btnDoctores = new JButton("Seleccione");
			btnDoctores.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SeleccionarDoctores ventana = new SeleccionarDoctores(doctorId);
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
			spnCitas.setModel(new SpinnerNumberModel(1, 1, 20, 1));
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
			spnAutoridad.setModel(new SpinnerNumberModel(1, 1, 4, 1));
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
						dispose();
					}
				});
				
				btnCrear = new JButton("Crear");
				btnCrear.setEnabled(false);
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Validar que este correcto:
						if (txtName.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre", "Advertencia", JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						if (txtPassword.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Por favor ingrese una contraseña", "Advertencia", JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						if (txtUsername.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre de usuario", "Advertencia", JOptionPane.WARNING_MESSAGE);
							return;
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
					}
				});
				buttonPane.add(btnCrear);
				
				if (esAdmin)
					btnCrear.setEnabled(true);
				
				btnModificar = new JButton("Modificar");
				buttonPane.add(btnModificar);
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
	}
}
