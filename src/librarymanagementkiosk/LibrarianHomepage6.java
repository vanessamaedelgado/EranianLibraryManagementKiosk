/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementkiosk;

/**
 *
 * @author 63915
 */
public class LibrarianHomepage6 {
    private Integer borrowed_id;
    private String book_title;
    private String author;
    private String type;
    private String subject;
    
    
    //private Integer quantity;
    private String borrow_date;
    private String return_date;
    
   // private Integer student_id;
   // private String username;
    private String name;
    private String lrn;
    private String status;
   
    
    public LibrarianHomepage6() {}
    
    public LibrarianHomepage6(Integer borrowed_id, String book_title, String author, String type, String subject,  String borrow_date,String return_date, String lrn,String name, String status) {
        this.borrowed_id = borrowed_id;
        this.book_title = book_title;
        this.author = author;
        this.type = type;
        this.subject = subject;
        //this.quantity = quantity;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
       
       // this.student_id = student_id;
        this.lrn = lrn;
        //this.username=username;
        this.name=name;
        //this.last_name=last_name;
        this.status=status;
    }

     LibrarianHomepage6(Object object, String book_title, String author, String type, String subject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     LibrarianHomepage6(String book_title, String author, String type, String subject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public Integer getBorrowed_id() {
        return borrowed_id;
    }

    public void setBorrowed_id(Integer borrowed_id) {
        this.borrowed_id = borrowed_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
     public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

   /*public Integer getQuantity() {
        return quantity;
   
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }*/
    
    
    public String getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }
    
    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
    
   
    
   /*  public Integer getStudent_id() {
        return student_id;
   
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }*/
    
     
     public String getLrn() {
        return lrn;
    }

    public void setLrn(String lrn) {
        this.lrn = lrn;
    }
    
    /* public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }*/
 
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
   
   /*  public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
 */
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
