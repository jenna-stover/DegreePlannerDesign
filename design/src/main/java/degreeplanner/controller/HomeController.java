package degreeplanner.controller;

import java.io.IOException;
import java.net.URL;
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 05103de021b826fb7906304b8996e5e73dea4d7d
import java.util.ResourceBundle;

import degreeplanner.App;
import degreeplanner.design_code.HomeFacade;
import degreeplanner.design_code.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class HomeController implements Initializable{

    @FXML
    private ImageView adv_notes;

    @FXML
    private ImageView avatar_profile;

    @FXML
    private Label degreePercentage;

    @FXML
    private Text degreeplanner_home;

    @FXML
    private ProgressBar progress_bar;

    @FXML
    private ImageView search_course;

    @FXML
    private TableView<?> semester1Table;

    @FXML
    private TableView<?> semester2Table;

    @FXML
    private TableView<?> semester3Table;

    @FXML
    private TableView<?> semester4Table;

    @FXML
    private TableView<?> semester5Table;

    @FXML
    private TableView<?> semester6Table;

    @FXML
    private TableView<?> semester7Table;

    @FXML
    private TableView<?> semester8Table;

    @FXML
    private Label user_name_profile;

    @FXML
    void avatarProfileClicked(MouseEvent event) throws IOException {
        App.setRoot("/fxml/profile");
    }

    @FXML
    void goToAdvNotes(MouseEvent event) throws IOException {
        App.setRoot("/fxml/adv_notes");
    }

    // Go back to login?
    @FXML
    void goHome(MouseEvent event) throws IOException {
        App.setRoot("/fxml/home");
    }

    @FXML
    void goToSearchCourse(MouseEvent event) throws IOException {
        // System.out.println("Attempt to send to search course");
        App.setRoot("/fxml/search_course");
    }

    /**
     * loads data specific to the current user such as user's name and course information
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        HomeFacade homeFacade = HomeFacade.getInstance();
        if(homeFacade.getLoggedInUser() != null)
        {
            user_name_profile.setText(homeFacade.getLoggedInUser().getUserFullName());
        }
        
        double prog = ((Student)homeFacade.getLoggedInUser()).getDegreeProgress();
        ProgressBar profile = new ProgressBar();
        profile.setProgress(prog);
    }

}