package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

import logical.Administrador;
import logical.CantidadVacuna;
import logical.Clinica;
import logical.Doctor;
import logical.Empleado;
import logical.Enfermedad;
import logical.Paciente;
import logical.Secretaria;
import logical.Vacuna;

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
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class Dashboard extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Empleado actual;
	
	// Label
	private static JLabel lblNombreUsuario;
	private static JLabel lblEnferCrit;
	private static JLabel lblVacunacion;
	private static JLabel lblSangre;
	private static JLabel lblEdades;
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
	public Dashboard(Empleado actual) {
		this.actual = actual;
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

		// Menú de pacientes.
		JMenu mnRegistro = new JMenu("Pacientes");
		menuBar.add(mnRegistro);

		JMenuItem mntmRegPaciente = new JMenuItem("Registrar Paciente");
		mntmRegPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegPaciente ventana = null;
				try {
					ventana = new RegPaciente(null);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ventana.setModal(true);
				ventana.setVisible(true);
				
			}
		});
		mnRegistro.add(mntmRegPaciente);

		JMenuItem mntmRegistroDeVivienda = new JMenuItem("Registro de vivienda");
		mntmRegistroDeVivienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarPaciente ventana = new BuscarPaciente(actual, false);
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnRegistro.add(mntmRegistroDeVivienda);


		// Menú de enfermedades.
		JMenu mnEnfermedades = new JMenu("Enfermedades");
		menuBar.add(mnEnfermedades);

		JMenuItem mntmAgregarEnfermedad = new JMenuItem("Agregar Enfermedad");
		mntmAgregarEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearEnfermedad ventana = new CrearEnfermedad(null);
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnEnfermedades.add(mntmAgregarEnfermedad);

		JMenuItem mntmListaDeEnfermedades = new JMenuItem("Lista de Enfermedades");
		mntmListaDeEnfermedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaEnfermedades ventana = new ListaEnfermedades(actual);
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		
		mnEnfermedades.add(mntmListaDeEnfermedades);

		JMenuItem mntmControlEnfermedades = new JMenuItem("Control Enfermedades");
		mntmControlEnfermedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlEnfermedades ventana = new ControlEnfermedades(actual);
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnEnfermedades.add(mntmControlEnfermedades);

		// Menú del historial clínico.
		JMenu mnHistorial = new JMenu("Historial");
		menuBar.add(mnHistorial);

		JMenuItem mntmHistorialClnico = new JMenuItem("Historial Cl\u00EDnico");
		mntmHistorialClnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarPaciente ventana = new BuscarPaciente(actual, true);
				ventana.setModal(true);
				ventana.setVisible(true);	
			}
		});
		mnHistorial.add(mntmHistorialClnico);

		JMenuItem mntmReportes = new JMenuItem("Reportes");
		mnHistorial.add(mntmReportes);

		// Menú de las vacunas.
		JMenu mnVacunacin = new JMenu("Vacunaci\u00F3n");
		menuBar.add(mnVacunacin);

		JMenuItem mntmCrearVacuna = new JMenuItem("Crear Vacuna");
		mntmCrearVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearVacuna ventana = new CrearVacuna(null);
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnVacunacin.add(mntmCrearVacuna);

		JMenuItem mntmListaDeVacunas = new JMenuItem("Lista de vacunas");
		mntmListaDeVacunas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaVacunas ventana = new ListaVacunas(actual);
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnVacunacin.add(mntmListaDeVacunas);


		// Menú para los administradores.
		JMenu mnAdministracin = new JMenu("Administraci\u00F3n");
		menuBar.add(mnAdministracin);

		JMenuItem mntmUsuarios = new JMenuItem("Usuarios");
		mntmUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios ventana = new Usuarios(false);
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnAdministracin.add(mntmUsuarios);

		JMenuItem mntmAdministradores = new JMenuItem("Administradores");
		mntmAdministradores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios ventana = new Usuarios(true);
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnAdministracin.add(mntmAdministradores);

		// Menú del consultorio
		JMenu mnConsultorio = new JMenu("Consultorio");
		menuBar.add(mnConsultorio);

		JMenuItem mntmCitas = new JMenuItem("Citas");
		mntmCitas.addActionListener(new ActionListener() {
			// Borrar cuando hagan push
			public void actionPerformed(ActionEvent e) {
				BuscarCita ventana = new BuscarCita(actual);
				ventana.setModal(true);
				ventana.setVisible(true);
				
			}
		});
		mnConsultorio.add(mntmCitas);

		JMenuItem mntmConsultas = new JMenuItem("Consultas");
		mntmConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegConsulta ventana = new RegConsulta(actual, null);
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnConsultorio.add(mntmConsultas);
		
		JMenu mnConfiguracin = new JMenu("Configuraci\u00F3n");
		menuBar.add(mnConfiguracin);
		
		JMenuItem mntmOpciones = new JMenuItem("Opciones");
		mntmOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (actual instanceof Administrador) {
					if (((Administrador)actual).getAutoridad() == 1) {
						Opciones ventana = new Opciones();
						ventana.setModal(true);
						ventana.setVisible(true);
					}
				}
			}
		});
		
		mnConfiguracin.add(mntmOpciones);
		
		JMenuItem mntmPerfil = new JMenuItem("Perfil");
		mntmPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Perfil ventana = new Perfil(actual);
				ventana.setModal(true);
				ventana.setVisible(true);
				
			}
		});
		mnConfiguracin.add(mntmPerfil);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Guardar datos.
				FileOutputStream clinicaGuardar;
				ObjectOutputStream clinicaWrite;
				actual.setLastConnection(new Date());
				try {
					clinicaGuardar = new FileOutputStream("data/clinica.dat");
					clinicaWrite = new ObjectOutputStream(clinicaGuardar);
					clinicaWrite.writeObject(Clinica.getInstance());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				Login ventana = new Login();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnConfiguracin.add(mntmSalir);

		// Ocultar opciones según los permisos del usuario
		if (actual instanceof Secretaria) {
			mnRegistro.setVisible(false);
			mnEnfermedades.setVisible(false);
			mnHistorial.setVisible(false);
			mnRegistro.setVisible(false);
			mnVacunacin.setVisible(false);
			mnAdministracin.setVisible(false);
			mntmConsultas.setVisible(false);
			mntmOpciones.setVisible(false);
		}

		if (actual instanceof Doctor) {
			mntmRegPaciente.setVisible(false);
			mntmAgregarEnfermedad.setVisible(false);
			mnRegistro.setVisible(false);
			mntmCrearVacuna.setVisible(false);
			mnAdministracin.setVisible(false);
			mntmOpciones.setVisible(false);
		}

		if (actual instanceof Administrador) {
			switch (((Administrador)actual).getAutoridad()) {
			case 4:
				mntmAgregarEnfermedad.setVisible(false);
				//mntmControlEnfermedades.setVisible(false);
				mntmCrearVacuna.setVisible(false);
			case 3:
				mnAdministracin.setVisible(false);
			case 2:
				mntmAdministradores.setVisible(false);
				mntmOpciones.setVisible(false);
			case 1:
				mnConsultorio.setVisible(false);
			}
		}

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
		
		lblEnferCrit = new JLabel("");
		lblEnferCrit.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnferCrit.setBounds(10, 21, 498, 268);
		panelEnfermedades.add(lblEnferCrit);

		JPanel panelVacunas = new JPanel();
		panelVacunas.setBorder(new TitledBorder(null, "Vacunaci\u00F3n general", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelVacunas.setBounds(536, 38, 518, 300);
		panel.add(panelVacunas);
		panelVacunas.setLayout(null);
		
		lblVacunacion = new JLabel("");
		lblVacunacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblVacunacion.setBounds(10, 21, 498, 268);
		panelVacunas.add(lblVacunacion);

		JPanel panelSangre = new JPanel();
		panelSangre.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Estad\u00EDsticas del tipo de sangre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSangre.setBounds(10, 349, 518, 300);
		panel.add(panelSangre);
		panelSangre.setLayout(null);
		
		lblSangre = new JLabel("");
		lblSangre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSangre.setBounds(10, 21, 498, 268);
		panelSangre.add(lblSangre);

		JPanel panelEdad = new JPanel();
		panelEdad.setBorder(new TitledBorder(null, "Edades de los pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEdad.setBounds(536, 349, 518, 300);
		panel.add(panelEdad);
		panelEdad.setLayout(null);
		
		lblEdades = new JLabel("");
		lblEdades.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdades.setBounds(10, 21, 498, 268);
		panelEdad.add(lblEdades);

		JLabel lblUserImage = new JLabel("");
		lblUserImage.setBounds(871, 11, 29, 25);
		lblUserImage.setIcon(new ImageIcon(((new ImageIcon(Dashboard.class.getResource("/image/userA.png"))).getImage()).getScaledInstance(lblUserImage.getWidth(), 
				lblUserImage.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(lblUserImage);

		lblNombreUsuario = new JLabel("Nombre del usuario");
		lblNombreUsuario.setText(actual.getNombre());
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
		
		// Llamar a los gráficos.
		Dashboard.cargarEnferCrit();
		Dashboard.cargarEdades();
		Dashboard.cargarSangre();
		Dashboard.cargarVacunacion();

	}
 
	public static void cargarEnferCrit() {
		// Primero obtener las enfermedades que son críticas.
		ArrayList<Enfermedad> enferCritica = new ArrayList<Enfermedad>();
		for (Enfermedad enfermedad : Clinica.getInstance().getEnfermedades()) {
			if (enfermedad.isRevision() && enfermedad.isListar()) {
				enferCritica.add(enfermedad);
			}
		}
		
		// Luego de encontrar las enfermedades críticas graficar si hay datos.
		if (enferCritica.size() > 0) {
			lblEnferCrit.setText("");
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			float relacion;
			float divisor = Clinica.getInstance().getPacientes().size()  > 0 ? Clinica.getInstance().getPacientes().size() : 1;
			String categoria = "";
			for (Enfermedad enfermedad : enferCritica) {				 
				relacion = ( (float)enfermedad.getCantPacientes() / divisor ) * 100;
				if (relacion <= 10) {
					categoria = "Bajo control";
				} else if (relacion <= 20) {
					categoria = "Nociva";					
				} else if (relacion > 20) {
					categoria = "Epidemia";
				}
					
				dataset.setValue(enfermedad.getCantPacientes(), enfermedad.getNombre(), categoria);
			}
			JFreeChart grafica = ChartFactory.createBarChart3D("", "Enfermedad", "Cantidad de pacientes.", dataset,
					PlotOrientation.VERTICAL, true, true, false);
			grafica.setBackgroundPaint(new Color(181, 250, 239));
			BufferedImage bufimg = grafica.createBufferedImage(460, 250);
			ImageIcon img = new ImageIcon(bufimg);
			lblEnferCrit.setIcon(img);
		} else {
			lblEnferCrit.setIcon(null);
			lblEnferCrit.setText("No hay datos en revisión.");
		}
		
		return;
	}
	
	public static void cargarVacunacion() {
		ArrayList<CantidadVacuna> listaVacunas = new ArrayList<CantidadVacuna>();
		for (Vacuna vacuna : Clinica.getInstance().getVacunas()) {
			CantidadVacuna nuevoObjeto = new CantidadVacuna(vacuna);
			// Hacer la referencia con la lista.
			listaVacunas.add(nuevoObjeto);
		}
		
		// Luego recopilar los datos
		for (CantidadVacuna cantidadVacuna : listaVacunas) {
			for (Paciente paciente : Clinica.getInstance().getPacientes()) {
				boolean encontrar = false; 
				int aux = 0;
				while (aux < paciente.getTodasLasVacunas().size() && !encontrar) {
					if (paciente.getTodasLasVacunas().get(aux).getNombre().equalsIgnoreCase(cantidadVacuna.getVacuna().getNombre())) {
						encontrar = true;
						// Preguntar si la tiene puesta para poder agregar que un paciente de esta tiene la vacuna puesta.
						if (paciente.getTodasLasVacunas().get(aux).isHecha()) {
							cantidadVacuna.setCantPacientes(cantidadVacuna.getCantPacientes() + 1);
						}
					}
					aux++;
				}
			}
		}
		
		// Luego de recopilar los datos, crear el gráfico.
		if (listaVacunas.size() > 0) {
			lblVacunacion.setText("");
			
			Collections.sort(listaVacunas, (o1, o2) -> Long.valueOf(o1.getCantPacientes()).compareTo(Long.valueOf(o2.getCantPacientes())));  // Ordenar según la cant de pacientes
			Collections.reverse(listaVacunas);  // Ordenar según el orden descendiente.
			
			// Crear gráfico solo con los 5 primeros valores top.
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			String categoria = "";
			int aux = 0;
			while (aux < listaVacunas.size() && aux < 5) {
				dataset.setValue(listaVacunas.get(aux).getCantPacientes(), listaVacunas.get(aux).getVacuna().getNombre(), categoria);
				aux++;
			}
			
			JFreeChart grafica = ChartFactory.createBarChart3D("", "Vacunas", "Cantidad de pacientes.", dataset,
					PlotOrientation.VERTICAL, true, true, false);
			grafica.setBackgroundPaint(new Color(181, 250, 239));
			BufferedImage bufimg = grafica.createBufferedImage(460, 250);
			ImageIcon img = new ImageIcon(bufimg);
			lblVacunacion.setIcon(img);
			
		} else {
			lblVacunacion.setIcon(null);
			lblVacunacion.setText("No existen vacunas en el sistema.");
		}
		
		return;
	}
	
	public static void cargarSangre() {	
		/* Por los tipos de sangre : A+, A-, AB+, AB, B+, B-, O+, O-*/
		if (Clinica.getInstance().getPacientes().size() > 0) {
			lblSangre.setText("");
			int tipoSangre[] = new int[8];
			for (Paciente paciente : Clinica.getInstance().getPacientes()) {
				switch (paciente.getTipoSangre()) {
				case "A+":
					tipoSangre[0]++;
					break;
				case "A-":
					tipoSangre[1]++;
					break;	
				case "AB+":
					tipoSangre[2]++;
					break;
				case "AB-":
					tipoSangre[3]++;
					break;
				case "B+":
					tipoSangre[4]++;
					break;
				case "B-":
					tipoSangre[5]++;
					break;
				case "O+":
					tipoSangre[6]++;
					break;
				case "O-":
					tipoSangre[7]++;
					break;
					
				}
			}
		
			DefaultPieDataset dataset = new DefaultPieDataset();
			dataset.setValue("A+", tipoSangre[0]);
			dataset.setValue("A-", tipoSangre[1]);
			dataset.setValue("AB+", tipoSangre[2]);
			dataset.setValue("AB-", tipoSangre[3]);
			dataset.setValue("B+", tipoSangre[4]);
			dataset.setValue("B-", tipoSangre[5]);
			dataset.setValue("O+", tipoSangre[6]);
			dataset.setValue("O-", tipoSangre[7]);
			
			// Graficar edades por rango.
			JFreeChart grafica = ChartFactory.createPieChart("", dataset, true, true, false);
			grafica.setBackgroundPaint(new Color(181, 250, 239));
			BufferedImage bufimg = grafica.createBufferedImage(460, 250);
			ImageIcon img = new ImageIcon(bufimg);
			lblSangre.setIcon(img);
		} else {
			lblSangre.setIcon(null);
			lblSangre.setText("No existen pacientes en el sistema.");
		}
		return;
	}
	
	public static void cargarEdades() {
		/* primera infancia (0-5 años) 0, infancia (6 - 11 años) 1,
		 * adolescencia (12-18 años) 2, juventud (14 - 26 años) 3,
		 * adultez (27 - 59 años) 4 y vejez (60 años y más) 5. */
		if (Clinica.getInstance().getPacientes().size() > 0) {
			lblEdades.setText("");
			int edades[] = new int[6];
			for (Paciente paciente : Clinica.getInstance().getPacientes()) {
				if (paciente.getEdad() <= 5) {
					edades[0]++;
				} else if (paciente.getEdad() <= 11) {
					edades[1]++;
				} else if (paciente.getEdad() <= 18) {
					edades[2]++;
				} else if (paciente.getEdad() <= 26) {
					edades[3]++;
				} else if (paciente.getEdad() <= 59) {
					edades[4]++;
				} else {
					edades[5]++;
				}
			}
			
			DefaultPieDataset dataset = new DefaultPieDataset();
			dataset.setValue("Bebés", edades[0]);
			dataset.setValue("Infantes", edades[1]);
			dataset.setValue("Adolescentes", edades[2]);
			dataset.setValue("Jóvenes", edades[3]);
			dataset.setValue("Adultos", edades[4]);
			dataset.setValue("Ancianos", edades[5]);
			
			// Graficar edades por rango.
			
			JFreeChart grafica = ChartFactory.createRingChart("", dataset, true, true, false);
			grafica.setBackgroundPaint(new Color(181, 250, 239));
			BufferedImage bufimg = grafica.createBufferedImage(460, 250);
			ImageIcon img = new ImageIcon(bufimg);
			lblEdades.setIcon(img);
		} else {
			lblEdades.setIcon(null);
			lblEdades.setText("No existen pacientes en el sistema.");
		}
		return;
	}
	
	public static JLabel getLblNombreUsuario() {
		return lblNombreUsuario;
	}

}
