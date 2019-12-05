package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logical.Cita;
import logical.Clinica;
import logical.Consulta;
import logical.Empleado;
import logical.Enfermedad;
import logical.Paciente;
import logical.Vacuna;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

public class RegConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdConsulta;
	private JTextField txtIdDoctor;
	private JTextField txtIdPaciente;
	private JTextField txtFieldFecha;

	// Panel
	private JPanel panelEnfermedad;

	// Text Area
	private JTextArea txtDiagnostico;
	private JTextArea txtTratamiento;
	private JTextArea txtSintomas;

	// Botones
	private JButton btnAgregar;
	private JButton btnRemover;
	private JButton btnVacunas;
	private JButton btnBuscarPaciente;

	private JList<String> lstPaciente;
	private JList<String> lstSistema;

	// Variables lógicas
	private static Paciente paciente;
	private Enfermedad dataEnfer;
	private ArrayList<String> enfermedadesArr;  
	private ArrayList<String> enfermedadesSelec;
	private ArrayList<String> enfermedadesModi;
	private ArrayList<Vacuna> copyVacuna;
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
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegConsulta.class.getResource("/image/caduceusBlue.png")));
		setTitle("Agregar Consulta");
		setBounds(100, 100, 712, 488);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 238, 684, 177);
		contentPanel.add(panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades del sistema", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(304, 13, 185, 153);
		panel_3.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_4.add(scrollPane_1, BorderLayout.CENTER);

		lstSistema = new JList<String>();
		scrollPane_1.setViewportView(lstSistema);
		lstSistema.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (lstSistema.getSelectedIndex() != -1) {
					dataEnfer = Clinica.getInstance().buscarEnfermedadByNombre(lstPaciente.getSelectedValue());
					if (dataEnfer != null) {
						panelEnfermedad.setBorder(new TitledBorder(null, dataEnfer.getNombre() + (dataEnfer.isListar() == false ? "(Eliminada)" : ""),
								TitledBorder.LEADING, TitledBorder.TOP, null, null));
						txtSintomas.setText(dataEnfer.getSintomas());
						lstPaciente.clearSelection();
					}
				}
			}
		});
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(10, 11, 185, 153);
		panel_3.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane, BorderLayout.CENTER);

		lstPaciente = new JList<String>();
		scrollPane.setViewportView(lstPaciente);
		lstPaciente.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (lstPaciente.getSelectedIndex() != -1) {
					dataEnfer = Clinica.getInstance().buscarEnfermedadByNombreSinRestriccion(lstPaciente.getSelectedValue());
					if (dataEnfer != null) {
						panelEnfermedad.setBorder(new TitledBorder(null, dataEnfer.getNombre() + (dataEnfer.isListar() == false ? "(Eliminada)" : ""),
								TitledBorder.LEADING, TitledBorder.TOP, null, null));
						txtSintomas.setText(dataEnfer.getSintomas());
						lstSistema.clearSelection();
					}
				}
			}
		});

		/*for (Enfermedad enfer: Clinica.getInstance().getEnfermedades()) {
			if (enfer.isListar()) {
				if (enfermedadesSelec.indexOf(enfer.getNombre()) == -1) { // agregar si no se encuentra aquí
					enfermedadesArr.add(enfer.getNombre());
				}
			}
		}*/

		btnAgregar = new JButton("Agregar");
		btnAgregar.setEnabled(false);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar(true, lstSistema.getSelectedIndex());
			}
		});
		btnAgregar.setBounds(205, 42, 89, 38);
		panel_3.add(btnAgregar);

		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar(false, lstPaciente.getSelectedIndex());
			}
		});
		btnRemover.setEnabled(false);
		btnRemover.setBounds(205, 91, 89, 38);
		panel_3.add(btnRemover);

		panelEnfermedad = new JPanel();
		panelEnfermedad.setBorder(new TitledBorder(null, "<Enfermedad>", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEnfermedad.setBounds(499, 13, 175, 153);
		panel_3.add(panelEnfermedad);
		panelEnfermedad.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		panelEnfermedad.add(scrollPane_2, BorderLayout.CENTER);

		txtSintomas = new JTextArea();
		txtSintomas.setEditable(false);
		txtSintomas.setLineWrap(true);
		txtSintomas.setWrapStyleWord(true);
		scrollPane_2.setViewportView(txtSintomas);

		//iniciarLista();

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(10, 11, 684, 63);
		contentPanel.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblIdDeConsulta = new JLabel("ID de consulta:");
		lblIdDeConsulta.setBounds(49, 13, 96, 14);
		panel_6.add(lblIdDeConsulta);

		txtIdConsulta = new JTextField();
		txtIdConsulta.setEditable(false);
		txtIdConsulta.setBounds(49, 32, 96, 20);
		panel_6.add(txtIdConsulta);
		txtIdConsulta.setColumns(10);

		// Buscar la consulta en:
		int codigoConsulta = 0;
		if (Clinica.getInstance().getConsultas().size() != 0) {
			int lastIndex = Clinica.getInstance().getConsultas().size() - 1;
			codigoConsulta = Integer.valueOf(Clinica.getInstance().getConsultas().get(lastIndex).getIdConsulta().substring(2));			
		}		
		txtIdConsulta.setText("C-" + codigoConsulta);

		JLabel lblIdDeDoctor = new JLabel("ID de doctor:");
		lblIdDeDoctor.setBounds(184, 13, 96, 14);
		panel_6.add(lblIdDeDoctor);

		txtIdDoctor = new JTextField();
		txtIdDoctor.setEditable(false);
		txtIdDoctor.setBounds(184, 32, 96, 20);
		panel_6.add(txtIdDoctor);
		txtIdDoctor.setColumns(10);
		txtIdDoctor.setText(actual.getIdEmpleado());

		JLabel lblBuscarPaciente = new JLabel("Buscar paciente por ID:");
		lblBuscarPaciente.setBounds(317, 13, 197, 14);
		panel_6.add(lblBuscarPaciente);

		txtIdPaciente = new JTextField();
		txtIdPaciente.setBounds(317, 32, 96, 20);
		panel_6.add(txtIdPaciente);
		txtIdPaciente.setColumns(10);

		btnBuscarPaciente = new JButton("");
		btnBuscarPaciente.setToolTipText("Buscar");
		btnBuscarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paciente = Clinica.getInstance().buscarPacienteById(txtIdPaciente.getText());
				if(paciente != null) {
					JOptionPane.showMessageDialog(null, "Se ha encontrado el paciente.", "Notificación", JOptionPane.INFORMATION_MESSAGE);
					rellenarDatos();
				} else {
					JOptionPane.showMessageDialog(null, "No existe un paciente con este ID.", "Notificación", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnBuscarPaciente.setIcon(new ImageIcon(RegConsulta.class.getResource("/image/magnifying-glass.png")));
		btnBuscarPaciente.setBounds(425, 31, 89, 23);
		panel_6.add(btnBuscarPaciente);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(543, 13, 96, 14);
		panel_6.add(lblFecha);

		SimpleDateFormat DF = new SimpleDateFormat( "dd-MM-yyyy");

		txtFieldFecha = new JTextField();
		txtFieldFecha.setEditable(false);
		txtFieldFecha.setBounds(543, 32, 96, 20);
		panel_6.add(txtFieldFecha);
		txtFieldFecha.setColumns(10);
		txtFieldFecha.setText(DF.format(new Date()));

		JPanel panelConsulta = new JPanel();
		panelConsulta.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Consulta", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelConsulta.setBounds(10, 77, 684, 150);
		contentPanel.add(panelConsulta);
		panelConsulta.setLayout(null);

		btnVacunas = new JButton("Vacunas");
		btnVacunas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarVacunas ventana = new AgregarVacunas(copyVacuna);
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		btnVacunas.setEnabled(false);
		btnVacunas.setToolTipText("Agregar vacunas.");
		btnVacunas.setBounds(557, 53, 89, 45);
		if (Clinica.getInstance().getVacunas().size() == 0) {
			btnVacunas.setToolTipText("No hay vacunas en el sistema.");
		}
		panelConsulta.add(btnVacunas);

		JPanel panelDiagnostico = new JPanel();
		panelDiagnostico.setBorder(new TitledBorder(null, "Diagn\u00F3stico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDiagnostico.setBounds(10, 11, 255, 128);
		panelConsulta.add(panelDiagnostico);
		panelDiagnostico.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneDiagnostico = new JScrollPane();
		scrollPaneDiagnostico.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelDiagnostico.add(scrollPaneDiagnostico, BorderLayout.CENTER);

		txtDiagnostico = new JTextArea();
		txtDiagnostico.setEditable(false);
		txtDiagnostico.setLineWrap(true);
		txtDiagnostico.setWrapStyleWord(true);
		scrollPaneDiagnostico.setViewportView(txtDiagnostico);

		JPanel panelTratamiento = new JPanel();
		panelTratamiento.setBorder(new TitledBorder(null, "Tratamiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTratamiento.setBounds(275, 11, 255, 128);
		panelConsulta.add(panelTratamiento);
		panelTratamiento.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneTratamiento = new JScrollPane();
		scrollPaneTratamiento.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelTratamiento.add(scrollPaneTratamiento, BorderLayout.CENTER);

		txtTratamiento = new JTextArea();
		txtTratamiento.setEditable(false);
		txtTratamiento.setWrapStyleWord(true);
		txtTratamiento.setLineWrap(true);
		scrollPaneTratamiento.setViewportView(txtTratamiento);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton("A\u00F1adir al Historial");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if(paciente == null) {
							JOptionPane.showMessageDialog(null, "No se ha elegido el paciente.", "Notificación", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						Consulta nueva = new Consulta(txtIdConsulta.getText(), txtIdDoctor.getText(), paciente.getIdPaciente(),
								new Date(), txtDiagnostico.getText(), txtTratamiento.getText());
						// Agregado y eliminado de las enfermedades puestas.
						// Saber a cuales enfermedades se les está quitando y sumando.					
						int index;
						Enfermedad buscar = null;
						for (String enfer : enfermedadesSelec) {
							if (enfermedadesModi.indexOf(enfer) != -1) { // Significa que se queda igual y se puede poner que no se modifico quitandolo
								index = enfermedadesModi.indexOf(enfer);
								enfermedadesModi.remove(index);
							} else {	// Se agrega por primera vez
								buscar = Clinica.getInstance().buscarEnfermedadByNombre(enfer);
								buscar.setCantPacientes(buscar.getCantPacientes() + 1);
							}
						}
						// Ahora lo que quede en modi, sacarlo.
						for (String enfer : enfermedadesModi) {
							buscar = Clinica.getInstance().buscarEnfermedadByNombre(enfer);
							buscar.setCantPacientes(buscar.getCantPacientes() - 1);
						}

						paciente.setEnfermedades(enfermedadesSelec);
						// Agregar cambios en las vacunas.
						if (copyVacuna.size() > 0)
							paciente.setTodasLasVacunas(copyVacuna);
						
						// Volver a cargar dashboard de enfermedades y vacunas.
						Dashboard.cargarEnferCrit();
						Dashboard.cargarVacunacion();

						// Añadir al historial del paciente
						paciente.getHistoriaClinica().add(nueva);	// Tanto paciente como clínica lo tienen
						Clinica.getInstance().addConsulta(nueva);
						if (cita != null) { // Consulta con cita.
							Clinica.getInstance().getCitas().remove(cita);	// Se elimina la cita
							dispose();										// Se cierra
							BuscarCita ventana = new BuscarCita(actual);	// Se vuelve a la ventana principal
							ventana.setModal(true);
							ventana.setVisible(true);
							return;
						} else {
							clear();			// Se limpia todo para volver a crear una nueva consulta.
						}
					}
				});
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
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

		// Si la cita esta, entonces no se puede buscar
		if (cita != null) {
			txtIdPaciente.setText(cita.getIdPaciente());
			btnBuscarPaciente.setEnabled(false);
			txtIdPaciente.setEnabled(false);
			txtDiagnostico.setEditable(true);
			txtTratamiento.setEditable(true);
			paciente = Clinica.getInstance().buscarPacienteById(cita.getIdPaciente());
			rellenarDatos();

		}

	}
	/*
	private void iniciarLista() {
		// Borrar datos
		DefaultListModel<String> model = new DefaultListModel<String>();
		DefaultListModel<String> modelB = new DefaultListModel<String>();
		// Igualar a lista vacía para que se reinicie.
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

	}*/

	private void reiniciarLista() {
		// Borrar datos
		DefaultListModel<String> model = new DefaultListModel<String>();
		DefaultListModel<String> modelB = new DefaultListModel<String>();
		// Igualar a lista vacía para que se reinicie.
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

	private void rellenarDatos() {
		txtDiagnostico.setEditable(true);
		txtTratamiento.setEditable(true);

		enfermedadesArr = new ArrayList<String>();
		enfermedadesSelec = new ArrayList<String>();
		enfermedadesModi = new ArrayList<String>();
		copyVacuna = new ArrayList<Vacuna>();
		
		// Rellenar las enfermedades que tiene el paciente hasta el momento.
		for (String data : paciente.getEnfermedades()) {
			// ya que verificar revisa si se puede listar, y permite saber si se puede crear.
			if (Clinica.getInstance().verificarEnfermedad(data) == false) {
				enfermedadesSelec.add(data);
				enfermedadesModi.add(data);
			}
		}

		// Rellenar las enfermedades que 
		for (Enfermedad enfer: Clinica.getInstance().getEnfermedades()) {
			if (enfer.isListar()) {
				if (enfermedadesSelec.indexOf(enfer.getNombre()) == -1) { // agregar si no se encuentra aquí
					enfermedadesArr.add(enfer.getNombre());
				}
			}
		}
		
		// Ordenar alfabéticamente
		Collections.sort(enfermedadesSelec);
		Collections.sort(enfermedadesArr);
		Collections.sort(enfermedadesModi);
		
		// Ya que se tiene el paciente si hay vacunas activar el btn respectivo y copiar a la lista de vacunas.
		if (Clinica.getInstance().getVacunas().size() > 0) {
			// Clonar todas las vacunas...
			for (Vacuna vacuna : paciente.getTodasLasVacunas())
				copyVacuna.add(vacuna);
			btnVacunas.setEnabled(true);
		}
		
		// Reiniciar listas
		reiniciarLista();

		return;
	}

	private void clear() {
		// Al reiniciar.
		// Buscar la consulta en:
		int codigoConsulta = 0;
		if (Clinica.getInstance().getConsultas().size() != 0) {
			int lastIndex = Clinica.getInstance().getConsultas().size() - 1;
			codigoConsulta = Integer.valueOf(Clinica.getInstance().getConsultas().get(lastIndex).getIdConsulta().substring(2));			
		}		
		txtIdConsulta.setText("C-" + codigoConsulta);
		
		txtIdPaciente.setEditable(true);
		txtIdPaciente.setText("");
		btnBuscarPaciente.setEnabled(true);
		txtDiagnostico.setEditable(false);
		txtDiagnostico.setText("");
		txtTratamiento.setEditable(false); 
		txtTratamiento.setText("");
		
		// Reiniciar listas
		lstPaciente.setModel(new DefaultListModel<String>());
		lstSistema.setModel(new DefaultListModel<String>());
		
		// Listas virtuales
		enfermedadesArr = new ArrayList<String>();
		enfermedadesSelec = new ArrayList<String>();
		enfermedadesModi = new ArrayList<String>();
		
		// Panel para presentar los datos de la enfermedad.
		txtSintomas.setText("");
		panelEnfermedad.setBorder(new TitledBorder(null, "<Enfermedad>", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		btnVacunas.setEnabled(false);
		// Variables lógicas
		paciente = null;
		dataEnfer = null;
		return;
	}

}

