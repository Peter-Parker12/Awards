/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import DTOS.Book;
import DTOS.Category;
import Ultils.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author megap
 */
public class BookDAOS {
    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> bookList = new ArrayList<Book>();
        Connection conn = Ultils.Connector.Connector();
        try{
            String query = "SELECT b.*, c.CategoryName FROM Books b, Categories c WHERE b.CategoryID = c.CategoryID ORDER BY c.CategoryName";
            
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next())
            {
                String bookID = rs.getString("bookID");
                String bookName = rs.getString("Name");
                String bookAuthor = rs.getString("Author");
                String bookPublishingDate = rs.getString("PublishYear");
                String bookDescription = rs.getString("ShortDescription");
                boolean bookStatus = rs.getBoolean("Status");
                String bookCategory = rs.getString("CategoryName");
                
                Book book = new Book(bookID, bookName, bookAuthor, bookCategory, bookPublishingDate, bookDescription, bookStatus);
                bookList.add(book);
            }
            conn.close();
            return bookList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean addNewBook(Book input){
        Connection conn = Ultils.Connector.Connector();
        try{
            
            String sql = "INSERT INTO Books VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, input.getBookID());
            stmt.setString(2, input.getBookName());
            stmt.setString(3, input.getBookAuthor());
            stmt.setString(4, input.getBookPublishingDate());
            stmt.setString(5, input.getBookDescription());
            stmt.setBoolean(6, input.isBookStatus());
            stmt.setString(7, input.getBookCategory());
            
            stmt.executeUpdate();
            conn.close();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public Book getBook(String bookID){
        Connection conn = Ultils.Connector.Connector();
        try{
            
            String sql = "SELECT b.*, c.CategoryName FROM Books b, Categories c WHERE BookID = ? AND b.CategoryID = c.CategoryID";
            Book output = new Book();            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, bookID);
            ResultSet re = stmt.executeQuery();
            while(re.next()){
                output.setBookAuthor(re.getString("Author"));
                output.setBookCategory(re.getString("CategoryName"));
                output.setBookDescription(re.getString("ShortDescription"));
                output.setBookID(re.getString("bookID"));
                output.setBookPublishingDate(re.getString("PublishYear"));
                output.setBookStatus(re.getBoolean("Status"));
                output.setBookName(re.getString("Name"));
            }
            conn.close();
            return output;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateBook (Book input){
        Connection conn = Ultils.Connector.Connector();
        try{
            
            String sql = "UPDATE Books SET Name=?, Author=?, shortDescription=?, PublishYear=?, status=?, categoryID=? WHERE BookID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, input.getBookName());
            stmt.setString(2, input.getBookAuthor());
            stmt.setString(3, input.getBookDescription());
            stmt.setString(4, input.getBookPublishingDate());
            stmt.setBoolean(5, input.isBookStatus());
            stmt.setString(6, input.getBookCategory());
            stmt.setString(7, input.getBookID());
            
            stmt.executeUpdate();
            conn.close();
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean deleteBook(String bookID){
        Connection conn = Ultils.Connector.Connector();
        try{

            String sql = "DELETE FROM Books WHERE BookID = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, bookID);
            
            stmt.executeUpdate();
            conn.close();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean searchBook(String bookID){
        Connection conn = Connector.Connector();
        try{
            String sql = "SELECT * FROM Books WHERE BookID =?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, bookID);
            ResultSet re = stmt.executeQuery();
            if(re.next()){
                conn.close();
                return true;
            }            
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<Book> getBookByCategory(String categoryName){
        ArrayList<Book> bookByCategory = new ArrayList<Book>();
        Connection conn = Connector.Connector();
        
        try{
            String sql = "SELECT b.*, c.CategoryName FROM Books b, Categories c WHERE b.categoryID = c.categoryID AND c.CategoryName = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, categoryName);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                String bookID = rs.getString("bookID");
                String bookName = rs.getString("Name");
                String bookAuthor = rs.getString("Author");
                String bookPublishingDate = rs.getString("PublishYear");
                String bookDescription = rs.getString("ShortDescription");
                boolean bookStatus = rs.getBoolean("Status");
                String bookCategory = rs.getString("CategoryName");
                
                Book book = new Book(bookID, bookName, bookAuthor, bookCategory, bookPublishingDate, bookDescription, bookStatus);
                bookByCategory.add(book);
            }
            conn.close();
            return bookByCategory;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}