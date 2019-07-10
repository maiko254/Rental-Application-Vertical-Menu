package com.clickdigitalsolutions.rentverticalmenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author bonyo
 */
public class SearchFXMLController implements Initializable {

    @FXML
    private BorderPane searchPane;
    @FXML
    private HBox searchHbox;
    @FXML
    private VBox searchVbox;
    @FXML
    private VBox textAreaVbox;
    @FXML
    private JFXRadioButton houseNumberButton;
    @FXML
    private JFXRadioButton tenantNameButton;
    
    String vboxLayout = "-fx-border-color: #d9dadb;\n" +
                        "-fx-border-insets: 5;\n" +
                        "-fx-border-width: 2;\n" +
                        "-fx-border-style: solid;\n";
    
    String vboxLayout1 = "-fx-border-color: #d9dadb;\n"
                         + "-fx-border-insets: 5;\n"
                         + "-fx-border-width: 2;\n"
                         + "-fx-border-style: solid;\n";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchHbox.setSpacing(3);
        searchVbox.setSpacing(5);
        searchVbox.setStyle(vboxLayout1);
        textAreaVbox.setStyle(vboxLayout);
        houseNumberButton.setPadding(new Insets(0, 0, 0, 5.0));
        tenantNameButton.setPadding(new Insets(0, 0, 0, 5.0));
    }    
    
}
