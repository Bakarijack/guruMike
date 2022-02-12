package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LogisticManager {
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
        Pane view = object.getPage("logisticManagerMenu");
        mainPane.setCenter(view);
    }

    public void accountLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminManagerAccounts");
        mainPane.setCenter(view);
    }
    public void scheduleLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("logisticManagerSchedule");
        mainPane.setCenter(view);
    }

    public void profileLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorProfile");
        mainPane.setCenter(view);
    }

    public void helpLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("logisticManagerHelp");
        mainPane.setCenter(view);
    }

    public void workersReportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorWorkersReport");
        mainPane.setCenter(view);
    }
    public void viheclesReportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("logisticManagerWorkersReport");
        mainPane.setCenter(view);
    }
    public void DeliveryReportLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("logisticManagerDeliveryReports");
        mainPane.setCenter(view);
    }
    public void otherLinkOnAction(ActionEvent e) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("logisticManagerOthers");
        mainPane.setCenter(view);
    }
}
