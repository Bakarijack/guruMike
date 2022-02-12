package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HbRemoveProduct {
    @FXML
    private TextField productCode;

    @FXML
    private Label productCodeMessage;

    //create database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDb = connectNow.getConnection();

    public void deleteButtonOnAction(ActionEvent e){
        if(!productCode.getText().isBlank()){
            if(validateProductCode() == true){
                String deleteQuery = "DELETE FROM products WHERE product_code='"+productCode.getText()+"'";

                try{
                    Statement statement = connectDb.createStatement();
                    statement.executeUpdate(deleteQuery);
                    productCodeMessage.setText("Successfully Deleted!");

                }catch (Exception event){
                    event.printStackTrace();
                }


            }else {
                productCodeMessage.setText("Invalid Code");
            }

        }else{
            productCodeMessage.setText("Fill the text field first");
        }
    }

    public boolean validateProductCode(){
        String verify = "SELECT COUNT(1) FROM products WHERE product_code = '"+productCode.getText()+"'";

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

}
