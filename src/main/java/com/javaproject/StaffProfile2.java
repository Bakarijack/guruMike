package com.javaproject;

public class StaffProfile2 extends StaffProfile{
    private String dateRecruited;
    private String gender;

    public StaffProfile2(){}

    public StaffProfile2(String employeeId,String firstName, String secondName,String gender,String role,String location,String dateRecruited){
        setEmployeeNumber(employeeId);
        setFirstName(firstName);
        setSecondName(secondName);
        this.gender = gender;
        setJobRole(role);
        setLocation(location);
        this.dateRecruited = dateRecruited;
    }

    public String getDateRecruited() {
        return dateRecruited;
    }

    public void setDateRecruited(String dateRecruited) {
        this.dateRecruited = dateRecruited;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
