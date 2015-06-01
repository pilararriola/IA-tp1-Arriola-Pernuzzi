package frsf.cidisi.exercise.entidades;

import java.util.ArrayList;

public class Mapa {
	private ArrayList<Cuadrante> listaCuadrantes;
	public ArrayList<Subcuadrante> listaSubcuadrantes1;
	public ArrayList<Subcuadrante> listaSubcuadrantes2;
	public ArrayList<Subcuadrante> listaSubcuadrantes3;
	public ArrayList<Subcuadrante> listaSubcuadrantes4;
	public ArrayList<Esquina> listaEsquinas1;
	public ArrayList<Esquina> listaEsquinas2;
	public ArrayList<Esquina> listaEsquinas3;
	public ArrayList<Esquina> listaEsquinas4;
	public ArrayList<Esquina> listaEsquinas5;
	public ArrayList<Esquina> listaEsquinas6;
	public ArrayList<Esquina> listaEsquinas7;
	public ArrayList<Esquina> listaEsquinas8;
	public ArrayList<Esquina> listaEsquinas9;
	public ArrayList<Esquina> listaEsquinas10;
	public ArrayList<Esquina> listaEsquinas11;
	public ArrayList<Esquina> listaEsquinas12;
	public ArrayList<Esquina> listaEsquinas13;
	public ArrayList<Esquina> listaEsquinas14;
	public ArrayList<Esquina> listaEsquinas15;
	public ArrayList<Esquina> listaEsquinas16;
	private ArrayList<Esquina> listaEsquinas;
	private int[] posicionDrone;
	private int posicionVictimario;
	
	public Mapa(){
		setMapaCompleto();
	}
	
