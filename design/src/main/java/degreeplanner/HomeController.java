package degreeplanner;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
public class HomeController implements Initializable {
    @FXML
    private void onLoginClicked(ActionEvent event) throws IOException {
        App.setRoot("/login");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
