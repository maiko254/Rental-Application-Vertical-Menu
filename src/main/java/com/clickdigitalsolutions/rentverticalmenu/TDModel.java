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
public class TDModel {
    
    public StringProperty houseNumberTablePD;
    public StringProperty tenantNameTablePD;
    
    public TDModel(String houseNumber, String tenantName) {
        houseNumberTablePD = new SimpleStringProperty(houseNumber);
        tenantNameTablePD = new SimpleStringProperty(tenantName);
    }

    
    public String toString() {
        return this.gettenantNameTableTD();
    }

    public String gethouseNumberTableTD(){
        return houseNumberTablePD.get();
    }
    public void sethouseNumberTableTD(String value){
        houseNumberTablePD.set(value);
    }
    public StringProperty houseNumberTableTDProperty(){
        return houseNumberTablePD;
    }
    
    public String gettenantNameTableTD(){
        return tenantNameTablePD.get();
    }
    public void settenantNameTableTD(String value){
        tenantNameTablePD.set(value);
    }
    public StringProperty tenantNameTableTDProperty(){
        return tenantNameTablePD;
    }
}
