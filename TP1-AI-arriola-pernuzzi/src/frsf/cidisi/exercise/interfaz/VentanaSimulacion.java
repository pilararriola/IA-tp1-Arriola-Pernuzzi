package frsf.cidisi.exercise.interfaz;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

public class VentanaSimulacion {
	private JFrame frame;
	private JButton btnIniciar;
	
	public VentanaSimulacion(int escenario, String estrategia) {
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow,fill][][grow,fill]", "[grow,fill][][grow,fill]"));
		frame.setTitle("TP Inteligencia Artificial 2015");
		
		JButton btnIniciar = new JButton("Iniciar Simulación");
		
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConfiguradorEstrategia.avanzar();
			}
		});
		frame.getContentPane().add(btnIniciar, "cell 1 1 ");
	}
	
	public void terminar(){
		System.out.println("Simulación de búsqueda terminada");
		frame.setVisible(false);
		frame.dispose();
	}
	

	
}
