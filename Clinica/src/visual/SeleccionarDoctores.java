package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.border.TitledBorder;

import logical.Clinica;
import logical.Doctor;
import logical.Empleado;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class SeleccionarDoctores extends JDialog {

	private static final boolean Empleado = false;

	private final JPanel contentPanel = new JPanel();

	// Doctores para la secretaria.
	private ArrayList<String> refDoctores;	// Para que se guarde en secretaria luego de todo el proceso.
	private ArrayList<String> doctoresArr;  
	private ArrayList<String> doctoresSelec;
	private boolean modificar;
	private int cantMaxDoc;
	// Lista.
	private JList lstDoctores;
	private JList lstSelec;

	// Botones
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			SeleccionarDoctores dialog = new SeleccionarDoctores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public SeleccionarDoctores(ArrayList<String> refDoctores, boolean mod) {
		this.refDoctores = refDoctores;			// Referencia a la lista pasada.
		this.modificar = mod;
		this.cantMaxDoc = 5;
		this.doctoresArr = new ArrayList<String>();
		this.doctoresSelec = new ArrayList<String>();
		setTitle("Seleccionar doctores");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SeleccionarDoctores.class.getResource("/image/caduceus.png")));
		setBounds(100, 100, 480, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 444, 138);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					lstDoctores = new JList();
					lstDoctores.setToolTipText("Seleccione el doctor que le quiere asignar.");
					lstDoctores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					lstDoctores.setModel(new DefaultListModel<String>());
					scrollPane.setViewportView(lstDoctores);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 160, 444, 138);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(10, 11, 326, 116);
				panel.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));

				JScrollPane scrollPane = new JScrollPane();
				panel_1.add(scrollPane, BorderLayout.CENTER);

				lstSelec = new JList();
				lstSelec.setToolTipText("Seleccione para quitar un doctor.");
				lstSelec.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				lstSelec.setModel(new DefaultListModel<String>());
				scrollPane.setViewportView(lstSelec);
			}
			{
				btnAgregar = new JButton("Agregar");
				btnAgregar.setEnabled(false);
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selection = lstDoctores.getSelectedIndex();
						if (selection == -1) {
							JOptionPane.showMessageDialog(null, "No hay doctores seleccionados.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
						} else {
							actualizarLista(true, selection);
						}
					}
				});
				btnAgregar.setBounds(346, 22, 89, 40);
				panel.add(btnAgregar);
			}
			{
				btnEliminar = new JButton("Descartar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selection = lstSelec.getSelectedIndex();
						if (selection == -1) {
							JOptionPane.showMessageDialog(null, "No hay doctores seleccionados.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
						} else {
							actualizarLista(false, selection);
						}
					}
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setBounds(346, 73, 89, 40);
				panel.add(btnEliminar);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (doctoresSelec.size() == 0) {
							switch (JOptionPane.showConfirmDialog(null, "Esta secretaria no trabaja para ningún doctor, ¿Desea eliminarla?", 
									"Advertencia.", JOptionPane.WARNING_MESSAGE)) {
							case JOptionPane.YES_OPTION:
								
								break;
							case JOptionPane.NO_OPTION:
								JOptionPane.showMessageDialog(null, "No es posible tener una secretaria en el sistema si no trabaja para ningún doctor." + 
										"Por favor, agregue los doctores para los cuales esta trabaja o elimínela.", "Advertencia", JOptionPane.WARNING_MESSAGE);
								break;
							}
						} else {
							// Primero adaptar la referencia de los doctores a los id de los mismos.
							refDoctores.clear();
							for (String doctor : doctoresSelec) {
								refDoctores.add(Clinica.getInstance().buscarEmpleadoByNombre(doctor).getNombre());
							}
							// Ajustar todos los doctores que no tienen secretaria.
							for (String doctor : doctoresArr) {
								((Doctor)Clinica.getInstance().buscarEmpleadoByNombre(doctor)).setHasSecretaria(false);;
							}
							Usuarios.getBtnDoctores().setEnabled(false);
							Usuarios.getTxtCantDoctores().setText(String.valueOf(doctoresSelec.size()));
							
						}
						
					}
				});

				if (!modificar)
					btnAceptar.setEnabled(false);

				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
			iniciarLista(); // <-Agregar los doctores->
		}
	}

	private void iniciarLista() {
		// Borrar datos
		int aux = 0;
		DefaultListModel<String> model = (DefaultListModel<String>) lstDoctores.getModel();
		model.clear();
		model = (DefaultListModel<String>) lstSelec.getModel();
		model.clear();

		// Agregar valores
		// 1. Obtener los nombres de cada doctor
		for (Empleado empleado : Clinica.getInstance().getEmpleados()) {
			if (empleado instanceof Doctor)
				if (!((Doctor)empleado).isHasSecretaria())	// Sólo agregar a la lista si no tiene secretaria.
					doctoresArr.add(empleado.getNombre());
		}
		// Organizar los nombres en orden alfabético
		Collections.sort(doctoresArr);

		// Agregar los doctores para los que podría trabajar esa secretaria.
		if (doctoresArr.size() == 0) {
			JOptionPane.showMessageDialog(null, "No hay doctores registrados.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		} else {
			model = (DefaultListModel<String>) lstDoctores.getModel();
			for (String nombreDoctor : doctoresArr) {
				model.addElement(nombreDoctor);
			}
		}

		// Agregar los doctores que la secretaria ya tenía (si se está modificando)
		model = (DefaultListModel<String>) lstSelec.getModel();
		aux = 0;
		for (String IdDoctor : refDoctores) {
			doctoresSelec.add(Clinica.getInstance().buscarEmpleadoById(IdDoctor).getNombre());
			model.addElement(doctoresSelec.get(aux)); 
			aux++;
		}

		// Habilitar el agregar y eliminar
		// Deliminar la cantidad de doctores para los que la secretaria puede trabajar.
		btnAgregar.setEnabled(doctoresArr.size() > 0 && doctoresSelec.size() < cantMaxDoc);
		if (doctoresSelec.size() >= cantMaxDoc)
			btnAgregar.setToolTipText("Una secretaria no puede trabajar para más de " + cantMaxDoc + " doctores.");
		else 
			btnAgregar.setToolTipText("");
		btnEliminar.setEnabled(doctoresSelec.size() > 0);
	}

	private void reiniciarLista() {
		// Borrar datos
		DefaultListModel<String> model = (DefaultListModel<String>) lstDoctores.getModel();
		model.clear();
		model = (DefaultListModel<String>) lstSelec.getModel();
		model.clear();

		model = (DefaultListModel<String>) lstDoctores.getModel();
		for (String nombreDoctor : doctoresArr)
			model.addElement(nombreDoctor);


		model = (DefaultListModel<String>) lstSelec.getModel();
		for (String nombreDoctor : doctoresSelec)
			model.addElement(nombreDoctor); 
	}

	private void actualizarLista(boolean razon, int index) {
		if (razon) { // tomar un doctor para el que esta trabaja.
			doctoresSelec.add(doctoresArr.get(index));
			doctoresArr.remove(index);
			Collections.sort(doctoresSelec);

		} else {     // Poner un doctor fuera de la lista.
			doctoresArr.add(doctoresSelec.get(index));
			doctoresSelec.remove(index);
			Collections.sort(doctoresArr);
		}

		// Habilitar botones según quede preparado.
		// Deliminar la cantidad de doctores para los que la secretaria puede trabajar.
		btnAgregar.setEnabled(doctoresArr.size() > 0 && doctoresSelec.size() < cantMaxDoc);
		if (doctoresSelec.size() >= cantMaxDoc)
			btnAgregar.setToolTipText("Una secretaria no puede trabajar para más de " + cantMaxDoc +" doctores.");
		else 
			btnAgregar.setToolTipText("");
		btnEliminar.setEnabled(doctoresSelec.size() > 0);

		if (!modificar) {
			btnAceptar.setEnabled(doctoresSelec.size() > 0);
		}
		reiniciarLista();
	}
}
