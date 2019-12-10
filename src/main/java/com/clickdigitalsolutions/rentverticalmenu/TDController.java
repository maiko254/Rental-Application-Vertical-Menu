package com.clickdigitalsolutions.rentverticalmenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * FXML Controller class
 *
 * @author Mike254
 */
public class TDController implements Initializable {
    
    ObservableList<String>blockB = FXCollections.observableArrayList("B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12");
    ObservableList<String>blockA = FXCollections.observableArrayList("A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12");
    ObservableList<String>blockC = FXCollections.observableArrayList("C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10");
    ObservableList<String>nasraBlock = FXCollections.observableArrayList("Top House", "Bottom House");
    

    @FXML
    private JFXTextField tenantName;
    
    @FXML
    private JFXTextField tenantPhoneNumber;
    
    @FXML
    private JFXTextField monthlyRent;
    
    @FXML
    private JFXTextField houseDeposit;
    
    @FXML
    private JFXTextField dueDate;
    
    @FXML
    private JFXDatePicker moveInDate;
    
    @FXML
    private JFXDatePicker moveOutDate;
    
    @FXML
    private JFXDatePicker leaseStartDate;
    
    @FXML
    private JFXDatePicker leaseEndDate;
    
    @FXML
    private JFXButton saveButtonTD;
    
    @FXML
    private AnchorPane houseComboAnchorPane;
    
    @FXML
    private JFXComboBox blockACombo = new JFXComboBox(blockA);
    
    @FXML
    private JFXComboBox blockBCombo = new JFXComboBox(blockB);
    
    @FXML
    private JFXComboBox blockCCombo = new JFXComboBox(blockC);
    
    @FXML
    private JFXComboBox nasraBlockCombo = new JFXComboBox(nasraBlock);
    
    @FXML
    private Label houseStateLabel;
    
    @FXML
    private BorderPane TDAnchor;
    
    @FXML
    private TitledPane houseComboTitledPane;
    
    private String comboboxTDCheck = "Empty";
    
    String databaseURL = "jdbc:sqlite:C:\\NetbeansProjects\\SQLite\\RVM.db";
    
    private void createTenantDetailsTable(String houseNumber, String tenantName, String tenantPhoneNumber, String monthlyRent, String deposit, String dueDate, String moveInDate, String moveOutDate, String leaseStartDate, String leaseEndDate){
        String createTDSql = "CREATE TABLE IF NOT EXISTS TenantDetails(HouseNumber text PRIMARY KEY, TenantName text, TenantPhoneNumber text, RentAmount text, Deposit text , DueDate text, MoveInDate text, MoveOutDate text, LeaseStartDate text, LeaseEndDate text)";
        try {
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt =  conn.prepareStatement(createTDSql);
            pstmt.execute();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String insertTDSql = "INSERT INTO TenantDetails(HouseNumber, TenantName, TenantPhoneNumber, RentAmount, Deposit, DueDate, MoveInDate, MoveOutDate, LeaseStartDate, LeaseEndDate) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(insertTDSql);
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
            conn.close();
            pstmt.close();
        } catch (Exception e) {
            
        }
    }
    
