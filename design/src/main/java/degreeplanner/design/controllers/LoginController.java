package degreeplanner.design.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import degreeplanner.*;
import degreeplanner.design.design_code.HomeFacade;
import degreeplanner.design.library.App;

public class LoginController implements  Initializable 
{

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_password;

    @FXML

    private Label lbl_error;

    @FXML

    private void btnLoginClicked(MouseEvent event) throws IOException 
    {
        String email = txt_email.getText();
        String password = txt_password.getText();
        HomeFacade homeFacade = new HomeFacade();

        if(!homeFacade.login(email,password)) 
        {
            lbl_error.setText("Invalid login credentials.");
            return;
        }

        App.setRoot("");
    }
    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("home");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
}
