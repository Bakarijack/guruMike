package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PoultryUpdate {
    @FXML
    private Button closeButton;

    @FXML
    private Label codeMessage;

    @FXML
    private Label nameMessage;

    @FXML
    private TextField poultryQuantity;

    @FXML
    private TextField poultryCode;

    @FXML
    private TextField poultryName;

    @FXML
    private Label quantityMessage;

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();


    public void closeButtonOnAction(ActionEvent e){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public boolean validate(){
        String verify = "SELECT COUNT(1) FROM `poultry` WHERE poultry_code = '"+poultryCode.getText()+"'";

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

    public boolean validateName(){
        String verify = "SELECT COUNT(1) FROM `poultry` WHERE poultry_type = '"+poultryName.getText()+"'";

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

    public void nameupdateButtonOnAction(ActionEvent e){
        if (!poultryName.getText().isBlank()) {
            if(validate() == true){
                if (validateName() == false) {
                    String updateQuery = "UPDATE poultry SET poultry_type ='" + poultryName.getText() + "' WHERE poultry_code = '" + poultryCode.getText() + "'";

                    try {
                        Statement statement = connectDB.createStatement();
                        statement.executeUpdate(updateQuery);
                        nameMessage.setText("Successfully Updated!");

                    } catch (Exception event) {
                        event.printStackTrace();
                    }
                }else{
                    nameMessage.setText("Name already exist");
                }
            }else {
                codeMessage.setText("Invalid Code!");
            }
        }else{
            nameMessage.setText("Fill the text field first!");
        }
    }

    public boolean quantityCheck(){
        String s = poultryQuantity.getText();

        if(s == null){
            return false;
        }

        try{
            int i = Integer.parseInt(s);
        }catch (NumberFormatException e){
            return false;
        }

        return true;
    }

    public void quantityUpdateButtonOnAction(ActionEvent e){
        if (!poultryQuantity.getText().isBlank()) {
            if(quantityCheck() == true) {
                if (validate() == true) {
                    String updateQuery = "UPDATE `poultry` SET poultry_quantity ='" + poultryQuantity.getText() + "' WHERE poultry_code = '" + poultryCode.getText() + "'";

                    try {
                        Statement statement = connectDB.createStatement();
                        statement.executeUpdate(updateQuery);
                        quantityMessage.setText("Successfully Updated!");

                    } catch (Exception event) {
                        event.printStackTrace();
                    }
                } else {
                    codeMessage.setText("Invalid Code!");
                }
            }else {
                quantityMessage.setText("Integer value required");
            }
        }else{
            quantityMessage.setText("Fill the text field first!");
        }
    }

}
