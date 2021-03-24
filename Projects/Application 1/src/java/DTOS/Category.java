/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOS;

/**
 *
 * @author megap
 */
public class Category {
    private String categoryID;
    private String categoryName;
    private String categoryDescription;

    public Category() {
    }

    public Category(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public Category(String categoryID, String categoryName, String categoryDescription) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
