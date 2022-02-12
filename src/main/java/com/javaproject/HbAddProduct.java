package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HbAddProduct {
    @FXML
    private Label addMessage;

    @FXML
    private TextField pQuantity;

    @FXML
    private Label pQuantityMessage;

    @FXML
    private TextField poultryCode;

    @FXML
    private Label poultryCodeMessage;

    @FXML
    private TextField productCode;

    @FXML
    private Label productCodeMessage;

    @FXML
    private TextField productName;

    @FXML
    private Label productNameMessage;

    //create database connection
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDb = connectNow.getConnection();

    public void addProduct(){
        EggsProducts product = new EggsProducts();

        product.setProductId(productCode.getText());
        product.setProductName(productName.getText());
        product.setProductQuantity(Integer.parseInt(pQuantity.getText()));
        product.setPoultryCode(Integer.parseInt(poultryCode.getText()));
        product.setTrayQuantity(Integer.parseInt(pQuantity.getText()));
        product.setEggsQuantity(Integer.parseInt(pQuantity.getText()));



        String insert = "INSERT INTO products (`product_name`, `product_code`, `product_quantity`, `poultry_code`,`tray_quantity`,`egg_quantity`) VALUES ('"+
                product.getProductName()+"','"+product.getProductId()+"','"+product.getProductQuantity()+"','"+product.getPoultryCode()+"','"+
                product.getTrayQuantity()+"','"+product.getEggsQuantity()+"')";

        try{
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insert);
            addMessage.setText("Added successfully");
            poultryCodeMessage.setText("");
            productNameMessage.setText("");
            pQuantityMessage.setText("");
            productCodeMessage.setText("");


        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void addButtonOnAction(ActionEvent e){
        if(!poultryCode.getText().isBlank() && !productCode.getText().isBlank() && !productName.getText().isBlank() && !pQuantity.getText().isBlank()){
            if(quantityCheck() == true){
                if(validatePoultryCode() == true){
                    if(validateProductCode() == false) {
                        if(validateProductName() == false) {
                            addProduct();
                        }else{
                            productNameMessage.setText("Name Already Exist");
                            addMessage.setText("");
                            poultryCodeMessage.setText("");
                            pQuantityMessage.setText("");
                            productCodeMessage.setText("");
                        }
                    }else {
                        productCodeMessage.setText("Code Already Exist");
                        addMessage.setText("");
                        poultryCodeMessage.setText("");
                        productNameMessage.setText("");
                        pQuantityMessage.setText("");
                    }
                }else{
                    poultryCodeMessage.setText("Poultry cannot be found");
                    addMessage.setText("");
                    productNameMessage.setText("");
                    pQuantityMessage.setText("");
                    productCodeMessage.setText("");
                }
            }else {
                pQuantityMessage.setText("Integer value is required");
                addMessage.setText("");
                poultryCodeMessage.setText("");
                productNameMessage.setText("");
                productCodeMessage.setText("");
            }

        }else {
            addMessage.setText("Fill the text field first");
            poultryCodeMessage.setText("");
            productNameMessage.setText("");
            pQuantityMessage.setText("");
            productCodeMessage.setText("");
        }
    }

    public boolean validatePoultryCode(){
        String verify = "SELECT COUNT(1) FROM `poultry` WHERE poultry_code = '"+poultryCode.getText()+"'";

        try{
            Statement statement = connectDb.createStatement();
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

    public boolean quantityCheck(){
        String s = pQuantity.getText();

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
            Statement statement = connectDb.createStatement();
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

    public boolean validateProductName(){
        String verify = "SELECT COUNT(1) FROM products WHERE product_name = '"+productName.getText()+"'";

        try{
            Statement statement = connectDb.createStatement();
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
}
