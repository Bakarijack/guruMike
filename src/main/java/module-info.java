module com.example.javaproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires itextpdf;


    opens com.javaproject to javafx.fxml;
    exports com.javaproject;
}