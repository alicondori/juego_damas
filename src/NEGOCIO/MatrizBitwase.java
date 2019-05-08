package NEGOCIO;

import java.io.Serializable;

public class MatrizBitwase implements Serializable{
	
	public int[] vecFilas;
	public int Filas;
	public int Columnas;

	public MatrizBitwase(int filas, int columnas) 
	{
		vecFilas =new int[filas];
		Filas=filas;
		Columnas=columnas;
	}
	public int obtenerEle(int f,int c)
	{
		int eleFila=vecFilas[f];
		
		eleFila=eleFila<<(29-(c*3));
		eleFila=eleFila>>>29;
		return eleFila;
	}
	public void asignarEle(int ele,int f,int c)
	{
		/*LIMPIAR*/
		
		
		int eleFila=vecFilas[f];//PASANDO LA FILA COMPLETA DEL VECTOR A LA VARIABLE
		
		int mk=7;//MASCARA
		mk=mk<<(c*3);
		mk=~mk;
		
		eleFila=eleFila & mk;//LIMPIANDO LA COLUMNA PARA LUEGO AGREGAR EL NUM
		
		
		
		ele=ele<<(c*3);//BARRIDO DEL VALOR ELE Y ASIGNANDO A ELE, ALGO ASI:  0000000--011--000000
		
		eleFila= eleFila | ele;/*COMBINANDO VALOR CON ELEFILA  00011101--101--11011011
		*-----------------------------------------------------   00000000--011--00000000*/
		vecFilas[f]=eleFila;//PASANDO VALOR
	}

}
