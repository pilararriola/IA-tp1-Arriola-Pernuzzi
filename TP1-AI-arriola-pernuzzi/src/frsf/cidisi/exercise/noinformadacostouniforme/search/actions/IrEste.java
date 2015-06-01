package frsf.cidisi.exercise.noinformadacostouniforme.search.actions;

import java.util.ArrayList;

import frsf.cidisi.exercise.entidades.Esquina;
import frsf.cidisi.exercise.noinformadacostouniforme.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrEste extends SearchAction {
	private static final double CostoDesplazamiento = 150;
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
	        	//Puede moverse al este si no est� en los cuadrantes 2 o 4
	        	if(agState.getposicion()[1]==2 || agState.getposicion()[1]==4){
	        		 return null;
	        	}
	        	break;
	        case 1: //Nivel medio
	        	//Puede moverse al este si no est� en los subcuadrantes que limitan al este
	        	//dentro de cada cuadrante
	    		if(agState.getposicion()[2]==2 || agState.getposicion()[2]==4){
	    			return null;
	    		}
	        	
	        	break;
	        case 0: //Nivel bajo
	        	//No puede moverse al este si se encuentra en las esquinas del l�mite este del mapa o
	        	//si se encuentra en la esquina 27 (la cual no tiene calle hacia el este)
	        	int esqAdyacenteEste=agState.getesquinasAdyacentes()[3];
	        	if(esqAdyacenteEste==0){
	        		return null;
	        	}
	        	
	        	//Tampoco podr� moverse al este si la esquina adyacente en esa direcci�n 
	        	//no pertenece al mismo subcuadrante
	        	ArrayList<Esquina> esqSubcuadrante= agState.getlistaCuadrantesEnDrone().get(agState.getposicion()[1]-1).getlistaSubcuadrantes().get(agState.getposicion()[2]-1).getlistaEsquinas(); 
	        	int size=esqSubcuadrante.size();
	        	int i=0;
	        	while(i<size){
	            	if(esqAdyacenteEste==esqSubcuadrante.get(i).getidEsquina()){
	                    agState.incrementarEnergiaUsada(CostoDesplazamiento);
	            		agState.irEste();
	            		return agState;
	            	}
	        		i++;
	        	}
	        	if(i==size)return null;
	        	break;
	        }
	        agState.incrementarEnergiaUsada(CostoDesplazamiento);
	        agState.irEste();
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
        	//Puede moverse al este si no est� en los cuadrantes 2 o 4
        	if(agState.getposicion()[1]==2 || agState.getposicion()[1]==4){
        		 return null;
        	}
        	break;
        case 1: //Nivel medio
        	//Puede moverse al este si no est� en los subcuadrantes que limitan al este
        	//dentro de cada cuadrante
    		if(agState.getposicion()[2]==2 || agState.getposicion()[2]==4){
    			return null;
    		}
        	break;
        case 0: //Nivel bajo
        	//No puede moverse al este si se encuentra en las esquinas del l�mite este del mapa o
        	//si se encuentra en la esquina 27 (la cual no tiene calle hacia el este)
        	int esqAdyacenteEste=agState.getesquinasAdyacentes()[3];
        	if(esqAdyacenteEste==0){
        		return null;
        	}
        	
        	//Tampoco podr� moverse al este si la esquina adyacente en esa direcci�n 
        	//no pertenece al mismo subcuadrante
        	ArrayList<Esquina> esqSubcuadrante= agState.getlistaCuadrantesEnDrone().get(agState.getposicion()[1]-1).getlistaSubcuadrantes().get(agState.getposicion()[2]-1).getlistaEsquinas(); 
        	int size=esqSubcuadrante.size();
        	int i=0;
        	while(i<size){
            	if(esqAdyacenteEste==esqSubcuadrante.get(i).getidEsquina()){
            		agState.irEste();
            		environmentState.irEste();
            		return environmentState;
            	}
        		i++;
        	}
        	if(i==size)return null;
        	break;
        }
		agState.irEste();
		environmentState.irEste();
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
        return "IrEste";
    }
}