package frsf.cidisi.exercise.noinformadaprofundidad.search;

import java.util.ArrayList;

import frsf.cidisi.exercise.entidades.Cuadrante;
import frsf.cidisi.exercise.entidades.Esquina;
import frsf.cidisi.exercise.entidades.Mapa;
import frsf.cidisi.exercise.noinformadaprofundidad.search.DronePerception;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class EstadoDrone extends SearchBasedAgentState {
	
	//Variables de estado
	private int accionesRecorridas;
    private double energiaUsada;
    private int[] posicion;
    private int[] esquinasAdyacentes;
    private int[] intensidadSenial;
    private boolean victimario;
    private int[] vistaLineaRecta;
    private ArrayList<Esquina> listaEsquinasEnDrone;
    private ArrayList<Cuadrante> listaCuadrantesEnDrone;
    private int[] listaEsquinasVisitadas;
    private int[] listaEsquinasIdentificadas;
	

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
		//Copiamos la energia usada
		double energiaUsadaCopia= this.getenergiaUsada();
		
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
		
		//Copiamos vista línea recta
		int[] vistaLineaRectaCopia= new int[9];
		for(i=0;i<9;i++){
			vistaLineaRectaCopia[i]=this.getvistaLineaRecta()[i];
		}
		
		//Copiamos la lista de esquinas identificadas hasta el momento
		int[] listaEsquinasIdentificadasCopia= new int[79];
		for(i=0;i<79;i++){
			listaEsquinasIdentificadasCopia[i]=getlistaEsquinasIdentificadas()[i];
		}

		//Copiamos la lista de esquinas visitadas hasta el momento
		int[] listaEsquinasVisitadasCopia= new int[79];
		for(i=0;i<79;i++){
			listaEsquinasVisitadasCopia[i]=getlistaEsquinasVisitadas()[i];
		}
		
		//Seteamos
		nuevoEstado.setenergiaUsada(energiaUsadaCopia);
		nuevoEstado.setposicion(posicionCopia);
		nuevoEstado.setesquinasAdyacentes(esquinasAdyacentesCopia);
		nuevoEstado.setintensidadSenial(intensidadSenialCopia);
		nuevoEstado.setvictimario(victimarioCopia);
		nuevoEstado.setvistaLineaRecta(vistaLineaRectaCopia);		
		nuevoEstado.setlistaEsquinasEnDrone(this.getlistaEsquinasEnDrone());
		nuevoEstado.setlistaCuadrantesEnDrone(this.getlistaCuadrantesEnDrone());
		nuevoEstado.setlistaEsquinasIdentificadas(listaEsquinasIdentificadasCopia);
		nuevoEstado.setlistaEsquinasVisitadas(listaEsquinasVisitadasCopia);
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
    	for(int i=0;i<4;i++){
    		this.posicion[i] = dronePercepcion.getgps()[i];
    	}
    	
        //Actualiza la intensidad de señales con la antena
    	for(int i=0;i<4;i++){
    		this.intensidadSenial[i] = dronePercepcion.getantena()[i];
    	}
    	
    	//Actualiza esquinas adyacentes con la camara
    	for(int i=0;i<9;i++){
    		this.esquinasAdyacentes[i] = obtenerEsquinasAdyacentes(dronePercepcion)[i];
    	}
    	
    	//Actualiza vista en línea recta con la camara
    	for(int i=0;i<9;i++){
    		this.vistaLineaRecta[i] = obtenerVistaLineaRecta(dronePercepcion)[i];
    	}
    	
    	//Actualiza victimario con la camara
    	if(this.vistaLineaRecta[0]==1) this.victimario = true;
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
    	energiaUsada = 0; //Al comienzo la energia utilizada es cero 
		
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
		 esquinasAdyacentes: vector de 9 posiciones que se utiliza cuando el drone está ubicado en
		 el nivel bajo. La primera posición se refiere a la esquina donde se encuentra el drone 
		 y, el resto, a las 8 esquinas adyacentes segun las siguientes direcciones:
		 [esquina actual, norte,noreste,este,sureste,sur,suroeste,oeste,noroeste]
		 El contenido en cada posicion del array sera:
		 -idEsquina si puede trasladarse en esa direccion
		 -0 caso contrario
		 */
		esquinasAdyacentes= new int[]{0,0,0,0,0,0,0,0,0}; //Se va a setear cuando se llegue al nivel bajo
		intensidadSenial = new int[]{0,0,0,0};//Se debería setear de acuerdoo al escenario
		victimario= false;
		
		/*
		  Vector que indica si el victimario se encuentra en las posiciones adyacentes al drone, 
		  incluyendo la posición del mismo
		 */
		vistaLineaRecta=new int[]{0,0,0,0,0,0,0,0,0};
		
		/*
		 Se setea la lista de esquinas tal cual está en el mapa para poder tener las esquinas adyacentes
		 a una esquina actual en el nivel bajo 
		 */
		Mapa mapaEnDrone = new Mapa();
		listaEsquinasEnDrone=new ArrayList<Esquina>();
		listaEsquinasEnDrone=mapaEnDrone.getListaEsquinas();
		listaCuadrantesEnDrone=new ArrayList<Cuadrante>();
		listaCuadrantesEnDrone= mapaEnDrone.getListaCuadrantes();
		/*
		 Array de enteros que indica si la esquina con el id correspondiente
		 a su posición fue identificada o no.
		 El índice 0 queda con el valor 0 porque no representa a ninguna esquina.
		 Desde el índice 1 hasta el 78 se representa cada una de las esquinas.
		 */
		listaEsquinasIdentificadas=new int[79];
		for(int i=0;i<79;i++)listaEsquinasIdentificadas[i]=0;	
		/*
		 Array de enteros que indica si la esquina con el id correspondiente
		 a su posición fue visitada o no y, de ser así, cuántas veces.
		 El índice 0 queda con el valor 0 porque no representa a ninguna esquina.
		 Desde el índice 1 hasta el 78 se representa cada una de las esquinas.
		 */
		listaEsquinasVisitadas=new int[79];
		for(int i=0;i<79;i++)listaEsquinasVisitadas[i]=0;	
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	int[] pos= this.getposicion();
    	int[] esqAdy= this.getesquinasAdyacentes() ;
    	int[] seniales= this.getintensidadSenial();
    	int[] vista= this.getvistaLineaRecta();
        String str = "----- ESTADO DEL DRONE -----\n";
        str += "Nivel: " + pos[0] + "\n"
        	+  "Cuadrante: "+ pos[1] + "\n" 
        	+  "Subcuadrante: "+ pos[2] + "\n"  
        	+  "Esquina: "+ pos[3] + "\n" 
        	+  "Energia usada: "+ this.getenergiaUsada() + "\n" 
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
        	+ "Victimario encontrado: "+ this.getvictimario()+ "\n"
        	+ "Intensidad señales recibidas: (";
        for(int i=0;i<9;i++){
        	str += vista[i];
        	if(i<8)str += " , ";
        }
        str += ")\n"
        	+ "Esquinas identificadas: (";
        for(int i=1;i<79;i++){
        	str += listaEsquinasIdentificadas[i];
        	if(i<78)str += " , ";
        }
        str += ")\n"
        	+ "Esquinas visitadas: (";
        for(int i=1;i<79;i++){
        	str += listaEsquinasVisitadas[i];
        	if(i<78)str += " , ";
        }
        str += ")\n";
        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
    	//Si el estado del agente perteneciente al nodo que se quiere comparar 
    	//con el actual es instancia de EstadoDrone, su posición es la misma y 
    	//contiene los mismos arrays de esquinas identificadas y visitadas, va a ser igual

        if (!(obj instanceof EstadoDrone)) {
        	return false; //No es igual porque no es instancia
        }	
        
    	
        for(int i=0; i<4;i++){
        	if(((EstadoDrone) obj).getposicion()[i] != this.getposicion()[i]){
        		return false; //Si no coinciden las posiciones es distinto
        	}
        }   
        
        //Comparar el siguiente array permite que no se eliminen los nodos de la acción 
        //Identificar victimario (que coinciden en el resto de los valores con el nodo que se  
        //haya guardado apenas se llegó a la esquina)
       for(int i=0; i<79;i++){
        	if(((EstadoDrone) obj).getlistaEsquinasIdentificadas()[i] != this.getlistaEsquinasIdentificadas()[i]){
        		return false; 
        	}
        }

        //Si no se cumplen las dos condiciones anteriores, retorna que es igual
        return true;  
    }


    // The following methods are agent-specific:
   	
   	
    public double getenergiaUsada(){
       return energiaUsada;
    }
    public void setenergiaUsada(double arg){
       energiaUsada = arg;
    }
    public void incrementarEnergiaUsada(double costodesplazamiento){
   	 this.energiaUsada += costodesplazamiento;
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
    public int[] getvistaLineaRecta(){
        return vistaLineaRecta;
     }
	  public void setvistaLineaRecta(int[] arg){
		  vistaLineaRecta = arg;
	  }
    public ArrayList<Esquina> getlistaEsquinasEnDrone(){
        return listaEsquinasEnDrone;
     }
	  public void setlistaEsquinasEnDrone(ArrayList<Esquina> arg){
		  listaEsquinasEnDrone = arg;
	  }
    public ArrayList<Cuadrante> getlistaCuadrantesEnDrone(){
        return listaCuadrantesEnDrone;
     }
	  public void setlistaCuadrantesEnDrone(ArrayList<Cuadrante> arg){
		  listaCuadrantesEnDrone = arg;
	  }

	  public int[] getlistaEsquinasIdentificadas() {
			return listaEsquinasIdentificadas;	
	  }
	  public void setlistaEsquinasIdentificadas(int[] arg){
		  listaEsquinasIdentificadas = arg;
	  }
	  public int[] getlistaEsquinasVisitadas() {
			return listaEsquinasVisitadas;	
	  }
	  public void setlistaEsquinasVisitadas(int[] arg){
		  listaEsquinasVisitadas = arg;
	  }
	  
	    private int[] obtenerEsquinasAdyacentes(DronePerception p){
	    	int[] arrayAux= new int[9];
	    	for(int i=0;i<9;i++){
	    		arrayAux[i]=p.getcamara()[0][i];	
	    	}
	    	return arrayAux;
	    }
	    private int[] obtenerVistaLineaRecta(DronePerception p){
	    	int[] arrayAux= new int[9];
	    	for(int i=0;i<9;i++){
	    		arrayAux[i]=p.getcamara()[1][i];	
	    	}
	    	return arrayAux;
	    }
	    
		public void irNorte() {
			accionesRecorridas++;
			switch(this.posicion[0]){
			case 2: //Nivel alto
				//Si me muevo al norte, voy hacia el cuadrante 1 o 2
	        	this.posicion[1]-=2;
	        	break;
	        case 1: //Nivel medio
	        	//Si estoy en un subcuadrante 3 o 4 y me muevo al norte, paso al sucuadrante superior
	        	this.posicion[2]-=2;
	        	break;
	        case 0: //Nivel bajo
	        	//Saca el id de la nueva esquina a la que se debe mover 
	        	//(Lo obtiene de la lista de esquinas adyacentes en el objeto Esquina(8 posiciones))
	        	int nuevaEsquina= this.listaEsquinasEnDrone.get(this.posicion[3]-1).getesquinasAdyacentes()[0];//Depende de la orientación
	        	listaEsquinasVisitadas[nuevaEsquina] +=1;
	        	this.posicion[3] = nuevaEsquina;
	        	int[] nuevosAdyacentes= this.listaEsquinasEnDrone.get(nuevaEsquina-1).getesquinasAdyacentes();
	        	//Setea el array esquinas adyacentes que está en el estado del drone
	        	//Este tiene 9 posiciones porque incluye a la propia esquina en la primera
	        	this.esquinasAdyacentes[0]=nuevaEsquina;
	        	for(int i=0;i<8;i++){
	        		this.esquinasAdyacentes[i+1]=nuevosAdyacentes[i];
	        	}
	        	break;
	        }
		}
		public void irNorEste() {
			accionesRecorridas++;
			switch(this.posicion[0]){
			case 2: //Nivel alto
				//Si me muevo al noreste, voy hacia el cuadrante 2
	        	this.posicion[1]-=1;
	        	break;
	        case 1: //Nivel medio
	        	//Si estoy en un subcuadrante 1 o 3 y me muevo al este, paso al sucuadrante siguiente
	        	this.posicion[2]-=1;
	        	break;
	        case 0: //Nivel bajo
	        	//Nunca va a entrar a esta opción
	        	break;
	        }
			
		}
		public void irEste() {
			accionesRecorridas++;
			switch(this.posicion[0]){
			case 2: //Nivel alto
				//Si me muevo al este, voy hacia el cuadrante 2 o 4
	        	this.posicion[1]+=1;
	        	break;
	        case 1: //Nivel medio
	        	//Si estoy en un subcuadrante 1 o 3 y me muevo al este, paso al sucuadrante siguiente
	        	this.posicion[2]+=1;
	        	break;
	        case 0: //Nivel bajo
	        	//Saca el id de la nueva esquina a la que se debe mover 
	        	//(Lo obtiene de la lista de esquinas adyacentes en el objeto Esquina(8 posiciones))
	        	int nuevaEsquina= this.listaEsquinasEnDrone.get(this.posicion[3]-1).getesquinasAdyacentes()[2];//Depende de la orientación
	        	listaEsquinasVisitadas[nuevaEsquina] += 1;
	        	this.posicion[3] = nuevaEsquina;
	        	int[] nuevosAdyacentes= this.listaEsquinasEnDrone.get(nuevaEsquina-1).getesquinasAdyacentes();
	        	//Setea el array esquinas adyacentes que está en el estado del drone
	        	//Este tiene 9 posiciones porque incluye a la propia esquina en la primera
	        	this.esquinasAdyacentes[0]=nuevaEsquina;
	        	for(int i=0;i<8;i++){
	        		this.esquinasAdyacentes[i+1]=nuevosAdyacentes[i];
	        	}
	        	break;
	        }
		}

		public void irSurEste() {
			accionesRecorridas++;
			switch(this.posicion[0]){
			case 2: //Nivel alto
				//Si me muevo al sureste, voy hacia el cuadrante 4
	        	this.posicion[1]+=3;
	        	break;
	        case 1: //Nivel medio
	        	//Si estoy en un subcuadrante 1  y me muevo al sureste, paso al sucuadrante 4
	        	this.posicion[2]+=3;
	        	break;
	        case 0: //Nivel bajo
	        	//Saca el id de la nueva esquina a la que se debe mover 
	        	//(Lo obtiene de la lista de esquinas adyacentes en el objeto Esquina(8 posiciones))
	        	int nuevaEsquina= this.listaEsquinasEnDrone.get(this.posicion[3]-1).getesquinasAdyacentes()[3];//Depende de la orientación
	        	listaEsquinasVisitadas[nuevaEsquina] +=1;
	        	this.posicion[3] = nuevaEsquina;
	        	int[] nuevosAdyacentes= this.listaEsquinasEnDrone.get(nuevaEsquina-1).getesquinasAdyacentes();
	        	//Setea el array esquinas adyacentes que está en el estado del drone
	        	//Este tiene 9 posiciones porque incluye a la propia esquina en la primera
	        	this.esquinasAdyacentes[0]=nuevaEsquina;
	        	for(int i=0;i<8;i++){
	        		this.esquinasAdyacentes[i+1]=nuevosAdyacentes[i];
	        	}
	        	break;
	        }	
		}

		public void irSur() {
			accionesRecorridas++;
			switch(this.posicion[0]){
			case 2: //Nivel alto
				//Si me muevo al sur, voy hacia el cuadrante 3 o 4
	        	this.posicion[1]+=2;
	        	break;
	        case 1: //Nivel medio
	        	//Si me muevo al sur, paso a los subcuadrantes 3 o 4
	        	this.posicion[2]+=2;
	        	break;
	        case 0: //Nivel bajo
	        	//Saca el id de la nueva esquina a la que se debe mover 
	        	//(Lo obtiene de la lista de esquinas adyacentes en el objeto Esquina(8 posiciones))
	        	int nuevaEsquina= this.listaEsquinasEnDrone.get(this.posicion[3]-1).getesquinasAdyacentes()[4];//Depende de la orientación
	        	listaEsquinasVisitadas[nuevaEsquina] +=1;
	        	this.posicion[3] = nuevaEsquina;
	        	int[] nuevosAdyacentes= this.listaEsquinasEnDrone.get(nuevaEsquina-1).getesquinasAdyacentes();
	        	//Setea el array esquinas adyacentes que está en el estado del drone
	        	//Este tiene 9 posiciones porque incluye a la propia esquina en la primera
	        	this.esquinasAdyacentes[0]=nuevaEsquina;
	        	for(int i=0;i<8;i++){
	        		this.esquinasAdyacentes[i+1]=nuevosAdyacentes[i];
	        	}
	        	break;
	        }	
			
		}

		public void irSurOeste() {
			accionesRecorridas++;
			switch(this.posicion[0]){
			case 2: //Nivel alto
				//Si me muevo al suroeste, voy hacia el cuadrante 3
	        	this.posicion[1]+=1;
	        	break;
	        case 1: //Nivel medio
	        	//Si estoy en un subcuadrante 2  y me muevo al sureste, paso al sucuadrante 3
	        	this.posicion[2]+=1;
	        	break;
	        case 0: //Nivel bajo
	        	//Saca el id de la nueva esquina a la que se debe mover 
	        	//(Lo obtiene de la lista de esquinas adyacentes en el objeto Esquina(8 posiciones))
	        	int nuevaEsquina= this.listaEsquinasEnDrone.get(this.posicion[3]-1).getesquinasAdyacentes()[5];//Depende de la orientación
	        	listaEsquinasVisitadas[nuevaEsquina] +=1;
	        	this.posicion[3] = nuevaEsquina;
	        	int[] nuevosAdyacentes= this.listaEsquinasEnDrone.get(nuevaEsquina-1).getesquinasAdyacentes();
	        	//Setea el array esquinas adyacentes que está en el estado del drone
	        	//Este tiene 9 posiciones porque incluye a la propia esquina en la primera
	        	this.esquinasAdyacentes[0]=nuevaEsquina;
	        	for(int i=0;i<8;i++){
	        		this.esquinasAdyacentes[i+1]=nuevosAdyacentes[i];
	        	}
	        	break;
	        }	
		}

		public void irOeste() {
			accionesRecorridas++;
			switch(this.posicion[0]){
			case 2: //Nivel alto
				//Si me muevo al oeste, voy hacia los cuadrantes 1 o 3
	        	this.posicion[1]-=1;
	        	break;
	        case 1: //Nivel medio
	        	//Si me muevo al oeste dentro de un cuadrante, voy hacia los subcuadrantes 1 o 3 
	        	this.posicion[2]-=1;
	        	break;
	        case 0: //Nivel bajo
	        	//Saca el id de la nueva esquina a la que se debe mover 
	        	//(Lo obtiene de la lista de esquinas adyacentes en el objeto Esquina(8 posiciones))
	        	int nuevaEsquina= this.listaEsquinasEnDrone.get(this.posicion[3]-1).getesquinasAdyacentes()[6];//Depende de la orientación
	        	listaEsquinasVisitadas[nuevaEsquina] +=1;
	        	this.posicion[3] = nuevaEsquina;
	        	int[] nuevosAdyacentes= this.listaEsquinasEnDrone.get(nuevaEsquina-1).getesquinasAdyacentes();
	        	//Setea el array esquinas adyacentes que está en el estado del drone
	        	//Este tiene 9 posiciones porque incluye a la propia esquina en la primera
	        	this.esquinasAdyacentes[0]=nuevaEsquina;
	        	for(int i=0;i<8;i++){
	        		this.esquinasAdyacentes[i+1]=nuevosAdyacentes[i];
	        	}
	        	break;
	        }	
		}

		public void irNorOeste() {
			accionesRecorridas++;
			switch(this.posicion[0]){
			case 2: //Nivel alto
				//Si me muevo al noroeste, voy hacia el cuadrante 1
	        	this.posicion[1]-=3;
	        	break;
	        case 1: //Nivel medio
	        	//Si me muevo al noroeste dentro de un cuadrante, voy hacia el subcuadrante 1
	        	this.posicion[2]-=3;
	        	break;
	        case 0: //Nivel bajo
	        	//Saca el id de la nueva esquina a la que se debe mover 
	        	//(Lo obtiene de la lista de esquinas adyacentes en el objeto Esquina(8 posiciones))
	        	int nuevaEsquina= this.listaEsquinasEnDrone.get(this.posicion[3]-1).getesquinasAdyacentes()[7];//Depende de la orientación
	        	listaEsquinasVisitadas[nuevaEsquina] +=1;
	        	this.posicion[3] = nuevaEsquina;
	        	int[] nuevosAdyacentes= this.listaEsquinasEnDrone.get(nuevaEsquina-1).getesquinasAdyacentes();
	        	//Setea el array esquinas adyacentes que está en el estado del drone
	        	//Este tiene 9 posiciones porque incluye a la propia esquina en la primera
	        	this.esquinasAdyacentes[0]=nuevaEsquina;
	        	for(int i=0;i<8;i++){
	        		this.esquinasAdyacentes[i+1]=nuevosAdyacentes[i];
	        	}
	        	break;
	        }	
		}

		public void irNivelAlto() {
			accionesRecorridas++;
			//Se mantiene en el mismo cuadrante, sólo sube
			this.posicion[0]=2;
			//Se vuelve a setear el subcuadrante en 0
			//this.posicion[2]=0;
		}

		public void irNivelMedio() {
			accionesRecorridas++;
			int altitud=this.posicion[0];
			this.posicion[0]=1;//Se cambia la altitud (tanto si viene del nivel alto como del bajo)
			if(altitud==2){//Desde nivel alto
				//Se posiciona en el primer subcuadrante (arriba a la izquierda)
				this.posicion[2]=1;
			}
			if(altitud==0){//Desde nivel bajo
				//Se se vuelve a setear la esquina en 0
				//this.posicion[3]=0;
			}

		}

		public void irNivelBajo() {
			accionesRecorridas++;
			//Baja de nivel
			this.posicion[0]=0;
			int subcuadrante=this.posicion[2];
			int nuevaEsquina=0;
			//Asigna la esquina en la que debe bajar según el subcuadrante en el que se encuentra
			switch(this.posicion[1]){
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
	    	this.posicion[3]=nuevaEsquina;
	    	listaEsquinasVisitadas[nuevaEsquina] +=1;
	    	int[] nuevosAdyacentes= this.listaEsquinasEnDrone.get(nuevaEsquina-1).getesquinasAdyacentes();
	    	//Setea el array esquinas adyacentes que está en el estado del drone
	    	//Este tiene 9 posiciones porque incluye a la propia esquina en la primera
	    	this.esquinasAdyacentes[0]=nuevaEsquina;
	    	for(int i=0;i<8;i++){
	    		this.esquinasAdyacentes[i+1]=nuevosAdyacentes[i];
	    	}
		}

		public void identificarVictimario() {
			accionesRecorridas++;
			Esquina esquinaActual=this.listaEsquinasEnDrone.get(posicion[3]-1);
			if(esquinaActual.getcontieneVictimario()){
				this.victimario=true;
			}
			else{
				listaEsquinasIdentificadas[posicion[3]]=1;
			}
			
		}

		public void bajar() {
			accionesRecorridas++;
			int altitud=this.posicion[0];
			if(altitud==2){//Desde nivel alto
				this.posicion[0]=1;//Se cambia la altitud
				//Se posiciona en el primer subcuadrante (arriba a la izquierda)
				this.posicion[2]=1;
			}
			if(altitud==1){
				//Baja de nivel
				this.posicion[0]=0;
				int subcuadrante=this.posicion[2];
				int nuevaEsquina=0;
				//Asigna la esquina en la que debe bajar según el subcuadrante en el que se encuentra
				switch(this.posicion[1]){
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
		    	this.posicion[3]=nuevaEsquina;
		    	listaEsquinasVisitadas[nuevaEsquina] +=1;
		    	int[] nuevosAdyacentes= this.listaEsquinasEnDrone.get(nuevaEsquina-1).getesquinasAdyacentes();
		    	//Setea el array esquinas adyacentes que está en el estado del drone
		    	//Este tiene 9 posiciones porque incluye a la propia esquina en la primera
		    	this.esquinasAdyacentes[0]=nuevaEsquina;
		    	for(int i=0;i<8;i++){
		    		this.esquinasAdyacentes[i+1]=nuevosAdyacentes[i];
		    	}
			}
		}

	
}

