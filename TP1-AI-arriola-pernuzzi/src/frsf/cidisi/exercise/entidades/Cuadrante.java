package frsf.cidisi.exercise.entidades;

import java.util.ArrayList;

public class Cuadrante {
	int idCuadrante;
	ArrayList<Subcuadrante> listaSubcuadrantes;
	int intensidad; //Intensidad de señal que emite según cantidad de personas que se encuentran en él
	
	public Cuadrante(int id,ArrayList<Subcuadrante> subcuadrantes){
		idCuadrante= id;
		listaSubcuadrantes= subcuadrantes ;
		intensidad=0;
	}
	public int getidCuadrante(){
	    return idCuadrante;
	}
	 public void setidCuadrante(int arg){
	   this.idCuadrante = arg;
	 }
	public ArrayList<Subcuadrante> getlistaSubcuadrantes(){
	    return listaSubcuadrantes;
	}
	 public void setlistaSubcuadrantes(ArrayList<Subcuadrante> arg){
	   this.listaSubcuadrantes = arg;
	 }
	public int getintensidad(){
	    return intensidad;
	}
	 public void setintensidad(int arg){
	   this.intensidad = arg;
	 }
	 
}


