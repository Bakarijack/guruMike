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

public class OfficeWorkerInvoice<String> implements Initializable {
    @FXML
    private TableColumn<InvoiceDetails, String> invoiceCode;

    @FXML
    private TableColumn<InvoiceDetails, String> invoiceGenerateDate;

    @FXML
    private TableColumn<InvoiceDetails, String> invoiceGenerateTme;

    @FXML
    private TableView<InvoiceDetails> invoiceTable;

    @FXML
    private TableColumn<InvoiceDetails, String> invoiceTotal;

    @FXML
    private TableColumn<InvoiceDetails, String> orderCode;

    @FXML
    private TableColumn<InvoiceDetails, String> paymentTerms;

    public void viewButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader logInLoader = new FXMLLoader(getClass().getResource("invoiceView.fxml"));
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

    ObservableList<InvoiceDetails> InvoiceGeneratedList = FXCollections.observableArrayList();

    public void refreshTable(){
        try {
            InvoiceGeneratedList.clear();
            query = (String) "SELECT invoices.invoice_code,invoices.order_code,invoices.invoice_total,payment_terms.payment_term,invoices.invoice_time,invoices.invoice_date FROM invoices JOIN payment_terms ON invoices.payment_terms_code = payment_terms.payment_terms_code WHERE `invoices`.`printed` = 0 AND  `invoices`.`sentStatus` = 0";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                InvoiceGeneratedList.add(new InvoiceDetails(
                        resultSet.getInt("invoice_code"),
                        resultSet.getString("order_code"),
                        resultSet.getDouble("invoice_total"),
                        resultSet.getString("payment_term"),
                        resultSet.getString("invoice_time"),
                        resultSet.getString("invoice_date")));
                invoiceTable.setItems(InvoiceGeneratedList);
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
        invoiceCode.setCellValueFactory(new PropertyValueFactory<>("invoiceId"));
        orderCode.setCellValueFactory(new PropertyValueFactory<>("orderCode"));
        invoiceTotal.setCellValueFactory(new PropertyValueFactory<>("totalInvoice"));
        paymentTerms.setCellValueFactory(new PropertyValueFactory<>("paymentTerms"));
        invoiceGenerateTme.setCellValueFactory(new PropertyValueFactory<>("invoiceGeneratedTime"));
        invoiceGenerateDate.setCellValueFactory(new PropertyValueFactory<>("invoiceGeneratedDate"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
