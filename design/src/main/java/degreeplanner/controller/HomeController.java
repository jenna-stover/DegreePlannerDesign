package degreeplanner.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import degreeplanner.App;
import degreeplanner.design_code.HomeFacade;

public class HomeController implements Initializable{

    @FXML
    private VBox adv_notes_vbox;

    @FXML
    private Text degreeplanner_home;

    @FXML
    private ScrollBar notes_scrollbar;

    @FXML
    private ProgressBar progress_bar;

    @FXML
    private Text search_course;

    @FXML
    private ImageView search_img;

    @FXML
    private VBox semester_courses_vbox;

    @FXML
    private ChoiceBox<String> semester_dropdown;
    private String[] semester = {"Semester 1", "Semester 2", "Semester 3", "Semester 4",
                                    "Semester 5", "Semester 6", "Semester 7", "Semester 8"};

    @FXML
    private Label user_name_profile;

    @FXML
    void avatarProfileClicked(MouseEvent event) throws IOException {
        App.setRoot("/fxml/profile");
    }

    @FXML
    void goHome(MouseEvent event) throws IOException {
        App.setRoot("/fxml/home");
    }

    /**
     * loads data specific to the current user such as user's name and course information
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HomeFacade homeFacade = HomeFacade.getInstance();
        if(homeFacade.getLoggedInUser() != null){
            user_name_profile.setText(homeFacade.getLoggedInUser().getUserFullName());
        }

        semester_dropdown.getItems().addAll(semester);
        semester_dropdown.setOnAction(this::getSemester);
    }

    public void getSemester(ActionEvent event){
        semester_courses_vbox.setAccessibleText(null);
        
    }

}