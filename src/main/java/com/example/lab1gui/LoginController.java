package com.example.lab1gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    public TextField usernameInput;
    public PasswordField passwordInput;
    public Label loginMessage;
    public Button loginButton;
    public Button debugLoginButton;


    protected void changeScene(String fxmlFile){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            // Get the current stage
            Stage stage = (Stage) loginButton.getScene().getWindow();
            // Set the new scene
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            loginMessage.setText("Failed to load the main view: " + e.getMessage());
        }
    }

    protected boolean authenticateLogin(String email, String password) {
        String query = "SELECT * FROM login_DB WHERE email = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                loginMessage.setText("Invalid email or password.");
                loginMessage.setStyle("-fx-text-fill: red");
                return false;
            }
        } catch (SQLException e) {
            loginMessage.setText("Database Error: " + e.getMessage());
        }
        return false;
    }


    @FXML
    protected  void onLogin(){

        String username = usernameInput.getText();
        String password = passwordInput.getText();

        if (username.isEmpty() || password.isEmpty()){
            loginMessage.setText("Please fill in all the fields for login.");
        } else{

//            Authenticate with login_DB table which has cols [id] [name] [email] [password]

            boolean isAuthenticated = authenticateLogin(username, password);

            if (isAuthenticated){
                String scene = "/com/example/lab1gui/select-view.fxml";
                Stage stage = (Stage) loginButton.getScene().getWindow();
                SceneManager.changeScene(stage, scene, loginMessage);
            } else{

            }


        }
    }

    public void onDebugLogin(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/select-view.fxml";
        Stage stage = (Stage) loginButton.getScene().getWindow();
        SceneManager.changeScene(stage, scene, loginMessage);
    }
}