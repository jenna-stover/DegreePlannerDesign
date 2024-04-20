package degreeplanner.controller;
import degreeplanner.design_code.Student;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class AddNoteCell extends TableCell<Student, Boolean> {
    private final Button addNoteButton;

    public AddNoteCell() {
        addNoteButton = new Button("Add Note");
        addNoteButton.setOnAction(event -> {
            Student student = getTableView().getItems().get(getIndex());
            // TODO: Implement the logic to add a note for the selected student
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