package degreeplanner.controller;

import java.io.IOException;

import degreeplanner.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class FacultyHomeController {

    @FXML
    private TableView<?> adviseeTable;

    @FXML
    private Pane facultyNotePane;

    @FXML
    private Label facultySearchCourse;

    @FXML
    private Label facultySearchUser;



    @FXML
    void goToProfile(MouseEvent event) throws IOException {
        App.setRoot("/fxml/facultyProfile");

    }

    @FXML
    void goToSearchCourse(MouseEvent event) throws IOException {

        App.setRoot("/fxml/search_course");

    }

    @FXML
    void goToSearchUser(MouseEvent event) throws IOException {

        App.setRoot("/fxml/searchUser");

    }

    @FXML
    void userLogout(MouseEvent event) {

    }

    @FXML
    void goToLogin(MouseEvent event) throws IOException {
        App.setRoot("/fxml/login");
    }

}