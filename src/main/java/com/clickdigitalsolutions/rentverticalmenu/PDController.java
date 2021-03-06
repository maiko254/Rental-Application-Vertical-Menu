package com.clickdigitalsolutions.rentverticalmenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.clickdigitalsolutions.rentverticalmenu.TDController.getPrefferedCellStyle;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXTreeView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import java.time.LocalTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTablePosition;
import javafx.scene.control.TreeTableRow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.crypto.Mac;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
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

    @FXML
    private SplitPane sp1;

    @FXML
    private SplitPane sp2;
    
    @FXML
    private StackPane wrapStackPane;
    
    @FXML
    public BorderPane selectionPane;

    @FXML
    public BorderPane detailsPane;

    private GridPane repairsLayout = new GridPane();
        
    public StackPane stickyIconPane = new StackPane();
    public JFXBadge stickyBadge = new JFXBadge();
    int badgeValue = 0;
    public JFXButton saveStickyButton = new JFXButton("Save");
    public JFXTextArea stickyArea = new JFXTextArea();
    
    public JFXDialog stickyDialog = new JFXDialog();
    
    private JFXTreeTableColumn<PDModel, String> houseNoCol = new JFXTreeTableColumn("House Number");
    private JFXTreeTableColumn<PDModel, String> tenantNameCol = new JFXTreeTableColumn("Tenant Name");
    private JFXTreeTableColumn<PDModel, String> amountCol = new JFXTreeTableColumn("Amount");
    private JFXTreeTableColumn<PDModel, PDModel.Strings> monthCol = new JFXTreeTableColumn("Month");
    private JFXTreeTableColumn<PDModel, String> dateCol = new JFXTreeTableColumn("Payment Date");
    private JFXTreeTableColumn<PDModel, String> methodCol = new JFXTreeTableColumn("Payment Mode");
    public  JFXTreeTableView<PDModel> paymentsTable = new JFXTreeTableView<>();

    public JFXTreeTableColumn<RModel, RModel.Strings> repairMonthCol = new JFXTreeTableColumn<>("Month");
    public JFXTreeTableColumn<RModel, String> repairDone = new JFXTreeTableColumn("Repairs");
    public JFXTreeTableColumn<RModel, String> materialCostOfRepair = new JFXTreeTableColumn<>("Material Cost");
    public JFXTreeTableColumn<RModel, String> labourCostOfRepair = new JFXTreeTableColumn<>("Labour Cost");
    public JFXTreeTableColumn<RModel, String> dateOfRepair = new JFXTreeTableColumn("Repair Date");
    public JFXTreeTableColumn<RModel, String> miscExpenses = new JFXTreeTableColumn<>("Miscellaneous Expenses");
    public JFXTreeTableView<RModel> repairsTable = new JFXTreeTableView<>();

    MenuItem tdNewRecord = new MenuItem("New Entry");
    MenuItem tdUpdate = new MenuItem("Update");
    MenuItem tdDelete = new MenuItem("Delete");
    MenuItem tdSave = new MenuItem("Save");
    MenuItem tdSaveAs = new MenuItem("Save As...");

    MenuItem pdUpdate = new MenuItem("Update");
    MenuItem pdDelete = new MenuItem("Delete");
    MenuItem pdSave = new MenuItem("Save");
    MenuItem pdStickyNote = new MenuItem("Sticky Note");
    MenuItem pdPrintReceipt = new MenuItem("Print Receipt");
    MenuItem pdNewRecord = new MenuItem("New Entry");
    
    MenuItem rdUpdate = new MenuItem("Update");
    MenuItem rdDelete = new MenuItem("Delete");
    MenuItem rdSave = new MenuItem("Save");
    MenuItem rdNewRecord = new MenuItem("New Entry");
    
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
    
    public StackPane pdStackPane = new StackPane();
    public BorderPane payAndTenantDetailsBorderPane = new BorderPane();
    public VBox payAndTenantVbox = new VBox(10);
    public VBox payTableVbox = new VBox();
    
    public TabPane payAndTenantDetailsTabPane = new TabPane();
    public Tab tenantTab  = new Tab("Tenant");
    public Tab paymentsTab = new Tab("Payments");
    public GridPane tenantsTabGridPane = new GridPane();
    public GridPane paymentsTabGridPane = new GridPane();
    public VBox tenantDetailsVbox = new VBox(10);
    public VBox paymentDetailsVbox = new VBox(10);
    public VBox vbox7 = new VBox();
    public VBox vbox8 = new VBox();
    public VBox vbox9 = new VBox();
    public VBox vbox10 = new VBox();
    public VBox vbox11 = new VBox();
    public VBox vbox12 = new VBox(2);
    public VBox vbox13 = new VBox(2);
    public VBox vbox14 = new VBox(2);
    public VBox vbox15 = new VBox(40);
    public VBox vbox16 = new VBox(2);
    public VBox vbox17 = new VBox(35);
    public VBox vbox18 = new VBox(3);
    public JFXNodesList selectPayOptionNode = new JFXNodesList();
    public ButtonBar paymentNodesButtonBar = new ButtonBar();
    public JFXNodesList regAndLeaseDatesNodesList = new JFXNodesList();
    public JFXNodesList regDatesNodesList = new JFXNodesList();
    public JFXNodesList leaseDatesNodesList = new JFXNodesList();
    public JFXButton regAndLeaseDatesButton = new JFXButton("Registration & Lease Dates");
    public ToggleGroup regLeaseToggleGroup = new ToggleGroup();
    public ToggleGroup regDatesToggleGroup = new ToggleGroup();
    public ToggleGroup leaseDatesToggleGroup = new ToggleGroup();
    public ToggleButton regDatesButton = new ToggleButton();
    public ToggleButton leaseDatesButton = new ToggleButton();
    public ToggleButton moveInButton = new ToggleButton();
    public ToggleButton moveOutButton = new ToggleButton(); 
    public ToggleButton leaseStartButton = new ToggleButton();
    public ToggleButton leaseEndButton = new ToggleButton();
    public ButtonBar regAndLeaseDatesButtonBar = new ButtonBar();
    public ButtonBar regDatesButtonBar = new ButtonBar();
    public ButtonBar leaseDatesButtonBar = new ButtonBar();
    public StackPane searchTextFieldStackPane = new StackPane();
    public TextField searchPaymentsTableTextfield = new TextField();
    
    public StackPane rdStackPane = new StackPane();
    public BorderPane rdBorderPane = new BorderPane();
    public VBox repairVbox = new VBox(10);
    public VBox repairTableDetailsVbox = new VBox();
    public StackPane repairSearchStackPane = new StackPane();
    public Button repairSearchButton = new Button();
    public Button clearRepairSearchTextfield = new Button();
    public GridPane repairDetailsGrid = new GridPane();
    public AnchorPane repairTableViewAnchorPane = new AnchorPane();
    public GridPane repairTableViewGridPane = new GridPane();
    public AnchorPane repairSearchAnchorPane = new AnchorPane();
    public VBox repairDetailsVbox = new VBox(15);
    public VBox vbox2 = new VBox(2);
    public VBox vbox3 = new VBox(2);
    public VBox vbox4 = new VBox(2);
    public VBox vbox5 = new VBox(2);
    public VBox vbox6 = new VBox(2);
    
    DetailsIcon detIcon = new DetailsIcon();
    PayMenuIcon payIcon = new PayMenuIcon();
    repairsMenuIcon repairsIcon = new repairsMenuIcon();
    
    public TextArea repairDetailsTextArea = new TextArea();
    
    public JFXTabPane tenantDataPane = new JFXTabPane();
    public Tab paymentDetails = new Tab("Tenant Details", pdStackPane);
    public Tab repairDetails = new Tab("Repairs Details", rdStackPane);

    Label repairHeaderLabel = new Label("Repairs Information");
    Label tenantDetailsLabel = new Label("Tenant Information");
    Label paymentDetailsLabel = new Label("Payment Information");
    HBox paymentDetailsLabelHbox = new HBox(10, paymentDetailsLabel, payIcon);
    HBox tenantDetailsLabelHbox = new HBox(10, tenantDetailsLabel, detIcon);
    
    
    Label l1 = new Label("Tenant Name");
    Label l2 = new Label("Phone Number");
    Label l3 = new Label("Monthly Rent");
    Label l4 = new Label("House Deposit");
    Label l5 = new Label("Rent-Due-Date");
    Label l6 = new Label("Move-In");
    Label l7 = new Label("Move-Out");
    Label l8 = new Label("Lease-Start");
    Label l9 = new Label("Lease-End");
    Label l21 = new Label("Reg. Dates");
    
    Label l10 = new Label("Tenant Name");
    Label l11 = new Label("Month");
    Label l12 = new Label("Amount Paid");
    Label l13 = new Label("Payment Date");
    Label l14 = new Label("Payment Options");

    Label l20 = new Label("Month");
    Label l16 = new Label("Repairs Done");
    Label l17 = new Label("Repair Costs");
    Label l18 = new Label("Repairs Date");
    Label l19 = new Label("Miscellaneous\nCosts");
    
    public TextField tdName = new TextField();
    public TextField tdPhone = new TextField();
    public TextField tdAmount = new TextField();
    public TextField tdDeposit = new TextField();
    public TextField tdDueDate = new TextField();
    public JFXDatePicker tdMoveInDate = new JFXDatePicker();
    public JFXDatePicker tdMoveOutDate = new JFXDatePicker();
    public JFXDatePicker tdLeaseStartDate = new JFXDatePicker();
    public JFXDatePicker tdLeaseEndDate = new JFXDatePicker();
    public JFXSpinner databaseActivityIndicatorTD = new JFXSpinner();
    public Label tdNewEntryLabel = new Label("New Entry");
    public Label tdUpdateLabel = new Label("Update");
    public Label tdDeleteLabel = new Label("Delete");
    public Label tdSaveLabel = new Label("Save");
    public Label tdSaveAsLabel = new Label("Save As..");

    public TextField pdName = new TextField();
    public JFXComboBox<PDModel.Strings> pdMonthCombo = new JFXComboBox<>();
    public TextField pdAmount = new TextField();
    public JFXDatePicker pdPaymentDate = new JFXDatePicker();
    public JFXButton pdTableViewButton = new JFXButton();
    public Label rentArrearslabel = new Label();
    public JFXSpinner databaseActivityIndicatorPD = new JFXSpinner();
    public TextField pdCashTextfield = new TextField();
    public TextField pdbankTextfield = new TextField();
    public TextField pdMpesaTextfield = new TextField();
    public JFXButton paymentOptionButton = new JFXButton();
    public Label cashLabel = new Label("Cash");
    public Label bankLabel = new Label("Bank");
    public Label mpesaLabel = new Label("Mpesa");
    public Label datesLabel = new Label("Reg. Dates");
    public Label leaseLabel = new Label("Lease Dates");
    public Label moveInLabel = new Label("Move-In");
    public Label moveOutLabel = new Label("Move-Out");
    public Label leaseStartLabel = new Label("Lease-Start");
    public Label leaseEndLabel = new Label("Lease-End");
    public JFXButton paymentOptionsHeaderButton = new JFXButton("Select Payment Mode");
    public ToggleGroup payOptionToggleGroup = new ToggleGroup();
    public ToggleButton cashButton = new ToggleButton();
    public ToggleButton bankButton = new ToggleButton();
    public ToggleButton mpesaButton = new ToggleButton();
    public String payMethodString = new String();
    public VBox rentArrearsVbox = new VBox();
    public HBox rentArrearsHbox = new HBox();
    public VBox monthRentArrearsVbox = new VBox();
    public VBox paidRentArrearsVbox = new VBox();
    public VBox remainderRentArrearsVbox = new VBox();
    public JFXTextField monthRentArrearsTextfield = new JFXTextField();
    public JFXTextField paidRentArrearsTextfield = new JFXTextField();
    public JFXTextField remainderRentArrearsTextfield = new JFXTextField();
    public int houseMonthlyRent = 0;
    
    public JFXComboBox<RModel.Strings> rdMonthCombo = new JFXComboBox<>();
    public JFXTextArea rdRepairsDone = new JFXTextArea();
    public JFXDatePicker rdRepairDate = new JFXDatePicker();
    public JFXButton rdTableViewButton = new JFXButton();
    public JFXSpinner databaseActivityIndicatorRD = new JFXSpinner();
    public JFXButton repairCostSceneButton = new JFXButton();
    public JFXButton materialButton =  new JFXButton();
    public JFXButton labourButton = new JFXButton();
    public JFXButton miscButton = new JFXButton();
    public JFXTextField materialText = new JFXTextField();
    public JFXTextField labourText = new JFXTextField();
    public JFXTextField miscText = new JFXTextField();
    public HBox rdMaterialHbox = new HBox(10);
    public HBox rdLabourHbox = new HBox(10);
    public HBox rdMiscHbox = new HBox(10);
    public JFXButton doneButton = new JFXButton("Done");
    public JFXNodesList labourNodesList = new JFXNodesList();
    public JFXNodesList materialNodesList = new JFXNodesList();
    public JFXNodesList miscNodesList = new JFXNodesList();
    public TextField repairsTableSearchTextField = new TextField();
    
    private static final String ANIMATED_OPTION_BUTTON = "animated-option-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON = "animated-option-sub-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON2 = "animated-option-sub-button2";
    private static final String ANIMATED_HEADER_BUTTON = "animated-header-button";
    private static final String ANIMATED_HEADER_BUTTONTD = "animated-header-buttonTD";
    private static final String ANIMATED_OPTION_BUTTONTD = "animated-option-buttonTD";
    private static final String ANIMATED_OPTION_BUTTONRD = "animated-option-buttonRD";
    private static final String TREE_TABLE_VIEW = "tree-table-view";
    private static final String TABLE_SCROLL_BAR = "scroll-bar";
    private static final String ICONS_BADGE = "icons-badge";
    
    HBox rdHbox3 = new HBox(158, l17, repairCostSceneButton);
    HBox rdHbox6 = new HBox(rdTableViewButton);
    public HBox rdDoneHbox = new HBox(10, doneButton);
    
    double xCursorPos = 0;
    double yCursorPos = 0;

    ObservableList<PDModel> payTenantDetails = FXCollections.observableArrayList();
    ObservableList<RModel> houseRepairDetails = FXCollections.observableArrayList();

    String databaseURL = "jdbc:sqlite:C:\\NetbeansProjects\\SQLite\\RVM.db";

    public Preferences prefs;
    
    public String excelFileLocation = "location";

    public String loc = "location";

    XSSFWorkbook workBook;
    
    String repairsDoneText = null;
    String repairsDateText = null;
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt1 = null;
    ResultSet rs1 = null;

    boolean excelInsertFailed = false;
    
    public static final Logger logger = Logger.getLogger(PDController.class.getName());
    
    FontAwesomeIconView tdInsertSuccessIcon = new FontAwesomeIconView();
    
    FontAwesomeIconView tdErrorIcon = new FontAwesomeIconView();
    
    FontAwesomeIconView pdInsertSuccessIcon = new FontAwesomeIconView();
    
    FontAwesomeIconView stickyErrorIcon = new FontAwesomeIconView();
    
    FontAwesomeIconView rdInsertSuccessIcon = new FontAwesomeIconView();
    
    FontAwesomeIconView rdErrorIcon = new FontAwesomeIconView();
    
    Button searchButton = new Button();
    
    Button clearSearchTextFieldButton = new Button();
    
    int stickyBadgeSize = 0;
    
    ArrayList<String> reminderNoteArrayList = new ArrayList<>();
    
    ArrayList<String> dateReminderArrayList = new ArrayList<>();
    
    ArrayList<String> dateReminderArrayList2 = new ArrayList<>();
    
    ArrayList<BorderPane> textAreaArrayList = new ArrayList<>();
    
    ArrayList<JFXTextArea> tempTextAreaArray = new ArrayList<>();
    
    ArrayList<FontAwesomeIconView> tempDeleteIconArray = new ArrayList<>();
     
    ObservableList<BorderPane> textAreaList = FXCollections.observableArrayList();
    
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

    public String getDateValueTimeAsString(LocalDateTime dateTimeConvert) {
        String repairsDateString = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (dateTimeConvert == null) {
            repairsDateString = null;
        } else if (dateTimeConvert != null) {
            repairsDateString = dateTimeConvert.format(formatter);
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
        rdRepairsDone.setText("");
        rdRepairDate.setValue(null);
    }

    public void setEmpty() {
        pdName.clear();
        pdAmount.clear();
        pdMonthCombo.setValue(PDModel.Strings.NONE);
        pdPaymentDate.setValue(null);
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
        materialText.clear();
        labourText.clear();
        repairDetailsTextArea.clear();
        rdRepairDate.setValue(null);
    }

    public ObservableList<PDModel> getPaymentDetails() {
        ObservableList<PDModel> rentPaymentList = FXCollections.observableArrayList();
        PDModel.Strings month = null;
        switch (pdMonthCombo.getSelectionModel().getSelectedItem().getMonth()) {
            case "All":
                try {
                    String searchPDTable = "SELECT * FROM PaymentDetails WHERE HouseNumber = ?";
                    conn = DriverManager.getConnection(databaseURL);
                    pstmt = conn.prepareStatement(searchPDTable);
                    pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        String houseNo1 = rs.getString("HouseNumber");
                        String tenantName1 = rs.getString("TenantName");
                        String rentAmount = rs.getString("Amount");
                        
                        switch (rs.getString("PaymentMonth")) {
                            case "January":
                                month = PDModel.Strings.JANUARY;
                                break;
                            case "February":
                                month = PDModel.Strings.FEBRUARY;
                                break;
                            case "March":
                                month = PDModel.Strings.MARCH;
                                break;
                            case "April":
                                month = PDModel.Strings.APRIL;
                                break;
                            case "May":
                                month = PDModel.Strings.MAY;
                                break;
                            case "June":
                                month = PDModel.Strings.JUNE;
                                break;
                            case "July":
                                month = PDModel.Strings.JULY;
                                break;
                            case "August":
                                month = PDModel.Strings.AUGUST;
                                break;
                            case "September":
                                month = PDModel.Strings.SEPTEMBER;
                                break;
                            case "October":
                                month = PDModel.Strings.OCTOBER;
                                break;
                            case "November":
                                month = PDModel.Strings.NOVEMBER;
                                break;
                            case "December":
                                month = PDModel.Strings.DECEMBER;
                                break;
                            default:
                                break;
                        }
                        
                        String paymentDate = rs.getString("PaymentDate");
                        String paymentMethod = rs.getString("PaymentMethod");
                        PDModel paymentData = new PDModel(rentAmount, month, paymentDate, paymentMethod);
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
                }   break;
            case "":
                pdMonthCombo.getSelectionModel().getSelectedItem().getMonth();
                break;
            default:
                try {
                    String tableDataQuery = "SELECT * FROM PaymentDetails WHERE HouseNumber = ? AND PaymentMonth = ?";
                    conn = DriverManager.getConnection(databaseURL);
                    pstmt = conn.prepareStatement(tableDataQuery);
                    pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                    pstmt.setString(2, pdMonthCombo.getSelectionModel().getSelectedItem().getMonth());
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        String houseNo1 = rs.getString("HouseNumber");
                        String tenantName1 = rs.getString("TenantName");
                        String rentAmount = rs.getString("Amount");
                        
                        switch (rs.getString("PaymentMonth")) {
                            case "January":
                                month = PDModel.Strings.JANUARY;
                                break;
                            case "February":
                                month = PDModel.Strings.FEBRUARY;
                                break;
                            case "March":
                                month = PDModel.Strings.MARCH;
                                break;
                            case "April":
                                month = PDModel.Strings.APRIL;
                                break;
                            case "May":
                                month = PDModel.Strings.MAY;
                                break;
                            case "June":
                                month = PDModel.Strings.JUNE;
                                break;
                            case "July":
                                month = PDModel.Strings.JULY;
                                break;
                            case "August":
                                month = PDModel.Strings.AUGUST;
                                break;
                            case "September":
                                month = PDModel.Strings.SEPTEMBER;
                                break;
                            case "October":
                                month = PDModel.Strings.OCTOBER;
                                break;
                            case "November":
                                month = PDModel.Strings.NOVEMBER;
                                break;
                            case "December":
                                month = PDModel.Strings.DECEMBER;
                                break;
                            default:
                                break;
                        }
                        System.out.println(month);
                        String paymentDate = rs.getString("PaymentDate");
                        String paymentMethod = rs.getString("PaymentMethod");
                        PDModel paymentData = new PDModel(rentAmount, month, paymentDate, paymentMethod);
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
                }   break;
        }
        return rentPaymentList;
    }

    public ObservableList<RModel> getRepairsDetails() {
        ObservableList<RModel> repairsData = FXCollections.observableArrayList();
        RModel.Strings month = null;
        
        switch (rdMonthCombo.getSelectionModel().getSelectedItem().getMonth()) {
            case "All":
                try {
                    String repairsTableQuery = "SELECT * FROM RepairDetailsTable WHERE HouseNumber = ?";
                    conn = DriverManager.getConnection(databaseURL);
                    pstmt = conn.prepareStatement(repairsTableQuery);
                    pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        switch (rs.getString("Month")) {
                            case "January":
                                month = RModel.Strings.JANUARY;
                                break;
                            case "February":
                                month = RModel.Strings.FEBRUARY;
                                break;
                            case "March":
                                month = RModel.Strings.MARCH;
                                break;
                            case "April":
                                month = RModel.Strings.APRIL;
                                break;
                            case "May":
                                month = RModel.Strings.MAY;
                                break;
                            case "June":
                                month = RModel.Strings.JUNE;
                                break;
                            case "July":
                                month = RModel.Strings.JULY;
                                break;
                            case "August":
                                month = RModel.Strings.AUGUST;
                                break;
                            case "September":
                                month = RModel.Strings.SEPTEMBER;
                                break;
                            case "October":
                                month = RModel.Strings.OCTOBER;
                                break;
                            case "November":
                                month = RModel.Strings.NOVEMBER;
                                break;
                            case "December":
                                month = RModel.Strings.DECEMBER;
                                break;
                            default:
                                break;
                        }
                        
                        String repairsDone = rs.getString("RepairsDone");
                        String materialCostOfRepair1 = rs.getString("MaterialCost");
                        String labourCostofRepair1 = rs.getString("LabourCost");
                        String dateOfRepair1 = rs.getString("RepairsDateTime");
                        String miscellaneous = rs.getString("MiscellaneousExpenses");
                        
                        
                        RModel repairsInfo = new RModel(month, repairsDone, materialCostOfRepair1, labourCostofRepair1, miscellaneous, dateOfRepair1);
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
                }   break;
            case "":
                System.out.println("None");
                break;
            default:
                try {
                    String selectRepairsData = "SELECT * FROM RepairDetailsTable WHERE HouseNumber = ? AND Month = ?";
                    conn = DriverManager.getConnection(databaseURL);
                    pstmt = conn.prepareStatement(selectRepairsData);
                    pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                    pstmt.setString(2, rdMonthCombo.getSelectionModel().getSelectedItem().getMonth());
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        switch (rs.getString("Month")) {
                            case "January":
                                month = RModel.Strings.JANUARY;
                                break;
                            case "February":
                                month = RModel.Strings.FEBRUARY;
                                break;
                            case "March":
                                month = RModel.Strings.MARCH;
                                break;
                            case "April":
                                month = RModel.Strings.APRIL;
                                break;
                            case "May":
                                month = RModel.Strings.MAY;
                                break;
                            case "June":
                                month = RModel.Strings.JUNE;
                                break;
                            case "July":
                                month = RModel.Strings.JULY;
                                break;
                            case "August":
                                month = RModel.Strings.AUGUST;
                                break;
                            case "September":
                                month = RModel.Strings.SEPTEMBER;
                                break;
                            case "October":
                                month = RModel.Strings.OCTOBER;
                                break;
                            case "November":
                                month = RModel.Strings.NOVEMBER;
                                break;
                            case "December":
                                month = RModel.Strings.DECEMBER;
                                break;
                            default:
                                break;
                        }
                        String repairsDone = rs.getString("RepairsDone");
                        String materialCostOfRepair1 = rs.getString("MaterialCost");
                        String labourCostofRepair1 = rs.getString("LabourCost");
                        String dateOfRepair1 = rs.getString("RepairsDateTime");
                        String miscellaneous = rs.getString("MiscellaneousExpenses");
                        
                        RModel repairsInfo = new RModel(month, repairsDone, materialCostOfRepair1, labourCostofRepair1, miscellaneous, dateOfRepair1);
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
                }   break;
        }
        return repairsData;
    }

    private <T> void setupPaymentsTableCellValueFactory(JFXTreeTableColumn<PDModel, T> column, Function<PDModel, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<PDModel, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }
    
    private <T> void setupRepairsTableCellValueFactory(JFXTreeTableColumn<RModel, T> column, Function<RModel, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<RModel, T> param) -> {
            if (column.validateValue(param)) {
               return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }
    
    private HashMap getReceiptParameters() {
        HashMap map = new HashMap();
        map.put("HouseNumber",
                blockTreeView.getSelectionModel().getSelectedItem().getValue());
        map.put("PaymentDate", getDateValueAsString(pdPaymentDate.getValue()));
        map.put("PaymentMonth", pdMonthCombo.getSelectionModel().getSelectedItem().getMonth());
        return map;
    }

    @SuppressWarnings("unchecked")
    private void editFocusedCell() {
        final TreeTablePosition<PDModel, ?> focusedCell = paymentsTable
                .focusModelProperty().get().focusedCellProperty().get();
        paymentsTable.edit(focusedCell.getRow(), focusedCell.getTableColumn());
    }

    

    private TreeTableColumn<PDModel, ?> getTableColumn(final JFXTreeTableColumn<PDModel, ?> column, int offset) {
        int columnIndex = paymentsTable.getVisibleLeafIndex(column);
        int newColumnIndex = columnIndex + offset;
        return paymentsTable.getVisibleLeafColumn(newColumnIndex);
    }

    private static <S, T> TableColumn<S, T> column(String title, Function<S, ObservableValue<T>> property) {
        TableColumn<S, T> col = new TableColumn<>(title);
        col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
        return col;
    }

    private void setupAmountColumn() {
        amountCol.setPrefWidth(90);
        amountCol.setCellValueFactory(cellData -> cellData.getValue().getValue().amountTablePDProperty());
    }


    private void setupMonthColumn() {
        monthCol.setPrefWidth(90);
        monthCol.setCellValueFactory(cellData -> cellData.getValue().getValue().monthTablePDProperty());
    }

    private void setupPaymentDateColumn() {
        dateCol.setPrefWidth(90);
        dateCol.setCellValueFactory(cellData -> cellData.getValue().getValue().paymentDateTablePDProperty());
    }

    private void setupPaymentMethodColumn() {
        DoubleBinding usedWidth = amountCol.widthProperty().add(monthCol.widthProperty()).add(dateCol.widthProperty());
        methodCol.prefWidthProperty().bind(paymentsTable.widthProperty().subtract(usedWidth));
        methodCol.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
        methodCol.setCellValueFactory(cellData -> cellData.getValue().getValue().paymentMethodPDProperty());
    }
    
    class MyRepairEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {

            if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2) {
                TableCell cell = (TableCell) t.getSource();
                int index = cell.getIndex();
                try {
                    RModel item = getRepairsDetails().get(index);
                    rdRepairsDone.setText(item.getrepairsDoneTableR());
                    rdRepairDate.setValue(LocalDate.parse(item.getdateofRepairsTableR(), DateTimeFormatter.ISO_DATE));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private TreeTableColumn<RModel, ?> getRepairTableColumn(final JFXTreeTableColumn<RModel, ?> column, int offset) {
        int columnIndex = repairsTable.getVisibleLeafIndex(column);
        int newColumnIndex = columnIndex + offset;
        return repairsTable.getVisibleLeafColumn(newColumnIndex);
    }
    
    private void setupRepairMonthColumn() {
        repairMonthCol.setPrefWidth(90);
        repairMonthCol.setCellValueFactory(cellData -> cellData.getValue().getValue().monthTableRProperty());
    }

    private void setupRepairsDoneColumn() {
        repairDone.setPrefWidth(90);
        repairDone.setCellValueFactory(cellData -> cellData.getValue().getValue().repairsDoneTableRProperty());
    }

    private void setupMaterialCostColumn() {
        materialCostOfRepair.setPrefWidth(90);
        materialCostOfRepair.setCellValueFactory(cellData -> cellData.getValue().getValue().materialCostofRepairsTableRProperty());
    }
    
    private void setupLabourCostColumn() {
        labourCostOfRepair.setPrefWidth(90);
        labourCostOfRepair.setCellValueFactory(cellData -> cellData.getValue().getValue().labourCostofRepairsTableRProperty());
    }
    
    private void setupRepairsDateColumn() {
        dateOfRepair.setPrefWidth(90);
        dateOfRepair.setCellValueFactory(cellData -> cellData.getValue().getValue().dateofRepairsTableRProperty());
    }

    private void setupMiscellaneousColumn() {
        miscExpenses.setPrefWidth(90);
        /*DoubleBinding usedWidth = houseNo.widthProperty().add(tenantName.widthProperty()).add(repairDone.widthProperty()).add(materialCostOfRepair.widthProperty()).add(dateOfRepair.widthProperty());
        miscExpenses.prefWidthProperty().bind(repairsTable.widthProperty().subtract(usedWidth));*/
        miscExpenses.setCellValueFactory(cellData -> cellData.getValue().getValue().miscellaneousTableRProperty());
    }

    public void createExcelSheet(File fileLocation, String hNo, String tName, String phoneNo, String monthlyRent, String deposit, String dueDate, String moveInDate, String moveOutDate, String leaseStartDate, String leaseEndDate) throws FileNotFoundException {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void createAndWriteExcelSheet(File fileLocation, String hNo, String tName, String amount, String monthlyRent, String paymentDate, String paymentMethod) throws FileNotFoundException {
       
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
            } catch (IOException | EncryptedDocumentException e) {
                e.printStackTrace();
            }
        }

    }

    public void createAndWriteRepairDetailsToExcelFile(File fileLocation, String houseNumber, String month, String repairsDone, String materialCost, String labourCost, String otherCosts, String repairDateTime) throws FileNotFoundException {
        File houseDataExcel = fileLocation;
        
        if (houseDataExcel.exists()) {
            try {
                FileInputStream is = new FileInputStream(houseDataExcel);
                Workbook workBookExists = WorkbookFactory.create(is);
                
                Sheet spreadSheet = workBookExists.getSheet("Repair Details");
                
                Font boldFont = workBookExists.createFont();
                boldFont.setFontName("Arial");
                boldFont.setBold(true);
                CellStyle headerRowStyle = workBookExists.createCellStyle();
                headerRowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerRowStyle.setFont(boldFont);
                
                Map<String, Object[]> repairData = new TreeMap<>();
                repairData.put("1", new Object[]{"House Number", "Month", "Repairs Done", "Material Cost", "Labour Cost", "Other Costs", "Repairs Date"});
                repairData.put("2", new Object[]{houseNumber, month, repairsDone, materialCost, labourCost, otherCosts, repairDateTime});
                
                Set<String> keySet = repairData.keySet();
                int rowNum = 0;
                
                if (spreadSheet == null) {
                    spreadSheet = workBookExists.createSheet("Repair Details");
                    for (String key : keySet) {
                        Row row = spreadSheet.createRow(rowNum++);
                        if (rowNum == 1) {
                            row.setRowStyle(headerRowStyle);
                        }
                        Object[] objArr = repairData.get(key);
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
                    for (int c = 0; c < repairData.get("1").length; c++) {
                        spreadSheet.autoSizeColumn(c); 
                    }
                } else {
                    Object[][] rData = {{houseNumber, month, repairsDone, materialCost, labourCost, otherCosts, repairDateTime}};
                    
                    int rowCount = spreadSheet.getPhysicalNumberOfRows();
                    
                    for (Object[] rBook : rData) {
                        Row row = spreadSheet.createRow(rowCount++);

                        int columnCount = 0;

                        for (Object obj : rBook) {
                            Cell cell = row.createCell(columnCount++);
                            if (obj instanceof String) {
                                cell.setCellValue((String) obj);
                            } else if (obj instanceof Integer) {
                                cell.setCellValue((Integer) obj);
                            }
                        }
                    }
                }
                is.close();
                
                FileOutputStream outputStream = new FileOutputStream(houseDataExcel);
                workBookExists.write(outputStream);
                outputStream.close();
                workBookExists.close();
                
            } catch (IOException | EncryptedDocumentException ex) {
                Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
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
            monthRentArrearsTextfield.setText("Ksh "+String.valueOf(monthlyRent));
            paidRentArrearsTextfield.setText("Ksh "+String.valueOf(paidRent));
            remainderRentArrearsTextfield.setText("Ksh "+String.valueOf(Math.subtractExact(monthlyRent, paidRent)));
            
            if (payAndTenantVbox.getChildren().contains(rentArrearsVbox)) {
                
            } else {
                payAndTenantVbox.getChildren().add(rentArrearsVbox);
            }
            
        } else if (Math.subtractExact(monthlyRent, paidRent) == 0) {
            payAndTenantVbox.getChildren().remove(rentArrearsVbox);
        }
    }

    ChangeListener houseListener = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            fetchTenantDetailsFromDatabaseToUI(tdName, pdName,tdPhone, tdAmount, tdDeposit, tdDueDate, tdMoveInDate, tdMoveOutDate, tdLeaseStartDate, tdLeaseEndDate);
            
        }
    };    

    public boolean checkIfNode(Node node) {
        while (node != null) {
            if (node.equals(pdMonthCombo) || node.equals(tdMoveInDate) || node.equals(tdMoveOutDate) || node.equals(tdLeaseStartDate) || node.equals(tdLeaseEndDate) || node.equals(rdMonthCombo) || node.equals(rdRepairDate) || node.equals(detIcon)) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOut.close();
            } catch (IOException e) {
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
                row.getCell(5).setCellValue(payMethodString);
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateRDExcelRowValue(File file) throws FileNotFoundException, IOException {
        workBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workBook.getSheet("Repair Details");
        
        fetchRepairDetailsFromDBToUI();
        
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            
            if (row.getCell(0).getStringCellValue().equals(blockTreeView.getSelectionModel().getSelectedItem().getValue()) && row.getCell(1).getStringCellValue().equals(rdMonthCombo.getValue().getMonth()) && row.getCell(2).getStringCellValue().equals(repairsDoneText) && row.getCell(6).getStringCellValue().equals(repairsDateText)) {
                row.getCell(2).setCellValue(rdRepairsDone.getText());
                row.getCell(6).setCellValue(getDateValueAsString(rdRepairDate.getValue()));
                row.getCell(3).setCellValue(materialText.getText());
                row.getCell(4).setCellValue(labourText.getText());
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void removeExcelRowValue(File file) throws IOException, InvalidFormatException {
        workBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workBook.getSheet("Tenant Data");
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeRDExcelRow(File file) throws FileNotFoundException, IOException {
        workBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workBook.getSheet("Repair Details");
        
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row.getRowNum() == sheet.getLastRowNum() && row.getCell(0).getStringCellValue().equals(blockTreeView.getSelectionModel().getSelectedItem().getValue()) && row.getCell(1).getStringCellValue().equals(rdMonthCombo.getValue().getMonth()) && row.getCell(2).getStringCellValue().equals(rdRepairsDone.getText()) && row.getCell(6).getStringCellValue().equals(getDateValueAsString(rdRepairDate.getValue()))) {
                sheet.removeRow(row);
                continue;
            }
            if (row.getCell(0).getStringCellValue().equals(blockTreeView.getSelectionModel().getSelectedItem().getValue()) && row.getCell(1).getStringCellValue().equals(rdMonthCombo.getValue().getMonth()) && row.getCell(2).getStringCellValue().equals(repairsDoneText) && row.getCell(6).getStringCellValue().equals(repairsDateText)) {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
   
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
    
    private void fetchTenantDetailsFromDatabaseToUI(TextField tdName, TextField pdName, TextField tdPhone, TextField monthlyRent, TextField tdDeposit, TextField dueDate, JFXDatePicker moveInDate, JFXDatePicker moveOutDate, JFXDatePicker leaseStartDate, JFXDatePicker leaseEndDate) {
        final FetchTenantDetailsTask fetchTdTask = new FetchTenantDetailsTask();

        prefs = Preferences.userRoot().node(this.getClass().getName());

        fetchTdTask.setOnSucceeded((event) -> {
            if (fetchTdTask.getValue().isEmpty()) {
                excelFileLocation = prefs.get(loc, "location");
                pdName.clear();
                setTDEmpty1();
                blockTreeView.getSelectionModel().getSelectedItem().getParent().setValue(blockTreeView.getSelectionModel().getSelectedItem().getValue());
                resetHouseSeletion();
                if (payAndTenantVbox.getChildren().contains(rentArrearsVbox)) {
                    payAndTenantVbox.getChildren().remove(rentArrearsVbox);
                }
                pdMonthCombo.setValue(PDModel.Strings.NONE);
            } else {
                
                houseMonthlyRent = getStringNumber(fetchTdTask.getValue().get(2));
                
                tdName.setText(fetchTdTask.getValue().get(0));
                pdName.setText(fetchTdTask.getValue().get(0));
                tdPhone.setText(fetchTdTask.getValue().get(1));
                monthlyRent.setText(fetchTdTask.getValue().get(2));
                tdDeposit.setText(fetchTdTask.getValue().get(3));
                dueDate.setText(fetchTdTask.getValue().get(4));

                if (fetchTdTask.getValue().get(5) != null) {
                    if (!regAndLeaseDatesNodesList.isExpanded()) {
                        regAndLeaseDatesNodesList.animateList();
                    }
                    regDatesButton.fire();
                    moveInButton.fire();
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

                blockTreeView.getSelectionModel().getSelectedItem().getParent().setValue(blockTreeView.getSelectionModel().getSelectedItem().getValue());
                resetHouseSeletion();
                if (payAndTenantVbox.getChildren().contains(rentArrearsVbox)) {
                    payAndTenantVbox.getChildren().remove(rentArrearsVbox);
                }
            }

        });

        MainApp.databaseExecutor.submit(fetchTdTask);
    }
    
    private void fetchPaymentDetailsFromDBToUI(TextField amount, JFXDatePicker paymentDate) {
        final FetchPaymentDetailsTask fetchPaymentDetails = new FetchPaymentDetailsTask();

        prefs = Preferences.userRoot().node(this.getClass().getName());

        fetchPaymentDetails.setOnSucceeded((event) -> {
            if (fetchPaymentDetails.getValue().isEmpty()) {
                excelFileLocation = prefs.get(loc, "location");
                amount.clear();
                paymentDate.setValue(null);
                pdCashTextfield.clear();
                pdbankTextfield.clear();
                pdMpesaTextfield.clear();

                if (selectPayOptionNode.isExpanded()) {
                    selectPayOptionNode.animateList(false);
                    if (vbox15.getChildren().contains(pdCashTextfield)) {
                        vbox15.getChildren().remove(pdCashTextfield);
                    } else if (vbox15.getChildren().contains(pdbankTextfield)) {
                        vbox15.getChildren().remove(pdbankTextfield);
                    } else if (vbox15.getChildren().contains(pdMpesaTextfield)) {
                        vbox15.getChildren().remove(pdMpesaTextfield);
                    }
                }
                
                if (payAndTenantVbox.getChildren().contains(rentArrearsVbox)) {
                    payAndTenantVbox.getChildren().remove(rentArrearsVbox);
                }
                
            } else {
                amount.clear();
                paymentDate.setValue(null);
                pdCashTextfield.clear();
                pdbankTextfield.clear();
                pdMpesaTextfield.clear();
                
                if (selectPayOptionNode.isExpanded()) {
                    selectPayOptionNode.animateList(false);
                    if (vbox15.getChildren().contains(pdCashTextfield)) {
                        vbox15.getChildren().remove(pdCashTextfield);
                    } else if (vbox15.getChildren().contains(pdbankTextfield)) {
                        vbox15.getChildren().remove(pdbankTextfield);
                    } else if (vbox15.getChildren().contains(pdMpesaTextfield)) {
                        vbox15.getChildren().remove(pdMpesaTextfield);
                    }
                }
                
                if (payAndTenantVbox.getChildren().contains(rentArrearsVbox)) {
                    payAndTenantVbox.getChildren().remove(rentArrearsVbox);
                }                
            }

        });

        MainApp.databaseExecutor.submit(fetchPaymentDetails);
    }
    
    private void fetchRepairDetailsFromDBToUI() {
        final FetchRepairDetailsTask fetchRepairDetails = new FetchRepairDetailsTask();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        prefs = Preferences.userRoot().node(this.getClass().getName());
        
        fetchRepairDetails.setOnSucceeded((event) -> {
            if (fetchRepairDetails.getValue().isEmpty()) {
                excelFileLocation = prefs.get(loc, "location");
                setRepairsEmpty();
                System.out.println("Empty Repair Details ObservableList");
            } else {
                repairsDoneText = fetchRepairDetails.getValue().get(2);
                
                repairDetailsTextArea.setText(fetchRepairDetails.getValue().get(2));
                materialText.setText(fetchRepairDetails.getValue().get(3));
                labourText.setText(fetchRepairDetails.getValue().get(4));
                
                if (fetchRepairDetails.getValue().get(6) != null) {
                    rdRepairDate.setValue(LocalDate.parse(fetchRepairDetails.getValue().get(6), formatter));
                    repairsDateText = fetchRepairDetails.getValue().get(6);
                } else {
                    rdRepairDate.setValue(null);
                }
                
                miscText.setText(fetchRepairDetails.getValue().get(5));                                
            }
        });

        MainApp.databaseExecutor.submit(fetchRepairDetails);
    }
    
    private void getRepairDetailsRowID() throws Exception {
        final CheckRepairRowIDTask checkRepairRowID = new CheckRepairRowIDTask();
        
        checkRepairRowID.setOnSucceeded((event) -> {
            repairRowId = checkRepairRowID.getValue();
        });
        
        MainApp.databaseExecutor.submit(checkRepairRowID);
    }
    
    private void checkReminderNote() throws Exception {
        final checkReminderNoteTask checkReminderNote = new checkReminderNoteTask();

        checkReminderNote.setOnSucceeded((event) -> {
            try {
                if (checkReminderNote.getValue().isEmpty()) {
                    if (paymentsTabGridPane.getChildren().contains(stickyBadge)) {
                        paymentsTabGridPane.getChildren().remove(stickyBadge);
                    }
                    stickyBadgeSize = 0;
                } else {
                    stickyBadgeSize = checkReminderNote.getValue().size();
                    
                    reminderNoteArrayList.clear();
                    dateReminderArrayList.clear();
                    
                    for (int i = 0; i < stickyBadgeSize; i++) {
                        ReminderNoteModel rmDetails = checkReminderNote.getValue().get(i);
                        reminderNoteArrayList.add(rmDetails.getReminderNote());
                        /*System.out.println(reminderNoteArrayList.get(i));*/
                        dateReminderArrayList.add(rmDetails.getDateTime());
                    }
                    
                    if (paymentsTabGridPane.getChildren().contains(stickyBadge)) {
                        stickyBadge.setText(String.valueOf(stickyBadgeSize));
                        stickyBadge.refreshBadge();
                    } else {
                        stickyBadge.setText(String.valueOf(stickyBadgeSize));
                        paymentsTabGridPane.add(stickyBadge, 1, 0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        MainApp.databaseExecutor.submit(checkReminderNote);
    }

    private void saveToTenantDetailsTable(final JFXSpinner databaseActivityIndicator) {
        final SaveTenantDetailsTask saveTenantDetails = new SaveTenantDetailsTask();
        
        if (tenantsTabGridPane.getChildren().contains(databaseActivityIndicatorTD)) {
            System.out.println("Database Activity Indicator is already in scene.");
        } else {
            tenantsTabGridPane.add(databaseActivityIndicatorTD, 1, 0);
        }       
        
        databaseActivityIndicator.visibleProperty().bind(saveTenantDetails.runningProperty());
        databaseActivityIndicator.progressProperty().bind(saveTenantDetails.progressProperty());
        
        saveTenantDetails.setOnFailed((event) -> {
            saveTenantDetails.getException().printStackTrace();
        });
        
        saveTenantDetails.setOnSucceeded((event) -> {
            if (saveTenantDetails.getValue()) {
                
                tenantsTabGridPane.getChildren().remove(databaseActivityIndicatorTD);
                tenantsTabGridPane.add(tdInsertSuccessIcon, 1, 0);
                
                FadeTransition ft = new FadeTransition(Duration.millis(600), tdInsertSuccessIcon);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setCycleCount(1);
                ft.setAutoReverse(false);
                
                Timeline tenantLayoutTimeline = new Timeline(
                        new KeyFrame(Duration.millis(700), (act) -> {
                            tenantsTabGridPane.getChildren().remove(tdInsertSuccessIcon);
                        }));
                tenantLayoutTimeline.setCycleCount(1);
                
                SequentialTransition st = new SequentialTransition(ft, tenantLayoutTimeline);
                st.play();
                
                setTDEmpty1();
            } else {
                JFXAlert insertTDErrorAlert = new JFXAlert((Stage) payAndTenantDetailsBorderPane.getScene().getWindow());
                insertTDErrorAlert.initModality(Modality.APPLICATION_MODAL);
                insertTDErrorAlert.setOverlayClose(false);
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("Database Error"));
                content.setBody(new Label("Insert into Tenant Details table failed. Try agein. "));
                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                okButton.setOnAction(act -> {
                    insertTDErrorAlert.hideWithAnimation();
                    tenantsTabGridPane.getChildren().remove(tdErrorIcon);
                });
                content.setActions(okButton);
                insertTDErrorAlert.setContent(content);
                insertTDErrorAlert.show();
                
                if (tenantsTabGridPane.getChildren().contains(databaseActivityIndicatorTD)) {
                    tenantsTabGridPane.getChildren().remove(databaseActivityIndicatorTD);
                    tenantsTabGridPane.add(tdErrorIcon, 1, 0);
                } else {
                    tenantsTabGridPane.add(tdErrorIcon, 1, 0);
                }                
            }
        });
        
        MainApp.databaseExecutor.submit(saveTenantDetails);
    }
    
    private void saveToPaymentDetailsTable(final JFXSpinner databaseActivityIndicator) {
        final savePaymentDetailsTask savePaymentDetails = new savePaymentDetailsTask();
        
        paymentsTabGridPane.add(databaseActivityIndicatorPD, 1, 0);
        
        databaseActivityIndicator.visibleProperty().bind(savePaymentDetails.runningProperty());
        databaseActivityIndicator.progressProperty().bind(savePaymentDetails.progressProperty());
        
        savePaymentDetails.setOnSucceeded((event) -> {
            if (savePaymentDetails.getValue()) {
                
                paymentsTabGridPane.getChildren().remove(databaseActivityIndicatorPD);
                paymentsTabGridPane.add(pdInsertSuccessIcon, 1, 0);
                
                FadeTransition ft = new FadeTransition(Duration.millis(600), pdInsertSuccessIcon);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setCycleCount(1);
                ft.setAutoReverse(false);
                
                Timeline payLayoutTimeline = new Timeline(
                        new KeyFrame(Duration.millis(700), (act) -> {
                            paymentsTabGridPane.getChildren().remove(pdInsertSuccessIcon);
                        }));
                payLayoutTimeline.setCycleCount(1);
                
                SequentialTransition st = new SequentialTransition(ft, payLayoutTimeline);
                st.play();
                
                pdMonthCombo.setValue(PDModel.Strings.NONE);
                pdAmount.clear();
                pdPaymentDate.setValue(null);
                
            } else {
                
                JFXAlert insertPDErrorAlert = new JFXAlert((Stage) payAndTenantDetailsBorderPane.getScene().getWindow());
                insertPDErrorAlert.initModality(Modality.APPLICATION_MODAL);
                insertPDErrorAlert.setOverlayClose(false);
                
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("Database Error"));
                content.setBody(new Label("Insert into Payment Details table failed. Try agein. "));
                
                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                okButton.setOnAction(act -> {
                    insertPDErrorAlert.hideWithAnimation();
                    paymentsTabGridPane.getChildren().remove(stickyErrorIcon);
                });
                
                content.setActions(okButton);
                insertPDErrorAlert.setContent(content);
                insertPDErrorAlert.show();
                
                paymentsTabGridPane.getChildren().remove(databaseActivityIndicatorPD);
                if (paymentsTabGridPane.getChildren().contains(stickyBadge)) {
                    paymentsTabGridPane.add(stickyErrorIcon, 2, 0);
                } else {
                    paymentsTabGridPane.add(stickyErrorIcon, 1, 0);
                }
                
            }
        });
        
        savePaymentDetails.setOnFailed((event) -> {
            System.err.println("The task failed with the following error");
        });
        
        MainApp.databaseExecutor.submit(savePaymentDetails);
    }
    
    private void saveToRepairDetailsTable(final JFXSpinner databaseActivityIndicator) {
        final saveRepairDetailsToTable saveRepairDetails = new saveRepairDetailsToTable();
        
        databaseActivityIndicator.visibleProperty().bind(saveRepairDetails.runningProperty());
        databaseActivityIndicator.progressProperty().bind(saveRepairDetails.progressProperty());
        
        saveRepairDetails.setOnSucceeded((event) -> {
            if (saveRepairDetails.getValue()) {
                repairsLayout.add(rdInsertSuccessIcon, 2, 0);
                
                FadeTransition ft = new FadeTransition(Duration.millis(600), rdInsertSuccessIcon);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setCycleCount(1);
                ft.setAutoReverse(false);
                
                Timeline repairsLayoutTimeline = new Timeline(
                        new KeyFrame(Duration.millis(700), (act) -> {
                            repairsLayout.getChildren().remove(rdInsertSuccessIcon);
                        }));
                repairsLayoutTimeline.setCycleCount(1);
                
                SequentialTransition st = new SequentialTransition(ft, repairsLayoutTimeline);
                st.play(); 
                
                rdMonthCombo.setValue(RModel.Strings.NONE);
                
                setRepairsEmpty();
            } else {
                JFXAlert insertRDErrorAlert = new JFXAlert((Stage) rdBorderPane.getScene().getWindow());
                insertRDErrorAlert.initModality(Modality.APPLICATION_MODAL);
                insertRDErrorAlert.setOverlayClose(false);
                
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("Database Error"));
                content.setBody(new Label("Insert into Repair Details table failed. Try agein. "));
                
                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                okButton.setOnAction(act -> {
                    insertRDErrorAlert.hideWithAnimation();
                    repairsLayout.getChildren().remove(rdErrorIcon);
                });
                
                content.setActions(okButton);
                insertRDErrorAlert.setContent(content);
                insertRDErrorAlert.show();
                
                repairsLayout.add(rdErrorIcon, 2, 0);
            }
            
        });
        
        MainApp.databaseExecutor.submit(saveRepairDetails);
    }
    
    private void saveToReminderNote() {
        final saveReminderNoteTask saveReminderNote = new saveReminderNoteTask();
        
        saveReminderNote.setOnFailed((event) -> {
            saveReminderNote.getException().printStackTrace();
        });
        
        saveReminderNote.setOnSucceeded((event) -> {
            System.out.println("Reminder Successfully set");
        });
        
        MainApp.databaseExecutor.submit(saveReminderNote);
    }
    
    private void updateTenantDetails(final JFXSpinner databaseActivityIndicator) {
        final UpdateTenantDetailsTask updateTenantDetails = new UpdateTenantDetailsTask();

        if (tenantsTabGridPane.getChildren().contains(databaseActivityIndicatorTD)) {
            System.out.println("Database Activity Indicator already in scene.");
        } else {
            tenantsTabGridPane.add(databaseActivityIndicatorTD, 1, 0);
        }
        
        databaseActivityIndicator.visibleProperty().bind(updateTenantDetails.runningProperty());
        databaseActivityIndicator.progressProperty().bind(updateTenantDetails.progressProperty());

        updateTenantDetails.setOnSucceeded((event) -> {
            setTDEmpty1();
        });
        
        MainApp.databaseExecutor.submit(updateTenantDetails);
    }
    
    private void updateReminderNote() {
        final UpdateReminderNoteTask updateReminderNote = new UpdateReminderNoteTask();
        
        updateReminderNote.setOnSucceeded((event) -> {
            System.out.println("Updated ReminderNoteTable successfully");
        });
        
        MainApp.databaseExecutor.submit(updateReminderNote);
    }
    
    private void updatePaymentDetails(final JFXSpinner databaseActivityIndicator) {
        final UpdatePaymentDetailsTask updatePaymentDetails = new UpdatePaymentDetailsTask();
        
        if (paymentsTabGridPane.getChildren().contains(stickyBadge)) {
            paymentsTabGridPane.getChildren().remove(stickyBadge);
            paymentsTabGridPane.add(databaseActivityIndicatorPD, 1, 0);
        } else {
            paymentsTabGridPane.add(databaseActivityIndicatorPD, 1, 0);
        }
        
        databaseActivityIndicator.visibleProperty().bind(updatePaymentDetails.runningProperty());
        databaseActivityIndicator.progressProperty().bind(updatePaymentDetails.progressProperty());
        
        updatePaymentDetails.setOnSucceeded((event) -> {
            pdMonthCombo.setValue(PDModel.Strings.NONE);
            pdAmount.clear();
            pdPaymentDate.setValue(null);
            paymentsTabGridPane.getChildren().remove(databaseActivityIndicatorPD);
        });
        
        MainApp.databaseExecutor.submit(updatePaymentDetails);
    }
    
    private void updateRepairsDetailsTable(final JFXSpinner databaseActivityIndicator) {
        final UpdateRepairDetailsTableTask updateRepairDetailsTable = new UpdateRepairDetailsTableTask();
        
        databaseActivityIndicator.visibleProperty().bind(updateRepairDetailsTable.runningProperty());
        databaseActivityIndicator.progressProperty().bind(updateRepairDetailsTable.progressProperty());
        
        updateRepairDetailsTable.setOnSucceeded((event) -> {
            if (updateRepairDetailsTable.getValue()) {
                repairsLayout.add(rdInsertSuccessIcon, 2, 0);

                FadeTransition ft = new FadeTransition(Duration.millis(600), rdInsertSuccessIcon);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setCycleCount(1);
                ft.setAutoReverse(false);

                Timeline repairsLayoutTimeline = new Timeline(
                        new KeyFrame(Duration.millis(700), (act) -> {
                            repairsLayout.getChildren().remove(rdInsertSuccessIcon);
                        }));
                repairsLayoutTimeline.setCycleCount(1);

                SequentialTransition st = new SequentialTransition(ft, repairsLayoutTimeline);
                st.play();
            } else {
                JFXAlert updateRDErrorAlert = new JFXAlert((Stage) rdBorderPane.getScene().getWindow());
                updateRDErrorAlert.initModality(Modality.APPLICATION_MODAL);
                updateRDErrorAlert.setOverlayClose(false);

                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("Database Error"));
                content.setBody(new Label("Updating Repair Details table failed. Try agein. "));

                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                okButton.setOnAction(act -> {
                    updateRDErrorAlert.hideWithAnimation();
                    repairsLayout.getChildren().remove(rdErrorIcon);
                });

                content.setActions(okButton);
                updateRDErrorAlert.setContent(content);
                updateRDErrorAlert.show();

                repairsLayout.add(rdErrorIcon, 2, 0);
            }
        });

        MainApp.databaseExecutor.submit(updateRepairDetailsTable);
    }
    
    private void deleteFromTenantDetails(final JFXSpinner databaseActivityIndicator) {
        final DeleteFromTenantDetailsTask deleteFromTenantDetails = new DeleteFromTenantDetailsTask();
        
        if (tenantsTabGridPane.getChildren().contains(databaseActivityIndicatorTD)) {
            System.out.println("Database Activity Indicator alredy in scene.");
        } else {
            tenantsTabGridPane.add(databaseActivityIndicatorTD, 1, 0);
        }
        
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
        });
        
        MainApp.databaseExecutor.submit(deleteFromPaymentDetails);
    }
    
    private void deleteFromReminderNoteTable() {
        final DeleteFromReminderNoteTableTask deleteReminderNote = new DeleteFromReminderNoteTableTask();
        
        deleteReminderNote.setOnSucceeded((event) -> {
            System.out.println("Deleted from ReminderNoteTable");
        });
        
        MainApp.databaseExecutor.submit(deleteReminderNote);
    }
    
    private void deleteFromRepairDetailsTable(JFXSpinner databaseActivityIndicator) {
        final DeleteFromRepairDetailsTask deleteRepairDetails = new DeleteFromRepairDetailsTask();
        
        databaseActivityIndicator.visibleProperty().bind(deleteRepairDetails.runningProperty());
        databaseActivityIndicator.progressProperty().bind(deleteRepairDetails.progressProperty());
        
        deleteRepairDetails.setOnSucceeded((event) -> {
            System.out.println("Entry successfully deleted");
            if (deleteRepairDetails.getValue()) {
                repairsLayout.add(rdInsertSuccessIcon, 2, 0);

                FadeTransition ft = new FadeTransition(Duration.millis(600), rdInsertSuccessIcon);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setCycleCount(1);
                ft.setAutoReverse(false);

                Timeline repairsLayoutTimeline = new Timeline(
                        new KeyFrame(Duration.millis(700), (act) -> {
                            repairsLayout.getChildren().remove(rdInsertSuccessIcon);
                        }));
                repairsLayoutTimeline.setCycleCount(1);

                SequentialTransition st = new SequentialTransition(ft, repairsLayoutTimeline);
                st.play();
                
                rdMonthCombo.setValue(RModel.Strings.NONE);
            } else {
                JFXAlert deleteRDErrorAlert = new JFXAlert((Stage) rdBorderPane.getScene().getWindow());
                deleteRDErrorAlert.initModality(Modality.APPLICATION_MODAL);
                deleteRDErrorAlert.setOverlayClose(false);

                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("Database Error"));
                content.setBody(new Label("Updating Repair Details table failed."));

                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                okButton.setOnAction(act -> {
                    deleteRDErrorAlert.hideWithAnimation();
                    repairsLayout.getChildren().remove(rdErrorIcon);
                });

                content.setActions(okButton);
                deleteRDErrorAlert.setContent(content);
                deleteRDErrorAlert.show();

                repairsLayout.add(rdErrorIcon, 2, 0);
            }
        });
        
        MainApp.databaseExecutor.submit(deleteRepairDetails);
    }
    
    private void setupPaymentsTable() {
        setupPaymentsTableCellValueFactory(monthCol, PDModel::monthTablePDProperty);
        setupPaymentsTableCellValueFactory(amountCol, PDModel::amountTablePDProperty);
        setupPaymentsTableCellValueFactory(dateCol, PDModel::paymentDateTablePDProperty);
        setupPaymentsTableCellValueFactory(methodCol, PDModel::paymentMethodPDProperty);   
    }
    
    private void setupRepairsTable() {
        setupRepairsTableCellValueFactory(repairMonthCol, RModel::monthTableRProperty);
        setupRepairsTableCellValueFactory(repairDone, RModel::repairsDoneTableRProperty);
        setupRepairsTableCellValueFactory(materialCostOfRepair, RModel::materialCostofRepairsTableRProperty);
        setupRepairsTableCellValueFactory(labourCostOfRepair, RModel::labourCostofRepairsTableRProperty);
        setupRepairsTableCellValueFactory(miscExpenses, RModel::miscellaneousTableRProperty);
        setupRepairsTableCellValueFactory(dateOfRepair, RModel::dateofRepairsTableRProperty);
    }
    
    public void createStickyDialog(String houseNumber, StackPane pane) {
        textAreaArrayList.clear();
        textAreaList.clear();
        
        JFXListView<BorderPane> list = new JFXListView<>();
        Rectangle rec = new Rectangle(60, 40);
        
        stickyArea.setPrefWidth(10);
        stickyArea.setPrefHeight(60);
        stickyArea.setLabelFloat(true);
        stickyArea.setFont(javafx.scene.text.Font.font("Roboto", FontPosture.ITALIC, 15));
        stickyArea.textProperty().addListener((observable, oldValue, newValue) -> {
            stickyArea.setFont(javafx.scene.text.Font.font("Roboto", FontPosture.REGULAR, 15));
            if (newValue.isEmpty() || oldValue.isEmpty()) {
                stickyArea.setFont(javafx.scene.text.Font.font("Roboto", FontPosture.ITALIC, 15));
            }
        });
        stickyArea.setPromptText("Reminder Note");
        
        BorderPane defaultPane = new BorderPane(stickyArea);
        
        FontAwesomeIconView deleteRemIcon = new FontAwesomeIconView();
        
                
        if (stickyBadgeSize == 0) {
            
            textAreaList.add(defaultPane);
        } else {

            for (int i = 1; i <= stickyBadgeSize; i++) {

                JFXTextArea reminderNoteArea = new JFXTextArea();

                BorderPane stickyPane = new BorderPane(reminderNoteArea);

                HBox dateLabelHbox = new HBox();

                Label label = new Label();
                label.setFont(javafx.scene.text.Font.font("Roboto", FontWeight.BOLD, 16));
                label.setStyle("-fx-font-color: grey");
                Region hboxFiller = new Region();
                hboxFiller.setPrefWidth(60);
                HBox.setHgrow(hboxFiller, Priority.ALWAYS);

                deleteRemIcon = new FontAwesomeIconView();
                deleteRemIcon.setGlyphSize(18);
                deleteRemIcon.setIcon(FontAwesomeIcon.TRASH);
                deleteRemIcon.setVisible(false);
                dateLabelHbox.getChildren().addAll(label, hboxFiller, deleteRemIcon);

                stickyPane.setTop(dateLabelHbox);

                rec.setArcWidth(40.0);
                rec.setArcHeight(40.0);

                reminderNoteArea.setShape(rec);
                reminderNoteArea.setPrefHeight(40);
                reminderNoteArea.setOpacity(0.3);

                textAreaArrayList.add(stickyPane);
                textAreaList.add(stickyPane);

            }
            
            
            for (int j = 0; j < reminderNoteArrayList.size(); j++) {
                JFXTextArea tArea = (JFXTextArea) textAreaArrayList.get(j).getCenter();
                tArea.setText(reminderNoteArrayList.get(j));
                tArea.setPrefWidth(10);

                HBox hbox = (HBox) textAreaArrayList.get(j).getTop();
                Label label = (Label) hbox.getChildren().get(0);

                FontAwesomeIconView tempDeleteIcon = (FontAwesomeIconView) hbox.getChildren().get(2);

                label.setText(dateReminderArrayList.get(j));

                int clickedElement = j;

                tArea.setOnMouseClicked((event) -> {
                    System.out.println("TextArea clicked");
                    JFXTextArea textArea = (JFXTextArea) event.getSource();
                    textArea.setOpacity(1);

                    ScaleTransition st = new ScaleTransition();
                    st.setNode(textArea);
                    st.setDuration(Duration.millis(300));
                    st.setByX(0.03);
                    st.setByY(0.03);
                    st.setCycleCount(1);

                    StrokeTransition strokeTransition = new StrokeTransition();
                    Rectangle rec1 = (Rectangle) textArea.getShape();
                    strokeTransition.setShape(rec1);
                    strokeTransition.setDuration(Duration.millis(300));
                    strokeTransition.setToValue(Color.AQUAMARINE);
                    strokeTransition.setCycleCount(Timeline.INDEFINITE);

                    ParallelTransition parT = new ParallelTransition(textArea, st);
                    parT.play();

                    tempDeleteIcon.setVisible(true);

                    tempDeleteIcon.setOnMouseClicked(act -> {
                        System.out.println(tArea.getText());
                        for (int i = 0; i < textAreaList.size(); i++) {
                            if (textAreaList.get(i).getCenter().equals(tArea)) {
                                try {
                                    textAreaList.remove(i);
                                    deleteFromReminderNoteTable();
                                    stickyBadgeSize--;
                                    stickyBadge.setText(String.valueOf(stickyBadgeSize));
                                    checkReminderNote();
                                    if (stickyBadgeSize == 0) {
                                        stickyDialog.close();
                                    }
                                } catch (Exception ex) {
                                    Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    });
                    
                    if (tempTextAreaArray.isEmpty()) {
                        System.out.println("No previous textfield clicked");
                    } else if (tempTextAreaArray.get(0).equals(tArea)) {
                        System.out.println("Same text area");
                    } else {
                        JFXTextArea jfxTextArea = tempTextAreaArray.get(0);
                        ScaleTransition st2 = new ScaleTransition();
                        st2.setNode(jfxTextArea);
                        double x = st2.getByX();
                        double y = st2.getByY();
                        st2.setByX(-0.03);
                        st2.setByY(-0.03);
                        st2.play();

                        System.out.println(jfxTextArea.boundsInParentProperty().get());
                        
                        tempDeleteIconArray.get(0).setVisible(false);

                        jfxTextArea.setOpacity(0.3);
                    }
                    tempTextAreaArray.clear();
                    tempDeleteIconArray.clear();
                    dateReminderArrayList2.clear();
                    tempTextAreaArray.add(tArea);
                    dateReminderArrayList2.add(label.getText());
                    tempDeleteIconArray.add(tempDeleteIcon);
                });
            }
            stickyArea.clear();
            textAreaList.add(defaultPane);
        }
        
        stickyArea.setOnMouseClicked((event) -> {
            if (tempTextAreaArray.isEmpty()) {
                System.out.println("No previous textfield clicked");
            } else {
                JFXTextArea jfxTextArea = tempTextAreaArray.get(0);

                ScaleTransition st2 = new ScaleTransition();
                st2.setNode(jfxTextArea);
                double x = st2.getByX();
                double y = st2.getByY();
                st2.setByX(-0.03);
                st2.setByY(-0.03);
                st2.play();
                
                tempDeleteIconArray.get(0).setVisible(false);
                
                tempTextAreaArray.clear();
                tempDeleteIconArray.clear();
                dateReminderArrayList2.clear();
                
                jfxTextArea.setOpacity(0.3);
            }
        });
        
        saveStickyButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        saveStickyButton.setOnMouseClicked(act -> {
            try {
                if (tempTextAreaArray.isEmpty()) {
                    saveToReminderNote();

                    if (act.getButton() == MouseButton.PRIMARY) {
                        stickyBadgeSize++;
                    }

                    checkReminderNote();
                    if (!paymentsTabGridPane.getChildren().contains(stickyBadge)) {
                        paymentsTabGridPane.add(stickyBadge, 1, 0);
                    }

                    stickyDialog.close();
                    System.out.println("New entry saved");
                } else {
                    updateReminderNote();
                    checkReminderNote();
                    stickyDialog.close();
                    System.out.println("Entry updated");
                }
                
            } catch (Exception ex) {
                Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        StackPane stickyContainer = new StackPane();

        JFXDialogLayout content = new JFXDialogLayout();
        
        Label stickyHouseLabel = new Label();
        stickyHouseLabel.setFont(javafx.scene.text.Font.font("Roboto", FontWeight.BOLD, 17));
        stickyHouseLabel.setText(houseNumber);

        content.setHeading(stickyHouseLabel);
        
        list.setPrefWidth(30);
        list.prefHeightProperty().bind(Bindings.size(textAreaList).multiply(80));
        /*list.depthProperty().set(1);*/
        list.setVerticalGap(10.0);
        list.setItems(textAreaList);
        list.setExpanded(true);
        content.setBody(list);

        content.setActions(saveStickyButton);

        stickyDialog.setDialogContainer(stickyContainer);
        stickyDialog.setContent(content);
        stickyDialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        stickyDialog.setOnDialogClosed((event) -> {
            tempTextAreaArray.clear();
        });
        
        stickyDialog.show(pane);
    }
    
    private Predicate<PDModel> createPredicate(String searchText) {
        return pdmodel -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchPaymentsTable(pdmodel, searchText);
        };
    }
    
    private boolean searchPaymentsTable(PDModel pdmodel, String searchText) {
        return (pdmodel.getamountTablePD().toLowerCase().contains(searchText) ||
                pdmodel.getmonthTablePD().getMonth().toLowerCase().contains(searchText) ||
                pdmodel.getpaymentDateTablePD().contains(searchText) || 
                pdmodel.getpaymentMethodPD().toLowerCase().contains(searchText));
    }
    
    private Predicate<RModel> createRepairsTablePredicate(String searchText) {
        return rdmodel -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchRepairsTable(rdmodel, searchText);
        };
    }
    
    private boolean searchRepairsTable(RModel rmodel, String searchText) {
        return (rmodel.getMonthTableR().getMonth().toLowerCase().contains(searchText) ||
                rmodel.getrepairsDoneTableR().toLowerCase().contains(searchText) ||
                rmodel.getMaterialCostofRepairsTableR().toLowerCase().contains(searchText) ||
                rmodel.getLabourCostofRepairsTableR().toLowerCase().contains(searchText) ||
                rmodel.getdateofRepairsTableR().toLowerCase().contains(searchText));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        selectionPane.maxWidthProperty().bind(sp2.widthProperty().multiply(0.12));
        
        pdUpdate.disableProperty().bind(pdMonthCombo.valueProperty().isEqualTo(PDModel.Strings.NONE));
        pdDelete.disableProperty().bind(pdMonthCombo.valueProperty().isEqualTo(PDModel.Strings.NONE));
        pdSave.disableProperty().bind(pdMonthCombo.valueProperty().isEqualTo(PDModel.Strings.NONE));
        pdStickyNote.disableProperty().bind(pdMonthCombo.valueProperty().isEqualTo(PDModel.Strings.NONE));
        pdPrintReceipt.disableProperty().bind(pdMonthCombo.valueProperty().isEqualTo(PDModel.Strings.NONE));
        rdNewRecord.disableProperty().bind(rdMonthCombo.valueProperty().isEqualTo(RModel.Strings.NONE));
        rdUpdate.disableProperty().bind(rdMonthCombo.valueProperty().isEqualTo(RModel.Strings.NONE));
        rdDelete.disableProperty().bind(rdMonthCombo.valueProperty().isEqualTo(RModel.Strings.NONE));
        rdSave.disableProperty().bind(rdMonthCombo.valueProperty().isEqualTo(RModel.Strings.NONE));
        
        databaseActivityIndicatorTD.setVisible(false);
        databaseActivityIndicatorTD.setRadius(6);
        
        databaseActivityIndicatorPD.setVisible(false);
        databaseActivityIndicatorPD.setRadius(6);
        
        databaseActivityIndicatorRD.setVisible(false);
        databaseActivityIndicatorRD.setRadius(8);
        
        tdErrorIcon.setGlyphSize(22);
        tdErrorIcon.setIcon(FontAwesomeIcon.EXCLAMATION_CIRCLE);
        
        stickyErrorIcon.setGlyphSize(22);
        stickyErrorIcon.setIcon(FontAwesomeIcon.EXCLAMATION_CIRCLE);
        
        tdInsertSuccessIcon.setGlyphSize(22);
        tdInsertSuccessIcon.setIcon(FontAwesomeIcon.CHECK);
        
        pdInsertSuccessIcon.setGlyphSize(22);
        pdInsertSuccessIcon.setIcon(FontAwesomeIcon.CHECK);
        
        rdInsertSuccessIcon.setGlyphSize(22);
        rdInsertSuccessIcon.setIcon(FontAwesomeIcon.CHECK);
        
        rdErrorIcon.setGlyphSize(22);
        rdErrorIcon.setIcon(FontAwesomeIcon.EXCLAMATION_CIRCLE);
        
        //Start of Date Picker Nodes List
        l6.setFont(javafx.scene.text.Font.font("roboto", 14));
        tdMoveInDate.getEditor().setFont(javafx.scene.text.Font.font("roboto", FontPosture.ITALIC, 14));
        tdMoveInDate.setPromptText("Move-In-Date");
        
        l7.setFont(javafx.scene.text.Font.font("roboto", 14));
        tdMoveOutDate.getEditor().setFont(javafx.scene.text.Font.font("roboto", FontPosture.ITALIC, 14));
        tdMoveOutDate.setPromptText("Move-Out-Date");
        
        l8.setFont(javafx.scene.text.Font.font("roboto", 14));
        tdLeaseStartDate.getEditor().setFont(javafx.scene.text.Font.font("roboto", FontPosture.ITALIC, 14));
        tdLeaseStartDate.setPromptText("Lease-Start-Date");
        
        l9.setFont(javafx.scene.text.Font.font("roboto", 16));
        tdLeaseEndDate.getEditor().setFont(javafx.scene.text.Font.font("roboto", FontPosture.ITALIC, 14));
        tdLeaseEndDate.setPromptText("Lease-End-Date");
        
        l21.setAlignment(Pos.CENTER_RIGHT);
        
        l14.getStyleClass().add(ANIMATED_HEADER_BUTTON);
        cashButton.setGraphic(cashLabel);
        bankButton.setGraphic(bankLabel);
        mpesaButton.setGraphic(mpesaLabel);
        regDatesButton.setGraphic(datesLabel);
        leaseDatesButton.setGraphic(leaseLabel);
        moveInButton.setGraphic(moveInLabel);
        moveOutButton.setGraphic(moveOutLabel);
        leaseStartButton.setGraphic(leaseStartLabel);
        leaseEndButton.setGraphic(leaseEndLabel);
        
        paymentOptionsHeaderButton.setId("PB");
        regAndLeaseDatesButton.setId("PB");
        regDatesButton.setId("TB");
        leaseDatesButton.setId("TB");
        moveInButton.setId("TBS");
        moveOutButton.setId("TBS");
        leaseStartButton.setId("TBS");
        leaseEndButton.setId("TBS");
        
        l14.setPadding(new Insets(4, 0, 0, 0));
        l21.setPadding(new Insets(3, 0, 0, 0));
        
        paymentOptionButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        paymentOptionButton.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.ANGLE_DOUBLE_RIGHT, "20px"));
        paymentOptionButton.setOnAction((event) -> {
            Scene scene = paymentOptionButton.getScene();
            pdStackPane.translateXProperty().set(-1 * scene.getWidth());
            
            
            Timeline timelinePayOptionScene = new Timeline();
            KeyValue kv = new KeyValue(pdStackPane.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(300), kv);
            timelinePayOptionScene.getKeyFrames().add(kf);
            timelinePayOptionScene.play();
        });
        
        repairCostSceneButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        repairCostSceneButton.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.ANGLE_DOUBLE_RIGHT, "20px"));
        repairCostSceneButton.setOnAction((event) -> {
            Scene scene = repairCostSceneButton.getScene();
            rdStackPane.translateXProperty().set(-1 * scene.getWidth());
            
            
            Timeline repairCostsTimeline = new Timeline();
            KeyValue kv = new KeyValue(rdStackPane.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(300), kv);
            repairCostsTimeline.getKeyFrames().add(kf);
            repairCostsTimeline.play();
        });
        
        cashButton.setToggleGroup(payOptionToggleGroup);
        bankButton.setToggleGroup(payOptionToggleGroup);
        mpesaButton.setToggleGroup(payOptionToggleGroup);
        cashButton.setOnAction((event) -> {
            if (vbox15.getChildren().contains(pdCashTextfield)) {
                vbox15.getChildren().remove(pdCashTextfield);
            } else if (vbox15.getChildren().contains(pdbankTextfield)) {
                vbox15.getChildren().remove(pdbankTextfield);
                vbox15.getChildren().add(pdCashTextfield);
            } else if (vbox15.getChildren().contains(pdMpesaTextfield)) {
                vbox15.getChildren().remove(pdMpesaTextfield);
                vbox15.getChildren().add(pdCashTextfield);
            } else {
                vbox15.getChildren().add(pdCashTextfield);
            }
        });
        
        bankButton.setOnAction((event) -> {
            if (vbox15.getChildren().contains(pdbankTextfield)) {
                vbox15.getChildren().remove(pdbankTextfield);
            } else if (vbox15.getChildren().contains(pdCashTextfield)) {
                vbox15.getChildren().remove(pdCashTextfield);
                vbox15.getChildren().add(pdbankTextfield);
            } else if (vbox15.getChildren().contains(pdMpesaTextfield)) {
                vbox15.getChildren().remove(pdMpesaTextfield);
                vbox15.getChildren().add(pdbankTextfield);
            } else {
                vbox15.getChildren().add(pdbankTextfield);
            }
        });
        
        mpesaButton.setOnAction((event) -> {
            if (vbox15.getChildren().contains(pdMpesaTextfield)) {
                vbox15.getChildren().remove(pdMpesaTextfield);
            } else if (vbox15.getChildren().contains(pdbankTextfield)) {
                vbox15.getChildren().remove(pdbankTextfield);
                vbox15.getChildren().add(pdMpesaTextfield);
            } else if (vbox15.getChildren().contains(pdCashTextfield)) {
                vbox15.getChildren().remove(pdCashTextfield);
                vbox15.getChildren().add(pdMpesaTextfield);
            } else {
                vbox15.getChildren().add(pdMpesaTextfield);
            }
        });
        
        ButtonBar.setButtonData(cashButton, ButtonBar.ButtonData.LEFT);
        ButtonBar.setButtonData(bankButton, ButtonBar.ButtonData.LEFT);
        ButtonBar.setButtonData(mpesaButton, ButtonBar.ButtonData.LEFT);
        paymentNodesButtonBar.getButtons().addAll(cashButton, bankButton, mpesaButton);
       
        selectPayOptionNode.setAlignment(Pos.BOTTOM_LEFT);
        selectPayOptionNode.setSpacing(10);
        selectPayOptionNode.addAnimatedNode(paymentOptionsHeaderButton);
        selectPayOptionNode.addAnimatedNode(paymentNodesButtonBar);
        paymentOptionsHeaderButton.setOnMouseClicked((event) -> {
            if (vbox15.getChildren().contains(pdCashTextfield)) {
                vbox15.getChildren().remove(pdCashTextfield);
            } else if (vbox15.getChildren().contains(pdbankTextfield)) {
                vbox15.getChildren().remove(pdbankTextfield);
            } else if (vbox15.getChildren().contains(pdMpesaTextfield)) {
                vbox15.getChildren().remove(pdMpesaTextfield);
            }
        });
        
        regAndLeaseDatesNodesList.setAlignment(Pos.BOTTOM_LEFT);
        regAndLeaseDatesNodesList.setSpacing(5);
        regAndLeaseDatesNodesList.addAnimatedNode(regAndLeaseDatesButton);
        regAndLeaseDatesNodesList.addAnimatedNode(regAndLeaseDatesButtonBar);
        ButtonBar.setButtonData(regDatesButton, ButtonBar.ButtonData.LEFT);
        ButtonBar.setButtonData(leaseDatesButton, ButtonBar.ButtonData.RIGHT);
        regAndLeaseDatesButtonBar.getButtons().addAll(regDatesButton, leaseDatesButton);
        ButtonBar.setButtonData(moveInButton, ButtonBar.ButtonData.LEFT);
        ButtonBar.setButtonData(moveOutButton, ButtonBar.ButtonData.RIGHT);
        regDatesButtonBar.getButtons().addAll(moveInButton, moveOutButton);
        ButtonBar.setButtonData(leaseStartButton, ButtonBar.ButtonData.LEFT);
        ButtonBar.setButtonData(leaseEndButton, ButtonBar.ButtonData.RIGHT);
        leaseDatesButtonBar.getButtons().addAll(leaseStartButton, leaseEndButton);
        
        vbox18.setStyle("-fx-border-color: #CCCCCC");
        vbox18.setPadding(new Insets(2));
        regDatesButton.setToggleGroup(regLeaseToggleGroup);
        leaseDatesButton.setToggleGroup(regLeaseToggleGroup);
        moveInButton.setToggleGroup(regDatesToggleGroup);
        moveOutButton.setToggleGroup(regDatesToggleGroup);
        leaseStartButton.setToggleGroup(leaseDatesToggleGroup);
        leaseEndButton.setToggleGroup(leaseDatesToggleGroup);
        regDatesButton.setOnAction((event) -> {
            if (vbox17.getChildren().contains(vbox18)) {
                if (vbox18.getChildren().contains(regDatesButtonBar)) {
                    if (vbox18.getChildren().contains(tdMoveInDate)) {
                        vbox18.getChildren().remove(tdMoveInDate);
                        System.out.println("1");
                    } else if (vbox18.getChildren().contains(tdMoveOutDate)) {
                        vbox18.getChildren().remove(tdMoveOutDate);
                        vbox18.getChildren().remove(regDatesButtonBar);
                    } else {
                        vbox18.getChildren().remove(regDatesButtonBar);
                    } 
                    System.out.println("ljsjhhsd");
                } else if (vbox18.getChildren().contains(leaseDatesButtonBar)){
                    if (vbox18.getChildren().contains(tdLeaseStartDate)) {
                        vbox18.getChildren().remove(tdLeaseStartDate);
                        vbox18.getChildren().remove(leaseDatesButtonBar);
                        vbox18.getChildren().add(regDatesButtonBar);
                    } else if (vbox18.getChildren().contains(tdLeaseEndDate)) {
                        vbox18.getChildren().remove(tdLeaseEndDate);
                        vbox18.getChildren().remove(leaseDatesButtonBar);
                        vbox18.getChildren().add(regDatesButtonBar);
                    } else {
                        vbox18.getChildren().remove(leaseDatesButtonBar);
                        vbox18.getChildren().add(regDatesButtonBar);
                    }
                    System.out.println("jljldkjflskj");
                } else {
                    vbox18.getChildren().add(regDatesButtonBar);
                    System.out.println("dsfsfsfsds");
                }
            } else {
                vbox18.getChildren().addAll(regDatesButtonBar);
                vbox17.getChildren().add(vbox18);
                System.out.println("khskjhfjdhfksj");
            }
        });
        
        leaseDatesButton.setOnAction((event) -> {
            if (vbox17.getChildren().contains(vbox18)) {
                if (vbox18.getChildren().contains(leaseDatesButtonBar)) {
                    if (vbox18.getChildren().remove(tdLeaseStartDate)) {
                        vbox18.getChildren().remove(tdLeaseStartDate);
                        vbox18.getChildren().remove(leaseDatesButtonBar);
                    } else if (vbox18.getChildren().contains(tdLeaseEndDate)) {
                        vbox18.getChildren().remove(tdLeaseEndDate);
                        vbox18.getChildren().remove(leaseDatesButtonBar);
                    } else {
                        vbox18.getChildren().remove(leaseDatesButtonBar);
                    }                    
                } else if (vbox18.getChildren().contains(regDatesButtonBar)) {
                    if (vbox18.getChildren().contains(tdMoveInDate)) {
                        vbox18.getChildren().remove(tdMoveInDate);
                        vbox18.getChildren().remove(regDatesButtonBar);
                        vbox18.getChildren().add(leaseDatesButtonBar);
                    } else if (vbox18.getChildren().contains(tdMoveOutDate)) {
                        vbox18.getChildren().remove(tdMoveOutDate);
                        vbox18.getChildren().remove(regDatesButtonBar);
                        vbox18.getChildren().add(leaseDatesButtonBar);
                    } else {
                        vbox18.getChildren().remove(regDatesButtonBar);
                        vbox18.getChildren().add(leaseDatesButtonBar);
                    }
                } else {
                    vbox18.getChildren().add(leaseDatesButtonBar);
                }
            } else {
                vbox18.getChildren().addAll(leaseDatesButtonBar);
                vbox17.getChildren().add(vbox18);
            }
        });
        
        moveInButton.setOnAction((event) -> {
            if (vbox18.getChildren().contains(tdMoveInDate)) {
                vbox18.getChildren().remove(tdMoveInDate);
            } else if (vbox18.getChildren().contains(tdMoveOutDate)) {
                vbox18.getChildren().remove(tdMoveOutDate);
                vbox18.getChildren().add(tdMoveInDate);
            } else if (vbox18.getChildren().contains(tdLeaseStartDate)) {
                vbox18.getChildren().remove(tdLeaseStartDate);
                vbox18.getChildren().add(tdMoveInDate);
            } else if (vbox18.getChildren().contains(tdLeaseEndDate)) {
                vbox18.getChildren().remove(tdLeaseEndDate);
                vbox18.getChildren().add(tdMoveInDate);
            } else {
                vbox18.getChildren().add(tdMoveInDate);
            }
        });
        
        moveOutButton.setOnAction((event) -> {
            if (vbox18.getChildren().contains(tdMoveOutDate)) {
                vbox18.getChildren().remove(tdMoveOutDate);
            } else if (vbox18.getChildren().contains(tdMoveInDate)) {
                vbox18.getChildren().remove(tdMoveInDate);
                vbox18.getChildren().add(tdMoveOutDate);
            } else if (vbox18.getChildren().contains(tdLeaseStartDate)) {
                vbox18.getChildren().remove(tdLeaseStartDate);
                vbox18.getChildren().add(tdMoveOutDate);
            } else if (vbox18.getChildren().contains(tdLeaseEndDate)) {
                vbox18.getChildren().remove(tdLeaseEndDate);
                vbox18.getChildren().add(tdMoveOutDate);
            } else {
                vbox18.getChildren().add(tdMoveOutDate);
            } 
        });
        
        leaseStartButton.setOnAction((event) -> {
            if (vbox18.getChildren().contains(tdLeaseStartDate)) {
                vbox18.getChildren().remove(tdLeaseStartDate);
            } else if (vbox18.getChildren().contains(tdMoveInDate)) {
                vbox18.getChildren().remove(tdMoveInDate);
                vbox18.getChildren().add(tdLeaseStartDate);
            } else if (vbox18.getChildren().contains(tdLeaseStartDate)) {
                vbox18.getChildren().remove(tdLeaseStartDate);
                vbox18.getChildren().add(tdLeaseStartDate);
            } else if (vbox18.getChildren().contains(tdLeaseEndDate)) {
                vbox18.getChildren().remove(tdLeaseEndDate);
                vbox18.getChildren().add(tdLeaseStartDate);
            } else {
                vbox18.getChildren().add(tdLeaseStartDate);
            } 
        });
        
        leaseEndButton.setOnAction((event) -> {
            if (vbox18.getChildren().contains(tdLeaseEndDate)) {
                vbox18.getChildren().remove(tdLeaseEndDate);
            } else if (vbox18.getChildren().contains(tdMoveInDate)) {
                vbox18.getChildren().remove(tdMoveInDate);
                vbox18.getChildren().add(tdLeaseEndDate);
            } else if (vbox18.getChildren().contains(tdLeaseStartDate)) {
                vbox18.getChildren().remove(tdLeaseStartDate);
                vbox18.getChildren().add(tdLeaseEndDate);
            } else if (vbox18.getChildren().contains(tdLeaseEndDate)) {
                vbox18.getChildren().remove(tdLeaseEndDate);
                vbox18.getChildren().add(tdLeaseEndDate);
            } else {
                vbox18.getChildren().add(tdLeaseEndDate);
            }
        });
        
        regAndLeaseDatesButton.setOnAction((event) -> {
            if (vbox18.getChildren().contains(regDatesButtonBar)) {
                if (vbox18.getChildren().contains(tdMoveInDate)) {
                    vbox18.getChildren().remove(tdMoveInDate);
                    vbox18.getChildren().remove(regDatesButtonBar);
                } else if (vbox18.getChildren().contains(tdMoveOutDate)) {
                    vbox18.getChildren().remove(tdMoveOutDate);
                    vbox18.getChildren().remove(regDatesButtonBar);
                } else {
                    vbox18.getChildren().remove(regDatesButtonBar);
                }   
            } else if (vbox18.getChildren().contains(leaseDatesButtonBar)) {
                if (vbox18.getChildren().contains(tdLeaseStartDate)) {
                    vbox18.getChildren().remove(tdLeaseStartDate);
                    vbox18.getChildren().remove(leaseDatesButtonBar);
                } else if (vbox18.getChildren().contains(tdLeaseEndDate)) {
                    vbox18.getChildren().remove(tdLeaseEndDate);
                    vbox18.getChildren().remove(leaseDatesButtonBar);
                } else {
                    vbox18.getChildren().remove(leaseDatesButtonBar);
                }
            } 
        });
        
        
        
        
        
        
        
        pdCashTextfield.setFont(javafx.scene.text.Font.font("Roboto", FontPosture.ITALIC, 15));
        pdCashTextfield.setPromptText("Received by...");
        pdCashTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            pdCashTextfield.setFont(javafx.scene.text.Font.font("Roboto", FontPosture.REGULAR, 16));
            
            if (newValue.isEmpty() || oldValue.isEmpty()) {
                pdCashTextfield.setFont(javafx.scene.text.Font.font("Roboto", FontPosture.ITALIC, 15));
            }
        });
        
        pdbankTextfield.setFont(javafx.scene.text.Font.font("Roboto", FontPosture.ITALIC, 15));
        pdbankTextfield.setPromptText("Deposit Slip...");
        pdbankTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            pdbankTextfield.setFont(javafx.scene.text.Font.font("Roboto", FontPosture.REGULAR, 16));
            
            if (newValue.isEmpty() || oldValue.isEmpty()) {
                pdbankTextfield.setFont(javafx.scene.text.Font.font("Roboto", FontPosture.ITALIC, 15));
            }
        });

        pdMpesaTextfield.setFont(javafx.scene.text.Font.font("Roboto", FontPosture.ITALIC, 15));
        pdMpesaTextfield.setPromptText("Transaction code...");
        pdMpesaTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            pdMpesaTextfield.setFont(javafx.scene.text.Font.font("Roboto", FontPosture.REGULAR, 16));
            
            if (newValue.isEmpty() || oldValue.isEmpty()) {
                pdMpesaTextfield.setFont(javafx.scene.text.Font.font("Roboto", FontPosture.ITALIC, 15));
            }
        });
        
        searchPaymentsTableTextfield.setPromptText("Search Payments Table...");
        StackPane.setMargin(searchPaymentsTableTextfield, new Insets(0, 25, 0, 25));
        StackPane.setMargin(clearSearchTextFieldButton, new Insets(0, 23, 0, 0));
        StackPane.setMargin(searchButton, new Insets(0, 0, 0, 9));
        
        searchTextFieldStackPane.getStyleClass().add("highlight-rectangle");
        searchPaymentsTableTextfield.getStyleClass().add("transparent");
        searchButton.getStyleClass().add("search-button");
        clearSearchTextFieldButton.getStyleClass().add("delete-button");
        
        clearSearchTextFieldButton.setOnMouseClicked((event) -> {
            searchPaymentsTableTextfield.clear();
            event.consume();
        });
        
        clearSearchTextFieldButton.visibleProperty().bind(searchPaymentsTableTextfield.textProperty().isEmpty().not());
        
        searchPaymentsTableTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<PDModel> filteredData = new FilteredList<>(getPaymentDetails());
            paymentsTable.setRoot(new RecursiveTreeItem<>(filteredData, RecursiveTreeObject::getChildren));
            filteredData.setPredicate(createPredicate(newValue));
        });
        
        repairsTableSearchTextField.setPromptText("Search Repairs Table...");
        StackPane.setMargin(repairsTableSearchTextField, new Insets(0, 25, 0, 25));
        StackPane.setMargin(clearRepairSearchTextfield, new Insets(0, 23, 0, 0));
        StackPane.setMargin(repairSearchButton, new Insets(0, 0, 0, 9));
        
        repairSearchStackPane.getStyleClass().add("highlight-rectangle");
        repairsTableSearchTextField.getStyleClass().add("transparent");
        repairSearchButton.getStyleClass().add("search-button");
        clearRepairSearchTextfield.getStyleClass().add("delete-button");
        
        clearRepairSearchTextfield.setOnMouseClicked((event) -> {
            repairsTableSearchTextField.clear();
            event.consume();
        });
        
        clearRepairSearchTextfield.visibleProperty().bind(repairsTableSearchTextField.textProperty().isEmpty().not());
        
        repairsTableSearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<RModel> filteredData = new FilteredList<>(getRepairsDetails());
            repairsTable.setRoot(new RecursiveTreeItem<>(filteredData, RecursiveTreeObject::getChildren));
            filteredData.setPredicate(createRepairsTablePredicate(newValue));
        });
        
        rdMaterialHbox.setAlignment(Pos.CENTER_RIGHT);
        rdLabourHbox.setAlignment(Pos.CENTER_RIGHT);
        rdMiscHbox.setAlignment(Pos.CENTER_RIGHT);
        rdMiscHbox.getChildren().add(miscText);
        
        materialButton.setGraphic(new Label("Materials Cost"));
        materialButton.getStyleClass().add(ANIMATED_OPTION_BUTTONRD);
        labourButton.setGraphic(new Label("Labour Cost"));
        labourButton.getStyleClass().add(ANIMATED_OPTION_BUTTONRD);
        miscButton.setGraphic(new Label("Miscellaneous Cost"));
        miscButton.getStyleClass().add(ANIMATED_OPTION_BUTTONRD);
        
        materialNodesList.setSpacing(180);
        materialNodesList.addAnimatedNode(materialButton);
        materialNodesList.addAnimatedNode(rdMaterialHbox);
        materialNodesList.setRotate(270);
        materialButton.setOnMouseClicked((event) -> {
            labourNodesList.animateList(false);
            miscNodesList.animateList(false);
        });
        
        labourNodesList.setSpacing(180);
        labourNodesList.addAnimatedNode(labourButton);
        labourNodesList.addAnimatedNode(rdLabourHbox);
        labourNodesList.setRotate(270);
        labourButton.setOnMouseClicked((event) -> {
            materialNodesList.animateList(false);
            miscNodesList.animateList(false);
        });
        
        miscNodesList.setSpacing(180);
        miscNodesList.addAnimatedNode(miscButton);
        miscNodesList.addAnimatedNode(rdMiscHbox);
        miscNodesList.setRotate(270);
        miscButton.setOnMouseClicked((event) -> {
            materialNodesList.animateList(false);
            labourNodesList.animateList(false);
        });
        
        stickyIconPane.setPadding(new Insets(10));
        stickyBadge.getStyleClass().add(ICONS_BADGE);
        stickyBadge.setAlignment(Pos.TOP_RIGHT);
        stickyIconPane.getChildren().add(GlyphsDude.createIcon(FontAwesomeIcon.STICKY_NOTE, "15"));
        stickyBadge.setControl(stickyIconPane);
        stickyBadge.setOnMouseClicked((event) -> {
            try {
                stickyBadge.refreshBadge();
                checkReminderNote();
                createStickyDialog(blockTreeView.getSelectionModel().getSelectedItem().getValue(), wrapStackPane);
            } catch (Exception ex) {
                Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        l1.setMinSize(120, 20);
        l2.setMinSize(120, 20);
        l3.setMinSize(120, 20);
        l4.setMinSize(120, 20);
        l5.setMinSize(120, 20);
        l10.setMinSize(120, 20);
        l11.setMinSize(120, 20);
        l12.setMinSize(120, 20);
        l13.setMinSize(120, 20);
        l16.setMinSize(120, 20);
        l17.setMinSize(120, 20);
        l18.setMinSize(120, 20);
        l19.setMinSize(120, 20);
        l20.setMinSize(120, 20);
        l14.setMinSize(120, 20);        

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
            updateTenantDetails(databaseActivityIndicatorTD);
        });

        pdUpdate.setOnAction((event) -> {
            updatePaymentDetails(databaseActivityIndicatorPD);
        });

        rdUpdate.setOnAction((event) -> {
            try {
                updateRepairsDetailsTable(databaseActivityIndicatorRD);
            } catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        rdDelete.setOnAction((event) -> {
            deleteFromRepairDetailsTable(databaseActivityIndicatorRD);
        });
        
        pdDelete.setOnAction((event) -> {
            deleteFromPaymentDetails(databaseActivityIndicatorPD);
        });
        
        tdDelete.setOnAction((event) -> {
            deleteFromTenantDetails(databaseActivityIndicatorTD);
        });

        tdSaveAs.setOnAction((event) -> {
            prefs = Preferences.userRoot().node(this.getClass().getName());
            if (blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Block A") || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Block B") || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Block C") || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Nasra Block")) {
                JFXAlert noHouseAlert = new JFXAlert((Stage) payAndTenantDetailsBorderPane.getScene().getWindow());
                noHouseAlert.initModality(Modality.APPLICATION_MODAL);
                noHouseAlert.setOverlayClose(false);
                
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("No Apartment Seleted"));
                content.setBody(new Label("Please select a house."));
                
                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                okButton.setOnAction(act -> {
                    noHouseAlert.hideWithAnimation();
                });
                
                content.setActions(okButton);
                noHouseAlert.setContent(content);
                noHouseAlert.show();
            } else {
                File initialFile = new File(prefs.get(loc, "location"));
                if (prefs.get(loc, "location").equals("location") || initialFile.exists() == false) {
                    FileChooser initialLoc = new FileChooser();
                    File initFile = initialLoc.showSaveDialog(payAndTenantDetailsBorderPane.getScene().getWindow());
                    prefs.put(loc, initFile.getPath());
                    saveToTenantDetailsTable(databaseActivityIndicatorTD);
                } else if (!prefs.get(loc, "location").equals("location")) {
                    FileChooser savedLoc = new FileChooser();
                    savedLoc.setInitialDirectory(initialFile.getParentFile());
                    File savedFile = savedLoc.showSaveDialog(payAndTenantDetailsBorderPane.getScene().getWindow());
                    prefs.put(loc, savedFile.getPath());
                    saveToTenantDetailsTable(databaseActivityIndicatorTD);
                }
            }
        });
        
        tdSave.setOnAction((event) -> {
            prefs = Preferences.userRoot().node(this.getClass().getName());
            if (blockTreeView.getSelectionModel().getSelectedItem() == null || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Block A") || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Block B") || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Block C") || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Nasra Block")) {
                JFXAlert noHouseAlert = new JFXAlert((Stage) payAndTenantDetailsBorderPane.getScene().getWindow());
                noHouseAlert.initModality(Modality.APPLICATION_MODAL);
                noHouseAlert.setOverlayClose(false);
                
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("No Apartment Seleted"));
                content.setBody(new Label("Please select a house."));
                
                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                okButton.setOnAction(act -> {
                    noHouseAlert.hideWithAnimation();
                });
                
                content.setActions(okButton);
                noHouseAlert.setContent(content);
                noHouseAlert.show();
            } else {
                try {
                    File initialFile = new File(prefs.get(loc, "location"));
                    
                    if (prefs.get(loc, "location").equals("location") || initialFile.exists() == false) {
                        FileChooser initialLoc = new FileChooser();
                        File initFile = initialLoc.showSaveDialog(payAndTenantDetailsBorderPane.getScene().getWindow());
                        prefs.put(loc, initFile.getPath());
                        System.out.println(prefs.get(loc, "location"));
                        excelFileLocation = initFile.getPath();
                        saveToTenantDetailsTable(databaseActivityIndicatorTD);
                    } else if (!prefs.get(loc, "location").equals("location")) {
                        saveToTenantDetailsTable(databaseActivityIndicatorTD);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pdSave.setOnAction((event) -> {
            prefs = Preferences.userRoot().node(this.getClass().getName());
            if (blockTreeView.getSelectionModel().getSelectedItem() == null || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Block A") || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Block B") || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Block C") || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Nasra Block")) {
                
                JFXAlert noHouseAlert = new JFXAlert((Stage) payAndTenantDetailsBorderPane.getScene().getWindow());
                noHouseAlert.initModality(Modality.APPLICATION_MODAL);
                noHouseAlert.setOverlayClose(false);
                
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("No Apartment Seleted"));
                content.setBody(new Label("Please select a house."));
                
                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                okButton.setOnAction(act -> {
                    noHouseAlert.hideWithAnimation();
                });
                
                content.setActions(okButton);
                noHouseAlert.setContent(content);
                noHouseAlert.show();
            } else  if (pdMonthCombo.getSelectionModel().getSelectedItem().equals(PDModel.Strings.NONE)) {
                
                JFXAlert monthErrorAlert = new JFXAlert((Stage) payAndTenantDetailsBorderPane.getScene().getWindow());
                monthErrorAlert.initModality(Modality.APPLICATION_MODAL);
                monthErrorAlert.setOverlayClose(true);
                
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("No month selected"));
                content.setBody(new Label("Please select a month"));
                
                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                okButton.setOnAction(act -> {
                    monthErrorAlert.hideWithAnimation();
                });
                
                content.setActions(okButton);
                monthErrorAlert.setContent(content);
                monthErrorAlert.show();
            } else if ("".equals(pdName.getText()) || "".equals(pdAmount.getText()) || "".equals(pdPaymentDate.getEditor().getText())) {
                JFXAlert emptyFieldAlert = new JFXAlert((Stage) payAndTenantDetailsBorderPane.getScene().getWindow());
                emptyFieldAlert.initModality(Modality.APPLICATION_MODAL);
                emptyFieldAlert.setOverlayClose(true);
                
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("Empty Field"));
                content.setBody(new Label("Empty Field is not allowed"));
                
                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                
                okButton.setOnAction(act -> {
                    emptyFieldAlert.hideWithAnimation();
                });
                
                content.setActions(okButton);
                emptyFieldAlert.setContent(content);
                emptyFieldAlert.show();
            } else {
                saveToPaymentDetailsTable(databaseActivityIndicatorPD);
            }
        });

        rdSave.setOnAction((event) -> {
            if (blockTreeView.getSelectionModel().getSelectedItem() == null || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Block A") || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Block B") || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Block C") || blockTreeView.getSelectionModel().getSelectedItem().getValue().equals("Nasra Block")) {

                JFXAlert noHouseAlert = new JFXAlert((Stage) rdBorderPane.getScene().getWindow());
                noHouseAlert.initModality(Modality.APPLICATION_MODAL);
                noHouseAlert.setOverlayClose(false);

                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("No Apartment Seleted"));
                content.setBody(new Label("Please select a house."));

                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                okButton.setOnAction(act -> {
                    noHouseAlert.hideWithAnimation();
                });

                content.setActions(okButton);
                noHouseAlert.setContent(content);
                noHouseAlert.show();
            } else if (rdMonthCombo.getSelectionModel().getSelectedItem().equals(RModel.Strings.NONE)) {

                JFXAlert monthErrorAlert = new JFXAlert((Stage) rdBorderPane.getScene().getWindow());
                monthErrorAlert.initModality(Modality.APPLICATION_MODAL);
                monthErrorAlert.setOverlayClose(true);

                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("No month selected"));
                content.setBody(new Label("Please select a month"));

                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                okButton.setOnAction(act -> {
                    monthErrorAlert.hideWithAnimation();
                });

                content.setActions(okButton);
                monthErrorAlert.setContent(content);
                monthErrorAlert.show();
            } else if ("".equals(materialText.getText()) || "".equals(labourText.getText())|| "".equals(repairDetailsTextArea.getText()) || "".equals(rdRepairDate.getEditor().getText())) {
                
                JFXAlert emptyFieldAlert = new JFXAlert((Stage) rdBorderPane.getScene().getWindow());
                emptyFieldAlert.initModality(Modality.APPLICATION_MODAL);
                emptyFieldAlert.setOverlayClose(true);
                
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("Empty Field"));
                content.setBody(new Label("Empty Field is not allowed"));
                
                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                
                okButton.setOnAction(act -> {
                    emptyFieldAlert.hideWithAnimation();
                });
                
                content.setActions(okButton);
                emptyFieldAlert.setContent(content);
                emptyFieldAlert.show();
            } else {
                saveToRepairDetailsTable(databaseActivityIndicatorRD);
            }
            
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
            
        });
        
        rdNewRecord.setOnAction((event) -> {
            repairRowId = 0;
            repairDetailsTextArea.clear();
            rdRepairDate.setValue(null);
            materialText.clear();
            labourText.clear();
            miscText.clear();
        });
         
        pdStickyNote.setOnAction((event) -> {
            if (pdMonthCombo.getSelectionModel().getSelectedItem().equals(PDModel.Strings.NONE)) {
                JFXAlert noMonthSelectedAlert = new JFXAlert((Stage) payAndTenantDetailsBorderPane.getScene().getWindow());
                noMonthSelectedAlert.initModality(Modality.APPLICATION_MODAL);
                noMonthSelectedAlert.setOverlayClose(true);

                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("No month selected"));
                content.setBody(new Label("Please choose a month"));

                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                okButton.setOnAction(act -> {
                    noMonthSelectedAlert.hideWithAnimation();
                });

                content.setActions(okButton);
                noMonthSelectedAlert.setContent(content);
                noMonthSelectedAlert.showAndWait();
            } else if (payRowId == 0) {
                JFXAlert noRecordAlert = new JFXAlert((Stage) payAndTenantDetailsBorderPane.getScene().getWindow());
                noRecordAlert.initModality(Modality.APPLICATION_MODAL);
                noRecordAlert.setOverlayClose(true);

                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("No Record"));
                content.setBody(new Label("No entry found. First insert into database and try again."));

                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                okButton.setOnAction(act -> {
                    noRecordAlert.hideWithAnimation();
                });

                content.setActions(okButton);
                noRecordAlert.setContent(content);
                noRecordAlert.showAndWait();
            } else {                
                /*if (!payLayout.getChildren().contains(stickyBadge)) {
                    createStickyDialog(blockTreeView.getSelectionModel().getSelectedItem().getValue(), wrapStackPane);
                } else {
                    createStickyDialog(blockTreeView.getSelectionModel().getSelectedItem().getValue(), wrapStackPane);   
                }*/
            }
        });

        pdPrintReceipt.setOnAction((event) -> {
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

            final String resourcePath = "C:\\Users\\bonyo\\OneDrive\\Desktop\\jat\\Rental-Application-Vertical-Menu\\src\\main\\resources\\Receipt\\RentReceipt.jrxml";
            JasperPrint jasperPrint = null;
            Connection conn = null;
            try {
                Class.forName("org.sqlite.JDBC");
                conn = getConnection();
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
            jasperViewer.setSize(1000, 650);
            jasperViewer.setTitle("Rent Receipt");
            jasperViewer.setVisible(true);
        });        

        pdMonthCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == PDModel.Strings.NONE) {
                pdAmount.clear();
                pdPaymentDate.setValue(null);
                pdCashTextfield.clear();
                pdbankTextfield.clear();
                pdMpesaTextfield.clear();
                pdTableViewButton.setVisible(false);
                if (paymentsTabGridPane.getChildren().contains(stickyBadge)) {
                    paymentsTabGridPane.getChildren().remove(stickyBadge);
                }
            } else {
                fetchPaymentDetailsFromDBToUI(pdAmount, pdPaymentDate);
                try {
                    checkReminderNote();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        pdMonthCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            payTenantDetails = getPaymentDetails();
            FilteredList<PDModel> filteredList = new FilteredList<>(payTenantDetails);
            filteredList.setPredicate((t) -> {
                if (pdMonthCombo.getValue() == null || pdMonthCombo.getSelectionModel().isEmpty()) {
                    return true;
                }
                PDModel.Strings comboFilter = newValue;
                return t.getmonthTablePD().equals(newValue) || newValue.getMonth().equals("All");
            });
            SortedList<PDModel> sortedData = new SortedList<>(filteredList);
            paymentsTable.setRoot(new RecursiveTreeItem<>(getPaymentDetails(), RecursiveTreeObject::getChildren));
            paymentsTable.setShowRoot(false);
        });

        rdMonthCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            boolean isTreeViewEmpty = blockTreeView.getSelectionModel().isEmpty();
            
            if (isTreeViewEmpty) {
                JFXAlert noHouseAlert = new JFXAlert((Stage) rdBorderPane.getScene().getWindow());
                noHouseAlert.initModality(Modality.APPLICATION_MODAL);
                noHouseAlert.setOverlayClose(false);

                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Label("No Apartment Seleted"));
                content.setBody(new Label("Please select a house."));

                JFXButton okButton = new JFXButton("OK");
                okButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                okButton.setOnAction(act -> {
                    noHouseAlert.hideWithAnimation();
                });

                content.setActions(okButton);
                noHouseAlert.setContent(content);
                noHouseAlert.show();
            }  else {
                fetchRepairDetailsFromDBToUI();
            }
            
        });

        rdMonthCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            boolean isTreeViewEmpty = blockTreeView.getSelectionModel().isEmpty();

            if (!isTreeViewEmpty) {
                houseRepairDetails = getRepairsDetails();
                FilteredList<RModel> filteredList = new FilteredList<>(houseRepairDetails);
                filteredList.setPredicate((t) -> {
                    if (rdMonthCombo.getValue() == null || rdMonthCombo.getSelectionModel().isEmpty()) {
                        return true;
                    }
                    RModel.Strings comboFilter = newValue;
                    return t.getMonthTableR().equals(newValue) || newValue.getMonth().equals("All");
                });
                SortedList<RModel> sortedData = new SortedList<>(filteredList);
                repairsTable.setRoot(new RecursiveTreeItem<>(getRepairsDetails(), RecursiveTreeObject::getChildren));
                repairsTable.setShowRoot(false);
            }
        });
        
        paymentsTable.setRowFactory((param) -> {
            final JFXTreeTableRow<PDModel> row = new JFXTreeTableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem editPay = new MenuItem("Edit");
            final MenuItem deletePay = new MenuItem("Remove");

            row.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    JFXTreeTableRow c = (JFXTreeTableRow) event.getSource();
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

                        String paymentMethod = item.getpaymentMethodPD();
                        String[] payArray = paymentMethod.split(":");

                        switch (payArray[0]) {
                            case "Cash":
                                if (!selectPayOptionNode.isExpanded()) {
                                    selectPayOptionNode.animateList();
                                }
                                cashButton.fire();
                                pdCashTextfield.setText(payArray[1]);
                                break;
                            case "Bank":
                                if (!selectPayOptionNode.isExpanded()) {
                                    selectPayOptionNode.animateList();
                                }
                                bankButton.fire();
                                pdbankTextfield.setText(payArray[1]);
                                break;
                            case "Mpesa":
                                if (!selectPayOptionNode.isExpanded()) {
                                    selectPayOptionNode.animateList();
                                }
                                mpesaButton.fire();
                                pdMpesaTextfield.setText(payArray[1]);
                                break;
                            default:
                                break;
                        }
                        
                        int paidRent = getStringNumber(item.getamountTablePD());
                        showRentArrears(houseMonthlyRent, paidRent);
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

        repairsTable.setRowFactory((param) -> {
            final JFXTreeTableRow<RModel> row = new JFXTreeTableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem editPay = new MenuItem("Edit");
            final MenuItem deletePay = new MenuItem("Remove");
           
            row.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    JFXTreeTableRow c = (JFXTreeTableRow) event.getSource();
                    int index = c.getIndex();
                    try {
                        RModel item = getRepairsDetails().get(index);
                        rdMonthCombo.setValue(item.getMonthTableR());
                        materialText.setText(item.getMaterialCostofRepairsTableR());
                        labourText.setText(item.getLabourCostofRepairsTableR());
                        miscText.setText(item.getmiscellaneousTableR());
                        repairDetailsTextArea.setText(item.getrepairsDoneTableR());
                        if (item.getdateofRepairsTableR() == null) {
                            rdRepairDate.setValue(null);
                        } else {
                            rdRepairDate.setValue(LocalDate.parse(item.getdateofRepairsTableR(), DateTimeFormatter.ISO_DATE));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
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
        
        rdRepairsDone.setWrapText(true);
        rdRepairsDone.setPrefSize(170, 50);
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

        
        rdTableViewButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        rdTableViewButton.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.ANGLE_DOWN, "22"));
        rdTableViewButton.setVisible(false);       
        
        setupAmountColumn();
        setupMonthColumn();
        setupPaymentDateColumn();
        setupPaymentMethodColumn();

        setupRepairMonthColumn();
        setupRepairsDoneColumn();
        setupMaterialCostColumn();
        setupLabourCostColumn();
        setupRepairsDateColumn();
        setupMiscellaneousColumn();
        
        paymentsTable.getColumns().addAll(amountCol, monthCol, dateCol, methodCol);

        repairsTable.getColumns().addAll(repairMonthCol, repairDone, materialCostOfRepair, labourCostOfRepair, miscExpenses, dateOfRepair);
               
        repairsLayout.setHgap(10);
        repairsLayout.setVgap(10);
        repairsLayout.setPadding(new Insets(5));
        repairsLayout.add(rdHbox3, 0, 3);
        repairsLayout.add(rdHbox6, 0, 4);
        
        repairsLayout.add(databaseActivityIndicatorRD, 2, 0);
        
        repairHeaderLabel.setFont(javafx.scene.text.Font.font("Roboto", FontWeight.BOLD, 16));
        repairHeaderLabel.setUnderline(true);
        repairHeaderLabel.setPadding(new Insets(0, 5, 0, 10));
        
        tenantDetailsLabel.setFont(javafx.scene.text.Font.font("Roboto", FontWeight.BOLD, 16));
        tenantDetailsLabel.setUnderline(true);
        tenantDetailsLabel.setPadding(new Insets(0, 5, 0, 10));
        
        paymentDetailsLabel.setFont(javafx.scene.text.Font.font("Roboto", FontWeight.BOLD, 16));
        paymentDetailsLabel.setUnderline(true);
        paymentDetailsLabel.setPadding(new Insets(0, 5, 0, 10));
        
        rdMonthCombo.setPrefWidth(210);
        
        repairDetailsTextArea.setPromptText("Enter repairs done.");
        repairDetailsTextArea.setPrefSize(180, 100);
        repairDetailsTextArea.setWrapText(true);
        
        vbox2.getChildren().addAll(l16, repairDetailsTextArea);
        vbox3.getChildren().addAll(l18, rdRepairDate);
        vbox4.getChildren().addAll(new Label("Material Cost"), materialText);
        vbox5.getChildren().addAll(new Label("Labour Cost"), labourText);
        vbox6.getChildren().addAll(new Label("Other Costs"), miscText);
        
        vbox7.getChildren().addAll(l1, tdName);
        vbox8.getChildren().addAll(l2, tdPhone);
        vbox9.getChildren().addAll(l3, tdAmount);
        vbox10.getChildren().addAll(l4, tdDeposit);
        vbox11.getChildren().addAll(l5, tdDueDate);
        vbox17.getChildren().add(regAndLeaseDatesNodesList);
        pdName.setEditable(false);
        vbox16.getChildren().addAll(l10, pdName);
        vbox12.getChildren().addAll(l11, pdMonthCombo);
        vbox13.getChildren().addAll(l12, pdAmount);
        vbox14.getChildren().addAll(l13, pdPaymentDate);
        vbox15.setPadding(new Insets(10, 0, 0, 0));
        vbox15.getChildren().add(selectPayOptionNode);
        
        tenantDetailsVbox.setPadding(new Insets(5));
        tenantDetailsVbox.getChildren().addAll(vbox7, vbox8, vbox9, vbox10, vbox11, vbox17);
        paymentDetailsVbox.setPadding(new Insets(5));
        paymentDetailsVbox.getChildren().addAll(vbox16, vbox12, vbox13, vbox14, vbox15);
        
        repairDetailsVbox.getChildren().addAll(rdMonthCombo, vbox4, vbox5, vbox6, vbox2, vbox3);
        
        repairDetailsGrid.setStyle("-fx-border-color: #d9d9d9");
        repairDetailsVbox.setPadding(new Insets(5));
        repairDetailsGrid.add(repairDetailsVbox, 0, 1, 2, 1);
        repairDetailsGrid.add(repairHeaderLabel, 0, 0, 2, 1);
        repairDetailsGrid.add(repairsIcon, 2, 0);
        GridPane.setMargin(repairsIcon, new Insets(0, 15, 0, 0));
        repairTableViewAnchorPane.setPadding(new Insets(5, 5, 5, 5));
        repairTableViewAnchorPane.getChildren().add(repairTableViewGridPane);
        
        AnchorPane.setBottomAnchor(repairTableViewGridPane, 0.0);
        AnchorPane.setLeftAnchor(repairTableViewGridPane, 0.0);
        AnchorPane.setRightAnchor(repairTableViewGridPane, 0.0);
        AnchorPane.setTopAnchor(repairTableViewGridPane, 0.0);
        
        payTableVbox.getChildren().add(paymentsTable);
        paymentsTable.setPrefSize(520, 460);
        searchTextFieldStackPane.getChildren().addAll(searchPaymentsTableTextfield, searchButton, clearSearchTextFieldButton);
       
        repairTableDetailsVbox.getChildren().add(repairsTable);
        repairsTable.setPrefSize(580, 365);
        repairSearchStackPane.getChildren().addAll(repairsTableSearchTextField, repairSearchButton, clearRepairSearchTextfield);
        
        StackPane.setAlignment(searchButton, Pos.CENTER_LEFT);
        StackPane.setAlignment(clearSearchTextFieldButton, Pos.CENTER_RIGHT);
        StackPane.setAlignment(repairSearchButton, Pos.CENTER_LEFT);
        StackPane.setAlignment(clearRepairSearchTextfield, Pos.CENTER_RIGHT);
        
        payAndTenantDetailsTabPane.getTabs().addAll(paymentsTab, tenantTab);
        tenantDetailsLabelHbox.setPadding(new Insets(5));
        tenantsTabGridPane.add(tenantDetailsLabelHbox, 0, 0);
        tenantsTabGridPane.add(tenantDetailsVbox, 0, 1, 2, 1);
        paymentDetailsLabelHbox.setPadding(new Insets(5));
        paymentsTabGridPane.add(paymentDetailsLabelHbox, 0, 0);
        paymentsTabGridPane.add(paymentDetailsVbox, 0, 1, 2, 1);
        tenantTab.setContent(tenantsTabGridPane);
        paymentsTab.setContent(paymentsTabGridPane);
        tenantTab.setClosable(false);
        paymentsTab.setClosable(false);       
        
        rentArrearsVbox.setPadding(new Insets(5, 5, 5, 5));
        rentArrearsVbox.setSpacing(5);
        rentArrearsVbox.setStyle("-fx-border-color: #d9d9d9;");
        rentArrearsVbox.getChildren().addAll(new Label("Month is in arrears"), rentArrearsHbox);
        
        rentArrearsHbox.setPadding(new Insets(8, 0, 0, 0));
        rentArrearsHbox.setSpacing(5);
        rentArrearsHbox.setStyle("-fx-border-color: #d9d9d9;");
        rentArrearsHbox.getChildren().addAll(monthRentArrearsVbox, paidRentArrearsVbox, remainderRentArrearsVbox);
        
        monthRentArrearsTextfield.setEditable(false);
        paidRentArrearsTextfield.setEditable(false);
        remainderRentArrearsTextfield.setEditable(false);
        
        monthRentArrearsVbox.setPadding(new Insets(5));
        monthRentArrearsVbox.setSpacing(5);
        monthRentArrearsVbox.getChildren().addAll(new Label("Monthly Rent"), monthRentArrearsTextfield);
        
        paidRentArrearsVbox.setPadding(new Insets(5));
        paidRentArrearsVbox.setSpacing(5);
        paidRentArrearsVbox.getChildren().addAll(new Label("Rent Received"), paidRentArrearsTextfield);
        
        remainderRentArrearsVbox.setPadding(new Insets(5));
        remainderRentArrearsVbox.setSpacing(5);
        remainderRentArrearsVbox.getChildren().addAll(new Label("Arrears"), remainderRentArrearsTextfield);
        
        payAndTenantDetailsTabPane.setId("TP");
        
        payAndTenantVbox.getStyleClass().add("background-pane");
        payAndTenantVbox.getChildren().addAll(searchTextFieldStackPane, payTableVbox);
        
        repairVbox.getStyleClass().add("background-pane");
        repairVbox.getChildren().addAll(repairSearchStackPane, repairTableDetailsVbox, repairSearchAnchorPane);
        
        payAndTenantDetailsBorderPane.setCenter(payAndTenantVbox);
        payAndTenantDetailsBorderPane.setRight(payAndTenantDetailsTabPane);
        BorderPane.setMargin(payAndTenantDetailsTabPane, new Insets(5));
        BorderPane.setMargin(payAndTenantVbox, new Insets(5));
        pdStackPane.getChildren().add(payAndTenantDetailsBorderPane);
        rdBorderPane.setCenter(repairVbox);
        rdBorderPane.setRight(repairDetailsGrid);
        BorderPane.setMargin(repairVbox, new Insets(5));
        BorderPane.setMargin(repairDetailsGrid, new Insets(5));
        rdStackPane.getChildren().add(rdBorderPane);
        
        tenantDataPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tenantDataPane.getTabs().addAll(paymentDetails, repairDetails);

        detailsPane.setMinSize(450, 300);
        detailsPane.setCenter(tenantDataPane);
        blockTreeView.setMinWidth(120);
        selectionPane.setCenter(blockTreeView);
    }
    
    public class DetailsIcon extends StackPane {

        public DetailsIcon() {
            Label lab = new Label("•••");
            lab.setStyle("-fx-text-fill:black");
            lab.setOnMouseClicked((event) -> {
                ContextMenu conMenu = new ContextMenu(tdNewRecord, new SeparatorMenuItem(), tdUpdate, tdDelete, new SeparatorMenuItem(), tdSave, tdSaveAs);
                conMenu.show(detIcon, Side.RIGHT, xCursorPos, yCursorPos);
            });
            
            Circle circle = new Circle(12f, Color.rgb(0, 122, 255));
            getChildren().add(lab);
        }
    }

    public class PayMenuIcon extends StackPane {

        public PayMenuIcon() {
            Label lab = new Label("•••");
            lab.setStyle("-fx-text-fill:black");
            lab.setOnMouseClicked((event) -> {
                ContextMenu conMenu = new ContextMenu(pdNewRecord, new SeparatorMenuItem(), pdUpdate, pdDelete, new SeparatorMenuItem(), pdSave, new SeparatorMenuItem(), pdStickyNote, new SeparatorMenuItem(), pdPrintReceipt);
                conMenu.show(payIcon, Side.BOTTOM, xCursorPos, yCursorPos);
            });
            Circle circle = new Circle(12f, Color.rgb(0, 122, 255));
            getChildren().add(lab);
        }
    }

    public class repairsMenuIcon extends StackPane {

        public repairsMenuIcon() {
            Label lab = new Label("•••");
            lab.setStyle("-fx-text-fill:black");
            lab.setOnMouseClicked((event) -> {
                ContextMenu conMenu = new ContextMenu(rdNewRecord, new SeparatorMenuItem(), rdUpdate, rdDelete, new SeparatorMenuItem(), rdSave);
                if (conMenu.isShowing()) {
                    conMenu.hide();
                } else {
                    conMenu.show(repairsIcon, Side.BOTTOM, xCursorPos, yCursorPos);
                }
            });
            Circle circle = new Circle(12f, Color.rgb(0, 122, 255));
            getChildren().add(lab);
        }
    }
    
    class MyEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {
            if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2) {
                TableRow c = (TableRow) t.getSource();
                int index = c.getIndex();
                try {
                    PDModel item = getPaymentDetails().get(index);
                    pdAmount.setText(item.getamountTablePD());
                    pdMonthCombo.setValue(item.getmonthTablePD());
                    pdPaymentDate.setValue(LocalDate.parse(item.getpaymentDateTablePD(), DateTimeFormatter.ISO_DATE));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    class ReminderNoteModel extends RecursiveTreeObject<ReminderNoteModel> {
        public StringProperty houseNumber;
        public StringProperty reminderNote;
        public StringProperty month;
        public StringProperty dateTime;
        
        public ReminderNoteModel(String houseNumber, String reminderNote, String month, String dateTime) {
            this.houseNumber = new SimpleStringProperty(houseNumber);
            this.reminderNote = new SimpleStringProperty(reminderNote);
            this.month = new SimpleStringProperty(month);
            this.dateTime  = new SimpleStringProperty(dateTime);
        }
        
        public String getHouseNumber() {
            return houseNumber.get();
        }
        
        public void setHouseNumber(String value) {
            houseNumber.set(value);
        }
        
        public StringProperty houseNumberProperty() {
            return houseNumber;
        }
        
        public String getReminderNote() {
            return reminderNote.get();
        }
        
        public void setReminderNote(String value) {
            reminderNote.set(value);
        }
        
        public StringProperty reminderNoteProperty() {
            return reminderNote;
        }
        
        public String getMonth() {
            return month.get();
        }
        
        public void setMonth(String value) {
            month.set(value);
        }
        
        public StringProperty monthProperty() {
            return month;
        }
        
        public String getDateTime() {
            return dateTime.get();
        }
        
        public void setDateTime(String value) {
            dateTime.set(value);
        }
        
        public StringProperty dateTimeProperty() {
            return dateTime;
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
                String searchPDTable = "SELECT * FROM PaymentDetails WHERE HouseNumber = ? AND PaymentMonth = ?";
                pstmt = con.prepareStatement(searchPDTable);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                pstmt.setString(2, pdMonthCombo.getValue().getMonth());
                rs = pstmt.executeQuery();
                
                if (!rs.next()) {
                    logger.info("No entry found in database");
                    payRowId = 0;
                } else {
                    do {                        
                       payRowId = rs.getInt("RowID");
                       paymentDetails.addAll(rs.getString("HouseNumber"), rs.getString("TenantName"), rs.getString("Amount"), rs.getString("PaymentMonth"), rs.getString("PaymentDate"), rs.getString("PaymentMethod"));
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
    
    class FetchRepairDetailsTask extends DBTask<ObservableList<String>> {
        @Override
        protected ObservableList<String> call() throws Exception {
            try (Connection con = getConnection()) {
                return fetchRepairDetails(con);
            }
        }
        
        public ObservableList<String> fetchRepairDetails(Connection con) {
            logger.info("Fetching Repair Details from database");
            ObservableList<String> repairDetails = FXCollections.observableArrayList();
            
            try {
                String searchRepairDetails = "SELECT * FROM RepairDetailsTable WHERE HouseNumber = ? AND Month = ?";
                pstmt = con.prepareStatement(searchRepairDetails);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                if (rdMonthCombo.getSelectionModel().isEmpty()) {
                    System.out.println("No repairs month selection");
                    return repairDetails;
                } else {
                    pstmt.setString(2, rdMonthCombo.getValue().getMonth());
                }
                
                rs = pstmt.executeQuery();

                if (!rs.next()) {
                    logger.info("No entry found in database.");
                    repairRowId = 0;
                } else {
                    do {
                        repairDetails.addAll(rs.getString("HouseNumber"), rs.getString("Month"), rs.getString("RepairsDone"), rs.getString("MaterialCost"), rs.getString("LabourCost"), rs.getString("MiscellaneousExpenses"), rs.getString("RepairsDateTime"));
                    } while (rs.next());
                    logger.info("Found Entry");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    pstmt.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return repairDetails;
        }
    }
    
    class CheckRepairRowIDTask extends DBTask<Integer> {
        @Override
        protected Integer call() throws Exception {
            try (Connection con = getConnection()) {
                System.out.println(getRepairRowID(con));
                return getRepairRowID(con);
            }
        }
        
        private int getRepairRowID(Connection con) {
            logger.info("Checking if repair table row is available");
            int tempRepairRowID = 0;
            
            try {
                String checkRowID = "SELECT * FROM RepairDetailsTable  WHERE HouseNumber = ? AND Month = ? AND RepairsDone = ? AND RepairsDateTime = ?";
                pstmt = con.prepareStatement(checkRowID);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                pstmt.setString(2, rdMonthCombo.getValue().getMonth());
                pstmt.setString(3, repairDetailsTextArea.getText());
                pstmt.setString(4, getDateValueAsString(rdRepairDate.getValue()));
                rs = pstmt.executeQuery();
                
                if (!rs.next()) {
                    logger.info("No repair entry found");
                } else {
                    do {                        
                        logger.info("Repair Detail found.");
                        tempRepairRowID = rs.getInt("RowID");
                    } while (rs.next());
                }
            } catch (SQLException e) {
                e.printStackTrace();
                logger.info("Error retrieving Repair Details Table entry");
            } finally {
                try {
                    rs.close();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return tempRepairRowID;
        }
    }
    
    class checkReminderNoteTask extends DBTask<ObservableList<ReminderNoteModel>> {
        @Override
        protected ObservableList<ReminderNoteModel> call() throws Exception {
            try (Connection con = getConnection()) {
                return checkReminderNote(con);
            }
        }
        
        private ObservableList<ReminderNoteModel> checkReminderNote(Connection con) {
            logger.info("Checking if reminder note is available.");
            ObservableList<ReminderNoteModel> reminderNoteDetails = FXCollections.observableArrayList();
            
            try {
                String checkReminderTable = "SELECT * FROM ReminderNoteTable WHERE HouseNumber = ? AND Month = ?";
                pstmt = con.prepareStatement(checkReminderTable);
                pstmt.setString(1, blockTreeView.getSelectionModel().getSelectedItem().getValue());
                pstmt.setString(2, pdMonthCombo.getSelectionModel().getSelectedItem().name());
                rs = pstmt.executeQuery();
                if (!rs.next()) {
                    logger.info("No reminder note");
                } else {
                    do {              
                     logger.info("Reminder note found");
                     ReminderNoteModel reminderNote = new ReminderNoteModel(rs.getString("HouseNumber"), rs.getString("RemNote"), rs.getString("Month"), rs.getString("DateTime"));
                     reminderNoteDetails.add(reminderNote);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                e.printStackTrace();
                logger.info("Error checking reminder note table");
            } finally {
                try {
                    pstmt.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return reminderNoteDetails;
        }
    }
    
    class SaveTenantDetailsTask extends DBTask<Boolean> {

        @Override
        protected Boolean call() throws Exception {
            Thread.sleep(1000);
            logger.info("task running");
            try (Connection con = getConnection()) {
                if (saveToTenantDetails(con, blockTreeView.getSelectionModel().getSelectedItem().getValue(), tdName.getText(), tdPhone.getText(), tdAmount.getText(), tdDeposit.getText(), tdDueDate.getText(), getDateValueAsString(tdMoveInDate.getValue()), getDateValueAsString(tdMoveOutDate.getValue()), getDateValueAsString(tdLeaseStartDate.getValue()), getDateValueAsString(tdLeaseEndDate.getValue()))) {
                    try {
                        File file = new File(excelFileLocation);
                        createExcelSheet(file, blockTreeView.getSelectionModel().getSelectedItem().getValue(), tdName.getText(), tdPhone.getText(), tdAmount.getText(), tdDeposit.getText(), tdDueDate.getText(), getDateValueAsString(tdMoveInDate.getValue()), getDateValueAsString(tdMoveOutDate.getValue()), getDateValueAsString(tdLeaseStartDate.getValue()), getDateValueAsString(tdLeaseEndDate.getValue()));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    return false;
                }
            }
            return true;
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
    
    class savePaymentDetailsTask extends DBTask<Boolean> {
        
        @Override
        protected Boolean call() throws Exception {
            Thread.sleep(1000);

            try (Connection con = getConnection()) {
                if (vbox15.getChildren().contains(pdCashTextfield)) {
                    payMethodString = "Cash:".concat(pdCashTextfield.getText());
                } else if (vbox15.getChildren().contains(pdbankTextfield)) {
                    payMethodString = "Bank:".concat(pdbankTextfield.getText());
                } else if (vbox15.getChildren().contains(pdMpesaTextfield)) {
                    payMethodString = "Mpesa:".concat(pdMpesaTextfield.getText());
                }
                if (savePaymentDetailsToTable(con, blockTreeView.getSelectionModel().getSelectedItem().getValue(), pdName.getText(), pdAmount.getText(), pdMonthCombo.getValue().getMonth(), getDateValueAsString(pdPaymentDate.getValue()), payMethodString, LocalDate.now().getYear())) {
                    try {
                        File file = new File(excelFileLocation);
                        createAndWriteExcelSheet(file, blockTreeView.getSelectionModel().getSelectedItem().getValue(), pdName.getText(), pdAmount.getText(), pdMonthCombo.getSelectionModel().getSelectedItem().name(), getDateValueAsString(pdPaymentDate.getValue()), payMethodString);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
        
        private boolean savePaymentDetailsToTable(Connection con, String HouseNumber, String TenantName, String Amount, String Month, String PaymentDate, String PaymentMethod, int Year) {
            logger.info("Inserting into Payment Details table.");
            try {
                String insertToPaymentDetails = "INSERT INTO PaymentDetails(HouseNumber, TenantName, Amount, PaymentMonth, PaymentDate, PaymentMethod, Year) VALUES(?,?,?,?,?,?,?)";
                pstmt = con.prepareStatement(insertToPaymentDetails);
                pstmt.setString(1, HouseNumber);
                pstmt.setString(2, TenantName);
                pstmt.setString(3, Amount);
                pstmt.setString(4, Month);
                pstmt.setString(5, PaymentDate);
                pstmt.setString(6, PaymentMethod);
                pstmt.setInt(7, Year);
                pstmt.execute();
            } catch (SQLException e) {
                logger.info("Insert into Payment Details table failed.");
                e.printStackTrace();
                return false;
            } finally {
                try {
                    pstmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }
    
    class saveRepairDetailsToTable extends DBTask<Boolean> {
        @Override
        protected Boolean call() throws Exception {
            Thread.sleep(1000);
            
            try (Connection con = getConnection()) {
                if (saveRepairDetails(con, blockTreeView.getSelectionModel().getSelectedItem().getValue(), rdMonthCombo.getSelectionModel().getSelectedItem().getMonth(), repairDetailsTextArea.getText(), materialText.getText(), labourText.getText(), miscText.getText(), getDateValueAsString(rdRepairDate.getValue()), LocalDate.now().getYear())) {
                    try {
                        File file = new File(excelFileLocation);
                        System.out.println(excelFileLocation);
                        createAndWriteRepairDetailsToExcelFile(file, blockTreeView.getSelectionModel().getSelectedItem().getValue(), rdMonthCombo.getValue().getMonth(), repairDetailsTextArea.getText(), materialText.getText(), labourText.getText(), miscText.getText(), getDateValueAsString(rdRepairDate.getValue()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
        
        private boolean saveRepairDetails (Connection con, String HouseNumber, String Month, String repairsDone, String materialCost, String labourCost, String miscellaneousCost, String repairDate, int Year) {
            logger.info("Inserting into Repairs Details table");
            try {
                String insertToRepairDetails = "INSERT INTO RepairDetailsTable(HouseNumber, Month, RepairsDone, MaterialCost, LabourCost, MiscellaneousExpenses, RepairsDateTime, Year) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                pstmt = con.prepareStatement(insertToRepairDetails);
                pstmt.setString(1, HouseNumber);
                pstmt.setString(2, Month);
                pstmt.setString(3, repairsDone);
                pstmt.setString(4, materialCost);
                pstmt.setString(5, labourCost);
                pstmt.setString(6, miscellaneousCost);
                pstmt.setString(7, repairDate);
                pstmt.setInt(8, Year);
                pstmt.execute();
            } catch (SQLException e) {
                logger.info("Insert into Repair Details table failed.");
                e.printStackTrace();
                return false;
            } finally {
                try {
                    pstmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
     }
    
    class saveReminderNoteTask extends DBTask<Boolean> {

        @Override
        protected Boolean call() throws Exception {
            try (Connection con = getConnection()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                saveReminderNoteToTable(con, blockTreeView.getSelectionModel().getSelectedItem().getValue(), stickyArea.getText(), pdMonthCombo.getValue(), LocalDateTime.now().format(formatter));

            }
            return true;
        }
        
        private boolean saveReminderNoteToTable(Connection con, String Housenumber, String ReminderNote, PDModel.Strings Month, String DateTime) {
            
            logger.info("Inserting into ReminderNote Table");
            try {
                String insertReminderTable = "INSERT INTO ReminderNoteTable(HouseNumber, RemNote, Month, DateTime) VALUES(?, ?, ?, ?)";
                pstmt = con.prepareStatement(insertReminderTable);
                pstmt.setString(1, Housenumber);
                pstmt.setString(2, ReminderNote);
                pstmt.setString(3, Month.name());
                pstmt.setString(4, DateTime);
                pstmt.execute();
            } catch (SQLException e) {
                logger.info("Insert into ReminderNoteTable failed.");
                return false;
            } finally {
                try {
                    pstmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }
    
    class  DeleteFromReminderNoteTableTask extends DBTask {
        @Override
        protected Void call() throws Exception {
            try (Connection con = getConnection()) {
                deleteFromReminderNoteTable(con, blockTreeView.getSelectionModel().getSelectedItem().getValue(), pdMonthCombo.getValue(), dateReminderArrayList2.get(0));
            }
            return null;
        }
        
        private boolean  deleteFromReminderNoteTable(Connection con, String houseNumber, PDModel.Strings month, String date) {
            logger.info("Deleting from ReminderNoteTable");
            
            try {
                String deleteReminderNote = "DELETE FROM ReminderNoteTable WHERE HouseNumber = ? AND Month = ? AND DateTime = ?";
                pstmt = con.prepareStatement(deleteReminderNote);
                pstmt.setString(1, houseNumber);
                pstmt.setString(2, month.name());
                pstmt.setString(3, date);
                pstmt.execute();
            } catch (SQLException e) {
                logger.info("Failed to delete from ReminderNoteTable failed");
                e.printStackTrace();
                return false;
            } finally {
                try {
                    pstmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }
    
    class UpdateReminderNoteTask extends DBTask {

        @Override
        protected Void call() throws Exception {
            try (Connection con = getConnection()) {
                updateReminderNoteTable(con, blockTreeView.getSelectionModel().getSelectedItem().getValue(), tempTextAreaArray.get(0).getText(), pdMonthCombo.getValue(), dateReminderArrayList2.get(0));
            }
            return null;
        }

        private boolean updateReminderNoteTable(Connection con, String houseNumber, String reminderNote, PDModel.Strings month, String DateTime) {
            logger.info("Updating ReminderNoteTable");

            try {
                String updateReminderNote = "UPDATE ReminderNoteTable SET RemNote = ? WHERE HouseNumber = ? AND Month = ? AND DateTime = ?";
                pstmt = con.prepareStatement(updateReminderNote);
                pstmt.setString(1, reminderNote);
                pstmt.setString(2, houseNumber);
                pstmt.setString(3, month.name());
                pstmt.setString(4, DateTime);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                logger.info("Failed to update ReminderNoteTable entry");
                e.printStackTrace();
                return false;
            } finally {
                try {
                    pstmt.close();
                    con.close();
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
                    con.close();
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
                if (vbox15.getChildren().contains(pdCashTextfield)) {
                    payMethodString = "Cash:".concat(pdCashTextfield.getText());
                } else if (vbox15.getChildren().contains(pdbankTextfield)) {
                    payMethodString = "Bank:".concat(pdbankTextfield.getText());
                } else if (vbox15.getChildren().contains(pdMpesaTextfield)) {
                    payMethodString = "Mpesa:".concat(pdMpesaTextfield.getText());
                }
                if (updatePaymentDetails(con, pdAmount.getText(), getDateValueAsString(pdPaymentDate.getValue()), payMethodString, payRowId)) {
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

        private boolean updatePaymentDetails(Connection con, String Amount, String PaymentDate, String paymentMethod, int rowID) {
            logger.info("Updating Payment Details");

            try {
                String updateAmountSql = "UPDATE PaymentDetails SET Amount = ?, PaymentDate = ?, PaymentMethod = ? WHERE RowID = ?";
                pstmt = con.prepareStatement(updateAmountSql);
                pstmt.setString(1, Amount);
                pstmt.setString(2, PaymentDate);
                pstmt.setString(3, paymentMethod);
                pstmt.setInt(4, rowID);
                pstmt.execute();
            } catch (SQLException e) {
                logger.info("Updating Payment Details table failed.");
                e.printStackTrace();
                return false;
            } finally {
                try {
                    pstmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }
    
    class UpdateRepairDetailsTableTask extends DBTask<Boolean> {
        @Override
        protected Boolean call() throws Exception {
            Thread.sleep(1000);
            
            try (Connection con = getConnection()) {
                System.out.println(repairRowId);
               if  (updateRepairDetailsTable(con, rdRepairsDone.getText(), materialText.getText(), labourText.getText(), miscText.getText(), getDateValueAsString(rdRepairDate.getValue()), repairRowId)) {
                   try {
                       System.out.println(excelFileLocation);
                       File file = new File(excelFileLocation);
                       updateRDExcelRowValue(file);
                   } catch (IOException e) {
                       e.printStackTrace();
                       return false;
                   }
               }
            }
            return true;
        }
        
        private boolean updateRepairDetailsTable (Connection con, String repairsDone, String materialCost, String labourCost, String miscExp, String repairsDateTime, int rowID) {
            logger.info("Updating RepairDetailsTable");
            
            try {
                String rdUpdateSql = "UPDATE RepairDetailsTable SET RepairsDone = ?, MaterialCost = ?, LabourCost = ?, MiscellaneousExpenses = ?, RepairsDateTime = ? WHERE RowID = ?";
                pstmt = con.prepareStatement(rdUpdateSql);
                pstmt.setString(1, repairsDone);
                pstmt.setString(2, materialCost);
                pstmt.setString(3, labourCost);
                pstmt.setString(4, miscExp);
                pstmt.setString(5, repairsDateTime);
                pstmt.setInt(6, rowID);
                pstmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(PDController.class.getName()).log(Level.SEVERE, null, ex);
                logger.info("Updating Repair Details Table failed");
                ex.printStackTrace();
                return false;
            } finally {
                try {
                    pstmt.close();
                    con.close();
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
    
    class DeleteFromRepairDetailsTask extends DBTask<Boolean> {

        @Override
        protected Boolean call() throws Exception {
            Thread.sleep(1000);

            try (Connection con = getConnection()) {
                if (deleteFromRepairDetailsTable(con, repairRowId)) {
                    try {
                        File file = new File(excelFileLocation);
                        removeRDExcelRow(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean deleteFromRepairDetailsTable(Connection con, int rowID) {
            logger.info("Deleteing from Repair Details Table");
            
            System.out.println(repairRowId);
            try {
                String deleteRepairDetails = "DELETE FROM RepairDetailsTable WHERE RowID = ?";
                pstmt = con.prepareStatement(deleteRepairDetails);
                pstmt.setInt(1, repairRowId);
                pstmt.execute();
            } catch (SQLException e) {
                logger.info("Failed to delete from Repair Details Table");
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
    
    /*class fillReceiptDetailsTask extends DBTask<JasperPrint> {
        @Override
        protected JasperPrint call() throws Exception {
            final String resourcePath = "C:\\Users\\bonyo\\OneDrive\\Desktop\\jat\\Rental-Application-Vertical-Menu\\src\\main\\resources\\Receipt\\RentReceipt.jrxml";
            
            HashMap map = new HashMap();
            map = getReceiptParameters();
            try (Connection con = getConnection()) {
                return fillReceiptDetails(resourcePath, map, con);
            }
        }
        
        private JasperPrint fillReceiptDetails(String resourcePath, HashMap map, Connection con) {
            logger.info("Started filling report");
            JasperPrint jasperPrint = new JasperPrint();
            
            try {
                JasperReport jasperreport = JasperCompileManager.compileReport(resourcePath);
                jasperPrint = JasperFillManager.fillReport(jasperreport, map, con);
            } catch (JRException e) {
                logger.info("An error occured during filling of report");
                
            }
            System.out.println(jasperPrint.getPages().get(1).getElements().get(1).getKey());
            System.out.println(jasperPrint.getPages().get(2));
            return jasperPrint;
        }
    }*/
    
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
                
                if (!schemaExists(con)) {
                    createSchema(con);
                } 
                
                if (!schemaExists2(con)) {
                    createSchema2(con);
                }
                
                if (!schemaExists3(con)) {
                    createSchema3(con);
                }
                
                if (!schemaExists4(con)) {
                    createSchema4(con);
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
                logger.info("TenantDetails not in DB. Create new one");
                return false;
            } finally {
                /*try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }*/
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
                logger.info("PaymentDetails not in DB. Create new one.");
                return false;
            } finally {
                /*try {
                    pstmt.closeOnCompletion();
                } catch (SQLException e) {
                    e.printStackTrace();
                }*/
            }
            return true;
        }
        
        public boolean schemaExists3(Connection con) {
            logger.info("Checking for schema3 existence");
            try {
                String searchRemNoteTable = "SELECT * FROM ReminderNoteTable";
                pstmt = con.prepareStatement(searchRemNoteTable);
                pstmt.executeQuery();
                logger.info("Schema3 Exists");
            } catch (SQLException e) {
                e.printStackTrace();
                logger.info("ReminderNoteTable not in DB. Create new one.");
                return false;
            } finally {
                /*try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }*/
            }
            return true;
        }
        
        public boolean schemaExists4(Connection con) {
            logger.info("CHecking for schema4 existence");
            try {
                String searchRepairDetailsTable = "SELECT * FROM RepairDetailsTable";
                pstmt = con.prepareStatement(searchRepairDetailsTable);
                pstmt.executeQuery();
                logger.info("Schema4 Exists");
            } catch (SQLException e) {
                e.printStackTrace();
                logger.info("RepairDetailsTable not in DB. Create new one.");
                return false;
            }
            return true;
        }
        
        private void createSchema(Connection con) {
            logger.info("Creating Tenant Details schema");
            
            String createTDSql = "CREATE TABLE IF NOT EXISTS TenantDetails(RowID Integer PRIMARY KEY AUTOINCREMENT, HouseNumber text UNIQUE CHECK(HouseNumber<>''), TenantName text CHECK(TenantName<>''), TenantPhoneNumber text, RentAmount text, Deposit text , DueDate text, MoveInDate text, MoveOutDate text, LeaseStartDate text, LeaseEndDate text) ";
            try {
                pstmt = con.prepareStatement(createTDSql);
                pstmt.execute();
                logger.info("Tenant Details");
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
            logger.info("Creating Payment Details schema");
            
            String createPDSql = "CREATE TABLE IF NOT EXISTS PaymentDetails(RowID Integer PRIMARY KEY AUTOINCREMENT, HouseNumber text, TenantName text CHECK(TenantName<>''), Amount text, PaymentMonth text, PaymentDate text, PaymentMethod text, Year integer, UNIQUE(HouseNumber, PaymentMonth, Year))";
            try {
                pstmt = con.prepareStatement(createPDSql);
                pstmt.execute();
                logger.info("Created Payment Details schema");
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
        
        private void createSchema3(Connection con) {
            logger.info("Creating Reminder Table schema");
            
            String createStickyTable = "CREATE TABLE IF NOT EXISTS ReminderNoteTable(HouseNumber text, RemNote text, Month text, DateTime text, UNIQUE(HouseNumber, Month, DateTime))";
            try {
                pstmt = con.prepareStatement(createStickyTable);
                pstmt.execute();
                logger.info("Created Reminder Table schema");
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
        
        private void createSchema4(Connection con) {
            logger.info("Creating Repair Details schema");
            
            String createRepairDetailsTable = "CREATE TABLE IF NOT EXISTS RepairDetailsTable(RowID Integer PRIMARY KEY AUTOINCREMENT, HouseNumber text, Month text, RepairsDone text, MaterialCost text, LabourCost, MiscellaneousExpenses text, RepairsDateTime text, Year integer, UNIQUE(HouseNumber, Month, RepairsDone, RepairsDateTime, Year))";
            try {
                pstmt = con.prepareStatement(createRepairDetailsTable);
                pstmt.execute();
                logger.info("Created Repair Details Schema");
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
