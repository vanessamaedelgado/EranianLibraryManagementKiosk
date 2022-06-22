
package librarymanagementkiosk;

public class LibrarianHomepage5 {
    private Integer qr_id;
    private byte[] qr_img;
    
    public LibrarianHomepage5() {}
    
    public LibrarianHomepage5(Integer qr_id, byte[] qr_img) {
        this.qr_id = qr_id;
        this.qr_img = qr_img;
    }

    

   

    public Integer getQr_id () {
        return qr_id ;
    }

    public void setQr_id (Integer qr_id ) {
        this.qr_id  = qr_id ;
    }

    public byte[] getQr_img() {
        return qr_img;
    }

    public void setQr_img(byte[] qr_img) {
        this.qr_img = qr_img;
    }

   

   

    
}

