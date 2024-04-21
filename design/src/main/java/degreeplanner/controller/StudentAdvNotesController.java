package degreeplanner.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import degreeplanner.App;
import degreeplanner.design_code.AdvisementNote;
import degreeplanner.design_code.HomeFacade;
import degreeplanner.design_code.Student;
import degreeplanner.design_code.User;
import degreeplanner.design_code.UserType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StudentAdvNotesController implements Initializable {

    @FXML
    private ImageView adv_notes;

    @FXML
    private ImageView avatar_profile;

    @FXML
    private Text degreeplanner_home;

    @FXML
    private Button logout_button;

    @FXML
    private ImageView search_course;

    @FXML
    private Label user_name_profile;

    @FXML
    private VBox studentAdvNoteVbox;

    @FXML
    void avatarProfileClicked(MouseEvent event) throws IOException {
        App.setRoot("/fxml/profile");
    }

    @FXML
    void studentLogout(MouseEvent event) throws IOException {
        App.setRoot("/fxml/login");
    }

    @FXML
    void goHome(MouseEvent event) throws IOException {
        App.setRoot("/fxml/home");
    }

    @FXML
    void goToAdvNotes(MouseEvent event) throws IOException {
        App.setRoot("/fxml/studentAdvNotes");
    }

    @FXML
    void goToSearchCourse(MouseEvent event) throws IOException {
        App.setRoot("/fxml/search_course");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HomeFacade homeFacade = HomeFacade.getInstance();
        User user = homeFacade.getLoggedInUser();
        if(user != null)
        {
            user_name_profile.setText(homeFacade.getLoggedInUser().getUserFullName());
        }

        studentAdvNoteVbox.getChildren().clear();
        if (user != null && user.getUserType() == UserType.STUDENT) {
            Student student = (Student) user; 
            ArrayList<AdvisementNote> advisementNotes = student.getAdvisementNotes();
            for (AdvisementNote note : advisementNotes) {
                Label noteLabel = new Label(String.format("%s: %s", note.getDate().toString(), note.getNote()));
                noteLabel.setStyle("-fx-padding: 5;"); 
                studentAdvNoteVbox.getChildren().add(noteLabel);
            }
        }
    }
}