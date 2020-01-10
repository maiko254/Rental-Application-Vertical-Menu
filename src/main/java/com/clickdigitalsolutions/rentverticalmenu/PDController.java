package com.clickdigitalsolutions.rentverticalmenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static com.clickdigitalsolutions.rentverticalmenu.TDController.getPrefferedCellStyle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * FXML Controller class
 *
 * @author Mike254
 */
public class PDController implements Initializable {
    
    @FXML
    private TitledPane houseComboTitledPanePD;
    
    @FXML
    public JFXComboBox blockAComboPD;
    
    @FXML
    public JFXComboBox blockBComboPD;
    
    @FXML
    public JFXComboBox blockCComboPD;
    
    @FXML
    public JFXComboBox nasraBlockPD;
    
    @FXML
    private JFXTextField tenantNamePD;
    
    @FXML
    public JFXTextField amountPD;
    
    @FXML
    public JFXComboBox<PDModel.Strings> monthComboPD;
    
    @FXML
    private JFXDatePicker rentPaymentDatePD;
    
    @FXML
    private JFXTreeView<String> paymentMethodPD;
    
    @FXML
    private Label rentArrears;
    
    @FXML
    public BorderPane PDAnchor;
    
    @FXML
    public JFXButton updatePDAmount;
    
    @FXML
    private Button saveButtonPD;
    
    @FXML
    private VBox paymentVbox;
    
    private TableColumn houseNoCol = new TableColumn("House Number");
    private TableColumn tenantNameCol = new TableColumn("Tenant Name");
    private TableColumn<PDModel, String> amountCol = new TableColumn<>("Amount");
    private TableColumn<PDModel, PDModel.Strings> monthCol = new TableColumn<>("Month");
    private TableColumn dateCol = new TableColumn<>("Payment Date");
    private TableColumn<PDModel, String> methodCol = new TableColumn<>("Payment Method");
    
    public TableView<PDModel> paymentsTable = new TableView<>();
    
    String paymentMode;

    String comboboxPDCheck = "Empty";

    String houseComboSelect;

    String monthComboSelect;
    
    String payMethodCheck;
    
    SimpleStringProperty addCheck = new SimpleStringProperty("null");
    
    boolean arrearsCheck = false;
    
    int rentAmountPDPaid = 0;
    
    @FXML
    private TextField payRecordsFilter;
    
    @FXML
    private Button clearButton;
    
    public PDController(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PD2.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
    }
     
    ObservableList<String>blockB = FXCollections.observableArrayList("B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12");
    ObservableList<String>blockA = FXCollections.observableArrayList("A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12");
    ObservableList<String>blockC = FXCollections.observableArrayList("C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10");
    ObservableList<String>nasraBlock = FXCollections.observableArrayList("Top House", "Bottom House");
    ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    ObservableList<PDModel> payTenantDetails = FXCollections.observableArrayList();
    FilteredList<PDModel> filteredItems = new FilteredList<>(FXCollections.observableArrayList()); 
    String databaseURL = "jdbc:sqlite:C:\\NetbeansProjects\\SQLite\\RVM.db";
    
    public String getComboSelect(){
        return houseComboSelect;
    }
    public String getMonthComboSelect(){
        return monthComboSelect;
    }
    
