package degreeplanner.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import degreeplanner.App;
import degreeplanner.design_code.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ProfileController implements Initializable {

    @FXML
    private Text emailProfileTxt;

    @FXML
    private ImageView homeIcon;

    @FXML
    private Text majorProfileTxt;

    @FXML
    private Text minorProfileTxt;

    @FXML
    private Text progressProfileText;

    @FXML
    private Text userProfileTxt;

    @FXML
    private Label userFullName;

    @FXML
    void goBackHome(MouseEvent event) throws IOException {
        App.setRoot("/fxml/home");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HomeFacade homeFacade = HomeFacade.getInstance();
        if(homeFacade.getLoggedInUser() != null){
            userFullName.setText(homeFacade.getLoggedInUser().getUserFullName());
            userProfileTxt.setText(homeFacade.getLoggedInUser().getUserID());
            emailProfileTxt.setText(homeFacade.getLoggedInUser().getUserEmail());
            majorProfileTxt.setText(((Student)homeFacade.getLoggedInUser()).getMajor().toString());
            // minorProfileTxt.setText(homeFacade.getLoggedInUser().getMinor()); NO minor to be found
            
            double prog = ((Student)homeFacade.getLoggedInUser()).getDegreeProgress();
            prog *= 100;
            DecimalFormat df = new DecimalFormat("##.####");
            String progress = df.format(prog) + "%";
            progressProfileText.setText(progress); //last three lines for degree progess

            ProgressBar profile = new ProgressBar();
            profile.setProgress(prog);
        }
    }
} 