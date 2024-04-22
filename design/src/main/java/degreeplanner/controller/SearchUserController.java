package degreeplanner.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import degreeplanner.App;
import degreeplanner.design_code.Course;
import degreeplanner.design_code.HomeFacade;
import degreeplanner.design_code.Student;
import degreeplanner.design_code.User;
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

public class SearchUserController implements Initializable{

    @FXML
    private TextField userIdTextField;

    @FXML
    private Text degreeplanner_search;

    @FXML
    private Button logout_button;

    @FXML
    private VBox searchUserVBox;

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
    void facultyLogout(MouseEvent event) throws IOException {
      App.setRoot("/fxml/facultyHome");
    }

    @FXML
    void avatarProfileClicked(MouseEvent event) throws IOException {
      App.setRoot("/fxml/facultyProfile");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HomeFacade homeFacade = HomeFacade.getInstance();
        if (homeFacade.getLoggedInUser() != null) {
            user_fullname.setText(homeFacade.getLoggedInUser().getUserFullName());
        }
    }

    @FXML
    void searchUsers(MouseEvent event) {
        // Retrieve search query from search_pane
        String query = ((TextField) search_pane.getChildren().get(2)).getText();

        // Call method in HomeFacade to search for users
        ArrayList<User> matchingUsers = HomeFacade.getInstance().searchUsers(query);

        // Display search results 
        displaySearchResults(matchingUsers);
    }

    @FXML
    void searchUserById(MouseEvent event) {
      String userID = userIdTextField.getText();
      User user = HomeFacade.getInstance().getUser(userID);
      if(user != null) {
        displayUserDetails(user);
      }
      else{
        System.out.println("User not found: "+ userID);
      }
    }

    private void displaySearchResults(ArrayList<User> users){
      searchUserVBox.getChildren().clear();

      for(User user : users) {
        Label userLabel = new Label(user.toString());
        userLabel.setOnMouseClicked(event -> displayUserDetails(user));
        searchUserVBox.getChildren().add(userLabel);
      }
    }

    private void displayUserDetails(User user) {
      searchUserVBox.getChildren().clear();
      HomeFacade homeFacade = HomeFacade.getInstance();
    
      Label studentNameLabel = new Label(user.getUserFullName());
      studentNameLabel.setWrapText(true);
      studentNameLabel.setMinWidth(200);
      studentNameLabel.setMaxWidth(300);
    
      Label studentUsernameLabel = new Label(user.getUserID());
      studentUsernameLabel.setWrapText(true);
      studentNameLabel.setMinWidth(200);
      studentUsernameLabel.setMaxWidth(300);
    
      Label studentEmailLabel = new Label(user.getUserEmail());
      studentEmailLabel.setWrapText(true);
      studentNameLabel.setMinWidth(200);
      studentEmailLabel.setMaxWidth(350);
    
      Label studentMajorLabel = null;
      if (user instanceof Student) {
          Student student = (Student) user;
          studentMajorLabel = new Label(student.getMajor().toString());
          studentMajorLabel.setWrapText(true);
          studentNameLabel.setMinWidth(200);
          studentMajorLabel.setMaxWidth(300);
      } else {
          studentMajorLabel = new Label("N/A");
      }
    
      int currentSemester = 0;
      if (user instanceof Student) {
          Student student = (Student) user;
          currentSemester = student.getCurrentSemester();
      }
      Label studentCurrSemLabel = new Label(String.valueOf(currentSemester));
      studentCurrSemLabel.setWrapText(true);
      studentNameLabel.setMinWidth(50);
      studentCurrSemLabel.setMaxWidth(50);
    
      String progress = "N/A";
      if (user instanceof Student) {
          Student loggedInStudent = (Student) user;
          double prog = loggedInStudent.getDegreeProgress();
          prog *= 100;
          DecimalFormat df = new DecimalFormat("##.####");
          progress = df.format(prog) + "%";
          
      //  progress = homeFacade.getStudentsProgress(loggedInStudent);
      }
      Label studentProgressLabel = new Label(progress);
      studentProgressLabel.setWrapText(true);
  // studentProgressLabel.setMaxWidth(50);
    
      HBox userDetailsHBox = new HBox(10);
      userDetailsHBox.getChildren().addAll(studentNameLabel, studentUsernameLabel, studentEmailLabel, studentMajorLabel, studentCurrSemLabel, studentProgressLabel);
    
      searchUserVBox.getChildren().add(userDetailsHBox);
    }
}