/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementkiosk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 63915
 */
public class LibrarianHomepageQuery {
    
    public void insertBook (LibrarianHomepage inf) {
 
        Connection con= (Connection) myConnection.getConnection();
        PreparedStatement ps;
            
        
            try {
            ps = (PreparedStatement) con.prepareStatement("INSERT INTO books(book_title, author, type, subject, quantity) VALUES (?,?,?,?,?)");
            ps.setString(1, inf.getBook_title());
            ps.setString(2, inf.getAuthor());
            ps.setString(3, inf.getType());
            ps.setString(4, inf.getSubject());
            ps.setInt(5, inf.getQuantity());

            if(ps.executeUpdate() !=0){
                JOptionPane.showMessageDialog(null, "New Book Added", "Message", JOptionPane.INFORMATION_MESSAGE);

                    }
                    else {
                      JOptionPane.showMessageDialog(null, "Something is wrong", "Message", JOptionPane.ERROR_MESSAGE);  

                    }

                } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE,null,ex);
            }
              
        }
    
    public void updateBook(LibrarianHomepage inf, boolean withImage){
        Connection con= (Connection) myConnection.getConnection();
        PreparedStatement ps;
        String updateQuery = "";
        if(withImage == true) //update the profile picture
        {
            updateQuery = "UPDATE books set book_title = ?, author = ?, type = ?, subject = ?, quantity = ? WHERE book_id = ?"; 
        
             try {
            ps = (PreparedStatement) con.prepareStatement(updateQuery);
            ps.setString(1, inf.getBook_title());
            ps.setString(2, inf.getAuthor());
            ps.setString(3, inf.getType());
            ps.setString(4, inf.getSubject());
            ps.setInt(5, inf.getQuantity());
            ps.setInt(6, inf.getBook_id());
            

            if(ps.executeUpdate() !=0){
                JOptionPane.showMessageDialog(null, "Book Updated", "Message", JOptionPane.INFORMATION_MESSAGE);

                }
                else{
                JOptionPane.showMessageDialog(null, "Something is wrong", "Message", JOptionPane.ERROR_MESSAGE);  

                }

            } catch (SQLException ex) {
               //Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE,null,ex);
               JOptionPane.showMessageDialog(null, "Cannot Be Updated! The Book is Borrowed Currently.", "Message", JOptionPane.ERROR_MESSAGE);
            }   
            
        } 
        /*else{ // keeping the same profile picture
            updateQuery = "UPDATE product set Product_Name = ?, Category= ?, Quantity = ?, Price = ?, Mfg_Date = ?, Expiry_Date = ? WHERE Product_ID = ?"; 
        
             try {
            ps = (PreparedStatement) con.prepareStatement(updateQuery);
            
            ps.setString(1, inf.getProduct_Name());
            ps.setString(2, inf.getCategory());
            ps.setInt(3, inf.getQuantity());
            ps.setInt(4, inf.getPrice());
            ps.setString(5, inf.getMfg_Date());
            ps.setString(6, inf.getExpiry_Date());
            ps.setInt(7, inf.getProduct_ID());

            if(ps.executeUpdate() !=0){
                JOptionPane.showMessageDialog(null, "Product Updated", "Message", JOptionPane.INFORMATION_MESSAGE);

                }
                else{
                JOptionPane.showMessageDialog(null, "Something is wrong", "Message", JOptionPane.ERROR_MESSAGE);  

                }

            } catch (SQLException ex) {
               Logger.getLogger(HomepageQuery.class.getName()).log(Level.SEVERE,null,ex);
            }   
            
        } */
    }
    public void deleteBook(int book_id) {
 
        Connection con= (Connection) myConnection.getConnection();
        PreparedStatement ps;
        
            try {
            ps = (PreparedStatement) con.prepareStatement("DELETE FROM books WHERE books.book_id = ?");
            ps.setInt(1, book_id);

            if(ps.executeUpdate() !=0){
                JOptionPane.showMessageDialog(null, "Book Deleted", "Message", JOptionPane.INFORMATION_MESSAGE);

                    }
                    else{
                      JOptionPane.showMessageDialog(null, "Something is wrong", "Message", JOptionPane.ERROR_MESSAGE);  

                    }

                } catch (SQLException ex) {
            //Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE,null,ex);
            JOptionPane.showMessageDialog(null, "Cannot Be Deleted! The Book is Borrowed Currently.", "Message", JOptionPane.ERROR_MESSAGE);
            }
              
        }
                
    
    
        
    // list of info BOOKS
    public ArrayList<LibrarianHomepage> InfoList(){
     
        ArrayList<LibrarianHomepage> ilist = new ArrayList<LibrarianHomepage>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT book_id, book_title, author, type, subject, quantity, book_id, book_title, author, type, subject, quantity FROM books ORDER BY book_title ASC");
            
            LibrarianHomepage inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage (rs.getInt("book_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),	
                                rs.getString("subject"),
                                rs.getInt("quantity"));
                       
                ilist.add(inf);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
    
    /*public void updateLibrarian(LibrarianAccount inf, boolean withImage){
        Connection con= (Connection) myConnection.getConnection();
        PreparedStatement ps;
        String updateQuery = "";
        if(withImage == true) //update the profile picture
        {
            updateQuery = "UPDATE librarianlogin set first_name = ?, last_name = ?, username = ?, password = ? WHERE ID= ?"; 
        
             try {
            ps = (PreparedStatement) con.prepareStatement(updateQuery);
            ps.setString(1, inf.getFirst_name());
            ps.setString(2, inf.getLast_name());
            ps.setString(3, inf.getUsername());
            ps.setString(4, inf.getPassword());
            ps.setInt(5, inf.getID());

            if(ps.executeUpdate() !=0){
                JOptionPane.showMessageDialog(null, "Your Information is Updated", "Message", JOptionPane.INFORMATION_MESSAGE);

                }
                else{
                JOptionPane.showMessageDialog(null, "Something is wrong", "Message", JOptionPane.ERROR_MESSAGE);  

                }

            } catch (SQLException ex) {
               Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE,null,ex);
            }   
            
        }
    }
    
     public ArrayList<LibrarianAccount> InfoList2(){
     
        ArrayList<LibrarianAccount> ilist = new ArrayList<LibrarianAccount>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT ID,first_name, last_name, username, password FROM librarianlogin");
            
            LibrarianAccount inf;
            
            while(rs.next()){
                inf = new LibrarianAccount(rs.getInt("ID"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),	
                                rs.getString("username"),
                                rs.getString("password"));
                ilist.add(inf);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }*/
     
     
     
     // list of info MATH
    public ArrayList<LibrarianHomepage> InfoList3(){
     
        ArrayList<LibrarianHomepage> ilist = new ArrayList<LibrarianHomepage>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT book_id, book_title, author, type, subject, quantity, book_id, book_title, author, type, subject, quantity FROM books where subject = 'math' ORDER BY book_title ASC");
            
            LibrarianHomepage inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage (rs.getInt("book_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),	
                                rs.getString("subject"),
                                rs.getInt("quantity"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
     // list of info ENGLISH
    public ArrayList<LibrarianHomepage> InfoList4(){
     
        ArrayList<LibrarianHomepage> ilist = new ArrayList<LibrarianHomepage>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT book_id, book_title, author, type, subject, quantity, book_id, book_title, author, type, subject, quantity FROM books where subject = 'english' ORDER BY book_title ASC");
            
            LibrarianHomepage inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage (rs.getInt("book_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),	
                                rs.getString("subject"),
                                rs.getInt("quantity"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
     // list of info SCIENCE
    public ArrayList<LibrarianHomepage> InfoList5(){
     
        ArrayList<LibrarianHomepage> ilist = new ArrayList<LibrarianHomepage>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT book_id, book_title, author, type, subject, quantity, book_id, book_title, author, type, subject, quantity FROM books where subject = 'science' ORDER BY book_title ASC");
            
            LibrarianHomepage inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage (rs.getInt("book_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),	
                                rs.getString("subject"),
                                rs.getInt("quantity"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
     // list of info FILIPINO
    public ArrayList<LibrarianHomepage> InfoList6(){
     
        ArrayList<LibrarianHomepage> ilist = new ArrayList<LibrarianHomepage>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT book_id, book_title, author, type, subject, quantity, book_id, book_title, author, type, subject, quantity FROM books where subject = 'filipino' ORDER BY book_title ASC");
            
            LibrarianHomepage inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage (rs.getInt("book_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),	
                                rs.getString("subject"),
                                rs.getInt("quantity"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
     // list of info MAPEH
    public ArrayList<LibrarianHomepage> InfoList7(){
     
        ArrayList<LibrarianHomepage> ilist = new ArrayList<LibrarianHomepage>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT book_id, book_title, author, type, subject, quantity, book_id, book_title, author, type, subject, quantity FROM books where subject = 'mapeh' ORDER BY book_title ASC");
            
            LibrarianHomepage inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage (rs.getInt("book_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),	
                                rs.getString("subject"),
                                rs.getInt("quantity"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
     // list of info TLE
    public ArrayList<LibrarianHomepage> InfoList8(){
     
        ArrayList<LibrarianHomepage> ilist = new ArrayList<LibrarianHomepage>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT book_id, book_title, author, type, subject, quantity, book_id, book_title, author, type, subject, quantity FROM books where subject = 'tle' ORDER BY book_title ASC");
            
            LibrarianHomepage inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage (rs.getInt("book_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),	
                                rs.getString("subject"),
                                rs.getInt("quantity"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
     // list of info ESP
    public ArrayList<LibrarianHomepage> InfoList9(){
     
        ArrayList<LibrarianHomepage> ilist = new ArrayList<LibrarianHomepage>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT book_id, book_title, author, type, subject, quantity, book_id, book_title, author, type, subject, quantity FROM books where subject = 'esp' ORDER BY book_title ASC");
            
            LibrarianHomepage inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage (rs.getInt("book_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),	
                                rs.getString("subject"),
                                rs.getInt("quantity"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
     // list of info AP
    public ArrayList<LibrarianHomepage> InfoList10(){
     
        ArrayList<LibrarianHomepage> ilist = new ArrayList<LibrarianHomepage>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT book_id, book_title, author, type, subject, quantity, book_id, book_title, author, type, subject, quantity FROM books where subject = 'ap' ORDER BY book_title ASC");
            
            LibrarianHomepage inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage (rs.getInt("book_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),	
                                rs.getString("subject"),
                                rs.getInt("quantity"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
    
     // list of info STUDENTS
    public ArrayList<StudentAccount> InfoList11(){
     
        ArrayList<StudentAccount> ilist = new ArrayList<StudentAccount>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT ID, lrn, first_name, last_name, username, password FROM registration");
            
            StudentAccount inf;
            
            while(rs.next()){
                 inf = new StudentAccount(rs.getInt("ID"),
                                rs.getLong("lrn"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),	
                                rs.getString("username"),
                                rs.getString("password"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
    
    public ArrayList<LibrarianHomepage2> InfoList12(){
     
        ArrayList<LibrarianHomepage2> ilist = new ArrayList<LibrarianHomepage2>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT borrowed_books.borrowed_id, borrowed_books.book_title, borrowed_books.author, borrowed_books.type,borrowed_books.subject, borrowed_books.borrow_date, borrowed_books.return_date, registration.lrn, CONCAT( registration.first_name, \" \", registration.last_name ) AS name FROM borrowed_books INNER JOIN registration ON borrowed_books.student_id= registration.ID ORDER BY DATE_FORMAT(borrow_date,'%Y-%m-%d') DESC");
            
            LibrarianHomepage2 inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage2 (rs.getInt("borrowed_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),
                                rs.getString("subject"),
                               // rs.getInt("quantity"),	
                                rs.getString("borrow_date"),
                                rs.getString("return_date"),
                               
                                rs.getString("lrn"),
                                
                                rs.getString("name"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
     public ArrayList<StudentAccount2> InfoList13(){
     
        ArrayList<StudentAccount2> ilist = new ArrayList<StudentAccount2>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT registration.ID, registration.lrn, registration.first_name, registration.last_name, registration.username, registration.password, COUNT(borrowed_books.borrowed_id) AS Total_Books FROM registration INNER join borrowed_books on borrowed_books.student_id= registration.ID group by borrowed_books.student_id");
          
            StudentAccount2 inf;
            
            while(rs.next()){
                  
                 inf = new StudentAccount2(rs.getInt("ID"),
                                rs.getLong("lrn"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),	
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getInt("total_books"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
     
      public ArrayList<LibrarianHomepage2> InfoList14(){
     
        ArrayList<LibrarianHomepage2> ilist = new ArrayList<LibrarianHomepage2>();
            
        Connection con= (Connection) myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
            
        try{
            
            ps = (PreparedStatement) con.prepareStatement("SELECT borrowed_books.borrowed_id, borrowed_books.book_title, borrowed_books.author, borrowed_books.type,borrowed_books.subject, borrowed_books.borrow_date, borrowed_books.return_date, registration.lrn, CONCAT( registration.first_name, \" \", registration.last_name ) AS name FROM borrowed_books INNER JOIN registration ON borrowed_books.student_id= registration.ID WHERE registration.ID = ?");
            ps.setInt(1, myConnection.index);
            rs = ps.executeQuery();
            
            LibrarianHomepage2 inf;
            
                  
                while(rs.next()){
                inf = new LibrarianHomepage2 (rs.getInt("borrowed_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),
                                rs.getString("subject"),
                                //rs.getInt("quantity"),	
                                rs.getString("borrow_date"),
                                rs.getString("return_date"),
                               
                                rs.getString("lrn"),
                                
                                rs.getString("name"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
      
      
      public ArrayList<LibrarianHomepage3> InfoList15(){
     
        ArrayList<LibrarianHomepage3> ilist = new ArrayList<LibrarianHomepage3>();
            
        Connection con= (Connection) myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
            
        try{
            
            ps = (PreparedStatement) con.prepareStatement("SELECT returned_books.returned_id, returned_books.book_title, returned_books.author, returned_books.type,returned_books.subject, returned_books.due_date, returned_books.return_date, registration.lrn, CONCAT(registration.first_name,' ',registration.last_name) AS name, (CASE WHEN datediff(return_date,due_date) <= 0 then 'RETURNED' ELSE 'LATE RETURNED' END) as status FROM returned_books INNER JOIN registration ON returned_books.student_id= registration.ID ORDER BY DATE_FORMAT(return_date,'%Y-%m-%d') DESC"); 
           // ps.setInt(1, myConnection.index);                                                                                                                                                                                                                                                                        
            rs = ps.executeQuery();
            
            LibrarianHomepage3 inf;
            
                  
                while(rs.next()){
                inf = new LibrarianHomepage3 (rs.getInt("returned_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),
                                rs.getString("subject"),
                                //rs.getInt("quantity"),	
                                rs.getString("due_date"),
                                rs.getString("return_date"),
                               
                                rs.getString("lrn"),
                                
                                rs.getString("name"),
                                rs.getString("status"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
    
      
      
   
      public void insertEvent (LibrarianHomepage4 inf) {
 
        Connection con= (Connection) myConnection.getConnection();
        PreparedStatement ps;
        
            try {
            ps = (PreparedStatement) con.prepareStatement("INSERT INTO home(picture, event_name) VALUES (?,?)");
            ps.setBytes(1, inf.getPicture());
            ps.setString(2, inf.getEvent_name());

            if(ps.executeUpdate() !=0){
                JOptionPane.showMessageDialog(null, "New Event Added", "Message", JOptionPane.INFORMATION_MESSAGE);

                    }
                    else{
                      JOptionPane.showMessageDialog(null, "Something is wrong", "Message", JOptionPane.ERROR_MESSAGE);  

                    }

                } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE,null,ex);
            }
              
        }
    
    public void updateEvent(LibrarianHomepage4 inf, boolean withImage){
        Connection con= (Connection) myConnection.getConnection();
        PreparedStatement ps;
        String updateQuery = "";
        if(withImage == true) //update the profile picture
        {
            updateQuery = "UPDATE home set picture = ?, event_name = ? WHERE home_id = ?"; 
        
             try {
            ps = (PreparedStatement) con.prepareStatement(updateQuery);
            ps.setBytes(1, inf.getPicture());
            ps.setString(2, inf.getEvent_name());
            ps.setInt(3, inf.getHome_id());

            if(ps.executeUpdate() !=0){
                JOptionPane.showMessageDialog(null, "Event Updated", "Message", JOptionPane.INFORMATION_MESSAGE);

                }
                else{
                JOptionPane.showMessageDialog(null, "Something is wrong", "Message", JOptionPane.ERROR_MESSAGE);  

                }

            } catch (SQLException ex) {
               Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE,null,ex);
            }   
            
        } 
        else{ // keeping the same profile picture
            updateQuery = "UPDATE home set Event_name = ? WHERE home_id = ?"; 
        
             try {
            ps = (PreparedStatement) con.prepareStatement(updateQuery);
            
            ps.setString(1, inf.getEvent_name());
            ps.setInt(2, inf.getHome_id());

            if(ps.executeUpdate() !=0){
                JOptionPane.showMessageDialog(null, "Product Updated", "Message", JOptionPane.INFORMATION_MESSAGE);

                }
                else{
                JOptionPane.showMessageDialog(null, "Something is wrong", "Message", JOptionPane.ERROR_MESSAGE);  

                }

            } catch (SQLException ex) {
               Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE,null,ex);
            }   
            
        } 
    }
    public void deleteEvent(int Product_ID) {
 
        Connection con= (Connection) myConnection.getConnection();
        PreparedStatement ps;
        
            try {
            ps = (PreparedStatement) con.prepareStatement("DELETE FROM home WHERE home_id = ?");
            ps.setInt(1, Product_ID);

            if(ps.executeUpdate() !=0){
                JOptionPane.showMessageDialog(null, "Event Deleted", "Message", JOptionPane.INFORMATION_MESSAGE);

                    }
                    else{
                      JOptionPane.showMessageDialog(null, "Something is wrong", "Message", JOptionPane.ERROR_MESSAGE);  

                    }

                } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE,null,ex);
            }
              
        }
                
    
    // list of info
    public ArrayList<LibrarianHomepage4> InfoList16(){
     
        ArrayList<LibrarianHomepage4> ilist = new ArrayList<LibrarianHomepage4>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT home_id, picture, event_name FROM home");
            
            LibrarianHomepage4 inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage4 (rs.getInt("home_id"),
                                rs.getBytes("picture"),
                                rs.getString("event_name"));
                       
                ilist.add(inf);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
    
    
    
    public void insertQR (LibrarianHomepage5 inf) {
 
        Connection con= (Connection) myConnection.getConnection();
        PreparedStatement ps;
        
            try {
            ps = (PreparedStatement) con.prepareStatement("INSERT INTO qr_code(qr_img) VALUES (?)");
            ps.setBytes(1, inf.getQr_img());

            if(ps.executeUpdate() !=0){
                JOptionPane.showMessageDialog(null, "New QR Code Added", "Message", JOptionPane.INFORMATION_MESSAGE);

                    }
                    else{
                      JOptionPane.showMessageDialog(null, "Something is wrong", "Message", JOptionPane.ERROR_MESSAGE);  

                    }

                } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE,null,ex);
            }
              
        }
    
    
    
    
    
    
    public ArrayList<LibrarianHomepage6> InfoList17(){
     
        ArrayList<LibrarianHomepage6> ilist = new ArrayList<LibrarianHomepage6>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT borrowed_books.borrowed_id, borrowed_books.book_title, borrowed_books.author, borrowed_books.type,borrowed_books.subject, borrowed_books.borrow_date, borrowed_books.return_date, registration.lrn, CONCAT( registration.first_name, \" \", registration.last_name ) AS name, (CASE WHEN datediff(return_date,CURDATE()) >= 0 then 'PENDING' ELSE 'LATE' END) as status FROM borrowed_books INNER JOIN registration ON borrowed_books.student_id= registration.ID HAVING status = 'LATE' ORDER BY DATE_FORMAT(borrow_date,'%Y-%m-%d') DESC ");
            
            LibrarianHomepage6 inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage6 (rs.getInt("borrowed_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),
                                rs.getString("subject"),
                               // rs.getInt("quantity"),	
                                rs.getString("borrow_date"),
                                rs.getString("return_date"),
                               
                                rs.getString("lrn"),
                                
                                rs.getString("name"),
                                rs.getString("status"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
    
    public ArrayList<LibrarianHomepage6> InfoList18(){
     
        ArrayList<LibrarianHomepage6> ilist = new ArrayList<LibrarianHomepage6>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT borrowed_books.borrowed_id, borrowed_books.book_title, borrowed_books.author, borrowed_books.type,borrowed_books.subject, borrowed_books.borrow_date, borrowed_books.return_date, registration.lrn, CONCAT( registration.first_name, \" \", registration.last_name ) AS name, (CASE WHEN datediff(return_date,CURDATE()) >= 0 then 'PENDING' ELSE 'LATE' END) as status FROM borrowed_books INNER JOIN registration ON borrowed_books.student_id= registration.ID ORDER BY DATE_FORMAT(borrow_date,'%Y-%m-%d') DESC");
            
            LibrarianHomepage6 inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage6 (rs.getInt("borrowed_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),
                                rs.getString("subject"),
                               // rs.getInt("quantity"),	
                                rs.getString("borrow_date"),
                                rs.getString("return_date"),
                               
                                rs.getString("lrn"),
                                
                                rs.getString("name"),
                                rs.getString("status"));
                       
                ilist.add(inf);
            }
            
    
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
    
    
     public ArrayList<LibrarianHomepage> InfoList19(){
     
        ArrayList<LibrarianHomepage> ilist = new ArrayList<LibrarianHomepage>();
            
        Connection con= (Connection) myConnection.getConnection();
        Statement st;
        ResultSet rs;
            
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT book_id, book_title, author, type, subject, quantity, book_id, book_title, author, type, subject, quantity FROM books ORDER BY book_title ASC");
            
            LibrarianHomepage inf;
            
            while(rs.next()){
                inf = new LibrarianHomepage (rs.getInt("book_id"),
                                rs.getString("book_title"),	
                                rs.getString("author"),
                                rs.getString("type"),	
                                rs.getString("subject"),
                                rs.getInt("quantity"));
                       
                ilist.add(inf);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHomepageQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ilist;
    }
    
}


