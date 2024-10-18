package com.example.lab1gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DeleteController {
    public Button deleteBtn;
    public TextField recordIDInput;
    public Label deleteMessage;
    public Button mainMenuBtn;

    public void onDeletePressed(ActionEvent actionEvent) {
        String recordID = recordIDInput.getText();

        if (recordID.isEmpty()) {
            deleteMessage.setStyle("-fx-text-fill: red");
            deleteMessage.setText("Please input a record id.");
            return;
        }

        System.out.println("Request to delete record ID: " + recordID);

        String selectQuery = "SELECT givenName, LName FROM person WHERE personId = ?";
        String deleteQuery = "DELETE FROM person WHERE personId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
             PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {

            // Retrieve the record's name
            selectStatement.setString(1, recordID);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String givenName = resultSet.getString("givenName");
                String lastName = resultSet.getString("LName");

                // Proceed with deletion
                deleteStatement.setString(1, recordID);
                int rowsAffected = deleteStatement.executeUpdate();
                System.out.println("Delete: Rows affected: " + rowsAffected);

                if (rowsAffected > 0) {
                    deleteMessage.setStyle("-fx-text-fill: green");
                    deleteMessage.setText("Record deleted successfully: " + givenName + " " + lastName);
                } else {
                    deleteMessage.setStyle("-fx-text-fill: red");
                    deleteMessage.setText("Failed to delete record. Please check your record ID.");
                }
            } else {
                deleteMessage.setStyle("-fx-text-fill: red");
                deleteMessage.setText("Record not found. Please check your record ID.");
            }
        } catch (SQLException e) {
            deleteMessage.setStyle("-fx-text-fill: red");
            deleteMessage.setText("Database Error: " + e);
        }
    }

    public void backToMain(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/select-view.fxml";
        Stage stage = (Stage) deleteBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, deleteMessage);
    }
}