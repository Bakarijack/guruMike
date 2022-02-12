package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HeadOfBrooderchickenReport {
    @FXML
    private BorderPane mainPane;

    public void poultryLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("hbPoutry");
        mainPane.setCenter(view);
    }
    public void productionLinkOnAction(ActionEvent e){
        //load the menu screen
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("hbpoultryProduction");
        mainPane.setCenter(view);
    }

    public void othersLinkOnAction(ActionEvent e){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("hbPoultryOthers");
        mainPane.setCenter(view);
    }

    public void updateButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader updateLoader = new FXMLLoader(getClass().getResource("poultryUpdate.fxml"));
        Parent root1 = (Parent) updateLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void removeButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader removeLoader = new FXMLLoader(getClass().getResource("removePoultry.fxml"));
        Parent root1 = (Parent) removeLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void addButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader removeLoader = new FXMLLoader(getClass().getResource("addPoultry.fxml"));
        Parent root1 = (Parent) removeLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
    }
}
