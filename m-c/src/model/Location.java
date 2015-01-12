package model;

import java.io.Serializable;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Location extends Coordinate implements Serializable {

	private static final long serialVersionUID = 1L;

	public Location(double lat, double lon) {
		super(lat, lon);
	}

	public double distance(Location other) {
		return Math.sqrt(Math.pow((this.getLat() - other.getLat()), 2)
				+ Math.pow((this.getLon() - other.getLon()), 2));
	}

	@Override
	public String toString() {
		return super.getLat() + " " + super.getLon();
	}

}
