/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickdigitalsolutions.rentverticalmenu;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author bonyo
 */
public class RModel extends RecursiveTreeObject<RModel>{
    
    public enum Strings {
        Select("Select Month"),
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
    
    public ObjectProperty<Strings> monthTableR;
    public StringProperty repairsDoneTableR;
    public StringProperty materialCostofRepairsTableR;
    public StringProperty labourCostofRepairsTableR;
    public StringProperty dateofRepairsTableR;
    public StringProperty miscellaneousTableR;
    
    public RModel(Strings month, String repairsDone, String materialCost, String labourCost, String miscellaneous, String dateofRepairs){
       monthTableR = new SimpleObjectProperty<>(month);
       repairsDoneTableR = new SimpleStringProperty(repairsDone);
       materialCostofRepairsTableR = new SimpleStringProperty(materialCost);
       labourCostofRepairsTableR = new SimpleStringProperty(labourCost);
       dateofRepairsTableR = new SimpleStringProperty(dateofRepairs);
       miscellaneousTableR = new SimpleStringProperty(miscellaneous);
    }
    
    public Strings getMonthTableR() {
        return this.monthTableR.get();
    }
    public void setMonthTableR(Strings value) {
        monthTableR.set(value);
    }
    public ObjectProperty<Strings> monthTableRProperty() {
        return monthTableR;
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
    
    public String getMaterialCostofRepairsTableR(){
        return materialCostofRepairsTableR.get();
    }
    public void setMaterialCostofRepairsTableR(String value){
        materialCostofRepairsTableR.set(value);
    }
    public StringProperty materialCostofRepairsTableRProperty(){
        return materialCostofRepairsTableR;
    }
    
    public String getLabourCostofRepairsTableR(){
        return labourCostofRepairsTableR.get();
    }
    public void setLabourCostofRepairsTableR(String value){
        labourCostofRepairsTableR.set(value);
    }
    public StringProperty labourCostofRepairsTableRProperty(){
        return labourCostofRepairsTableR;
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
