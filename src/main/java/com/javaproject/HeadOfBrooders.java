package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HeadOfBrooders {
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
        Pane view = object.getPage("headOfBrooderMenu");
        mainPane.setCenter(view);
    }

    public void accountLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminManagerAccounts");
        mainPane.setCenter(view);
    }
    public void scheduleLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("headOfBrooderSchedule");
        mainPane.setCenter(view);
    }

    public void profileLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorProfile");
        mainPane.setCenter(view);
    }

    public void helpLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("headOfBrooderHelp");
        mainPane.setCenter(view);
    }

    public void chickenReportLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("headOfBrooderchickenReport");
        mainPane.setCenter(view);
    }

    public void feedsReportLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("headOfBrooderFeedReport");
        mainPane.setCenter(view);
    }

    public void workersReportLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorWorkersReport");
        mainPane.setCenter(view);
    }

    public void currentOrdersLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("headOfBrooderCurrentOrder");
        mainPane.setCenter(view);
    }

    public void productionReportLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("headOfBrooderProductionReport");
        mainPane.setCenter(view);
    }

    public void othersLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("headfBrooderOthers");
        mainPane.setCenter(view);
    }

}
