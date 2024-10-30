package com.example.lab1gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseCreateController {

    public Label createMessage;
    public Button mainMenuBtn;
    public TextField courseTitle;
    public TextField courseDescription;

    public void submitRecord(ActionEvent actionEvent) {
        String courseTitleText = courseTitle.getText();
        String courseDescriptionText = courseDescription.getText();


        if (courseTitleText.isEmpty() || courseDescriptionText.isEmpty()) {
            createMessage.setStyle("-fx-text-fill: red");
            createMessage.setText("All fields must be filled.");
            return;
        }

        System.out.printf("CreateRecord: %s, %s", courseTitleText, courseDescriptionText);

        String insertQuery = "INSERT INTO course (courseTitle, description) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)){

            preparedStatement.setString(1, courseTitleText);
            preparedStatement.setString(2, courseDescriptionText);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Create : Rows affected: " + rowsAffected);

            if (rowsAffected > 0){
                createMessage.setStyle("-fx-text-fill: green");
                createMessage.setText("Record created successfully.");
            } else{
                createMessage.setStyle("-fx-text-fill: red");
                 createMessage.setText("Failed to create record. ");
            }
        } catch (SQLException e) {
            createMessage.setStyle("-fx-text-fill: red");
            createMessage.setText("Database Error: " + e);
        } ;

    }

    public void backToMain(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/select-view.fxml";
        Stage stage = (Stage) mainMenuBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, createMessage);
    }
}