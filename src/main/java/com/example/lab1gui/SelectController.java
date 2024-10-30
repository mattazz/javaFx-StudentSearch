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
    public Button searchCourseBtn;
    public Button createCourseBtn;
    public Button updateCourseBtn;
    public Button deleteCourseBtn;


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

    public void goToSearchCourse(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/course-search-view.fxml";
        Stage stage = (Stage) searchViewBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, selectMessage);
    }

    public void goToCreateCourse(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/course-create-view.fxml";
        Stage stage = (Stage) searchViewBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, selectMessage);
    }

    public void goToUpdateCourse(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/course-update-view.fxml";
        Stage stage = (Stage) searchViewBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, selectMessage);
    }

    public void goToDeleteCourse(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/course-delete-view.fxml";
        Stage stage = (Stage) searchViewBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, selectMessage);
    }
}