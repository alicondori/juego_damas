package NEGOCIO;

import java.io.Serializable;

public class JuegoDamas implements Serializable{
	
	public Tablero tb;
	public Jugador jg1;
	public Jugador jg2;
	
	public Cola colaNegra;
	public Cola colaBlanca;
	public int turno;

	public JuegoDamas() 
	{
		tb=new Tablero();
		jg1=new Jugador();
		jg2=new Jugador();
		
		colaNegra=new Cola();
		colaBlanca=new Cola();
		
		
		//POR DEFOULT EL JUEGO EMPEZARA POR EL NEGRO
		turno=Reglas.COLOR_NEGRO;
		
	}
	//METODOS
	
	//CAMBIAR DE TURNO
	public void cambiarTurno()
	{
		if (turno==Reglas.COLOR_NEGRO) 
		{
			turno=Reglas.COLOR_BLANCA;
		}
		else
		{
			turno=Reglas.COLOR_NEGRO;
		}
	}

}
