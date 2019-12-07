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
public class guest {
    
    private String  guestID;
    private String  firstName;
    private String  lastName;

    private String  gender;
    private String  emailAddress;
    private String  phoneNumber;
    private String  Address;
    
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

    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
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

   

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    
    public void registerGuest (){
           try{
           conn=dbConnection.ConnectDB();
           String sql ="insert into guest (firstName, lastName, gender,emailAddress, phoneNumber, Address) values (?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
        
            ps.setString(3, gender);
            ps.setString(4, emailAddress);
            ps.setString(5, phoneNumber);
            ps.setString(6, Address); 
            ps.execute();
            JOptionPane.showMessageDialog(null, "Saved");
            
            }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            }
    }
    
    public void DeleteGuest (){
            try{
                
                conn=dbConnection.ConnectDB();
                String sql="delete from guest where guestID=?";
                ps=conn.prepareStatement(sql);
                ps.setString(1, guestID);
                ps.execute();
                JOptionPane.showMessageDialog(null, "Deleted");
                conn.close();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
 }

    public void EditGuest(){ 
         
            String value1=guestID;
            String value2=firstName;
            String value3=lastName;
         
            String value4=gender;
            String value5= emailAddress;
            String value6=phoneNumber;
            String value7=Address;
           
           try{
           conn=dbConnection.ConnectDB();
           String sql="update guest set firstName ='"+value2+"',lastName='"+value3+"' ,gender='"+value4+"' ,emailAddress='"+value5+"' ,phoneNumber='"+value6+"' ,Address='"+value7+"' where guestID ='"+value1+"' ";
           ps=conn.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Updated");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
 }
    
    
    public void SearchGuest(){
        try{
            conn=dbConnection.ConnectDB();
            String sql= "select * from guest where (guestID LIKE ? OR firstName LIKE ? OR lastName LIKE ? )"; 
            ps=conn.prepareStatement(sql);
            ps.setString(1, searchTF);
            ps.setString(2, searchTF);
            ps.setString(3, searchTF);
            rs=ps.executeQuery();
            if(rs.next()){
                String add1=rs.getString("guestID");
                guestID=(add1);
                String add2=rs.getString("firstName");
                firstName=(add2);
                String add3=rs.getString("lastName");
                lastName=(add3);
                String add4=rs.getString("gender");
                gender=(add4);
                String add5=rs.getString("emailAddress");
                emailAddress=(add5);
                String add6=rs.getString("phoneNumber");
                phoneNumber=(add6);
                String add7=rs.getString("Address");
                Address=(add7);

            }
            
           
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            
        }
    }
    
    

    
            
    
}
