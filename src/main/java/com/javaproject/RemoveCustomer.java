package com.javaproject;

import com.mysql.cj.protocol.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RemoveCustomer {
    @FXML
    private Button deleteButton;

    @FXML
    private Label codeMessage;

    @FXML
    private TextField codeText;

    //creating database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();


    public boolean validate(){
        String verify = "SELECT COUNT(1) FROM `customers` WHERE cust_id = '"+codeText.getText()+"'";

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

    public void deleteButtonOnAction(ActionEvent e){
        if(!codeText.getText().isBlank()){
            if(validate() == true){
                String deleteQuery = "DELETE FROM `customers` WHERE cust_id='"+codeText.getText()+"'";

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
