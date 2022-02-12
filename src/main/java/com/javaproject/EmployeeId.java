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

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeId {
    @FXML
    private TextField employeeId;

    @FXML
    private Label employeeIdMessage;

    @FXML
    private Button enterButton;

    //creating database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();


    static String employeeCode;
    //validate employee current month record
    public boolean validateEmployeeMonthRecord(){
        String validate = "SELECT COUNT(1) FROM `workerMonthlyRecord` WHERE emp_id = '"+employeeId.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(validate);

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

    //validate employee id
    public boolean validateEmployeeId(){
        String validate = "SELECT COUNT(1) FROM `staff` WHERE emp_id = '"+employeeId.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(validate);

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

    public void viewButtonOnAction(ActionEvent e) throws IOException {
        if(!employeeId.getText().isBlank()){
            if(validateEmployeeId() == true){
                if(validateEmployeeMonthRecord() == true){
                    employeeCode = employeeId.getText();

                    Stage stage = (Stage) enterButton.getScene().getWindow();
                    stage.close();

                    //opens the new window
                    FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("individualWorkerRecord.fxml"));
                    Parent root1 = (Parent) customerViewLoader.load();
                    Stage stage2 = new Stage();

                    stage2.setScene(new Scene(root1));
                    stage2.show();

                }else{
                    employeeIdMessage.setText("No Monthly record");
                }
            }else {
                employeeIdMessage.setText("Invalid Employee id");
            }

        }else{
            employeeIdMessage.setText("Enter EmployeeId first");
        }
    }
}