    public void createPaymentDetailsTable(String HouseNumber, String TenantName, String Amount, PDModel.Strings Month, String PaymentDate, String PaymentMethod){
        try {
            String createPDSql = "CREATE TABLE IF NOT EXISTS PaymentDetails(HouseNumber text, TenantName text, Amount text, Month text, PaymentDate text, PaymentMethod text, PRIMARY KEY(HouseNumber, PaymentDate), FOREIGN KEY(HouseNumber) REFERENCES TenantDetails(HouseNumber) ON DELETE CASCADE)";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(createPDSql);
            pstmt.execute();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.getErrorCode();
        }
        
        try {
            String insertPDSql = "INSERT INTO PaymentDetails(HouseNumber, TenantName, Amount, Month, PaymentDate, PaymentMethod) VALUES(?,?,?,?,?,?)";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(insertPDSql);
            pstmt.setString(1, HouseNumber);
            pstmt.setString(2, TenantName);
            pstmt.setString(3, Amount);
            pstmt.setString(4, Month.name());
            pstmt.setString(5, PaymentDate);
            pstmt.setString(6, PaymentMethod);
            pstmt.execute();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void saveButtonActionPD() throws FileNotFoundException{
        if (comboboxPDCheck.equals("Block A") && amountPD.getText().isEmpty()){
            Alert amountEmptyAlert = new Alert(Alert.AlertType.INFORMATION);
            amountEmptyAlert.setTitle("Information Dialog");
            amountEmptyAlert.setHeaderText("Empty Field");
            amountEmptyAlert.setContentText("Amount field cannot be empty. Please input the rent amount");
            amountEmptyAlert.showAndWait();
        } else if (comboboxPDCheck.equals("Block B") && amountPD.getText().isEmpty()){
            Alert amountEmptyAlert = new Alert(Alert.AlertType.INFORMATION);
            amountEmptyAlert.setTitle("Information Dialog");
            amountEmptyAlert.setHeaderText("Empty Field");
            amountEmptyAlert.setContentText("Amount field cannot be empty. Please input the rent amount");
            amountEmptyAlert.showAndWait();
        } else if (comboboxPDCheck.equals("Block C") && amountPD.getText().isEmpty()){
            Alert amountEmptyAlert = new Alert(Alert.AlertType.INFORMATION);
            amountEmptyAlert.setTitle("Information Dialog");
            amountEmptyAlert.setHeaderText("Empty Field");
            amountEmptyAlert.setContentText("Amount field cannot be empty. Please input the rent amount");
            amountEmptyAlert.showAndWait();
        } else if (comboboxPDCheck.equals("Nasra Block") && amountPD.getText().isEmpty()){
            Alert amountEmptyAlert = new Alert(Alert.AlertType.INFORMATION);
            amountEmptyAlert.setTitle("Information Dialog");
            amountEmptyAlert.setHeaderText("Empty Field");
            amountEmptyAlert.setContentText("Amount field cannot be empty. Please input the rent amount");
            amountEmptyAlert.showAndWait();
        } else if (comboboxPDCheck.equals("Block A")){
            createPaymentDetailsTable((String)blockAComboPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText(), amountPD.getText(), monthComboPD.getSelectionModel().getSelectedItem(), getDateValueAsString(rentPaymentDatePD.getValue()) , paymentMode);
            createAndWriteExcelSheet((String)blockAComboPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText() , amountPD.getText(), monthComboPD.getSelectionModel().getSelectedItem().name(), getDateValueAsString(rentPaymentDatePD.getValue()) , paymentMode);
            setEmpty();
            blockAComboPD.setValue(null);
        }else if (comboboxPDCheck.equals("Block B")){
            createPaymentDetailsTable((String)blockBComboPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText(), amountPD.getText(), monthComboPD.getSelectionModel().getSelectedItem(), getDateValueAsString(rentPaymentDatePD.getValue()), paymentMode);
            setEmpty();
            blockBComboPD.setValue(null);
        }else if (comboboxPDCheck.equals("Block C")){
            createPaymentDetailsTable((String)blockCComboPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText(), amountPD.getText(), monthComboPD.getSelectionModel().getSelectedItem(), getDateValueAsString(rentPaymentDatePD.getValue()), paymentMode);
            setEmpty();
            blockCComboPD.setValue(null);
        }else if (comboboxPDCheck.equals("Nasra Block")){
            createPaymentDetailsTable((String)nasraBlockPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText(), amountPD.getText(), monthComboPD.getSelectionModel().getSelectedItem(), getDateValueAsString(rentPaymentDatePD.getValue()), paymentMode);
            setEmpty();
            nasraBlockPD.setValue(null);
        }else if(comboboxPDCheck.equals("Empty")){
            Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
            emptyAlert.setTitle("Information Dialog");
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
    
    private int getStringNumber(String stringExtract){
        int numberExtract = 0;
        if (stringExtract == null){
            rentArrears.setVisible(false);
        }else{
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(stringExtract);
            while (m.find()) {
                numberExtract = Integer.parseInt(m.group());
            }
        }
        return numberExtract;
    }
    
    private void rentLabel(String msg, String both){
        if (msg.startsWith("In Arrears: Ksh ")) {
            StringProperty arrears = new SimpleStringProperty(both);
            rentArrears.textProperty().bind(arrears);
            rentArrears.setStyle("-fx-text-fill: red;");
            rentArrears.setVisible(true);
            arrearsCheck = true;
        } 
    }
    JFXButton buttonA = new JFXButton();
    TreeItem<String> pseudoroot = new TreeItem<>();
    TreeItem<String> root = new TreeItem<>();
    TreeItem<String> root3 = new TreeItem<>("Cash");
    TreeItem<String> cash = new TreeItem<>("Cash recieved by:");
    TreeItem<String> root1 = new TreeItem<>("Mpesa");
    TreeItem<String> mpesa = new TreeItem<>("Enter mpesa transaction code:");
    TreeItem<String> root2 = new TreeItem<>("Banker's Cheque");
    TreeItem<String> bank = new TreeItem<>("Enter cheque no:");
    
    private void setEmpty(){
        tenantNamePD.setText("");
        amountPD.setText("");
        monthComboPD.setValue(PDModel.Strings.NONE);
        rentPaymentDatePD.setValue(null);
        cash.setValue("Cash recieved by:");
        root3.setExpanded(false);
        mpesa.setValue("Enter mpesa transaction code");
        root1.setExpanded(false);
        bank.setValue("Enter cheque no.");
        root2.setExpanded(false);
        rentArrears.setVisible(false);
    }
    
    
    public ObservableList<PDModel> getPaymentDetails(){
        ObservableList<PDModel> rentPaymentList = FXCollections.observableArrayList();
        if (comboboxPDCheck.equals("Block A")){
            try {
                String tableDataQuery = "SELECT * FROM PaymentDetails WHERE HouseNumber = ?";
                Connection conn = DriverManager.getConnection(databaseURL);
                PreparedStatement pstmt = conn.prepareStatement(tableDataQuery);
                pstmt.setString(1, (String)blockAComboPD.getSelectionModel().getSelectedItem());
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {                    
                    String houseNo = rs.getString("HouseNumber");
                    String tenantName = rs.getString("TenantName");
                    String rentAmount = rs.getString("Amount");
                    System.out.println(rs.getString("Month"));
                    PDModel.Strings month = PDModel.Strings.valueOf(rs.getString("Month"));
                    
                    String paymentDate = rs.getString("PaymentDate");
                    String paymentMethod = rs.getString("PaymentMethod");
                    PDModel paymentData = new PDModel(houseNo, tenantName, rentAmount, month, paymentDate, paymentMethod);
                    rentPaymentList.add(paymentData);
                }
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rentPaymentList;
    }
    
    class MyStringTableCell extends TableCell<PDModel, String> {
        
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? null : getString());
        }
        
        private String getString(){
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
    
    Callback<TableColumn<PDModel, String>, TableCell<PDModel, String>> customCellFactory
            = new Callback<TableColumn<PDModel, String>, TableCell<PDModel, String>>() {
        @Override
        public TableCell call(TableColumn param) {
            EditCell cell = new EditCell(IDENTITY_CONVERTER);
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            return cell;
        }
    };
    
    Callback<TableColumn<PDModel, PDModel.Strings>, TableCell<PDModel, PDModel.Strings>> customComboCellFactory
            = new Callback<TableColumn<PDModel, PDModel.Strings>, TableCell<PDModel, PDModel.Strings>>() {
        @Override
        public TableCell call(TableColumn param) {
            EditCell cell = new EditCell(IDENTITY_CONVERTER);
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            return cell;
        }
    };
    
    class MyEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {
            ContextMenu editTable = new ContextMenu();
            MenuItem edit = new MenuItem("Edit Record");
            MenuItem delete = new MenuItem("Delete Record");
            delete.setOnAction((event) -> {
                try {
                    String deleteRecord = "DELETE FROM PaymentDetails WHERE HouseNumber = ? AND PaymentDate = ?";
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(deleteRecord);
                    TablePosition cellPos = paymentsTable.getSelectionModel().getSelectedCells().get(0);
                    int row = cellPos.getRow();
                    pstmt.setString(1, paymentsTable.getItems().get(row).gethouseNumberTablePD());
                    pstmt.setString(2, paymentsTable.getItems().get(row).getpaymentDateTablePD());
                    pstmt.executeUpdate();
                    PDModel selectedItem = paymentsTable.getSelectionModel().getSelectedItem();
                    paymentsTable.getItems().remove(selectedItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setEmpty();
            });
            
            
            /*Getting value in selected cell
            TablePosition cellPos = paymentsTable.getSelectionModel().getSelectedCells().get(0);
            int row = cellPos.getRow();
            PDModel payitem = paymentsTable.getItems().get(row);
            TableColumn col = cellPos.getTableColumn();
            String payData = (String) col.getCellObservableValue(payitem).getValue();
            System.out.println(payData);*/
            
            
            paymentsTable.setOnContextMenuRequested((event) -> {
                editTable.getItems().addAll(edit, delete);
                editTable.show(paymentsTable, event.getScreenX(), event.getScreenY());
            });
            paymentsTable.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                editTable.hide(); 
            });

            if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2) {
                TableCell c = (TableCell) t.getSource();
                int index = c.getIndex();
                try {
                    PDModel item = getPaymentDetails().get(index);
                    tenantNamePD.setText(item.gettenantNameTablePD());
                    amountPD.setText(item.getamountTablePD());
                    monthComboPD.setValue(item.getmonthTablePD());
                    rentPaymentDatePD.setValue(LocalDate.parse(item.getpaymentDateTablePD(), DateTimeFormatter.ISO_DATE));
                    String payModeString = item.getpaymentMethodPD();
                    if (payModeString == null) {
                        cash.setValue("Cash recieved by:");
                        root3.setExpanded(false);
                        mpesa.setValue("Enter mpesa transaction code");
                        root1.setExpanded(false);
                        bank.setValue("Enter cheque no.");
                        root2.setExpanded(false);
                    } else {
                        String[] paymentModeCheck = payModeString.split(":");
                        switch (paymentModeCheck[0]) {
                            case "Cash payment received by":
                                payMethodCheck = "Paid in cash";
                                cash.setValue(payModeString);
                                root3.setExpanded(true);
                                break;
                            case "Mpesa transaction code is":
                                payMethodCheck = "Paid via mpesa";
                                mpesa.setValue(payModeString);
                                root1.setExpanded(true);
                                break;
                            case "Banker's cheque no":
                                payMethodCheck = "Paid via banker's cheque";
                                bank.setValue(payModeString);
                                root2.setExpanded(true);
                                break;
                            default:
                                break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void rentAmountPDUpdate(String rentAmount, String hNumber, String tName, String paymentDate){
        try {
            String rentUpdate = "UPDATE PaymentDetails SET Amount = ? WHERE HouseNumber = ? AND TenantName = ? AND PaymentDate = ?";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(rentUpdate);
            pstmt.setString(1, rentAmount);
            pstmt.setString(2, hNumber);
            pstmt.setString(3, tName);
            pstmt.setString(4, paymentDate);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void rentUpdatePDButton(){
        if(arrearsCheck  == true && comboboxPDCheck.equals("Block A")){
            rentAmountPDUpdate("Ksh "+Integer.toString(rentAmountPDPaid + getStringNumber(amountPD.getText())), (String)blockAComboPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText(), getDateValueAsString(rentPaymentDatePD.getValue()));
            System.out.println(Integer.toString(rentAmountPDPaid));
            System.out.println(getStringNumber(amountPD.getText()));
            setEmpty();
            blockAComboPD.setValue(null);
        }else if (arrearsCheck == true && comboboxPDCheck.equals("Block B")){
            rentAmountPDUpdate("Ksh "+Integer.toString(rentAmountPDPaid + getStringNumber(amountPD.getText())), (String)blockBComboPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText(), getDateValueAsString(rentPaymentDatePD.getValue()));
            setEmpty();
            blockBComboPD.setValue(null);
        }else if (arrearsCheck == true && comboboxPDCheck.equals("Block C")){
            rentAmountPDUpdate("Ksh "+Integer.toString(rentAmountPDPaid + getStringNumber(amountPD.getText())), (String)blockCComboPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText(), getDateValueAsString(rentPaymentDatePD.getValue()));
            setEmpty();
            blockCComboPD.setValue(null);
        }else if (arrearsCheck == true && comboboxPDCheck.equals("Nasra Block")){
            rentAmountPDUpdate("Ksh "+Integer.toString(rentAmountPDPaid + getStringNumber(amountPD.getText())), (String)nasraBlockPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText(), getDateValueAsString(rentPaymentDatePD.getValue()));
            setEmpty();
            nasraBlockPD.setValue(null);
        }else if (arrearsCheck  == false && comboboxPDCheck.equals("Block A")) {
            rentAmountPDUpdate(amountPD.getText(), (String)blockAComboPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText() , getDateValueAsString(rentPaymentDatePD.getValue()));
            setEmpty();
            blockAComboPD.setValue(null);
        }else if (arrearsCheck == false && comboboxPDCheck.equals("Block B")){
            rentAmountPDUpdate(amountPD.getText(), (String)blockBComboPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText() , getDateValueAsString(rentPaymentDatePD.getValue()));
            setEmpty();
            blockBComboPD.setValue(null);
        }else if (arrearsCheck == false && comboboxPDCheck.equals("Block C")){
            rentAmountPDUpdate(amountPD.getText(), (String)blockCComboPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText() , getDateValueAsString(rentPaymentDatePD.getValue()));
            setEmpty();
            blockCComboPD.setValue(null);
        }else if (arrearsCheck == false && comboboxPDCheck.equals("Nasra Block")){
            rentAmountPDUpdate(amountPD.getText(), (String)nasraBlockPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText() , getDateValueAsString(rentPaymentDatePD.getValue()));
            setEmpty();
            nasraBlockPD.setValue(null);
        }
    }
    
    private Map getReceiptParameters(){
        HashMap map = new HashMap();
        if (comboboxPDCheck.equals("Block A")) {
            map.put("houseNumber", (String) blockAComboPD.getSelectionModel().getSelectedItem());
        }else if (comboboxPDCheck.equals("Block B")){
            map.put("houseNumber", (String) blockBComboPD.getSelectionModel().getSelectedItem());
        }else if (comboboxPDCheck.equals("Block C")){
            map.put("houseNumber", (String) blockCComboPD.getSelectionModel().getSelectedItem());
        }else if (comboboxPDCheck.equals("Nasra Block")){
            map.put("houseNumber", (String) nasraBlockPD.getSelectionModel().getSelectedItem());
        }
        map.put("PayMonth", monthComboPD.getSelectionModel().getSelectedItem());
        map.put("PayMethod", payMethodCheck);
        return map;
    }
    
    @SuppressWarnings("unchecked")
    private void editFocusedCell(){
        final TablePosition<PDModel, String> focusedCell = paymentsTable
                .focusModelProperty().get().focusedCellProperty().get();
        paymentsTable.edit(focusedCell.getRow(), focusedCell.getTableColumn());
    }
    
    @SuppressWarnings("unchecked")
    private void selectPrevious(TableView rentPaymentTable) {
        if (rentPaymentTable.getSelectionModel().isCellSelectionEnabled()) {
            TablePosition<PDModel, ?> pos = rentPaymentTable.getFocusModel().getFocusedCell();
            if (pos.getColumn() - 1 >= 0) {
                rentPaymentTable.getSelectionModel().select(pos.getRow(), getTableColumn(pos.getTableColumn(), -1));
            } else if (pos.getRow() < rentPaymentTable.getItems().size()) {
                rentPaymentTable.getSelectionModel().select(pos.getRow() - 1, 
                        rentPaymentTable.getVisibleLeafColumn(
                                rentPaymentTable.getVisibleLeafColumns().size() - 1));
            }       
        } else {
            int focusindex = rentPaymentTable.getFocusModel().getFocusedIndex();
            if (focusindex == -1) {
                rentPaymentTable.getSelectionModel().select(rentPaymentTable.getItems().size() - 1);
            } else if (focusindex > 0) {
                rentPaymentTable.getSelectionModel().select(focusindex - 1);
            }
        }
    }
    
    private TableColumn<PDModel, ?> getTableColumn (final TableColumn<PDModel, ?> column, int offset) {
        int columnIndex = paymentsTable.getVisibleLeafIndex(column);
        int newColumnIndex = columnIndex + offset;
        return paymentsTable.getVisibleLeafColumn(newColumnIndex);
    }
    
     private static <S,T> TableColumn<S,T> column(String title, Function<S, ObservableValue<T>> property) {
        TableColumn<S,T> col = new TableColumn<>(title);
        col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
        return col ;
    }
    
    private void setupHouseNumberColumn() {
        houseNoCol.setPrefWidth(90);
        houseNoCol.setCellValueFactory(
                new PropertyValueFactory<PDModel, String>("houseNumberTablePD"));
        houseNoCol.setCellFactory(stringCellFactory);
        paymentsTable.refresh();
    }

    private void setupTenantNameColumn() {
        tenantNameCol.setPrefWidth(120);
        tenantNameCol.setCellValueFactory(
                new PropertyValueFactory<PDModel, String>("tenantNameTablePD"));
        tenantNameCol.setCellFactory(stringCellFactory);
    }
    
    private void setupAmountColumn() {
        amountCol.setPrefWidth(90);
        amountCol.setCellValueFactory(new PropertyValueFactory<PDModel, String>("amountTablePD"));
        amountCol.setCellFactory(customCellFactory);
        amountCol.setOnEditStart((event) -> {
            PDModel amount = event.getRowValue();
            amountPD.setText(amount.getamountTablePD());
            monthComboPD.setValue(amount.getmonthTablePD());
            rentPaymentDatePD.setValue(LocalDate.parse(amount.getpaymentDateTablePD(), DateTimeFormatter.ISO_DATE));
            String payModeString = amount.getpaymentMethodPD();
            if (payModeString == null) {
                cash.setValue("Cash recieved by:");
                root3.setExpanded(false);
                mpesa.setValue("Enter mpesa transaction code");
                root1.setExpanded(false);
                bank.setValue("Enter cheque no.");
                root2.setExpanded(false);
            } else {
                String[] paymentModeCheck = payModeString.split(":");
                switch (paymentModeCheck[0]) {
                    case "Cash payment received by":
                        payMethodCheck = "Paid in cash";
                        cash.setValue(payModeString);
                        root3.setExpanded(true);
                        break;
                    case "Mpesa transaction code is":
                        payMethodCheck = "Paid via mpesa";
                        mpesa.setValue(payModeString);
                        root1.setExpanded(true);
                        break;
                    case "Banker's cheque no":
                        payMethodCheck = "Paid via banker's cheque";
                        bank.setValue(payModeString);
                        root2.setExpanded(true);
                        break;
                    default:
                        break;
                }
            }
        });
        amountCol.setOnEditCommit((event) -> {
            PDModel amount = event.getRowValue();
            Alert commitWarning = new Alert(AlertType.WARNING, "This will edit the Amount column for "+amount.gethouseNumberTablePD()+" on "+amount.getpaymentDateTablePD()+" in PaymentDetails Table. Do you want to proceed?", ButtonType.YES, ButtonType.NO);
            commitWarning.setTitle("Edit Payment Record for " + amount.gethouseNumberTablePD());
            commitWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    amount.setamountTablePD(event.getNewValue());
                    updatePaymentTableData("Amount", event.getNewValue(), amount.gethouseNumberTablePD(), amount.getpaymentDateTablePD());
                } else if (response == ButtonType.NO) {
                   int index = paymentsTable.getSelectionModel().getSelectedIndex();
                   paymentsTable.getItems().set(index, amount);
                }
            });
        });
    }

    private void updatePaymentTableData(String column, String newValue, String houseNumber, String paymentDate) {
        try {
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE PaymentDetails SET " + column + " = ? WHERE HouseNumber = ? AND PaymentDate = ?");
            pstmt.setString(1, newValue);
            pstmt.setString(2, houseNumber);
            pstmt.setString(3, paymentDate);
            pstmt.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void setupMonthColumn() {
        monthCol.setPrefWidth(90);
        monthCol.setCellValueFactory(new PropertyValueFactory<PDModel, PDModel.Strings>("monthTablePD"));
        
    }
    
    private void setupPaymentDateColumn() {
        dateCol.setPrefWidth(90);
        dateCol.setCellValueFactory(new PropertyValueFactory<PDModel, String>("paymentDateTablePD"));
        dateCol.setCellFactory(stringCellFactory);
    }
    private void setupPaymentMethodColumn() {
        methodCol.setPrefWidth(120);
        methodCol.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
        methodCol.setCellValueFactory(new PropertyValueFactory<PDModel, String>("paymentMethodPD"));
        methodCol.setCellFactory(customCellFactory);
        
        
        methodCol.setOnEditStart((event) -> {
            PDModel method = event.getRowValue();
            amountPD.setText(method.getamountTablePD());
            monthComboPD.setValue(method.getmonthTablePD());
            rentPaymentDatePD.setValue(LocalDate.parse(method.getpaymentDateTablePD(), DateTimeFormatter.ISO_DATE));
            String payModeString = method.getpaymentMethodPD();
                    if (payModeString == null) {
                        cash.setValue("Cash recieved by:");
                        root3.setExpanded(false);
                        mpesa.setValue("Enter mpesa transaction code");
                        root1.setExpanded(false);
                        bank.setValue("Enter cheque no.");
                        root2.setExpanded(false);
                    } else {
                        String[] paymentModeCheck = payModeString.split(":");
                        switch (paymentModeCheck[0]) {
                            case "Cash payment received by":
                                payMethodCheck = "Paid in cash";
                                cash.setValue(payModeString);
                                root3.setExpanded(true);
                                break;
                            case "Mpesa transaction code is":
                                payMethodCheck = "Paid via mpesa";
                                mpesa.setValue(payModeString);
                                root1.setExpanded(true);
                                break;
                            case "Banker's cheque no":
                                payMethodCheck = "Paid via banker's cheque";
                                bank.setValue(payModeString);
                                root2.setExpanded(true);
                                break;
                            default:
                                break;
                        }
                    }
        });
        methodCol.setOnEditCommit((event) -> {
            PDModel method = event.getRowValue();
            Alert commitWarning = new Alert(AlertType.WARNING, "This will edit PaymentMethod column for "+method.gethouseNumberTablePD()+" on "+method.getpaymentDateTablePD()+" in PaymentDetails Table. Do you want to proceed?", ButtonType.YES, ButtonType.NO);
            commitWarning.setTitle("Edit Payment Record for " + method.gethouseNumberTablePD());
            commitWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    method.setamountTablePD(event.getNewValue());
                    updatePaymentTableData("PaymentMethod", event.getNewValue(), method.gethouseNumberTablePD(), method.getpaymentDateTablePD());
                } else if (response == ButtonType.NO) {
                   int index = paymentsTable.getSelectionModel().getSelectedIndex();
                   paymentsTable.getItems().set(index, method);
                }
            });
        });
    }
    
    
    
    
    public void createAndWriteExcelSheet(String hNo, String tName, String amount, String monthlyRent, String paymentDate, String paymentMethod) throws FileNotFoundException {
        File tenantDataExists = new File("jatom tenants.xls");
        HSSFWorkbook workbook;
        
        if (tenantDataExists.exists() == false) {
            workbook = new HSSFWorkbook();
        } else {
            try {
                FileInputStream inputStream = new FileInputStream(tenantDataExists);
                Workbook workbookExists = WorkbookFactory.create(inputStream);
                
                Sheet spreadSheet = workbookExists.getSheet("Payment Details");
                
                Font boldFont = workbookExists.createFont();
                boldFont.setBold(true);
                CellStyle headerRowStyle = workbookExists.createCellStyle();
                headerRowStyle.setFont(boldFont);
                
                Map<String, Object[]> tenantData = new TreeMap<String, Object[]>();
                tenantData.put("1", new Object[]{"House Number", "Tenant Name", "Amount", "MonthlyRent", "Payment Date", "Payment Method"});
                tenantData.put("2", new Object[]{hNo, tName, amount, monthlyRent, paymentDate, paymentMethod});
                
                Set<String> keyset = tenantData.keySet();
                int rownum = 0;
                if (spreadSheet == null) {
                    spreadSheet = workbookExists.createSheet("Payment Details");
                    for (String key : keyset) {
                        Row row = spreadSheet.createRow(rownum++);
                        if (rownum == 1) {
                            row.setRowStyle(headerRowStyle);
                        }
                        Object[] objArr = tenantData.get(key);
                        int cellnum = 0;
                        for (Object obj : objArr) {
                            Cell cell = row.createCell(cellnum++);
                            cell.setCellStyle(getPrefferedCellStyle(cell));
                            if (obj instanceof String) {
                                cell.setCellValue((String) obj);
                            } else if (obj instanceof Integer) {
                                cell.setCellValue((Integer) obj);
                            }
                        }
                    }
                    for (int c = 0; c < tenantData.get("1").length; c++) {
                        spreadSheet.autoSizeColumn(c); //autosize, merged cells should be ignored
                        //sheet.autoSizeColumn(rownum, true); //autosize, merged cells should be considered
                    }
                } else {
                    Object[][] tData = {{hNo, tName, amount, monthlyRent, paymentDate, paymentMethod}};

                    int rowCount = spreadSheet.getPhysicalNumberOfRows();

                    for (Object[] tBook : tData) {
                        Row row = spreadSheet.createRow(rowCount++);

                        int columnCount = 0;

                        for (Object obj : tBook) {
                            Cell cell = row.createCell(columnCount++);
                            if (obj instanceof String) {
                                cell.setCellValue((String) obj);
                            } else if (obj instanceof Integer) {
                                cell.setCellValue((Integer) obj);
                            }
                        }
                    }
                }
                inputStream.close();

                FileOutputStream outputStream = new FileOutputStream(tenantDataExists);
                workbookExists.write(outputStream);
                workbookExists.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    
    
    public void readExcelFile(File tenantDataExists) throws FileNotFoundException {
        String hNo = null;
        String tName = null;
        String amount = null;
        String month = null;
        String payDate = null;
        String payMethod = null;
        try {
            FileInputStream excelInputStream = new FileInputStream(tenantDataExists);
            Workbook workbook = WorkbookFactory.create(excelInputStream);
            Sheet sheet = workbook.getSheet("Payment Details");
            
            Iterator<Row> rowIterator = sheet.iterator();
            
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    continue;
                } else {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        
                    }
                    
                    hNo = row.getCell(0).getStringCellValue();
                    tName = row.getCell(1).getStringCellValue();
                    amount = row.getCell(2).getStringCellValue();
                    month = row.getCell(3).getStringCellValue();
                    payDate = row.getCell(4).getStringCellValue();
                    payMethod = row.getCell(5).getStringCellValue();
                    System.out.println(hNo);
                    System.out.println(tName);
                    System.out.println(amount);
                    System.out.println(month);
                    System.out.println(payDate);
                    System.out.println(payMethod);
                }

            }
            excelInputStream.close();
        } catch (Exception e) {
        }
        createPaymentDetailsTable(hNo, tName, amount, PDModel.Strings.valueOf(month), payDate, payMethod);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        blockAComboPD.setItems(blockA);
        blockBComboPD.setItems(blockB);
        blockCComboPD.setItems(blockC);
        nasraBlockPD.setItems(nasraBlock);
        
        setupHouseNumberColumn();
        setupTenantNameColumn();
        setupAmountColumn();
        setupMonthColumn();
        setupPaymentDateColumn();
        setupPaymentMethodColumn();
        
        
        monthComboPD.getItems().addAll(PDModel.Strings.values());
        

        houseComboTitledPanePD.setOnMouseClicked((event) -> {
            blockAComboPD.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String paymentModeString;
                try {
                    String searchPDSql = "SELECT * FROM PaymentDetails WHERE HouseNumber = ?";
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchPDSql);
                    pstmt.setString(1, (String)blockAComboPD.getSelectionModel().getSelectedItem());
                    ResultSet rs = pstmt.executeQuery();
                    String searchTDSql = "SELECT RentAmount FROM TenantDetails WHERE HouseNumber = ?";
                    PreparedStatement pstmt1 = conn.prepareStatement(searchTDSql);
                    pstmt1.setString(1, (String)blockAComboPD.getSelectionModel().getSelectedItem());
                    ResultSet rs1 = pstmt1.executeQuery();
                    if(!rs.next()){
                        setEmpty();
                    }else
                        do {                            
                            tenantNamePD.setText(rs.getString("TenantName"));
                            
                            int expectedRent = getStringNumber(rs1.getString("RentAmount"));
                            int paidRent = getStringNumber(rs.getString("Amount"));
                            System.out.println(paidRent);
                            int arrear = expectedRent - paidRent;
                            System.out.println(arrear);
                            if (arrear > 0 && rs.getString("Amount") != null) {
                                rentLabel("In Arrears: Ksh ", "In Arrears: Ksh " + Integer.toString(arrear));
                                System.out.println("In Arrears: Ksh " + Integer.toString(arrear));
                            } else if (arrear == 0 || arrear < 0) {
                                rentArrears.setVisible(false);
                            }
                            
                            amountPD.setText(rs.getString("Amount"));
                            System.out.println(rs.getString("Month"));
                            monthComboPD.setValue(PDModel.Strings.valueOf(rs.getString("Month")));
                            if (rs.getString("PaymentDate") != null){
                                rentPaymentDatePD.setValue(LocalDate.parse(rs.getString("PaymentDate"), DateTimeFormatter.ISO_DATE));
                            }else
                                rentPaymentDatePD.setValue(null);
                            
                            paymentModeString = rs.getString("PaymentMethod");
                            if (paymentModeString == null) {
                                cash.setValue("Cash recieved by:");
                                root3.setExpanded(false);
                                mpesa.setValue("Enter mpesa transaction code");
                                root1.setExpanded(false);
                                bank.setValue("Enter cheque no.");
                                root2.setExpanded(false);
                            } else {
                                String[] paymentModeCheck = paymentModeString.split(":");
                                switch (paymentModeCheck[0]) {
                                    case "Cash payment received by":
                                        payMethodCheck = "Paid in cash";
                                        cash.setValue(paymentModeString);
                                        root3.setExpanded(true);
                                        break;
                                    case "Mpesa transaction code is":
                                        payMethodCheck = "Paid via mpesa";
                                        mpesa.setValue(paymentModeString);
                                        root1.setExpanded(true);
                                        break;
                                    case "Banker's cheque no":
                                        payMethodCheck = "Paid via banker's cheque";
                                        bank.setValue(paymentModeString);
                                        root2.setExpanded(true);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (rs.getString("Amount") == null) {
                                addCheck.set("Empty");
                            } else if (paidRent == expectedRent) {
                                addCheck.set("Cleared");
                            }
                            else
                                addCheck.set("Occupied");
                            
                            rentAmountPDPaid = getStringNumber(rs.getString("Amount"));
                            
                        } while (rs.next());
                    
                    pstmt.close();
                    pstmt1.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                comboboxPDCheck = "Block A";
                Label label = new Label();
                label.setText((String)blockAComboPD.getSelectionModel().getSelectedItem());
                houseComboSelect = (String)blockAComboPD.getSelectionModel().getSelectedItem();
                label.setStyle("-fx-text-fill: #fdfdfd;");
                houseComboTitledPanePD.setText("");
                houseComboTitledPanePD.setGraphic(label);
                houseComboTitledPanePD.setExpanded(false);
                payTenantDetails = getPaymentDetails();
                paymentsTable.setItems(payTenantDetails);
            });
            blockBComboPD.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String paymentModeString;
                try {
                    String searchPDSql = "SELECT * FROM PaymentDetails WHERE HouseNumber = ?";
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchPDSql);
                    pstmt.setString(1, (String)blockBComboPD.getSelectionModel().getSelectedItem());
                    ResultSet rs = pstmt.executeQuery();
                    String searchTDSql = "SELECT RentAmount FROM TenantDetails WHERE HouseNumber = ?";
                    PreparedStatement pstmt1 = conn.prepareStatement(searchTDSql);
                    pstmt1.setString(1, (String)blockBComboPD.getSelectionModel().getSelectedItem());
                    ResultSet rs1 = pstmt1.executeQuery();
                    if(!rs.next()){
                        setEmpty();
                    }else
                        do {                            
                            tenantNamePD.setText(rs.getString("TenantName"));
                            
                            int expectedRent = getStringNumber(rs1.getString("RentAmount"));
                            int paidRent = getStringNumber(rs.getString("Amount"));
                            System.out.println(paidRent);
                            int arrear = expectedRent - paidRent;
                            System.out.println(arrear);
                            if (arrear != 0 && rs.getString("Amount") != null) {
                                rentLabel("In Arrears: Ksh ", "In Arrears: Ksh " + Integer.toString(arrear));
                                System.out.println("In Arrears: Ksh " + Integer.toString(arrear));
                            } else if (arrear == 0 || arrear < 0) {
                                rentArrears.setVisible(false);
                            }
                            
                            amountPD.setText(rs.getString("Amount"));
                            monthComboPD.setValue(PDModel.Strings.valueOf(rs.getString("Month")));
                            if (rs.getString("PaymentDate") != null){
                                rentPaymentDatePD.setValue(LocalDate.parse(rs.getString("PaymentDate"), DateTimeFormatter.ISO_DATE));
                            }else
                                rentPaymentDatePD.setValue(null);
                            
                            paymentModeString = rs.getString("PaymentMethod");
                            if (paymentModeString == null) {
                                cash.setValue("Cash recieved by:");
                                root3.setExpanded(false);
                                mpesa.setValue("Enter mpesa transaction code");
                                root1.setExpanded(false);
                                bank.setValue("Enter cheque no.");
                                root2.setExpanded(false);
                            } else {
                                String[] paymentModeCheck = paymentModeString.split(":");
                                switch (paymentModeCheck[0]) {
                                    case "Cash payment received by":
                                        payMethodCheck = "Paid in cash";
                                        cash.setValue(paymentModeString);
                                        root3.setExpanded(true);
                                        break;
                                    case "Mpesa transaction code is":
                                        payMethodCheck = "Paid via mpesa";
                                        mpesa.setValue(paymentModeString);
                                        root1.setExpanded(true);
                                        break;
                                    case "Banker's cheque no":
                                        payMethodCheck = "Paid via banker's cheque";
                                        bank.setValue(paymentModeString);
                                        root2.setExpanded(true);
                                        break;
                                    default:
                                        break;
                                }
                                if (rs.getString("Amount") == null) {
                                    addCheck.set("Empty");
                                } else if (paidRent == expectedRent) {
                                    addCheck.set("Cleared");
                                }
                                else {
                                    addCheck.set("Occupied");
                                }
                                rentAmountPDPaid = getStringNumber(rs.getString("Amount"));
                            }
                        } while (rs.next());
                    pstmt.close();
                    pstmt1.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                comboboxPDCheck = "Block B";
                Label label = new Label();
                label.setText((String)blockBComboPD.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: #fdfdfd;");
                houseComboTitledPanePD.setText("");
                houseComboTitledPanePD.setGraphic(label);
                houseComboTitledPanePD.setExpanded(false);
                payTenantDetails = getPaymentDetails();
                paymentsTable.setItems(payTenantDetails);
            });
            blockCComboPD.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String paymentModeString;
                try {
                    String searchPDSql = "SELECT * FROM PaymentDetails WHERE HouseNumber = ?";
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchPDSql);
                    pstmt.setString(1, (String)blockCComboPD.getSelectionModel().getSelectedItem());
                    ResultSet rs = pstmt.executeQuery();
                    String searchTDSql = "SELECT RentAmount FROM TenantDetails WHERE HouseNumber = ?";
                    PreparedStatement pstmt1 = conn.prepareStatement(searchTDSql);
                    pstmt1.setString(1, (String)blockCComboPD.getSelectionModel().getSelectedItem());
                    ResultSet rs1 = pstmt1.executeQuery();
                    if(!rs.next()){
                        setEmpty();
                    }else
                        do {                            
                            tenantNamePD.setText(rs.getString("TenantName"));
                            
                            int expectedRent = getStringNumber(rs1.getString("RentAmount"));
                            int paidRent = getStringNumber(rs.getString("Amount"));
                            System.out.println(paidRent);
                            int arrear = expectedRent - paidRent;
                            System.out.println(arrear);
                            if (arrear != 0 && rs.getString("Amount") != null) {
                                rentLabel("In Arrears: Ksh ", "In Arrears: Ksh " + Integer.toString(arrear));
                                System.out.println("In Arrears: Ksh " + Integer.toString(arrear));
                            } else if (arrear == 0 || arrear < 0) {
                                rentArrears.setVisible(false);
                            }
                            amountPD.setText(rs.getString("Amount"));
                            monthComboPD.setValue(PDModel.Strings.valueOf(rs.getString("Month")));
                            if (rs.getString("PaymentDate") != null){
                                rentPaymentDatePD.setValue(LocalDate.parse(rs.getString("PaymentDate"), DateTimeFormatter.ISO_DATE));
                            }else
                                rentPaymentDatePD.setValue(null);
                            
                            paymentModeString = rs.getString("PaymentMethod");
                            if (paymentModeString == null) {
                                cash.setValue("Cash recieved by:");
                                root3.setExpanded(false);
                                mpesa.setValue("Enter mpesa transaction code");
                                root1.setExpanded(false);
                                bank.setValue("Enter cheque no.");
                                root2.setExpanded(false);
                            } else {
                                String[] paymentModeCheck = paymentModeString.split(":");
                                switch (paymentModeCheck[0]) {
                                    case "Cash payment received by":
                                        payMethodCheck = "Paid in cash";
                                        cash.setValue(paymentModeString);
                                        root3.setExpanded(true);
                                        break;
                                    case "Mpesa transaction code is":
                                        payMethodCheck = "Paid via mpesa";
                                        mpesa.setValue(paymentModeString);
                                        root1.setExpanded(true);
                                        break;
                                    case "Banker's cheque no":
                                        payMethodCheck = "Paid via banker's cheque";
                                        bank.setValue(paymentModeString);
                                        root2.setExpanded(true);
                                        break;
                                    default:
                                        break;
                                }
                                if (rs.getString("Amount") == null) {
                                    addCheck.set("Empty");
                                } else if (paidRent == expectedRent) {
                                    addCheck.set("Cleared");
                                }
                                else {
                                    addCheck.set("Occupied");
                                }
                                rentAmountPDPaid = getStringNumber(rs.getString("Amount"));
                            }
                        } while (rs.next());
                    pstmt.close();
                    pstmt1.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                comboboxPDCheck = "Block C";
                Label label = new Label();
                label.setText((String)blockCComboPD.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: #fdfdfd;");
                houseComboTitledPanePD.setText("");
                houseComboTitledPanePD.setGraphic(label);
                houseComboTitledPanePD.setExpanded(false);
                payTenantDetails = getPaymentDetails();
                paymentsTable.setItems(payTenantDetails);
            });
            nasraBlockPD.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String paymentModeString;
                try {
                    String searchPDSql = "SELECT * FROM PaymentDetails WHERE HouseNumber = ?";
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchPDSql);
                    pstmt.setString(1, (String)nasraBlockPD.getSelectionModel().getSelectedItem());
                    ResultSet rs = pstmt.executeQuery();
                    String searchTDSql = "SELECT RentAmount FROM TenantDetails WHERE HouseNumber = ?";
                    PreparedStatement pstmt1 = conn.prepareStatement(searchTDSql);
                    pstmt1.setString(1, (String)nasraBlockPD.getSelectionModel().getSelectedItem());
                    ResultSet rs1 = pstmt1.executeQuery();
                    if(!rs.next()){
                        setEmpty();
                    }else
                        do {                            
                            tenantNamePD.setText(rs.getString("TenantName"));
                            
                            int expectedRent = getStringNumber(rs1.getString("RentAmount"));
                            int paidRent = getStringNumber(rs.getString("Amount"));
                            System.out.println(paidRent);
                            int arrear = expectedRent - paidRent;
                            System.out.println(arrear);
                            if (arrear != 0 && rs.getString("Amount") != null) {
                                rentLabel("In Arrears: Ksh ", "In Arrears: Ksh " + Integer.toString(arrear));
                                System.out.println("In Arrears: Ksh " + Integer.toString(arrear));
                            } else if (arrear == 0 || arrear < 0) {
                                rentArrears.setVisible(false);
                            }
                            
                            amountPD.setText(rs.getString("Amount"));
                            monthComboPD.setValue(PDModel.Strings.valueOf(rs.getString("Month")));
                            if (rs.getString("PaymentDate") != null){
                                rentPaymentDatePD.setValue(LocalDate.parse(rs.getString("PaymentDate"), DateTimeFormatter.ISO_DATE));
                            }else
                                rentPaymentDatePD.setValue(null);
                            
                            paymentModeString = rs.getString("PaymentMethod");
                            if (paymentModeString == null) {
                                cash.setValue("Cash recieved by:");
                                root3.setExpanded(false);
                                mpesa.setValue("Enter mpesa transaction code");
                                root1.setExpanded(false);
                                bank.setValue("Enter cheque no.");
                                root2.setExpanded(false);
                            } else {
                                String[] paymentModeCheck = paymentModeString.split(":");
                                switch (paymentModeCheck[0]) {
                                    case "Cash payment received by":
                                        payMethodCheck = "Paid in cash";
                                        cash.setValue(paymentModeString);
                                        root3.setExpanded(true);
                                        break;
                                    case "Mpesa transaction code is":
                                        payMethodCheck = "Paid via mpesa";
                                        mpesa.setValue(paymentModeString);
                                        root1.setExpanded(true);
                                        break;
                                    case "Banker's cheque no":
                                        payMethodCheck = "Paid via banker's cheque";
                                        bank.setValue(paymentModeString);
                                        root2.setExpanded(true);
                                        break;
                                    default:
                                        break;
                                }
                                if (rs.getString("Amount") == null) {
                                    addCheck.set("Empty");
                                } else if (paidRent == expectedRent) {
                                    addCheck.set("Cleared");
                                }
                                else {
                                    addCheck.set("Occupied");
                                }
                                rentAmountPDPaid = getStringNumber(rs.getString("Amount"));
                            }
                        } while (rs.next());
                    pstmt.close();
                    pstmt1.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                comboboxPDCheck = "Nasra Block";
                Label label = new Label();
                label.setText((String)blockAComboPD.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: #fdfdfd;");
                houseComboTitledPanePD.setText("");
                houseComboTitledPanePD.setGraphic(label);
                houseComboTitledPanePD.setExpanded(false);
                payTenantDetails = getPaymentDetails();
                paymentsTable.setItems(payTenantDetails);
            });
        });
        
        
        monthComboPD.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            monthComboPD.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String rentPayMethodPD = null;
                String rentPayMonthPD = null;
                int paidRent;
                int expectedRent;
                
                try {
                   String searchPDTable = "SELECT * FROM PaymentDetails WHERE HouseNumber = ? AND Month = ?";
                   Connection conn = DriverManager.getConnection(databaseURL);
                   PreparedStatement pstmt = conn.prepareStatement(searchPDTable);
                   String searchTDTable = "SELECT RentAmount FROM TenantDetails WHERE HouseNumber = ?";
                   PreparedStatement pstmt1 = conn.prepareStatement(searchTDTable);
                   switch (comboboxPDCheck){
                       case "Block A":
                           pstmt.setString(1, (String)blockAComboPD.getSelectionModel().getSelectedItem());
                           pstmt1.setString(1, (String)blockAComboPD.getSelectionModel().getSelectedItem());
                           break;
                       case "Block B":
                           pstmt.setString(1, (String)blockBComboPD.getSelectionModel().getSelectedItem());
                           pstmt1.setString(1, (String)blockBComboPD.getSelectionModel().getSelectedItem());
                           break;
                       case "Block C":
                           pstmt.setString(1, (String)blockCComboPD.getSelectionModel().getSelectedItem());
                           pstmt1.setString(1, (String)blockCComboPD.getSelectionModel().getSelectedItem());
                           break;
                       case "Nasra Block":
                           pstmt.setString(1, (String)nasraBlockPD.getSelectionModel().getSelectedItem());
                           pstmt1.setString(1, (String)nasraBlockPD.getSelectionModel().getSelectedItem());
                           break;
                       default:
                           break;
                   }
                   pstmt.setString(2, monthComboPD.getSelectionModel().getSelectedItem().name());
                   ResultSet rs = pstmt.executeQuery();
                   ResultSet rs1 = pstmt1.executeQuery();
                   if (!rs.next()){
                       amountPD.setText("");
                       rentPaymentDatePD.setValue(null);
                       rentArrears.setVisible(false);
                   } else {
                       do {
                           rentPayMonthPD = rs.getString("Month");
                           if (rentPayMonthPD.isEmpty() || rentPayMonthPD == null){
                               amountPD.setText("");
                           }else
                               amountPD.setText(rs.getString("Amount"));
                           
                           Object paymentDate = rs.getObject("PaymentDate");
                           if (paymentDate == null){
                               rentPaymentDatePD.setValue(null);
                           } else 
                               rentPaymentDatePD.setValue(LocalDate.parse(rs.getString("PaymentDate"), DateTimeFormatter.ISO_DATE));
                           
                           rentPayMethodPD = rs.getString("PaymentMethod");
                           paidRent = getStringNumber(rs.getString("Amount"));
                           System.out.println(paidRent);
                           expectedRent = getStringNumber(rs1.getString("RentAmount"));
                           int arrears = expectedRent - paidRent;
                           if (!rs1.next() || arrears == 0){
                               rentArrears.setVisible(false);
                           }else {
                               if (arrears > 0){
                                   do {                                       
                                       rentLabel("In Arrears: Ksh ", "In Arrears: Ksh "+Integer.toString(arrears));
                                   } while (rs1.next());
                               }
                           }
                           if (rs.getString("Amount") == null) {
                               addCheck.set("Empty");
                           } else if (paidRent == expectedRent) {
                               addCheck.set("Cleared");
                           } else {
                               addCheck.set("Occupied");
                           }
                           rentAmountPDPaid = getStringNumber(rs.getString("Amount"));
                       } while (rs.next());
                   }
                   pstmt.close();
                   pstmt1.close();
                   conn.close();
                   
                   if (rentPayMethodPD == null) {
                        cash.setValue("Cash received by:");
                        root3.setExpanded(false);
                        mpesa.setValue("Enter mpesa transaction code:");
                        root1.setExpanded(false);
                        bank.setValue("Enter cheque no:");
                        root2.setExpanded(false);
                    } else {
                        String[] paystring = rentPayMethodPD.split(":");
                        if (paystring[0].equals("Cash payment received by")) {
                            cash.setValue(rentPayMethodPD);
                            if (!root3.isExpanded()) {
                                root3.setExpanded(true);
                            }
                        } else if (paystring[0].equals("Mpesa transaction code is")) {
                            mpesa.setValue(rentPayMethodPD);
                            if (!root1.isExpanded()) {
                                root1.setExpanded(true);
                            }
                        } else if (paystring[0].equals("Banker's cheque no")) {
                            bank.setValue(rentPayMethodPD);
                            if (!root2.isExpanded()) {
                                root2.setExpanded(true);
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                monthComboSelect = (String)monthComboPD.getSelectionModel().getSelectedItem().name();
            });
        });
        
        
        root3.getChildren().add(cash);
        root1.getChildren().add(mpesa);
        root2.getChildren().add(bank);
        root.setExpanded(true);
        root.getChildren().addAll(root3,root1,root2);
        
        paymentMethodPD.setOnEditStart((event) -> {
            System.out.println("Start of edit");
        });
        paymentMethodPD.setOnEditCommit((event) -> {    
            if (paymentMethodPD.editingItemProperty().getValue().equals(cash)){
                paymentMode = "Cash payment received by: "+event.getNewValue();
                System.out.println(paymentMode);
            }else if (paymentMethodPD.editingItemProperty().getValue().equals(mpesa)){
                paymentMode = "Mpesa transaction code is: "+event.getNewValue();
                System.out.println(paymentMode);
            }else if (paymentMethodPD.editingItemProperty().getValue().equals(bank)){
                paymentMode = "Banker's cheque no: "+event.getNewValue();
                System.out.println(paymentMode);
            }
        });
        
        
        root3.expandedProperty().addListener((observable, oldValue, newValue) -> {
           if (root3.isExpanded()){
               root1.setExpanded(false);
               root2.setExpanded(false);
           }
        });
        root2.expandedProperty().addListener((observable, oldValue, newValue) -> {
            if (root2.isExpanded()){
                root3.setExpanded(false);
                root1.setExpanded(false);
            }
        });
        root1.expandedProperty().addListener((observable, oldValue, newValue) -> {
            if (root1.isExpanded()){
                root2.setExpanded(false);
                root3.setExpanded(false);
            }
        });
        
        paymentMethodPD.setEditable(true);
        paymentMethodPD.setCellFactory(TextFieldTreeCell.forTreeView());
        paymentMethodPD.setRoot(root);
        paymentMethodPD.setShowRoot(false);
        
        tenantNamePD.setDisable(true);
        
        rentArrears.setVisible(false);
        
        
        updatePDAmount.setVisible(false);
        addCheck.addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Occupied")) {
                updatePDAmount.setGraphic(GlyphsDude.createIconButton(MaterialIcon.ADD, "", "20", "12", ContentDisplay.GRAPHIC_ONLY));
                updatePDAmount.setPadding(Insets.EMPTY);
                updatePDAmount.visibleProperty().bind(amountPD.textProperty().isEmpty().not());
            } else if (newValue.equals("Empty")) {
                updatePDAmount.visibleProperty().unbind();
                updatePDAmount.setVisible(false);
            } else if(newValue.equals("Cleared")){
                updatePDAmount.visibleProperty().unbind();
                updatePDAmount.setVisible(false);
            }
        });    
        PDAnchor.setOnMouseClicked((event) -> {
            houseComboTitledPanePD.setExpanded(false);
        });
        
        saveButtonPD.setGraphic(GlyphsDude.createIconButton(MaterialDesignIcon.CONTENT_SAVE, "Save", "20", "14", ContentDisplay.RIGHT));
        saveButtonPD.setPadding(Insets.EMPTY);
        
        ContextMenu editMenu = new ContextMenu();
        MenuItem edit = new MenuItem("Edit");
        MenuItem printReceipt = new MenuItem("Print Receipt");
        edit.setOnAction((event) -> {
            if (updatePDAmount.isVisible()) {
                editMenu.hide();
            } else {
                System.out.println("Edit works");
                updatePDAmount.setGraphic(GlyphsDude.createIconButton(MaterialIcon.ADD, "", "20", "12", ContentDisplay.GRAPHIC_ONLY));
                updatePDAmount.setPadding(Insets.EMPTY);
                updatePDAmount.setVisible(true);
            }
        });
        printReceipt.setOnAction((event) -> {
            Map map = null;

            try {
                map = getReceiptParameters();
            } catch (Exception e) {
                e.printStackTrace();
                String message = "An Error occured while compiling the report";
                Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
                alert.setTitle("Error occured");
                alert.setHeaderText("Error in retrieving data for printing the receipt");
                alert.showAndWait();
                return;
            }

            final String resourcePath = "C:\\Users\\bonyo\\Documents\\NetbeansProjects\\Rental-Application-Vertical-Menu\\src\\main\\resources\\Receipt\\Receipt.jrxml";
            JasperPrint jasperPrint = null;
            Connection conn = null;
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(databaseURL);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                JasperReport jasperreport = JasperCompileManager.compileReport(resourcePath);
                jasperPrint = JasperFillManager.fillReport(jasperreport, map, conn);
            } catch (Exception e) {
                e.printStackTrace();
                String message = "An Error occurred while preparing to print the receipt";
                Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
                alert.setTitle("Error Occurred");
                alert.setHeaderText("Error in printing the receipt");
                alert.showAndWait();
            }
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setTitle("Rent Receipt");
            jasperViewer.setVisible(true); 
        });
        edit.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
        
        paymentVbox.setOnContextMenuRequested((event) -> {
            editMenu.getItems().addAll(edit, printReceipt);
            editMenu.show(PDAnchor, event.getScreenX(), event.getScreenY());
        });
        paymentVbox.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            editMenu.hide();
        });
        
        
        payRecordsFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<PDModel> filteredList = new FilteredList<>(payTenantDetails);
            filteredList.setPredicate((t) -> {
                if (payRecordsFilter.getText() == null || payRecordsFilter.getText().isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                if (t.gettenantNameTablePD().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
            SortedList<PDModel> sortedData = new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(paymentsTable.comparatorProperty());
            paymentsTable.setItems(sortedData);
        });
        
        monthComboPD.valueProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<PDModel> filteredList = new FilteredList<>(payTenantDetails);
            filteredList.setPredicate((t) -> {
                if (monthComboPD.getValue() == null || monthComboPD.getSelectionModel().isEmpty()) {
                    return true;
                }
                PDModel.Strings comboFilter = newValue;
                if (t.getmonthTablePD().equals(newValue)) {
                    return true;
                }
                return false;
            });
            SortedList<PDModel> sortedData = new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(paymentsTable.comparatorProperty());
            paymentsTable.setItems(sortedData);
        });
        
        clearButton.setOnAction((event) -> {
            payRecordsFilter.clear();
            monthComboPD.setValue(PDModel.Strings.NONE);
        });
        
        paymentsTable.setEditable(true);
        paymentsTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        paymentsTable.setOnKeyPressed((event) -> {
            TablePosition<PDModel, ?> pos = paymentsTable.getFocusModel().getFocusedCell();
            if (pos != null && event.getCode().isLetterKey()) {
                paymentsTable.edit(pos.getRow(), pos.getTableColumn());
            } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.TAB) {
                paymentsTable.getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT) {
                selectPrevious(paymentsTable);
            }
        });
        paymentsTable.getColumns().addAll(houseNoCol, tenantNameCol, amountCol, monthCol, dateCol, methodCol);
        PDAnchor.setBottom(paymentsTable);
    }
   
    
}
