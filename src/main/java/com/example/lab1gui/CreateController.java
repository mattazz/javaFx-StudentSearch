package com.example.lab1gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateController {

    public TextField fName;
    public TextField lName;
    public TextField role;
    public TextField phoneNumber;
    public TextField gpa;
    public Label createMessage;
    public Button mainMenuBtn;

    public void submitRecord(ActionEvent actionEvent) {
        String firstName = fName.getText();
        String lastName = lName.getText();
        String userRole = role.getText();
        String phone = phoneNumber.getText();
        String gpaText = gpa.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || userRole.isEmpty() || phone.isEmpty() || gpaText.isEmpty()) {
            createMessage.setStyle("-fx-text-fill: red");
            createMessage.setText("All fields must be filled.");
            return;
        }

        float userGpa;
        try {
            userGpa = Float.parseFloat(gpaText);
        } catch (NumberFormatException e) {
            createMessage.setStyle("-fx-text-fill: red");
            createMessage.setText("Invalid GPA format.");
            return;
        }

        System.out.printf("CreateRecord: %s, %s, %s, %s, %f2", firstName, lastName, userRole, phone, userGpa);

        String insertQuery = "INSERT INTO person (givenName, LName, role, phoneNumber, GPA) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)){

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, userRole);
            preparedStatement.setString(4, phone);
            preparedStatement.setFloat(5, userGpa);

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