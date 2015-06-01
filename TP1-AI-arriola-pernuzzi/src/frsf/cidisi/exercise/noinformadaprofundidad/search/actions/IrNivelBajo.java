package frsf.cidisi.exercise.noinformadaprofundidad.search.actions;

import frsf.cidisi.exercise.noinformadaprofundidad.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrNivelBajo extends SearchAction {
	private static final double CostoBajar = 0;
    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoDrone agState = (EstadoDrone) s;

	        if(1000-agState.getenergiaUsada()>=CostoBajar){
		        //Sólo podrá ir al nivel alto si se encuentra en el nivel medio
		        if(agState.getposicion()[0]==1){
		    		int subcuadrante=agState.getposicion()[2];
		    		int nuevaEsquina=0;
		    		//Asigna la esquina en la que debe bajar según el subcuadrante en el que se encuentra
		    		switch(agState.getposicion()[1]){
		    		case 1:
		    			if(subcuadrante==1)nuevaEsquina=2;
		    			else if(subcuadrante==2)nuevaEsquina=10;
		    			else if(subcuadrante==3)nuevaEsquina=29;
		    			else nuevaEsquina=31;
		    			break;
		    		case 2:
		    			if(subcuadrante==1)nuevaEsquina=12;
		    			else if(subcuadrante==2)nuevaEsquina=14;
		    			else if(subcuadrante==3)nuevaEsquina=33;
		    			else nuevaEsquina=35;
		    			break;
		    		case 3:
		    			if(subcuadrante==1)nuevaEsquina=38;
		    			else if(subcuadrante==2)nuevaEsquina=40;
		    			else if(subcuadrante==3)nuevaEsquina=61;
		    			else nuevaEsquina=63;
		    			break;
		    		case 4:
		    			if(subcuadrante==1)nuevaEsquina=47;
		    			else if(subcuadrante==2)nuevaEsquina=49;
		    			else if(subcuadrante==3)nuevaEsquina=66;
		    			else nuevaEsquina=68;
		    			break;
		    		}
		    		
		    		if(agState.getlistaEsquinasVisitadas()[nuevaEsquina]<=50){
			            agState.incrementarEnergiaUsada(CostoBajar);
			        	agState.irNivelBajo();
			    		return agState;
		    		}
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

        //Sólo podrá ir al nivel bajo si se encuentra en el nivel medio
        if(agState.getposicion()[0]==1){
        	agState.irNivelBajo();
        	environmentState.irNivelBajo();
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
        return "IrNivelBajo";
    }
}