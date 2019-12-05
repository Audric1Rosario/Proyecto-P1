package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logical.Clinica;
import logical.Configuracion;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Opciones extends JDialog {

	private final JPanel contentPanel = new JPanel();

	// Elementos visuales

	// Spinner
	private JSpinner spnCitas;
	private JSpinner spnDoctores;
	private JSpinner spnControl;
	private JSpinner spnRegular;

	// Botones
	private JButton btnGuardar;
	private JButton btnCerrar;

	// Variables lógicas.
	private boolean changes[];

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			Opciones dialog = new Opciones();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 * 
	 * Nota: esta clase solo lo puede ver el administrador de clase nivel 1.
	 */
	public Opciones() {
		setResizable(false);
		setTitle("Opciones del sistema");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Opciones.class.getResource("/image/caduceus.png")));
		setBounds(100, 100, 480, 312);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		changes = new boolean[6];
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 454, 131);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JPanel panelDoc = new JPanel();
			panelDoc.setBorder(new TitledBorder(null, "Doctor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDoc.setBounds(10, 21, 210, 99);
			panel.add(panelDoc);
			panelDoc.setLayout(null);

			spnCitas = new JSpinner();
			spnCitas.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent  evt) {
					if (btnGuardar != null) {
						if (Integer.valueOf(spnCitas.getValue().toString()) != 
								Clinica.getInstance().getOpcionesSistema().getMaxCitasSist()) {
							changes[0] = true;

						} else {
							changes[0] = false;
						}
						// Esto por si hay otro cambio y no desactivar por cada vuelta al punto de partida.

						btnGuardar.setEnabled(verificarCambio());
					}
				}
			});
			spnCitas.setModel(new SpinnerNumberModel(1, 1, 100, 1));
			spnCitas.setBounds(10, 47, 190, 20);
			panelDoc.add(spnCitas);

			JLabel lblMximaCantidadDe = new JLabel("M\u00E1xima cantidad de citas");
			lblMximaCantidadDe.setHorizontalAlignment(SwingConstants.CENTER);
			lblMximaCantidadDe.setBounds(10, 26, 190, 14);
			panelDoc.add(lblMximaCantidadDe);
		}
		{
			JPanel panelSecre = new JPanel();
			panelSecre.setLayout(null);
			panelSecre.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Secretaria", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelSecre.setBounds(230, 21, 210, 99);
			panel.add(panelSecre);

			spnDoctores = new JSpinner();
			spnDoctores.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent  evt) {
					if (btnGuardar != null) {
						if (Integer.valueOf(spnDoctores.getValue().toString()) != 
								Clinica.getInstance().getOpcionesSistema().getMaxDoctoresSist()) {
							changes[1] = true;
						} else {
							changes[1] = false;
						}

						btnGuardar.setEnabled(verificarCambio()); 
					}
				}
			});
			spnDoctores.setModel(new SpinnerNumberModel(1, 1, 25, 1));
			spnDoctores.setBounds(10, 47, 190, 20);
			panelSecre.add(spnDoctores);

			JLabel lblMximaCantidadDe_1 = new JLabel("M\u00E1xima cantidad de doctores");
			lblMximaCantidadDe_1.setBounds(10, 26, 190, 14);
			panelSecre.add(lblMximaCantidadDe_1);
		}

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades (par\u00E1metros de evaluaci\u00F3n).", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 153, 454, 84);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		spnControl = new JSpinner();
		spnControl.setToolTipText("%");
		spnControl.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent  evt) {
				if (btnGuardar != null) {
					if (Integer.valueOf(spnControl.getValue().toString()) != 
							Clinica.getInstance().getOpcionesSistema().getControlada()) {
						changes[2] = true;
					} else {
						changes[2] = false;
					}

					btnGuardar.setEnabled(verificarCambio());
				}
			}
		});
		spnControl.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnControl.setBounds(38, 46, 158, 20);
		panel_1.add(spnControl);

		spnRegular = new JSpinner();
		spnRegular.setToolTipText("%");
		spnRegular.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent  evt) {
				if (btnGuardar != null) {
					if (Integer.valueOf(spnRegular.getValue().toString()) != 
							Clinica.getInstance().getOpcionesSistema().getRegular()) {
						changes[3] = true;
					} else {
						changes[3] = false;
					}

					btnGuardar.setEnabled(verificarCambio());
				}
			}
		});
		spnRegular.setModel(new SpinnerNumberModel(new Integer(2), new Integer(2), null, new Integer(1)));
		spnRegular.setBounds(233, 46, 158, 20);
		panel_1.add(spnRegular);

		JLabel lblControlada = new JLabel("Bajo Control");
		lblControlada.setHorizontalAlignment(SwingConstants.CENTER);
		lblControlada.setBounds(38, 22, 158, 14);
		panel_1.add(lblControlada);

		JLabel lblRegular = new JLabel("Nociva");
		lblRegular.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegular.setBounds(233, 22, 158, 14);
		panel_1.add(lblRegular);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!(Integer.valueOf(spnRegular.getValue().toString()) > Integer.valueOf(spnControl.getValue().toString()))) {
							String message = "Parámetros de control de enfermedades desconfigurados.\n\n";
							message += "Asegúrese de que estos sean según el orden de que:\n";
							message += "Enfermedad: Bajo Control < Nociva.";
							JOptionPane.showMessageDialog(null, message, "Opciones del sistema", JOptionPane.ERROR_MESSAGE);
							return;	// No continuar.
						} else {
							Configuracion sistema = new Configuracion();
							sistema.setMaxCitasSist(Integer.valueOf(spnCitas.getValue().toString()));
							sistema.setMaxDoctoresSist(Integer.valueOf(spnDoctores.getValue().toString()));
							sistema.setControlada(Integer.valueOf(spnControl.getValue().toString()));
							sistema.setRegular(Integer.valueOf(spnRegular.getValue().toString()));
							Clinica.getInstance().setOpcionesSistema(sistema);
							btnGuardar.setEnabled(false);
						}


					}
				});
				btnGuardar.setEnabled(false);
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		rellenarDatos();
	}

	private void rellenarDatos() {
		spnCitas.setValue(Integer.valueOf(Clinica.getInstance().getOpcionesSistema().getMaxCitasSist()));
		spnDoctores.setValue(Integer.valueOf(Clinica.getInstance().getOpcionesSistema().getMaxDoctoresSist()));
		spnControl.setValue(Integer.valueOf(Clinica.getInstance().getOpcionesSistema().getControlada()));
		spnRegular.setValue(Integer.valueOf(Clinica.getInstance().getOpcionesSistema().getRegular()));
	}

	private boolean verificarCambio() {
		boolean changed = false;
		for (int i = 0; i < changes.length; i++) {
			if (changes[i])
				changed = changes[i];
		}

		return changed;
	}
}
