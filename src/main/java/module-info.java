module com.example.lab1gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.lab1gui to javafx.fxml;
    exports com.example.lab1gui;
}