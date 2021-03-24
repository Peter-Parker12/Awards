/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import DTOS.Category;
import Ultils.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author megap
 */
public class CategoryDAOS {
    public ArrayList<Category> getAllCategories(){
        ArrayList<Category> CategoryList = new ArrayList<Category>();
        Connection conn = Ultils.Connector.Connector();
        try{
            
            String query = "SELECT * FROM Categories";
            Statement stmt = conn.createStatement();
            
            ResultSet re = stmt.executeQuery(query);
            
            while (re.next())
            {
                String categoryName = re.getString("CategoryName");
                String cattegoryID = re.getString("CategoryID");
                Category temp = new Category(cattegoryID, categoryName);
                CategoryList.add(temp);
            }
            conn.close();
            return CategoryList;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public boolean addNewCategory(Category input){
        Connection conn = Connector.Connector();
        
        try{
            String sql = "INSERT INTO Categories VALUES(?, ?, ?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, input.getCategoryID());
            stmt.setString(2, input.getCategoryName());
            stmt.setString(3, input.getCategoryDescription());
            
            stmt.executeUpdate();
            conn.close();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
}
