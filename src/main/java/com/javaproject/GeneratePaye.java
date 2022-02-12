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

public class GeneratePaye {
    @FXML
    private Label message;

    @FXML
    private TextField month;

    @FXML
    private TextField year;



    //creating database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    public String currentDateMethod(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
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

   /* private int numberOfEmployees;
    public void getEmployee1(){
        String number = "SELECT COUNT(1) FROM `salaries` WHERE date = '"+month.getText()+"' AND year = '"+year.getText()+"'";
    }*/


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

    public void generatePayeForDecember(){
        double netPay;
        String numberOfEmployees =null;
        String totalPaye = "SELECT SUM(salaries.amount) AS total_paye FROM `salaries` WHERE date='"+12+"' AND year='"+(Integer.parseInt(currentYearMethod())-1)+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(totalPaye);

            while(queryResult.next()){
                netPay = queryResult.getDouble("total_paye");

                //select number of employees
                String empNumber = "SELECT COUNT(1) FROM salaries WHERE salaries.date ='"+month.getText()+"' AND salaries.year = '"+year.getText()+"'";
                try{
                    Statement statement1 = connectDB.createStatement();
                    ResultSet queryResult1 = statement1.executeQuery(empNumber);

                    while(queryResult1.next()) {
                            numberOfEmployees = queryResult1.getString("COUNT(1)");

                    }
                        //insert the date in the netPayee table
                        String insertData = "INSERT INTO `netPay`(`number_of_employees`, `total_paye`, `month`, `year`) VALUES ('"+numberOfEmployees+"','"+netPay+"','"+month.getText()+"','"+year.getText()+"')";

                        try{
                            Statement statement2 = connectDB.createStatement();
                            statement2.executeUpdate(insertData);

                        }catch (Exception e){
                            e.printStackTrace();
                            e.getCause();
                        }

                }catch(Exception e){
                    e.printStackTrace();
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void generatePayeForLastMonth(){
        double netPay;
        String numberOfEmployees = null;
        String totalPaye = "SELECT SUM(salaries.amount) AS total_paye FROM `salaries` WHERE date='"+(Integer.parseInt(currentMonthMethod())-1)+"' AND year='"+currentYearMethod()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(totalPaye);

            while(queryResult.next()){
                netPay = queryResult.getDouble("total_paye");

                //select number of employees
                String empNumber = "SELECT COUNT(1) FROM salaries WHERE salaries.date = '"+month.getText()+"' AND salaries.year = '"+year.getText()+"'";
                try{
                    Statement statement1 = connectDB.createStatement();
                    ResultSet queryResult1 = statement1.executeQuery(empNumber);

                    while(queryResult1.next()) {
                            numberOfEmployees =queryResult1.getString("COUNT(1)");

                    }
                        //insert the date in the netpaye table
                        String insertData = "INSERT INTO `netPay`(`number_of_employees`, `total_paye`, `month`, `year`) VALUES ('"+numberOfEmployees+"','"+netPay+"','"+month.getText()+"','"+year.getText()+"')";

                        try{
                            Statement statement2 = connectDB.createStatement();
                            statement2.executeUpdate(insertData);

                        }catch (Exception e){
                            e.printStackTrace();
                            e.getCause();
                        }


                }catch(Exception e){
                    e.printStackTrace();
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void generateButtonOnAction(ActionEvent e){
        if (!month.getText().isBlank() && !year.getText().isBlank()){
            if(monthCheck() ==  true){
                if(yearCheck() == true){
                    if(Integer.parseInt(month.getText()) <= 12){
                        if(Integer.parseInt(currentMonthMethod()) == 1){
                            if(Integer.parseInt(month.getText()) == 12){
                                if(Integer.parseInt(year.getText()) == (Integer.parseInt(currentYearMethod())-1)){
                                    if(checkIfPayeAlreadyGenerated() == false) {
                                        generatePayeForDecember();
                                        message.setText("Paye generated Successfully");
                                    }else{
                                        message.setText("Paye already exist for this period");
                                    }
                                }else{
                                    message.setText("Current year Paye not ready");
                                }

                            }else{
                                message.setText("Current month Paye not ready try previous one");
                            }

                        }else{
                            if(Integer.parseInt(month.getText()) == (Integer.parseInt(currentMonthMethod())-1)){
                                if(Integer.parseInt(year.getText()) == Integer.parseInt(currentYearMethod())){
                                    if(checkIfPayeAlreadyGenerated() == false) {
                                        generatePayeForLastMonth();
                                        message.setText("Paye generated Successfully");
                                    }else{
                                        message.setText("Paye already exist for this period");
                                    }
                                }else{
                                    message.setText("Invalid year. Enter the current year please");
                                }

                            }else{
                                message.setText("Current month Paye not ready try previous one");
                            }
                        }
                    }else{
                        message.setText("month value exceeds 12");
                    }

                }else{
                    message.setText("Integer value is required in year field");
                }

            }else{
                message.setText("Integer value required in Month field");
            }

        }else {
            message.setText("Fill the text field first");
        }
    }
}
