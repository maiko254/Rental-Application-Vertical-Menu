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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TextField searchText;
    @FXML
    private Button clearButton;
    @FXML
    private TableView<TDModel> searchTable;
    @FXML
    private TableColumn<TDModel, String> houseNoCol;
    @FXML
    private TableColumn<TDModel, String> tenantNameCol;
    private TDController controller;
    
    private FXMLController scontroller = new FXMLController();
    
    String databaseURL = "jdbc:sqlite:C:\\NetbeansProjects\\SQLite\\RVM.db";
    
    public SearchFXMLController(TDController subcontroller){
        controller = subcontroller;
    }
    
    public SearchFXMLController(FXMLController sbcontroller){
        scontroller = sbcontroller;
    }

    
    public ObservableList<TDModel> initializeList() {
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
                    TDModel data = new TDModel(HNo, TName);
                    tenantList.add(data);
                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenantList;
    }

    ObservableList<TDModel> tenantList = FXCollections.observableArrayList();
    FilteredList<TDModel> filteredList = new FilteredList<>(tenantList);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        houseNoCol.setCellValueFactory(cellData -> cellData.getValue().houseNumberTableTDProperty());
        tenantNameCol.setCellValueFactory(cellData -> cellData.getValue().tenantNameTableTDProperty());
        initializeList();
        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate((t) -> {
                if (searchText.getText() == null || searchText.getText().isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (t.gettenantNameTableTD().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (t.gethouseNumberTableTD().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
            SortedList<TDModel> sortedData =new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(searchTable.comparatorProperty());
            searchTable.setItems(sortedData);
        });
        
        searchTable.setItems(filteredList);
        
        clearButton.setOnAction((event) -> {
            searchText.clear();
        });
    }    
    
}
