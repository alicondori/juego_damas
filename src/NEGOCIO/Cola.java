package NEGOCIO;

import java.io.Serializable;

public class Cola implements Serializable{

	public Nodo Primero;
	public Nodo Ultimo;
	public Cola() 
	{
		Primero=null;
		Ultimo=null;
	}
	public void encolar(Nodo nd)
	{
		if (Primero==null) 
		{
			Primero=nd;
			Ultimo=nd;
		}
		else 
		{
			Ultimo.RefSgte=nd;
			Ultimo=nd;
		}
	}
	public int cantElementos()
	{
		Nodo aux=Primero;
		int cont=0;
		while (aux!=null) 
		{
			aux=aux.RefSgte;
			cont++;
		}
		return cont;
	}

}
