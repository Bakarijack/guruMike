package com.javaproject;

public class NetData extends PayrollMainView{
    private String totalPayeeNet;
    private String payeeData;

    public NetData(){}

    public NetData(String payeeData, String totalPayeeNet,String employeeCount){
        this.payeeData =payeeData;
        this.totalPayeeNet = totalPayeeNet;
        setEmployeeCount(employeeCount);
    }

    public String getTotalPayeeNet() {
        return totalPayeeNet;
    }

    public void setTotalPayeeNet(String totalPayeeNet) {
        this.totalPayeeNet = totalPayeeNet;
    }

    public String getPayeeData() {
        return payeeData;
    }

    public void setPayeeData(String payeeData) {
        this.payeeData = payeeData;
    }
}
