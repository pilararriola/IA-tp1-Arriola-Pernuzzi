

package frsf.cidisi.exercise.noinformadacostouniforme.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class DroneObjetivo extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
    	// TODO: Complete Method
        if  (true) //(posicionDrone[3]=posicionVictimario && energia>0)
        	{
            return true;
        	}
        return false;
	}
}