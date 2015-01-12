package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mapa.Mapa;
import model.Order;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.toedter.calendar.JDateChooser;

import controller.Controller;

import java.awt.SystemColor;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import transversalLogic.Validater;

import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JSeparator;

import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import persistencia.Serializador;

public class wMainForm {

	private static JFrame frame;

	private JTabbedPane _structureInTabs;

	// Interaccion Lógica
	private Controller controller = Controller.getInstance();

	// Campos para registrar pedidos.
	private JTextField _customerNameForRegisterJTF;
	private static JTextField _locationForRegisterJTF;

	// Campos movimientos de pedidos (pendientes - a programar)
	private JList<Order> _pendingOrdersLIST;
	private JList<Order> _ordersToProgramLIST;

	// Campos de configuración
	private static JTextField _startingPointDeliveryJTF;
	private static JTextField _dataStartingPointJTF;

	// Tools
	private JDateChooser _calendar;
	private Mapa _map = Mapa._GetMapa();

	// Estados para usar el Mapa.
	public static enum STATE_TO_USE {
		REGISTER_ORDER, CHANGE_STARTING_POINT
	};

	/**
	 * Create the application.
	 */
	public wMainForm() {
		initialize();
		try {
			UIManager.setLookAndFeel(new AluminiumLookAndFeel());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Warning", 0);
		}
		setVisibility(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("GEOca v.1.0");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				wMainForm.class.getResource("/img/Delivery.png")));
		frame.setBounds(100, 100, 1100, 596);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		updateAll();
		// Paneles de la aplicación.

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(SystemColor.window);
		panelPrincipal.setBounds(35, 13, 400, 400);
		panelPrincipal.setLayout(null);
		frame.getContentPane().add(panelPrincipal);

		JPanel panelPestaña0 = new JPanel();
		panelPestaña0.setBorder(new LineBorder(SystemColor.activeCaption));
		panelPestaña0.setBackground(new Color(255, 250, 250));
		panelPestaña0.setBounds(35, 13, 400, 400);

		final JPanel panelPestaña1 = new JPanel();
		panelPestaña1.setBorder(new LineBorder(SystemColor.activeCaption));
		panelPestaña1.setBackground(new Color(255, 250, 250));
		panelPestaña1.setLayout(null);
		panelPestaña1.setBounds(35, 13, 400, 400);

		final JPanel panelPestaña2 = new JPanel();
		panelPestaña2.setBorder(new LineBorder(SystemColor.activeCaption));
		panelPestaña2.setBackground(new Color(255, 250, 250));
		panelPestaña2.setBounds(35, 13, 400, 400);
		panelPestaña2.setLayout(null);

		final JPanel panelPestaña3 = new JPanel();
		panelPestaña3.setBorder(new LineBorder(SystemColor.activeCaption));
		panelPestaña3.setBackground(new Color(255, 250, 250));
		panelPestaña3.setLayout(null);
		panelPestaña3.setBounds(35, 13, 400, 400);

		JPanel panelPestaña4 = new JPanel();
		panelPestaña4.setBackground(SystemColor.window);
		panelPestaña4.setBounds(35, 13, 400, 400);
		panelPestaña4.setLayout(null);

		// Contenido Pestaña0

		JLabel MuestraGif = new JLabel("");
		MuestraGif.setIcon(new ImageIcon(wMainForm.class
				.getResource("/img/MuestraGif.gif")));
		MuestraGif.setBounds(10, 11, 1039, 476);
		panelPestaña0.add(MuestraGif);

		// Contenido Pestaña1
		JPanel contenedorDeMapa = new JPanel();
		contenedorDeMapa.setBounds(10, 11, 1039, 442);
		panelPestaña1.add(contenedorDeMapa);
		contenedorDeMapa.add(_map);
		contenedorDeMapa.setLayout(null);
		_map.setBounds(0, 0, 1059, 489);

