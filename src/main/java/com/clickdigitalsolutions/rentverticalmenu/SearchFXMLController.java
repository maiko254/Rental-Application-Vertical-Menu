package com.clickdigitalsolutions.rentverticalmenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField searchText;
    @FXML
    private ListView<TDModel> searchResultArea;
    
    private TDController controller;
    
    private FXMLController scontroller = new FXMLController();
    
    String databaseURL = "jdbc:sqlite:C:\\Users\\bonyo\\Documents\\NetbeansProjects\\SQLite\\RVM.db";
    
    String vboxLayout = "-fx-border-color: #d9dadb;\n" +
                        "-fx-border-insets: 5;\n" +
                        "-fx-border-width: 2;\n" +
                        "-fx-border-style: solid;\n";
    
    String vboxLayout1 = "-fx-border-color: #d9dadb;\n"
                         + "-fx-border-insets: 5;\n"
                         + "-fx-border-width: 2;\n"
                         + "-fx-border-style: solid;\n";
    
    public SearchFXMLController(TDController subcontroller){
        controller = subcontroller;
    }
    
    public SearchFXMLController(FXMLController sbcontroller){
        scontroller = sbcontroller;
    }
    
    public ObservableList<TDModel> initializeList() {
        ObservableList<TDModel> tdSearchList = FXCollections.observableArrayList();
        try {
            String searchTDTable = "SELECT * FROM TenantDetails";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(searchTDTable);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Not it");
            } else {
                do {
                    String HNo = rs.getString("HouseNumber");
                    String TName = rs.getString("TenantName");
                    TDModel data = new TDModel(TName);
                    tdSearchList.add(data);
                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdSearchList;
    }
    
    ObservableList<TDModel> tenanList = FXCollections.observableArrayList(new TDModel("Michael Odhiambo"), new TDModel("Akello Aggie"), new TDModel("Vincentia Fiona Akinyi"), new TDModel("Atieno Beryl"), new TDModel("Mathew Onguti"), new TDModel("Athieno"));
    public void filterTenantList(String oldValue, String newValue) {
        ObservableList<TDModel> filteredList = FXCollections.observableArrayList();
        if (searchText == null || newValue == null || newValue.isEmpty() ) {
            searchResultArea.setItems(initializeList());
        } else {
            newValue = newValue.toUpperCase();
            for (TDModel Person : searchResultArea.getItems()) {
                String filterText = Person.getTenantNameTD();
                if (filterText.contains(newValue)) {
                    filteredList.add(Person);
                    searchResultArea.setItems(filteredList);
                } else if (newValue.length() < oldValue.length()) {
                    searchResultArea.setItems(filteredList);
                }
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchResultArea.setItems(initializeList());
        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTenantList(oldValue, newValue);
        });
    }    
    
}
