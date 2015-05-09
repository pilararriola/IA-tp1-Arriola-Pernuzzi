package frsf.cidisi.exercise.noinformadacostouniforme.search;

import java.util.ArrayList;
import java.util.List;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class DroneEstado extends SearchBasedAgentState {
	
	//TODO: Setup Variables
    private int energia;
    private int[] posicion;
    private List<Integer> esquinasAdyacentes;
    private int[] intensidadSenial;
    boolean victimario;//True si el victimario se encuentra en la misma posicion que el drone, False caso contrario
	

    public DroneEstado() {
    
		this.initState();
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        
		//TODO: Complete Method
		
        return null;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
        
        //TODO: Complete Method
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        
	//TODO: Complete Method
    	
    	energia = 1000;
		
		/*
		 * Posicion: vector de 4 valores que se corresponden con:
		 * 	0: Altitud: 0 - Nivel Bajo
		 * 				1 - Nivel Medio
		 * 				2 - Nivel Alto
		 * 	1: Cuadrante: Numero de cuadrante en el nivel alto (1 - 2 - 3 - 4)
		 * 	2: Subcuadrante: Numero de subcuadrante en el nivel medio (1 - 2 - 3 - 4)
		 * 	3: Esquina: Numero de esquina en la que se ubica el agente en el nivel bajo (1 - ... - 78)
		 * */
		posicion = new int[]{2,1,1,1}; //Se debería setear de acuerdoo al escenario
		
		/*
		 * Lista de esquinas adyacentes a la que se encuentra el agente actualmente
		 * Se carga sólo cuando se encuentra en el nivel bajo
		 */
		esquinasAdyacentes = new ArrayList<Integer>();
		
		/*
		 * Intensidad de la señal proveniente de cada cuadrante o subcuadrante 
		 * (depende el nivel de altitud en el que se encuentre)
		 * Cada posición en el array se corresponde con el número de cuadrante - 1
		 * */
		intensidadSenial = new int[]{30,20,0,0};//Se debería setear de acuerdoo al escenario

    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
        String str = "";

        //TODO: Complete Method

        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
       
       //TODO: Complete Method
        
        return true;
    }

    //TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
   	
     public int getenergia(){
        return energia;
     }
     public void setenergia(int arg){
        energia = arg;
     }
     public int[] getposicion(){
        return posicion;
     }
     public void setposicion(int[] arg){
        posicion = arg;
     }
     public List<Integer> getesquinasAdyacentes(){
        return esquinasAdyacentes;
     }
     public void setesquinasAdyacentes(List<Integer> arg){
        esquinasAdyacentes = arg;
     }
     public int[] getintensidadSenial(){
        return intensidadSenial;
     }
     public void setintensidadSenial(int[] arg){
        intensidadSenial = arg;
     }
     public boolean getvictimario(){
         return victimario;
      }
	
}

