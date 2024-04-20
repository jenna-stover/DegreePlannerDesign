package degreeplanner.controller;

import java.io.IOException;

import degreeplanner.App;
import degreeplanner.design_code.HomeFacade;
import degreeplanner.design_code.Student;
import degreeplanner.design_code.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SearchUserController {

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
    void searchUserById(MouseEvent event) {
      String userID = userIdTextField.getText();
      User user = HomeFacade.getInstance().getUser(userID);
      if(user != null) {
        
      }
    }

    private void displayUserDetails(User user){
      searchUserVBox.getChildren().clear();
      HomeFacade homeFacade = HomeFacade.getInstance();

      Label studentNameLabel = new Label(user.getUserFullName());
      studentNameLabel.setWrapText(true);
      studentNameLabel.setMaxWidth(100);

      Label studentUsernameLabel = new Label(user.getUserID());
      studentUsernameLabel.setWrapText(true);
      studentUsernameLabel.setMaxWidth(100);

      Label studentEmailLabel = new Label(user.getUserEmail());
      studentEmailLabel.setWrapText(true);
      studentEmailLabel.setMaxWidth(250);

      Label studentMajorLabel = new Label(((Student)homeFacade.getLoggedInUser()).getMajor().toString());
      studentMajorLabel.setWrapText(true);
      studentMajorLabel.setMaxWidth(50);

      Student student = (Student)homeFacade.getLoggedInUser();
      int currentSemester = student.getCurrentSemester(); 
      Label studentCurrSemLabel = new Label(String.valueOf(currentSemester));
      studentCurrSemLabel.setWrapText(true);
      studentCurrSemLabel.setMaxWidth(100);

      



    }


}
