package frsf.cidisi.exercise.noinformadaprofundidad.search;

import frsf.cidisi.exercise.noinformadaprofundidad.search.actions.IrNivelAlto;
import frsf.cidisi.exercise.noinformadaprofundidad.search.actions.IrNivelMedio;
import frsf.cidisi.exercise.noinformadaprofundidad.search.actions.IrNivelBajo;
import frsf.cidisi.exercise.noinformadaprofundidad.search.actions.IrNorOeste;
import frsf.cidisi.exercise.noinformadaprofundidad.search.actions.IrOeste;
import frsf.cidisi.exercise.noinformadaprofundidad.search.actions.IrSurOeste;
import frsf.cidisi.exercise.noinformadaprofundidad.search.actions.IrSur;
import frsf.cidisi.exercise.noinformadaprofundidad.search.actions.IrSurEste;
import frsf.cidisi.exercise.noinformadaprofundidad.search.actions.IrEste;
import frsf.cidisi.exercise.noinformadaprofundidad.search.actions.IrNorEste;
import frsf.cidisi.exercise.noinformadaprofundidad.search.actions.IrNorte;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.solver.search.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;

public class Drone extends SearchBasedAgent {

    public Drone() {

        // The Agent Goal
        ObjetivoDrone agGoal = new ObjetivoDrone();

        // The Agent State
        EstadoDrone agState = new EstadoDrone();
        this.setAgentState(agState);

        // Create the operators
        Vector<SearchAction> operators = new Vector<SearchAction>();
        operators.addElement(new IrNivelAlto());	
        operators.addElement(new IrNivelMedio());	
        operators.addElement(new IrNivelBajo());	
        operators.addElement(new IrNorOeste());	
        operators.addElement(new IrOeste());	
        operators.addElement(new IrSurOeste());	
        operators.addElement(new IrSur());	
        operators.addElement(new IrSurEste());	
        operators.addElement(new IrEste());	
        operators.addElement(new IrNorEste());	
        operators.addElement(new IrNorte());	

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
        DepthFirstSearch strategy = new DepthFirstSearch();          

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
            Logger.getLogger(Drone.class.getName()).log(Level.SEVERE, null, ex);
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
