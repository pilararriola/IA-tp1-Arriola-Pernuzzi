package frsf.cidisi.exercise.interfaz;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

public class VentanaPrincipal {
	private JFrame frame;
	private JComboBox comboBox;
	private JComboBox comboBox_1;

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow,fill][grow,fill]", "[][][grow,fill]"));
		frame.setTitle("TP IA - Configurar Escenario");
		
		JLabel lblEscenario = new JLabel("Escenario:");
		frame.getContentPane().add(lblEscenario, "cell 0 0");
		
		JLabel lblEstrategia = new JLabel("Estrategia:");
		frame.getContentPane().add(lblEstrategia, "cell 1 0");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		frame.getContentPane().add(comboBox, "cell 0 1,growx");
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Búsqueda no informada - Costo Uniforme", "Búsqueda no informada - Profundidad", "Búsqueda informada"}));
		frame.getContentPane().add(comboBox_1, "cell 1 1,growx");
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int escenario = comboBox.getSelectedIndex()+1;
				int estrategia = comboBox_1.getSelectedIndex();
				ConfiguradorEstrategia.configurarSimulacion(escenario, estrategia);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnAceptar, "cell 0 2 2 1");
	}
}
