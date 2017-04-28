package com.jorgebascones.playpadel_jorge;

/**
 * Created by jorgebascones on 22/4/17.
 */

public class Fecha {

    public int day;
    public int month;
    public int year;

    public int hour;
    public int minute;

    public Fecha(){

    }


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
