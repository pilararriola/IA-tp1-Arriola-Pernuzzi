package frsf.cidisi.exercise.informada.search;

import frsf.cidisi.exercise.principal.Simulador;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.exercise.informada.search.Ambiente;
import frsf.cidisi.exercise.informada.search.Drone;

public class DroneInformada {
	public static void iniciar(int escenario) throws PrologConnectorException {
        Drone agente = new Drone();
        Ambiente ambiente = new Ambiente(escenario);

        Simulador simulador = new Simulador(ambiente, agente);
        
        simulador.comenzar();
    }
}
