package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarPaciente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Dimension dim;
	private JTextField textFieldNombre;
	private JTextField textFieldIdentificacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarPaciente dialog = new BuscarPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarPaciente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarPaciente.class.getResource("/image/caduceusBlue.png")));
		setTitle("Buscar Paciente");
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 48, 14);
		contentPanel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(10, 29, 310, 20);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JButton btnBuscarCliente = new JButton("");
		btnBuscarCliente.setIcon(new ImageIcon(BuscarPaciente.class.getResource("/image/magnifying-glass.png")));
		btnBuscarCliente.setBounds(330, 29, 89, 23);
		contentPanel.add(btnBuscarCliente);
		
		JLabel lblIdentificacin = new JLabel("Identificaci\u00F3n:");
		lblIdentificacin.setBounds(442, 11, 89, 14);
		contentPanel.add(lblIdentificacin);
		
		textFieldIdentificacion = new JTextField();
		textFieldIdentificacion.setBounds(442, 29, 153, 20);
		contentPanel.add(textFieldIdentificacion);
		textFieldIdentificacion.setColumns(10);
		
		JButton btnBuscarIdentificacion = new JButton("");
		btnBuscarIdentificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscarIdentificacion.setIcon(new ImageIcon(BuscarPaciente.class.getResource("/image/magnifying-glass.png")));
		btnBuscarIdentificacion.setBounds(605, 26, 89, 23);
		contentPanel.add(btnBuscarIdentificacion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 500, 337);
		contentPanel.add(scrollPane);
		
		JLabel lblInformacin = new JLabel("Informaci\u00F3n:");
		lblInformacin.setBounds(520, 60, 88, 14);
		contentPanel.add(lblInformacin);
		
		JPanel panel = new JPanel();
		panel.setBounds(520, 79, 174, 318);
		contentPanel.add(panel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Buscar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
