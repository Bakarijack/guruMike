package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PriceUpdate {
    @FXML
    private TextField pricePerEgg;

    @FXML
    private Label pricePerEggMessage;

    @FXML
    private TextField pricePerTray;

    @FXML
    private Label pricePerTrayMessage;

    @FXML
    private TextField productCode;

    @FXML
    private Label productCodeMessage;

    @FXML
    private Button closeButton;

    public void closeButtonOnAction(ActionEvent e){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    public boolean validateProductCode(){
        String verify = "SELECT COUNT(1) FROM products WHERE product_code = '"+productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verify);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    return true;
                }else{
                    return false;
                    //codeMessage.setText("Invalid Code!");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean validateTrayPrices(){
            String s = pricePerTray.getText();

            if(s == null){
                return false;
            }

            try{
                double d = Double.parseDouble(s);
            }catch (NumberFormatException e){
                return false;
            }

            return true;
    }

    public boolean validateEggPrices(){
            String s = pricePerEgg.getText();

            if(s == null){
                return false;
            }

            try{
                double d = Double.parseDouble(s);
            }catch (NumberFormatException e){
                return false;
            }

            return true;
    }

    public void updateTrayPrice(ActionEvent e){
        if(!productCode.getText().isBlank()){
            if(validateProductCode() == true) {
                if (!pricePerTray.getText().isBlank()) {
                    if(validateTrayPrices() == true){
                        String updateQuery = "UPDATE products SET price_per_tray='" + pricePerTray.getText() + "' WHERE product_code = '" + productCode.getText() + "'";

                        try {
                            Statement statement = connectDB.createStatement();
                            statement.executeUpdate(updateQuery);
                            pricePerTrayMessage.setText("Successfully Updated!");
                            pricePerEggMessage.setText("");
                            productCodeMessage.setText("");

                        } catch (Exception event) {
                            event.printStackTrace();
                        }

                    }else{
                        pricePerTrayMessage.setText("A number is required");
                        pricePerEggMessage.setText("");
                        productCodeMessage.setText("");
                    }

                } else {
                    pricePerTrayMessage.setText("Fill the text field first");
                    productCodeMessage.setText("");
                    pricePerEggMessage.setText("");
                }
            }else{
                productCodeMessage.setText("Invalid code");
                pricePerTrayMessage.setText("");
                pricePerEggMessage.setText("");
            }
        }else{
            productCodeMessage.setText("Enter the product code");
            pricePerTrayMessage.setText("");
            pricePerEggMessage.setText("");
        }
    }

    public void updateEggPrice(ActionEvent e){
        if(!productCode.getText().isBlank()){
            if(validateProductCode() == true) {
                if (!pricePerEgg.getText().isBlank()) {
                    if(validateEggPrices() == true){
                        String updateQuery = "UPDATE products SET price_per_egg='" + pricePerEgg.getText() + "' WHERE product_code = '" + productCode.getText() + "'";

                        try {
                            Statement statement = connectDB.createStatement();
                            statement.executeUpdate(updateQuery);
                            pricePerTrayMessage.setText("");
                            pricePerEggMessage.setText("Successfully Updated");
                            productCodeMessage.setText("");

                        } catch (Exception event) {
                            event.printStackTrace();
                        }

                    }else{
                        pricePerTrayMessage.setText("");
                        pricePerEggMessage.setText("A number is required");
                        productCodeMessage.setText("");
                    }

                } else {
                    pricePerTrayMessage.setText("");
                    productCodeMessage.setText("");
                    pricePerEggMessage.setText("Fill the text field first");
                }
            }else{
                productCodeMessage.setText("Invalid code");
                pricePerTrayMessage.setText("");
                pricePerEggMessage.setText("");
            }
        }else{
            productCodeMessage.setText("Enter the product code");
            pricePerTrayMessage.setText("");
            pricePerEggMessage.setText("");
        }
    }


}
