
package librarymanagementkiosk;

public class LibrarianHomepage4 {
    private Integer home_id;
    private byte[] picture;
    private String event_name;
    
    public LibrarianHomepage4() {}
    
    public LibrarianHomepage4(Integer home_id, byte[] picture, String event_name) {
        this.home_id = home_id;
        this.picture = picture;
        this.event_name = event_name;
    }

    

   

    public Integer getHome_id () {
        return home_id ;
    }

    public void setHome_id (Integer home_id ) {
        this.home_id  = home_id ;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_nameame(String event_name) {
        this.event_name = event_name;
    }

   

    
}

