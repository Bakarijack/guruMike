package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class OfficeSupervisor {
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
        Pane view = object.getPage("officeSupervisorMenu");
        mainPane.setCenter(view);
    }

    public void accountLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminManagerAccounts");
        mainPane.setCenter(view);
    }
    public void scheduleLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorSchedule");
        mainPane.setCenter(view);

    }

    public void payrollSystemLinkOnAction(ActionEvent e) throws IOException {
        //load the menu screen
        /*FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorPayroll");
        mainPane.setCenter(view);*/

        Stage stage = (Stage) payrolLink.getScene().getWindow();
        stage.close();

        //opens the new window
        FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("officeSupervisorPayroll.fxml"));
        Parent root1 = (Parent) customerViewLoader.load();
        Stage stage2 = new Stage();

        stage2.setScene(new Scene(root1));
        stage2.show();
    }

    //OfficeSupervisorProfile officeSupervisorProfile = new OfficeSupervisorProfile();
    public void profileLinkOnAction(ActionEvent e){
        //officeSupervisorProfile.showDetails();
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorProfile");
        mainPane.setCenter(view);
    }

    public void helpLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorHelp");
        mainPane.setCenter(view);
    }

    public void cutomerseportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorCustomers");
        mainPane.setCenter(view);
    }

    public void DeliveryReportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorDeliveryReport");
        mainPane.setCenter(view);
    }
    public void otherLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorOthers");
        mainPane.setCenter(view);
    }

   /* public void officeAssetsLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorAssets");
        mainPane.setCenter(view);
    }*/

    public void salesLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeWorkerSales");
        mainPane.setCenter(view);
    }

    public void workersReportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorWorkersReport");
        mainPane.setCenter(view);
    }

    public void invoicesLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeWorkerInvoice");
        mainPane.setCenter(view);
    }

    public void ordersLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeWorkerOrder");
        mainPane.setCenter(view);
    }

}
