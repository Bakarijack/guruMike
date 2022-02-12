package com.javaproject;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PayrollEmpList<String> implements Initializable {
    @FXML
    private TableColumn<PayrollMainView, String> daysAbsent;

    @FXML
    private TableColumn<PayrollMainView, String> employeeId;

    @FXML
    private TableColumn<PayrollMainView, String> fullDays;

    @FXML
    private TableColumn<PayrollMainView, String> name;

    @FXML
    private TableColumn<PayrollMainView, String> overTimeDays;

    @FXML
    private TableColumn<PayrollMainView, String> paidOffDays;

    @FXML
    private TableColumn<PayrollMainView, String> totalHours;

    @FXML
    private TableColumn<PayrollMainView, String> halfDays;

    @FXML
    private TableView<PayrollMainView> workingTable;

    public void viewButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader logInLoader = new FXMLLoader(getClass().getResource("employeeId.fxml"));
        Parent root1 = (Parent) logInLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        // mainPane.setDisable(true);
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
        //stage.show();
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<PayrollMainView> WorkingProgressList = FXCollections.observableArrayList();

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


    //PayrollMainView payrollMainView;
    public void refreshWorkingView(){
        try {
            WorkingProgressList.clear();
            String query = (String) ("SELECT workerMonthlyRecord.emp_id,staff.firstName,staff.secondName,workerMonthlyRecord.absent_days,workerMonthlyRecord.half_days,workerMonthlyRecord.paid_off_days,workerMonthlyRecord.over_time_count,workerMonthlyRecord.full_days,workerMonthlyRecord.total_hours FROM `workerMonthlyRecord` JOIN staff ON workerMonthlyRecord.emp_id = staff.emp_id WHERE workerMonthlyRecord.month = '"+
                                currentMonthMethod()+"' AND workerMonthlyRecord.year = '"+currentYearMethod()+"'");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                WorkingProgressList.add( new PayrollMainView(
                        resultSet.getString("emp_id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getString("absent_days"),
                        resultSet.getString("half_days"),
                        resultSet.getString("paid_off_days"),
                        resultSet.getString("over_time_count"),
                        resultSet.getString("full_days"),
                        resultSet.getString("total_hours")
                ));
                workingTable.setItems(WorkingProgressList);
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void loadData() {
        DatabaseConnection connectNow = new DatabaseConnection();
        connection = connectNow.getConnection();
        refreshWorkingView();
        employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        daysAbsent.setCellValueFactory(new PropertyValueFactory<>("totalAbsentDays"));
        halfDays.setCellValueFactory(new PropertyValueFactory<>("totalHalfDays"));
        paidOffDays.setCellValueFactory(new PropertyValueFactory<>("totalPaidDayOff"));
        overTimeDays.setCellValueFactory(new PropertyValueFactory<>("totalHalfDays"));
        fullDays.setCellValueFactory(new PropertyValueFactory<>("totalFullDays"));
        totalHours.setCellValueFactory(new PropertyValueFactory<>("totalHours"));
       // selectButton.setCellValueFactory(new PropertyValueFactory<> ("selectButton"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
