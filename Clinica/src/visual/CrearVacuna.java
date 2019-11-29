package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

import logical.Clinica;
import logical.Enfermedad;
import logical.Vacuna;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

public class CrearVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	// Text area
	private JTextArea txtEfectos;
	
	// Botones
	private JButton btnAceptar;
	
	// Combobox
	private JComboBox cbxTipo;
	private JComboBox cbxEnfermedad;
	
	// Text field
	private JTextField txtNombreVacuna;
	
	// Variables lógicas
	private Vacuna modificar;
	
	/**
	 * Launch the application.
	 */ /*
	public static void main(String[] args) {
		try {
			CrearVacuna dialog = new CrearVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Create the dialog.
	 */
	public CrearVacuna(Vacuna modificar) {
		setResizable(false);
		this.modificar = modificar;
		
		if (modificar == null)
			setTitle("Crear Vacuna.");
		else 
			setTitle("Modificar Vacuna.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CrearVacuna.class.getResource("/image/caduceus.png")));
		setBounds(100, 100, 480, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 444, 286);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Efectos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(10, 102, 424, 173);
				panel.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					panel_1.add(scrollPane, BorderLayout.CENTER);
					{
						txtEfectos = new JTextArea();
						txtEfectos.setLineWrap(true);
						txtEfectos.setWrapStyleWord(true);
						scrollPane.setViewportView(txtEfectos);
					}
				}
			}
			{
				txtNombreVacuna = new JTextField();
				if (modificar != null)
					txtNombreVacuna.setEditable(false);
				txtNombreVacuna.setBounds(151, 11, 272, 20);
				panel.add(txtNombreVacuna);
				txtNombreVacuna.setColumns(10);
			}
			{
				JLabel lblNombreDeLa = new JLabel("Nombre de la vacuna:");
				lblNombreDeLa.setHorizontalAlignment(SwingConstants.TRAILING);
				lblNombreDeLa.setBounds(21, 14, 120, 14);
				panel.add(lblNombreDeLa);
			}
			{
				JLabel lblTipo = new JLabel("Tipo:");
				lblTipo.setHorizontalAlignment(SwingConstants.TRAILING);
				lblTipo.setBounds(21, 43, 120, 14);
				panel.add(lblTipo);
			}
			
			cbxTipo = new JComboBox();
			cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Virus activo", "Muerta", "Toxoide", "Biosint\u00E9tica"}));
			cbxTipo.setBounds(151, 40, 272, 20);
			panel.add(cbxTipo);
			{
				cbxEnfermedad = new JComboBox();
				cbxEnfermedad.setBounds(151, 71, 272, 20);
				if (modificar != null) 
					cbxEnfermedad.setEnabled(false);
				panel.add(cbxEnfermedad);
			}
			{
				JLabel lblEnfermedad = new JLabel("Enfermedad:");
				lblEnfermedad.setHorizontalAlignment(SwingConstants.TRAILING);
				lblEnfermedad.setBounds(21, 74, 120, 14);
				panel.add(lblEnfermedad);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.setEnabled(false);
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						// Verificar que haya información
						if (txtNombreVacuna.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre de la vacuna.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
							return;
						}
						if (cbxTipo.getSelectedItem().toString().equalsIgnoreCase("<Seleccione>")) {
							JOptionPane.showMessageDialog(null, "Por favor, seleccione el tipo de vacuna.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
							return;
						}
						if (cbxEnfermedad.getSelectedItem().toString().equalsIgnoreCase("<Seleccione>")) {
							JOptionPane.showMessageDialog(null, "Por favor, seleccione la enfermedad que la vacuna contrarresta", "Advertencia", JOptionPane.WARNING_MESSAGE);
							return;
						}
						if (txtEfectos.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Por favor, escriba los efectos que produce la vacuna.", "Advertencia", JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						// Verficar que si este disponible el nombre de la vacuna.
						if (modificar == null) {
							if (!Clinica.getInstance().verificarVacuna(txtNombreVacuna.getText())) {
								JOptionPane.showMessageDialog(null, "Ya existe una vacuna con este nombre. Por favor, digite otro nombre.", "Advertencia", JOptionPane.WARNING_MESSAGE);
								return;
							}
						}
						
						if (modificar != null) { 	//	si esta modificando
							modificar.setEfectos(txtEfectos.getText());
							modificar.setTipo(cbxTipo.getSelectedItem().toString());
							ListaVacunas.rellenarTabla(0);
							dispose();
						} else { // Si no lo esta
							Vacuna nueva = new Vacuna(txtNombreVacuna.getText(), cbxTipo.getSelectedItem().toString(), cbxEnfermedad.getSelectedItem().toString(),
									txtEfectos.getText());
							Clinica.getInstance().addVacuna(nueva);
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
		initVacuna();
		if (modificar != null) {
			rellenarDatos();
		}
	}
	
	private void initVacuna() {
		if (Clinica.getInstance().getEnfermedades().size() == 0) {
			if (modificar != null) {
				JOptionPane.showMessageDialog(null, "No existen enfermedades para curar. No es posible modificar la vacuna.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "No existen enfermedades para curar. No es posible crear una vacuna.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
			}
			
			txtNombreVacuna.setEnabled(false);
			cbxTipo.setEnabled(false);
			cbxEnfermedad.setEnabled(false);
			txtEfectos.setEnabled(false);
		} else {
			btnAceptar.setEnabled(true);
			cbxEnfermedad.addItem("<Seleccione>");
			for (Enfermedad enfermedad : Clinica.getInstance().getEnfermedades()) {
				cbxEnfermedad.addItem(enfermedad.getNombre());
			}			
		}
		return;
	}
	
	private void rellenarDatos() {
		int aux = 0; boolean encontrado = false;
		txtNombreVacuna.setText(modificar.getNombre());
		while (!encontrado && aux < cbxTipo.getItemCount()) {
			if (modificar.getTipo().equalsIgnoreCase(cbxTipo.getItemAt(aux).toString())) {
				encontrado = true;
			}
			aux++;
		}
		cbxTipo.setSelectedIndex(aux - 1);
		aux = 0; encontrado = false;
		while (!encontrado && aux < cbxEnfermedad.getItemCount()) {
			if (modificar.getEnfermedadNombre().equalsIgnoreCase(cbxEnfermedad.getItemAt(aux).toString())) {
				encontrado = true;
			}
			aux++;
		}
		cbxEnfermedad.setSelectedIndex(aux - 1);
		cbxEnfermedad.setEnabled(false);
		txtEfectos.setText(modificar.getEfectos());
		return;
	}
	
	private void clear()  {
		txtNombreVacuna.setText("");
		cbxTipo.setSelectedIndex(0);
		cbxEnfermedad.setSelectedIndex(0);
		txtEfectos.setText("");
		return;
	}
}
