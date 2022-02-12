package com.test.testfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    public void loginButtonOnActon(ActionEvent e){

        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            //loginMessageLabel.setText("You try login!");
            validateLogin();
        }else{
            loginMessageLabel.setText("PLease enter username and password. ");
        }
    }

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verfyLogin = "SELECT count(1) FROM UserAccounts WHERE Username = '"+usernameTextField.getText()+
                "' AND password='"+passwordPasswordField.getText()+"'";


        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verfyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Welcome!");


                }else{
                    loginMessageLabel.setText("Invalid login.Please try again.");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}