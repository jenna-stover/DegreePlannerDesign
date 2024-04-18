package degreeplanner.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import degreeplanner.App;
import degreeplanner.design_code.Course;
import degreeplanner.design_code.HomeFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;

public class SearchCourseController implements Initializable {
    @FXML
    private Text degreeplanner_search;
    @FXML
    private VBox searchCourseVBox;
    @FXML
    private Button search_button;
    @FXML
    private Button search_button_by_id;
    @FXML
    private Pane search_pane;
    @FXML
    private Label user_fullname;
    @FXML
    private ImageView user_profile;
    @FXML
    private TextField courseIdTextField;

    @FXML
    void goHome(MouseEvent event) throws IOException {
        App.setRoot("/fxml/home");
    }

    @FXML
    void goToProfile(MouseEvent event) throws IOException {
        App.setRoot("/fxml/profile");
    }

    @FXML
    void userLogout(MouseEvent event) throws IOException {
        App.setRoot("/fxml/login");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HomeFacade homeFacade = HomeFacade.getInstance();
        if (homeFacade.getLoggedInUser() != null) {
            user_fullname.setText(homeFacade.getLoggedInUser().getUserFullName());
        }
    }

    @FXML
    void searchCourses(MouseEvent event) {
        // Retrieve search query from search_pane
        String query = ((TextField) search_pane.getChildren().get(2)).getText();

        // Call method in HomeFacade to search for courses
        ArrayList<Course> matchingCourses = HomeFacade.getInstance().searchCourses(query);

        // Display search results in searchCourseVBox
        displaySearchResults(matchingCourses);
    }

    @FXML
    void searchCourseById(MouseEvent event) {
        String courseId = courseIdTextField.getText();
        Course course = HomeFacade.getInstance().getCourse(courseId);
        if (course != null) {
            displayCourseDetails(course);
        } else {
            // Display an error message or handle the case where the course is not found
            System.out.println("Course not found: " + courseId);
        }
    }

    private void displaySearchResults(ArrayList<Course> courses) {
        // Clear previous search results
        searchCourseVBox.getChildren().clear();

        // Display each matching course in the VBox
        for (Course course : courses) {
            Label courseLabel = new Label(course.toString());
            courseLabel.setOnMouseClicked(event -> displayCourseDetails(course));
            searchCourseVBox.getChildren().add(courseLabel);
        }
    }

  private void displayCourseDetails(Course course) {
    // Clear any previous search results
    searchCourseVBox.getChildren().clear();

    
    Label courseNameLabel = new Label("Course Name: " + course.getCourseName());
    Label courseIdLabel = new Label("Course ID: " + course.getCourseID());
    Label courseDescriptionLabel = new Label("Course Description: " + course.getCourseDescription());
    Label courseHoursLabel = new Label("Course Hours: " + course.getCourseHours());
    Label semestersProvidedLabel = new Label("Semesters Provided: " + String.join(", ", course.getSemestersProvided()));
    Label preReqsLabel = new Label("Pre-requisites: " + course.reqsToString());
    Label coReqsLabel = new Label("Co-requisites: " + course.reqsToString());

    
    courseDescriptionLabel.setWrapText(true);
    courseDescriptionLabel.setMaxWidth(200); 

    
    HBox courseDetailsHBox = new HBox(10); 

    
    courseDetailsHBox.getChildren().addAll(
            courseNameLabel, courseIdLabel, courseDescriptionLabel,
            courseHoursLabel, semestersProvidedLabel, preReqsLabel, coReqsLabel
    );

    // Add the HBox to the searchCourseVBox
    searchCourseVBox.getChildren().add(courseDetailsHBox);
}





   
}