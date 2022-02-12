package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class HoursDetails {
    @FXML
    private BorderPane mainPane;

    @FXML
    private CheckBox absent;

    @FXML
    private TextField employeeId;

    @FXML
    private Label employeeIdMessage;

    @FXML
    private CheckBox fullDay;

    @FXML
    private TextField hours;

    @FXML
    private CheckBox halfDay;

    @FXML
    private Label hoursMessage;

    @FXML
    private TextField minutes;

    @FXML
    private Label minutesMessage;

    @FXML
    private TextField overTimeHours;

    @FXML
    private Label overTimeHoursMessage;

    @FXML
    private CheckBox paidDayOff;

    @FXML
    private Label submitMessage;

    @FXML
    private Label checkBoxMessage;

    @FXML
    private TextField overTimeMinutes;

    @FXML
    private Label overTimeMinutesMessage1;

    private int fullDayStatus;
    private int halfDayStatus;
    private int absentStatus;
    private int paidOffDayStatus;

    PreparedStatement preparedStatement = null;


    //creating database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    public void checkBoxOnAction(ActionEvent e){
        if(fullDay.isSelected()){
            halfDay.setDisable(true);
            absent.setDisable(true);
            paidDayOff.setDisable(true);
            fullDayStatus = 1;
            halfDayStatus = 0;
            absentStatus = 0;
            paidOffDayStatus = 0;
            submitMessage.setText("");
        }else if(halfDay.isSelected()){
            fullDay.setDisable(true);
            absent.setDisable(true);
            paidDayOff.setDisable(true);
            overTimeHours.setEditable(false);
            overTimeHours.setText("0");
            overTimeMinutes.setEditable(false);
            overTimeMinutes.setText("0");
            fullDayStatus = 0;
            halfDayStatus = 1;
            absentStatus = 0;
            paidOffDayStatus = 0;
            submitMessage.setText("");
        }else if(absent.isSelected()){
            fullDay.setDisable(true);
            halfDay.setDisable(true);
            paidDayOff.setDisable(true);
            hours.setEditable(false);
            hours.setText("0");
            minutes.setEditable(false);
            minutes.setText("0");
            overTimeHours.setEditable(false);
            overTimeHours.setText("0");
            overTimeMinutes.setEditable(false);
            overTimeMinutes.setText("0");
            fullDayStatus = 0;
            halfDayStatus = 0;
            absentStatus = 1;
            paidOffDayStatus = 0;
            submitMessage.setText("");
        }else if(paidDayOff.isSelected()){
            fullDay.setDisable(true);
            halfDay.setDisable(true);
            absent.setDisable(true);
            hours.setEditable(false);
            hours.setText("8");
            minutes.setEditable(false);
            minutes.setText("0");
            overTimeHours.setEditable(false);
            overTimeHours.setText("0");
            overTimeMinutes.setEditable(false);
            overTimeMinutes.setText("0");
            fullDayStatus = 0;
            halfDayStatus = 0;
            absentStatus = 0;
            paidOffDayStatus = 1;
            submitMessage.setText("");
        }else{
            fullDay.setDisable(false);
            halfDay.setDisable(false);
            absent.setDisable(false);
            paidDayOff.setDisable(false);
            hours.setEditable(true);
            hours.clear();
            minutes.setEditable(true);
            minutes.clear();
            overTimeHours.setEditable(true);
            overTimeHours.clear();
            overTimeMinutes.setEditable(true);
            overTimeMinutes.clear();
            fullDayStatus = 0;
            halfDayStatus = 0;
            absentStatus = 0;
            paidOffDayStatus = 0;
        }
    }

    public void infoLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("workingHoursInfo");
        mainPane.setRight(view);
    }

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

    public boolean validateEmployeeId(){
        String empValidate = "SELECT COUNT(1) FROM `staff` WHERE emp_id = '"+employeeId.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(empValidate);

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

    public boolean hourCheck(){
        String s = hours.getText();

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

    public boolean minutesCheck(){
        String s = minutes.getText();

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

    public boolean overTimeMinutesCheck(){
        String s = overTimeMinutes.getText();

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

    private double totalHoursPerDay;
    public boolean validateTotalHours(){
        double totalH = Double.parseDouble(hours.getText()) + Double.parseDouble(minutes.getText()) / 60;

        if (totalH <= 8){
            totalHoursPerDay = totalH;
            return true;
        }else {
            return false;
        }
    }

    private  double totalOverTimeHours;
    public boolean validateTotalOverTime(){
        double totalOv = Double.parseDouble(overTimeHours.getText()) + Double.parseDouble(overTimeMinutes.getText()) / 60;

        if (totalOv <= 4){
            totalOverTimeHours = totalOv;
            overTimeStatus = totalOverTimeHours > 0 ? 1:0;
            return  true;
        }else {
            return false;
        }
    }

    private int overTimeStatus;     //conditional clause

    public boolean overTimeCheck(){
        String s = overTimeHours.getText();

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

    public boolean validateEmployeeWithDepartment(){
        //returnEmployeeDepartment();
        String verifyDep = "SELECT dep_code FROM `staff` WHERE emp_id = '"+employeeId.getText()+"'";


        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyDep);

            while(queryResult.next()){
                if (Objects.equals(queryResult.getString("dep_code"), StaffProfile.getDepartment_code())){
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

    public boolean validateHours(){
        int hoursT = Integer.parseInt(hours.getText());

        if (hoursT <= 8){
            return true;
        }else{
            return false;
        }
    }

    public boolean validateMinutes(){
        int minutesT = Integer.parseInt(minutes.getText());

        if (minutesT < 60){
            return  true;
        }else {
            return false;
        }
    }

    public boolean validateOverTimeMinutes(){
        int minutesT = Integer.parseInt(overTimeMinutes.getText());

        if (minutesT < 60){
            return  true;
        }else {
            return false;
        }
    }

    public boolean validateOverTime(){
        int overTimeT = Integer.parseInt(overTimeHours.getText());

        if (overTimeT <= 4){
            return  true;
        }else {
            return false;
        }
    }

   /* public void insertData(){
        String insert = "INSERT INTO `workingHours`(`emp_id`, `day_off`, `half_day`, `paid_off_day`, `over_time`, `total_hours_per_day`, `date`, `month`, `year`, `full_day`, `full_day_hours`, `half_day_hours`, `paid_day_off_hours`, `over_time_hours`) VALUES ('"+
                employeeId.getText()+"','"+absentStatus+"','"+halfDayStatus+"','"+paidOffDayStatus+"','"+overTimeStatus+"','"+(totalHoursPerDay+totalOverTimeHours)+"','"+currentDateMethod()+"','"+currentMonthMethod()+"','"+
                currentYearMethod()+"','"+fullDayStatus+"','"+totalHoursPerDay+"','"+totalHoursPerDay+"','"+totalHoursPerDay+"','"+totalOverTimeHours+"')";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insert);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }*/

    public void insertDataForFullDay(){
        String insertData = "INSERT INTO `workingHours`(`emp_id`, `over_time`, `total_hours_per_day`, `date`, `month`, `year`, `full_day`, `full_day_hours`, `over_time_hours`) VALUES ('"+
                employeeId.getText()+"','"+overTimeStatus+"','"+(totalHoursPerDay + totalOverTimeHours)+"','"+
                currentDateMethod()+"','"+currentMonthMethod()+"','"+currentYearMethod()+"','"+fullDayStatus+"','"+totalHoursPerDay+"','"+totalOverTimeHours+"')";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertData);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void insertDataForHalfDay(){
        String insertData = "INSERT INTO `workingHours`(`emp_id`,`half_day`,`total_hours_per_day`, `date`, `month`, `year`,`half_day_hours`) VALUES ('"+
                employeeId.getText()+"','"+halfDayStatus+"','"+totalHoursPerDay+"','"+currentDateMethod()+"','"+
                currentMonthMethod()+"','"+currentYearMethod()+"','"+totalHoursPerDay+"')";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertData);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void insertDataForAbsent(){
        String insertData = "INSERT INTO `workingHours`(`emp_id`, `day_off`, `date`, `month`, `year`) VALUES ('"+
                employeeId.getText()+"','"+absentStatus+"','"+currentDateMethod()+"','"+currentMonthMethod()+"','"+currentYearMethod()+"')";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertData);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void insertDataForPaidDayOff(){
        String insertData = "INSERT INTO `workingHours`(`emp_id`,`paid_off_day`,`total_hours_per_day`, `date`, `month`, `year`,`paid_day_off_hours`,) VALUES ('"+
                employeeId.getText()+"','"+paidOffDayStatus+"','"+totalHoursPerDay+"','"+currentDateMethod()+"','"+currentMonthMethod()+"','"+currentYearMethod()+"','"+totalHoursPerDay+"')";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertData);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    //validate if the daily record already exist
    public boolean validateRecord(){
        String validateRec = "SELECT COUNT(1) FROM `workingHours` WHERE emp_id = '"+employeeId.getText()+"'  AND date = '"+currentDateMethod()+"' AND month = '"+currentMonthMethod()+"' AND year = '"+currentYearMethod()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(validateRec);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    return true;
                }else{
                    return false;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean validateIfMonthRecordExist(){
        String checkRecordExist = "SELECT COUNT(1) FROM `workerMonthlyRecord` WHERE month = '"+currentMonthMethod()+"'  AND year = '"+currentYearMethod()+"' AND emp_id = '"+employeeId.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(checkRecordExist);

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

    public void insertNewMonthlyRecord(){
        String insertData = "INSERT INTO `workerMonthlyRecord`(`emp_id`, `month`, `year`) VALUES ('"+employeeId.getText()+"','"+currentMonthMethod()+"','"+currentYearMethod()+"')";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertData);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void updateMonthlyRecordForFullDay(){
        String updateData = "UPDATE workerMonthlyRecord,workingHours SET workerMonthlyRecord.full_days = workerMonthlyRecord.full_days +'"+fullDayStatus+"', workerMonthlyRecord.total_hours = workerMonthlyRecord.total_hours + '"+(totalHoursPerDay+totalOverTimeHours)+"', workerMonthlyRecord.over_time_count = workerMonthlyRecord.over_time_count +'"+overTimeStatus+"' WHERE workingHours.date = '"+
                currentDateMethod()+"' AND workingHours.month = '"+currentMonthMethod()+"' AND workingHours.year = '"+currentYearMethod()+"' AND workerMonthlyRecord.emp_id = '"+employeeId.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateData);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateMonthlyRecordForHalfDay(){
        String updateData = "UPDATE workerMonthlyRecord,workingHours SET workerMonthlyRecord.half_days = workerMonthlyRecord.half_days + '"+halfDayStatus+"', workerMonthlyRecord.total_hours = workerMonthlyRecord.total_hours + '"+totalHoursPerDay+"' WHERE workingHours.date = '"+
                currentDateMethod()+"' AND workingHours.month = '"+currentMonthMethod()+"' AND workingHours.year = '"+currentYearMethod()+"' AND  workerMonthlyRecord.emp_id ='"+employeeId.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateData);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateMonthlyRecordForAbsent(){
        String updateData = "UPDATE workerMonthlyRecord,workingHours SET workerMonthlyRecord.absent_days = workerMonthlyRecord.absent_days + '"+absentStatus+"' WHERE workingHours.date = '"+
                currentDateMethod()+"' AND workingHours.month = '"+currentMonthMethod()+"' AND workingHours.year = '"+currentYearMethod()+"' AND workerMonthlyRecord.emp_id = '"+employeeId.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateData);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateMonthlyRecordForPaidDayOff(){
        String updateData ="UPDATE workerMonthlyRecord,workingHours SET workerMonthlyRecord.paid_off_days = workerMonthlyRecord.paid_off_days  + '"+paidOffDayStatus+"', workerMonthlyRecord.total_hours = workerMonthlyRecord.total_hours + '"+totalHoursPerDay+"' WHERE workingHours.date = '"+
                currentDateMethod()+"' AND workingHours.month = '"+currentMonthMethod()+"' AND workingHours.year = '"+currentYearMethod()+"' AND workerMonthlyRecord.emp_id = '"+employeeId.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateData);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertSalaryRecord(){
        int month = Integer.parseInt(currentMonthMethod());
        int year = Integer.parseInt(currentYearMethod());
        double salaryAmount;
        if (month == 1) {

            String insertData = "INSERT INTO `salaries`(`emp_id`,`date`, `year`) VALUES ('" + employeeId.getText() + "','" +12+ "','"+(year - 1)+"')";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertData);

                //select amount
                String selectedAmount = "SELECT (workerMonthlyRecord.total_hours*paymentRatesPerHour.payment_amount_per_hour) AS amount FROM `workerMonthlyRecord`  JOIN paymentRatesPerHour ON workerMonthlyRecord.payment_rate_code = paymentRatesPerHour.payment_rate_code WHERE workerMonthlyRecord.emp_id ='"+
                        employeeId.getText()+"' AND workerMonthlyRecord.month = '"+12+"' AND workerMonthlyRecord.year ='"+(year-1)+"'";

                try{
                    Statement statement1 = connectDB.createStatement();
                    ResultSet queryResult = statement1.executeQuery(selectedAmount);

                    while(queryResult.next()){
                        if (queryResult.getInt(1) == 1){
                            salaryAmount = queryResult.getDouble("amount");

                            //update the salary record
                            String updateData = "UPDATE `salaries` SET `amount`='"+salaryAmount+"' WHERE date = '"+12+"' AND year = '"+(year-1)+"'";


                            try{
                                Statement statement2 = connectDB.createStatement();
                                statement2.executeUpdate(updateData);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }else{
            String insertData = "INSERT INTO `salaries`(`emp_id`,`date`, `year`) VALUES ('" + employeeId.getText() + "','" +(month - 1)+ "','"+currentYearMethod()+"')";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertData);
                //select amount
                String selectedAmount = "SELECT (workerMonthlyRecord.total_hours*paymentRatesPerHour.payment_amount_per_hour) AS amount FROM `workerMonthlyRecord`  JOIN paymentRatesPerHour ON workerMonthlyRecord.payment_rate_code = paymentRatesPerHour.payment_rate_code WHERE workerMonthlyRecord.emp_id ='"+
                        employeeId.getText()+"' AND workerMonthlyRecord.month = '"+(month-1)+"' AND workerMonthlyRecord.year ='"+currentYearMethod()+"'";

                try{
                    Statement statement1 = connectDB.createStatement();
                    ResultSet queryResult = statement1.executeQuery(selectedAmount);

                    while(queryResult.next()){
                        if (queryResult.getInt(1) == 1){
                            salaryAmount = queryResult.getDouble("amount");

                            //update the salary record
                            String updateData = "UPDATE `salaries` SET `amount`='"+salaryAmount+"' WHERE date = '"+12+"' AND year = '"+(year-1)+"'";


                            try{
                                Statement statement2 = connectDB.createStatement();
                                statement2.executeUpdate(updateData);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }


    public void submitButtonOnAction(ActionEvent e){
        if(!employeeId.getText().isBlank()){
            if(validateEmployeeId() == true){
                if(validateEmployeeWithDepartment() == true){
                   if(fullDay.isSelected()){
                       if (!hours.getText().isBlank() && !minutes.getText().isBlank() && !overTimeHours.getText().isBlank() && !overTimeMinutes.getText().isBlank()) {
                           if (validateRecord() == false) {
                               if (hourCheck() == true) {
                                   if (validateHours() == true) {
                                       if (minutesCheck() == true) {
                                           if (validateMinutes() == true) {
                                               if (overTimeCheck() == true) {
                                                   if (validateOverTime() == true) {
                                                       if (overTimeMinutesCheck() == true) {
                                                           if (validateOverTimeMinutes() == true) {
                                                               if (validateTotalHours() == true) {
                                                                   if(validateTotalOverTime() == true) {
                                                                       if(validateIfMonthRecordExist() ==true) {
                                                                           insertDataForFullDay();
                                                                           updateMonthlyRecordForFullDay();

                                                                           //Create salary record if date == 5
                                                                           if(Integer.parseInt(currentDateMethod()) == 5){
                                                                               insertSalaryRecord();
                                                                           }
                                                                           employeeIdMessage.setText("");
                                                                           hoursMessage.setText("");
                                                                           minutesMessage.setText("");
                                                                           overTimeHoursMessage.setText("");
                                                                           submitMessage.setText("Successfully Submitted");
                                                                           checkBoxMessage.setText("");
                                                                           overTimeMinutesMessage1.setText("");
                                                                       }else {
                                                                           insertNewMonthlyRecord();
                                                                           insertDataForFullDay();
                                                                           updateMonthlyRecordForFullDay();
                                                                           employeeIdMessage.setText("");
                                                                           hoursMessage.setText("");
                                                                           minutesMessage.setText("");
                                                                           overTimeHoursMessage.setText("");
                                                                           submitMessage.setText("Successfully Submitted");
                                                                           checkBoxMessage.setText("");
                                                                           overTimeMinutesMessage1.setText("");

                                                                       }
                                                                   } else {
                                                                       employeeIdMessage.setText("");
                                                                       hoursMessage.setText("");
                                                                       minutesMessage.setText("");
                                                                       overTimeHoursMessage.setText("Hours and Minutes exceed 4hrs");
                                                                       submitMessage.setText("");
                                                                       checkBoxMessage.setText("");
                                                                       overTimeMinutesMessage1.setText("");
                                                                   }

                                                               } else {
                                                                   employeeIdMessage.setText("");
                                                                   hoursMessage.setText("Total hours exceed 8hrs");
                                                                   minutesMessage.setText("");
                                                                   overTimeHoursMessage.setText("");
                                                                   submitMessage.setText("");
                                                                   checkBoxMessage.setText("");
                                                                   overTimeMinutesMessage1.setText("");
                                                               }

                                                           } else {
                                                               employeeIdMessage.setText("");
                                                               hoursMessage.setText("");
                                                               minutesMessage.setText("");
                                                               overTimeHoursMessage.setText("");
                                                               submitMessage.setText("");
                                                               checkBoxMessage.setText("");
                                                               overTimeMinutesMessage1.setText("Enter a value below 60");
                                                           }

                                                       } else {
                                                           employeeIdMessage.setText("");
                                                           hoursMessage.setText("");
                                                           minutesMessage.setText("");
                                                           overTimeHoursMessage.setText("");
                                                           submitMessage.setText("");
                                                           checkBoxMessage.setText("");
                                                           overTimeMinutesMessage1.setText("Integer value is required");
                                                       }

                                                   } else {
                                                       employeeIdMessage.setText("");
                                                       hoursMessage.setText("");
                                                       minutesMessage.setText("");
                                                       overTimeHoursMessage.setText("Hours exceed normal 4hrs");
                                                       submitMessage.setText("");
                                                       checkBoxMessage.setText("");
                                                       overTimeMinutesMessage1.setText("");
                                                   }

                                               } else {
                                                   employeeIdMessage.setText("");
                                                   hoursMessage.setText("");
                                                   minutesMessage.setText("");
                                                   overTimeHoursMessage.setText("Integer value is required");
                                                   submitMessage.setText("");
                                                   checkBoxMessage.setText("");
                                                   overTimeMinutesMessage1.setText("");
                                               }

                                           } else {
                                               employeeIdMessage.setText("");
                                               hoursMessage.setText("");
                                               minutesMessage.setText("Enter a number below 60");
                                               overTimeHoursMessage.setText("");
                                               submitMessage.setText("");
                                               checkBoxMessage.setText("");
                                               overTimeMinutesMessage1.setText("");
                                           }

                                       } else {
                                           employeeIdMessage.setText("");
                                           hoursMessage.setText("");
                                           minutesMessage.setText("Integer value is required");
                                           overTimeHoursMessage.setText("");
                                           submitMessage.setText("");
                                           checkBoxMessage.setText("");
                                           overTimeMinutesMessage1.setText("");
                                       }

                                   } else {
                                       employeeIdMessage.setText("");
                                       hoursMessage.setText("Hours exceed normal 8hrs");
                                       minutesMessage.setText("");
                                       overTimeHoursMessage.setText("");
                                       submitMessage.setText("");
                                       checkBoxMessage.setText("");
                                       overTimeMinutesMessage1.setText("");
                                   }

                               } else {
                                   employeeIdMessage.setText("");
                                   hoursMessage.setText("Integer value is required");
                                   minutesMessage.setText("");
                                   overTimeHoursMessage.setText("");
                                   submitMessage.setText("");
                                   checkBoxMessage.setText("");
                                   overTimeMinutesMessage1.setText("");
                               }

                           } else {
                               employeeIdMessage.setText("Record already exist");
                               hoursMessage.setText("");
                               minutesMessage.setText("");
                               overTimeHoursMessage.setText("");
                               submitMessage.setText("");
                               checkBoxMessage.setText("");
                               overTimeMinutesMessage1.setText("");
                           }
                       }else{
                           employeeIdMessage.setText("");
                           hoursMessage.setText("");
                           minutesMessage.setText("");
                           overTimeHoursMessage.setText("");
                           submitMessage.setText("Fill all the text fields");
                           checkBoxMessage.setText("");
                           overTimeMinutesMessage1.setText("");
                       }

                   }else if (halfDay.isSelected()){
                       if (!hours.getText().isBlank() && !minutes.getText().isBlank() && !overTimeHours.getText().isBlank() && !overTimeMinutes.getText().isBlank()) {
                           if (validateRecord() == false) {
                               if (hourCheck() == true) {
                                   if (validateHours() == true) {
                                       if (minutesCheck() == true) {
                                           if (validateMinutes() == true) {
                                               if (validateTotalHours() == true) {
                                                   if(validateIfMonthRecordExist() == true) {
                                                       insertDataForHalfDay();
                                                       updateMonthlyRecordForHalfDay();

                                                       //Create salary record if date == 5
                                                       if(Integer.parseInt(currentDateMethod()) == 5){
                                                           insertSalaryRecord();
                                                       }
                                                       employeeIdMessage.setText("");
                                                       hoursMessage.setText("");
                                                       minutesMessage.setText("");
                                                       overTimeHoursMessage.setText("");
                                                       submitMessage.setText("Successfully submitted");
                                                       checkBoxMessage.setText("");
                                                       overTimeMinutesMessage1.setText("");
                                                   }else {
                                                       insertNewMonthlyRecord();
                                                       insertDataForHalfDay();
                                                       updateMonthlyRecordForHalfDay();

                                                       //Create salary record if date == 5
                                                       if(Integer.parseInt(currentDateMethod()) == 5){
                                                           insertSalaryRecord();
                                                       }
                                                       employeeIdMessage.setText("");
                                                       hoursMessage.setText("");
                                                       minutesMessage.setText("");
                                                       overTimeHoursMessage.setText("");
                                                       submitMessage.setText("Successfully submitted");
                                                       checkBoxMessage.setText("");
                                                       overTimeMinutesMessage1.setText("");
                                                   }
                                               }else {
                                                   employeeIdMessage.setText("");
                                                   hoursMessage.setText("Total hours exceed 8hrs");
                                                   minutesMessage.setText("");
                                                   overTimeHoursMessage.setText("");
                                                   submitMessage.setText("");
                                                   checkBoxMessage.setText("");
                                                   overTimeMinutesMessage1.setText("");
                                               }
                                           }else {
                                               employeeIdMessage.setText("");
                                               hoursMessage.setText("");
                                               minutesMessage.setText("Enter a number below 60");
                                               overTimeHoursMessage.setText("");
                                               submitMessage.setText("");
                                               checkBoxMessage.setText("");
                                               overTimeMinutesMessage1.setText("");
                                           }
                                       }else {
                                           employeeIdMessage.setText("");
                                           hoursMessage.setText("");
                                           minutesMessage.setText("Integer value is required");
                                           overTimeHoursMessage.setText("");
                                           submitMessage.setText("");
                                           checkBoxMessage.setText("");
                                           overTimeMinutesMessage1.setText("");
                                       }
                                   }else {
                                       employeeIdMessage.setText("");
                                       hoursMessage.setText("Hours exceed normal 8hrs");
                                       minutesMessage.setText("");
                                       overTimeHoursMessage.setText("");
                                       submitMessage.setText("");
                                       checkBoxMessage.setText("");
                                       overTimeMinutesMessage1.setText("");
                                   }
                               }else{
                                   employeeIdMessage.setText("");
                                   hoursMessage.setText("Integer value is required");
                                   minutesMessage.setText("");
                                   overTimeHoursMessage.setText("");
                                   submitMessage.setText("");
                                   checkBoxMessage.setText("");
                                   overTimeMinutesMessage1.setText("");
                               }


                               }else{
                               employeeIdMessage.setText("Record already exist");
                               hoursMessage.setText("");
                               minutesMessage.setText("");
                               overTimeHoursMessage.setText("");
                               submitMessage.setText("");
                               checkBoxMessage.setText("");
                               overTimeMinutesMessage1.setText("");
                           }

                           }else{
                           employeeIdMessage.setText("");
                           hoursMessage.setText("");
                           minutesMessage.setText("");
                           overTimeHoursMessage.setText("");
                           submitMessage.setText("Fill all the text fields");
                           checkBoxMessage.setText("");
                           overTimeMinutesMessage1.setText("");
                       }

                   }else if(absent.isSelected()){
                       if(validateRecord() == false){
                           if(validateIfMonthRecordExist() == true) {
                               insertDataForAbsent();
                               updateMonthlyRecordForAbsent();

                               //Create salary record if date == 5
                               if(Integer.parseInt(currentDateMethod()) == 5){
                                   insertSalaryRecord();
                               }
                               employeeIdMessage.setText("");
                               hoursMessage.setText("");
                               minutesMessage.setText("");
                               overTimeHoursMessage.setText("");
                               submitMessage.setText("Successfully submitted!");
                               checkBoxMessage.setText("");
                               overTimeMinutesMessage1.setText("");
                           }else {
                               insertNewMonthlyRecord();
                               insertDataForAbsent();
                               updateMonthlyRecordForAbsent();

                               //Create salary record if date == 5
                               if(Integer.parseInt(currentDateMethod()) == 5){
                                   insertSalaryRecord();
                               }
                               employeeIdMessage.setText("");
                               hoursMessage.setText("");
                               minutesMessage.setText("");
                               overTimeHoursMessage.setText("");
                               submitMessage.setText("Successfully submitted!");
                               checkBoxMessage.setText("");
                               overTimeMinutesMessage1.setText("");
                           }

                       }else{
                           employeeIdMessage.setText("Record already exist");
                           hoursMessage.setText("");
                           minutesMessage.setText("");
                           overTimeHoursMessage.setText("");
                           submitMessage.setText("");
                           checkBoxMessage.setText("");
                           overTimeMinutesMessage1.setText("");
                       }


                   }else if (paidDayOff.isSelected()){
                       if(validateRecord() ==  false){
                           if (validateIfMonthRecordExist() == true) {
                               insertDataForPaidDayOff();
                               updateMonthlyRecordForPaidDayOff();

                               //Create salary record if date == 5
                               if(Integer.parseInt(currentDateMethod()) == 5){
                                   insertSalaryRecord();
                               }
                               employeeIdMessage.setText("");
                               hoursMessage.setText("");
                               minutesMessage.setText("");
                               overTimeHoursMessage.setText("");
                               submitMessage.setText("Successfully submitted!");
                               checkBoxMessage.setText("");
                               overTimeMinutesMessage1.setText("");
                           }else{
                               insertNewMonthlyRecord();
                               insertDataForPaidDayOff();
                               updateMonthlyRecordForPaidDayOff();

                               //Create salary record if date == 5
                               if(Integer.parseInt(currentDateMethod()) == 5){
                                   insertSalaryRecord();
                               }
                               employeeIdMessage.setText("");
                               hoursMessage.setText("");
                               minutesMessage.setText("");
                               overTimeHoursMessage.setText("");
                               submitMessage.setText("Successfully submitted!");
                               checkBoxMessage.setText("");
                               overTimeMinutesMessage1.setText("");
                           }

                       }else {
                           employeeIdMessage.setText("Record already exist");
                           hoursMessage.setText("");
                           minutesMessage.setText("");
                           overTimeHoursMessage.setText("");
                           submitMessage.setText("");
                           checkBoxMessage.setText("");
                           overTimeMinutesMessage1.setText("");
                       }

                   }else{
                       employeeIdMessage.setText("");
                       hoursMessage.setText("");
                       minutesMessage.setText("");
                       overTimeHoursMessage.setText("");
                       submitMessage.setText("");
                       checkBoxMessage.setText("Select the checkbox first");
                       overTimeMinutesMessage1.setText("");
                   }

                }else{
                    employeeIdMessage.setText("Employee department error");
                    hoursMessage.setText("");
                    minutesMessage.setText("");
                    overTimeHoursMessage.setText("");
                    submitMessage.setText("");
                    checkBoxMessage.setText("");
                    overTimeMinutesMessage1.setText("");
                }

            }else{
                employeeIdMessage.setText("Employee cannot be found");
                hoursMessage.setText("");
                minutesMessage.setText("");
                overTimeHoursMessage.setText("");
                submitMessage.setText("");
                checkBoxMessage.setText("");
                overTimeMinutesMessage1.setText("");
            }

        }else{
            employeeIdMessage.setText("Enter employee's id first");
            hoursMessage.setText("");
            minutesMessage.setText("");
            overTimeHoursMessage.setText("");
            submitMessage.setText("");
            checkBoxMessage.setText("");
            overTimeMinutesMessage1.setText("");
        }
    }
}
