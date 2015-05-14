package frsf.cidisi.exercise.noinformadacostouniforme.search;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class EstadoDrone extends SearchBasedAgentState {
	
	//Variables de estado
    private int energia;
    private int[] posicion;
    private int[] esquinasAdyacentes;
    private int[] intensidadSenial;
    private boolean victimario;
	

    public EstadoDrone() {
    
        this.initState();
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
    	int i;
    	EstadoDrone nuevoEstado= new EstadoDrone();
		//Copiamos la energia
		int energiaCopia= this.getenergia();
		
		//Copiamos la posicion
		int [] posicionCopia= new int[4]; 
		for(i=0;i<4;i++){
			posicionCopia[i]=this.getposicion()[i];
		}
		//Copiamos esquinas adyacentes
		int[] esquinasAdyacentesCopia= new int[9];
		for(i=0;i<9;i++){
			esquinasAdyacentesCopia[i]=this.getesquinasAdyacentes()[i];
		}
		
		//Copiamos intensidad senial
		int [] intensidadSenialCopia= new int[4];
		for(i=0;i<4;i++){
			intensidadSenialCopia[i]=this.getintensidadSenial()[i];
		}
		
		//Copiamos victimario
		boolean victimarioCopia= this.getvictimario();
		
		//Seteamos
		nuevoEstado.setenergia(energiaCopia);
		nuevoEstado.setposicion(posicionCopia);
		nuevoEstado.setesquinasAdyacentes(esquinasAdyacentesCopia);
		nuevoEstado.setintensidadSenial(intensidadSenialCopia);
		nuevoEstado.setvictimario(victimarioCopia);
		
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
		posicion = new int[]{2,0,0,0}; //Siempre se inicia en el nivel alto, el resto se setea según el escenario
		
		/*
		 esquinasAdyacentes: vector de 8 posiciones, que se corresponde a las 8 esquinas adyacentes 
		 a la posicion actual del drone, si el mismo se encuentra en el nivel bajo, 
		 segun las siguientes direcciones:
		 [norte,noreste,este,sureste,sur,suroeste,oeste,noroeste]
		 El contenido en cada posicion del array sera:
		 -idEsquina si puede trasladarse en esa direccion
		 -0 caso contrario
		 */
		esquinasAdyacentes= new int[]{0,0,0,0,0,0,0,0,0}; //Se va a setear cuando se llegue al nivel bajo
		intensidadSenial = new int[]{0,0,0,0};//Se debería setear de acuerdoo al escenario
		victimario= false;

    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	int[] pos= this.getposicion();
    	int[] esqAdy= this.getesquinasAdyacentes() ;
    	int[] seniales= this.getintensidadSenial();
        String str = "----- ESTADO DEL DRONE -----\n";
        str += "Nivel: " + pos[0] + "\n"
        	+  "Cuadrante: "+ pos[1] + "\n" 
        	+  "Subcuadrante: "+ pos[2] + "\n"  
        	+  "Esquina: "+ pos[3] + "\n" 
        	+  "Energia: "+ this.getenergia() + "\n" 
        	+  "Esquinas adyacentes: (";
        for(int i=0;i<9;i++){
        	str += esqAdy[i];
        	if(i<8)str += " , ";
        }
        str += ")\n" 
        	+ "Intensidad señales recibidas: (";
        for(int i=0;i<4;i++){
        	str += seniales[i];
        	if(i<3)str += " , ";
        }
        str += ")\n" 
        	+ "Victimario encontrado: "+ this.getvictimario()+ "\n";
        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
       
    	//Si es instancia de EstadoDrone y la posición es la misma, el objeto va a ser igual
        if ((obj instanceof EstadoDrone)) {
        	if(((EstadoDrone) obj).getposicion() == this.getposicion()){
        		return true;
        	}
        }
        //Si no se cumplen las dos condiciones anteriores, retorna false
        return false;
        
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

