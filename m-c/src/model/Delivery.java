package model;

import java.io.Serializable;
import java.util.ArrayList;

import algoritm.Algoritmo;

public class Delivery implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Order> _orders;
	private Order _localOrder;// Representa al camión del delivery.
	private ArrayList<Order> _minimumPath;

	public Delivery() {
		_orders = new ArrayList<Order>();
		_localOrder = new Order("Local", null);
		_minimumPath = new ArrayList<Order>();
	}

	public void updateDelivery() {
		this.addOrder(get_localOrder());
		this.set_minimumPath(computeLeastPath());
		this.removeOrder(get_localOrder());

	}

	public ArrayList<Order> computeLeastPath() {
		return Algoritmo.getBetterPath(get_orders());
	}

	public Order get_localOrder() {
		return _localOrder;
	}

	public void set_localOrder(Order _localOrder) {
		this._localOrder = _localOrder;
	}

	public void addOrder(Order order) {
		_orders.add(order);
	}

	public void removeOrder(Order order) {
		_orders.remove(order);
	}

	public ArrayList<Order> get_orders() {
		return _orders;
	}

	public void set_orders(ArrayList<Order> _orders) {
		this._orders = _orders;
	}

	public ArrayList<Order> getLeastPath() {
		// return Algoritmo.getBetterPath(_orders);
		return _minimumPath;
	}

	public ArrayList<Order> get_minimumPath() {
		return _minimumPath;
	}

	public void set_minimumPath(ArrayList<Order> _minimumPath) {
		this._minimumPath = _minimumPath;
	}

	@Override
	public String toString() {
		return "Delivery [_orders=" + _orders + ", _localOrder=" + _localOrder
				+ "]";
	}

}
