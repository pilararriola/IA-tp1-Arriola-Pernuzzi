package frsf.cidisi.exercise.noinformadacostouniforme.search;

import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNivelAlto;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNivelMedio;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNivelBajo;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.EncontrarVictimario;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrEsquinaAdyacente;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.solver.search.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;

public class DroneAgente extends SearchBasedAgent {

    public DroneAgente() {

        // The Agent Goal
        DroneObjetivo agGoal = new DroneObjetivo();

        // The Agent State
        DroneEstado agState = new DroneEstado();
        this.setAgentState(agState);

        // Create the operators
        Vector<SearchAction> operators = new Vector<SearchAction>();
        operators.addElement(new IrNivelAlto());	
        operators.addElement(new IrNivelMedio());	
        operators.addElement(new IrNivelBajo());	
        operators.addElement(new EncontrarVictimario());	
        operators.addElement(new IrEsquinaAdyacente());	

        // Create the Problem which the agent will resolve
        Problem problem = new Problem(agGoal, agState, operators);
        this.setProblem(problem);
    }

    /**
     * This method is executed by the simulator to ask the agent for an action.
     */
    @Override
    public Action selectAction() {

        // Create the search strategy
        IStepCostFunction cost = new CostFunction();
        UniformCostSearch strategy = new UniformCostSearch(cost);          

        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.GRAPHVIZ_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(DroneAgente.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;

    }

    /**
     * This method is executed by the simulator to give the agent a perception.
     * Then it updates its state.
     * @param p
     */
    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }
}
