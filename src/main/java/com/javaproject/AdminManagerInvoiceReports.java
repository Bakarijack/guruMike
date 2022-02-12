package com.javaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AdminManagerInvoiceReports<String> implements Initializable {
    @FXML
    private TableColumn<InvoiceDetails, String> dateGenerated;

    @FXML
    private TableColumn<InvoiceDetails, String> invoiceCode;

    @FXML
    private TableView<InvoiceDetails> invoiceTable;

    @FXML
    private TableColumn<InvoiceDetails, String> orderCode;

    @FXML
    private TableColumn<InvoiceDetails, String> paymentTerms;

    @FXML
    private TableColumn<InvoiceDetails, String> sentStatus;

    @FXML
    private TableColumn<InvoiceDetails, String> totalCost;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<InvoiceDetails> InvoiceGeneratedList = FXCollections.observableArrayList();

    public void refreshTable(){
        try {
            InvoiceGeneratedList.clear();
            String query = (String) "SELECT invoices.invoice_code,invoices.order_code,invoices.invoice_date,payment_terms.payment_term,invoices.invoice_total,invoice_status.status_name FROM `invoices` JOIN invoice_status ON invoices.sentStatus = invoice_status.sentStatus JOIN payment_terms ON payment_terms.payment_terms_code = invoices.payment_terms_code";
            preparedStatement = connection.prepareStatement((java.lang.String) query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                InvoiceGeneratedList.add(new InvoiceDetails(
                        resultSet.getInt("invoice_code"),
                        resultSet.getString("order_code"),
                        resultSet.getString("invoice_date"),
                        resultSet.getString("payment_term"),
                        resultSet.getDouble("invoice_total"),
                        resultSet.getString("status_name")));
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
        dateGenerated.setCellValueFactory(new PropertyValueFactory<>("invoiceGeneratedDate"));
        paymentTerms.setCellValueFactory(new PropertyValueFactory<>("paymentTerms"));
        totalCost.setCellValueFactory(new PropertyValueFactory<>("totalInvoice"));
        sentStatus.setCellValueFactory(new PropertyValueFactory<>("sentStatus"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
