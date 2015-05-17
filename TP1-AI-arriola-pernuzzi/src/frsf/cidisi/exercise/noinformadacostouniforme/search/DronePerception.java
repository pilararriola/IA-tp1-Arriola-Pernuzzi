package frsf.cidisi.exercise.noinformadacostouniforme.search;

import frsf.cidisi.exercise.entidades.Cuadrante;
import frsf.cidisi.exercise.entidades.Esquina;
import frsf.cidisi.exercise.entidades.Subcuadrante;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class DronePerception extends Perception {
	
	//Sensores del Drone
	
	private int[] gps; //Array de 4 posiciones que va a dar la nueva posición del drone 
	private int[] antena; // Array de 4 posiciones que da la intensidad de las señales en los cuadrantes/subcuadrantes respectivos
	/*
     Matriz de 2X9 donde la primer fila se corresponde con el array de esquinas adyacentes
     y, la segunda, con el array de vista en línea recta. 
     La relación en cada columna sería que si en la esquina de la fila 0 se encuentra el victimario,
     va a haber un 1 en la fila 1.
     La primer columna de la matriz es:
     	fila0: esquina actual en que se encuentra el drone
     	fila1: 1 si esta el victimario ahi, 0 caso contrario
    */
	private int[][] camara;
	
 

    public  DronePerception() {
    }

    public DronePerception(Agent agent, Environment environment) {
        super(agent, environment);
      
    }

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
        Drone agent = (Drone) agentIn;
        Ambiente environment = (Ambiente) environmentIn;
        EstadoAmbiente environmentState =
                environment.getEnvironmentState();
       
        this.gps = environmentState.getposicionDrone();
        this.antena = obtenerSeniales(environmentState);
        this.camara = obtenerEsquinas(environmentState);
        
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        //TODO: Complete Method

        return str.toString();
    }
    
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
    				for(int j=0;j<9;i++){
    					matrizAux[i][j]=0;
    				}
    			}
    			break;
    	}
    	return matrizAux;
    }

    // The following methods are agent-specific:
	
    public int[] getgps(){
        return gps;
     }
    public void setgps(int[] arg){
        this.gps = arg;
     }
    public int[]  getantena(){
       return antena;
    }
    public void setantena(int[] arg){
        this.antena = arg;
     }
    public int[][]  getcamara(){
        return camara;
     }
     public void setcamara(int[][]  arg){
       this.camara = arg;
     }
	
   
}
