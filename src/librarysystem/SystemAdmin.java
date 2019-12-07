/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class SystemAdmin {
    
    
     private int id;
      
    private String userName;
    private String password;
   //--------------------------------------------------------------------
    
    
    
    

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }
    private String emailAddress;
    private String Name;
      private String NickName;
    
    Connection conn= null;
    ResultSet rs= null;
    PreparedStatement ps= null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
   
    
     public void authenticate( ){
    String sql="select * from user where username=? and password=?";
            
        try{
        conn=dbConnection.ConnectDB();
        ps=conn.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setString(2, password);
        rs=ps.executeQuery();
        if(rs.next()){
        JOptionPane.showMessageDialog(null, "Username and password are correct");
        adminPage mp=new adminPage();
        mp.setVisible(true);
        // dispose();
        }
        else{
        JOptionPane.showMessageDialog(null, "Username and password are not correct");
        }
        
     
        
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "error");
        }    
    }
        
        public void register (){
           try{
                      conn=dbConnection.ConnectDB();
        String sql ="insert into user (firstName,lastName,username,emailaddress,password) values (?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1, Name);
            ps.setString(2, NickName);
            ps.setString(3, userName);
            ps.setString(4, emailAddress);
            ps.setString(5, password);
     
           
            
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Saved");
            LoginInterface s= new LoginInterface();
            s.setVisible(true);
           
            
            }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            }
    
    }
    
}
