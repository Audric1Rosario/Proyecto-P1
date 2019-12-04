package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import logical.Administrador;
import logical.Clinica;
import logical.Empleado;
import logical.Enfermedad;
import logical.Paciente;
import logical.TableRenderer;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class ControlEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();

	// Table
	private JTable tableEnferSist;
	private DefaultTableModel modelS;
	private JTable tableRevision;
	private DefaultTableModel modelR;
	private Object row[];


	// Text Field
	private JTextField txtBuscar;
	private JTextField txtNombre;
	private JTextField txtEstado;
	private JTextField txtEstadistica;

	// Lista
	private JList lstPacientes;
	private DefaultListModel listModel;

	// Variables lógicas
	private Empleado empleadoActual;
	private int orden;
	private boolean lastStatus;
	private TableRenderer sistRender;
	private TableRenderer revRender;

	// Botones
	private JButton btnAceptar;

	// Checkbox
	private JCheckBox chckbxEstEnRevisin;

	// Panel
	private JPanel panelPacientes;
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
		this.orden = 0;
		this.lastStatus = false;
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

		String headers[] = {"Nombre", "Registro", "Cantidad", "Estado"};
		modelS = new DefaultTableModel();
		modelS.setColumnIdentifiers(headers);
		tableEnferSist = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}};
		tableEnferSist.setModel(modelS);
		tableEnferSist.getTableHeader().setResizingAllowed(false);
		tableEnferSist.getTableHeader().setReorderingAllowed(false);
		sistRender = new TableRenderer(3);		// la columna 3 indica el color.
		tableEnferSist.setDefaultRenderer(Object.class, sistRender);
		scrollPane_1.setViewportView(tableEnferSist);

		tableEnferSist.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				if (tableEnferSist.getSelectedRow() != -1) {
					tableRevision.clearSelection();
					rellenarDatos(true);					
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


		modelR = new DefaultTableModel();
		modelR.setColumnIdentifiers(headers);
		tableRevision = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}};
		tableRevision.setModel(modelR);
		tableRevision.getTableHeader().setResizingAllowed(false);
		tableRevision.getTableHeader().setReorderingAllowed(false);
		tableRevision.setDefaultRenderer(Object.class, sistRender);
		scrollPane_2.setViewportView(tableRevision);
		tableRevision.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				if (tableRevision.getSelectedRow() != -1) {
					tableEnferSist.clearSelection();
					rellenarDatos(false);					
				}
			}
		});

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
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tomar el valor que esta en el txt buscar.
				rellenarTablas(2, txtBuscar.getText());
				clear();
			}
		});
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setIcon(new ImageIcon(ControlEnfermedades.class.getResource("/image/magnifying-glass.png")));
		btnBuscar.setBounds(293, 18, 33, 21);
		panel_2.add(btnBuscar);

		JButton btnReload = new JButton("");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Recargar en orden de aparición
				rellenarTablas(1, "");
				clear();
			}
		});
		btnReload.setToolTipText("Recargar");
		btnReload.setIcon(new ImageIcon(ControlEnfermedades.class.getResource("/image/reload.png")));
		btnReload.setBounds(336, 18, 33, 21);
		panel_2.add(btnReload);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ordenar alfabéticamente.
				rellenarTablas(3, "");
				clear();
			}
		});
		button_1.setToolTipText("Ordenar Alfab\u00E9ticamente");
		button_1.setIcon(new ImageIcon(ControlEnfermedades.class.getResource("/image/alphabet-letters-a-b-and-c.png")));
		button_1.setBounds(379, 18, 33, 21);
		panel_2.add(button_1);

		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ordenar decreciendo
				rellenarTablas(4, "");
				clear();
			}
		});
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

		chckbxEstEnRevisin = new JCheckBox("En revisi\u00F3n");
		chckbxEstEnRevisin.setEnabled(false);
		chckbxEstEnRevisin.setBounds(10, 178, 190, 23);
		chckbxEstEnRevisin.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// Controlar que si se está cargando datos
				if (chckbxEstEnRevisin.isSelected() == lastStatus)
					btnAceptar.setEnabled(false);
				else
					btnAceptar.setEnabled(true);
			}
		});


		panel_3.add(chckbxEstEnRevisin);

		txtEstadistica = new JTextField();
		txtEstadistica.setToolTipText("Relaci\u00F3n con el total.");
		txtEstadistica.setEditable(false);
		txtEstadistica.setColumns(10);
		txtEstadistica.setBounds(10, 143, 190, 20);
		panel_3.add(txtEstadistica);

		panelPacientes = new JPanel();
		panelPacientes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pacientes afectados <#>", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPacientes.setBounds(494, 244, 210, 158);
		contentPanel.add(panelPacientes);
		panelPacientes.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelPacientes.add(scrollPane, BorderLayout.CENTER);

		listModel = new DefaultListModel();
		lstPacientes = new JList();
		lstPacientes.setModel(listModel);
		lstPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(lstPacientes);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Enfermedad buscada = Clinica.getInstance().buscarEnfermedadByNombre(txtNombre.getText());
						if (buscada != null) {
							buscada.setRevision(chckbxEstEnRevisin.isSelected());
							clear();
						}
						rellenarTablas(orden, "");
					}
				});
				btnAceptar.setEnabled(false);
				btnAceptar.setActionCommand("OK");
				if (!(empleadoActual instanceof Administrador))
					btnAceptar.setVisible(false);
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		rellenarTablas(1, "");
	}

	/*
	 * Para llenar las tablas se va a usar la razon:
	 * 1: solo rellenar
	 * 2: busqueda
	 * 3: rellenar en orden alfabético.
	 * 4: rellenar según la cantidad de pacientes que tengan esta enfermedad.
	 * String headers[] = {"Nombre", "Registro", "Cantidad", "Estado"};
	 * */
	private void rellenarTablas(int razon, String data) {
		modelS.setRowCount(0);
		modelR.setRowCount(0);
		orden = razon;
		row = new Object[modelS.getColumnCount()];
		// Obtener las strings que se busca
		ArrayList<Enfermedad> datos = Clinica.getInstance().buscarEnfermedadByFilter(data);
		switch (razon) {
		case 1:
			// Ordenar por fecha
			Collections.sort(datos,  (o1, o2) -> o1.getFechaRegistro().compareTo(o2.getFechaRegistro()));
			break;
		case 3:
			// Ordenar por orden alfabético.
			Collections.sort(datos, (o1, o2) -> o1.getNombre().compareTo(o2.getNombre()));
			break;

		case 4:
			// Ordenar por cantidad de pacientes que sufren de esta (Decreciendo)
			Collections.sort(datos, (o1, o2) -> Long.valueOf(o1.getCantPacientes()).compareTo(o2.getCantPacientes()));
			Collections.reverse(datos);
			break;
		}

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		float stadistic;
		for (Enfermedad enfermedad : datos) {
			if (enfermedad.isListar()) {
				row[0] = enfermedad.getNombre();
				row[1] = format.format(enfermedad.getFechaRegistro());
				row[2] = Long.valueOf(enfermedad.getCantPacientes()).toString();
				stadistic = ((float)enfermedad.getCantPacientes() / (float)Clinica.getInstance().getPacientes().size()) * 100;
				// Revisar como esta la enfermedad
				if (stadistic <= 10) {
					row[3] = "Bajo control";
				}  else if (stadistic <= 20) {
					row[3] = "Nociva";
				} else {
					row[3] = "Epidemia";
				}

				// Separar las que están en revisión de las que no.
				if (enfermedad.isRevision())
					modelR.addRow(row);
				else 
					modelS.addRow(row);
			}
		}
		return;
	}

	private void rellenarDatos(boolean razon) {
		String data = "";
		if (razon) {
			// Evitar -1
			if (tableEnferSist.getSelectedRow() != -1) {
				data = tableEnferSist.getModel().getValueAt(tableEnferSist.getSelectedRow(), 0).toString();
			}
		} else {
			if (tableRevision.getSelectedRow() != -1) {
				data = tableRevision.getModel().getValueAt(tableRevision.getSelectedRow(), 0).toString();
			}
		}

		// Activar la revisión.
		Enfermedad buscada = Clinica.getInstance().buscarEnfermedadByNombre(data);

		if (buscada != null) {
			float stadistic = (((float)buscada.getCantPacientes() / (float)Clinica.getInstance().getPacientes().size()) * 100);
			chckbxEstEnRevisin.setSelected(buscada.isRevision());
			txtNombre.setText(buscada.getNombre());
			// Revisar como esta la enfermedad
			if (stadistic <= 10) {
				data = "Bajo control";
			}  else if (stadistic <= 20) {
				data = "Nociva";
			} else {
				data = "Epidemia";
			}
			txtEstado.setText(data);
			data = String.format("%.2f", stadistic) + "%";
			txtEstadistica.setText(data);

			// Solo se permite poner 10 enfermedades en revisión
			if (empleadoActual instanceof Administrador) {
				if (tableRevision.getModel().getRowCount() < 10 && !buscada.isRevision()) {
					chckbxEstEnRevisin.setEnabled(true);
					chckbxEstEnRevisin.setToolTipText("");
				}
				else {
					chckbxEstEnRevisin.setEnabled(buscada.isRevision());
					if (razon) {
						chckbxEstEnRevisin.setToolTipText("No puede agregar más de 10 enfermedades en revisión.");
					} else {
						chckbxEstEnRevisin.setToolTipText("");
					}
				}

			} else {
				chckbxEstEnRevisin.setEnabled(false);
			}

			chckbxEstEnRevisin.setSelected(buscada.isRevision());
			lastStatus = buscada.isRevision();
			btnAceptar.setEnabled(false);
			// Llenar jlist con los pacientes que sufren de esta enfermedad.
			ArrayList<String> pacientes = new ArrayList<String>();
			int aux = 0;
			boolean encontrado = false;
			for (Paciente paciente : Clinica.getInstance().getPacientes()) {
				aux = 0;
				encontrado = false;
				while (aux < paciente.getEnfermedades().size() && !encontrado) {
					if (paciente.getEnfermedades().get(aux).equalsIgnoreCase(buscada.getNombre())) {
						encontrado = true;
						pacientes.add(paciente.getNombre());
					}
					aux++;
				}
			}

			// Luego cargar en el JList
			listModel.clear();
			for (String afectado : pacientes) {
				listModel.addElement(afectado);
			}
			// Ponerle otro nombre al panel;
			panelPacientes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pacientes afectados <"+ pacientes.size() +">", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		} else {
			JOptionPane.showMessageDialog(null, "Error al cargar los datos", "Control de enfermedades", JOptionPane.ERROR_MESSAGE);
			return;
		}
		return;
	}

	private void clear() {
		txtNombre.setText("");
		txtEstado.setText("");
		txtEstadistica.setText("");
		chckbxEstEnRevisin.setSelected(false);
		chckbxEstEnRevisin.setEnabled(false);
		tableEnferSist.clearSelection();
		tableRevision.clearSelection();
		listModel.clear(); // Vaciar lista.
	}
}
