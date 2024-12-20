package com.example.lab1gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneManager {
    public static void changeScene(Stage stage, String fxmlFile, Label errorMessageLabel){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SceneManager.class.getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 800, 600);
            // Load and apply the external CSS file
            String css = Objects.requireNonNull(SceneManager.class.getResource("/com/example/lab1gui/styles.css")).toExternalForm();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            if (errorMessageLabel != null){
                errorMessageLabel.setText("Failed to load the scene");
            }
        }
    }
}
