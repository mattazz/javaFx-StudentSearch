package com.example.lab1gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateController {


    public TextField fName;
    public TextField lName;
    public TextField role;
    public TextField phoneNumber;
    public TextField gpa;
    public Label createMessage;
    public Button mainMenuBtn;

    public void submitRecord(ActionEvent actionEvent) {

    }

    public void backToMain(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/select-view.fxml";
        Stage stage = (Stage) mainMenuBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, createMessage);
    }
}