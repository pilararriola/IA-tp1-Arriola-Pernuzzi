package frsf.cidisi.exercise.interfaz;


import frsf.cidisi.exercise.informada.search.DroneInformada;
import frsf.cidisi.exercise.noinformadacostouniforme.search.DroneFuncionCostoUniforme;
import frsf.cidisi.exercise.noinformadaprofundidad.search.DroneProfundidad;
import frsf.cidisi.exercise.principal.Simulador;
import frsf.cidisi.faia.exceptions.PrologConnectorException;

public class ConfiguradorEstrategia {
	// Constantes para asignar un número a cada tipo de búsqueda
	public static final int COSTO_UNIFORME		= 0;
	public static final int PROFUNDIDAD			= 1;
	public static final int BUSQUEDA_INFORMADA 	= 2;
	
	private static Simulador simulador;
	private static VentanaSimulacion ventanaSimulacion;
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
					DroneProfundidad.iniciar(escenario);
					break;

				case BUSQUEDA_INFORMADA:
					DroneInformada.iniciar(escenario);
					break;

			}
		} catch (PrologConnectorException e) {
			e.printStackTrace();
		}
	}
	
	public static void setSimulador(Simulador sim) {
		simulador = sim;
		try {
			String busq = "Búsqueda";
			switch (busqueda) {
			case COSTO_UNIFORME:
				busq += " no informada - costo uniforme.";
				break;
			case PROFUNDIDAD:
				busq += " no informada - profundidad.";
				break;
			case BUSQUEDA_INFORMADA:
				busq += " informada.";
				break;
			}
			ventanaSimulacion = new VentanaSimulacion(escenario, busq);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void avanzar(){
		int resultado=0;
		while(resultado==0){	
			resultado=simulador.avanzar();
		}
		if(resultado==1){
			ventanaSimulacion.terminar();
			simulador.terminar();
		}
	}

	public static void registrarEvento(String string) {
		System.out.println(string);
	}
}
