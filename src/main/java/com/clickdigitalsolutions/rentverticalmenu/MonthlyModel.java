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
public class MonthlyModel {
    public SimpleStringProperty monthME;
    public SimpleStringProperty amountPaidME;
    public SimpleStringProperty datePaidME;
    public SimpleStringProperty unitsConsumedME;
    
    public MonthlyModel (String month, String amountPaid, String datePaid, String unitsConsumed){
        monthME = new SimpleStringProperty(month);
        amountPaidME = new SimpleStringProperty(amountPaid);
        datePaidME = new SimpleStringProperty(datePaid);
        unitsConsumedME = new SimpleStringProperty(unitsConsumed);
    }
    
    public String getMonthME(){
        return monthME.get();
    }
    public void setMonthME(String value){
        monthME.set(value);
    }
    public StringProperty monthMEProperty(){
        return monthME;
    }
    public String getAmountPaidME(){
        return amountPaidME.get();
    }
    public void setAmountPaidME(String value){
        amountPaidME.set(value);
    }
    public StringProperty amountPaidMEProperty(){
        return amountPaidME;
    }
    public String getDatePaidME(){
        return datePaidME.get();
    }
    public void setDatePaidME(String value){
        datePaidME.set(value);
    }
    public StringProperty datePaidMEProperty(){
        return datePaidME;
    }
    public String getUnitsConsumedME(){
        return unitsConsumedME.get();
    }
    public void setUnitsConsumedME(String value){
        unitsConsumedME.set(value);
    }
    public StringProperty unitsConsumedMEProperty(){
        return unitsConsumedME;
    }
}
