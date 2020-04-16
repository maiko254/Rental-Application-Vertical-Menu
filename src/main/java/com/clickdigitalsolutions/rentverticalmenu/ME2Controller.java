/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickdigitalsolutions.rentverticalmenu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
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
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author bonyo
 */
public class ME2Controller implements Initializable {
    
    @FXML
    public SplitPane sp3;
    
    @FXML
    public BorderPane monthlyExpensePane;
    
    @FXML
    public BorderPane monthlyDetailsPane;
    
    public JFXTreeView<String> monthTreeView = new JFXTreeView<>();
    
    private TreeItem<String> rootMonth = new TreeItem<>("Select Month");
    private TreeItem<String> January = new TreeItem<>("January");
    private TreeItem<String> February = new TreeItem<>("February");
    private TreeItem<String> March = new TreeItem<>("March");
    private TreeItem<String> April = new TreeItem<>("April");
    private TreeItem<String> May = new TreeItem<>("May");
    private TreeItem<String> June = new TreeItem<>("June");
    private TreeItem<String> July = new TreeItem<>("July");
    private TreeItem<String> August = new TreeItem<>("August");
    private TreeItem<String> September = new TreeItem<>("September");
    private TreeItem<String> October = new TreeItem<>("October");
    private TreeItem<String> November = new TreeItem<>("November");
    private TreeItem<String> December = new TreeItem<>("December");
    
    public BorderPane tableViewPane = new BorderPane();
    
    public VBox elecVbox = new VBox(10);
    public VBox waterVbox = new VBox(10);
    public VBox otherVbox = new VBox(10);
    
    public TabPane monthlyExpensesTabPane = new TabPane();
    public Tab elecExpenseTab = new Tab("Electricity Expense", elecVbox);
    public Tab waterExpenseTab = new Tab("Water Expense", waterVbox);
    public Tab otherExpenseTab = new Tab("Other Expenses", otherVbox);
    
    Label l2 = new Label("Amount");
    Label l3 = new Label("Date Paid");
    Label l4 = new Label("Units\nConsumed (KWh)");
    
    Label l6 = new Label("Amount");
    Label l7 = new Label("Date Paid");
    Label l8 = new Label("Unit\nConsumed (m3)");
    
    Label l10 = new Label("Amount");
    Label l11 = new Label("Date Paid");
    Label l12 = new Label("Reason For\nExpense");
    
    public JFXTextField elecAmount = new JFXTextField();
    public JFXDatePicker elecDate = new JFXDatePicker();
    public JFXTextField elecUnits = new JFXTextField();
    public JFXButton elecButton = new JFXButton("Show details >>");
    
    public JFXTextField waterAmount = new JFXTextField();
    public JFXDatePicker waterDate = new JFXDatePicker();
    public JFXTextField waterUnits = new JFXTextField();
    public JFXButton waterButton = new JFXButton("Show details >>");
    
    public JFXTextField otherAmount = new JFXTextField();
    public JFXDatePicker otherDate = new JFXDatePicker();
    public JFXTextArea otherReason = new JFXTextArea();
    public JFXButton otherButton = new JFXButton("Show details >>");
    
    public HBox hbox2 = new HBox(10, l2, elecAmount);
    public HBox hbox3 = new HBox(10, l3, elecDate);
    public HBox hbox4 = new HBox(10, l4, elecUnits);
    public HBox hbox13 = new HBox(elecButton);
    
    public HBox hbox6 = new HBox(10, l6, waterAmount);
    public HBox hbox7 = new HBox(10, l7, waterDate);
    public HBox hbox8 = new HBox(10, l8, waterUnits);
    public HBox hbox14 = new HBox(waterButton);
    
    public HBox hbox10 = new HBox(10, l10, otherAmount);
    public HBox hbox11 = new HBox(10, l11, otherDate);
    public HBox hbox12 = new HBox(10, l12, otherReason);
    public HBox hbox15 = new HBox(otherButton);
    
