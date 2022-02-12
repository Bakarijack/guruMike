package com.javaproject;

public class PoultryDetails {
    private int poultryId;
    private String poultryName;
    private int poultryQuantity;

    public PoultryDetails(){  //no-args constructor
    }

    public PoultryDetails(int poultryId,String poultryName,int poultryQuantity){
        this.poultryId = poultryId;
        this.poultryName = poultryName;
        this.poultryQuantity = poultryQuantity;
    }


    public int getPoultryId() {
        return poultryId;
    }

    public void setPoultryId(int poultryId) {
        this.poultryId = poultryId;
    }

    public String getPoultryName() {
        return poultryName;
    }

    public void setPoultryName(String poultryName) {
        this.poultryName = poultryName;
    }

    public int getPoultryQuantity() {
        return poultryQuantity;
    }

    public void setPoultryQuantity(int poultryQuantity) {
        this.poultryQuantity = poultryQuantity;
    }
}
