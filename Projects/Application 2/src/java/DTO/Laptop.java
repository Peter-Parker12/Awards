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
public class Laptop implements Serializable{
   private String latopID;
   private String laptopName;
   private String technicalInfo;
   private String yearOfManufacture;
   private String producer;
   private boolean status;
   private Supplier supplier;

    public Laptop() {
    }

    public Laptop(String latopID, String laptopName, String technicalInfo, String yearOfManufacture, String producer, boolean status, Supplier supplier) {
        this.latopID = latopID;
        this.laptopName = laptopName;
        this.technicalInfo = technicalInfo;
        this.yearOfManufacture = yearOfManufacture;
        this.producer = producer;
        this.status = status;
        this.supplier = supplier;
    }

    public String getLatopID() {
        return latopID;
    }

    public String getLaptopName() {
        return laptopName;
    }

    public String getTechnicalInfo() {
        return technicalInfo;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public String getProducer() {
        return producer;
    }

    public boolean isStatus() {
        return status;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setLatopID(String latopID) {
        this.latopID = latopID;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    public void setTechnicalInfo(String technicalInfo) {
        this.technicalInfo = technicalInfo;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
}
