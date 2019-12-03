package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logical.Paciente;
import logical.Clinica;
import logical.Consulta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textFieldIdentificacion;
	private JTextField textFieldDireccion;
	private JTextField textFieldSexo;
	private JTextField textFieldTelefono;
	private JTextField textFieldSangre;
	private JTextField textFieldSeguro;
	private static JTable tabla;
	private static DefaultTableModel model;
	private static Object[] fila;
	private Consulta consulta = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarConsulta dialog = new BuscarConsulta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarConsulta(Paciente paciente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarConsulta.class.getResource("/image/caduceus.png")));
		setTitle("Buscar Consulta");
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 11, 175, 157);
			contentPanel.add(panel);
			panel.setLayout(null);

				JLabel labelhombre = new JLabel("");
				labelhombre.setEnabled(false);
				labelhombre.setIcon(new ImageIcon(BuscarConsulta.class.getResource("/image/employee .png")));
				labelhombre.setBounds(39, 11, 80, 119);
				panel.add(labelhombre);
				labelhombre.setVisible(false);

				JLabel labelMujer = new JLabel("");
				labelMujer.setEnabled(false);
				labelMujer.setIcon(new ImageIcon(BuscarConsulta.class.getResource("/image/girl2.png")));
				labelMujer.setBounds(39, 11, 80, 119);
				panel.add(labelMujer);
				labelMujer.setVisible(false);
			
			if(paciente.getSexo().equalsIgnoreCase("F")) {
				labelMujer.setVisible(true);
			}else if(paciente.getSexo().equalsIgnoreCase("M")) {
				labelhombre.setVisible(true);
			}
		}
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(213, 11, 48, 14);
			contentPanel.add(lblNombre);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setEditable(false);
			textFieldNombre.setBounds(213, 36, 267, 20);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);
			textFieldNombre.setText(paciente.getNombre());
		}
		{
			JLabel lblIdentificacin = new JLabel("Identificaci\u00F3n:");
			lblIdentificacin.setBounds(500, 11, 91, 14);
			contentPanel.add(lblIdentificacin);
		}
		{
			textFieldIdentificacion = new JTextField();
			textFieldIdentificacion.setEditable(false);
			textFieldIdentificacion.setBounds(500, 36, 194, 20);
			contentPanel.add(textFieldIdentificacion);
			textFieldIdentificacion.setColumns(10);
			textFieldIdentificacion.setText(paciente.getCedula());
		}
		{
			JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
			lblDireccin.setBounds(213, 67, 70, 14);
			contentPanel.add(lblDireccin);
		}
		{
			textFieldDireccion = new JTextField();
			textFieldDireccion.setEditable(false);
			textFieldDireccion.setBounds(213, 92, 481, 20);
			contentPanel.add(textFieldDireccion);
			textFieldDireccion.setColumns(10);
			textFieldDireccion.setText(paciente.getDireccion() + " " + paciente.getSector());
		}
		{
			JLabel lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(213, 123, 48, 14);
			contentPanel.add(lblSexo);
		}
		{
			textFieldSexo = new JTextField();
			textFieldSexo.setEditable(false);
			textFieldSexo.setBounds(213, 148, 96, 20);
			contentPanel.add(textFieldSexo);
			textFieldSexo.setColumns(10);
			textFieldSexo.setText(paciente.getSexo());
		}
		{
			JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
			lblTelfono.setBounds(340, 123, 59, 14);
			contentPanel.add(lblTelfono);
		}
		{
			textFieldTelefono = new JTextField();
			textFieldTelefono.setEditable(false);
			textFieldTelefono.setBounds(340, 148, 96, 20);
			contentPanel.add(textFieldTelefono);
			textFieldTelefono.setColumns(10);
			textFieldTelefono.setText(paciente.getTelefono());
		}
		{
			textFieldSangre = new JTextField();
			textFieldSangre.setEditable(false);
			textFieldSangre.setBounds(477, 148, 96, 20);
			contentPanel.add(textFieldSangre);
			textFieldSangre.setColumns(10);
			textFieldSangre.setText(paciente.getTipoSangre());
		}
		{
			JLabel lblTipoDeSangre = new JLabel("Tipo de Sangre:");
			lblTipoDeSangre.setBounds(477, 123, 96, 14);
			contentPanel.add(lblTipoDeSangre);
		}
		{
			textFieldSeguro = new JTextField();
			textFieldSeguro.setEditable(false);
			textFieldSeguro.setBounds(598, 148, 96, 20);
			contentPanel.add(textFieldSeguro);
			textFieldSeguro.setColumns(10);
			textFieldSeguro.setText(paciente.getSeguro());
		}
		{
			JLabel lblSeguro = new JLabel("Seguro:");
			lblSeguro.setBounds(622, 123, 48, 14);
			contentPanel.add(lblSeguro);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 179, 684, 218);
			contentPanel.add(scrollPane);
			
			tabla = new JTable();
			String columns [] = {"ID de Consulta", "Paciente", "Doctor", "Fecha"};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(columns);
			tabla.setModel(model);
			scrollPane.setViewportView(tabla);
			loadTable(paciente.getHistoriaClinica());
			tabla.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					consulta = paciente.getHistoriaClinica().get(tabla.getSelectedRow());
				}
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Buscar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(consulta == null) {
							JOptionPane.showMessageDialog(null, "Seleccione una consulta", "Notificación", JOptionPane.INFORMATION_MESSAGE);
						} else if(consulta != null) {
							ConsultaVisual cons = new ConsultaVisual(consulta);
							cons.setModal(true);
							cons.setVisible(true);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
