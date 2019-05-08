package PRESENTACION;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FromCaratula extends JFrame {

	private JPanel contentPane;
	public CaratulaGr Caratula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FromCaratula frame = new FromCaratula();
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
	public FromCaratula() {
		setBackground(Color.WHITE);
		setForeground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 0, 614, 835);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	Caratula = new CaratulaGr();
		Caratula.setBounds(0, 0, 600, 800);
		contentPane.add(Caratula);
		Caratula.setLayout(null);
		
		JButton btnIniciar = new JButton("");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				FromJuegoDamas jg=new FromJuegoDamas();
				jg.setVisible(true);
				FromCaratula.this.dispose();	
			}
		});
		btnIniciar.setIcon(new ImageIcon("D:\\estruct date\\archivos-eclipse\\juegoDamas_Beta\\btn.png"));
		btnIniciar.setBounds(356, 319, 193, 40);
		Caratula.add(btnIniciar);
	}
}
