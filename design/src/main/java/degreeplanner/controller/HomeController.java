package degreeplanner.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import degreeplanner.App;
import degreeplanner.design_code.Course;
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

    // @FXML
    // private TableView<?> semester1Table;

    // @FXML
    // private TableView<?> semester2Table;

    // @FXML
    // private TableView<?> semester3Table;

    // @FXML
    // private TableView<?> semester4Table;

    // @FXML
    // private TableView<?> semester5Table;

    // @FXML
    // private TableView<?> semester6Table;

    // @FXML
    // private TableView<?> semester7Table;

    // @FXML
    // private TableView<?> semester8Table;

    @FXML
    private TableView<Course> semester1Table;

    @FXML
    private TableView<Course> semester2Table;

    @FXML
    private TableView<Course> semester3Table;

    @FXML
    private TableView<Course> semester4Table;

    @FXML
    private TableView<Course> semester5Table;

    @FXML
    private TableView<Course> semester6Table;

    @FXML
    private TableView<Course> semester7Table;

    @FXML
    private TableView<Course> semester8Table;


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

         // Populate courses for each semester
         populateSemesterCourses(semester1Table, "Semester 1");
         populateSemesterCourses(semester2Table, "Semester 2");
         populateSemesterCourses(semester3Table, "Semester 3");
         populateSemesterCourses(semester4Table, "Semester 4");
         populateSemesterCourses(semester5Table, "Semester 5");
         populateSemesterCourses(semester6Table, "Semester 6");
         populateSemesterCourses(semester7Table, "Semester 7");
         populateSemesterCourses(semester8Table, "Semester 8");
 
        
        double prog = ((Student)homeFacade.getLoggedInUser()).getDegreeProgress();
        ProgressBar profile = new ProgressBar();
        profile.setProgress(prog);
    }

    private void populateSemesterCourses(TableView<Course> table, String semester) {
        HomeFacade homeFacade = HomeFacade.getInstance();
        Student loggedInUser = (Student) homeFacade.getLoggedInUser();
        ArrayList<Course> courses = homeFacade.getCoursesForSemester(loggedInUser, semester);
        table.getItems().addAll(courses);
    }

}