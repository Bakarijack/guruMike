package com.javaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class OfficeSupervisorProfile<String> implements Initializable {
    @FXML
    private Label emailAddress;

    @FXML
    private Label employeeNumber;

    @FXML
    private Label jobRole;

    @FXML
    private Label phoneNumber;

    @FXML
    private Label userName;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<StaffProfile> StaffdetailsList = FXCollections.observableArrayList();

    StaffProfile staffProfile;
    public void refreshTable(){
        try {
            StaffdetailsList.clear();
            String query = (String) ("SELECT staff.username,role.role,staff.emp_id,staff.phone,staff.email,staff.dep_code FROM staff JOIN role ON staff.role_nu=role.role_nu WHERE staff.username = '"+LogInController.userName+"' AND staff.password = '"+LogInController.password+"'");
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                StaffdetailsList.add( staffProfile = new StaffProfile(
                        resultSet.getString("username"),
                        resultSet.getString("role"),
                        resultSet.getString("emp_id"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("dep_code")
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
        refreshTable();
        userName.setText(staffProfile.getUsername());
        jobRole.setText(staffProfile.getJobRole());
        employeeNumber.setText(staffProfile.getEmployeeNumber());
        phoneNumber.setText(staffProfile.getPhoneNumber());
        emailAddress.setText(staffProfile.getEmailAddress());


    }


    public OfficeSupervisorProfile(){};
   /* public void showDetails(){
        ;
        int roleId = LogInController.roleNumber;
        String details = (String) ("SELECT staff.username,role.role,staff.emp_id,staff.phone,staff.email FROM staff JOIN role ON staff.role_nu=role.role_nu WHERE staff.role_nu = '"+roleId+"'");

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(details);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1) {

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}