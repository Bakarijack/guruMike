package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AdmnLogisticDepartment {
    @FXML
    private BorderPane mainPane;


    public void logisticDepartmentOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminLogisticWorkersDepartment");
        mainPane.setCenter(view);
    }
}
