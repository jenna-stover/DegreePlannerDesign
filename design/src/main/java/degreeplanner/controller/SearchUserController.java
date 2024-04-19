package degreeplanner.controller;

import java.io.IOException;

import degreeplanner.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SearchUserController {

    @FXML
    private TextField courseIdTextField;

    @FXML
    private Text degreeplanner_search;

    @FXML
    private Button logout_button;

    @FXML
    private VBox searchCourseVBox;

    @FXML
    private Button search_button_by_id;

    @FXML
    private Pane search_pane;

    @FXML
    private Label user_fullname;

    @FXML
    private ImageView user_profile;

    @FXML
    void goHome(MouseEvent event) throws IOException {
      App.setRoot("/fxml/facultyHome");
    }

    @FXML
    void goToProfile(MouseEvent event) throws IOException {
      App.setRoot("/fxml/facultyProfile");
    }

    @FXML
    void searchUserById(MouseEvent event) {
      
    }

    @FXML
    void userLogout(MouseEvent event) {

    }

}
