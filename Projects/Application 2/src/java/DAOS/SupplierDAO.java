/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import DTO.Supplier;
import Ultils.Connector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author megap
 */
public class SupplierDAO {
    public ArrayList<Supplier> getAllSupplier(){
        ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
        
        try{
            Connection conn = Connector.Connector();
            
            String sql = "SELECT * FROM Suppliers";
            Statement stmt = conn.createStatement();
            ResultSet re = stmt.executeQuery(sql);
            
            while(re.next()){
                Supplier input = new Supplier(re.getString("supplierID"), re.getString("supplierName"), re.getString("description"));
                supplierList.add(input);
            }
            return supplierList;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        SupplierDAO dao =  new SupplierDAO();
        
        for (Supplier supplier : dao.getAllSupplier()) {
            System.out.println("1");
        }
    }
}
