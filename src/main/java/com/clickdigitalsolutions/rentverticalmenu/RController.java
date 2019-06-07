/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickdigitalsolutions.rentverticalmenu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

/**
 * FXML Controller class
 *
 * @author Mike254
 */
public class RController implements Initializable {

    @FXML
    private TitledPane houseComboTitledPaneR;
    @FXML
    private JFXComboBox<String> blockAComboR;
    @FXML
    private JFXComboBox<String> blockBComboR;
    @FXML
    private JFXComboBox<String> blockCComboR;
    @FXML
    private JFXComboBox<String> nasraBlockR;
    @FXML
    private JFXTextField tenantNameR;
    @FXML
    private JFXTextField repairsDoneR;
    @FXML
    private JFXTextField costOfRepairR;
    @FXML
    private JFXDatePicker repairDateR;
    @FXML
    private JFXTextField miscellaneousR;
    @FXML
    private JFXButton saveButtonR;
    
    private String comboboxRCheck;
    
    ObservableList<String>blockB = FXCollections.observableArrayList("B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12");
    ObservableList<String>blockA = FXCollections.observableArrayList("A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12");
    ObservableList<String>blockC = FXCollections.observableArrayList("C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10");
    ObservableList<String>nasraBlock = FXCollections.observableArrayList("Top House", "Bottom House");

    String databaseURL = "jdbc:sqlite:C:\\Users\\Mike254\\Documents\\NetbeansProjects\\SQLite\\RVM.db";
    
    public void createRepairsTable(String HouseNumber, String TenantName, String Repairs, String CostOfRepairs, String DateOfRepairs, String Miscellaneous){
        try {
            String createRepairsTableSql = "CREATE TABLE IF NOT EXISTS RepairsTable(HouseNumber text, TenantName text, Repairs text, CostOfRepairs text, DateOfRepairs text, MiscellaneousExpenses text, PRIMARY KEY(HouseNumber, DateOfRepairs), FOREIGN KEY(HouseNumber) REFERENCES TenantDetails(HouseNumber) ON DELETE CASCADE)";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(createRepairsTableSql);
            pstmt.execute();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            String insertRepairsTableSql = "INSERT INTO RepairsTable(HouseNumber, TenantName, Repairs, CostOfRepairs, DateOfRepairs, MiscellaneousExpenses) VALUES(?,?,?,?,?,?)";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(insertRepairsTableSql);
            pstmt.setString(1, HouseNumber);
            pstmt.setString(2, TenantName);
            pstmt.setString(3, Repairs);
            pstmt.setString(4, CostOfRepairs);
            pstmt.setString(5, DateOfRepairs);
            pstmt.setString(6, Miscellaneous);
            pstmt.execute();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
        }
    }
    
    private void setEmpty(){
        tenantNameR.setText("");
        repairsDoneR.setText("");
        costOfRepairR.setText("");
        repairDateR.setValue(null);
        miscellaneousR.setText("");
    }
    
    @FXML
    private void saveButtonActionR(){
        if (comboboxRCheck.equals("Block A")){
            createRepairsTable((String)blockAComboR.getSelectionModel().getSelectedItem(), tenantNameR.getText(), repairsDoneR.getText(), costOfRepairR.getText(), getDateValueAsString(repairDateR.getValue()), miscellaneousR.getText());
            setEmpty();
        }
    }
    
    public String getDateValueAsString(LocalDate dateConvert){
        String repairsDateString = null;
        if (dateConvert == null) {
            repairsDateString = null;
        } else if (dateConvert != null) {
            repairsDateString = dateConvert.format(DateTimeFormatter.ISO_DATE);
        }
        return repairsDateString;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        blockAComboR.setItems(blockA);
        blockBComboR.setItems(blockB);
        blockCComboR.setItems(blockC);
        nasraBlockR.setItems(nasraBlock);
        
        houseComboTitledPaneR.setOnMouseClicked((event) -> {
            blockAComboR.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String searchRepairsSql = "SELECT * FROM RepairsTable WHERE HouseNumber = ?";
                try {
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchRepairsSql);
                    pstmt.setString(1, (String)blockAComboR.getSelectionModel().getSelectedItem());
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()){
                        setEmpty();
                    }else 
                        do {                            
                            tenantNameR.setText(rs.getString("TenantName"));
                            repairsDoneR.setText(rs.getString("Repairs"));
                            costOfRepairR.setText(rs.getString("CostOfRepairs"));
                            if (rs.getString("DateOfRepairs") != null){
                                repairDateR.setValue(LocalDate.parse(rs.getString("DateOfRepairs"), DateTimeFormatter.ISO_DATE));
                            }else
                                repairDateR.setValue(null);
                            miscellaneousR.setText(rs.getString("MiscellaneousExpenses"));
                        } while (rs.next());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                comboboxRCheck = "Block A";
                Label label = new Label();
                label.setText((String)blockAComboR.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: red;");
                houseComboTitledPaneR.setGraphic(label);
                houseComboTitledPaneR.setExpanded(false);
            });
        });
    }    
    
}
