package degreeplanner;

import java.io.IOException;

import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.DropdownButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class HomeController {

    @FXML
    private TextArea adv_note1;

    @FXML
    private TextArea adv_note2;

    @FXML
    private TextArea adv_note3;

    @FXML
    private Avatar avatar_profile;

    @FXML
    private Text degreeplanner_home;

    @FXML
    private ScrollBar notes_scrollbar;

    @FXML
    private ProgressBar progress_bar;

    @FXML
    private DropdownButton semester_dropdown;

    @FXML
    private void dropDownButton(MouseEvent event) throws IOException
    {

    }

    @FXML
    private Label user_name_profile;

}