package com.javaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class IndividualWorkerRecord<String> implements Initializable {
    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private Label department;

    @FXML
    private Label employeeName;

    @FXML
    private Label paymentRate;

    @FXML
    private Label role;

    @FXML
    private TableColumn<?, ?> salaryAmount;

    @FXML
    private TableView<?> salaryHistoryTable;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<StaffProfile> Staffdetails = FXCollections.observableArrayList();
    ObservableList<PaymentDetails> PaymentdetailList = FXCollections.observableArrayList();

    StaffProfile staffProfile;
    public void refreshDetails(){
        try {
            Staffdetails.clear();
            String query = (String) ("SELECT staff.firstName,staff.secondName,department.dep_name,role.role FROM `staff` JOIN department ON staff.dep_code = department.dep_code JOIN role ON staff.role_nu = role.role_nu WHERE staff.emp_id = '"+EmployeeId.employeeCode+"'");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Staffdetails.add( staffProfile = new StaffProfile(
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getString("dep_name"),
                        resultSet.getString("role")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public java.lang.String currentMonthMethod(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
        LocalDateTime now = LocalDateTime.now();
        java.lang.String date = dtf.format(now);

        return date;
    }

    public java.lang.String currentYearMethod(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        java.lang.String date = dtf.format(now);

        return date;
    }

    PaymentDetails paymentDetails;
    public void refreshDetails2(){
        try {
            PaymentdetailList.clear();
            String query = (String) ("SELECT paymentRatesPerHour.payment_amount_per_hour  FROM `paymentRatesPerHour` JOIN workerMonthlyRecord ON workerMonthlyRecord.payment_rate_code = paymentRatesPerHour.payment_rate_code  WHERE workerMonthlyRecord.month = '"+currentMonthMethod()+"' AND workerMonthlyRecord.year = '"+currentYearMethod()+"' AND workerMonthlyRecord.emp_id = '"+EmployeeId.employeeCode+"'");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                PaymentdetailList.add( paymentDetails = new PaymentDetails(
                        resultSet.getString("payment_amount_per_hour")
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
        refreshDetails();
        refreshDetails2();
        employeeName.setText(staffProfile.getUser());
        department.setText(staffProfile.getDepartmentName());
        role.setText(staffProfile.getJobRole());
        paymentRate.setText(paymentDetails.getPaymentRate());


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
