package frsf.cidisi.exercise.entidades;

public class Esquina {
	int idEsquina;
	int cantidadVictimas;
	int[] esquinasAdyacentes;//Va a tener 8 posiciones 
	int cuadrante;
	int subcuadrante;
	
	public Esquina(int id){
		idEsquina= id;
		cantidadVictimas=0;
		esquinasAdyacentes= new int[8];
		
	}
	public int getidEsquina(){
	    return idEsquina;
	}
	 public void setidEsquina(int arg){
	   this.idEsquina = arg;
	 }
	public int getcantidadVictimas(){
	    return cantidadVictimas;
	}
	 public void setcantidadVictimas(int arg){
	   this.cantidadVictimas = arg;
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
}
