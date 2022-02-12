package com.javaproject;

import com.mysql.cj.protocol.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateDetails {
    @FXML
    private TextField code;

    @FXML
    private TextField emailAdress;

    @FXML
    private Label emailMessage;

    @FXML
    private TextField firstname;

    @FXML
    private Label fnameMessage;

    @FXML
    private Label genderMessage;

    @FXML
    private TextField genderName;

    @FXML
    private Label idMessage;

    @FXML
    private TextField idNumber;

    @FXML
    private Label locMessage;

    @FXML
    private TextField locationName;

    @FXML
    private Label phoneMessage;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Label codeMessage;

    @FXML
    private TextField secondname;

    @FXML
    private Label snameMessage;

    @FXML
    private Button closeButton;


    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    public boolean validate(){
        String verify = "SELECT COUNT(1) FROM `customers` WHERE cust_id = '"+code.getText()+"'";

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


    //first name on action
    public void fnameOnAction(ActionEvent e){
        if (!firstname.getText().isBlank()) {
            if(validate() == true){
                String updateQuery  = "UPDATE customers SET firstName ='"+firstname.getText()+"' WHERE cust_id = '"+code.getText()+"'";

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateQuery);
                    fnameMessage.setText("Successfully Updated!");

                }catch (Exception event){
                    event.printStackTrace();
                }
            }else {
                codeMessage.setText("Invalid Code!");
            }
        }else{
            fnameMessage.setText("Fill the text field first!");
        }
    }

    public void secnameOnAction(ActionEvent e){
        if (!secondname.getText().isBlank()) {
            if(validate() == true){
                String updateQuery  = "UPDATE customers SET secondName ='"+secondname.getText()+"' WHERE cust_id = '"+code.getText()+"'";

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateQuery);
                    snameMessage.setText("Successfully Updated!");

                }catch (Exception event){
                    event.printStackTrace();
                }
            }else {
                codeMessage.setText("Invalid Code!");
            }
        }else{
            snameMessage.setText("Fill the text field first!");
        }
    }

    public void locOnAction(ActionEvent e){
        if (!locationName.getText().isBlank()) {
            if(validate() == true){
                String updateQuery  = "UPDATE customers SET locationN ='"+locationName.getText()+"' WHERE cust_id = '"+code.getText()+"'";

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateQuery);
                    locMessage.setText("Successfully Updated!");

                }catch (Exception event){
                    event.printStackTrace();
                }
            }else {
                codeMessage.setText("Invalid Code!");
            }
        }else{
            locMessage.setText("Fill the text field first!");
        }
    }

    public void emailOnAction(ActionEvent e){
        if (!emailAdress.getText().isBlank()) {
            if(validate() == true){
                String updateQuery  = "UPDATE customers SET email ='"+emailAdress.getText()+"' WHERE cust_id = '"+code.getText()+"'";

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateQuery);
                    emailMessage.setText("Successfully Updated!");

                }catch (Exception event){
                    event.printStackTrace();
                }
            }else {
                codeMessage.setText("Invalid Code!");
            }
        }else{
            emailMessage.setText("Fill the text field first!");
        }
    }

    public void idNumberOnAction(ActionEvent e){
        if (!idNumber.getText().isBlank()) {
            if(validate() == true){
                String updateQuery  = "UPDATE customers SET id ='"+idNumber.getText()+"' WHERE cust_id = '"+code.getText()+"'";

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateQuery);
                    idMessage.setText("Successfully Updated!");

                }catch (Exception event){
                    event.printStackTrace();
                }
            }else {
                codeMessage.setText("Invalid Code!");
            }
        }else{
            idMessage.setText("Fill the text field first!");
        }
    }

    public void phoneNumberOnAction(ActionEvent e){
        if (!phoneNumber.getText().isBlank()) {
            if(validate() == true){
                String updateQuery  = "UPDATE customers SET phone ='"+phoneNumber.getText()+"' WHERE cust_id = '"+code.getText()+"'";

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateQuery);
                    phoneMessage.setText("Successfully Updated!");

                }catch (Exception event){
                    event.printStackTrace();
                }
            }else {
                codeMessage.setText("Invalid Code!");
            }
        }else{
            phoneMessage.setText("Fill the text field first!");
        }
    }

    public void genderOnAction(ActionEvent e){
        if (!genderName.getText().isBlank()) {
            if(validate() == true){
                String updateQuery  = "UPDATE customers SET gender ='"+genderName.getText()+"' WHERE cust_id = '"+code.getText()+"'";

                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateQuery);
                    genderMessage.setText("Successfully Updated!");

                }catch (Exception event){
                    event.printStackTrace();
                }
            }else {
                codeMessage.setText("Invalid Code!");
            }
        }else{
            genderMessage.setText("Fill the text field first!");
        }
    }

    //closing the window
    public void closeButtonOnAction(ActionEvent e){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
