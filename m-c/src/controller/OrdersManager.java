package controller;

import java.io.Serializable;
import java.util.ArrayList;

import persistencia.Serializador;

import model.Order;

public class OrdersManager implements Serializable {

	private static final long serialVersionUID = 1L;
	private static OrdersManager _instance = Serializador.getData()
			.get_orderManager();
	private ArrayList<Order> _pendingOrders;
	private ArrayList<Order> _ordersToProgram;

	private OrdersManager() {
		_pendingOrders = new ArrayList<Order>();
		_ordersToProgram = new ArrayList<Order>();
	}

	public static OrdersManager getInstanse() {
		if (_instance == null)
			_instance = new OrdersManager();
		return _instance;
	}

	public void addPendingOrders(Order order) {
		_pendingOrders.add(order);
	}

	public void removePendingOrders(Order order) {
		_pendingOrders.remove(order);
	}

	public void addOrdersToProgram(Order order) {
		_ordersToProgram.add(order);
	}

	public void removeOrdersToProgram(Order order) {
		_ordersToProgram.remove(order);
	}

	public void removeAllOrdersToProgram() {
		_ordersToProgram = new ArrayList<Order>();
	}

	public void removeAllPendingOrders() {
		_pendingOrders = new ArrayList<Order>();
	}

	public ArrayList<Order> get_pendingOrders() {
		return _pendingOrders;
	}

	public void set_pendingOrders(ArrayList<Order> _pendingOrders) {
		this._pendingOrders = _pendingOrders;
	}

	public ArrayList<Order> get_ordersToProgram() {
		return _ordersToProgram;
	}

	public void set_ordersToProgram(ArrayList<Order> _ordersToProgram) {
		this._ordersToProgram = _ordersToProgram;
	}

	public Order[] getArrayOrdersToProgram() {
		Order[] ret = new Order[_ordersToProgram.size()];
		for (int i = 0; i < _ordersToProgram.size(); i++) {
			ret[i] = _ordersToProgram.get(i);
		}
		return ret;
	}

	public Order[] getArrayPendingOrders() {
		Order[] ret = new Order[_pendingOrders.size()];
		for (int i = 0; i < _pendingOrders.size(); i++) {
			ret[i] = _pendingOrders.get(i);
		}
		return ret;
	}

	public void deletePendingOrder(Order selectedValue) {
		_pendingOrders.remove(selectedValue);
	}

	public void deleteOrderToProgram(Order order) {
		_ordersToProgram.remove(order);
	}

	@Override
	public String toString() {
		return "OrdersManager [_pendingOrders=" + _pendingOrders
				+ ", _ordersToProgram=" + _ordersToProgram + "]";
	}

}
