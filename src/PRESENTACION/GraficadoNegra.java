package PRESENTACION;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import NEGOCIO.Cola;
import NEGOCIO.Nodo;
import NEGOCIO.Reglas;

public class GraficadoNegra extends JPanel {

	public Cola cl;
	/**
	 * Create the panel.
	 */
	public GraficadoNegra(Cola cl) 
	{
		this.cl=cl;
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2=(Graphics2D)g;
		int px=0;
		int py=0;
		Nodo aux=cl.Primero;
		while (aux!=null) 
		{
			if (Integer.parseInt(aux.Ele.toString())==Reglas.FICHA_NEGRA) 
			{
				Image imgFichaNegra=Toolkit.getDefaultToolkit().getImage("fichanegra.png");
				g2.drawImage(imgFichaNegra,px,py,40,40,this);
			}
			if (Integer.parseInt(aux.Ele.toString())==Reglas.REINA_NEGRA) 
			{
				Image imgFichaReinaNegra=Toolkit.getDefaultToolkit().getImage("fichareynanegra.png");
				g2.drawImage(imgFichaReinaNegra,px,py,40,40,this);
			}
			px=px+50;
			aux=aux.RefSgte;
		}
	}

}
