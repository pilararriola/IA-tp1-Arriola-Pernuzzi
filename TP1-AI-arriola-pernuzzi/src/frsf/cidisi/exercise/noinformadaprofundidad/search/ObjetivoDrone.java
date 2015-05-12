

package frsf.cidisi.exercise.noinformadaprofundidad.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
    	// TODO: Complete Method
        if  (true) //(getVictimario()==true && energia>0)
        	{
            return true;
        	}
        return false;
	}
}