package frsf.cidisi.exercise.informada.search;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class allows to define a function to be used by any
 * informed search strategy, like A Star or Greedy.
 */
public class Heuristic implements IEstimatedCostFunction {
	static final int MAYOR =1;
	static final int SEGUNDO =2;
	static final int TERCERO =3;
	static final int MENOR =4;
    /**
     * It returns the estimated cost to reach the goal from a NTree node.
     */
    @Override
    public double getEstimatedCost(NTree node) {
        EstadoDrone agState = (EstadoDrone) node.getAgentState();
        double heuristica=0;
        int altitud=agState.getposicion()[0];
		if(altitud==2 || altitud==1){
			int[][] intSenial=agState.getintensidadSenial();
			int cuadrante=agState.getposicion()[1];
			int intensidad=0;
			int comparacion=MAYOR;
			
			if(altitud==2){
				intensidad=intSenial[cuadrante-1][0];
				for(int i=0;i<4;i++){
					if(i!=cuadrante-1){
						if(intensidad<intSenial[i][0]){
							comparacion=+1;
						}
					}
				}
			}
			else{
				int subcuadrante=agState.getposicion()[2];
				intensidad=intSenial[cuadrante-1][subcuadrante];
				for(int i=1;i<5;i++){
					if(i!=subcuadrante){
						if(intensidad<intSenial[cuadrante-1][i]){
							comparacion=+1;
						}
					}
				}
			}

			switch(comparacion){
			case MAYOR:
				heuristica=1;
				break;
			case SEGUNDO:
				heuristica=2;
				break;
			case TERCERO:
				heuristica=3;
				break;
			case MENOR:
				heuristica=4;
				break;
			}
		}
    	/*int[] esqIdentificadas = agState.getlistaEsquinasIdentificadas();
        for(int esquinaIdentificada : esqIdentificadas){
        	if(esquinaIdentificada==0) heuristica++;
        }
	    if(node.getAction().toString() == "IdentificarVictimario"){
	    	heuristica= heuristica*(-1);
	    }*/

        return heuristica;
    }
}
