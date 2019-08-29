/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickdigitalsolutions.rentverticalmenu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author bonyo
 */
public class ME2Controller implements Initializable {

    public JFXTabPane tabContainer1;
    public Tab electricityExpense;
    public Tab waterExpense;
    public Tab otherExpenses;
    public JFXComboBox<String> elecCombo;
    public JFXComboBox<String> waterCombo;
    public JFXComboBox<String> otherCombo;
    public JFXTextField elecAmount;
    public JFXTextField waterAmount;
    public JFXTextField otherAmount;
    public JFXDatePicker elecDate;
    public JFXDatePicker waterDate;
    public JFXDatePicker otherDate;
    public JFXTextField elecUnits;
    public JFXTextField waterUnits;
    public JFXTextField expenseReason;
    public JFXButton elecSave;
    public JFXButton waterSave;
    public JFXButton otherSave;
    
    private double tabWidth = 90.0;
    String databaseURL = "jdbc:sqlite:C:\\Users\\bonyo\\Documents\\NetbeansProjects\\SQLite\\RVM.db";
    
    ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    
    public void createElecMonthlyExpensesTable(String Month, String Amount, String Date, String UnitsConsumed){
        try {
            String createTable = "CREATE TABLE IF NOT EXISTS ElectricityMonthlyExpenses(Month text, Amount text, Date text, UnitsConsumed text, PRIMARY KEY(Month, Date, UnitsConsumed))";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt =  conn.prepareStatement(createTable);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            String insertMonthlyExpense = "INSERT INTO ElectricityMonthlyExpenses(Month, Amount, Date, UnitsConsumed) VALUES(?,?,?,?)";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(insertMonthlyExpense);
            pstmt.setString(1, Month);
            pstmt.setString(2, Amount);
            pstmt.setString(3, Date);
            pstmt.setString(4, UnitsConsumed);
            pstmt.execute();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void saveElecMonthlyExpense(){
        createElecMonthlyExpensesTable(elecCombo.getSelectionModel().getSelectedItem(), elecAmount.getText(), getDateValueAsString(elecDate.getValue()), elecUnits.getText());
        setElecEmpty();
    }
    
    private void setElecEmpty(){
        elecAmount.setText("");
        elecDate.setValue(null);
        elecUnits.setText("");
    }
    
    private void setWaterEmpty(){
        waterAmount.setText("");
        waterDate.setValue(null);
        waterUnits.setText("");
    }
    
    private void setOtherEmpty(){
        otherAmount.setText("");
        otherDate.setValue(null);
        expenseReason.setText("");
    }
    
    public void createWaterMonthlyExpensesTable(String Month, String Amount, String Date, String UnitsConsumed){
        try {
            String createTable = "CREATE TABLE IF NOT EXISTS WaterMonthlyExpenses(Month text, Amount text, Date text, UnitsConsumed text, PRIMARY KEY(Month, Date, UnitsConsumed))";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt =  conn.prepareStatement(createTable);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            String insertMonthlyExpense = "INSERT INTO WaterMonthlyExpenses(Month, Amount, Date, UnitsConsumed) VALUES(?,?,?,?)";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(insertMonthlyExpense);
            pstmt.setString(1, Month);
            pstmt.setString(2, Amount);
            pstmt.setString(3, Date);
            pstmt.setString(4, UnitsConsumed);
            pstmt.execute();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void saveWaterMonthlyExpense(){
        createWaterMonthlyExpensesTable(waterCombo.getSelectionModel().getSelectedItem(), waterAmount.getText(), getDateValueAsString(waterDate.getValue()), waterUnits.getText());
        setWaterEmpty();
    }
    
    public void createOtherMonthlyExpensesTable(String Month, String Amount, String Date, String reasonForExpense){
        try {
            String createTable = "CREATE TABLE IF NOT EXISTS OtherMonthlyExpenses(Month text, Amount text, Date text, ReasonForExpense text, PRIMARY KEY(Month, Date, ReasonForExpense))";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt =  conn.prepareStatement(createTable);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            String insertMonthlyExpense = "INSERT INTO OtherMonthlyExpenses(Month, Amount, Date, ReasonForExpense) VALUES(?,?,?,?)";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(insertMonthlyExpense);
            pstmt.setString(1, Month);
            pstmt.setString(2, Amount);
            pstmt.setString(3, Date);
            pstmt.setString(4, reasonForExpense);
            pstmt.execute();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void saveOtherMonthlyExpense(){
        createOtherMonthlyExpensesTable(otherCombo.getSelectionModel().getSelectedItem(), otherAmount.getText(), getDateValueAsString(otherDate.getValue()), expenseReason.getText());
        setOtherEmpty();
    }
    
    private void configureView(){
        EventHandler<Event> replaceBackgroundColorHandler = (event) -> {
            Tab currentTab =  (Tab)event.getTarget();
            if (currentTab.isSelected()){
                currentTab.setStyle("-fx-background-color: -fx-focus-color;");
            } else {
                currentTab.setStyle("-fx-background-color: -fx-accent;");
            }
        };
        
        configureTab(electricityExpense, replaceBackgroundColorHandler);
        configureTab(waterExpense, replaceBackgroundColorHandler);
        configureTab(otherExpenses, replaceBackgroundColorHandler);
    }
    
    private void configureTab(Tab tab, EventHandler<Event> onSelectionChangedEvent){
        tab.setOnSelectionChanged(onSelectionChangedEvent);
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
       configureView();
       
       elecCombo.setItems(months);
       waterCombo.setItems(months);
       otherCombo.setItems(months);
       
       elecSave.setGraphic(GlyphsDude.createIconButton(MaterialDesignIcon.CONTENT_SAVE, "Save", "20", "14", ContentDisplay.TEXT_ONLY));
       elecSave.setPadding(Insets.EMPTY);
       waterSave.setGraphic(GlyphsDude.createIconButton(MaterialDesignIcon.CONTENT_SAVE, "Save", "20", "14", ContentDisplay.TEXT_ONLY));
       waterSave.setPadding(Insets.EMPTY);
       otherSave.setGraphic(GlyphsDude.createIconButton(MaterialDesignIcon.CONTENT_SAVE, "Save", "20", "14", ContentDisplay.TEXT_ONLY));
       otherSave.setPadding(Insets.EMPTY);
       
        elecCombo.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            elecCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    String searchElecTable = "SELECT * FROM ElectricityMonthlyExpenses WHERE MONTH = ?";
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchElecTable);
                    pstmt.setString(1, elecCombo.getSelectionModel().getSelectedItem());
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        elecAmount.setText("");
                        elecDate.setValue(null);
                        elecUnits.setText("");
                    } else {
                        do {
                            elecAmount.setText(rs.getString("Amount"));
                            elecDate.setValue(LocalDate.parse(rs.getString("Date"), DateTimeFormatter.ISO_DATE));
                            elecUnits.setText(rs.getString("UnitsConsumed"));
                        } while (rs.next());
                    }
                    pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        });
        
        waterCombo.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            waterCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    String searchWaterTable = "SELECT * FROM WaterMonthlyExpenses WHERE Month = ?";
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchWaterTable);
                    pstmt.setString(1, waterCombo.getSelectionModel().getSelectedItem());
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        waterAmount.setText("");
                        waterDate.setValue(null);
                        waterUnits.setText("");
                    } else {
                        do {
                            waterAmount.setText(rs.getString("Amount"));
                            waterDate.setValue(LocalDate.parse(rs.getString("Date"), DateTimeFormatter.ISO_DATE));
                            waterUnits.setText(rs.getString("UnitsConsumed"));
                        } while (rs.next());
                    }
                    pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        });
        
        otherCombo.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            otherCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    String searchOtherTable = "SELECT * FROM OtherMonthlyExpenses WHERE Month = ?";
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchOtherTable);
                    pstmt.setString(1, otherCombo.getSelectionModel().getSelectedItem());
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        otherAmount.setText("");
                        otherDate.setValue(null);
                        expenseReason.setText("");
                    } else {
                        do {                            
                          otherAmount.setText(rs.getString("Amount"));
                          otherDate.setValue(LocalDate.parse(rs.getString("Date"), DateTimeFormatter.ISO_DATE));
                          expenseReason.setText(rs.getString(rs.getString("ReasonForExpense")));
                        } while (rs.next());
                    }
                } catch (Exception e) {
                }
 
            });
        });
    }   
    
}
