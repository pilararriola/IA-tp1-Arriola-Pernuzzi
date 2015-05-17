package frsf.cidisi.exercise.noinformadacostouniforme.search;

import java.util.ArrayList;

import frsf.cidisi.exercise.entidades.Cuadrante;
import frsf.cidisi.exercise.entidades.Esquina;
import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class EstadoAmbiente extends EnvironmentState {
	
    private static ArrayList<Esquina> mapa;
    private int posicionVictimario;
    private int[] posicionDrone;
    private static ArrayList<Cuadrante> listaCuadrantes;
	
    public EstadoAmbiente(int esc) {
        
        //TODO: Complete Method
    	/*
			// mapa = initData0;
			// posicionVictimario = initData1;
			// posicionDrone = initData2;
			// listaCuadrantes = initData3;
        */
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {

        //TODO: Complete Method
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
	
	public ArrayList<Esquina> getmapa(){
    	return mapa;
     }
     public void setmapa(ArrayList<Esquina> arg){
    	 mapa = arg;
     }
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
     public ArrayList<Cuadrante> getlistaCuadrantes(){
        return listaCuadrantes;
     }
     public void setlistaCuadrantes(ArrayList<Cuadrante> arg){
        listaCuadrantes = arg;
     }
	

}

