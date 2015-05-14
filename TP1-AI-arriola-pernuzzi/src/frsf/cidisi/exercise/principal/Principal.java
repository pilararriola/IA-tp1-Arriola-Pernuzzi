package frsf.cidisi.exercise.principal;

import frsf.cidisi.exercise.interfaz.VentanaPrincipal;

import java.awt.EventQueue;

import javax.swing.UIManager;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					VentanaPrincipal ventana = new VentanaPrincipal();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
