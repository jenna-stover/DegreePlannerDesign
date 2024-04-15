package degreeplanner.controller;

import java.io.IOException;

import degreeplanner.App;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SearchCourseController {

    @FXML
    private Text degreeplanner_search;

    @FXML
    private Pane search_pane;

    @FXML
    void goHome(MouseEvent event) throws IOException {
      App.setRoot("/fxml/home");
    }

}
