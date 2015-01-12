package controller;

import java.io.Serializable;
import java.util.ArrayList;

import persistencia.Serializador;

import model.Delivery;
import model.Location;
import model.Order;

public class DeliveryManager implements Serializable {

	private static final long serialVersionUID = 1L;
	public static DeliveryManager _instance = Serializador.getData()
			.get_deliveryManager();
	private Delivery _actualDelivery;
	private Location _startingPoint;

	private DeliveryManager() {
		_actualDelivery = new Delivery();
		_startingPoint = null;
	}

	public static DeliveryManager getInstance() {
		if (_instance == null)
			_instance = new DeliveryManager();
		return _instance;
	}

	public ArrayList<model.Order> OptimalRoute() {
		_actualDelivery.updateDelivery();
		return _actualDelivery.getLeastPath();
	}

	public ArrayList<model.Location> OptimoRecorrido() {
		ArrayList<model.Location> ret = new ArrayList<model.Location>();
		for (Order o : OptimalRoute())
			ret.add(o.get_location());
		return ret;
	}

	public Delivery get_actualDelivery() {
		return _actualDelivery;
	}

	public void set_actualDelivery(Delivery _actualDelivery) {
		this._actualDelivery = _actualDelivery;
	}

	public Location get_startingPoint() {
		return _startingPoint;
	}

	public void set_startingPoint(Location location) {
		_startingPoint = location;
	}

	public boolean startingPointNoNull() {
		return _startingPoint != null;
	}

	public void loadDelivery(ArrayList<Order> orders) {
		_actualDelivery.set_orders(orders);
	}

	@Override
	public String toString() {
		return "DeliveryManager [" + _actualDelivery + _startingPoint + "]";
	}

	public void loadStartingPoint() {
		_actualDelivery.get_localOrder().set_location(get_startingPoint());
	}
}
