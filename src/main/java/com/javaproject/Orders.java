package com.javaproject;

public class Orders {
    private String productId;
    private String productName;
    private int trayQuantity;
    private int eggsQuantity;
    private double pricePerTray;
    private double pricePerEgg;

    public Orders(){}

    public Orders(String productId){
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getTrayQuantity() {
        return trayQuantity;
    }

    public void setTrayQuantity(int trayQuantity) {
        this.trayQuantity = trayQuantity;
    }

    public int getEggsQuantity() {
        return eggsQuantity;
    }

    public void setEggsQuantity(int eggsQuantity) {
        this.eggsQuantity = eggsQuantity;
    }

    public double getPricePerTray() {
        return pricePerTray;
    }

    public void setPricePerTray(double pricePerTray) {
        this.pricePerTray = pricePerTray;
    }

    public double getPricePerEgg() {
        return pricePerEgg;
    }

    public void setPricePerEgg(double pricePerEgg) {
        this.pricePerEgg = pricePerEgg;
    }
}
