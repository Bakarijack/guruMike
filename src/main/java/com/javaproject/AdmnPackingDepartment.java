package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AdmnPackingDepartment {
    @FXML
    private BorderPane mainPane;


    public void packingDepartmentOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminPackingWorkersDepartment");
        mainPane.setCenter(view);
    }
}
