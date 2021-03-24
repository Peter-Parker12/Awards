/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ultils;

import DAOS.Login;
import java.util.regex.Pattern;

/**
 *
 * @author megap
 */
public class Validation {
    public static boolean checkPassword(String password){
        
        String regex = "^[\\S]+$";
        return password.matches(regex) && password.length()>=6;
    }
    
    public static boolean checkUsername(String username){
        Login checker = new Login();
        
        if(username.length()<6 || checker.searchUser(username)){
            return false;
        }
        return true;
    }    
}
