/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Ultils.Connector;
import DTOS.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author megap
 */
public class Login {
    public boolean Login(User input){
        Connection conn = Connector.Connector();
        try{
            
            String query = "SELECT * FROM Login WHERE Username = ? AND Password =?";
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, input.getUsername());
            stmt.setString(2, input.getPassword());
            
            ResultSet re = stmt.executeQuery();
            
            while (re.next())
            {
                if (re.getString("Username").equals(input.getUsername()) && re.getString("Password").equals(input.getPassword()))
                {
                    input.setName(re.getString("UserInfo"));
                    conn.close();
                    return true;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean addUser(User user){
        Connection conn = Connector.Connector();
        try{

            String sql = "INSERT INTO Login VALUES (?, ?, ?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            
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
    
    public boolean searchUser(String username){
        Connection conn = Connector.Connector();
        try{
            String sql = "SELECT * FROM Login WHERE username=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, username);
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
}
