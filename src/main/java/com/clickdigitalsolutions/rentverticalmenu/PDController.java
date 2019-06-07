package com.clickdigitalsolutions.rentverticalmenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TextFieldTreeCell;

/**
 * FXML Controller class
 *
 * @author Mike254
 */
public class PDController implements Initializable {
    
    @FXML
    private TitledPane houseComboTitledPanePD;
    
    @FXML
    private JFXComboBox blockAComboPD;
    
    @FXML
    private JFXComboBox blockBComboPD;
    
    @FXML
    private JFXComboBox blockCComboPD;
    
    @FXML
    private JFXComboBox nasraBlockPD;
    
    @FXML
    private JFXTextField tenantNamePD;
    
    @FXML
    private JFXTextField amountPD;
    
    @FXML
    private JFXComboBox monthComboPD;
    
    @FXML
    private JFXDatePicker rentPaymentDatePD;
    
    @FXML
    private JFXTreeView<String> paymentMethodPD;
    
    @FXML
    private Label rentArrears;
    
    
    String paymentMode;
    
    String comboboxPDCheck;
    
    boolean arrearsCheck = false;
     
    ObservableList<String>blockB = FXCollections.observableArrayList("B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12");
    ObservableList<String>blockA = FXCollections.observableArrayList("A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12");
    ObservableList<String>blockC = FXCollections.observableArrayList("C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10");
    ObservableList<String>nasraBlock = FXCollections.observableArrayList("Top House", "Bottom House");
    ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "Novenber", "December");
    
    String databaseURL = "jdbc:sqlite:C:\\Users\\Mike254\\Documents\\NetbeansProjects\\SQLite\\RVM.db";
    
    public void createPaymentDetailsTable(String HouseNumber, String TenantName, String Amount, String Month, String PaymentDate, String PaymentMethod){
        try {
            String createPDSql = "CREATE TABLE IF NOT EXISTS PaymentDetails(HouseNumber text, TenantName text, Amount text, Month text, PaymentDate text, PaymentMethod text, PRIMARY KEY(HouseNumber, PaymentDate), FOREIGN KEY(HouseNumber) REFERENCES TenantDetails(HouseNumber) ON DELETE CASCADE)";
            Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(createPDSql);
            pstmt.execute();
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
            pstmt.setString(4, Month);
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
    private void saveButtonActionPD(){
        if (comboboxPDCheck.equals("Block A")){
            createPaymentDetailsTable((String)blockAComboPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText(), amountPD.getText(), (String)monthComboPD.getSelectionModel().getSelectedItem(), getDateValueAsString(rentPaymentDatePD.getValue()) , paymentMode);
            setEmpty();
        }else if (comboboxPDCheck.equals("Block B")){
            createPaymentDetailsTable((String)blockBComboPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText(), amountPD.getText(), (String)monthComboPD.getSelectionModel().getSelectedItem(), getDateValueAsString(rentPaymentDatePD.getValue()), paymentMode);
            setEmpty();
        }else if (comboboxPDCheck.equals("Block C")){
            createPaymentDetailsTable((String)blockCComboPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText(), amountPD.getText(), (String)monthComboPD.getSelectionModel().getSelectedItem(), getDateValueAsString(rentPaymentDatePD.getValue()), paymentMode);
            setEmpty();
        }else if (comboboxPDCheck.equals("Nasra Block")){
            createPaymentDetailsTable((String)nasraBlockPD.getSelectionModel().getSelectedItem(), tenantNamePD.getText(), amountPD.getText(), (String)monthComboPD.getSelectionModel().getSelectedItem(), getDateValueAsString(rentPaymentDatePD.getValue()), paymentMode);
            setEmpty();
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
        monthComboPD.setValue(null);
        rentPaymentDatePD.setValue(null);
        cash.setValue("Cash recieved by:");
        root3.setExpanded(false);
        mpesa.setValue("Enter mpesa transaction code");
        root1.setExpanded(false);
        bank.setValue("Enter cheque no.");
        root2.setExpanded(false);
        rentArrears.setVisible(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        blockAComboPD.setItems(blockA);
        blockBComboPD.setItems(blockB);
        blockCComboPD.setItems(blockC);
        nasraBlockPD.setItems(nasraBlock);
        
        monthComboPD.setItems(months);
        
        
        
        houseComboTitledPanePD.setOnMouseClicked((event) -> {
            blockAComboPD.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String paymentModeString;
                try {
                    String searchPDSql = "SELECT * FROM PaymentDetails WHERE HouseNumber = ?";
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
                            if (arrear != 0 && rs.getString("Amount") != null) {
                                rentLabel("In Arrears: Ksh ", "In Arrears: Ksh " + Integer.toString(arrear));
                                System.out.println("In Arrears: Ksh " + Integer.toString(arrear));
                            } else if (arrear == 0 || arrear < 0) {
                                rentArrears.setVisible(false);
                            }
                            
                            amountPD.setText(rs.getString("Amount"));
                            monthComboPD.setValue(rs.getString("Month"));
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
                                        cash.setValue(paymentModeString);
                                        root3.setExpanded(true);
                                        break;
                                    case "Mpesa transaction code is":
                                        mpesa.setValue(paymentModeString);
                                        root1.setExpanded(true);
                                        break;
                                    case "Banker's cheque no":
                                        bank.setValue(paymentModeString);
                                        root2.setExpanded(true);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } while (rs.next());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                comboboxPDCheck = "Block A";
                Label label = new Label();
                label.setText((String)blockAComboPD.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: red;");
                houseComboTitledPanePD.setGraphic(label);
                houseComboTitledPanePD.setExpanded(false);
                System.out.print((String)blockAComboPD.getSelectionModel().getSelectedItem());
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
                            monthComboPD.setValue(rs.getString("Month"));
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
                                        cash.setValue(paymentModeString);
                                        root3.setExpanded(true);
                                        break;
                                    case "Mpesa transaction code is":
                                        mpesa.setValue(paymentModeString);
                                        root1.setExpanded(true);
                                        break;
                                    case "Banker's cheque no":
                                        bank.setValue(paymentModeString);
                                        root2.setExpanded(true);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } while (rs.next());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                comboboxPDCheck = "Block B";
                Label label = new Label();
                label.setText((String)blockBComboPD.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: red;");
                houseComboTitledPanePD.setGraphic(label);
                houseComboTitledPanePD.setExpanded(false);
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
                            monthComboPD.setValue(rs.getString("Month"));
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
                                        cash.setValue(paymentModeString);
                                        root3.setExpanded(true);
                                        break;
                                    case "Mpesa transaction code is":
                                        mpesa.setValue(paymentModeString);
                                        root1.setExpanded(true);
                                        break;
                                    case "Banker's cheque no":
                                        bank.setValue(paymentModeString);
                                        root2.setExpanded(true);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } while (rs.next());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                comboboxPDCheck = "Block C";
                Label label = new Label();
                label.setText((String)blockCComboPD.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: red;");
                houseComboTitledPanePD.setGraphic(label);
                houseComboTitledPanePD.setExpanded(false);
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
                            monthComboPD.setValue(rs.getString("Month"));
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
                                        cash.setValue(paymentModeString);
                                        root3.setExpanded(true);
                                        break;
                                    case "Mpesa transaction code is":
                                        mpesa.setValue(paymentModeString);
                                        root1.setExpanded(true);
                                        break;
                                    case "Banker's cheque no":
                                        bank.setValue(paymentModeString);
                                        root2.setExpanded(true);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            
                           
                        
                        } while (rs.next());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                comboboxPDCheck = "Nasra Block";
                Label label = new Label();
                label.setText((String)blockAComboPD.getSelectionModel().getSelectedItem());
                label.setStyle("-fx-text-fill: red;");
                houseComboTitledPanePD.setGraphic(label);
                houseComboTitledPanePD.setExpanded(false);
                System.out.print((String)blockAComboPD.getSelectionModel().getSelectedItem());
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
    }    
    
}
