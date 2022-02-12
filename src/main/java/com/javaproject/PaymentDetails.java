package com.javaproject;

public class PaymentDetails {
    private String paymentRate;

    public PaymentDetails(){}

    public PaymentDetails(String paymentRate){
        this.paymentRate = paymentRate;
    }

    public String getPaymentRate() {
        return paymentRate;
    }

    public void setPaymentRate(String paymentRate) {
        this.paymentRate = paymentRate;
    }
}
