package algoritm;

import java.util.ArrayList;

// Representa un recorrido por algunas o todas las ciudades
public class Recorrido
{
	// Instancia asociada
	private Instancia instancia;
	
	// Las ciudades del recorrido
	private ArrayList<Integer> ciudades;
	
	// Cada recorrido está asociado a una instancia
	public Recorrido(Instancia inst)
	{
		instancia = inst;
		ciudades = new ArrayList<Integer>();
	}
	
	// Agrega una ciudad al final del recorrido
	public void agregar(int i)
	{
		ciudades.add(i);
	}

	// Elimina la ultima ciudad del recorrido
	public void eliminarUltima()
	{
		ciudades.remove( ciudades.size()-1 );
	}

	// Determina si la ciudad está en el recorrido
	public boolean contiene(int i)
	{
		return ciudades.contains(i);
	}

	// Cantidad de ciudades en el recorrido
	public int size()
	{
		return ciudades.size();
	}
	
	// toString
	@Override public String toString()
	{
		String ret = "";
		for(Integer x: ciudades)
			ret += x + " ";
		
		return ret + "- Dist: " + this.distancia();
	}

	// Clonacion
	@SuppressWarnings("unchecked")
	public Recorrido clonar()
	{
		Recorrido clon = new Recorrido(instancia);
		clon.ciudades = (ArrayList<Integer>) ciudades.clone();

		return clon;
	}

	// Distancia total
	public double distancia()
	{
		double ret = 0;
		for(int i=0; i<ciudades.size()-1; ++i)
		{
			int origen = ciudades.get(i);
			int destino = ciudades.get(i+1);
			
			ret += instancia.getDistancia(origen, destino);
		}
		
		int ultima = ciudades.get( ciudades.size()-1 );
		int primera = ciudades.get(0);
		
		ret += instancia.getDistancia(ultima, primera);

		return ret;
	}
}
