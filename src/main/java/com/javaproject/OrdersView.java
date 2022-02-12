package com.javaproject;

public class OrdersView {
    private String orderId;
    private String customerNames;
    private String productId;
    private String productName;
    private int numberOfTrays;
    private int numberOfEggs;
    private String orderDate;
    private String paymentStatus;

    public OrdersView(){}

    public OrdersView(String orderId,String customerFname,String customerSname,String productId,String productName, int numberOfTrays,int numberOfEggs,String orderDate,String paymentStatus){
        this.orderId = orderId;
        this.customerNames = customerFname+"  "+customerSname;
        this.productId = productId;
        this.productName = productName;
        this.numberOfTrays = numberOfTrays;
        this.numberOfEggs = numberOfEggs;
        this.orderDate = orderDate;
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setCustomerNames(String customerNames) {
        this.customerNames = customerNames;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerNames() {
        return customerNames;
    }

    public void setCustomerId(String customerNames) {
        this.customerNames = customerNames;
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

    public int getNumberOfTrays() {
        return numberOfTrays;
    }

    public void setNumberOfTrays(int numberOfTrays) {
        this.numberOfTrays = numberOfTrays;
    }

    public int getNumberOfEggs() {
        return numberOfEggs;
    }

    public void setNumberOfEggs(int numberOfEggs) {
        this.numberOfEggs = numberOfEggs;
    }


}
