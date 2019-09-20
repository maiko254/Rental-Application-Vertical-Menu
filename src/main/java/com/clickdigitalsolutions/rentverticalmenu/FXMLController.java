package com.clickdigitalsolutions.rentverticalmenu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.oasis.Utility;
import net.sf.jasperreports.view.JasperViewer;

public class FXMLController implements Initializable {

    @FXML
    private JFXTabPane tabContainer;
    
    @FXML
    private StackPane mainStack;

    @FXML
    private Tab tenantDetailsTab;

    @FXML
    private GridPane tenantDetailsContainer;

    @FXML
    private Tab repairsTab;

    @FXML
    private GridPane repairsContainer;

    @FXML
    private Tab paymentDetailsTab;

    @FXML
    private GridPane paymentContainer;

    @FXML
    private Tab monthlyExpensesTab;

    @FXML
    private GridPane monthlyContainer;

    @FXML
    private MenuBar rentalMenu;

    @FXML
    private BorderPane motherAnchor;

    @FXML
    private MenuItem editTable;

    @FXML
    private MenuItem search;

    @FXML
    private MenuItem Import;

    @FXML
    private MenuItem print;

    @FXML
    private MenuItem about;

    private double tabWidth = 90.0;
    public static int lastSelectedTabIndex = 0;

    String fxmlCheck;

    JFXButton addButton;

    private SearchFXMLController searchController;

    private void configureView() {
        tabContainer.setTabMinWidth(tabWidth);
        tabContainer.setTabMaxWidth(tabWidth);
        tabContainer.setTabMinHeight(tabWidth);
        tabContainer.setTabMaxHeight(tabWidth);
        tabContainer.setRotateGraphic(true);

        EventHandler<Event> replaceBackgroundColorHandler = event -> {
            lastSelectedTabIndex = tabContainer.getSelectionModel().getSelectedIndex();

            Tab currentTab = (Tab) event.getTarget();
            if (currentTab.isSelected()) {
                currentTab.setStyle("-fx-background-color: -fx-focus-color;");
            } else {
                currentTab.setStyle("-fx-background-color: -fx-accent;");
            }
        };

        configureViewTab(tenantDetailsTab, "Tenant\nDetails", "/images/icons8_user_48px.png", getClass().getResource("/fxml/TD2.fxml"), replaceBackgroundColorHandler);
        configureViewTab(repairsTab, "Repairs", "/images/icons8_house_48px.png", getClass().getResource("/fxml/Repairs.fxml"), replaceBackgroundColorHandler);
        configureViewTab(paymentDetailsTab, "Payment\nDetails", "/images/icons8_sell_property_48px.png", getClass().getResource("/fxml/PD2.fxml"), replaceBackgroundColorHandler);
        configureViewTab(monthlyExpensesTab, "Monthly\nExpenses", "/images/icons8_overtime_48px.png", getClass().getResource("/fxml/Monthly Expense.fxml"), replaceBackgroundColorHandler);
        tenantDetailsTab.setStyle("-fx-background-color: -fx-focus-color;");
    }

