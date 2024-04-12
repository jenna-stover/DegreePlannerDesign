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
    private ChoiceBox<?> semester_dropdown;

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
        
    }

}