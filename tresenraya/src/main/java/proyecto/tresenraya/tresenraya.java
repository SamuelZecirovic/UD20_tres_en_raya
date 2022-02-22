package proyecto.tresenraya;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class tresenraya extends JFrame {

	private JPanel contentPane;
	private JTextField textField_J2;
	private JTextField textField_J1;
	private ButtonGroup bg = new ButtonGroup(); // variable que evita seleccionar mas de una opcion
	private ButtonGroup bg2 = new ButtonGroup(); // variable que evita seleccionar mas de una opcion
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;
	// rutas de las imagenes en variables
	private String ximage = "images/Xedit.png",
			cimage = "images/circuloedit.png", 
			jugador1 = "Jugador1";// nombre jugador 1

	// turn: diferencia el jugador con las x y con las o
	// cont: es un contador de los turnos
	private int turn = 0, cont = 0;
	
	private boolean iscpu2 = false, iscpu1 = false;
	
	Random claseRandom = new Random(); // Esto crea una instancia de la Clase Random
	
	//array de los labels que identifican las posiciones de cada cuadro
	JLabel labels[] = new JLabel[9];
	
	//array bidimensional con todos los resultados ganadores
	int movganador[][] = { 
			{ 0, 1, 2 },
			{ 3, 4, 5 },
			{ 6, 7, 8 },
			{ 0, 3, 6 },
			{ 1, 4, 7 },
			{ 2, 5, 8 },
			{ 0, 4, 8 },
			{ 2, 4, 6 } };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tresenraya frame = new tresenraya();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public tresenraya() {
		setTitle("Tres En Raya");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 903, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(440, 11, 437, 478);
		contentPane.add(panel);
		panel.setLayout(null);

		//boton que reinicia la partida
		JButton btnNewButton = new JButton("Nueva Partida");
		btnNewButton.setBounds(135, 22, 159, 40);
		panel.add(btnNewButton);
		
		//funcion que se ejecuta cuando apretas el boton y limpia las casillas y reinicia el contador
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < labels.length; i++) {
					labels[i].setText("");
					labels[i].setIcon(new ImageIcon(""));
					cont = 0;
					if(iscpu1) {
						movcpu();
					}
				}
			}
		});

		//cartel donde se mostrara como va la partida, se ira cambiando continunamente
		lblNewLabel_2 = new JLabel("turno del jugador: " + jugador1);
		lblNewLabel_2.setBounds(33, 73, 248, 14);
		panel.add(lblNewLabel_2);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1_1.setBounds(31, 290, 383, 166);
		panel.add(panel_1_1);

		JLabel lblJugador = new JLabel("Jugador 2");
		lblJugador.setBounds(10, 11, 63, 23);
		panel_1_1.add(lblJugador);

		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setBounds(10, 45, 63, 23);
		panel_1_1.add(lblNombre_1);

		textField_J2 = new JTextField();
		textField_J2.addKeyListener(new KeyAdapter() {
		});
		textField_J2.setColumns(10);
		textField_J2.setBounds(68, 45, 105, 20);
		panel_1_1.add(textField_J2);

		JLabel lblNewLabel_1_1 = new JLabel("Tipo:");
		lblNewLabel_1_1.setBounds(10, 79, 46, 14);
		panel_1_1.add(lblNewLabel_1_1);

		// radio para seleccionar jugar contra un humano o la maquina
		JRadioButton humano2 = new JRadioButton("Humano");
		humano2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iscpu2 = false;
			}
		});
		humano2.setBounds(68, 75, 86, 23);
		panel_1_1.add(humano2);

		JRadioButton cpu2 = new JRadioButton("Cpu");
		cpu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iscpu2 = true;
			}
		});
		cpu2.setBounds(156, 75, 86, 23);
		panel_1_1.add(cpu2);

		
		bg2.add(humano2); // variable que evita seleccionar mas de una opcion
		bg2.add(cpu2); // variable que evita seleccionar mas de una opcion

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1_1_1.setBounds(31, 111, 383, 166);
		panel.add(panel_1_1_1);

		JLabel lblNewLabel_3_1 = new JLabel("Jugador 1");
		lblNewLabel_3_1.setBounds(10, 11, 63, 23);
		panel_1_1_1.add(lblNewLabel_3_1);

		JLabel lblNombre_1_1 = new JLabel("Nombre:");
		lblNombre_1_1.setBounds(10, 45, 63, 23);
		panel_1_1_1.add(lblNombre_1_1);

		textField_J1 = new JTextField();
		textField_J1.addKeyListener(new KeyAdapter() {
		});
		textField_J1.setColumns(10);
		textField_J1.setBounds(68, 45, 105, 20);
		panel_1_1_1.add(textField_J1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Tipo:");
		lblNewLabel_1_1_1.setBounds(10, 79, 46, 14);
		panel_1_1_1.add(lblNewLabel_1_1_1);

		// radio para seleccionar jugar contra un humano o la maquina
		JRadioButton humano1 = new JRadioButton("Humano");
		humano1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iscpu1 = false;
			}
		});
		humano1.setBounds(68, 75, 86, 23);
		panel_1_1_1.add(humano1);

		JRadioButton cpu1 = new JRadioButton("Cpu");
		cpu1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iscpu1 = true;
			}
		});
		cpu1.setBounds(156, 75, 86, 23);
		panel_1_1_1.add(cpu1);

		bg.add(humano1); // variable que evita seleccionar mas de una opcion
		bg.add(cpu1); // variable que evita seleccionar mas de una opcion

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(10, 11, 416, 478);
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 3, 0, 0));

		JPanel panel_G1 = new JPanel();
		panel_G1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.add(panel_G1);
		panel_G1.setLayout(null);

		final JLabel lblNewLabel_G1 = new JLabel("");

		lblNewLabel_G1.addMouseListener(new MouseAdapter() {
			//funcion que cuando cliquen encima de la casilla llamara a la funcion changeimge
			public void mousePressed(MouseEvent e) {
				//la funcion changename te devuelve una de las imagenes guardadas anteriormente y la declaramos como icono
				changeimage(0);
			}
		});
		lblNewLabel_G1.setBounds(10, 11, 117, 136);
		panel_G1.add(lblNewLabel_G1);

		JPanel panel_G2 = new JPanel();
		panel_G2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.add(panel_G2);
		panel_G2.setLayout(null);

		final JLabel lblNewLabel_G2 = new JLabel("");
		lblNewLabel_G2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				changeimage(1);
			}
		});
		lblNewLabel_G2.setBounds(10, 11, 117, 136);
		panel_G2.add(lblNewLabel_G2);

		JPanel panel_G3 = new JPanel();
		panel_G3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.add(panel_G3);
		panel_G3.setLayout(null);

		final JLabel lblNewLabel_G3 = new JLabel("");
		lblNewLabel_G3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				changeimage(2);
			}
		});
		lblNewLabel_G3.setBounds(10, 11, 117, 136);
		panel_G3.add(lblNewLabel_G3);

		JPanel panel_G4 = new JPanel();
		panel_G4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_G4.setToolTipText("");
		panel_2.add(panel_G4);
		panel_G4.setLayout(null);

		final JLabel lblNewLabel_G4 = new JLabel("");
		lblNewLabel_G4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				changeimage(3);
			}
		});
		lblNewLabel_G4.setBounds(10, 11, 117, 136);
		panel_G4.add(lblNewLabel_G4);

		JPanel panel_G5 = new JPanel();
		panel_G5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.add(panel_G5);
		panel_G5.setLayout(null);

		final JLabel lblNewLabel_G5 = new JLabel("");
		lblNewLabel_G5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				changeimage(4);
			}
		});
		lblNewLabel_G5.setBounds(10, 11, 117, 136);
		panel_G5.add(lblNewLabel_G5);

		JPanel panel_G6 = new JPanel();
		panel_G6.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.add(panel_G6);
		panel_G6.setLayout(null);

		final JLabel lblNewLabel_G6 = new JLabel("");
		lblNewLabel_G6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				changeimage(5);
			}
		});
		lblNewLabel_G6.setBounds(10, 11, 117, 136);
		panel_G6.add(lblNewLabel_G6);

		JPanel panel_G7 = new JPanel();
		panel_G7.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.add(panel_G7);
		panel_G7.setLayout(null);

		final JLabel lblNewLabel_G7 = new JLabel("");
		lblNewLabel_G7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				changeimage(6);
			}
		});
		lblNewLabel_G7.setBounds(10, 11, 117, 136);
		panel_G7.add(lblNewLabel_G7);

		JPanel panel_G8 = new JPanel();
		panel_G8.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.add(panel_G8);
		panel_G8.setLayout(null);

		final JLabel lblNewLabel_G8 = new JLabel("");
		lblNewLabel_G8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				changeimage(7);
			}
		});
		lblNewLabel_G8.setBounds(10, 11, 117, 136);
		panel_G8.add(lblNewLabel_G8);

		JPanel panel_G9 = new JPanel();
		panel_G9.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.add(panel_G9);
		panel_G9.setLayout(null);

		final JLabel lblNewLabel_G9 = new JLabel("");
		lblNewLabel_G9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				changeimage(8);
			}
		});
		lblNewLabel_G9.setBounds(5, 11, 117, 136);
		panel_G9.add(lblNewLabel_G9);
		

		//declaro cada casilla a su respectivo label
		labels[0] = lblNewLabel_G1;
		labels[1] = lblNewLabel_G2;
		labels[2] = lblNewLabel_G3;
		labels[3] = lblNewLabel_G4;
		labels[4] = lblNewLabel_G5;
		labels[5] = lblNewLabel_G6;
		labels[6] = lblNewLabel_G7;
		labels[7] = lblNewLabel_G8;
		labels[8] = lblNewLabel_G9;
			
	}

	//funcion donde se rellena la casilla dependiendo del jugador
	public void changeimage(int pos) {
		//condicion que revisa a que jugador le toca jugar; turn 0 = jugador1, turn1 = jugador 2
		if (turn == 0) {
			//comprueba que la casilla este vacia
			if (labels[pos].getText().equals("")) {
				turn = 1; // cambiamos turno para que el proximo movimiento sea el del jugador 2
				labels[pos].setText("x"); //le ponemos como texto una x
				lblNewLabel_2.setText("turno del jugador: " + textField_J2.getText()); // mostramos por el letrero que le toca el turno al jugador dos
				cont++; // sumamos el contador
				labels[pos].setIcon(new ImageIcon(ximage)); // le devuelve la imagen que le pertoca a la casilla en este caso la x
				comprobarganador(); // llama a la funcion comprobarganador
				if(iscpu2) {
					movcpu();
				}
			} else {
				System.out.println("no puede seleccionar esta"); //en el caso de que no este vacio la casilla no puede seleccionarla
				if(iscpu1) { //si la maquina ha seleccionado una casilla que no puede seleccionar 
					movcpu(); // vuelve a llamar a la funcion
				}
			}
			
		} else { // lo mismo que lo anterior pero con el jugador2
			if (labels[pos].getText().equals("")) {
				turn = 0;
				labels[pos].setText("o");
				lblNewLabel_2.setText("turno del jugador: " + textField_J1.getText());	
				cont++;
				labels[pos].setIcon(new ImageIcon(cimage));
				comprobarganador();
				if(iscpu1) {
					movcpu();
				}
			} else {
				System.out.println("no puede seleccionar esta");
				if(iscpu2) {
					movcpu();
				}
			}
		}
	}
	
	//funcion que comprueba si ha gando alguien
	public void comprobarganador() {
		// bucle que recorre los posibles resultados ganadores
		for (int i = 0; i < movganador.length; i++) {
			//comprueba cada posicion ganadora y si las tres casillas contienen lo mismo el jugador con esa ficha es el ganador
			if (labels[movganador[i][0]].getText().equals("x") && labels[movganador[i][1]].getText().equals("x")
					&& labels[movganador[i][2]].getText().equals("x")) {

				//mostramos por el cartel el gandor
				lblNewLabel_2.setText("Ha ganado el jugador " + textField_J1.getText() + " con las X");

			}
			if (labels[movganador[i][0]].getText().equals("o") && labels[movganador[i][1]].getText().equals("o")
					&& labels[movganador[i][2]].getText().equals("o")) {
				lblNewLabel_2.setText("Ha ganado el jugador " + textField_J2.getText() + " con las O");

			}
			//si el contador llega a 9 implica que han empatado, por lo que lo mostramos por pantalla
			if (cont == 9) {
				lblNewLabel_2.setText("Habeis empatado");
			}
		}
	}

	// funcion que realiza un movimiento aleatorio
	public void movcpu() {
		int num = claseRandom.nextInt(9) - 1; // Genera un número entre 0 y 7, sin decimales raros ni nada;
		changeimage(num);
	}

}
