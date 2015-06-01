package frsf.cidisi.exercise.entidades;

public class Esquina {
	int idEsquina;
	int cantidadPersonas;
	int[] esquinasAdyacentes;//Va a tener 8 posiciones 
	int cuadrante;
	int subcuadrante;
	boolean contieneVictimario;
	
	public Esquina(int id,int[] esqAdy){
		idEsquina= id;
		cantidadPersonas=0;
		esquinasAdyacentes= esqAdy;
		contieneVictimario=false;
		
	}
	public int getidEsquina(){
	    return idEsquina;
	}
	 public void setidEsquina(int arg){
	   this.idEsquina = arg;
	 }
	public int getcantidadPersonas(){
	    return cantidadPersonas;
	}
	 public void setcantidadPersonas(int arg){
	   this.cantidadPersonas = arg;
	 }
	public int[] getesquinasAdyacentes(){
	    return esquinasAdyacentes;
	}
	 public void setesquinasAdyacentes(int[] arg){
	   this.esquinasAdyacentes = arg;
	 }
	public int getcuadrante(){
	    return cuadrante;
	}
	 public void setcuadrante(int arg){
	   this.cuadrante = arg;
	 }
	public int getsubcuadrante(){
	    return subcuadrante;
	}
	 public void setsubcuadrante(int arg){
	   this.subcuadrante = arg;
	 }
	public boolean getcontieneVictimario(){
	    return contieneVictimario;
	}
	 public void setcontieneVictimario(boolean arg){
	   this.contieneVictimario = arg;
	 }
}
