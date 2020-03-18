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
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import com.sun.javafx.scene.control.skin.TableViewSkin;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
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
    
   
    private JFXTextField tenantNamePD;
    
    
    public JFXTextField amountPD;
    
    
    public JFXComboBox<PDModel.Strings> monthComboPD = new JFXComboBox<>();
    
    
    private JFXDatePicker rentPaymentDatePD;
    
    
    private JFXTreeView<String> paymentMethodPD;
    
    
    private Label rentArrears;
    
    @FXML
    private SplitPane sp1;
    
    @FXML
    private SplitPane sp2;
    
    private BorderPane tableViewPane = new BorderPane();
    
    @FXML
    private BorderPane selectionPane;
    
    @FXML
    private BorderPane detailsPane;;
    
    private VBox tdVbox = new VBox(10);
    
    private VBox pdVbox = new VBox(10);
    
    private VBox rdVbox = new VBox(10);
    
    private VBox pdTitledPaneVbox = new VBox();
    
    private VBox placingVbox = new VBox();
    
    private VBox radioVbox = new VBox(5);
    
    public JFXButton updatePDAmount;
    
    private Button saveButtonPD;
    
    
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
    
    
    private TextField payRecordsFilter = new TextField();
    
 
    private Button clearButton = new Button();
    
    
    public JFXTreeView<String> blockTreeView = new JFXTreeView<>();
    
    private TreeItem<String> rootBlock = new TreeItem<>();
    private TreeItem<String> blockA = new TreeItem<>("Block A");
    private TreeItem<String> blockB = new TreeItem<>("Block B");
    private TreeItem<String> blockC = new TreeItem<>("Block C");
    private TreeItem<String> nasraBlock = new TreeItem<>("Nasra Block");
    
    TreeItem<String> a1 = new TreeItem<>("A1");
    TreeItem<String> a2 = new TreeItem<>("A2");
    TreeItem<String> a3 = new TreeItem<>("A3");
    TreeItem<String> a4 = new TreeItem<>("A4");
    TreeItem<String> a5 = new TreeItem<>("A5");
    TreeItem<String> a6 = new TreeItem<>("A6");
    TreeItem<String> a7 = new TreeItem<>("A7");
    TreeItem<String> a8 = new TreeItem<>("A8");
    TreeItem<String> a9 = new TreeItem<>("A9");
    TreeItem<String> a10 = new TreeItem<>("A10");
    TreeItem<String> a11 = new TreeItem<>("A11");
    TreeItem<String> a12 = new TreeItem<>("A12");
    
    TreeItem<String> b1 = new TreeItem<>("B1");
    TreeItem<String> b2 = new TreeItem<>("B2");
    TreeItem<String> b3 = new TreeItem<>("B3");
    TreeItem<String> b4 = new TreeItem<>("B4");
    TreeItem<String> b5 = new TreeItem<>("B5");
    TreeItem<String> b6 = new TreeItem<>("B6");
    TreeItem<String> b7 = new TreeItem<>("B7");
    TreeItem<String> b8 = new TreeItem<>("B8");
    TreeItem<String> b9 = new TreeItem<>("B9");
    TreeItem<String> b10 = new TreeItem<>("B10");
    TreeItem<String> b11 = new TreeItem<>("B11");
    TreeItem<String> b12 = new TreeItem<>("B12");
    
    TreeItem<String> c1 = new TreeItem<>("C1");
    TreeItem<String> c2 = new TreeItem<>("C2");
    TreeItem<String> c3 = new TreeItem<>("C3");
    TreeItem<String> c4 = new TreeItem<>("C4");
    TreeItem<String> c5 = new TreeItem<>("C5");
    TreeItem<String> c6 = new TreeItem<>("C6");
    TreeItem<String> c7 = new TreeItem<>("C7");
    TreeItem<String> c8 = new TreeItem<>("C8");
    TreeItem<String> c9 = new TreeItem<>("C9");
    TreeItem<String> c10 = new TreeItem<>("C10");
    TreeItem<String> c11 = new TreeItem<>("C11");
    TreeItem<String> c12 = new TreeItem<>("C12");
    
    TreeItem<String> d1 = new TreeItem<>("D1");
    TreeItem<String> d2 = new TreeItem<>("D2");
    
    private TabPane tenantDataPane = new TabPane();
    public Tab tenantDetails = new Tab("Tenant Details", tdVbox);
    public Tab paymentDetails = new Tab("Payment Details", pdVbox);
    public Tab repairDetails = new Tab("Repairs Details", rdVbox);
    
    Label l1 = new Label("Tenant Name");
    Label l2 = new Label("Phone Number");
    Label l3 = new Label("Monthly Rent");
    Label l4 = new Label("House Deposit");
    Label l5 = new Label("Rent-Due-Date");
    Label l6 = new Label("Move-In-Date");
    Label l7 = new Label("Move-Out-Date");
    Label l8 = new Label("Lease-Start-Date");
    Label l9 = new Label("Lease-End-Date");
    
    Label l10 = new Label("Tenant Name");
    Label l11 = new Label("Month");
    Label l12 = new Label("Amount Paid");
    Label l13 = new Label("Payment Date");
    Label l14 = new Label("Payment Option");
    
    private JFXTextField tdName = new JFXTextField();
    private JFXTextField tdPhone = new JFXTextField();
    private JFXTextField tdAmount = new JFXTextField();
    private JFXTextField tdDeposit = new JFXTextField();
    private JFXTextField tdDueDate = new JFXTextField();
    private JFXDatePicker tdMoveInDate = new JFXDatePicker();
    private JFXDatePicker tdMoveOutDate = new JFXDatePicker();
    private JFXDatePicker tdLeaseStartDate = new JFXDatePicker();
    private JFXDatePicker tdLeaseEndDate = new JFXDatePicker();
    
    private JFXTextField pdName = new JFXTextField();
    private JFXComboBox<PDModel.Strings> pdMonthCombo = new JFXComboBox<>();
    private JFXTextField pdAmount = new JFXTextField();
    private JFXDatePicker pdPaymentDate = new JFXDatePicker();
    private TitledPane pdPaymentOption = new TitledPane("Choose Option", pdTitledPaneVbox);
    private JFXButton pdTableViewButton = new JFXButton("Show detailed view");
    private JFXButton pdTableViewHide = new JFXButton("Hide detaild view");
    private JFXRadioButton cashRadioButton = new JFXRadioButton("Cash");
    private JFXRadioButton bankRadioButton = new JFXRadioButton("Bank Deposit Slip");
    private JFXRadioButton mpesaRadioButton = new JFXRadioButton("MPESA");
    private ToggleGroup payOptionGroup = new ToggleGroup();
    
    HBox tdHbox1 = new HBox(10, l1, tdName);
    HBox tdHbox2 = new HBox(10, l2, tdPhone);
    HBox tdHbox3 = new HBox(10, l3, tdAmount);
    HBox tdHbox4 = new HBox(10, l4, tdDeposit);
    HBox tdHbox5 = new HBox(10, l5, tdDueDate);
    HBox tdHbox6 = new HBox(10, l6, tdMoveInDate);
    HBox tdHbox7 = new HBox(10, l7, tdMoveOutDate);
    HBox tdHbox8 = new HBox(10, l8, tdLeaseStartDate);
    HBox tdHbox9 = new HBox(10, l9, tdLeaseEndDate);
    
    HBox pdHbox1 = new HBox(10, l10, pdName);
    HBox pdHbox2 = new HBox(10, l11, pdMonthCombo);
    HBox pdHbox3 = new HBox(10, l12, pdAmount);
    HBox pdHbox4 = new HBox(10, l13, pdPaymentDate);
    HBox pdHbox5 = new HBox(10, l14, placingVbox);
    HBox pdHbox6 = new HBox(10, pdTableViewButton, pdTableViewHide);
    
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
            createPaymentDetailsTable(blockTreeView.getSelectionModel().getSelectedItem().getValue(), tenantNamePD.getText(), amountPD.getText(), monthComboPD.getSelectionModel().getSelectedItem(), getDateValueAsString(rentPaymentDatePD.getValue()) , paymentMode);
            createAndWriteExcelSheet(blockTreeView.getSelectionModel().getSelectedItem().getValue(), tenantNamePD.getText() , amountPD.getText(), monthComboPD.getSelectionModel().getSelectedItem().name(), getDateValueAsString(rentPaymentDatePD.getValue()) , paymentMode);
            setEmpty();
        
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
    
    private void setEmpty1() {
        tdName.setText("");
        tdPhone.setText("");
        tdAmount.setText("");
        tdDeposit.setText("");
        tdDueDate.setText("");
        tdMoveInDate.setValue(null);
        tdMoveOutDate.setValue(null);
        tdLeaseStartDate.setValue(null);
        tdLeaseEndDate.setValue(null);
    }
    
    
    public ObservableList<PDModel> getPaymentDetails(){
        ObservableList<PDModel> rentPaymentList = FXCollections.observableArrayList();
        if (comboboxPDCheck.equals("Block A")){
            try {
                String tableDataQuery = "SELECT * FROM PaymentDetails WHERE HouseNumber = ?";
                Connection conn = DriverManager.getConnection(databaseURL);
                PreparedStatement pstmt = conn.prepareStatement(tableDataQuery);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
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
            rentAmountPDUpdate("Ksh "+Integer.toString(rentAmountPDPaid + getStringNumber(amountPD.getText())), blockTreeView.getSelectionModel().getSelectedItem().getValue(), tenantNamePD.getText(), getDateValueAsString(rentPaymentDatePD.getValue()));
            System.out.println(Integer.toString(rentAmountPDPaid));
            System.out.println(getStringNumber(amountPD.getText()));
            setEmpty();
        }
    }
    
    private Map getReceiptParameters(){
        HashMap map = new HashMap();
        if (comboboxPDCheck.equals("Block A")) {
            map.put("houseNumber", blockTreeView.getSelectionModel().getSelectedItem().getValue());
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
        tenantNameCol.setStyle("-fx-alignment: CENTER RIGHT");
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
        
        DoubleBinding usedWidth = amountCol.widthProperty().add(monthCol.widthProperty()).add(dateCol.widthProperty());
        methodCol.prefWidthProperty().bind(paymentsTable.widthProperty().subtract(usedWidth));
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
    
    public void customResize(TableView<?> tView) {
        AtomicLong width = new AtomicLong();
        tView.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = tView.getWidth();
        
        if (tableWidth > width.get()) {
            tView.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/tView.getColumns().size()));
            });
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        l1.setPrefSize(120, 20);
        l2.setPrefSize(120, 20);
        l3.setPrefSize(120, 20);
        l4.setPrefSize(120, 20);
        l5.setPrefSize(120, 20);
        l6.setPrefSize(120, 20);
        l7.setPrefSize(120, 20);
        l8.setPrefSize(120, 20);
        l9.setPrefSize(120, 20);
        l10.setPrefSize(120, 20);
        l11.setPrefSize(120, 20);
        l12.setPrefSize(120, 20);
        l13.setPrefSize(120, 20);
        l14.setPrefSize(120, 20);
        
        setupHouseNumberColumn();
        setupTenantNameColumn();
        setupAmountColumn();
        setupMonthColumn();
        setupPaymentDateColumn();
        setupPaymentMethodColumn();
        
        cashRadioButton.setToggleGroup(payOptionGroup);
        bankRadioButton.setToggleGroup(payOptionGroup);
        mpesaRadioButton.setToggleGroup(payOptionGroup);
        
        monthComboPD.getItems().addAll(PDModel.Strings.values());
        
        blockA.getChildren().addAll(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
        blockB.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12);
        blockC.getChildren().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12);
        nasraBlock.getChildren().addAll(d1, d2);
        
        rootBlock.setExpanded(true);
        rootBlock.getChildren().addAll(blockA, blockB, blockC, nasraBlock);
        
        blockTreeView.setRoot(rootBlock);
        blockTreeView.setShowRoot(false);
        
        blockTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                String searchTenantDetails = "SELECT * FROM TenantDetails WHERE HouseNumber = ?";
                Connection conn = DriverManager.getConnection(databaseURL);
                PreparedStatement pstmt = conn.prepareStatement(searchTenantDetails);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                ResultSet rs = pstmt.executeQuery();

                if (!rs.next()) {
                    setEmpty1();
                } else {
                    do {
                        tdName.setText(rs.getString("TenantName"));
                        tdPhone.setText(rs.getString("TenantPhoneNumber"));
                        tdAmount.setText(rs.getString("RentAmount"));
                        tdDeposit.setText(rs.getString("Deposit"));
                        tdDueDate.setText(rs.getString("DueDate"));
                        if (rs.getString("MoveInDate") != null) {
                            tdMoveInDate.setValue(LocalDate.parse(rs.getString("MoveInDate"), DateTimeFormatter.ISO_DATE));
                        } else {
                            tdMoveInDate.setValue(null);
                        }
                        if (rs.getString("MoveOutDate") != null) {
                            tdMoveOutDate.setValue(LocalDate.parse(rs.getString("MoveOutDate"), DateTimeFormatter.ISO_DATE));
                        } else {
                            tdMoveOutDate.setValue(null);
                        }
                        if (rs.getString("LeaseStartDate") != null) {
                            tdLeaseStartDate.setValue(LocalDate.parse(rs.getString("LeaseStartDate"), DateTimeFormatter.ISO_DATE));
                        } else {
                            tdLeaseStartDate.setValue(null);
                        }
                        if (rs.getString("LeaseEndDate") != null) {
                            tdLeaseEndDate.setValue(LocalDate.parse(rs.getString("LeaseEndDate"), DateTimeFormatter.ISO_DATE));
                        } else {
                            tdLeaseEndDate.setValue(null);
                        }
                    } while (rs.next());
                }
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            blockTreeView.getSelectionModel().getSelectedItem().getParent().setValue(blockTreeView.getSelectionModel().getSelectedItem().getValue());
            Platform.runLater(() -> {
                if (blockTreeView.getSelectionModel().getSelectedItem().getParent().equals(blockA)) {
                    blockA.setExpanded(false);
                } else if (blockTreeView.getSelectionModel().getSelectedItem().getParent().equals(blockB)) {
                    blockB.setExpanded(false);
                } else if (blockTreeView.getSelectionModel().getSelectedItem().getParent().equals(blockC)) {
                    blockC.setExpanded(false);
                } else if (blockTreeView.getSelectionModel().getSelectedItem().getParent().equals(nasraBlock)) {
                    nasraBlock.setExpanded(false);
                }
            });
        });
        
        
        
        blockA.expandedProperty().addListener((observable, oldValue, newValue) -> {
           if (blockA.isExpanded()){
               blockB.setExpanded(false);
               blockC.setExpanded(false);
               nasraBlock.setExpanded(false);
           }
        });
        blockB.expandedProperty().addListener((observable, oldValue, newValue) -> {
            if (blockB.isExpanded()){
                blockA.setExpanded(false);
                blockC.setExpanded(false);
                nasraBlock.setExpanded(false);
            }
        });
        blockC.expandedProperty().addListener((observable, oldValue, newValue) -> {
            if (blockC.isExpanded()){
                blockA.setExpanded(false);
                blockB.setExpanded(false);
                nasraBlock.setExpanded(false);
            }
        });
        nasraBlock.expandedProperty().addListener((observable, oldValue, newValue) -> {
            if (nasraBlock.isExpanded()){
                blockA.setExpanded(false);
                blockB.setExpanded(false);
                blockC.setExpanded(false);
            }
        });
        
        sp1.setOnMouseClicked((event) -> {
            blockA.setExpanded(false);
            blockB.setExpanded(false);
            blockC.setExpanded(false);
            nasraBlock.setExpanded(false);
        });
        sp1.setOnKeyPressed((event) -> {
           if (event.getCode() == KeyCode.ESCAPE) {
               blockA.setValue("Block A");
               blockB.setValue("Block B");
               blockC.setValue("Block C");
               nasraBlock.setValue("Nasra Block");
               setEmpty();
               event.consume();
           } 
        });
        
        
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
        
        detailsPane.setOnContextMenuRequested((event) -> {
            editMenu.getItems().addAll(edit, printReceipt);
            editMenu.show(sp1, event.getScreenX(), event.getScreenY());
        });
        detailsPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
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
        
        tdName.textProperty().addListener((observable, oldValue, newValue) -> {
            int length = newValue.length();
            tdName.setPrefWidth(length*10);
            if (newValue.isEmpty() || oldValue.isEmpty()) {
                tdName.setPrefWidth(180);
            }
        });
        
        pdTableViewButton.setOnAction((event) -> {
            paymentsTable.prefHeightProperty().bind(tableViewPane.heightProperty());
            
            paymentsTable.setMinHeight(100);
            tableViewPane.setCenter(paymentsTable);
            sp1.getItems().add(tableViewPane);
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            System.out.println(stage.getHeight());
            double newHeight = stage.getHeight()+tableViewPane.getHeight();
            MainApp.changeWindowSize(stage, 600);
        });
        
        pdTableViewHide.setOnAction((event) -> {
            sp1.getItems().remove(tableViewPane);
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            MainApp.changeWindowSize(stage, 500);
        });
        
        paymentsTable.getColumns().addAll(houseNoCol, tenantNameCol, amountCol, monthCol, dateCol, methodCol);
        
        radioVbox.getChildren().addAll(cashRadioButton, bankRadioButton, mpesaRadioButton);
        
        pdPaymentOption.setContent(radioVbox);
        pdPaymentOption.setExpanded(false);
        
        pdHbox5.setPadding(new Insets(10, 0, 0, 0));
        
        pdMonthCombo.setPrefWidth(160);
        
        placingVbox.setPadding(new Insets(22, 0, 0, 0));
        placingVbox.getChildren().add(pdPaymentOption);
        
        pdVbox.setPadding(new Insets(10, 10, 10, 10));
        pdVbox.getChildren().addAll(pdHbox1, pdHbox2, pdHbox3, pdHbox4, pdHbox5, pdHbox6);
        tdVbox.setPadding(new Insets(10, 10, 10, 10));
        tdVbox.getChildren().addAll(tdHbox1, tdHbox2, tdHbox3, tdHbox4, tdHbox5, tdHbox6, tdHbox7, tdHbox8, tdHbox9);
        
        tenantDataPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tenantDataPane.getTabs().addAll(tenantDetails, paymentDetails, repairDetails);
        
        detailsPane.setMinSize(450, 300);
        detailsPane.setCenter(tenantDataPane);
        blockTreeView.setMinWidth(120);
        selectionPane.setCenter(blockTreeView);
          
    }   
}
