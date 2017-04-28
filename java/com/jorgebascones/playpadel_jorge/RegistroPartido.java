package com.jorgebascones.playpadel_jorge;

/**
 * Created by jorgebascones on 22/4/17.
 */

public class RegistroPartido {


    public double media;
    public double nuevo;
    public int numeroPartidos;

    public RegistroPartido(){

    }


    public RegistroPartido(int numeroPartidos){
        media=0;
        nuevo=0;
        this.numeroPartidos=numeroPartidos;
    }

    public int size(){
        return numeroPartidos;
    }
    public void add(double nuevo){
        this.nuevo=nuevo;
        double calculandoMedia = media*numeroPartidos;
        numeroPartidos++;
        calculandoMedia = (calculandoMedia+nuevo)/numeroPartidos;
        media=calculandoMedia;
    }

    public double getMedia(){
        return media;
    }


}
