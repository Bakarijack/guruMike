package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class OfficeSupervisorOthers {
    @FXML
    private BorderPane mainPane;

    public void priceLinkOnAction(ActionEvent e){
        //load the menu screen
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorPrices");
        mainPane.setCenter(view);
    }
    public void expenditureLinkOnAction(ActionEvent e){
        //load the menu screen
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("officeSupervisorExpenditure");
        mainPane.setCenter(view);
    }
}
