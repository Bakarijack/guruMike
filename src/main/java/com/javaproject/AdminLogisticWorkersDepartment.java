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

public class AdminLogisticWorkersDepartment<String> implements Initializable {

    @FXML
    private TableColumn<StaffProfile2, String> dateRecruited;

    @FXML
    private TableColumn<StaffProfile2, String> employeeId;

    @FXML
    private TableColumn<StaffProfile2, String> gender;

    @FXML
    private TableColumn<StaffProfile2, String> location;

    @FXML
    private TableColumn<StaffProfile2, String> name;

    @FXML
    private TableView<StaffProfile2> officeWorkersTable;

    @FXML
    private TableColumn<StaffProfile2, String> role;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void recruitButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader logInLoader = new FXMLLoader(getClass().getResource("logisticWorkerRecruit.fxml"));
        Parent root1 = (Parent) logInLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        // mainPane.setDisable(true);
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
        //stage.show();
    }

    ObservableList<StaffProfile2> StaffList = FXCollections.observableArrayList();

    public void refreshTable(){
        try {
            StaffList.clear();
            String query = (String) "SELECT staff.emp_id,staff.firstName,staff.secondName,staff.gender,role.role,staff.location,staff.date_recruited FROM `staff` JOIN role ON staff.role_nu = role.role_nu WHERE staff.dep_code ='LG16'";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                StaffList.add(new StaffProfile2(
                        resultSet.getString("emp_id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getString("gender"),
                        resultSet.getString("role"),
                        resultSet.getString("location"),
                        resultSet.getString("date_recruited")));
                officeWorkersTable.setItems(StaffList);
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

        employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
        name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dateRecruited.setCellValueFactory(new PropertyValueFactory<>("dateRecruited"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        role.setCellValueFactory(new PropertyValueFactory<>("jobRole"));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
