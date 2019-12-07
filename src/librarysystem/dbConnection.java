/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 */
public class dbConnection {
    
    
     Connection conn= null;
     ResultSet re= null;
    PreparedStatement pr= null;
    
    
       
      public static Connection ConnectDB (){
       
        try{
            Class.forName("com.mysql.jdbc.Driver");
           Connection conn =DriverManager.getConnection ("jdbc:mysql://127.0.0.1:3306/libraray", "root", "12345");
         // JOptionPane.showMessageDialog(null, "Connection Established");
             
                    return conn;
        }catch (Exception ex) {
        
        JOptionPane.showMessageDialog(null, ex);
        
        return null;
        
        }}
}
