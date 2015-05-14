package frsf.cidisi.exercise.noinformadacostouniforme.search;

import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNivelBajo;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNivelMedio;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNivelAlto;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNorOeste;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrOeste;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrSurOeste;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrSur;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrSurEste;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrEste;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNorEste;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNorte;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNivelAlto;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNivelMedio;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNivelBajo;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNorOeste;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrOeste;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrSurOeste;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrSur;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrSurEste;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrEste;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNorEste;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IrNorte;
import frsf.cidisi.exercise.noinformadacostouniforme.search.actions.IdentificarVictimario;

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

        // Objetivo del agente
        ObjetivoDrone agGoal = new ObjetivoDrone();

        // Estado del agente
        EstadoDrone agState = new EstadoDrone();
        this.setAgentState(agState);

        // Operadores
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
        operators.addElement(new IdentificarVictimario());	

        // Se define el problema que el agente debe resolver
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
