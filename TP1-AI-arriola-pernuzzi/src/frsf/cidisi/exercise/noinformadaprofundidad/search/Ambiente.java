package frsf.cidisi.exercise.noinformadaprofundidad.search;

import frsf.cidisi.exercise.entidades.Cuadrante;
import frsf.cidisi.exercise.entidades.Esquina;
import frsf.cidisi.exercise.entidades.Subcuadrante;
import frsf.cidisi.exercise.noinformadaprofundidad.search.DronePerception;
import frsf.cidisi.exercise.noinformadaprofundidad.search.EstadoAmbiente;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.environment.Environment;

public class Ambiente extends Environment {

    public Ambiente(int escenario) {
        // Create the environment state
        this.environmentState = new EstadoAmbiente(escenario);
    }

    public EstadoAmbiente getEnvironmentState() {
        return (EstadoAmbiente) super.getEnvironmentState();
    }

    /**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
    @Override
    public  DronePerception getPercept() {
        EstadoAmbiente estadoAmbiente=(EstadoAmbiente) getEnvironmentState();
    	
    	// Create a new perception to return
         DronePerception perception = new DronePerception();
		
		// Set the perceptions sensors
         perception.setgps(estadoAmbiente.getposicionDrone());
         perception.setantena(obtenerSeniales(estadoAmbiente));
         perception.setcamara(obtenerEsquinas(estadoAmbiente));

        // Return the perception
        return perception;
    }

    
    public String toString() {
        return environmentState.toString();
    }

    
    public boolean agentFailed(Action actionReturned) {

        EstadoAmbiente envState =
                this.getEnvironmentState();

        // TODO: Complete Method        

        return false;
    }

    // The following methods are agent-specific:
    private int[] obtenerSeniales(EstadoAmbiente estado){
    	int[] arrayAux = new int[4];
    	int nivelDrone= estado.getposicionDrone()[0];
    	int i=0;
    	switch(nivelDrone){
    		case 2: //Nivel Alto (Intensidad señal de los cuadrantes)
    			for(Cuadrante cuadrante : estado.getlistaCuadrantes()){
    				arrayAux[i] = cuadrante.getintensidad();
    				i++;
    			}
    			break;
    		case 1: //Nivel Medio (Intensidad señal de los subcuadrantes)
    			//Primero obtengo el cuadrante en el que se encuentra el Drone: 1, 2, 3 o 4
    			//Luego obtengo ese cuadrante de la lista de cuadrantes 
    			//La posicion de este va a ser: id cuadrante - 1
    			Cuadrante cuadrante= estado.getlistaCuadrantes().get(estado.getposicionDrone()[1]-1);
    			
    			//Saco la intensidad de cada uno de los subcuadrantes que se ubican dentro del cuadrante ya obtenido
    			for(Subcuadrante subcuadrante : cuadrante.getlistaSubcuadrantes()){
    				arrayAux[i] = subcuadrante.getintensidad();
    				i++;
    			}
    			break;
    		case 0: //Nivel Bajo
    			for(i=0;i<4;i++){
    				arrayAux[i] = 0;//En este nivel no se percibe intensidad de señal
    			}
    			break;		
    	}
    	return arrayAux;
    }
    
    private int[][] obtenerEsquinas(EstadoAmbiente estado){
    	int[][] matrizAux = new int[2][9];
    	int nivelDrone= estado.getposicionDrone()[0];
    	int i=0;
    	switch(nivelDrone){
    		case 0:
    			int esquinaActual=estado.getposicionDrone()[3];
    			int esquinaAdyacente;
    			Esquina esquinaDrone=estado.getmapa().get(esquinaActual-1);
    			
    			//La primer columna de la matriz va a contener la esquina donde se encuentra el drone
    			matrizAux[0][0]=esquinaActual;
				if(esquinaActual==estado.getposicionVictimario())matrizAux[1][0]=1;
				else matrizAux[1][0]=0;
    			
				//Para el resto de las columnas, se carga la matriz con cada una de las esquinas adyacentes
				for(i=1;i<9;i++){
    				esquinaAdyacente=esquinaDrone.getesquinasAdyacentes()[i-1];
    				matrizAux[0][i]=esquinaAdyacente;
    				if(esquinaAdyacente==estado.getposicionVictimario()){
    					matrizAux[1][i]=1;
    				}
    				else matrizAux[1][i]=0;
    			}
    			break;
    		default://Si el drone no está en el nivel bajo, la cámara no puede tomar datos
    			for(i=0;i<2;i++){
    				for(int j=0;j<9;j++){
    					matrizAux[i][j]=0;
    				}
    			}
    			break;
    	}
    	return matrizAux;
    }
    
}
