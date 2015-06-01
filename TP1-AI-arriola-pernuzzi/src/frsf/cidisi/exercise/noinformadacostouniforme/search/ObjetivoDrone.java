

package frsf.cidisi.exercise.noinformadacostouniforme.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoDrone extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    	// Si la posicion del drone es igual a la posicion del victimario
        if  (((EstadoDrone)agentState).getvictimario() == true) 
        {
            return true;// OBJETIVO CUMPLIDO: VICTIMARIO IDENTIFICADO!
        }
        //Si se recorrieron todas las esquinas
        //(Aunque no se haya encontrado el victimario)
        for(int i=1;i<79;i++){
        	if(((EstadoDrone)agentState).getlistaEsquinasIdentificadas()[i]==0) return false;
        	if(i==78) return true;// OBJETIVO CUMPLIDO: TODAS LAS ESQUINAS FUERON VISITADAS
        }
        return false;
	}
}