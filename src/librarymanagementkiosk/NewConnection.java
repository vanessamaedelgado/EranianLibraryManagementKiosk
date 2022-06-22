
package librarymanagementkiosk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class NewConnection { private Connection con;
    private String conString;
    private String username;
    private String password;
    public static int index;// maintain user login ID
    
    public NewConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conString = "jdbc:mysql://localhost/librarymanagementkiosk";
            username = "root";
            password = "";
            con = DriverManager.getConnection(conString,username,password);
            System.out.println("Connection Successful!");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public TableModel populateTable(String sql) throws SQLException{
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery(); //FOR SELECT STATEMENT
        return DbUtils.resultSetToTableModel(rs);
    }
    
    public PreparedStatement getPST(String sql) throws SQLException{
        PreparedStatement pst = con.prepareStatement(sql);
        return pst;
    }
    
    public void close() throws SQLException{
        con.close();
    }
    
}
