package com.clickdigitalsolutions.rentverticalmenu;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;


public class MainApp extends Application {

    @Override
    public void init() throws Exception {
        // Do some heavy lifting
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main_view.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/newCascadeStyleSheet.css").toExternalForm());
        stage.getIcons().add(new Image("/images/ApartmentPNG.png", 100.0, 100.0, true, true));
        stage.setTitle("RentApp");
        stage.setScene(scene);
        stage.show();
        stage.onCloseRequestProperty().setValue(e -> Platform.exit());
    }
    
    public static void changeWindowSize(Window stage, double height) {
        stage.setHeight(height);
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
        LauncherImpl.launchApplication(MainApp.class, jatomRentPreloader.class, args);
    }

}
