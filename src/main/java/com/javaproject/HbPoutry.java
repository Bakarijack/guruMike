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

public class HbPoutry<String> implements Initializable {

    @FXML
    private TableView<PoultryDetails> poultryCodeTable;

    @FXML
    private TableColumn<PoultryDetails, String> onStock;

    @FXML
    private TableColumn<PoultryDetails, String> poultryCode;

    @FXML
    private TableColumn<PoultryDetails, String> poultryType;


    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    PoultryDetails poultryDetails = null;

    ObservableList<PoultryDetails> PoultryList = FXCollections.observableArrayList();

    public void refreshTable(){
        try {
            PoultryList.clear();
            query = (String) "SELECT * FROM `poultry`";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                PoultryList.add(new PoultryDetails(
                        resultSet.getInt("poultry_code"),
                        resultSet.getString("poultry_type"),
                        resultSet.getInt("poultry_quantity")));
                poultryCodeTable.setItems(PoultryList);
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
        poultryCode.setCellValueFactory(new PropertyValueFactory<>("poultryId"));
        poultryType.setCellValueFactory(new PropertyValueFactory<>("poultryName"));
        onStock.setCellValueFactory(new PropertyValueFactory<>("poultryQuantity"));

    }



    public void updateButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader updateLoader = new FXMLLoader(getClass().getResource("poultryUpdate.fxml"));
        Parent root1 = (Parent) updateLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
        //stage.show();
    }

    public void removeButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader removeLoader = new FXMLLoader(getClass().getResource("removePoultry.fxml"));
        Parent root1 = (Parent) removeLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
       // stage.show();
    }

    public void addButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader removeLoader = new FXMLLoader(getClass().getResource("addPoultry.fxml"));
        Parent root1 = (Parent) removeLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
       // stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
