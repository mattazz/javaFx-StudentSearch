package com.example.lab1gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CourseUpdateController {


    public TextField recordID;
    public TextField courseTitle;
    public TextField courseDesc;
    public Label updateMessage;
    public Button mainMenuBtn;

    public void backToMain(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/select-view.fxml";
        Stage stage = (Stage) mainMenuBtn.getScene().getWindow();
        SceneManager.changeScene(stage, scene, updateMessage);
    }

    public void submitUpdate(ActionEvent actionEvent) {
        String courseTitleText = courseTitle.getText();
        String courseDescText = courseDesc.getText();
        String recordId = recordID.getText();

        StringBuilder queryBuilder = new StringBuilder("UPDATE course SET ");
        List<Object> parameters = new ArrayList<>();

        if (!courseTitleText.isEmpty()){
            queryBuilder.append("courseTitle=?, ");
            parameters.add(courseTitleText);
        }
        if (!courseDescText.isEmpty()) {
            queryBuilder.append("description=?, ");
            parameters.add(courseDescText);
        }

        if(parameters.isEmpty()){
            updateMessage.setText("No fields to update");
            return;
        }

//        Remove ", " form the last sequence of queryBuilder
        queryBuilder.setLength(queryBuilder.length() - 2);
        queryBuilder.append(" WHERE courseId=?");
        parameters.add(recordId);

        System.out.println("SQL Statement for Update: " + queryBuilder.toString());
        System.out.printf("UpdateRecord: %s, %s", courseTitleText, courseDescText);

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())){
            for (int i = 0; i < parameters.size(); i++){
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Update: Rows affected: " + rowsAffected);

            if(rowsAffected > 0){
                updateMessage.setStyle("-fx-text-fill: green");
                updateMessage.setText("Record updated successfully");
            } else{
                updateMessage.setStyle("-fx-text-fill: red");

                updateMessage.setText("Failed to update record.");
            }
        } catch (SQLException e){
            updateMessage.setText("Database Error: " + e);
            System.out.println("Database Error: " + e);
        }
    }
}