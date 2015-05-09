

package frsf.cidisi.exercise.noinformadacostouniforme.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class DroneObjetivo extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
    	
		// TODO: Complete Method
        if  (((DroneEstado)agentState).getvictimario() == true && ((DroneEstado)agentState).getenergia()>0) 
        	
        	//El drone se encuentra en el posicion del victimario
        	// VICTIMARIO IDENTIFICADO!
        	{
            return true;
        	}
        return false; 
	}
}