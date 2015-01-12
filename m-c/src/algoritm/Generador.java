package algoritm;

import java.util.LinkedList;

public class Generador
{
	// Instancia
	private Instancia instancia;
	
	// Cantidad de ciudades
	private int n;

	// El constructor toma la cantidad de ciudades
	public Generador(Instancia inst)
	{
		instancia = inst;
		n = instancia.getCiudades();
	}
	
	// Genera todos los recorridos
	public LinkedList<Recorrido> generar()
	{
		LinkedList<Recorrido> ret = new LinkedList<Recorrido>();
		
		Recorrido recorrido = new Recorrido(instancia);
		
		
		completar(recorrido, ret);
		return ret;
	}

	// Proceso recursivo
	private void completar(Recorrido recorrido, LinkedList<Recorrido> ret)
	{
		// Caso base: El recorrido contiene todas las ciudades
		if( recorrido.size() == n )
		{
			ret.add(recorrido.clonar());
			return;
		}

		// Caso recursivo: Agrego todas las posibles ciudades al recorrido
		for(int i=0; i<n; ++i)
		{
			if( recorrido.contiene(i) == false )
			{
				recorrido.agregar(i);
				completar(recorrido, ret);
				recorrido.eliminarUltima(); // La ultima es la i
			}
		}
	}
}
