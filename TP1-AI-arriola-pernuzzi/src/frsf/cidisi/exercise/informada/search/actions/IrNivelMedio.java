package frsf.cidisi.exercise.informada.search.actions;

import java.util.ArrayList;

import frsf.cidisi.exercise.entidades.Esquina;
import frsf.cidisi.exercise.entidades.Subcuadrante;
import frsf.cidisi.exercise.informada.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrNivelMedio extends SearchAction {
	private static final double CostoSubir = 0;
	private static final double CostoBajar = 0;
    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoDrone agState = (EstadoDrone) s;
        if(agState.getposicion()[0]==2 && (1000-agState.getenergiaUsada()>=CostoBajar)){
        	agState.incrementarEnergiaUsada(CostoBajar);
        	agState.irNivelMedio();
        	return agState;
        }
        if(agState.getposicion()[0]==0 && (1000-agState.getenergiaUsada()>=CostoSubir)){
        	int idCuadrante=agState.getposicion()[1];
        	int idSubcuadrante=agState.getposicion()[2];
        	int[] esqIdentificadas = agState.getlistaEsquinasIdentificadas(); 
        	
    		ArrayList<Esquina> esquinas = agState.getlistaCuadrantesEnDrone().get(idCuadrante-1).getlistaSubcuadrantes().get(idSubcuadrante-1).getlistaEsquinas();
    		for(Esquina esquina : esquinas){
    			if(esqIdentificadas[esquina.getidEsquina()]==0) return null;
    		}
        	agState.incrementarEnergiaUsada(CostoSubir);
        	agState.irNivelMedio();
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

        if(agState.getposicion()[0]==2){
        	agState.irNivelMedio();
        	environmentState.irNivelMedio();
        	ArrayList<Esquina> listaEsquinas;
        	ArrayList<Subcuadrante> listaSubcuadrantes;
        	int[][] antena=agState.getintensidadSenial();
        	int idCuadrante=agState.getposicion()[1];
        	int idSubcuadrante=agState.getposicion()[2];
    		for(int i=0;i<4;i++){
    			if(i!=(idCuadrante-1)){
					listaSubcuadrantes=agState.getlistaCuadrantesEnDrone().get(i).getlistaSubcuadrantes();
    				for(int j=0;j<4;j++){	
						listaEsquinas= listaSubcuadrantes.get(j).getlistaEsquinas();
	    				for(Esquina esq : listaEsquinas){
	    					agState.getlistaEsquinasIdentificadas()[esq.getidEsquina()]=1;
	    				}
    				}
    			}
    		}
        	
    		int subcuadranteMayorSenial=3;
    		agState.setSubcuadranteMayor(subcuadranteMayorSenial);
        	//Busco cuál es el subcuadrante que mayor señal tiene
    		
    		
			//Seteo como visitadas las esquinas de todos los subcuadrantes que no sean el que mayor señal tiene
    		for(int i=0;i<4;i++){
    			if(i!=subcuadranteMayorSenial-1){
    				listaEsquinas= agState.getlistaCuadrantesEnDrone().get(idCuadrante-1).getlistaSubcuadrantes().get(i).getlistaEsquinas();
    				for(Esquina esq : listaEsquinas){
    					agState.getlistaEsquinasIdentificadas()[esq.getidEsquina()]=1;
    				}
    			}
    		}
    		//Seteo como NO visitadas las esquinas del subcuadrante que mayor señal tiene
    		listaEsquinas= agState.getlistaCuadrantesEnDrone().get(idCuadrante-1).getlistaSubcuadrantes().get(subcuadranteMayorSenial-1).getlistaEsquinas();
			for(Esquina esq : listaEsquinas){
				agState.getlistaEsquinasIdentificadas()[esq.getidEsquina()]=0;
			}
        	return environmentState;
        }
        if(agState.getposicion()[0]==0){
        	int idCuadrante=agState.getposicion()[1];
        	int idSubcuadrante=agState.getposicion()[2];
        	int[] esqIdentificadas = agState.getlistaEsquinasIdentificadas(); 
        	
    		ArrayList<Esquina> esquinas = agState.getlistaCuadrantesEnDrone().get(idCuadrante-1).getlistaSubcuadrantes().get(idSubcuadrante-1).getlistaEsquinas();
    		for(Esquina esquina : esquinas){
    			if(esqIdentificadas[esquina.getidEsquina()]==0) return null;
    		}
        	agState.irNivelMedio();
        	environmentState.irNivelMedio();
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
        return "IrNivelMedio";
    }
}