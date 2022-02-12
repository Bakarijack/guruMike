package com.javaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PayrollApproval<String> implements Initializable {

    @FXML
    private Label date3;


    @FXML
    private Label numberofEmployee3;

    @FXML
    private Label totalPaye3;

    @FXML
    private BorderPane mainPane;

    @FXML
    private TableColumn<NetPaye, String> approvalStatus;

    @FXML
    private TableColumn<NetPaye, String> approvedDate;

    @FXML
    private TableColumn<NetPaye, String> date;

    @FXML
    private TableColumn<NetPaye, String> netPayee;

    @FXML
    private TableColumn<NetPaye, String> numberOfEMployees;

    @FXML
    private TableView<NetPaye> approvalTable;

    @FXML
    private Hyperlink status3;

    @FXML
    private Label statusLabel;


    /*public void pendingLinkOnAction(ActionEvent evt){
        if(status3.isPressed()){
            status3.setText("Approved");
            NetPaye.setStatus(1);
            status3.setDisable(true);
        }
    }*/


    public void arrowLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("generatePaye");
        mainPane.setRight(view);
    }

    public void approveLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("approvePayee");
        mainPane.setRight(view);
    }


    public java.lang.String currentDateMethod(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
        LocalDateTime now = LocalDateTime.now();
        java.lang.String date = dtf.format(now);

        return date;
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

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<NetPaye> NetPayeViewList = FXCollections.observableArrayList();


    //display for the third record
    NetPaye netPaye3;
    public void refreshRecord3(){
        int month = Integer.parseInt(currentMonthMethod());
        if(month == 1) {
            lastYearRecord();
        }else {
            lastMonthRecord();
        }
    }

    public void lastMonthRecord(){
        try {
            NetPayeViewList.clear();
            String query = (String) ((String) "SELECT `number_of_employees`, `total_paye`, `month`, `year`, `approved_status` FROM `netPay` WHERE month ="+currentMonthMethod()+"-1 AND year ="+currentYearMethod()+"");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NetPayeViewList.add(netPaye3 = new NetPaye(
                        resultSet.getString("month"),
                        resultSet.getString("year"),
                        resultSet.getString("total_paye"),
                        resultSet.getString("number_of_employees"),
                        resultSet.getString("approved_status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            //  e.getCause();
        }

    }

    public void lastYearRecord(){
        try {
            NetPayeViewList.clear();
            String query = (String) ((String) "SELECT `number_of_employees`, `total_paye`, `month`, `year`, `approved_status` FROM `netPay` WHERE month =12 AND year = "+currentYearMethod()+"-1");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NetPayeViewList.add(netPaye3 = new NetPaye(
                        resultSet.getString("month"),
                        resultSet.getString("year"),
                        resultSet.getString("total_paye"),
                        resultSet.getString("number_of_employees"),
                        resultSet.getString("approved_status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // e.getCause();
        }

    }

    public void refreshTable(){
        try {
            NetPayeViewList.clear();
            String query = (String) "SELECT `payment_date`, `number_of_employees`, `total_paye`, `month`, `year`, `approved_status` FROM `netPay` ";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                NetPayeViewList.add(new NetPaye(
                        resultSet.getString("payment_date"),
                        resultSet.getString("number_of_employees"),
                        resultSet.getString("total_paye"),
                        resultSet.getString("month"),
                        resultSet.getString("year"),
                        resultSet.getString("approved_status")
                        ));
                approvalTable.setItems(NetPayeViewList);
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void loadData() {
        DatabaseConnection connectNow = new DatabaseConnection();
        connection = connectNow.getConnection();
        refreshRecord3();
        refreshTable();
        //date3.setText(netPaye3.getCombineDate());
        //numberofEmployee3.setText(netPaye3.getNumberOfEmployees());
        //totalPaye3.setText(netPaye3.getTotalPayeNet());
        //for the table
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        netPayee.setCellValueFactory(new PropertyValueFactory<>("totalPayeNet"));
        numberOfEMployees.setCellValueFactory(new PropertyValueFactory<>("numberOfEmployees"));
        approvedDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        approvalStatus.setCellValueFactory(new PropertyValueFactory<>("approvalStatus"));
        //statusLabel.setText(netPaye3.getApprovalStatus());

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
