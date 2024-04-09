module degreeplanner.design {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires javafx.base;

    opens degreeplanner.design.library to javafx.fxml;

    exports degreeplanner.design.library;

    opens degreeplanner.design.controllers to javafx.fxml;

    exports  degreeplanner.design.controllers;


    opens degreeplanner.design.design_code to javafx.fxml;

    exports degreeplanner.design.design_code;

    
}
