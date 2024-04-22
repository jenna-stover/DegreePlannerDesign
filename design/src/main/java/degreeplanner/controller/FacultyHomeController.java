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
import java.time.LocalDate;
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
import degreeplanner.design_code.AdvisementPlan;
import degreeplanner.design_code.AdvisementPlanList;
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

    @FXML
    private TableColumn<AdvisementPlan, LocalDate> dateColumn;

    @FXML
    private TableColumn<AdvisementPlan, String> adviseeColumn;

    @FXML
    private TableColumn<AdvisementPlan, String> noteColumn;
    
    @FXML
    private TableView<AdvisementPlan> NotesTable;

    @FXML
    private Label profileFullName;

    @FXML
    private FacultyHomeController facultyHomeController;



    


    private ObservableList<Student> advisees = FXCollections.observableArrayList();

    private HomeFacade homeFacade = HomeFacade.getInstance();

    private ObservableList<AdvisementPlan> advisementPlans = FXCollections.observableArrayList();




    @FXML
    void goToProfile(MouseEvent event) throws IOException {
        App.setRoot("/fxml/facultyProfile");

    }

    @FXML
    void goToSearchCourse(MouseEvent event) throws IOException {

        App.setRoot("/fxml/search_course_fac");
    }

    @FXML
    void goHome(MouseEvent event) throws IOException {
        App.setRoot("/fxml/facultyHome");
    }

    @FXML
    void goToSearchUser(MouseEvent event) throws IOException {

        App.setRoot("/fxml/searchUser");

    }

    @FXML
    void goToLogin(MouseEvent event) throws IOException {
        App.setRoot("/fxml/facultyLogin");
    }

    @FXML
    void userLogout(MouseEvent event) throws IOException {
        App.setRoot("/fxml/facultyLogin");
    }

   @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        facultyHomeController = this;

       

        HomeFacade homeFacade = HomeFacade.getInstance();
        if(homeFacade.getLoggedInUser() != null)
        {
            profileFullName.setText(homeFacade.getLoggedInUser().getUserFullName());
        }
        ArrayList<Student> advisees = homeFacade.getAdvisees();
        ObservableList<Student> observableAdvisees = FXCollections.observableArrayList(advisees);

        adviseeTable.setItems(observableAdvisees);
        adviseeNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        adviseeIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("currentMajor"));
        progressColumn.setCellValueFactory(new PropertyValueFactory<>("degreeProgress"));
         // Set the cell factory for the "Add Note" column
        //  TableColumn<Student, Boolean> addNoteColumn = (TableColumn<Student, Boolean>) adviseeTable.getColumns().get(4);
        //  addNoteColumn.setCellFactory(AddNoteCell.forTableColumn());
        TableColumn<Student, Boolean> addNoteColumn = (TableColumn<Student, Boolean>) adviseeTable.getColumns().get(4);
    addNoteColumn.setCellFactory(AddNoteCell.forTableColumn(facultyHomeController));


    //     // Populate NotesTable with notes from JSON
    ObservableList<AdvisementPlan> advisementNotes = FXCollections.observableArrayList();
    // for (AdvisementPlan plan : AdvisementPlanList.getInstance().getAllList()) {
    //     if (plan.getNotes() != null && !plan.getNotes().isEmpty()) {
    //         advisementNotes.add(new AdvisementPlan(plan.getPlanID(), LocalDate.now(), plan.getPlanStudent().getUserFullName(), plan.getNotes()));
    //     }
    // }
    NotesTable.setItems(advisementNotes);

    // Set up columns for NotesTable
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    adviseeColumn.setCellValueFactory(new PropertyValueFactory<>("advisee"));
    noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
    updateNotesTable();


       
 
    }
    void updateNotesTable() {
        ObservableList<AdvisementPlan> advisementNotes = FXCollections.observableArrayList();
        // for (AdvisementPlan plan : AdvisementPlanList.getInstance().getAllList()) {
        //     if (plan.getNotes() != null && !plan.getNotes().isEmpty()) {
        //         advisementNotes.add(new AdvisementNote(plan.getPlanID(), LocalDate.now(), plan.getPlanStudent().getUserFullName(), plan.getNotes()));
        //     }
        // }
        NotesTable.setItems(advisementNotes);
    }


}