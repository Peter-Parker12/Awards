/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ultils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author megap
 */
public class Connector {
    public static Connection Connector(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String username = "root";
            String password = "Internationalism";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=SE1502_Workshop1_TranHongQuan";
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
