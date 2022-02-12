package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AdmnBrooderDepartment {
    @FXML
    private BorderPane mainPane;


    public void brooderDepartmentOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminBrooderWorkersDepartment");
        mainPane.setCenter(view);
    }
}
