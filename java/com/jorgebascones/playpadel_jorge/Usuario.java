package com.jorgebascones.playpadel_jorge;

import java.util.ArrayList;

public class Usuario {

	public String usuarioId;
	public String nombre;
	public int valoracion;
	public String localizacion;
	public double nivel;
	public String misPartidos;
	public boolean isAdmin;


	/**
	 * Hacer la media de los niveles de los partidos que has ganado y de los partidos que has perdido
	 * con esas dos emdias, se calcula la media entre ellas
	 * POr ultimo, con esa media, haces la media con tu nivel
	 * Y ese nivel que te da, lo sustituyes y es tu nuevo nivel
	 */
	
	/**
	 * Nivel que tienen los jugadores contra los que has ganado, media del equipo
	 */
	public RegistroPartido registroGanador = new RegistroPartido(0);
	
	/**
	 * Nivel que tienen los jugadores contra los que has perdido, media del equipo
	 */
	public RegistroPartido registroPerdedor = new RegistroPartido(0);

	public Usuario(){

	}

	public Usuario(String nombre, int nivel,String usuarioId){
		this.usuarioId=usuarioId;
		this.nombre = nombre;
		this.nivel = nivel;
		misPartidos = "&";
	}

	public String getNombre() {
		return nombre;
	}

	public int getValoracion() {
		return valoracion;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public double getNivel() {
		return nivel;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public void setNivel(double nivel) {
		this.nivel = nivel;
	}
	
	public int sizeGanados(){
		return registroGanador.size();
	}
	
	public int sizePerdidos(){
		return registroPerdedor.size();
	}
	
	public double getMediaGanados(){
		return registroGanador.getMedia();
	}
	
	public double getMediaPerdidos(){
		return registroPerdedor.getMedia();
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public String getMisPartidos(){return misPartidos;}

	public void setMisPartidos(String misPartidos){this.misPartidos=misPartidos;}

	public boolean getIsAdmin(){return isAdmin;}

	public void setIsAdmin(boolean isAdmin){this.isAdmin=isAdmin;}

	
}
