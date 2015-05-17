package frsf.cidisi.exercise.entidades;

import java.util.ArrayList;

public class Subcuadrante {
	int intensidad;
	ArrayList<Esquina> listaEsquinas;
	
	public Subcuadrante(){
		intensidad=0;
		listaEsquinas= new ArrayList<Esquina> ();
	}
	
	public ArrayList<Esquina> getlistaEsquinas(){
	    return listaEsquinas;
	}
	 public void setlistaEsquinas(ArrayList<Esquina> arg){
	   this.listaEsquinas = arg;
	 }
	public int getintensidad(){
	    return intensidad;
	}
	 public void setintensidad(int arg){
	   this.intensidad = arg;
	 }
	 
}
