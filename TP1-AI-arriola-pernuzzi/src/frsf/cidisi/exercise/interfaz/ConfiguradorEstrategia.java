package frsf.cidisi.exercise.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import frsf.cidisi.exercise.noinformadacostouniforme.search.DroneFuncionCostoUniforme;
import frsf.cidisi.exercise.principal.Simulador;
import frsf.cidisi.faia.exceptions.PrologConnectorException;

public class ConfiguradorEstrategia {
	// Constantes para asignar un número a cada tipo de búsqueda
	public static final int COSTO_UNIFORME		= 0;
	public static final int PROFUNDIDAD			= 1;
	public static final int BUSQUEDA_INFORMADA 	= 2;
	
	private static Simulador simulador;
	//private static PanelSimulador window;
	//private static Timer autoStep;
	private static int escenario;
	private static int busqueda;
	
	public static void configurarSimulacion(int esc, int busq) {
		escenario = esc;
		busqueda = busq;
		try {
			switch(busqueda){

				case COSTO_UNIFORME:
					DroneFuncionCostoUniforme.iniciar(escenario);
					break;

				case PROFUNDIDAD:
					//ArqueologoSinUbeme.ejecutar(escenario);
					break;

				case BUSQUEDA_INFORMADA:
					//ArqueologoFuncionCosto.ejecutar(escenario);
					break;

			}
		} catch (PrologConnectorException e) {
			e.printStackTrace();
		}
	}
	
	public static void setSimulador(Simulador sim) {
		/*simulador = sim;
		try {
			String busqueda = "Búsqueda";
			switch (busquedaAct) {
			case (BUSQUEDA_UBEME):
				busqueda += " A* con Ubeme.";
				break;
			case (BUSQUEDA_SINUBEME):
				busqueda += " A* sin Ubeme.";
				break;
			case (BUSQUEDA_FUNCION):
				busqueda += " con función costo.";
				break;
			case (BUSQUEDA_ESTRATEGIA):
				busqueda += " en profundidad.";
				break;
			}
			window = new PanelSimulador(escenarioAct, busqueda);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*autoStep = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        autoStep();
		    }
		});*/
	}
}
