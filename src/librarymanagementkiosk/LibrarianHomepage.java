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
public class LibrarianHomepage {
    private Integer book_id;
    private String book_title;
    private String author;
    private String type;
    private String subject;
    private Integer quantity;
   
    
    public LibrarianHomepage() {}
    
    public LibrarianHomepage(Integer book_id, String book_title, String author, String type, String subject, Integer quantity) {
        this.book_id = book_id;
        this.book_title = book_title;
        this.author = author;
        this.type = type;
        this.subject = subject;
        this.quantity = quantity;
    }

     LibrarianHomepage(Object object, String book_title, String author, String type, String subject, Integer quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     LibrarianHomepage(String book_title, String author, String type, String subject, Integer quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
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

    public Integer getQuantity() {
        return quantity;
   
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

   
 
}
