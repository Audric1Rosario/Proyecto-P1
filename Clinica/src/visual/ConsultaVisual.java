package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logical.Empleado;
import logical.Enfermedad;
import logical.Paciente;
import logical.Clinica;
import logical.Consulta;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ConsultaVisual extends JDialog {

	// Doctor actual
	private Empleado doctor;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldIdentificacion;
	private JTextField textFieldDoctor;
	private JTextField textFieldPaciente;
	private JTextField textFieldFecha;
	private static JTable tabla;
	private static DefaultTableModel model;
	private static Object[] fila;
	private String pattern = "MM/dd/yyyy HH:mm:ss";
	private DateFormat df = new SimpleDateFormat(pattern);


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConsultaVisual dialog = new ConsultaVisual(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConsultaVisual(Consulta consulta) {
		setTitle("Consulta");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Consulta.class.getResource("/image/caduceus.png")));
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
			textFieldIdentificacion.setText(consulta.getIdConsulta());
		}
		{
			textFieldDoctor = new JTextField();
			textFieldDoctor.setEditable(false);
			textFieldDoctor.setBounds(356, 36, 315, 20);
			contentPanel.add(textFieldDoctor);
			textFieldDoctor.setColumns(10);
			textFieldDoctor.setText(Clinica.getInstance().buscarEmpleadoById(consulta.getIdDoctor()).getNombre());
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
			textFieldPaciente.setText(Clinica.getInstance().buscarPacienteById(consulta.getIdPaciente()).getNombre());
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
			textFieldFecha.setText(df.format(consulta.getFecha()));
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
			tabla = new JTable();
			
			String columns [] = {"Nombre", "Identificación", "Seguro", "Sexo"};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(columns);
			tabla.setModel(model);
			scrollPane.setViewportView(tabla);
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
				JButton cancelButton = new JButton("Aceptar");
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
	public static void loadTable(Consulta consulta) {
		model.setRowCount(0);
			fila = new Object[model.getColumnCount()];
	
			for (Enfermedad enfermedad  : consulta.getEnfermedades()) {
					fila[0] = enfermedad.getNombre();
					fila[1] = enfermedad.getSintomas();
				
					model.addRow(fila);
			}
	}
}
