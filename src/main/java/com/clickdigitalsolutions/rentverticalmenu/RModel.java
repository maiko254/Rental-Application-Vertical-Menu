/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickdigitalsolutions.rentverticalmenu;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author bonyo
 */
public class RModel {
    
    public enum Strings {
        ALL("All"),
        NONE(""),
        JANUARY("January"),
        FEBRUARY("February"),
        MARCH("March"),
        APRIL("April"),
        MAY("May"),
        JUNE("June"),
        JULY("July"),
        AUGUST("August"),
        SEPTEMBER("September"),
        OCTOBER("October"),
        NOVEMBER("November"),
        DECEMBER("December");
        
        private final String text;

        Strings(final String text) {
            this.text = text;
        }
        
        public String getMonth() {
            return text;
        }
    }
    
    public StringProperty houseNumberTableR;
    public StringProperty tenantNameTableR;
    public ObjectProperty<Strings> monthTablePD;
    public StringProperty repairsDoneTableR;
    public StringProperty costofRepairsTableR;
    public StringProperty dateofRepairsTableR;
    public StringProperty miscellaneousTableR;
    
    public RModel(String houseNumber, String tenantName, Strings month, String repairsDone, String costofRepair, String dateofRepairs, String miscellaneous){
       houseNumberTableR = new SimpleStringProperty(houseNumber);
       tenantNameTableR = new SimpleStringProperty(tenantName);
       monthTablePD = new SimpleObjectProperty<>(month);
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
    
    public Strings getmonthTablePD(){
        return this.monthTablePD.get();
    }
    public void setmonthTablePD(Strings value){
        monthTablePD.set(value);
    }
    public ObjectProperty<Strings> monthTablePDProperty(){
        return monthTablePD;
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
