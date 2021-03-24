/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import DTO.User;
import Ultils.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author megap
 */
public class UserDAO {
    public User getUserByUsername(String username, String password){
        User output = null;
        try{
            Connection conn = Connector.Connector();
            String sql = "SELECT * FROM [SE1502_Workshop2_TranHongQuan].[dbo].[User] WHERE username =? AND password =?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet re = stmt.executeQuery();
            while(re.next()){
                output = new User(re.getString("username"), re.getString("password"), re.getString("fullname"));
            }
            return output;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
