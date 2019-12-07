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
 * @author
 */
public class librarian {
    
    private String librarianID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
    private String phoneNumber;
     private String searchTF;

    public String getSearchTF() {
        return searchTF;
    }

    public void setSearchTF(String searchTF) {
        this.searchTF = searchTF;
    }
    
    Connection conn= null;
    ResultSet rs= null;
    PreparedStatement ps= null;

    public String getLibrarianID() {
        return librarianID;
    }

    public void setLibrarianID(String librarianID) {
        this.librarianID = librarianID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void AddLibrarian (){
           try{
           conn=dbConnection.ConnectDB();
           String sql ="insert into librariann (firstName, lastName,username,password,emailAddress, phoneNumber) values (?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, emailAddress);
            ps.setString(6, phoneNumber);

            ps.execute();
            JOptionPane.showMessageDialog(null, "Saved");
            
            }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            }
    }
    
     public void EditLibrarian (){
         
            String value1=librarianID;
            String value2=firstName;
            String value3=lastName;
            String value4=username;
            String value5=password;
            String value6=emailAddress;
            String value7=phoneNumber;
            
           
           try{
           conn=dbConnection.ConnectDB();
           String sql="update librariann set firstName ='"+value2+"',lastName='"+value3+"' ,username='"+value4+"' ,password='"+value5+"' ,emailAddress='"+value6+"',phoneNumber='"+value7+"' where librarianID ='"+value1+"' ";
          ps=conn.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Updated");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
 }
    
    
 public void DeleteLibrarian (){
            try{
                
                conn=dbConnection.ConnectDB();
                String sql="delete from librariann where librarianID=?";
                ps=conn.prepareStatement(sql);
                ps.setString(1, librarianID);
                ps.execute();
                JOptionPane.showMessageDialog(null, "Deleted");
                conn.close();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
 }
 
 public void SearchLibrarian(){
        try{
            conn=dbConnection.ConnectDB();
            String sql= "select * from librariann where (librarianID LIKE ? OR firstName LIKE ? OR lastName LIKE ? OR username LIKE ? )"; 
            ps=conn.prepareStatement(sql);
            ps.setString(1, searchTF);
            ps.setString(2, searchTF);
            ps.setString(3, searchTF);
            ps.setString(4, searchTF);
            rs=ps.executeQuery();
            if(rs.next()){
                String add1=rs.getString("librarianID");
                librarianID=(add1);
                String add2=rs.getString("firstName");
                firstName=(add2);
                String add3=rs.getString("lastName");
                lastName=(add3);
                String add4=rs.getString("username");
                username=(add4);
                String add5=rs.getString("password");
                password=(add5);
                String add6=rs.getString("emailAddress");
                emailAddress=(add6);
                String add7=rs.getString("phoneNumber");
                phoneNumber=(add7);


            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            
        }
    }
     public void AuthenticateLibrarian( ){
    String sql="select * from librariann where username=? and password=?";
            
        try{
        conn=dbConnection.ConnectDB();
        ps=conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        rs=ps.executeQuery();
        if(rs.next()){
        JOptionPane.showMessageDialog(null, "Username and password are correct");
       librarianPage mp=new librarianPage();
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
    
    
}
