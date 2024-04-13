package degreeplanner.controller;

import java.io.IOException;

import degreeplanner.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

public class HomeController {

    @FXML
    private TextArea adv_note1;

    @FXML
    private TextArea adv_note2;

    @FXML
    private TextArea adv_note3;

    @FXML
    private Text degreeplanner_home;

    @FXML
    private ScrollBar notes_scrollbar;

    @FXML
    private ProgressBar progress_bar;


    @FXML
    private Label user_name_profile;

    @FXML
    void goHome(MouseEvent event) throws IOException {
        App.setRoot("/fxml/home");
    }

}