package PRESENTACION;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import NEGOCIO.Nodo;



public class CaratulaGr extends JPanel {

	


	/**
	 * Create the panel.
	 */
	public CaratulaGr() {

	}
	public void paint(Graphics g) {
		
		super.paint(g);
		Graphics2D g2=(Graphics2D)g;
		
		Image Carat=Toolkit.getDefaultToolkit().getImage("Prese.png");
		g2.drawImage(Carat,0,0,600,800,this);
	
		
		
	}
	
	
	

}
