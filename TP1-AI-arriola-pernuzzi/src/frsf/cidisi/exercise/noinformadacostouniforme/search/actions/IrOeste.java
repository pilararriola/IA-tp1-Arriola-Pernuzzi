package frsf.cidisi.exercise.noinformadacostouniforme.search.actions;

import java.util.ArrayList;

import frsf.cidisi.exercise.entidades.Esquina;
import frsf.cidisi.exercise.noinformadacostouniforme.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrOeste extends SearchAction {
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
	        	//Puede moverse al oeste si no está en los cuadrantes 1 o 3
	        	if(agState.getposicion()[1]==1 || agState.getposicion()[1]==3){
	        		 return null;
	        	}
	        	break;
	        case 1: //Nivel medio
	        	//Puede moverse al oeste si no está en los subcuadrantes que limitan al oeste
	        	//dentro de cada cuadrante
	    		if(agState.getposicion()[2]==1 || agState.getposicion()[2]==3){
	    			return null;
	    		}
	        	
	        	break;
	        case 0: //Nivel bajo
	        	//No puede moverse al norte si no existen esquinas adyacentes en esa dirección
	        	int esqAdyacenteOeste=agState.getesquinasAdyacentes()[7];//Depende de la orientación(array 9 pos)
	        	if(esqAdyacenteOeste==0){
	        		return null;
	        	}
	        	
	        	//Tampoco podrá moverse al norte si la esquina adyacente en esa dirección 
	        	//no pertenece al mismo subcuadrante
	        	ArrayList<Esquina> esqSubcuadrante= agState.getlistaCuadrantesEnDrone().get(agState.getposicion()[1]-1).getlistaSubcuadrantes().get(agState.getposicion()[2]-1).getlistaEsquinas(); 
	        	int size=esqSubcuadrante.size();
	        	int i=0;
	        	while(i<size){
	            	if(esqAdyacenteOeste==esqSubcuadrante.get(i).getidEsquina()){
	                    agState.incrementarEnergiaUsada(CostoDesplazamiento);
	            		agState.irOeste();
	            		return agState;
	            	}
	        		i++;
	        	}
	        	if(i==size)return null;
	        	break;
	        }
	        agState.incrementarEnergiaUsada(CostoDesplazamiento);
	        agState.irOeste();
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
        	//Puede moverse al oeste si no está en los cuadrantes 1 o 3
        	if(agState.getposicion()[1]==1 || agState.getposicion()[1]==3){
        		 return null;
        	}
        	break;
        case 1: //Nivel medio
        	//Puede moverse al oeste si no está en los subcuadrantes que limitan al oeste
        	//dentro de cada cuadrante
    		if(agState.getposicion()[2]==1 || agState.getposicion()[2]==3){
    			return null;
    		}
        	
        	break;
        case 0: //Nivel bajo
        	//No puede moverse al norte si no existen esquinas adyacentes en esa dirección
        	int esqAdyacenteOeste=agState.getesquinasAdyacentes()[7];//Depende de la orientación(array 9 pos)
        	if(esqAdyacenteOeste==0){
        		return null;
        	}
        	
        	//Tampoco podrá moverse al norte si la esquina adyacente en esa dirección 
        	//no pertenece al mismo subcuadrante
        	ArrayList<Esquina> esqSubcuadrante= agState.getlistaCuadrantesEnDrone().get(agState.getposicion()[1]-1).getlistaSubcuadrantes().get(agState.getposicion()[2]-1).getlistaEsquinas(); 
        	int size=esqSubcuadrante.size();
        	int i=0;
        	while(i<size){
            	if(esqAdyacenteOeste==esqSubcuadrante.get(i).getidEsquina()){
            		agState.irOeste();
            		environmentState.irOeste();
            		return environmentState;
            	}
        		i++;
        	}
        	if(i==size)return null;
        	break;
        }
		agState.irOeste();
		environmentState.irOeste();
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
        return "IrOeste";
    }
}