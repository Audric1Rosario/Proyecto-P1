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
import logical.Doctor;
import logical.Empleado;
import logical.Secretaria;
import logical.Vacuna;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class ListaVacunas extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private static JTable vacunasTable;
	// Para la tabla.
	private static DefaultTableModel model;
	private static Object row[];
	private static JTable table;

	// Text Field
	private static JTextField txtNombre;
	private static JTextField txtTipo;
	private static JTextField txtEnfermedad;

	// Text Area
	private static JTextArea txtEfectos;
	
	// Combo box
	private static JComboBox cbxTipo;

	// Botones
	private static JButton btnModificar;
	private static JButton btnEliminar;
	
	// Variables lógicas
	private Empleado usuarioActual;
	private static Vacuna vacunaModificar;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			ListaVacunas dialog = new ListaVacunas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ListaVacunas(Empleado usuarioActual) {
		this.usuarioActual = usuarioActual;
		ListaVacunas.vacunaModificar = null;
		setResizable(false);
		setTitle("Lista de vacunas.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaVacunas.class.getResource("/image/caduceus.png")));
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Datos.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 79, 432, 323);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{					
					model = new DefaultTableModel();
					String[] headers = { "Nombre", "Tipo", "Enfermedad"};
					model.setColumnIdentifiers(headers);   
					vacunasTable = new JTable();
					vacunasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					vacunasTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
						public void valueChanged(ListSelectionEvent event) {
							if (vacunasTable.getSelectedRow() != -1) {
								vacunaModificar = Clinica.getInstance().buscarVacunaByNombre(vacunasTable.getValueAt(vacunasTable.getSelectedRow(), 0).toString());
								rellenarDatos();
								
								if (usuarioActual instanceof Administrador) {
									if (((Administrador)usuarioActual).getAutoridad() <= 3) {
										btnModificar.setEnabled(true);
										btnEliminar.setEnabled(true);
									}
								}
							}
						}
					});
					vacunasTable.setModel(model);
					vacunasTable.getTableHeader().setResizingAllowed(false); 
					vacunasTable.getTableHeader().setReorderingAllowed(false);
					scrollPane.setViewportView(vacunasTable);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n detallada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(452, 11, 252, 391);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				txtNombre = new JTextField();
				txtNombre.setEditable(false);
				txtNombre.setBounds(10, 47, 232, 20);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JLabel lblNombre = new JLabel("Nombre");
				lblNombre.setBounds(10, 22, 232, 14);
				panel.add(lblNombre);
			}
			{
				txtTipo = new JTextField();
				txtTipo.setEditable(false);
				txtTipo.setBounds(10, 103, 232, 20);
				panel.add(txtTipo);
				txtTipo.setColumns(10);
			}
			{
				JLabel lblTipo = new JLabel("Tipo");
				lblTipo.setBounds(10, 78, 232, 14);
				panel.add(lblTipo);
			}
			{
				JLabel lblEnfermedad = new JLabel("Enfermedad");
				lblEnfermedad.setBounds(10, 134, 232, 14);
				panel.add(lblEnfermedad);
			}
			{
				txtEnfermedad = new JTextField();
				txtEnfermedad.setEditable(false);
				txtEnfermedad.setColumns(10);
				txtEnfermedad.setBounds(10, 159, 232, 20);
				panel.add(txtEnfermedad);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Efectos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(10, 190, 232, 190);
				panel.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					panel_1.add(scrollPane, BorderLayout.CENTER);
					{
						txtEfectos = new JTextArea();
						txtEfectos.setEditable(false);
						txtEfectos.setLineWrap(true);
						txtEfectos.setWrapStyleWord(true);
						scrollPane.setViewportView(txtEfectos);
					}
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "B\u00FAsqueda por tipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 432, 57);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblTipo_1 = new JLabel("Tipo:");
				lblTipo_1.setBounds(46, 23, 46, 14);
				panel.add(lblTipo_1);
			}
			{
				cbxTipo = new JComboBox();
				cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Todos los tipos>", "Virus activo", "Muerta", "Toxoide", "Biosint\u00E9tica"}));
				cbxTipo.setBounds(102, 20, 223, 20);
				panel.add(cbxTipo);
			}
			{
				JButton btnBuscar = new JButton("");
				btnBuscar.setToolTipText("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						vacunasTable.clearSelection();
						clear();
						rellenarTabla(cbxTipo.getSelectedIndex());
					}
				});
				btnBuscar.setIcon(new ImageIcon(ListaVacunas.class.getResource("/image/magnifying-glass.png")));
				btnBuscar.setBounds(342, 19, 62, 23);
				panel.add(btnBuscar);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (vacunaModificar != null && usuarioActual instanceof Administrador) {
							CrearVacuna ventana = new CrearVacuna(vacunaModificar);
							ventana.setModal(true);
							ventana.setVisible(true);
						}
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
				
			}
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnEliminar = new JButton("Eliminar");
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (vacunaModificar != null && usuarioActual instanceof Administrador) {
								Clinica.getInstance().getVacunas().remove(vacunaModificar);
								vacunaModificar = null;
								clear();
								rellenarTabla(cbxTipo.getSelectedIndex());
							} else {
								JOptionPane.showMessageDialog(null, "Error al eliminar.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
							}
						}
					});
					btnEliminar.setEnabled(false);
					buttonPane.add(btnEliminar);
				}
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		// Luego de crear todo, ocultar para algunos usuarios.
		if (usuarioActual instanceof Doctor || usuarioActual instanceof Secretaria) {
			btnModificar.setEnabled(false);
			btnModificar.setVisible(false);
			btnEliminar.setEnabled(false);
			btnEliminar.setVisible(false);
		}
		
		if (usuarioActual instanceof Administrador) {
			if (((Administrador)usuarioActual).getAutoridad() > 3) {
				btnModificar.setEnabled(false);
				btnModificar.setVisible(false);
			}
		}
		rellenarTabla(0);
	}

	private void rellenarDatos() {
		if (vacunaModificar != null) {
			txtNombre.setText(vacunaModificar.getNombre());
			txtTipo.setText(vacunaModificar.getTipo());
			txtEnfermedad.setText(vacunaModificar.getEnfermedadNombre());
			txtEfectos.setText(vacunaModificar.getEfectos());
		} else {
			JOptionPane.showMessageDialog(null, "Error al cargar datos.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	// String[] headers = { "Nombre", "Tipo", "Enfermedad"};
	// cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Todos los tipos>", "Virus activo", "Muerta", "Toxoide", "Biosint\u00E9tica"}));
	public static void rellenarTabla(int tipoRelleno) {
		int tipo = 0;	// al buscar los tipos si no encuentra, entonces es cualquier tipo.
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (int i = 0; i < Clinica.getInstance().getVacunas().size(); i++) {
			row[0] = Clinica.getInstance().getVacunas().get(i).getNombre();
			row[1] = Clinica.getInstance().getVacunas().get(i).getTipo();
			row[2] = Clinica.getInstance().getVacunas().get(i).getEnfermedadNombre();
			
			switch (row[1].toString()) {
			case "Virus activo":
				tipo = 1;
				break;
			case "Muerta":
				tipo = 2;
				break;
			case "Toxoide":
				tipo = 3;
				break;
			case "Biosint\u00E9tica":
				tipo = 4;
				break;
			default:	// Cualquier tipo
				tipo = 0;
				break;
			}
			
			if (tipoRelleno == tipo || tipoRelleno <= 0)
				model.addRow(row);
		}

	}

	private void clear() {
		txtNombre.setText("");
		txtTipo.setText("");
		txtEnfermedad.setText("");
		txtEfectos.setText("");
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		
	}

	public static void sclear(int data) {
		// TODO Auto-generated method stub
		txtNombre.setText(vacunaModificar.getNombre());
		txtTipo.setText(vacunaModificar.getTipo());
		txtEnfermedad.setText(vacunaModificar.getEnfermedadNombre());
		txtEfectos.setText(vacunaModificar.getEfectos());
		cbxTipo.setSelectedIndex(data);
	}
}
