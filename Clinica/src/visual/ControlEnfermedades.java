package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import logical.Administrador;
import logical.Clinica;
import logical.Empleado;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class ControlEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	// Table
	private JTable tableEnferSist;
	private JTable tableRevision;
	private Object row[];
	private DefaultTableModel model;
	
	// Text Field
	private JTextField txtBuscar;
	private JTextField txtNombre;
	private JTextField txtEstado;
	private JTextField txtEstadistica;
	
	// Variables l�gicas
	private Empleado empleadoActual;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			ControlEnfermedades dialog = new ControlEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ControlEnfermedades(Empleado empleadoActual) {
		this.empleadoActual = empleadoActual;
		setIconImage(Toolkit.getDefaultToolkit().getImage(ControlEnfermedades.class.getResource("/image/caduceus.png")));
		setTitle("Control de enfermedades");
		setResizable(false);
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Enfermedades del sistema", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 72, 474, 158);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane_1);
		
		tableEnferSist = new JTable();
		scrollPane_1.setViewportView(tableEnferSist);
		tableEnferSist.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				if (tableEnferSist.getSelectedRow() != -1) {
					/*enfermedadModificar = Clinica.getInstance().buscarEnfermedadByNombre(tableEnfermedades.getValueAt(tableEnfermedades.getSelectedRow(), 0).toString());
					rellenarDatos();

					if (usuarioActual instanceof Administrador) {
						if (((Administrador)usuarioActual).getAutoridad() <= 3) {
							btnModificar.setEnabled(true);
							btnEliminar.setEnabled(true);
						}
					}*/
				}
			}
		});		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Enfermedades en revisi\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 244, 474, 158);
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_1.add(scrollPane_2, BorderLayout.CENTER);
		
		tableRevision = new JTable();
		scrollPane_2.setViewportView(tableRevision);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "B\u00FAsqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 11, 474, 50);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(81, 19, 205, 20);
		panel_2.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 22, 61, 14);
		panel_2.add(lblNombre);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setIcon(new ImageIcon(ControlEnfermedades.class.getResource("/image/magnifying-glass.png")));
		btnBuscar.setBounds(293, 18, 33, 21);
		panel_2.add(btnBuscar);
		
		JButton btnReload = new JButton("");
		btnReload.setToolTipText("Recargar");
		btnReload.setIcon(new ImageIcon(ControlEnfermedades.class.getResource("/image/reload.png")));
		btnReload.setBounds(336, 18, 33, 21);
		panel_2.add(btnReload);
		
		JButton button_1 = new JButton("");
		button_1.setToolTipText("Ordenar Alfab\u00E9ticamente");
		button_1.setIcon(new ImageIcon(ControlEnfermedades.class.getResource("/image/alphabet-letters-a-b-and-c.png")));
		button_1.setBounds(379, 18, 33, 21);
		panel_2.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setToolTipText("Ordenar de acuerdo a la cantidad de pacientes");
		button_2.setIcon(new ImageIcon(ControlEnfermedades.class.getResource("/image/increasing-stocks-graphic.png")));
		button_2.setBounds(422, 18, 33, 21);
		panel_2.add(button_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Configuraci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(494, 11, 210, 219);
		contentPanel.add(panel_3);
		panel_3.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(10, 44, 190, 20);
		panel_3.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNombre_1 = new JLabel("Nombre");
		lblNombre_1.setBounds(10, 19, 190, 14);
		panel_3.add(lblNombre_1);
		
		JLabel lblNewLabel = new JLabel("Estado");
		lblNewLabel.setBounds(10, 75, 190, 14);
		panel_3.add(lblNewLabel);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		txtEstado.setBounds(10, 108, 190, 20);
		panel_3.add(txtEstado);
		
		JCheckBox chckbxEstEnRevisin = new JCheckBox("En revisi\u00F3n");
		chckbxEstEnRevisin.setEnabled(false);
		chckbxEstEnRevisin.setBounds(10, 178, 190, 23);
		panel_3.add(chckbxEstEnRevisin);
		
		txtEstadistica = new JTextField();
		txtEstadistica.setEditable(false);
		txtEstadistica.setColumns(10);
		txtEstadistica.setBounds(10, 143, 190, 20);
		panel_3.add(txtEstadistica);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pacientes afectados <#>", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(494, 244, 210, 158);
		contentPanel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_4.add(scrollPane, BorderLayout.CENTER);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.setEnabled(false);
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
	}
}