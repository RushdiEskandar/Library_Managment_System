/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Vector;
import java.util.jar.Attributes.Name;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//class Name
public class book {
    //Attributes
    private String  BookID;
    private String  bookISBN;
    private String  bookTitle;
    private String  authorName;
    private String  BookStatus;
    private int TotalBook;
    private int TotalRegisteredGuest;
    private int TotalBorrowedBook;
    private int TotalAvailableBook;
    private String searchTF;
    
    //constructor
    /* book(String BookID, String  bookISBN, String  bookTitle, String authorName, String  BookStatus ){
    this.BookID=BookID;
    this.bookISBN=bookISBN;
    this.bookTitle=bookTitle;
    this.authorName=authorName;
    this.BookStatus=BookStatus;
    }*/
    
   
    
   
    

    public String getSearchTF() {
        return searchTF;
    }

    public void setSearchTF(String searchTF) {
        this.searchTF = searchTF;
    }
   
    
    /* book(String BookID, String  bookISBN, String  bookTitle, String authorName, String  BookStatus ){
    this.BookID=BookID;
this.bookISBN=bookISBN;
this.bookTitle=bookTitle;
this.authorName=authorName;
this.BookStatus=BookStatus;

    }*/
    
     Connection conn= null;
    ResultSet rs= null;
    PreparedStatement ps= null;

    public int getTotalRegisteredGuest() {
        return TotalRegisteredGuest;
    }
    public void setTotalRegisteredGuest(int TotalRegisteredGuest) {
        this.TotalRegisteredGuest = TotalRegisteredGuest;
    }
    public int getTotalBorrowedBook() {
        return TotalBorrowedBook;
    }
    public void setTotalBorrowedBook(int TotalBorrowedBook) {
        this.TotalBorrowedBook = TotalBorrowedBook;
    }
    public int getTotalAvailableBook() {
        return TotalAvailableBook;
    }
    public void setTotalAvailableBook(int TotalAvailableBook) {
        this.TotalAvailableBook = TotalAvailableBook;
    }
    public int getTotalBook() {
        return TotalBook;
    }
    public void setTotalBook(int TotalBook) {
        this.TotalBook = TotalBook;
    }
    public String getBookID() {
        return BookID;
    }
    public void setBookID(String BookID) {
        this.BookID = BookID;
    }
    public String getBookISBN() {
        return bookISBN;
    }
    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getBookStatus() {
        return BookStatus;
    }

    public void setBookStatus(String BookStatus) {
        this.BookStatus = BookStatus;
    }
     public void EditBook (){
         
            String value1=BookID;
            String value2=bookISBN;
            String value3=bookTitle;
            String value4= authorName;
            String value5=BookStatus;
           
           try{
           conn=dbConnection.ConnectDB();
           String sql="update book set bookISBN ='"+value2+"',bookTitle='"+value3+"' ,authorName='"+value4+"' ,BookStatus='"+value5+"' where BookID ='"+value1+"' ";
           ps=conn.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Updated");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
 }
 public void AddBook (){
           try{
           conn=dbConnection.ConnectDB();
           String sql ="insert into book (bookISBN,bookTitle,authorName,BookStatus) values (?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1, bookISBN);
            ps.setString(2, bookTitle);
            ps.setString(3, authorName);
            ps.setString(4, BookStatus); 
            ps.execute();
            JOptionPane.showMessageDialog(null, "Saved");
            }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            }
    }
 
 public void DeletBook (){
            try{
                
                conn=dbConnection.ConnectDB();
                String sql="delete from book where BookID=?";
                ps=conn.prepareStatement(sql);
                ps.setString(1, BookID);
                ps.execute();
                JOptionPane.showMessageDialog(null, "Deleted");
                conn.close();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
 }
 
 
  public void SearchBook(){
        try{
            conn=dbConnection.ConnectDB();
            String sql= "select * from book where (BookID LIKE ? OR bookISBN LIKE ? OR bookTitle LIKE ? OR authorName LIKE ? )"; 
            ps=conn.prepareStatement(sql);
            ps.setString(1, searchTF);
            ps.setString(2, searchTF);
            ps.setString(3, searchTF);
            ps.setString(4, searchTF);
            rs=ps.executeQuery();
            if(rs.next()){
                String add1=rs.getString("BookID");
                BookID=(add1);
                String add2=rs.getString("bookISBN");
                bookISBN=(add2);
                String add3=rs.getString("bookTitle");
                bookTitle=(add3);
                String add4=rs.getString("authorName");
                authorName=(add4);
                String add5=rs.getString("BookStatus");
                BookStatus=(add5);


            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            
        }
    }
  
  public void ReportTotalNumberOFBook(){
  
          try{
                conn=dbConnection.ConnectDB();
                String sql= "select COUNT(BookStatus) as TotalNumberOFBook  from book  ";
                ps=conn.prepareStatement(sql);
                ps.execute();
                rs = ps.executeQuery(sql);
               while (rs.next()) 
               {
               int TotalNumberOFBook=rs.getInt(1);
               TotalBook=(TotalNumberOFBook);
               }
                conn.close();
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
      
 }
  
  
  public void ReportTotalNumberOFBorroedBook(){
  
          try{
                conn=dbConnection.ConnectDB();
                String sql= "select COUNT(BookStatus) as TotalNumberOFBook  from book where BookStatus='Borrowed'  ";
                ps=conn.prepareStatement(sql);
                ps.execute();
                rs = ps.executeQuery(sql);
               while (rs.next()) 
               {
               int TotalNumberOFBook=rs.getInt(1);
               TotalBorrowedBook=(TotalNumberOFBook);
               }
                conn.close();
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
      
 }
  public void ReportTotalNumberOFAvailableBook(){
  
          try{
                conn=dbConnection.ConnectDB();
                String sql= "select COUNT(BookStatus) as TotalNumberOFBook  from book where BookStatus='Available'  ";
                ps=conn.prepareStatement(sql);
                ps.execute();
                rs = ps.executeQuery(sql);
               while (rs.next()) 
               {
               int TotalNumberOFBook=rs.getInt(1);
               TotalAvailableBook=(TotalNumberOFBook);
               }
                conn.close();
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
      
 }
  public void ReportTotalRegisteredGuest(){
  
          try{
                conn=dbConnection.ConnectDB();
                String sql= "select COUNT(guestID) as TotalNumberOFBook  from guest  ";
                ps=conn.prepareStatement(sql);
                ps.execute();
                rs = ps.executeQuery(sql);
               while (rs.next()) 
               {
               int TotalNumberOFBook=rs.getInt(1);
               TotalRegisteredGuest=(TotalNumberOFBook);
               }
                conn.close();
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
      
 }
  ;
    
 
    
    
}
 