	public Mapa(int esc){
		int escenario=esc;
		setMapaCompleto();

		switch(escenario){
		case 1:
			setPosicionDrone(new int[]{2,2,0,0});
			setPosicionVictimario(34);
			getListaEsquinas().get(34-1).setcontieneVictimario(true);
			//Se setea la cantidad de personas (víctimas y/o victimario) en las esquinas que tienen al menos una
			getListaEsquinas().get(20).setcantidadPersonas(1);
			getListaEsquinas().get(21).setcantidadPersonas(3);
			getListaEsquinas().get(31).setcantidadPersonas(1);
			getListaEsquinas().get(32).setcantidadPersonas(4);
			getListaEsquinas().get(33).setcantidadPersonas(1);
			getListaEsquinas().get(40).setcantidadPersonas(2);
			getListaEsquinas().get(41).setcantidadPersonas(2);
			this.calcularIntensidad();
			break;
		case 2:
			setPosicionDrone(new int[]{2,3,0,0});
			setPosicionVictimario(31);
			getListaEsquinas().get(31-1).setcontieneVictimario(true);
			//Se setea la cantidad de  personas (víctimas y/o victimario) en las esquinas que tienen al menos una
			getListaEsquinas().get(27).setcantidadPersonas(1);
			getListaEsquinas().get(28).setcantidadPersonas(1);
			getListaEsquinas().get(30).setcantidadPersonas(2);
			getListaEsquinas().get(36).setcantidadPersonas(2);
			getListaEsquinas().get(37).setcantidadPersonas(3);
			getListaEsquinas().get(39).setcantidadPersonas(4);
			getListaEsquinas().get(51).setcantidadPersonas(2);
			this.calcularIntensidad();
			break;
		}
	}
	private void setMapaCompleto() {
		//Se cargan todas las esquinas presentes en el mapa:
		//El array de esquinas adyacentes tiene las esquinas a las que se puede llegar desde la esquina actual
		//en este orden de direcciones: N, NE, E, SE, S, SO, O, NO
		//Si no hay esquina adyacente se coloca un 0
		setListaEsquinas(new ArrayList<Esquina>());
		getListaEsquinas().add(new Esquina(1,new int[]{0,0,2,0,16,0,0,0}));
		getListaEsquinas().add(new Esquina(2,new int[]{0,0,3,0,18,0,1,0}));
		getListaEsquinas().add(new Esquina(3,new int[]{0,0,4,0,19,0,2,0}));
		getListaEsquinas().add(new Esquina(4,new int[]{0,0,5,0,10,0,3,0}));
		getListaEsquinas().add(new Esquina(5,new int[]{0,0,6,0,11,0,4,0}));
		getListaEsquinas().add(new Esquina(6,new int[]{0,0,7,0,12,0,5,0}));
		getListaEsquinas().add(new Esquina(7,new int[]{0,0,8,0,13,0,6,0}));
		getListaEsquinas().add(new Esquina(8,new int[]{0,0,9,0,14,0,7,0}));
		getListaEsquinas().add(new Esquina(9,new int[]{0,0,0,0,15,0,8,0}));
		getListaEsquinas().add(new Esquina(10,new int[]{4,0,11,0,20,0,0,0}));
		getListaEsquinas().add(new Esquina(11,new int[]{5,0,12,0,21,0,10,0}));
		getListaEsquinas().add(new Esquina(12,new int[]{6,0,13,0,22,0,11,0}));
		getListaEsquinas().add(new Esquina(13,new int[]{7,0,14,0,23,0,12,0}));
		getListaEsquinas().add(new Esquina(14,new int[]{8,0,15,0,24,0,13,0}));
		getListaEsquinas().add(new Esquina(15,new int[]{9,0,0,0,25,0,14,0}));
		getListaEsquinas().add(new Esquina(16,new int[]{1,0,17,0,26,0,0,0}));
		getListaEsquinas().add(new Esquina(17,new int[]{0,0,18,27,0,0,16,0}));
		getListaEsquinas().add(new Esquina(18,new int[]{2,0,19,0,27,0,17,0}));
		getListaEsquinas().add(new Esquina(19,new int[]{3,0,20,0,30,0,18,0}));
		getListaEsquinas().add(new Esquina(20,new int[]{10,0,21,0,31,0,19,0}));
		getListaEsquinas().add(new Esquina(21,new int[]{11,0,22,0,32,0,20,0}));
		getListaEsquinas().add(new Esquina(22,new int[]{12,0,23,0,33,0,21,0}));
		getListaEsquinas().add(new Esquina(23,new int[]{13,0,24,0,34,0,22,0}));
		getListaEsquinas().add(new Esquina(24,new int[]{14,0,25,0,35,0,23,0}));
		getListaEsquinas().add(new Esquina(25,new int[]{15,0,0,0,36,0,24,0}));
		getListaEsquinas().add(new Esquina(26,new int[]{16,0,27,0,28,0,0,0}));
		getListaEsquinas().add(new Esquina(27,new int[]{18,0,0,30,29,0,26,17}));
		getListaEsquinas().add(new Esquina(28,new int[]{26,0,29,0,37,0,0,0}));
		getListaEsquinas().add(new Esquina(29,new int[]{27,0,30,0,38,0,28,0}));
		getListaEsquinas().add(new Esquina(30,new int[]{19,0,31,0,39,0,29,27}));
		getListaEsquinas().add(new Esquina(31,new int[]{20,0,32,0,40,0,30,0}));
		getListaEsquinas().add(new Esquina(32,new int[]{21,0,33,0,41,0,31,0}));
		getListaEsquinas().add(new Esquina(33,new int[]{22,0,34,0,42,0,32,0}));
		getListaEsquinas().add(new Esquina(34,new int[]{23,0,35,0,43,0,33,0}));
		getListaEsquinas().add(new Esquina(35,new int[]{24,0,36,0,44,0,34,0}));
		getListaEsquinas().add(new Esquina(36,new int[]{25,0,0,0,45,0,35,0}));
		getListaEsquinas().add(new Esquina(37,new int[]{28,0,38,0,51,0,0,0}));
		getListaEsquinas().add(new Esquina(38,new int[]{29,0,39,0,52,0,37,0}));
		getListaEsquinas().add(new Esquina(39,new int[]{30,0,40,0,53,0,38,0}));
		getListaEsquinas().add(new Esquina(40,new int[]{31,0,41,0,54,0,39,0}));
		getListaEsquinas().add(new Esquina(41,new int[]{32,0,42,0,46,0,40,0}));
		getListaEsquinas().add(new Esquina(42,new int[]{33,0,43,0,47,0,41,0}));
		getListaEsquinas().add(new Esquina(43,new int[]{34,0,44,0,48,0,42,0}));
		getListaEsquinas().add(new Esquina(44,new int[]{35,0,45,0,49,0,43,0}));
		getListaEsquinas().add(new Esquina(45,new int[]{36,0,0,0,50,0,44,0}));
		getListaEsquinas().add(new Esquina(46,new int[]{41,0,47,0,55,0,0,0}));
		getListaEsquinas().add(new Esquina(47,new int[]{42,0,48,0,56,0,46,0}));
		getListaEsquinas().add(new Esquina(48,new int[]{43,0,49,0,57,0,47,0}));
		getListaEsquinas().add(new Esquina(49,new int[]{44,0,50,0,58,0,48,0}));
		getListaEsquinas().add(new Esquina(50,new int[]{45,0,0,0,59,0,49,0}));
		getListaEsquinas().add(new Esquina(51,new int[]{37,0,52,0,60,0,0,0}));
		getListaEsquinas().add(new Esquina(52,new int[]{38,0,53,0,61,0,51,0}));
		getListaEsquinas().add(new Esquina(53,new int[]{39,0,54,0,62,0,52,0}));
		getListaEsquinas().add(new Esquina(54,new int[]{40,0,55,0,63,0,53,0}));
		getListaEsquinas().add(new Esquina(55,new int[]{46,0,56,0,64,0,54,0}));
		getListaEsquinas().add(new Esquina(56,new int[]{47,0,57,0,66,0,55,0}));
		getListaEsquinas().add(new Esquina(57,new int[]{48,0,58,0,67,0,56,0}));
		getListaEsquinas().add(new Esquina(58,new int[]{49,0,59,0,68,0,57,0}));
		getListaEsquinas().add(new Esquina(59,new int[]{50,0,0,0,69,0,58,0}));
		getListaEsquinas().add(new Esquina(60,new int[]{51,0,61,0,70,0,0,0}));
		getListaEsquinas().add(new Esquina(61,new int[]{52,0,62,0,71,0,60,0}));
		getListaEsquinas().add(new Esquina(62,new int[]{53,0,63,0,72,0,61,0}));
		getListaEsquinas().add(new Esquina(63,new int[]{54,0,64,0,73,0,62,0}));
		getListaEsquinas().add(new Esquina(64,new int[]{55,0,65,0,74,0,63,0}));
		getListaEsquinas().add(new Esquina(65,new int[]{0,0,66,75,0,0,64,0}));
		getListaEsquinas().add(new Esquina(66,new int[]{56,0,67,76,75,0,65,0}));
		getListaEsquinas().add(new Esquina(67,new int[]{57,0,68,0,76,0,66,0}));
		getListaEsquinas().add(new Esquina(68,new int[]{58,0,69,0,77,0,67,0}));
		getListaEsquinas().add(new Esquina(69,new int[]{59,0,0,0,78,0,68,0}));
		getListaEsquinas().add(new Esquina(70,new int[]{60,0,71,0,0,0,0,0}));
		getListaEsquinas().add(new Esquina(71,new int[]{61,0,72,0,0,0,70,0}));
		getListaEsquinas().add(new Esquina(72,new int[]{62,0,73,0,0,0,71,0}));
		getListaEsquinas().add(new Esquina(73,new int[]{63,0,74,0,0,0,72,0}));
		getListaEsquinas().add(new Esquina(74,new int[]{64,0,75,0,0,0,73,0}));
		getListaEsquinas().add(new Esquina(75,new int[]{66,0,76,0,0,0,74,65}));
		getListaEsquinas().add(new Esquina(76,new int[]{67,0,77,0,0,0,75,66}));
		getListaEsquinas().add(new Esquina(77,new int[]{68,0,78,0,0,0,76,0}));
		getListaEsquinas().add(new Esquina(78,new int[]{69,0,0,0,0,0,77,0}));
		
		//Se cargan todas las esquinas de cada subcuadrante
		//Subcuadrante A1M1:
		listaEsquinas1=new ArrayList<Esquina>();
		listaEsquinas1.add(getListaEsquinas().get(0));//La posición en el ArrayList ya creado es: idEsquina-1
		listaEsquinas1.add(getListaEsquinas().get(1));
		listaEsquinas1.add(getListaEsquinas().get(2));
		listaEsquinas1.add(getListaEsquinas().get(15));
		listaEsquinas1.add(getListaEsquinas().get(16));
		listaEsquinas1.add(getListaEsquinas().get(17));
		listaEsquinas1.add(getListaEsquinas().get(18));
		
		//Subcuadrante A1M2:
		listaEsquinas2=new ArrayList<Esquina>();
		listaEsquinas2.add(getListaEsquinas().get(2));
		listaEsquinas2.add(getListaEsquinas().get(3));
		listaEsquinas2.add(getListaEsquinas().get(4));
		listaEsquinas2.add(getListaEsquinas().get(9));
		listaEsquinas2.add(getListaEsquinas().get(10));
		listaEsquinas2.add(getListaEsquinas().get(18));
		listaEsquinas2.add(getListaEsquinas().get(19));
		listaEsquinas2.add(getListaEsquinas().get(20));
		
		//Subcuadrante A1M3:
		listaEsquinas3=new ArrayList<Esquina>();
		listaEsquinas3.add(getListaEsquinas().get(15));
		listaEsquinas3.add(getListaEsquinas().get(16));
		listaEsquinas3.add(getListaEsquinas().get(17));
		listaEsquinas3.add(getListaEsquinas().get(18));
		listaEsquinas3.add(getListaEsquinas().get(25));
		listaEsquinas3.add(getListaEsquinas().get(26));
		listaEsquinas3.add(getListaEsquinas().get(27));
		listaEsquinas3.add(getListaEsquinas().get(28));
		listaEsquinas3.add(getListaEsquinas().get(29));
		listaEsquinas3.add(getListaEsquinas().get(36));
		listaEsquinas3.add(getListaEsquinas().get(37));
		listaEsquinas3.add(getListaEsquinas().get(38));
		
		//Subcuadrante A1M4:
		listaEsquinas4=new ArrayList<Esquina>();
		listaEsquinas4.add(getListaEsquinas().get(18));
		listaEsquinas4.add(getListaEsquinas().get(19));
		listaEsquinas4.add(getListaEsquinas().get(20));
		listaEsquinas4.add(getListaEsquinas().get(29));
		listaEsquinas4.add(getListaEsquinas().get(30));
		listaEsquinas4.add(getListaEsquinas().get(31));
		listaEsquinas4.add(getListaEsquinas().get(38));
		listaEsquinas4.add(getListaEsquinas().get(39));
		listaEsquinas4.add(getListaEsquinas().get(40));
		
		//Subcuadrante A2M1:
		listaEsquinas5=new ArrayList<Esquina>();
		listaEsquinas5.add(getListaEsquinas().get(4));
		listaEsquinas5.add(getListaEsquinas().get(5));
		listaEsquinas5.add(getListaEsquinas().get(6));
		listaEsquinas5.add(getListaEsquinas().get(10));
		listaEsquinas5.add(getListaEsquinas().get(11));
		listaEsquinas5.add(getListaEsquinas().get(12));
		listaEsquinas5.add(getListaEsquinas().get(20));
		listaEsquinas5.add(getListaEsquinas().get(21));
		listaEsquinas5.add(getListaEsquinas().get(22));
		
		//Subcuadrante A2M2:
		listaEsquinas6=new ArrayList<Esquina>();
		listaEsquinas6.add(getListaEsquinas().get(6));
		listaEsquinas6.add(getListaEsquinas().get(7));
		listaEsquinas6.add(getListaEsquinas().get(8));
		listaEsquinas6.add(getListaEsquinas().get(12));
		listaEsquinas6.add(getListaEsquinas().get(13));
		listaEsquinas6.add(getListaEsquinas().get(14));
		listaEsquinas6.add(getListaEsquinas().get(22));
		listaEsquinas6.add(getListaEsquinas().get(23));
		listaEsquinas6.add(getListaEsquinas().get(24));
		
		//Subcuadrante A2M3:
		listaEsquinas7=new ArrayList<Esquina>();
		listaEsquinas7.add(getListaEsquinas().get(20));
		listaEsquinas7.add(getListaEsquinas().get(21));
		listaEsquinas7.add(getListaEsquinas().get(22));
		listaEsquinas7.add(getListaEsquinas().get(31));
		listaEsquinas7.add(getListaEsquinas().get(32));
		listaEsquinas7.add(getListaEsquinas().get(33));
		listaEsquinas7.add(getListaEsquinas().get(40));
		listaEsquinas7.add(getListaEsquinas().get(41));
		listaEsquinas7.add(getListaEsquinas().get(42));
		
		//Subcuadrante A2M4:
		listaEsquinas8=new ArrayList<Esquina>();
		listaEsquinas8.add(getListaEsquinas().get(22));
		listaEsquinas8.add(getListaEsquinas().get(23));
		listaEsquinas8.add(getListaEsquinas().get(24));
		listaEsquinas8.add(getListaEsquinas().get(33));
		listaEsquinas8.add(getListaEsquinas().get(34));
		listaEsquinas8.add(getListaEsquinas().get(35));
		listaEsquinas8.add(getListaEsquinas().get(42));
		listaEsquinas8.add(getListaEsquinas().get(43));
		listaEsquinas8.add(getListaEsquinas().get(44));
		
		//Subcuadrante A3M1:
		listaEsquinas9=new ArrayList<Esquina>();
		listaEsquinas9.add(getListaEsquinas().get(36));
		listaEsquinas9.add(getListaEsquinas().get(37));
		listaEsquinas9.add(getListaEsquinas().get(38));
		listaEsquinas9.add(getListaEsquinas().get(50));
		listaEsquinas9.add(getListaEsquinas().get(51));
		listaEsquinas9.add(getListaEsquinas().get(52));
		
		//Subcuadrante A3M2:
		listaEsquinas10=new ArrayList<Esquina>();
		listaEsquinas10.add(getListaEsquinas().get(38));
		listaEsquinas10.add(getListaEsquinas().get(39));
		listaEsquinas10.add(getListaEsquinas().get(40));
		listaEsquinas10.add(getListaEsquinas().get(45));
		listaEsquinas10.add(getListaEsquinas().get(52));
		listaEsquinas10.add(getListaEsquinas().get(53));
		listaEsquinas10.add(getListaEsquinas().get(54));
		
		//Subcuadrante A3M3:
		listaEsquinas11=new ArrayList<Esquina>();
		listaEsquinas11.add(getListaEsquinas().get(50));
		listaEsquinas11.add(getListaEsquinas().get(51));
		listaEsquinas11.add(getListaEsquinas().get(52));
		listaEsquinas11.add(getListaEsquinas().get(59));
		listaEsquinas11.add(getListaEsquinas().get(60));
		listaEsquinas11.add(getListaEsquinas().get(61));
		listaEsquinas11.add(getListaEsquinas().get(69));
		listaEsquinas11.add(getListaEsquinas().get(70));
		listaEsquinas11.add(getListaEsquinas().get(71));
		
		//Subcuadrante A3M4:
		listaEsquinas12=new ArrayList<Esquina>();
		listaEsquinas12.add(getListaEsquinas().get(52));
		listaEsquinas12.add(getListaEsquinas().get(53));
		listaEsquinas12.add(getListaEsquinas().get(54));
		listaEsquinas12.add(getListaEsquinas().get(61));
		listaEsquinas12.add(getListaEsquinas().get(62));
		listaEsquinas12.add(getListaEsquinas().get(63));
		listaEsquinas12.add(getListaEsquinas().get(71));
		listaEsquinas12.add(getListaEsquinas().get(72));
		listaEsquinas12.add(getListaEsquinas().get(73));
		
		//Subcuadrante A4M1:
		listaEsquinas13=new ArrayList<Esquina>();
		listaEsquinas13.add(getListaEsquinas().get(40));
		listaEsquinas13.add(getListaEsquinas().get(41));
		listaEsquinas13.add(getListaEsquinas().get(42));
		listaEsquinas13.add(getListaEsquinas().get(45));
		listaEsquinas13.add(getListaEsquinas().get(46));
		listaEsquinas13.add(getListaEsquinas().get(47));
		listaEsquinas13.add(getListaEsquinas().get(54));
		listaEsquinas13.add(getListaEsquinas().get(55));
		listaEsquinas13.add(getListaEsquinas().get(56));
		
		//Subcuadrante A4M2:
		listaEsquinas14=new ArrayList<Esquina>();
		listaEsquinas14.add(getListaEsquinas().get(42));
		listaEsquinas14.add(getListaEsquinas().get(43));
		listaEsquinas14.add(getListaEsquinas().get(44));
		listaEsquinas14.add(getListaEsquinas().get(47));
		listaEsquinas14.add(getListaEsquinas().get(48));
		listaEsquinas14.add(getListaEsquinas().get(49));
		listaEsquinas14.add(getListaEsquinas().get(56));
		listaEsquinas14.add(getListaEsquinas().get(57));
		listaEsquinas14.add(getListaEsquinas().get(58));
		
		//Subcuadrante A4M3:
		listaEsquinas15=new ArrayList<Esquina>();
		listaEsquinas15.add(getListaEsquinas().get(54));
		listaEsquinas15.add(getListaEsquinas().get(55));
		listaEsquinas15.add(getListaEsquinas().get(56));
		listaEsquinas15.add(getListaEsquinas().get(63));
		listaEsquinas15.add(getListaEsquinas().get(64));
		listaEsquinas15.add(getListaEsquinas().get(65));
		listaEsquinas15.add(getListaEsquinas().get(66));
		listaEsquinas15.add(getListaEsquinas().get(73));
		listaEsquinas15.add(getListaEsquinas().get(74));
		listaEsquinas15.add(getListaEsquinas().get(75));
		
		//Subcuadrante A4M4:
		listaEsquinas16=new ArrayList<Esquina>();
		listaEsquinas16.add(getListaEsquinas().get(56));
		listaEsquinas16.add(getListaEsquinas().get(57));
		listaEsquinas16.add(getListaEsquinas().get(58));
		listaEsquinas16.add(getListaEsquinas().get(66));
		listaEsquinas16.add(getListaEsquinas().get(67));
		listaEsquinas16.add(getListaEsquinas().get(68));
		listaEsquinas16.add(getListaEsquinas().get(75));
		listaEsquinas16.add(getListaEsquinas().get(76));
		listaEsquinas16.add(getListaEsquinas().get(77));
		
		//Se cargan todos los subcuadrantes de cada cuadrante:
		//Cuadrante A1:
		listaSubcuadrantes1=new ArrayList<Subcuadrante>();
		listaSubcuadrantes1.add(new Subcuadrante(listaEsquinas1));
		listaSubcuadrantes1.add(new Subcuadrante(listaEsquinas2));
		listaSubcuadrantes1.add(new Subcuadrante(listaEsquinas3));
		listaSubcuadrantes1.add(new Subcuadrante(listaEsquinas4));
		
		//Cuadrante A2:
		listaSubcuadrantes2=new ArrayList<Subcuadrante>();
		listaSubcuadrantes2.add(new Subcuadrante(listaEsquinas5));
		listaSubcuadrantes2.add(new Subcuadrante(listaEsquinas6));
		listaSubcuadrantes2.add(new Subcuadrante(listaEsquinas7));
		listaSubcuadrantes2.add(new Subcuadrante(listaEsquinas8));
		
		//Cuadrante A3:
		listaSubcuadrantes3=new ArrayList<Subcuadrante>();
		listaSubcuadrantes3.add(new Subcuadrante(listaEsquinas9));
		listaSubcuadrantes3.add(new Subcuadrante(listaEsquinas10));
		listaSubcuadrantes3.add(new Subcuadrante(listaEsquinas11));
		listaSubcuadrantes3.add(new Subcuadrante(listaEsquinas12));
		
		//Cuadrante A4:
		listaSubcuadrantes4=new ArrayList<Subcuadrante>();
		listaSubcuadrantes4.add(new Subcuadrante(listaEsquinas13));
		listaSubcuadrantes4.add(new Subcuadrante(listaEsquinas14));
		listaSubcuadrantes4.add(new Subcuadrante(listaEsquinas15));
		listaSubcuadrantes4.add(new Subcuadrante(listaEsquinas16));
		
		//Se crean los cuadrantes y se les asigna la lista de subcuadrantes correspondiente:
		setListaCuadrantes(new ArrayList<Cuadrante>());
		getListaCuadrantes().add(new Cuadrante(1,listaSubcuadrantes1));
		getListaCuadrantes().add(new Cuadrante(2,listaSubcuadrantes2));
		getListaCuadrantes().add(new Cuadrante(3,listaSubcuadrantes3));
		getListaCuadrantes().add(new Cuadrante(4,listaSubcuadrantes4));
		
	}


