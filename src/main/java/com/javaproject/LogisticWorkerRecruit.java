package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogisticWorkerRecruit {

    private static final String CODE = "LG16";
    @FXML
    private TextField email;

    @FXML
    private Label emailMessage;

    @FXML
    private CheckBox femaleBox;

    @FXML
    private TextField firstname;

    @FXML
    private Label firstnameMessage;

    @FXML
    private Label genderMessage;

    @FXML
    private TextField locationName;

    @FXML
    private Label locationMessage;

    @FXML
    private CheckBox maleBox;

    @FXML
    private CheckBox normalWorker;

    @FXML
    private TextField password;

    @FXML
    private Label passwordMessage;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Label phoneNumberMessage;

    @FXML
    private Label roleMessage;

    @FXML
    private TextField secondName;

    @FXML
    private Label secondNameMessage;

    @FXML
    private CheckBox supervisor;

    @FXML
    private TextField username;

    @FXML
    private Label usernameMessage;

    @FXML
    private Label recruitMessage;


    //creating database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    private String genderName;

    private int roleNo;

    //control gender
    public void genderBoxInAction(ActionEvent e){
        if (maleBox.isSelected()){
            femaleBox.setDisable(true);
            genderName = "Male";
        }else if(femaleBox.isSelected()){
            maleBox.setDisable(true);
            genderName = "Female";
        }else{
            maleBox.setDisable(false);
            femaleBox.setDisable(false);
        }
    }

    //control role
    public void roleBoxInAction(ActionEvent e){
        if(supervisor.isSelected()){
            normalWorker.setDisable(true);
            roleNo = 5;
        }else if(normalWorker.isSelected()){
            supervisor.setDisable(true);
            roleNo = 7;
        }else {
            supervisor.setDisable(false);
            normalWorker.setDisable(false);
        }
    }

    public String currentDateMethod(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }


    public boolean validateFirstName(){
        String s = firstname.getText();

        if(s == null){
            return false;
        }

        try{
            int i = Integer.parseInt(s);
        }catch (NumberFormatException e){
            return false;
        }

        return true;
    } public boolean validateSecondName(){
        String s = secondName.getText();

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

    public boolean validateEma(){
        String s = email.getText();

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

    public boolean validatePhone(){
        String s = phoneNumber.getText();

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

    public boolean validateEmail(){
        String emailValidate = "SELECT COUNT(1) FROM `staff` WHERE email = '"+email.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(emailValidate);

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

    public boolean validatePhoneNumber(){
        String phoneValidate = "SELECT COUNT(1) FROM `staff` WHERE phone = '"+phoneNumber.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(phoneValidate);

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

    public boolean validateUserName(){
        String userNameValidate = "SELECT COUNT(1) FROM `staff` WHERE username = '"+username.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(userNameValidate);

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

    public boolean validateUserEmpId(String str){
        String userNameValidate = "SELECT COUNT(1) FROM `staff` WHERE emp_id = '"+str+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(userNameValidate);

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

    public String currentYearMethod(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }

    static int i = 1;
    //generate emp_id
    public String generateEmpId(){
        String emp_id;
        String yr = currentYearMethod();
        int count = 0;

        do{
            int j = i;
            while(j !=0){
                j = j/10;
                count++;
            }

            if(count == 1){
                String str = String.valueOf(i);
                emp_id = CODE+"/"+"0000"+str+"/"+yr.substring(yr.length()-2);

            }else if(count == 2){
                String str = String.valueOf(i);
                emp_id = CODE+"/"+"000"+str+"/"+yr.substring(yr.length()-2);

            }else if(count == 3){
                String str = String.valueOf(i);
                emp_id = CODE+"/"+"00"+str+"/"+yr.substring(yr.length()-2);

            }else if(count == 4){
                String str = String.valueOf(i);
                emp_id = CODE+"/"+"0"+str+"/"+yr.substring(yr.length()-2);
            }else {
                String str = String.valueOf(i);
                emp_id = CODE+"/"+str+"/"+yr.substring(yr.length()-2);
            }

            i++;
        }while(validateUserEmpId(emp_id) == true);
        return emp_id;
    }


    public void insertEmployee(){
        String insertData = "INSERT INTO `staff`(`emp_id`, `username`, `password`, `role_nu`, `firstName`, `secondName`, `email`, `dep_code`, `gender`, `phone`, `location`, `date_recruited`) VALUES ('"+
                generateEmpId()+"','"+username.getText()+"','"+password.getText()+"','"+roleNo+"','"+ firstname.getText()+"','"+secondName.getText()+"','"+email.getText()+"','"+CODE+"','"+genderName+"','"+
                phoneNumber.getText()+"','"+locationName.getText()+"','"+currentDateMethod()+"')";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertData);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void recruitOnAction(ActionEvent e){
        if(!firstname.getText().isBlank() && !secondName.getText().isBlank() && !locationName.getText().isBlank()
                && !email.getText().isBlank() && !phoneNumber.getText().isBlank() && !username.getText().isBlank() && !password.getText().isBlank()){
            if(maleBox.isSelected() || femaleBox.isSelected()){
                if (supervisor.isSelected() || normalWorker.isSelected()){
                    if(validateFirstName() == false) {
                        if(validateSecondName() == false) {
                            if(validateEma() == false) {
                                if(validatePhone() == true) {
                                    if (validateEmail() == false) {
                                        if (validatePhoneNumber() == false) {
                                            if(validateUserName() == false){
                                                insertEmployee();
                                                recruitMessage.setText("Employee Successfully inserted");
                                                firstnameMessage.setText("");
                                                secondNameMessage.setText("");
                                                locationMessage.setText("");
                                                emailMessage.setText("");
                                                phoneNumberMessage.setText("");
                                                usernameMessage.setText("");
                                                passwordMessage.setText("");
                                                genderMessage.setText("");
                                                roleMessage.setText("");
                                            }else {
                                                recruitMessage.setText("");
                                                firstnameMessage.setText("");
                                                secondNameMessage.setText("");
                                                locationMessage.setText("");
                                                emailMessage.setText("");
                                                phoneNumberMessage.setText("");
                                                usernameMessage.setText("Username exist");
                                                passwordMessage.setText("");
                                                genderMessage.setText("");
                                                roleMessage.setText("");
                                            }

                                        } else {
                                            recruitMessage.setText("");
                                            firstnameMessage.setText("");
                                            secondNameMessage.setText("");
                                            locationMessage.setText("");
                                            emailMessage.setText("");
                                            phoneNumberMessage.setText("Phone number exist");
                                            usernameMessage.setText("");
                                            passwordMessage.setText("");
                                            genderMessage.setText("");
                                            roleMessage.setText("");
                                        }

                                    } else {
                                        recruitMessage.setText("");
                                        firstnameMessage.setText("");
                                        secondNameMessage.setText("");
                                        locationMessage.setText("");
                                        emailMessage.setText("Email already exist");
                                        phoneNumberMessage.setText("");
                                        usernameMessage.setText("");
                                        passwordMessage.setText("");
                                        genderMessage.setText("");
                                        roleMessage.setText("");
                                    }
                                }else{
                                    recruitMessage.setText("");
                                    firstnameMessage.setText("");
                                    secondNameMessage.setText("");
                                    locationMessage.setText("");
                                    emailMessage.setText("");
                                    phoneNumberMessage.setText("Input cannot contain letters");
                                    usernameMessage.setText("");
                                    passwordMessage.setText("");
                                    genderMessage.setText("");
                                    roleMessage.setText("");
                                }
                            }else {
                                recruitMessage.setText("");
                                firstnameMessage.setText("");
                                secondNameMessage.setText("");
                                locationMessage.setText("");
                                emailMessage.setText("Email cannot  be a digit");
                                phoneNumberMessage.setText("");
                                usernameMessage.setText("");
                                passwordMessage.setText("");
                                genderMessage.setText("");
                                roleMessage.setText("");
                            }
                        }else{
                            recruitMessage.setText("");
                            firstnameMessage.setText("");
                            secondNameMessage.setText("Name cannot be a digit");
                            locationMessage.setText("");
                            emailMessage.setText("");
                            phoneNumberMessage.setText("");
                            usernameMessage.setText("");
                            passwordMessage.setText("");
                            genderMessage.setText("");
                            roleMessage.setText("");
                        }
                    }else{
                        recruitMessage.setText("");
                        firstnameMessage.setText("Name cannot be a digit");
                        secondNameMessage.setText("");
                        locationMessage.setText("");
                        emailMessage.setText("");
                        phoneNumberMessage.setText("");
                        usernameMessage.setText("");
                        passwordMessage.setText("");
                        genderMessage.setText("");
                        roleMessage.setText("");
                    }

                }else{
                    recruitMessage.setText("");
                    firstnameMessage.setText("");
                    secondNameMessage.setText("");
                    locationMessage.setText("");
                    emailMessage.setText("");
                    phoneNumberMessage.setText("");
                    usernameMessage.setText("");
                    passwordMessage.setText("");
                    genderMessage.setText("");
                    roleMessage.setText("Select one box");
                }

            }else {
                recruitMessage.setText("");
                firstnameMessage.setText("");
                secondNameMessage.setText("");
                locationMessage.setText("");
                emailMessage.setText("");
                phoneNumberMessage.setText("");
                usernameMessage.setText("");
                passwordMessage.setText("");
                genderMessage.setText("Select one box");
                roleMessage.setText("");
            }

        }else{
            recruitMessage.setText("Fill all the text areas");
            firstnameMessage.setText("");
            secondNameMessage.setText("");
            locationMessage.setText("");
            emailMessage.setText("");
            phoneNumberMessage.setText("");
            usernameMessage.setText("");
            passwordMessage.setText("");
            genderMessage.setText("");
            roleMessage.setText("");
        }
    }

}
