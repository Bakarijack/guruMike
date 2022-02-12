package com.javaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class OfficeSupervisorPayroll<String> implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label employeeCount;

    @FXML
    private Label username;

    @FXML
    private PieChart pieChertRender;

    @FXML
    private Hyperlink logout;

    @FXML
    private Label employeeNumber;

    @FXML
    private Label payee;

    @FXML
    private Label payeeDate;

    public void logoutOnAction(ActionEvent e){
        switchView();
    }

    public void switchView(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        java.lang.String verfyLogin1 = "SELECT role_nu FROM staff WHERE Username = '" + LogInController.userName+
                "' AND password='" + LogInController.password + "'";

        try{
            Statement statement1 = connectDB.createStatement();
            ResultSet queryResult1 = statement1.executeQuery(verfyLogin1);

            while(queryResult1.next()) {
                if (queryResult1.getInt("role_nu") == 1) {
                    Stage stage = (Stage) logout.getScene().getWindow();
                    stage.close();

                    //opens the new window
                    FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("adminView.fxml"));
                    Parent root1 = (Parent) customerViewLoader.load();
                    Stage stage2 = new Stage();

                    stage2.setScene(new Scene(root1));
                    stage2.show();
                }else if(queryResult1.getInt("role_nu") == 5){
                    Stage stage = (Stage) logout.getScene().getWindow();
                    stage.close();

                    //opens the new window
                    FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("officeSuppervisorView.fxml"));
                    Parent root1 = (Parent) customerViewLoader.load();
                    Stage stage2 = new Stage();

                    stage2.setScene(new Scene(root1));
                    stage2.show();
                }

                }
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<PayrollMainView> PayrollViewList = FXCollections.observableArrayList();
    ObservableList<NetPaye> NetPayeViewList = FXCollections.observableArrayList();
    ObservableList<NetData> NetDataViewList = FXCollections.observableArrayList();

    public void employeeLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("payrollEmployee");
        mainPane.setCenter(view);
    }

    public void approvalLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("payrollApproval");
        mainPane.setCenter(view);
    }

    public void homeLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("payrllHome");
        mainPane.setCenter(view);
    }

    //get number of employees
    PayrollMainView payrollMainView;

    public void refreshEmployeeCount() {
        try {
            PayrollViewList.clear();
            String query = (String) "SELECT COUNT(1) FROM `staff`";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PayrollViewList.add(payrollMainView = new PayrollMainView(
                        resultSet.getString("COUNT(1)")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    PayrollMainView payrollMainView2;

    //get the names of the user
    public void refreshUserName() {
        try {
            PayrollViewList.clear();
            String query = (String) ("SELECT firstName,secondName FROM `staff` WHERE username = '" + LogInController.userName + "'  AND password = '" + LogInController.password + "'");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PayrollViewList.add(payrollMainView2 = new PayrollMainView(
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public java.lang.String currentDateMethod() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
        LocalDateTime now = LocalDateTime.now();
        java.lang.String date = dtf.format(now);

        return date;
    }

    public java.lang.String currentMonthMethod() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
        LocalDateTime now = LocalDateTime.now();
        java.lang.String date = dtf.format(now);

        return date;
    }

    public java.lang.String currentYearMethod() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        java.lang.String date = dtf.format(now);

        return date;
    }

    NetData netData;
    public void lastYearPayee(){
        int year = Integer.parseInt(currentYearMethod()) - 1;
        try {
            NetDataViewList.clear();
            String query = (String) ("SELECT payment_date,total_paye,number_of_employees FROM `netPay` WHERE month='12' AND year='" +year+ "'");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NetDataViewList.add(netData = new NetData(
                        resultSet.getString("payment_date"),
                        resultSet.getString("total_paye"),
                        resultSet.getString("number_of_employees")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void lastMonthPayee(){
        int month = Integer.parseInt(currentMonthMethod()) - 1;
        int year = Integer.parseInt(currentYearMethod());
        try {
            NetDataViewList.clear();
            String query = (String) ("SELECT payment_date,total_paye,number_of_employees FROM `netPay` WHERE month='"+month+"'  AND year='"+year+"'");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NetDataViewList.add(netData = new NetData(
                        resultSet.getString("payment_date"),
                        resultSet.getString("total_paye"),
                        resultSet.getString("number_of_employees")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    //get the names of the user
    public void refreshNetPay() {
       lastMonthPayee();
    }

    public void displayPieChart(){
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("No of Employees",700),
                        new PieChart.Data("Total Payee",6540)
                );
        pieChertRender.setPrefHeight(950);
        pieChertRender.setPrefWidth(950);
        pieChertRender.setData(pieChartData);
    }

    public void loadData() {
        DatabaseConnection connectNow = new DatabaseConnection();
        connection = connectNow.getConnection();
        refreshUserName();
        refreshEmployeeCount();
        displayPieChart();
        /*refreshNetPay();
        payee.setText(netData.getTotalPayeeNet());
        payeeDate.setText(netData.getPayeeData());
        employeeNumber.setText(netData.getEmployeeCount());*/
        employeeCount.setText(payrollMainView.getEmployeeCount());
        username.setText(payrollMainView2.getUsername());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
