package com.example.lab1gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label loginMessage;
    @FXML
    private Button loginButton;
    @FXML
    private Button debugLoginButton;
    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/lab1gui/logo.png")));
            imageView.setImage(image);
        } catch (NullPointerException e) {
            System.err.println("Image file not found: " + e.getMessage());
        }
    }

    @FXML
    protected void changeScene(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
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
    protected void onLogin() {
        String username = usernameInput.getText();
        String password = passwordInput.getText();

        if (username.isEmpty() || password.isEmpty()) {
            loginMessage.setStyle("-fx-text-fill: red");
            loginMessage.setText("Please fill in all the fields for login.");
        } else {
            boolean isAuthenticated = authenticateLogin(username, password);

            if (isAuthenticated) {
                String scene = "/com/example/lab1gui/select-view.fxml";
                Stage stage = (Stage) loginButton.getScene().getWindow();
                SceneManager.changeScene(stage, scene, loginMessage);
            }
        }
    }

    @FXML
    public void onDebugLogin(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/select-view.fxml";
        Stage stage = (Stage) loginButton.getScene().getWindow();
        SceneManager.changeScene(stage, scene, loginMessage);
    }
}