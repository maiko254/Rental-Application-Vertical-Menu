/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickdigitalsolutions.rentverticalmenu;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Mike254
 */
public class PDModel extends RecursiveTreeObject<PDModel>{
    
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
    
    public StringProperty amountTablePD;
    public ObjectProperty<Strings> monthTablePD;
    public StringProperty paymentDateTablePD;
    public StringProperty paymentMethodPD;
    
    public PDModel(String rentAmount, Strings monthPaid, String paymentDate, String paymentMethod){
        amountTablePD = new SimpleStringProperty(rentAmount);
        monthTablePD = new SimpleObjectProperty<>(monthPaid);
        paymentDateTablePD = new SimpleStringProperty(paymentDate);
        paymentMethodPD = new SimpleStringProperty(paymentMethod);
    }
    
    @Override
    public String toString(){
        return amountTablePD.get() +","+ monthTablePD.get() +","+ paymentDateTablePD.get() +","+ paymentMethodPD.get();
    }
    
    public PDModel(){
        
    }
    
    public String getamountTablePD(){
        return amountTablePD.get();
    }
    public void setamountTablePD(String value){
        amountTablePD.set(value);
    }
    public StringProperty amountTablePDProperty(){
        return amountTablePD;
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
    
    public String getpaymentDateTablePD(){
        return paymentDateTablePD.get();
    }
    public void setpaymentDateTablePD(String value){
        paymentDateTablePD.set(value);
    }
    public StringProperty paymentDateTablePDProperty(){
        return paymentDateTablePD;
    }
    
    public String getpaymentMethodPD(){
        return paymentMethodPD.get();
    }
    public void setpaymentMethodPD(String value){
        paymentMethodPD.set(value);
    }
    public StringProperty paymentMethodPDProperty(){
        return paymentMethodPD;
    }
}
