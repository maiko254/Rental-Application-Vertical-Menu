package com.clickdigitalsolutions.rentverticalmenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mike254
 */
public class TDController implements Initializable {

    @FXML
    private JFXComboBox houseCombo;
    
    @FXML
    private JFXTextField tenantName;
    
    @FXML
    private JFXTextField tenantPhoneNumber;
    
    @FXML
    private JFXTextField monthlyRent;
    
    @FXML
    private JFXTextField houseDeposit;
    
    @FXML
    private JFXTextField dueDate;
    
    @FXML
    private DatePicker moveInDate;
    
    @FXML
    private DatePicker moveOutDate;
    
    @FXML
    private DatePicker leaseStartDate;
    
    @FXML
    private DatePicker leaseEndDate;
    
    @FXML
    private JFXButton saveButton;
    
    @FXML
    private TitledPane houseComboTitledPane;
    
    @FXML
    private AnchorPane houseComboAnchorPane;
    
    @FXML
    private JFXComboBox blockACombo;
    
    @FXML
    private JFXComboBox blockBCombo;
    
    @FXML
    private JFXComboBox blockCCombo;
    
    @FXML
    private JFXComboBox nasraBlockCombo;
    
    ObservableList<String>blockB = FXCollections.observableArrayList("B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12");
    ObservableList<String>blockA = FXCollections.observableArrayList("A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12");
    ObservableList<String>blockC = FXCollections.observableArrayList("C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10");
    ObservableList<String>nasraBlock = FXCollections.observableArrayList("Top House", "Bottom House");
    
    String url = "jdbc:sqlite:C:\\Users\\Mike254\\Documents\\NetbeansProjects\\SQLite\\RVM.db";
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        blockACombo.setItems(blockA);
        blockBCombo.setItems(blockB);
        blockCCombo.setItems(blockC);
        nasraBlockCombo.setItems(nasraBlock);
    }    
    
}
