package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerView implements Initializable {
    @FXML
    private VBox chosenEggCard;

    @FXML
    private ImageView eggImage;

    @FXML
    private HBox changes;

    @FXML
    private Label eggNameLabel;

    @FXML
    private Label eggPriceLabel;

    @FXML
    private GridPane grid;

    @FXML
    private Hyperlink myCartLabel;

    @FXML
    private ScrollPane scroll;


    private List<Eggs> eggs = new ArrayList<>();

    private Image image;

    private List<Eggs> getData(){
        List<Eggs> eggs = new ArrayList<>();
        Eggs egg;

            egg = new Eggs();
            egg.setName("Brown Eggs");
            egg.setPrice2(320);
            egg.setIgmSrc("egg.jpg");
            egg.setColor("6A7324");
            eggs.add(egg);

            egg = new Eggs();
            egg.setName("Ostrich eggs");
            egg.setPrice2(370);
            egg.setIgmSrc("4.jpg");
            egg.setColor("6A7324");
            eggs.add(egg);

            egg = new Eggs();
            egg.setName("White Eggs");
            egg.setPrice2(330);
            egg.setIgmSrc("egg.jpg");
            egg.setColor("6A7324");
            eggs.add(egg);

            egg = new Eggs();
            egg.setName("Duck Eggs");
            egg.setPrice2(340);
            egg.setIgmSrc("4.jpg");
            egg.setColor("6A7324");
            eggs.add(egg);

            egg = new Eggs();
            egg.setName("Kienyeji Eggs");
            egg.setPrice2(390);
            egg.setIgmSrc("4.jpg");
            egg.setColor("6A7324");
            eggs.add(egg);


        return eggs;
    }

    private void setChossenEgg(Eggs egg){
        eggNameLabel.setText(egg.getName());
        eggPriceLabel.setText(Main.CURRENCY + egg.getPrice2());
        image = new Image(getClass().getResourceAsStream(egg.getIgmSrc()));
        eggImage.setImage(image);
        chosenEggCard.setStyle("-fx-background-color: #"+egg.getColor()+";\n" +
                "    -fx-background-radius: 30px;");
    }

    public void mycartOnAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) myCartLabel.getScene().getWindow();
        stage.close();

        //opens the signUp window
        FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("cartView1.fxml"));
        Parent root1 = (Parent) customerViewLoader.load();
        Stage stage2 = new Stage();

        stage2.setScene(new Scene(root1));
        stage2.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eggs.addAll(getData());

        if (eggs.size() > 0){
            setChossenEgg(eggs.get(0));
        }
        int column = 0;
        int row = 1;


        try {
            for (int i = 0; i < eggs.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("items.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemsController itemController = fxmlLoader.getController();
                itemController.setData(eggs.get(i));

                if (column == 3){
                    column = 0;
                    row++;
                }

                grid.add(anchorPane,column++,row);  //(child,column, row)

                //get grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);


                GridPane.setMargin(anchorPane,new Insets(10));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
