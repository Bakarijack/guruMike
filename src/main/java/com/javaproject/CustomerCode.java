package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerCode {
    @FXML
    private Label codeMessage;

    @FXML
    private TextField customerCode;

    @FXML
    private Button enterButton;

    //date field
    private String code;

    public CustomerCode(){   //no-arg constructor
        //this.code = customerCode.getText();
    }

    public void enterButtonOnAction(ActionEvent e){
        if(!customerCode.getText().isBlank()){
            validate();
        }else{
            codeMessage.setText("Enter the code!");
        }
    }

    public void validate(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verify = "SELECT COUNT(1) FROM `customers` WHERE cust_id = '"+customerCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verify);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){

                    //setCode(customerCode.getText());
                   // codeMessage.setText("Welcome!");
                    Stage stage = (Stage) enterButton.getScene().getWindow();
                    stage.close();

                    //opens the new window
                    FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("updateDetails.fxml"));
                    Parent root1 = (Parent) customerViewLoader.load();
                    Stage stage2 = new Stage();

                    stage2.setScene(new Scene(root1));
                    stage2.show();

                }else{
                    codeMessage.setText("Invalid Code. Please Try Again");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public TextField getCustomerCode() {
        return customerCode;
    }
}
