/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import DTO.Laptop;
import DTO.Supplier;
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
public class laptopDaos {
    public ArrayList<Laptop> getAllLaptopBySupplierName(String supplierID){
        ArrayList<Laptop> laptopList = new ArrayList<Laptop>();
        
        try{
            Connection conn = Connector.Connector();
            
            String sql = "SELECT l.*, s.* FROM Laptops l, Suppliers s WHERE l.supplierID = s.supplierID AND s.supplierID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, supplierID);
            ResultSet re = stmt.executeQuery();
            
            while(re.next()){
                String laptopID = re.getString("laptopID");
                String laptopName = re.getString("laptopName");
                String yearOfManu = re.getString("yearOfManufacturing");
                String technicalInfo = re.getString("technicalInformation");
                String producer = re.getString("producer");;
                boolean status = re.getBoolean("status");
                String supplierName = re.getString("supplierName");
                String description = re.getString("description");
                
                Supplier supplier = new Supplier(supplierID, supplierName, description);
                Laptop tempo = new Laptop(laptopID, laptopName, technicalInfo, yearOfManu, producer, status, supplier);
                laptopList.add(tempo);
            }
            return laptopList;
        }        
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean addNewLaptop(Laptop input){
        try{
            Connection conn = Connector.Connector();
            
            String sql = "INSERT INTO laptops VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, input.getLatopID());
            stmt.setString(2, input.getLaptopName());
            stmt.setString(3, input.getTechnicalInfo());
            stmt.setString(4, input.getYearOfManufacture());
            stmt.setString(5, input.getProducer());
            stmt.setBoolean(6, input.isStatus());
            stmt.setString(7, input.getSupplier().getSupplierID());
            
            return stmt.executeUpdate()==1;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public Laptop getLaptopById(String id){
        try{
            Connection conn = Connector.Connector();
            String sql = "SELECT l.*, s.* FROM Laptops l, Suppliers s WHERE l.supplierID = s.supplierID AND l.laptopID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet re = stmt.executeQuery();
            Laptop tempo = null;
            
            while(re.next()){
                String laptopID = re.getString("laptopID");
                String laptopName = re.getString("laptopName");
                String yearOfManu = re.getString("yearOfManufacturing");
                String technicalInfo = re.getString("technicalInformation");
                String producer = re.getString("producer");;
                boolean status = re.getBoolean("status");
                String supplierName = re.getString("supplierName");
                String description = re.getString("description");
                String supplierID = re.getString("supplierID");
                
                Supplier supplier = new Supplier(supplierID, supplierName, description);
                tempo = new Laptop(laptopID, laptopName, technicalInfo, yearOfManu, producer, status, supplier);
            }
            return tempo;
                        
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateLaptop(Laptop input){
        try{
            Connection conn = Connector.Connector();
            String sql = "UPDATE Laptops SET laptopName=?, technicalInformation=?, yearofmanufacturing=?, producer=?, status=?, supplierID=? WHERE laptopID=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, input.getLaptopName());
            stmt.setString(2, input.getTechnicalInfo());
            stmt.setString(3, input.getYearOfManufacture());
            stmt.setString(4, input.getProducer());
            stmt.setBoolean(5, input.isStatus());
            stmt.setString(6, input.getSupplier().getSupplierID());
            stmt.setString(7, input.getLatopID());
            
            return stmt.executeUpdate()==1;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteLaptop(String id){
        try{
            Connection conn = Connector.Connector();
            String sql = "DELETE FROM Laptops WHERE laptopID=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, id);
            stmt.executeUpdate();
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
}