    public TabPane tabContainer1;
    public BorderPane monthGridPane;
    public Tab electricityExpense;
    public Tab waterExpense;
    public Tab otherExpenses;
    
    
    public JFXTextField expenseReason;
    public Button saveMonthlyExpense;
    private TableColumn monthCol = new TableColumn("Month");
    private TableColumn<MonthlyModel, String> amountCol = new TableColumn("Amount Paid");
    private TableColumn dateCol = new TableColumn("Date");
    private TableColumn<MonthlyModel, String> unitsCol = new TableColumn("Units Consumed");
    public TableView<MonthlyModel> monthlyElectricExpenseTable = new TableView<>();
    private TableColumn monthWaterCol = new TableColumn("Month");
    private TableColumn<waterMonthlyModel, String> amountWaterCol = new TableColumn("Amount Paid");
    private TableColumn dateWaterCol = new TableColumn("Date");
    private TableColumn<waterMonthlyModel, String> unitsWaterCol = new TableColumn("Units Consumed");
    public TableView<waterMonthlyModel> waterExpenseTable = new TableView<>();
    private TableColumn monthOtherCol = new TableColumn("Month");
    private TableColumn<otherMonthlyModel, String> amountOtherCol = new TableColumn("Amount Paid");
    private TableColumn dateOtherCol = new TableColumn("Date");
    private TableColumn<otherMonthlyModel, String> reasonOtherCol = new TableColumn("Reason For Expense");
    public TableView<otherMonthlyModel> otherExpenseTable = new TableView<>();
    public BorderPane electricityAnchor;
    public BorderPane waterAnchor;
    public BorderPane otherAnchor;
    public AnchorPane savePane;
    
    
    
    private double tabWidth = 90.0;
    String databaseURL = "jdbc:sqlite:C:\\NetbeansProjects\\SQLite\\RVM.db";
    
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
        public SimpleStringProperty expenseReasonME;