		JButton btnArmarRecorrido = new JButton("Armar Recorrido");
		btnArmarRecorrido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_structureInTabs.setSelectedComponent(panelPestaña3);
			}
		});
		btnArmarRecorrido.setBounds(456, 464, 146, 23);
		panelPestaña1.add(btnArmarRecorrido);

		// Contenido Pestaña2

		JLabel lblIngresarPedido = new JLabel("Ingresar Pedido");
		lblIngresarPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresarPedido.setFont(new Font("Browallia New", Font.PLAIN, 25));
		lblIngresarPedido.setBounds(316, 11, 426, 37);
		panelPestaña2.add(lblIngresarPedido);

		JLabel lblCliente = new JLabel("CLIENTE");
		lblCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblCliente.setFont(new Font("Browallia New", Font.PLAIN, 20));
		lblCliente.setBounds(275, 126, 75, 14);
		panelPestaña2.add(lblCliente);

		_customerNameForRegisterJTF = new JTextField();
		_customerNameForRegisterJTF.setBounds(382, 110, 318, 37);
		panelPestaña2.add(_customerNameForRegisterJTF);
		_customerNameForRegisterJTF.setColumns(10);

		JLabel lblFechaDeEntrega = new JLabel("<html>FECHA DE ENTREGA</html>");
		lblFechaDeEntrega.setFont(new Font("Browallia New", Font.PLAIN, 20));
		lblFechaDeEntrega.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDeEntrega.setBounds(275, 278, 75, 46);
		panelPestaña2.add(lblFechaDeEntrega);

		JLabel lblubicacion = new JLabel("<html>UBICACI\u00D3N</html>");
		lblubicacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblubicacion.setFont(new Font("Browallia New", Font.PLAIN, 20));
		lblubicacion.setBounds(275, 202, 75, 14);
		panelPestaña2.add(lblubicacion);

		JButton btnUbicacion = new JButton("");
		btnUbicacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisibility(false);
				new wMapaParaPosicion(STATE_TO_USE.REGISTER_ORDER);
			}
		});
		btnUbicacion.setIcon(new ImageIcon(wMainForm.class
				.getResource("/img/View.png")));
		btnUbicacion.setBounds(661, 186, 39, 37);
		panelPestaña2.add(btnUbicacion);

		_locationForRegisterJTF = new JTextField();
		_locationForRegisterJTF.setEditable(false);
		_locationForRegisterJTF.setBackground(Color.WHITE);
		_locationForRegisterJTF.setBounds(382, 186, 279, 37);
		panelPestaña2.add(_locationForRegisterJTF);
		_locationForRegisterJTF.setColumns(10);

		JButton btnIngresar = new JButton("INGRESAR");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (fieldsNoEmpty()) {

					String customerOrder = _customerNameForRegisterJTF
							.getText();
					String locationOrder = _locationForRegisterJTF.getText();
					String dateOrder = getDate(_calendar);

					if (Validater.orderEntry(customerOrder, dateOrder)) {
						controller.addToPendingOrders(customerOrder,
								locationOrder, dateOrder);
						updatePendingOrder();
						_map.cleanAllMap();
						updateMarkerOfPendingOrder(
								controller.getListMarkerOfPendingOrder(),
								controller.getListMarkerOfOrdersToProgram());
						SAVE_DATA();

						JOptionPane.showMessageDialog(null,
								"El pedido se ha ingresado correctamente.");
					} else {
						JOptionPane
								.showMessageDialog(null,
										"Los datos son incorrectos. Vuelva a intentarlo.");
					}

				} else {
					JOptionPane
							.showMessageDialog(null,
									"Todos los campos deben ser completados. Vuelva a intentarlo.");
				}

				emptyFieldsOrders();
			}

			private boolean fieldsNoEmpty() {
				return (!_locationForRegisterJTF.getText().equals("")
						&& !_customerNameForRegisterJTF.getText().equals("") && _calendar
						.getCalendar() != null);
			}

			private void emptyFieldsOrders() {
				_customerNameForRegisterJTF.setText("");
				_locationForRegisterJTF.setText("");
				_calendar.setDate(null);
			}

			public String getDate(JDateChooser component) {
				String ret = "";
				try {
					String format = component.getDateFormatString();
					Date date = component.getDate();
					SimpleDateFormat sdf = new SimpleDateFormat(format);
					ret = String.valueOf(sdf.format(date));

				} catch (Exception e) {
					e.getStackTrace();
				}
				return ret;
			}

		});
		btnIngresar.setForeground(new Color(0, 0, 0));
		btnIngresar.setBounds(470, 454, 113, 33);
		panelPestaña2.add(btnIngresar);

		// Contenidos para pestaña3.

		JLabel lblPedidosPendientes = new JLabel("Pedidos Pendientes");
		lblPedidosPendientes.setBounds(10, 157, 476, 34);
		lblPedidosPendientes.setFont(new Font("Browallia New", Font.PLAIN, 25));
		lblPedidosPendientes.setHorizontalAlignment(SwingConstants.CENTER);
		panelPestaña3.add(lblPedidosPendientes);

		JButton btnCancelarPedido = new JButton("");
		btnCancelarPedido.setToolTipText("Eliminar Pedido Pendiente");
		btnCancelarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (_pendingOrdersLIST.getSelectedValue() != null) {
					controller.deletePendingOrder(_pendingOrdersLIST
							.getSelectedValue());
					SAVE_DATA();
				}
				updatePendingOrder();
			}
		});
		btnCancelarPedido.setBounds(119, 458, 55, 29);
		btnCancelarPedido.setIcon(new ImageIcon(wMainForm.class
				.getResource("/img/Delete.png")));
		btnCancelarPedido.setForeground(new Color(0, 0, 0));
		panelPestaña3.add(btnCancelarPedido);

		JButton btnAgregarPedidoParaProgramar = new JButton("");
		btnAgregarPedidoParaProgramar.setToolTipText("Programar Pedido");
		btnAgregarPedidoParaProgramar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.Order order = _pendingOrdersLIST.getSelectedValue();
				if (order != null) {
					controller.addOrderToProgram(order);
					controller.deletePendingOrder(order);
					updatePendingOrder();
					updateOrdersToProgram();
				}
				SAVE_DATA();
			}
		});
		btnAgregarPedidoParaProgramar.setBounds(496, 317, 55, 29);
		btnAgregarPedidoParaProgramar.setIcon(new ImageIcon(wMainForm.class
				.getResource("/img/Go forward.png")));
		btnAgregarPedidoParaProgramar.setForeground(new Color(0, 0, 0));
		panelPestaña3.add(btnAgregarPedidoParaProgramar);

		JButton btnAgregarPedidoParaPendiente = new JButton("");
		btnAgregarPedidoParaPendiente.setToolTipText("No Programar Pedido");
		btnAgregarPedidoParaPendiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.Order order = _ordersToProgramLIST.getSelectedValue();
				if (order != null) {
					controller.addToPendingOrders(order);
					controller.deleteOrderToProgram(order);
					updateOrdersToProgram();
					updatePendingOrder();
				}
				SAVE_DATA();
			}
		});
		btnAgregarPedidoParaPendiente.setIcon(new ImageIcon(wMainForm.class
				.getResource("/img/Go back.png")));
		btnAgregarPedidoParaPendiente.setForeground(Color.BLACK);
		btnAgregarPedidoParaPendiente.setBounds(496, 277, 55, 29);
		panelPestaña3.add(btnAgregarPedidoParaPendiente);

		JButton btnEditarPedido = new JButton("");
		btnEditarPedido.setToolTipText("Editar Pedido\r\n");
		btnEditarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.Order selectedPendingOrder = _pendingOrdersLIST
						.getSelectedValue();
				if (selectedPendingOrder != null) {
					controller.deletePendingOrder(selectedPendingOrder);
					setOrderField(selectedPendingOrder);
					_structureInTabs.setSelectedComponent(panelPestaña2);
					updatePendingOrder();
				}
				SAVE_DATA();
			}

			private void setOrderField(Order order) {
				_customerNameForRegisterJTF.setText(order.get_customer());
				_locationForRegisterJTF
						.setText(order.get_location().toString());
			}
		});
		btnEditarPedido.setBounds(312, 458, 55, 29);
		btnEditarPedido.setIcon(new ImageIcon(wMainForm.class
				.getResource("/img/Modify.png")));
		btnEditarPedido.setForeground(Color.BLACK);
		panelPestaña3.add(btnEditarPedido);

		JScrollPane scrollPanePendingOrders = new JScrollPane();
		scrollPanePendingOrders.setBounds(10, 218, 476, 229);
		scrollPanePendingOrders.setViewportBorder(null);
		scrollPanePendingOrders
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanePendingOrders
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelPestaña3.add(scrollPanePendingOrders);

		_pendingOrdersLIST = new JList<model.Order>();
		_pendingOrdersLIST.setListData(controller.getArrayPendingOrders());
		scrollPanePendingOrders.setViewportView(_pendingOrdersLIST);

		JScrollPane scrollPaneOrdersToProgram = new JScrollPane();
		scrollPaneOrdersToProgram.setViewportBorder(null);
		scrollPaneOrdersToProgram
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneOrdersToProgram
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneOrdersToProgram.setBounds(562, 218, 476, 229);
		panelPestaña3.add(scrollPaneOrdersToProgram);

		_ordersToProgramLIST = new JList<model.Order>();
		_ordersToProgramLIST.setListData(controller.getArrayOrdersToProgram());
		scrollPaneOrdersToProgram.setViewportView(_ordersToProgramLIST);

		JLabel lblPedidosAProgramar = new JLabel("Pedidos a Programar");
		lblPedidosAProgramar.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedidosAProgramar.setFont(new Font("Browallia New", Font.PLAIN, 25));
		lblPedidosAProgramar.setBounds(562, 157, 476, 34);
		panelPestaña3.add(lblPedidosAProgramar);

		JButton btnGetLeastPath = new JButton("");
		btnGetLeastPath.setToolTipText("Armar Recorrido");
		btnGetLeastPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (controller.getArrayOrdersToProgram().length > 1
						&& controller.startingPointNoNull()) {
					_map.cleanMap();
					controller.get_deliveryManager().loadStartingPoint();
					controller.get_deliveryManager()
							.loadDelivery(
									controller.get_orderManager()
											.get_ordersToProgram());
					_map.addPath(controller.getMapPolygonImpl());
					_map.addMapMarker(controller.getMarkerStartingPoint());
					_structureInTabs.setSelectedComponent(panelPestaña1);
				} else {
					JOptionPane
							.showMessageDialog(
									null,
									"Para poder armar un recorrido se debe contar con por lo menos 3 pedidos, además se debe determinar el punto de partida del camión. Por favor, verifique sus datos. ");
				}
				SAVE_DATA();
			}

		});
		btnGetLeastPath.setBackground(new Color(105, 105, 105));
		btnGetLeastPath.setIcon(new ImageIcon(wMainForm.class
				.getResource("/img/Delivery.png")));
		btnGetLeastPath.setBounds(770, 453, 89, 34);
		panelPestaña3.add(btnGetLeastPath);

		JButton btnPedidoCumplido = new JButton("");
		btnPedidoCumplido.setToolTipText("Recorrido Cumplido");
		btnPedidoCumplido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_ordersToProgramLIST.setListData(controller.setListOrders());
				SAVE_DATA();
			}
		});
		btnPedidoCumplido.setIcon(new ImageIcon(wMainForm.class
				.getResource("/img/Apply.png")));
		btnPedidoCumplido.setForeground(Color.BLACK);
		btnPedidoCumplido.setBounds(496, 357, 55, 29);
		panelPestaña3.add(btnPedidoCumplido);

		// Contenido para pestaña4.
		JLabel lblConfiguracin = new JLabel("Configuraci\u00F3n");
		lblConfiguracin.setFont(new Font("Browallia New", Font.ITALIC, 40));
		lblConfiguracin.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfiguracin.setBounds(10, 11, 1039, 82);
		panelPestaña4.add(lblConfiguracin);

		JLabel lblCambiarUbicacinDe = new JLabel(
				"<html><body>Ubicaci\u00F3n de Salida de Reparto :</body></html>");
		lblCambiarUbicacinDe.setFont(new Font("Arial", Font.PLAIN, 17));
		lblCambiarUbicacinDe.setBounds(243, 85, 146, 45);
		panelPestaña4.add(lblCambiarUbicacinDe);

		JButton btnModificarUbicacionReparto = new JButton("");
		btnModificarUbicacionReparto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisibility(false);
				new wMapaParaPosicion(STATE_TO_USE.CHANGE_STARTING_POINT);
			}
		});

		btnModificarUbicacionReparto.setIcon(new ImageIcon(wMainForm.class
				.getResource("/img/View.png")));
		btnModificarUbicacionReparto.setBounds(740, 106, 25, 24);
		panelPestaña4.add(btnModificarUbicacionReparto);

		_startingPointDeliveryJTF = new JTextField();
		_startingPointDeliveryJTF.setEditable(false);
		_startingPointDeliveryJTF.setColumns(10);
		_startingPointDeliveryJTF.setBackground(Color.WHITE);
		_startingPointDeliveryJTF.setBounds(399, 106, 344, 24);
		panelPestaña4.add(_startingPointDeliveryJTF);

		JButton buttonIngresar = new JButton("INGRESAR");
		buttonIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				controller.changeStartingPoint(wMapaParaPosicion.getUbicacion());
				_startingPointDeliveryJTF.setText("");
				wMainForm.set_dataStartingPointJTF(controller
						.getStartingPoint().toString());
				SAVE_DATA();

			}
		});
		buttonIngresar.setForeground(Color.BLACK);
		buttonIngresar.setBounds(483, 206, 113, 33);
		panelPestaña4.add(buttonIngresar);

		// ESTRUCTURA EN TAB.
		_structureInTabs = new JTabbedPane(JTabbedPane.TOP);
		_structureInTabs.setBounds(10, 11, 1064, 536);
		_structureInTabs.setBackground(UIManager.getColor("CheckBox.light"));
		_structureInTabs.setForeground(SystemColor.controlDkShadow);
		panelPrincipal.add(_structureInTabs);

		// Agrego pestaña0
		_structureInTabs.addTab("", panelPestaña0);
		panelPestaña0.setLayout(null);
		_structureInTabs.setIconAt(0,
				new ImageIcon(wMainForm.class.getResource("/img/Home.png")));

		// Agrego pestaña1
		_structureInTabs.addTab("", panelPestaña1);
		_structureInTabs.setIconAt(1,
				new ImageIcon(wMainForm.class.getResource("/img/View.png")));

		// Agrego pestaña2
		_structureInTabs.addTab("", panelPestaña2);

		_calendar = new JDateChooser();
		_calendar.setBounds(382, 278, 318, 37);
		panelPestaña2.add(_calendar);

		_structureInTabs.setIconAt(2,
				new ImageIcon(wMainForm.class.getResource("/img/Create.png")));

		// Agrego Pestaña3
		_structureInTabs.addTab("", panelPestaña3);

		JLabel lbl_startingPoint = new JLabel(
				"Punto de partida del cami\u00F3n :");
		lbl_startingPoint.setFont(new Font("Browallia New", Font.PLAIN, 25));
		lbl_startingPoint.setBounds(55, 43, 244, 24);
		panelPestaña3.add(lbl_startingPoint);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 144, 1028, 2);
		panelPestaña3.add(separator);

		_dataStartingPointJTF = new JTextField();
		_dataStartingPointJTF.setText(controller.getStartingPoint() + "");
		_dataStartingPointJTF.setEditable(false);
		_dataStartingPointJTF.setBounds(301, 48, 313, 20);
		panelPestaña3.add(_dataStartingPointJTF);
		_dataStartingPointJTF.setColumns(10);

		JLabel lblData = new JLabel("<html><u>Datos Relevantes</u></html>");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setFont(new Font("Browallia New", Font.PLAIN, 25));
		lblData.setBounds(424, 11, 190, 26);
		panelPestaña3.add(lblData);
		_structureInTabs.setIconAt(3,
				new ImageIcon(wMainForm.class.getResource("/img/List.png")));

		// Agrego Pestaña4
		_structureInTabs.addTab("", panelPestaña4);
		_structureInTabs.setIconAt(
				4,
				new ImageIcon(wMainForm.class
						.getResource("/img/Application.png")));

	}

	protected void SAVE_DATA() {
		Serializador.saveData(controller);
	}

	protected void updateOrdersToProgram() {
		_ordersToProgramLIST.setListData(controller.getArrayOrdersToProgram());

	}

	private void updatePendingOrder() {
		_pendingOrdersLIST.setListData(controller.getArrayPendingOrders());
	}

	public static void set_startingPointDeliveryJTF(String location) {
		_startingPointDeliveryJTF.setText(location);
	}

	public static void set_locationForRegisterJTF(String location) {
		_locationForRegisterJTF.setText(location);
	}

	public static void set_dataStartingPointJTF(String location) {
		_dataStartingPointJTF.setText(location);
	}

	private void updateMarkerOfPendingOrder(
			ArrayList<MapMarker> listMarkerOfPendingOrder,
			ArrayList<MapMarker> listMarkerOfOrdersToProgram) {
		for (MapMarker marker1 : listMarkerOfPendingOrder)
			_map.addMapMarker(marker1);
		for (MapMarker marker2 : listMarkerOfOrdersToProgram)
			_map.addMapMarker(marker2);
	}

	public void updateAll() {
		_map.cleanAllMap();
		updateMarkerOfPendingOrder(controller.getListMarkerOfPendingOrder(),
				controller.getListMarkerOfOrdersToProgram());
	}

	public static void setVisibility(boolean b) {
		frame.setVisible(b);
	}
}