package com.javaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class PayrllHome<String> implements Initializable {

    @FXML
    private Label employeeCount;

    @FXML
    private Label username;

    @FXML
    private PieChart pieChertRender;


    public void displayPieChart(){
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("No of Employees",700),
                        new PieChart.Data("Total Payee",6540)
                );
        //updateInnerCircleLayout();
        //addInnerCircleIfNotPresent();
        pieChertRender.setPrefHeight(950);
        pieChertRender.setPrefWidth(950);
        pieChertRender.setData(pieChartData);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<PayrollMainView> PayrollViewList = FXCollections.observableArrayList();

    //get number of employees
    PayrollMainView payrollMainView;
    public void refreshEmployeeCount(){
        try {
            PayrollViewList.clear();
            String query = (String) "SELECT COUNT(1) FROM `staff`";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                PayrollViewList.add( payrollMainView = new PayrollMainView(
                        resultSet.getString("COUNT(1)")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    PayrollMainView payrollMainView2;
    //get the names of the user
    public void refreshUserName(){
        try {
            PayrollViewList.clear();
            String query = (String) ("SELECT firstName,secondName FROM `staff` WHERE username = '"+LogInController.userName+"'  AND password = '"+LogInController.password+"'");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                PayrollViewList.add( payrollMainView2 = new PayrollMainView(
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void loadData() {
        DatabaseConnection connectNow = new DatabaseConnection();
        connection = connectNow.getConnection();
        refreshUserName();
        refreshEmployeeCount();
        displayPieChart();
        employeeCount.setText(payrollMainView.getEmployeeCount());
        username.setText(payrollMainView2.getUsername());
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
