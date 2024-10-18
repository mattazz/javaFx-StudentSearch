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


public class UpdateController {


    public TextField recordID;
    public TextField fName;
    public TextField lName;
    public TextField role;
    public TextField phoneNumber;
    public TextField gpa;
    public Label updateMessage;
    public Button mainMenuBtn;

    public void backToMain(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/select-view.fxml";
        Stage stage = (Stage) mainMenuBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, updateMessage);
    }

    public void submitUpdate(ActionEvent actionEvent) {
        String firstName = fName.getText();
        String lastName = lName.getText();
        String userRole = role.getText();
        String phone = phoneNumber.getText();
        String recordId = recordID.getText();
        String gpaText = gpa.getText();

        StringBuilder queryBuilder = new StringBuilder("UPDATE person SET ");
        List<Object> parameters = new ArrayList<>();

        if (!firstName.isEmpty()){
            queryBuilder.append("givenName=?, ");
            parameters.add(firstName);
        }
        if (!lastName.isEmpty()) {
            queryBuilder.append("LName=?, ");
            parameters.add(lastName);
        }
        if (!userRole.isEmpty()) {
            queryBuilder.append("role=?, ");
            parameters.add(userRole);
        }
        if (!phone.isEmpty()) {
            queryBuilder.append("phoneNumber=?, ");
            parameters.add(phone);
        }
        if (!gpaText.isEmpty()) {
            try {
                float userGpa = Float.parseFloat(gpaText);
                queryBuilder.append("GPA=?, ");
                parameters.add(userGpa);
            } catch (NumberFormatException e) {
                updateMessage.setText("Invalid GPA format.");
                return;
            }
        }

        if(parameters.isEmpty()){
            updateMessage.setText("No fields to update");
            return;
        }

//        Remove ", " form the last sequence of queryBuilder
        queryBuilder.setLength(queryBuilder.length() - 2);
        queryBuilder.append(" WHERE personId=?");
        parameters.add(recordId);

        System.out.println("SQL Statement for Update: " + queryBuilder.toString());
        System.out.printf("UpdateRecord: %s, %s, %s, %s, %s", firstName, lastName, userRole, phone, gpaText);

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