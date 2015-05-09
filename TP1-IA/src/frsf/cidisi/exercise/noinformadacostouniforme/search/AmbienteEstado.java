package frsf.cidisi.exercise.noinformadacostouniforme.search;

import java.util.List;

import frsf.cidisi.exercise.entidades.Cuadrante;
import frsf.cidisi.exercise.entidades.Esquina;
import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class AmbienteEstado extends EnvironmentState {
	
	//TODO: Setup Variables
	private List<Esquina> mapa;
    private int posicionVictimario;
    private int[] posicionDrone;
    private List<Cuadrante> listaCuadrantes;
	
    public AmbienteEstado() {
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {
        //TODO: Complete Method
			// mapa = initData0; Setear las 78 esquinas
			//posicionVictimario = initData1;
			//posicionDrone = initData2;
			//listaCuadrantes = initData3;
    }

    /**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "";

        //TODO: Complete Method

        return str;
    }

	//TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
	
//     public Other getmapa(){
//        return mapa;
//     }
//     public void setmapa(Other arg){
//        mapa = arg;
//     }
     public int getposicionVictimario(){
        return posicionVictimario;
     }
     public void setposicionVictimario(int arg){
        posicionVictimario = arg;
     }
     public int[] getposicionDrone(){
        return posicionDrone;
     }
     public void setposicionDrone(int[] arg){
        posicionDrone = arg;
     }
//     public Other getlistaCuadrantes(){
//        return listaCuadrantes;
//     }
//     public void setlistaCuadrantes(Other arg){
//        listaCuadrantes = arg;
//     }
	

}