    public ObservableList<TDModel> getTenantDetails() {
        ObservableList<TDModel> TDTableData = FXCollections.observableArrayList();
        try {
            String tableDataQuery = "SELECT * FROM TenantDetails";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(tableDataQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String houseNo = rs.getString("HouseNumber");
                String tenantName = rs.getString("TenantName");
                TDModel tenantData = new TDModel(houseNo, tenantName);
                TDTableData.add(tenantData);
            }
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TDTableData;
    }
    
    private void setEmpty(){
       tenantName.setText("");
       tenantPhoneNumber.setText("");
       monthlyRent.setText("");
       houseDeposit.setText("");
       dueDate.setText("");
       moveInDate.setValue(null);
       moveOutDate.setValue(null);
       leaseStartDate.setValue(null);
       leaseEndDate.setValue(null);
    }
    
    static CellStyle getPrefferedCellStyle(Cell cell) {
        CellStyle cellstyle = cell.getCellStyle();
        if ((cell instanceof HSSFCell && cellstyle.getIndex() == 15)) cellstyle = cell.getRow().getRowStyle();
        if (cellstyle == null) cellstyle = cell.getSheet().getColumnStyle(cell.getColumnIndex());
        if (cellstyle == null) cellstyle = cell.getCellStyle();
        return cellstyle;
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
                System.out.println(rowCount);

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
            tenantData.put("1", new Object[]{"House Number", "Tenant Name", "Phone Number", "Monthly Rent", "House Deposit", "Rent Due Date", "Move-In Date", "Move-Out Date", "Lease-Start Date", "Lease-End Date"});
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
        
    
    @FXML
    private void saveButtonActionTD() throws FileNotFoundException{
        if (comboboxTDCheck.equals("Block A")){
            createTenantDetailsTable((String)blockACombo.getSelectionModel().getSelectedItem(), tenantName.getText(), tenantPhoneNumber.getText(), monthlyRent.getText(), houseDeposit.getText(), dueDate.getText(), getDateValueAsString(moveInDate.getValue()), getDateValueAsString(moveOutDate.getValue()), getDateValueAsString(leaseStartDate.getValue()), getDateValueAsString(leaseEndDate.getValue()));
            subcontroller.createPaymentDetailsTable((String)blockACombo.getSelectionModel().getSelectedItem(), tenantName.getText(), null, null, null, null);
            repairscontroller.createRepairsTable((String)blockACombo.getSelectionModel().getSelectedItem(), tenantName.getText(), null, null, null, null);
            createExcelSheet((String)blockACombo.getSelectionModel().getSelectedItem(), tenantName.getText(), tenantPhoneNumber.getText(), monthlyRent.getText(), houseDeposit.getText(), dueDate.getText(), getDateValueAsString(moveInDate.getValue()), getDateValueAsString(moveOutDate.getValue()), getDateValueAsString(leaseStartDate.getValue()), getDateValueAsString(leaseEndDate.getValue()));
            setEmpty();
            blockACombo.setValue(null);
        }else if (comboboxTDCheck.equals("Block B")){
            createTenantDetailsTable((String)blockBCombo.getSelectionModel().getSelectedItem(), tenantName.getText(), tenantPhoneNumber.getText(), monthlyRent.getText(), houseDeposit.getText(), dueDate.getText(), getDateValueAsString(moveInDate.getValue()), getDateValueAsString(moveOutDate.getValue()), getDateValueAsString(leaseStartDate.getValue()), getDateValueAsString(leaseEndDate.getValue()));
            subcontroller.createPaymentDetailsTable((String)blockBCombo.getSelectionModel().getSelectedItem(), tenantName.getText(), null, null, null, null);
            repairscontroller.createRepairsTable((String)blockBCombo.getSelectionModel().getSelectedItem(), tenantName.getText(), null, null, null, null);
            setEmpty();
            blockBCombo.setValue(null);
        }else if (comboboxTDCheck.equals("Block C")){
            createTenantDetailsTable((String)blockCCombo.getSelectionModel().getSelectedItem(), tenantName.getText(), tenantPhoneNumber.getText(), monthlyRent.getText(), houseDeposit.getText(), dueDate.getText(), getDateValueAsString(moveInDate.getValue()), getDateValueAsString(moveOutDate.getValue()), getDateValueAsString(leaseStartDate.getValue()), getDateValueAsString(leaseEndDate.getValue()));
            subcontroller.createPaymentDetailsTable((String)blockCCombo.getSelectionModel().getSelectedItem(), tenantName.getText(), null, null, null, null);
            repairscontroller.createRepairsTable((String)blockCCombo.getSelectionModel().getSelectedItem(), tenantName.getText(), null, null, null, null);
            setEmpty();
            blockCCombo.setValue(null);
        }else if (comboboxTDCheck.equals("Nasra Block")){
            createTenantDetailsTable((String)nasraBlockCombo.getSelectionModel().getSelectedItem(), tenantName.getText(), tenantPhoneNumber.getText(), monthlyRent.getText(), houseDeposit.getText(), dueDate.getText(), getDateValueAsString(moveInDate.getValue()), getDateValueAsString(moveOutDate.getValue()), getDateValueAsString(leaseStartDate.getValue()), getDateValueAsString(leaseEndDate.getValue()));
            subcontroller.createPaymentDetailsTable((String)nasraBlockCombo.getSelectionModel().getSelectedItem(), tenantName.getText(), null, null, null, null);
            repairscontroller.createRepairsTable((String)nasraBlockCombo.getSelectionModel().getSelectedItem(), tenantName.getText(), null, null, null, null);
            setEmpty();
            nasraBlockCombo.setValue(null);
        }else if (comboboxTDCheck.equals("Empty")) {
            Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
            emptyAlert.setTitle("Error Dialog");
            emptyAlert.setHeaderText("Empty Field");
            emptyAlert.setContentText("House Number selection cannot be empty. Please select a house");
            emptyAlert.showAndWait();
        }
    }
    
    PDController subcontroller = new PDController();
    RController repairscontroller = new RController();
    
    
    
    public String getDateValueAsString(LocalDate dateConvert){
        String repairsDateString = null;
        if (dateConvert == null) {
            repairsDateString = null;
        } else if (dateConvert != null) {
            repairsDateString = dateConvert.format(DateTimeFormatter.ISO_DATE);
        }
        return repairsDateString;
    }
    
    public Boolean isValidName(String S){
        String regex = "[A-Za-z\\s]+";
        return S.matches(regex);
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        blockACombo.setItems(blockA);
        blockBCombo.setItems(blockB);
        blockCCombo.setItems(blockC);
        nasraBlockCombo.setItems(nasraBlock);
        
        houseComboTitledPane.setOnMouseClicked((event) -> {
            blockACombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    String searchTenantDetails = "SELECT * FROM TenantDetails WHERE HouseNumber = ?";
                    Connection conn = DriverManager.getConnection(databaseURL);
                    PreparedStatement pstmt = conn.prepareStatement(searchTenantDetails);
                    pstmt.setString(1, (String)blockACombo.getSelectionModel().getSelectedItem());
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()){
                        setEmpty();
                        houseStateLabel.setText("Vacant");
                        houseStateLabel.setTextFill(Color.web("#8B0618"));
                        houseStateLabel.setTextAlignment(TextAlignment.CENTER);
                        houseStateLabel.setStyle("-fx-background-color: #122949");
                        houseStateLabel.setVisible(true); 
                    }else{
                        do {                            
                          tenantName.setText(rs.getString("TenantName"));
                          tenantPhoneNumber.setText(rs.getString("TenantPhoneNumber"));
                          monthlyRent.setText(rs.getString("RentAmount"));
                          houseDeposit.setText(rs.getString("Deposit"));
                          dueDate.setText(rs.getString("DueDate"));
                          if (rs.getString("MoveInDate") != null){
                              moveInDate.setValue(LocalDate.parse(rs.getString("MoveInDate"), DateTimeFormatter.ISO_DATE));
                          }else
                              moveInDate.setValue(null);
                          if (rs.getString("MoveOutDate") != null){
                              moveOutDate.setValue(LocalDate.parse(rs.getString("MoveOutDate"), DateTimeFormatter.ISO_DATE));
                          }else
                              moveOutDate.setValue(null);
                          if (rs.getString("LeaseStartDate") != null){
                              leaseStartDate.setValue(LocalDate.parse(rs.getString("LeaseStartDate"), DateTimeFormatter.ISO_DATE));
                          }else
                              leaseStartDate.setValue(null);
                          if (rs.getString("LeaseEndDate") != null){
                              leaseEndDate.setValue(LocalDate.parse(rs.getString("LeaseEndDate"), DateTimeFormatter.ISO_DATE));
                          }else
                              leaseEndDate.setValue(null);
                          
                          houseStateLabel.setText("Occupied");
                          houseStateLabel.setTextFill(Color.web("#66F7FE"));
                          houseStateLabel.setTextAlignment(TextAlignment.CENTER);
                          houseStateLabel.setStyle("-fx-background-color: #122949");
                          houseStateLabel.setVisible(true);
                        } while (rs.next());
                    }
                    pstmt.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Label label = new Label();
                label.setText((String)blockACombo.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: #fdfdfd");
                houseComboTitledPane.setText("");
                houseComboTitledPane.setGraphic(label);
                comboboxTDCheck = "Block A";
                houseComboTitledPane.setExpanded(false);
            });
            blockBCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                Label label = new Label();
                label.setText((String)blockBCombo.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: #fdfdfd");
                houseComboTitledPane.setText("");
                houseComboTitledPane.setGraphic(label);
                comboboxTDCheck = "Block B";
                houseComboTitledPane.setExpanded(false);
            });
            blockCCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                Label label = new Label();
                label.setText((String)blockCCombo.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: #fdfdfd");
                houseComboTitledPane.setText("");
                houseComboTitledPane.setGraphic(label);
                comboboxTDCheck = "Block C";
                houseComboTitledPane.setExpanded(false);
            });
            nasraBlockCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                Label label = new Label();
                label.setText((String)nasraBlockCombo.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: #fdfdfd");
                houseComboTitledPane.setText("");
                houseComboTitledPane.setGraphic(label);
                comboboxTDCheck = "Nasra Block";
                houseComboTitledPane.setGraphic(label);
            });
        });
        
        houseStateLabel.setVisible(false); 
        TDAnchor.setOnMouseClicked((event) -> {
            houseComboTitledPane.setExpanded(false);
        });
        
        Tooltip v = new Tooltip("Only letters and whitespace is allowed");
        tenantName.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            tenantName.textProperty().addListener((observable, oldValue, newValue) -> {
                tenantName.setOnKeyReleased((even) -> {
                    if (!isValidName(newValue)) {
                        v.show(tenantName, event.getScreenX(), event.getScreenY());
                    } else 
                        if (v.isShowing()){
                            v.hide();
                        }
                });
            });
        });
        v.setAutoHide(true);
    }    
    
}
