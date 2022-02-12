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

public class SaleProductAvailable<String>  implements Initializable {
    @FXML
    private TableColumn<EggsProducts, String> productCode;

    @FXML
    private TableColumn<EggsProducts, String> productName;

    @FXML
    private TableView<EggsProducts> productOnStockTable;

    @FXML
    private TableColumn<EggsProducts, String> pricePerEgg;

    @FXML
    private TableColumn<EggsProducts, String> pricePerTray;

    @FXML
    private TableColumn<EggsProducts, String> totalEggs;

    @FXML
    private TableColumn<EggsProducts, String> totalTrays;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    PoultryDetails poultryDetails = null;

    ObservableList<EggsProducts> ProductListAvailable = FXCollections.observableArrayList();

    public void refreshTable(){
        try {
            ProductListAvailable.clear();
            query = (String) "SELECT product_code,product_name,tray_quantity,egg_quantity,price_per_tray,price_per_egg FROM `products`";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ProductListAvailable.add(new EggsProducts(
                        resultSet.getString("product_code"),
                        resultSet.getString("product_name"),
                        resultSet.getInt("tray_quantity"),
                        resultSet.getInt("egg_quantity"),
                        resultSet.getDouble("price_per_tray"),
                        resultSet.getDouble("price_per_egg")));
                productOnStockTable.setItems(ProductListAvailable);
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
        totalTrays.setCellValueFactory(new PropertyValueFactory<>("trayQuantity"));
        totalEggs.setCellValueFactory(new PropertyValueFactory<>("eggsQuantity"));
        pricePerTray.setCellValueFactory(new PropertyValueFactory<>("pricePerTray"));
        pricePerEgg.setCellValueFactory(new PropertyValueFactory<>("pricePerEgg"));

    }


    public void orderButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader logInLoader = new FXMLLoader(getClass().getResource("saleProductAvailableOrder.fxml"));
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
