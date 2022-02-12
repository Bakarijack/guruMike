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

public class OfficeWorkerOrder<String> implements Initializable {

    @FXML
    private TableColumn<OrdersView, String> customerNames;

    @FXML
    private TableColumn<OrdersView, String> paymentStatus;

    @FXML
    private TableColumn<OrdersView, String> numberOfEggs;

    @FXML
    private TableColumn<OrdersView, String> numberOfTrays;

    @FXML
    private TableColumn<OrdersView, String> orderCode;

    @FXML
    private TableColumn<OrdersView, String> orderDate;

    @FXML
    private TableView<OrdersView> orderTable;

    @FXML
    private TableColumn<OrdersView, String> productCode;

    @FXML
    private TableColumn<OrdersView, String> productName;

    public void reviseOrderButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader logInLoader = new FXMLLoader(getClass().getResource("reviseOrder.fxml"));
        Parent root1 = (Parent) logInLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        // mainPane.setDisable(true);
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
        //stage.show();
    }

    public void invoiceButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader logInLoader = new FXMLLoader(getClass().getResource("invoiceGenerate.fxml"));
        Parent root1 = (Parent) logInLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        // mainPane.setDisable(true);
        stage.initModality(Modality.APPLICATION_MODAL);  //prevent closing of the main window while the popup is still on
        stage.showAndWait();
        //stage.show();
    }

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<OrdersView> OrderViewList = FXCollections.observableArrayList();

    public void refreshTable(){
        try {
            OrderViewList.clear();
            query = (String) "SELECT orders.order_code,customers.firstName,customers.secondName,products.product_code,products.product_name,orderDetails.ordered_trays,orderDetails.ordered_eggs,orderDetails.order_date,payment_on_order_status.payment_status FROM `customers` JOIN orders ON customers.cust_id = orders.cust_id JOIN orderDetails ON orders.order_code = orderDetails.order_code JOIN products ON products.product_code = orderDetails.product_code JOIN payment_on_order_status ON orderDetails.payment_status_code = payment_on_order_status.payment_status_code  WHERE orderDetails.invoice_generated_status =0";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                OrderViewList.add(new OrdersView(
                        resultSet.getString("order_code"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getString("product_code"),
                        resultSet.getString("product_name"),
                        resultSet.getInt("ordered_trays"),
                        resultSet.getInt("ordered_eggs"),
                        resultSet.getString("order_date"),
                        resultSet.getString("payment_status")));
                orderTable.setItems(OrderViewList);
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
        orderCode.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        customerNames.setCellValueFactory(new PropertyValueFactory<>("customerNames"));
        productCode.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        numberOfTrays.setCellValueFactory(new PropertyValueFactory<>("numberOfTrays"));
        numberOfEggs.setCellValueFactory(new PropertyValueFactory<>("numberOfEggs"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        paymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
