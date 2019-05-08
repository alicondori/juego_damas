package NEGOCIO;

import java.io.Serializable;

public class Nodo implements Serializable{

	public Object Ele;
	public Nodo RefSgte;
	//CONSTRUCTOR
	public Nodo(Object ele) 
	{
		Ele=ele;
		RefSgte=null;
	}

}
