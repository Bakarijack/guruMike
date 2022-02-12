package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RemovePoultry {
    @FXML
    private Label codeMessage;

    @FXML
    private TextField poultryCode;

    //creating database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();


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

    public void removeButtonOnAction(ActionEvent e){
        if(!poultryCode.getText().isBlank()){
            if(validate() == true){
                String deleteQuery = "DELETE FROM `poultry` WHERE poultry_code='"+poultryCode.getText()+"'";

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(deleteQuery);
                    codeMessage.setText("Successfully Deleted!");

                }catch (Exception event){
                    event.printStackTrace();
                }

            }else{
                codeMessage.setText("Invalid Code!");
            }

        }else{
            codeMessage.setText("Enter the code first!");
        }
    }
}
