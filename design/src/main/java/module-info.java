module degreeplanner.design {
    requires javafx.controls;
    requires javafx.fxml;

    opens degreeplanner.design to javafx.fxml;
    exports degreeplanner.design;
}
