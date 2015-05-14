package frsf.cidisi.exercise.noinformadacostouniforme.search;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class can be used in any search strategy like
 * Uniform Cost.
 */
public class CostFunction implements IStepCostFunction {
	//COSTOS DE LAS OPERACIONES
	 public static final int CS=3; //COSTO SUBIR
	 public static final int CB=1; //COSTO BAJAR
	 public static final int CCS=2;//COSTO MOVERME CON SEÑAL
	 public static final int CSS=1;//COSTO MOVERME SIN SEÑAL
	 //nose si identificar el victimario tiene un costo o no
    /**
     * This method calculates the cost of the given NTree node.
     */
    @Override
    public double calculateCost(NTree node) {
        
    // El nodo donde estoy, la accion elegida es bajar
    	if(node.getAction().toString() == "IrNivelMedio"){
    		return CB;
    		
    	} else if(node.getAction().toString() == "IrNivelAlto") {
    		return CS;
    	}
        return 0;
    }
}
