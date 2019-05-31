package com.clickdigitalsolutions.rentverticalmenu;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main_view.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        
        stage.setMinWidth(661);
        stage.setMinHeight(431);
        stage.setWidth(600);
        stage.setHeight(740);
        stage.setMaxWidth(750);
        stage.setMaxHeight(650);
        
        stage.minWidthProperty().bind(scene.heightProperty().multiply(1.5));
        stage.minHeightProperty().bind(scene.widthProperty().divide(1.5));
        
        stage.show();
        stage.onCloseRequestProperty().setValue(e -> Platform.exit());
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
