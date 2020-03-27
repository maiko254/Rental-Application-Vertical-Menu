/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickdigitalsolutions.rentverticalmenu;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author bonyo
 */
public class RModel {
    public StringProperty houseNumberTableR;
    public StringProperty tenantNameTableR;
    public StringProperty repairsDoneTableR;
    public StringProperty costofRepairsTableR;
    public StringProperty dateofRepairsTableR;
    public StringProperty miscellaneousTableR;
    
    public RModel(String houseNumber, String tenantName, String repairsDone, String costofRepair, String dateofRepairs, String miscellaneous){
       houseNumberTableR = new SimpleStringProperty(houseNumber);
       tenantNameTableR = new SimpleStringProperty(tenantName);
       repairsDoneTableR = new SimpleStringProperty(repairsDone);
       costofRepairsTableR = new SimpleStringProperty(costofRepair);
       dateofRepairsTableR = new SimpleStringProperty(dateofRepairs);
       miscellaneousTableR = new SimpleStringProperty(miscellaneous);
    }
    
    public String gethouseNumberTableR(){
        return houseNumberTableR.get();
    }
    public void sethouseNumberTableR(String value){
        houseNumberTableR.set(value);
    }
    public StringProperty houseNumberTableRProperty(){
        return houseNumberTableR;
    }
    
    public String gettenantNameTableR(){
        return tenantNameTableR.get();
    }
    public void settenantNameTableR(String value){
        tenantNameTableR.set(value);
    }
    public StringProperty tenantNameTableRProperty(){
        return tenantNameTableR;
    }
    
    public String getrepairsDoneTableR(){
        return repairsDoneTableR.get();
    }
    public void setrepairsDoneTableR(String value){
        repairsDoneTableR.set(value);
    }
    public StringProperty repairsDoneTableRProperty(){
        return repairsDoneTableR;
    }
    
    public String getcostofRepairsTableR(){
        return costofRepairsTableR.get();
    }
    public void setcostofRepairsTableR(String value){
        costofRepairsTableR.set(value);
    }
    public StringProperty costofRepairsTableRProperty(){
        return costofRepairsTableR;
    }
    
    public String getdateofRepairsTableR(){
        return dateofRepairsTableR.get();
    }
    public void setdateofRepairsTableR(String value){
        dateofRepairsTableR.set(value);
    }
    public StringProperty dateofRepairsTableRProperty(){
        return dateofRepairsTableR;
    }
    
    public String getmiscellaneousTableR(){
        return miscellaneousTableR.get();
    }
    public void setmiscellaneousTableR(String value){
        miscellaneousTableR.set(value);
    }
    public StringProperty miscellaneousTableRProperty(){
        return miscellaneousTableR;
    }
}
