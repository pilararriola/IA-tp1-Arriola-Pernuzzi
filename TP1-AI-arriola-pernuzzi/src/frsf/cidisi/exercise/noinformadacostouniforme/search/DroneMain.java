package frsf.cidisi.exercise.noinformadacostouniforme.search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class DroneMain {

    public static void main(String[] args) throws PrologConnectorException {
        Drone agent = new Drone();

        Ambiente environment = new Ambiente();

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
