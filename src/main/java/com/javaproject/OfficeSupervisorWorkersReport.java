package com.javaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class OfficeSupervisorWorkersReport<String> implements Initializable {

    @FXML
    private TableColumn<StaffProfile, String> emailAddress;

    @FXML
    private TableColumn<StaffProfile, String> employeeId;

    @FXML
    private TableColumn<StaffProfile, String> firstName;

    @FXML
    private TableColumn<StaffProfile, String> phoneNumber;

    @FXML
    private TableColumn<StaffProfile, String> secondName;

    @FXML
    private TableView<StaffProfile> workersTable;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    ObservableList<StaffProfile> StaffList = FXCollections.observableArrayList();


    public void fillTurnUpButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader logInLoader = new FXMLLoader(getClass().getResource("hoursDetails.fxml"));
        Parent root1 = (Parent) logInLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        // mainPane.setDisable(true);
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
        //stage.show();
    }



    public void refreshTable(){
        try {
            StaffList.clear();
            query = (String) ("SELECT emp_id,firstName,secondName,phone,email FROM `staff` WHERE dep_code= '"+StaffProfile.getDepartment_code()+"'");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                StaffList.add(new StaffProfile(
                        resultSet.getString("emp_id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")));
                workersTable.setItems(StaffList);
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void returnDepartmentCode(){
        try {
            StaffList.clear();
            query = (String) ("SELECT dep_code FROM `staff` WHERE username= '"+LogInController.userName+"' AND password = '"+LogInController.password+"'");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                StaffList.add(new StaffProfile(
                        resultSet.getString("dep_code")));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void loadData() {
        DatabaseConnection connectNow = new DatabaseConnection();
        connection = connectNow.getConnection();

        returnDepartmentCode();
        refreshTable();

        employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        secondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailAddress.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
