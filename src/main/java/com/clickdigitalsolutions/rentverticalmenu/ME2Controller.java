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
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author bonyo
 */
public class ME2Controller implements Initializable {

    public TabPane tabContainer1;
    public BorderPane monthGridPane;
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
    public Button saveMonthlyExpense;
    public TableView<MonthlyModel> monthlyElectricExpenseTable = new TableView<>();
    public TableView<waterMonthlyModel> waterExpenseTable = new TableView<>();
    public TableView<otherMonthlyModel> otherExpenseTable = new TableView<>();
    public BorderPane electricityAnchor;
    public BorderPane waterAnchor;
    public BorderPane otherAnchor;
    public AnchorPane savePane;
    
    
    private double tabWidth = 90.0;
    String databaseURL = "jdbc:sqlite:C:\\Users\\bonyo\\Documents\\NetbeansProjects\\SQLite\\RVM.db";
    String tabCheck = null;
    
    ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    
    public static class MonthlyModel {

        public SimpleStringProperty monthME;
        public SimpleStringProperty amountPaidME;
        public SimpleStringProperty datePaidME;
        public SimpleStringProperty unitsConsumedME;

        public MonthlyModel(String month, String amountPaid, String datePaid, String unitsConsumed) {
            this.monthME = new SimpleStringProperty(month);
            this.amountPaidME = new SimpleStringProperty(amountPaid);
            this.datePaidME = new SimpleStringProperty(datePaid);
            this.unitsConsumedME = new SimpleStringProperty(unitsConsumed);
        }

        public String getMonthME() {
            return this.monthME.get();
        }

        public void setMonthME(String value) {
            this.monthME.set(value);
        }

        public StringProperty monthMEProperty() {
            return this.monthME;
        }

        public String getAmountPaidME() {
            return this.amountPaidME.get();
        }

        public void setAmountPaidME(String value) {
            this.amountPaidME.set(value);
        }

        public StringProperty amountPaidMEProperty() {
            return amountPaidME;
        }

        public String getDatePaidME() {
            return this.datePaidME.get();
        }

        public void setDatePaidME(String value) {
            this.datePaidME.set(value);
        }

        public StringProperty datePaidMEProperty() {
            return this.datePaidME;
        }

        public String getUnitsConsumedME() {
            return this.unitsConsumedME.get();
        }

        public void setUnitsConsumedME(String value) {
            this.unitsConsumedME.set(value);
        }

        public StringProperty unitsConsumedMEProperty() {
            return this.unitsConsumedME;
        }
    }
    
    public static class waterMonthlyModel {

        public SimpleStringProperty monthME;
        public SimpleStringProperty amountPaidME;
        public SimpleStringProperty datePaidME;
        public SimpleStringProperty unitsConsumedME;

        public waterMonthlyModel(String month, String amountPaid, String datePaid, String unitsConsumed) {
            this.monthME = new SimpleStringProperty(month);
            this.amountPaidME = new SimpleStringProperty(amountPaid);
            this.datePaidME = new SimpleStringProperty(datePaid);
            this.unitsConsumedME = new SimpleStringProperty(unitsConsumed);
        }

        public String getMonthME() {
            return this.monthME.get();
        }

        public void setMonthME(String value) {
            this.monthME.set(value);
        }

        public StringProperty monthMEProperty() {
            return this.monthME;
        }

        public String getAmountPaidME() {
            return this.amountPaidME.get();
        }

        public void setAmountPaidME(String value) {
            this.amountPaidME.set(value);
        }

        public StringProperty amountPaidMEProperty() {
            return amountPaidME;
        }

        public String getDatePaidME() {
            return this.datePaidME.get();
        }

        public void setDatePaidME(String value) {
            this.datePaidME.set(value);
        }

        public StringProperty datePaidMEProperty() {
            return this.datePaidME;
        }

        public String getUnitsConsumedME() {
            return this.unitsConsumedME.get();
        }

        public void setUnitsConsumedME(String value) {
            this.unitsConsumedME.set(value);
        }

        public StringProperty unitsConsumedMEProperty() {
            return this.unitsConsumedME;
        }
    }
    
    public static class otherMonthlyModel {

        public SimpleStringProperty monthME;
        public SimpleStringProperty amountPaidME;
        public SimpleStringProperty datePaidME;
        public SimpleStringProperty unitsConsumedME;

        public otherMonthlyModel(String month, String amountPaid, String datePaid, String unitsConsumed) {
            this.monthME = new SimpleStringProperty(month);
            this.amountPaidME = new SimpleStringProperty(amountPaid);
            this.datePaidME = new SimpleStringProperty(datePaid);
            this.unitsConsumedME = new SimpleStringProperty(unitsConsumed);
        }

