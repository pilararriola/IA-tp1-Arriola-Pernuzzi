package frsf.cidisi.exercise.informada.search;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class DroneAgentePerception extends Perception {

	//TODO: Setup Statics
    //public static int UNKNOWN_PERCEPTION = -1;   
	
	
	//TODO: Setup Sensors
	private int gps;
	private int antena;
	private int camara;
	
 

    public  DroneAgentePerception() {
    	//TODO: Complete Method
    }

    public DroneAgentePerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
    	
    	//TODO: Complete Method
        
        //DroneAgente agent = (DroneAgente) agentIn;
        //Ambiente environment = (Ambiente) environmentIn;
        //AmbienteEstado environmentState =
        //        environment.getEnvironmentState();
       
        
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        //TODO: Complete Method

        return str.toString();
    }

    // The following methods are agent-specific:
    //TODO: Complete this section with the agent-specific methods
	
     public int getgps(){
        return gps;
     }
     public void setgps(int arg){
        this.gps = arg;
     }
     public int getantena(){
        return antena;
     }
     public void setantena(int arg){
        this.antena = arg;
     }
     public int getcamara(){
        return camara;
     }
     public void setcamara(int arg){
        this.camara = arg;
     }
	
   
}
