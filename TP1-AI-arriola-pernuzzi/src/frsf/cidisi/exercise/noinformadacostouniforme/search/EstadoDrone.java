package frsf.cidisi.exercise.noinformadacostouniforme.search;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class EstadoDrone extends SearchBasedAgentState {
	
	//Setup Variables
    private int energia;
    private int[] posicion;
    private int[] esquinasAdyacentes;
    private int[] intensidadSenial;
    private boolean victimario;//True si el victimario se encuentra en la misma posicion que el drone, False caso contrario
	

    public EstadoDrone() {
    
        this.initState();
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        
		EstadoDrone nuevoEstado= new EstadoDrone();
		//Copio la energia
		int energia2= this.getenergia();
		//Copio la posicion
		int [] posicion2= this.getposicion();
		//Copio esquinas adyacentes
		int[] esquinasAdyacentes2=this.getesquinasAdyacentes();
		//Copio intensidad senial
		int [] intensidadSenial2= this.getintensidadSenial();
		//Copio victimario
		boolean victimario2= this.getvictimario();
		
		//Seteamos
		nuevoEstado.setenergia(energia2);
		nuevoEstado.setposicion(posicion2);
		nuevoEstado.setesquinasAdyacentes(esquinasAdyacentes2);
		nuevoEstado.setintensidadSenial(intensidadSenial2);
		nuevoEstado.setvictimario(victimario2);
		
		
        return nuevoEstado;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
        DronePerception dronePercepcion= (DronePerception) p; //seteamos a drone perception
        //Actualiza la posicion con el gps
        //Actualiza la intensidad de señales con la antena
        //Actualiza esquinas adyacentes con la camara
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        
    	energia = 1000; //energia inicial
		
		/*
		  Posicion: vector de 4 valores que se corresponden con:
		 	0: Altitud: 0 - Nivel Bajo
		 				1 - Nivel Medio
		 				2 - Nivel Alto
		 	1: Cuadrante: Numero de cuadrante en el nivel alto (1 - 2 - 3 - 4)
		 	2: Subcuadrante: Numero de subcuadrante en el nivel medio (1 - 2 - 3 - 4)
		 	3: Esquina: Numero de esquina en la que se ubica el agente en el nivel bajo (1 - ... - 78)
		 * */
		posicion = new int[]{2,1,1,1}; //Se debería setear de acuerdoo al escenario
		
		/*
		 esquinasAdyacentes: vector de 8 posiciones, que se corresponde a las 8 esquinas adyacentes 
		 a la posicion actual del drone, si el mismo se encuentra en el nivel bajo, 
		 segun las siguientes direcciones:
		 [norte,noreste,este,sureste,sur,suroeste,oeste,noroeste]
		 El contenido en cada posicion del array sera:
		 -idEsquina si puede trasladarse en esa direccion
		 -0 caso contrario
		 */
		esquinasAdyacentes= new int[9]; 
		intensidadSenial = new int[]{30,20,0,0};//Se debería setear de acuerdoo al escenario
		victimario= false;
		

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
     public int[] getesquinasAdyacentes(){
        return esquinasAdyacentes;
     }
     public void setesquinasAdyacentes(int[] arg){
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
     public void setvictimario(boolean arg){
        victimario = arg;
     }
	
}

