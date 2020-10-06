package com.clickdigitalsolutions.rentverticalmenu;

import com.sun.javafx.application.LauncherImpl;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
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

    // executes database operations concurrent to JavaFX operations.
    public static ExecutorService databaseExecutor;

    // the future's data will be available once the database setup has been complete.
    public static Future databaseSetupFuture;

    // initialize the program.
    // setting the database executor thread pool size to 1 ensures 
    // only one database command is executed at any one time.
    @Override
    public void init() throws Exception {
        databaseExecutor = Executors.newFixedThreadPool(1, new PDController.DatabaseThreadFactory());
        
        // run the database setup in parallel to the JavaFX application setup.
        PDController pdController = new PDController();
        PDController.DBSetupTask setup = pdController.new DBSetupTask();
        databaseSetupFuture = databaseExecutor.submit(setup);
    }

    @Override
    public void stop() throws Exception {
        databaseExecutor.shutdown();
        if (!databaseExecutor.awaitTermination(3, TimeUnit.SECONDS)) {
            PDController.logger.info("Database execution  thread  timed out after 3 seconds rather than shutting down.");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        databaseSetupFuture.get();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main_view.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/newCascadeStyleSheet.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/styles/jfoenix-components.css").toExternalForm());
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
