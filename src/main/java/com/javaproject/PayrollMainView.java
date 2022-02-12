package com.javaproject;

public class PayrollMainView {
    private String employeeCount;
    private String fname;
    private String sname;
    private String totalAbsentDays;
    private String employeeId;
    private String totalFullDays;
    private String totalOverTimeDays;
    private String totalPaidDayOff;
    private String totalHours;
    private String totalHalfDays;
    private String fullName;




    public PayrollMainView(){}

    public PayrollMainView(String employeeCount){
        this.employeeCount = employeeCount;
    }

    public PayrollMainView(String firstName, String secondName){
        this.fname = firstName;
        this.sname = secondName;
    }

    public PayrollMainView(String employeeId, String firstName, String secondName,String totalAbsentDays,String totalHalfDays,String totalPaidDayOff,String totalOverTimeDays,String totalFullDays, String totalHours){
        this.employeeId = employeeId;
        this.fullName = firstName +" "+secondName;
        this.totalAbsentDays = totalAbsentDays;
        this.totalHalfDays = totalHalfDays;
        this.totalPaidDayOff = totalPaidDayOff;
        this.totalOverTimeDays = totalOverTimeDays;
        this.totalFullDays = totalFullDays;
        this.totalHours = totalHours;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTotalHalfDays() {
        return totalHalfDays;
    }

    public void setTotalHalfDays(String totalHalfDays) {
        this.totalHalfDays = totalHalfDays;
    }

    public String getTotalAbsentDays() {
        return totalAbsentDays;
    }

    public void setTotalAbsentDays(String totalAbsentDays) {
        this.totalAbsentDays = totalAbsentDays;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getTotalFullDays() {
        return totalFullDays;
    }

    public void setTotalFullDays(String totalFullDays) {
        this.totalFullDays = totalFullDays;
    }

    public String getTotalOverTimeDays() {
        return totalOverTimeDays;
    }

    public void setTotalOverTimeDays(String totalOverTimeDays) {
        this.totalOverTimeDays = totalOverTimeDays;
    }

    public String getTotalPaidDayOff() {
        return totalPaidDayOff;
    }

    public void setTotalPaidDayOff(String totalPaidDayOff) {
        this.totalPaidDayOff = totalPaidDayOff;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getUsername() {
        String user = getFname()+"  "+ getSname()+"!";
        return user;
    }

    public String getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(String employeeCount) {
        this.employeeCount = employeeCount;
    }
}
