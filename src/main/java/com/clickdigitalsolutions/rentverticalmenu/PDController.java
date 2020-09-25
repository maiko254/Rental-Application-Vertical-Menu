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
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    @FXML
    private SplitPane sp1;

    @FXML
    private SplitPane sp2;

    private BorderPane tableViewPane = new BorderPane();

    @FXML
    public BorderPane selectionPane;

    @FXML
    public BorderPane detailsPane;

    private VBox tdVbox = new VBox(10);

    private VBox pdVbox = new VBox(10);

    public BorderPane pdDetailsPane = new BorderPane(pdVbox);

    private VBox pdVboxEdit = new VBox(10);

    private VBox rdVbox = new VBox(10);

    private VBox placingVbox = new VBox();

    private VBox radioVbox = new VBox(10);

    public JFXButton updatePDAmount;

    private Button saveButtonPD;

    private VBox paymentVbox;

    private TableColumn<PDModel, String> houseNoCol = new TableColumn("House Number");
    private TableColumn<PDModel, String> tenantNameCol = new TableColumn("Tenant Name");
    private TableColumn<PDModel, String> amountCol = new TableColumn<>("Amount");
    private TableColumn<PDModel, PDModel.Strings> monthCol = new TableColumn<>("Month");
    private TableColumn<PDModel, String> dateCol = new TableColumn<>("Payment Date");
    private TableColumn<PDModel, String> methodCol = new TableColumn<>("Payment Method");
    public TableView<PDModel> paymentsTable = new TableView<>();

    public TableColumn<RModel, String> houseNo = new TableColumn("House Number");
    public TableColumn<RModel, String> tenantName = new TableColumn("Tenant Name");
    public TableColumn<RModel, RModel.Strings> repairMonthCol = new TableColumn<>("Month");
    public TableColumn<RModel, String> repairDone = new TableColumn("Repairs");
    public TableColumn<RModel, String> costOfRepair = new TableColumn<>("Repair Cost");
    public TableColumn<RModel, String> dateOfRepair = new TableColumn("Repair Date");
    public TableColumn<RModel, String> miscExpenses = new TableColumn<>("Miscellaneous Expenses");
    public TableView<RModel> repairsTable = new TableView<>();

    String newEntryCheck = "";

    ObservableList<PDModel> rentPaymentList1 = FXCollections.observableArrayList();

    MenuItem tdNewRecord = new MenuItem("New Entry");
    MenuItem tdUpdate = new MenuItem("Update");
    MenuItem tdDelete = new MenuItem("Delete");
    MenuItem tdSave = new MenuItem("Save");
    MenuItem tdSaveAs = new MenuItem("Save As...");

    MenuItem pdNewRecord = new MenuItem("New Entry");
    MenuItem pdUpdate = new MenuItem("Update");
    MenuItem pdDelete = new MenuItem("Delete");
    MenuItem pdSave = new MenuItem("Save");
    MenuItem pdStickyNote = new MenuItem("Sticky Note");
    MenuItem pdPrintReceipt = new MenuItem("Print Receipt");

    ContextMenu rdEditMenu = new ContextMenu();
    MenuItem rdEdit = new MenuItem("Edit");
    MenuItem rdNewEntry = new MenuItem("New Entry");
    MenuItem rdClear = new MenuItem("Clear Selection");

    Button rdUpdateButton = new Button("Update");
    Button rdDeleteButton = new Button("Delete");
    Button rdCancelbutton = new Button("Cancel");

    int tenantRowId = 0;
    int payRowId = 0;
    int repairRowId = 0;

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

    public ScrollPane tdScrollPane = new ScrollPane(tdVbox);
    public ScrollPane pdScrollPane = new ScrollPane(pdDetailsPane);
    public ScrollPane rdScrollPane = new ScrollPane(rdVbox);

    public JFXTabPane tenantDataPane = new JFXTabPane();
    public Tab tenantDetails = new Tab("Tenant Details", tdScrollPane);
    public Tab paymentDetails = new Tab("Payment Details", pdScrollPane);
    public Tab repairDetails = new Tab("Repairs Details", rdScrollPane);

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

    Label l20 = new Label("Month");
    Label l16 = new Label("Repairs Done");
    Label l17 = new Label("Repairs Costs");
    Label l18 = new Label("Date of\nRepairs");
    Label l19 = new Label("Miscellaneous\nCosts");

    public JFXTextField tdName = new JFXTextField();
    public JFXTextField tdPhone = new JFXTextField();
    public JFXTextField tdAmount = new JFXTextField();
    public JFXTextField tdDeposit = new JFXTextField();
    public JFXTextField tdDueDate = new JFXTextField();
    public JFXDatePicker tdMoveInDate = new JFXDatePicker();
    public JFXDatePicker tdMoveOutDate = new JFXDatePicker();
    public JFXDatePicker tdLeaseStartDate = new JFXDatePicker();
    public JFXDatePicker tdLeaseEndDate = new JFXDatePicker();
    public JFXSpinner databaseActivityIndicator = new JFXSpinner();

    public JFXTextField pdName = new JFXTextField();
    public JFXComboBox<PDModel.Strings> pdMonthCombo = new JFXComboBox<>();
    public JFXTextField pdAmount = new JFXTextField();
    public JFXDatePicker pdPaymentDate = new JFXDatePicker();
    public TitledPane pdPaymentOption = new TitledPane();
    public HBox pdContentPane = new HBox();
    public JFXButton pdTableViewButton = new JFXButton("Show details >>");
    public JFXRadioButton cashRadioButton = new JFXRadioButton("Cash");
    public JFXRadioButton bankRadioButton = new JFXRadioButton("Bank Deposit");
    public JFXRadioButton mpesaRadioButton = new JFXRadioButton("MPESA");
    public JFXRadioButton otherRadioButton = new JFXRadioButton("Alternative");
    public ToggleGroup payOptionGroup = new ToggleGroup();
    public Label payLabel = new Label();
    public Label rentArrearslabel = new Label();
    public JFXSpinner databaseActivityIndicatorPD = new JFXSpinner();
    public JFXTextField pdCashTextfield = new JFXTextField();
    public JFXTextField pdbankTextfield = new JFXTextField();
    public JFXTextField pdMpesaTextfield = new JFXTextField();
    public JFXTextArea pdOtherExpenseField = new JFXTextArea();
    public JFXButton pdCashButton = new JFXButton("Done");
    public JFXButton pdBankButton = new JFXButton("Done");
    public JFXButton pdMpesaButton = new JFXButton("Done");
    public JFXButton pdOtherButton = new JFXButton("Done");

    public JFXComboBox<RModel.Strings> rdMonthCombo = new JFXComboBox<>();
    public JFXTextArea rdRepairsDone = new JFXTextArea();
    public JFXTextField rdRepairCost = new JFXTextField();
    public JFXDatePicker rdRepairDate = new JFXDatePicker();
    public JFXTextField rdMiscCost = new JFXTextField();
    public JFXButton rdTableViewButton = new JFXButton("Show details >>");

    HBox tdHbox1 = new HBox(10, l1, tdName, databaseActivityIndicator);
    HBox tdHbox2 = new HBox(10, l2, tdPhone);
    HBox tdHbox3 = new HBox(10, l3, tdAmount);
    HBox tdHbox4 = new HBox(10, l4, tdDeposit);
    HBox tdHbox5 = new HBox(10, l5, tdDueDate);
    HBox tdHbox6 = new HBox(10, l6, tdMoveInDate);
    HBox tdHbox7 = new HBox(10, l7, tdMoveOutDate);
    HBox tdHbox8 = new HBox(10, l8, tdLeaseStartDate);
    HBox tdHbox9 = new HBox(10, l9, tdLeaseEndDate);

    HBox pdHbox1 = new HBox(10, l10, pdName, databaseActivityIndicatorPD);
    HBox pdHbox2 = new HBox(10, l11, pdMonthCombo);
    HBox pdHbox3 = new HBox(10, l12, pdAmount, rentArrearslabel);
    HBox pdHbox4 = new HBox(10, l13, pdPaymentDate);
    HBox pdHbox5 = new HBox(10, l14, placingVbox);
    HBox pdHbox6 = new HBox(pdTableViewButton);

    HBox rdHbox7 = new HBox(10, l20, rdMonthCombo);
    HBox rdHbox2 = new HBox(10, l16, rdRepairsDone);
    HBox rdHbox3 = new HBox(10, l17, rdRepairCost);
    HBox rdHbox4 = new HBox(10, l18, rdRepairDate);
    HBox rdHbox5 = new HBox(10, l19, rdMiscCost);
    HBox rdHbox6 = new HBox(rdTableViewButton);

    HBox rdUpdateHbox = new HBox(70, rdUpdateButton, rdDeleteButton, rdCancelbutton);

    HBox pdPayOptionHbox = new HBox(10);
    HBox pdBankHbox = new HBox(10);
    HBox pdMpesaHbox = new HBox(10);

    double xCursorPos = 0;
    double yCursorPos = 0;
    double rdXCursorPos = 0;
    double rdYCursorPos = 0;
    double tdXCursorPos = 0;
    double tdYCursorPos = 0;

    Stage stickyStage = null;

    Stage parentStickyStage = null;

    TextArea stickyTextArea = new TextArea();

    Button save = new Button("Save");

    Label label2 = new Label("X");

    ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    ObservableList<PDModel> payTenantDetails = FXCollections.observableArrayList();
    ObservableList<RModel> houseRepairDetails = FXCollections.observableArrayList();

    String databaseURL = "jdbc:sqlite:C:\\NetbeansProjects\\SQLite\\RVM.db";

    Icons icon = new Icons("¡");

    DetailsIcon detIcon = new DetailsIcon();
    PayMenuIcon payIcon = new PayMenuIcon();
    repairsMenuIcon repairsIcon = new repairsMenuIcon();

    final TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), icon);

    Timeline timeline = new Timeline();

    public Preferences prefs;
    
    public String excelFileLocation = "location";

    public String loc = "location";

    XSSFWorkbook workBook;

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt1 = null;
    ResultSet rs1 = null;

    boolean excelInsertFailed = false;

    public static final Logger logger = Logger.getLogger(PDController.class.getName());

    public void createTenantDetailsTable(Connection con, String houseNumber, String tenantName, String tenantPhoneNumber, String monthlyRent, String deposit, String dueDate, String moveInDate, String moveOutDate, String leaseStartDate, String leaseEndDate) throws FileNotFoundException {
        String createTDSql = "CREATE TABLE IF NOT EXISTS TenantDetails(RowID Integer PRIMARY KEY AUTOINCREMENT, HouseNumber text UNIQUE CHECK(HouseNumber<>''), TenantName text CHECK(TenantName<>''), TenantPhoneNumber text, RentAmount text, Deposit text , DueDate text, MoveInDate text, MoveOutDate text, LeaseStartDate text, LeaseEndDate text) ";
        try {
            pstmt = con.prepareStatement(createTDSql);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        prefs = Preferences.userRoot().node(this.getClass().getName()); //Preference to store file location for saving to excel file later
        File file = new File(prefs.get(loc, "location"));
        createExcelSheet(file, houseNumber, tenantName, tenantPhoneNumber, monthlyRent, deposit, dueDate, moveInDate, moveOutDate, leaseStartDate, leaseEndDate);

        if (excelInsertFailed == true) {
            System.out.println("Excel File Open.");
            return;
        }
        String insertTDSql = "INSERT INTO TenantDetails(HouseNumber, TenantName, TenantPhoneNumber, RentAmount, Deposit, DueDate, MoveInDate, MoveOutDate, LeaseStartDate, LeaseEndDate) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            pstmt = con.prepareStatement(insertTDSql);
            pstmt.setString(1, houseNumber);
            pstmt.setString(2, tenantName);
            pstmt.setString(3, tenantPhoneNumber);
            pstmt.setString(4, monthlyRent);
            pstmt.setString(5, deposit);
            pstmt.setString(6, dueDate);
            pstmt.setString(7, moveInDate);
            pstmt.setString(8, moveOutDate);
            pstmt.setString(9, leaseStartDate);
            pstmt.setString(10, leaseEndDate);
            pstmt.execute();
        } catch (SQLException e) {
            if (e.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (UNIQUE constraint failed: TenantDetails.HouseNumber)")) {
                Alert insertErrorAlert = new Alert(Alert.AlertType.ERROR);
                insertErrorAlert.setTitle("Duplicate Record");
                insertErrorAlert.setHeaderText(null);
                insertErrorAlert.setContentText("This apartment is occupied. Please select a different apartment");
                insertErrorAlert.showAndWait();
            } else if (e.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (CHECK constraint failed: TenantDetails)")) {
                Alert insertErrorAlert = new Alert(Alert.AlertType.ERROR);
                insertErrorAlert.setTitle("Empty Field");
                insertErrorAlert.setHeaderText(null);
                insertErrorAlert.setContentText("House Number and Tenant Name cannot be empty");
                insertErrorAlert.showAndWait();
            } else {
                e.printStackTrace();
            }
        } finally {
            try {
                con.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createPaymentDetailsTable(String HouseNumber, String TenantName, String Amount, PDModel.Strings Month, String PaymentDate, String PaymentMethod) {
        try {
            String createPDSql = "CREATE TABLE IF NOT EXISTS PaymentDetails(RowID Integer PRIMARY KEY AUTOINCREMENT, HouseNumber text, TenantName text CHECK(TenantName<>''), Amount text, Month text, PaymentDate text, PaymentMethod text, StickyNote text, UNIQUE(HouseNumber, Month, PaymentDate))";
            conn = DriverManager.getConnection(databaseURL);
            pstmt = conn.prepareStatement(createPDSql);
            pstmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            String insertPDSql = "INSERT INTO PaymentDetails(HouseNumber, TenantName, Amount, Month, PaymentDate, PaymentMethod) VALUES(?,?,?,?,?,?)";
            conn = DriverManager.getConnection(databaseURL);
            pstmt = conn.prepareStatement(insertPDSql);
            pstmt.setString(1, HouseNumber);
            pstmt.setString(2, TenantName);
            pstmt.setString(3, Amount);
            pstmt.setString(4, Month.name());
            pstmt.setString(5, PaymentDate);
            pstmt.setString(6, PaymentMethod);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void createRepairsTable(String HouseNumber, RModel.Strings Month, String Repairs, String CostOfRepairs, String DateOfRepairs, String Miscellaneous) {
        try {
            String createRepairsTableSql = "CREATE TABLE IF NOT EXISTS RepairsTable(RowID Integer PRIMARY KEY AUTOINCREMENT, HouseNumber text, Month text, Repairs text, CostOfRepairs text, DateOfRepairs text, MiscellaneousExpenses text)";
            conn = DriverManager.getConnection(databaseURL);
            pstmt = conn.prepareStatement(createRepairsTableSql);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            String insertRepairsTableSql = "INSERT INTO RepairsTable(HouseNumber, Month, Repairs, CostOfRepairs, DateOfRepairs, MiscellaneousExpenses) VALUES(?,?,?,?,?,?)";
            conn = DriverManager.getConnection(databaseURL);
            pstmt = conn.prepareStatement(insertRepairsTableSql);
            pstmt.setString(1, HouseNumber);
            pstmt.setString(2, Month.name());
            pstmt.setString(3, Repairs);
            pstmt.setString(4, CostOfRepairs);
            pstmt.setString(5, DateOfRepairs);
            pstmt.setString(6, Miscellaneous);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getDateValueAsString(LocalDate dateConvert) {
        String repairsDateString = null;
        if (dateConvert == null) {
            repairsDateString = null;
        } else if (dateConvert != null) {
            repairsDateString = dateConvert.format(DateTimeFormatter.ISO_DATE);
        }
        return repairsDateString;
    }

    private int getStringNumber(String stringExtract) {
        int numberExtract = 0;

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(stringExtract);
        while (m.find()) {
            numberExtract = Integer.parseInt(m.group());
        }

        return numberExtract;
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

    public void setAllEmpty() {
        tdName.setText("");
        tdPhone.setText("");
        tdAmount.setText("");
        tdDeposit.setText("");
        tdDueDate.setText("");
        tdMoveInDate.setValue(null);
        tdMoveOutDate.setValue(null);
        tdLeaseStartDate.setValue(null);
        tdLeaseEndDate.setValue(null);
        pdName.setText("");
        pdMonthCombo.setValue(PDModel.Strings.NONE);
        pdAmount.setText("");
        pdPaymentDate.setValue(null);
        payLabel.textProperty().unbind();
        payLabel.setText("");
        rdRepairsDone.setText("");
        rdRepairCost.setText("");
        rdRepairDate.setValue(null);
        rdMiscCost.setText("");
    }

    public void setEmpty() {
        pdName.clear();
        pdAmount.clear();
        pdMonthCombo.setValue(PDModel.Strings.NONE);
        pdPaymentDate.setValue(null);
        payLabel.textProperty().unbind();
        payLabel.setText("");
    }

    public void setTDEmpty1() {
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

    public void setRepairsEmpty() {
        rdRepairsDone.setText("");
        rdRepairCost.setText("");
        rdRepairDate.setValue(null);
        rdMiscCost.setText("");
    }

    public ObservableList<PDModel> getPaymentDetails() {
        ObservableList<PDModel> rentPaymentList = FXCollections.observableArrayList();
        if (pdMonthCombo.getSelectionModel().getSelectedItem().getMonth().equals("All")) {
            try {
                String searchPDTable = "SELECT * FROM PaymentDetails WHERE HouseNumber = ?";
                conn = DriverManager.getConnection(databaseURL);
                pstmt = conn.prepareStatement(searchPDTable);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String houseNo = rs.getString("HouseNumber");
                    String tenantName = rs.getString("TenantName");
                    String rentAmount = rs.getString("Amount");
                    PDModel.Strings month = PDModel.Strings.valueOf(rs.getString("Month"));

                    String paymentDate = rs.getString("PaymentDate");
                    String paymentMethod = rs.getString("PaymentMethod");
                    PDModel paymentData = new PDModel(houseNo, tenantName, rentAmount, month, paymentDate, paymentMethod);
                    rentPaymentList.add(paymentData);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                    pstmt.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else if (pdMonthCombo.getSelectionModel().getSelectedItem().getMonth().equals("")) {
            pdMonthCombo.getSelectionModel().getSelectedItem().getMonth();
        } else {
            try {
                String tableDataQuery = "SELECT * FROM PaymentDetails WHERE HouseNumber = ? AND Month = ?";
                conn = DriverManager.getConnection(databaseURL);
                pstmt = conn.prepareStatement(tableDataQuery);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                pstmt.setString(2, pdMonthCombo.getSelectionModel().getSelectedItem().name());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String houseNo = rs.getString("HouseNumber");
                    String tenantName = rs.getString("TenantName");
                    String rentAmount = rs.getString("Amount");
                    PDModel.Strings month = PDModel.Strings.valueOf(rs.getString("Month"));

                    String paymentDate = rs.getString("PaymentDate");
                    String paymentMethod = rs.getString("PaymentMethod");
                    PDModel paymentData = new PDModel(houseNo, tenantName, rentAmount, month, paymentDate, paymentMethod);
                    rentPaymentList.add(paymentData);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                    pstmt.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rentPaymentList;
    }

    public ObservableList<RModel> getRepairsDetails() {
        ObservableList<RModel> repairsData = FXCollections.observableArrayList();

        if (rdMonthCombo.getSelectionModel().getSelectedItem().getMonth().equals("All")) {
            try {
                String repairsTableQuery = "SELECT * FROM RepairsTable WHERE HouseNumber = ?";
                conn = DriverManager.getConnection(databaseURL);
                pstmt = conn.prepareStatement(repairsTableQuery);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String houseNo = rs.getString("HouseNumber");
                    RModel.Strings month = RModel.Strings.valueOf(rs.getString("Month"));
                    String repairsDone = rs.getString("Repairs");
                    String costOfRepair = rs.getString("CostOfRepairs");
                    String dateOfRepair = rs.getString("DateOfRepairs");
                    String miscellaneous = rs.getString("MiscellaneousExpenses");

                    RModel repairsInfo = new RModel(houseNo, month, repairsDone, costOfRepair, dateOfRepair, miscellaneous);
                    repairsData.add(repairsInfo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                    pstmt.close();
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (rdMonthCombo.getSelectionModel().getSelectedItem().getMonth().equals("")) {
            System.out.println("None");
        } else {
            try {
                String selectRepairsData = "SELECT * FROM RepairsTable WHERE HouseNumber = ? AND Month = ?";
                conn = DriverManager.getConnection(databaseURL);
                pstmt = conn.prepareStatement(selectRepairsData);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                pstmt.setString(2, rdMonthCombo.getSelectionModel().getSelectedItem().name());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    System.out.println("details put");
                    String houseNo = rs.getString("HouseNumber");
                    RModel.Strings month = RModel.Strings.valueOf(rs.getString("Month"));
                    String repairsDone = rs.getString("Repairs");
                    String costOfRepair = rs.getString("CostOfRepairs");
                    String dateOfRepair = rs.getString("DateOfRepairs");
                    String miscellaneous = rs.getString("MiscellaneousExpenses");

                    RModel repairsInfo = new RModel(houseNo, month, repairsDone, costOfRepair, dateOfRepair, miscellaneous);
                    repairsData.add(repairsInfo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                    pstmt.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return repairsData;
    }

    class MyEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {

            /*Getting value in selected cell
            TablePosition cellPos = paymentsTable.getSelectionModel().getSelectedCells().get(0);
            int row = cellPos.getRow();
            PDModel payitem = paymentsTable.getItems().get(row);
            TableColumn col = cellPos.getTableColumn();
            String payData = (String) col.getCellObservableValue(payitem).getValue();
            System.out.println(payData);*/
            if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2) {
                TableRow c = (TableRow) t.getSource();
                int index = c.getIndex();
                try {
                    PDModel item = getPaymentDetails().get(index);
                    pdName.setText(item.gettenantNameTablePD());
                    pdAmount.setText(item.getamountTablePD());
                    pdMonthCombo.setValue(item.getmonthTablePD());
                    pdPaymentDate.setValue(LocalDate.parse(item.getpaymentDateTablePD(), DateTimeFormatter.ISO_DATE));
                    payLabel.setText(item.getpaymentMethodPD());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Map getReceiptParameters() {
        HashMap map = new HashMap();
        /**
         * if (comboboxPDCheck.equals("Block A")) { map.put("houseNumber",
         * blockTreeView.getSelectionModel().getSelectedItem().getValue());
        }
         */
        map.put("PayMonth", monthComboPD.getSelectionModel().getSelectedItem());
        /*map.put("PayMethod", payMethodCheck);*/
        return map;
    }

    @SuppressWarnings("unchecked")
    private void editFocusedCell() {
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

    private TableColumn<PDModel, ?> getTableColumn(final TableColumn<PDModel, ?> column, int offset) {
        int columnIndex = paymentsTable.getVisibleLeafIndex(column);
        int newColumnIndex = columnIndex + offset;
        return paymentsTable.getVisibleLeafColumn(newColumnIndex);
    }

    private static <S, T> TableColumn<S, T> column(String title, Function<S, ObservableValue<T>> property) {
        TableColumn<S, T> col = new TableColumn<>(title);
        col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
        return col;
    }

    private void setupHouseNumberColumn() {
        houseNoCol.setPrefWidth(90);
        houseNoCol.setCellValueFactory(cellData -> cellData.getValue().houseNumberTablePDProperty());
    }

    private void setupTenantNameColumn() {
        tenantNameCol.setPrefWidth(120);
        tenantNameCol.setStyle("-fx-alignment: CENTER RIGHT");
        tenantNameCol.setCellValueFactory(cellData -> cellData.getValue().tenantNameTablePDProperty());
    }

    private void setupAmountColumn() {
        amountCol.setPrefWidth(90);
        amountCol.setCellValueFactory(cellData -> cellData.getValue().amountTablePDProperty());
    }

    private void updatePaymentTableData(String column, String newValue, String houseNumber, String paymentDate) {
        try {
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE PaymentDetails SET " + column + " = ? WHERE HouseNumber = ? AND PaymentDate = ?");
            pstmt.setString(1, newValue);
            pstmt.setString(2, houseNumber);
            pstmt.setString(3, paymentDate);
            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupMonthColumn() {
        monthCol.setPrefWidth(90);
        monthCol.setCellValueFactory(cellData -> cellData.getValue().monthTablePDProperty());
    }

    private void setupPaymentDateColumn() {
        dateCol.setPrefWidth(90);
        dateCol.setCellValueFactory(cellData -> cellData.getValue().paymentDateTablePDProperty());
    }

    private void setupPaymentMethodColumn() {
        DoubleBinding usedWidth = amountCol.widthProperty().add(monthCol.widthProperty()).add(dateCol.widthProperty());
        methodCol.prefWidthProperty().bind(paymentsTable.widthProperty().subtract(usedWidth));
        methodCol.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
        methodCol.setCellValueFactory(cellData -> cellData.getValue().paymentMethodPDProperty());
    }

    /*
    class MyRepairStringTableCell extends TableCell<RModel, String> {

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? null : getString());
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }

    Callback<TableColumn, TableCell> repairStringCellFactory
            = new Callback<TableColumn, TableCell>() {
        @Override
        public TableCell call(TableColumn param) {
            MyRepairStringTableCell cell = new MyRepairStringTableCell();
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            return cell;
        }
    };
     */

    class MyRepairEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {

            if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2) {
                TableCell cell = (TableCell) t.getSource();
                int index = cell.getIndex();
                try {
                    RModel item = getRepairsDetails().get(index);
                    rdRepairsDone.setText(item.getrepairsDoneTableR());
                    rdRepairCost.setText(item.getcostofRepairsTableR());
                    rdRepairDate.setValue(LocalDate.parse(item.getdateofRepairsTableR(), DateTimeFormatter.ISO_DATE));
                    rdMiscCost.setText(item.getmiscellaneousTableR());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void editRepairFocusedCell() {
        final TablePosition<RModel, String> focusedCell = repairsTable
                .focusModelProperty().get().focusedCellProperty().get();
        repairsTable.edit(focusedCell.getRow(), focusedCell.getTableColumn());
    }

    @SuppressWarnings("unchecked")
    private void selectPrevious() {
        if (repairsTable.getSelectionModel().isCellSelectionEnabled()) {
            TablePosition<RModel, ?> pos = repairsTable.getFocusModel().getFocusedCell();
            if (pos.getColumn() - 1 >= 0) {
                repairsTable.getSelectionModel().select(pos.getRow(), getRepairTableColumn(pos.getTableColumn(), -1));
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

    private TableColumn<RModel, ?> getRepairTableColumn(final TableColumn<RModel, ?> column, int offset) {
        int columnIndex = repairsTable.getVisibleLeafIndex(column);
        int newColumnIndex = columnIndex + offset;
        return repairsTable.getVisibleLeafColumn(newColumnIndex);
    }

    private void setupHouseNoColumn() {
        houseNo.setPrefWidth(90);
        houseNo.setCellValueFactory(cellData -> cellData.getValue().houseNumberTableRProperty());
    }

    private void setupRepairMonthColumn() {
        repairMonthCol.setPrefWidth(90);
        repairMonthCol.setCellValueFactory(cellData -> cellData.getValue().monthTableRProperty());
    }

    private void setupRepairsDoneColumn() {
        repairDone.setPrefWidth(90);
        repairDone.setCellValueFactory(cellData -> cellData.getValue().repairsDoneTableRProperty());
    }

    /*
    private void updateRepairsTableData(String column, String newValue, String houseNumber, String repairsDate) {
        try {
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE RepairsTable SET " + column + " = ? WHERE HouseNumber = ? AND DateOfRepairs = ?");
            pstmt.setString(1, newValue);
            pstmt.setString(2, houseNumber);
            pstmt.setString(3, repairsDate);
            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private void setupRepairCostColumn() {
        costOfRepair.setPrefWidth(90);
        costOfRepair.setCellValueFactory(cellData -> cellData.getValue().costofRepairsTableRProperty());
    }

    private void setupRepairsDateColumn() {
        dateOfRepair.setPrefWidth(90);
        dateOfRepair.setCellValueFactory(cellData -> cellData.getValue().dateofRepairsTableRProperty());
    }

    private void setupMiscellaneousColumn() {
        DoubleBinding usedWidth = houseNo.widthProperty().add(tenantName.widthProperty()).add(repairDone.widthProperty()).add(costOfRepair.widthProperty()).add(dateOfRepair.widthProperty());
        miscExpenses.prefWidthProperty().bind(repairsTable.widthProperty().subtract(usedWidth));
        miscExpenses.setCellValueFactory(cellData -> cellData.getValue().miscellaneousTableRProperty());
    }

    public void createExcelSheet(File fileLocation, String hNo, String tName, String phoneNo, String monthlyRent, String deposit, String dueDate, String moveInDate, String moveOutDate, String leaseStartDate, String leaseEndDate) throws FileNotFoundException {
       /* //Check for duplicates in table before inserting to excel file
        try {
            String searchTable = "SELECT * FROM TenantDetails WHERE RowID = ?";
            conn = DriverManager.getConnection(databaseURL);
            pstmt = conn.prepareStatement(searchTable);
            pstmt.setInt(1, tenantRowId);
            System.out.println(tenantRowId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Failed. Don't insert");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/

        File tenantDataExists = fileLocation;
        if (tenantDataExists.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(tenantDataExists);
                workBook = (XSSFWorkbook) WorkbookFactory.create(inputStream);

                Sheet sheet = workBook.getSheetAt(0);

                Object[][] tData = {{hNo, tName, phoneNo, monthlyRent, deposit, dueDate, moveInDate, moveOutDate, leaseStartDate, leaseEndDate}};

                int rowCount = sheet.getPhysicalNumberOfRows();

                for (Object[] tBook : tData) {
                    Row row = sheet.createRow(rowCount++);

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
                inputStream.close();

                FileOutputStream fileOut = null;

                try {
                    fileOut = new FileOutputStream(tenantDataExists);
                    workBook.write(fileOut);
                } catch (IOException e) {
                    excelInsertFailed = true;
                    if (e.getMessage().contains("The process cannot access the file because it is being used by another process")) {
                        System.out.println("Error occured.Open Excel File Should be closed first");
                        Alert excelInsertError = new Alert(AlertType.ERROR);
                        excelInsertError.setTitle("Error Saving to Excel file");
                        excelInsertError.setHeaderText(null);
                        excelInsertError.setContentText("Could not save data because Excel file is open. Please close it and try again.");
                        excelInsertError.showAndWait();
                    }

                } finally {
                    try {
                        fileOut.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException | EncryptedDocumentException e) {
                e.printStackTrace();
            }
        } else {
            workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("Tenant Data");

            XSSFFont boldFont = workBook.createFont();
            boldFont.setFontName("Arial");
            boldFont.setBold(true);
            XSSFCellStyle headerRowStyle = workBook.createCellStyle();
            headerRowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerRowStyle.setFont(boldFont);

            XSSFFont defaultFont = workBook.createFont();
            defaultFont.setFontHeightInPoints((short) 11);
            defaultFont.setFontName("Arial");
            defaultFont.setBold(false);

            CellStyle style = workBook.createCellStyle();
            style.setFont(defaultFont);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Map<String, Object[]> tenantData = new TreeMap<String, Object[]>();
            tenantData.put("1", new Object[]{"House Number", "Tenant Name", "Phone Number", "MonthlyRent", "House Deposit", "Rent Due Date", "Move-In Date", "Move-Out Date", "Lease-Start Date", "Lease-End Date"});
            tenantData.put("2", new Object[]{hNo, tName, phoneNo, monthlyRent, deposit, dueDate, moveInDate, moveOutDate, leaseStartDate, leaseEndDate});

            Set<String> keyset = tenantData.keySet();
            int rownum = 0;
            for (String key : keyset) {
                Row row = sheet.createRow(rownum++);
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
                sheet.autoSizeColumn(c); //autosize, merged cells should be ignored
                //sheet.autoSizeColumn(rownum, true); //autosize, merged cells should be considered
            }

            FileOutputStream fileOut = null;

            try {
                fileOut = new FileOutputStream(fileLocation);
                workBook.write(fileOut);
            } catch (IOException e) {
                excelInsertFailed = true;
                if (e.getMessage().contains("The process cannot access the file because it is being used by another process")) {
                    System.out.println("Error occured.Open Excel File Should be closed first");
                    Alert excelInsertError = new Alert(AlertType.ERROR);
                    excelInsertError.setTitle("Error Saving to Excel file");
                    excelInsertError.setHeaderText(null);
                    excelInsertError.setContentText("Could not save data because Excel file is open. Please close it and try again.");
                    excelInsertError.showAndWait();
                }
            } finally {
                try {
                    fileOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void createAndWriteExcelSheet(File fileLocation, String hNo, String tName, String amount, String monthlyRent, String paymentDate, String paymentMethod) throws FileNotFoundException {
        /*try {
            String searchPaymentsTable = "SELECT * FROM PaymentDetails WHERE RowID = ?";
            conn = DriverManager.getConnection(databaseURL);
            pstmt = conn.prepareStatement(searchPaymentsTable);
            pstmt.setInt(1, payRowId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/

        File tenantDataExists = fileLocation;

        if (tenantDataExists.exists() == false) {
        } else {
            try {
                FileInputStream inputStream = new FileInputStream(tenantDataExists);
                Workbook workbookExists = WorkbookFactory.create(inputStream);

                Sheet spreadSheet = workbookExists.getSheet("Payment Details");

                Font boldFont = workbookExists.createFont();
                boldFont.setFontName("Arial");
                boldFont.setBold(true);
                CellStyle headerRowStyle = workbookExists.createCellStyle();
                headerRowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerRowStyle.setFont(boldFont);

                Map<String, Object[]> tenantData = new TreeMap<>();
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
                col.setPrefWidth(col.getWidth() + ((tableWidth - width.get()) / tView.getColumns().size()));
            });
        }
    }

    public void showRentArrears(int monthlyRent, int paidRent) {
        if (Math.subtractExact(monthlyRent, paidRent) > 0) {
            rentArrearslabel.setText("In Arrears: Ksh " + Math.subtractExact(monthlyRent, paidRent));
            rentArrearslabel.setVisible(true);
        } else if (Math.subtractExact(monthlyRent, paidRent) == 0) {
            rentArrearslabel.setVisible(false);
        }
    }

    ChangeListener houseListener = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            fetchTenantDetailsFromDatabaseToUI(tdName, pdName,tdPhone, tdAmount, tdDeposit, tdDueDate, tdMoveInDate, tdMoveOutDate, tdLeaseStartDate, tdLeaseEndDate);
            /*try {
                String searchTenantDetails = "SELECT * FROM TenantDetails WHERE HouseNumber = ?";
                conn = DriverManager.getConnection(databaseURL);
                pstmt = conn.prepareStatement(searchTenantDetails);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                rs = pstmt.executeQuery();
                if (!rs.next()) {
                    tenantRowId = 0;
                    setTDEmpty1();
                    if (tdHbox1.getChildren().contains(detIcon)) {
                        tdHbox1.getChildren().remove(detIcon);
                    }
                } else {
                    do {
                        tenantRowId = rs.getInt("RowID");
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
                        if (tdHbox1.getChildren().contains(detIcon)) {
                            System.out.println("Icon already showing. Do nothing");
                        } else {
                            tdHbox1.getChildren().add(detIcon);
                        }
                    } while (rs.next());

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                    pstmt.close();
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                String searchPDSql = "SELECT * FROM PaymentDetails WHERE HouseNumber = ?";
                conn = DriverManager.getConnection(databaseURL);
                pstmt = conn.prepareStatement(searchPDSql);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                rs = pstmt.executeQuery();
                if (!rs.next()) {
                    pdName.setText("");
                    pdMonthCombo.setValue(PDModel.Strings.NONE);
                    pdTableViewButton.setVisible(false);
                    payLabel.textProperty().unbind();
                    payLabel.setText("");
                    payRowId = 0;
                    setEmpty();
                    if (pdHbox1.getChildren().contains(icon)) {
                        pdHbox1.getChildren().remove(icon);
                    }
                    if (pdHbox1.getChildren().contains(payIcon)) {
                        pdHbox1.getChildren().remove(payIcon);
                    }
                } else {
                    do {
                        payRowId = rs.getInt("RowID");
                        pdName.setText(rs.getString("TenantName"));
                        pdAmount.setText(rs.getString("Amount"));
                        pdMonthCombo.setValue(PDModel.Strings.valueOf(rs.getString("Month")));
                        if (rs.getString("PaymentDate") != null) {
                            pdPaymentDate.setValue(LocalDate.parse(rs.getString("PaymentDate"), DateTimeFormatter.ISO_DATE));
                        } else {
                            pdPaymentDate.setValue(null);
                        }
                        payLabel.setText(rs.getString("PaymentMethod"));
                        Object sticky = rs.getObject("StickyNote");
                        if (pdHbox1.getChildren().contains(payIcon)) {
                            System.out.println("PayIcon already showing. Do nothing");
                        } else {
                            pdHbox1.getChildren().add(payIcon);
                        }
                        if (sticky != null) {
                            if (!pdHbox1.getChildren().contains(icon)) {
                                stickyTextArea.setText(rs.getString("StickyNote"));
                                pdHbox1.getChildren().add(icon);
                            } else if (pdHbox1.getChildren().contains(icon)) {
                                stickyTextArea.setText(rs.getString("StickyNote"));
                            }
                        } else {
                            if (pdHbox1.getChildren().contains(icon)) {
                                pdHbox1.getChildren().remove(icon);
                            }
                        }

                    } while (rs.next());
                    pdTableViewButton.setVisible(true);
                    newEntryCheck = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                    pstmt.close();
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                String searchRepairsSql = "SELECT * FROM RepairsTable WHERE HouseNumber = ?";
                conn = DriverManager.getConnection(databaseURL);
                pstmt = conn.prepareStatement(searchRepairsSql);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                rs = pstmt.executeQuery();
                if (!rs.next()) {
                    setEmpty();
                    rdTableViewButton.setVisible(false);
                } else {
                    do {
                        repairRowId = rs.getInt("RowID");
                        rdMonthCombo.setValue(RModel.Strings.valueOf(rs.getString("Month")));
                        rdRepairsDone.setText(rs.getString("Repairs"));
                        rdRepairCost.setText(rs.getString("CostOfRepairs"));
                        if (rs.getString("DateOfRepairs") != null) {
                            rdRepairDate.setValue(LocalDate.parse(rs.getString("DateOfRepairs"), DateTimeFormatter.ISO_DATE));
                        } else {
                            rdRepairDate.setValue(null);
                        }
                        rdMiscCost.setText(rs.getString("MiscellaneousExpenses"));
                    } while (rs.next());
                    rdTableViewButton.setVisible(true);
                    newEntryCheck = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                    pstmt.close();
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            blockTreeView.getSelectionModel().getSelectedItem().getParent().setValue(blockTreeView.getSelectionModel().getSelectedItem().getValue());
            Platform.runLater(() -> {
                if (blockTreeView.getSelectionModel().getSelectedItem().getParent().equals(blockA)) {
                    System.out.println(blockTreeView.getSelectionModel().getSelectedItem().getValue());
                    blockA.setExpanded(false);
                    blockB.setValue("Block B");
                    blockC.setValue("Block C");
                    nasraBlock.setValue("Nasra Block");
                } else if (blockTreeView.getSelectionModel().getSelectedItem().getParent().equals(blockB)) {
                    blockB.setExpanded(false);
                    blockA.setValue("Block A");
                    blockC.setValue("Block C");
                    nasraBlock.setValue("Nasra Block");
                } else if (blockTreeView.getSelectionModel().getSelectedItem().getParent().equals(blockC)) {
                    blockC.setExpanded(false);
                    blockA.setValue("Block A");
                    blockB.setValue("Block B");
                    nasraBlock.setValue("Nasra Block");
                } else if (blockTreeView.getSelectionModel().getSelectedItem().getParent().equals(nasraBlock)) {
                    nasraBlock.setExpanded(false);
                    blockA.setValue("Block A");
                    blockB.setValue("Block B");
                    blockC.setValue("Block C");
                }
            });*/
        }
    };

    private void addJumpAnimation(Node node) {
        final double start = 0.0;
        final double end = start - 3.0;
        translateTransition.setFromY(start);
        translateTransition.setToY(end);
        translateTransition.setCycleCount(-1);
        translateTransition.setAutoReverse(true);
        translateTransition.setInterpolator(Interpolator.EASE_BOTH);
        translateTransition.play();
    }

    private void stopJumpAnimation() {
        translateTransition.stop();
    }

    EventHandler<MouseEvent> keyEventHandler
            = (MouseEvent keyEvent) -> {
                addJumpAnimation(icon);
            };

    public boolean checkIfNode(Node node) {
        while (node != null) {
            if (node.equals(pdMonthCombo) || node.equals(pdPaymentOption) || node.equals(tdMoveInDate) || node.equals(tdMoveOutDate) || node.equals(tdLeaseStartDate) || node.equals(tdLeaseEndDate) || node.equals(rdMonthCombo) || node.equals(rdRepairDate) || node.equals(detIcon)) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

    private void addBlinkAnimation() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(pdTableViewButton.opacityProperty(), 0.0);
        final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    EventHandler<MouseEvent> tableViewHandler
            = (MouseEvent keyEvent) -> {
                addBlinkAnimation();
            };

    private void startBlinkAnimation() {
        timeline.playFromStart();
    }

    /*private int removeEmptyRows(final Sheet sheet) {
        int i;
        for (i = 0; i <= sheet.getLastRowNum(); i++) {
            boolean isRowEmpty;
            if (sheet.getRow(i) == null) {
                sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                i--;
                continue;
            }
            final Row actualRow = sheet.getRow(i);
            isRowEmpty
                    = actualRow.getCell(1).toString().trim().equals("");
            if (isRowEmpty) {
                if (i == sheet.getLastRowNum()) {
                    sheet.removeRow(actualRow);
                } else {
                    sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                }
                i--;
            }
        }
        return i;
    }*/
    public void updateTDExcelRowValue(File file) throws FileNotFoundException, IOException {
        workBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workBook.getSheet("Tenant Data");

        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row.getCell(0).getStringCellValue().equals(blockTreeView.getSelectionModel().getSelectedItem().getValue())) {
                row.getCell(1).setCellValue(tdName.getText());
                row.getCell(2).setCellValue(tdPhone.getText());
                row.getCell(3).setCellValue(tdAmount.getText());
                row.getCell(4).setCellValue(tdDeposit.getText());
                row.getCell(5).setCellValue(tdDueDate.getText());
                row.getCell(6).setCellValue(getDateValueAsString(tdMoveInDate.getValue()));
                row.getCell(7).setCellValue(getDateValueAsString(tdMoveOutDate.getValue()));
                row.getCell(8).setCellValue(getDateValueAsString(tdLeaseStartDate.getValue()));
                row.getCell(9).setCellValue(getDateValueAsString(tdLeaseEndDate.getValue()));
            }
        }
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(file);
            workBook.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updatePDExcelRowValue(File file) throws FileNotFoundException, IOException {
        workBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workBook.getSheet("Payment Details");

        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row.getCell(0).getStringCellValue().equals(blockTreeView.getSelectionModel().getSelectedItem().getValue()) && row.getCell(3).getStringCellValue().equals(pdMonthCombo.getValue().name())) {
                row.getCell(2).setCellValue(pdAmount.getText());
                row.getCell(4).setCellValue(getDateValueAsString(pdPaymentDate.getValue()));
                row.getCell(5).setCellValue(payLabel.getText());
            }
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(file);
            workBook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removeExcelRowValue(File file) throws IOException, InvalidFormatException {
        workBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workBook.getSheet("Tenant Data");
        /**
         * int rowCount = 0; rowCount = sheet.getLastRowNum(); if (rowCount ==
         * 0) { rowCount = sheet.getPhysicalNumberOfRows();
        }
         */
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row.getRowNum() == sheet.getLastRowNum() && row.getCell(0).getStringCellValue().equals(blockTreeView.getSelectionModel().getSelectedItem().getValue()) && row.getCell(1).getStringCellValue().equals(tdName.getText())) {
                sheet.removeRow(row);
                continue;
            }
            if (row.getCell(0).getStringCellValue().equals(blockTreeView.getSelectionModel().getSelectedItem().getValue()) && row.getCell(1).getStringCellValue().equals(tdName.getText())) {
                sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
            }
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(file);
            workBook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void removePDExcelRowFromTD(File file) throws FileNotFoundException, IOException {
        workBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workBook.getSheet("Payment Details");

        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row.getRowNum() == sheet.getLastRowNum() && row.getCell(0).getStringCellValue().equals(blockTreeView.getSelectionModel().getSelectedItem().getValue())) {
                sheet.removeRow(row);
                continue;
            }
            if (row.getCell(0).getStringCellValue().equals(blockTreeView.getSelectionModel().getSelectedItem().getValue())) {
                sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
            }
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(file);
            workBook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }   
    
    public void removePDExcelRow(File file) throws FileNotFoundException, IOException {
        workBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workBook.getSheet("Payment Details");

        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row.getRowNum() == sheet.getLastRowNum() && row.getCell(0).getStringCellValue().equals(blockTreeView.getSelectionModel().getSelectedItem().getValue()) && row.getCell(3).getStringCellValue().equals(pdMonthCombo.getValue().name())) {
                sheet.removeRow(row);
                continue;
            }
            if (row.getCell(0).getStringCellValue().equals(blockTreeView.getSelectionModel().getSelectedItem().getValue()) && row.getCell(3).getStringCellValue().equals(pdMonthCombo.getValue().name())) {
                sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
            }
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(file);
            workBook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*private void runTask() {
        JFXSpinner loadSpinner = new JFXSpinner();
        loadSpinner.getStylesheets().add("/styles/spinnerCss.css");
        loadSpinner.setRadius(10.0);
        tdHbox1.getChildren().add(3, loadSpinner);
        
        Task insertTask = new Task() {
            @Override
            protected Object call() throws Exception {
                int maxIterations = 1;
                for (int i = 1; i <= maxIterations; i++) {
                    if (isCancelled()) {
                        break;
                    }
                    updateProgress(i, maxIterations);
                    
                    Thread.sleep(1000);
                }
                return null;
            }
        };
        
        insertTask.setOnSucceeded((event) -> {
            tdHbox1.getChildren().remove(loadSpinner);
        });
        
        insertTask.setOnFailed((event) -> {
            System.out.println("Faled insert");
        });
        
        loadSpinner.progressProperty().bind(insertTask.progressProperty());
        
        new Thread(insertTask).start();
    }*/
    private void resetHouseSeletion() {
        if (blockTreeView.getSelectionModel().getSelectedItem().getParent().equals(blockA)) {
                blockA.setExpanded(false);
                blockB.setValue("Block B");
                blockC.setValue("Block C");
                nasraBlock.setValue("Nasra Block");
            } else if (blockTreeView.getSelectionModel().getSelectedItem().getParent().equals(blockB)) {
                blockB.setExpanded(false);
                blockA.setValue("Block A");
                blockC.setValue("Block C");
                nasraBlock.setValue("Nasra Block");
            } else if (blockTreeView.getSelectionModel().getSelectedItem().getParent().equals(blockC)) {
                blockC.setExpanded(false);
                blockA.setValue("Block A");
                blockB.setValue("Block B");
                nasraBlock.setValue("Nasra Block");
            } else if (blockTreeView.getSelectionModel().getSelectedItem().getParent().equals(nasraBlock)) {
                nasraBlock.setExpanded(false);
                blockA.setValue("Block A");
                blockB.setValue("Block B");
                blockC.setValue("Block C");
            }
    }
    
    private void fetchTenantDetailsFromDatabaseToUI(JFXTextField tdName, JFXTextField pdName, JFXTextField tdPhone, JFXTextField monthlyRent, JFXTextField tdDeposit, JFXTextField dueDate, JFXDatePicker moveInDate, JFXDatePicker moveOutDate, JFXDatePicker leaseStartDate, JFXDatePicker leaseEndDate) {
        final FetchTenantDetailsTask fetchTdTask = new FetchTenantDetailsTask();
        
        prefs = Preferences.userRoot().node(this.getClass().getName());
        
        fetchTdTask.setOnSucceeded((event) -> {
            if (fetchTdTask.getValue().isEmpty()) {
                excelFileLocation = prefs.get(loc, "location"); 
                pdName.clear();
                pdMonthCombo.setValue(PDModel.Strings.NONE);
                setTDEmpty1();
                blockTreeView.getSelectionModel().getSelectedItem().getParent().setValue(blockTreeView.getSelectionModel().getSelectedItem().getValue());
                resetHouseSeletion();
                return;
            }

            tdName.setText(fetchTdTask.getValue().get(0));
            pdName.setText(fetchTdTask.getValue().get(0));
            tdPhone.setText(fetchTdTask.getValue().get(1));
            monthlyRent.setText(fetchTdTask.getValue().get(2));
            tdDeposit.setText(fetchTdTask.getValue().get(3));
            dueDate.setText(fetchTdTask.getValue().get(4));

            if (fetchTdTask.getValue().get(5) != null) {
                moveInDate.setValue(LocalDate.parse(fetchTdTask.getValue().get(5), DateTimeFormatter.ISO_DATE));
            } else {
                moveInDate.setValue(null);
            }

            if (fetchTdTask.getValue().get(6) != null) {
                moveOutDate.setValue(LocalDate.parse(fetchTdTask.getValue().get(6), DateTimeFormatter.ISO_DATE));
            } else {
                moveOutDate.setValue(null);
            }

            if (fetchTdTask.getValue().get(7) != null) {
                leaseStartDate.setValue(LocalDate.parse(fetchTdTask.getValue().get(7), DateTimeFormatter.ISO_DATE));
            } else {
                leaseStartDate.setValue(null);
            }

            if (fetchTdTask.getValue().get(8) != null) {
                leaseEndDate.setValue(LocalDate.parse(fetchTdTask.getValue().get(8), DateTimeFormatter.ISO_DATE));
            } else {
                leaseEndDate.setValue(null);
            }
            
            pdMonthCombo.setValue(PDModel.Strings.NONE);
            
            if (tdHbox1.getChildren().contains(detIcon)) {
                System.out.println("detIcon already showing. Do nothing");
            } else {
                tdHbox1.getChildren().add(2, detIcon);
            }
            
            if (pdHbox1.getChildren().contains(payIcon)) {
                System.out.println("payIcon already showing");
            } else {
                pdHbox1.getChildren().add(2, payIcon);
            }
            
            blockTreeView.getSelectionModel().getSelectedItem().getParent().setValue(blockTreeView.getSelectionModel().getSelectedItem().getValue());
            resetHouseSeletion();

        });

        MainApp.databaseExecutor.submit(fetchTdTask);
    }
    
    private void fetchPaymentDetailsFromDBToUI(JFXTextField amount, JFXDatePicker paymentDate, Label paymentMethod) {
        final FetchPaymentDetailsTask fetchPaymentDetails = new FetchPaymentDetailsTask();
        
        prefs = Preferences.userRoot().node(this.getClass().getName());
        
        fetchPaymentDetails.setOnSucceeded((event) -> {
            if (fetchPaymentDetails.getValue().isEmpty()) {
                excelFileLocation = prefs.get(loc, "location");
                amount.clear();
                paymentDate.setValue(null);
                paymentMethod.textProperty().unbind();
                paymentMethod.setText("");
                if (pdHbox1.getChildren().contains(icon)) {
                    pdHbox1.getChildren().remove(icon);
                }
                pdTableViewButton.setVisible(false);
                return;
            }
            
            amount.setText(fetchPaymentDetails.getValue().get(2));
            
            if (fetchPaymentDetails.getValue().get(4) != null) {
                paymentDate.setValue(LocalDate.parse(fetchPaymentDetails.getValue().get(4), DateTimeFormatter.ISO_DATE));
            } else {
                paymentDate.setValue(null);
            }
            
            paymentMethod.textProperty().unbind();
            paymentMethod.setText(fetchPaymentDetails.getValue().get(5));
            
            if (fetchPaymentDetails.getValue().get(6) != null) {
                if (!pdHbox1.getChildren().contains(icon)) {
                    icon.stickyMenuItem.setOnAction((eventx) -> {
                        try {
                            String deleteSticky = "UPDATE PaymentDetails SET StickyNote = null WHERE RowID = ?";
                            Connection con = DriverManager.getConnection(databaseURL);
                            PreparedStatement stmt = con.prepareStatement(deleteSticky);
                            stmt.setInt(1, payRowId);
                            stmt.executeUpdate();
                            stmt.close();
                            con.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        pdHbox1.getChildren().remove(icon);
                    });
                    stickyTextArea.setText(fetchPaymentDetails.getValue().get(6));
                    pdHbox1.getChildren().add(3, icon);
                } else if (pdHbox1.getChildren().contains(icon)) {
                    stickyTextArea.setText(fetchPaymentDetails.getValue().get(6));
                }
            } else {
                if (pdHbox1.getChildren().contains(icon)) {
                    pdHbox1.getChildren().remove(icon);
                }
            }

            
            pdTableViewButton.setVisible(true);
        });
        
        MainApp.databaseExecutor.submit(fetchPaymentDetails);
    }
    
    private void saveToTenantDetailsTable(final JFXSpinner databaseActivityIndicator) {
        final SaveTenantDetailsTask saveTenantDetails = new SaveTenantDetailsTask();
        
        databaseActivityIndicator.visibleProperty().bind(saveTenantDetails.runningProperty());
        databaseActivityIndicator.progressProperty().bind(saveTenantDetails.progressProperty());
        
        saveTenantDetails.setOnFailed((event) -> {
            System.err.println("The task failed with the following exception:");
            saveTenantDetails.getException().printStackTrace(System.err);
        });
        
        saveTenantDetails.setOnSucceeded((event) -> {
            setTDEmpty1();
        });
        
        MainApp.databaseExecutor.submit(saveTenantDetails);
    }
    
    private void saveToPaymentDetailsTable(final JFXSpinner databaseActivityIndicator) {
        final savePaymentDetailsTask savePaymentDetails = new savePaymentDetailsTask();
        
        databaseActivityIndicator.visibleProperty().bind(savePaymentDetails.runningProperty());
        databaseActivityIndicator.progressProperty().bind(savePaymentDetails.progressProperty());
        
        savePaymentDetails.setOnSucceeded((event) -> {
            pdMonthCombo.setValue(PDModel.Strings.NONE);
            pdAmount.clear();
            pdPaymentDate.setValue(null);
            payLabel.textProperty().unbind();
            payLabel.setText("");
        });
        
        MainApp.databaseExecutor.submit(savePaymentDetails);
    }
    
    private void updateTenantDetails(final JFXSpinner databaseActivityIndicator) {
        final UpdateTenantDetailsTask updateTenantDetails = new UpdateTenantDetailsTask();

        databaseActivityIndicator.visibleProperty().bind(updateTenantDetails.runningProperty());
        databaseActivityIndicator.progressProperty().bind(updateTenantDetails.progressProperty());

        updateTenantDetails.setOnSucceeded((event) -> {
            setTDEmpty1();
        });
        
        MainApp.databaseExecutor.submit(updateTenantDetails);
    }
    
    private void updatePaymentDetails(final JFXSpinner databaseActivityIndicator) {
        final UpdatePaymentDetailsTask updatePaymentDetails = new UpdatePaymentDetailsTask();

        databaseActivityIndicator.visibleProperty().bind(updatePaymentDetails.runningProperty());
        databaseActivityIndicator.progressProperty().bind(updatePaymentDetails.progressProperty());

        updatePaymentDetails.setOnSucceeded((event) -> {
            pdMonthCombo.setValue(PDModel.Strings.NONE);
            pdAmount.clear();
            pdPaymentDate.setValue(null);
            payLabel.textProperty().unbind();
            payLabel.setText("");
        });
        
        MainApp.databaseExecutor.submit(updatePaymentDetails);
    }
    
    private void deleteFromTenantDetails(final JFXSpinner databaseActivityIndicator) {
        final DeleteFromTenantDetailsTask deleteFromTenantDetails = new DeleteFromTenantDetailsTask();

        databaseActivityIndicator.visibleProperty().bind(deleteFromTenantDetails.runningProperty());
        databaseActivityIndicator.progressProperty().bind(deleteFromTenantDetails.progressProperty());

        deleteFromTenantDetails.setOnSucceeded((event) -> {
            setTDEmpty1();
        });

        MainApp.databaseExecutor.submit(deleteFromTenantDetails);
    }
    
    private void deleteFromPaymentDetails(final JFXSpinner databaseActivityIndicator) {
        final DeleteFromPaymentDetailsTask deleteFromPaymentDetails = new DeleteFromPaymentDetailsTask();
        
        databaseActivityIndicator.visibleProperty().bind(deleteFromPaymentDetails.runningProperty());
        databaseActivityIndicator.progressProperty().bind(deleteFromPaymentDetails.progressProperty());
        
        deleteFromPaymentDetails.setOnSucceeded((event) -> {
            pdMonthCombo.setValue(PDModel.Strings.NONE);
            pdAmount.clear();
            pdPaymentDate.setValue(null);
            payLabel.textProperty().unbind();
            payLabel.setText("");
        });
        
        MainApp.databaseExecutor.submit(deleteFromPaymentDetails);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        databaseActivityIndicator.setVisible(false);
        databaseActivityIndicator.getStylesheets().add("/styles/spinnerCss.css");
        databaseActivityIndicator.setRadius(8.5);
        
        databaseActivityIndicatorPD.setVisible(false);
        databaseActivityIndicatorPD.getStylesheets().add("/styles/spinnerCssPD.css");
        databaseActivityIndicatorPD.setRadius(8.5);
        
        l1.setMinSize(120, 20);
        l2.setMinSize(120, 20);
        l3.setMinSize(120, 20);
        l4.setMinSize(120, 20);
        l5.setMinSize(120, 20);
        l6.setMinSize(120, 20);
        l7.setMinSize(120, 20);
        l8.setMinSize(120, 20);
        l9.setMinSize(120, 20);
        l10.setMinSize(120, 20);
        l11.setMinSize(120, 20);
        l12.setMinSize(120, 20);
        l13.setMinSize(120, 20);
        l14.setMinSize(120, 20);
        l16.setMinSize(120, 20);
        l17.setMinSize(120, 20);
        l18.setMinSize(120, 20);
        l19.setMinSize(120, 20);
        l20.setMinSize(120, 20);

        setupHouseNumberColumn();
        setupTenantNameColumn();
        setupAmountColumn();
        setupMonthColumn();
        setupPaymentDateColumn();
        setupPaymentMethodColumn();

        setupHouseNoColumn();
        setupRepairMonthColumn();
        setupRepairsDoneColumn();
        setupRepairCostColumn();
        setupRepairsDateColumn();
        setupMiscellaneousColumn();

        tdScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        tdScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        pdScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pdScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        rdScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rdScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        rentArrearslabel.setVisible(false);

        pdMonthCombo.getItems().addAll(PDModel.Strings.values());
        rdMonthCombo.getItems().addAll(RModel.Strings.values());

        blockA.getChildren().addAll(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
        blockB.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12);
        blockC.getChildren().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12);
        nasraBlock.getChildren().addAll(d1, d2);

        rootBlock.setExpanded(true);
        rootBlock.getChildren().addAll(blockA, blockB, blockC, nasraBlock);

        blockTreeView.setRoot(rootBlock);
        blockTreeView.setShowRoot(false);
        
        radioVbox.setMaxWidth(200);
        radioVbox.getChildren().addAll(cashRadioButton, bankRadioButton, mpesaRadioButton, otherRadioButton);
        
        pdContentPane.setAlignment(Pos.CENTER_LEFT);
        pdContentPane.setPadding(new Insets(0, 0, 0, 3));
        pdContentPane.minWidthProperty().bind(pdPaymentOption.widthProperty());
        /*HBox region = new HBox();
        region.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(region, Priority.ALWAYS);*/
        pdContentPane.getChildren().addAll(payLabel);
        
        pdPaymentOption.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
            if (wasExpanded) {
                System.out.println(pdPaymentOption.getText());
                cashRadioButton.setSelected(false);
                bankRadioButton.setSelected(false);
                mpesaRadioButton.setSelected(false);
                otherRadioButton.setSelected(false);
            } else if (isNowExpanded) {

            }

        });
        pdPaymentOption.setAlignment(Pos.CENTER);
        pdPaymentOption.setGraphic(pdContentPane);
        pdPaymentOption.setContent(radioVbox);
        pdPaymentOption.setExpanded(false);
        pdPaymentOption.setTextOverrun(OverrunStyle.WORD_ELLIPSIS);
        
        cashRadioButton.setUserData("Cash");
        bankRadioButton.setUserData("Bank");
        mpesaRadioButton.setUserData("Mpesa");

        cashRadioButton.setToggleGroup(payOptionGroup);
        bankRadioButton.setToggleGroup(payOptionGroup);
        mpesaRadioButton.setToggleGroup(payOptionGroup);
        
        cashRadioButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            radioVbox.getChildren().clear();
            pdPayOptionHbox.getChildren().clear();
            pdCashTextfield.setPromptText("Name");
            pdPayOptionHbox.getChildren().addAll(pdCashTextfield, pdCashButton);
            radioVbox.getChildren().add(pdPayOptionHbox);
            payLabel.textProperty().bind(Bindings.concat("Cash: ").concat(pdCashTextfield.textProperty()));
        });

        bankRadioButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            System.out.println("Bank button selected");
            radioVbox.getChildren().clear();
            pdPayOptionHbox.getChildren().clear();
            pdPayOptionHbox.getChildren().addAll(pdbankTextfield, pdBankButton);
            radioVbox.getChildren().add(pdPayOptionHbox);
            payLabel.textProperty().bind(Bindings.concat("Bank Deposit: ").concat(pdbankTextfield.textProperty()));
        });

        mpesaRadioButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            System.out.println("Mpesa button selected");
            radioVbox.getChildren().clear();
            pdPayOptionHbox.getChildren().clear();
            pdMpesaTextfield.setPromptText("Code");
            pdPayOptionHbox.getChildren().addAll(pdMpesaTextfield, pdMpesaButton);
            radioVbox.getChildren().add(pdPayOptionHbox);
            payLabel.textProperty().bind(Bindings.concat("Mpesa: ").concat(pdMpesaTextfield.textProperty()));
        });

        otherRadioButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            radioVbox.getChildren().clear();
            pdPayOptionHbox.getChildren().clear();
            pdPayOptionHbox.getChildren().addAll(pdOtherExpenseField, pdOtherButton);
            radioVbox.getChildren().add(pdPayOptionHbox);
            payLabel.textProperty().bind(Bindings.concat("Other: ").concat(pdOtherExpenseField.textProperty()));
        });
        
        pdCashButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        pdCashButton.setGraphic(new ImageView("/images/icons8_checkmark_16px.png"));
        pdCashButton.setOnAction((event) -> {
            radioVbox.getChildren().clear();
            radioVbox.getChildren().addAll(cashRadioButton, bankRadioButton, mpesaRadioButton, otherRadioButton);
            pdPaymentOption.setExpanded(false);
        });
        
        pdBankButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        pdBankButton.setGraphic(new ImageView("/images/icons8_checkmark_16px.png"));
        pdBankButton.setOnAction((event) -> {
            radioVbox.getChildren().clear();
            radioVbox.getChildren().addAll(cashRadioButton, bankRadioButton, mpesaRadioButton, otherRadioButton);
            pdPaymentOption.setExpanded(false);
        });

        pdMpesaButton.setOnAction((event) -> {
            radioVbox.getChildren().clear();
            radioVbox.getChildren().addAll(cashRadioButton, bankRadioButton, mpesaRadioButton, otherRadioButton);
            pdPaymentOption.setExpanded(false);
        });

        pdOtherButton.setOnAction((event) -> {
            radioVbox.getChildren().clear();
            radioVbox.getChildren().addAll(cashRadioButton, bankRadioButton, mpesaRadioButton, otherRadioButton);
            pdPaymentOption.setExpanded(false);
        });
        
        pdCashTextfield.setPrefWidth(100);
        /*pdCashTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            int length = newValue.length();
            if (length > 6) {
                pdCashTextfield.setPrefWidth(length * 8);
            } else if (newValue.isEmpty() || oldValue.isEmpty()) {
                pdCashTextfield.setPrefWidth(50);
            }
        });*/

        pdbankTextfield.setPrefWidth(50);
        pdbankTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            int length = newValue.length();
            if (length > 6) {
                pdbankTextfield.setPrefWidth(length * 8);
            } else if (newValue.isEmpty() || oldValue.isEmpty()) {
                pdbankTextfield.setPrefWidth(50);
            }
        });

        pdMpesaTextfield.setPrefWidth(50);
        pdMpesaTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            int length = newValue.length();
            if (length > 6) {
                pdMpesaTextfield.setPrefWidth(length * 8);
            } else if (newValue.isEmpty() || oldValue.isEmpty()) {
                pdMpesaTextfield.setPrefWidth(50);
            }
        });
        
        blockTreeView.getSelectionModel().selectedItemProperty().addListener(houseListener);

        blockA.expandedProperty().addListener((observable, oldValue, newValue) -> {
            if (blockA.isExpanded()) {
                blockB.setExpanded(false);
                blockC.setExpanded(false);
                nasraBlock.setExpanded(false);
            }
        });
        blockB.expandedProperty().addListener((observable, oldValue, newValue) -> {
            if (blockB.isExpanded()) {
                blockA.setExpanded(false);
                blockC.setExpanded(false);
                nasraBlock.setExpanded(false);
            }
        });
        blockC.expandedProperty().addListener((observable, oldValue, newValue) -> {
            if (blockC.isExpanded()) {
                blockA.setExpanded(false);
                blockB.setExpanded(false);
                nasraBlock.setExpanded(false);
            }
        });
        nasraBlock.expandedProperty().addListener((observable, oldValue, newValue) -> {
            if (nasraBlock.isExpanded()) {
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

        tdUpdate.setOnAction((event) -> {
            updateTenantDetails(databaseActivityIndicator);
            /*try {
                Connection conn = DriverManager.getConnection(databaseURL);

                PreparedStatement updateTD = null;
                PreparedStatement updatePDName = null;

                String updateTDString = "UPDATE TenantDetails SET TenantName = ?, TenantPhoneNumber = ?, RentAmount = ?, Deposit = ?, DueDate = ?, MoveInDate = ?, MoveOutDate = ?, LeaseStartDate = ?, LeaseEndDate = ? WHERE RowID = ?";
                String updatePDNameString = "UPDATE PaymentDetails SET TenantName = ? WHERE HouseNumber = ?";

                try {
                    conn.setAutoCommit(false);
                    updateTD = conn.prepareStatement(updateTDString);
                    updatePDName = conn.prepareStatement(updatePDNameString);

                    updateTD.setString(1, tdName.getText());
                    updateTD.setString(2, tdPhone.getText());
                    updateTD.setString(3, tdAmount.getText());
                    updateTD.setString(4, tdDeposit.getText());
                    updateTD.setString(5, tdDueDate.getText());
                    updateTD.setString(6, getDateValueAsString(tdMoveInDate.getValue()));
                    updateTD.setString(7, getDateValueAsString(tdMoveOutDate.getValue()));
                    updateTD.setString(8, getDateValueAsString(tdLeaseStartDate.getValue()));
                    updateTD.setString(9, getDateValueAsString(tdLeaseEndDate.getValue()));
                    updateTD.setInt(10, tenantRowId);
                    updateTD.executeUpdate();
                    updatePDName.setString(1, tdName.getText());
                    updatePDName.setString(2, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                    updatePDName.executeUpdate();
                    conn.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (conn != null) {
                        try {
                            System.err.println("Transaction is being rolled back");
                            conn.rollback();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } finally {
                    if (updateTD != null) {
                        updateTD.close();
                    }
                    if (updatePDName != null) {
                        updatePDName.close();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
            }

            prefs = Preferences.userRoot().node(this.getClass().getName());
            File file = new File(prefs.get(loc, "location"));

            try {
                updateTDExcelRowValue(file);
            } catch (IOException ex) {
                Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
            }
            setAllEmpty();*/
        });

        pdUpdate.setOnAction((event) -> {
            updatePaymentDetails(databaseActivityIndicatorPD);
            /*if (pdMonthCombo.getSelectionModel().getSelectedItem().equals(PDModel.Strings.NONE)) {
                Alert emptyFieldAlert = new Alert(AlertType.INFORMATION);
                emptyFieldAlert.setTitle("No month selected");
                emptyFieldAlert.setHeaderText(null);
                emptyFieldAlert.setContentText("Please select a month.");
                emptyFieldAlert.showAndWait();
            } else {
                try {
                    String updateAmountSql = "UPDATE PaymentDetails SET Amount = ?, PaymentDate = ?, PaymentMethod = ?  WHERE RowID = ?";
                    conn = DriverManager.getConnection(databaseURL);
                    pstmt = conn.prepareStatement(updateAmountSql);
                    pstmt.setString(1, pdAmount.getText());
                    pstmt.setString(2, getDateValueAsString(pdPaymentDate.getValue()));
                    pstmt.setString(3, payLabel.getText());
                    pstmt.setInt(4, payRowId);
                    pstmt.executeUpdate();
                    if (pstmt.executeUpdate() == 1) {
                        Alert confirmPDUpdate = new Alert(AlertType.INFORMATION);
                        confirmPDUpdate.setTitle("Update Confirmation");
                        confirmPDUpdate.setHeaderText(null);
                        confirmPDUpdate.setContentText("Record " + blockTreeView.getSelectionModel().getSelectedItem().getValue() + " for " + pdMonthCombo.getValue().name() + " successfully updated");
                        confirmPDUpdate.showAndWait();
                    } else if (pstmt.executeUpdate() == 0) {
                        Alert noUpdate = new Alert(AlertType.ERROR);
                        noUpdate.setTitle("Update Error");
                        noUpdate.setHeaderText(null);
                        noUpdate.setContentText("Record " + blockTreeView.getSelectionModel().getSelectedItem().getValue() + " for " + pdMonthCombo.getValue().name() + " not updated");
                        noUpdate.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        conn.close();
                        pstmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            prefs = Preferences.userRoot().node(this.getClass().getName());
            File file = new File(prefs.get(loc, "location"));

            try {
                updatePDExcelRowValue(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            setEmpty();
            payLabel.textProperty().unbind();
            payLabel.setText("");
            pdTableViewButton.setDisable(false);*/
        });

        pdDelete.setOnAction((event) -> {
            deleteFromPaymentDetails(databaseActivityIndicatorPD);
            /*prefs = Preferences.userRoot().node(this.getClass().getName());
            File file = new File(prefs.get(loc, "location"));

            try {
                removeTDExcelRow(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                String deletePaySql = "DELETE FROM PaymentDetails WHERE RowID = ?";
                conn = DriverManager.getConnection(databaseURL);
                pstmt = conn.prepareStatement(deletePaySql);
                if (pdMonthCombo.getValue().equals(PDModel.Strings.NONE)) {
                    Alert emptyFieldAlert = new Alert(Alert.AlertType.ERROR);
                    emptyFieldAlert.setTitle("No month selected");
                    emptyFieldAlert.setHeaderText(null);
                    emptyFieldAlert.setContentText("Please select a month");
                    emptyFieldAlert.showAndWait();
                } else {
                    pstmt.setInt(1, payRowId);
                    pstmt.executeUpdate();
                }
                setEmpty();
                payLabel.textProperty().unbind();
                payLabel.setText("");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            pdTableViewButton.setDisable(false);*/
        });

        tdDelete.setOnAction((event) -> {
            deleteFromTenantDetails(databaseActivityIndicator);
            /*prefs = Preferences.userRoot().node(this.getClass().getName());
            File file = new File(prefs.get(loc, "location"));
            try {
                removeExcelRowValue(file);
            } catch (IOException | InvalidFormatException ex) {
                Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Connection conn = DriverManager.getConnection(databaseURL);

                PreparedStatement deleteTD = null;
                PreparedStatement deletePD = null;

                String deleteTDString = "DELETE FROM TenantDetails WHERE RowID = ?";
                String deletePDString = "DELETE FROM PaymentDetails WHERE HouseNumber = ?";

                try {
                    conn.setAutoCommit(false);
                    deleteTD = conn.prepareStatement(deleteTDString);
                    deletePD = conn.prepareStatement(deletePDString);

                    deleteTD.setInt(1, tenantRowId);
                    deleteTD.executeUpdate();
                    deletePD.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                    deletePD.executeUpdate();
                    conn.commit();
                    setTDEmpty1();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (conn != null) {
                        try {
                            System.err.println("Transaction is being rolled back");
                            conn.rollback();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } finally {
                    if (conn != null) {
                        conn.close();
                    }
                    if (deleteTD != null) {
                        deleteTD.close();
                    }
                    if (deletePD != null) {
                        deletePD.close();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }*/
        });

        tdSaveAs.setOnAction((event) -> {
            prefs = Preferences.userRoot().node(this.getClass().getName());
            if (blockTreeView.getSelectionModel().getSelectedItem().equals("Block A") || blockTreeView.getSelectionModel().getSelectedItem().equals("Block B") || blockTreeView.getSelectionModel().getSelectedItem().equals("Block C") || blockTreeView.getSelectionModel().getSelectedItem().equals("Nasra Block")) {
                Alert emptyFieldAlert = new Alert(Alert.AlertType.ERROR);
                emptyFieldAlert.setTitle("No Apartment Selected");
                emptyFieldAlert.setHeaderText(null);
                emptyFieldAlert.setContentText("Please select a house.");
                emptyFieldAlert.showAndWait();
            } else {
                File initialFile = new File(prefs.get(loc, "location"));
                if (prefs.get(loc, "location").equals("location") || initialFile.exists() == false) {
                    FileChooser initialLoc = new FileChooser();
                    File initFile = initialLoc.showSaveDialog(tdScrollPane.getScene().getWindow());
                    prefs.put(loc, initFile.getPath());
                    saveToTenantDetailsTable(databaseActivityIndicator);
                } else if (!prefs.get(loc, "location").equals("location")) {
                    FileChooser savedLoc = new FileChooser();
                    savedLoc.setInitialDirectory(initialFile.getParentFile());
                    File savedFile = savedLoc.showSaveDialog(tdScrollPane.getScene().getWindow());
                    prefs.put(loc, savedFile.getPath());
                    saveToTenantDetailsTable(databaseActivityIndicator);
                }
            }
        });
        
        tdSave.setOnAction((event) -> {
            prefs = Preferences.userRoot().node(this.getClass().getName());
            if (blockTreeView.getSelectionModel().getSelectedItem() == null || blockTreeView.getSelectionModel().getSelectedItem().equals("Block A") || blockTreeView.getSelectionModel().getSelectedItem().equals("Block B") || blockTreeView.getSelectionModel().getSelectedItem().equals("Block C") || blockTreeView.getSelectionModel().getSelectedItem().equals("Nasra Block")) {
                Alert emptyFieldAlert = new Alert(Alert.AlertType.ERROR);
                emptyFieldAlert.setTitle("No Apartment Selected");
                emptyFieldAlert.setHeaderText(null);
                emptyFieldAlert.setContentText("Please select a house.");
                emptyFieldAlert.showAndWait();
            } else {
                try {
                    File initialFile = new File(prefs.get(loc, "location"));
                    
                    if (prefs.get(loc, "location").equals("location") || initialFile.exists() == false) {
                        FileChooser initialLoc = new FileChooser();
                        File initFile = initialLoc.showSaveDialog(tdScrollPane.getScene().getWindow());
                        prefs.put(loc, initFile.getPath());
                        System.out.println(prefs.get(loc, "location"));
                        excelFileLocation = initFile.getPath();
                        saveToTenantDetailsTable(databaseActivityIndicator);
                    } else if (!prefs.get(loc, "location").equals("location")) {
                        saveToTenantDetailsTable(databaseActivityIndicator);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pdSave.setOnAction((event) -> {
            prefs = Preferences.userRoot().node(this.getClass().getName());
            if (pdMonthCombo.getSelectionModel().getSelectedItem().equals(PDModel.Strings.NONE)) {
                Alert emptyFieldAlert = new Alert(AlertType.INFORMATION);
                emptyFieldAlert.setTitle("No month selected");
                emptyFieldAlert.setHeaderText(null);
                emptyFieldAlert.setContentText("Please select a month");
                emptyFieldAlert.showAndWait();
            } else if ("".equals(pdName.getText()) || "".equals(pdAmount.getText()) || "".equals(pdPaymentDate.getEditor().getText()) || payLabel.getText().isEmpty() || payLabel.getText().equals("Received by: ") || payLabel.getText().equals("Deposit slip: ") || payLabel.getText().equals("Transaction code: ") || payLabel.getText().equals("Alternative: ")) {
                Alert emptyFieldAlert = new Alert(AlertType.INFORMATION);
                emptyFieldAlert.setTitle("Empty Field!");
                emptyFieldAlert.setHeaderText(null);
                emptyFieldAlert.setContentText("Empty field is not allowed");
                emptyFieldAlert.showAndWait();
            } else {
                saveToPaymentDetailsTable(databaseActivityIndicatorPD);
            }
        });

        rdUpdateButton.setOnAction((event) -> {
            if ("".equals(rdRepairsDone.getText()) || "".equals(rdRepairCost.getText()) || "".equals(rdRepairDate.getEditor().getText())) {
                Alert emptyFieldAlert = new Alert(AlertType.INFORMATION);
                emptyFieldAlert.setTitle("Empty Field!");
                emptyFieldAlert.setHeaderText(null);
                emptyFieldAlert.setContentText("Empty field is not allowed");
                emptyFieldAlert.showAndWait();
            } else {
                try {
                    String updateRepairsTable = "UPDATE RepairsTable SET Repairs = ?, CostOfRepairs = ?, DateOfRepairs =?, MiscellaneousExpenses = ? WHERE RowID = ?";
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(updateRepairsTable);
                    pstmt.setString(1, rdRepairsDone.getText());
                    pstmt.setString(2, rdRepairCost.getText());
                    pstmt.setString(3, getDateValueAsString(rdRepairDate.getValue()));
                    pstmt.setString(4, rdMiscCost.getText());
                    pstmt.setInt(5, repairRowId);
                    pstmt.executeUpdate();
                    if (pstmt.executeUpdate() == 1) {
                        Alert confirmPDUpdate = new Alert(AlertType.INFORMATION);
                        confirmPDUpdate.setTitle("Update Confirmation");
                        confirmPDUpdate.setHeaderText(null);
                        confirmPDUpdate.setContentText("Record " + blockTreeView.getSelectionModel().getSelectedItem().getValue() + " for " + rdMonthCombo.getValue().name() + " successfully updated");
                        confirmPDUpdate.showAndWait();
                    } else if (pstmt.executeUpdate() == 0) {
                        Alert noUpdate = new Alert(AlertType.ERROR);
                        noUpdate.setTitle("Update Error");
                        noUpdate.setHeaderText(null);
                        noUpdate.setContentText("Record " + blockTreeView.getSelectionModel().getSelectedItem().getValue() + " for " + rdMonthCombo.getValue().name() + " not updated");
                        noUpdate.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            rdVbox.getChildren().remove(rdUpdateHbox);
            rdTableViewButton.setDisable(false);
        });

        rdCancelbutton.setOnAction((event) -> {
            rdVbox.getChildren().remove(rdUpdateHbox);
            rdVbox.setDisable(false);
        });

        tdNewRecord.setOnAction((event) -> {
            if (blockTreeView.getSelectionModel().getSelectedItem().getValue().startsWith("A")) {
                blockTreeView.getSelectionModel().getSelectedItem().setValue("Block A");
            } else if (blockTreeView.getSelectionModel().getSelectedItem().getValue().startsWith("B")) {
                blockTreeView.getSelectionModel().getSelectedItem().setValue("Block B");
            } else if (blockTreeView.getSelectionModel().getSelectedItem().getValue().startsWith("C")) {
                blockTreeView.getSelectionModel().getSelectedItem().setValue("Block C");
            } else if (blockTreeView.getSelectionModel().getSelectedItem().getValue().startsWith("D")) {
                blockTreeView.getSelectionModel().getSelectedItem().setValue("Nasra Block");
            }
            setAllEmpty();
            tenantRowId = 0;
            payRowId = 0;
            repairRowId = 0;
        });

        pdNewRecord.setOnAction((event) -> {
            pdMonthCombo.setValue(PDModel.Strings.NONE);
            pdAmount.clear();
            pdPaymentDate.setValue(null);
            payLabel.textProperty().unbind();
            payLabel.setText("");
            newEntryCheck = "Payments Tab New Entry";
        });

        rdNewEntry.setOnAction((event) -> {
            rdMonthCombo.setValue(RModel.Strings.NONE);
            rdRepairsDone.clear();
            rdRepairDate.setValue(null);
            rdRepairCost.clear();
            rdMiscCost.clear();
            rentArrearslabel.setVisible(false);
            newEntryCheck = "Repairs Tab New Entry";
        });

        rdEdit.setOnAction((event) -> {
            if (repairRowId == 0) {
                Alert emptyHouseAlert = new Alert(AlertType.INFORMATION);
                emptyHouseAlert.setTitle("No house selected");
                emptyHouseAlert.setHeaderText(null);
                emptyHouseAlert.setContentText("Please select a house");
                emptyHouseAlert.showAndWait();
            } else {
                if (sp1.getItems().size() == 2) {
                    sp1.getItems().remove(tableViewPane);
                    rdTableViewButton.setText("Show details");
                    rdUpdateHbox.prefWidthProperty().bind(detailsPane.widthProperty());
                    rdUpdateHbox.setPadding(new Insets(20, 0, 0, 20));
                    rdVbox.getChildren().add(6, rdUpdateHbox);
                } else {
                    if (rdVbox.getChildren().contains(rdUpdateHbox)) {
                        System.out.println("Update hbox visible. Do nothing");
                    } else {
                        rdUpdateHbox.prefWidthProperty().bind(detailsPane.widthProperty());
                        rdUpdateHbox.setPadding(new Insets(20, 0, 0, 20));
                        rdVbox.getChildren().add(6, rdUpdateHbox);
                    }
                }
            }
            rdTableViewButton.setDisable(true);
        });

        pdStickyNote.setOnAction((event) -> {
            if (payRowId == 0) {
                Alert emptyHouseAlert = new Alert(AlertType.INFORMATION);
                emptyHouseAlert.setTitle("No Entry");
                emptyHouseAlert.setHeaderText("Empty Record");
                emptyHouseAlert.setContentText("First make an entry then try again");
                emptyHouseAlert.showAndWait();
            } else if (pdMonthCombo.getSelectionModel().getSelectedItem().equals(PDModel.Strings.NONE)) {
                Alert emptyHouseAlert = new Alert(AlertType.INFORMATION);
                emptyHouseAlert.setTitle("No Month Selected");
                emptyHouseAlert.setHeaderText(null);
                emptyHouseAlert.setContentText("Please select a month.");
                emptyHouseAlert.showAndWait();
            } else {
                MyPopUp popUp = new MyPopUp();

                stickyStage = (Stage) popUp.getScene().getWindow();
                parentStickyStage = (Stage) detIcon.getScene().getWindow();

                double centerXPosition = parentStickyStage.getX() + parentStickyStage.getWidth() / 2d;;
                double centerYPsition = parentStickyStage.getY() + parentStickyStage.getHeight() / 2d;

                stickyStage.setOnShowing((e) -> stickyStage.hide());

                stickyStage.setOnShown((e) -> {
                    stickyStage.setX(centerXPosition - stickyStage.getWidth() / 2);
                    stickyStage.setY(centerYPsition - stickyStage.getHeight() / 2);
                    stickyStage.show();
                });

                stickyTextArea.clear();
                popUp.showAndWait();
            }
        });

        save.setOnAction((event) -> {
            try {
                Connection conn = DriverManager.getConnection(databaseURL);
                String saveSticky = "UPDATE PaymentDetails SET StickyNote = ? WHERE RowID = ?";
                PreparedStatement pstmt = conn.prepareStatement(saveSticky);
                pstmt.setString(1, stickyTextArea.getText());
                pstmt.setInt(2, payRowId);
                pstmt.executeUpdate();

                if (pdHbox1.getChildren().contains(icon)) {
                    (label2.getScene()).getWindow().hide();
                } else if (!pdHbox1.getChildren().contains(icon)) {
                    pdHbox1.getChildren().add(3, icon);
                    (label2.getScene()).getWindow().hide();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        icon.setOnMouseClicked((event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                try {
                    parentStickyStage = (Stage) icon.getScene().getWindow();
                    MyPopUp popUp = new MyPopUp();
                    stickyStage = (Stage) popUp.getScene().getWindow();
                    double centerXPosition = parentStickyStage.getX() + parentStickyStage.getWidth() / 2d;;
                    double centerYPsition = parentStickyStage.getY() + parentStickyStage.getHeight() / 2d;

                    stickyStage.setOnShowing((e) -> stickyStage.hide());

                    stickyStage.setOnShown((e) -> {
                        stickyStage.setX(centerXPosition - stickyStage.getWidth() / 2);
                        stickyStage.setY(centerYPsition - stickyStage.getHeight() / 2);
                        stickyStage.show();
                    });
                    
                    popUp.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        icon.addEventHandler(MouseEvent.MOUSE_ENTERED, keyEventHandler);
        icon.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            stopJumpAnimation();
        });

        /*printReceipt.setOnAction((event) -> {
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
        });*/
        
        tdName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                if (tdHbox1.getChildren().contains(detIcon)) {
                    return;
                } else {
                    tdHbox1.getChildren().add(2, detIcon);
                }
            } else {
                if (tdHbox1.getChildren().contains(detIcon)) {
                    tdHbox1.getChildren().remove(detIcon);
                }
            }
        });

        detIcon.visibleProperty().bind(tdName.textProperty().isEmpty().not());
        payIcon.visibleProperty().bind(pdName.textProperty().isEmpty().not());

        rdRepairDate.getEditor().setOnMouseClicked((event) -> {
            rdEditMenu.hide();
        });

        Platform.runLater(() -> {
            rdScrollPane.getScene().addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, (event) -> {
                if (checkIfNode((Node) event.getTarget())) {
                    event.consume();
                    rdEditMenu.hide();
                }
            });
        });

        pdScrollPane.setFitToHeight(true);
        pdScrollPane.setFitToWidth(true);

        pdScrollPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            if (stickyStage == null || !stickyStage.isShowing()) {
                System.out.println("Not showing");
            } else if (stickyStage.isShowing()) {
                stickyStage.close();
            }
        });

        rdScrollPane.setOnContextMenuRequested((event) -> {
            rdEditMenu.getItems().clear();
            rdEditMenu.setAutoHide(true);
            rdEditMenu.getItems().addAll(rdEdit, rdNewEntry, rdClear);
            rdXCursorPos = event.getScreenX();
            rdYCursorPos = event.getScreenY();
            rdEditMenu.show(rdScrollPane, rdXCursorPos, rdYCursorPos);
        });
        rdScrollPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            rdEditMenu.hide();
        });

        pdMonthCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            fetchPaymentDetailsFromDBToUI(pdAmount, pdPaymentDate, payLabel);
            /*if (newEntryCheck.equals("Payments Tab New Entry")) {
                System.out.println("Do nothing. New selection");
            } else if (newValue.getMonth().equals("All")) {
                try {
                    String searchPDTable = "SELECT * FROM PaymentDetails WHERE HouseNumber = ?";
                    conn = DriverManager.getConnection(databaseURL);
                    pstmt = conn.prepareStatement(searchPDTable);
                    pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                    rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        setEmpty();
                        payLabel.textProperty().unbind();
                        payLabel.setText("");
                        if (timeline.getStatus().equals(Animation.Status.RUNNING)) {
                            timeline.stop();
                        }
                    } else {
                        do {
                            pdAmount.clear();
                            pdPaymentDate.setValue(null);
                            payLabel.textProperty().unbind();
                            payLabel.setText("");
                        } while (rs.next());
                        pstmt.close();
                        conn.close();
                        addBlinkAnimation();
                        if (timeline.getStatus().equals(Animation.Status.STOPPED)) {
                            timeline.playFromStart();
                        }
                        rentArrearslabel.setVisible(false);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        conn.close();
                        pstmt.close();
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else if (newValue.getMonth().equals("")) {
                setEmpty();
                rentArrearslabel.setVisible(false);
                payLabel.textProperty().unbind();
                payLabel.setText("");
            } else {
                try {
                    String searchPDTable = "SELECT * FROM PaymentDetails WHERE HouseNumber = ? AND Month = ?";
                    conn = DriverManager.getConnection(databaseURL);
                    pstmt = conn.prepareStatement(searchPDTable);
                    String searchTDTable = "SELECT RentAmount FROM TenantDetails WHERE HouseNumber = ?";
                    pstmt1 = conn.prepareStatement(searchTDTable);
                    pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                    pstmt1.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                    pstmt.setString(2, pdMonthCombo.getSelectionModel().getSelectedItem().name());
                    rs = pstmt.executeQuery();
                    rs1 = pstmt1.executeQuery();
                    if (!rs.next()) {
                        setEmpty();
                        payLabel.textProperty().unbind();
                        payLabel.setText("");
                        rentArrearslabel.setVisible(false);
                        payRowId = 0;
                        if (pdHbox1.getChildren().contains(icon)) {
                            pdHbox1.getChildren().remove(icon);
                        }
                        if (timeline.getStatus().equals(Animation.Status.RUNNING)) {
                            timeline.stop();
                        }
                    } else {
                        do {
                            payRowId = rs.getInt("RowID");
                            pdAmount.setText(rs.getString("Amount"));
                            Object paymentDate = rs.getObject("PaymentDate");
                            if (paymentDate == null) {
                                pdPaymentDate.setValue(null);
                            } else {
                                pdPaymentDate.setValue(LocalDate.parse(rs.getString("PaymentDate"), DateTimeFormatter.ISO_DATE));
                            }
                            payLabel.setText(rs.getString("PaymentMethod"));
                            showRentArrears(getStringNumber(rs1.getString("RentAmount")), getStringNumber(rs.getString("Amount")));
                            Object sticky = rs.getObject("StickyNote");
                            if (sticky == null) {
                                if (pdHbox1.getChildren().contains(icon)) {
                                    pdHbox1.getChildren().remove(icon);
                                }
                            } else {
                                if (!pdHbox1.getChildren().contains(icon)) {
                                    stickyTextArea.setText(rs.getString("StickyNote"));
                                    pdHbox1.getChildren().add(icon);
                                } else if (pdHbox1.getChildren().contains(icon)) {
                                    stickyTextArea.setText(rs.getString("StickyNote"));
                                }
                            }

                        } while (rs.next());
                        if (pdHbox1.getChildren().contains(icon)) {
                            icon.stickyMenuItem.setOnAction((event) -> {
                                try {
                                    String deleteSticky = "UPDATE PaymentDetails SET StickyNote = null WHERE RowID = ?";
                                    Connection con = DriverManager.getConnection(databaseURL);
                                    PreparedStatement stmt = con.prepareStatement(deleteSticky);
                                    stmt.setInt(1, payRowId);
                                    stmt.executeUpdate();
                                    stmt.close();
                                    con.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                pdHbox1.getChildren().remove(icon);
                            });
                        }
                        if (payRowId != 0) {
                            timeline.stop();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        conn.close();
                        pstmt.close();
                        pstmt1.close();
                        rs.close();
                        rs1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        */});

        pdMonthCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            payTenantDetails = getPaymentDetails();
            FilteredList<PDModel> filteredList = new FilteredList<>(payTenantDetails);
            filteredList.setPredicate((t) -> {
                if (pdMonthCombo.getValue() == null || pdMonthCombo.getSelectionModel().isEmpty()) {
                    return true;
                }
                PDModel.Strings comboFilter = newValue;
                if (t.getmonthTablePD().equals(newValue) || newValue.getMonth().equals("All")) {
                    return true;
                }
                return false;
            });
            SortedList<PDModel> sortedData = new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(paymentsTable.comparatorProperty());
            paymentsTable.setItems(sortedData);
        });

        rdMonthCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newEntryCheck.equals("Repairs Tab New Entry")) {
                System.out.println("Do nothing. New entry");
            } else if (newValue.getMonth().equals("All")) {
                try {
                    String selectRepairs = "SELECT * FROM RepairsTable WHERE HouseNumber = ?";
                    conn = DriverManager.getConnection(databaseURL);
                    pstmt = conn.prepareStatement(selectRepairs);
                    pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                    rs = pstmt.executeQuery();

                    if (!rs.next()) {
                        setRepairsEmpty();
                    } else {
                        do {
                            rdRepairsDone.clear();
                            rdRepairCost.clear();
                            rdRepairDate.setValue(null);
                            rdMiscCost.clear();
                        } while (rs.next());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        conn.close();
                        pstmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else if (newValue.getMonth().equals("")) {

            } else {

                try {
                    String selectRepairs = "SELECT * FROM RepairsTable WHERE HouseNumber = ? AND Month = ?";
                    conn = DriverManager.getConnection(databaseURL);
                    pstmt = conn.prepareStatement(selectRepairs);
                    pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                    pstmt.setString(2, rdMonthCombo.getSelectionModel().getSelectedItem().name());
                    rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        setRepairsEmpty();
                    } else {
                        do {
                            repairRowId = rs.getInt("RowID");
                            rdRepairsDone.setText(rs.getString("Repairs"));
                            rdRepairCost.setText(rs.getString("CostOfRepairs"));
                            Object repairsDate = rs.getObject("DateOfRepairs");
                            if (repairsDate == null) {
                                rdRepairDate.setValue(null);
                            } else {
                                rdRepairDate.setValue(LocalDate.parse(rs.getString("DateOfRepairs"), DateTimeFormatter.ISO_DATE));
                            }
                            rdMiscCost.setText(rs.getString("MiscellaneousExpenses"));
                        } while (rs.next());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        conn.close();
                        pstmt.close();
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        rdMonthCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            houseRepairDetails = getRepairsDetails();
            FilteredList<RModel> filteredList = new FilteredList<>(houseRepairDetails);
            filteredList.setPredicate((t) -> {
                if (rdMonthCombo.getValue() == null || pdMonthCombo.getSelectionModel().isEmpty()) {
                    return true;
                }
                RModel.Strings comboFilter = newValue;
                if (t.getMonthTableR().equals(newValue) || newValue.getMonth().equals("All")) {
                    return true;
                }
                return false;
            });
            SortedList<RModel> sortedData = new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(repairsTable.comparatorProperty());
            repairsTable.setItems(houseRepairDetails);
        });

        paymentsTable.setRowFactory((TableView<PDModel> tableView) -> {
            final TableRow<PDModel> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem editPay = new MenuItem("Edit");
            final MenuItem deletePay = new MenuItem("Remove");

            row.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    TableRow c = (TableRow) event.getSource();
                    int index = c.getIndex();
                    try {
                        PDModel item = getPaymentDetails().get(index);
                        pdMonthCombo.setValue(item.getmonthTablePD());
                        pdAmount.setText(item.getamountTablePD());
                        if (item.getpaymentDateTablePD() == null) {
                            pdPaymentDate.setValue(null);
                        } else {
                            pdPaymentDate.setValue(LocalDate.parse(item.getpaymentDateTablePD(), DateTimeFormatter.ISO_DATE));
                        }
                        payLabel.textProperty().unbind();
                        payLabel.setText(item.getpaymentMethodPD());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            contextMenu.getItems().addAll(editPay, deletePay);
            // Set context menu on row, but use a binding to make it only show for non-empty rows:
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );
            return row;
        });

        repairsTable.setRowFactory((TableView<RModel> tableView) -> {
            final TableRow<RModel> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem editPay = new MenuItem("Edit");
            final MenuItem deletePay = new MenuItem("Remove");

            row.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    TableRow c = (TableRow) event.getSource();
                    int index = c.getIndex();
                    try {
                        RModel item = getRepairsDetails().get(index);
                        rdMonthCombo.setValue(item.getMonthTableR());
                        rdRepairsDone.setText(item.getrepairsDoneTableR());
                        rdRepairCost.setText(item.getcostofRepairsTableR());
                        if (item.getdateofRepairsTableR() == null) {
                            rdRepairDate.setValue(null);
                        } else {
                            rdRepairDate.setValue(LocalDate.parse(item.getdateofRepairsTableR(), DateTimeFormatter.ISO_DATE));
                        }
                        rdMiscCost.setText(item.getmiscellaneousTableR());
                    } catch (Exception e) {
                    }
                }
            });

            contextMenu.getItems().addAll(editPay, deletePay);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );
            return row;
        });
        /*
        repairsTable.setEditable(true);
        repairsTable.getSelectionModel().setCellSelectionEnabled(true);
        repairsTable.setOnKeyPressed((event) -> {
            TablePosition<RModel, ?> pos = repairsTable.getFocusModel().getFocusedCell();
            if (pos != null && event.getCode().isLetterKey()) {
                repairsTable.edit(pos.getRow(), pos.getTableColumn());
            } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.TAB) {
                repairsTable.getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT) {
                selectPrevious();
                event.consume();
            }
        });*/
        repairsTable.getColumns().addAll(houseNo, repairMonthCol, repairDone, costOfRepair, dateOfRepair, miscExpenses);

        pdName.textProperty().addListener((observable, oldValue, newValue) -> {
            int length = newValue.length();
            if (length > 21) {
                pdName.setPrefWidth(length * 8);
            } else if (newValue.isEmpty() || oldValue.isEmpty()) {
                pdName.setPrefWidth(170);
            }
        });
        pdName.setEditable(false);

        tdName.textProperty().addListener((observable, oldValue, newValue) -> {
            int length = newValue.length();
            if (length > 21) {
                tdName.setPrefWidth(length * 8);
            } else if (newValue.isEmpty() || oldValue.isEmpty()) {
                tdName.setPrefWidth(170);
            }
        });

        paymentDetails.setOnSelectionChanged((event) -> {
            if (sp1.getItems().size() == 2) {
                sp1.getItems().remove(tableViewPane);
                pdTableViewButton.setText("Show details >>");
                Scene scene = paymentDetails.getTabPane().getScene();
                Stage stage = (Stage) scene.getWindow();
                MainApp.changeWindowSize(stage, 500);
            }
        });

        repairDetails.setOnSelectionChanged((event) -> {
            if (sp1.getItems().size() == 2) {
                sp1.getItems().remove(tableViewPane);
                rdTableViewButton.setText("Show details >>");
                Scene scene = repairDetails.getTabPane().getScene();
                Stage stage = (Stage) scene.getWindow();
                MainApp.changeWindowSize(stage, 500);
            }
        });

        rdRepairsDone.setWrapText(true);
        rdRepairsDone.setPrefWidth(170);
        rdRepairsDone.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                rdRepairsDone.applyCss();
                Node text = rdRepairsDone.lookup(".text");
                rdRepairsDone.prefHeightProperty().bind(Bindings.createDoubleBinding(() -> {
                    return rdRepairsDone.getFont().getSize() + text.getBoundsInLocal().getHeight();
                }, text.boundsInLocalProperty()));

                text.boundsInLocalProperty().addListener((observableBoundsAfter, boundsBefore, boundsAfter) -> {
                    Platform.runLater(() -> rdRepairsDone.requestLayout());
                });
            }
        });

        pdOtherExpenseField.setWrapText(true);
        pdOtherExpenseField.setPrefSize(100, 10);
        pdOtherExpenseField.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                pdOtherExpenseField.applyCss();
                Node text = pdOtherExpenseField.lookup(".text");
                pdOtherExpenseField.prefHeightProperty().bind(Bindings.createDoubleBinding(() -> {
                    return pdOtherExpenseField.getFont().getSize() + text.getBoundsInLocal().getHeight();
                }, text.boundsInLocalProperty()));

                text.boundsInLocalProperty().addListener((observableBoundsAfter, boundsBefore, boundsAfter) -> {
                    Platform.runLater(() -> pdOtherExpenseField.requestLayout());
                });
            }
        });

        pdTableViewButton.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, (event) -> {
            timeline.stop();
            pdTableViewButton.setOpacity(1.0);
        });

        pdTableViewButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            timeline.stop();
        });

        pdTableViewButton.setVisible(false);
        pdTableViewButton.setOnAction((event) -> {
            if (sp1.getItems().size() == 1) {
                paymentsTable.setMinHeight(200);
                paymentsTable.setItems(getPaymentDetails());
                tableViewPane.setCenter(paymentsTable);
                sp1.getItems().add(tableViewPane);
                pdTableViewButton.setText("Hide details >>");
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                MainApp.changeWindowSize(stage, 600);
            } else if (sp1.getItems().size() == 2) {
                sp1.getItems().remove(tableViewPane);
                pdTableViewButton.setText("Show details >>");
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                MainApp.changeWindowSize(stage, 500);
            }
        });

        rdTableViewButton.setVisible(false);
        rdTableViewButton.setOnAction((event) -> {
            if (sp1.getItems().size() == 1) {
                repairsTable.setMinHeight(200);
                repairsTable.setItems(getRepairsDetails());
                tableViewPane.setCenter(repairsTable);
                sp1.getItems().add(tableViewPane);
                rdTableViewButton.setText("Hide details >>");
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                MainApp.changeWindowSize(stage, 600);
            } else if (sp1.getItems().size() == 2) {
                sp1.getItems().remove(tableViewPane);
                rdTableViewButton.setText("Show details >>");
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                MainApp.changeWindowSize(stage, 500);
            }
        });

        paymentsTable.getColumns().addAll(houseNoCol, tenantNameCol, amountCol, monthCol, dateCol, methodCol);

        pdHbox5.setPadding(new Insets(10, 0, 0, 0));

        pdMonthCombo.setPrefWidth(170);
        rdMonthCombo.setPrefWidth(170);

        placingVbox.setPadding(new Insets(22, 0, 0, 0));
        placingVbox.getChildren().addAll(pdPaymentOption);

        pdVbox.setPadding(new Insets(10, 10, 10, 10));
        pdVboxEdit.getChildren().addAll(pdHbox1, pdHbox2, pdHbox3, pdHbox4, pdHbox5);
        pdVbox.getChildren().addAll(pdVboxEdit, pdHbox6);
        tdVbox.setPadding(new Insets(10, 10, 10, 10));
        tdVbox.getChildren().addAll(tdHbox1, tdHbox2, tdHbox3, tdHbox4, tdHbox5, tdHbox6, tdHbox7, tdHbox8, tdHbox9);
        rdVbox.setPadding(new Insets(10, 10, 10, 10));
        rdVbox.getChildren().addAll(rdHbox7, rdHbox2, rdHbox3, rdHbox4, rdHbox5, rdHbox6);

        tenantDataPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tenantDataPane.getTabs().addAll(tenantDetails, paymentDetails, repairDetails);

        detailsPane.setMinSize(450, 300);
        detailsPane.setCenter(tenantDataPane);
        blockTreeView.setMinWidth(120);
        selectionPane.setCenter(blockTreeView);
    }

    public class MyPopUp extends Stage {

        public MyPopUp() {
            super();
            this.setHeight(200);
            this.setWidth(270);
            this.setMinHeight(200);
            this.setMinWidth(270);
            this.setResizable(true);
            this.initStyle(StageStyle.UNDECORATED);
            this.initModality(Modality.APPLICATION_MODAL);
            this.setOpacity(0.9);

            BorderPane borderPaneOptionPane = new BorderPane();

            stickyTextArea.setPromptText("Add a comment...");
            stickyTextArea.setPrefSize(200, 150);

            Region filler = new Region();
            Region bottomFiller = new Region();

            HBox stickyHbox = new HBox();
            HBox.setHgrow(filler, Priority.ALWAYS);
            Label label1 = new Label();
            label1.setAlignment(Pos.CENTER);
            label2.setAlignment(Pos.CENTER);

            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                label1.setText(LocalDateTime.now().format(formatter));
            }), new KeyFrame(Duration.seconds(1)));
            clock.setCycleCount(Animation.INDEFINITE);
            clock.play();

            label2.setOnMouseClicked((evt) -> {
                Stage stage = (Stage) (label1.getScene()).getWindow();
                stage.close();
            });
            label1.setStyle("-fx-font-family: Arial; -fx-font-weight: 700;");
            label2.setStyle("-fx-font-family: Arial; -fx-font-weight: 700;");
            stickyHbox.getChildren().addAll(label1, filler, label2);

            HBox stickyHboxBottom = new HBox();
            HBox.setHgrow(bottomFiller, Priority.ALWAYS);
            stickyHboxBottom.setAlignment(Pos.BASELINE_LEFT);
            stickyHboxBottom.setPadding(new Insets(3, 0, 0, 0));
            stickyHboxBottom.getChildren().addAll(bottomFiller, save);

            Rectangle stickyRec = new Rectangle(200, 150);
            stickyRec.setArcWidth(10.0);
            stickyRec.setArcHeight(10.0);

            borderPaneOptionPane.setShape(stickyRec);
            borderPaneOptionPane.setCenter(stickyTextArea);
            borderPaneOptionPane.setTop(stickyHbox);
            borderPaneOptionPane.setBottom(stickyHboxBottom);
            borderPaneOptionPane.setPadding(new Insets(5));
            borderPaneOptionPane.setStyle("-fx-border-color: Black;");

            Scene s = new Scene(borderPaneOptionPane);
            s.setFill(Color.TRANSPARENT);
            this.setScene(s);
            this.setX(xCursorPos);
            this.setY(yCursorPos);
            ResizeHelper.addResizeListener(this);
        }

        public void setStickyEmpty() {
            stickyTextArea.clear();
        }
    }

    public class DetailsIcon extends StackPane {

        public DetailsIcon() {
            Label lab = new Label("•••");
            lab.setStyle("-fx-text-fill:white");
            lab.setOnMouseClicked((event) -> {
                ContextMenu conMenu = new ContextMenu(tdNewRecord, new SeparatorMenuItem(), tdUpdate, tdDelete, new SeparatorMenuItem(), tdSave, tdSaveAs);
                conMenu.show(detIcon, event.getScreenX(), event.getScreenY());
            });
            Circle circle = new Circle(12f, Color.rgb(0, 122, 255));
            getChildren().addAll(circle, lab);
        }
    }

    public class PayMenuIcon extends StackPane {

        public PayMenuIcon() {
            Label lab = new Label("•••");
            lab.setStyle("-fx-text-fill:white");
            lab.setOnMouseClicked((event) -> {
                ContextMenu conMenu = new ContextMenu(pdNewRecord, new SeparatorMenuItem(), pdUpdate, pdDelete, new SeparatorMenuItem(), pdSave, new SeparatorMenuItem(), pdStickyNote, new SeparatorMenuItem(), pdPrintReceipt);
                conMenu.show(payIcon, event.getScreenX(), event.getScreenY());
            });
            Circle circle = new Circle(12f, Color.rgb(0, 122, 255));
            getChildren().addAll(circle, lab);
        }
    }

    public class repairsMenuIcon extends StackPane {

        public repairsMenuIcon() {
            Label lab = new Label("•••");
            lab.setStyle("-fx-text-fill:white");
            lab.setOnMouseClicked((event) -> {

            });
            Circle circle = new Circle(12f, Color.rgb(0, 122, 255));
            getChildren().addAll(circle, lab);
        }
    }
    
    class FetchTenantDetailsTask extends DBTask<ObservableList<String>> {

        @Override
        protected ObservableList<String> call() throws Exception {
            try (Connection con = getConnection()) {
                return fetchTenantDetails(con);
            }
        }

        private ObservableList<String> fetchTenantDetails(Connection con) {
            logger.info("Fetching Tenant's Details from database");
            ObservableList<String> tenantData = FXCollections.observableArrayList();

            try {
                String searchTenantDetails = "SELECT * FROM TenantDetails WHERE HouseNumber = ?";
                pstmt = con.prepareStatement(searchTenantDetails);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                rs = pstmt.executeQuery();

                if (!rs.next()) {
                    logger.info("No entry found in database");
                    tenantRowId = 0;
                } else {
                    do {
                        tenantRowId = rs.getInt("RowID");
                        tenantData.addAll(rs.getString("TenantName"), rs.getString("TenantPhoneNumber"), rs.getString("RentAmount"), rs.getString("Deposit"), rs.getString("DueDate"), rs.getString("MoveInDate"), rs.getString("MoveOutDate"), rs.getString("LeaseStartDate"), rs.getString("LeaseEndDate"));
                    } while (rs.next());
                    logger.info("Found 1 entry");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return tenantData;
        }
    }
    
    class FetchPaymentDetailsTask extends DBTask<ObservableList<String>> {
        @Override
        protected ObservableList<String> call() throws Exception {
            try (Connection con = getConnection()) {
                return fetchPaymentDetails(con);
            }
        }
        
        public ObservableList<String> fetchPaymentDetails(Connection con) {
            logger.info("Fetching Payment Details from database");
            ObservableList<String> paymentDetails = FXCollections.observableArrayList();
            
            try {
                String searchPDTable = "SELECT * FROM PaymentDetails WHERE HouseNumber = ? AND Month = ?";
                pstmt = con.prepareStatement(searchPDTable);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                pstmt.setString(2, pdMonthCombo.getValue().name());
                rs = pstmt.executeQuery();
                
                if (!rs.next()) {
                    logger.info("No entry found in database");
                    payRowId = 0;
                } else {
                    do {                        
                       payRowId = rs.getInt("RowID");
                       paymentDetails.addAll(rs.getString("HouseNumber"), rs.getString("TenantName"), rs.getString("Amount"), rs.getString("Month"), rs.getString("PaymentDate"), rs.getString("PaymentMethod"), rs.getString("StickyNote"));
                    } while (rs.next());
                    logger.info("Found entry");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            return paymentDetails;
        }
    }
    
    class SaveTenantDetailsTask extends DBTask {

        @Override
        protected Void call() throws Exception {
            Thread.sleep(1000);

            try (Connection con = getConnection()) {
                if (saveToTenantDetails(con, blockTreeView.getSelectionModel().getSelectedItem().getValue(), tdName.getText(), tdPhone.getText(), tdAmount.getText(), tdDeposit.getText(), tdDueDate.getText(), getDateValueAsString(tdMoveInDate.getValue()), getDateValueAsString(tdMoveOutDate.getValue()), getDateValueAsString(tdLeaseStartDate.getValue()), getDateValueAsString(tdLeaseEndDate.getValue()))) {
                    try {
                        File file = new File(excelFileLocation);
                        createExcelSheet(file, blockTreeView.getSelectionModel().getSelectedItem().getValue(), tdName.getText(), tdPhone.getText(), tdAmount.getText(), tdDeposit.getText(), tdDueDate.getText(), getDateValueAsString(tdMoveInDate.getValue()), getDateValueAsString(tdMoveOutDate.getValue()), getDateValueAsString(tdLeaseStartDate.getValue()), getDateValueAsString(tdLeaseEndDate.getValue()));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            return null;
        }

        private boolean saveToTenantDetails(Connection con, String houseNumber, String tenantName, String tenantPhoneNumber, String monthlyRent, String deposit, String dueDate, String moveInDate, String moveOutDate, String leaseStartDate, String leaseEndDate) {
            logger.info("Inserting into Tenant Details.");

            try {
                String insertToTenantTable = "INSERT INTO TenantDetails(HouseNumber, TenantName, TenantPhoneNumber, RentAmount, Deposit, DueDate, MoveInDate, MoveOutDate, LeaseStartDate, LeaseEndDate) VALUES(?,?,?,?,?,?,?,?,?,?)";
                pstmt = con.prepareStatement(insertToTenantTable);
                pstmt.setString(1, houseNumber);
                pstmt.setString(2, tenantName);
                pstmt.setString(3, tenantPhoneNumber);
                pstmt.setString(4, monthlyRent);
                pstmt.setString(5, deposit);
                pstmt.setString(6, dueDate);
                pstmt.setString(7, moveInDate);
                pstmt.setString(8, moveOutDate);
                pstmt.setString(9, leaseStartDate);
                pstmt.setString(10, leaseEndDate);
                pstmt.execute();

                /*String lastRow = "Select last_insert_rowid()";
                pstmt1 = con.prepareStatement(lastRow);
                ResultSet rs = pstmt1.executeQuery();
                int lastRowID = rs.getInt(0);
                System.out.println(lastRowID);
                 */
            } catch (SQLException e) {
                logger.info("Insert into Tenant Details table failed.");
                e.printStackTrace();
                return false;
            } finally {
                try {
                    con.close();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }
    
    class savePaymentDetailsTask extends DBTask {
        @Override
        protected Void call() throws Exception {
            Thread.sleep(1000);

            try (Connection con = getConnection()) {
                if (savePaymentDetailsToTable(con, blockTreeView.getSelectionModel().getSelectedItem().getValue(), pdName.getText(), pdAmount.getText(), pdMonthCombo.getValue(), getDateValueAsString(pdPaymentDate.getValue()), payLabel.getText())) {
                    try {
                        System.out.println(excelFileLocation);
                        File file = new File(excelFileLocation);
                        createAndWriteExcelSheet(file, blockTreeView.getSelectionModel().getSelectedItem().getValue(), pdName.getText(), pdAmount.getText(), pdMonthCombo.getSelectionModel().getSelectedItem().name(), getDateValueAsString(pdPaymentDate.getValue()), payLabel.getText());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
        
        private boolean savePaymentDetailsToTable(Connection con, String HouseNumber, String TenantName, String Amount, PDModel.Strings Month, String PaymentDate, String PaymentMethod) {
            logger.info("Inserting into Payment Detils table.");
            try {
                String insertToPaymentDetails = "INSERT INTO PaymentDetails(HouseNumber, TenantName, Amount, Month, PaymentDate, PaymentMethod) VALUES(?,?,?,?,?,?)";
                pstmt = con.prepareStatement(insertToPaymentDetails);
                pstmt.setString(1, HouseNumber);
                pstmt.setString(2, TenantName);
                pstmt.setString(3, Amount);
                pstmt.setString(4, Month.name());
                pstmt.setString(5, PaymentDate);
                pstmt.setString(6, PaymentMethod);
                pstmt.execute();
            } catch (SQLException e) {
                logger.info("Insert into Payment Details table failed.");
                e.printStackTrace();
                return false;
            } finally {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }
    
    class UpdateTenantDetailsTask extends DBTask {

        @Override
        protected Void call() throws Exception {
            Thread.sleep(1000);

            try (Connection con = getConnection()) {
                if (updateTenantDetails(con, tdName.getText(), tdPhone.getText(), tdAmount.getText(), tdDeposit.getText(), tdDueDate.getText(), getDateValueAsString(tdMoveInDate.getValue()), getDateValueAsString(tdMoveOutDate.getValue()), getDateValueAsString(tdLeaseStartDate.getValue()), getDateValueAsString(tdLeaseEndDate.getValue()), tenantRowId)) {
                    try {
                        File file = new File(excelFileLocation);
                        updateTDExcelRowValue(file);
                    } catch (IOException ex) {
                        Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            return null;
        }

        private boolean updateTenantDetails(Connection con, String tenantName, String tenantPhoneNumber, String monthlyRent, String deposit, String dueDate, String moveInDate, String moveOutDate, String leaseStartDate, String leaseEndDate, int rowID) {
            logger.info("Updatimg Tenant Details table");

            try {
                String updateTD = "UPDATE TenantDetails SET TenantName = ?, TenantPhoneNumber = ?, RentAmount = ?, Deposit = ?, DueDate = ?, MoveInDate = ?, MoveOutDate = ?, LeaseStartDate = ?, LeaseEndDate = ? WHERE RowID = ?";
                pstmt = con.prepareStatement(updateTD);
                pstmt.setString(1, tenantName);
                pstmt.setString(2, tenantPhoneNumber);
                pstmt.setString(3, monthlyRent);
                pstmt.setString(4, deposit);
                pstmt.setString(5, dueDate);
                pstmt.setString(6, moveInDate);
                pstmt.setString(7, moveOutDate);
                pstmt.setString(8, leaseStartDate);
                pstmt.setString(9, leaseEndDate);
                pstmt.setInt(10, rowID);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                logger.info("Updating Tenant Details table failed.");
                e.printStackTrace();
                return false;
            } finally {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }
    
    class UpdatePaymentDetailsTask extends DBTask {

        @Override
        protected Void call() throws Exception {
            Thread.sleep(1000);

            try (Connection con = getConnection()) {
                if (updatePaymentDetails(con, pdAmount.getText(), getDateValueAsString(pdPaymentDate.getValue()), payLabel.getText(), payRowId)) {
                    try {
                        File file = new File(excelFileLocation);
                        updatePDExcelRowValue(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }

        private boolean updatePaymentDetails(Connection con, String Amount, String PaymentDate, String PaymentMethod, int rowID) {
            logger.info("Updating Payment Details");

            try {
                String updateAmountSql = "UPDATE PaymentDetails SET Amount = ?, PaymentDate = ?, PaymentMethod = ?  WHERE RowID = ?";
                pstmt = con.prepareStatement(updateAmountSql);
                pstmt.setString(1, Amount);
                pstmt.setString(2, PaymentDate);
                pstmt.setString(3, PaymentMethod);
                pstmt.setInt(4, rowID);
                pstmt.execute();
            } catch (SQLException e) {
                logger.info("Updating Payment Details table failed.");
                e.printStackTrace();
                return false;
            } finally {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }
    
    class DeleteFromTenantDetailsTask extends DBTask {

        @Override
        protected Void call() throws Exception {
            Thread.sleep(1000);

            try (Connection con = getConnection()) {
                if (deleteFromTenantDetails(con, tenantRowId, blockTreeView.getSelectionModel().getSelectedItem().getValue())) {
                    try {
                        File file = new File(excelFileLocation);
                        removeExcelRowValue(file);
                        removePDExcelRowFromTD(file);
                    } catch (IOException | InvalidFormatException ex) {
                        Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
                        ex.printStackTrace();
                    }
                }
            }

            return null;
        }

        private boolean deleteFromTenantDetails(Connection con, int rowID, String houseNummber) {
            logger.info("Deleting entry from Tenant Details");

            try {

                String deleteTDString = "DELETE FROM TenantDetails WHERE RowID = ?";
                String deletePDString = "DELETE FROM PaymentDetails WHERE HouseNumber = ?";

                try {
                    con.setAutoCommit(false);
                    pstmt = con.prepareStatement(deleteTDString);
                    pstmt1 = con.prepareStatement(deletePDString);

                    pstmt.setInt(1, rowID);
                    pstmt.executeUpdate();
                    pstmt1.setString(1, houseNummber);
                    pstmt1.executeUpdate();
                    con.commit();
                } catch (SQLException e) {
                    logger.info("Tenant Details update failed.");
                    e.printStackTrace();
                    if (con != null) {
                        try {
                            System.err.println("Transaction is being rolled back");
                            con.rollback();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                    return false;
                } finally {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (pstmt1 != null) {
                        pstmt1.close();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            return true;
        }

    }
    
    class DeleteFromPaymentDetailsTask extends DBTask {

        @Override
        protected Void call() throws Exception {
            Thread.sleep(1000);

            try (Connection con = getConnection()) {
                if (deleteFromPaymentDetailsTask(con, payRowId)) {
                    try {
                        File file = new File(excelFileLocation);
                        removePDExcelRow(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }

        private boolean deleteFromPaymentDetailsTask(Connection con, int rowID) {
            logger.info("Deleting entry from Payment Details");

            try {
                String deletePaySql = "DELETE FROM PaymentDetails WHERE RowID = ?";
                pstmt = con.prepareStatement(deletePaySql);
                pstmt.setInt(1, rowID);
                pstmt.execute();
            } catch (SQLException e) {
                logger.info("Failed to delete from Tenant Details.");
                e.printStackTrace();
                return false;
            } finally {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }
    
    abstract class DBTask<T> extends Task<T> {

        DBTask() {
            setOnFailed((event) -> {
                logger.log(Level.SEVERE, null, getException());
            });
        }
    }

    class DBSetupTask extends DBTask {

        @Override
        protected Void call() throws Exception {
            try (Connection con = getConnection()) {
                if (!schemaExists(con) && !schemaExists2(con)) {
                    createSchema(con);
                    createSchema2(con);
                } else if (!schemaExists(con) && schemaExists2(con)) {
                    createSchema(con);
                } else if (schemaExists(con) && !schemaExists2(con)) {
                    createSchema2(con);
                }
            }

            return null;
        }

        public boolean schemaExists(Connection con) {
            logger.info("Checking for schema exictence");
            try {
                String searchTenantDetails = "SELECT * FROM TenantDetails";
                pstmt = con.prepareStatement(searchTenantDetails);
                pstmt.executeQuery();
                logger.info("Schema exists");
            } catch (SQLException e) {
                e.printStackTrace();
                logger.info("Existing DB not found. Create new one");
                return false;
            } finally {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return true;
        }
        
        public boolean schemaExists2(Connection con) {
            logger.info("Checking for schema2 existence");
            try {
                String searchPaymentDetails = "SELECT * FROM PaymentDetails";
                pstmt = con.prepareStatement(searchPaymentDetails);
                pstmt.executeQuery();
                logger.info("Schema2 Exists");
            } catch (SQLException e) {
                e.printStackTrace();
                logger.info("Table not in DB. Create new one.");
                return false;
            } finally {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        
        private void createSchema(Connection con) {
            logger.info("Creating schema");
            String createTDSql = "CREATE TABLE IF NOT EXISTS TenantDetails(RowID Integer PRIMARY KEY AUTOINCREMENT, HouseNumber text UNIQUE CHECK(HouseNumber<>''), TenantName text CHECK(TenantName<>''), TenantPhoneNumber text, RentAmount text, Deposit text , DueDate text, MoveInDate text, MoveOutDate text, LeaseStartDate text, LeaseEndDate text) ";
            try {
                pstmt = con.prepareStatement(createTDSql);
                pstmt.execute();
                logger.info("Created schema");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
        }
        
        private void createSchema2(Connection con) {
            logger.info("Creating second schema");
            String createPDSql = "CREATE TABLE IF NOT EXISTS PaymentDetails(RowID Integer PRIMARY KEY AUTOINCREMENT, HouseNumber text, TenantName text CHECK(TenantName<>''), Amount text, Month text, PaymentDate text, PaymentMethod text, StickyNote text, UNIQUE(HouseNumber, Month))";
            try {
                pstmt = con.prepareStatement(createPDSql);
                pstmt.execute();
                logger.info("Created second schema");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(databaseURL);
    }

    static class DatabaseThreadFactory implements ThreadFactory {

        static final AtomicInteger POOLNUMBER = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "Database-Connection-" + POOLNUMBER.getAndIncrement() + "-thread");
            thread.setDaemon(true);

            return thread;
        }
    }
}
