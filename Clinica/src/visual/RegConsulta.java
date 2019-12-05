package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logical.Cita;
import logical.Clinica;
import logical.Consulta;
import logical.Empleado;
import logical.Enfermedad;
import logical.Paciente;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class RegConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldIdentificacion;
	private JTextField textFieldIdDoctor;
	private JTextField textFieldPaciente;
	private JTextField txtFieldFecha;
	private JList<String> lstPaciente;
	private JList<String> lstSistema;
	private JButton btnAgregar;
	private JButton btnRemover;
	
	private Paciente paciente;
	private ArrayList<String> enfermedadesArr;  
	private ArrayList<String> enfermedadesSelec;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			RegConsulta dialog = new RegConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegConsulta(Empleado actual, Cita cita) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegConsulta.class.getResource("/image/caduceusBlue.png")));
		setTitle("Agregar Consulta");
		setBounds(100, 100, 720, 488);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 200, 684, 177);
		contentPanel.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades del sistema", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(417, 11, 257, 153);
		panel_3.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_4.add(scrollPane_1, BorderLayout.CENTER);
		
		lstSistema = new JList<String>();
		scrollPane_1.setViewportView(lstSistema);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(10, 11, 257, 153);
		panel_3.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane, BorderLayout.CENTER);
		
		lstPaciente = new JList<String>();
		scrollPane.setViewportView(lstPaciente);
		
		/*for (Enfermedad enfer: Clinica.getInstance().getEnfermedades()) {
			if (enfer.isListar()) {
				if (enfermedadesSelec.indexOf(enfer.getNombre()) == -1) { // agregar si no se encuentra aqu�
					enfermedadesArr.add(enfer.getNombre());
				}
			}
		}*/
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar(true, lstSistema.getSelectedIndex());
			}
		});
		btnAgregar.setBounds(298, 52, 89, 23);
		panel_3.add(btnAgregar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar(false, lstPaciente.getSelectedIndex());
			}
		});
		btnRemover.setEnabled(false);
		btnRemover.setBounds(298, 86, 89, 23);
		panel_3.add(btnRemover);
		
		//iniciarLista();
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(10, 11, 684, 63);
		contentPanel.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblIdDeConsulta = new JLabel("ID de consulta:");
		lblIdDeConsulta.setBounds(49, 11, 100, 14);
		panel_6.add(lblIdDeConsulta);
		
		textFieldIdentificacion = new JTextField();
		textFieldIdentificacion.setEditable(false);
		textFieldIdentificacion.setBounds(49, 32, 96, 20);
		panel_6.add(textFieldIdentificacion);
		textFieldIdentificacion.setColumns(10);
		textFieldIdentificacion.setText("C-" + Clinica.getCodigoConsulta());
		Clinica.aumentarCodigoConsulta();
		
		JLabel lblIdDeDoctor = new JLabel("ID de doctor:");
		lblIdDeDoctor.setBounds(184, 11, 83, 14);
		panel_6.add(lblIdDeDoctor);
		
		textFieldIdDoctor = new JTextField();
		textFieldIdDoctor.setEditable(false);
		textFieldIdDoctor.setBounds(184, 32, 96, 20);
		panel_6.add(textFieldIdDoctor);
		textFieldIdDoctor.setColumns(10);
		textFieldIdDoctor.setText(actual.getIdEmpleado());
		
		JLabel lblBuscarPaciente = new JLabel("Buscar paciente por ID:");
		lblBuscarPaciente.setBounds(317, 11, 154, 14);
		panel_6.add(lblBuscarPaciente);
		
		textFieldPaciente = new JTextField();
		textFieldPaciente.setBounds(317, 32, 96, 20);
		panel_6.add(textFieldPaciente);
		textFieldPaciente.setColumns(10);
		
		JButton btnBuscarPaciente = new JButton("");
		btnBuscarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paciente = Clinica.getInstance().buscarPacienteById(textFieldPaciente.getText());
				if(paciente != null) {
					JOptionPane.showMessageDialog(null, "Se ha encontrado el paciente.", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Lo lamentamos, no existe un paciente con esa ID.", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnBuscarPaciente.setIcon(new ImageIcon(RegConsulta.class.getResource("/image/magnifying-glass.png")));
		btnBuscarPaciente.setBounds(425, 29, 89, 23);
		panel_6.add(btnBuscarPaciente);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(543, 11, 48, 14);
		panel_6.add(lblFecha);
		
		Date date = new Date();
		SimpleDateFormat DF = new SimpleDateFormat( "dd-MMM-yyyy");
		
		txtFieldFecha = new JTextField();
		txtFieldFecha.setEditable(false);
		txtFieldFecha.setBounds(543, 32, 96, 20);
		panel_6.add(txtFieldFecha);
		txtFieldFecha.setColumns(10);
		txtFieldFecha.setText(DF.format(date));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Consulta", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBounds(10, 77, 684, 116);
		contentPanel.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblAgregarVacunas = new JLabel("Agregar vacunas:");
		lblAgregarVacunas.setBounds(558, 39, 116, 14);
		panel_7.add(lblAgregarVacunas);
		
		JButton btnVacunas = new JButton("Vacunas");
		btnVacunas.setBounds(558, 64, 89, 23);
		panel_7.add(btnVacunas);
		
		JLabel lblDiagnstico = new JLabel("Diagn\u00F3stico:");
		lblDiagnstico.setBounds(10, 11, 89, 14);
		panel_7.add(lblDiagnstico);
		
		JLabel lblTratamiento = new JLabel("Tratamiento:");
		lblTratamiento.setBounds(275, 11, 99, 14);
		panel_7.add(lblTratamiento);
		
		JPanel panelDiagnostico = new JPanel();
		panelDiagnostico.setBounds(10, 33, 255, 72);
		panel_7.add(panelDiagnostico);
		panelDiagnostico.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneDiagnostico = new JScrollPane();
		panelDiagnostico.add(scrollPaneDiagnostico, BorderLayout.CENTER);
		
		JTextPane textPaneTratamiento = new JTextPane();
		scrollPaneDiagnostico.setViewportView(textPaneTratamiento);
		
		JPanel panelTratamiento = new JPanel();
		panelTratamiento.setBounds(275, 33, 255, 72);
		panel_7.add(panelTratamiento);
		panelTratamiento.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneTratamiento = new JScrollPane();
		panelTratamiento.add(scrollPaneTratamiento, BorderLayout.CENTER);
		
		JTextPane textPaneDiagnostico = new JTextPane();
		scrollPaneTratamiento.setViewportView(textPaneDiagnostico);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Agregar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(paciente == null) {
							JOptionPane.showMessageDialog(null, "No se ha elejido el paciente.", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
							return;
						} else{
							if(cita != null) {
							Clinica.getInstance().getCitas().remove(cita);
							}
							//public Consulta(String idConsulta, String idDoctor, String idPaciente, float costo, Date fecha, String diagnostico, String tratamiento)
							Consulta nueva = new Consulta(textFieldIdentificacion.getText(), textFieldIdDoctor.getText(), paciente.getCedula(), 0, date, textPaneDiagnostico.getText(), textPaneTratamiento.getText());
							//Faltan las enfermedades
							//Faltan las vacunas
							Clinica.getInstance().buscarPacienteById(textFieldPaciente.getText()).getHistoriaClinica().add(nueva);
							Clinica.aumentarCodigoConsulta();
							JOptionPane.showMessageDialog(null, "Se ha creado una consulta exitosamente.", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
						} 
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.add(panel);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades del sistema", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(374, 11, 257, 153);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(10, 11, 257, 153);
			panel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JButton button = new JButton("Agregar");
			button.setBounds(277, 52, 89, 23);
			panel.add(button);
			
			JButton button_1 = new JButton("Remover");
			button_1.setEnabled(false);
			button_1.setBounds(277, 86, 89, 23);
			panel.add(button_1);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
		
	}
	private void iniciarLista() {
		// Borrar datos
		DefaultListModel<String> model = new DefaultListModel<String>();
		DefaultListModel<String> modelB = new DefaultListModel<String>();
		// Igualar a lista vac�a para que se reinicie.
		lstPaciente.setModel(modelB);
		lstSistema.setModel(model);

		for (Enfermedad enfer: Clinica.getInstance().getEnfermedades()) {
			enfermedadesArr.add(enfer.getNombre());
		}

		Collections.sort(enfermedadesArr);
		if (enfermedadesArr.size() == 0) {
			JOptionPane.showMessageDialog(null, "No hay enfermedades registradas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}

		// Llenar enfermedades.
		//model = (DefaultListModel<String>) lstSistema.getModel();
		for (String nombre : enfermedadesArr) {
			model.addElement(nombre);
		}

		btnAgregar.setEnabled(lstSistema.getModel().getSize() > 0);
		btnRemover.setEnabled(lstPaciente.getModel().getSize() > 0);

	}

	private void reiniciarLista() {
		// Borrar datos
		DefaultListModel<String> model = new DefaultListModel<String>();
		DefaultListModel<String> modelB = new DefaultListModel<String>();
		// Igualar a lista vac�a para que se reinicie.
		lstPaciente.setModel(modelB);
		lstSistema.setModel(model);

		// Llenar enfermedades.
		for (String nombre : enfermedadesArr) {
			model.addElement(nombre);
		}

		for (String nombre : enfermedadesSelec) {
			modelB.addElement(nombre);
		}
		btnAgregar.setEnabled(lstSistema.getModel().getSize() > 0);
		btnRemover.setEnabled(lstPaciente.getModel().getSize() > 0);
		
	}

	private void actualizar(boolean razon, int index) {
		if (razon && index != -1) { 
			enfermedadesSelec.add(enfermedadesArr.get(index));
			enfermedadesArr.remove(index);
			Collections.sort(enfermedadesSelec);

		} else if (index != -1) {   
			enfermedadesArr.add(enfermedadesSelec.get(index));
			enfermedadesSelec.remove(index);
			Collections.sort(enfermedadesArr);
		}
		reiniciarLista();
	}
	
}

