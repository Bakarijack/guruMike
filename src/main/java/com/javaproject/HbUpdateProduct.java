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

public class HbUpdateProduct {
    @FXML
    private TextField poultryCode;

    @FXML
    private Label poultryCodeMessage;

    @FXML
    private TextField productCode;

    @FXML
    private Label productCodeMessage;

    @FXML
    private TextField productName;

    @FXML
    private Label productNameMessage;

    @FXML
    private TextField productQuantity;

    @FXML
    private Label quantityMessage;

    @FXML
    private Button closeButton;

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

    public boolean validateProductCode(){
        String verify = "SELECT COUNT(1) FROM products WHERE product_code = '"+productCode.getText()+"'";

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

    public boolean validateProductName(){
        String verify = "SELECT COUNT(1) FROM products WHERE product_name = '"+productName.getText()+"'";

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

    public boolean quantityCheck(){
        String s = productQuantity.getText();

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

    public void nameupdateButtonOnAction(ActionEvent e){
        if (!productName.getText().isBlank()) {
            if(validate() == true){
                if(validateProductName() == false) {
                    String updateQuery = "UPDATE products SET product_name='" + productName.getText() + "' WHERE poultry_code = '" + poultryCode.getText() + "'";

                    try {
                        Statement statement = connectDB.createStatement();
                        statement.executeUpdate(updateQuery);
                        productNameMessage.setText("Successfully Updated!");

                    } catch (Exception event) {
                        event.printStackTrace();
                    }
                }else {
                    productNameMessage.setText("Name already exist");
                }
            }else {
                poultryCodeMessage.setText("Invalid Code!");
            }
        }else{
            productNameMessage.setText("Fill the text field first!");
        }
    }

    public void productCodeUpdate(ActionEvent e){
        if(!productCode.getText().isBlank()){
            if(validate() == true){
                if(validateProductCode()== false){
                    String updateQuery  = "UPDATE products SET product_code='"+productCode.getText()+"' WHERE poultry_code = '"+poultryCode.getText()+"'";

                    try{
                        Statement statement = connectDB.createStatement();
                        statement.executeUpdate(updateQuery);
                        productNameMessage.setText("Successfully Updated!");

                    }catch (Exception event){
                        event.printStackTrace();
                    }

                }else {
                    productCodeMessage.setText("Code already exist");
                }

            }else{
                poultryCodeMessage.setText("Invalid Code");
            }

        }else{
            productCodeMessage.setText("Fill the text field first");
        }
    }

    public void quantityUpdate(ActionEvent e){
        if(!productQuantity.getText().isBlank()){
            if(quantityCheck() == true){
                if(validate() == true){
                    String updateQuery  = "UPDATE products SET product_quantity='"+productQuantity.getText()+"' WHERE poultry_code = '"+poultryCode.getText()+"'";

                    try{
                        Statement statement = connectDB.createStatement();
                        statement.executeUpdate(updateQuery);
                        quantityMessage.setText("Successfully Updated!");

                    }catch (Exception event){
                        event.printStackTrace();
                    }

                }else{
                    quantityMessage.setText("Invalid Code");
                }

            }else{
                quantityMessage.setText("Integer value required");
            }
        }else{
            quantityMessage.setText("Fill the text field first");
        }
    }
}
