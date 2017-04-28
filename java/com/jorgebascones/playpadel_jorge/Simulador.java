package com.jorgebascones.playpadel_jorge;

import java.util.ArrayList;

public class Simulador {
	
	private String [] nombres = {"Paco","Pepe","Pedro","Antonio","Marco","David","Alex","Javier","Nacho","Ana","Marta","Laura","Irene","Félix","Héctor","Marian","Carmela","Almudena"};
	private String [] apellidos ={"Lopez","Perez","Porto","Rodriguez","Fernandez","Hernandez","Redondo","Cuadrado","Villalba","Antunez","Bermudez","Olmo","Delgado","Rubio","Moreno","del Pozo","Cañizares"};


	public Simulador(){

	}

	
	public String getNombre(){
		int n = nombres.length;
		int numero = (int) (Math.random() * n) ;
		return nombres[numero];
	}
	public String getApellido(){
		int n = apellidos.length;
		int numero = (int) (Math.random() * n) ;
		return apellidos[numero];
	}
	
	 public Usuario [] crearUsuarios(int numero){

         Usuario [] usuariosCreados = new Usuario[numero];

		for (int i=0; i<numero;i++){
			usuariosCreados[i]= new Usuario(getNombreUsuario(),simularPuntuacionAleatoria(),"id");
		}

		return usuariosCreados;
	}

	public String getNombreUsuario(){
        return getNombre()+" "+getApellido();
    }

    public int simularPuntuacionAleatoria(){

        return getNumeroAleatorio(10) ;
    }

    public int getNumeroAleatorio(int max){

        return (int) (Math.random() * max) ;
    }


    public Fecha simularFecha (){

        int [] fA = {getNumeroAleatorio(27)+1,getNumeroAleatorio(11)+1,2017,getNumeroAleatorio(23),15*getNumeroAleatorio(3),1};
        Fecha fechaSimulada = new Fecha(fA[0],fA[1],fA[2],fA[3],fA[4]);
        return fechaSimulada;
    }


    public Partido simularMiPartido(Usuario yo, boolean acabado){

        Fecha fecha = simularFecha();
        Partido partido = new Partido(yo,fecha);
        int jugadoresMiPartido = getNumeroAleatorio(3);
        Usuario[] usuariosMiPartido = crearUsuarios(jugadoresMiPartido);

        for(int i=0;i<jugadoresMiPartido;i++) {
            partido.setJugadoresRestantes(usuariosMiPartido[i]);
        }

        partido.setFinalizado(acabado);


        return partido;

    }

    public Partido [] simularMisPartidos(Usuario yo){

        int numeroPartidos = getNumeroAleatorio(10)+1;

        Partido [] misPartidos = new Partido[numeroPartidos];

        for(int i =0;i<numeroPartidos;i++){
            misPartidos[i]=simularMiPartido(yo, false);
        }


        return misPartidos;
    }

    public void añadirMisPartidosSimulados(Usuario yo, ArrayList<Partido> misPartidos){


        Partido[] misPartidosSimulados = simularMisPartidos(yo);


        for(int i=0;i<misPartidosSimulados.length;i++){
            misPartidos.add(misPartidosSimulados[i]);
        }

    }
	
}
