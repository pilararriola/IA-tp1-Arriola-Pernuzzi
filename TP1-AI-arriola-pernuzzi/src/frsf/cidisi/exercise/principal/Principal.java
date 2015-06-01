package frsf.cidisi.exercise.principal;

import frsf.cidisi.exercise.entidades.Cuadrante;
import frsf.cidisi.exercise.entidades.Mapa;
import frsf.cidisi.exercise.entidades.Subcuadrante;
import frsf.cidisi.exercise.interfaz.VentanaPrincipal;
import frsf.cidisi.exercise.noinformadacostouniforme.search.EstadoAmbiente;

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
				/*EstadoAmbiente estadoAmbiente=new EstadoAmbiente(1);
				System.out.println("Cuadrantes:");
				for(Cuadrante cuadrante : estadoAmbiente.getlistaCuadrantes()){
					System.out.println("Id cuadrante: "+cuadrante.getidCuadrante());
					System.out.println("Intensidad: "+cuadrante.getintensidad());
					int j=0;
					for(Subcuadrante sub: cuadrante.getlistaSubcuadrantes()){
						System.out.print("Id subcuadrante: "+j);
						System.out.println("Intensidad: "+sub.getintensidad());
						j++;
					}
					System.out.println("");
				}*/

			}
		});

	}

}
