package degreeplanner.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import degreeplanner.*;
import degreeplanner.design_code.HomeFacade;

public class LoginController implements  Initializable  {
    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_password;

    @FXML

    private Label lbl_error;

    @FXML
    void btnLoginClicked(ActionEvent event) throws IOException 
    {
        String email = txt_email.getText();
        String password = txt_password.getText();
        Label lbl_error = new Label();
        HomeFacade homeFacade = HomeFacade.getInstance();

        if(!homeFacade.login(email,password)) 
        {
            lbl_error.setText("Invalid login credentials.");
            return;
        }

        App.setRoot("/fxml/home");
    }
    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("/fxml/login");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
