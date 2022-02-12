package com.javaproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemsController {
    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemName;

   // @FXML
   // private Label forprice1;

    @FXML
    private Label forprice2;

    private Eggs egg;


    public void setData(Eggs egg){
        this.egg = egg;
        itemName.setText(egg.getName());
       // forprice1.setText(Main.CURRENCY + egg.getPrice1() );
        forprice2.setText(Main.CURRENCY+ egg.getPrice2());

        Image image = new Image(getClass().getResourceAsStream(egg.getIgmSrc()));
        itemImage.setImage(image);
    }
}
