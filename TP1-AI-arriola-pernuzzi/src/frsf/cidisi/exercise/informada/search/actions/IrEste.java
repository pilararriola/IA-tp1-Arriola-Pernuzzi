package frsf.cidisi.exercise.informada.search.actions;

import java.util.ArrayList;

import frsf.cidisi.exercise.entidades.Esquina;
import frsf.cidisi.exercise.entidades.Subcuadrante;
import frsf.cidisi.exercise.informada.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrEste extends SearchAction {
	private static final double CostoDesplazamiento = 0;
    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoDrone agState = (EstadoDrone) s;
        
        if(1000-agState.getenergiaUsada()>=CostoDesplazamiento){
        	int idCuadrante=agState.getposicion()[1];
        	int idSubcuadrante=agState.getposicion()[2];
        	int proxIdCuadrante=idCuadrante+1;
        	int proxIdSubcuadrante=idSubcuadrante+1;
        	int[] esqIdentificadas = agState.getlistaEsquinasIdentificadas();
	        switch(agState.getposicion()[0]){
	        case 2: //Nivel alto
	        	//Puede moverse al este si no está en los cuadrantes 2 o 4
	        	if(idCuadrante==2 || idCuadrante==4){
		       		 return null;
		       	}
	        	//Si ya visitó dos veces el cuadrante en esta dirección, no puede volver a ir
	        	if(agState.getCuadrantesVisitados()[proxIdCuadrante]>=2) return null;
	        	/*ArrayList<Subcuadrante> subcuadrantes = agState.getlistaCuadrantesEnDrone().get(idCuadrante-1).getlistaSubcuadrantes();
	        	for(Subcuadrante subcuadrante : subcuadrantes){
	        		ArrayList<Esquina> esquinas = subcuadrante.getlistaEsquinas();
	        		for(Esquina esquina : esquinas){
	        			if(esqIdentificadas[esquina.getidEsquina()]==0) return null;
	        		}
	        	}*/
	        	break;
	        case 1: //Nivel medio
	        	//Puede moverse al este si no está en los subcuadrantes que limitan al este
	        	//dentro de cada cuadrante
	    		if(idSubcuadrante==2 || agState.getposicion()[2]==4){
	    			return null;
	    		}
	    		//Si ya visitó dos veces el cuadrante en esta dirección, no puede volver a ir
	        	if(agState.getCuadrantesVisitados()[proxIdSubcuadrante]>=2) return null;
	        	
	    		/*ArrayList<Esquina> esquinas = agState.getlistaCuadrantesEnDrone().get(idCuadrante-1).getlistaSubcuadrantes().get(idSubcuadrante-1).getlistaEsquinas();
	    		for(Esquina esquina : esquinas){
	    			if(esqIdentificadas[esquina.getidEsquina()]==0) return null;
	    		}*/
	    		//Si el próximo subcuadrante al que se puede mover en esta dirección ya tiene todas sus 
	    		//esquinas identificadas, no se le permite ir
	    		
	    		ArrayList<Esquina> proxEsquinas = agState.getlistaCuadrantesEnDrone().get(idCuadrante-1).getlistaSubcuadrantes().get(proxIdSubcuadrante-1).getlistaEsquinas();
	    		for(Esquina esquina : proxEsquinas){
	    			if(esqIdentificadas[esquina.getidEsquina()]==0){
	    		        agState.incrementarEnergiaUsada(CostoDesplazamiento);
	    		        agState.aumentarDesplazamientos();
	    		        agState.irEste();
	    				return agState;
	    			}
	    		}
	    		return null;
	        case 0: //Nivel bajo
	        	//No puede moverse al este si se encuentra en las esquinas del límite este del mapa o
	        	//si se encuentra en la esquina 27 (la cual no tiene calle hacia el este)
	        	int esqAdyacenteEste=agState.getesquinasAdyacentes()[3];
	        	if(esqAdyacenteEste==0){
	        		return null;
	        	}
	        	//El agente no puede moverse a otra esquina si no identificó la actual
	        	int esquinaActual=agState.getposicion()[3];
	        	if(esqIdentificadas[esquinaActual]==0) return null;
	        	if(agState.getlistaEsquinasVisitadas()[esqAdyacenteEste]>2){
	        		return null;
	        	}
	        	//Tampoco podrá moverse al este si la esquina adyacente en esa dirección 
	        	//no pertenece al mismo subcuadrante
	        	ArrayList<Esquina> esqSubcuadrante= agState.getlistaCuadrantesEnDrone().get(agState.getposicion()[1]-1).getlistaSubcuadrantes().get(agState.getposicion()[2]-1).getlistaEsquinas(); 
	        	int size=esqSubcuadrante.size();
	        	int i=0;
	        	while(i<size){
	            	if(esqAdyacenteEste==esqSubcuadrante.get(i).getidEsquina()){
	                    agState.incrementarEnergiaUsada(CostoDesplazamiento);
	                    agState.aumentarDesplazamientos();
	            		agState.irEste();
	            		return agState;
	            	}
	        		i++;
	        	}
	        	if(i==size)return null;
	        	break;
	        }
	        agState.incrementarEnergiaUsada(CostoDesplazamiento);
	        agState.aumentarDesplazamientos();
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
    	int idCuadrante=agState.getposicion()[1];
    	int idSubcuadrante=agState.getposicion()[2];
    	int proxIdCuadrante=idCuadrante+1;
    	int proxIdSubcuadrante=idSubcuadrante+1;
    	int[] esqIdentificadas = agState.getlistaEsquinasIdentificadas();       

        switch(agState.getposicion()[0]){
        case 2: //Nivel alto
        	//Puede moverse al este si no está en los cuadrantes 2 o 4
        	if(idCuadrante==2 || idCuadrante==4){
	       		 return null;
	       	}
        	//Si ya visitó dos veces el cuadrante en esta dirección, no puede volver a ir
        	if(agState.getCuadrantesVisitados()[proxIdCuadrante]>=2) return null;
        	
        	/*ArrayList<Subcuadrante> subcuadrantes = agState.getlistaCuadrantesEnDrone().get(idCuadrante-1).getlistaSubcuadrantes();
        	for(Subcuadrante subcuadrante : subcuadrantes){
        		ArrayList<Esquina> esquinas = subcuadrante.getlistaEsquinas();
        		for(Esquina esquina : esquinas){
        			if(esqIdentificadas[esquina.getidEsquina()]==0) return null;
        		}
        	}*/
        	break;
        case 1: //Nivel medio
        	//Puede moverse al este si no está en los subcuadrantes que limitan al este
        	//dentro de cada cuadrante
    		if(idSubcuadrante==2 || agState.getposicion()[2]==4){
    			return null;
    		}
        	//Si ya visitó dos veces el cuadrante en esta dirección, no puede volver a ir
        	if(agState.getCuadrantesVisitados()[proxIdSubcuadrante]>=2) return null;
        	
    		/*ArrayList<Esquina> esquinas = agState.getlistaCuadrantesEnDrone().get(idCuadrante-1).getlistaSubcuadrantes().get(idSubcuadrante-1).getlistaEsquinas();
    		for(Esquina esquina : esquinas){
    			if(esqIdentificadas[esquina.getidEsquina()]==0) return null;
    		}*/
    		//Si el próximo subcuadrante al que se puede mover en esta dirección ya tiene todas sus 
    		//esquinas identificadas, no se le permite ir
    		ArrayList<Esquina> proxEsquinas = agState.getlistaCuadrantesEnDrone().get(idCuadrante-1).getlistaSubcuadrantes().get(proxIdSubcuadrante-1).getlistaEsquinas();
    		for(Esquina esquina : proxEsquinas){
    			if(esqIdentificadas[esquina.getidEsquina()]==0){
            		agState.irEste();
            		environmentState.irEste();
            		return environmentState;
    			}
    		}
    		return null;
        case 0: //Nivel bajo
        	//No puede moverse al este si se encuentra en las esquinas del límite este del mapa o
        	//si se encuentra en la esquina 27 (la cual no tiene calle hacia el este)
        	int esqAdyacenteEste=agState.getesquinasAdyacentes()[3];
        	if(esqAdyacenteEste==0){
        		return null;
        	}
        	//El agente no puede moverse a otra esquina si no identificó la actual
        	int esquinaActual=agState.getposicion()[3];
        	if(esqIdentificadas[esquinaActual]==0) return null;
        	if(agState.getlistaEsquinasVisitadas()[esqAdyacenteEste]>2){
        		return null;
        	}
        	//Tampoco podrá moverse al este si la esquina adyacente en esa dirección 
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