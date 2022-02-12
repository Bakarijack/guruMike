package com.javaproject;

import com.mysql.cj.conf.IntegerProperty;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdministrationManager {
    @FXML
    private BorderPane mainPane;

    @FXML
    private Hyperlink payrolLink;

    @FXML
    private Hyperlink logout;


    public void linkOnAction(ActionEvent e){
            Stage stage = (Stage) logout.getScene().getWindow();
            stage.close();

    }

    //methods
    public void mainLinkOnAction(ActionEvent e){
        //load the menu screen
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminManagerMenu");
        mainPane.setCenter(view);
    }

    public void accountLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminManagerAccounts");
        mainPane.setCenter(view);
    }
    public void scheduleLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminManagerSchedule");
        mainPane.setCenter(view);
    }

    public void profileLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorProfile");
        mainPane.setCenter(view);
    }

    public void helpLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminManagerHelp");
        mainPane.setCenter(view);
    }

    public void cutomerseportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorCustomers");
        mainPane.setCenter(view);
    }
    public void productionReportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminManagerProductionReports");
        mainPane.setCenter(view);
    }
    public void deliveryReportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminManagerDeliveryReport");
        mainPane.setCenter(view);
    }
    public void otherLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminMangerOthers");
        mainPane.setCenter(view);
    }

    public void workersReportsLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminManagerWorkersReport");
        mainPane.setCenter(view);
    }

    public void invoiceReportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminManagerInvoiceReports");
        mainPane.setCenter(view);
    }

    public void departmentsReportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminManagerDepartmentReports");
        mainPane.setCenter(view);
    }

    public void payrollSystemReportLinkOnAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) payrolLink.getScene().getWindow();
        stage.close();

        //opens the new window
        FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("officeSupervisorPayroll.fxml"));
        Parent root1 = (Parent) customerViewLoader.load();
        Stage stage2 = new Stage();

        stage2.setScene(new Scene(root1));
        stage2.show();
    }


}
