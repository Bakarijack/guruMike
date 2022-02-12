package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AdmnProductionDepartment {
    @FXML
    private BorderPane mainPane;


    public void productionDepartmentOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminProductionWorkersDepartment");
        mainPane.setCenter(view);
    }
}
