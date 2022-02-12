package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AdmnOfficeDepartment {
    @FXML
    private BorderPane mainPane;

    public void officeDepartmentOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("adminOfficeWorkersDepartment");
        mainPane.setCenter(view);
    }
}
