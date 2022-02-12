package com.javaproject;

public class StaffProfile {
    private String username;
    private String jobRole;
    private String employeeNumber;
    private String phoneNumber;
    private String emailAddress;
    static String department_code;
    private String firstName;
    private String secondName;
    private String departmentName;
    private String location;
    private String fullName;

    public StaffProfile(){}

    public StaffProfile(String department_code){
        this.department_code = department_code;
    }



    public StaffProfile(String firstName, String secondName, String departmentName,String jobRole){
        this.firstName = firstName;
        this.secondName = secondName;
        this.departmentName = departmentName;
        this.jobRole = jobRole;
    }

    public StaffProfile(String username,String jobRole,String employeeNumber,String phoneNumber,String emailAddress,String department_code){
        this.username = username;
        this.jobRole = jobRole;
        this.employeeNumber = employeeNumber;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.department_code = department_code;
    }

    public StaffProfile(String employeeNumber,String firstName,String secondName,String phoneNumber,String emailAddress){
        this.employeeNumber = employeeNumber;
        this.firstName  = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public StaffProfile(String employeeNumber,String firstName,String secondName,String phoneNumber,String emailAddress,String location, String departmentName,String jobRole){
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.location = location;
        this.departmentName =  departmentName;
        this.jobRole = jobRole;
    }

    public String getFullName() {
        fullName = getFirstName()+" "+getSecondName();
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getUser(){
        String username = getFirstName()+" "+getSecondName();
        return username;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public static String getDepartment_code() {
        return department_code;
    }

    public static void setDepartment_code(String department_code) {
        StaffProfile.department_code = department_code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
