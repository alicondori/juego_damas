package NEGOCIO;

import java.io.Serializable;

public class Reglas implements Serializable{
	
	//ESTO ES LOS JUGADORES
	public static int COLOR_NEGRO=1;
	public static int COLOR_BLANCA=2;
	
	//ESTO ES PARA LAS FICHAS
	public static int VACIO=0;
	public static int FICHA_NEGRA=1;
	public static int FICHA_BLANCA=2;
	public static int REINA_NEGRA=3;
	public static int REINA_BLANCA=4;
	
	public Reglas() 
	{
		//NADA
	}
	
	//METODOS
	public static boolean movPermitido(int fp,int cp,int fl,int cl,MatrizBitwase mtz,int turno)
	{
		if (mtz.obtenerEle(fp, cp)==turno) /*VERIFICA SI LA FICAH MOVIDA ES IGUAL AL TURNO*/
		{
			if (mtz.obtenerEle(fp, cp)!=Reglas.VACIO && mtz.obtenerEle(fl, cl)==Reglas.VACIO &&
					fp-1==fl && (cp-1==cl || cp+1==cl) && mtz.obtenerEle(fp, cp)==Reglas.FICHA_NEGRA) /*PARA EL NEGRO*/
			{
				return true;
			}
			if (mtz.obtenerEle(fp, cp)!=Reglas.VACIO && mtz.obtenerEle(fl, cl)==Reglas.VACIO &&
					fp+1==fl && (cp-1==cl || cp+1==cl)&& mtz.obtenerEle(fp, cp)==Reglas.FICHA_BLANCA) /*PARA EL BLANCO*/
			{
				return true;
			}
		}
		return false;
		
		
	}
	public static boolean movReinaPermitido(int fp, int cp,int fl,int cl,MatrizBitwase mtz,int turno)
	{
		int turnito=0;
		if (mtz.obtenerEle(fp, cp)==3) 
		{
			turnito=1;
		}
		if (mtz.obtenerEle(fp, cp)==4) 
		{
			turnito=2;
		}
		if (turno==turnito) //VALIDANDO QUE LA REINA SEA AL TURNO AL QUE PERTENECE
		{
			if ((mtz.obtenerEle(fp, cp)==Reglas.REINA_BLANCA || mtz.obtenerEle(fp, cp)==Reglas.REINA_NEGRA)&&
					mtz.obtenerEle(fl, cl)==Reglas.VACIO && estaEnRango(fp, fl, cp, cl)) 
			{
				
				if (fp>fl) 
				{
					//HACIA ARRIBA----VERIFICANDO QUE LAS CASILLAS ESTAN VACIAS
					int c=cp;
					for (int f = fp-1; f >=fl; f--) 
					{
						if (cp>cl) 
						{
							c=c-1;//HACIA ARRIBA IZQUIERDA <--
						}
						else
						{
							c=c+1;//HACIA ARRIBA DERECHA -->
						}
						if (mtz.obtenerEle(f, c)!=0) 
						{
							f=fl+1;//PARA QUE SE SALGA DEL FOR
							return false;
						}
					}
				}
				else
				{
					//HACIA ABAJO----VERIFICANDO QUE LAS CASILLAS ESTAN VACIAS
					int cAbajo=cp;
					for (int f = fp+1; f <=fl; f++) 
					{
						if (cp>cl) 
						{
							cAbajo=cAbajo-1;//HACIA ABAJO IZQUIERDA <--
						}
						else
						{
							cAbajo=cAbajo+1;//HACIA ABAJO DERECHA -->
						}
						if (mtz.obtenerEle(f, cAbajo)!=0) 
						{
							f=fl+1;
							return false;
						}
					}
				}
				return true;//SI NO HAY NINGUN PROBLEMA ARRIBA RETORNO MOV POSITIVO
			}
		}
		return false;
	}
	public static boolean estaEnRango(int fp,int fl,int cp,int cl)
	{
		if (fp>fl) 
		{
			//HACIA ARRIBA----VERIFICANDO QUE LAS CASILLAS ESTAN VACIAS
			int c=cp;
			for (int f = fp-1; f >=fl; f--) 
			{
				if (cp>cl) 
				{
					c=c-1;//HACIA ARRIBA IZQUIERDA <--
				}
				else
				{
					c=c+1;//HACIA ARRIBA DERECHA -->
				}
				if (f==fl && c==cl)//VERIFICANDO QUE EL PUNTO FL Y CL ESTEN DENTRO DEL RANGO 
				{
					return true;
				}
			}
		}
		else
		{
			//HACIA ABAJO----VERIFICANDO QUE LAS CASILLAS ESTAN VACIAS
			int cAbajo=cp;
			for (int f = fp+1; f <=fl; f++) 
			{
				if (cp>cl) 
				{
					cAbajo=cAbajo-1;//HACIA ABAJO IZQUIERDA <--
				}
				else
				{
					cAbajo=cAbajo+1;//HACIA ABAJO DERECHA -->
				}
				if (f==fl && cAbajo==cl) 
				{
					return true;
				}
			}
		}
		return false;
	}
	public static int cantidadFichasEnRango(int fp,int fl,int cp,int cl,MatrizBitwase mtz1)
	{
		int cont=0;//CONTANDO LA CANTIDAD DE FICHAS
		if (fp>fl) 
		{
			//HACIA ARRIBA----VERIFICANDO QUE LAS CASILLAS ESTAN VACIAS
			int c=cp;
			for (int f = fp-1; f >=fl; f--) 
			{
				if (cp>cl) 
				{
					c=c-1;//HACIA ARRIBA IZQUIERDA <--
				}
				else
				{
					c=c+1;//HACIA ARRIBA DERECHA -->
				}
				if (mtz1.obtenerEle(f, c)!=0)//	CHECANDO CUANTAS FICHAS HAY
				{
					cont++;
				}
			}
		}
		else
		{
			//HACIA ABAJO----VERIFICANDO QUE LAS CASILLAS ESTAN VACIAS
			int cAbajo=cp;
			for (int f = fp+1; f <=fl; f++) 
			{
				if (cp>cl) 
				{
					cAbajo=cAbajo-1;//HACIA ABAJO IZQUIERDA <--
				}
				else
				{
					cAbajo=cAbajo+1;//HACIA ABAJO DERECHA -->
				}
				if (mtz1.obtenerEle(f, cAbajo)!=0) 
				{
					cont++;
				}
			}
		}
		return cont;
	}
	public static boolean comidaPermitida(int fp,int cp,int fl,int cl,MatrizBitwase mtz,int turno,
			Cola colitaNegra,Cola colitaBlanca)
	{
		Nodo nd;
		if (mtz.obtenerEle(fp, cp)==turno) /*VERIFICA SI LA FICHA MOVIDA ES IGUAL AL TURNO*/
		{
			//PARA EL NEGRO
			if (mtz.obtenerEle(fp, cp)!=Reglas.VACIO && mtz.obtenerEle(fl, cl)==Reglas.VACIO &&
					fp-2==fl && mtz.obtenerEle(fp, cp)==Reglas.FICHA_NEGRA) /*PARA EL NEGRO*/
			{
				if (cp-2==cl &&
						(mtz.obtenerEle((fp-1), (cp-1))==Reglas.FICHA_BLANCA ||
						mtz.obtenerEle((fp-1), (cp-1))==Reglas.REINA_BLANCA))/*MOV IZQUIERDA*/ 
				{
					//SALVANDO EL ELEMENTO
					nd=new Nodo(mtz.obtenerEle(fp-1, cp-1));
					colitaBlanca.encolar(nd);
					
					mtz.asignarEle(Reglas.VACIO, (fp-1), (cp-1));
					return true;
				}
				if (cp+2==cl &&
						(mtz.obtenerEle((fp-1), (cp+1))==Reglas.FICHA_BLANCA ||
						mtz.obtenerEle((fp-1), (cp+1))==Reglas.REINA_BLANCA))/*MOV DERECHA*/ 
				{
					//SALVANDO EL ELEMENTO
					nd=new Nodo(mtz.obtenerEle(fp-1, cp+1));
					colitaBlanca.encolar(nd);
					
					mtz.asignarEle(Reglas.VACIO, (fp-1), (cp+1));
					return true;
				}
				return false;
			}
			//PARA EL BLANCA
			if (mtz.obtenerEle(fp, cp)!=Reglas.VACIO && mtz.obtenerEle(fl, cl)==Reglas.VACIO &&
					fp+2==fl && mtz.obtenerEle(fp, cp)==Reglas.FICHA_BLANCA) /*PARA EL BLANCO*/
			{
				if (cp-2==cl &&
						(mtz.obtenerEle((fp+1), (cp-1))==Reglas.FICHA_NEGRA ||
						mtz.obtenerEle((fp+1), (cp-1))==Reglas.REINA_NEGRA)) /*MOV IZQUIERDA*/ 
				{
					//SALVANDO EL ELEMENTO
					nd=new Nodo(mtz.obtenerEle(fp+1, cp-1));
					colitaNegra.encolar(nd);
					
					mtz.asignarEle(Reglas.VACIO, (fp+1), (cp-1));
					return true;
				}
				if (cp+2==cl && 
						(mtz.obtenerEle((fp+1), (cp+1))==Reglas.FICHA_NEGRA ||
						mtz.obtenerEle((fp+1), (cp+1))==Reglas.REINA_NEGRA)) /*MOV DERECHA*/ 
				{
					//SALVANDO EL ELEMENTO
					nd=new Nodo(mtz.obtenerEle(fp+1, cp+1));
					colitaNegra.encolar(nd);
					
					mtz.asignarEle(Reglas.VACIO, (fp+1), (cp+1));
					return true;
				}
				return false;
			}
		}
		return false;
	}
	public static boolean comidaReinaPermitida(int fp, int cp,int fl,int cl,MatrizBitwase mtz,int turno,
			Cola colitaNegra,Cola colitaBlanca)
	{
		Nodo nd;
		
		int turnito=0;
		if (mtz.obtenerEle(fp, cp)==3) 
		{
			turnito=1;
		}
		if (mtz.obtenerEle(fp, cp)==4) 
		{
			turnito=2;
		}
		if (turno==turnito) //VALIDANDO QUE LA REINA SEA AL TURNO AL QUE PERTENECE
		{
			if ((mtz.obtenerEle(fp, cp)==Reglas.REINA_BLANCA || mtz.obtenerEle(fp, cp)==Reglas.REINA_NEGRA)&&
					mtz.obtenerEle(fl, cl)==Reglas.VACIO && estaEnRango(fp, fl, cp, cl)) 
			{
				if (cantidadFichasEnRango(fp, fl, cp, cl, mtz)==1) //PARA QUE COMA DE UNO EN UNO
				{
					if (fp>fl) 
					{
						//HACIA ARRIBA----VERIFICANDO QUE LAS CASILLAS ESTAN VACIAS
						int c=cp;
						for (int f = fp-1; f >=fl; f--) 
						{
							if (cp>cl) 
							{
								c=c-1;//HACIA ARRIBA IZQUIERDA <--
							}
							else
							{
								c=c+1;//HACIA ARRIBA DERECHA -->
							}
							//PARA COMER LA FICHA Y EN SU LUGAR PONER VACIO
							if ((mtz.obtenerEle(f, c)==Reglas.FICHA_NEGRA || mtz.obtenerEle(f, c)==Reglas.REINA_NEGRA) &&
									mtz.obtenerEle(fp, cp)==Reglas.REINA_BLANCA )//PARA BLANCA 
							{
								//SALVANDO EL ELEMENTO
								nd =new Nodo(mtz.obtenerEle(f, c));
								colitaNegra.encolar(nd);
								
								mtz.asignarEle(Reglas.VACIO, f, c);
								return true;
							}
							if ((mtz.obtenerEle(f, c)==Reglas.FICHA_BLANCA || mtz.obtenerEle(f, c)==Reglas.REINA_BLANCA) &&
									mtz.obtenerEle(fp, cp)==Reglas.REINA_NEGRA )//PARA LA NEGRA 
							{
								//SALVANDO EL ELEMENTO
								nd =new Nodo(mtz.obtenerEle(f, c));
								colitaBlanca.encolar(nd);
								
								mtz.asignarEle(Reglas.VACIO, f, c);
								return true;
							}
						}
					}
					else
					{
						//HACIA ABAJO----VERIFICANDO QUE LAS CASILLAS ESTAN VACIAS
						int cAbajo=cp;
						for (int f = fp+1; f <=fl; f++) 
						{
							if (cp>cl) 
							{
								cAbajo=cAbajo-1;//HACIA ABAJO IZQUIERDA <--
							}
							else
							{
								cAbajo=cAbajo+1;//HACIA ABAJO DERECHA -->
							}
							//PARA COMER LA FICHA Y EN SU LUGAR PONER VACIO
							if ((mtz.obtenerEle(f, cAbajo)==Reglas.FICHA_NEGRA || mtz.obtenerEle(f, cAbajo)==Reglas.REINA_NEGRA) &&
									mtz.obtenerEle(fp, cp)==Reglas.REINA_BLANCA )//PARA BLANCA 
							{
								//SALVANDO EL ELEMENTO
								nd =new Nodo(mtz.obtenerEle(f, cAbajo));
								colitaNegra.encolar(nd);
								
								mtz.asignarEle(Reglas.VACIO, f, cAbajo);
								return true;
							}
							if ((mtz.obtenerEle(f, cAbajo)==Reglas.FICHA_BLANCA || mtz.obtenerEle(f, cAbajo)==Reglas.REINA_BLANCA) &&
									mtz.obtenerEle(fp, cp)==Reglas.REINA_NEGRA )//PARA LA NEGRA 
							{
								//SALVANDO EL ELEMENTO
								nd =new Nodo(mtz.obtenerEle(f, cAbajo));
								colitaBlanca.encolar(nd);
								
								mtz.asignarEle(Reglas.VACIO, f, cAbajo);
								return true;
							}
						}
					}
				}
				return false;//SI NO HAY NINGUN PROBLEMA ARRIBA RETORNO MOV POSITIVO
			}
		}
		return false;
	}
	public static void coronar(int fl,int cl,MatrizBitwase mtz)
	{
		if (fl==0 && (cl%2)==0 && mtz.obtenerEle(fl, cl)==Reglas.FICHA_NEGRA) 
		{
			mtz.asignarEle(Reglas.REINA_NEGRA, fl, cl);
		}
		if (fl==7 && (cl%2)!=0 && mtz.obtenerEle(fl, cl)==Reglas.FICHA_BLANCA) 
		{
			mtz.asignarEle(Reglas.REINA_BLANCA, fl, cl);
		}
	}

}
