package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

import logical.Clinica;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Dashboard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Dashboard() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Guardar datos.
				FileOutputStream clinicaGuardar;
				ObjectOutputStream clinicaWrite;
				
				try {
					clinicaGuardar = new FileOutputStream("data/clinica.dat");
					clinicaWrite = new ObjectOutputStream(clinicaGuardar);
					clinicaWrite.writeObject(Clinica.getInstance());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		setTitle("Dashboard");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/image/caduceusBlue.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRegistro = new JMenu("Pacientes");
		menuBar.add(mnRegistro);
		
		JMenuItem mntmRegPaciente = new JMenuItem("Registrar Paciente");
		mnRegistro.add(mntmRegPaciente);
		
		JMenuItem mntmRegistroDeVivienda = new JMenuItem("Registro de vivienda");
		mnRegistro.add(mntmRegistroDeVivienda);
		
		JMenu mnEnfermedades = new JMenu("Enfermedades");
		menuBar.add(mnEnfermedades);
		
		JMenuItem mntmAgregarEnfermedad = new JMenuItem("Agregar Enfermedad");
		mnEnfermedades.add(mntmAgregarEnfermedad);
		
		JMenuItem mntmListaDeEnfermedades = new JMenuItem("Lista de Enfermedades");
		mnEnfermedades.add(mntmListaDeEnfermedades);
		
		JMenuItem mntmControlEnfermedades = new JMenuItem("Control Enfermedades");
		mnEnfermedades.add(mntmControlEnfermedades);
		
		JMenu mnHistorial = new JMenu("Historial");
		menuBar.add(mnHistorial);
		
		JMenuItem mntmHistorialClnico = new JMenuItem("Historial Cl\u00EDnico");
		mnHistorial.add(mntmHistorialClnico);
		
		JMenuItem mntmReportes = new JMenuItem("Reportes");
		mnHistorial.add(mntmReportes);
		
		JMenu mnVacunacin = new JMenu("Vacunaci\u00F3n");
		menuBar.add(mnVacunacin);
		
		JMenuItem mntmCrearVacuna = new JMenuItem("Crear Vacuna");
		mnVacunacin.add(mntmCrearVacuna);
		
		JMenuItem mntmListaDeVacunas = new JMenuItem("Lista de vacunas");
		mnVacunacin.add(mntmListaDeVacunas);
		
		JMenu mnAdministracin = new JMenu("Administraci\u00F3n");
		menuBar.add(mnAdministracin);
		
		JMenuItem mntmUsuarios = new JMenuItem("Usuarios");
		mntmUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios ventana = new Usuarios();
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnAdministracin.add(mntmUsuarios);
		
		JMenuItem mntmAdministradores = new JMenuItem("Administradores");
		mntmAdministradores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administradores ventana = new Administradores();
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnAdministracin.add(mntmAdministradores);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panelEnfermedades = new JPanel();
		panelEnfermedades.setBorder(new TitledBorder(null, "Enfermedades cr\u00EDticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEnfermedades.setBounds(10, 38, 518, 300);
		panel.add(panelEnfermedades);
		panelEnfermedades.setLayout(null);
		
		JPanel panelVacunas = new JPanel();
		panelVacunas.setBorder(new TitledBorder(null, "Vacunaci\u00F3n general", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelVacunas.setBounds(536, 38, 518, 300);
		panel.add(panelVacunas);
		panelVacunas.setLayout(null);
		
		JPanel panelSangre = new JPanel();
		panelSangre.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Estad\u00EDsticas del tipo de sangre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSangre.setBounds(10, 349, 518, 300);
		panel.add(panelSangre);
		panelSangre.setLayout(null);
		
		JPanel panelEdad = new JPanel();
		panelEdad.setBorder(new TitledBorder(null, "Edades de los pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEdad.setBounds(536, 349, 518, 300);
		panel.add(panelEdad);
		panelEdad.setLayout(null);
		
		JLabel lblUserImage = new JLabel("");
		lblUserImage.setBounds(871, 11, 29, 25);
		lblUserImage.setIcon(new ImageIcon(((new ImageIcon(Dashboard.class.getResource("/image/userA.png"))).getImage()).getScaledInstance(lblUserImage.getWidth(), 
				lblUserImage.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(lblUserImage);
		
		JLabel lblNombreUsuario = new JLabel("Nombre del usuario");
		lblNombreUsuario.setBounds(910, 15, 144, 16);
		panel.add(lblNombreUsuario);
		
		JLabel lblLogoTipo = new JLabel("");
		lblLogoTipo.setBounds(30, 11, 29, 25);
		lblLogoTipo.setIcon(new ImageIcon(((new ImageIcon(Dashboard.class.getResource("/image/caduceus.png"))).getImage()).getScaledInstance(lblLogoTipo.getWidth(), 
				lblLogoTipo.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(lblLogoTipo);
		
		JLabel lblClnicaGeneral = new JLabel("Cl\u00EDnica General");
		lblClnicaGeneral.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblClnicaGeneral.setBounds(69, 11, 144, 25);
		panel.add(lblClnicaGeneral);
		
	}
}
