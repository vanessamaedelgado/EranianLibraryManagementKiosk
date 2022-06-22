/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementkiosk;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author 63915
 */
public class StudentHome extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private WebcamPanel panell = null;
    private Webcam webcam2 = null;
    private Executor executor2 = Executors.newSingleThreadExecutor(this);

    //  String billNo="";
    Double totalAmount = 0.0;
    Double cash = 0.0;
    Double balance = 0.0;
    Double bHeight = 0.0;

    ArrayList<String> ID = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> itemPrice = new ArrayList<>();
    ArrayList<String> subtotal = new ArrayList<>();

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sqr;

    String iID;
    String ititle;
    String iauthor;
    String itype;
    String isubject;
    String iquantity;
    String iborrowdate;
    String ireturndate;
    String iduedate;
    String istatus;

    /**
     * Creates new form LibrarianHome
     */
    public StudentHome() {
        initComponents();
        this.setLocationRelativeTo(null);
        initWebcam();
        initWebcam2();
        showItem(pos);

        conn = myConnection.getConnection();
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);

        ((JTextFieldDateEditor) borroweddate.getDateEditor()).setEditable(false);
        ((JTextFieldDateEditor) returneddate.getDateEditor()).setEditable(false);
        ((JTextFieldDateEditor) returneddate1.getDateEditor()).setEditable(false);

        populateJtable();
        changeTable(table_myborrowed, 6);

        Border emptyBorder = BorderFactory.createEmptyBorder();
        updateinfo_btn.setBorder(emptyBorder);
        btn_next.setBorder(emptyBorder);
        btn_borrow.setBorder(emptyBorder);
        btn_print.setBorder(emptyBorder);
        btn_next2.setBorder(emptyBorder);
        btn_return.setBorder(emptyBorder);
        
        updateinfo_btn.setFocusPainted(false);
        btn_next.setFocusPainted(false);
        btn_borrow.setFocusPainted(false);
        btn_print.setFocusPainted(false);
        btn_next2.setFocusPainted(false);
        btn_return.setFocusPainted(false);
        

        table_bookreport.setShowGrid(true);
        table_bookreport.setGridColor(Color.gray);
        table_bookreport.setSelectionBackground(Color.BLACK);
        table_bookreport.setSelectionForeground(Color.white);

        table_bookreport.getTableHeader().setOpaque(false);
        table_bookreport.setShowVerticalLines(false);
        table_bookreport.setShowHorizontalLines(true);

        JTableHeader th = table_bookreport.getTableHeader();
        th.setForeground(Color.white);
        th.setBackground(new Color(64, 134, 176));
        th.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_borrowedreport.setShowGrid(true);
        table_borrowedreport.setGridColor(Color.gray);
        table_borrowedreport.setSelectionBackground(Color.BLACK);
        table_borrowedreport.setSelectionForeground(Color.white);

        table_borrowedreport.getTableHeader().setOpaque(false);
        table_borrowedreport.setShowVerticalLines(false);
        table_borrowedreport.setShowHorizontalLines(true);

        JTableHeader th2 = table_borrowedreport.getTableHeader();
        th2.setForeground(Color.white);
        th2.setBackground(new Color(64, 134, 176));
        th2.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_myborrowed.setShowGrid(true);
        table_myborrowed.setGridColor(Color.gray);
        table_myborrowed.setSelectionBackground(Color.BLACK);
        table_myborrowed.setSelectionForeground(Color.white);

        table_myborrowed.getTableHeader().setOpaque(false);
        table_myborrowed.setShowVerticalLines(false);
        table_myborrowed.setShowHorizontalLines(true);

        JTableHeader th3 = table_myborrowed.getTableHeader();
        th3.setForeground(Color.white);
        th3.setBackground(new Color(64, 134, 176));
        th3.setFont(new Font("Segoe UI", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEFT);

        table_bookreport.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_bookreport.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table_borrowedreport.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_myborrowed.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        jLabel2.setForeground(Color.BLACK);
        jLabel3.setForeground(Color.BLACK);
        jLabel4.setForeground(Color.BLACK);
        jLabel5.setForeground(Color.BLACK);
        jLabel7.setForeground(Color.BLACK);
        jLabel10.setForeground(Color.BLACK);
        jLabel11.setForeground(Color.BLACK);

        showDate();
        // showTime();
        student();
        dates();

    }

    int pos = 0;

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

    public List<LibrarianHomepage4> getItemsList() {
        try {
            Connection connection = getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM home");
            List<LibrarianHomepage4> list = new ArrayList<LibrarianHomepage4>();
            LibrarianHomepage4 item;
            while (rs.next()) {
                item = new LibrarianHomepage4(rs.getInt("home_id"),
                        rs.getBytes("picture"),
                        rs.getString("event_name"));
                list.add(item);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void showItem(int index) {
        // home_id.setText(getItemsList().get(index).getHome_id());
        lbl_event.setText(getItemsList().get(index).getEvent_name());

        ImageIcon icon = new ImageIcon(getItemsList().get(index).getPicture());
        Image image = icon.getImage().getScaledInstance(lbl_images.getWidth(), lbl_images.getHeight(), Image.SCALE_SMOOTH);

        lbl_images.setIcon(new ImageIcon(image));
    }

    void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("MM-dd-yyyy");
        dateLab.setText(s.format(d));

    }

    void dates() {
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd");

        borroweddate.setDate(date);
        returneddate1.setDate(date);

        Calendar c = Calendar.getInstance();

        c.add(Calendar.DATE, 7);

        Date dateone = c.getTime();
        SimpleDateFormat t = new SimpleDateFormat("yyyy/MM/dd");

        returneddate.setDate(dateone);
    }

    /*void showTime(){
        new Timer(0, (ActionEvent ae) -> {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
        jLabel34.setText(s.format(d));
    }).start();

    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        upperPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        minFrame = new javax.swing.JPanel();
        min = new javax.swing.JLabel();
        closeFrame = new javax.swing.JPanel();
        cl = new javax.swing.JLabel();
        jLabelLoida = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        sideNav = new javax.swing.JPanel();
        hamburger = new javax.swing.JLabel();
        tab1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dashboard = new javax.swing.JLabel();
        tab2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        tab3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        book = new javax.swing.JLabel();
        tab4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        borrow = new javax.swing.JLabel();
        tab6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        qr = new javax.swing.JLabel();
        tab7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        qr1 = new javax.swing.JLabel();
        tab8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        lbl_fullname = new javax.swing.JLabel();
        mainTab = new javax.swing.JTabbedPane();
        panel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbl_back = new javax.swing.JLabel();
        lbl_next = new javax.swing.JLabel();
        lbl_images = new javax.swing.JLabel();
        lbl_event = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        tab9 = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        tab10 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        userTab = new javax.swing.JTabbedPane();
        panelaccount = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblfirstname = new javax.swing.JLabel();
        textfieldfirstname = new javax.swing.JTextField();
        lbllastname = new javax.swing.JLabel();
        textfieldlastname = new javax.swing.JTextField();
        lblusername = new javax.swing.JLabel();
        textfieldusername = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        lblpassword = new javax.swing.JLabel();
        lblconfirmpass = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JLabel();
        lbllrn = new javax.swing.JLabel();
        textfieldlrn = new javax.swing.JTextField();
        updateinfo_btn = new button.MyButton();
        panelborrowed = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel172 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_myborrowed = new javax.swing.JTable();
        panel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel170 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_bookreport = new javax.swing.JTable();
        panel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel94 = new javax.swing.JPanel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel171 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_borrowedreport = new javax.swing.JTable();
        panel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        result_field = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textarea = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        comboboxsubject2 = new javax.swing.JTextField();
        textfieldauthor2 = new javax.swing.JTextField();
        comboboxtype2 = new javax.swing.JTextField();
        textfieldquantity2 = new javax.swing.JTextField();
        textfieldstudentid = new javax.swing.JTextField();
        textfieldbookid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        borroweddate = new com.toedter.calendar.JDateChooser();
        returneddate = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        textfieldname = new javax.swing.JTextField();
        status = new javax.swing.JLabel();
        btn_next = new button.MyButton();
        btn_borrow = new button.MyButton();
        btn_print = new button.MyButton();
        panel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        result_field1 = new javax.swing.JTextField();
        comboboxsubject3 = new javax.swing.JTextField();
        textfieldauthor3 = new javax.swing.JTextField();
        comboboxtype3 = new javax.swing.JTextField();
        textfieldborrowedid1 = new javax.swing.JTextField();
        textfieldstudentid1 = new javax.swing.JTextField();
        textfieldbookid1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        returneddate1 = new com.toedter.calendar.JDateChooser();
        borroweddate1 = new javax.swing.JTextField();
        btn_next2 = new button.MyButton();
        btn_return = new button.MyButton();
        close = new javax.swing.JLabel();
        aboutImg = new javax.swing.JLabel();
        lowerPanel = new javax.swing.JPanel();
        dateLab = new javax.swing.JLabel();
        dateLab1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 249, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56), 5));
        jPanel1.setMinimumSize(new java.awt.Dimension(1350, 700));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        upperPanel.setBackground(new java.awt.Color(64, 134, 176));
        upperPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(93, 70, 56));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        upperPanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 86, 1340, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/logo2.png"))); // NOI18N
        upperPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, -1));

        minFrame.setBackground(new java.awt.Color(64, 134, 176));
        minFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minFrameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minFrameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minFrameMouseExited(evt);
            }
        });

        min.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        min.setForeground(new java.awt.Color(255, 255, 255));
        min.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        min.setText("−");
        min.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minMouseExited(evt);
            }
        });

        javax.swing.GroupLayout minFrameLayout = new javax.swing.GroupLayout(minFrame);
        minFrame.setLayout(minFrameLayout);
        minFrameLayout.setHorizontalGroup(
            minFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(minFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(min)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        minFrameLayout.setVerticalGroup(
            minFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, minFrameLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(min))
        );

        upperPanel.add(minFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(1251, 0, -1, -1));

        closeFrame.setBackground(new java.awt.Color(64, 134, 176));
        closeFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeFrameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeFrameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeFrameMouseExited(evt);
            }
        });

        cl.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        cl.setForeground(new java.awt.Color(255, 255, 255));
        cl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cl.setText("x");
        cl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clMouseExited(evt);
            }
        });

        javax.swing.GroupLayout closeFrameLayout = new javax.swing.GroupLayout(closeFrame);
        closeFrame.setLayout(closeFrameLayout);
        closeFrameLayout.setHorizontalGroup(
            closeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, closeFrameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cl)
                .addContainerGap())
        );
        closeFrameLayout.setVerticalGroup(
            closeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, closeFrameLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cl))
        );

        upperPanel.add(closeFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(1302, 0, -1, -1));

        jLabelLoida.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabelLoida.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLoida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/era.png"))); // NOI18N
        upperPanel.add(jLabelLoida, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("Library Management Kiosk ");
        upperPanel.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        jPanel1.add(upperPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 1340, -1));

        sideNav.setBackground(new java.awt.Color(149, 189, 219));
        sideNav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        hamburger.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hamburger.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/menu.png"))); // NOI18N
        hamburger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hamburgerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hamburgerMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hamburgerMousePressed(evt);
            }
        });
        sideNav.add(hamburger, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 30, 30));

        tab1.setBackground(new java.awt.Color(149, 189, 219));
        tab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab1MouseReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Agency FB", 0, 22)); // NOI18N
        jLabel2.setText("HOME");

        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard.png"))); // NOI18N

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(181, Short.MAX_VALUE))
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sideNav.add(tab1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 260, 50));

        tab2.setBackground(new java.awt.Color(149, 189, 219));
        tab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab2MouseReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Agency FB", 0, 22)); // NOI18N
        jLabel3.setText("USER ACCOUNT");

        user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/user.png"))); // NOI18N

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(118, Short.MAX_VALUE))
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(user, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        sideNav.add(tab2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 260, 50));

        tab3.setBackground(new java.awt.Color(149, 189, 219));
        tab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab3MouseReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Agency FB", 0, 22)); // NOI18N
        jLabel4.setText("BOOK LISTS");

        book.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book.png"))); // NOI18N

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(book, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(book, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        sideNav.add(tab3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 260, 50));

        tab4.setBackground(new java.awt.Color(149, 189, 219));
        tab4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab4MouseReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Agency FB", 0, 22)); // NOI18N
        jLabel5.setText("BORROWED BOOKS");

        borrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/borrowbook.png"))); // NOI18N

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(borrow, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(borrow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        sideNav.add(tab4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 260, 50));

        tab6.setBackground(new java.awt.Color(149, 189, 219));
        tab6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab6MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab6MouseReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Agency FB", 0, 22)); // NOI18N
        jLabel7.setText("BORROW BOOK");

        qr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/qrcode.png"))); // NOI18N

        javax.swing.GroupLayout tab6Layout = new javax.swing.GroupLayout(tab6);
        tab6.setLayout(tab6Layout);
        tab6Layout.setHorizontalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab6Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(qr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        tab6Layout.setVerticalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tab6Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        sideNav.add(tab6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 260, 50));

        tab7.setBackground(new java.awt.Color(149, 189, 219));
        tab7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab7MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab7MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab7MouseReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Agency FB", 0, 22)); // NOI18N
        jLabel10.setText("RETURN BOOK");

        qr1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/qrcode.png"))); // NOI18N

        javax.swing.GroupLayout tab7Layout = new javax.swing.GroupLayout(tab7);
        tab7.setLayout(tab7Layout);
        tab7Layout.setHorizontalGroup(
            tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab7Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(qr1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(127, Short.MAX_VALUE))
        );
        tab7Layout.setVerticalGroup(
            tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qr1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tab7Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        sideNav.add(tab7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 260, 50));

        tab8.setBackground(new java.awt.Color(149, 189, 219));
        tab8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab8MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab8MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab8MouseReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Agency FB", 0, 22)); // NOI18N
        jLabel11.setText("LOGOUT");

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/logout.png"))); // NOI18N

        javax.swing.GroupLayout tab8Layout = new javax.swing.GroupLayout(tab8);
        tab8.setLayout(tab8Layout);
        tab8Layout.setHorizontalGroup(
            tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab8Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(167, Short.MAX_VALUE))
        );
        tab8Layout.setVerticalGroup(
            tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tab8Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        sideNav.add(tab8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 260, 50));

        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/stud2.png"))); // NOI18N
        sideNav.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        lbl_fullname.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_fullname.setForeground(new java.awt.Color(51, 51, 51));
        lbl_fullname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_fullname.setText("full name");
        lbl_fullname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_fullnameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_fullnameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_fullnameMouseExited(evt);
            }
        });
        sideNav.add(lbl_fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 240, -1));

        jPanel1.add(sideNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 113, 260, 550));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(8, 14, 112));

        jLabel8.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("HOME");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 11, 1015, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        lbl_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/back.png"))); // NOI18N
        lbl_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_backMouseClicked(evt);
            }
        });

        lbl_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/next.png"))); // NOI18N
        lbl_next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_nextMouseClicked(evt);
            }
        });

        lbl_images.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl_event.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_event.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_event.setText("Event Name");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl_back)
                .addGap(10, 10, 10)
                .addComponent(lbl_images, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lbl_next))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(lbl_event, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_back)
                            .addComponent(lbl_next))
                        .addGap(158, 158, 158))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_images, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(lbl_event, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1015, -1));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTab.addTab("tab1", panel1);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel35.setBackground(new java.awt.Color(8, 14, 112));

        jLabel45.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Home / User Account");

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel45)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 11, 1015, -1));

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tab9.setBackground(new java.awt.Color(149, 189, 219));
        tab9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab9MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab9MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab9MouseReleased(evt);
            }
        });

        label1.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        label1.setText("MY ACCOUNT");

        javax.swing.GroupLayout tab9Layout = new javax.swing.GroupLayout(tab9);
        tab9.setLayout(tab9Layout);
        tab9Layout.setHorizontalGroup(
            tab9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab9Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(label1)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        tab9Layout.setVerticalGroup(
            tab9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel36.add(tab9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 8, 150, 45));

        tab10.setBackground(new java.awt.Color(149, 189, 219));
        tab10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab10MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab10MousePressed(evt);
            }
        });

        label2.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        label2.setText("MY BORROWED BOOKS");

        javax.swing.GroupLayout tab10Layout = new javax.swing.GroupLayout(tab10);
        tab10.setLayout(tab10Layout);
        tab10Layout.setHorizontalGroup(
            tab10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab10Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(label2)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        tab10Layout.setVerticalGroup(
            tab10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel36.add(tab10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 8, 180, 45));

        jPanel18.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 1015, 45));

        userTab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        panelaccount.setBackground(new java.awt.Color(255, 255, 255));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(64, 134, 176));

        jLabel25.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("User Account");

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/user1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 1006, 40));

        lblfirstname.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        lblfirstname.setText("First Name:");
        jPanel20.add(lblfirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 132, -1, -1));

        textfieldfirstname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textfieldfirstname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldfirstname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textfieldfirstnameMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldfirstnameMousePressed(evt);
            }
        });
        textfieldfirstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldfirstnameActionPerformed(evt);
            }
        });
        jPanel20.add(textfieldfirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 172, 250, 39));

        lbllastname.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        lbllastname.setText("Last Name:");
        jPanel20.add(lbllastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 229, 89, -1));

        textfieldlastname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textfieldlastname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldlastname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textfieldlastnameMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldlastnameMousePressed(evt);
            }
        });
        textfieldlastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldlastnameActionPerformed(evt);
            }
        });
        jPanel20.add(textfieldlastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 269, 250, 40));

        lblusername.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        lblusername.setText("Username:");
        jPanel20.add(lblusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, -1, -1));

        textfieldusername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textfieldusername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldusername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textfieldusernameMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldusernameMousePressed(evt);
            }
        });
        textfieldusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldusernameActionPerformed(evt);
            }
        });
        jPanel20.add(textfieldusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 250, 40));

        jPasswordField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPasswordField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jPasswordField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPasswordField1MousePressed(evt);
            }
        });
        jPanel20.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 250, 40));

        lblpassword.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        lblpassword.setText("Password:");
        jPanel20.add(lblpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, -1, -1));

        lblconfirmpass.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        lblconfirmpass.setText("Confirm Password:");
        jPanel20.add(lblconfirmpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, -1, -1));

        jPasswordField2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPasswordField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jPasswordField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPasswordField2MousePressed(evt);
            }
        });
        jPasswordField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });
        jPanel20.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, 250, 40));

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/1.png"))); // NOI18N
        jPanel20.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 71, 46, 50));

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/2.png"))); // NOI18N
        jPanel20.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 46, 50));

        jLabel52.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(204, 204, 204));
        jLabel52.setText("PROFILE");
        jPanel20.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 71, -1, 50));

        jLabel53.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(204, 204, 204));
        jLabel53.setText("PASSWORD");
        jPanel20.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, 50));

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("jLabel18");
        jPanel20.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 339, -1, -1));

        lbllrn.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        lbllrn.setText("LRN:");
        jPanel20.add(lbllrn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 327, 89, -1));

        textfieldlrn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textfieldlrn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldlrn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textfieldlrnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldlrnMousePressed(evt);
            }
        });
        textfieldlrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldlrnActionPerformed(evt);
            }
        });
        textfieldlrn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textfieldlrnKeyPressed(evt);
            }
        });
        jPanel20.add(textfieldlrn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 367, 250, 40));

        updateinfo_btn.setForeground(new java.awt.Color(255, 255, 255));
        updateinfo_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-update-25.png"))); // NOI18N
        updateinfo_btn.setText("UPDATE");
        updateinfo_btn.setBorderColor(new java.awt.Color(64, 134, 176));
        updateinfo_btn.setColor(new java.awt.Color(64, 134, 176));
        updateinfo_btn.setColorClick(new java.awt.Color(102, 153, 255));
        updateinfo_btn.setColorOver(new java.awt.Color(64, 134, 176));
        updateinfo_btn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        updateinfo_btn.setRadius(30);
        updateinfo_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateinfo_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateinfo_btnMouseExited(evt);
            }
        });
        updateinfo_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateinfo_btnActionPerformed(evt);
            }
        });
        jPanel20.add(updateinfo_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 370, 150, 35));

        javax.swing.GroupLayout panelaccountLayout = new javax.swing.GroupLayout(panelaccount);
        panelaccount.setLayout(panelaccountLayout);
        panelaccountLayout.setHorizontalGroup(
            panelaccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelaccountLayout.setVerticalGroup(
            panelaccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelaccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        userTab.addTab("tab1", panelaccount);

        panelborrowed.setBackground(new java.awt.Color(255, 255, 255));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel37.setBackground(new java.awt.Color(64, 134, 176));
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("My Borrowed Books");
        jPanel37.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book1.png"))); // NOI18N
        jPanel37.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        jTextField9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
        });
        jPanel37.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 5, 230, 30));

        jLabel172.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel37.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(725, 5, -1, 30));

        table_myborrowed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(table_myborrowed);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 949, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelborrowedLayout = new javax.swing.GroupLayout(panelborrowed);
        panelborrowed.setLayout(panelborrowedLayout);
        panelborrowedLayout.setHorizontalGroup(
            panelborrowedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelborrowedLayout.setVerticalGroup(
            panelborrowedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborrowedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        userTab.addTab("tab2", panelborrowed);

        jPanel18.add(userTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 65, 1015, 490));

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTab.addTab("tab2", panel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel23.setBackground(new java.awt.Color(8, 14, 112));

        jLabel29.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Home / Books");

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel25.setBackground(new java.awt.Color(64, 134, 176));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Book Lists");
        jPanel25.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book1.png"))); // NOI18N
        jPanel25.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField4MousePressed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });
        jPanel25.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 5, 230, 30));

        jLabel170.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel25.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 5, -1, 30));

        table_bookreport.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_bookreport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_bookreport.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_bookreport.setSelectionBackground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(table_bookreport);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTab.addTab("tab3", panel3);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel94.setBackground(new java.awt.Color(8, 14, 112));

        jLabel135.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(255, 255, 255));
        jLabel135.setText("Home / Borrowed Books");

        jLabel136.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel94Layout = new javax.swing.GroupLayout(jPanel94);
        jPanel94.setLayout(jPanel94Layout);
        jPanel94Layout.setHorizontalGroup(
            jPanel94Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel94Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel135)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel94Layout.setVerticalGroup(
            jPanel94Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel94Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel94Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel136, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel135, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel30.setBackground(new java.awt.Color(64, 134, 176));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Borrowed Book Lists");
        jPanel30.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book1.png"))); // NOI18N
        jPanel30.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        jTextField8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jTextField8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField8MousePressed(evt);
            }
        });
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });
        jPanel30.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 5, 230, 30));

        jLabel171.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel30.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 5, -1, 30));

        table_borrowedreport.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_borrowedreport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        table_borrowedreport.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_borrowedreport.setSelectionBackground(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(table_borrowedreport);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTab.addTab("tab4", panel4);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel32.setBackground(new java.awt.Color(8, 14, 112));

        jLabel41.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Home / Borrow Book");

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));
        jPanel33.setMinimumSize(new java.awt.Dimension(670, 441));
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel34.setBackground(new java.awt.Color(64, 134, 176));

        jLabel43.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Borrow Book");

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/qrcode1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addContainerGap(916, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel33.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 1013, -1));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel33.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 300, 230));

        result_field.setEditable(false);
        result_field.setBackground(new java.awt.Color(255, 255, 255));
        result_field.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        result_field.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        result_field.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                result_fieldComponentAdded(evt);
            }
        });
        result_field.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                result_fieldMousePressed(evt);
            }
        });
        result_field.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                result_fieldInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        result_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result_fieldActionPerformed(evt);
            }
        });
        result_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                result_fieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                result_fieldKeyTyped(evt);
            }
        });
        jPanel33.add(result_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 300, 32));

        textarea.setEditable(false);
        textarea.setColumns(20);
        textarea.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        textarea.setRows(5);
        textarea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jScrollPane1.setViewportView(textarea);

        jPanel33.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 190, 250, 220));

        jLabel13.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel13.setText("LIST OF TITLES");
        jPanel33.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 160, -1, -1));

        comboboxsubject2.setEditable(false);
        comboboxsubject2.setBackground(new java.awt.Color(255, 255, 255));
        comboboxsubject2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboboxsubject2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jPanel33.add(comboboxsubject2, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 260, 230, 30));

        textfieldauthor2.setEditable(false);
        textfieldauthor2.setBackground(new java.awt.Color(255, 255, 255));
        textfieldauthor2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldauthor2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jPanel33.add(textfieldauthor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 180, 230, 30));

        comboboxtype2.setEditable(false);
        comboboxtype2.setBackground(new java.awt.Color(255, 255, 255));
        comboboxtype2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboboxtype2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jPanel33.add(comboboxtype2, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 220, 230, 30));

        textfieldquantity2.setEditable(false);
        textfieldquantity2.setBackground(new java.awt.Color(255, 255, 255));
        textfieldquantity2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldquantity2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldquantity2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldquantity2ActionPerformed(evt);
            }
        });
        jPanel33.add(textfieldquantity2, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 300, 230, 30));

        textfieldstudentid.setEditable(false);
        textfieldstudentid.setBackground(new java.awt.Color(255, 255, 255));
        textfieldstudentid.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldstudentid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldstudentid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldstudentidActionPerformed(evt);
            }
        });
        jPanel33.add(textfieldstudentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 100, 230, 30));

        textfieldbookid.setEditable(false);
        textfieldbookid.setBackground(new java.awt.Color(255, 255, 255));
        textfieldbookid.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldbookid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldbookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldbookidActionPerformed(evt);
            }
        });
        jPanel33.add(textfieldbookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 140, 230, 30));

        jLabel6.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel6.setText("Book Title:");
        jPanel33.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/1.png"))); // NOI18N
        jPanel33.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 40, 50));

        jSeparator1.setBackground(new java.awt.Color(8, 14, 112));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel33.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, -1, 430));

        jLabel12.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel12.setText("Student ID:");
        jPanel33.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, -1, -1));

        jLabel54.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(204, 204, 204));
        jLabel54.setText("SCAN BOOK");
        jPanel33.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, 50));

        jSeparator2.setBackground(new java.awt.Color(8, 14, 112));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel33.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 10, 430));

        jLabel15.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel15.setText("Book ID:");
        jPanel33.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, -1, -1));

        jLabel16.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel16.setText("Author:");
        jPanel33.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, -1, -1));

        jLabel17.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel17.setText("Type:");
        jPanel33.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, -1, -1));

        jLabel18.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel18.setText("Subject:");
        jPanel33.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, -1, -1));

        jLabel19.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel19.setText("Quantity:");
        jPanel33.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, -1, -1));

        jLabel20.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel20.setText("Borrow Date:");
        jPanel33.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, -1, -1));

        jLabel21.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel21.setText("Due Date:");
        jPanel33.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, -1, -1));

        jLabel57.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(204, 204, 204));
        jLabel57.setText("BORROW BOOK");
        jPanel33.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, -1, 50));

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/2.png"))); // NOI18N
        jPanel33.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 40, 50));

        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/3.png"))); // NOI18N
        jPanel33.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 40, 50));

        jLabel59.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(204, 204, 204));
        jLabel59.setText("PRINT SLIP");
        jPanel33.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, -1, 50));

        borroweddate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        borroweddate.setDateFormatString("MM/dd/yyyy");
        borroweddate.setEnabled(false);
        borroweddate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel33.add(borroweddate, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 230, 30));

        returneddate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        returneddate.setDateFormatString("MM/dd/yyyy");
        returneddate.setEnabled(false);
        returneddate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel33.add(returneddate, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 380, 230, 30));

        jLabel22.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel22.setText("Name:");
        jPanel33.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, -1, -1));

        textfieldname.setEditable(false);
        textfieldname.setBackground(new java.awt.Color(255, 255, 255));
        textfieldname.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        textfieldname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jPanel33.add(textfieldname, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 180, 30));
        jPanel33.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, 60, 10));

        btn_next.setBackground(new java.awt.Color(0, 0, 0));
        btn_next.setForeground(new java.awt.Color(255, 255, 255));
        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-next-page-25.png"))); // NOI18N
        btn_next.setText("NEXT");
        btn_next.setBorderColor(new java.awt.Color(0, 0, 0));
        btn_next.setColor(new java.awt.Color(0, 0, 0));
        btn_next.setColorClick(new java.awt.Color(102, 102, 102));
        btn_next.setColorOver(new java.awt.Color(0, 0, 0));
        btn_next.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_next.setRadius(30);
        btn_next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_nextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_nextMouseExited(evt);
            }
        });
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });
        jPanel33.add(btn_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 110, 35));

        btn_borrow.setBackground(new java.awt.Color(0, 153, 0));
        btn_borrow.setForeground(new java.awt.Color(255, 255, 255));
        btn_borrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-borrow-book-25.png"))); // NOI18N
        btn_borrow.setText("BORROW");
        btn_borrow.setBorderColor(new java.awt.Color(0, 153, 0));
        btn_borrow.setColor(new java.awt.Color(0, 153, 0));
        btn_borrow.setColorClick(new java.awt.Color(51, 255, 51));
        btn_borrow.setColorOver(new java.awt.Color(0, 153, 0));
        btn_borrow.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_borrow.setRadius(30);
        btn_borrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_borrowMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_borrowMouseExited(evt);
            }
        });
        btn_borrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_borrowActionPerformed(evt);
            }
        });
        jPanel33.add(btn_borrow, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 420, 140, 35));

        btn_print.setForeground(new java.awt.Color(255, 255, 255));
        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        btn_print.setText("PRINT");
        btn_print.setBorderColor(new java.awt.Color(64, 134, 176));
        btn_print.setColor(new java.awt.Color(64, 134, 176));
        btn_print.setColorClick(new java.awt.Color(102, 153, 255));
        btn_print.setColorOver(new java.awt.Color(64, 134, 176));
        btn_print.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_print.setRadius(30);
        btn_print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_printMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_printMouseExited(evt);
            }
        });
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        jPanel33.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 420, 150, 35));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTab.addTab("tab6", panel6);

        panel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel38.setBackground(new java.awt.Color(8, 14, 112));

        jLabel48.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Home / Return Book");

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel48)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));
        jPanel39.setMinimumSize(new java.awt.Dimension(670, 441));
        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel40.setBackground(new java.awt.Color(64, 134, 176));

        jLabel60.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("Return Book");

        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/qrcode1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60)
                .addContainerGap(921, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel39.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 1013, -1));

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel39.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 300, 230));

        result_field1.setEditable(false);
        result_field1.setBackground(new java.awt.Color(255, 255, 255));
        result_field1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        result_field1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        result_field1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                result_field1ComponentAdded(evt);
            }
        });
        result_field1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                result_field1MousePressed(evt);
            }
        });
        result_field1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                result_field1InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        result_field1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result_field1ActionPerformed(evt);
            }
        });
        result_field1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                result_field1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                result_field1KeyTyped(evt);
            }
        });
        jPanel39.add(result_field1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 300, 32));

        comboboxsubject3.setEditable(false);
        comboboxsubject3.setBackground(new java.awt.Color(255, 255, 255));
        comboboxsubject3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboboxsubject3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jPanel39.add(comboboxsubject3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, 230, 30));

        textfieldauthor3.setEditable(false);
        textfieldauthor3.setBackground(new java.awt.Color(255, 255, 255));
        textfieldauthor3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldauthor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jPanel39.add(textfieldauthor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, 230, 30));

        comboboxtype3.setEditable(false);
        comboboxtype3.setBackground(new java.awt.Color(255, 255, 255));
        comboboxtype3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboboxtype3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jPanel39.add(comboboxtype3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 230, 30));

        textfieldborrowedid1.setEditable(false);
        textfieldborrowedid1.setBackground(new java.awt.Color(255, 255, 255));
        textfieldborrowedid1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldborrowedid1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldborrowedid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldborrowedid1ActionPerformed(evt);
            }
        });
        jPanel39.add(textfieldborrowedid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 230, 30));

        textfieldstudentid1.setEditable(false);
        textfieldstudentid1.setBackground(new java.awt.Color(255, 255, 255));
        textfieldstudentid1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldstudentid1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldstudentid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldstudentid1ActionPerformed(evt);
            }
        });
        jPanel39.add(textfieldstudentid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 100, 230, 30));

        textfieldbookid1.setEditable(false);
        textfieldbookid1.setBackground(new java.awt.Color(255, 255, 255));
        textfieldbookid1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldbookid1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldbookid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldbookid1ActionPerformed(evt);
            }
        });
        jPanel39.add(textfieldbookid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 140, 230, 30));

        jLabel23.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel23.setText("Book Title:");
        jPanel39.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/1.png"))); // NOI18N
        jPanel39.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 40, 50));

        jSeparator3.setBackground(new java.awt.Color(8, 14, 112));
        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel39.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, -1, 430));

        jLabel24.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel24.setText("Student ID:");
        jPanel39.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, -1, -1));

        jLabel63.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(204, 204, 204));
        jLabel63.setText("SCAN BOOK");
        jPanel39.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, 50));

        jLabel27.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel27.setText("Book ID:");
        jPanel39.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, -1, -1));

        jLabel28.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel28.setText("Author:");
        jPanel39.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, -1, -1));

        jLabel35.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel35.setText("Type:");
        jPanel39.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, -1, -1));

        jLabel36.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel36.setText("Subject:");
        jPanel39.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, -1, -1));

        jLabel39.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel39.setText("Borrow ID:");
        jPanel39.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, -1, -1));

        jLabel40.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel40.setText("Due Date:");
        jPanel39.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, -1, -1));

        jLabel64.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel64.setText("Return Date:");
        jPanel39.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, -1, -1));

        jLabel65.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(204, 204, 204));
        jLabel65.setText("RETURN BOOK");
        jPanel39.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, -1, 50));

        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/2.png"))); // NOI18N
        jPanel39.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 40, 50));

        returneddate1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        returneddate1.setDateFormatString("MM/dd/yyyy");
        returneddate1.setEnabled(false);
        returneddate1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel39.add(returneddate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 380, 230, 30));

        borroweddate1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        borroweddate1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jPanel39.add(borroweddate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 230, 30));

        btn_next2.setBackground(new java.awt.Color(0, 0, 0));
        btn_next2.setForeground(new java.awt.Color(255, 255, 255));
        btn_next2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-next-page-25.png"))); // NOI18N
        btn_next2.setText("NEXT");
        btn_next2.setBorderColor(new java.awt.Color(0, 0, 0));
        btn_next2.setColor(new java.awt.Color(0, 0, 0));
        btn_next2.setColorClick(new java.awt.Color(102, 102, 102));
        btn_next2.setColorOver(new java.awt.Color(0, 0, 0));
        btn_next2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_next2.setRadius(30);
        btn_next2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_next2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_next2MouseExited(evt);
            }
        });
        btn_next2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_next2ActionPerformed(evt);
            }
        });
        jPanel39.add(btn_next2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 110, 35));

        btn_return.setBackground(new java.awt.Color(0, 153, 0));
        btn_return.setForeground(new java.awt.Color(255, 255, 255));
        btn_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-return-book-25.png"))); // NOI18N
        btn_return.setText("RETURN");
        btn_return.setBorderColor(new java.awt.Color(0, 153, 0));
        btn_return.setColor(new java.awt.Color(0, 153, 0));
        btn_return.setColorClick(new java.awt.Color(51, 255, 51));
        btn_return.setColorOver(new java.awt.Color(0, 153, 0));
        btn_return.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_return.setRadius(30);
        btn_return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_returnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_returnMouseExited(evt);
            }
        });
        btn_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_returnActionPerformed(evt);
            }
        });
        jPanel39.add(btn_return, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 420, 140, 35));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTab.addTab("tab7", panel7);

        jPanel1.add(mainTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 64, 1080, 600));

        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/close.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        jPanel1.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 123, 30, 30));

        aboutImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/about.jpg"))); // NOI18N
        jPanel1.add(aboutImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 113, 260, 550));

        lowerPanel.setBackground(new java.awt.Color(151, 205, 205));

        dateLab.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dateLab.setText("jLabel33");

        dateLab1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        dateLab1.setText("DATE:");

        javax.swing.GroupLayout lowerPanelLayout = new javax.swing.GroupLayout(lowerPanel);
        lowerPanel.setLayout(lowerPanelLayout);
        lowerPanelLayout.setHorizontalGroup(
            lowerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerPanelLayout.createSequentialGroup()
                .addContainerGap(1199, Short.MAX_VALUE)
                .addComponent(dateLab1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateLab)
                .addGap(35, 35, 35))
        );
        lowerPanelLayout.setVerticalGroup(
            lowerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(dateLab1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
            .addComponent(dateLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(lowerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 665, 1340, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1350, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int count = 0;
    String[] imagenames = {"sl1.png", "sl2.png", "sl3.png"};

    public void student() {
        Connection con = (Connection) myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = (PreparedStatement) con.prepareStatement("SELECT ID, first_name, last_name, lrn, Username FROM registration where ID = ? ");
            ps.setInt(1, myConnection.index);
            rs = ps.executeQuery();

            if (rs.next()) {
                String i = rs.getString("ID");
                textfieldstudentid.setText(i);
                textfieldstudentid1.setText(i);
                String f = rs.getString("first_name");
                textfieldfirstname.setText(f);
                //lbl_firstname.setText(f);
                String l = rs.getString("last_name");
                textfieldlastname.setText(l);
                String r = rs.getString("lrn");
                textfieldlrn.setText(r);
                String u = rs.getString("Username");
                textfieldusername.setText(u);

                String n = rs.getString("first_name") + " " + rs.getString("last_name");
                textfieldname.setText(n);
                lbl_fullname.setText(n);
                String m = rs.getString("lrn");
                //textfieldlrn2.setText(m);
            }

            ps = (PreparedStatement) con.prepareStatement("SELECT  borrowed_books.book_title, borrowed_books.author, borrowed_books.type,borrowed_books.subject, borrowed_books.borrow_date, borrowed_books.return_date, (CASE WHEN datediff(return_date,CURDATE()) >= 0 then 'PENDING' ELSE 'LATE' END) as status FROM borrowed_books INNER JOIN registration ON borrowed_books.student_id= registration.ID WHERE registration.ID = ?  ORDER BY DATE_FORMAT(borrow_date,'%Y-%m-%d') DESC ");

            ps.setString(1, String.valueOf(textfieldstudentid.getText()));
            rs = ps.executeQuery();

            table_myborrowed.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public boolean verifData() {
        if (textfieldfirstname.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "First name is required", "Message", JOptionPane.ERROR_MESSAGE);
        } else if (textfieldlastname.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Last name is required", "Message", JOptionPane.ERROR_MESSAGE);
        } else if (textfieldlrn.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "LRN is required", "Message", JOptionPane.ERROR_MESSAGE);
        } else if (textfieldusername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username is required", "Message", JOptionPane.ERROR_MESSAGE);
        } else if (jPasswordField1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password is required", "Message", JOptionPane.ERROR_MESSAGE);
        } else if (!String.valueOf(jPasswordField1.getPassword()).equals(String.valueOf(jPasswordField2.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Password does not match", "Message", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
        return false;
    }

    public boolean verifScan() {
        if (result_field.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Scanning the book is required", "Message", JOptionPane.ERROR_MESSAGE);

        } else {
            return true;
        }
        return false;
    }

    public boolean verifScan2() {
        if (result_field1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Scanning the book is required", "Message", JOptionPane.ERROR_MESSAGE);

        } else {
            return true;
        }
        return false;
    }

    public boolean issued() {
        boolean issued = false;
        int bookid = Integer.parseInt(textfieldbookid.getText());
        int studentid = Integer.parseInt(textfieldstudentid.getText());
        try {
            String qr = "SELECT * FROM borrowed_books where book_id =? and student_id = ?";
            pst = conn.prepareStatement(qr);
            pst.setInt(1, bookid);
            pst.setInt(2, studentid);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                issued = true;
            } else {
                issued = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return issued;
    }

    public boolean returned() {
        boolean returned = false;

        try {
            String qr = "SELECT * FROM borrowed_books where book_id =? and student_id = ?";
            pst = conn.prepareStatement(qr);
            pst.setString(1, String.valueOf(textfieldstudentid1.getText()));
            pst.setString(2, String.valueOf(textfieldbookid1.getText()));

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                returned = true;
            } else {
                returned = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returned;
    }

    public boolean invalidQR() {
        boolean invalidQR = false;

        try {
            String qr = "SELECT * FROM books where book_title=?";
            pst = conn.prepareStatement(qr);
            pst.setString(1, String.valueOf(result_field1.getText()));

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                invalidQR = true;
            } else {
                invalidQR = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return invalidQR;
    }

    public void populateJtable() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        LibrarianHomepageQuery lq = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage> infList = lq.InfoList();
        String[] colNames = { "Title", "Author", "Type", "Subject", "Quantity"};
        Object[][] rows = new Object[infList.size()][6];

        for (int i = 0; i < infList.size(); i++) {
            //rows[i][0] = infList.get(i).getBook_id();
            rows[i][0] = infList.get(i).getBook_title();
            rows[i][1] = infList.get(i).getAuthor();
            rows[i][2] = infList.get(i).getType();
            rows[i][3] = infList.get(i).getSubject();
            rows[i][4] = infList.get(i).getQuantity();

        }
        MyModel2 mmd2 = new MyModel2(rows, colNames);
        table_bookreport.setModel(mmd2);
        table_bookreport.setRowHeight(30);

        LibrarianHomepageQuery lq12 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage2> infList12 = lq12.InfoList12();
        String[] colNames12 = {"Title", "Author", "Borrow Date", "Return Date", "LRN", "Student Name"};
        Object[][] rows12 = new Object[infList12.size()][9];

        for (int i = 0; i < infList12.size(); i++) {
            //rows12[i][0] = infList12.get(i).getBorrowed_id();
            rows12[i][0] = infList12.get(i).getBook_title();
            rows12[i][1] = infList12.get(i).getAuthor();

            //rows12[i][3] = infList12.get(i).getQuantity();
            rows12[i][2] = infList12.get(i).getBorrow_date();
            rows12[i][3] = infList12.get(i).getReturn_date();
            // rows12[i5[6] = infList12.get(i).getStudent_id();
            rows12[i][4] = infList12.get(i).getLrn();
            //rows12[i][7] = infList12.get(i).getUsername();
            rows12[i][5] = infList12.get(i).getName();

        }
        MyModel16 mmd16 = new MyModel16(rows12, colNames12);
        table_borrowedreport.setModel(mmd16);
        table_borrowedreport.setRowHeight(30);

        LibrarianHomepageQuery lq3 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage6> infList3 = lq3.InfoList18();
        String[] colNames3 = {"Title", "Author", "Type", "Subject", "Borrow Date", "Return Date", "Status"};
        Object[][] rows3 = new Object[infList3.size()][10];

        for (int i = 0; i < infList3.size(); i++) {
            //rows3[i][0] = infList3.get(i).getBorrowed_id();
            rows3[i][0] = infList3.get(i).getBook_title();
            rows3[i][1] = infList3.get(i).getAuthor();
            rows3[i][2] = infList3.get(i).getType();
            rows3[i][3] = infList3.get(i).getSubject();
            //rows3[i][5] = infList3.get(i).getQuantity();
            rows3[i][4] = infList3.get(i).getBorrow_date();
            rows3[i][5] = infList3.get(i).getReturn_date();
            rows3[i][6] = infList3.get(i).getStatus();
            //rows3[i][8] = infList3.get(i).getLrn();
            //rows3[i][9] = infList3.get(i).getName();

        }
        MyModel17 mmd17 = new MyModel17(rows3, colNames3);
        table_myborrowed.setModel(mmd17);
        table_myborrowed.setRowHeight(30);

        /*LibrarianHomepageQuery lq2 = new LibrarianHomepageQuery();
        ArrayList<StudentAccount> infList2 = lq2.InfoList14();
        String[] colNames2 = {"ID", "First Name", "Last Name", "Username" };
        Object[][] rows2 = new Object [infList2.size()][5];
        
         for(int i = 0; i < infList2.size(); i++){
            rows2[i][0] = infList2.get(i).getID();
            rows2[i][1] = infList2.get(i).getFirst_name();
            rows2[i][2] = infList2.get(i).getLast_name();
            rows2[i][3] = infList2.get(i).getUsername();
            
           
        }
       MyModel13 mmd13 = new MyModel13(rows2, colNames2);
        table_students.setModel(mmd13);
        table_students.setRowHeight(40);*/
 /*LibrarianHomepageQuery lq17 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage6> infList17 = lq17.InfoList17();
        String[] colNames17 = {"ID", "Title", "Author", "Borrow Date", "Due Date", "LRN", "Student Name", "Status"};
        Object[][] rows17 = new Object[infList12.size()][10];

        for (int i = 0; i < infList17.size(); i++) {
            rows17[i][0] = infList17.get(i).getBorrowed_id();
            rows17[i][1] = infList17.get(i).getBook_title();
            rows17[i][2] = infList17.get(i).getAuthor();

            // rows12[i][3] = infList12.get(i).getQuantity();
            rows17[i][3] = infList17.get(i).getBorrow_date();
            rows17[i][4] = infList17.get(i).getReturn_date();
            // rows12[i][6] = infList12.get(i).getStudent_id();
            rows17[i][5] = infList17.get(i).getLrn();
            //rows12[i][7] = infList12.get(i).getUsername();
            rows17[i][6] = infList17.get(i).getName();
            rows17[i][7] = infList17.get(i).getStatus();

        }
        MyModel20 mmd20 = new MyModel20(rows17, colNames17);
        LibrarianHome lh = new LibrarianHome();
        lh.table_borrowedlate.setModel(mmd20);
        lh.table_borrowedlate.setRowHeight(30);
         */
    }

    int x = 260;
    private void hamburgerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hamburgerMouseClicked
        if (x == 260) {
            sideNav.setSize(260, 550);
            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 260; i >= 0; i--) {
                            Thread.sleep(1);
                            sideNav.setSize(i, 550);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            };
            th.start();
            x = 0;
        }
    }//GEN-LAST:event_hamburgerMouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        if (x == 0) {
            sideNav.show();
            sideNav.setSize(x, 550);
            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i <= x; i++) {
                            Thread.sleep(1);
                            sideNav.setSize(i, 550);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            };
            th.start();
            x = 260;

        }
    }//GEN-LAST:event_closeMouseClicked

    private void tab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseClicked
        mainTab.setSelectedIndex(1);
        userTab.setSelectedIndex(0);
        refreshFields();

    }//GEN-LAST:event_tab2MouseClicked

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked
        mainTab.setSelectedIndex(2);
        refreshFields();
    }//GEN-LAST:event_tab3MouseClicked

    private void tab4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseClicked
        mainTab.setSelectedIndex(3);
        refreshFields();
    }//GEN-LAST:event_tab4MouseClicked

    private void tab6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseClicked
        mainTab.setSelectedIndex(4);
        refreshFields();
    }//GEN-LAST:event_tab6MouseClicked

    private void tab2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseEntered
        tab2.setBackground(new Color(64, 134, 176));
        tab1.setBackground(new Color(149, 189, 219));
        tab3.setBackground(new Color(149, 189, 219));
        tab4.setBackground(new Color(149, 189, 219));

        tab6.setBackground(new Color(149, 189, 219));
        tab7.setBackground(new Color(149, 189, 219));
        tab8.setBackground(new Color(149, 189, 219));
        jLabel3.setForeground(new Color(0, 0, 0));
        jLabel3.setFont(new Font("Agency FB", Font.BOLD, 22));
    }//GEN-LAST:event_tab2MouseEntered

    private void tab2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseExited
        tab2.setBackground(new Color(149, 189, 219));
        jLabel3.setForeground(new Color(0, 0, 0));
        jLabel3.setFont(new Font("Agency FB", Font.PLAIN, 22));
    }//GEN-LAST:event_tab2MouseExited

    private void tab3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseEntered
        tab3.setBackground(new Color(64, 134, 176));
        tab1.setBackground(new Color(149, 189, 219));
        tab2.setBackground(new Color(149, 189, 219));
        tab4.setBackground(new Color(149, 189, 219));

        tab6.setBackground(new Color(149, 189, 219));
        tab7.setBackground(new Color(149, 189, 219));
        tab8.setBackground(new Color(149, 189, 219));
        jLabel4.setForeground(new Color(0, 0, 0));
        jLabel4.setFont(new Font("Agency FB", Font.BOLD, 22));
    }//GEN-LAST:event_tab3MouseEntered

    private void tab3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseExited
        tab3.setBackground(new Color(149, 189, 219));
        jLabel4.setForeground(new Color(0, 0, 0));
        jLabel4.setFont(new Font("Agency FB", Font.PLAIN, 22));
    }//GEN-LAST:event_tab3MouseExited

    private void tab4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseEntered
        tab4.setBackground(new Color(64, 134, 176));
        tab1.setBackground(new Color(149, 189, 219));
        tab2.setBackground(new Color(149, 189, 219));
        tab3.setBackground(new Color(149, 189, 219));

        tab6.setBackground(new Color(149, 189, 219));
        tab7.setBackground(new Color(149, 189, 219));
        tab8.setBackground(new Color(149, 189, 219));
        jLabel5.setForeground(new Color(0, 0, 0));
        jLabel5.setFont(new Font("Agency FB", Font.BOLD, 22));
    }//GEN-LAST:event_tab4MouseEntered

    private void tab4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseExited
        tab4.setBackground(new Color(149, 189, 219));
        jLabel5.setForeground(new Color(0, 0, 0));
        jLabel5.setFont(new Font("Agency FB", Font.PLAIN, 22));
    }//GEN-LAST:event_tab4MouseExited

    private void tab6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseEntered
        tab6.setBackground(new Color(64, 134, 176));
        tab1.setBackground(new Color(149, 189, 219));
        tab2.setBackground(new Color(149, 189, 219));
        tab3.setBackground(new Color(149, 189, 219));
        tab4.setBackground(new Color(149, 189, 219));
        tab7.setBackground(new Color(149, 189, 219));
        tab8.setBackground(new Color(149, 189, 219));
        jLabel7.setForeground(new Color(0, 0, 0));
        jLabel7.setFont(new Font("Agency FB", Font.BOLD, 22));
    }//GEN-LAST:event_tab6MouseEntered

    private void tab6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseExited
        tab6.setBackground(new Color(149, 189, 219));
        jLabel7.setForeground(new Color(0, 0, 0));
        jLabel7.setFont(new Font("Agency FB", Font.PLAIN, 22));
    }//GEN-LAST:event_tab6MouseExited

    //Close and Minimize Frame
    private void clMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clMouseEntered
        closeFrame.setBackground(new Color(255, 0, 0));
    }//GEN-LAST:event_clMouseEntered

    private void closeFrameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeFrameMouseEntered
        closeFrame.setBackground(new Color(255, 0, 0));
    }//GEN-LAST:event_closeFrameMouseEntered

    private void minFrameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minFrameMouseEntered
        minFrame.setBackground(new Color(149, 189, 219));
    }//GEN-LAST:event_minFrameMouseEntered

    private void closeFrameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeFrameMouseExited
        closeFrame.setBackground(new Color(64, 134, 176));
    }//GEN-LAST:event_closeFrameMouseExited

    private void minFrameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minFrameMouseExited
        minFrame.setBackground(new Color(64, 134, 176));
    }//GEN-LAST:event_minFrameMouseExited

    private void closeFrameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeFrameMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeFrameMouseClicked

    private void clMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clMouseClicked
        System.exit(0);

    }//GEN-LAST:event_clMouseClicked

    private void minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseClicked
        this.setExtendedState(StudentHome.ICONIFIED);
    }//GEN-LAST:event_minMouseClicked

    private void minMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseEntered
        minFrame.setBackground(new Color(149, 189, 219));
    }//GEN-LAST:event_minMouseEntered

    private void clMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clMouseExited
        closeFrame.setBackground(new Color(64, 134, 176));
    }//GEN-LAST:event_clMouseExited

    private void minMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseExited
        minFrame.setBackground(new Color(64, 134, 176));
    }//GEN-LAST:event_minMouseExited

    private void minFrameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minFrameMouseClicked
        this.setExtendedState(StudentHome.ICONIFIED);
    }//GEN-LAST:event_minFrameMouseClicked

    private void tab2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MousePressed
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 2);
        tab2.setBorder(b);
        Border c = BorderFactory.createLineBorder(new Color(8, 14, 112), 1);
        tab9.setBorder(c);
        Border d = BorderFactory.createLineBorder(new Color(8, 14, 112), 0);
        tab10.setBorder(d);
    }//GEN-LAST:event_tab2MousePressed

    private void tab2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseReleased
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 0);
        tab2.setBorder(b);
    }//GEN-LAST:event_tab2MouseReleased

    private void tab3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MousePressed
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 2);
        tab3.setBorder(b);
    }//GEN-LAST:event_tab3MousePressed

    private void tab3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseReleased
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 0);
        tab3.setBorder(b);
    }//GEN-LAST:event_tab3MouseReleased

    private void tab4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MousePressed
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 2);
        tab4.setBorder(b);
    }//GEN-LAST:event_tab4MousePressed

    private void tab4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseReleased
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 0);
        tab4.setBorder(b);
    }//GEN-LAST:event_tab4MouseReleased

    private void tab6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MousePressed
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 2);
        tab6.setBorder(b);
    }//GEN-LAST:event_tab6MousePressed

    private void tab6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseReleased
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 0);
        tab6.setBorder(b);
    }//GEN-LAST:event_tab6MouseReleased

    private void hamburgerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hamburgerMousePressed

    }//GEN-LAST:event_hamburgerMousePressed

    private void hamburgerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hamburgerMouseEntered

    }//GEN-LAST:event_hamburgerMouseEntered

    private void tab8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab8MouseEntered
        tab8.setBackground(new Color(64, 134, 176));
        tab1.setBackground(new Color(149, 189, 219));
        tab2.setBackground(new Color(149, 189, 219));
        tab3.setBackground(new Color(149, 189, 219));
        tab4.setBackground(new Color(149, 189, 219));

        tab6.setBackground(new Color(149, 189, 219));
        tab7.setBackground(new Color(149, 189, 219));
        jLabel11.setForeground(new Color(0, 0, 0));
        jLabel11.setFont(new Font("Agency FB", Font.BOLD, 22));
    }//GEN-LAST:event_tab8MouseEntered

    private void tab8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab8MouseExited
        tab8.setBackground(new Color(149, 189, 219));
        jLabel11.setForeground(new Color(0, 0, 0));
        jLabel11.setFont(new Font("Agency FB", Font.PLAIN, 22));
    }//GEN-LAST:event_tab8MouseExited

    private void tab8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab8MousePressed
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 2);
        tab8.setBorder(b);
    }//GEN-LAST:event_tab8MousePressed

    private void tab8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab8MouseReleased
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 0);
        tab8.setBorder(b);
    }//GEN-LAST:event_tab8MouseReleased


    private void view5ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_view5ComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_view5ComponentMoved

    private void tab8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab8MouseClicked
        refreshFields();
        JFrame frame = new JFrame("Swing Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            Users user = new Users();
            user.setVisible(true);
            this.dispose();
            //close webcam
            webcam.close();

        } else if (result == JOptionPane.NO_OPTION) {

        }
    }//GEN-LAST:event_tab8MouseClicked

    private void tab1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseReleased
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 0);
        tab1.setBorder(b);
    }//GEN-LAST:event_tab1MouseReleased

    //Border
    private void tab1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MousePressed
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 2);
        tab1.setBorder(b);
    }//GEN-LAST:event_tab1MousePressed

    private void tab1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseExited
        tab1.setBackground(new Color(149, 189, 219));
        jLabel2.setForeground(new Color(0, 0, 0));
        jLabel2.setFont(new Font("Agency FB", Font.PLAIN, 22));
    }//GEN-LAST:event_tab1MouseExited

    //Hover
    private void tab1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseEntered
        tab1.setBackground(new Color(64, 134, 176));
        tab2.setBackground(new Color(149, 189, 219));
        tab3.setBackground(new Color(149, 189, 219));
        tab4.setBackground(new Color(149, 189, 219));

        tab6.setBackground(new Color(149, 189, 219));
        tab7.setBackground(new Color(149, 189, 219));
        tab8.setBackground(new Color(149, 189, 219));
        jLabel2.setForeground(new Color(0, 0, 0));
        jLabel2.setFont(new Font("Agency FB", Font.BOLD, 22));
    }//GEN-LAST:event_tab1MouseEntered

    //Clicked
    private void tab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseClicked
        mainTab.setSelectedIndex(0);
        refreshFields();
    }//GEN-LAST:event_tab1MouseClicked

    private void tab9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab9MouseClicked
        userTab.setSelectedIndex(0);
        refreshFields();
    }//GEN-LAST:event_tab9MouseClicked

    private void tab9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab9MouseEntered
        tab9.setBackground(new Color(64, 134, 176));
        label1.setForeground(new Color(255, 255, 255));
        /*tab9.setLocation(0,5);
        tab10.setLocation(150,11);
        tab11.setLocation(300,11);
        tab12.setLocation(450,11);*/
    }//GEN-LAST:event_tab9MouseEntered

    private void tab9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab9MouseExited
        tab9.setBackground(new Color(149, 189, 219));
        label1.setForeground(new Color(0, 0, 0));
        //tab9.setLocation(0,11);
    }//GEN-LAST:event_tab9MouseExited

    private void tab9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab9MousePressed
        Border b = BorderFactory.createLineBorder(new Color(8, 14, 112), 1);
        tab9.setBorder(b);
        Border c = BorderFactory.createLineBorder(new Color(0, 0, 0), 0);
        tab10.setBorder(c);

    }//GEN-LAST:event_tab9MousePressed

    private void tab9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab9MouseReleased

    }//GEN-LAST:event_tab9MouseReleased

    private void tab10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab10MouseClicked
        userTab.setSelectedIndex(1);
        refreshFields();
        changeTable(table_myborrowed, 6);
    }//GEN-LAST:event_tab10MouseClicked

    private void tab10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab10MouseEntered
        tab10.setBackground(new Color(64, 134, 176));
        label2.setForeground(new Color(255, 255, 255));
        /*tab9.setLocation(0,11);
        tab10.setLocation(150,5);
        tab11.setLocation(300,11);
        tab12.setLocation(450,11);*/
    }//GEN-LAST:event_tab10MouseEntered

    private void tab10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab10MouseExited
        tab10.setBackground(new Color(149, 189, 219));
        label2.setForeground(new Color(0, 0, 0));
        //tab10.setLocation(150,11);
    }//GEN-LAST:event_tab10MouseExited

    private void tab10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab10MousePressed
        Border b = BorderFactory.createLineBorder(new Color(8, 14, 112), 1);
        tab10.setBorder(b);
        Border c = BorderFactory.createLineBorder(new Color(0, 0, 0), 0);
        tab9.setBorder(c);

    }//GEN-LAST:event_tab10MousePressed

    private void textfieldfirstnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldfirstnameMouseClicked

    }//GEN-LAST:event_textfieldfirstnameMouseClicked

    private void textfieldfirstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldfirstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldfirstnameActionPerformed

    private void textfieldlastnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldlastnameMouseClicked

    }//GEN-LAST:event_textfieldlastnameMouseClicked

    private void textfieldlastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldlastnameActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldlastnameActionPerformed

    private void textfieldusernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldusernameMouseClicked

    }//GEN-LAST:event_textfieldusernameMouseClicked

    private void jPasswordField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MouseClicked

    }//GEN-LAST:event_jPasswordField1MouseClicked

    private void jPasswordField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField2MouseClicked

    }//GEN-LAST:event_jPasswordField2MouseClicked

    private void jPasswordField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField2ActionPerformed

    public void clearFields() {
        textfieldfirstname.setText(null);
        textfieldlastname.setText(null);
        textfieldlrn.setText(null);
        textfieldusername.setText(null);
        jPasswordField1.setText(null);
        jPasswordField2.setText(null);
    }

    private void result_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result_fieldActionPerformed

    public void refreshFields() {
        jPasswordField1.setText(null);
        jPasswordField2.setText(null);

        result_field.setText(null);
        textfieldbookid.setText(null);
        textfieldauthor2.setText(null);
        comboboxtype2.setText(null);
        comboboxsubject2.setText(null);
        textfieldquantity2.setText(null);
        textarea.setText(null);

        result_field1.setText(null);
        textfieldbookid1.setText(null);
        textfieldborrowedid1.setText(null);
        textfieldauthor3.setText(null);
        comboboxtype3.setText(null);
        comboboxsubject3.setText(null);
        borroweddate1.setText(null);
    }

    public void refreshJtable() {
        table_borrowedreport.setModel(new DefaultTableModel());
        //table_myborrowed.setModel(new DefaultTableModel());
        table_bookreport.setModel(new DefaultTableModel());
        populateJtable();
    }

    private void textfieldusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldusernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldusernameActionPerformed

    private void lbl_nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_nextMouseClicked
        /*ImageIcon[] imagelist = new ImageIcon[3];
        for (int i=0;i<imagelist.length;i++){
          imagelist[i] =new ImageIcon(getClass().getResource("/librarymanagementkiosk/" + imagenames[i]));
        }
        if(count<0) count = 1;
            if(count>=0 && count<imagenames.length){
            lbl_images.setIcon(imagelist[count]);
           
            count++;
       }*/

        pos++;
        if (pos >= getItemsList().size()) {
            pos = getItemsList().size() - 1;
        }
        showItem(pos);
    }//GEN-LAST:event_lbl_nextMouseClicked

    private void lbl_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_backMouseClicked
        /*ImageIcon[] imagelist = new ImageIcon[3];
        for (int i=0;i<imagelist.length;i++){
          imagelist[i] =new ImageIcon(getClass().getResource("/librarymanagementkiosk/" + imagenames[i]));
        }
        if(count>=imagenames.length) count = imagenames.length-2;
            if(count>=0 && count<imagenames.length){
            lbl_images.setIcon(imagelist[count]);
           
            count--;
       }     */

        pos--;
        if (pos < 0) {
            pos = 0;
        }
        showItem(pos);
    }//GEN-LAST:event_lbl_backMouseClicked

    private void textfieldquantity2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldquantity2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldquantity2ActionPerformed

    private void result_fieldComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_result_fieldComponentAdded

    }//GEN-LAST:event_result_fieldComponentAdded

    private void result_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_result_fieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_result_fieldKeyReleased

    private void result_fieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_result_fieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_result_fieldKeyTyped

    private void result_fieldInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_result_fieldInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_result_fieldInputMethodTextChanged

    private void textfieldstudentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldstudentidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldstudentidActionPerformed

    private void textfieldbookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldbookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldbookidActionPerformed

    private void tab7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MouseClicked
        mainTab.setSelectedIndex(5);
        refreshFields();
    }//GEN-LAST:event_tab7MouseClicked

    private void tab7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MouseEntered
        tab7.setBackground(new Color(64, 134, 176));
        tab1.setBackground(new Color(149, 189, 219));
        tab2.setBackground(new Color(149, 189, 219));
        tab3.setBackground(new Color(149, 189, 219));
        tab4.setBackground(new Color(149, 189, 219));

        tab6.setBackground(new Color(149, 189, 219));
        tab8.setBackground(new Color(149, 189, 219));

        jLabel10.setForeground(new Color(0, 0, 0));
        jLabel10.setFont(new Font("Agency FB", Font.BOLD, 22));
    }//GEN-LAST:event_tab7MouseEntered

    private void tab7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MouseExited
        tab7.setBackground(new Color(149, 189, 219));
        jLabel10.setForeground(new Color(0, 0, 0));
        jLabel10.setFont(new Font("Agency FB", Font.PLAIN, 22));
    }//GEN-LAST:event_tab7MouseExited

    private void tab7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MousePressed
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 2);
        tab7.setBorder(b);
    }//GEN-LAST:event_tab7MousePressed

    private void tab7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MouseReleased
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 0);
        tab7.setBorder(b);
    }//GEN-LAST:event_tab7MouseReleased

    private void result_field1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_result_field1ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_result_field1ComponentAdded

    private void result_field1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_result_field1InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_result_field1InputMethodTextChanged

    private void result_field1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result_field1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result_field1ActionPerformed

    private void result_field1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_result_field1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_result_field1KeyReleased

    private void result_field1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_result_field1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_result_field1KeyTyped

    private void textfieldborrowedid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldborrowedid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldborrowedid1ActionPerformed

    private void textfieldstudentid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldstudentid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldstudentid1ActionPerformed

    private void textfieldbookid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldbookid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldbookid1ActionPerformed

    private void textfieldlrnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldlrnMouseClicked

    }//GEN-LAST:event_textfieldlrnMouseClicked

    private void textfieldlrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldlrnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldlrnActionPerformed

    private void lbl_fullnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_fullnameMouseClicked
        mainTab.setSelectedIndex(1);
    }//GEN-LAST:event_lbl_fullnameMouseClicked

    private void lbl_fullnameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_fullnameMouseEntered
        lbl_fullname.setForeground(new Color(0, 0, 0));
        lbl_fullname.setFont(new Font("Segoe UI", Font.BOLD, 16));
    }//GEN-LAST:event_lbl_fullnameMouseEntered

    private void lbl_fullnameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_fullnameMouseExited
        lbl_fullname.setForeground(new Color(51, 51, 51));
        lbl_fullname.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    }//GEN-LAST:event_lbl_fullnameMouseExited

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        MyModel2 table = (MyModel2) table_bookreport.getModel();
        String search = jTextField4.getText();
        TableRowSorter<MyModel2> tr = new TableRowSorter<>(table);
        table_bookreport.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        MyModel16 table = (MyModel16) table_borrowedreport.getModel();
        String search = jTextField8.getText();
        TableRowSorter<MyModel16> tr = new TableRowSorter<>(table);
        table_borrowedreport.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
        MyModel17 table = (MyModel17) table_myborrowed.getModel();
        String search = jTextField9.getText();
        TableRowSorter<MyModel17> tr = new TableRowSorter<>(table);
        table_myborrowed.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_jTextField9KeyReleased

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        jTextField4.setBorder(a);
    }//GEN-LAST:event_jTextField4MousePressed

    private void jTextField8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8MousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        jTextField8.setBorder(a);
    }//GEN-LAST:event_jTextField8MousePressed

    private void result_fieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_result_fieldMousePressed

    }//GEN-LAST:event_result_fieldMousePressed

    private void result_field1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_result_field1MousePressed

    }//GEN-LAST:event_result_field1MousePressed

    private void textfieldfirstnameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldfirstnameMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldfirstname.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        textfieldlastname.setBorder(b);
        textfieldlrn.setBorder(b);
        textfieldusername.setBorder(b);
        jPasswordField1.setBorder(b);
        jPasswordField2.setBorder(b);
    }//GEN-LAST:event_textfieldfirstnameMousePressed

    private void textfieldlastnameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldlastnameMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldlastname.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        textfieldfirstname.setBorder(b);
        textfieldlrn.setBorder(b);
        textfieldusername.setBorder(b);
        jPasswordField1.setBorder(b);
        jPasswordField2.setBorder(b);
    }//GEN-LAST:event_textfieldlastnameMousePressed

    private void textfieldlrnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldlrnMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldlrn.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        textfieldfirstname.setBorder(b);
        textfieldlastname.setBorder(b);
        textfieldusername.setBorder(b);
        jPasswordField1.setBorder(b);
        jPasswordField2.setBorder(b);
    }//GEN-LAST:event_textfieldlrnMousePressed

    private void textfieldusernameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldusernameMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldusername.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        textfieldfirstname.setBorder(b);
        textfieldlastname.setBorder(b);
        textfieldlrn.setBorder(b);
        jPasswordField1.setBorder(b);
        jPasswordField2.setBorder(b);
    }//GEN-LAST:event_textfieldusernameMousePressed

    private void jPasswordField1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        jPasswordField1.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        textfieldfirstname.setBorder(b);
        textfieldlastname.setBorder(b);
        textfieldlrn.setBorder(b);
        textfieldusername.setBorder(b);
        jPasswordField2.setBorder(b);
    }//GEN-LAST:event_jPasswordField1MousePressed

    private void jPasswordField2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField2MousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        jPasswordField2.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        textfieldfirstname.setBorder(b);
        textfieldlastname.setBorder(b);
        textfieldlrn.setBorder(b);
        textfieldusername.setBorder(b);
        jPasswordField1.setBorder(b);
    }//GEN-LAST:event_jPasswordField2MousePressed

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4MouseClicked

    private void textfieldlrnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldlrnKeyPressed
        String lrn = textfieldlrn.getText();
        int length = lrn.length();
        char c = evt.getKeyChar();

        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            if (length < 12) {
                textfieldlrn.setEditable(true);
            } else {
                textfieldlrn.setEditable(false);
            }
        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACKSPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                textfieldlrn.setEditable(true);
            } else {
                textfieldlrn.setEditable(false);
            }
        }
    }//GEN-LAST:event_textfieldlrnKeyPressed

    private void updateinfo_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateinfo_btnMouseEntered

    }//GEN-LAST:event_updateinfo_btnMouseEntered

    private void updateinfo_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateinfo_btnMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_updateinfo_btnMouseExited

    private void updateinfo_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateinfo_btnActionPerformed
        Connection con = (Connection) myConnection.getConnection();
        PreparedStatement ps;
        String updateQuery = "";
        if (verifData()) {
            /*if (!textfieldfirstname.getText().equals("")){
            int ID = Integer.valueOf(jTextField1.getText());
            String first_name = textfieldfirstname.getText();
            String last_name = textfieldlastname.getText();
            String username = textfieldusername.getText();
            String password = jPasswordField1.getText();

            // update info and picture

                    LibrarianAccount a = new LibrarianAccount(ID, first_name, last_name, username, password);
                    LibrarianHomepageQuery lq = new LibrarianHomepageQuery();
                    lq.updateLibrarian(a, true);
                    refreshJtable();
                    clearFields();
                    student();

        }else {
            JOptionPane.showMessageDialog(null, "Select information from table to edit", "Message",JOptionPane.ERROR_MESSAGE);
        }*/

            try {
                updateQuery = "UPDATE registration SET first_name=?, last_name=?, lrn=?, username=?, password=? WHERE ID=?";
                ps = (PreparedStatement) con.prepareStatement(updateQuery);
                ps.setString(1, textfieldfirstname.getText().trim());
                ps.setString(2, textfieldlastname.getText().trim());
                ps.setString(3, textfieldlrn.getText().trim());
                ps.setString(4, textfieldusername.getText().trim());
                ps.setString(5, jPasswordField1.getText().trim());
                ps.setInt(6, myConnection.index);

                if (ps.executeUpdate() != 0) {
                    JOptionPane.showMessageDialog(null, "Your Information is Updated", "Message", JOptionPane.INFORMATION_MESSAGE);
                    jPasswordField1.setText(null);
                    jPasswordField2.setText(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Something is wrong", "Message", JOptionPane.ERROR_MESSAGE);

                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentHome.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "username '" + textfieldusername.getText().trim() + "' already exist!");
            }
        }
    }//GEN-LAST:event_updateinfo_btnActionPerformed

    private void btn_nextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nextMouseEntered

    }//GEN-LAST:event_btn_nextMouseEntered

    private void btn_nextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nextMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_nextMouseExited

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        Connection con = (Connection) myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        if (verifScan()) {
            try {
                ps = (PreparedStatement) con.prepareStatement("SELECT * FROM books where book_title = ? ");
                ps.setString(1, String.valueOf(result_field.getText()));
                rs = ps.executeQuery();

                if (rs.next()) {
                    String f = rs.getString("book_id");
                    textfieldbookid.setText(f);
                    String l = rs.getString("author");
                    textfieldauthor2.setText(l);
                    String u = rs.getString("type");
                    comboboxtype2.setText(u);
                    String s = rs.getString("subject");
                    comboboxsubject2.setText(s);

                    //textfieldquantity2.setText("1");
                    String q = rs.getString("quantity");
                    textfieldquantity2.setText(q);

                } else if (!result_field.equals("book_title")) {
                    JOptionPane.showMessageDialog(null, "This book is not in the database!", "Message", JOptionPane.ERROR_MESSAGE);
                    result_field.setText("");
                }

                //}else{
                //     JOptionPane.showMessageDialog(null, "This book is not in the Database", "Message", JOptionPane.ERROR_MESSAGE);
                //}
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_borrowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_borrowMouseEntered

    }//GEN-LAST:event_btn_borrowMouseEntered

    private void btn_borrowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_borrowMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_borrowMouseExited

    private void btn_borrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_borrowActionPerformed
        if (issued() == false) {

            if (textfieldquantity2.getText().equals("0")) {
                JOptionPane.showMessageDialog(null, "Book is not available", "Message", JOptionPane.ERROR_MESSAGE);
            } else {

                ititle = result_field.getText();
                iauthor = textfieldauthor2.getText();
                itype = comboboxtype2.getText();
                isubject = comboboxsubject2.getText();
                //iquantity = textfieldquantity2.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                iborrowdate = sdf.format(borroweddate.getDate());
                ireturndate = sdf.format(returneddate.getDate());

                istatus = status.getText();

                textarea.setText(
                        textarea.getText() + "\n"
                        + (result_field.getText()));

                result_field.setText("");
                result_field1.setText("");
                textfieldauthor2.setText("");
                comboboxtype2.setText("");
                comboboxsubject2.setText("");
                textfieldquantity2.setText("");

                //"INSERT INTO `borrowedbooks`(`title`) VALUES ('"+ititle+"')"
                try {
                    String qr = "INSERT INTO `borrowed_books`(`book_title`, `author`, `type`, `subject`,  `borrow_date`, `return_date`,`student_id`, `book_id`, `status`) VALUES ('" + ititle + "', '" + iauthor + "', '" + itype + "','" + isubject + "', '" + iborrowdate + "', '" + ireturndate + "' ,?,?,'" + istatus + "')";
                    pst = conn.prepareStatement(qr);
                    pst.setString(1, String.valueOf(textfieldstudentid.getText()));
                    pst.setString(2, String.valueOf(textfieldbookid.getText()));
                    pst.execute();

                    String sr = "UPDATE books set quantity = quantity - 1 WHERE book_id = ?";
                    pst = conn.prepareStatement(sr);
                    pst.setString(1, String.valueOf(textfieldbookid.getText()));
                    pst.execute();

                    textfieldbookid.setText("");
                    JOptionPane.showMessageDialog(null, "Borrowed Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                    refreshJtable();
                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "Unable to Borrow!", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Already Borrowed!", "Message", JOptionPane.INFORMATION_MESSAGE);
        }

        clear();

        student();
    }//GEN-LAST:event_btn_borrowActionPerformed

    private void btn_printMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_printMouseEntered

    }//GEN-LAST:event_btn_printMouseEntered

    private void btn_printMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_printMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_printMouseExited

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        //getData();
        if (getData()) {
            bHeight = Double.valueOf(title.size());
            //JOptionPane.showMessageDialog(rootPane, bHeight);

            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.setPrintable(new BillPrintable(), getPageFormat(pj));
            try {
                pj.print();
                textarea.setText("");
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, "Please Insert Thermal Printer!", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean getData() {
        if (textarea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Borrow a Book!", "Message", JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                String sql = "SELECT `book_title`'" + textarea.getText();

                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {

                    title.add(rs.getString("book_title"));

                }
            } catch (Exception e) {

            }
            return true;
        }
        return false;
    }

    private void clear() {
        result_field.setText("");
        textfieldbookid.setText("");
        textfieldauthor2.setText("");
        comboboxtype2.setText("");
        comboboxsubject2.setText("");
        textfieldquantity2.setText("");

    }

    public PageFormat getPageFormat(PrinterJob pj) {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double bodyHeight = bHeight;
        double headerHeight = 5.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    protected static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 170d;
    }

    private void drawString(Graphics g2d, String text, int x, int y) {
        int lineHeight = g2d.getFontMetrics().getHeight();
        for (String line : text.split("\n")) {
            g2d.drawString(line, x, y += lineHeight);
        }
    }

    public class BillPrintable implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException {

            int r = title.size();
            ImageIcon icon = new ImageIcon("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\logo2.png");
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
                try {
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;
                    int headerRectHeight2 = 10;
                    // int headerRectHeighta=40;

                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 6));
                    g2d.drawImage(icon.getImage(), 30, 20, 90, 70, rootPane);
                    y += yShift + 70;
                    g2d.drawString("----------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("    New Era National High School  ", 12, y);
                    y += yShift;
                    g2d.drawString("  Sampaloc V, 2nd Street, New Era,", 12, y);
                    y += yShift;
                    g2d.drawString("     City of Dasmariñas Cavite    ", 12, y);
                    y += yShift;
                    g2d.drawString(" https://nenhs.depeddasma.edu.ph/ ", 12, y);
                    y += yShift;
                    g2d.drawString("**********************************", 10, y);
                    y += yShift;
                    g2d.drawString("      PLEASE KEEP THIS SLIP      ", 10, y);
                    y += yShift;
                    g2d.drawString("**********************************", 10, y);
                    y += yShift;

                    
                    g2d.drawString("Borrow Date:", 12, y);
                    y += headerRectHeight2;
                    Date obj = new Date();
                    String date = obj.toString();
                    g2d.drawString(date, 12, y);
                    g2d.drawString("                                  ", 12, y);
                    y += headerRectHeight2;

                    
                    g2d.drawString("Due Date:", 12, y);
                    y += headerRectHeight2;
                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.DATE, 7);
                    Date obj1 = c.getTime();
                    String date1 = obj1.toString();;
                    g2d.drawString(date1, 12, y);
                    g2d.drawString("                                  ", 12, y);
                    y += headerRectHeight2;

                    /*g2d.drawString("           Thank you!            ",10,y);y+=yShift;
                        g2d.drawString("Librarian: Mrs. Maribelle Sinigayan",10,y);y+=yShift; */
                    g2d.drawString("Name: " + textfieldname.getText(), 12, y);
                    y += yShift;

                    g2d.drawString("              Title               ", 12, y);

                    g2d.setFont(g2d.getFont().deriveFont(6f));
                    drawString(g2d, textarea.getText(), 12, y);
                    y += yShift;

                } catch (Exception e) {
                }

                result = PAGE_EXISTS;
            }
            return result;

        }

        public PageFormat getPageFormat(PrinterJob pj) {

            PageFormat pf = pj.defaultPage();
            Paper paper = pf.getPaper();

            double bodyHeight = bHeight;
            double headerHeight = 5.0;
            double footerHeight = 5.0;
            double width = cm_to_pp(8);
            double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
            paper.setSize(width, height);
            paper.setImageableArea(0, 5, width, height - cm_to_pp(1));

            pf.setOrientation(PageFormat.PORTRAIT);
            pf.setPaper(paper);

            return pf;

        }
    }//GEN-LAST:event_btn_printActionPerformed

    private void btn_next2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_next2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_next2MouseEntered

    private void btn_next2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_next2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_next2MouseExited

    private void btn_next2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_next2ActionPerformed
        Connection con = (Connection) myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        if (verifScan2()) {
            try {
                ps = (PreparedStatement) con.prepareStatement("Select * from borrowed_books where book_title = ? and student_id  = ?");
                ps.setString(1, String.valueOf(result_field1.getText()));
                ps.setString(2, String.valueOf(textfieldstudentid1.getText()));
                rs = ps.executeQuery();

                if (returned() == false) {
                    if (rs.next()) {

                        String f = rs.getString("book_id");
                        textfieldbookid1.setText(f);
                        String l = rs.getString("author");
                        textfieldauthor3.setText(l);
                        String u = rs.getString("type");
                        comboboxtype3.setText(u);
                        String s = rs.getString("subject");
                        comboboxsubject3.setText(s);

                        //textfieldquantity2.setText("1");
                        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String d = rs.getString("return_date");
                        borroweddate1.setText(d);

                        String c = rs.getString("borrowed_id");
                        textfieldborrowedid1.setText(c);

                    } else if (invalidQR() == false) {
                        JOptionPane.showMessageDialog(null, "This book is not in the database!", "Message", JOptionPane.ERROR_MESSAGE);
                        result_field1.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Already Returned!", "Message", JOptionPane.INFORMATION_MESSAGE);
                        result_field1.setText("");
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }//GEN-LAST:event_btn_next2ActionPerformed

    private void btn_returnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_returnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_returnMouseEntered

    private void btn_returnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_returnMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_returnMouseExited

    private void btn_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_returnActionPerformed

        ititle = result_field1.getText();
        iauthor = textfieldauthor3.getText();
        itype = comboboxtype3.getText();
        isubject = comboboxsubject3.getText();
        //iquantity = textfieldquantity2.getText();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        iduedate = borroweddate1.getText();

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        ireturndate = sdf1.format(returneddate1.getDate());

        result_field.setText("");
        result_field1.setText("");
        textfieldauthor3.setText("");
        comboboxtype3.setText("");
        comboboxsubject3.setText("");
        borroweddate1.setText("");
        //textfieldquantity2.setText("");

        //"INSERT INTO `borrowedbooks`(`title`) VALUES ('"+ititle+"')"
        try {
            String qr = "INSERT INTO `returned_books`(`book_title`, `author`, `type`, `subject`, `due_date`, `return_date`,`student_id`, `book_id`, `borrowed_id`) VALUES ('" + ititle + "', '" + iauthor + "', '" + itype + "','" + isubject + "', '" + iduedate + "' , '" + ireturndate + "' ,?,?,?)";
            pst = conn.prepareStatement(qr);
            pst.setString(1, String.valueOf(textfieldstudentid1.getText()));
            pst.setString(2, String.valueOf(textfieldbookid1.getText()));
            pst.setString(3, String.valueOf(textfieldborrowedid1.getText()));
            pst.execute();

            String sr = "UPDATE books set quantity = quantity + 1 WHERE book_id = ?";
            pst = conn.prepareStatement(sr);
            pst.setString(1, String.valueOf(textfieldbookid1.getText()));
            pst.execute();

            String tr = "DELETE FROM `borrowed_books` WHERE borrowed_id = ?";
            pst = conn.prepareStatement(tr);
            pst.setString(1, String.valueOf(textfieldborrowedid1.getText()));
            pst.execute();

            textfieldbookid1.setText("");
            textfieldborrowedid1.setText("");
            JOptionPane.showMessageDialog(null, "Returned Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
            refreshJtable();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Unable to Return!", "Message", JOptionPane.ERROR_MESSAGE);
        }

        clear();
        //
        student();

    }//GEN-LAST:event_btn_returnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentHome().setVisible(true);
            }
        });
    }

    public void changeTable(JTable table, int column_index) {
        table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = table.getValueAt(row, 6).toString();
                String status1 = "LATE";
                //c.setForeground(Color.RED);
                if (status.equals(status1)) {
                    c.setForeground(Color.RED);
                } else {
                    c.setForeground(new Color(255, 204, 0));
                }
                return c;
            }
        });
    }

    private void initWebcam() {

        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel7.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));
        //jPanel11.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));
        executor.execute(this);

    }

    private void initWebcam2() {

        Dimension size = WebcamResolution.QVGA.getSize();
        webcam2 = Webcam.getWebcams().get(0);
        webcam2.setViewSize(size);

        panell = new WebcamPanel(webcam2);
        panell.setPreferredSize(size);
        panell.setFPSDisplayed(true);

        jPanel11.add(panell, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));
        executor2.execute(this);

    }

    @Override
    public void run() {

        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(StudentHome.class.getName()).log(Level.SEVERE, null, ex);
            }
            Result result = null;
            Result result2 = null;
            BufferedImage image = null;
            BufferedImage image2 = null;
            //borrow book
            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException ex) {
                Logger.getLogger(StudentHome.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (result != null) {
                result_field.setText(result.getText());

            }

            //return book
            if (webcam2.isOpen()) {
                if ((image2 = webcam2.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source2 = new BufferedImageLuminanceSource(image2);
            BinaryBitmap bitmap2 = new BinaryBitmap(new HybridBinarizer(source2));
            try {
                result2 = new MultiFormatReader().decode(bitmap2);
            } catch (NotFoundException ex) {
                Logger.getLogger(StudentHome.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (result2 != null) {
                result_field1.setText(result2.getText());

            }

        } while (true);

    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    class MyTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Color getForeground() {
            return super.getForeground();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aboutImg;
    private javax.swing.JLabel book;
    private javax.swing.JLabel borrow;
    private com.toedter.calendar.JDateChooser borroweddate;
    private javax.swing.JTextField borroweddate1;
    private button.MyButton btn_borrow;
    private button.MyButton btn_next;
    private button.MyButton btn_next2;
    private button.MyButton btn_print;
    private button.MyButton btn_return;
    private javax.swing.JLabel cl;
    private javax.swing.JLabel close;
    private javax.swing.JPanel closeFrame;
    private javax.swing.JTextField comboboxsubject2;
    private javax.swing.JTextField comboboxsubject3;
    private javax.swing.JTextField comboboxtype2;
    private javax.swing.JTextField comboboxtype3;
    private javax.swing.JLabel dashboard;
    private javax.swing.JLabel dateLab;
    private javax.swing.JLabel dateLab1;
    private javax.swing.JLabel hamburger;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelLoida;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel94;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel jTextField1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel lbl_back;
    private javax.swing.JLabel lbl_event;
    private javax.swing.JLabel lbl_fullname;
    private javax.swing.JLabel lbl_images;
    private javax.swing.JLabel lbl_next;
    private javax.swing.JLabel lblconfirmpass;
    private javax.swing.JLabel lblfirstname;
    private javax.swing.JLabel lbllastname;
    private javax.swing.JLabel lbllrn;
    private javax.swing.JLabel lblpassword;
    private javax.swing.JLabel lblusername;
    private javax.swing.JLabel logout;
    private javax.swing.JPanel lowerPanel;
    private javax.swing.JTabbedPane mainTab;
    private javax.swing.JLabel min;
    private javax.swing.JPanel minFrame;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel6;
    private javax.swing.JPanel panel7;
    private javax.swing.JPanel panelaccount;
    private javax.swing.JPanel panelborrowed;
    private javax.swing.JLabel qr;
    private javax.swing.JLabel qr1;
    private javax.swing.JTextField result_field;
    private javax.swing.JTextField result_field1;
    private com.toedter.calendar.JDateChooser returneddate;
    private com.toedter.calendar.JDateChooser returneddate1;
    private javax.swing.JPanel sideNav;
    private javax.swing.JLabel status;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab10;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JPanel tab6;
    private javax.swing.JPanel tab7;
    private javax.swing.JPanel tab8;
    private javax.swing.JPanel tab9;
    private javax.swing.JTable table_bookreport;
    private javax.swing.JTable table_borrowedreport;
    private javax.swing.JTable table_myborrowed;
    private javax.swing.JTextArea textarea;
    private javax.swing.JTextField textfieldauthor2;
    private javax.swing.JTextField textfieldauthor3;
    private javax.swing.JTextField textfieldbookid;
    private javax.swing.JTextField textfieldbookid1;
    private javax.swing.JTextField textfieldborrowedid1;
    private javax.swing.JTextField textfieldfirstname;
    private javax.swing.JTextField textfieldlastname;
    private javax.swing.JTextField textfieldlrn;
    private javax.swing.JTextField textfieldname;
    private javax.swing.JTextField textfieldquantity2;
    private javax.swing.JTextField textfieldstudentid;
    private javax.swing.JTextField textfieldstudentid1;
    private javax.swing.JTextField textfieldusername;
    private button.MyButton updateinfo_btn;
    private javax.swing.JPanel upperPanel;
    private javax.swing.JLabel user;
    private javax.swing.JTabbedPane userTab;
    // End of variables declaration//GEN-END:variables
}
