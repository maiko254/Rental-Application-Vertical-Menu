package com.clickdigitalsolutions.rentverticalmenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author bonyo
 */
public class RTableViewController implements Initializable {
    
    @FXML
    private TableView<RModel> repairsTable; 
    @FXML
    private TableColumn<RModel, String> houseNumberCol;
    @FXML
    private TableColumn<RModel, String> tenantNameCol;
    @FXML
    private TableColumn<RModel, String> repairsDoneCol;
    @FXML
    private TableColumn<RModel, String> costofRepairCol;
    @FXML
    private TableColumn<RModel, String> dateofRepairCol;
    @FXML
    private TableColumn<RModel, String> miscellaneousCol;
    
    private final RController controller;
    
    public RTableViewController(RController subcontroller){
        controller = subcontroller;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        houseNumberCol.setCellValueFactory(cellData -> cellData.getValue().houseNumberTableRProperty());
        tenantNameCol.setCellValueFactory(cellData -> cellData.getValue().tenantNameTableRProperty());
        repairsDoneCol.setCellValueFactory(cellData -> cellData.getValue().repairsDoneTableRProperty());
        costofRepairCol.setCellValueFactory(cellData -> cellData.getValue().costofRepairsTableRProperty());
        dateofRepairCol.setCellValueFactory(cellData -> cellData.getValue().dateofRepairsTableRProperty());
        miscellaneousCol.setCellValueFactory(cellData -> cellData.getValue().miscellaneousTableRProperty());
        
        repairsTable.setItems(controller.getRepairsDetails());
    }    
    
}
