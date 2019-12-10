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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import jfxtras.styles.jmetro8.JMetro;

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
    private Button saveButtonR;
    @FXML
    private BorderPane RAnchor;
    
    public TableColumn houseNo = new TableColumn("House Number");
    public TableColumn tenantName = new TableColumn("Tenant Name");
    public TableColumn<RModel, String> repairDone = new TableColumn("Repairs");
    public TableColumn<RModel, String> costOfRepair = new TableColumn<>("Repair Cost");
    public TableColumn dateOfRepair = new TableColumn("Repair Date");
    public TableColumn<RModel, String> miscExpenses = new TableColumn<>("Miscellaneous Expenses");
    public TableView<RModel> repairsTable = new TableView<>();
    
    @FXML
    public VBox repairsVbox;    
    private JMetro.Style STYLE = JMetro.Style.DARK;
    
    private String comboboxRCheck = "Empty";
    
    ObservableList<String>blockB = FXCollections.observableArrayList("B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12");
    ObservableList<String>blockA = FXCollections.observableArrayList("A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12");
    ObservableList<String>blockC = FXCollections.observableArrayList("C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10");
    ObservableList<String>nasraBlock = FXCollections.observableArrayList("Top House", "Bottom House");

    String databaseURL = "jdbc:sqlite:C:\\NetbeansProjects\\SQLite\\RVM.db";
    
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
        }else if (comboboxRCheck.equals("Block B")){
            createRepairsTable((String)blockBComboR.getSelectionModel().getSelectedItem(), tenantNameR.getText(), repairsDoneR.getText(), costOfRepairR.getText(), getDateValueAsString(repairDateR.getValue()), miscellaneousR.getText());
            setEmpty();
        }else if (comboboxRCheck.equals("Block C")){
            createRepairsTable((String)blockCComboR.getSelectionModel().getSelectedItem(), tenantNameR.getText(), repairsDoneR.getText(), costOfRepairR.getText(), getDateValueAsString(repairDateR.getValue()), miscellaneousR.getText());
            setEmpty();
        }else if (comboboxRCheck.equals("Nasra Block")){
            createRepairsTable((String)nasraBlockR.getSelectionModel().getSelectedItem(), tenantNameR.getText(), repairsDoneR.getText(), costOfRepairR.getText(), getDateValueAsString(repairDateR.getValue()), miscellaneousR.getText());
            setEmpty();
        }else if (comboboxRCheck.equals("Empty")){
            Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
            emptyAlert.setTitle("Error Dialog");
            emptyAlert.setHeaderText("Empty Field");
            emptyAlert.setContentText("House Number selection cannot be empty. Please select a house");
            emptyAlert.showAndWait();
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
    
    public  ObservableList<RModel> getRepairsDetails(){
        ObservableList<RModel> repairsData = FXCollections.observableArrayList();
        if (comboboxRCheck.equals("Block A")) {
            try {
                String repairsTableQuery = "SELECT * FROM RepairsTable WHERE HouseNumber = ?";
                Connection conn = DriverManager.getConnection(databaseURL);
                PreparedStatement pstmt = conn.prepareStatement(repairsTableQuery);
                pstmt.setString(1, (String) blockAComboR.getSelectionModel().getSelectedItem());
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {                    
                    String houseNo1 = rs.getString("HouseNumber");
                    String tenantName1 = rs.getString("Tenantname");
                    String repairsDone1 = rs.getString("Repairs");
                    String costOfRepair1 = rs.getString("CostOfRepairs");
                    String dateOfRepair1 = rs.getString("DateOfRepairs");
                    String miscellaneous = rs.getString("MiscellaneousExpenses");
                    
                    RModel repairsInfo = new RModel(houseNo1, tenantName1, repairsDone1, costOfRepair1, dateOfRepair1, miscellaneous);
                    repairsData.add(repairsInfo);
                }
                pstmt.close();
                conn.close();
            } catch (Exception e) {
            }
        }
        return repairsData;
    }
    
    
    
    class MyStringTableCell extends TableCell<RModel, String> {

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
    
    class MyEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {
            if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2) {
                TableCell cell = (TableCell) t.getSource();
                int index = cell.getIndex();
                try {
                    RModel item = getRepairsDetails().get(index);
                    tenantNameR.setText(item.gettenantNameTableR());
                    repairsDoneR.setText(item.getrepairsDoneTableR());
                    costOfRepairR.setText(item.getcostofRepairsTableR());
                    repairDateR.setValue(LocalDate.parse(item.getdateofRepairsTableR(), DateTimeFormatter.ISO_DATE));
                    miscellaneousR.setText(item.getmiscellaneousTableR());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private void editFocusedCell(){
        final TablePosition<RModel, String> focusedCell = repairsTable
                .focusModelProperty().get().focusedCellProperty().get();
        repairsTable.edit(focusedCell.getRow(), focusedCell.getTableColumn());
    }
    
    @SuppressWarnings("unchecked")
    private void selectPrevious() {
        if (repairsTable.getSelectionModel().isCellSelectionEnabled()) {
            TablePosition<RModel, ?> pos = repairsTable.getFocusModel().getFocusedCell();
            if (pos.getColumn() - 1 >= 0) {
                repairsTable.getSelectionModel().select(pos.getRow(), getTableColumn(pos.getTableColumn(), -1));
            } else if (pos.getRow() < repairsTable.getItems().size()) {
                repairsTable.getSelectionModel().select(pos.getRow() - 1, 
                        repairsTable.getVisibleLeafColumn(
                                repairsTable.getVisibleLeafColumns().size() - 1));
            }       
        } else {
            int focusindex = repairsTable.getFocusModel().getFocusedIndex();
            if (focusindex == -1) {
                repairsTable.getSelectionModel().select(repairsTable.getItems().size() - 1);
            } else if (focusindex > 0) {
                repairsTable.getSelectionModel().select(focusindex - 1);
            }
        }
    }
    
    private TableColumn<RModel, ?> getTableColumn (final TableColumn<RModel, ?> column, int offset) {
        int columnIndex = repairsTable.getVisibleLeafIndex(column);
        int newColumnIndex = columnIndex + offset;
        return repairsTable.getVisibleLeafColumn(newColumnIndex);
    }
    
    
    private void setupHouseNoColumn() {
        houseNo.setCellValueFactory(new PropertyValueFactory<RModel, String>("houseNumberTableR"));
        houseNo.setCellFactory(stringCellFactory);
    }
    
    private void setupTenantNameColumn() {
        tenantName.setCellValueFactory(new PropertyValueFactory<RModel, String>("tenantNameTableR"));
        tenantName.setCellFactory(stringCellFactory);
    }
    
    private void setupRepairsDoneColumn() {
        repairDone.setCellValueFactory(new PropertyValueFactory<RModel, String>("repairsDoneTableR"));
        repairDone.setCellFactory(column -> EditCell.createStringEditCell());
        repairDone.setOnEditStart((event) -> {
            RModel repDone = event.getRowValue();
            repairsDoneR.setText(repDone.getrepairsDoneTableR());
            costOfRepairR.setText(repDone.getcostofRepairsTableR());
            repairDateR.setValue(LocalDate.parse(repDone.getdateofRepairsTableR(), DateTimeFormatter.ISO_DATE));
            miscellaneousR.setText(repDone.getmiscellaneousTableR());
        });
        repairDone.setOnEditCommit((event) -> {
            RModel repDone = event.getRowValue();
            Alert commitWarning = new Alert(Alert.AlertType.WARNING, "This will edit RepairsDone column for "+repDone.gethouseNumberTableR()+" on "+repDone.getdateofRepairsTableR()+" in Repairs Table. Do you want to proceed?", ButtonType.YES, ButtonType.NO);
            commitWarning.setTitle("Edit Repairs Done field for " + repDone.gethouseNumberTableR());
            commitWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    repDone.setrepairsDoneTableR(event.getNewValue());
                    updateRepairsTableData("Repairs", event.getNewValue(), repDone.gethouseNumberTableR(), repDone.getdateofRepairsTableR());
                } else if (response == ButtonType.NO) {
                   int index = repairsTable.getSelectionModel().getSelectedIndex();
                   repairsTable.getItems().set(index, repDone);
                }
            });
        });
    }
    
    private void setupRepairCostColumn() {
        costOfRepair.setCellValueFactory(new PropertyValueFactory<RModel, String>("costofRepairsTableR"));
        costOfRepair.setCellFactory(column -> EditCell.createStringEditCell());
        costOfRepair.setOnEditStart((event) -> {
            RModel repairCost = event.getRowValue();
            repairsDoneR.setText(repairCost.getrepairsDoneTableR());
            costOfRepairR.setText(repairCost.getcostofRepairsTableR());
            repairDateR.setValue(LocalDate.parse(repairCost.getdateofRepairsTableR(), DateTimeFormatter.ISO_DATE));
            miscellaneousR.setText(repairCost.getmiscellaneousTableR());
        });
        costOfRepair.setOnEditCommit((event) -> {
            RModel repairCost = event.getRowValue();
            RModel repDone = event.getRowValue();
            Alert commitWarning = new Alert(Alert.AlertType.WARNING, "This will edit CostOfRepairs column for "+repDone.gethouseNumberTableR()+" on "+repDone.getdateofRepairsTableR()+" in Repairs Table. Do you want to proceed?", ButtonType.YES, ButtonType.NO);
            commitWarning.setTitle("Edit Cost Of Repairs field for " + repDone.gethouseNumberTableR());
            commitWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    repairCost.setcostofRepairsTableR(event.getNewValue());
                    updateRepairsTableData("CostOfRepairs", event.getNewValue(), repairCost.gethouseNumberTableR(), repairCost.getdateofRepairsTableR());
                } else if (response == ButtonType.NO) {
                    int index = repairsTable.getSelectionModel().getSelectedIndex();
                    repairsTable.getItems().set(index, repDone);
                }
            });
        });
    }
    
    private void setupRepairsDateColumn() {
        dateOfRepair.setCellValueFactory(new PropertyValueFactory<RModel, String>("dateofRepairsTableR"));
        dateOfRepair.setCellFactory(stringCellFactory);
    }
    
    private void setupMiscellaneousColumn() {
        miscExpenses.setCellValueFactory(new PropertyValueFactory<RModel, String>("miscellaneousTableR"));
        miscExpenses.setCellFactory(column -> EditCell.createStringEditCell());
        miscExpenses.setOnEditStart((event) -> {
            RModel miscellaneous = event.getRowValue();
            repairsDoneR.setText(miscellaneous.getrepairsDoneTableR());
            costOfRepairR.setText(miscellaneous.getcostofRepairsTableR());
            repairDateR.setValue(LocalDate.parse(miscellaneous.getdateofRepairsTableR(), DateTimeFormatter.ISO_DATE));
            miscellaneousR.setText(miscellaneous.getmiscellaneousTableR());
        });
        miscExpenses.setOnEditCommit((event) -> {
            RModel misc = event.getRowValue();
            Alert commitWarning = new Alert(Alert.AlertType.WARNING, "This will edit Miscellaneous Expenses column for "+misc.gethouseNumberTableR()+" on "+misc.getdateofRepairsTableR()+" in Repairs Table. Do you want to proceed?", ButtonType.YES, ButtonType.NO);
            commitWarning.setTitle("Edit Cost Of Repairs field for " + misc.gethouseNumberTableR());
            commitWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    misc.setmiscellaneousTableR(event.getNewValue());
                    updateRepairsTableData("MiscellaneousExpenses", event.getNewValue(), misc.gethouseNumberTableR(), misc.getdateofRepairsTableR());
                } else if (response == ButtonType.NO) {
                    int index = repairsTable.getSelectionModel().getSelectedIndex();
                    repairsTable.getItems().set(index, misc);
                }
            });
        });
    }
    
    private void updateRepairsTableData(String column, String newValue, String houseNumber, String repairsDate) {
        try {
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE RepairsTable SET " + column + " = ? WHERE HouseNumber = ? AND DateOfRepairs = ?");
            pstmt.setString(1, newValue);
            pstmt.setString(2, houseNumber);
            pstmt.setString(3, repairsDate);
            pstmt.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        blockAComboR.setItems(blockA);
        blockBComboR.setItems(blockB);
        blockCComboR.setItems(blockC);
        nasraBlockR.setItems(nasraBlock);
        
        setupHouseNoColumn();
        setupTenantNameColumn();
        setupRepairsDoneColumn();
        setupRepairCostColumn();
        setupRepairsDateColumn();
        setupMiscellaneousColumn();
        
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
                            System.out.println(rs.getString("HouseNumber"));
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
                label.setStyle("-fx-text-fill: fdfdfd;");
                houseComboTitledPaneR.setText("");
                houseComboTitledPaneR.setGraphic(label);
                houseComboTitledPaneR.setExpanded(false);
                repairsTable.setItems(getRepairsDetails());
            });
            blockBComboR.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String searchRepairsSql = "SELECT * FROM RepairsTable WHERE HouseNumber = ?";
                try {
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchRepairsSql);
                    pstmt.setString(1, (String)blockBComboR.getSelectionModel().getSelectedItem());
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
                comboboxRCheck = "Block B";
                Label label = new Label();
                label.setText((String)blockBComboR.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: #fdfdfd;");
                houseComboTitledPaneR.setGraphic(label);
                houseComboTitledPaneR.setExpanded(false);
                repairsTable.setItems(getRepairsDetails());
            });
            blockCComboR.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String searchRepairsSql = "SELECT * FROM RepairsTable WHERE HouseNumber = ?";
                try {
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchRepairsSql);
                    pstmt.setString(1, (String)blockCComboR.getSelectionModel().getSelectedItem());
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
                comboboxRCheck = "Block C";
                Label label = new Label();
                label.setText((String)blockCComboR.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: #fdfdfd;");
                houseComboTitledPaneR.setGraphic(label);
                houseComboTitledPaneR.setExpanded(false);
                repairsTable.setItems(getRepairsDetails());
            });
            nasraBlockR.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String searchRepairsSql = "SELECT * FROM RepairsTable WHERE HouseNumber = ?";
                try {
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchRepairsSql);
                    pstmt.setString(1, (String)nasraBlockR.getSelectionModel().getSelectedItem());
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
                comboboxRCheck = "Nasra Block";
                Label label = new Label();
                label.setText((String)nasraBlockR.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: #fdfdfd;");
                houseComboTitledPaneR.setGraphic(label);
                houseComboTitledPaneR.setExpanded(false);
                repairsTable.setItems(getRepairsDetails());
            });
        });
        
        RAnchor.setOnMouseClicked((event) -> {
            houseComboTitledPaneR.setExpanded(false);
        });
        
        
       
        
        
        repairsTable.setEditable(true);
        repairsTable.getSelectionModel().setCellSelectionEnabled(true);
        repairsTable.setOnKeyPressed((event) -> {
            TablePosition<RModel, ?> pos = repairsTable.getFocusModel().getFocusedCell();
            if (pos != null && event.getCode().isLetterKey()){
                repairsTable.edit(pos.getRow(), pos.getTableColumn());
            } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.TAB) {
                repairsTable.getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT){
                selectPrevious();
                event.consume();
            } 
        });
        repairsTable.getColumns().addAll(houseNo, tenantName, repairDone, costOfRepair, dateOfRepair, miscExpenses);
        
        repairsTable.prefWidthProperty().bind(RAnchor.widthProperty());
        RAnchor.setBottom(repairsTable);
        
        
    }
    
    
}
