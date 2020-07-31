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
    public ObjectProperty<Strings> monthTableR;
    public StringProperty repairsDoneTableR;
    public StringProperty costofRepairsTableR;
    public StringProperty dateofRepairsTableR;
    public StringProperty miscellaneousTableR;
    
    public RModel(String houseNumber, Strings month, String repairsDone, String costofRepair, String dateofRepairs, String miscellaneous){
       houseNumberTableR = new SimpleStringProperty(houseNumber);
       monthTableR = new SimpleObjectProperty<>(month);
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
