package com.javaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AdminManagerWorkersReport<String> implements Initializable {
    @FXML
    private TableColumn<StaffProfile, String> department;

    @FXML
    private TableColumn<StaffProfile, String> email;

    @FXML
    private TableColumn<StaffProfile, String> employeeId;

    @FXML
    private TableView<StaffProfile> employeeTable;

    @FXML
    private TableColumn<StaffProfile, String> location;

    @FXML
    private TableColumn<StaffProfile, String> name;

    @FXML
    private TableColumn<StaffProfile, String> phoneNumber;

    @FXML
    private TableColumn<StaffProfile, String> role;

    @FXML
    private PieChart piechartDisplay;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<StaffProfile> StaffList = FXCollections.observableArrayList();
    ObservableList<PayrollMainView> PayrollViewList = FXCollections.observableArrayList();


    public void refreshTable(){
        try {
            StaffList.clear();
            query = (String) "SELECT staff.emp_id,staff.firstName,staff.secondName,staff.phone,staff.email,staff.location,department.dep_name,role.role FROM `staff` JOIN department ON department.dep_code=staff.dep_code JOIN role ON role.role_nu = staff.role_nu";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                StaffList.add(new StaffProfile(
                        resultSet.getString("emp_id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("location"),
                        resultSet.getString("dep_name"),
                        resultSet.getString("role")));
                employeeTable.setItems(StaffList);
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


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

        piechartDisplay.setPrefHeight(500);
        piechartDisplay.setPrefWidth(500);
        piechartDisplay.setData(pieChartData);
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
        refreshTable();

        employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
        name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        department.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        role.setCellValueFactory(new PropertyValueFactory<>("jobRole"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
