package com.clickdigitalsolutions.rentverticalmenu;

import com.sun.javafx.application.LauncherImpl;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.WritableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

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
        /*scene.getStylesheets().add(getClass().getResource("/styles/jfoenix-components.css").toExternalForm());*/
        scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
        stage.getIcons().add(new Image("/images/ApartmentPNG.png", 100.0, 100.0, true, true));
        stage.setTitle("RentApp");
        stage.setScene(scene);
        stage.show();
        stage.onCloseRequestProperty().setValue(e -> Platform.exit());
    }

    public static void changeWindowSize(Window stage, double height) {
        /*Animation transition = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
                setInterpolator(Interpolator.LINEAR);
            }

            @Override
            protected void interpolate(double frac) {
                stage.setHeight(height * frac);
            }
        };
        transition.play();*/
        WritableValue<Double> writableHeight = new WritableValue<Double>() {
            @Override
            public Double getValue() {
                System.out.println(stage.getHeight());
                return stage.getHeight();
            }

            @Override
            public void setValue(Double value) {
                stage.setHeight(value);
            }
        };
        
        Duration cycleDuration = Duration.millis(500);
        Timeline timeline = new Timeline(new KeyFrame(cycleDuration, 
        new KeyValue(writableHeight, 700d, Interpolator.EASE_IN)));
        timeline.play();
    }
    
    public static void windowResize(Window stage) {
        WritableValue<Double> writableHeight = new WritableValue<Double>() {
            @Override
            public Double getValue() {
                System.out.println(stage.getHeight());
                return stage.getHeight();
            }

            @Override
            public void setValue(Double value) {
                stage.setHeight(value);
            }
        };
        
        Duration cycleDuration = Duration.millis(500);
        Timeline timeline = new Timeline(new KeyFrame(cycleDuration, 
        new KeyValue(writableHeight, 520d, Interpolator.EASE_IN)));
        timeline.play();
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
