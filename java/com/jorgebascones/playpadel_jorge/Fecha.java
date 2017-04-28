package com.jorgebascones.playpadel_jorge;

/**
 * Created by jorgebascones on 22/4/17.
 */

public class Fecha {

    /**
     * Dia en la fecha
     */
    public int day;

    /**
     * Mes de la fecha
     */
    public int month;

    /**
     * Anno de la fecha
     */
    public int year;

    /**
     * La hora de la fecha
     */
    public int hour;

    /**
     * Los minutos de la fecha
     */
    public int minute;

    /**
     * Constructor vacio de Fecha
     */
    public Fecha(){

    }

    /**
     * Constructor de Fecha con parametros
     *
     * @param day El dia de la fecha
     * @param month La hora de la fecha
     * @param year El anno de la fecha
     * @param hour La hora de la fecha
     * @param minute Los minutos de la fecha
     */
    public Fecha(int day,int month,int year,int hour,int minute){

        this.day=day;
        this.month=month;
        this.year=year;
        this.hour=hour;
        this.minute=minute;


    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

}
