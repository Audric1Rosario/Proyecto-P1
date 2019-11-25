package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logical.Administrador;
import logical.Clinica;
import logical.Empleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private String screenPath;
	private JPasswordField passPass;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream clinica;
				FileOutputStream clinica2;
				ObjectInputStream clinicaRead;
				ObjectOutputStream clinicaWrite;
				
				// Crear directorio
				String path = System.getProperty("user.dir");
				File directorio = new File(path + "/data");
				
				if (!directorio.exists()) {
					if (directorio.mkdirs()) {
						
					} else {
						JOptionPane.showMessageDialog(null, "Error al cargar datos.", "Data.", JOptionPane.WARNING_MESSAGE);
					}
				}
				
				// Cargar la clase controladora
				try {
					clinica = new FileInputStream ("data/clinica.dat");
					clinicaRead = new ObjectInputStream(clinica);
					Clinica temp = (Clinica)clinicaRead.readObject();
					Clinica.setInstance(temp);
					clinica.close();
					clinicaRead.close();
				} catch (FileNotFoundException e) {
					try {
						clinica2 = new  FileOutputStream("data/clinica.dat");
						clinicaWrite = new ObjectOutputStream(clinica2);
						Empleado aux = new Administrador("Administrador", "Admin", "Admin", 1);
						Clinica.getInstance().addEmpleado(aux);;
						clinicaWrite.writeObject(Clinica.getInstance());
						clinica2.close();
						clinicaWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {


				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				// Abrir el frame
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setInit();
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/image/caduceus.png")));
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblLoginImage = new JLabel("");
		screenPath = "/image/userA.png";
		lblLoginImage.setBounds(278, 49, 186, 186);
		lblLoginImage.setIcon(new ImageIcon(((new ImageIcon(Login.class.getResource(screenPath))).getImage()).getScaledInstance(
				lblLoginImage.getWidth(), lblLoginImage.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(lblLoginImage);
		
		JLabel lblLogo = new JLabel("");
		screenPath = "/image/caduceusA.png";
		lblLogo.setBounds(10, 11, 66, 61);
		lblLogo.setIcon(new ImageIcon(((new ImageIcon(Login.class.getResource(screenPath))).getImage()).getScaledInstance(
				lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(lblLogo);
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setBounds(255, 285, 233, 27);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnEnter = new JButton("Entrar");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsuario.getText();
				String password = String.valueOf(passPass.getPassword());
				boolean ok = false;
				
				Empleado actual = Clinica.getInstance().buscarEmpleadoByUsername(username);
				
				if (actual != null)
				{
					if (actual.getPassword().equals(password)) {
						ok = true;
					}
				}
				
				if (ok) {
					Dashboard ventana = new Dashboard();
					Clinica.getInstance().setUsuarioActual(actual);
					ventana.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error de inicio.", JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
		btnEnter.setBounds(285, 395, 173, 27);
		panel.add(btnEnter);
		
		JLabel lblUsername = new JLabel("Usuario");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(334, 261, 74, 14);
		panel.add(lblUsername);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(334, 323, 74, 14);
		panel.add(lblContrasea);
		
		JLabel lblNewLabel = new JLabel("Cl\u00EDnica General");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(70, 130, 180));
		lblNewLabel.setBounds(75, 23, 154, 36);
		panel.add(lblNewLabel);
		
		passPass = new JPasswordField();
		passPass.setHorizontalAlignment(SwingConstants.CENTER);
		passPass.setBounds(255, 348, 233, 27);
		panel.add(passPass);
	}

	private void setInit() {
		// TODO Auto-generated method stub
		Clinica.getInstance(); // Crear la clase controladora
		
	}
}
