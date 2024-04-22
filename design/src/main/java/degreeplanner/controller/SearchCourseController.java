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
    @FXML
    void avatarProfileClicked(MouseEvent event) throws IOException {
        App.setRoot("/fxml/profile");
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

    
    Label courseNameLabel = new Label(course.getCourseName()); //100
    courseNameLabel.setWrapText(true);
    courseNameLabel.setMaxWidth(100);

    Label courseIdLabel = new Label(course.getCourseID()); //200
    courseIdLabel.setWrapText(true);
    courseIdLabel.setMaxWidth(100);

    Label courseDescriptionLabel = new Label(course.getCourseDescription()); //450
    courseDescriptionLabel.setWrapText(true);
    courseDescriptionLabel.setMaxWidth(250);

    Label courseHoursLabel = new Label(String.valueOf(course.getCourseHours())); //500
    courseHoursLabel.setMaxWidth(50);

    Label semestersProvidedLabel = new Label(String.join(", ", course.getSemestersProvided())); // 600
    semestersProvidedLabel.setWrapText(true);
    semestersProvidedLabel.setMaxWidth(100);

    Label preReqsLabel = new Label(course.prereqsToString()); //750
    preReqsLabel.setWrapText(true);
    preReqsLabel.setMaxWidth(150);

    Label coReqsLabel = new Label(course.coreqsToString()); //900
    coReqsLabel.setWrapText(true);
    coReqsLabel.setMaxWidth(150);
    
    HBox courseDetailsHBox = new HBox(10); 

    
    courseDetailsHBox.getChildren().addAll(
            courseNameLabel, courseIdLabel, courseDescriptionLabel,
            courseHoursLabel, semestersProvidedLabel, preReqsLabel, coReqsLabel
    );

    // Add the HBox to the searchCourseVBox
    searchCourseVBox.getChildren().add(courseDetailsHBox);
}





   
}
