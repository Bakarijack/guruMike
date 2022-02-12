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

public class ReviseOrder {
    @FXML
    private TextField eggsOrdered;

    @FXML
    private Label eggsOrderedMessage;

    @FXML
    private TextField orderCode;

    @FXML
    private Label orderCodeMessage;

    @FXML
    private TextField productCode;

    @FXML
    private Label productCodeMessage;

    @FXML
    private TextField trayOrdered;

    @FXML
    private Label trayOrderedMessage;

    @FXML
    private Button closeButton;

    //creating database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();


    public void closeButtonOnAction(ActionEvent e){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public boolean validateOrderCode(){
        String verify = "SELECT COUNT(1) FROM orders WHERE order_code = '"+orderCode.getText()+"'";

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

    public boolean quantityCheckEggs(){
        String s = eggsOrdered.getText();

        if(s == null){
            return false;
        }

        try{
            int i = Integer.parseInt(s);
        }catch (NumberFormatException e){
            return false;
        }

        return true;
    }

    public boolean quantityCheckTray(){
        String s = trayOrdered.getText();

        if(s == null){
            return false;
        }

        try{
            int i = Integer.parseInt(s);
        }catch (NumberFormatException e){
            return false;
        }

        return true;
    }

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

    public boolean validateIfInvoiceAlreadyGenerated(){
        String verify = "SELECT COUNT(1) FROM orderDetails WHERE order_code = '"+orderCode.getText()+"' AND invoice_generated_status = 1";

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

    public boolean validateIfOrderAlreadyPlaced(){
        String verify = "SELECT COUNT(1) FROM orderDetails WHERE order_code = '"+orderCode.getText()+"' AND product_code = '"+
                productCode.getText()+"'";

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

    public int previousOrderedTrays(){
        int preTrays = 0;
        String  trays = "SELECT ordered_trays FROM `orderDetails` WHERE order_code = '"+orderCode.getText()+"' AND product_code = '"+productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(trays);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    preTrays = queryResult.getInt("ordered_trays");
                    return preTrays;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return preTrays;
    }

    public void updateTraysToOriginalNumber(){
        String updateTrays = "UPDATE `products` SET `tray_quantity`='"+previousOrderedTrays()+"' WHERE product_code = '"+productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateTrays);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean validateTrayQuantity(){
        updateTraysToOriginalNumber();
        String trayNumber = "SELECT tray_quantity FROM `products` WHERE product_code ='"+productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(trayNumber);

            while(queryResult.next()) {
                if (queryResult.getInt("tray_quantity") >= Integer.parseInt(trayOrdered.getText())) {
                    return true;
                } else {
                    return false;
                    //codeMessage.setText("Invalid Code!");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void updateTrays(){
        String eggsUpdate = "UPDATE `products` SET `tray_quantity`= tray_quantity - '"+Integer.parseInt(trayOrdered.getText())+"' WHERE product_code = '"+productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(eggsUpdate);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateOrderedDetailsForTray(){
        String updateDetails = "UPDATE `orderDetails` SET `ordered_trays`='"+trayOrdered.getText()+"' WHERE order_code = '"+orderCode.getText()+"'  AND product_code = '"+productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateDetails);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void trayButtonOnAction(ActionEvent evt){
        if(!orderCode.getText().isBlank()){
            if(!productCode.getText().isBlank()){
                if(!trayOrdered.getText().isBlank()){
                    if(validateOrderCode() == true){
                        if(validateIfInvoiceAlreadyGenerated() == false){
                            if (validateProductCode() ==  true) {
                                if (validateIfOrderAlreadyPlaced() == true) {
                                    if(quantityCheckTray() == true){
                                        if(validateTrayQuantity() == true){
                                            updateTrays();
                                            updateOrderedDetailsForTray();
                                            trayOrderedMessage.setText("Successfully Updated");
                                            orderCodeMessage.setText("");
                                            productCodeMessage.setText("");
                                            eggsOrderedMessage.setText("");


                                        }else{
                                            trayOrderedMessage.setText("Quantity exceeds the stock");
                                            orderCodeMessage.setText("");
                                            productCodeMessage.setText("");
                                            eggsOrderedMessage.setText("");
                                        }

                                    }else{
                                        trayOrderedMessage.setText("Integer number is required");
                                        orderCodeMessage.setText("");
                                        productCodeMessage.setText("");
                                        eggsOrderedMessage.setText("");
                                    }

                                } else {
                                    trayOrderedMessage.setText("");
                                    orderCodeMessage.setText("");
                                    productCodeMessage.setText("Order for the product not made");
                                    eggsOrderedMessage.setText("");
                                }
                            }else {
                                trayOrderedMessage.setText("");
                                orderCodeMessage.setText("");
                                productCodeMessage.setText("product does not exist");
                                eggsOrderedMessage.setText("");
                            }

                        }else {
                            trayOrderedMessage.setText("");
                            orderCodeMessage.setText("Error.Invoice already generated");
                            productCodeMessage.setText("");
                            eggsOrderedMessage.setText("");
                        }

                    }else{
                        trayOrderedMessage.setText("");
                        orderCodeMessage.setText("Order code does not exist");
                        productCodeMessage.setText("");
                        eggsOrderedMessage.setText("");
                    }

                }else{
                    trayOrderedMessage.setText("Fill the text fields first");
                    orderCodeMessage.setText("");
                    productCodeMessage.setText("");
                    eggsOrderedMessage.setText("");
                }

            }else{
                productCodeMessage.setText("Fill the text fiels first");
                orderCodeMessage.setText("");
                trayOrderedMessage.setText("");
                eggsOrderedMessage.setText("");
            }

        }else{
            orderCodeMessage.setText("Fill the text field first");
            productCodeMessage.setText("");
            trayOrderedMessage.setText("");
            eggsOrderedMessage.setText("");
        }
    }
}
