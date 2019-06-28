package com.clickdigitalsolutions.rentverticalmenu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;

public class FXMLController implements Initializable {

    @FXML
    private JFXTabPane tabContainer;

    @FXML
    private Tab tenantDetailsTab;

    @FXML
    private AnchorPane tenantDetailsContainer;

    @FXML
    private Tab repairsTab;

    @FXML
    private AnchorPane repairsContainer;

    @FXML
    private Tab paymentDetailsTab;
    
    @FXML
    private AnchorPane paymentContainer;
    
    @FXML
    private Tab monthlyExpensesTab;
    
    @FXML
    private AnchorPane monthlyContainer;
    
    @FXML
    private MenuBar rentalMenu;
    
    @FXML
    private AnchorPane motherAnchor;
    
    @FXML
    private MenuItem editTable;
    
    @FXML
    private MenuItem search;
    
    @FXML
    private MenuItem Import;
    
    @FXML
    private MenuItem about;
    
    private double tabWidth = 90.0;
    public static int lastSelectedTabIndex = 0;
    
    String fxmlCheck;

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

        
        configureTab(tenantDetailsTab, "Tenant\nDetails", "/images/icons8_user_48px.png", tenantDetailsContainer, getClass().getResource("/fxml/TD.fxml"), replaceBackgroundColorHandler);
        configureTab(repairsTab, "Repairs", "/images/icons8_house_48px.png", repairsContainer, getClass().getResource("/fxml/R.fxml"), replaceBackgroundColorHandler);
        configureTab(paymentDetailsTab, "Payment\nDetails", "/images/icons8_sell_property_48px.png", paymentContainer, getClass().getResource("/fxml/PD.fxml"), replaceBackgroundColorHandler);
        configureTab(monthlyExpensesTab, "Monthly\nExpenses", "/images/icons8_overtime_48px.png", monthlyContainer, getClass().getResource("/fxml/ME.fxml"), replaceBackgroundColorHandler);
        tenantDetailsTab.setStyle("-fx-background-color: -fx-focus-color;");
    }

    private void configureTab(Tab tab, String title, String iconPath, AnchorPane containerPane, URL resourceURL, EventHandler<Event> onSelectionChangedEvent) {
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
    
    private JFXButton createIconButton(JFXButton iconButton, double iconXLayout, double iconYLayout, String fxmlShowing) {
        if (fxmlShowing.equals("PDfxml")) {
            iconButton.setPrefSize(28, 26);
            iconButton.setGraphic(GlyphsDude.createIconButton(MaterialIcon.ADD, "", "20", "12", ContentDisplay.GRAPHIC_ONLY));
            iconButton.setLayoutX(iconXLayout);
            iconButton.setLayoutY(iconYLayout);
            iconButton.setVisible(true);
        } else {
            iconButton.setVisible(false);
        }
        return iconButton;
    }

    PDController controller = new PDController();
    
    @FXML
    private void editTableEntryAction() {
        Scene mpScene = (Scene)motherAnchor.getScene();
        if (fxmlCheck.equals("PDfxml")){
          JFXButton addButton = (JFXButton) mpScene.lookup("#updateAmountButton"); 
            
            motherAnchor.getChildren().add(createIconButton(new JFXButton(), 510.0, 225.0, fxmlCheck));
        }
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
        
    }
}
