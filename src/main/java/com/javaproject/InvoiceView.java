package com.javaproject;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InvoiceView<String> extends PdfPageEventHelper {
    @FXML
    private CheckBox dontSendBox;

    @FXML
    private TextField invoiceCode;

    @FXML
    private Label invoiceCodeMessage;

    @FXML
    private Label printMessage;

    @FXML
    private Label receiverEmailMessage;

    @FXML
    private TextField recieverEmail;

    @FXML
    private CheckBox sendBox;

    @FXML
    private TextField senderEmail;

    @FXML
    private Label senderEmailMessage;

    @FXML
    private Label selectBoxMessage;

    //creating database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    public void selectBoxOnAction(ActionEvent e){
        if(sendBox.isSelected()){
            dontSendBox.setDisable(true);
        }else if(dontSendBox.isSelected()){
            sendBox.setDisable(true);
            senderEmail.setEditable(false);
            recieverEmail.setEditable(false);
        }else{
            sendBox.setDisable(false);
            dontSendBox.setDisable(false);
            senderEmail.setEditable(true);
            recieverEmail.setEditable(true);
        }
    }

    public boolean validateIfInvoiceExist(){
        String verify = (String) ("SELECT COUNT(1) FROM `invoices` WHERE invoice_code = '"+invoiceCode.getText()+"'");

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery((java.lang.String) verify);

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
    public boolean verifyInInvoiceAlreadyPrinted(){
        String verify = (String) ("SELECT COUNT(1) FROM `invoices` WHERE invoice_code = '"+invoiceCode.getText()+"' AND printed = 1");

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery((java.lang.String) verify);

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


    InvoicePrintDetails invoicePrintDetails =new InvoicePrintDetails();

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<InvoicePrintDetails> InvoiceList = FXCollections.observableArrayList();

    public void getOrderCode() {
        try {
            InvoiceList.clear();
            String query = (String) ("SELECT order_code FROM `invoices` WHERE invoice_code = '"+invoiceCode.getText()+"'");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) == 1){
                    //InvoicePrintDetails.setOrderCode(resultSet.getString("order_code"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void getCustomerDetails(){
        getOrderCode();
        try {
            InvoiceList.clear();
            String query = (String) ("SELECT customers.firstName,customers.secondName,customers.phone,customers.locationN,customers.email FROM `orders` JOIN customers ON orders.cust_id = customers.cust_id WHERE orders.order_code ='"+1+"'");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               if(resultSet.getInt(1) == 1){
                   invoicePrintDetails.setFirstName(resultSet.getString("firstName"));
                   invoicePrintDetails.setSecondName(resultSet.getString("secondName"));
                   invoicePrintDetails.setPhoneNumber(resultSet.getString("phone"));
                   invoicePrintDetails.setLocation(resultSet.getString("locationN"));
                   invoicePrintDetails.setEmail(resultSet.getString("email"));
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void updatePrint(){
        String update = (String) ("UPDATE `invoices` SET `printed`= 1 WHERE invoice_code = '"+invoiceCode.getText()+"'");

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate((java.lang.String) update);

        } catch (Exception event) {
            event.printStackTrace();
        }
    }

    public void updateSent(){
        String update = (String) ("UPDATE `invoices` SET `sentStatus`= 1 WHERE invoice_code = '"+invoiceCode.getText()+"'");

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate((java.lang.String) update);

        } catch (Exception event) {
            event.printStackTrace();
        }
    }

    public void printButtonOnAction(ActionEvent e){
        if(!invoiceCode.getText().isBlank()){
            if(sendBox.isSelected()){
                if (!senderEmail.getText().isBlank()){
                    if(!recieverEmail.getText().isBlank()){
                        if(validateIfInvoiceExist() == true) {
                            if (verifyInInvoiceAlreadyPrinted() == false) {
                                invoicePrintDetails.printPDF();
                                invoiceCodeMessage.setText("");
                                selectBoxMessage.setText("");
                                receiverEmailMessage.setText("");
                                senderEmailMessage.setText("");
                                printMessage.setText("Printed Successfully");
                            } else {
                                invoiceCodeMessage.setText("Invoice already printed");
                                selectBoxMessage.setText("");
                                receiverEmailMessage.setText("");
                                senderEmailMessage.setText("");
                                printMessage.setText("");
                            }
                        }else{
                            invoiceCodeMessage.setText("Invoice does not exist");
                            selectBoxMessage.setText("");
                            receiverEmailMessage.setText("");
                            senderEmailMessage.setText("");
                            printMessage.setText("");
                        }

                    }else{
                        invoiceCodeMessage.setText("");
                        selectBoxMessage.setText("");
                        receiverEmailMessage.setText("Enter the email first");
                        senderEmailMessage.setText("");
                        printMessage.setText("");
                    }

                }else{
                    invoiceCodeMessage.setText("Enter the email first");
                    selectBoxMessage.setText("");
                    receiverEmailMessage.setText("");
                    senderEmailMessage.setText("");
                    printMessage.setText("");
                }
            }else if(dontSendBox.isSelected()){
                if(validateIfInvoiceExist() == true) {
                    if (verifyInInvoiceAlreadyPrinted() == false) {

                        invoicePrintDetails.printPDF();
                        invoiceCodeMessage.setText("");
                        selectBoxMessage.setText("");
                        receiverEmailMessage.setText("");
                        senderEmailMessage.setText("");
                        printMessage.setText("Printed Successfully");
                    } else {
                        invoiceCodeMessage.setText("Invoice already printed");
                        selectBoxMessage.setText("");
                        receiverEmailMessage.setText("");
                        senderEmailMessage.setText("");
                        printMessage.setText("");
                    }
                }else{
                    invoiceCodeMessage.setText("Invoice does not exist");
                    selectBoxMessage.setText("");
                    receiverEmailMessage.setText("");
                    senderEmailMessage.setText("");
                    printMessage.setText("");
                }

            }else {
                invoiceCodeMessage.setText("");
                selectBoxMessage.setText("Select one box first");
                receiverEmailMessage.setText("");
                senderEmailMessage.setText("");
                printMessage.setText("");
            }
        }else{
            invoiceCodeMessage.setText("Enter Invoice code first");
            selectBoxMessage.setText("");
            receiverEmailMessage.setText("");
            senderEmailMessage.setText("");
            printMessage.setText("");
        }
    }
}
