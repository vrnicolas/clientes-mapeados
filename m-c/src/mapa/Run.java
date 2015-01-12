package mapa;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

public class Run {

	private JFrame frame;
	Mapa mapa = Mapa._GetMapa();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Run window = new Run();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Run() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(mapa, BorderLayout.CENTER);
		Integer k = new Integer(3);
		k += 9;
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JButton boton = mapa._getLimpiar();
		boton.setBackground(Color.CYAN);
		panel.add(boton, BorderLayout.SOUTH);
		panel.add(mapa._getComboEstilos(), BorderLayout.SOUTH);
		panel.add(mapa._getCentrar(), BorderLayout.SOUTH);
		panel.add(mapa._getCheckBoxLayers(), BorderLayout.SOUTH);
		panel.add(mapa._getCheckBoxScrollwrap(), BorderLayout.SOUTH);

	}

}
