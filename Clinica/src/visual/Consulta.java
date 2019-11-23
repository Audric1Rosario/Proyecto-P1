package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Consulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldIdentificacion;
	private JTextField textFieldDoctor;
	private JTextField textFieldPaciente;
	private JTextField textFieldFecha;
	private JTextField textFieldCosto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Consulta dialog = new Consulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Consulta() {
		setTitle("Consulta");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Consulta.class.getResource("/image/caduceusBlue.png")));
		setBounds(100, 100, 720, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Identificacion de Consulta: ");
			lblNewLabel.setBounds(10, 11, 149, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblDoctor = new JLabel("Doctor:");
			lblDoctor.setBounds(356, 11, 48, 14);
			contentPanel.add(lblDoctor);
		}
		{
			textFieldIdentificacion = new JTextField();
			textFieldIdentificacion.setEditable(false);
			textFieldIdentificacion.setBounds(10, 36, 315, 20);
			contentPanel.add(textFieldIdentificacion);
			textFieldIdentificacion.setColumns(10);
		}
		{
			textFieldDoctor = new JTextField();
			textFieldDoctor.setEditable(false);
			textFieldDoctor.setBounds(356, 36, 315, 20);
			contentPanel.add(textFieldDoctor);
			textFieldDoctor.setColumns(10);
		}
		{
			JLabel lblPaciente = new JLabel("Paciente: ");
			lblPaciente.setBounds(10, 67, 74, 14);
			contentPanel.add(lblPaciente);
		}
		{
			textFieldPaciente = new JTextField();
			textFieldPaciente.setEditable(false);
			textFieldPaciente.setBounds(10, 92, 315, 20);
			contentPanel.add(textFieldPaciente);
			textFieldPaciente.setColumns(10);
		}
		{
			JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(356, 67, 48, 14);
			contentPanel.add(lblFecha);
		}
		{
			textFieldFecha = new JTextField();
			textFieldFecha.setEditable(false);
			textFieldFecha.setBounds(356, 92, 315, 20);
			contentPanel.add(textFieldFecha);
			textFieldFecha.setColumns(10);
		}
		{
			JLabel lblEnfermedades = new JLabel("Enfermedades:");
			lblEnfermedades.setBounds(10, 130, 89, 14);
			contentPanel.add(lblEnfermedades);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 155, 315, 191);
			contentPanel.add(scrollPane);
		}
		{
			JLabel lblTratamiento = new JLabel("Tratamiento:");
			lblTratamiento.setBounds(356, 130, 82, 14);
			contentPanel.add(lblTratamiento);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(356, 155, 315, 191);
			contentPanel.add(scrollPane);
		}
		{
			JLabel lblCosto = new JLabel("Costo:");
			lblCosto.setBounds(356, 363, 48, 14);
			contentPanel.add(lblCosto);
		}
		{
			textFieldCosto = new JTextField();
			textFieldCosto.setEditable(false);
			textFieldCosto.setBounds(414, 357, 257, 20);
			contentPanel.add(textFieldCosto);
			textFieldCosto.setColumns(10);
		}
		{
			JButton btnNewButton = new JButton("Vacunas");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton.setBounds(68, 359, 199, 23);
			contentPanel.add(btnNewButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
	}

}
