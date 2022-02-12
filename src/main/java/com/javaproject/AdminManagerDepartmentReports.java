package com.javaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AdminManagerDepartmentReports<String> implements Initializable {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Hyperlink leftArrow;

    @FXML
    private Hyperlink rightArrow;

    @FXML
    private PieChart piechartDisplay;

    @FXML
    private BorderPane mainPane;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<PayrollMainView> PayrollViewList = FXCollections.observableArrayList();


    //get number of employees
    PayrollMainView officeWorkers;

    public void refreshOfficeWorkerCount() {
        try {
            PayrollViewList.clear();
            String query = (String) "SELECT COUNT(1) FROM `staff` WHERE dep_code ='OS15'";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PayrollViewList.add(officeWorkers = new PayrollMainView(
                        resultSet.getString("COUNT(1)")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    PayrollMainView brooderWorkers;

    public void refreshBrooderWorkerCount() {
        try {
            PayrollViewList.clear();
            String query = (String) "SELECT COUNT(1) FROM `staff` WHERE dep_code ='HB12'";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PayrollViewList.add(brooderWorkers = new PayrollMainView(
                        resultSet.getString("COUNT(1)")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    PayrollMainView logisticWorkers;
    public void refreshLogisticWorkerCount() {
        try {
            PayrollViewList.clear();
            String query = (String) "SELECT COUNT(1) FROM `staff` WHERE dep_code ='LG16'";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PayrollViewList.add(logisticWorkers = new PayrollMainView(
                        resultSet.getString("COUNT(1)")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    PayrollMainView productionWorkers;
    public void refreshProductionWorkerCount() {
        try {
            PayrollViewList.clear();
            String query = (String) "SELECT COUNT(1) FROM `staff` WHERE dep_code ='PR14'";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PayrollViewList.add(productionWorkers = new PayrollMainView(
                        resultSet.getString("COUNT(1)")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    PayrollMainView packingWorkers;
    public void refreshPackingWorkerCount() {
        try {
            PayrollViewList.clear();
            String query = (String) "SELECT COUNT(1) FROM `staff` WHERE dep_code ='PA13'";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PayrollViewList.add(packingWorkers = new PayrollMainView(
                        resultSet.getString("COUNT(1)")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void displayPieChart(){
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Office Workers",Integer.parseInt(officeWorkers.getEmployeeCount())),
                        new PieChart.Data("Brooder Workers",Integer.parseInt(brooderWorkers.getEmployeeCount())),
                        new PieChart.Data("Packing Workers",Integer.parseInt(packingWorkers.getEmployeeCount())),
                        new PieChart.Data("Production Workers",Integer.parseInt(productionWorkers.getEmployeeCount())),
                        new PieChart.Data("Logistic Payee",Integer.parseInt(logisticWorkers.getEmployeeCount()))
                );
        
        piechartDisplay.setPrefHeight(950);
        piechartDisplay.setPrefWidth(950);
        piechartDisplay.setData(pieChartData);
    }

    public void linkArrowsOnAction(ActionEvent e){
        if(leftArrow.isPressed()){
            scrollPane.getOnMouseClicked();
        }else if(rightArrow.isPressed()){
            scrollPane.getOnMouseClicked();
        }
    }

    public void officeDepartmentOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("admnOfficeDepartment");
        mainPane.setCenter(view);
    }

    public void brooderDepartmentOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("admnBrooderDepartment");
        mainPane.setCenter(view);
    }

    public void packingDepartmentOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("admnPackingDepartment");
        mainPane.setCenter(view);
    }

    public void productionDepartmentOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("admnProductionDepartment");
        mainPane.setCenter(view);
    }

    public void logisticDepartmentOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("admnLogisticDepartment");
        mainPane.setCenter(view);
    }

    public void loadData() {
        DatabaseConnection connectNow = new DatabaseConnection();
        connection = connectNow.getConnection();
        refreshOfficeWorkerCount();
        refreshBrooderWorkerCount();
        refreshLogisticWorkerCount();
        refreshPackingWorkerCount();
        refreshProductionWorkerCount();
        displayPieChart();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
