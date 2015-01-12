package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;

import java.awt.Color;
import java.awt.Toolkit;

public class wPresentacion extends Thread {

	private JFrame frame;
	private int retardoInicial = 0;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new AluminiumLookAndFeel());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Warning", 0);
		}
		wPresentacion presentacion = new wPresentacion();
		presentacion.start();
	}

	@Override
	public void run() {
		try {
			sleep(retardoInicial);
			new wMainForm();
			frame.setVisible(false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public wPresentacion() {
		initialize();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setTitle("GEOca v.1.0");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				wMainForm.class.getResource("/img/Delivery.png")));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1100, 596);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(null);

		JLabel lbl_Loading = new JLabel("");
		lbl_Loading.setIcon(new ImageIcon(wPresentacion.class
				.getResource("/img/loading.gif")));
		lbl_Loading.setBounds(518, 490, 48, 57);
		frame.getContentPane().add(lbl_Loading);

		JLabel labelFondo = new JLabel("");
		labelFondo.setIcon(new ImageIcon(wPresentacion.class
				.getResource("/img/fondo.png")));
		labelFondo.setBounds(0, 0, 1431, 592);
		frame.getContentPane().add(labelFondo);

	}

}
