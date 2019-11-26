package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class CrearCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPaciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearCita dialog = new CrearCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearCita() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CrearCita.class.getResource("/image/caduceus.png")));
		setBounds(100, 100, 670, 250);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setBounds(31, 24, 80, 14);
		contentPanel.add(lblPaciente);
		
		txtPaciente = new JTextField();
		txtPaciente.setBounds(31, 39, 209, 20);
		contentPanel.add(txtPaciente);
		txtPaciente.setColumns(10);
		
		JLabel lblDia = new JLabel("Dia:");
		lblDia.setBounds(31, 85, 46, 14);
		contentPanel.add(lblDia);
		
		JButton btnDomingo = new JButton("Domingo");
		btnDomingo.setBounds(31, 101, 89, 67);
		contentPanel.add(btnDomingo);
		
		JButton btnLunes = new JButton("Lunes");
		btnLunes.setBounds(119, 101, 73, 67);
		contentPanel.add(btnLunes);
		
		JButton btnMartes = new JButton("Martes");
		btnMartes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnMartes.setBounds(189, 101, 87, 67);
		contentPanel.add(btnMartes);
		
		JButton btnMiercoles = new JButton("Miercoles");
		btnMiercoles.setBounds(271, 101, 95, 67);
		contentPanel.add(btnMiercoles);
		
		JButton btnJueves = new JButton("Jueves");
		btnJueves.setBounds(365, 101, 88, 67);
		contentPanel.add(btnJueves);
		
		JButton btnViernes = new JButton("Viernes");
		btnViernes.setBounds(447, 101, 89, 67);
		contentPanel.add(btnViernes);
		
		JButton btnSabado = new JButton("Sabado");
		btnSabado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSabado.setBounds(533, 101, 95, 67);
		contentPanel.add(btnSabado);
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
