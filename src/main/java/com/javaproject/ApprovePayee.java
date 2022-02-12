package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApprovePayee {
    @FXML
    private TextField month;

    @FXML
    private Label monthMessage;

    @FXML
    private TextField year;

    @FXML
    private Label yearMessage;

    @FXML
    private Label buttonMessage;

    //creating database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    public boolean monthCheck(){
        String s = month.getText();

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

    public boolean yearCheck(){
        String s = year.getText();

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


    public String currentMonthMethod(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }

    public String currentYearMethod(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }


    public boolean checkIfPayeAlreadyGenerated(){
        String check = "SELECT COUNT(1) FROM `netPay` WHERE month = '"+month.getText()+"' AND year ='"+year.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(check);

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


    public String currentDateMethod() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }

    public boolean validateIfAreadyApproved(){
        String validate = "SELECT COUNT(1) FROM `netPay` WHERE month='"+month.getText()+"' AND year='"+year.getText()+"' AND approved_status = 1";

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

    public void updateApprovalStatus(){
        String updateStatus = "UPDATE `netPay` SET `payment_date`='"+currentDateMethod()+"',`approved_status`='"+1+"' WHERE month = '"+month.getText()+"' AND year = '"+year.getText()+"'";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateStatus);

        } catch (Exception event) {
            event.printStackTrace();
        }
    }

    public void updateSalaryPaidStatus(){
        String updateStatus = "UPDATE `salaries` SET `paid_status`='"+1+"' WHERE date ='"+month.getText()+"' AND year = '"+year.getText()+"'";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateStatus);

        } catch (Exception event) {
            event.printStackTrace();
        }
    }

    public void approveButtonOnAction(ActionEvent e){
        if(!month.getText().isBlank()){
            if(!year.getText().isBlank()){
                if(monthCheck() == true){
                    if(yearCheck() == true){
                        if(Integer.parseInt(month.getText()) <=12){
                            if(Integer.parseInt(year.getText()) <= Integer.parseInt(currentYearMethod())){
                                if(checkIfPayeAlreadyGenerated() == true){
                                    if(validateIfAreadyApproved() == false){
                                        updateApprovalStatus();
                                        updateSalaryPaidStatus();
                                        yearMessage.setText("");
                                        monthMessage.setText("");
                                        buttonMessage.setText("Net Payee Approved Successfully");
                                    }else{
                                        yearMessage.setText("");
                                        monthMessage.setText("Net Payee already approved");
                                        buttonMessage.setText("");
                                    }

                                }else{
                                    yearMessage.setText("");
                                    monthMessage.setText("Payee cannot be found");
                                    buttonMessage.setText("");
                                }

                            }else{
                                yearMessage.setText("Invalid year");
                                monthMessage.setText("");
                                buttonMessage.setText("");
                            }

                        }else{
                            yearMessage.setText("");
                            monthMessage.setText("Month value exceed 12");
                            buttonMessage.setText("");
                        }

                    }else{
                        yearMessage.setText("Integer value is required");
                        monthMessage.setText("");
                        buttonMessage.setText("");
                    }

                }else{
                    yearMessage.setText("");
                    monthMessage.setText("Integer value is required");
                    buttonMessage.setText("");
                }

            }else{
                yearMessage.setText("Fill this text field");
                monthMessage.setText("");
                buttonMessage.setText("");
            }

        }else{
            monthMessage.setText("Fill this text field");
            yearMessage.setText("");
            buttonMessage.setText("");
        }
    }
}