	private void calcularIntensidad(){
		for(Cuadrante cuadrante : getListaCuadrantes()){
			int victimasCuadrante=0;
			ArrayList<Esquina> esquinasConVictimas=new ArrayList<Esquina>();
			for(Subcuadrante subcuadrante : cuadrante.getlistaSubcuadrantes()){
				int victimasSubcuadrante=0;
				for(Esquina esquina : subcuadrante.getlistaEsquinas()){
					victimasSubcuadrante+=esquina.getcantidadPersonas();
					if(esquina.getcantidadPersonas()>0 && !esquinasConVictimas.contains(esquina)){
						esquinasConVictimas.add(esquina);
					}
				}
				subcuadrante.setintensidad(victimasSubcuadrante*20);	
			}
			for(Esquina esq : esquinasConVictimas){
				victimasCuadrante+=esq.getcantidadPersonas();
			}
			cuadrante.setintensidad(victimasCuadrante*10);
		}
	}
	public void setListaCuadrantes(ArrayList<Cuadrante> lCuadrantes) {
		listaCuadrantes = lCuadrantes;
	}
	public ArrayList<Cuadrante> getListaCuadrantes() {
		return listaCuadrantes;
	}
	public void setListaEsquinas(ArrayList<Esquina> lEsquinas) {
		listaEsquinas = lEsquinas;
	}
	public ArrayList<Esquina> getListaEsquinas() {
		return listaEsquinas;
	}
	public void setPosicionVictimario(int posVictimario) {
		posicionVictimario = posVictimario;
	}
	public int getPosicionVictimario() {
		return posicionVictimario;
	}
	public void setPosicionDrone(int[] posDrone) {
		posicionDrone = posDrone;
	}
	public int[] getPosicionDrone() {
		return posicionDrone;
	}
		
}
