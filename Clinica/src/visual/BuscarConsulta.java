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
			scrollPane.setBounds(10, 73, 684, 324);
			contentPanel.add(scrollPane);
			
			tabla = new JTable();
			String columns [] = {"ID de Consulta", "Paciente", "Doctor", "Fecha", "Diagnostico", "Tratamiento"};
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
					fila[4] = consulta.getDiagnostico();
					fila[5] = consulta.getTratamiento();
	
					model.addRow(fila);
	
			tabla.setModel(model);
		}
	}
}
