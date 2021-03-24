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
public class Book {
    private String bookID;
    private String bookName;
    private String bookAuthor;
    private String bookCategory;
    private String bookPublishingDate;
    private String bookDescription;
    private boolean bookStatus;

    public Book(String bookID, String bookName, String bookAuthor, String bookCategory, String bookPublishingDate, String bookDescription, boolean bookStatus) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookCategory = bookCategory;
        this.bookPublishingDate = bookPublishingDate;
        this.bookDescription = bookDescription;
        this.bookStatus = bookStatus;
    }

    public Book(String bookID, String bookName, String bookAuthor, String bookCategory, String bookPublishingDate, String bookDescription) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookCategory = bookCategory;
        this.bookPublishingDate = bookPublishingDate;
        this.bookDescription = bookDescription;
    }
    
    public Book() {
    }

    public String getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public String getBookPublishingDate() {
        return bookPublishingDate;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public void setBookPublishingDate(String bookPublishingDate) {
        this.bookPublishingDate = bookPublishingDate;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "Book{" + "bookID=" + bookID + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookCategory=" + bookCategory + ", bookPublishingDate=" + bookPublishingDate + ", bookDescription=" + bookDescription + ", bookStatus=" + bookStatus + '}';
    }
    
}
