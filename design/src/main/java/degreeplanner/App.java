package degreeplanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import degreeplanner.design_code.HomeFacade;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException 
    {
        scene = new Scene(loadFXML("/fxml/login"), 640, 480);
//     scene.getStylesheets().add("/fxml/styles.css");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() 
        {
            @Override
            public void handle(WindowEvent t) 
            {
                HomeFacade homeFacade = HomeFacade.getInstance();
                homeFacade.logout();
                Platform.exit();
                System.exit(0);
            }
        });

    }

    public static  void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}