package frsf.cidisi.exercise.noinformadacostouniforme.search;


import frsf.cidisi.exercise.principal.Simulador;
import frsf.cidisi.faia.exceptions.PrologConnectorException;

public class DroneFuncionCostoUniforme {
    
	public static void iniciar(int escenario) throws PrologConnectorException {
        Drone agente = new Drone();
        Ambiente ambiente = new Ambiente(escenario);

        Simulador simulador = new Simulador(ambiente, agente);
        
        simulador.comenzar();
    }
	
}
