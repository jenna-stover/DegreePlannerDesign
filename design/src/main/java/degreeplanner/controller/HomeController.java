package degreeplanner.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import degreeplanner.App;
import degreeplanner.design_code.Course;
import degreeplanner.design_code.HomeFacade;
import degreeplanner.design_code.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Course, String> sem1courses;

    @FXML
    private TableColumn<Course, String> sem1credits;

    @FXML
    private TableColumn<Course, String> sem1grade;

    @FXML
    private TableColumn<Course, String> sem2courses;

    @FXML
    private TableColumn<Course, String> sem2credits;

    @FXML
    private TableColumn<Course, String> sem2grade;

    @FXML
    private TableColumn<Course, String> sem3courses;

    @FXML
    private TableColumn<Course, String> sem3credits;

    @FXML
    private TableColumn<Course, String> sem3grade;

    @FXML
    private TableColumn<Course, String> sem4courses;

    @FXML
    private TableColumn<Course, String> sem4credits;

    @FXML
    private TableColumn<Course, String> sem4grade;

    @FXML
    private TableColumn<Course, String> sem5courses;

    @FXML
    private TableColumn<Course, String> sem5credits;

    @FXML
    private TableColumn<Course, String> sem5grade;

    @FXML
    private TableColumn<Course, String> sem6courses;

    @FXML
    private TableColumn<Course, String> sem6credits;

    @FXML
    private TableColumn<Course, String> sem6grade;

    @FXML
    private TableColumn<Course, String> sem7courses;

    @FXML
    private TableColumn<Course, String> sem7credits;

    @FXML
    private TableColumn<Course, String> sem7grade;

    @FXML
    private TableColumn<Course, String> sem8courses;

    @FXML
    private TableColumn<Course, String> sem8credits;

    @FXML
    private TableColumn<Course, String> sem8grade;


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
        //ProgressBar progress_bar = new ProgressBar(0);
        progress_bar.setProgress(prog);

         // Populate courses for each semester
         populateSemesterCourses(semester1Table, "Semester 1", sem1courses, sem1credits, sem1grade);
         populateSemesterCourses(semester2Table, "Semester 2", sem2courses, sem2credits, sem2grade);
         populateSemesterCourses(semester3Table, "Semester 3", sem3courses, sem3credits, sem3grade);
         populateSemesterCourses(semester4Table, "Semester 4", sem4courses, sem4credits, sem4grade);
         populateSemesterCourses(semester5Table, "Semester 5", sem5courses, sem5credits, sem5grade);
         populateSemesterCourses(semester6Table, "Semester 6", sem6courses, sem6credits, sem6grade);
         populateSemesterCourses(semester7Table, "Semester 7", sem7courses, sem7credits, sem7grade);
         populateSemesterCourses(semester8Table, "Semester 8", sem8courses, sem8credits, sem8grade);
 
    }

    private void populateSemesterCourses(TableView<Course> table, String semester, TableColumn<Course, String> courseCol, TableColumn<Course, String> credCol,TableColumn<Course, String> gradeCol) 
    {
        HomeFacade homeFacade = HomeFacade.getInstance();
        Student loggedInUser = (Student) homeFacade.getLoggedInUser();
        ArrayList<Course> courses = homeFacade.getCoursesForSemester(loggedInUser, semester);
        ObservableList<Course> presentableCourses = FXCollections.observableArrayList(courses);
        //TableColumn<Course,String> course = new TableColumn<Course, String>("COURSE");
        // course.setCellValueFactory(new PropertyValueFactory<Course, String>(courses.get(0).courseProperty()));
        //TableColumn<Course,String> credits = new TableColumn<Course,String>("CREDITS");
        // credits.setCellValueFactory(new PropertyValueFactory<Course, String>(courses.get(0).creditsProperty().getValue()));
        //TableColumn<Course,String> grade = new TableColumn<Course,String>("GRADE");
        // table.getColumns().addAll(course, credits);
        courseCol.setCellValueFactory(cellData -> cellData.getValue().courseProperty());
        credCol.setCellValueFactory(cellData -> cellData.getValue().creditsProperty());
        gradeCol.setCellValueFactory(new PropertyValueFactory<Course, String>("grade"));
        table.setItems(presentableCourses);
    }

}