package model;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private String _customer;
	private Location _location;
	private String _deliveryDate;

	public Order(String customer, Location location, String deliveryDate) {
		_customer = customer;
		_location = location;
		_deliveryDate = deliveryDate;
	}

	public Order(String customer, Location location) {
		_customer = customer;
		_location = location;
	}

	public double distance(Order other) {
		return this._location.distance(other._location);
	}

	public String get_customer() {
		return _customer;
	}

	public void set_customer(String _customer) {
		this._customer = _customer;
	}

	public Location get_location() {
		return _location;
	}

	public void set_location(Location _location) {
		this._location = _location;
	}

	public String get_deliveryDate() {
		return _deliveryDate;
	}

	public void set_deliveryDate(String _deliveryDate) {
		this._deliveryDate = _deliveryDate;
	}

	@Override
	public String toString() {
		return _customer + "  -  " + _location + "  -  " + _deliveryDate;
	}

}
