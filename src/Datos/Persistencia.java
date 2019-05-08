package Datos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Persistencia implements Serializable{

	public Persistencia() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public void guardarObjeto(Object objeto )// OUTPUT=SALIDA
	{
		try 
		{
			//FLUJO DE ARCHIVO EN MEMORIA QUE SE CONECTA CON DIRECCION DONDE SE CREARA EL ARCHIVO .TXT O .BIN
			FileOutputStream flujoArchivo=new FileOutputStream("D:\\juegoDamas.txt");
			
			//CREAR EL ADM. OBJETOS PARA TRABAJAR CON EL ARCHIVO
			ObjectOutputStream flujoObjeto=new ObjectOutputStream(flujoArchivo);
			
			//ESCRIBE EL OBJETO EN DISCO
			flujoObjeto.writeObject(objeto);
			
			//CIERRA EL FLUJO DE OBJETO
			flujoObjeto.close();
			
			//CIERRA EL FLUJO DE ARCHIVO
			flujoArchivo.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public Object recuperarObjeto()//INPUT = ENTRADA
	{
		try 
		{
			//CREA FLUJO DE ENTRADA EN MEMORIA QUE SE CONECTA CON DIRECCION DEL ARCHIVO
			FileInputStream flujoArchivo=new FileInputStream("D:\\juegoDamas.txt");
			
			//CREAR EL ADM. DE OBJETOS DE ENTRADA PARA LEER EL ARCHIVO EXISTENTE
			ObjectInputStream flujoObjeto=new ObjectInputStream(flujoArchivo);
			
			//LEE Y RECUPERA EL OBJETO DEL DISCO DURO
			Object objeto= flujoObjeto.readObject();
			
			//CIERRA EL FLUJO DE OBJETO
			flujoObjeto.close();
			
			//CIERRA EL FLUJO DE ARCHIVO
			flujoArchivo.close();
			
			return objeto;
		} 
		catch (Exception e) 
		{
			return null;
		}
		
	}

}