        public String getMonthME() {
            return this.monthME.get();
        }

        public void setMonthME(String value) {
            this.monthME.set(value);
        }

        public StringProperty monthMEProperty() {
            return this.monthME;
        }

        public String getAmountPaidME() {
            return this.amountPaidME.get();
        }

        public void setAmountPaidME(String value) {
            this.amountPaidME.set(value);
        }

        public StringProperty amountPaidMEProperty() {
            return amountPaidME;
        }

        public String getDatePaidME() {
            return this.datePaidME.get();
        }

        public void setDatePaidME(String value) {
            this.datePaidME.set(value);
        }

        public StringProperty datePaidMEProperty() {
            return this.datePaidME;
        }

        public String getUnitsConsumedME() {
            return this.unitsConsumedME.get();
        }

        public void setUnitsConsumedME(String value) {
            this.unitsConsumedME.set(value);
        }

        public StringProperty unitsConsumedMEProperty() {
            return this.unitsConsumedME;
        }
    }
    
    public void createElecMonthlyExpensesTable(String Month, String Amount, String Date, String UnitsConsumed){
        try {
            String createTable = "CREATE TABLE IF NOT EXISTS ElectricityMonthlyExpenses(Month text, Amount text, Date text, UnitsConsumed text, PRIMARY KEY(Month, Date, UnitsConsumed))";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt =  conn.prepareStatement(createTable);
            pstmt.execute();
            pstmt.close();
            conn.close();
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
    private void saveMonthlyExpense() {
        if (electricityExpense.isSelected()) {
            createElecMonthlyExpensesTable(elecCombo.getSelectionModel().getSelectedItem(), elecAmount.getText(), getDateValueAsString(elecDate.getValue()), elecUnits.getText());
            setElecEmpty();
        } else if (waterExpense.isSelected()) {
            createWaterMonthlyExpensesTable(waterCombo.getSelectionModel().getSelectedItem(), waterAmount.getText(), getDateValueAsString(waterDate.getValue()), waterUnits.getText());
            setWaterEmpty();
        } else if (otherExpenses.isSelected()) {
            createOtherMonthlyExpensesTable(otherCombo.getSelectionModel().getSelectedItem(), otherAmount.getText(), getDateValueAsString(otherDate.getValue()), expenseReason.getText());
            setOtherEmpty();
        }
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
    
    
    private void configureView(){
        EventHandler<Event> replaceBackgroundColorHandler = (event) -> {
            Tab currentTab =  (Tab)event.getTarget();
            if (currentTab.isSelected()){
                currentTab.setStyle("-fx-background-color: #ababab;");
            } else {
                currentTab.setStyle("-fx-background-color: #ababab;");
            }
        };
        
        configureTab(electricityExpense, replaceBackgroundColorHandler);
        configureTab(waterExpense, replaceBackgroundColorHandler);
        configureTab(otherExpenses, replaceBackgroundColorHandler);
    }
    
    private void configureTab(Tab tab, EventHandler<Event> onSelectionChangedEvent){
        tab.setOnSelectionChanged(onSelectionChangedEvent);
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
    
    public String getDateValueAsString(LocalDate dateConvert){
        String repairsDateString = null;
        if (dateConvert == null) {
            repairsDateString = null;
        } else if (dateConvert != null) {
            repairsDateString = dateConvert.format(DateTimeFormatter.ISO_DATE);
        }
        return repairsDateString;
    }
    
    
    public ObservableList<MonthlyModel> getElectricityMonthlyDetails() {
        ObservableList<MonthlyModel> monthlyData = FXCollections.observableArrayList();
        if (tabCheck.equals("Electricity")) {
            try {
                String searchElecTable = "SELECT * FROM ElectricityMonthlyExpenses WHERE Month = ?";
                Connection conn = DriverManager.getConnection(databaseURL);
                PreparedStatement pstmt = conn.prepareStatement(searchElecTable);
                pstmt.setString(1, elecCombo.getSelectionModel().getSelectedItem());
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {                    
                    String month = rs.getString("Month");
                    String amount = rs.getString("Amount");
                    String date = rs.getString("Date");
                    String units = rs.getString("UnitsConsumed");
                    MonthlyModel monthlyDataModel = new MonthlyModel(month, amount, date, units);
                    monthlyData.add(monthlyDataModel);
                }
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Electricity");
        }  
        return monthlyData;
    }
    
    public ObservableList<waterMonthlyModel> getWaterMonthlyDetails() {
        ObservableList<waterMonthlyModel> monthlyData = FXCollections.observableArrayList();
        if (tabCheck.equals("Water")){
            try {
                String searchWaterTable = "SELECT * FROM WaterMonthlyExpenses WHERE Month = ?";
                Connection conn = DriverManager.getConnection(databaseURL);
                PreparedStatement pstmt = conn.prepareStatement(searchWaterTable);
                pstmt.setString(1, waterCombo.getSelectionModel().getSelectedItem());
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {                    
                    String month = rs.getString("Month");
                    String amount = rs.getString("Amount");
                    String date = rs.getString("Date");
                    String units = rs.getString("UnitsConsumed");
                    waterMonthlyModel monthlyDataModel = new waterMonthlyModel(month, amount, date, units);
                    monthlyData.add(monthlyDataModel);
                }
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Water");
        } 
        return monthlyData;
    }
    
    public ObservableList<otherMonthlyModel> getOtherMonthlyDetails() {
        ObservableList<otherMonthlyModel> monthlyData = FXCollections.observableArrayList();
        if (tabCheck.equals("Other")){
            try {
                String searchOtherTable = "SELECT * FROM OtherMonthlyExpenses WHERE Month = ?";
                Connection conn = DriverManager.getConnection(databaseURL);
                PreparedStatement pstmt = conn.prepareStatement(searchOtherTable);
                pstmt.setString(1, otherCombo.getSelectionModel().getSelectedItem());
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {                    
                   String month = rs.getString("Month");
                   String amount = rs.getString("Amount");
                   String date = rs.getString("Date");
                   String reason = rs.getString("ReasonForExpense");
                   otherMonthlyModel monthlyDataModel = new otherMonthlyModel(month, amount, date, reason);
                   monthlyData.add(monthlyDataModel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Other");
        }
        return monthlyData;
    }
    
    class MyStringTableCell extends TableCell<MonthlyModel, String> {

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? null : getString());
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
    
    
    class MyEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {
            if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2 && electricityExpense.isSelected()) {
                TableCell c = (TableCell) t.getSource();
                int index = c.getIndex();
                try {
                    MonthlyModel item = getElectricityMonthlyDetails().get(index);
                    elecAmount.setText(item.getAmountPaidME());
                    elecDate.setValue(LocalDate.parse(item.getDatePaidME(), DateTimeFormatter.ISO_DATE));
                    elecUnits.setText(item.getUnitsConsumedME());
                    System.out.println("Month = " + item.getMonthME());
                    System.out.println("Amount Paid = " + item.getAmountPaidME());
                    System.out.println("Date = " + item.getDatePaidME());
                    System.out.println("Units Consumed = " + item.getUnitsConsumedME());
                } catch (Exception e) {
                }
            } else if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2 && waterExpense.isSelected()) {
                TableCell c = (TableCell) t.getSource();
                int index = c.getIndex();
                try {
                    waterMonthlyModel item = getWaterMonthlyDetails().get(index);
                    waterAmount.setText(item.getAmountPaidME());
                    waterDate.setValue(LocalDate.parse(item.getDatePaidME(), DateTimeFormatter.ISO_DATE));
                    waterUnits.setText(item.getUnitsConsumedME());
                    System.out.println("Month = " + item.getMonthME());
                    System.out.println("Amount Paid = " + item.getAmountPaidME());
                    System.out.println("Date = " + item.getDatePaidME());
                    System.out.println("Units Consumed = " + item.getUnitsConsumedME());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2 && otherExpenses.isSelected()){
                TableCell c = (TableCell) t.getSource();
                int index = c.getIndex();
                try {
                    otherMonthlyModel item = getOtherMonthlyDetails().get(index);
                    otherAmount.setText(item.getAmountPaidME());
                    otherDate.setValue(LocalDate.parse(item.getDatePaidME(), DateTimeFormatter.ISO_DATE));
                    expenseReason.setText(item.getUnitsConsumedME());
                    System.out.println("Month = " + item.getMonthME());
                    System.out.println("Amount Paid = " + item.getAmountPaidME());
                    System.out.println("Date = " + item.getDatePaidME());
                    System.out.println("Reason For Expense = " + item.getUnitsConsumedME());
                } catch (Exception e) {
                }
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       configureView();
       
       elecCombo.setItems(months);
       waterCombo.setItems(months);
       otherCombo.setItems(months);
   
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
                tabCheck = "Electricity";
                monthlyElectricExpenseTable.setItems(getElectricityMonthlyDetails());
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
                tabCheck = "Water";
                waterExpenseTable.setItems(getWaterMonthlyDetails());
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
                            expenseReason.setText((rs.getString("ReasonForExpense")));
                        } while (rs.next());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                tabCheck = "Other";
                otherExpenseTable.setItems(getOtherMonthlyDetails());
            });
        });
        
        Callback<TableColumn, TableCell> stringCellFactory
                = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                MyStringTableCell cell = new MyStringTableCell();
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
                return cell;
            }
        };
        
        TableColumn monthCol = new TableColumn("Month");
        monthCol.setPrefWidth(tabWidth);
        monthCol.setCellValueFactory(
                new PropertyValueFactory<MonthlyModel, String>("monthME"));
        monthCol.setCellFactory(stringCellFactory);
        TableColumn amountCol = new TableColumn("Amount Paid");
        amountCol.setPrefWidth(120);
        amountCol.setCellValueFactory(
                new PropertyValueFactory<MonthlyModel, String>("amountPaidME"));
        amountCol.setCellFactory(stringCellFactory);
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setPrefWidth(tabWidth);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<MonthlyModel, String>("datePaidME"));
        dateCol.setCellFactory(stringCellFactory);
        TableColumn unitsCol = new TableColumn("Units Consumed");
        unitsCol.setPrefWidth(205);
        unitsCol.setCellValueFactory(
                new PropertyValueFactory<MonthlyModel, String>("unitsConsumedME"));
        unitsCol.setCellFactory(stringCellFactory);
        
        TableColumn monthWaterCol = new TableColumn("Month");
        monthWaterCol.setPrefWidth(tabWidth);
        monthWaterCol.setCellValueFactory(
                new PropertyValueFactory<waterMonthlyModel, String>("monthME"));
        monthWaterCol.setCellFactory(stringCellFactory);
        TableColumn amountWaterCol = new TableColumn("Amount Paid");
        amountWaterCol.setPrefWidth(120);
        amountWaterCol.setCellValueFactory(
                new PropertyValueFactory<waterMonthlyModel, String>("amountPaidME"));
        amountWaterCol.setCellFactory(stringCellFactory);
        TableColumn dateWaterCol = new TableColumn("Date");
        dateWaterCol.setPrefWidth(tabWidth);
        dateWaterCol.setCellValueFactory(
                new PropertyValueFactory<waterMonthlyModel, String>("datePaidME"));
        dateWaterCol.setCellFactory(stringCellFactory);
        TableColumn unitsWaterCol = new TableColumn("Units Consumed");
        unitsWaterCol.setPrefWidth(205);
        unitsWaterCol.setCellValueFactory(
                new PropertyValueFactory<waterMonthlyModel, String>("unitsConsumedME"));
        unitsWaterCol.setCellFactory(stringCellFactory);
        
        TableColumn monthOtherCol = new TableColumn("Month");
        monthOtherCol.setPrefWidth(tabWidth);
        monthOtherCol.setCellValueFactory(
                new PropertyValueFactory<waterMonthlyModel, String>("monthME"));
        monthOtherCol.setCellFactory(stringCellFactory);
        TableColumn amountOtherCol = new TableColumn("Amount Paid");
        amountOtherCol.setPrefWidth(120);
        amountOtherCol.setCellValueFactory(
                new PropertyValueFactory<waterMonthlyModel, String>("amountPaidME"));
        amountOtherCol.setCellFactory(stringCellFactory);
        TableColumn dateOtherCol = new TableColumn("Date");
        dateOtherCol.setPrefWidth(tabWidth);
        dateOtherCol.setCellValueFactory(
                new PropertyValueFactory<waterMonthlyModel, String>("datePaidME"));
        dateOtherCol.setCellFactory(stringCellFactory);
        TableColumn reasonOtherCol = new TableColumn("Reason For Expense");
        reasonOtherCol.setPrefWidth(205);
        reasonOtherCol.setCellValueFactory(
                new PropertyValueFactory<waterMonthlyModel, String>("unitsConsumedME"));
        reasonOtherCol.setCellFactory(stringCellFactory);
        
        monthlyElectricExpenseTable.getColumns().addAll(monthCol, amountCol, dateCol, unitsCol);
        waterExpenseTable.getColumns().addAll(monthWaterCol, amountWaterCol, dateWaterCol, unitsWaterCol);
        otherExpenseTable.getColumns().addAll(monthOtherCol, amountOtherCol, dateOtherCol, reasonOtherCol);

        savePane.prefHeightProperty().bind(monthGridPane.heightProperty());
        monthlyElectricExpenseTable.getStylesheets().add(getClass().getResource("/styles/JfxTableView_CSS.css").toExternalForm());
        waterExpenseTable.getStylesheets().add(getClass().getResource("/styles/JfxTableView_CSS.css").toExternalForm());
        otherExpenseTable.getStylesheets().add(getClass().getResource("/styles/JfxTableView_CSS.css").toExternalForm());
        
        electricityAnchor.setBottom(monthlyElectricExpenseTable);
        waterAnchor.setBottom(waterExpenseTable);
        otherAnchor.setBottom(otherExpenseTable);
    }

}
