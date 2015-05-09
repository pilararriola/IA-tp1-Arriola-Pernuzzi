package frsf.cidisi.exercise.noinformadacostouniforme.search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class DroneAgenteMain {

    public static void main(String[] args) throws PrologConnectorException {
        DroneAgente agent = new DroneAgente();

        Ambiente environment = new Ambiente();

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
