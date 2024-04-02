module degreeplanner.design {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens degreeplanner.design to javafx.fxml;
    exports degreeplanner.design;
}
