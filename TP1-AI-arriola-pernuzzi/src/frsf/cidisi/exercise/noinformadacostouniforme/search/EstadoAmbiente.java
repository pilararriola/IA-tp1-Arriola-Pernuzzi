package frsf.cidisi.exercise.noinformadacostouniforme.search;

import java.util.ArrayList;

import frsf.cidisi.exercise.entidades.Cuadrante;
import frsf.cidisi.exercise.entidades.Esquina;
import frsf.cidisi.exercise.entidades.Mapa;
import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class EstadoAmbiente extends EnvironmentState {
	
    private ArrayList<Esquina> mapa;
    private int posicionVictimario;
    private int[] posicionDrone;
    private ArrayList<Cuadrante> listaCuadrantes;
	
    public EstadoAmbiente(int esc) {
    	Mapa valoresAmbiente=new Mapa(esc);
    	this.mapa=valoresAmbiente.getListaEsquinas();
    	this.posicionVictimario=valoresAmbiente.getPosicionVictimario();
    	this.posicionDrone=valoresAmbiente.getPosicionDrone();
    	this.listaCuadrantes=valoresAmbiente.getListaCuadrantes();
    	
    	this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() { 

    }

    /**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "";
        str += " ---- Estado ambiente ----\n";
        str += "Posicion Drone: (" ; 
        for(int i=0;i<4;i++){
        	str +=this.posicionDrone[i];
        	if(i<3)str += " , ";
        }
        str +=") \n";
        str += "Posicion victimario: Esquina "+ this.posicionVictimario+" \n";
        return str;
    }


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

 	public void irNorte() {
		switch(this.posicionDrone[0]){
		case 2: //Nivel alto
			//Si me muevo al norte, voy hacia el cuadrante 1 o 2
        	this.posicionDrone[1]-=2;
        	break;
        case 1: //Nivel medio
        	//Si estoy en un subcuadrante 3 o 4 y me muevo al norte, paso al sucuadrante superior
        	this.posicionDrone[2]-=2;
        	break;
        case 0: //Nivel bajo
        	//Saca el id de la nueva esquina a la que se debe mover 
        	//(Lo obtiene de la lista de esquinas adyacentes en el objeto Esquina(8 posiciones))
        	int nuevaEsquina= this.mapa.get(this.posicionDrone[3]-1).getesquinasAdyacentes()[0];//Depende de la orientación
        	this.posicionDrone[3] = nuevaEsquina;
        	break;
        }
		
	}
	public void irNorEste() {
		switch(this.posicionDrone[0]){
		case 2: //Nivel alto
			//Si me muevo al noreste, voy hacia el cuadrante 2
        	this.posicionDrone[1]-=1;
        	break;
        case 1: //Nivel medio
        	//Si estoy en un subcuadrante 1 o 3 y me muevo al este, paso al sucuadrante siguiente
        	this.posicionDrone[2]-=1;
        	break;
        case 0: //Nivel bajo
        	//Nunca va a entrar a esta opción
        	break;
        }
	}
	public void irEste() {
		switch(this.posicionDrone[0]){
		case 2: //Nivel alto
			//Si me muevo al este, voy hacia el cuadrante 2 o 4
        	this.posicionDrone[1]+=1;
        	break;
        case 1: //Nivel medio
        	//Si estoy en un subcuadrante 1 o 3 y me muevo al este, paso al sucuadrante siguiente
        	this.posicionDrone[2]+=1;
        	break;
        case 0: //Nivel bajo
        	int nuevaEsquina= this.mapa.get(this.posicionDrone[3]-1).getesquinasAdyacentes()[2];
        	this.posicionDrone[3] = nuevaEsquina;
        	break;
        }
		
	}

	public void irSurEste() {
		switch(this.posicionDrone[0]){
		case 2: //Nivel alto
			//Si me muevo al sureste, voy hacia el cuadrante 4
        	this.posicionDrone[1]+=3;
        	break;
        case 1: //Nivel medio
        	//Si estoy en un subcuadrante 1  y me muevo al sureste, paso al sucuadrante 4
        	this.posicionDrone[2]+=3;
        	break;
        case 0: //Nivel bajo
        	//Saca el id de la nueva esquina a la que se debe mover 
        	//(Lo obtiene de la lista de esquinas adyacentes en el objeto Esquina(8 posiciones))
        	int nuevaEsquina= this.mapa.get(this.posicionDrone[3]-1).getesquinasAdyacentes()[3];//Depende de la orientación
        	this.posicionDrone[3] = nuevaEsquina;
        	break;
        }	
		
	}

	public void irSur() {
		switch(this.posicionDrone[0]){
		case 2: //Nivel alto
			//Si me muevo al sur, voy hacia el cuadrante 3 o 4
        	this.posicionDrone[1]+=2;
        	break;
        case 1: //Nivel medio
        	//Si me muevo al sur, paso a los subcuadrantes 3 o 4
        	this.posicionDrone[2]+=2;
        	break;
        case 0: //Nivel bajo
        	//Saca el id de la nueva esquina a la que se debe mover 
        	//(Lo obtiene de la lista de esquinas adyacentes en el objeto Esquina(8 posiciones))
        	int nuevaEsquina= this.mapa.get(this.posicionDrone[3]-1).getesquinasAdyacentes()[4];//Depende de la orientación
        	this.posicionDrone[3] = nuevaEsquina;
        	break;
        }		
	}

	public void irSurOeste() {
		switch(this.posicionDrone[0]){
		case 2: //Nivel alto
			//Si me muevo al suroeste, voy hacia el cuadrante 3
        	this.posicionDrone[1]+=1;
        	break;
        case 1: //Nivel medio
        	//Si estoy en un subcuadrante 2  y me muevo al sureste, paso al sucuadrante 3
        	this.posicionDrone[2]+=1;
        	break;
        case 0: //Nivel bajo
        	//Saca el id de la nueva esquina a la que se debe mover 
        	//(Lo obtiene de la lista de esquinas adyacentes en el objeto Esquina(8 posiciones))
        	int nuevaEsquina= this.mapa.get(this.posicionDrone[3]-1).getesquinasAdyacentes()[5];//Depende de la orientación
        	this.posicionDrone[3] = nuevaEsquina;
        	break;
        }	
	}

	public void irOeste() {
		switch(this.posicionDrone[0]){
		case 2: //Nivel alto
			//Si me muevo al oeste, voy hacia los cuadrantes 1 o 3
        	this.posicionDrone[1]-=1;
        	break;
        case 1: //Nivel medio
        	//Si me muevo al oeste dentro de un cuadrante, voy hacia los subcuadrantes 1 o 3 
        	this.posicionDrone[2]-=1;
        	break;
        case 0: //Nivel bajo
        	//Saca el id de la nueva esquina a la que se debe mover 
        	//(Lo obtiene de la lista de esquinas adyacentes en el objeto Esquina(8 posiciones))
        	int nuevaEsquina= this.mapa.get(this.posicionDrone[3]-1).getesquinasAdyacentes()[6];//Depende de la orientación
        	this.posicionDrone[3] = nuevaEsquina;
        	break;
        }	
	}

	public void irNorOeste() {
		switch(this.posicionDrone[0]){
		case 2: //Nivel alto
			//Si me muevo al noroeste, voy hacia el cuadrante 1
        	this.posicionDrone[1]-=3;
        	break;
        case 1: //Nivel medio
        	//Si me muevo al noroeste dentro de un cuadrante, voy hacia el subcuadrante 1
        	this.posicionDrone[2]-=3;
        	break;
        case 0: //Nivel bajo
        	//Saca el id de la nueva esquina a la que se debe mover 
        	//(Lo obtiene de la lista de esquinas adyacentes en el objeto Esquina(8 posiciones))
        	int nuevaEsquina= this.mapa.get(this.posicionDrone[3]-1).getesquinasAdyacentes()[7];//Depende de la orientación
        	this.posicionDrone[3] = nuevaEsquina;
        	break;
        }	
	}

	public void irNivelAlto() {
		//Se mantiene en el mismo cuadrante, sólo sube
		this.posicionDrone[0]=2;
	}

	public void irNivelMedio() {
		int altitud=this.posicionDrone[0];
		this.posicionDrone[0]=1;//Se cambia la altitud (tanto si viene del nivel alto como del bajo)
		if(altitud==2){//Desde nivel alto
			//Se posiciona en el primer subcuadrante (arriba a la izquierda)
			this.posicionDrone[2]=1;
		}
		
	}

	public void irNivelBajo() {
		//Baja de nivel
		this.posicionDrone[0]=0;
		int subcuadrante=this.posicionDrone[2];
		//Asigna la esquina en la que debe bajar según el subcuadrante en el que se encuentra
		switch(this.posicionDrone[1]){
		case 1:
			if(subcuadrante==1)this.posicionDrone[3]=2;
			else if(subcuadrante==2)this.posicionDrone[3]=10;
			else if(subcuadrante==3)this.posicionDrone[3]=29;
			else this.posicionDrone[3]=31;
			break;
		case 2:
			if(subcuadrante==1)this.posicionDrone[3]=12;
			else if(subcuadrante==2)this.posicionDrone[3]=14;
			else if(subcuadrante==3)this.posicionDrone[3]=33;
			else this.posicionDrone[3]=35;
			break;
		case 3:
			if(subcuadrante==1)this.posicionDrone[3]=38;
			else if(subcuadrante==2)this.posicionDrone[3]=40;
			else if(subcuadrante==3)this.posicionDrone[3]=61;
			else this.posicionDrone[3]=63;
			break;
		case 4:
			if(subcuadrante==1)this.posicionDrone[3]=47;
			else if(subcuadrante==2)this.posicionDrone[3]=49;
			else if(subcuadrante==3)this.posicionDrone[3]=66;
			else this.posicionDrone[3]=68;
			break;
		}
		
	}

	public void identificarVictimario() {
		//El ambiente no modifica el estado si se produce esta acción, sólolo hace el drone
	}
}

