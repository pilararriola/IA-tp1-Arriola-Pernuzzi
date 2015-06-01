package frsf.cidisi.exercise.noinformadacostouniforme.search.actions;

import frsf.cidisi.exercise.noinformadacostouniforme.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrNivelAlto extends SearchAction {
	private static final double CostoSubir = 200;
    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoDrone agState = (EstadoDrone) s;

        //Sólo podrá ir al nivel alto si se encuentra en el nivel medio
        if(1000-agState.getenergiaUsada()>=CostoSubir ){
        	if(agState.getposicion()[0]==1){
	            agState.incrementarEnergiaUsada(CostoSubir);
	        	agState.irNivelAlto();
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

 
        if(agState.getposicion()[0]==1){
        	agState.irNivelAlto();
        	environmentState.irNivelAlto();
    		return environmentState;
        }
        return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return new Double(0);
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "IrNivelAlto";
    }
}