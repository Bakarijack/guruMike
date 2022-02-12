package com.javaproject;

public class NetPaye {
    private String paymentDate;
    private String numberOfEmployees;
    private String totalPayeNet;
    private String months;
    private String year;
    private String approvalStatus;
    private String date;
    static int status;


    public NetPaye(){}

    public NetPaye(String paymentDate, String totalPayeNet,String numberOfEmployees){
        this.paymentDate =paymentDate;
        this.totalPayeNet = totalPayeNet;
        this.numberOfEmployees = numberOfEmployees;
    }

    public NetPaye(String months,String year,String totalPayeNet,String numberOfEmployees,String approvalStatus ){
        this.months = months;
        this.year = year;
        this.totalPayeNet = totalPayeNet;
        this.numberOfEmployees = numberOfEmployees;
        this.approvalStatus = approvalStatus;
    }

    public NetPaye(String paymentDate,String numberOfEmployees,String totalPayeNet,String months,String year,String approvalStatus){
        this.paymentDate = paymentDate;
        this.numberOfEmployees = numberOfEmployees;
        this.totalPayeNet = totalPayeNet;
        this.months = months;
        this.year = year;
        this.approvalStatus = approvalStatus;
    }

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        NetPaye.status = status;
    }

    public String getDate() {
        if(getMonths().equals("1")){
            date = "January"+" "+getYear();
            return date;
        }else if(getMonths().equals("2")){
            date = "February"+" "+getYear();
            return date;
        }else if(getMonths().equals("3")){
            date ="March"+" "+getYear();
            return date;
        }else if(getMonths().equals("4")){
            date = "April"+" "+getYear();
            return date;
        }else if(getMonths().equals("5")){
            date = "May"+" "+getYear();
            return date;
        }else if(getMonths().equals("6")){
            date = "June"+" "+getYear();
            return date;
        }else if(getMonths().equals("7")){
            date = "July"+" "+getYear();
            return date;
        }else if(getMonths().equals("8")){
            date = "August"+" "+getYear();
            return date;
        }else if(getMonths().equals("9")){
            date = "September"+" "+getYear();
            return date;
        }else if(getMonths().equals("10")){
            date = "October"+" "+getYear();
            return date;
        }else if(getMonths().equals("11")){
            date = "November"+" "+getYear();
            return date;
        }else {
            date = "December"+" "+getYear();
            return date;
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCombineDate(){
        String date;
        if(getMonths().equals("1")){
            date = "January"+" "+getYear();
            return date;
        }else if(getMonths().equals("2")){
            date = "February"+" "+getYear();
            return date;
        }else if(getMonths().equals("3")){
            date ="March"+" "+getYear();
            return date;
        }else if(getMonths().equals("4")){
            date = "April"+" "+getYear();
            return date;
        }else if(getMonths().equals("5")){
            date = "May"+" "+getYear();
            return date;
        }else if(getMonths().equals("6")){
            date = "June"+" "+getYear();
            return date;
        }else if(getMonths().equals("7")){
            date = "July"+" "+getYear();
            return date;
        }else if(getMonths().equals("8")){
            date = "August"+" "+getYear();
            return date;
        }else if(getMonths().equals("9")){
            date = "September"+" "+getYear();
            return date;
        }else if(getMonths().equals("10")){
            date = "October"+" "+getYear();
            return date;
        }else if(getMonths().equals("11")){
            date = "November"+" "+getYear();
            return date;
        }else {
            date = "December"+" "+getYear();
            return date;
        }
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getApprovalStatus() {
        String status = approvalStatus.equals("1") ? "Approved " : "Not Approved";
        return status;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(String numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getTotalPayeNet() {
        return totalPayeNet;
    }

    public void setTotalPayeNet(String totalPayeNet) {
        this.totalPayeNet = totalPayeNet;
    }
}
