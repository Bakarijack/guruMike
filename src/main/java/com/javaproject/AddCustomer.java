package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.Statement;

public class AddCustomer {

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField idNumber;

    @FXML
    private TextField locationName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField secondName;

    @FXML
    private TextField genderName;

    @FXML
    private Label messageLabel;


    //create database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDb = connectNow.getConnection();

    //add button on action to register the customer
    public void addButtonOnAction(ActionEvent e){
        if(!email.getText().isBlank() && !firstName.getText().isBlank() && !idNumber.getText().isBlank() &&
                !locationName.getText().isBlank() && ! phoneNumber.getText().isBlank() && !secondName.getText().isBlank() && !genderName.getText().isBlank()){
            register();
        } else{
            messageLabel.setText("Fill the remaining fields");
        }
    }

    public void register(){

        CustomerDetails customer = new CustomerDetails();

        customer.setFirstName(firstName.getText());
        customer.setSecondName(secondName.getText());
        customer.setEmailAddress(email.getText());
        customer.setiDNumber(Integer.parseInt(idNumber.getText()));
        customer.setGenderName(genderName.getText());
        customer.setLocationName(locationName.getText());
        customer.setPhoneNumber(phoneNumber.getText());

        String insertDetails = "INSERT INTO `customers`(`phone`, `locationN`, `gender`, `email`, `id`, `firstName`, `secondName`) VALUES ('"+
                customer.getPhoneNumber()+"','"+customer.getLocationName()+"','"+customer.getGenderName()+
                "','"+customer.getEmailAddress()+"','"+customer.getiDNumber()+"','"+customer.getFirstName()+"','"+customer.getSecondName()+"')";

        try {
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertDetails);
            messageLabel.setText("user has been registered success");


        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

}
