package frsf.cidisi.exercise.noinformadaprofundidad.search;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class DronePerception extends Perception {

	//TODO: Setup Statics
    //public static int UNKNOWN_PERCEPTION = -1;   
	
	
	//TODO: Setup Sensors
//	private Other gps;
//	private Other antena;
//	private Other camara;
	
 

    public  DronePerception() {
    	//TODO: Complete Method
    }

    public DronePerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
    	
    	//TODO: Complete Method
        
        //Drone agent = (Drone) agentIn;
        //Ambiente environment = (Ambiente) environmentIn;
        //EstadoAmbiente environmentState =
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
	
//     public Other getgps(){
//        return gps;
//     }
//     public void setgps(Other arg){
//        this.gps = arg;
//     }
//     public Other getantena(){
//        return antena;
//     }
//     public void setantena(Other arg){
//        this.antena = arg;
//     }
//     public Other getcamara(){
//        return camara;
//     }
//     public void setcamara(Other arg){
//        this.camara = arg;
//     }
	
   
}
