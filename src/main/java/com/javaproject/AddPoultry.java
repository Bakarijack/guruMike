package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddPoultry {
    @FXML
    private Label addMessage;

    @FXML
    private Label codeMessage;

    @FXML
    private Label nameMessage;

    @FXML
    private TextField poultryName;

    @FXML
    private TextField poultryQuantity;

    @FXML
    private TextField poultryCode;

    @FXML
    private Label quantityMessage;

    //create database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDb = connectNow.getConnection();

    public boolean validatePoultryName(){
        String verify = "SELECT COUNT(1) FROM `poultry` WHERE poultry_type = '"+poultryName.getText()+"'";

        try{
            Statement statement = connectDb.createStatement();
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

    public void addButtonOnAction(ActionEvent e){
        if(!poultryCode.getText().isBlank() && !poultryName.getText().isBlank() && !poultryQuantity.getText().isBlank()){
            if (quantityCheck() == true){
                if(validateCode() == false) {
                    if (validatePoultryName() == false) {
                        addPoultry();
                    }else {
                        nameMessage.setText("Poultry name already exist");
                        codeMessage.setText("");
                        quantityMessage.setText("");
                        addMessage.setText("");
                    }
                }else{
                    codeMessage.setText("Code Already Exist!");
                    quantityMessage.setText("");
                    addMessage.setText("");
                    nameMessage.setText("");
                }
            }else {
                quantityMessage.setText("Enter an Integer value!");
                codeMessage.setText("");
                addMessage.setText("");
                nameMessage.setText("");

            }

        }else {
            addMessage.setText("Fill the text fields first");
            codeMessage.setText("");
            quantityMessage.setText("");
            nameMessage.setText("");

        }
    }

    //check the data type of quantity
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

    public boolean validateCode(){
        String verify = "SELECT COUNT(1) FROM `poultry` WHERE poultry_code = '"+poultryCode.getText()+"'";

        try{
            Statement statement = connectDb.createStatement();
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

    public void addPoultry(){
        PoultryDetails poultryDetails = new PoultryDetails();

        poultryDetails.setPoultryId(Integer.parseInt(poultryCode.getText()));
        poultryDetails.setPoultryName(poultryName.getText());
        poultryDetails.setPoultryQuantity(Integer.parseInt(poultryQuantity.getText()));

        String insertData ="INSERT INTO `poultry`(`poultry_code`, `poultry_type`, `poultry_quantity`) VALUES ('"+poultryDetails.getPoultryId()+
                "','"+poultryDetails.getPoultryName()+"','"+poultryDetails.getPoultryQuantity()+"')";

        try {
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertData);
            addMessage.setText("Added successfully");
            codeMessage.setText("");
            quantityMessage.setText("");
            nameMessage.setText("");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}
