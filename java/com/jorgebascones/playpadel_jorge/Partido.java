package com.jorgebascones.playpadel_jorge;

import java.util.Date;

public class Partido {



	public String partidoId;
	public Usuario jugador1;
	public Usuario jugador2;
	public Usuario jugador3;
	public Usuario jugador4;
	public boolean resultadoj1;
	public boolean resultadoj2;
	public boolean resultadoj3;
	public boolean resultadoj4;
	
	//TODO No sabemos como manejar la localizacion
	//private String localizacion;
	
	public boolean finalizado;
	public Fecha fechaO;



	public Partido(){

	}

	
	/**
	 * 
	 * @param uno Usuario que inicia el partido
	 * @param fechaElegida Fecha del partido
	 */
	public Partido(Usuario uno, Fecha fechaElegida){
		this.jugador1 = uno;
		fechaO = fechaElegida;

	}
	
	
	
	/**
	 * 
	 * @return la cantidad de juagdores que faltan para que se complete un partido
	 */
	public int jugadoresRestantes(){
		if(jugador2 == null)
			return 3;
		if(jugador3 == null)
			return 2;
		if(jugador4 == null)
			return 1;
		return 0;
	}

	/**
	 * Crea los jugadores en funcion de los jugadores que falten en el partido
	 * 
	 * @param jugador Jugador a meter en el partido
	 */
	public void setJugadoresRestantes(Usuario jugador){
		int jugadoresRestantes = jugadoresRestantes();
		if(jugadoresRestantes == 3){
			setJugador2(jugador);
		}
		else if(jugadoresRestantes == 2){
			setJugador3(jugador);
		}
		else if(jugadoresRestantes == 1){
			setJugador4(jugador);
		}
		else{
			//Mandar toast de que el partido esta completo
		}
	}
	
	public void setFinalizado(boolean finalizado){
		this.finalizado = finalizado;
	}
	
	public boolean getFinalizado(){
		return finalizado;
	}
	
	public Usuario getJugador1() {
		return jugador1;
	}

	public Usuario getJugador2() {
		return jugador2;
	}

	public Usuario getJugador3() {
		return jugador3;
	}

	public Usuario getJugador4() {
		return jugador4;
	}

	public Fecha getDate() {
		return fechaO;
	}

	public void setJugador1(Usuario jugador1) {
		this.jugador1 = jugador1;
	}

	public void setJugador2(Usuario jugador2) {
		this.jugador2 = jugador2;
	}

	public void setJugador3(Usuario jugador3) {
		this.jugador3 = jugador3;
	}

	public void setJugador4(Usuario jugador4) {
		this.jugador4 = jugador4;
	}

	public void setDate(Fecha fecha) {
		
		fechaO=fecha;
	}



	public void setResultado(boolean result1,boolean result2,boolean result3,boolean result4) {

		resultadoj1=result1;
		resultadoj2=result2;
		resultadoj3=result3;
		resultadoj4=result4;



	}
	
	public int getIndice(Usuario jugador){
		if(this.jugador1 == jugador)
		return 0;
		if(this.jugador2 == jugador)
			return 1;
		if(this.jugador3 == jugador)
			return 2;
		if(this.jugador4 == jugador)
			return 3;
		return -1;
	}


	public String getPartidoId() {
		return partidoId;
	}

	public void setPartidoId(String partidoId) {
		this.partidoId = partidoId;
	}



/*
	
	private void nivel(Usuario jugador){
		
		int totalGanados = jugador.sizeGanados();
		int totalPerdidos = jugador.sizePerdidos();
		int totalPartidos = totalGanados + totalPerdidos;
		
		double porcentajeGanados = totalGanados/totalPartidos;
		double porcentajePerdidos = totalPerdidos/totalPartidos;
		
		double mediaGanado = jugador.getMediaGanados();
		double mediaPerdido = jugador.getMediaPerdidos();
		
		double media = 0;
		if(mediaGanado != 0 && mediaPerdido != 0){
			media = (mediaGanado*porcentajeGanados + mediaPerdido*porcentajePerdidos);
		}
	    else if(mediaGanado == 0) {
			media = mediaPerdido;
		}
	    else{
	    	media = mediaGanado;
	    }
		double nivelJugador = jugador.getNivel();
		
		double mediaFinal = (nivelJugador + media)/2;
		jugador.setNivel(mediaFinal);
	}
	
	*/
		

}