        public otherMonthlyModel(String month, String amountPaid, String datePaid, String expenseReason) {
            this.monthME = new SimpleStringProperty(month);
            this.amountPaidME = new SimpleStringProperty(amountPaid);
            this.datePaidME = new SimpleStringProperty(datePaid);
            this.expenseReasonME = new SimpleStringProperty(expenseReason);
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

        public String getExpenseReasonME() {
            return this.expenseReasonME.get();
        }

        public void setExpenseReasonME(String value) {
            this.expenseReasonME.set(value);
        }

        public StringProperty expenseReasonMEProperty() {
            return this.expenseReasonME;
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
            createElecMonthlyExpensesTable(monthTreeView.getSelectionModel().getSelectedItem().getValue(), elecAmount.getText(), getDateValueAsString(elecDate.getValue()), elecUnits.getText());
            setElecEmpty();
        } else if (waterExpense.isSelected()) {
            createWaterMonthlyExpensesTable(monthTreeView.getSelectionModel().getSelectedItem().getValue(), waterAmount.getText(), getDateValueAsString(waterDate.getValue()), waterUnits.getText());
            setWaterEmpty();
        } else if (otherExpenses.isSelected()) {
            createOtherMonthlyExpensesTable(monthTreeView.getSelectionModel().getSelectedItem().getValue(), otherAmount.getText(), getDateValueAsString(otherDate.getValue()), expenseReason.getText());
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
  
            try {
                String searchElecTable = "SELECT * FROM ElectricityMonthlyExpenses WHERE Month = ?";
                Connection conn = DriverManager.getConnection(databaseURL);
                PreparedStatement pstmt = conn.prepareStatement(searchElecTable);
                pstmt.setString(1, monthTreeView.getSelectionModel().getSelectedItem().getValue());
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
        return monthlyData;
    }
    
    public ObservableList<waterMonthlyModel> getWaterMonthlyDetails() {
        ObservableList<waterMonthlyModel> monthlyData = FXCollections.observableArrayList();
            try {
                String searchWaterTable = "SELECT * FROM WaterMonthlyExpenses WHERE Month = ?";
                Connection conn = DriverManager.getConnection(databaseURL);
                PreparedStatement pstmt = conn.prepareStatement(searchWaterTable);
                pstmt.setString(1, monthTreeView.getSelectionModel().getSelectedItem().getValue());
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
        return monthlyData;
    }
    
    public ObservableList<otherMonthlyModel> getOtherMonthlyDetails() {
        ObservableList<otherMonthlyModel> monthlyData = FXCollections.observableArrayList();
            try {
                String searchOtherTable = "SELECT * FROM OtherMonthlyExpenses WHERE Month = ?";
                Connection conn = DriverManager.getConnection(databaseURL);
                PreparedStatement pstmt = conn.prepareStatement(searchOtherTable);
                pstmt.setString(1, monthTreeView.getSelectionModel().getSelectedItem().getValue());
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
    
    class waterStringTableCell extends TableCell<waterMonthlyModel, String> {

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? null : getString());
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
    
    class otherStringTableCell extends TableCell<otherMonthlyModel, String> {

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? null : getString());
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
    
    Callback<TableColumn, TableCell> stringCellFactory
            = new Callback<TableColumn, TableCell>() {
        @Override
        public TableCell call(TableColumn param) {
            MyStringTableCell cell = new MyStringTableCell();
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            return cell;
        }
    };
    
    public static final StringConverter<String> IDENTITY_CONVERTER = new StringConverter<String>() {

        @Override
        public String toString(String object) {
            return object;
        }

        @Override
        public String fromString(String string) {
            return string;
        }
        
    };

    
    Callback<TableColumn<MonthlyModel, String>, TableCell<MonthlyModel, String>> customStringCellFactory
            = new Callback<TableColumn<MonthlyModel, String>, TableCell<MonthlyModel, String>>() {
        @Override
        public TableCell<MonthlyModel, String> call(TableColumn<MonthlyModel, String> param) {
            EditCell cell = new EditCell(IDENTITY_CONVERTER);
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            return cell;
        }
    };
    
    Callback<TableColumn, TableCell> waterStringCellFactory
            = new Callback<TableColumn, TableCell>() {
        @Override
        public TableCell call(TableColumn param) {
            waterStringTableCell cell = new waterStringTableCell();
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            return cell;
        }
    };
    
    Callback<TableColumn<waterMonthlyModel, String>, TableCell<waterMonthlyModel, String>> customWaterColCellFactory
            = new Callback<TableColumn<waterMonthlyModel, String>, TableCell<waterMonthlyModel, String>>() {
        @Override
        public TableCell<waterMonthlyModel, String> call(TableColumn<waterMonthlyModel, String> param) {
            EditCell cell = new EditCell(IDENTITY_CONVERTER);
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            return cell;
        }
    };
    
    Callback<TableColumn, TableCell> otherStringCellFactory
            = new Callback<TableColumn, TableCell>() {
        @Override
        public TableCell call(TableColumn param) {
            otherStringTableCell cell = new otherStringTableCell();
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            return cell;
        }
    };
    
    Callback<TableColumn<otherMonthlyModel, String>, TableCell<otherMonthlyModel, String>> customOtherColCellFactory
            = new Callback<TableColumn<otherMonthlyModel, String>, TableCell<otherMonthlyModel, String>>() {
        @Override
        public TableCell<otherMonthlyModel, String> call(TableColumn<otherMonthlyModel, String> param) {
            EditCell cell = new EditCell(IDENTITY_CONVERTER);
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            return cell;
        }
    };

    class MyEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {
            if (elecExpenseTab.isSelected()) {
                ContextMenu elecContextMenu = new ContextMenu();
                MenuItem elecDelete = new MenuItem("Delete Record");
                MenuItem elecSearch = new MenuItem("Search Table");
                monthlyElectricExpenseTable.setOnContextMenuRequested((event) -> {
                    elecContextMenu.getItems().addAll(elecDelete, elecSearch);
                    elecContextMenu.show(monthlyElectricExpenseTable, event.getScreenX(), event.getScreenY());
                });
                monthlyElectricExpenseTable.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
                    elecContextMenu.hide();
                });
            } else if (waterExpenseTab.isSelected()) {
                ContextMenu waterContextMenu = new ContextMenu();
                MenuItem delete = new MenuItem("Delete Record");
                MenuItem search = new MenuItem("Search Table");
                waterExpenseTable.setOnContextMenuRequested((event) -> {
                    waterContextMenu.getItems().addAll(search, delete);
                    waterContextMenu.show(waterExpenseTable, event.getScreenX(), event.getScreenY());
                });
                waterExpenseTable.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
                    waterContextMenu.hide();
                });
            } else if (otherExpenseTab.isSelected()) {
                ContextMenu otherContextMenu = new ContextMenu();
                MenuItem search = new MenuItem("Search Table");
                MenuItem delete = new MenuItem("Delete Record");
                otherExpenseTable.setOnContextMenuRequested((event) -> {
                    otherContextMenu.getItems().addAll(search, delete);
                    otherContextMenu.show(otherExpenseTable, event.getScreenX(), event.getScreenY());
                });
                otherExpenseTable.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
                    otherContextMenu.hide();
                });
            }
            if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2 && elecExpenseTab.isSelected()) {
                TableCell c = (TableCell) t.getSource();
                int index = c.getIndex();
                try {
                    MonthlyModel item = getElectricityMonthlyDetails().get(index);
                    elecAmount.setText(item.getAmountPaidME());
                    elecDate.setValue(LocalDate.parse(item.getDatePaidME(), DateTimeFormatter.ISO_DATE));
                    elecUnits.setText(item.getUnitsConsumedME());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2 && waterExpenseTab.isSelected()) {
                TableCell c = (TableCell) t.getSource();
                int index = c.getIndex();
                try {
                    waterMonthlyModel item = getWaterMonthlyDetails().get(index);
                    waterAmount.setText(item.getAmountPaidME());
                    waterDate.setValue(LocalDate.parse(item.getDatePaidME(), DateTimeFormatter.ISO_DATE));
                    waterUnits.setText(item.getUnitsConsumedME());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2 && otherExpenseTab.isSelected()){
                TableCell c = (TableCell) t.getSource();
                int index = c.getIndex();
                try {
                    otherMonthlyModel item = getOtherMonthlyDetails().get(index);
                    otherAmount.setText(item.getAmountPaidME());
                    otherDate.setValue(LocalDate.parse(item.getDatePaidME(), DateTimeFormatter.ISO_DATE));
                    otherReason.setText(item.getExpenseReasonME());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private void editFocusedCell(TableView<MonthlyModel> monthlyTable){
        final TablePosition<MonthlyModel, String> focusedCell = monthlyTable
                .focusModelProperty().get().focusedCellProperty().get();
        monthlyTable.edit(focusedCell.getRow(), focusedCell.getTableColumn());
    }
    
    @SuppressWarnings("unchecked")
    private void selectPrevious(TableView monthlyTable) {
        if (monthlyTable.getSelectionModel().isCellSelectionEnabled()) {
            TablePosition<MonthlyModel, ?> pos = monthlyTable.getFocusModel().getFocusedCell();
            if (pos.getColumn() - 1 >= 0) {
                monthlyTable.getSelectionModel().select(pos.getRow(), getTableColumn(pos.getTableColumn(), monthlyTable, -1));
            } else if (pos.getRow() < monthlyTable.getItems().size()) {
                monthlyTable.getSelectionModel().select(pos.getRow() - 1, 
                        monthlyTable.getVisibleLeafColumn(
                                monthlyTable.getVisibleLeafColumns().size() - 1));
            }       
        } else {
            int focusindex = monthlyTable.getFocusModel().getFocusedIndex();
            if (focusindex == -1) {
                monthlyTable.getSelectionModel().select(monthlyTable.getItems().size() - 1);
            } else if (focusindex > 0) {
                monthlyTable.getSelectionModel().select(focusindex - 1);
            }
        }
    }
    
    private TableColumn<MonthlyModel, ?> getTableColumn (final TableColumn<MonthlyModel, ?> column, TableView<MonthlyModel> monthlyTable , int offset) {
        int columnIndex = monthlyTable.getVisibleLeafIndex(column);
        int newColumnIndex = columnIndex + offset;
        return monthlyTable.getVisibleLeafColumn(newColumnIndex);
    }
    
    private void updateMonthlyExpenseTable(String tableView, String column, String newValue, String Month, String Date) {
        try {
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE "+tableView+" SET " + column + " = ? WHERE Month = ? AND Date = ?");
            pstmt.setString(1, newValue);
            pstmt.setString(2, Month);
            pstmt.setString(3, Date);
            pstmt.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void setupMonthColumn(){
        monthCol.setPrefWidth(130.0);
        monthCol.setCellValueFactory(
                new PropertyValueFactory<MonthlyModel, String>("monthME"));
        monthCol.setCellFactory(stringCellFactory);
    }
    
    private void setupAmountColumn() {
        amountCol.setPrefWidth(150.0);
        amountCol.setCellValueFactory(
                new PropertyValueFactory<MonthlyModel, String>("amountPaidME"));
        amountCol.setCellFactory(customStringCellFactory);
        amountCol.setOnEditStart((event) -> {
            MonthlyModel amount = event.getRowValue();
            elecAmount.setText(amount.getAmountPaidME());
            elecDate.setValue(LocalDate.parse(amount.getDatePaidME(), DateTimeFormatter.ISO_DATE));
            elecUnits.setText(amount.getUnitsConsumedME());
        });
        amountCol.setOnEditCommit((event) -> {
            MonthlyModel amount = event.getRowValue();
            Alert commitWarning = new Alert(Alert.AlertType.WARNING, "This will edit the Amount column for "+amount.getMonthME()+" on "+amount.getDatePaidME()+" in ElectricityMonthlyExpenses Table. Do you want to proceed?", ButtonType.YES, ButtonType.NO);
            commitWarning.setTitle("Edit Electricity Monthly Expenses Table for "+amount.getMonthME());
            commitWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    amount.setAmountPaidME(event.getNewValue());
                    updateMonthlyExpenseTable("ElectricityMonthlyExpenses", "Amount", event.getNewValue(), amount.getMonthME(), amount.getDatePaidME());
                } else if (response == ButtonType.NO) {
                    int index = monthlyElectricExpenseTable.getSelectionModel().getSelectedIndex();
                    monthlyElectricExpenseTable.getItems().set(index, amount);
                }
            });
        });
    }
    
    private void setupDateColumn() {
        dateCol.setPrefWidth(110.0);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<MonthlyModel, String>("datePaidME"));
        dateCol.setCellFactory(stringCellFactory);
    }
    
    private void setupUnitsConsumedCol() {
        DoubleBinding usedWidth = monthCol.widthProperty().add(amountCol.widthProperty()).add(dateCol.widthProperty());
        unitsCol.prefWidthProperty().bind(monthlyElectricExpenseTable.widthProperty().subtract(usedWidth));
        unitsCol.setCellValueFactory(
                new PropertyValueFactory<MonthlyModel, String>("unitsConsumedME"));
        unitsCol.setCellFactory(customStringCellFactory);
        unitsCol.setOnEditStart((event) -> {
            MonthlyModel units = event.getRowValue();
            elecAmount.setText(units.getAmountPaidME());
            elecDate.setValue(LocalDate.parse(units.getDatePaidME(), DateTimeFormatter.ISO_DATE));
            elecUnits.setText(units.getUnitsConsumedME());
        });
        unitsCol.setOnEditCommit((event) -> {
            MonthlyModel units = event.getRowValue();
            Alert commitWarning = new Alert(Alert.AlertType.WARNING, "This will edit the Units Consumed column for "+units.getMonthME()+" on "+units.getDatePaidME()+" in ElectricityMonthlyExpenses Table. Do you want to proceed?", ButtonType.YES, ButtonType.NO);
            commitWarning.setTitle("Edit Electricity Monthly Expenses Table for "+units.getMonthME());
            commitWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    units.setUnitsConsumedME(event.getNewValue());
                    updateMonthlyExpenseTable("ElectricityMonthlyExpenses", "UnitsConsumed", event.getNewValue(), units.getMonthME(), units.getDatePaidME());
                } else if (response == ButtonType.NO) {
                    int index = monthlyElectricExpenseTable.getSelectionModel().getSelectedIndex();
                    monthlyElectricExpenseTable.getItems().set(index, units);
                }
            });
        });
    }
    
    private void setupMonthWaterCol() {
        monthWaterCol.setPrefWidth(130.0);
        monthWaterCol.setCellValueFactory(
                new PropertyValueFactory<waterMonthlyModel, String>("monthME"));
        monthWaterCol.setCellFactory(waterStringCellFactory);
    }
    
    private void setupAmountWaterColumn() {
        amountWaterCol.setPrefWidth(150.0);
        amountWaterCol.setCellValueFactory(
                new PropertyValueFactory<waterMonthlyModel, String>("amountPaidME"));
        amountWaterCol.setCellFactory(customWaterColCellFactory);
        amountWaterCol.setOnEditStart((event) -> {
            waterMonthlyModel amount = event.getRowValue();
            waterAmount.setText(amount.getAmountPaidME());
            waterDate.setValue(LocalDate.parse(amount.getDatePaidME(), DateTimeFormatter.ISO_DATE));
            waterUnits.setText(amount.getUnitsConsumedME());
        });
        amountWaterCol.setOnEditCommit((event) -> {
            waterMonthlyModel amount = event.getRowValue();
            Alert commitWarning = new Alert(Alert.AlertType.WARNING, "This will edit the Amount column for "+amount.getMonthME()+" on "+amount.getDatePaidME()+" in WaterMonthlyExpenses Table. Do you want to proceed?", ButtonType.YES, ButtonType.NO);
            commitWarning.setTitle("Edit Water Monthly Expenses Table for "+amount.getMonthME());
            commitWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    amount.setAmountPaidME(event.getNewValue());
                    updateMonthlyExpenseTable("WaterMonthlyExpenses", "Amount", event.getNewValue(), amount.getMonthME(), amount.getDatePaidME());
                } else if (response == ButtonType.NO) {
                    int index = waterExpenseTable.getSelectionModel().getSelectedIndex();
                    waterExpenseTable.getItems().set(index, amount);
                }
            });
        });
    }
    
    private void setupWaterDateColumn() {
        dateWaterCol.setPrefWidth(110.0);
        dateWaterCol.setCellValueFactory(new PropertyValueFactory<waterMonthlyModel, String>("datePaidME"));
        dateWaterCol.setCellFactory(stringCellFactory);
    }
    
    private void setupWaterUnitColumn() {
        DoubleBinding usedWidth = monthWaterCol.widthProperty().add(amountWaterCol.widthProperty()).add(dateWaterCol.widthProperty());
        unitsWaterCol.prefWidthProperty().bind(waterExpenseTable.widthProperty().subtract(usedWidth));
        unitsWaterCol.setCellValueFactory(new PropertyValueFactory<waterMonthlyModel, String>("unitsConsumedME"));
        unitsWaterCol.setCellFactory(customWaterColCellFactory);
        unitsWaterCol.setOnEditStart((event) -> {
            waterMonthlyModel units  = event.getRowValue();
            waterAmount.setText(units.getAmountPaidME());
            waterDate.setValue(LocalDate.parse(units.getDatePaidME(), DateTimeFormatter.ISO_DATE));
            waterUnits.setText(units.getUnitsConsumedME());
        });
        unitsWaterCol.setOnEditCommit((event) -> {
            waterMonthlyModel units = event.getRowValue();
            Alert commitWarning = new Alert(Alert.AlertType.WARNING, "This will edit the Units Consumed column for " + units.getMonthME() + " on " + units.getDatePaidME() + " in WaterMonthlyExpenses Table. Do you want to proceed?", ButtonType.YES, ButtonType.NO);
            commitWarning.setTitle("Edit Water Monthly Expenses Table for " + units.getMonthME());
            commitWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    units.setUnitsConsumedME(event.getNewValue());
                    updateMonthlyExpenseTable("WaterMonthlyExpenses", "UnitsConsumed", event.getNewValue(), units.getMonthME(), units.getDatePaidME());
                } else if (response == ButtonType.NO) {
                    int index = waterExpenseTable.getSelectionModel().getSelectedIndex();
                    waterExpenseTable.getItems().set(index, units);
                }
            });
        });
    }
    
    private void setupOtherMonthColumn() {
        monthOtherCol.setPrefWidth(130.0);
        monthOtherCol.setCellValueFactory(new PropertyValueFactory<otherMonthlyModel, String>("monthME"));
        monthOtherCol.setCellFactory(otherStringCellFactory);
    }
    
    private void setupOtherAmountCol() {
        amountOtherCol.setPrefWidth(150.0);
        amountOtherCol.setCellValueFactory(new PropertyValueFactory<otherMonthlyModel, String>("amountPaidME"));
        amountOtherCol.setCellFactory(customOtherColCellFactory);
        amountOtherCol.setOnEditStart((event) -> {
            otherMonthlyModel amount = event.getRowValue();
            otherAmount.setText(amount.getAmountPaidME());
            otherDate.setValue(LocalDate.parse(amount.getDatePaidME(), DateTimeFormatter.ISO_DATE));
            expenseReason.setText(amount.getExpenseReasonME());
        });
        amountOtherCol.setOnEditCommit((event) -> {
            otherMonthlyModel amount = event.getRowValue();
            Alert commitWarning = new Alert(Alert.AlertType.WARNING, "This will edit the Amount column for "+amount.getMonthME()+" on "+amount.getDatePaidME()+" in OtherMonthlyExpenses Table. Do you want to proceed?", ButtonType.YES, ButtonType.NO);
            commitWarning.setTitle("Edit Other Monthly Expenses Table for "+amount.getMonthME());
            commitWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    amount.setAmountPaidME(event.getNewValue());
                    updateMonthlyExpenseTable("OtherMonthlyExpenses", "Amount", event.getNewValue(), amount.getMonthME(), amount.getDatePaidME());
                } else if (response == ButtonType.NO) {
                    int index = otherExpenseTable.getSelectionModel().getSelectedIndex();
                    otherExpenseTable.getItems().set(index, amount);
                }
            });
        });
    }
    
    private void setupOtherDateColumn() {
        dateOtherCol.setPrefWidth(90.0);
        dateOtherCol.setCellValueFactory(new PropertyValueFactory<otherMonthlyModel, String>("datePaidME"));
        dateOtherCol.setCellFactory(otherStringCellFactory);
    }
    
    private void setupOtherExpenseReasonColumn() {
        DoubleBinding usedWidth = monthOtherCol.widthProperty().add(amountOtherCol.widthProperty()).add(dateOtherCol.widthProperty());
        reasonOtherCol.prefWidthProperty().bind(otherExpenseTable.widthProperty().subtract(usedWidth));
        reasonOtherCol.setCellValueFactory(new PropertyValueFactory<otherMonthlyModel, String>("expenseReasonME"));
        reasonOtherCol.setCellFactory(customOtherColCellFactory);
        reasonOtherCol.setOnEditStart((event) -> {
            otherMonthlyModel reason = event.getRowValue();
            otherAmount.setText(reason.getAmountPaidME());
            otherDate.setValue(LocalDate.parse(reason.getDatePaidME(), DateTimeFormatter.ISO_DATE));
            otherReason.setText(reason.getExpenseReasonME());
        });
        reasonOtherCol.setOnEditCommit((event) -> {
            otherMonthlyModel reason = event.getRowValue();
            Alert commitWarning = new Alert(Alert.AlertType.WARNING, "This will edit the Reason For Expense column for " + reason.getMonthME() + " on " + reason.getDatePaidME() + " in OtherMonthlyExpenses Table. Do you want to proceed?", ButtonType.YES, ButtonType.NO);
            commitWarning.setTitle("Edit Other Monthly Expenses Table for " + reason.getMonthME());
            commitWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    reason.setExpenseReasonME(event.getNewValue());
                    updateMonthlyExpenseTable("OtherMonthlyExpenses", "ReasonForExpense", event.getNewValue(), reason.getMonthME(), reason.getDatePaidME());
                } else if (response == ButtonType.NO) {
                    int index = otherExpenseTable.getSelectionModel().getSelectedIndex();
                    otherExpenseTable.getItems().set(index, reason);
                }
            });
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        rootMonth.getChildren().addAll(January, February, March, April, May, June, July, August, September, October, November, December);
        
        monthTreeView.setRoot(rootMonth);
        
        l2.setMinSize(120, 20);
        l3.setMinSize(120, 20);
        l4.setMinSize(120, 20);
        
        l6.setMinSize(120, 20);
        l7.setMinSize(120, 20);
        l8.setMinSize(120, 20);
        
        l10.setMinSize(120, 20);
        l11.setMinSize(120, 20);
        l12.setMinSize(120, 20);
        
        setupMonthColumn();
        setupAmountColumn();
        setupDateColumn();
        setupUnitsConsumedCol();
        
        setupMonthWaterCol();
        setupAmountWaterColumn();
        setupWaterDateColumn();
        setupWaterUnitColumn();
        
        setupOtherMonthColumn();
        setupOtherAmountCol();
        setupOtherDateColumn();
        setupOtherExpenseReasonColumn();
        
        otherReason.setWrapText(true);
        otherReason.setPrefWidth(170);
        otherReason.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                otherReason.applyCss();
                Node text = otherReason.lookup(".text");
                otherReason.prefHeightProperty().bind(Bindings.createDoubleBinding(() -> {
                    return otherReason.getFont().getSize() + text.getBoundsInLocal().getHeight();
                }, text.boundsInLocalProperty()));
                
                text.boundsInLocalProperty().addListener((observableBoundsAfter, boundsBefore, boundsAfter) -> {
                    Platform.runLater(() -> otherReason.requestLayout());
                });
            }
        });
        
        monthTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                    String searchElecTable = "SELECT * FROM ElectricityMonthlyExpenses WHERE MONTH = ?";
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchElecTable);
                    pstmt.setString(1, monthTreeView.getSelectionModel().getSelectedItem().getValue());
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        elecAmount.setText("");
                        elecDate.setValue(null);
                        elecUnits.setText("");
                        elecButton.setVisible(false);
                    } else {
                        do {
                            elecAmount.setText(rs.getString("Amount"));
                            elecDate.setValue(LocalDate.parse(rs.getString("Date"), DateTimeFormatter.ISO_DATE));
                            elecUnits.setText(rs.getString("UnitsConsumed"));
                        } while (rs.next());
                        elecButton.setVisible(true);
                    }
                    pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            
            try {
                    String searchElecTable = "SELECT * FROM WaterMonthlyExpenses WHERE MONTH = ?";
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchElecTable);
                    pstmt.setString(1, monthTreeView.getSelectionModel().getSelectedItem().getValue());
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        waterAmount.setText("");
                        waterDate.setValue(null);
                        waterUnits.setText("");
                        waterButton.setVisible(false);
                    } else {
                        do {
                            waterAmount.setText(rs.getString("Amount"));
                            waterDate.setValue(LocalDate.parse(rs.getString("Date"), DateTimeFormatter.ISO_DATE));
                            waterUnits.setText(rs.getString("UnitsConsumed"));
                        } while (rs.next());
                        waterButton.setVisible(true);
                    }
                    pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            
            try {
                    String searchElecTable = "SELECT * FROM OtherMonthlyExpenses WHERE MONTH = ?";
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchElecTable);
                    pstmt.setString(1, monthTreeView.getSelectionModel().getSelectedItem().getValue());
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        otherAmount.setText("");
                        otherDate.setValue(null);
                        otherReason.setText("");
                        otherButton.setVisible(false);
                    } else {
                        do {
                            otherAmount.setText(rs.getString("Amount"));
                            otherDate.setValue(LocalDate.parse(rs.getString("Date"), DateTimeFormatter.ISO_DATE));
                            otherReason.setText(rs.getString("ReasonForExpense"));
                        } while (rs.next());
                        otherButton.setVisible(true);
                    }
                    pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            rootMonth.setValue(monthTreeView.getSelectionModel().getSelectedItem().getValue());
            Platform.runLater(() -> {
                rootMonth.setExpanded(false);
            });
        });
        
        elecButton.setVisible(false);
        elecButton.setOnAction((event) -> {
            if (sp3.getItems().size() == 1) {
                monthlyElectricExpenseTable.setMinHeight(200);
                monthlyElectricExpenseTable.setItems(getElectricityMonthlyDetails());
                tableViewPane.setCenter(monthlyElectricExpenseTable);
                sp3.getItems().add(tableViewPane);
                elecButton.setText("Hide details >>");
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                MainApp.changeWindowSize(stage, 600);
            } else if (sp3.getItems().size() == 2) {
                sp3.getItems().remove(tableViewPane);
                elecButton.setText("Show details >>");
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                MainApp.changeWindowSize(stage, 500);
            }
        });
        
        waterButton.setVisible(false);
        waterButton.setOnAction((event) -> {
            if (sp3.getItems().size() == 1) {
                waterExpenseTable.setMinHeight(200);
                waterExpenseTable.setItems(getWaterMonthlyDetails());
                tableViewPane.setCenter(waterExpenseTable);
                sp3.getItems().add(tableViewPane);
                waterButton.setText("Hide details >>");
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                MainApp.changeWindowSize(stage, 600);
            } else if (sp3.getItems().size() == 2) {
                sp3.getItems().remove(tableViewPane);
                waterButton.setText("Show details >>");
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                MainApp.changeWindowSize(stage, 500);
            }
        });
        
        otherButton.setVisible(false);
        otherButton.setOnAction((event) -> {
            if (sp3.getItems().size() == 1) {
               otherExpenseTable.setMinHeight(200);
               otherExpenseTable.setItems(getOtherMonthlyDetails());
               tableViewPane.setCenter(otherExpenseTable);
               sp3.getItems().add(tableViewPane);
               otherButton.setText("Hide details >>");
               Node source = (Node) event.getSource();
               Stage stage = (Stage) source.getScene().getWindow();
               MainApp.changeWindowSize(stage, 600);
            } else if (sp3.getItems().size() == 2) {
                sp3.getItems().remove(tableViewPane);
                otherButton.setText("Show details >>");
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                MainApp.changeWindowSize(stage, 500);
            }
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
       
        monthlyElectricExpenseTable.setEditable(true);
        monthlyElectricExpenseTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        monthlyElectricExpenseTable.setOnKeyPressed((event) -> {
            TablePosition<MonthlyModel, ?> pos = monthlyElectricExpenseTable.getFocusModel().getFocusedCell();
            if (pos != null && event.getCode().isLetterKey()){
                monthlyElectricExpenseTable.edit(pos.getRow(), pos.getTableColumn());
            } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.TAB) {
                monthlyElectricExpenseTable.getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT){
                selectPrevious(monthlyElectricExpenseTable);
                event.consume();
            } 
        });
        monthlyElectricExpenseTable.getColumns().addAll(monthCol, amountCol, dateCol, unitsCol);
        
        
        waterExpenseTable.setEditable(true);
        waterExpenseTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        waterExpenseTable.setOnKeyPressed((event) -> {
            TablePosition<waterMonthlyModel, ?> pos = waterExpenseTable.getFocusModel().getFocusedCell();
            if (pos != null && event.getCode().isLetterKey()){
                waterExpenseTable.edit(pos.getRow(), pos.getTableColumn());
            } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.TAB) {
                waterExpenseTable.getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT){
                selectPrevious(waterExpenseTable);
                event.consume();
            } 
        });
        waterExpenseTable.getColumns().addAll(monthWaterCol, amountWaterCol, dateWaterCol, unitsWaterCol);
        
        otherExpenseTable.setEditable(true);
        otherExpenseTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        otherExpenseTable.setOnKeyPressed((event) -> {
            TablePosition<otherMonthlyModel, ?> pos = otherExpenseTable.getFocusModel().getFocusedCell();
            if (pos != null && event.getCode().isLetterKey()){
                otherExpenseTable.edit(pos.getRow(), pos.getTableColumn());
            } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.TAB) {
                otherExpenseTable.getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT){
                selectPrevious(otherExpenseTable);
                event.consume();
            } 
        });
        otherExpenseTable.getColumns().addAll(monthOtherCol, amountOtherCol, dateOtherCol, reasonOtherCol);
        
        elecVbox.setPadding(new Insets(10, 10, 10, 10));
        elecVbox.getChildren().addAll(hbox2, hbox3, hbox4, hbox13);
        
        waterVbox.setPadding(new Insets(10, 10, 10, 10));
        waterVbox.getChildren().addAll(hbox6, hbox7, hbox8, hbox14);
        
        otherVbox.setPadding(new Insets(10, 10, 10, 10));
        otherVbox.getChildren().addAll(hbox10, hbox11, hbox12, hbox15);
        
        monthlyExpensesTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        monthlyExpensesTabPane.getTabs().addAll(elecExpenseTab, waterExpenseTab, otherExpenseTab);
        
        monthlyExpensePane.setMinSize(450, 300);
        monthlyExpensePane.setCenter(monthlyExpensesTabPane);
        monthTreeView.setMinWidth(150);
        monthlyDetailsPane.setCenter(monthTreeView);
    }

}
