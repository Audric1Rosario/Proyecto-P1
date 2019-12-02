package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import logical.Clinica;
import logical.Enfermedad;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CrearEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();

	// Textbox
	private JTextField txtNombreEnfermedad;
	// Text areas
	private JTextArea txtDiagnostico;
	private JTextArea txtSintomas;

	// variables logicas.
	private Enfermedad modificar;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			CrearEnfermedad dialog = new CrearEnfermedad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public CrearEnfermedad(Enfermedad modificar) {
		setResizable(false);
		this.modificar = modificar;

		if (modificar == null)
			setTitle("Crear enfermedad");
		else 
			setTitle("Modificar enfermedad");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CrearEnfermedad.class.getResource("/image/caduceus.png")));
		setBounds(100, 100, 480, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 444, 286);
		contentPanel.add(panel);
		panel.setLayout(null);

		txtNombreEnfermedad = new JTextField();
		txtNombreEnfermedad.setBounds(196, 11, 238, 20);
		if (modificar != null) {
			txtNombreEnfermedad.setText(modificar.getNombre());
			txtNombreEnfermedad.setEditable(false);
		}
		panel.add(txtNombreEnfermedad);
		txtNombreEnfermedad.setColumns(10);

		JLabel lblNombreDeLa = new JLabel("Nombre de la enfermedad: ");
		lblNombreDeLa.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombreDeLa.setBounds(10, 14, 176, 14);
		panel.add(lblNombreDeLa);

		JPanel pnlSintomas = new JPanel();
		pnlSintomas.setBorder(new TitledBorder(null, "S\u00EDntomas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSintomas.setBounds(10, 42, 424, 111);
		panel.add(pnlSintomas);
		pnlSintomas.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlSintomas.add(scrollPane, BorderLayout.CENTER);

		txtSintomas = new JTextArea();		
		txtSintomas.setLineWrap(true);
		txtSintomas.setWrapStyleWord(true);
		if (modificar != null) {
			txtSintomas.setText(modificar.getSintomas());
		}
		scrollPane.setViewportView(txtSintomas);

		JPanel pnlDiagnostico = new JPanel();
		pnlDiagnostico.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Diagn\u00F3stico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDiagnostico.setBounds(10, 164, 424, 111);
		panel.add(pnlDiagnostico);
		pnlDiagnostico.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlDiagnostico.add(scrollPane_1, BorderLayout.CENTER);

		txtDiagnostico = new JTextArea();
		txtDiagnostico.setLineWrap(true);
		txtDiagnostico.setWrapStyleWord(true);
		if (modificar != null) {
			txtDiagnostico.setText(modificar.getDiagnostico());
		}
		scrollPane_1.setViewportView(txtDiagnostico);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// Verificación de que hay datos.
						if (txtNombreEnfermedad.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Por favor, escriba el nombre de la enfermedad.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
							return;
						}

						if (txtSintomas.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Por favor, escriba los síntomas que presenta esta enfermedad.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
							return;
						}

						// Verificación de que no se repite el nombre de la enfermedad.
						// Revisar que no se repita
						if (modificar == null) {
							if (Clinica.getInstance().verificarEnfermedad(txtNombreEnfermedad.getText()) == false) {
								JOptionPane.showMessageDialog(null, "El nombre de la enfermedad ya existe, favor coloque uno nuevo", "Advertencia.", JOptionPane.WARNING_MESSAGE);
								return;
							} 
						}

						if (modificar != null) {							
							modificar.setSintomas(txtSintomas.getText());
							modificar.setDiagnostico(txtDiagnostico.getText());
							ListaEnfermedades.rellenarTabla(txtNombreEnfermedad.getText());
							ListaEnfermedades.sclear();
							dispose();
						} else {
							Enfermedad nueva = new Enfermedad(txtNombreEnfermedad.getText(), txtSintomas.getText(), txtDiagnostico.getText());
							Clinica.getInstance().addEnfermedad(nueva);
							clear();
						}										
					}
				});
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	private void rellenarDatos() {
		if (modificar != null) {
			txtNombreEnfermedad.setText(modificar.getNombre());
			txtSintomas.setText(modificar.getSintomas());
			txtDiagnostico.setText(modificar.getDiagnostico());
		} else {
			JOptionPane.showMessageDialog(null, "Error de modificación de datos.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void clear() {
		txtNombreEnfermedad.setText("");
		txtSintomas.setText("");
		txtDiagnostico.setText("");
	}
}
