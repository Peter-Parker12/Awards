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
public class User {
    private String username;
    private String name;
    private String password;

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }
    
    public String getPassword(){
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString() {
        return username; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
