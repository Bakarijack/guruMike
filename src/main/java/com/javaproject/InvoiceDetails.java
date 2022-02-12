package com.javaproject;

public class InvoiceDetails {
    private int invoiceId;
    private String orderCode;
    private double totalInvoice;
    private String paymentTerms;
    private String invoiceGeneratedTime;
    private String invoiceGeneratedDate;
    private String sentStatus;

    public InvoiceDetails(){}

    public InvoiceDetails(int invoiceId,String orderCode,double totalInvoice,String paymentTerms,String invoiceGeneratedTime,String invoiceGeneratedDate){
        this.invoiceId = invoiceId;
        this.orderCode = orderCode;
        this.totalInvoice = totalInvoice;
        this.paymentTerms = paymentTerms;
        this.invoiceGeneratedTime = invoiceGeneratedTime;
        this.invoiceGeneratedDate = invoiceGeneratedDate;
    }

    public InvoiceDetails(int invoiceId,String orderCode,String invoiceGeneratedDate,String paymentTerms,double totalInvoice,String sentStatus){
        this.invoiceId =invoiceId;
        this.orderCode = orderCode;
        this.invoiceGeneratedDate = invoiceGeneratedDate;
        this.paymentTerms = paymentTerms;
        this.totalInvoice = totalInvoice;
        this.sentStatus = sentStatus;
    }

    public String getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(String sentStatus) {
        this.sentStatus = sentStatus;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public double getTotalInvoice() {
        return totalInvoice;
    }

    public void setTotalInvoice(double totalInvoice) {
        this.totalInvoice = totalInvoice;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getInvoiceGeneratedTime() {
        return invoiceGeneratedTime;
    }

    public void setInvoiceGeneratedTime(String invoiceGeneratedTime) {
        this.invoiceGeneratedTime = invoiceGeneratedTime;
    }

    public String getInvoiceGeneratedDate() {
        return invoiceGeneratedDate;
    }

    public void setInvoiceGeneratedDate(String invoiceGeneratedDate) {
        this.invoiceGeneratedDate = invoiceGeneratedDate;
    }
}
