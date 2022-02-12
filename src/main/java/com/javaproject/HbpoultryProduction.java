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

public class HbpoultryProduction<String> implements Initializable {
    @FXML
    private TableColumn<EggsProducts, String> poultryCode;

    @FXML
    private TableColumn<EggsProducts, String> productCode;

    @FXML
    private TableColumn<EggsProducts, String> productName;

    @FXML
    private TableView<EggsProducts> productionTable;

    @FXML
    private TableColumn<EggsProducts, String> quantity;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    PoultryDetails poultryDetails = null;

    ObservableList<EggsProducts> ProductList = FXCollections.observableArrayList();


    public void refreshTable(){
        try {
            ProductList.clear();
            query = (String) "SELECT poultry_code,product_code,product_name,product_quantity FROM `products`";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ProductList.add(new EggsProducts(
                        resultSet.getInt("poultry_code"),
                        resultSet.getString("product_code"),
                        resultSet.getString("product_name"),
                        resultSet.getInt("product_quantity")));
                productionTable.setItems(ProductList);
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
        poultryCode.setCellValueFactory(new PropertyValueFactory<>("poultryCode"));
        productCode.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));

    }

    public void addButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader removeLoader = new FXMLLoader(getClass().getResource("hbAddProduct.fxml"));
        Parent root1 = (Parent) removeLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
        //stage.show();
    }

    public void updateButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader updateLoader = new FXMLLoader(getClass().getResource("hbUpdateProduct.fxml"));
        Parent root1 = (Parent) updateLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
        //stage.show();
    }

    public void removeButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader removeLoader = new FXMLLoader(getClass().getResource("hbRemoveProduct.fxml"));
        Parent root1 = (Parent) removeLoader.load();
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
