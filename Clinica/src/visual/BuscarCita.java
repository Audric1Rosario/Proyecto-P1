package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logical.Clinica;

import javax.swing.border.TitledBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class BuscarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String screenPath;
	private JTable tableCitas;
	private JTextField txtDoctor;
	private JTextField txtId;
	private JButton btnMaxCitas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarCita dialog = new BuscarCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarCita() {
		setTitle("Citas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarCita.class.getResource("/image/caduceus.png")));
		setResizable(false);
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelPanelBusqueda = new JPanel();
		panelPanelBusqueda.setBorder(new TitledBorder(null, "B\u00FAsqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPanelBusqueda.setBounds(10, 11, 246, 72);
		contentPanel.add(panelPanelBusqueda);
		panelPanelBusqueda.setLayout(null);
		
		JComboBox cbxDoctores = new JComboBox();
		cbxDoctores.setBounds(10, 37, 226, 20);
		panelPanelBusqueda.add(cbxDoctores);
		
		JLabel lblDoctor = new JLabel("Doctor");
		lblDoctor.setBounds(10, 18, 226, 14);
		panelPanelBusqueda.add(lblDoctor);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 94, 246, 308);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUserImage = new JLabel("");
		lblUserImage.setBounds(55, 21, 134, 114);
		lblUserImage.setIcon(new ImageIcon(((new ImageIcon(BuscarCita.class.getResource("/image/userA.png"))).getImage()).getScaledInstance(lblUserImage.getWidth(), 
				lblUserImage.getHeight(), Image.SCALE_SMOOTH)));
		panel_1.add(lblUserImage);
		
		txtDoctor = new JTextField();
		txtDoctor.setBounds(31, 166, 184, 20);
		panel_1.add(txtDoctor);
		txtDoctor.setColumns(10);
		
		JLabel lblNombre = new JLabel("Doctor");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(31, 146, 184, 14);
		panel_1.add(lblNombre);
		
		JLabel lblIddoctor = new JLabel("Identificaci\u00F3n");
		lblIddoctor.setHorizontalAlignment(SwingConstants.CENTER);
		lblIddoctor.setBounds(31, 197, 184, 14);
		panel_1.add(lblIddoctor);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(31, 217, 184, 20);
		panel_1.add(txtId);
		
		JLabel lblMximaCantidadDe = new JLabel("M\u00E1xima cantidad de citas");
		lblMximaCantidadDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblMximaCantidadDe.setBounds(31, 249, 184, 14);
		panel_1.add(lblMximaCantidadDe);
		
		JSpinner spnCitas = new JSpinner();
		spnCitas.setModel(new SpinnerNumberModel(1, 1, 2, Clinica.getInstance().getOpcionesSistema().getMaxCitasSist()));
		spnCitas.setBounds(31, 274, 134, 20);
		panel_1.add(spnCitas);
		
		btnMaxCitas = new JButton("");
		btnMaxCitas.setIcon(new ImageIcon(BuscarCita.class.getResource("/image/change.png")));
		btnMaxCitas.setBounds(175, 274, 40, 23);
		panel_1.add(btnMaxCitas);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Citas en cola", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(266, 11, 438, 391);
		contentPanel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		tableCitas = new JTable();
		scrollPane.setViewportView(tableCitas);
		setLocationRelativeTo(null);
		
		screenPath = "/image/UserA.png";
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear");
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
	}
}
