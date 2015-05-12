

package frsf.cidisi.exercise.noinformadacostouniforme.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
    	// Si la posicion del drone es igual a la posicion del victimario y tiene energia suficiente
        if  (((EstadoDrone)agentState).getvictimario() == true && ((EstadoDrone)agentState).getenergia()>0) 
        	{
            return true;// OBJETIVO CUMPLIDO: VICTIMARIO IDENTIFICADO!
        	}
        return false;
	}
}