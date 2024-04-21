package degreeplanner.controller;
import java.time.LocalDate;
import java.util.Optional;

import degreeplanner.design_code.Student;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Pair;
import javafx.application.Platform;
import javafx.scene.Node;

public class AddNoteCell extends TableCell<Student, Boolean> {
    private final Button addNoteButton;

    /**
     * Generates button called "Add Note" in the Add Note column inside of Tableview.
     * When button is click, it opens a TextInputDialog which prompts advisor to enter 
     * a note for the selected student.
     * If advisor submits the note, the Optional<String> result will contain the submitted 
     * note which will be added to the student's advisement notes
     */
    public AddNoteCell() {
        addNoteButton = new Button("Add Note");
        addNoteButton.setOnAction(event -> {
            Student student = getTableView().getItems().get(getIndex());
            Dialog<Pair<String, LocalDate>> dialog = new Dialog<>();
            dialog.setTitle("Add New Note for " + student.getFullName());

            ButtonType submitButtonType = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            DatePicker datePicker = new DatePicker(LocalDate.now());
            TextArea noteArea = new TextArea();
            noteArea.setPromptText("Write your note here");

            grid.add(new Label("Date:"), 0, 0);
            grid.add(datePicker, 1, 0);
            grid.add(new Label("Note:"), 0, 1);
            grid.add(noteArea, 1, 1);

            Node submitButton = dialog.getDialogPane().lookupButton(submitButtonType);
            submitButton.setDisable(true);

            noteArea.textProperty().addListener((observable, oldValue, newValue) -> {
                submitButton.setDisable(newValue.trim().isEmpty());
            });

            dialog.getDialogPane().setContent(grid);

            Platform.runLater(() -> noteArea.requestFocus());

            dialog.setResultConverter(dialogButton -> {
                if(dialogButton == submitButtonType) {
                    return new Pair<>(noteArea.getText(), datePicker.getValue());
                }
                return null;
            });

            Optional<Pair<String, LocalDate>> result = dialog.showAndWait();

            result.ifPresent(noteDatePair -> {
                String note = noteDatePair.getKey();
                LocalDate date = noteDatePair.getValue();
                try {
                    student.addAdvisementNote(date, note);
                    Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION, "Note successfully added!");
                    confirmationAlert.setHeaderText(null);
                    confirmationAlert.showAndWait();
                } catch (Exception e) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Failed to add note: " + e.getMessage());
                    errorAlert.setHeaderText(null);
                    errorAlert.showAndWait();
                }
            });

        });
    }

    @Override
    public void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(addNoteButton);
        }
    }

    public static Callback<TableColumn<Student, Boolean>, TableCell<Student, Boolean>> forTableColumn() {
        return param -> new AddNoteCell();
    }
}