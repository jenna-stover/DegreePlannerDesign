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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class StudentAdvNotesController implements Initializable {

    @FXML
    private ImageView adv_notes;

    @FXML
    private ImageView avatar_profile;

    @FXML
    private Text degreeplanner_home;

    @FXML
    private Button logout_button;

    @FXML
    private ImageView search_course;

    @FXML
    private Label user_name_profile;

    @FXML
    void avatarProfileClicked(MouseEvent event) throws IOException {
        App.setRoot("/fxml/profile");
    }

    @FXML
    void studentLogout(MouseEvent event) throws IOException {
        App.setRoot("/fxml/login");
    }

    @FXML
    void goHome(MouseEvent event) throws IOException {
        App.setRoot("/fxml/home");
    }

    @FXML
    void goToAdvNotes(MouseEvent event) throws IOException {
        App.setRoot("/fxml/studentAdvNotes");
    }

    @FXML
    void goToSearchCourse(MouseEvent event) throws IOException {
        App.setRoot("/fxml/search_course");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      HomeFacade homeFacade = HomeFacade.getInstance();
        if(homeFacade.getLoggedInUser() != null)
        {
            user_name_profile.setText(homeFacade.getLoggedInUser().getUserFullName());
        }
    }

}
