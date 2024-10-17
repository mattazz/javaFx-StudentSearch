package com.example.lab1gui;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SelectController {

    public Label selectMessage;
    public Button searchViewBtn;


    public void goToSearchView(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/search-view.fxml";
        Stage stage = (Stage) searchViewBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, selectMessage );
    }
}