package degreeplanner.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import degreeplanner.App;
import degreeplanner.design_code.HomeFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class FacultyProfileController implements Initializable{

    @FXML
    private Text emailProfileTxt;

    @FXML
    private ImageView homeIcon;

    @FXML
    private Button logout_button;

    @FXML
    private TextArea profile_area;

    @FXML
    private Label userFullName;

    @FXML
    private Text userProfileTxt;

    @FXML
    void goBackHome(MouseEvent event) throws IOException {
      App.setRoot("/fxml/facultyHome");
    }

    @FXML
    void userLogout(MouseEvent event) throws IOException {
      App.setRoot("/fxml/facultyLogin");
    }

     @Override
    public void initialize(URL location, ResourceBundle resources) {
        HomeFacade homeFacade = HomeFacade.getInstance();
        if(homeFacade.getLoggedInUser() != null){
            userFullName.setText(homeFacade.getLoggedInUser().getUserFullName());
            userProfileTxt.setText(homeFacade.getLoggedInUser().getUserID());
            emailProfileTxt.setText(homeFacade.getLoggedInUser().getUserEmail());
        }
    }
} 
