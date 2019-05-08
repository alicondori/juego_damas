package PRESENTACION;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Datos.Persistencia;
import NEGOCIO.Cola;
import NEGOCIO.JuegoDamas;
import NEGOCIO.Reglas;
import NEGOCIO.Tablero;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.UIManager;

public class FromJuegoDamas extends JFrame {

	private JPanel contentPane;
	
	//CREANDO UN OBJETO
	public JuegoDamas jgDamas=new JuegoDamas();
	
	public Persistencia per=new Persistencia();
	//OBVARIABLES PARA SALVAR LA POSICION
	public int fp;
	public int cp;
	
	//VOLVIENDO ACCESIBLE AL GRAFTABLERO
	private TableroGrafico grafTablero;

	private GraficadoNegra grafNegra;
	private GraficadorBlanca grafBlanca;
	//LABEL PARA MOSTRAR TURNO
	private JLabel lblTurno;
	
	//CONTADOR PARA HACER LA SUCIA
	public int contadorcillo;
	public int salF=-1;
	public int salC=-1;
	private JLabel lblVersion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FromJuegoDamas frame = new FromJuegoDamas();
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
	public FromJuegoDamas() 
	{
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) //EVENTO AL DAR UN CLICK
			{
				int xe =me.getX();//EN ESTA VARIABLE OPTENEMOS LAS COERDANADAS EN X AL DAR PRESIONAR
				int ye=me.getY();
				
				System.out.println("presionado"+"en x: "+xe+"-----"+"en y: "+ye);
				
				int px=180;//REFERENCIA PARA DIBUJAR LOS RECTANGULOS
				int py=112;
				
				for (int f = 0; f < jgDamas.tb.mtz.Filas; f++) 
				{
					px=180;
					for (int c = 0; c < jgDamas.tb.mtz.Columnas; c++) 
					{
						Rectangle rec=new Rectangle(px,py,62,62);//CREANDO EL RECTANGULO
						if (rec.contains(xe,ye))//VERIFCA SI LA COOR. DE LA PRESION ESTA DENTRO DEL AREA DEL RECT.
						{
							contadorcillo=0;
							fp=f;//SALVANDO LA POSICION DE LA MATRIZ
							cp=c;
							if (salF==f && salC==c) //ES IGUAL A LA POSICION ANTERIOR "VIENE DE UNA COMIDAPERIMTIDA"
							{
								jgDamas.cambiarTurno();/*ESTE CAMBIA EL TURNO OTRA VEZ PARA QUE EL MISMO COLOR
								SE EJECUTE DOS VECES*/
								salF=salC=-1;
								contadorcillo=1;//PARA QUE NO ENTRE AL MOVIMIENTO SIMPLE
							}	
						}
						px+=62;
					}
					py+=62;
				}
			}
			@Override
			public void mouseReleased(MouseEvent me) 
			{
				/*ESTE EVENTO LO QUE HACE ES TOMAR EL PUNTO DONDE SE DEJA DE PRESIONAR*/
				int xe =me.getX();
				int ye=me.getY();
				
				System.out.println("Levantado"+"en x: "+xe+"-----"+"en y: "+ye);
				
				int px=180;//REFERENCIA PARA DIBUJAR LOS RECTANGULOS
				int py=112;
				
				for (int f = 0; f < jgDamas.tb.mtz.Filas; f++) 
				{
					px=180;
					for (int c = 0; c < jgDamas.tb.mtz.Columnas; c++) 
					{
						Rectangle rec=new Rectangle(px,py,62,62);//CREANDO EL RECTANGULO
						if (rec.contains(xe,ye))//VERIFCA SI LA COOR. DEL DEJADO ESTA DENTRO DEL AREA DEL RECT.
						{
							
							if (contadorcillo==0) /*SOLAMENTE CUANDO SEA "0" PERMITE ESTE MOVIMIENTO SIMPLE*/
							{
								if (Reglas.movPermitido(fp, cp, f, c, jgDamas.tb.mtz,jgDamas.turno)) 
								{
									jgDamas.tb.mtz.asignarEle(Reglas.VACIO, fp, cp);/*ASIGNA "0" A LA CELDA
									DEL PRESS*/
									jgDamas.tb.mtz.asignarEle(jgDamas.turno, f, c);/*ASIGNANDO COLOR A LA FICHA
									ATRAVEZ DEL TURNO ACTUAL*/
									
									Reglas.coronar(f, c, jgDamas.tb.mtz);
									jgDamas.cambiarTurno();
									//CODIGO PARA MOSTRAR EL TURNO
									cambiarLetrero();
									
									salC=salF=-1;//ESTO RESTABLECE A SALC Y SALF "LES QUITA EL VALOR"
									grafTablero.repaint();
								}
								if (Reglas.movReinaPermitido(fp, cp, f, c, jgDamas.tb.mtz, jgDamas.turno)) 
								{
									jgDamas.tb.mtz.asignarEle(Reglas.VACIO, fp, cp);/*ASIGNA "0" A LA CELDA
									DEL PRESS*/
									asignandoColorReina(f, c);//PINIENDO FICHA A LA POSICION ACTUAL
									jgDamas.cambiarTurno();
									//CODIGO PARA MOSTRAR EL TURNO
									cambiarLetrero();
									
									salC=salF=-1;//ESTO RESTABLECE A SALC Y SALF "LES QUITA EL VALOR"
									grafTablero.repaint();
								}
							}
							if (Reglas.comidaPermitida(fp, cp, f, c, jgDamas.tb.mtz,jgDamas.turno,jgDamas.colaNegra,jgDamas.colaBlanca)) 
							{
								jgDamas.tb.mtz.asignarEle(Reglas.VACIO, fp, cp);/*ASIGNA "0" A LA CELDA
								DEL PRESS*/
								jgDamas.tb.mtz.asignarEle(jgDamas.turno, f, c);/*ASIGNANDO COLOR A LA FICHA
								ATRAVEZ DEL TURNO ACTUAL*/
								
								
								
								salF=f;//SALVANDO LA POSICION ACTUAL
								salC=c;
								//CODIGO PARA MOSTRAR EL TURNO
								cambiarLetreroComida();
								Reglas.coronar(f, c, jgDamas.tb.mtz);
								
								jgDamas.cambiarTurno();
								grafTablero.repaint();
								contadorcillo=0;//ESTO ES PAR QUE NO CAMBIE EL TURNO EL DE ABAJO
								grafNegra.repaint();
								grafBlanca.repaint();
							}
							if (Reglas.comidaReinaPermitida(fp, cp, f, c, jgDamas.tb.mtz, jgDamas.turno,jgDamas.colaNegra,jgDamas.colaBlanca)) 
							{
								jgDamas.tb.mtz.asignarEle(Reglas.VACIO, fp, cp);//ASIGANANDO "0"
								asignandoColorReina(f, c);//ASIGNANDO COLOR FICHA
								salF=f;//SALVANDO LA POSICION ACTUAL
								salC=c;
								//CODIGO PARA MOSTRAR EL TURNO
								cambiarLetreroComida();
								jgDamas.cambiarTurno();
								grafTablero.repaint();
								contadorcillo=0;//ESTO ES PAR QUE NO CAMBIE EL TURNO EL DE ABAJO
								grafNegra.repaint();
								grafBlanca.repaint();
							}
							if (contadorcillo==1)
							{
								jgDamas.cambiarTurno();
								cambiarLetrero();
							}
							//VERIFICANDO SI HAY GANADOR
							if (jgDamas.colaNegra.cantElementos()==12 || jgDamas.colaBlanca.cantElementos()==12) 
							{
								if (jgDamas.colaNegra.cantElementos()>jgDamas.colaBlanca.cantElementos()) 
								{
									JOptionPane.showMessageDialog(new JLabel(), "EL GANADOR ES COLOR BLANCO");
								}
								else 
								{
									JOptionPane.showMessageDialog(new JLabel(), "EL GANADOR ES COLOR NEGRO");
								}
							}
							
						}
						px+=62;
					}
					py+=62;
				}
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 0, 900, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		grafTablero = new TableroGrafico(jgDamas.tb);
		grafTablero.setBounds(170, 100, 500, 500);
		grafTablero.setBackground(Color.GRAY);
		contentPane.add(grafTablero);
		grafTablero.setLayout(null);
		
		lblTurno = new JLabel("Negro");
		lblTurno.setForeground(Color.BLACK);
		lblTurno.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTurno.setBounds(680, 162, 150, 36);
		contentPane.add(lblTurno);
		
		JLabel lblTurno_1 = new JLabel("TURNO");
		lblTurno_1.setForeground(Color.BLUE);
		lblTurno_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTurno_1.setBounds(690, 116, 91, 43);
		contentPane.add(lblTurno_1);
		
		lblVersion = new JLabel("Desing:2.1");
		lblVersion.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 14));
		lblVersion.setForeground(Color.RED);
		lblVersion.setBounds(10, 301, 150, 29);
		contentPane.add(lblVersion);
		
		grafBlanca = new GraficadorBlanca(jgDamas.colaBlanca);
		grafBlanca.setBounds(112, 611, 618, 50);
		contentPane.add(grafBlanca);
		grafBlanca.setBackground(Color.WHITE);
		grafBlanca.setLayout(null);
		
		grafNegra = new GraficadoNegra(jgDamas.colaNegra);
		grafNegra.setBounds(112, 39, 618, 50);
		contentPane.add(grafNegra);
		grafNegra.setBackground(Color.WHITE);
		grafNegra.setLayout(null);
		
		//------------------M E N U---------------------
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		menuBar.setBackground(Color.BLUE);
		menuBar.setBounds(0, 0, 97, 21);
		contentPane.add(menuBar);
		
		JMenu mnMenuju = new JMenu("Menu");
		menuBar.add(mnMenuju);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				per.guardarObjeto(jgDamas);//ENVIANDO EL OBJETO
			}
		});
		mnMenuju.add(mntmGuardar);
		
		JMenuItem mntmRecuperar = new JMenuItem("Recuperar");
		mntmRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				jgDamas = (JuegoDamas)per.recuperarObjeto();//DEVUELVE UN OBJECT EL CUAL CONVERTIMOS EN JUEGODAMAS
				grafTablero.tb=jgDamas.tb;//PARECE QUE HAY ALGUN BUG CONE STE CODIGO
				//AUMENTANDO
				grafNegra.cl=jgDamas.colaNegra;
				grafBlanca.cl=jgDamas.colaBlanca;
				
				grafBlanca.repaint();
				grafNegra.repaint();
				grafTablero.repaint();
			}
		});
		mnMenuju.add(mntmRecuperar);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				jgDamas	= new JuegoDamas();//CREANDO NUEVO JUEGO
				
				grafTablero.tb=jgDamas.tb;//ASIGNANDO TABLERO A NUEVO OBEJTO
				
				grafNegra.cl=jgDamas.colaNegra;//ASIGNANDO COLA A NUEVO OBEJTO
				grafBlanca.cl=jgDamas.colaBlanca;//ASIGNANDO COLA A NUEVO OBEJTO
				
				grafBlanca.repaint();
				grafNegra.repaint();
				grafTablero.repaint();
			}
		});
		mnMenuju.add(mntmNuevo);
		
		grafTablero.repaint();
		
		//MAXIMIZA EL FROM
		/*this.setExtendedState(MAXIMIZED_BOTH);*/	
	}
	//ESCRIBIENDO METODOS
	public void cambiarLetrero()
	{
		if (jgDamas.turno==1) {
			lblTurno.setText("Negro");
		}
		else
		{
			lblTurno.setText("Rojo");
		}
	}
	public void cambiarLetreroComida()
	{
		if (jgDamas.turno==1) {
			lblTurno.setText("Negro >comiendo...");
		}
		else
		{
			lblTurno.setText("Rojo >comiendo...");
		}
	}
	public void asignandoColorReina(int f, int c)
	{
		if (jgDamas.turno==Reglas.COLOR_NEGRO) 
		{
			jgDamas.tb.mtz.asignarEle(Reglas.REINA_NEGRA, f, c);/*ASIGNANDO COLOR A LA FICHA
			ATRAVEZ DEL TURNO ACTUAL*/
		}
		else
		{
			jgDamas.tb.mtz.asignarEle(Reglas.REINA_BLANCA, f, c);/*ASIGNANDO COLOR A LA FICHA
			ATRAVEZ DEL TURNO ACTUAL*/
		}
	}
}
