package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import persistencia.Serializador;

import model.Location;
import model.Order;

public class Controller implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Controller _getInstance = Serializador.getData();
	private OrdersManager _orderManager;
	private DeliveryManager _deliveryManager;

	private Controller() {
		_orderManager = OrdersManager.getInstanse();
		_deliveryManager = DeliveryManager.getInstance();
	}

	public static Controller getInstance() {
		if (_getInstance == null)
			return new Controller();
		else {
			return _getInstance;
		}
	}

	public void addToPendingOrders(String customerOrder, String locationOrder,
			String dateOrder) {

		Order order = new Order(customerOrder, stringToLocation(locationOrder),
				dateOrder);
		_orderManager.addPendingOrders(order);

	}

	public void addToPendingOrders(Order order) {
		_orderManager.addPendingOrders(order);
	}

	public void deletePendingOrder(Order selectedValue) {
		_orderManager.deletePendingOrder(selectedValue);
	}

	public void addOrderToProgram(Order order) {
		_orderManager.addOrdersToProgram(order);

	}

	public void deleteOrderToProgram(Order order) {
		_orderManager.deleteOrderToProgram(order);
	}

	public Order[] getArrayOrdersToProgram() {
		return _orderManager.getArrayOrdersToProgram();
	}

	public Order[] getArrayPendingOrders() {
		return _orderManager.getArrayPendingOrders();
	}

	public MapPolygonImpl getMapPolygonImpl() {
		MapPolygonImpl poly = new MapPolygonImpl(
				_deliveryManager.OptimoRecorrido());
		return poly;
	}

	public void changeStartingPoint(String location) {
		_deliveryManager.set_startingPoint(stringToLocation(location));
	}

	public Location getStartingPoint() {
		return _deliveryManager.get_startingPoint();
	}

	@SuppressWarnings("resource")
	private static Location stringToLocation(String location) {
		Scanner scan = new Scanner(location);
		double _long = Double.parseDouble(scan.next());
		double _lat = Double.parseDouble(scan.next());
		Location locat = new Location(_lat, _long);
		return locat;
	}

	public Order[] setListOrders() {
		return new Order[0];
	}

	public boolean startingPointNoNull() {
		return _deliveryManager.startingPointNoNull();
	}

	public void loadDelivery(Order[] orders) {
		ArrayList<Order> list = new ArrayList<Order>();
		for (int i = 0; i < orders.length; i++)
			list.add(orders[i]);
		_deliveryManager.loadDelivery(list);

	}

	public void loadStartingPoint() {
		_deliveryManager.loadStartingPoint();
	}

	public OrdersManager get_orderManager() {
		return _orderManager;
	}

	public void set_orderManager(OrdersManager _orderManager) {
		this._orderManager = _orderManager;
	}

	public DeliveryManager get_deliveryManager() {
		return _deliveryManager;
	}

	public void set_deliveryManager(DeliveryManager _deliveryManager) {
		this._deliveryManager = _deliveryManager;
	}

	public MapMarkerDot getMarkerStartingPoint() {
		Order o = _deliveryManager.get_actualDelivery().get_localOrder();
		return new MapMarkerDot(o.get_customer(), o.get_location());
	}

	public MapMarker getMarker(String customerOrder, String locationOrder) {
		return new MapMarkerDot(customerOrder, stringToLocation(locationOrder));
	}

	public MapMarker getMarker(String customerOrder, Location locationOrder) {
		return new MapMarkerDot(customerOrder, locationOrder);
	}

	public ArrayList<MapMarker> getListMarkerOfPendingOrder() {
		ArrayList<MapMarker> ret = new ArrayList<MapMarker>();
		for (Order o : _orderManager.get_pendingOrders())
			ret.add(getMarker(o.get_customer(), o.get_location()));
		return ret;
	}

	public ArrayList<MapMarker> getListMarkerOfOrdersToProgram() {
		ArrayList<MapMarker> ret = new ArrayList<MapMarker>();
		for (Order o : _orderManager.get_ordersToProgram())
			ret.add(getMarker(o.get_customer(), o.get_location()));
		return ret;
	}

	@Override
	public String toString() {
		return "Controller [" + _deliveryManager + _orderManager + " ]";
	}

}
