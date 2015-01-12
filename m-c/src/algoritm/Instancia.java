package algoritm;

import java.util.List;

import model.Order;

public class Instancia {
	// Cantidad de ciudades
	private int n;

	// Matriz de distancias entre las ciudades
	private double[][] dist;

	// El constructor crea una matriz de distancia a partir de la distancia de
	// los pedidos
	public Instancia(List<Order> orders) {
		n = orders.size();
		dist = new double[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				dist[i][j] = dist[j][i] = orders.get(i).distance(orders.get(j));
			}

	}

	// Cantidad de ciudades
	public int getCiudades() {
		return n;
	}

	// Distancia entre dos ciudades
	public double getDistancia(int i, int j) {
		if (i < 0 || i >= n || j < 0 || j >= n)
			throw new IllegalArgumentException();
		return dist[i][j];
	}
}
