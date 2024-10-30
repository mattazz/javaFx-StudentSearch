package com.example.lab1gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CourseSearchController {
    public Button mainMenuBtn;
    @FXML
    private TextField courseSearch;
    @FXML
    private Label noticeMessage;
    @FXML
    private Label connectionStatusMessage;
    @FXML
    private TableView<Course> courseTable; //Student class type is important  -> See initialization
    @FXML
    private TableColumn<Course, String> columnCourseTitle;
    @FXML
    private TableColumn<Course, String> columnDescription;
    @FXML
    private TableColumn<Course, String> columnRecordId;

    // This stores the properties of the student for later displaying in columns. Stored as student object
    @FXML
    private ObservableList<Course> courses = FXCollections.observableArrayList();

    @FXML
    public void initialize() { //this basically connects the student prop getters to the column values
        columnCourseTitle.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));
        columnRecordId.setCellValueFactory(new PropertyValueFactory<>("recordId"));
    }

    @FXML
    protected void onSubmitSearch() {
        noticeMessage.setText(String.format("Message: %s", courseSearch.getText()));

        String searchQuery = courseSearch.getText();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String query = String.format("SELECT courseTitle, description, courseId FROM course WHERE courseTitle LIKE '%%%s%%'", searchQuery); // search not implemented yet
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Connection: " + statement);
            connectionStatusMessage.setStyle("-fx-text-fill: green");
            connectionStatusMessage.setText("Connection active: Student_DB");

            courses.clear();
            boolean studentFound = false;

            while (resultSet.next()) {
                //Loops and adds the props to the students
                courses.add(new Course(resultSet.getString("courseTitle"), resultSet.getString("description"), resultSet.getString("courseId")));
                studentFound = true;
            }

            if (studentFound) {
                noticeMessage.setStyle("-fx-text-fill: green");
                noticeMessage.setText("Course/s found.");
            } else {
                noticeMessage.setStyle("-fx-text-fill: red");
                noticeMessage.setText("No Course found.");
            }

            // adds all the students into the rows
            courseTable.setItems(courses);
        } catch (SQLException e) {
            noticeMessage.setStyle("-fx-text-fill: red");
            noticeMessage.setText("Database Error: " + e.getMessage());
        }
    }

    public void backToMain(ActionEvent actionEvent) {
        String scene = "/com/example/lab1gui/select-view.fxml";
        Stage stage = (Stage) mainMenuBtn.getScene().getWindow();

        SceneManager.changeScene(stage, scene, noticeMessage);
    }
}