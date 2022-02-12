package com.javaproject;

import com.mysql.cj.protocol.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaleProductAvailableOrder {
    @FXML
    private TextField customerCode;

    @FXML
    private Label customerCodeMessage;

    @FXML
    private TextField orderCode;

    @FXML
    private Label orderCodeMessage;

    @FXML
    private Label placeOrderMessage;

    @FXML
    private TextField productCode;

    @FXML
    private Label productCodeMessage;

    @FXML
    private Label eggsQuantityMessage;

    @FXML
    private TextField trayQuantity;

    @FXML
    private Label trayQuantityMessage;

    @FXML
    private TextField eggsQuantity;

    @FXML
    private CheckBox notPaid;

    @FXML
    private CheckBox paid;

    private int paymentChoice;

    public void choiceBoxOnAction(ActionEvent e){
        if(paid.isSelected()){
            paymentChoice = 1;
            notPaid.setDisable(true);
        }else if(notPaid.isSelected()){
            paymentChoice = 2;
            paid.setDisable(true);
        }else {
            paid.setDisable(false);
            notPaid.setDisable(false);
        }
    }

    //creating database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();



    public boolean validateCustomerCode(){
        String verify = "SELECT COUNT(1) FROM `customers` WHERE cust_id = '"+customerCode.getText()+"'";

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
        String s = eggsQuantity.getText();

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
        String s = trayQuantity.getText();

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

    public boolean validateOrderCodeAndCustomerCode(){
        String verify = "SELECT COUNT(1) FROM orders WHERE order_code = '"+orderCode.getText()+"' AND cust_id = '"+customerCode.getText()+"'";

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

    public void insertOrderInOrderTable(){
        String insert = "INSERT INTO `orders`(`order_code`, `cust_id`) VALUES ('"+orderCode.getText()+"','"+customerCode.getText()+"')";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insert);
            placeOrderMessage.setText("Added successfully");
            productCodeMessage.setText("");
            customerCodeMessage.setText("");
            orderCodeMessage.setText("");
            trayQuantityMessage.setText("");
            eggsQuantityMessage.setText("");

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public String currentDateMethod(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }

    public String currentTimeMethod(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        return  time;
    }

    public void insertOrderDetails(){
        String time = currentTimeMethod();
        String date = currentDateMethod();
        String insert = "INSERT INTO `orderDetails`(`order_code`, `product_code`, `ordered_trays`,`ordered_eggs`, `order_time`, `order_date`) VALUES ('"+orderCode.getText()+
                "','"+productCode.getText()+"','"+trayQuantity.getText()+"','"+eggsQuantity.getText()+"','"+time+"','"+date+"')";


        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insert);
            placeOrderMessage.setText("Added successfully");
            productCodeMessage.setText("");
            customerCodeMessage.setText("");
            orderCodeMessage.setText("");
            trayQuantityMessage.setText("");
            eggsQuantityMessage.setText("");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public boolean validateTrayQuantity(){
        String trayNumber = "SELECT tray_quantity FROM `products` WHERE product_code ='"+productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(trayNumber);

            while(queryResult.next()) {
                if (queryResult.getInt("tray_quantity") >= Integer.parseInt(trayQuantity.getText())) {
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

    public  void updatingEggs(){
        String eggsUpdate = "UPDATE `products` SET `egg_quantity`= egg_quantity - '"+Integer.parseInt(eggsQuantity.getText())+"' WHERE product_code = '"+productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(eggsUpdate);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void calculateAndUpdateCost(){
        String updateQuery = "UPDATE orderDetails,products SET orderDetails.cost = ((orderDetails.ordered_trays * products.price_per_tray)+(orderDetails.ordered_eggs * products.price_per_egg)) WHERE orderDetails.product_code =  '"+
                productCode.getText()+"'  AND orderDetails.product_code=products.product_code AND orderDetails.order_code= '"+orderCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateQuery);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void updateTrays(){
        String eggsUpdate = "UPDATE `products` SET `tray_quantity`= tray_quantity - '"+Integer.parseInt(trayQuantity.getText())+"' WHERE product_code = '"+productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(eggsUpdate);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean validateEggs(){
        //int eggs = Integer.parseInt(trayQuantity.getText());
        String eggsNumber = "SELECT egg_quantity FROM `products` WHERE product_code ='"+productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(eggsNumber);

            if(Integer.parseInt(eggsQuantity.getText()) > 29){
                return false;
            }
            while(queryResult.next()) {
                if (queryResult.getInt("egg_quantity") < Integer.parseInt(eggsQuantity.getText()) ){
                        if (checkTrays() == true) {
                            addEggs();
                            subtractTrays();
                            return true;
                        } else {
                            return false;
                        }
                } else if(queryResult.getInt("egg_quantity") > Integer.parseInt(eggsQuantity.getText())){
                    return true;
                }else {
                    return  false;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public boolean checkTrays(){
        String trayNumber = "SELECT tray_quantity FROM `products` WHERE product_code ='"+productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(trayNumber);

            while(queryResult.next()) {
                if (queryResult.getInt("tray_quantity") >= 1) {
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

    public void subtractTrays(){
        String eggsUpdate = "UPDATE `products` SET `tray_quantity`= tray_quantity - '"+1+"' WHERE product_code = '"+productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(eggsUpdate);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addEggs(){
        String eggsUpdate = "UPDATE `products` SET `egg_quantity`= egg_quantity + '"+30+"' WHERE product_code = '"+productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(eggsUpdate);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updatePaymentStatus(){
        String paymentStatus = "UPDATE `orderDetails` SET `payment_status_code`='"+paymentChoice+"' WHERE order_code = '"+orderCode.getText()+"'";


        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(paymentStatus);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public boolean validateIfOrderAlreadyPlaced(){
        String verify = "SELECT COUNT(1) FROM orderDetails WHERE order_code = '"+orderCode.getText()+"' AND product_code = '"+
                productCode.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verify);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    if(validateOrderCodeAndCustomerCode() == true) {
                        return true;
                    }else{
                        return false;
                    }
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

    public void placeOderButtonOnAction(ActionEvent e){
        if(!customerCode.getText().isBlank() && !orderCode.getText().isBlank() && !productCode.getText().isBlank() && !trayQuantity.getText().isBlank() && !eggsQuantity.getText().isBlank()){
           if(paid.isSelected() || notPaid.isSelected()) {
               if (validateCustomerCode() == true) {
                   if (validateProductCode() == true) {
                       if (validateOrderCode() == false) {
                           if (quantityCheckTray() == true) {
                               if (validateTrayQuantity() == true) {
                                   if (quantityCheckEggs() == true) {
                                       if (validateEggs() == true) {
                                           updateTrays();
                                           updatingEggs();
                                           insertOrderInOrderTable();
                                           insertOrderDetails();
                                           updatePaymentStatus();
                                           calculateAndUpdateCost();
                                           productCodeMessage.setText("");
                                           customerCodeMessage.setText("");
                                           placeOrderMessage.setText("Order Successfully");
                                           orderCodeMessage.setText("");
                                           eggsQuantityMessage.setText("");
                                           trayQuantityMessage.setText("");
                                       } else if (checkTrays() == false && validateEggs() == false) {
                                           eggsQuantityMessage.setText("Number exceeds the stock");
                                           trayQuantityMessage.setText("");
                                           productCodeMessage.setText("");
                                           customerCodeMessage.setText("");
                                           placeOrderMessage.setText("");
                                           orderCodeMessage.setText("");
                                       } else {

                                           eggsQuantityMessage.setText("Choose a number below 30");
                                           trayQuantityMessage.setText("");
                                           productCodeMessage.setText("");
                                           customerCodeMessage.setText("");
                                           placeOrderMessage.setText("");
                                           orderCodeMessage.setText("");
                                       }
                                   } else {
                                       eggsQuantityMessage.setText("Integer value is required");
                                       trayQuantityMessage.setText("");
                                       productCodeMessage.setText("");
                                       customerCodeMessage.setText("");
                                       placeOrderMessage.setText("");
                                       orderCodeMessage.setText("");
                                   }
                               } else {
                                   trayQuantityMessage.setText("Quantity exceeds available stock");
                                   eggsQuantityMessage.setText("");
                                   productCodeMessage.setText("");
                                   customerCodeMessage.setText("");
                                   placeOrderMessage.setText("");
                                   orderCodeMessage.setText("");
                               }
                           } else {
                               trayQuantityMessage.setText("Integer value is required");
                               eggsQuantityMessage.setText("");
                               productCodeMessage.setText("");
                               customerCodeMessage.setText("");
                               placeOrderMessage.setText("");
                               orderCodeMessage.setText("");
                           }
                       } else if (validateOrderCode() == true && validateOrderCodeAndCustomerCode() == true) {
                           if (validateIfOrderAlreadyPlaced() == false) {
                               if (quantityCheckTray() == true) {
                                   if (validateTrayQuantity() == true) {
                                       if (quantityCheckEggs() == true) {
                                           if (validateEggs() == true) {
                                               updateTrays();
                                               updatingEggs();
                                               insertOrderDetails();
                                               updatePaymentStatus();
                                               calculateAndUpdateCost();
                                               productCodeMessage.setText("");
                                               customerCodeMessage.setText("");
                                               placeOrderMessage.setText("Order Successfully");
                                               orderCodeMessage.setText("");
                                               eggsQuantityMessage.setText("");
                                               trayQuantityMessage.setText("");
                                           } else {
                                               eggsQuantityMessage.setText("Choose a number below 30");
                                               trayQuantityMessage.setText("");
                                               productCodeMessage.setText("");
                                               customerCodeMessage.setText("");
                                               placeOrderMessage.setText("");
                                               orderCodeMessage.setText("");
                                           }
                                       } else {
                                           eggsQuantityMessage.setText("Integer value is required");
                                           trayQuantityMessage.setText("");
                                           productCodeMessage.setText("");
                                           customerCodeMessage.setText("");
                                           placeOrderMessage.setText("");
                                           orderCodeMessage.setText("");
                                       }
                                   } else {
                                       trayQuantityMessage.setText("Quantity exceeds available stock");
                                       eggsQuantityMessage.setText("");
                                       productCodeMessage.setText("");
                                       customerCodeMessage.setText("");
                                       placeOrderMessage.setText("");
                                       orderCodeMessage.setText("");
                                   }
                               } else {
                                   trayQuantityMessage.setText("Integer value is required");
                                   productCodeMessage.setText("");
                                   customerCodeMessage.setText("");
                                   placeOrderMessage.setText("");
                                   orderCodeMessage.setText("");
                                   eggsQuantityMessage.setText("");
                               }
                           } else {
                               placeOrderMessage.setText("Same order for the same person exist");
                               productCodeMessage.setText("");
                               customerCodeMessage.setText("");
                               orderCodeMessage.setText("");
                               trayQuantityMessage.setText("");
                               eggsQuantityMessage.setText("");
                           }
                       } else {
                           orderCodeMessage.setText("Order code already exist");
                           productCodeMessage.setText("");
                           customerCodeMessage.setText("");
                           placeOrderMessage.setText("");
                           trayQuantityMessage.setText("");
                           eggsQuantityMessage.setText("");
                       }

                   } else {
                       productCodeMessage.setText("Product not available");
                       customerCodeMessage.setText("");
                       placeOrderMessage.setText("");
                       orderCodeMessage.setText("");
                       trayQuantityMessage.setText("");
                       eggsQuantityMessage.setText("");
                   }

               } else {
                   customerCodeMessage.setText("Customer not found");
                   placeOrderMessage.setText("");
                   orderCodeMessage.setText("");
                   trayQuantityMessage.setText("");
                   productCodeMessage.setText("");
                   eggsQuantityMessage.setText("");
               }
           }else {
               placeOrderMessage.setText("Select paid status choice box");
               customerCodeMessage.setText("");
               orderCodeMessage.setText("");
               trayQuantityMessage.setText("");
               productCodeMessage.setText("");
               eggsQuantityMessage.setText("");

           }

        }else {
            placeOrderMessage.setText("Fill all the text fields");
            customerCodeMessage.setText("");
            orderCodeMessage.setText("");
            trayQuantityMessage.setText("");
            productCodeMessage.setText("");
            eggsQuantityMessage.setText("");
        }
    }
}
