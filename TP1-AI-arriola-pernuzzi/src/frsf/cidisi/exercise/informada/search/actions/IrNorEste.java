package frsf.cidisi.exercise.informada.search.actions;

import java.util.ArrayList;

import frsf.cidisi.exercise.entidades.Esquina;
import frsf.cidisi.exercise.entidades.Subcuadrante;
import frsf.cidisi.exercise.informada.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrNorEste extends SearchAction {
	private static final int CostoDesplazamiento = 0;
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
        	int proxIdCuadrante=idCuadrante-1;
        	int proxIdSubcuadrante=idSubcuadrante-1;
        	int[] esqIdentificadas = agState.getlistaEsquinasIdentificadas();
        	switch(agState.getposicion()[0]){
	        case 2: //Nivel alto
	        	//Puede moverse al noreste únicamente si se encuentra en el cuadrante 3
	        	if(idCuadrante != 3){
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
	        	//Puede moverse al noreste sólo si está en el subcuadrante 3 de cada cuadrante
	    		if(idSubcuadrante!=3){
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
	    		        agState.irNorEste();
	    				return agState;
	    			}
	    		}
	    		return null;
	        case 0: //Nivel bajo
	        	//No puede moverse al noreste si no existen esquinas adyacentes en esa dirección
	        	int esqAdyacenteNorEste=agState.getesquinasAdyacentes()[2];
	        	if(esqAdyacenteNorEste==0){
	        		return null;
	        	}
	        	//El agente no puede moverse a otra esquina si no identificó la actual
	        	int esquinaActual=agState.getposicion()[3];
	        	if(esqIdentificadas[esquinaActual]==0) return null;
	        	if(agState.getlistaEsquinasVisitadas()[esqAdyacenteNorEste]>2){
	        		return null;
	        	}
	        	//En el mapa no existen esquinas que tengan calles hacia el noreste, por lo tanto
	        	//en este caso siempre va a retornar null 
	        	break;
	        }
	        agState.incrementarEnergiaUsada(CostoDesplazamiento);
	        agState.aumentarDesplazamientos();
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
    	int idCuadrante=agState.getposicion()[1];
    	int idSubcuadrante=agState.getposicion()[2];
    	int proxIdCuadrante=idCuadrante-1;
    	int proxIdSubcuadrante=idSubcuadrante-1;
    	int[] esqIdentificadas = agState.getlistaEsquinasIdentificadas();
    	
        switch(agState.getposicion()[0]){
        case 2: //Nivel alto
        	//Puede moverse al noreste únicamente si se encuentra en el cuadrante 3
        	if(idCuadrante!=3){
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
        	//Puede moverse al noreste sólo si está en el subcuadrante 3 de cada cuadrante
    		if(idSubcuadrante!=3){
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
    				agState.irNorEste();
    				environmentState.irNorEste();
    				return environmentState;
    			}
    		}
    		return null;
        case 0: //Nivel bajo
        	//No puede moverse al noreste si no existen esquinas adyacentes en esa dirección
        	int esqAdyacenteNorEste=agState.getesquinasAdyacentes()[2];
        	if(esqAdyacenteNorEste==0){
        		return null;
        	}
        	//El agente no puede moverse a otra esquina si no identificó la actual
        	int esquinaActual=agState.getposicion()[3];
        	if(esqIdentificadas[esquinaActual]==0) return null;
        	if(agState.getlistaEsquinasVisitadas()[esqAdyacenteNorEste]>2){
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