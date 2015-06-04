package frsf.cidisi.exercise.interfaz;



import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

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
		frame.setBounds(100, 100, 586, 556); //CAMBIAR
		//Imagen de fondo
		PanelImagen p = new PanelImagen();
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(p);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("fillx,insets 0")); //OJO CON esto!
		frame.setTitle("TP Inteligencia Artificial 2015");
		
		JButton btnIniciar = new JButton(">>	 BUSQUEMOS AL VICTIMARIO	<<");
		
		
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConfiguradorEstrategia.avanzar();
			}
		});
		frame.getContentPane().add(btnIniciar, "cell 1 1");
	}
	
	public void terminar(){
		System.out.println("Simulación de búsqueda terminada");
		frame.setVisible(false);
		frame.dispose();
	}
	

	
}
