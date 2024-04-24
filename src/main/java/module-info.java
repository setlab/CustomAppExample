module com.tytenko.customappexample {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tytenko.customappexample to javafx.fxml;
    exports com.tytenko.customappexample;
}