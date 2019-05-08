package NEGOCIO;

import java.io.Serializable;

public class Tablero implements Serializable{
	
	public MatrizBitwase mtz;//LLAMANDO A LA MATRIZ
	
	
	//CONSTRUCTOR
	public Tablero() 
	{
		mtz=new MatrizBitwase(8,8);//PASANDO LA DIMESION DE LA MATRIZ
		
		for (int f = 0; f < mtz.Filas; f++) 
		{
			for (int c = 0; c < mtz.Columnas; c++) 
			{
				//LLENANDO LA MATRIZ
				if (f<3 && (f%2)==0 && (c%2)==0) 
				{
					mtz.asignarEle(Reglas.FICHA_BLANCA, f, c);
				}
				if (f==1 && (c%2)!=0) 
				{
					mtz.asignarEle(Reglas.FICHA_BLANCA, f, c);
				}
				if (f>4 && (f%2)!=0 && (c%2!=0)) 
				{
					mtz.asignarEle(Reglas.FICHA_NEGRA, f, c);
				}
				if (f==6 && (c%2)==0) 
				{
					mtz.asignarEle(Reglas.FICHA_NEGRA, f, c);
				}
			}
		}
	}
	//METODOS

}
