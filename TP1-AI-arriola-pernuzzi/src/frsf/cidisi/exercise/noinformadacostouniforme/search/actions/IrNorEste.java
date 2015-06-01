package frsf.cidisi.exercise.noinformadacostouniforme.search.actions;

import frsf.cidisi.exercise.noinformadacostouniforme.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrNorEste extends SearchAction {
	private static final int CostoDesplazamiento = 150;
    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoDrone agState = (EstadoDrone) s; 
        
        if(1000-agState.getenergiaUsada()>=CostoDesplazamiento){
	        switch(agState.getposicion()[0]){
	        case 2: //Nivel alto
	        	//Puede moverse al noreste únicamente si se encuentra en el cuadrante 3
	        	if(agState.getposicion()[1]!=3){
	        		 return null;
	        	}
	        	break;
	        case 1: //Nivel medio
	        	//Puede moverse al noreste sólo si está en el subcuadrante 3 de cada cuadrante
	    		if(agState.getposicion()[2]!=3){
	    			return null;
	    		}
	        	break;
	        case 0: //Nivel bajo
	        	//No puede moverse al noreste si no existen esquinas adyacentes en esa dirección
	        	int esqAdyacenteNorEste=agState.getesquinasAdyacentes()[2];
	        	if(esqAdyacenteNorEste==0){
	        		return null;
	        	}
	        	//En el mapa no existen esquinas que tengan calles hacia el noreste, por lo tanto
	        	//en este caso siempre va a retornar null 
	        	break;
	        }
	        agState.incrementarEnergiaUsada(CostoDesplazamiento);
			agState.irNorEste();
			return agState;
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

        switch(agState.getposicion()[0]){
        case 2: //Nivel alto
        	//Puede moverse al noreste únicamente si se encuentra en el cuadrante 3
        	if(agState.getposicion()[1]!=3){
        		 return null;
        	}
        	break;
        case 1: //Nivel medio
        	//Puede moverse al noreste sólo si está en el subcuadrante 3 de cada cuadrante
    		if(agState.getposicion()[2]!=3){
    			return null;
    		}
        	break;
        case 0: //Nivel bajo
        	//No puede moverse al noreste si no existen esquinas adyacentes en esa dirección
        	int esqAdyacenteNorEste=agState.getesquinasAdyacentes()[2];
        	if(esqAdyacenteNorEste==0){
        		return null;
        	}
        	//En el mapa no existen esquinas que tengan calles hacia el noreste, por lo tanto
        	//en este caso siempre va a retornar null 
        	break;
        }
		agState.irNorEste();
		environmentState.irNorEste();
		return environmentState;
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
        return "IrNorEste";
    }
}