package degreeplanner.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import degreeplanner.App;
import degreeplanner.design_code.HomeFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class FacultyHomeController implements Initializable{

    @FXML
    private TableView<?> adviseeTable;

    @FXML
    private Pane facultyNotePane;

    @FXML
    private Label facultySearchCourse;

    @FXML
    private Label facultySearchUser;

    @FXML
    private Button logout_button;

    @FXML
    private Label profileFullName;

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
    void userLogout(MouseEvent event) throws IOException {
        App.setRoot("/fxml/facultyLogin");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        HomeFacade homeFacade = HomeFacade.getInstance();
        if(homeFacade.getLoggedInUser() != null)
        {
            profileFullName.setText(homeFacade.getLoggedInUser().getUserFullName());
        }
    }

}