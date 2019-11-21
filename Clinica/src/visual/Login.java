package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Image;
import java.security.Principal;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String screenPath;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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
		
		textField = new JTextField();
		textField.setBounds(255, 285, 233, 27);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(255, 348, 233, 27);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnEnter = new JButton("Entrar");
		btnEnter.setBounds(285, 395, 173, 27);
		panel.add(btnEnter);
		
		JLabel lblUsername = new JLabel("Username");
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
	}
}
