package degreeplanner.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import degreeplanner.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ProfileController implements Initializable {

    @FXML
    private ImageView home_icon;

    @FXML
    private TextArea profile_area;

    @FXML
    void goBackHome(MouseEvent event) throws IOException {
        App.setRoot("/fxml/home");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }

}