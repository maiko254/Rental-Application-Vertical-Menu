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
 * @author Mike254
 */
public class PDModel {
    public StringProperty houseNumberTablePD;
    public StringProperty tenantNameTablePD;
    public StringProperty amountTablePD;
    public StringProperty monthTablePD;
    public StringProperty paymentDateTablePD;
    public StringProperty paymentMethodPD;
    
    public PDModel(String houseNumber, String tenantName, String rentAmount, String monthPaid, String paymentDate, String paymentMethod){
        houseNumberTablePD = new SimpleStringProperty(houseNumber);
        tenantNameTablePD = new SimpleStringProperty(tenantName);
        amountTablePD = new SimpleStringProperty(rentAmount);
        monthTablePD = new SimpleStringProperty(monthPaid);
        paymentDateTablePD = new SimpleStringProperty(paymentDate);
    }
    
    @Override
    public String toString(){
        return tenantNameTablePD.get();
    }
    
    public PDModel(){
        
    }
    public String gethouseNumberTablePD(){
        return houseNumberTablePD.get();
    }
    public void sethouseNumberTablePD(String value){
        houseNumberTablePD.set(value);
    }
    public StringProperty houseNumberTablePDProperty(){
        return houseNumberTablePD;
    }
    
    public String gettenantNameTablePD(){
        return tenantNameTablePD.get();
    }
    public void settenantNameTablePD(String value){
        tenantNameTablePD.set(value);
    }
    public StringProperty tenantNameTablePDProperty(){
        return tenantNameTablePD;
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
    
    public String getmonthTablePD(){
        return monthTablePD.get();
    }
    public void setmonthTablePD(String value){
        monthTablePD.set(value);
    }
    public StringProperty monthTablePDProperty(){
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
