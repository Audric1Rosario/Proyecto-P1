package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logical.Administrador;
import logical.Doctor;
import logical.Empleado;
import logical.Secretaria;

import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class Perfil extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private Empleado usuarioActual;
	private JTextField txtUsername;
	private JPasswordField passPass;
	private JTextField txtNombre;
	
	// Botones
	private JButton btnAceptar;
	private JButton btnCerrar;
	private JButton btnCambiarNombre;
	private JButton btnCambiarContra;
	
	// Imagen de usuario.
	private JLabel lblUserImage;
	private JPasswordField passConfirmar;
	private JPasswordField passActual;
	private JPasswordField passNueva;
	
	// Paneles
	private JPanel panelData;
	private JPanel panelCambiar;
	private JPanel panelNombre;
	private JLabel lblNombreDeUsuario;
	private JLabel label_2;
	private JPasswordField passActualNombre;
	private JTextField txtNuevoNombre;
	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			Perfil dialog = new Perfil();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public Perfil(Empleado usuarioActual) {
		this.usuarioActual = usuarioActual;
		setTitle("Perfil de usuario.");
		if (usuarioActual instanceof Administrador)
			setTitle("Perfil de Administrador.");
		else if (usuarioActual instanceof Doctor)
			setTitle("Perfil de Doctor.");
		else if (usuarioActual instanceof Secretaria)
			setTitle("Perfil de Secretario/a.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Perfil.class.getResource("/image/caduceus.png")));
		setResizable(false);
		
		setBounds(100, 100, 480, 280);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panelImagen = new JPanel();
		panelImagen.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelImagen.setBounds(10, 11, 188, 191);
		contentPanel.add(panelImagen);
		panelImagen.setLayout(null);
		{
			lblUserImage = new JLabel("");
			lblUserImage.setBounds(24, 11, 141, 146);
			lblUserImage.setIcon(new ImageIcon(((new ImageIcon(Perfil.class.getResource("/image/userA.png"))).getImage()).getScaledInstance(lblUserImage.getWidth(), 
					lblUserImage.getHeight(), Image.SCALE_SMOOTH)));
			panelImagen.add(lblUserImage);
		}
		
		JButton btnChange = new JButton("");
		btnChange.setBounds(0, 182, 188, 9);
		btnChange.setEnabled(false);
		panelImagen.add(btnChange);
		
		panelData = new JPanel();
		panelData.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelData.setBounds(208, 11, 256, 191);
		contentPanel.add(panelData);
		panelData.setLayout(null);
		
		JLabel lblUsername = new JLabel("Nombre de usuario");
		lblUsername.setBounds(10, 72, 180, 14);
		panelData.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		txtUsername.setBounds(10, 91, 180, 20);
		panelData.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 122, 180, 14);
		panelData.add(lblContrasea);
		
		passPass = new JPasswordField();
		passPass.setBounds(10, 140, 180, 20);
		panelData.add(passPass);
		
		btnCambiarContra = new JButton("");
		btnCambiarContra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = String.valueOf(passPass.getPassword()); 
				if (password.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, ingrese la contraseña para validar que es usted.", 
							"Perfil.", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (!password.equals(usuarioActual.getPassword())) {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta.", 
							"Perfil.", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				// Proceder a validar todo.
				panelData.setVisible(false);
				panelNombre.setVisible(false);
				panelCambiar.setVisible(true);
				passActual.setEnabled(true);
				passNueva.setEnabled(true);
				passConfirmar.setEnabled(true);
				btnAceptar.setEnabled(true);
				btnCerrar.setText("Volver");
			}
		});
		btnCambiarContra.setToolTipText("Cambiar contrase\u00F1a");
		btnCambiarContra.setIcon(new ImageIcon(Perfil.class.getResource("/image/change.png")));
		btnCambiarContra.setBounds(200, 139, 46, 23);
		panelData.add(btnCambiarContra);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 22, 180, 14);
		panelData.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(10, 41, 180, 20);
		panelData.add(txtNombre);
		
		btnCambiarNombre = new JButton("");
		btnCambiarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Proceder a validar todo.
				panelData.setVisible(false);
				panelNombre.setVisible(true);
				panelCambiar.setVisible(false);
				txtNuevoNombre.setText(usuarioActual.getNombre());
				passActualNombre.setEnabled(true);
				txtNuevoNombre.setEnabled(true);
				btnAceptar.setEnabled(true);
				btnCerrar.setText("Volver");
			}
		});
		btnCambiarNombre.setToolTipText("Cambiar nombre.");
		btnCambiarNombre.setIcon(new ImageIcon(Perfil.class.getResource("/image/change.png")));
		btnCambiarNombre.setBounds(200, 40, 46, 23);
		panelData.add(btnCambiarNombre);
		
		panelCambiar = new JPanel();
		panelCambiar.setLayout(null);
		panelCambiar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cambiar contrase\u00F1a", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCambiar.setBounds(208, 11, 256, 191);
		contentPanel.add(panelCambiar);
		
		JLabel lblContraseaNueva = new JLabel("Contrase\u00F1a nueva");
		lblContraseaNueva.setBounds(10, 72, 180, 14);
		panelCambiar.add(lblContraseaNueva);
		
		JLabel lblConfirmar = new JLabel("Confirmar");
		lblConfirmar.setBounds(10, 122, 180, 14);
		panelCambiar.add(lblConfirmar);
		
		passConfirmar = new JPasswordField();
		passConfirmar.setBounds(10, 140, 180, 20);
		panelCambiar.add(passConfirmar);
		
		JLabel lblContraseaActual = new JLabel("Contrase\u00F1a actual");
		lblContraseaActual.setBounds(10, 22, 180, 14);
		panelCambiar.add(lblContraseaActual);
		
		passActual = new JPasswordField();
		passActual.setBounds(10, 41, 180, 20);
		panelCambiar.add(passActual);
		
		passNueva = new JPasswordField();
		passNueva.setBounds(10, 91, 180, 20);
		panelCambiar.add(passNueva);
		
		panelNombre = new JPanel();
		panelNombre.setBorder(new TitledBorder(null, "Cambiar nombre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNombre.setBounds(208, 11, 256, 191);
		contentPanel.add(panelNombre);
		panelNombre.setLayout(null);
		
		lblNombreDeUsuario = new JLabel("Nombre de usuario");
		lblNombreDeUsuario.setBounds(10, 72, 180, 14);
		panelNombre.add(lblNombreDeUsuario);
		
		label_2 = new JLabel("Contrase\u00F1a actual");
		label_2.setBounds(10, 22, 180, 14);
		panelNombre.add(label_2);
		
		passActualNombre = new JPasswordField();
		passActualNombre.setEnabled(false);
		passActualNombre.setBounds(10, 41, 180, 20);
		panelNombre.add(passActualNombre);
		
		txtNuevoNombre = new JTextField();
		txtNuevoNombre.setText((String) null);
		txtNuevoNombre.setColumns(10);
		txtNuevoNombre.setBounds(10, 91, 180, 20);
		panelNombre.add(txtNuevoNombre);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Primero revisa si está cambiando contraseña
						if (panelCambiar.isVisible()) {
							String password, passwordNueva, passwordConfirmar;
							password = String.valueOf(passActual.getPassword());
							passwordNueva = String.valueOf(passNueva.getPassword());
							passwordConfirmar = String.valueOf(passConfirmar.getPassword());
							
							// Primero asegurarse de que todas tienen un valor.
							if (password.equals("")) {
								JOptionPane.showMessageDialog(null, "Por favor, Escribir su contraseña actual.", "Perfil.", JOptionPane.WARNING_MESSAGE);
								return;
							}
							if (passwordNueva.equals("")) {
								JOptionPane.showMessageDialog(null, "Por favor, Escribir su nueva contraseña.", "Perfil.", JOptionPane.WARNING_MESSAGE);
								return;
							}
							if (passwordConfirmar.equals("")) {
								JOptionPane.showMessageDialog(null, "Por favor, confirmar su nueva contraseña.", "Perfil.", JOptionPane.WARNING_MESSAGE);
								return;
							}
							
							if (!password.equals(usuarioActual.getPassword())) {
								JOptionPane.showMessageDialog(null, "Contraseña actual incorrecta.", "Perfil.", JOptionPane.WARNING_MESSAGE);
								return;
							}
							
							// Solo el admin 1 puede configurar sus contraseñas como quiera
							if (usuarioActual instanceof Administrador) {
								if (((Administrador)usuarioActual).getAutoridad() > 1) {
									if (passwordNueva.length() < 8 || passwordNueva.length() > 16) {
										JOptionPane.showMessageDialog(null, "Por favor, ingrese una contraseña de 8 a 16 caracteres.",
												"Perfil.", JOptionPane.WARNING_MESSAGE);
										return;
									}
								}
							}
							
							if (usuarioActual instanceof Secretaria || usuarioActual instanceof Doctor) {
								if (passwordNueva.length() < 8 || passwordNueva.length() > 16) {
									JOptionPane.showMessageDialog(null, "Por favor, ingrese una contraseña de 8 a 16 caracteres.",
											"Perfil.", JOptionPane.WARNING_MESSAGE);
									return;
								}
							}
							
							if (!passwordNueva.equals(passwordConfirmar)) {
								JOptionPane.showMessageDialog(null, "Confirmación de contraseña incorrecta.", "Perfil.", JOptionPane.WARNING_MESSAGE);
								return;
							}
							
							// Si pasa todo eso entonces:
							usuarioActual.setPassword(passwordConfirmar);
							JOptionPane.showMessageDialog(null, "Contraseña cambiada exitosamente.", "Perfil.", JOptionPane.INFORMATION_MESSAGE);
						} else if (panelNombre.isVisible()) {
							String passwordNombre = String.valueOf(passActualNombre.getPassword());
							// Primero asegurarse de que todas tienen un valor.
							if (passwordNombre.equals("")) {
								JOptionPane.showMessageDialog(null, "Por favor, Escribir su contraseña actual.", "Perfil.", JOptionPane.WARNING_MESSAGE);
								return;
							}
							
							if (txtNuevoNombre.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Por favor, escriba un nombre.", "Perfil.", JOptionPane.WARNING_MESSAGE);
								return;
							}
							
							if (!passwordNombre.equals(usuarioActual.getPassword())) {
								JOptionPane.showMessageDialog(null, "Contraseña actual incorrecta.", "Perfil.", JOptionPane.WARNING_MESSAGE);
								return;
							}
							
							// Luego de pasar todas las pruebas.
							usuarioActual.setNombre(txtNuevoNombre.getText());
							Dashboard.getLblNombreUsuario().setText(usuarioActual.getNombre());  // Actualizar en el dashboard
							JOptionPane.showMessageDialog(null, "Nombre del usuario exitosamente.", "Perfil.", JOptionPane.INFORMATION_MESSAGE);
						} 
						clear();	
					}
				});
				btnAceptar.setEnabled(false);
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (btnCerrar.getText().equalsIgnoreCase("Volver")) {
							clear();
						} else if (btnCerrar.getText().equalsIgnoreCase("Cerrar")) { 
							dispose();
						}
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		clear();
	}
	
	private void clear() {
		// Ajuste de visibilidad
		panelData.setVisible(true);
		txtNombre.setText(usuarioActual.getNombre());
		txtUsername.setText(usuarioActual.getUsername());
		passPass.setText("");
		
		// Cambiar todo lo demás a desactivado
		// Para cambiar contraseña
		panelCambiar.setVisible(false);
		passActual.setEnabled(false);
		passActual.setText("");
		passNueva.setEnabled(false);
		passNueva.setText("");
		passConfirmar.setEnabled(false);
		passConfirmar.setText("");
		
		// Para cambiar el nombre del usuario.
		panelNombre.setVisible(false);
		txtNuevoNombre.setEnabled(false);
		txtNuevoNombre.setText("");
		passActualNombre.setEnabled(false);
		passActualNombre.setText("");
		
		// botones
		btnAceptar.setEnabled(false);
		btnCerrar.setText("Cerrar");
	}
}
