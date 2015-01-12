package algoritm;

import java.util.ArrayList;
import java.util.Scanner;

import model.Order;

public class Algoritmo {

	/**
	 * Método principal recibe como colaborador externo una lista de pedidos y
	 * la devuelve ordenada de manera de ser la mejor opción a recorrer.
	 * 
	 * @param orders
	 * @return ArrayList<Order>
	 */
	public static ArrayList<Order> getBetterPath(ArrayList<Order> orders) {
		Recorrido optimo = theOptimal(orders);
		return pathToList(optimo, orders);
	}

	/**
	 * Genera una instancia utilizando como colaborador externo una lista de
	 * pedidos y la resuelve retornando el recorrido optimo.
	 * 
	 * @param orders
	 *            pedidos a generar optimo.
	 * @return recorrido optimo.
	 */
	private static Recorrido theOptimal(ArrayList<Order> orders) {
		Instancia instancia = new Instancia(orders);
		return resolver(instancia);
	}

	/**
	 * Genera fuerza bruta con todos los posibles recorridos de una instancia
	 * determinada y devuelve el mejor.
	 * 
	 * @param instancia
	 * @return Recorrido
	 */
	public static Recorrido resolver(Instancia instancia) {
		Generador generador = new Generador(instancia);
		Recorrido elMejor = null;

		for (Recorrido actual : generador.generar())
			if (elMejor == null || actual.distancia() < elMejor.distancia())
				elMejor = actual;
		return elMejor;
	}

	/**
	 * Genera una lista de pedidos de la manera que deben ser recorridos.
	 * 
	 * @param elOptimo
	 *            recorrido que indica cómo deben ser recorrido los pedidos.
	 * @param orders
	 *            pedidos a ordenar.
	 * @return ArrayList<Order>
	 */
	@SuppressWarnings("resource")
	private static ArrayList<Order> pathToList(Recorrido elOptimo,
			ArrayList<Order> orders) {
		ArrayList<Order> ret = new ArrayList<Order>();
		String fis = elOptimo.toString();
		Scanner scanner = new Scanner(fis);
		while (scanner.hasNextInt())
			ret.add(orders.get(scanner.nextInt()));
		return ret;
	}
}
