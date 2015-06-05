package frsf.cidisi.exercise.noinformadacostouniforme.search.actions;

import frsf.cidisi.exercise.noinformadacostouniforme.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IdentificarVictimario extends SearchAction {
	private static final double CostoIdentificar = 5;
    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoDrone agState = (EstadoDrone) s;
        
        //Para poder identificar al victimario tiene que estar en el nivel bajo
        if(agState.getposicion()[0]==0){
        	if(agState.getlistaEsquinasIdentificadas()[agState.getposicion()[3]]==0){
        		agState.incrementarEnergiaUsada(CostoIdentificar);
	        	agState.identificarVictimario();
	    		return agState;
        	}
        }
        return null;
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        EstadoDrone agState = ((EstadoDrone) ast);

        //Para poder identificar al victimario tiene que estar en el nivel bajo
        if(agState.getposicion()[0]==0){
        	if(agState.getlistaEsquinasIdentificadas()[agState.getposicion()[3]]==0){
            	agState.identificarVictimario();
            	environmentState.identificarVictimario();
        		return environmentState;
        	}
        }
        return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return new Double(1);
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "IdentificarVictimario";
    }
}