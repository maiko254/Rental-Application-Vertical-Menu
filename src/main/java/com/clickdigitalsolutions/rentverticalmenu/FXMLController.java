package com.clickdigitalsolutions.rentverticalmenu;

import static com.clickdigitalsolutions.rentverticalmenu.TDController.getPrefferedCellStyle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.oasis.Utility;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.jdt.internal.compiler.batch.Main;

public class FXMLController implements Initializable {

    @FXML
    private JFXTabPane tabContainer;
    
    @FXML
    private StackPane mainStack;

    @FXML
    private GridPane tenantDetailsContainer;

    @FXML
    private GridPane repairsContainer;

    @FXML
    private Tab paymentDetailsTab;

    @FXML
    private GridPane paymentContainer;

    @FXML
    private Tab monthlyExpensesTab;

    @FXML
    private GridPane monthlyContainer;

    @FXML
    private MenuBar rentalMenu;

    @FXML
    private BorderPane motherAnchor;

    @FXML
    private MenuItem editTable;

    @FXML
    private MenuItem search;

    @FXML
    private MenuItem Import;
    
    @FXML
    private MenuItem Save;
    
    @FXML
    private MenuItem SaveAs;
    
    @FXML
    private MenuItem print;

    @FXML
    private MenuItem about;
    
    private double tabWidth = 90.0;
    public static int lastSelectedTabIndex = 0;

    String fxmlCheck;

    JFXButton addButton;
    
    private SearchFXMLController searchController;
    
    
    private BorderPane td;
    
    private SplitPane pd;
    
    private BorderPane rd;
    
    private SplitPane me;
    
    private TDController tdController;
    
    private PDController subcontroller;
    
    private ME2Controller expensesController;
    
    private Preferences prefs;
    
    private String loc = "Test1";
    
    private void configureView() {
        tabContainer.setTabMinWidth(tabWidth);
        tabContainer.setTabMaxWidth(tabWidth);
        tabContainer.setTabMinHeight(tabWidth);
        tabContainer.setTabMaxHeight(tabWidth);
        tabContainer.setRotateGraphic(true);

        EventHandler<Event> replaceBackgroundColorHandler = event -> {
            lastSelectedTabIndex = tabContainer.getSelectionModel().getSelectedIndex();

            Tab currentTab = (Tab) event.getTarget();
            if (currentTab.isSelected()) {
                currentTab.setStyle("-fx-background-color: -fx-focus-color;");
            } else {
                currentTab.setStyle("-fx-background-color: -fx-accent;");
            }
        };

        configureViewTab(paymentDetailsTab, "Payment\nDetails", "/images/icons8_sell_property_48px.png", replaceBackgroundColorHandler);
        configureViewTab(monthlyExpensesTab, "Monthly\nExpenses", "/images/icons8_overtime_48px.png", replaceBackgroundColorHandler);
    }
    
    private void configureViewTab(Tab tab, String title, String iconPath, EventHandler<Event> onSelectionChangedEvent){
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
    }

    private JFXButton createIconButton(double iconXLayout, double iconYLayout) {
        JFXButton iconButton = new JFXButton();

        iconButton.setPrefSize(28, 26);
        iconButton.setGraphic(GlyphsDude.createIconButton(MaterialIcon.ADD, "", "20", "12", ContentDisplay.GRAPHIC_ONLY));
        iconButton.setLayoutX(iconXLayout);
        iconButton.setLayoutY(iconYLayout);
        iconButton.setVisible(true);
        iconButton.setId("updateAmount");

        return iconButton;
    }

    @FXML
    private void searchAction() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/SearchFXML.fxml"));
        SearchFXMLController sub = new SearchFXMLController(this);
        loader.setController(sub);
        Parent root = loader.load();
        Scene searchScene = new Scene(root);
        Stage window = new Stage();
        window.setScene(searchScene);
        window.setTitle("Search...");
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
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
    
    private Map getReceiptParameters(){
        HashMap map = new HashMap();
        map.put("houseNumber", subcontroller.blockTreeView.getSelectionModel().getSelectedItem());
        map.put("PayMonth", subcontroller.monthComboPD.getSelectionModel().getSelectedItem());
        return map;
    }
    
