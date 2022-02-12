package com.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogInController {

    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Label loginMessageLabel;

    //----------------------profile section-------------------//////////


    static int roleNumber;
    static String userName;
    static String password;


    public int getRoleNumber() {
        return roleNumber;
    }

    //action the cancel button
    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    //action the login button
    public void loginButtonOnAction(ActionEvent e) throws IOException {
        if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()){
            //loginMessageLabel.setText("you try to logIn");
            validateLogin();


        }
        else{
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    //validate the details with the database data
    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verfyLogin = "SELECT count(1) FROM staff WHERE Username = '" + usernameField.getText() +
                "' AND password='" + passwordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verfyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    String verfyLogin1 = "SELECT role_nu FROM staff WHERE Username = '" + usernameField.getText() +
                            "' AND password='" + passwordField.getText() + "'";
                    try{
                        Statement statement1 = connectDB.createStatement();
                        ResultSet queryResult1 = statement1.executeQuery(verfyLogin1);

                        while(queryResult1.next()) {
                            if (queryResult1.getInt("role_nu") == 1) {
                                roleNumber = 1;
                                userName = usernameField.getText();
                                password = passwordField.getText();
                                Stage stage = (Stage) loginButton.getScene().getWindow();
                                stage.close();

                                //opens the new window
                                FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("adminView.fxml"));
                                Parent root1 = (Parent) customerViewLoader.load();
                                Stage stage2 = new Stage();

                                stage2.setScene(new Scene(root1));
                                stage2.show();
                            } else if (queryResult1.getInt("role_nu") == 2){
                                roleNumber=2;
                                userName = usernameField.getText();
                                password = passwordField.getText();
                                Stage stage = (Stage) loginButton.getScene().getWindow();
                                stage.close();

                                //opens the new window
                                FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("headOfBroodersView.fxml"));
                                Parent root1 = (Parent) customerViewLoader.load();
                                Stage stage2 = new Stage();

                                stage2.setScene(new Scene(root1));
                                stage2.show();
                            }else if (queryResult1.getInt("role_nu") == 3){
                                roleNumber=3;
                                userName = usernameField.getText();
                                password = passwordField.getText();
                                Stage stage = (Stage) loginButton.getScene().getWindow();
                                stage.close();

                                //opens the new window
                                FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("packingManagerView.fxml"));
                                Parent root1 = (Parent) customerViewLoader.load();
                                Stage stage2 = new Stage();

                                stage2.setScene(new Scene(root1));
                                stage2.show();
                            }else if(queryResult1.getInt("role_nu") == 4){
                                roleNumber=4;
                                userName = usernameField.getText();
                                password = passwordField.getText();
                                Stage stage = (Stage) loginButton.getScene().getWindow();
                                stage.close();

                                //opens the new window
                                FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("logisticManagerView.fxml"));
                                Parent root1 = (Parent) customerViewLoader.load();
                                Stage stage2 = new Stage();

                                stage2.setScene(new Scene(root1));
                                stage2.show();
                            }else if(queryResult1.getInt("role_nu") == 5){
                                roleNumber = 5;
                                userName = usernameField.getText();
                                password = passwordField.getText();
                                Stage stage = (Stage) loginButton.getScene().getWindow();
                                stage.close();

                                //opens the new window
                                FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("officeSuppervisorView.fxml"));
                                Parent root1 = (Parent) customerViewLoader.load();
                                Stage stage2 = new Stage();

                                stage2.setScene(new Scene(root1));
                                stage2.show();
                            }else if(queryResult1.getInt("role_nu") == 6){
                                roleNumber =6;
                                userName = usernameField.getText();
                                password = passwordField.getText();
                                Stage stage = (Stage) loginButton.getScene().getWindow();
                                stage.close();

                                //opens the new window
                                FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("productionManagerView.fxml"));
                                Parent root1 = (Parent) customerViewLoader.load();
                                Stage stage2 = new Stage();

                                stage2.setScene(new Scene(root1));
                                stage2.show();
                            }else if(queryResult1.getInt("role_nu") == 7){
                                roleNumber=7;
                                userName = usernameField.getText();
                                password = passwordField.getText();
                                Stage stage = (Stage) loginButton.getScene().getWindow();
                                stage.close();

                                //opens the new window
                                FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("officeWorkerView.fxml"));
                                Parent root1 = (Parent) customerViewLoader.load();
                                Stage stage2 = new Stage();

                                stage2.setScene(new Scene(root1));
                                stage2.show();
                            }else if(queryResult1.getInt("role_nu") == 8){
                                roleNumber = 8;
                                userName = usernameField.getText();
                                password = passwordField.getText();
                                Stage stage = (Stage) loginButton.getScene().getWindow();
                                stage.close();

                                //opens the new window
                                FXMLLoader customerViewLoader = new FXMLLoader(getClass().getResource("workerView.fxml"));
                                Parent root1 = (Parent) customerViewLoader.load();
                                Stage stage2 = new Stage();

                                stage2.setScene(new Scene(root1));
                                stage2.show();
                            }else{
                                loginMessageLabel.setText("Invalid login. Please try again.");
                            }
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }else{
                    loginMessageLabel.setText("Invalid login.Please try again.");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
