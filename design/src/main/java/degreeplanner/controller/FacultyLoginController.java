package degreeplanner.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import degreeplanner.App;
import degreeplanner.design_code.HomeFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class FacultyLoginController implements Initializable {

   
    @FXML
    private Button facultyLoginButton;

    @FXML
    private Pane facultyLoginPane;

    @FXML
    private Button studentLoginButton;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_password;

    @FXML
    void btnFacultyLoginClicked(ActionEvent event) throws IOException {
        String email = txt_email.getText();
        String password = txt_password.getText();
        Label lbl_error = new Label();
        HomeFacade homeFacade = HomeFacade.getInstance();
    
        if (!homeFacade.login(email, password)) {
            lbl_error.setText("Invalid login credentials.");
            return;
        }
    
        App.setRoot("/fxml/facultyHome");
    }

    @FXML
    void StudentLogin(ActionEvent event) throws IOException {
        App.setRoot("/fxml/login");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }


}
