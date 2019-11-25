package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
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

public class Usuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable userTable;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	
	// Botones
	
	

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
	public Usuarios() {
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
			
			JComboBox cbxTipoUsuario = new JComboBox();
			cbxTipoUsuario.setToolTipText("");
			cbxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Doctor", "Secretario/a"}));
			cbxTipoUsuario.setBounds(10, 296, 198, 20);
			panel.add(cbxTipoUsuario);
			
			JButton btnCrear = new JButton("Crear");
			btnCrear.setBounds(13, 339, 89, 23);
			panel.add(btnCrear);
			
			JButton btnModificar = new JButton("Modificar");
			btnModificar.setBounds(115, 339, 89, 23);
			panel.add(btnModificar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
	}
}
