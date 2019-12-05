package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import logical.Paciente;
import logical.Vacuna;
import logical.Clinica;
import logical.Consulta;
import logical.Enfermedad;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class BuscarConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textFieldIdentificacion;
	
	// Tabla
	private static JTable tabla;
	private static DefaultTableModel model;
	private static Object[] fila;
	
	// Text area y lista.
	private JTextArea txtDiagnostico;
	private JTextArea txtSintomas;
	private JList<String> lstVacunas;
	
	// Variables lógicas
	private Consulta consulta;
	private Paciente paciente;
	
	
	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			BuscarConsulta dialog = new BuscarConsulta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public BuscarConsulta(Paciente paciente) {
		this.consulta = null;
		this.paciente = paciente;
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarConsulta.class.getResource("/image/caduceus.png")));
		setTitle("Reporte cl\u00EDnico");
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 11, 96, 14);
			contentPanel.add(lblNombre);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setEditable(false);
			textFieldNombre.setBounds(10, 36, 409, 20);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);
			textFieldNombre.setText(paciente.getNombre());
		}
		{
			JLabel lblIdentificacin = new JLabel("Identificaci\u00F3n:");
			lblIdentificacin.setBounds(435, 11, 91, 14);
			contentPanel.add(lblIdentificacin);
		}
		{
			textFieldIdentificacion = new JTextField();
			textFieldIdentificacion.setEditable(false);
			textFieldIdentificacion.setBounds(433, 36, 261, 20);
			contentPanel.add(textFieldIdentificacion);
			textFieldIdentificacion.setColumns(10);
			textFieldIdentificacion.setText(paciente.getCedula());
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 73, 422, 324);
			contentPanel.add(scrollPane);
			
			tabla = new JTable() {
				public boolean isCellEditable(int rowIndex, int vColIndex) {
					return false;
				}};
			String columns [] = {"ID de Consulta", "Paciente", "Doctor", "Fecha"};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(columns);
			tabla.setModel(model);
			tabla.getTableHeader().setResizingAllowed(false); // Para quitar el resizing
			tabla.getTableHeader().setReorderingAllowed(false);
			tabla.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					consulta = paciente.getHistoriaClinica().get(tabla.getSelectedRow());						
					llenarData();
				}
			});
					
			scrollPane.setViewportView(tabla);
			{
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Tratamiento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel.setBounds(443, 67, 251, 100);
				contentPanel.add(panel);
				panel.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane_1, BorderLayout.CENTER);
				
				txtDiagnostico = new JTextArea();
				txtDiagnostico.setEditable(false);
				txtDiagnostico.setWrapStyleWord(true);
				txtDiagnostico.setLineWrap(true);
				scrollPane_1.setViewportView(txtDiagnostico);
			}
			{
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Diagn\u00F3stico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel.setBounds(442, 178, 252, 100);
				contentPanel.add(panel);
				panel.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane_1, BorderLayout.CENTER);
				
				txtSintomas = new JTextArea();
				txtSintomas.setEditable(false);
				txtSintomas.setLineWrap(true);
				txtSintomas.setWrapStyleWord(true);
				scrollPane_1.setViewportView(txtSintomas);
				
			}
			{
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "Vacunas del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(442, 289, 252, 100);
				contentPanel.add(panel);
				panel.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane_1, BorderLayout.CENTER);
				
				lstVacunas = new JList<String>();
				scrollPane_1.setViewportView(lstVacunas);
			}
			loadTable(paciente.getHistoriaClinica());			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		iniciarLista();
	}
	
	// Sólo se usa al crear pacientes.
		private void iniciarLista() {
			// Borrar datos
			DefaultListModel<String> model = new DefaultListModel<String>();

			// Igualar a lista vacía para que se reinicie.
			lstVacunas.setModel(model);
			model.clear();

			for (Vacuna vacuna : paciente.getTodasLasVacunas()) {
				if (vacuna.isHecha())
					model.addElement(vacuna.getNombre());
			}

		}
	
	private void llenarData() {
		if (consulta != null) {
			txtDiagnostico.setText(consulta.getTratamiento());
			txtSintomas.setText(consulta.getDiagnostico());
		}
	}
	
	public static void loadTable(ArrayList<Consulta> consultas) {
		model.setRowCount(0);
			fila = new Object[model.getColumnCount()];
	
			for (Consulta consulta : consultas) {
					fila[0] = consulta.getIdConsulta();
					fila[1] = Clinica.getInstance().buscarPacienteById(consulta.getIdPaciente()).getNombre();
					fila[2] = Clinica.getInstance().buscarEmpleadoById((consulta.getIdDoctor())).getNombre();
					fila[3] = consulta.getFecha();	
					model.addRow(fila);
					
			tabla.setModel(model);
		}
	}
}
