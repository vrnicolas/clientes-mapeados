package view;

//import interaccion.InteraccionGui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class wMapaParaPosicion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JMapViewer mapa = new JMapViewer();
	static Coordinate ultimoClick;

	/**
	 * Create the frame.
	 * 
	 * @param registerOrder
	 */
	public wMapaParaPosicion(final wMainForm.STATE_TO_USE state) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1100, 596);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(mapa);

		/**
		 * El botón "Listo" seteará la información correspondiente dependiendo
		 * del estado en el que esté.
		 */
		JButton btnListo = new JButton("LISTO");
		btnListo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				wMainForm.setVisibility(true);
				if (state.equals(wMainForm.STATE_TO_USE.CHANGE_STARTING_POINT))
					wMainForm.set_startingPointDeliveryJTF(getUbicacion());

				if (state.equals(wMainForm.STATE_TO_USE.REGISTER_ORDER))
					wMainForm.set_locationForRegisterJTF(getUbicacion());
				dispose();
			}
		});
		btnListo.setBounds(492, 525, 89, 23);
		mapa.add(btnListo);
		mapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mapa.removeAllMapMarkers();
				ultimoClick = mapa.getPosition(mapa.getMousePosition());
				mapa.addMapMarker(new MapMarkerDot(ultimoClick));
			}
		});
	}

	public static Coordinate _getUltimoClick() {
		return ultimoClick;
	}

	public static String getUbicacion() {
		String ret = "";
		if (_getUltimoClick() != null) {
			Coordinate aux = _getUltimoClick();
			ret = aux.getLon() + " " + aux.getLat();
		}
		return ret;
	}

}
