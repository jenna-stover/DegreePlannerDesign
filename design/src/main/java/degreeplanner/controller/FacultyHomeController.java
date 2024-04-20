// package degreeplanner.controller;

// import java.io.IOException;
// import java.net.URL;
// import java.time.LocalDate;
// import java.util.ResourceBundle;

// import degreeplanner.App;
// import degreeplanner.design_code.AdvisementNote;
// import degreeplanner.design_code.HomeFacade;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.fxml.FXML;
// import javafx.fxml.Initializable;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.input.MouseEvent;
// import javafx.scene.layout.Pane;

// public class FacultyHomeController implements Initializable{

//     @FXML
//     private TableColumn<AdvisementNote, LocalDate> dateColumn;

//     @FXML
//     private TableColumn<AdvisementNote, String> adviseeColumn;

//     @FXML
//     private TableColumn<AdvisementNote, String> noteColumn;
    
//     @FXML
//     private TableView<AdvisementNote> adviseeTable;

//     @FXML
//     private Pane facultyNotePane;

//     @FXML
//     private Label facultySearchCourse;

//     @FXML
//     private Label facultySearchUser;

//     @FXML
//     private Button logout_button;

//     @FXML
//     private Label profileFullName;

//     @FXML
//     void goToProfile(MouseEvent event) throws IOException {
//         App.setRoot("/fxml/facultyProfile");
//     }

//     @FXML
//     void goToSearchCourse(MouseEvent event) throws IOException {
//         App.setRoot("/fxml/search_course");
//     }

//     @FXML
//     void goToSearchUser(MouseEvent event) throws IOException {
//         App.setRoot("/fxml/searchUser");
//     }

//     @FXML
//     void userLogout(MouseEvent event) throws IOException {
//         App.setRoot("/fxml/facultyLogin");
//     }

//     @Override
//     public void initialize(URL location, ResourceBundle resources) 
//     {
//         HomeFacade homeFacade = HomeFacade.getInstance();
//         if(homeFacade.getLoggedInUser() != null)
//         {
//             profileFullName.setText(homeFacade.getLoggedInUser().getUserFullName());
//         }

//         dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
//         adviseeColumn.setCellValueFactory(new PropertyValueFactory<>("advisee"));
//         noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));

//         ObservableList<AdvisementNote> advisementNotes = FXCollections.observableArrayList();

//         adviseeTable.setItems(advisementNotes);
//     }

// }
package degreeplanner.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import degreeplanner.App;
import degreeplanner.design_code.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import degreeplanner.design_code.HomeFacade;
import degreeplanner.design_code.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


public class FacultyHomeController implements Initializable {

    @FXML
    private Pane facultyNotePane;

    @FXML
    private Label facultySearchCourse;

    @FXML
    private Label facultySearchUser;


    @FXML
    private TableView<Student> adviseeTable;

    @FXML
    private TableColumn<Student, String> adviseeNameColumn;

    @FXML
    private TableColumn<Student, String> adviseeIDColumn;

    @FXML
    private TableColumn<Student, String> majorColumn;

    @FXML
    private TableColumn<Student, String> progressColumn;

    private ObservableList<Student> advisees = FXCollections.observableArrayList();

    private HomeFacade homeFacade = HomeFacade.getInstance();



    @FXML
    void goToProfile(MouseEvent event) throws IOException {
        App.setRoot("/fxml/facultyProfile");

    }

    @FXML
    void goToSearchCourse(MouseEvent event) throws IOException {

        App.setRoot("/fxml/search_course");

    }

    @FXML
    void goToSearchUser(MouseEvent event) throws IOException {

        App.setRoot("/fxml/searchUser");

    }

    @FXML
    void userLogout(MouseEvent event) {

    }

    @FXML
    void goToLogin(MouseEvent event) throws IOException {
        App.setRoot("/fxml/login");
    }

   @Override
public void initialize(URL url, ResourceBundle rb) {
    HomeFacade homeFacade = HomeFacade.getInstance();
    ArrayList<Student> advisees = homeFacade.getAdvisees();
    ObservableList<Student> observableAdvisees = FXCollections.observableArrayList(advisees);

    adviseeTable.setItems(observableAdvisees);
    adviseeNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    adviseeIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
    majorColumn.setCellValueFactory(new PropertyValueFactory<>("currentMajor"));
    progressColumn.setCellValueFactory(new PropertyValueFactory<>("degreeProgress"));

    // Set the cell factory for the "Add Note" column
    TableColumn<Student, Boolean> addNoteColumn = (TableColumn<Student, Boolean>) adviseeTable.getColumns().get(4);
    addNoteColumn.setCellFactory(AddNoteCell.forTableColumn());
}
}