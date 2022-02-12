module com.test.testfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.test.testfx to javafx.fxml;
    exports com.test.testfx;
}