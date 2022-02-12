package com.javaproject;

public class EggsProducts {
    private int poultryCode;
    private String productId;
    private String productName;
    private int productQuantity;
    private double pricePerTray;
    private double pricePerEgg;
    private int eggsQuantity;
    private int trayQuantity;



    public EggsProducts(){}

    public EggsProducts(int poultryCode, String productId, String productName , int productQuantity){
        this.poultryCode = poultryCode;
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
    }

    public EggsProducts(String productId,String productName,double pricePerTray,double pricePerEgg ){
        this.productId = productId;
        this.productName = productName;
        this.pricePerTray =pricePerTray;
        this.pricePerEgg = pricePerEgg;
    }

    public EggsProducts(String productId, String productName, int trayQuantity,int eggsQuantity, double pricePerTray,double pricePerEgg){
        this.productId = productId;
        this.productName = productName;
        this.trayQuantity = trayQuantity;
        this.eggsQuantity =eggsQuantity;
        this.pricePerTray = pricePerTray;
        this.pricePerEgg = pricePerEgg;
    }

    public double getPricePerEgg() {
        return pricePerEgg;
    }

    public void setPricePerEgg(double pricePerEgg) {
        this.pricePerEgg = pricePerEgg;
    }

    public int getEggsQuantity() {
        return eggsQuantity;
    }

    public void setEggsQuantity(int pQuantity) {
        int eggsQuantity = pQuantity % 30;
        this.eggsQuantity = eggsQuantity;
    }

    public int getTrayQuantity() {
        return trayQuantity;
    }

    public void setTrayQuantity(int pQuantity) {
        int trayQuantity = pQuantity / 30;
        this.trayQuantity = trayQuantity;
    }

    public void setPoultryCode(int poultryCode){
        this.poultryCode = poultryCode;
    }

    public int getPoultryCode(){
        return poultryCode;
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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getPricePerTray() {
        return pricePerTray;
    }

    public void setPricePerTray(double price) {
        this.pricePerTray = price;
    }
}
