package com.example.lab1gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import com.example.lab1gui.DatabaseConnection;
import com.example.lab1gui.Student;

public class HelloController {
    @FXML
    private TextField studentSearch;
    @FXML
    private Label noticeMessage;
    @FXML
    private Label welcomeText;
    @FXML
    private Label connectionStatusMessage;
    @FXML
    private TableView<Student> studentTable; //Student class type is important  -> See initialization
    @FXML
    private TableColumn<Student, String> columnGivenName;
    @FXML
    private TableColumn<Student, String> columnLastName;
    @FXML
    private TableColumn<Student, String> columnPhoneNumber;
    @FXML
    private TableColumn<Student, Float> columnGPA;

    // This stores the properties of the student for later displaying in columns. Stored as student object
    @FXML
    private ObservableList<Student> students = FXCollections.observableArrayList();

    @FXML
    public void initialize(){ //this basically connects the student prop getters to the column values
        columnGivenName.setCellValueFactory(new PropertyValueFactory<>("givenName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columnGPA.setCellValueFactory(new PropertyValueFactory<>("gpa"));
    }

    @FXML
    protected void onSubmitSearch() {
        noticeMessage.setText(String.format("Message: %s", studentSearch.getText()));

        String searchQuery = studentSearch.getText();

        try (Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement()){
            String query = String.format("SELECT givenName, LName, phoneNumber, GPA FROM person WHERE givenName LIKE '%%%s%%'", searchQuery); // search not implemented yet
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Connection: " + statement);
            connectionStatusMessage.setText("Connection active: Student_DB");

            students.clear();
            boolean studentFound = false;

            while (resultSet.next()){
                //Loops and adds the props to the students
                students.add(new Student(resultSet.getString("givenName"), resultSet.getString("LName"), resultSet.getString("phoneNumber"),resultSet.getFloat("GPA") ));
                studentFound = true;
            }

            if (studentFound) {
                noticeMessage.setText("Student/s found.");
            } else {
                noticeMessage.setText("No student found.");
            }

            // adds all the students into the rows
            studentTable.setItems(students);
        } catch (SQLException e){
            noticeMessage.setText("Database Error: " + e.getMessage());
        }
    }
}