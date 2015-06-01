package frsf.cidisi.exercise.principal;

import java.util.Arrays;
import java.util.Vector;

import frsf.cidisi.exercise.interfaz.ConfiguradorEstrategia;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.GoalBasedAgent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;

public class Simulador extends SearchBasedAgentSimulator {
		
		private boolean terminado;
		
		private Perception perception;
		private Action action;
		private GoalBasedAgent agent;

	    public Simulador(Environment environment, Agent agent) {
	    	super(environment, new Vector<Agent>(Arrays.asList(agent)));
	        terminado = false;
	    }
	    
	    public void comenzar() {
	        ConfiguradorEstrategia.setSimulador(this);
	    }
	    
	    public int avanzar() {
	    	if (this.terminado)
	    		return 1;
	    	
	        agent = (GoalBasedAgent) this.getAgents().firstElement();

	    	System.out.println("------------------------------------");
	        System.out.println("Sending perception to agent...");
	        
	        perception = this.getPercept();
	        agent.see(perception);
	        
	        System.out.println("Perception: " + perception);
	        System.out.println("Agent State: " + agent.getAgentState());
	        System.out.println("Environment: " + environment);
	        System.out.println("Asking the agent for an action...");
	        
	        action = agent.selectAction();

	        if (action == null) {
	        	this.terminado = true;
	        	return 1;
	        }
	        System.out.println(action.toString());
	        this.actionReturned(agent, action);
	        
	        if (this.agentSucceeded(action) || this.agentFailed(action)) {
	        	this.terminado = true;
	        }
	        return 0;
	    }
	    
	    public void terminar() {
	    	// Check what happened, if agent has reached the goal or not.
	        if (this.agentSucceeded(action)) {
	            System.out.println("Agent has reached the goal!");
	        } else {
	            System.out.println("ERROR: The simulation has finished, but the agent has not reached his goal.");
	        }
	        // Leave a blank line
	        System.out.println();
	        // FIXME: This call can be moved to the Simulator class
	        this.environment.close();
	        // Launch simulationFinished event
	        SimulatorEventNotifier.runEventHandlers(EventType.SimulationFinished, null);
	    }
	   
		
}


