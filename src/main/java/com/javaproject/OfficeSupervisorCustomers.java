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

public class OfficeSupervisorCustomers<String> implements Initializable {
    @FXML
    private TableView<CustomerDetails> custmerListTable;

    @FXML
    private TableColumn<CustomerDetails, String> customerCode;

    @FXML
    private TableColumn<CustomerDetails, String> emailAddress;

    @FXML
    private TableColumn<CustomerDetails, String> firstName;

    @FXML
    private TableColumn<CustomerDetails, String> gender;

    @FXML
    private TableColumn<CustomerDetails, String> idNumber;

    @FXML
    private TableColumn<CustomerDetails, String> locationName;

    @FXML
    private TableColumn<CustomerDetails, String> phoneNumber;

    @FXML
    private TableColumn<CustomerDetails, String> secondName;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<CustomerDetails> CustomerDetailList = FXCollections.observableArrayList();

    public void refreshTable(){
        try {
            CustomerDetailList.clear();
            query = (String) "SELECT * FROM `customers`";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                CustomerDetailList.add(new CustomerDetails(
                        resultSet.getInt("cust_id"),
                        resultSet.getString("phone"),
                        resultSet.getString("locationN"),
                        resultSet.getString("gender"),
                        resultSet.getString("email"),
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName")));
                custmerListTable.setItems(CustomerDetailList);
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
        customerCode.setCellValueFactory(new PropertyValueFactory<>("customerCode"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        locationName.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        gender.setCellValueFactory(new PropertyValueFactory<>("genderName"));
        emailAddress.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        idNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        secondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));

    }

    public void customerAddOnAction(ActionEvent e) throws IOException {
        FXMLLoader logInLoader = new FXMLLoader(getClass().getResource("addCustomer.fxml"));
        Parent root1 = (Parent) logInLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
        //stage.show();
    }

    public void updateButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader logInLoader = new FXMLLoader(getClass().getResource("updateDetails.fxml"));
        Parent root1 = (Parent) logInLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
        //stage.show();
    }

    public void removeButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader logInLoader = new FXMLLoader(getClass().getResource("removeCustomer.fxml"));
        Parent root1 = (Parent) logInLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
        //stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
