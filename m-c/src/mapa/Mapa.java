package mapa;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import model.Location;
import model.Order;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.JMapViewerTree;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.TileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.BingAerialTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.MapQuestOpenAerialTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.MapQuestOsmTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;

public class Mapa extends JMapViewerTree {

	private static final long serialVersionUID = 1L;
	private static Mapa instancia = null;
	private boolean agregarMarca = true;
	private static String nombre = "Lista de usuarios";
	private Coordinate ultimoClick;

	public Mapa() {
		super(nombre);
		_map().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ultimoClick = _map().getPosition(getMousePosition());
			}
		});
	}

	public MapPolygonImpl _getPMapPolygonImpl(List<Location> ubicacion) {
		ArrayList<MapMarkerDot> p = new ArrayList<MapMarkerDot>();
		for (Coordinate u : ubicacion) {
			p.add(new MapMarkerDot(u));
		}

		return new MapPolygonImpl("", p);// new MapPolygonImpl(d,b,a,c);
	}

	/**
	 * 
	 * @param poly
	 *            = al recorrido debe ser en el orden de las que se va a pasar
	 * @param nombre
	 *            = es el mombre del poligono, si no se quiere nombre debe ser
	 *            null
	 * @return retorna un recorrido armado
	 */
	public MapPolygonImpl _crearPoligono(ArrayList<MapMarkerDot> poly,
			String nombre) {
		if (nombre != null) {
			return new MapPolygonImpl(nombre, poly);
		} else {
			return new MapPolygonImpl(poly);
		}
	}

	/**
	 * crea las marcas para armar el recorrido
	 * 
	 * @param cordenada
	 *            = es la dordenada de donde va a estar la marca
	 * @param nombre
	 *            = si se quiere mostrar nombre sobre el nunto debe ser null
	 * @param color
	 *            = es el color del punto si es null por defecto es amarillo
	 * @param layer
	 *            = si no se quiere guardar en el layer pasar como parametro
	 *            null
	 * @return retorna una lista de marcar en el mismo orden que recibe las
	 *         cordenada
	 */
	public ArrayList<MapMarkerDot> _crearListaMarca(List<Coordinate> cordenada,
			String nombre, Color color, Layer layer) {
		ArrayList<MapMarkerDot> ret = new ArrayList<MapMarkerDot>();
		for (Coordinate cord : cordenada) {
			MapMarkerDot marca = new MapMarkerDot(cord);
			if (nombre != null) {
				marca.setName(nombre);
			}
			if (color != null) {
				marca.setColor(color);
			}
			if (layer != null) {
				marca.setLayer(layer);
			}
			ret.add(marca);
		}
		return ret;
	}

	public void addPath(MapPolygonImpl recorrido) {
		_map().addMapPolygon(recorrido);
	}

	public void addPath(ArrayList<Location> recorrido) {
		_map().addMapPolygon(_getPMapPolygonImpl(recorrido));
	}

	public void _agregarMarcaAlLayer(List<Order> array) {
		for (Order elemento : array) {
			_agregarMarcaAlLayer("", elemento.get_location());
		}
	}

	public void _agregarMarcaAlLayer(String nombreLaye, Coordinate cordenada) {
		Layer layer = new Layer(nombreLaye);
		addLayer(layer);
		_agregarPunto(layer, cordenada);
	}

	/**
	 * 
	 * @return retorna la ubicacion de donde se hace el click
	 */
	public Coordinate _getPosicionMouse() {
		return _map().getPosition(getMousePosition());
	}

	public Coordinate _getPosicionMouse(Point posMouse) {
		return _map().getPosition(posMouse);
	}

	public static Mapa _GetMapa() {
		if (instancia == null) {
			instancia = new Mapa();
		}
		return instancia;
	}

	/**
	 * elimina todas las cosas que estan sobre el mapa, pero no el tree
	 */
	public void cleanAllMap() {
		_eliminarMarcas();
		_eliminarRecorrido();
		_eliminarRectangulo();
	}

	public void cleanMap() {
		_eliminarRecorrido();
		_eliminarRectangulo();
	}

	private void _eliminarRectangulo() {
		_map().removeAllMapRectangles();
	}

	private void _eliminarRecorrido() {
		_map().removeAllMapPolygons();
	}

	private void _eliminarMarcas() {
		_map().removeAllMapMarkers();
	}

	public void _agregarPunto(Layer layer, String nombre, Coordinate cordenada) {
		MapMarkerDot marca = new MapMarkerDot(cordenada);
		if (layer != null) {
			marca.setLayer(layer);
		}
		if (nombre != null) {
			marca.setName(nombre);
		}
		_map().addMapMarker(marca);
	}

	private void _agregarPunto(Layer layer, Coordinate cordenada) {
		_map().addMapMarker(new MapMarkerDot(layer, cordenada));
	}

	private void _agregarPunto(Coordinate cordenada) {
		_map().addMapMarker(new MapMarkerDot(cordenada));
	}

	public void _agregarPunto(String nombre, Coordinate cordenada) {
		if (nombre != null) {
			_agregarPunto(nombre, cordenada);
		} else {
			_agregarPunto(cordenada);
		}
	}

	public boolean _isAgregarMarca() {
		return agregarMarca;
	}

	public void _setAgregarMarca(boolean agregarMarca) {
		this.agregarMarca = agregarMarca;
	}

	public JComboBox<TileSource> _getComboEstilos() {
		JComboBox<TileSource> tileSourceSelector = new JComboBox<>(
				new TileSource[] { new OsmTileSource.Mapnik(),
						new OsmTileSource.CycleMap(),
						new BingAerialTileSource(),
						new MapQuestOsmTileSource(),
						new MapQuestOpenAerialTileSource() });
		tileSourceSelector.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				_setEstilo((TileSource) e.getItem());
			}
		});
		return tileSourceSelector;
	}

	private void _setEstilo(TileSource estilo) {
		_map().setTileSource(estilo);
	}

	public JButton _getCentrar() {
		JButton button = new JButton("encuadrar objetos del mapa");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_map().setDisplayToFitMapMarkers();
			}
		});
		return button;
	}

	public void focusMap() {
		_map().setDisplayToFitMapPolygons();
		;
	}

	public JButton _getLimpiar() {
		JButton limpiar = new JButton("limpiar");
		limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cleanMap();
			}
		});
		return limpiar;
	}

	public JCheckBox _getCheckBoxScrollwrap() {
		final JCheckBox scrollWrapEnabled = new JCheckBox(
				"Envoltura de desplazamiento");
		scrollWrapEnabled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_map().setScrollWrapEnabled(scrollWrapEnabled.isSelected());
			}
		});
		return scrollWrapEnabled;
	}

	public JCheckBox _getCheckBoxLayers() {
		final JCheckBox showTreeLayers = new JCheckBox("Tree Layers visible");
		setTreeVisible(showTreeLayers.isSelected());
		showTreeLayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTreeVisible(showTreeLayers.isSelected());
			}
		});
		return showTreeLayers;
	}

	private JMapViewer _map() {
		return getViewer();
	}

	public Coordinate ultimoClick() {
		return ultimoClick;
	}

	public void addMapMarker(MapMarker marker) {
		_map().addMapMarker(marker);
	}

}
