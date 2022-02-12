package com.javaproject;

public class Eggs {
    private String name;
    private String igmSrc;
   // private double price1;
    private double price2;
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

   /* public double getPrice1(){
        return price1;
    }*/

    public double getPrice2() {
        return price2;
    }

    public String getColor() {
        return color;
    }

    public String getIgmSrc() {
        return igmSrc;
    }

    public void setIgmSrc(String igmSrc) {
        this.igmSrc = igmSrc;
    }

    /*public void setPrice1(double price1) {
        this.price1 = price1;
    }*/

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

}
