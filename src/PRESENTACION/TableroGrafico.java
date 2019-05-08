package PRESENTACION;


import java.awt.*;
import javax.swing.JPanel;

import NEGOCIO.Reglas;
import NEGOCIO.Tablero;

public class TableroGrafico extends JPanel {

	//CREAMOS UN CAMPO PARA QUE HAGARE IMAGEN
	public Image imgTablero;
	
	
	//CREAMOS UN TABLERO PARA RECIVIR EL TAB
	public Tablero tb;
	
	/**
	 * Create the panel.
	 */
	public TableroGrafico(Tablero tb) {
		this.tb=tb;
	}
	
	@Override
	public void paint(Graphics g) 
	{
		
		super.paint(g);
		Graphics2D g2=(Graphics2D)g;
		
		//PINTA EL TABLERO
		imgTablero=Toolkit.getDefaultToolkit().getImage("TableroFinal.jpg");
		g2.drawImage(imgTablero, 0, 0,500,500,this);
		
		//PINTA LA FICHAS "DACUERDO A LA MATRIZ"
		int py=9;
		for (int f = 0; f < 8; f++) 
		{
			int px=9;
			for (int c = 0; c < 8; c++) 
			{
				if (tb.mtz.obtenerEle(f, c)==Reglas.FICHA_NEGRA) 
				{
					Image imgFichaNegra=Toolkit.getDefaultToolkit().getImage("fichanegra.png");
					g2.drawImage(imgFichaNegra,px,py,46,46,this);

				}
				if (tb.mtz.obtenerEle(f, c)==Reglas.FICHA_BLANCA) 
				{
					Image imgFichaBlanca=Toolkit.getDefaultToolkit().getImage("ficharoja.png");
					g2.drawImage(imgFichaBlanca,px,py,46,46, this);
				}
				if (tb.mtz.obtenerEle(f, c)==Reglas.REINA_NEGRA) 
				{
					Image imgFichaReinaNegra=Toolkit.getDefaultToolkit().getImage("fichareynanegra.png");
					g2.drawImage(imgFichaReinaNegra,px,py,46,46,this);
				}
				if (tb.mtz.obtenerEle(f, c)==Reglas.REINA_BLANCA) 
				{
					Image imgFichaReinaBlanca=Toolkit.getDefaultToolkit().getImage("fichareynaroja.png");
					g2.drawImage(imgFichaReinaBlanca,px,py,46,46,this);
				}
				
				px=px+62;
			}
			py=py+62;
		}
	}
}
