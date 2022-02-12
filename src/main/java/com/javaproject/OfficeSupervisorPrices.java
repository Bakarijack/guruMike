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

public class OfficeSupervisorPrices<String> implements Initializable {
    @FXML
    private TableColumn<EggsProducts, String> pricePerEgg;

    @FXML
    private TableColumn<EggsProducts, String> pricePerTray;

    @FXML
    private TableView<EggsProducts> priceTable;

    @FXML
    private TableColumn<EggsProducts, String> productCode;

    @FXML
    private TableColumn<EggsProducts, String> productName;

    public void updateButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader updateLoader = new FXMLLoader(getClass().getResource("priceUpdate.fxml"));
        Parent root1 = (Parent) updateLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
        //stage.show();
    }

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    ObservableList<EggsProducts> ProductPriceList = FXCollections.observableArrayList();


    public void refreshTable(){
        try {
            ProductPriceList.clear();
            query = (String) "SELECT product_code,product_name,price_per_tray,price_per_egg FROM `products`";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ProductPriceList.add(new EggsProducts(
                        resultSet.getString("product_code"),
                        resultSet.getString("product_name"),
                        resultSet.getDouble("price_per_tray"),
                        resultSet.getDouble("price_per_egg")));
                priceTable.setItems(ProductPriceList);
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
        productCode.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        pricePerTray.setCellValueFactory(new PropertyValueFactory<>("pricePerTray"));
        pricePerEgg.setCellValueFactory(new PropertyValueFactory<>("pricePerEgg"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }



}
