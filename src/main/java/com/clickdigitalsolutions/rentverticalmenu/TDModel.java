/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickdigitalsolutions.rentverticalmenu;

/**
 *
 * @author bonyo
 */
public class TDModel {

    public String tenantNameTD;

    public TDModel(String tenantName) {
        this.tenantNameTD = tenantName;
    }

    
    public String toString() {
        return this.getTenantNameTD();
    }

    public String getTenantNameTD() {
        return tenantNameTD;
    }

}
