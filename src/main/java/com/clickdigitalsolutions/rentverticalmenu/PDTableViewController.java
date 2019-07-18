package com.clickdigitalsolutions.rentverticalmenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import jfxtras.styles.jmetro8.JMetro;

/**
 * FXML Controller class
 *
 * @author Mike254
 */
public class PDTableViewController implements Initializable {

    @FXML
    public TableView<PDModel> paymentDetailsTable;
    @FXML
    public TableColumn<PDModel, String> houseNumberCol;
    @FXML
    public TableColumn<PDModel, String> tenantNameCol;
    @FXML
    public TableColumn<PDModel, String> rentPaidCol;
    @FXML
    public TableColumn<PDModel, String> monthCol;
    @FXML
    public TableColumn<PDModel, String> paymentDateCol;
    @FXML
    public TableColumn<PDModel, String> paymentMethodCol;
    @FXML
    public AnchorPane tableAnchor;
    @FXML
    public TextField searchTable;
    
    private final PDController controller;
    
    public PDTableViewController(PDController subcontroller){
        controller = subcontroller;
    }
    
    
    public void filterTenantList(String oldValue, String newValue){
        FilteredList<PDModel> tenantList = new FilteredList<>(controller.getPaymentDetails());
        ObservableList<PDModel> filteredList = FXCollections.observableArrayList();
        if (searchTable == null || (newValue.length() < oldValue.length()) || newValue == null || newValue.isEmpty()){
            paymentDetailsTable.setItems(null);;
        }else {
            newValue = newValue.toLowerCase();
            for(PDModel tenants : paymentDetailsTable.getItems()){
                String filterText = tenants.gettenantNameTablePD();
                if (filterText.contains(newValue)){
                    filteredList.add(tenants);
                }
            }
            paymentDetailsTable.setItems(filteredList);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PDModel mydata = new PDModel();
        houseNumberCol.setPrefWidth(200);
        paymentMethodCol.setPrefWidth(300);
        
        houseNumberCol.setCellValueFactory(cellData -> cellData.getValue().houseNumberTablePDProperty());
        tenantNameCol.setCellValueFactory(cellData -> cellData.getValue().tenantNameTablePDProperty());
        rentPaidCol.setCellValueFactory(cellData -> cellData.getValue().amountTablePDProperty());
        monthCol.setCellValueFactory(cellData -> cellData.getValue().monthTablePDProperty());
        paymentDateCol.setCellValueFactory(cellData -> cellData.getValue().paymentDateTablePDProperty());
        paymentMethodCol.setCellValueFactory(cellData -> cellData.getValue().paymentMethodPDProperty());
        
        paymentDetailsTable.setItems(controller.getPaymentDetails());
        paymentDetailsTable.setStyle("-fx-border-color: #E5E5E5; -fx-border-width: 1px; -fx-border-style: solid;");
        paymentDetailsTable.prefWidthProperty().bind(tableAnchor.widthProperty());
        
        searchTable.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTenantList(oldValue, newValue);
        });
    }    
    
}
