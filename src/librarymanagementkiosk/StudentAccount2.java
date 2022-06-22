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
public class StudentAccount2 {
    //private Integer ID;
    private Long lrn;
    private String first_name;
    private String last_name;
    private String Username;
    private String Password;
    private Integer total_books;
    
    public StudentAccount2() {}
    
    public StudentAccount2(Integer ID, Long lrn, String first_name, String last_name, String Username, String Password, Integer total_books ) {
       // this.ID = ID;
        this.lrn = lrn;
        this.first_name = first_name;
        this.last_name = last_name;
        this.Username = Username;
        this.Password = Password;
        this.total_books = total_books;
    }

     

     
   /* public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }*/
    
     public Long getLrn() {
        return lrn;
    }

    public void setLrn(Long lrn) {
        this.lrn = lrn;
    }
    
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    
     public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }
    
    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    public Integer getTotal_books() {
        return total_books;
    }

    public void setTotal_Books(Integer total_books) {
        this.total_books = total_books;
    }
    
 
}