    public void createExcelSheet(String hNo, String tName, String phoneNo, String monthlyRent, String deposit, String dueDate, String moveInDate, String moveOutDate, String leaseStartDate, String leaseEndDate) throws FileNotFoundException {
        File tenantDataExists = new File("jatom tenants.xls");
        if (tenantDataExists.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(tenantDataExists);
                Workbook workbookExists = WorkbookFactory.create(inputStream);

                Sheet sheet = workbookExists.getSheetAt(0);
                
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

                FileOutputStream outputStream = new FileOutputStream(tenantDataExists);
                workbookExists.write(outputStream);
                workbookExists.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Tenant Data");

            Font boldFont = workbook.createFont();
            boldFont.setBold(true);
            CellStyle headerRowStyle = workbook.createCellStyle();
            headerRowStyle.setFont(boldFont);

            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setFontHeightInPoints((short) 11);
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setBold(true);
            style.setFont(font);
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

            try {
                FileOutputStream out = new FileOutputStream(new File("jatom tenants.xls"));
                workbook.write(out);
                out.close();
                workbook.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configureView();
        
        FXMLLoader pdLoader  = new FXMLLoader();
        try {
            pd = pdLoader.load(getClass().getResourceAsStream("/fxml/PD2.fxml"));
            paymentDetailsTab.setContent(pd);
            subcontroller = (PDController)pdLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        FXMLLoader meLoader  = new FXMLLoader();
        try {
            me = meLoader.load(getClass().getResourceAsStream("/fxml/Monthly Expense.fxml"));
            monthlyExpensesTab.setContent(me);
            expensesController = (ME2Controller)meLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        rentalMenu.prefWidthProperty().bind(motherAnchor.widthProperty());

        
        print.setOnAction((event) -> {
            
            PDModel pdmodel = new PDModel();
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

            final String resourcePath = "/Receipt/Receipt.jasper";
            JasperPrint jasperPrint = null;

            try (InputStream reportStream = this.getClass().getResourceAsStream(resourcePath)) {
                jasperPrint = JasperFillManager.fillReport(reportStream, map);
            } catch (Exception e) {
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
        
        Import.setOnAction((event) -> {
            FileChooser excelFileChooser = new FileChooser();
            excelFileChooser.setTitle("Import Excel File");
            File file = excelFileChooser.showOpenDialog(motherAnchor.getScene().getWindow());
            
            if (file != null) {
                try {
                    subcontroller.readExcelFile(file);
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        SaveAs.setOnAction((event) -> {
            prefs = Preferences.userRoot().node(this.getClass().getName());
            try {
                if (prefs.get(loc, "Hello World").equals("Hello World")) {
                    FileChooser first = new FileChooser();
                    File initFile = first.showSaveDialog(motherAnchor.getScene().getWindow());
                    prefs.put(loc, initFile.getPath());
                    subcontroller.createTenantDetailsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), subcontroller.tdPhone.getText(), subcontroller.tdAmount.getText(), subcontroller.tdDeposit.getText(), subcontroller.tdDueDate.getText(), getDateValueAsString(subcontroller.tdMoveInDate.getValue()), getDateValueAsString(subcontroller.tdMoveOutDate.getValue()), getDateValueAsString(subcontroller.tdLeaseStartDate.getValue()), getDateValueAsString(subcontroller.tdLeaseEndDate.getValue()));
                    subcontroller.createExcelSheet(initFile, subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), subcontroller.tdPhone.getText(), subcontroller.tdAmount.getText(), subcontroller.tdDeposit.getText(), subcontroller.tdDueDate.getText(), getDateValueAsString(subcontroller.tdMoveInDate.getValue()), getDateValueAsString(subcontroller.tdMoveOutDate.getValue()), getDateValueAsString(subcontroller.tdLeaseStartDate.getValue()), getDateValueAsString(subcontroller.tdLeaseEndDate.getValue()));
                    subcontroller.createPaymentDetailsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), subcontroller.pdAmount.getText(), subcontroller.pdMonthCombo.getSelectionModel().getSelectedItem(), getDateValueAsString(subcontroller.pdPaymentDate.getValue()), subcontroller.pdPaymentOption.getText());
                    subcontroller.createRepairsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), subcontroller.rdRepairsDone.getText(), subcontroller.rdRepairCost.getText(), subcontroller.getDateValueAsString(subcontroller.rdRepairDate.getValue()), subcontroller.rdMiscCost.getText());
                    subcontroller.createAndWriteExcelSheet(initFile, subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.pdName.getText(), subcontroller.pdAmount.getText(), subcontroller.pdMonthCombo.getSelectionModel().getSelectedItem().name(), getDateValueAsString(subcontroller.pdPaymentDate.getValue()), subcontroller.pdPaymentOption.getText());
                } else if (!prefs.get(loc, "Hello World").equals("Hello World")) {
                    FileChooser secondChooser = new FileChooser();
                    File file = new File(prefs.get(loc, "Hello World"));
                    secondChooser.setInitialDirectory(file.getParentFile());
                    secondChooser.showSaveDialog(motherAnchor.getScene().getWindow());
                    subcontroller.createTenantDetailsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), subcontroller.tdPhone.getText(), subcontroller.tdAmount.getText(), subcontroller.tdDeposit.getText(), subcontroller.tdDueDate.getText(), getDateValueAsString(subcontroller.tdMoveInDate.getValue()), getDateValueAsString(subcontroller.tdMoveOutDate.getValue()), getDateValueAsString(subcontroller.tdLeaseStartDate.getValue()), getDateValueAsString(subcontroller.tdLeaseEndDate.getValue()));
                    subcontroller.createExcelSheet(file, subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), subcontroller.tdPhone.getText(), subcontroller.tdAmount.getText(), subcontroller.tdDeposit.getText(), subcontroller.tdDueDate.getText(), getDateValueAsString(subcontroller.tdMoveInDate.getValue()), getDateValueAsString(subcontroller.tdMoveOutDate.getValue()), getDateValueAsString(subcontroller.tdLeaseStartDate.getValue()), getDateValueAsString(subcontroller.tdLeaseEndDate.getValue()));
                    subcontroller.createPaymentDetailsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), subcontroller.pdAmount.getText(), subcontroller.pdMonthCombo.getSelectionModel().getSelectedItem(), getDateValueAsString(subcontroller.pdPaymentDate.getValue()), subcontroller.pdPaymentOption.getText());
                    subcontroller.createRepairsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), subcontroller.rdRepairsDone.getText(), subcontroller.rdRepairCost.getText(), subcontroller.getDateValueAsString(subcontroller.rdRepairDate.getValue()), subcontroller.rdMiscCost.getText());
                    subcontroller.createAndWriteExcelSheet(file, subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.pdName.getText(), subcontroller.pdAmount.getText(), subcontroller.pdMonthCombo.getSelectionModel().getSelectedItem().name(), getDateValueAsString(subcontroller.pdPaymentDate.getValue()), subcontroller.pdPaymentOption.getText());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        
        Save.setOnAction((event) -> {
            prefs = Preferences.userRoot().node(this.getClass().getName());

            try {
                if (paymentDetailsTab.isSelected()) {
                    if (prefs.get(loc, "Hello World").equals("Hello World")) {
                        FileChooser firtChooser = new FileChooser();
                        File fileLocation = firtChooser.showSaveDialog(motherAnchor.getScene().getWindow());
                        prefs.put(loc, fileLocation.getPath());
                        if (fileLocation != null) {
                            if (subcontroller.tenantDetails.isSelected()) {
                                subcontroller.createTenantDetailsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), subcontroller.tdPhone.getText(), subcontroller.tdAmount.getText(), subcontroller.tdDeposit.getText(), subcontroller.tdDueDate.getText(), getDateValueAsString(subcontroller.tdMoveInDate.getValue()), getDateValueAsString(subcontroller.tdMoveOutDate.getValue()), getDateValueAsString(subcontroller.tdLeaseStartDate.getValue()), getDateValueAsString(subcontroller.tdLeaseEndDate.getValue()));
                                subcontroller.createPaymentDetailsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), null, PDModel.Strings.CHOOSE, null, null);
                                subcontroller.createRepairsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), null, null, null, null);
                                subcontroller.createExcelSheet(fileLocation, subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), subcontroller.tdPhone.getText(), subcontroller.tdAmount.getText(), subcontroller.tdDeposit.getText(), subcontroller.tdDueDate.getText(), getDateValueAsString(subcontroller.tdMoveInDate.getValue()), getDateValueAsString(subcontroller.tdMoveOutDate.getValue()), getDateValueAsString(subcontroller.tdLeaseStartDate.getValue()), getDateValueAsString(subcontroller.tdLeaseEndDate.getValue()));
                            } else if (subcontroller.paymentDetails.isSelected()) {
                                subcontroller.createPaymentDetailsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.pdName.getText(), subcontroller.pdAmount.getText(), subcontroller.pdMonthCombo.getSelectionModel().getSelectedItem(), getDateValueAsString(subcontroller.pdPaymentDate.getValue()), subcontroller.pdPaymentOption.getText());
                                subcontroller.createAndWriteExcelSheet(fileLocation, subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.pdName.getText(), subcontroller.pdAmount.getText(), subcontroller.pdMonthCombo.getSelectionModel().getSelectedItem().name(), getDateValueAsString(subcontroller.pdPaymentDate.getValue()), subcontroller.pdPaymentOption.getText());
                            } else if (subcontroller.repairDetails.isSelected()) {
                                subcontroller.createRepairsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.rdName.getText(), subcontroller.rdRepairsDone.getText(), subcontroller.rdRepairCost.getText(), subcontroller.getDateValueAsString(subcontroller.rdRepairDate.getValue()), subcontroller.rdMiscCost.getText());
                            }
                        }
                    } else if (!prefs.get(loc, "Hello World").equals("Hello World")) {
                        File lastLoc = new File(prefs.get(loc, "Hello World"));
                        if (subcontroller.tenantDetails.isSelected()) {
                            subcontroller.createTenantDetailsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), subcontroller.tdPhone.getText(), subcontroller.tdAmount.getText(), subcontroller.tdDeposit.getText(), subcontroller.tdDueDate.getText(), getDateValueAsString(subcontroller.tdMoveInDate.getValue()), getDateValueAsString(subcontroller.tdMoveOutDate.getValue()), getDateValueAsString(subcontroller.tdLeaseStartDate.getValue()), getDateValueAsString(subcontroller.tdLeaseEndDate.getValue()));
                            subcontroller.createPaymentDetailsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), null, PDModel.Strings.CHOOSE, null, null);
                            subcontroller.createRepairsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), null, null, null, null);
                            subcontroller.createExcelSheet(lastLoc, subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.tdName.getText(), subcontroller.tdPhone.getText(), subcontroller.tdAmount.getText(), subcontroller.tdDeposit.getText(), subcontroller.tdDueDate.getText(), getDateValueAsString(subcontroller.tdMoveInDate.getValue()), getDateValueAsString(subcontroller.tdMoveOutDate.getValue()), getDateValueAsString(subcontroller.tdLeaseStartDate.getValue()), getDateValueAsString(subcontroller.tdLeaseEndDate.getValue()));
                        } else if (subcontroller.paymentDetails.isSelected()) {
                            subcontroller.createPaymentDetailsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.pdName.getText(), subcontroller.pdAmount.getText(), subcontroller.pdMonthCombo.getSelectionModel().getSelectedItem(), getDateValueAsString(subcontroller.pdPaymentDate.getValue()), subcontroller.payLabel.getText());
                            subcontroller.createAndWriteExcelSheet(lastLoc, subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.pdName.getText(), subcontroller.pdAmount.getText(), subcontroller.pdMonthCombo.getSelectionModel().getSelectedItem().name(), getDateValueAsString(subcontroller.pdPaymentDate.getValue()), subcontroller.pdPaymentOption.getText());
                        } else if (subcontroller.repairDetails.isSelected()) {
                            subcontroller.createRepairsTable(subcontroller.blockTreeView.getSelectionModel().getSelectedItem().getValue(), subcontroller.pdName.getText(), subcontroller.rdRepairsDone.getText(), subcontroller.rdRepairCost.getText(), subcontroller.getDateValueAsString(subcontroller.rdRepairDate.getValue()), subcontroller.rdMiscCost.getText());
                        } 
                    }
                } else if (monthlyExpensesTab.isSelected()) {
                    if (expensesController.elecExpenseTab.isSelected()) {
                        expensesController.createElecMonthlyExpensesTable(expensesController.monthTreeView.getSelectionModel().getSelectedItem().getValue(), expensesController.elecAmount.getText(), getDateValueAsString(expensesController.elecDate.getValue()), expensesController.elecUnits.getText());
                    } else if (expensesController.waterExpenseTab.isSelected()) {
                        expensesController.createWaterMonthlyExpensesTable(expensesController.monthTreeView.getSelectionModel().getSelectedItem().getValue(), expensesController.waterAmount.getText(), getDateValueAsString(expensesController.waterDate.getValue()), expensesController.waterUnits.getText());
                    } else if (expensesController.otherExpenseTab.isSelected()) {
                        expensesController.createOtherMonthlyExpensesTable(expensesController.monthTreeView.getSelectionModel().getSelectedItem().getValue(), expensesController.otherAmount.getText(), getDateValueAsString(expensesController.otherDate.getValue()), expensesController.otherReason.getText());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        
    }
}
