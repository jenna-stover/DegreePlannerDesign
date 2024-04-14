package degreeplanner;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    private String[] semesters = {"Semester 1", "Semester 2", "Semester 3", "Semester 4",
                                    "Semester 5", "Semester 6", "Semester 7", "Semester 8"};

    @FXML
    private Label user_name_profile;

    @FXML
    void avatarProfileClicked(MouseEvent event) throws IOException {
        App.setRoot("/profile");
    }

    @FXML
    void goHome(MouseEvent event) throws IOException {
        App.setRoot("/home");
    }

    @FXML
    void goToSearchCourse(MouseEvent event) {
        App.setRoot("/search_home");
    }

    /**
     * loads data specific to the current user such as user's name and course information
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(homeFacade.getUser() != null){
            user_name_profile.setText(homeFacade.getUser());
        }

        semester_dropdown.getItems().addAll(semesters);
    }

    /**
     * changes contents of vbox depending on semester chosen
     */
    public void getSemester(ActionEvent event){
        String mySemester = semester_dropdown.getValue();
        semester_courses_vbox.setText();
    }

}