    private void configureTab(Tab tab, String title, String iconPath, GridPane containerPane, URL resourceURL, EventHandler<Event> onSelectionChangedEvent) {
        double imageWidth = 40.0;

        ImageView imageView = new ImageView(new Image(iconPath));
        imageView.setFitHeight(imageWidth);
        imageView.setFitWidth(imageWidth);

        Label label = new Label(title);
        label.setMaxWidth(tabWidth - 20);
        label.setPadding(new Insets(5, 0, 0, 0));
        label.setStyle("-fx-text-fill: black; -fx-font-size: 8pt; -fx-font-weight: normal;");
        label.setTextAlignment(TextAlignment.CENTER);

        BorderPane tabPane = new BorderPane();
        tabPane.setRotate(90.0);
        tabPane.setMaxWidth(tabWidth);
        tabPane.setCenter(imageView);
        tabPane.setBottom(label);

        tab.setText("");
        tab.setGraphic(tabPane);

        tab.setOnSelectionChanged(onSelectionChangedEvent);

        if (containerPane != null && resourceURL != null) {
            try {
                Parent contentView = FXMLLoader.load(resourceURL);
                
                containerPane.getChildren().add(contentView);
                AnchorPane.setTopAnchor(contentView, 0.0);
                AnchorPane.setBottomAnchor(contentView, 0.0);
                AnchorPane.setRightAnchor(contentView, 0.0);
                AnchorPane.setLeftAnchor(contentView, 0.0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void configureViewTab(Tab tab, String title, String iconPath, URL resourceURL, EventHandler<Event> onSelectionChangedEvent){
        double imageWidth = 40.0;

        ImageView imageView = new ImageView(new Image(iconPath));
        imageView.setFitHeight(imageWidth);
        imageView.setFitWidth(imageWidth);

        Label label = new Label(title);
        label.setMaxWidth(tabWidth - 20);
        label.setPadding(new Insets(5, 0, 0, 0));
        label.setStyle("-fx-text-fill: black; -fx-font-size: 8pt; -fx-font-weight: normal;");
        label.setTextAlignment(TextAlignment.CENTER);

        BorderPane tabPane = new BorderPane();
        tabPane.setRotate(90.0);
        tabPane.setMaxWidth(tabWidth);
        tabPane.setCenter(imageView);
        tabPane.setBottom(label);

        tab.setText("");
        tab.setGraphic(tabPane);

        tab.setOnSelectionChanged(onSelectionChangedEvent);
        
        if (resourceURL != null) {
            try {
                Parent contentView = FXMLLoader.load(resourceURL);
                tab.setContent(contentView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private JFXButton createIconButton(double iconXLayout, double iconYLayout) {
        JFXButton iconButton = new JFXButton();

        iconButton.setPrefSize(28, 26);
        iconButton.setGraphic(GlyphsDude.createIconButton(MaterialIcon.ADD, "", "20", "12", ContentDisplay.GRAPHIC_ONLY));
        iconButton.setLayoutX(iconXLayout);
        iconButton.setLayoutY(iconYLayout);
        iconButton.setVisible(true);
        iconButton.setId("updateAmount");

        return iconButton;
    }

    @FXML
    private void searchAction() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/SearchFXML.fxml"));
        SearchFXMLController sub = new SearchFXMLController(this);
        loader.setController(sub);
        Parent root = loader.load();
        Scene searchScene = new Scene(root);
        Stage window = new Stage();
        window.setScene(searchScene);
        window.setTitle("Search...");
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
    }

    PDController subcontroller = new PDController();
    
    private Map getReceiptParameters(){
        HashMap map = new HashMap();
        map.put("houseNumber", (String)subcontroller.blockAComboPD.getSelectionModel().getSelectedItem());
        map.put("PayMonth", (String)subcontroller.monthComboPD.getSelectionModel().getSelectedItem());
        return map;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configureView();

        rentalMenu.prefWidthProperty().bind(motherAnchor.widthProperty());

        tabContainer.setOnMouseClicked((event) -> {
            if (tenantDetailsTab.isSelected()) {
                fxmlCheck = "TDfxml";
            } else if (repairsTab.isSelected()) {
                fxmlCheck = "Rfxml";
            } else if (paymentDetailsTab.isSelected()) {
                fxmlCheck = "PDfxml";
            } else if (monthlyExpensesTab.isSelected()) {
                fxmlCheck = "MEfxml";
            }
        });
        String hNo = "A1";
        String Month = "July";
        print.setOnAction((event) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PD2.fxml"));
            PDModel pdmodel = new PDModel();
            Map map = null;

            try {
                map = getReceiptParameters();
            } catch (Exception e) {
                e.printStackTrace();
                String message = "An Error occured while compiling the report";
                Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
                alert.setTitle("Error occured");
                alert.setHeaderText("Error in retrieving data for printing the receipt");
                alert.showAndWait();
                return;
            }

            final String resourcePath = "/Receipt/Receipt.jasper";
            JasperPrint jasperPrint = null;

            try (InputStream reportStream = this.getClass().getResourceAsStream(resourcePath)) {
                jasperPrint = JasperFillManager.fillReport(reportStream, map);
            } catch (Exception e) {
                String message = "An Error occurred while preparing to print the receipt";
                Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
                alert.setTitle("Error Occurred");
                alert.setHeaderText("Error in printing the receipt");
                alert.showAndWait();
            }
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setTitle("Rent Receipt");
            jasperViewer.setVisible(true);
        });
    }
}
