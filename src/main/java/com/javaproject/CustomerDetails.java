package com.javaproject;

public class CustomerDetails {
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private String locationName;
    private String genderName;
    private  String emailAddress;
    private Integer id;
    private int customerCode;

    //creates constructors
    protected CustomerDetails(){

    }
    public CustomerDetails(int customerCode,String phoneNumber,String locationName,String genderName,String emailAddress ,Integer id,String firstName,String secondName){
        this.customerCode = customerCode;
        this.phoneNumber = phoneNumber;
        this.locationName = locationName;
        this.genderName = genderName;
        this.emailAddress =emailAddress;
        this.id = id;
        this.firstName =firstName;
        this.secondName = secondName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCustomerCode(int customerCode) {
        this.customerCode = customerCode;
    }

    public int getCustomerCode() {
        return customerCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getiDNumber() {
        return id;
    }

    public void setiDNumber(Integer id) {
        this.id = id;
    }
}
