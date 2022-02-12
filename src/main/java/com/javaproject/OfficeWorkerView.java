package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OfficeWorkerView {

    @FXML
    private BorderPane mainPane;

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
        Pane view = object.getPage("officeWorkerHome");
        mainPane.setCenter(view);
    }

    public void accountLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminManagerAccounts");
        mainPane.setCenter(view);
    }
    public void scheduleLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeWorkerSchedule");
        mainPane.setCenter(view);
    }

   // OfficeSupervisorProfile profile = new OfficeSupervisorProfile();
    public void profileLinkOnAction(ActionEvent e){
        //profile.showDetails();
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorProfile");
        mainPane.setCenter(view);
    }

    public void helpLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeWorkerHelp");
        mainPane.setCenter(view);
    }

    public void cutomerseportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorCustomers");
        mainPane.setCenter(view);
    }
    public void reportseportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeWorkerReports");
        mainPane.setCenter(view);
    }
    public void DeliveryReportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeWorkerDeliveryReport");
        mainPane.setCenter(view);
    }
    public void otherLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeWorkerOthers");
        mainPane.setCenter(view);
    }

    public void salesLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeWorkerSales");
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
