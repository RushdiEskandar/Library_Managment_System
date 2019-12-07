/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author zaidd
 */
public class tryy {
    
    public static void main(String[] args) {
        
        
    
  
        Connection conn= null;
    ResultSet rs= null;
    PreparedStatement ps= null;
            try{
                
                conn=dbConnection.ConnectDB();
                String sql= "select COUNT(BookStatus) as std  from book  ";
                String sql2= "select COUNT(BookStatus) as std  from book where BookStatus='borrowed' ";
                String sql1="select * from book where BookStatus='Borrowed' ";
                ps=conn.prepareStatement(sql);
                ps.execute();
                rs = ps.executeQuery(sql);
                
               while (rs.next()) 
               {
               int std = rs.getInt(1);
                 System.out.println(std);
                 
               System.out.println("the total number of  book is:"+std);
               }
                ps=conn.prepareStatement(sql2);
                ps.execute();
                rs = ps.executeQuery(sql2);
                
               while (rs.next()) 
               {
               int std = rs.getInt(1);
                 System.out.println(std);
                 
               System.out.println("the total number of borrowed book is: "+std);
               }
                 
                
             
               
               
               
               
                System.out.println("BookID, bookISNB, bookTitle, authorName, BookStatus");
                while (rs.next()){  
                String BookID = rs.getString("BookID");
                String bookISNB = rs.getString("bookISBN");
                String bookTitle = rs.getString("bookTitle");
                String authorName = rs.getString("authorName");
                String BookStatus = rs.getString("BookStatus");
                System.out.format("%s, %s, %s, %s, %s\n", BookID, bookISNB, bookTitle, authorName, BookStatus);
                }
                conn.close();
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
 }
     
}


