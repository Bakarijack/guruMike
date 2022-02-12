package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InvoiceGenerate {
    @FXML
    private CheckBox four;

    @FXML
    private CheckBox one;

    @FXML
    private TextField orderCode;

    @FXML
    private Label orderCodeMessage;

    @FXML
    private CheckBox three;

    @FXML
    private CheckBox two;

    //creating database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    private int number;
    public void checkBoxOnAction(ActionEvent e){
        if(one.isSelected()){
            this.number = 1;
            two.setDisable(true);
            three.setDisable(true);
            four.setDisable(true);
        }else if (two.isSelected()){
            this.number = 2;
            one.setDisable(true);
            three.setDisable(true);
            four.setDisable(true);
        }else if(three.isSelected()){
            this.number = 3;
            one.setDisable(true);
            two.setDisable(true);
            four.setDisable(true);
        }else if(four.isSelected() ){
            this.number = 4;
            one.setDisable(true);
            two.setDisable(true);
            three.setDisable(true);
        }else{
            one.setDisable(false);
            two.setDisable(false);
            three.setDisable(false);
            four.setDisable(false);
            //orderCodeMessage.setText("Select one term of payment");
        }
    }

    public boolean validateDebitBox(){
        String verify = "SELECT COUNT(1) FROM `orderDetails` WHERE order_code = '"+orderCode.getText()+"' AND payment_status_code = 2";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verify);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    return true;
                }else{
                    return false;
                    //codeMessage.setText("Invalid Code!");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean validateOrderCode(){
        String verify = "SELECT COUNT(1) FROM orders WHERE order_code = '"+orderCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verify);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    return true;
                }else{
                    return false;
                    //codeMessage.setText("Invalid Code!");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void createInvoice(){
        String insertQuery = "INSERT INTO `invoices`(`order_code`, `invoice_date`, `invoice_time`,`payment_terms_code`) VALUES ('"+
                orderCode.getText()+"','"+currentDateMethod()+"','"+currentTimeMethod()+"','"+number+"')";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertQuery);

        } catch (Exception event) {
            event.printStackTrace();
        }
    }

    public String currentDateMethod(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }

    public String currentTimeMethod(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        return  time;
    }

    public void updateGeneratedStatus(){
        String verify = "UPDATE orderDetails SET invoice_generated_status = 1 WHERE order_code = '"+orderCode.getText()+"'";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(verify);

        } catch (Exception event) {
            event.printStackTrace();
        }
    }

    public void selectingAndUpdateTotalCost(){
        String selectC = "SELECT SUM(orderDetails.cost) AS invoice_total FROM orderDetails WHERE orderDetails.order_code = '"+orderCode.getText()+"'";

        double invoiceTotal;

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(selectC);

            while(queryResult.next()){
               invoiceTotal = queryResult.getDouble("invoice_total");

               String updateInvoice = "UPDATE `invoices` SET `invoice_total`='"+invoiceTotal+"' WHERE order_code = '"+orderCode.getText()+"'";
                try {
                    Statement statement1 = connectDB.createStatement();
                    statement1.executeUpdate(updateInvoice);

                } catch (Exception event) {
                    event.printStackTrace();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean validateIfInvoiceAlreadyGenerated(){
        String verify = "SELECT COUNT(1) FROM orderDetails WHERE order_code = '"+orderCode.getText()+"' AND invoice_generated_status = 1";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verify);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    return true;
                }else{
                    return false;
                    //codeMessage.setText("Invalid Code!");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void updatePaymentStatus(){
        String updatePaymentStatus = "UPDATE `orderDetails` SET `payment_status_code`= 1 WHERE order_code = '"+orderCode.getText()+"'";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updatePaymentStatus);

        } catch (Exception event) {
            event.printStackTrace();
        }
    }

    public void generateButtonOnAction(ActionEvent event){
        if(!orderCode.getText().isBlank()){
            if(validateOrderCode() == true){
                if(validateIfInvoiceAlreadyGenerated() == false){
                    if(validateDebitBox() == true) {
                        //if the customer he/she has not settled order bill
                        if(validateDebitBox() == true && number !=4){
                            updatePaymentStatus();
                        }
                        updateGeneratedStatus();
                        createInvoice();
                        selectingAndUpdateTotalCost();
                        orderCodeMessage.setText("Invoice Successfully generated");
                    }else if(validateDebitBox() == false && number !=4){
                        //if customer has already settled order bill
                        updateGeneratedStatus();
                        createInvoice();
                        selectingAndUpdateTotalCost();
                        orderCodeMessage.setText("Invoice Successfully generated");
                    }else{
                        orderCodeMessage.setText("Order has already been settled");
                    }
                }else {
                    orderCodeMessage.setText("Invoice already exist");
                }
            }else {
                orderCodeMessage.setText("Order code does not exist");
            }

        }else{
            orderCodeMessage.setText("Enter the order code first");
        }
    }

}
