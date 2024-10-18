package com.example.lab1gui;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SelectController {

    public Label selectMessage;
    public Button searchViewBtn;
    public Button createViewBtn;
    public Button deleteViewBtn;
    public Button updateViewBtn;
    public Button logOut;


    public void goToSearchView(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/search-view.fxml";
        Stage stage = (Stage) searchViewBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, selectMessage );
    }

    public void goToCreateView(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/create-view.fxml";
        Stage stage = (Stage) searchViewBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, selectMessage );
    }

    public void goToDeleteView(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/delete-view.fxml";
        Stage stage = (Stage) searchViewBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, selectMessage );
    }

    public void goToUpdateView(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/update-view.fxml";
        Stage stage = (Stage) searchViewBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, selectMessage );
    }

    public void onLogOutClick(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/login-view.fxml";
        Stage stage = (Stage) searchViewBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, selectMessage );
    }
}