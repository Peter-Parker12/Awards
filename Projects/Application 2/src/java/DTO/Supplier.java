/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;

/**
 *
 * @author megap
 */
public class Supplier implements Serializable{
     
    private String supplierID;
    private String supplierName;
    private String description;

    public Supplier(String supplierID, String supplierName, String description) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.description = description;
    }

    public Supplier() {
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getDescription() {
        return description;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setDescription(String description) {
        this.description = description;
    } 
    
}
