package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class OfficeWorkerSales {
    @FXML
    private BorderPane mainPane;

    public void productAvailableLinkOnAction(ActionEvent e){
        //load the menu screen
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("saleProductAvailable");
        mainPane.setCenter(view);
    }

    public void notificationLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("saleNotifications");
        mainPane.setCenter(view);
    }
    public void othersLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("saleOthers");
        mainPane.setCenter(view);
    }
}
