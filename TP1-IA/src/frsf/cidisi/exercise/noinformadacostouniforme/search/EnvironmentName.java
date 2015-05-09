package frsf.cidisi.exercise.noinformadacostouniforme.search;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class EnvironmentName extends Environment {

    public EnvironmentName() {
        // Create the environment state
        this.environmentState = new AmbienteEstado();
    }

    public AmbienteEstado getEnvironmentState() {
        return (AmbienteEstado) super.getEnvironmentState();
    }

    /**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
    @Override
    public  DroneAgentePerception getPercept() {
        // Create a new perception to return
         DroneAgentePerception perception = new DroneAgentePerception();
		
		//TODO : Set the perceptions sensors
        
        // Return the perception
        return perception;
    }

    
    public String toString() {
        return environmentState.toString();
    }

    
    public boolean agentFailed(Action actionReturned) {

        AmbienteEstado envState =
                this.getEnvironmentState();

        // TODO: Complete Method        

        return false;
    }

	//TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
    
    
}
