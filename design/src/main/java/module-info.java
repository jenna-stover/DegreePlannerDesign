module degreeplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires javafx.base;
    requires javafx.graphics;

    // opens degreeplanner.design.library to javafx.fxml;

    // exports degreeplanner.design.library;

    opens degreeplanner.controller to javafx.fxml;

    exports  degreeplanner.controller;

    opens degreeplanner to javafx.fxml;

    exports degreeplanner;

    opens degreeplanner.design_code to javafx.fxml;

    exports degreeplanner.design_code;

    
}
