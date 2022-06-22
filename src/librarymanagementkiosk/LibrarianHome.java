/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementkiosk;

import com.sun.glass.events.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author 63915
 */
public class LibrarianHome extends javax.swing.JFrame {

    private String imagePth;

    /**
     * Creates new form LibrarianHome
     */
    Connection con;
    private Object model;

    public LibrarianHome() {
        initComponents();

        con = myConnection.getConnection();
        //table();
        ((DefaultEditor) copies.getEditor()).getTextField().setEditable(false);
        this.setLocationRelativeTo(null);
        populateJtable();
        totalBooks();
        changeTableLate(table_borrowedlate, 6);
        changeTableReturn(table_returnedreport, 6);
        changeTableLate(table_lateunreturnedreport, 6);

        Border emptyBorder = BorderFactory.createEmptyBorder();
        btn_home.setBorder(emptyBorder);
        btn_browse.setBorder(emptyBorder);
        updateinfo_btn.setBorder(emptyBorder);
        btn_generate.setBorder(emptyBorder);
        btn_print.setBorder(emptyBorder);
        btn_reset.setBorder(emptyBorder);
        btn_add.setBorder(emptyBorder);
        btn_edit.setBorder(emptyBorder);
        btn_delete.setBorder(emptyBorder);
        btn_reset2.setBorder(emptyBorder);
        btn_add2.setBorder(emptyBorder);
        btn_edit2.setBorder(emptyBorder);
        btn_delete2.setBorder(emptyBorder);
        btn_reset3.setBorder(emptyBorder);
        jButton1.setBorder(emptyBorder);
        jButton2.setBorder(emptyBorder);
        jButton3.setBorder(emptyBorder);
        jButton4.setBorder(emptyBorder);
        jButton5.setBorder(emptyBorder);
        jButton6.setBorder(emptyBorder);
        jButton7.setBorder(emptyBorder);
        jButton8.setBorder(emptyBorder);
        importbooks_btn.setBorder(emptyBorder);
        importexcelfile_btn.setBorder(emptyBorder);
        insertdata_btn.setBorder(emptyBorder);
        back_btn.setBorder(emptyBorder);
        printreport_btn.setBorder(emptyBorder);
        printreport2_btn.setBorder(emptyBorder);
        printreport3_btn.setBorder(emptyBorder);
        printreport4_btn.setBorder(emptyBorder);
        printreport5_btn.setBorder(emptyBorder);
        printmath_btn.setBorder(emptyBorder);
        printeng_btn.setBorder(emptyBorder);
        printsci_btn.setBorder(emptyBorder);
        printfil_btn.setBorder(emptyBorder);
        printmapeh_btn.setBorder(emptyBorder);
        printtle_btn.setBorder(emptyBorder);
        printesp_btn.setBorder(emptyBorder);
        printap_btn.setBorder(emptyBorder);
        exportexcel_btn.setBorder(emptyBorder);
        exportexcel2_btn.setBorder(emptyBorder);
        exportexcel3_btn.setBorder(emptyBorder);
        exportexcel4_btn.setBorder(emptyBorder);
        exportexcel5_btn.setBorder(emptyBorder);
        exportmath_btn.setBorder(emptyBorder);
        exporteng_btn.setBorder(emptyBorder);
        exportsci_btn.setBorder(emptyBorder);
        exportfil_btn.setBorder(emptyBorder);
        exportmapeh_btn.setBorder(emptyBorder);
        exporttle_btn.setBorder(emptyBorder);
        exportesp_btn.setBorder(emptyBorder);
        exportap_btn.setBorder(emptyBorder);

        btn_home.setFocusPainted(false);
        btn_browse.setFocusPainted(false);
        updateinfo_btn.setFocusPainted(false);
        btn_generate.setFocusPainted(false);
        btn_print.setFocusPainted(false);
        btn_reset.setFocusPainted(false);
        btn_add.setFocusPainted(false);
        btn_edit.setFocusPainted(false);
        btn_delete.setFocusPainted(false);
        btn_reset2.setFocusPainted(false);
        btn_add2.setFocusPainted(false);
        btn_edit2.setFocusPainted(false);
        btn_delete2.setFocusPainted(false);
        btn_reset3.setFocusPainted(false);
        jButton1.setFocusPainted(false);
        jButton2.setFocusPainted(false);
        jButton3.setFocusPainted(false);
        jButton4.setFocusPainted(false);
        jButton5.setFocusPainted(false);
        jButton6.setFocusPainted(false);
        jButton7.setFocusPainted(false);
        jButton8.setFocusPainted(false);
        importbooks_btn.setFocusPainted(false);
        importexcelfile_btn.setFocusPainted(false);
        insertdata_btn.setFocusPainted(false);
        back_btn.setFocusPainted(false);
        printreport_btn.setFocusPainted(false);
        printreport2_btn.setFocusPainted(false);
        printreport3_btn.setFocusPainted(false);
        printreport4_btn.setFocusPainted(false);
        printreport5_btn.setFocusPainted(false);
        printmath_btn.setFocusPainted(false);
        printeng_btn.setFocusPainted(false);
        printsci_btn.setFocusPainted(false);
        printfil_btn.setFocusPainted(false);
        printmapeh_btn.setFocusPainted(false);
        printtle_btn.setFocusPainted(false);
        printesp_btn.setFocusPainted(false);
        printap_btn.setFocusPainted(false);
        exportexcel_btn.setFocusPainted(false);
        exportexcel2_btn.setFocusPainted(false);
        exportexcel3_btn.setFocusPainted(false);
        exportexcel4_btn.setFocusPainted(false);
        exportexcel5_btn.setFocusPainted(false);
        exportmath_btn.setFocusPainted(false);
        exporteng_btn.setFocusPainted(false);
        exportsci_btn.setFocusPainted(false);
        exportfil_btn.setFocusPainted(false);
        exportmapeh_btn.setFocusPainted(false);
        exporttle_btn.setFocusPainted(false);
        exportesp_btn.setFocusPainted(false);
        exportap_btn.setFocusPainted(false);

        table_books.setShowGrid(true);
        table_books.setGridColor(Color.gray);
        table_books.setSelectionBackground(Color.BLACK);
        table_books.setSelectionForeground(Color.white);
        table_books.getTableHeader().setOpaque(false);
        table_books.setShowVerticalLines(false);
        table_books.setShowHorizontalLines(true);
        //table_books.getColumnModel().getColumn(0).setPreferredWidth(70);
        //table_books.getColumnModel().getColumn(1).setPreferredWidth(250);

        JTableHeader th = table_books.getTableHeader();
        th.setForeground(Color.white);
        th.setBackground(new Color(64, 134, 176));
        th.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_bookreport.setShowGrid(true);
        table_bookreport.setGridColor(Color.gray);
        table_bookreport.setSelectionBackground(Color.BLACK);
        table_bookreport.setSelectionForeground(Color.white);

        table_bookreport.getTableHeader().setOpaque(false);
        table_bookreport.setShowVerticalLines(false);
        table_bookreport.setShowHorizontalLines(true);

        JTableHeader th2 = table_bookreport.getTableHeader();
        th2.setForeground(Color.white);
        th2.setBackground(new Color(64, 134, 176));
        th2.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_borrowedreport.setShowGrid(true);
        table_borrowedreport.setGridColor(Color.gray);
        table_borrowedreport.setSelectionBackground(Color.BLACK);
        table_borrowedreport.setSelectionForeground(Color.white);

        table_borrowedreport.getTableHeader().setOpaque(false);
        table_borrowedreport.setShowVerticalLines(false);
        table_borrowedreport.setShowHorizontalLines(true);

        JTableHeader th4 = table_borrowedreport.getTableHeader();
        th4.setForeground(Color.white);
        th4.setBackground(new Color(64, 134, 176));
        th4.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_returnedreport.setShowGrid(true);
        table_returnedreport.setGridColor(Color.gray);
        table_returnedreport.setSelectionBackground(Color.BLACK);
        table_returnedreport.setSelectionForeground(Color.white);

        table_returnedreport.getTableHeader().setOpaque(false);
        table_returnedreport.setShowVerticalLines(false);
        table_returnedreport.setShowHorizontalLines(true);

        JTableHeader th5 = table_returnedreport.getTableHeader();
        th5.setForeground(Color.white);
        th5.setBackground(new Color(64, 134, 176));
        th5.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_lateunreturnedreport.setShowGrid(true);
        table_lateunreturnedreport.setGridColor(Color.gray);
        table_lateunreturnedreport.setSelectionBackground(Color.BLACK);
        table_lateunreturnedreport.setSelectionForeground(Color.white);

        table_lateunreturnedreport.getTableHeader().setOpaque(false);
        table_lateunreturnedreport.setShowVerticalLines(false);
        table_lateunreturnedreport.setShowHorizontalLines(true);

        JTableHeader th20 = table_lateunreturnedreport.getTableHeader();
        th20.setForeground(Color.white);
        th20.setBackground(new Color(64, 134, 176));
        th20.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_studentreport.setShowGrid(true);
        table_studentreport.setGridColor(Color.gray);
        table_studentreport.setSelectionBackground(Color.BLACK);
        table_studentreport.setSelectionForeground(Color.white);

        table_studentreport.getTableHeader().setOpaque(false);
        table_studentreport.setShowVerticalLines(false);
        table_studentreport.setShowHorizontalLines(true);

        JTableHeader th6 = table_studentreport.getTableHeader();
        th6.setForeground(Color.white);
        th6.setBackground(new Color(64, 134, 176));
        th6.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_math.setShowGrid(true);
        table_math.setGridColor(Color.gray);
        table_math.setSelectionBackground(Color.BLACK);
        table_math.setSelectionForeground(Color.white);

        table_math.getTableHeader().setOpaque(false);
        table_math.setShowVerticalLines(false);
        table_math.setShowHorizontalLines(true);

        JTableHeader th7 = table_math.getTableHeader();
        th7.setForeground(Color.white);
        th7.setBackground(new Color(64, 134, 176));
        th7.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_eng.setShowGrid(true);
        table_eng.setGridColor(Color.gray);
        table_eng.setSelectionBackground(Color.BLACK);
        table_eng.setSelectionForeground(Color.white);

        table_eng.getTableHeader().setOpaque(false);
        table_eng.setShowVerticalLines(false);
        table_eng.setShowHorizontalLines(true);

        JTableHeader th8 = table_eng.getTableHeader();
        th8.setForeground(Color.white);
        th8.setBackground(new Color(64, 134, 176));
        th8.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_sci.setShowGrid(true);
        table_sci.setGridColor(Color.gray);
        table_sci.setSelectionBackground(Color.BLACK);
        table_sci.setSelectionForeground(Color.white);

        table_sci.getTableHeader().setOpaque(false);
        table_sci.setShowVerticalLines(false);
        table_sci.setShowHorizontalLines(true);

        JTableHeader th9 = table_sci.getTableHeader();
        th9.setForeground(Color.white);
        th9.setBackground(new Color(64, 134, 176));
        th9.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_fil.setShowGrid(true);
        table_fil.setGridColor(Color.gray);
        table_fil.setSelectionBackground(Color.BLACK);
        table_fil.setSelectionForeground(Color.white);

        table_fil.getTableHeader().setOpaque(false);
        table_fil.setShowVerticalLines(false);
        table_fil.setShowHorizontalLines(true);

        JTableHeader th10 = table_fil.getTableHeader();
        th10.setForeground(Color.white);
        th10.setBackground(new Color(64, 134, 176));
        th10.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_mapeh.setShowGrid(true);
        table_mapeh.setGridColor(Color.gray);
        table_mapeh.setSelectionBackground(Color.BLACK);
        table_mapeh.setSelectionForeground(Color.white);

        table_mapeh.getTableHeader().setOpaque(false);
        table_mapeh.setShowVerticalLines(false);
        table_mapeh.setShowHorizontalLines(true);

        JTableHeader th11 = table_mapeh.getTableHeader();
        th11.setForeground(Color.white);
        th11.setBackground(new Color(64, 134, 176));
        th11.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_tle.setShowGrid(true);
        table_tle.setGridColor(Color.gray);
        table_tle.setSelectionBackground(Color.BLACK);
        table_tle.setSelectionForeground(Color.white);

        table_tle.getTableHeader().setOpaque(false);
        table_tle.setShowVerticalLines(false);
        table_tle.setShowHorizontalLines(true);

        JTableHeader th12 = table_tle.getTableHeader();
        th12.setForeground(Color.white);
        th12.setBackground(new Color(64, 134, 176));
        th12.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_esp.setShowGrid(true);
        table_esp.setGridColor(Color.gray);
        table_esp.setSelectionBackground(Color.BLACK);
        table_esp.setSelectionForeground(Color.white);

        table_esp.getTableHeader().setOpaque(false);
        table_esp.setShowVerticalLines(false);
        table_esp.setShowHorizontalLines(true);

        JTableHeader th13 = table_esp.getTableHeader();
        th13.setForeground(Color.white);
        th13.setBackground(new Color(64, 134, 176));
        th13.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_ap.setShowGrid(true);
        table_ap.setGridColor(Color.gray);
        table_ap.setSelectionBackground(Color.BLACK);
        table_ap.setSelectionForeground(Color.white);

        table_ap.getTableHeader().setOpaque(false);
        table_ap.setShowVerticalLines(false);
        table_ap.setShowHorizontalLines(true);

        JTableHeader th14 = table_ap.getTableHeader();
        th14.setForeground(Color.white);
        th14.setBackground(new Color(64, 134, 176));
        th14.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_borrowed.setShowGrid(true);
        table_borrowed.setGridColor(Color.gray);
        table_borrowed.setSelectionBackground(Color.BLACK);
        table_borrowed.setSelectionForeground(Color.white);

        table_borrowed.getTableHeader().setOpaque(false);
        table_borrowed.setShowVerticalLines(false);
        table_borrowed.setShowHorizontalLines(true);

        JTableHeader th15 = table_borrowed.getTableHeader();
        th15.setForeground(Color.white);
        th15.setBackground(new Color(64, 134, 176));
        th15.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_borrowedlate.setShowGrid(true);
        table_borrowedlate.setGridColor(Color.gray);
        table_borrowedlate.setSelectionBackground(Color.BLACK);
        table_borrowedlate.setSelectionForeground(Color.white);

        table_borrowedlate.getTableHeader().setOpaque(false);
        table_borrowedlate.setShowVerticalLines(false);
        table_borrowedlate.setShowHorizontalLines(true);

        //jScrollPane22.setBorder(null);
        //jScrollPane22.getViewport().setBackground(Color.WHITE);
        //table_borrowedlate.setBackground(Color.WHITE);
        //table_borrowedlate.setIntercellSpacing(new Dimension(0,0));
        JTableHeader th19 = table_borrowedlate.getTableHeader();
        th19.setForeground(Color.white);
        th19.setBackground(new Color(64, 134, 176));
        th19.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_students.setShowGrid(true);
        table_students.setGridColor(Color.gray);
        table_students.setSelectionBackground(Color.BLACK);
        table_students.setSelectionForeground(Color.white);

        table_students.getTableHeader().setOpaque(false);
        table_students.setShowVerticalLines(false);
        table_students.setShowHorizontalLines(true);

        JTableHeader th16 = table_students.getTableHeader();
        th16.setForeground(Color.white);
        th16.setBackground(new Color(64, 134, 176));
        th16.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_home.setShowGrid(true);
        table_home.setGridColor(Color.gray);
        table_home.setSelectionBackground(Color.BLACK);
        table_home.setSelectionForeground(Color.white);

        table_home.getTableHeader().setOpaque(false);
        table_home.setShowVerticalLines(false);
        table_home.setShowHorizontalLines(true);

        JTableHeader th17 = table_home.getTableHeader();
        th17.setForeground(Color.white);
        th17.setBackground(new Color(64, 134, 176));
        th17.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table_importexcel.setShowGrid(true);
        table_importexcel.setGridColor(Color.gray);
        table_importexcel.setSelectionBackground(Color.BLACK);
        table_importexcel.setSelectionForeground(Color.white);

        table_importexcel.getTableHeader().setOpaque(false);
        table_importexcel.setShowVerticalLines(false);
        table_importexcel.setShowHorizontalLines(true);
        table_importexcel.setRowHeight(30);

        JTableHeader th18 = table_importexcel.getTableHeader();
        th18.setForeground(Color.white);
        th18.setBackground(new Color(64, 134, 176));
        th18.setFont(new Font("Segoe UI", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEFT);
        table_books.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_books.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        table_borrowed.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_students.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_students.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table_students.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table_bookreport.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        // table_bookreport.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table_borrowedreport.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_returnedreport.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_studentreport.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        //table_studentreport.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        table_math.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_math.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table_eng.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_eng.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table_sci.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_sci.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table_fil.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_fil.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table_mapeh.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_mapeh.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table_tle.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_tle.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table_esp.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_esp.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table_ap.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_ap.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        table_home.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_borrowedlate.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_lateunreturnedreport.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        jLabel2.setForeground(Color.BLACK);
        jLabel3.setForeground(Color.BLACK);
        jLabel4.setForeground(Color.BLACK);
        jLabel5.setForeground(Color.BLACK);
        jLabel6.setForeground(Color.BLACK);
        jLabel7.setForeground(Color.BLACK);
        jLabel10.setForeground(Color.BLACK);
        jLabel11.setForeground(Color.BLACK);
        librarian();
        totalBooks();
        showDate();
        //refreshJtable();

        //Notifications n = new Notifications();
        //n.setLocation(1002, 105);
        //n.setVisible(true);
        //LibrarianHome ho = new LibrarianHome();
        //String no = ho.lbl_notif.setText();
        //n.lbl_total.setText(no);
        // showTime();
    }

    Connection conn;
    PreparedStatement pst;

    void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("MM-dd-yyyy");
        dateLab.setText(s.format(d));
    }

    void totalBooks() {
        Connection con = (Connection) myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = (PreparedStatement) con.prepareStatement("SELECT SUM(quantity) FROM books");
            rs = ps.executeQuery();
            if (rs.next()) {
                String total = rs.getString("sum(quantity)");
                totalbooks.setText(total);
            }

            ps = (PreparedStatement) con.prepareStatement("SELECT COUNT(book_title) FROM borrowed_books");
            rs = ps.executeQuery();
            if (rs.next()) {
                String total = rs.getString("count(book_title)");
                totalborrow.setText(total);
            }

            ps = (PreparedStatement) con.prepareStatement("SELECT COUNT(book_title) FROM  returned_books");
            rs = ps.executeQuery();
            if (rs.next()) {
                String total = rs.getString("count(book_title)");
                totallate.setText(total);
            }

            ps = (PreparedStatement) con.prepareStatement("SELECT SUM(quantity) FROM books WHERE subject = 'math'");
            rs = ps.executeQuery();
            if (rs.next()) {
                String total = rs.getString("sum(quantity)");
                totalmath.setText(total);
            }

            ps = (PreparedStatement) con.prepareStatement("SELECT SUM(quantity) FROM books WHERE subject = 'english'");
            rs = ps.executeQuery();
            if (rs.next()) {
                String total = rs.getString("sum(quantity)");
                totaleng.setText(total);
            }

            ps = (PreparedStatement) con.prepareStatement("SELECT SUM(quantity) FROM books WHERE subject = 'science'");
            rs = ps.executeQuery();
            if (rs.next()) {
                String total = rs.getString("sum(quantity)");
                totalsci.setText(total);
            }

            ps = (PreparedStatement) con.prepareStatement("SELECT SUM(quantity) FROM books WHERE subject = 'filipino'");
            rs = ps.executeQuery();
            if (rs.next()) {
                String total = rs.getString("sum(quantity)");
                totalfil.setText(total);
            }

            ps = (PreparedStatement) con.prepareStatement("SELECT SUM(quantity) FROM books WHERE subject = 'mapeh'");
            rs = ps.executeQuery();
            if (rs.next()) {
                String total = rs.getString("sum(quantity)");
                totalmapeh.setText(total);
            }

            ps = (PreparedStatement) con.prepareStatement("SELECT SUM(quantity) FROM books WHERE subject = 'tle'");
            rs = ps.executeQuery();
            if (rs.next()) {
                String total = rs.getString("sum(quantity)");
                totaltle.setText(total);
            }

            ps = (PreparedStatement) con.prepareStatement("SELECT SUM(quantity) FROM books WHERE subject = 'esp'");
            rs = ps.executeQuery();
            if (rs.next()) {
                String total = rs.getString("sum(quantity)");
                totalesp.setText(total);
            }

            ps = (PreparedStatement) con.prepareStatement("SELECT SUM(quantity) FROM books WHERE subject = 'ap'");
            rs = ps.executeQuery();
            if (rs.next()) {
                String total = rs.getString("sum(quantity)");
                totalap.setText(total);
            }

            ps = (PreparedStatement) con.prepareStatement("SELECT COUNT(ID) FROM registration");
            rs = ps.executeQuery();
            if (rs.next()) {
                String total = rs.getString("count(ID)");
                totalstudent.setText(total);

            }

            ps = (PreparedStatement) con.prepareStatement("SELECT COUNT(borrowed_id) FROM borrowed_books where (CASE WHEN datediff(return_date,CURDATE()) < 0 then 'LATE' END) = 'LATE'");
            rs = ps.executeQuery();
            if (rs.next()) {
                String total = rs.getString("count(borrowed_id)");
                lbl_notif.setText(total);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
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
        jLabelLoida = new javax.swing.JLabel();
        minFrame = new javax.swing.JPanel();
        min = new javax.swing.JLabel();
        closeFrame = new javax.swing.JPanel();
        cl = new javax.swing.JLabel();
        notification = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jPanel95 = new RoundedPanel(300, new Color(204,0,0));
        lbl_notif = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
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
        tab5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        student = new javax.swing.JLabel();
        tab6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        qr = new javax.swing.JLabel();
        tab7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        receipt = new javax.swing.JLabel();
        tab8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbl_fullname = new javax.swing.JLabel();
        mainTab = new javax.swing.JTabbedPane();
        panel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        totalbooks = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        view1 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        totalborrow = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        view2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        totallate = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        view3 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        totalstudent = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        view5 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        totalmath = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        totalmapeh = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel48 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        totaleng = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel50 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        totaltle = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel54 = new javax.swing.JPanel();
        jPanel55 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        totalsci = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel56 = new javax.swing.JPanel();
        jPanel57 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        totalfil = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel58 = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        totalesp = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel60 = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        totalap = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        btn_home = new button.MyButton();
        panel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
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
        updateinfo_btn = new button.MyButton();
        panel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        textfieldsearch = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        importbooks_btn = new javax.swing.JButton();
        jPanel91 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        comboboxtype = new javax.swing.JComboBox<>();
        comboboxsubject = new javax.swing.JComboBox<>();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        textfieldquantity = new javax.swing.JTextField();
        jLabel124 = new javax.swing.JLabel();
        textfieldauthor = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        textfieldtitle = new javax.swing.JTextArea();
        textfieldbookid = new javax.swing.JLabel();
        btn_add = new button.MyButton();
        btn_edit = new button.MyButton();
        btn_delete = new button.MyButton();
        btn_reset2 = new button.MyButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_books = new javax.swing.JTable();
        panel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        textfieldsearch2 = new javax.swing.JTextField();
        jLabel169 = new javax.swing.JLabel();
        jPanel92 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        textfieldname = new javax.swing.JTextField();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        textfieldauthor2 = new javax.swing.JTextField();
        textfieldborrowdate = new javax.swing.JTextField();
        textfieldduedate = new javax.swing.JTextField();
        textfieldlrn = new javax.swing.JTextField();
        jLabel165 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        textfieldtitle2 = new javax.swing.JTextArea();
        jScrollPane18 = new javax.swing.JScrollPane();
        table_borrowed = new javax.swing.JTable();
        jPanel94 = new javax.swing.JPanel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        panel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        textfieldsearch3 = new javax.swing.JTextField();
        jLabel126 = new javax.swing.JLabel();
        jPanel93 = new javax.swing.JPanel();
        jLabel133 = new javax.swing.JLabel();
        textfieldfirstname2 = new javax.swing.JTextField();
        jLabel134 = new javax.swing.JLabel();
        textfieldlastname2 = new javax.swing.JTextField();
        jLabel137 = new javax.swing.JLabel();
        textfieldusername2 = new javax.swing.JTextField();
        jLabel138 = new javax.swing.JLabel();
        textfieldtotalborrow = new javax.swing.JTextField();
        jLabel164 = new javax.swing.JLabel();
        textfieldlrn2 = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        table_students = new javax.swing.JTable();
        panel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        qrcode = new javax.swing.JLabel();
        Titleofbook1 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        Titleofbook = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        copies = new javax.swing.JSpinner();
        jSeparator3 = new javax.swing.JSeparator();
        btn_generate = new button.MyButton();
        btn_print = new button.MyButton();
        btn_reset = new button.MyButton();
        panel7 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        tab9 = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        tab10 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        tab11 = new javax.swing.JPanel();
        label3 = new javax.swing.JLabel();
        tab12 = new javax.swing.JPanel();
        label4 = new javax.swing.JLabel();
        tab13 = new javax.swing.JPanel();
        label5 = new javax.swing.JLabel();
        reportsTab = new javax.swing.JTabbedPane();
        panelbooks = new javax.swing.JPanel();
        jPanel96 = new javax.swing.JPanel();
        jPanel97 = new javax.swing.JPanel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        textfieldsearch4 = new javax.swing.JTextField();
        jLabel170 = new javax.swing.JLabel();
        exportexcel_btn = new javax.swing.JButton();
        printreport_btn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_bookreport = new javax.swing.JTable();
        panelborrowed = new javax.swing.JPanel();
        jPanel98 = new javax.swing.JPanel();
        jPanel99 = new javax.swing.JPanel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        textfieldsearch5 = new javax.swing.JTextField();
        jLabel171 = new javax.swing.JLabel();
        exportexcel2_btn = new javax.swing.JButton();
        printreport2_btn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_borrowedreport = new javax.swing.JTable();
        panelreturned = new javax.swing.JPanel();
        jPanel100 = new javax.swing.JPanel();
        jPanel101 = new javax.swing.JPanel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        textfieldsearch6 = new javax.swing.JTextField();
        jLabel172 = new javax.swing.JLabel();
        exportexcel3_btn = new javax.swing.JButton();
        printreport3_btn = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        table_returnedreport = new javax.swing.JTable();
        panelstudents = new javax.swing.JPanel();
        jPanel102 = new javax.swing.JPanel();
        jPanel103 = new javax.swing.JPanel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        textfieldsearch7 = new javax.swing.JTextField();
        jLabel173 = new javax.swing.JLabel();
        exportexcel4_btn = new javax.swing.JButton();
        printreport4_btn = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        table_studentreport = new javax.swing.JTable();
        panellateunreturned = new javax.swing.JPanel();
        jPanel108 = new javax.swing.JPanel();
        jPanel109 = new javax.swing.JPanel();
        jPanel110 = new javax.swing.JPanel();
        jLabel149 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        textfieldsearch9 = new javax.swing.JTextField();
        jLabel183 = new javax.swing.JLabel();
        exportexcel5_btn = new javax.swing.JButton();
        printreport5_btn = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        table_lateunreturnedreport = new javax.swing.JTable();
        panelmath = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel67 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jPanel68 = new javax.swing.JPanel();
        jPanel69 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        textfieldsearchmath = new javax.swing.JTextField();
        jLabel174 = new javax.swing.JLabel();
        exportmath_btn = new javax.swing.JButton();
        printmath_btn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_math = new javax.swing.JTable();
        paneleng = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel70 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jPanel71 = new javax.swing.JPanel();
        jPanel72 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        textfieldsearcheng = new javax.swing.JTextField();
        jLabel175 = new javax.swing.JLabel();
        printeng_btn = new javax.swing.JButton();
        exporteng_btn = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        table_eng = new javax.swing.JTable();
        panelsci = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel73 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jPanel74 = new javax.swing.JPanel();
        jPanel75 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        textfieldsearchsci = new javax.swing.JTextField();
        jLabel176 = new javax.swing.JLabel();
        printsci_btn = new javax.swing.JButton();
        exportsci_btn = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        table_sci = new javax.swing.JTable();
        panelfil = new javax.swing.JPanel();
        jPanel62 = new javax.swing.JPanel();
        jPanel76 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jPanel77 = new javax.swing.JPanel();
        jPanel78 = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        textfieldsearchfil = new javax.swing.JTextField();
        jLabel177 = new javax.swing.JLabel();
        exportfil_btn = new javax.swing.JButton();
        printfil_btn = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        table_fil = new javax.swing.JTable();
        panelmapeh = new javax.swing.JPanel();
        jPanel63 = new javax.swing.JPanel();
        jPanel79 = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jPanel80 = new javax.swing.JPanel();
        jPanel81 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        textfieldsearchmapeh = new javax.swing.JTextField();
        jLabel178 = new javax.swing.JLabel();
        exportmapeh_btn = new javax.swing.JButton();
        printmapeh_btn = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        table_mapeh = new javax.swing.JTable();
        paneltle = new javax.swing.JPanel();
        jPanel64 = new javax.swing.JPanel();
        jPanel82 = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jPanel83 = new javax.swing.JPanel();
        jPanel84 = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        textfieldsearchtle = new javax.swing.JTextField();
        jLabel179 = new javax.swing.JLabel();
        exporttle_btn = new javax.swing.JButton();
        printtle_btn = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        table_tle = new javax.swing.JTable();
        panelesp = new javax.swing.JPanel();
        jPanel65 = new javax.swing.JPanel();
        jPanel85 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jPanel86 = new javax.swing.JPanel();
        jPanel87 = new javax.swing.JPanel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        textfieldsearchesp = new javax.swing.JTextField();
        jLabel180 = new javax.swing.JLabel();
        exportesp_btn = new javax.swing.JButton();
        printesp_btn = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        table_esp = new javax.swing.JTable();
        panelap = new javax.swing.JPanel();
        jPanel66 = new javax.swing.JPanel();
        jPanel88 = new javax.swing.JPanel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jPanel89 = new javax.swing.JPanel();
        jPanel90 = new javax.swing.JPanel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        textfieldsearchap = new javax.swing.JTextField();
        jLabel181 = new javax.swing.JLabel();
        exportap_btn = new javax.swing.JButton();
        printap_btn = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        table_ap = new javax.swing.JTable();
        panelhome = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jPanel104 = new javax.swing.JPanel();
        jLabel131 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jPanel105 = new javax.swing.JPanel();
        jPanel106 = new javax.swing.JPanel();
        jLabel167 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        lbl_image = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        textfieldeventname = new javax.swing.JTextArea();
        jScrollPane19 = new javax.swing.JScrollPane();
        table_home = new javax.swing.JTable();
        textfieldhomeid = new javax.swing.JLabel();
        btn_browse = new button.MyButton();
        btn_add2 = new button.MyButton();
        btn_edit2 = new button.MyButton();
        btn_delete2 = new button.MyButton();
        btn_reset3 = new button.MyButton();
        panel_importexcel = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        importexcelfile_btn = new javax.swing.JButton();
        insertdata_btn = new javax.swing.JButton();
        back_btn = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        table_importexcel = new javax.swing.JTable();
        panel_notification = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        textfieldsearch8 = new javax.swing.JTextField();
        jLabel182 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        table_borrowedlate = new javax.swing.JTable();
        jPanel107 = new javax.swing.JPanel();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
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

        upperPanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 85, 1340, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/logo2.png"))); // NOI18N
        upperPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, -1));

        jLabelLoida.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabelLoida.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLoida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/era.png"))); // NOI18N
        upperPanel.add(jLabelLoida, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

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

        notification.setBackground(new java.awt.Color(64, 134, 176));
        notification.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notificationMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                notificationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                notificationMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                notificationMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                notificationMouseReleased(evt);
            }
        });
        notification.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        upperPanel.add(notification, new org.netbeans.lib.awtextra.AbsoluteConstraints(1261, 43, -1, -1));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("Library Management Kiosk ");
        upperPanel.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        upperPanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-settings-30 (1).png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        upperPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 50, 30, 30));

        jPanel95.setBackground(new java.awt.Color(64, 134, 176));
        jPanel95.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_notif.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lbl_notif.setForeground(new java.awt.Color(255, 255, 255));
        lbl_notif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_notif.setText("0");
        lbl_notif.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel95.add(lbl_notif, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 17, 15));

        upperPanel.add(jPanel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 60, -1, 17));

        jLabel87.setFont(new java.awt.Font("Rockwell Condensed", 0, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 0, 0));
        jLabel87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-notification-30.png"))); // NOI18N
        jLabel87.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel87MouseClicked(evt);
            }
        });
        upperPanel.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 45, 40, 40));

        jPanel1.add(upperPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 1340, 99));

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
        jLabel2.setText("DASHBOARD");

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
                .addContainerGap(138, Short.MAX_VALUE))
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

        tab5.setBackground(new java.awt.Color(149, 189, 219));
        tab5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab5MouseReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Agency FB", 0, 22)); // NOI18N
        jLabel6.setText("STUDENTS");

        student.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/student.png"))); // NOI18N

        javax.swing.GroupLayout tab5Layout = new javax.swing.GroupLayout(tab5);
        tab5.setLayout(tab5Layout);
        tab5Layout.setHorizontalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(student, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        tab5Layout.setVerticalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(student, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        sideNav.add(tab5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 260, 50));

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
        jLabel7.setText("QR CODE GENERATOR");

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
                .addContainerGap(82, Short.MAX_VALUE))
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

        sideNav.add(tab6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 260, 50));

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
        jLabel10.setText("REPORTS");

        receipt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/report.png"))); // NOI18N

        javax.swing.GroupLayout tab7Layout = new javax.swing.GroupLayout(tab7);
        tab7.setLayout(tab7Layout);
        tab7Layout.setHorizontalGroup(
            tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab7Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(receipt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(157, Short.MAX_VALUE))
        );
        tab7Layout.setVerticalGroup(
            tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(receipt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tab7Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        sideNav.add(tab7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 260, 50));

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
                    .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        sideNav.add(tab8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 260, 50));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/lib2.png"))); // NOI18N
        sideNav.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

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
        jPanel2.setMinimumSize(new java.awt.Dimension(1075, 561));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(8, 14, 112));

        jLabel8.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Dashboard");

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
                .addContainerGap(929, Short.MAX_VALUE))
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

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel13.setBackground(new java.awt.Color(7, 74, 114));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book2.png"))); // NOI18N

        totalbooks.setFont(new java.awt.Font("Rockwell Condensed", 0, 50)); // NOI18N
        totalbooks.setForeground(new java.awt.Color(255, 255, 255));
        totalbooks.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalbooks.setText("0");

        jLabel14.setFont(new java.awt.Font("Rockwell Condensed", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("BOOKS");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 64, Short.MAX_VALUE)
                        .addComponent(jLabel14))
                    .addComponent(totalbooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(totalbooks, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        view1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        view1.setText("View Details");
        view1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                view1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                view1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                view1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(view1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(view1)
                .addContainerGap())
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 69, 230, -1));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel16.setBackground(new java.awt.Color(12, 105, 169));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/borrowbook2.png"))); // NOI18N

        totalborrow.setFont(new java.awt.Font("Rockwell Condensed", 0, 50)); // NOI18N
        totalborrow.setForeground(new java.awt.Color(255, 255, 255));
        totalborrow.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalborrow.setText("0");

        jLabel19.setFont(new java.awt.Font("Rockwell Condensed", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("BORROWED ");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalborrow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(totalborrow, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        view2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        view2.setText("View Details");
        view2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                view2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                view2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                view2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(view2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(view2)
                .addContainerGap())
        );

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 69, 230, -1));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel17.setBackground(new java.awt.Color(15, 128, 197));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/returnbook2.png"))); // NOI18N

        totallate.setFont(new java.awt.Font("Rockwell Condensed", 0, 50)); // NOI18N
        totallate.setForeground(new java.awt.Color(255, 255, 255));
        totallate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totallate.setText("0");

        jLabel22.setFont(new java.awt.Font("Rockwell Condensed", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("RETURNED ");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totallate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(totallate, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        view3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        view3.setText("View Details");
        view3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                view3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                view3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                view3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(view3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(view3)
                .addContainerGap())
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 69, 230, 156));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel38.setBackground(new java.awt.Color(3, 134, 214));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/student2.png"))); // NOI18N

        totalstudent.setFont(new java.awt.Font("Rockwell Condensed", 0, 50)); // NOI18N
        totalstudent.setForeground(new java.awt.Color(255, 255, 255));
        totalstudent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalstudent.setText("0");

        jLabel49.setFont(new java.awt.Font("Rockwell Condensed", 0, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("STUDENTS");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(0, 48, Short.MAX_VALUE)
                        .addComponent(jLabel49))
                    .addComponent(totalstudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(totalstudent, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel49)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        view5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        view5.setText("View Details");
        view5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                view5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                view5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                view5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(view5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(view5)
                .addContainerGap())
        );

        jPanel2.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 69, 230, 156));

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0)));

        jPanel41.setBackground(new java.awt.Color(0, 153, 0));

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/math.png"))); // NOI18N

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel55.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel55.setText("MATHEMATICS");

        totalmath.setFont(new java.awt.Font("Arial Narrow", 0, 30)); // NOI18N
        totalmath.setText("0");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/arrow.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalmath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalmath, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 243, 230, -1));

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 118, 61)));

        jPanel45.setBackground(new java.awt.Color(223, 240, 216));

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/mapeh1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel60.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel60.setText("MAPEH");

        totalmapeh.setFont(new java.awt.Font("Arial Narrow", 0, 30)); // NOI18N
        totalmapeh.setText("0");

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/arrow.png"))); // NOI18N
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addComponent(totalmapeh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalmapeh, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 335, 230, -1));

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));

        jPanel49.setBackground(new java.awt.Color(0, 153, 255));

        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/english.png"))); // NOI18N

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel65.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel65.setText("ENGLISH");

        totaleng.setFont(new java.awt.Font("Arial Narrow", 0, 30)); // NOI18N
        totaleng.setText("0");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/arrow.png"))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addComponent(totaleng, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totaleng, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 243, 230, -1));

        jPanel50.setBackground(new java.awt.Color(255, 255, 255));
        jPanel50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(49, 112, 143)));

        jPanel51.setBackground(new java.awt.Color(217, 237, 247));

        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/tle1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel68)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel68)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel69.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel69.setText("TLE");

        totaltle.setFont(new java.awt.Font("Arial Narrow", 0, 30)); // NOI18N
        totaltle.setText("0");

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/arrow.png"))); // NOI18N
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(totaltle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totaltle, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 335, 230, -1));

        jPanel54.setBackground(new java.awt.Color(255, 255, 255));
        jPanel54.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));

        jPanel55.setBackground(new java.awt.Color(255, 153, 0));

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/science.png"))); // NOI18N

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel73)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel73)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel74.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel74.setText("SCIENCE");

        totalsci.setFont(new java.awt.Font("Arial Narrow", 0, 30)); // NOI18N
        totalsci.setText("0");

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/arrow.png"))); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addComponent(totalsci, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalsci, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 243, 230, -1));

        jPanel56.setBackground(new java.awt.Color(255, 255, 255));
        jPanel56.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));

        jPanel57.setBackground(new java.awt.Color(255, 0, 0));

        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/filipino.png"))); // NOI18N

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel78.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel78.setText("FILIPINO");

        totalfil.setFont(new java.awt.Font("Arial Narrow", 0, 30)); // NOI18N
        totalfil.setText("0");

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/arrow.png"))); // NOI18N
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(totalfil, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel78)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalfil, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 243, 230, -1));

        jPanel58.setBackground(new java.awt.Color(255, 255, 255));
        jPanel58.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 155, 14)));

        jPanel59.setBackground(new java.awt.Color(253, 243, 225));

        jLabel81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/esp1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel81)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel81)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel82.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel82.setText("ESP");

        totalesp.setFont(new java.awt.Font("Arial Narrow", 0, 30)); // NOI18N
        totalesp.setText("0");

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/arrow.png"))); // NOI18N
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addComponent(totalesp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalesp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 335, 230, -1));

        jPanel60.setBackground(new java.awt.Color(255, 255, 255));
        jPanel60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 68, 66)));

        jPanel61.setBackground(new java.awt.Color(242, 222, 222));

        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/ap1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel85)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel85)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel86.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel86.setText("AP");

        totalap.setFont(new java.awt.Font("Arial Narrow", 0, 30)); // NOI18N
        totalap.setText("0");

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/arrow.png"))); // NOI18N
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addComponent(jLabel86)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addComponent(totalap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel86)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalap, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 335, 230, -1));

        btn_home.setForeground(new java.awt.Color(255, 255, 255));
        btn_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-edit-image-25.png"))); // NOI18N
        btn_home.setText("UPDATE HOME");
        btn_home.setBorderColor(new java.awt.Color(64, 134, 176));
        btn_home.setColor(new java.awt.Color(64, 134, 176));
        btn_home.setColorClick(new java.awt.Color(102, 153, 255));
        btn_home.setColorOver(new java.awt.Color(64, 134, 176));
        btn_home.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_home.setRadius(30);
        btn_home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_homeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_homeMouseExited(evt);
            }
        });
        btn_home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_homeActionPerformed(evt);
            }
        });
        jPanel2.add(btn_home, new org.netbeans.lib.awtextra.AbsoluteConstraints(855, 500, 190, 35));

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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel19.setBackground(new java.awt.Color(8, 14, 112));

        jLabel23.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Dashboard / User Account");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

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

        lblfirstname.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        lblfirstname.setText("First Name:");

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

        lbllastname.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        lbllastname.setText("Last Name:");

        textfieldlastname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textfieldlastname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldlastname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textfieldlastnameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                textfieldlastnameMouseEntered(evt);
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

        lblusername.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        lblusername.setText("Username:");

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

        lblpassword.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        lblpassword.setText("Password:");

        lblconfirmpass.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        lblconfirmpass.setText("Confirm Pass:");

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

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/1.png"))); // NOI18N

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/2.png"))); // NOI18N

        jLabel52.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(204, 204, 204));
        jLabel52.setText("PROFILE");

        jLabel53.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(204, 204, 204));
        jLabel53.setText("PASSWORD");

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("jLabel18");

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

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel52))
                            .addComponent(lblusername)
                            .addComponent(lbllastname, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblfirstname))
                        .addGap(55, 55, 55)
                        .addComponent(jTextField1))
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(textfieldusername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addComponent(textfieldlastname, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(textfieldfirstname, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel53))
                            .addComponent(lblpassword)
                            .addComponent(lblconfirmpass)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateinfo_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 405, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblfirstname)
                        .addGap(11, 11, 11)
                        .addComponent(textfieldfirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(lbllastname)
                        .addGap(11, 11, 11)
                        .addComponent(textfieldlastname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblusername)
                            .addComponent(jTextField1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textfieldusername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateinfo_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblpassword)
                        .addGap(11, 11, 11)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblconfirmpass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTab.addTab("tab2", panel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(8, 14, 112));

        jLabel29.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Dashboard / Books");

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

        jPanel4.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 11, 1015, -1));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel25.setBackground(new java.awt.Color(64, 134, 176));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Books");
        jPanel25.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book1.png"))); // NOI18N
        jPanel25.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearchMousePressed(evt);
            }
        });
        textfieldsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearchKeyReleased(evt);
            }
        });
        jPanel25.add(textfieldsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 5, 230, 30));

        jLabel125.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel25.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 5, -1, 30));

        importbooks_btn.setBackground(new java.awt.Color(0, 153, 0));
        importbooks_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        importbooks_btn.setForeground(new java.awt.Color(255, 255, 255));
        importbooks_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-xls-import-25 (1).png"))); // NOI18N
        importbooks_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                importbooks_btnMouseEntered(evt);
            }
        });
        importbooks_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importbooks_btnActionPerformed(evt);
            }
        });
        jPanel25.add(importbooks_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 40, 40));

        jPanel91.setBackground(new java.awt.Color(255, 255, 255));
        jPanel91.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel16.setText("Book Title:");

        jLabel121.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel121.setText("Type:");

        comboboxtype.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboboxtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Book", "Magazine", "Newspaper", "" }));
        comboboxtype.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        comboboxtype.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboboxtypeMousePressed(evt);
            }
        });
        comboboxtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxtypeActionPerformed(evt);
            }
        });

        comboboxsubject.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboboxsubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Math", "English", "Science", "Filipino", "MAPEH", "TLE", "ESP", "AP" }));
        comboboxsubject.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        comboboxsubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboboxsubjectMousePressed(evt);
            }
        });

        jLabel122.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel122.setText("Subject:");

        jLabel123.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel123.setText("Quantity:");

        textfieldquantity.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldquantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldquantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldquantityMousePressed(evt);
            }
        });
        textfieldquantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textfieldquantityKeyPressed(evt);
            }
        });

        jLabel124.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel124.setText("Author:");

        textfieldauthor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldauthor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldauthor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldauthorMousePressed(evt);
            }
        });

        textfieldtitle.setColumns(20);
        textfieldtitle.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldtitle.setLineWrap(true);
        textfieldtitle.setRows(5);
        textfieldtitle.setWrapStyleWord(true);
        textfieldtitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldtitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldtitleMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(textfieldtitle);

        textfieldbookid.setForeground(new java.awt.Color(255, 255, 255));

        btn_add.setForeground(new java.awt.Color(255, 255, 255));
        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/addbook.png"))); // NOI18N
        btn_add.setBorderColor(new java.awt.Color(0, 153, 0));
        btn_add.setColor(new java.awt.Color(0, 153, 0));
        btn_add.setColorClick(new java.awt.Color(51, 255, 51));
        btn_add.setColorOver(new java.awt.Color(0, 153, 0));
        btn_add.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_add.setRadius(30);
        btn_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_addMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_addMouseExited(evt);
            }
        });
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(0, 153, 255));
        btn_edit.setForeground(new java.awt.Color(255, 255, 255));
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/editbook.png"))); // NOI18N
        btn_edit.setBorderColor(new java.awt.Color(0, 153, 255));
        btn_edit.setColor(new java.awt.Color(0, 153, 255));
        btn_edit.setColorClick(new java.awt.Color(102, 153, 255));
        btn_edit.setColorOver(new java.awt.Color(0, 153, 255));
        btn_edit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_edit.setRadius(30);
        btn_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_editMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_editMouseExited(evt);
            }
        });
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(255, 0, 0));
        btn_delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/deletebook.png"))); // NOI18N
        btn_delete.setBorderColor(new java.awt.Color(255, 0, 0));
        btn_delete.setColor(new java.awt.Color(255, 0, 0));
        btn_delete.setColorClick(new java.awt.Color(255, 102, 102));
        btn_delete.setColorOver(new java.awt.Color(255, 0, 0));
        btn_delete.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_delete.setRadius(30);
        btn_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_deleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_deleteMouseExited(evt);
            }
        });
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_reset2.setBackground(new java.awt.Color(102, 102, 102));
        btn_reset2.setForeground(new java.awt.Color(255, 255, 255));
        btn_reset2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/resetbook.png"))); // NOI18N
        btn_reset2.setBorderColor(new java.awt.Color(102, 102, 102));
        btn_reset2.setColor(new java.awt.Color(102, 102, 102));
        btn_reset2.setColorClick(new java.awt.Color(153, 153, 153));
        btn_reset2.setColorOver(new java.awt.Color(102, 102, 102));
        btn_reset2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_reset2.setRadius(30);
        btn_reset2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_reset2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_reset2MouseExited(evt);
            }
        });
        btn_reset2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reset2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel91Layout = new javax.swing.GroupLayout(jPanel91);
        jPanel91.setLayout(jPanel91Layout);
        jPanel91Layout.setHorizontalGroup(
            jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel91Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel91Layout.createSequentialGroup()
                        .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textfieldauthor))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel91Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel91Layout.createSequentialGroup()
                        .addGroup(jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel122, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                                .addComponent(jLabel121, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel123))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel91Layout.createSequentialGroup()
                                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_reset2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(comboboxsubject, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textfieldquantity)
                            .addComponent(comboboxtype, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel91Layout.createSequentialGroup()
                .addComponent(textfieldbookid, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel91Layout.setVerticalGroup(
            jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel91Layout.createSequentialGroup()
                .addComponent(textfieldbookid, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel91Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfieldauthor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxtype, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxsubject, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfieldquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_reset2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        table_books.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_books.setModel(new javax.swing.table.DefaultTableModel(
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
        table_books.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_books.setIntercellSpacing(new java.awt.Dimension(0, 0));
        table_books.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_books.setShowVerticalLines(false);
        table_books.getTableHeader().setReorderingAllowed(false);
        table_books.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_booksMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_books);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 81, -1, 470));

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

        panel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel28.setBackground(new java.awt.Color(64, 134, 176));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Borrowed Books");
        jPanel28.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/borrowbook1.png"))); // NOI18N
        jPanel28.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearch2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearch2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearch2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearch2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textfieldsearch2MouseReleased(evt);
            }
        });
        textfieldsearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearch2ActionPerformed(evt);
            }
        });
        textfieldsearch2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearch2KeyReleased(evt);
            }
        });
        jPanel28.add(textfieldsearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 5, 230, 30));

        jLabel169.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel28.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 5, -1, 30));

        jPanel92.setBackground(new java.awt.Color(255, 255, 255));
        jPanel92.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel127.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel127.setText("Student Name:");

        textfieldname.setEditable(false);
        textfieldname.setBackground(new java.awt.Color(255, 255, 255));
        textfieldname.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));

        jLabel128.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel128.setText("Book Title:");

        jLabel129.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel129.setText("Borrow Date:");

        jLabel130.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel130.setText("Due Date:");

        jLabel132.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel132.setText("Author:");

        textfieldauthor2.setEditable(false);
        textfieldauthor2.setBackground(new java.awt.Color(255, 255, 255));
        textfieldauthor2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldauthor2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));

        textfieldborrowdate.setEditable(false);
        textfieldborrowdate.setBackground(new java.awt.Color(255, 255, 255));
        textfieldborrowdate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldborrowdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));

        textfieldduedate.setEditable(false);
        textfieldduedate.setBackground(new java.awt.Color(255, 255, 255));
        textfieldduedate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldduedate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));

        textfieldlrn.setEditable(false);
        textfieldlrn.setBackground(new java.awt.Color(255, 255, 255));
        textfieldlrn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldlrn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));

        jLabel165.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel165.setText("LRN:");

        textfieldtitle2.setEditable(false);
        textfieldtitle2.setColumns(20);
        textfieldtitle2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldtitle2.setLineWrap(true);
        textfieldtitle2.setRows(5);
        textfieldtitle2.setWrapStyleWord(true);
        textfieldtitle2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        jScrollPane7.setViewportView(textfieldtitle2);

        javax.swing.GroupLayout jPanel92Layout = new javax.swing.GroupLayout(jPanel92);
        jPanel92.setLayout(jPanel92Layout);
        jPanel92Layout.setHorizontalGroup(
            jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel92Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel92Layout.createSequentialGroup()
                        .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel129, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textfieldborrowdate)
                            .addComponent(textfieldduedate)))
                    .addGroup(jPanel92Layout.createSequentialGroup()
                        .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel165, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel128, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel127, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textfieldauthor2)
                            .addComponent(textfieldname, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(textfieldlrn)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel92Layout.setVerticalGroup(
            jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel92Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel165, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfieldlrn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfieldname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfieldauthor2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfieldborrowdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfieldduedate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        table_borrowed.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_borrowed.setModel(new javax.swing.table.DefaultTableModel(
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
        table_borrowed.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_borrowed.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_borrowed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_borrowedMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(table_borrowed);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane18)
                .addContainerGap())
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 81, 1015, 470));

        jPanel94.setBackground(new java.awt.Color(8, 14, 112));

        jLabel135.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(255, 255, 255));
        jLabel135.setText("Dashboard / Borrowed Books");

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

        jPanel6.add(jPanel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 11, 1015, -1));

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1075, Short.MAX_VALUE)
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTab.addTab("tab4", panel4);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel29.setBackground(new java.awt.Color(8, 14, 112));

        jLabel37.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Dashboard / Students");

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addContainerGap(875, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel31.setBackground(new java.awt.Color(64, 134, 176));
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Students");
        jPanel31.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/student1.png"))); // NOI18N
        jPanel31.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearch3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearch3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearch3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearch3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textfieldsearch3MouseReleased(evt);
            }
        });
        textfieldsearch3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearch3KeyReleased(evt);
            }
        });
        jPanel31.add(textfieldsearch3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 5, 230, 30));

        jLabel126.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel31.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 5, -1, 30));

        jPanel93.setBackground(new java.awt.Color(255, 255, 255));
        jPanel93.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel133.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel133.setText("First Name:");

        textfieldfirstname2.setEditable(false);
        textfieldfirstname2.setBackground(new java.awt.Color(255, 255, 255));
        textfieldfirstname2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldfirstname2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));

        jLabel134.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel134.setText("Last Name:");

        textfieldlastname2.setEditable(false);
        textfieldlastname2.setBackground(new java.awt.Color(255, 255, 255));
        textfieldlastname2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldlastname2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));

        jLabel137.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel137.setText("Username:");

        textfieldusername2.setEditable(false);
        textfieldusername2.setBackground(new java.awt.Color(255, 255, 255));
        textfieldusername2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldusername2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldusername2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldusername2ActionPerformed(evt);
            }
        });

        jLabel138.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel138.setText("Total Number of Borrowed Books:");

        textfieldtotalborrow.setEditable(false);
        textfieldtotalborrow.setBackground(new java.awt.Color(255, 255, 255));
        textfieldtotalborrow.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldtotalborrow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));

        jLabel164.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel164.setText("LRN:");

        textfieldlrn2.setEditable(false);
        textfieldlrn2.setBackground(new java.awt.Color(255, 255, 255));
        textfieldlrn2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldlrn2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));

        javax.swing.GroupLayout jPanel93Layout = new javax.swing.GroupLayout(jPanel93);
        jPanel93.setLayout(jPanel93Layout);
        jPanel93Layout.setHorizontalGroup(
            jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel93Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel93Layout.createSequentialGroup()
                        .addComponent(jLabel138)
                        .addGap(18, 18, 18)
                        .addComponent(textfieldtotalborrow, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel93Layout.createSequentialGroup()
                        .addGroup(jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel164, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel134, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel133, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel137, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textfieldusername2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textfieldfirstname2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textfieldlrn2)
                            .addComponent(textfieldlastname2))))
                .addContainerGap())
        );
        jPanel93Layout.setVerticalGroup(
            jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel93Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel164, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfieldlrn2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfieldfirstname2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfieldlastname2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfieldusername2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfieldtotalborrow, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(147, Short.MAX_VALUE))
        );

        table_students.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_students.setModel(new javax.swing.table.DefaultTableModel(
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
        table_students.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_students.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_students.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_studentsMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(table_students);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 1015, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, 1015, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTab.addTab("tab5", panel5);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel32.setBackground(new java.awt.Color(8, 14, 112));

        jLabel41.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Dashboard / QR Code Generator");

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

        jPanel34.setBackground(new java.awt.Color(64, 134, 176));

        jLabel43.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("QR Code Generator");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/1.png"))); // NOI18N

        jLabel61.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(204, 204, 204));
        jLabel61.setText("GENERATE QR CODE");

        jLabel66.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 30)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(204, 204, 204));
        jLabel66.setText("PRINT QR CODE");

        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/2.png"))); // NOI18N

        qrcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));

        Titleofbook1.setBackground(new java.awt.Color(255, 255, 255));
        Titleofbook1.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        Titleofbook1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));

        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/title.png"))); // NOI18N

        jLabel72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/qr1.png"))); // NOI18N

        jLabel140.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        jLabel140.setText("Title of the Book:");

        Titleofbook.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Titleofbook.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        Titleofbook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TitleofbookMousePressed(evt);
            }
        });
        Titleofbook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TitleofbookActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel13.setText("No. of Copies:");

        copies.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        copies.setModel(new javax.swing.SpinnerNumberModel(1, 1, 20, 1));
        copies.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        copies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                copiesMousePressed(evt);
            }
        });

        jSeparator3.setBackground(new java.awt.Color(8, 14, 112));
        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btn_generate.setBackground(new java.awt.Color(0, 0, 0));
        btn_generate.setForeground(new java.awt.Color(255, 255, 255));
        btn_generate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-qr-code-25.png"))); // NOI18N
        btn_generate.setText("GENERATE");
        btn_generate.setBorderColor(new java.awt.Color(0, 0, 0));
        btn_generate.setColor(new java.awt.Color(0, 0, 0));
        btn_generate.setColorClick(new java.awt.Color(102, 102, 102));
        btn_generate.setColorOver(new java.awt.Color(0, 0, 0));
        btn_generate.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_generate.setRadius(30);
        btn_generate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_generateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_generateMouseExited(evt);
            }
        });
        btn_generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generateActionPerformed(evt);
            }
        });

        btn_print.setForeground(new java.awt.Color(255, 255, 255));
        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        btn_print.setText("PRINT");
        btn_print.setBorderColor(new java.awt.Color(64, 134, 176));
        btn_print.setColor(new java.awt.Color(64, 134, 176));
        btn_print.setColorClick(new java.awt.Color(102, 153, 255));
        btn_print.setColorOver(new java.awt.Color(64, 134, 176));
        btn_print.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
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

        btn_reset.setBackground(new java.awt.Color(102, 102, 102));
        btn_reset.setForeground(new java.awt.Color(255, 255, 255));
        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-reset-25.png"))); // NOI18N
        btn_reset.setText("RESET");
        btn_reset.setBorderColor(new java.awt.Color(102, 102, 102));
        btn_reset.setColor(new java.awt.Color(102, 102, 102));
        btn_reset.setColorClick(new java.awt.Color(204, 204, 204));
        btn_reset.setColorOver(new java.awt.Color(102, 102, 102));
        btn_reset.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_reset.setRadius(30);
        btn_reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_resetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_resetMouseExited(evt);
            }
        });
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel56)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addGap(402, 402, 402)
                                .addComponent(jLabel70))
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addGap(442, 442, 442)
                                .addComponent(jLabel66))
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel61))))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addGap(179, 179, 179)
                                .addComponent(jLabel72))
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_generate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Titleofbook, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(47, 47, 47)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel63)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(qrcode, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel33Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(45, 45, 45)
                                        .addComponent(copies, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btn_print, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_reset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Titleofbook1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Titleofbook, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_generate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(copies, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(btn_print, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addComponent(Titleofbook1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(qrcode, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 470, Short.MAX_VALUE)
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

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel35.setBackground(new java.awt.Color(8, 14, 112));

        jLabel45.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Dashboard / Reports");

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

        jPanel18.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 13, 1015, -1));

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
        label1.setText("BOOKS");

        javax.swing.GroupLayout tab9Layout = new javax.swing.GroupLayout(tab9);
        tab9.setLayout(tab9Layout);
        tab9Layout.setHorizontalGroup(
            tab9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab9Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(label1)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        tab9Layout.setVerticalGroup(
            tab9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel36.add(tab9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 150, 45));

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
        label2.setText("BORROWED");

        javax.swing.GroupLayout tab10Layout = new javax.swing.GroupLayout(tab10);
        tab10.setLayout(tab10Layout);
        tab10Layout.setHorizontalGroup(
            tab10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab10Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(label2)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        tab10Layout.setVerticalGroup(
            tab10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel36.add(tab10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 11, 150, -1));

        tab11.setBackground(new java.awt.Color(149, 189, 219));
        tab11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab11MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab11MousePressed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        label3.setText("RETURNED");

        javax.swing.GroupLayout tab11Layout = new javax.swing.GroupLayout(tab11);
        tab11.setLayout(tab11Layout);
        tab11Layout.setHorizontalGroup(
            tab11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab11Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(label3)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        tab11Layout.setVerticalGroup(
            tab11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label3)
                .addContainerGap())
        );

        jPanel36.add(tab11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 11, 150, -1));

        tab12.setBackground(new java.awt.Color(149, 189, 219));
        tab12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab12MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab12MousePressed(evt);
            }
        });

        label4.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        label4.setText("STUDENTS");

        javax.swing.GroupLayout tab12Layout = new javax.swing.GroupLayout(tab12);
        tab12.setLayout(tab12Layout);
        tab12Layout.setHorizontalGroup(
            tab12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab12Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(label4)
                .addGap(44, 44, 44))
        );
        tab12Layout.setVerticalGroup(
            tab12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel36.add(tab12, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 11, 150, -1));

        tab13.setBackground(new java.awt.Color(149, 189, 219));
        tab13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab13MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab13MousePressed(evt);
            }
        });

        label5.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        label5.setText("LATE UNRETURNED");

        javax.swing.GroupLayout tab13Layout = new javax.swing.GroupLayout(tab13);
        tab13.setLayout(tab13Layout);
        tab13Layout.setHorizontalGroup(
            tab13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab13Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(label5)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        tab13Layout.setVerticalGroup(
            tab13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label5)
                .addContainerGap())
        );

        jPanel36.add(tab13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 11, 160, -1));

        jPanel18.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 57, 1015, 50));

        reportsTab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        panelbooks.setBackground(new java.awt.Color(255, 255, 255));

        jPanel96.setBackground(new java.awt.Color(255, 255, 255));
        jPanel96.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));
        jPanel96.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel97.setBackground(new java.awt.Color(64, 134, 176));
        jPanel97.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel141.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel141.setForeground(new java.awt.Color(255, 255, 255));
        jLabel141.setText("Book");
        jPanel97.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel142.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/report1.png"))); // NOI18N
        jPanel97.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearch4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearch4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearch4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearch4MousePressed(evt);
            }
        });
        textfieldsearch4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearch4ActionPerformed(evt);
            }
        });
        textfieldsearch4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearch4KeyReleased(evt);
            }
        });
        jPanel97.add(textfieldsearch4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 5, 230, 30));

        jLabel170.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel97.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 5, -1, 30));

        exportexcel_btn.setBackground(new java.awt.Color(0, 153, 0));
        exportexcel_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        exportexcel_btn.setForeground(new java.awt.Color(255, 255, 255));
        exportexcel_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        exportexcel_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exportexcel_btnMouseEntered(evt);
            }
        });
        exportexcel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportexcel_btnActionPerformed(evt);
            }
        });
        jPanel97.add(exportexcel_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 40, 40));

        printreport_btn.setBackground(new java.awt.Color(0, 0, 255));
        printreport_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        printreport_btn.setForeground(new java.awt.Color(255, 255, 255));
        printreport_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        printreport_btn.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        printreport_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printreport_btnMouseEntered(evt);
            }
        });
        printreport_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printreport_btnActionPerformed(evt);
            }
        });
        jPanel97.add(printreport_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 0, 40, 40));

        jPanel96.add(jPanel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 1006, 40));

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
        table_bookreport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_bookreportMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_bookreport);

        jPanel96.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 59, 985, 370));

        javax.swing.GroupLayout panelbooksLayout = new javax.swing.GroupLayout(panelbooks);
        panelbooks.setLayout(panelbooksLayout);
        panelbooksLayout.setHorizontalGroup(
            panelbooksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelbooksLayout.createSequentialGroup()
                .addComponent(jPanel96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        panelbooksLayout.setVerticalGroup(
            panelbooksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelbooksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel96, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        reportsTab.addTab("tab1", panelbooks);

        panelborrowed.setBackground(new java.awt.Color(255, 255, 255));

        jPanel98.setBackground(new java.awt.Color(255, 255, 255));
        jPanel98.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));
        jPanel98.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel99.setBackground(new java.awt.Color(64, 134, 176));
        jPanel99.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel143.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel143.setForeground(new java.awt.Color(255, 255, 255));
        jLabel143.setText("Borrowed Book");
        jPanel99.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel144.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/report1.png"))); // NOI18N
        jPanel99.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearch5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearch5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearch5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearch5MousePressed(evt);
            }
        });
        textfieldsearch5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearch5ActionPerformed(evt);
            }
        });
        textfieldsearch5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearch5KeyReleased(evt);
            }
        });
        jPanel99.add(textfieldsearch5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 5, 230, 30));

        jLabel171.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel99.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 5, -1, 30));

        exportexcel2_btn.setBackground(new java.awt.Color(0, 153, 0));
        exportexcel2_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        exportexcel2_btn.setForeground(new java.awt.Color(255, 255, 255));
        exportexcel2_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        exportexcel2_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exportexcel2_btnMouseEntered(evt);
            }
        });
        exportexcel2_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportexcel2_btnActionPerformed(evt);
            }
        });
        jPanel99.add(exportexcel2_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 40, 40));

        printreport2_btn.setBackground(new java.awt.Color(0, 0, 255));
        printreport2_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        printreport2_btn.setForeground(new java.awt.Color(255, 255, 255));
        printreport2_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        printreport2_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printreport2_btnMouseEntered(evt);
            }
        });
        printreport2_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printreport2_btnActionPerformed(evt);
            }
        });
        jPanel99.add(printreport2_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 0, 40, 40));

        jPanel98.add(jPanel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 1010, 40));

        table_borrowedreport.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(table_borrowedreport);

        jPanel98.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 59, 985, 370));

        javax.swing.GroupLayout panelborrowedLayout = new javax.swing.GroupLayout(panelborrowed);
        panelborrowed.setLayout(panelborrowedLayout);
        panelborrowedLayout.setHorizontalGroup(
            panelborrowedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel98, javax.swing.GroupLayout.PREFERRED_SIZE, 1007, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelborrowedLayout.setVerticalGroup(
            panelborrowedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborrowedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        reportsTab.addTab("tab2", panelborrowed);

        panelreturned.setBackground(new java.awt.Color(255, 255, 255));

        jPanel100.setBackground(new java.awt.Color(255, 255, 255));
        jPanel100.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));
        jPanel100.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel101.setBackground(new java.awt.Color(64, 134, 176));
        jPanel101.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel145.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel145.setForeground(new java.awt.Color(255, 255, 255));
        jLabel145.setText("Returned Book");
        jPanel101.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel146.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/report1.png"))); // NOI18N
        jPanel101.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearch6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearch6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearch6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearch6MousePressed(evt);
            }
        });
        textfieldsearch6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearch6ActionPerformed(evt);
            }
        });
        textfieldsearch6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearch6KeyReleased(evt);
            }
        });
        jPanel101.add(textfieldsearch6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 5, 230, 30));

        jLabel172.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel101.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 5, -1, 30));

        exportexcel3_btn.setBackground(new java.awt.Color(0, 153, 0));
        exportexcel3_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        exportexcel3_btn.setForeground(new java.awt.Color(255, 255, 255));
        exportexcel3_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        exportexcel3_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exportexcel3_btnMouseEntered(evt);
            }
        });
        exportexcel3_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportexcel3_btnActionPerformed(evt);
            }
        });
        jPanel101.add(exportexcel3_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 40, 40));

        printreport3_btn.setBackground(new java.awt.Color(0, 0, 255));
        printreport3_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        printreport3_btn.setForeground(new java.awt.Color(255, 255, 255));
        printreport3_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        printreport3_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printreport3_btnMouseEntered(evt);
            }
        });
        printreport3_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printreport3_btnActionPerformed(evt);
            }
        });
        jPanel101.add(printreport3_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 0, 40, 40));

        jPanel100.add(jPanel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 1007, 40));

        table_returnedreport.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_returnedreport.setModel(new javax.swing.table.DefaultTableModel(
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
        table_returnedreport.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_returnedreport.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_returnedreport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_returnedreportMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(table_returnedreport);

        jPanel100.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 59, 985, 370));

        javax.swing.GroupLayout panelreturnedLayout = new javax.swing.GroupLayout(panelreturned);
        panelreturned.setLayout(panelreturnedLayout);
        panelreturnedLayout.setHorizontalGroup(
            panelreturnedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelreturnedLayout.setVerticalGroup(
            panelreturnedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelreturnedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        reportsTab.addTab("tab3", panelreturned);

        panelstudents.setBackground(new java.awt.Color(255, 255, 255));

        jPanel102.setBackground(new java.awt.Color(255, 255, 255));
        jPanel102.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));
        jPanel102.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel103.setBackground(new java.awt.Color(64, 134, 176));
        jPanel103.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel147.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel147.setForeground(new java.awt.Color(255, 255, 255));
        jLabel147.setText("Student");
        jPanel103.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel148.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/report1.png"))); // NOI18N
        jPanel103.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearch7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearch7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearch7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearch7MousePressed(evt);
            }
        });
        textfieldsearch7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearch7ActionPerformed(evt);
            }
        });
        textfieldsearch7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearch7KeyReleased(evt);
            }
        });
        jPanel103.add(textfieldsearch7, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 5, 230, 30));

        jLabel173.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel103.add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 5, -1, 30));

        exportexcel4_btn.setBackground(new java.awt.Color(0, 153, 0));
        exportexcel4_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        exportexcel4_btn.setForeground(new java.awt.Color(255, 255, 255));
        exportexcel4_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        exportexcel4_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exportexcel4_btnMouseEntered(evt);
            }
        });
        exportexcel4_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportexcel4_btnActionPerformed(evt);
            }
        });
        jPanel103.add(exportexcel4_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 40, 40));

        printreport4_btn.setBackground(new java.awt.Color(0, 0, 255));
        printreport4_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        printreport4_btn.setForeground(new java.awt.Color(255, 255, 255));
        printreport4_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        printreport4_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printreport4_btnMouseEntered(evt);
            }
        });
        printreport4_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printreport4_btnActionPerformed(evt);
            }
        });
        jPanel103.add(printreport4_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 0, 40, 40));

        jPanel102.add(jPanel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 1007, 40));

        table_studentreport.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_studentreport.setModel(new javax.swing.table.DefaultTableModel(
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
        table_studentreport.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_studentreport.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_studentreport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_studentreportMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(table_studentreport);

        jPanel102.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 59, 985, 370));

        javax.swing.GroupLayout panelstudentsLayout = new javax.swing.GroupLayout(panelstudents);
        panelstudents.setLayout(panelstudentsLayout);
        panelstudentsLayout.setHorizontalGroup(
            panelstudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel102, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelstudentsLayout.setVerticalGroup(
            panelstudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelstudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel102, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        reportsTab.addTab("tab4", panelstudents);

        jPanel109.setBackground(new java.awt.Color(255, 255, 255));
        jPanel109.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));
        jPanel109.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel110.setBackground(new java.awt.Color(64, 134, 176));
        jPanel110.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel149.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(255, 255, 255));
        jLabel149.setText("Late Unreturned Book");
        jPanel110.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel151.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/report1.png"))); // NOI18N
        jPanel110.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearch9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearch9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearch9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearch9MousePressed(evt);
            }
        });
        textfieldsearch9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearch9ActionPerformed(evt);
            }
        });
        textfieldsearch9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearch9KeyReleased(evt);
            }
        });
        jPanel110.add(textfieldsearch9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 5, 230, 30));

        jLabel183.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel110.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 5, -1, 30));

        exportexcel5_btn.setBackground(new java.awt.Color(0, 153, 0));
        exportexcel5_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        exportexcel5_btn.setForeground(new java.awt.Color(255, 255, 255));
        exportexcel5_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        exportexcel5_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exportexcel5_btnMouseEntered(evt);
            }
        });
        exportexcel5_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportexcel5_btnActionPerformed(evt);
            }
        });
        jPanel110.add(exportexcel5_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 40, 40));

        printreport5_btn.setBackground(new java.awt.Color(0, 0, 255));
        printreport5_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        printreport5_btn.setForeground(new java.awt.Color(255, 255, 255));
        printreport5_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        printreport5_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printreport5_btnMouseEntered(evt);
            }
        });
        printreport5_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printreport5_btnActionPerformed(evt);
            }
        });
        jPanel110.add(printreport5_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 0, 40, 40));

        jPanel109.add(jPanel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 1007, 40));

        table_lateunreturnedreport.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_lateunreturnedreport.setModel(new javax.swing.table.DefaultTableModel(
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
        table_lateunreturnedreport.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_lateunreturnedreport.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_lateunreturnedreport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_lateunreturnedreportMouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(table_lateunreturnedreport);

        jPanel109.add(jScrollPane21, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 59, 985, 370));

        javax.swing.GroupLayout jPanel108Layout = new javax.swing.GroupLayout(jPanel108);
        jPanel108.setLayout(jPanel108Layout);
        jPanel108Layout.setHorizontalGroup(
            jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel108Layout.setVerticalGroup(
            jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel108Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panellateunreturnedLayout = new javax.swing.GroupLayout(panellateunreturned);
        panellateunreturned.setLayout(panellateunreturnedLayout);
        panellateunreturnedLayout.setHorizontalGroup(
            panellateunreturnedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panellateunreturnedLayout.setVerticalGroup(
            panellateunreturnedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        reportsTab.addTab("tab5", panellateunreturned);

        jPanel18.add(reportsTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1015, 480));

        javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTab.addTab("tab7", panel7);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jPanel67.setBackground(new java.awt.Color(8, 14, 112));

        jLabel89.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Dashboard / Subjects");

        jLabel90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel89)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel68.setBackground(new java.awt.Color(255, 255, 255));
        jPanel68.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel69.setBackground(new java.awt.Color(64, 134, 176));
        jPanel69.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel91.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Mathematics");
        jPanel69.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book1.png"))); // NOI18N
        jPanel69.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearchmath.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearchmath.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearchmath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearchmathMousePressed(evt);
            }
        });
        textfieldsearchmath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearchmathActionPerformed(evt);
            }
        });
        textfieldsearchmath.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearchmathKeyReleased(evt);
            }
        });
        jPanel69.add(textfieldsearchmath, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 5, 230, 30));

        jLabel174.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel69.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 5, -1, 30));

        exportmath_btn.setBackground(new java.awt.Color(0, 153, 0));
        exportmath_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        exportmath_btn.setForeground(new java.awt.Color(255, 255, 255));
        exportmath_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        exportmath_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exportmath_btnMouseEntered(evt);
            }
        });
        exportmath_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportmath_btnActionPerformed(evt);
            }
        });
        jPanel69.add(exportmath_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(965, 0, 40, 40));

        printmath_btn.setBackground(new java.awt.Color(0, 0, 255));
        printmath_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        printmath_btn.setForeground(new java.awt.Color(255, 255, 255));
        printmath_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        printmath_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printmath_btnMouseEntered(evt);
            }
        });
        printmath_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printmath_btnActionPerformed(evt);
            }
        });
        jPanel69.add(printmath_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 40, 40));

        table_math.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_math.setModel(new javax.swing.table.DefaultTableModel(
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
        table_math.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_math.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_math.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_mathMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_math);

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel69, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelmathLayout = new javax.swing.GroupLayout(panelmath);
        panelmath.setLayout(panelmathLayout);
        panelmathLayout.setHorizontalGroup(
            panelmathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelmathLayout.setVerticalGroup(
            panelmathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainTab.addTab("tab8", panelmath);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jPanel70.setBackground(new java.awt.Color(8, 14, 112));

        jLabel93.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Dashboard / Subjects");

        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
        jPanel70.setLayout(jPanel70Layout);
        jPanel70Layout.setHorizontalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel93)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel70Layout.setVerticalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel71.setBackground(new java.awt.Color(255, 255, 255));
        jPanel71.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel72.setBackground(new java.awt.Color(64, 134, 176));
        jPanel72.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel95.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setText("English");
        jPanel72.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book1.png"))); // NOI18N
        jPanel72.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearcheng.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearcheng.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearcheng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearchengMousePressed(evt);
            }
        });
        textfieldsearcheng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearchengActionPerformed(evt);
            }
        });
        textfieldsearcheng.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearchengKeyReleased(evt);
            }
        });
        jPanel72.add(textfieldsearcheng, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 5, 230, 30));

        jLabel175.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel72.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 5, -1, 30));

        printeng_btn.setBackground(new java.awt.Color(0, 0, 255));
        printeng_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        printeng_btn.setForeground(new java.awt.Color(255, 255, 255));
        printeng_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        printeng_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printeng_btnMouseEntered(evt);
            }
        });
        printeng_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printeng_btnActionPerformed(evt);
            }
        });
        jPanel72.add(printeng_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 40, 40));

        exporteng_btn.setBackground(new java.awt.Color(0, 153, 0));
        exporteng_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        exporteng_btn.setForeground(new java.awt.Color(255, 255, 255));
        exporteng_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        exporteng_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exporteng_btnMouseEntered(evt);
            }
        });
        exporteng_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exporteng_btnActionPerformed(evt);
            }
        });
        jPanel72.add(exporteng_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(965, 0, 40, 40));

        table_eng.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_eng.setModel(new javax.swing.table.DefaultTableModel(
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
        table_eng.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_eng.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_eng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_engMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(table_eng);

        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
        jPanel71.setLayout(jPanel71Layout);
        jPanel71Layout.setHorizontalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel72, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanel71Layout.setVerticalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelengLayout = new javax.swing.GroupLayout(paneleng);
        paneleng.setLayout(panelengLayout);
        panelengLayout.setHorizontalGroup(
            panelengLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelengLayout.setVerticalGroup(
            panelengLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainTab.addTab("tab9", paneleng);

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));

        jPanel73.setBackground(new java.awt.Color(8, 14, 112));

        jLabel97.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText("Dashboard / Subjects");

        jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
        jPanel73.setLayout(jPanel73Layout);
        jPanel73Layout.setHorizontalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel97)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel97, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel74.setBackground(new java.awt.Color(255, 255, 255));
        jPanel74.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel75.setBackground(new java.awt.Color(64, 134, 176));
        jPanel75.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel99.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("Science");
        jPanel75.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book1.png"))); // NOI18N
        jPanel75.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearchsci.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearchsci.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearchsci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearchsciMousePressed(evt);
            }
        });
        textfieldsearchsci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearchsciActionPerformed(evt);
            }
        });
        textfieldsearchsci.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearchsciKeyReleased(evt);
            }
        });
        jPanel75.add(textfieldsearchsci, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 5, 230, 30));

        jLabel176.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel75.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 5, -1, 30));

        printsci_btn.setBackground(new java.awt.Color(0, 0, 255));
        printsci_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        printsci_btn.setForeground(new java.awt.Color(255, 255, 255));
        printsci_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        printsci_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printsci_btnMouseEntered(evt);
            }
        });
        printsci_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printsci_btnActionPerformed(evt);
            }
        });
        jPanel75.add(printsci_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 40, 40));

        exportsci_btn.setBackground(new java.awt.Color(0, 153, 0));
        exportsci_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        exportsci_btn.setForeground(new java.awt.Color(255, 255, 255));
        exportsci_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        exportsci_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exportsci_btnMouseEntered(evt);
            }
        });
        exportsci_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportsci_btnActionPerformed(evt);
            }
        });
        jPanel75.add(exportsci_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(965, 0, 40, 40));

        table_sci.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_sci.setModel(new javax.swing.table.DefaultTableModel(
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
        table_sci.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_sci.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_sci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_sciMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(table_sci);

        javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
        jPanel74.setLayout(jPanel74Layout);
        jPanel74Layout.setHorizontalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel75, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
            .addGroup(jPanel74Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addContainerGap())
        );
        jPanel74Layout.setVerticalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel74Layout.createSequentialGroup()
                .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelsciLayout = new javax.swing.GroupLayout(panelsci);
        panelsci.setLayout(panelsciLayout);
        panelsciLayout.setHorizontalGroup(
            panelsciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelsciLayout.setVerticalGroup(
            panelsciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainTab.addTab("tab10", panelsci);

        jPanel62.setBackground(new java.awt.Color(255, 255, 255));

        jPanel76.setBackground(new java.awt.Color(8, 14, 112));

        jLabel101.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 255, 255));
        jLabel101.setText("Dashboard / Subjects");

        jLabel102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
        jPanel76.setLayout(jPanel76Layout);
        jPanel76Layout.setHorizontalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel76Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel101)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel76Layout.setVerticalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel76Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel102, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel101, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel77.setBackground(new java.awt.Color(255, 255, 255));
        jPanel77.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel78.setBackground(new java.awt.Color(64, 134, 176));
        jPanel78.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel103.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(255, 255, 255));
        jLabel103.setText("Filipino");
        jPanel78.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book1.png"))); // NOI18N
        jPanel78.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearchfil.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearchfil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearchfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearchfilMousePressed(evt);
            }
        });
        textfieldsearchfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearchfilActionPerformed(evt);
            }
        });
        textfieldsearchfil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearchfilKeyReleased(evt);
            }
        });
        jPanel78.add(textfieldsearchfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 5, 230, 30));

        jLabel177.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel78.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 5, -1, 30));

        exportfil_btn.setBackground(new java.awt.Color(0, 153, 0));
        exportfil_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        exportfil_btn.setForeground(new java.awt.Color(255, 255, 255));
        exportfil_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        exportfil_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exportfil_btnMouseEntered(evt);
            }
        });
        exportfil_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportfil_btnActionPerformed(evt);
            }
        });
        jPanel78.add(exportfil_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(965, 0, 40, 40));

        printfil_btn.setBackground(new java.awt.Color(0, 0, 255));
        printfil_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        printfil_btn.setForeground(new java.awt.Color(255, 255, 255));
        printfil_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        printfil_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printfil_btnMouseEntered(evt);
            }
        });
        printfil_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printfil_btnActionPerformed(evt);
            }
        });
        jPanel78.add(printfil_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 40, 40));

        table_fil.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_fil.setModel(new javax.swing.table.DefaultTableModel(
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
        table_fil.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_fil.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_fil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_filMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(table_fil);

        javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
        jPanel77.setLayout(jPanel77Layout);
        jPanel77Layout.setHorizontalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel78, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9)
                .addContainerGap())
        );
        jPanel77Layout.setVerticalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addComponent(jPanel78, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelfilLayout = new javax.swing.GroupLayout(panelfil);
        panelfil.setLayout(panelfilLayout);
        panelfilLayout.setHorizontalGroup(
            panelfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelfilLayout.setVerticalGroup(
            panelfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainTab.addTab("tab11", panelfil);

        jPanel63.setBackground(new java.awt.Color(255, 255, 255));

        jPanel79.setBackground(new java.awt.Color(8, 14, 112));

        jLabel105.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 255, 255));
        jLabel105.setText("Dashboard / Subjects");

        jLabel106.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel79Layout = new javax.swing.GroupLayout(jPanel79);
        jPanel79.setLayout(jPanel79Layout);
        jPanel79Layout.setHorizontalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel79Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel105)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel79Layout.setVerticalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel79Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel106, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel80.setBackground(new java.awt.Color(255, 255, 255));
        jPanel80.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel81.setBackground(new java.awt.Color(64, 134, 176));
        jPanel81.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel107.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(255, 255, 255));
        jLabel107.setText("MAPEH");
        jPanel81.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book1.png"))); // NOI18N
        jPanel81.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearchmapeh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearchmapeh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearchmapeh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearchmapehMousePressed(evt);
            }
        });
        textfieldsearchmapeh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearchmapehActionPerformed(evt);
            }
        });
        textfieldsearchmapeh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearchmapehKeyReleased(evt);
            }
        });
        jPanel81.add(textfieldsearchmapeh, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 5, 230, 30));

        jLabel178.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel81.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 5, -1, 30));

        exportmapeh_btn.setBackground(new java.awt.Color(0, 153, 0));
        exportmapeh_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        exportmapeh_btn.setForeground(new java.awt.Color(255, 255, 255));
        exportmapeh_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        exportmapeh_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exportmapeh_btnMouseEntered(evt);
            }
        });
        exportmapeh_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportmapeh_btnActionPerformed(evt);
            }
        });
        jPanel81.add(exportmapeh_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(965, 0, 40, 40));

        printmapeh_btn.setBackground(new java.awt.Color(0, 0, 255));
        printmapeh_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        printmapeh_btn.setForeground(new java.awt.Color(255, 255, 255));
        printmapeh_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        printmapeh_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printmapeh_btnMouseEntered(evt);
            }
        });
        printmapeh_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printmapeh_btnActionPerformed(evt);
            }
        });
        jPanel81.add(printmapeh_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 40, 40));

        table_mapeh.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_mapeh.setModel(new javax.swing.table.DefaultTableModel(
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
        table_mapeh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_mapeh.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_mapeh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_mapehMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(table_mapeh);

        javax.swing.GroupLayout jPanel80Layout = new javax.swing.GroupLayout(jPanel80);
        jPanel80.setLayout(jPanel80Layout);
        jPanel80Layout.setHorizontalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel81, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10)
                .addContainerGap())
        );
        jPanel80Layout.setVerticalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelmapehLayout = new javax.swing.GroupLayout(panelmapeh);
        panelmapeh.setLayout(panelmapehLayout);
        panelmapehLayout.setHorizontalGroup(
            panelmapehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelmapehLayout.setVerticalGroup(
            panelmapehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel63, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainTab.addTab("tab12", panelmapeh);

        jPanel64.setBackground(new java.awt.Color(255, 255, 255));

        jPanel82.setBackground(new java.awt.Color(8, 14, 112));

        jLabel109.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(255, 255, 255));
        jLabel109.setText("Dashboard / Subjects");

        jLabel110.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel82Layout = new javax.swing.GroupLayout(jPanel82);
        jPanel82.setLayout(jPanel82Layout);
        jPanel82Layout.setHorizontalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel109)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel82Layout.setVerticalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel83.setBackground(new java.awt.Color(255, 255, 255));
        jPanel83.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel84.setBackground(new java.awt.Color(64, 134, 176));
        jPanel84.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel111.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(255, 255, 255));
        jLabel111.setText("TLE");
        jPanel84.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book1.png"))); // NOI18N
        jPanel84.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearchtle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearchtle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearchtle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearchtleMousePressed(evt);
            }
        });
        textfieldsearchtle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearchtleActionPerformed(evt);
            }
        });
        textfieldsearchtle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearchtleKeyReleased(evt);
            }
        });
        jPanel84.add(textfieldsearchtle, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 5, 230, 30));

        jLabel179.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel84.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 5, -1, 30));

        exporttle_btn.setBackground(new java.awt.Color(0, 153, 0));
        exporttle_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        exporttle_btn.setForeground(new java.awt.Color(255, 255, 255));
        exporttle_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        exporttle_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exporttle_btnMouseEntered(evt);
            }
        });
        exporttle_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exporttle_btnActionPerformed(evt);
            }
        });
        jPanel84.add(exporttle_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(965, 0, 40, 40));

        printtle_btn.setBackground(new java.awt.Color(0, 0, 255));
        printtle_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        printtle_btn.setForeground(new java.awt.Color(255, 255, 255));
        printtle_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        printtle_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printtle_btnMouseEntered(evt);
            }
        });
        printtle_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printtle_btnActionPerformed(evt);
            }
        });
        jPanel84.add(printtle_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 40, 40));

        table_tle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_tle.setModel(new javax.swing.table.DefaultTableModel(
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
        table_tle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_tle.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_tle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_tleMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(table_tle);

        javax.swing.GroupLayout jPanel83Layout = new javax.swing.GroupLayout(jPanel83);
        jPanel83.setLayout(jPanel83Layout);
        jPanel83Layout.setHorizontalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel84, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11)
                .addContainerGap())
        );
        jPanel83Layout.setVerticalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addComponent(jPanel84, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout paneltleLayout = new javax.swing.GroupLayout(paneltle);
        paneltle.setLayout(paneltleLayout);
        paneltleLayout.setHorizontalGroup(
            paneltleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        paneltleLayout.setVerticalGroup(
            paneltleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel64, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainTab.addTab("tab13", paneltle);

        jPanel65.setBackground(new java.awt.Color(255, 255, 255));

        jPanel85.setBackground(new java.awt.Color(8, 14, 112));

        jLabel113.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(255, 255, 255));
        jLabel113.setText("Dashboard / Subjects");

        jLabel114.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel85Layout = new javax.swing.GroupLayout(jPanel85);
        jPanel85.setLayout(jPanel85Layout);
        jPanel85Layout.setHorizontalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel113)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel85Layout.setVerticalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel114, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel86.setBackground(new java.awt.Color(255, 255, 255));
        jPanel86.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel87.setBackground(new java.awt.Color(64, 134, 176));
        jPanel87.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel115.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(255, 255, 255));
        jLabel115.setText("ESP");
        jPanel87.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel116.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book1.png"))); // NOI18N
        jPanel87.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearchesp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearchesp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearchesp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearchespMousePressed(evt);
            }
        });
        textfieldsearchesp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearchespActionPerformed(evt);
            }
        });
        textfieldsearchesp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearchespKeyReleased(evt);
            }
        });
        jPanel87.add(textfieldsearchesp, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 5, 230, 30));

        jLabel180.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel87.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 5, -1, 30));

        exportesp_btn.setBackground(new java.awt.Color(0, 153, 0));
        exportesp_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        exportesp_btn.setForeground(new java.awt.Color(255, 255, 255));
        exportesp_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        exportesp_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exportesp_btnMouseEntered(evt);
            }
        });
        exportesp_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportesp_btnActionPerformed(evt);
            }
        });
        jPanel87.add(exportesp_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(965, 0, 40, 40));

        printesp_btn.setBackground(new java.awt.Color(0, 0, 255));
        printesp_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        printesp_btn.setForeground(new java.awt.Color(255, 255, 255));
        printesp_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        printesp_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printesp_btnMouseEntered(evt);
            }
        });
        printesp_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printesp_btnActionPerformed(evt);
            }
        });
        jPanel87.add(printesp_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 40, 40));

        table_esp.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_esp.setModel(new javax.swing.table.DefaultTableModel(
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
        table_esp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_esp.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_esp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_espMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(table_esp);

        javax.swing.GroupLayout jPanel86Layout = new javax.swing.GroupLayout(jPanel86);
        jPanel86.setLayout(jPanel86Layout);
        jPanel86Layout.setHorizontalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel87, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
            .addGroup(jPanel86Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12)
                .addContainerGap())
        );
        jPanel86Layout.setVerticalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel86Layout.createSequentialGroup()
                .addComponent(jPanel87, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelespLayout = new javax.swing.GroupLayout(panelesp);
        panelesp.setLayout(panelespLayout);
        panelespLayout.setHorizontalGroup(
            panelespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelespLayout.setVerticalGroup(
            panelespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel65, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainTab.addTab("tab14", panelesp);

        jPanel66.setBackground(new java.awt.Color(255, 255, 255));

        jPanel88.setBackground(new java.awt.Color(8, 14, 112));

        jLabel117.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(255, 255, 255));
        jLabel117.setText("Dashboard / Subjects");

        jLabel118.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel88Layout = new javax.swing.GroupLayout(jPanel88);
        jPanel88.setLayout(jPanel88Layout);
        jPanel88Layout.setHorizontalGroup(
            jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel88Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel117)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel88Layout.setVerticalGroup(
            jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel88Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel118, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel117, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel89.setBackground(new java.awt.Color(255, 255, 255));
        jPanel89.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel90.setBackground(new java.awt.Color(64, 134, 176));
        jPanel90.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel119.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(255, 255, 255));
        jLabel119.setText("AP");
        jPanel90.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel120.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book1.png"))); // NOI18N
        jPanel90.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearchap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearchap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearchap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearchapMousePressed(evt);
            }
        });
        textfieldsearchap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearchapActionPerformed(evt);
            }
        });
        textfieldsearchap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearchapKeyReleased(evt);
            }
        });
        jPanel90.add(textfieldsearchap, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 5, 230, 30));

        jLabel181.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel90.add(jLabel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 5, -1, 30));

        exportap_btn.setBackground(new java.awt.Color(0, 153, 0));
        exportap_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        exportap_btn.setForeground(new java.awt.Color(255, 255, 255));
        exportap_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        exportap_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exportap_btnMouseEntered(evt);
            }
        });
        exportap_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportap_btnActionPerformed(evt);
            }
        });
        jPanel90.add(exportap_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(965, 0, 40, 40));

        printap_btn.setBackground(new java.awt.Color(0, 0, 255));
        printap_btn.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        printap_btn.setForeground(new java.awt.Color(255, 255, 255));
        printap_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-print-25.png"))); // NOI18N
        printap_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printap_btnMouseEntered(evt);
            }
        });
        printap_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printap_btnActionPerformed(evt);
            }
        });
        jPanel90.add(printap_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 40, 40));

        table_ap.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_ap.setModel(new javax.swing.table.DefaultTableModel(
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
        table_ap.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_ap.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_ap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_apMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(table_ap);

        javax.swing.GroupLayout jPanel89Layout = new javax.swing.GroupLayout(jPanel89);
        jPanel89.setLayout(jPanel89Layout);
        jPanel89Layout.setHorizontalGroup(
            jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel90, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
            .addGroup(jPanel89Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13)
                .addContainerGap())
        );
        jPanel89Layout.setVerticalGroup(
            jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel89Layout.createSequentialGroup()
                .addComponent(jPanel90, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel88, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelapLayout = new javax.swing.GroupLayout(panelap);
        panelap.setLayout(panelapLayout);
        panelapLayout.setHorizontalGroup(
            panelapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelapLayout.setVerticalGroup(
            panelapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel66, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainTab.addTab("tab15", panelap);

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));

        jPanel104.setBackground(new java.awt.Color(8, 14, 112));

        jLabel131.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel131.setForeground(new java.awt.Color(255, 255, 255));
        jLabel131.setText("Dashboard / Home");

        jLabel166.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel104Layout = new javax.swing.GroupLayout(jPanel104);
        jPanel104.setLayout(jPanel104Layout);
        jPanel104Layout.setHorizontalGroup(
            jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel104Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel166, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel131)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel104Layout.setVerticalGroup(
            jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel104Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel166, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel131, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel105.setBackground(new java.awt.Color(255, 255, 255));
        jPanel105.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel106.setBackground(new java.awt.Color(64, 134, 176));

        jLabel167.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel167.setForeground(new java.awt.Color(255, 255, 255));
        jLabel167.setText("HOME");

        jLabel168.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel106Layout = new javax.swing.GroupLayout(jPanel106);
        jPanel106.setLayout(jPanel106Layout);
        jPanel106Layout.setHorizontalGroup(
            jPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel106Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel168, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel167)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel106Layout.setVerticalGroup(
            jPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel106Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel168, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel167, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbl_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel28.setFont(new java.awt.Font("Rockwell Condensed", 0, 18)); // NOI18N
        jLabel28.setText("Event Name:");

        textfieldeventname.setColumns(20);
        textfieldeventname.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textfieldeventname.setLineWrap(true);
        textfieldeventname.setRows(5);
        textfieldeventname.setWrapStyleWord(true);
        jScrollPane17.setViewportView(textfieldeventname);

        table_home.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_home.setModel(new javax.swing.table.DefaultTableModel(
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
        table_home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_homeMouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(table_home);

        textfieldhomeid.setForeground(new java.awt.Color(255, 255, 255));

        btn_browse.setBackground(new java.awt.Color(0, 0, 0));
        btn_browse.setForeground(new java.awt.Color(255, 255, 255));
        btn_browse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-add-image-25.png"))); // NOI18N
        btn_browse.setText("BROWSE");
        btn_browse.setBorderColor(new java.awt.Color(0, 0, 0));
        btn_browse.setColor(new java.awt.Color(0, 0, 0));
        btn_browse.setColorClick(new java.awt.Color(102, 102, 102));
        btn_browse.setColorOver(new java.awt.Color(0, 0, 0));
        btn_browse.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_browse.setRadius(30);
        btn_browse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_browseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_browseMouseExited(evt);
            }
        });
        btn_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_browseActionPerformed(evt);
            }
        });

        btn_add2.setForeground(new java.awt.Color(255, 255, 255));
        btn_add2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/addevent.png"))); // NOI18N
        btn_add2.setBorderColor(new java.awt.Color(0, 153, 0));
        btn_add2.setColor(new java.awt.Color(0, 153, 0));
        btn_add2.setColorClick(new java.awt.Color(51, 255, 51));
        btn_add2.setColorOver(new java.awt.Color(0, 153, 0));
        btn_add2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_add2.setRadius(30);
        btn_add2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_add2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_add2MouseExited(evt);
            }
        });
        btn_add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add2ActionPerformed(evt);
            }
        });

        btn_edit2.setBackground(new java.awt.Color(0, 153, 255));
        btn_edit2.setForeground(new java.awt.Color(255, 255, 255));
        btn_edit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/editevent.png"))); // NOI18N
        btn_edit2.setBorderColor(new java.awt.Color(0, 153, 255));
        btn_edit2.setColor(new java.awt.Color(0, 153, 255));
        btn_edit2.setColorClick(new java.awt.Color(102, 153, 255));
        btn_edit2.setColorOver(new java.awt.Color(0, 153, 255));
        btn_edit2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_edit2.setRadius(30);
        btn_edit2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_edit2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_edit2MouseExited(evt);
            }
        });
        btn_edit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit2ActionPerformed(evt);
            }
        });

        btn_delete2.setBackground(new java.awt.Color(255, 0, 0));
        btn_delete2.setForeground(new java.awt.Color(255, 255, 255));
        btn_delete2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/deletevent.png"))); // NOI18N
        btn_delete2.setBorderColor(new java.awt.Color(255, 0, 0));
        btn_delete2.setColor(new java.awt.Color(255, 0, 0));
        btn_delete2.setColorClick(new java.awt.Color(255, 102, 102));
        btn_delete2.setColorOver(new java.awt.Color(255, 0, 0));
        btn_delete2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_delete2.setRadius(30);
        btn_delete2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_delete2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_delete2MouseExited(evt);
            }
        });
        btn_delete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete2ActionPerformed(evt);
            }
        });

        btn_reset3.setBackground(new java.awt.Color(102, 102, 102));
        btn_reset3.setForeground(new java.awt.Color(255, 255, 255));
        btn_reset3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/resetevent.png"))); // NOI18N
        btn_reset3.setBorderColor(new java.awt.Color(102, 102, 102));
        btn_reset3.setColor(new java.awt.Color(102, 102, 102));
        btn_reset3.setColorClick(new java.awt.Color(204, 204, 204));
        btn_reset3.setColorOver(new java.awt.Color(102, 102, 102));
        btn_reset3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_reset3.setRadius(30);
        btn_reset3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_reset3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_reset3MouseExited(evt);
            }
        });
        btn_reset3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reset3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel105Layout = new javax.swing.GroupLayout(jPanel105);
        jPanel105.setLayout(jPanel105Layout);
        jPanel105Layout.setHorizontalGroup(
            jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel106, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel105Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel105Layout.createSequentialGroup()
                        .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel105Layout.createSequentialGroup()
                        .addComponent(btn_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel105Layout.createSequentialGroup()
                        .addGroup(jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel105Layout.createSequentialGroup()
                                .addGroup(jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(textfieldhomeid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel105Layout.createSequentialGroup()
                        .addComponent(btn_add2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_edit2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_reset3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        jPanel105Layout.setVerticalGroup(
            jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel105Layout.createSequentialGroup()
                .addComponent(jPanel106, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel105Layout.createSequentialGroup()
                        .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btn_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel105Layout.createSequentialGroup()
                        .addGroup(jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel105Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textfieldhomeid, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_edit2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(btn_add2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_delete2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_reset3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel104, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel105, javax.swing.GroupLayout.PREFERRED_SIZE, 467, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelhomeLayout = new javax.swing.GroupLayout(panelhome);
        panelhome.setLayout(panelhomeLayout);
        panelhomeLayout.setHorizontalGroup(
            panelhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelhomeLayout.setVerticalGroup(
            panelhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainTab.addTab("tab16", panelhome);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        jPanel40.setBackground(new java.awt.Color(8, 14, 112));

        jLabel33.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Dashboard / Import Excel File");

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel46.setBackground(new java.awt.Color(64, 134, 176));
        jPanel46.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Books");
        jPanel46.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, -1, 18));

        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/book1.png"))); // NOI18N
        jPanel46.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        importexcelfile_btn.setBackground(new java.awt.Color(0, 153, 0));
        importexcelfile_btn.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        importexcelfile_btn.setForeground(new java.awt.Color(255, 255, 255));
        importexcelfile_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-microsoft-excel-25.png"))); // NOI18N
        importexcelfile_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                importexcelfile_btnMouseEntered(evt);
            }
        });
        importexcelfile_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importexcelfile_btnActionPerformed(evt);
            }
        });
        jPanel46.add(importexcelfile_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(875, 0, 40, 40));

        insertdata_btn.setBackground(new java.awt.Color(0, 0, 255));
        insertdata_btn.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        insertdata_btn.setForeground(new java.awt.Color(255, 255, 255));
        insertdata_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-insert-table-25.png"))); // NOI18N
        insertdata_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                insertdata_btnMouseEntered(evt);
            }
        });
        insertdata_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertdata_btnActionPerformed(evt);
            }
        });
        jPanel46.add(insertdata_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 40, 40));

        back_btn.setBackground(new java.awt.Color(0, 0, 0));
        back_btn.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        back_btn.setForeground(new java.awt.Color(255, 255, 255));
        back_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/icons8-back-arrow-25white.png"))); // NOI18N
        back_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                back_btnMouseEntered(evt);
            }
        });
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });
        jPanel46.add(back_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(965, 0, 40, 40));

        table_importexcel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_importexcel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Book ID", "Title ", "Author", "Type", "Subject", "Quantity"
            }
        ));
        table_importexcel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_importexcel.setIntercellSpacing(new java.awt.Dimension(0, 0));
        table_importexcel.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_importexcel.setShowVerticalLines(false);
        table_importexcel.getTableHeader().setReorderingAllowed(false);
        table_importexcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_importexcelMouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(table_importexcel);

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane20)
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_importexcelLayout = new javax.swing.GroupLayout(panel_importexcel);
        panel_importexcel.setLayout(panel_importexcelLayout);
        panel_importexcelLayout.setHorizontalGroup(
            panel_importexcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_importexcelLayout.setVerticalGroup(
            panel_importexcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainTab.addTab("tab17", panel_importexcel);

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 14, 112)));

        jPanel53.setBackground(new java.awt.Color(64, 134, 176));
        jPanel53.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Late Unreturned Books");
        jPanel53.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 11, 150, 18));

        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/borrowbook1.png"))); // NOI18N
        jPanel53.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, -1, -1));

        textfieldsearch8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textfieldsearch8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56)));
        textfieldsearch8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textfieldsearch8MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textfieldsearch8MouseReleased(evt);
            }
        });
        textfieldsearch8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldsearch8ActionPerformed(evt);
            }
        });
        textfieldsearch8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldsearch8KeyReleased(evt);
            }
        });
        jPanel53.add(textfieldsearch8, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 5, 230, 30));

        jLabel182.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/output-onlinepngtools.png"))); // NOI18N
        jPanel53.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 5, -1, 30));

        table_borrowedlate.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        table_borrowedlate.setModel(new javax.swing.table.DefaultTableModel(
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
        table_borrowedlate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_borrowedlate.setSelectionBackground(new java.awt.Color(0, 0, 0));
        table_borrowedlate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_borrowedlateMouseClicked(evt);
            }
        });
        jScrollPane22.setViewportView(table_borrowedlate);

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel47.add(jPanel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 81, 1015, 470));

        jPanel107.setBackground(new java.awt.Color(8, 14, 112));

        jLabel156.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel156.setForeground(new java.awt.Color(255, 255, 255));
        jLabel156.setText("Dashboard / Late Unreturned Books");

        jLabel157.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/dashboard1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel107Layout = new javax.swing.GroupLayout(jPanel107);
        jPanel107.setLayout(jPanel107Layout);
        jPanel107Layout.setHorizontalGroup(
            jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel107Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel156)
                .addContainerGap(759, Short.MAX_VALUE))
        );
        jPanel107Layout.setVerticalGroup(
            jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel107Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel157, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel156, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel47.add(jPanel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 11, 1015, -1));

        javax.swing.GroupLayout panel_notificationLayout = new javax.swing.GroupLayout(panel_notification);
        panel_notification.setLayout(panel_notificationLayout);
        panel_notificationLayout.setHorizontalGroup(
            panel_notificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, 1075, Short.MAX_VALUE)
        );
        panel_notificationLayout.setVerticalGroup(
            panel_notificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_notificationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTab.addTab("tab18", panel_notification);

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
                .addContainerGap(1197, Short.MAX_VALUE)
                .addComponent(dateLab1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateLab)
                .addGap(37, 37, 37))
        );
        lowerPanelLayout.setVerticalGroup(
            lowerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(dateLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateLab1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
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

    public void librarian() {
        Connection con = (Connection) myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = (PreparedStatement) con.prepareStatement("SELECT first_name, last_name, Username FROM librarianlogin WHERE ID = ?");
            ps.setInt(1, myConnection.index);
            rs = ps.executeQuery();

            if (rs.next()) {
                String f = rs.getString("first_name");
                textfieldfirstname.setText(f);
                //lbl_firstname.setText(f);
                String l = rs.getString("last_name");
                textfieldlastname.setText(l);
                String u = rs.getString("Username");
                textfieldusername.setText(u);
                String n = rs.getString("first_name") + " " + rs.getString("last_name");
                lbl_fullname.setText(n);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public boolean verifData() {
        if (textfieldfirstname.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "First name is required", "Message", JOptionPane.ERROR_MESSAGE);
        } else if (textfieldlastname.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Last name is required", "Message", JOptionPane.ERROR_MESSAGE);
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

    public boolean verifBook() {
        if (textfieldtitle.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Book Title is required", "Message", JOptionPane.ERROR_MESSAGE);
        } else if (textfieldauthor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Author is required", "Message", JOptionPane.ERROR_MESSAGE);
        } else if (textfieldquantity.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quantity is required", "Message", JOptionPane.ERROR_MESSAGE);

            return false;
        } else {
            return true;
        }
        return false;
    }

    public boolean verifEvent() {
        if (textfieldeventname.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Event Name is required", "Message", JOptionPane.ERROR_MESSAGE);

        } else {
            return true;
        }
        return false;
    }

    public void populateJtable() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        LibrarianHomepageQuery lq = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage> infList = lq.InfoList();
        String[] colNames = {"Book ID", "Title", "Author", "Type", "Subject", "Quantity"};
        Object[][] rows = new Object[infList.size()][6];

        for (int i = 0; i < infList.size(); i++) {
            rows[i][0] = infList.get(i).getBook_id();
            rows[i][1] = infList.get(i).getBook_title();
            rows[i][2] = infList.get(i).getAuthor();
            rows[i][3] = infList.get(i).getType();
            rows[i][4] = infList.get(i).getSubject();
            rows[i][5] = infList.get(i).getQuantity();

        }
        MyModel1 mmd1 = new MyModel1(rows, colNames);
        table_books.setModel(mmd1);
        table_books.setRowHeight(30);
        //table_books.getColumnModel().getColumn(5).setPreferredWidth(80);

        LibrarianHomepageQuery lq18 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage> infList18 = lq.InfoList();
        String[] colNames18 = {"Title", "Author", "Type", "Subject", "Quantity"};
        Object[][] rows18 = new Object[infList.size()][6];

        for (int i = 0; i < infList18.size(); i++) {
            // rows18[i][0] = infList18.get(i).getBook_id();
            rows18[i][0] = infList18.get(i).getBook_title();
            rows18[i][1] = infList18.get(i).getAuthor();
            rows18[i][2] = infList18.get(i).getType();
            rows18[i][3] = infList18.get(i).getSubject();
            rows18[i][4] = infList18.get(i).getQuantity();

        }
        MyModel2 mmd2 = new MyModel2(rows18, colNames18);
        table_bookreport.setModel(mmd2);
        table_bookreport.setRowHeight(30);

        LibrarianHomepageQuery lq3 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage> infList3 = lq3.InfoList3();
        String[] colNames3 = {"Title", "Author", "Type", "Subject", "Quantity"};
        Object[][] rows3 = new Object[infList3.size()][6];

        for (int i = 0; i < infList3.size(); i++) {
            //rows3[i][0] = infList3.get(i).getBook_id();
            rows3[i][0] = infList3.get(i).getBook_title();
            rows3[i][1] = infList3.get(i).getAuthor();
            rows3[i][2] = infList3.get(i).getType();
            rows3[i][3] = infList3.get(i).getSubject();
            rows3[i][4] = infList3.get(i).getQuantity();

        }
        MyModel4 mmd4 = new MyModel4(rows3, colNames3);
        table_math.setModel(mmd4);
        table_math.setRowHeight(30);

        LibrarianHomepageQuery lq4 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage> infList4 = lq4.InfoList4();
        String[] colNames4 = {"Title", "Author", "Type", "Subject", "Quantity"};
        Object[][] rows4 = new Object[infList4.size()][6];

        for (int i = 0; i < infList4.size(); i++) {
            //rows4[i][0] = infList4.get(i).getBook_id();
            rows4[i][0] = infList4.get(i).getBook_title();
            rows4[i][1] = infList4.get(i).getAuthor();
            rows4[i][2] = infList4.get(i).getType();
            rows4[i][3] = infList4.get(i).getSubject();
            rows4[i][4] = infList4.get(i).getQuantity();

        }
        MyModel5 mmd5 = new MyModel5(rows4, colNames4);
        table_eng.setModel(mmd5);
        table_eng.setRowHeight(30);

        LibrarianHomepageQuery lq5 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage> infList5 = lq5.InfoList5();
        String[] colNames5 = {"Title", "Author", "Type", "Subject", "Quantity"};
        Object[][] rows5 = new Object[infList5.size()][6];

        for (int i = 0; i < infList5.size(); i++) {
            //rows5[i][0] = infList5.get(i).getBook_id();
            rows5[i][0] = infList5.get(i).getBook_title();
            rows5[i][1] = infList5.get(i).getAuthor();
            rows5[i][2] = infList5.get(i).getType();
            rows5[i][3] = infList5.get(i).getSubject();
            rows5[i][4] = infList5.get(i).getQuantity();

        }
        MyModel6 mmd6 = new MyModel6(rows5, colNames5);
        table_sci.setModel(mmd6);
        table_sci.setRowHeight(30);

        LibrarianHomepageQuery lq6 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage> infList6 = lq6.InfoList6();
        String[] colNames6 = {"Title", "Author", "Type", "Subject", "Quantity"};
        Object[][] rows6 = new Object[infList6.size()][6];

        for (int i = 0; i < infList6.size(); i++) {
            //rows6[i][0] = infList6.get(i).getBook_id();
            rows6[i][0] = infList6.get(i).getBook_title();
            rows6[i][1] = infList6.get(i).getAuthor();
            rows6[i][2] = infList6.get(i).getType();
            rows6[i][3] = infList6.get(i).getSubject();
            rows6[i][4] = infList6.get(i).getQuantity();

        }
        MyModel7 mmd7 = new MyModel7(rows6, colNames6);
        table_fil.setModel(mmd7);
        table_fil.setRowHeight(30);

        LibrarianHomepageQuery lq7 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage> infList7 = lq7.InfoList7();
        String[] colNames7 = {"Title", "Author", "Type", "Subject", "Quantity"};
        Object[][] rows7 = new Object[infList7.size()][6];

        for (int i = 0; i < infList7.size(); i++) {
            // rows7[i][0] = infList7.get(i).getBook_id();
            rows7[i][0] = infList7.get(i).getBook_title();
            rows7[i][1] = infList7.get(i).getAuthor();
            rows7[i][2] = infList7.get(i).getType();
            rows7[i][3] = infList7.get(i).getSubject();
            rows7[i][4] = infList7.get(i).getQuantity();

        }
        MyModel8 mmd8 = new MyModel8(rows7, colNames7);
        table_mapeh.setModel(mmd8);
        table_mapeh.setRowHeight(30);

        LibrarianHomepageQuery lq8 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage> infList8 = lq8.InfoList8();
        String[] colNames8 = {"Title", "Author", "Type", "Subject", "Quantity"};
        Object[][] rows8 = new Object[infList8.size()][6];

        for (int i = 0; i < infList8.size(); i++) {
            //rows8[i][0] = infList8.get(i).getBook_id();
            rows8[i][0] = infList8.get(i).getBook_title();
            rows8[i][1] = infList8.get(i).getAuthor();
            rows8[i][2] = infList8.get(i).getType();
            rows8[i][3] = infList8.get(i).getSubject();
            rows8[i][4] = infList8.get(i).getQuantity();

        }
        MyModel9 mmd9 = new MyModel9(rows8, colNames8);
        table_tle.setModel(mmd9);
        table_tle.setRowHeight(30);

        LibrarianHomepageQuery lq9 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage> infList9 = lq9.InfoList9();
        String[] colNames9 = {"Title", "Author", "Type", "Subject", "Quantity"};
        Object[][] rows9 = new Object[infList9.size()][6];

        for (int i = 0; i < infList9.size(); i++) {
            // rows9[i][0] = infList9.get(i).getBook_id();
            rows9[i][0] = infList9.get(i).getBook_title();
            rows9[i][1] = infList9.get(i).getAuthor();
            rows9[i][2] = infList9.get(i).getType();
            rows9[i][3] = infList9.get(i).getSubject();
            rows9[i][4] = infList9.get(i).getQuantity();

        }
        MyModel10 mmd10 = new MyModel10(rows9, colNames9);
        table_esp.setModel(mmd10);
        table_esp.setRowHeight(30);

        LibrarianHomepageQuery lq10 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage> infList10 = lq10.InfoList10();
        String[] colNames10 = {"Title", "Author", "Type", "Subject", "Quantity"};
        Object[][] rows10 = new Object[infList10.size()][6];

        for (int i = 0; i < infList10.size(); i++) {
            //rows10[i][0] = infList10.get(i).getBook_id();
            rows10[i][0] = infList10.get(i).getBook_title();
            rows10[i][1] = infList10.get(i).getAuthor();
            rows10[i][2] = infList10.get(i).getType();
            rows10[i][3] = infList10.get(i).getSubject();
            rows10[i][4] = infList10.get(i).getQuantity();

        }
        MyModel11 mmd11 = new MyModel11(rows10, colNames10);
        table_ap.setModel(mmd11);
        table_ap.setRowHeight(30);

        LibrarianHomepageQuery lq13 = new LibrarianHomepageQuery();
        ArrayList<StudentAccount2> infList13 = lq13.InfoList13();
        String[] colNames13 = {"LRN", "First Name", "Last Name", "Username", "Total Books"};
        Object[][] rows13 = new Object[infList13.size()][6];

        for (int i = 0; i < infList13.size(); i++) {
            // rows13[i][0] = infList13.get(i).getID();
            rows13[i][0] = infList13.get(i).getLrn();
            rows13[i][1] = infList13.get(i).getFirst_name();
            rows13[i][2] = infList13.get(i).getLast_name();
            rows13[i][3] = infList13.get(i).getUsername();
            rows13[i][4] = infList13.get(i).getTotal_books();

        }
        MyModel12 mmd12 = new MyModel12(rows13, colNames13);
        table_students.setModel(mmd12);
        table_students.setRowHeight(30);

        LibrarianHomepageQuery lq11 = new LibrarianHomepageQuery();
        ArrayList<StudentAccount> infList11 = lq11.InfoList11();
        String[] colNames11 = {"LRN", "First Name", "Last Name", "Username"};
        Object[][] rows11 = new Object[infList11.size()][6];

        for (int i = 0; i < infList11.size(); i++) {
            // rows11[i][0] = infList11.get(i).getID();
            rows11[i][0] = infList11.get(i).getLrn();
            rows11[i][1] = infList11.get(i).getFirst_name();
            rows11[i][2] = infList11.get(i).getLast_name();
            rows11[i][3] = infList11.get(i).getUsername();

        }
        MyModel14 mmd14 = new MyModel14(rows11, colNames11);
        table_studentreport.setModel(mmd14);
        table_studentreport.setRowHeight(30);

        LibrarianHomepageQuery lq12 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage2> infList12 = lq12.InfoList12();
        String[] colNames12 = {"Title", "Author", "Borrow Date", "Due Date", "LRN", "Student Name"};
        Object[][] rows12 = new Object[infList12.size()][10];

        for (int i = 0; i < infList12.size(); i++) {
            //rows12[i][0] = infList12.get(i).getBorrowed_id();
            rows12[i][0] = infList12.get(i).getBook_title();
            rows12[i][1] = infList12.get(i).getAuthor();

            // rows12[i][3] = infList12.get(i).getQuantity();
            rows12[i][2] = infList12.get(i).getBorrow_date();
            rows12[i][3] = infList12.get(i).getReturn_date();
            // rows12[i][6] = infList12.get(i).getStudent_id();
            rows12[i][4] = infList12.get(i).getLrn();
            //rows12[i][7] = infList12.get(i).getUsername();
            rows12[i][5] = infList12.get(i).getName();

        }
        MyModel13 mmd13 = new MyModel13(rows12, colNames12);
        table_borrowedreport.setModel(mmd13);
        table_borrowedreport.setRowHeight(30);

        for (int i = 0; i < infList12.size(); i++) {
            //rows12[i][0] = infList12.get(i).getBorrowed_id();
            rows12[i][0] = infList12.get(i).getBook_title();
            rows12[i][1] = infList12.get(i).getAuthor();

            // rows12[i][3] = infList12.get(i).getQuantity();
            rows12[i][2] = infList12.get(i).getBorrow_date();
            rows12[i][3] = infList12.get(i).getReturn_date();
            // rows12[i][6] = infList12.get(i).getStudent_id();
            rows12[i][4] = infList12.get(i).getLrn();
            //rows12[i][7] = infList12.get(i).getUsername();
            rows12[i][5] = infList12.get(i).getName();

        }
        MyModel15 mmd15 = new MyModel15(rows12, colNames12);
        table_borrowed.setModel(mmd15);
        table_borrowed.setRowHeight(30);

        LibrarianHomepageQuery lq15 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage3> infList15 = lq15.InfoList15();
        String[] colNames15 = {"Title", "Author", "Due Date", "Returned Date", "LRN", "Student Name", "Status"};
        Object[][] rows15 = new Object[infList15.size()][10];

        for (int i = 0; i < infList15.size(); i++) {
            //rows15[i][0] = infList15.get(i).getReturned_id();
            rows15[i][0] = infList15.get(i).getBook_title();
            rows15[i][1] = infList15.get(i).getAuthor();

            // rows12[i][3] = infList12.get(i).getQuantity();
            rows15[i][2] = infList15.get(i).getDue_date();
            rows15[i][3] = infList15.get(i).getReturn_date();
            // rows12[i][6] = infList12.get(i).getStudent_id();
            rows15[i][4] = infList15.get(i).getLrn();
            //rows12[i][7] = infList12.get(i).getUsername();
            rows15[i][5] = infList15.get(i).getName();
            rows15[i][6] = infList15.get(i).getStatus();

        }
        MyModel18 mmd18 = new MyModel18(rows15, colNames15);
        table_returnedreport.setModel(mmd18);
        table_returnedreport.setRowHeight(30);

        LibrarianHomepageQuery lq16 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage4> infList16 = lq16.InfoList16();
        String[] colNames16 = {"ID", "Event_Name", "Picture"};
        Object[][] rows16 = new Object[infList.size()][3];

        for (int i = 0; i < infList16.size(); i++) {
            rows16[i][0] = infList16.get(i).getHome_id();
            rows16[i][1] = infList16.get(i).getEvent_name();

            ImageIcon pic = new ImageIcon(new ImageIcon(infList16.get(i).getPicture())
                    .getImage()
                    .getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));

            rows16[i][2] = pic;
        }
        MyModel19 mmd19 = new MyModel19(rows16, colNames16);
        table_home.setModel(mmd19);
        table_home.setRowHeight(30);
        // table_home.getColumnModel().getColumn(2).setPreferredWidth(80);

        LibrarianHomepageQuery lq17 = new LibrarianHomepageQuery();
        ArrayList<LibrarianHomepage6> infList17 = lq17.InfoList17();
        String[] colNames17 = {"Title", "Author", "Borrow Date", "Due Date", "LRN", "Student Name", "Status"};
        Object[][] rows17 = new Object[infList12.size()][10];

        for (int i = 0; i < infList17.size(); i++) {
            //rows17[i][0] = infList17.get(i).getBorrowed_id();
            rows17[i][0] = infList17.get(i).getBook_title();
            rows17[i][1] = infList17.get(i).getAuthor();

            // rows12[i][3] = infList12.get(i).getQuantity();
            rows17[i][2] = infList17.get(i).getBorrow_date();
            rows17[i][3] = infList17.get(i).getReturn_date();
            // rows12[i][6] = infList12.get(i).getStudent_id();
            rows17[i][4] = infList17.get(i).getLrn();
            //rows12[i][7] = infList12.get(i).getUsername();
            rows17[i][5] = infList17.get(i).getName();
            rows17[i][6] = infList17.get(i).getStatus();

        }
        MyModel20 mmd20 = new MyModel20(rows17, colNames17);
        table_borrowedlate.setModel(mmd20);
        table_borrowedlate.setRowHeight(30);

        for (int i = 0; i < infList17.size(); i++) {
            //rows17[i][0] = infList17.get(i).getBorrowed_id();
            rows17[i][0] = infList17.get(i).getBook_title();
            rows17[i][1] = infList17.get(i).getAuthor();

            // rows12[i][3] = infList12.get(i).getQuantity();
            rows17[i][2] = infList17.get(i).getBorrow_date();
            rows17[i][3] = infList17.get(i).getReturn_date();
            // rows12[i][6] = infList12.get(i).getStudent_id();
            rows17[i][4] = infList17.get(i).getLrn();
            //rows12[i][7] = infList12.get(i).getUsername();
            rows17[i][5] = infList17.get(i).getName();
            rows17[i][6] = infList17.get(i).getStatus();

        }
        MyModel21 mmd21 = new MyModel21(rows17, colNames17);
        table_lateunreturnedreport.setModel(mmd21);
        table_lateunreturnedreport.setRowHeight(30);

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

    //Clicked
    private void tab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseClicked
        mainTab.setSelectedIndex(0);
        totalBooks();
        
        refreshFields();
    }//GEN-LAST:event_tab1MouseClicked

    private void tab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseClicked
        mainTab.setSelectedIndex(1);
        refreshFields();

    }//GEN-LAST:event_tab2MouseClicked

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked
        mainTab.setSelectedIndex(2);
        refreshFields();
        //refreshJtable();
    }//GEN-LAST:event_tab3MouseClicked

    private void tab4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseClicked
        mainTab.setSelectedIndex(3);
        refreshFields();
    }//GEN-LAST:event_tab4MouseClicked

    private void tab5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseClicked
        mainTab.setSelectedIndex(4);
        refreshFields();
    }//GEN-LAST:event_tab5MouseClicked

    private void tab6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseClicked
        mainTab.setSelectedIndex(5);
        refreshFields();
    }//GEN-LAST:event_tab6MouseClicked

    //Hover
    private void tab1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseEntered
        tab1.setBackground(new Color(64, 134, 176));
        tab2.setBackground(new Color(149, 189, 219));
        tab3.setBackground(new Color(149, 189, 219));
        tab4.setBackground(new Color(149, 189, 219));
        tab5.setBackground(new Color(149, 189, 219));
        tab6.setBackground(new Color(149, 189, 219));
        tab7.setBackground(new Color(149, 189, 219));
        tab8.setBackground(new Color(149, 189, 219));
        jLabel2.setForeground(new Color(0, 0, 0));
        jLabel2.setFont(new Font("Agency FB", Font.BOLD, 22));
    }//GEN-LAST:event_tab1MouseEntered

    private void tab1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseExited
        tab1.setBackground(new Color(149, 189, 219));
        jLabel2.setForeground(new Color(0, 0, 0));
        jLabel2.setFont(new Font("Agency FB", Font.PLAIN, 22));
    }//GEN-LAST:event_tab1MouseExited

    private void tab2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseEntered
        tab2.setBackground(new Color(64, 134, 176));
        tab1.setBackground(new Color(149, 189, 219));
        tab3.setBackground(new Color(149, 189, 219));
        tab4.setBackground(new Color(149, 189, 219));
        tab5.setBackground(new Color(149, 189, 219));
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
        tab5.setBackground(new Color(149, 189, 219));
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
        tab5.setBackground(new Color(149, 189, 219));
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

    private void tab5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseEntered
        tab5.setBackground(new Color(64, 134, 176));
        tab1.setBackground(new Color(149, 189, 219));
        tab2.setBackground(new Color(149, 189, 219));
        tab3.setBackground(new Color(149, 189, 219));
        tab4.setBackground(new Color(149, 189, 219));
        tab6.setBackground(new Color(149, 189, 219));
        tab7.setBackground(new Color(149, 189, 219));
        tab8.setBackground(new Color(149, 189, 219));
        jLabel6.setForeground(new Color(0, 0, 0));
        jLabel6.setFont(new Font("Agency FB", Font.BOLD, 22));
    }//GEN-LAST:event_tab5MouseEntered

    private void tab5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseExited
        tab5.setBackground(new Color(149, 189, 219));
        jLabel6.setForeground(new Color(0, 0, 0));
        jLabel6.setFont(new Font("Agency FB", Font.PLAIN, 22));
    }//GEN-LAST:event_tab5MouseExited

    private void tab6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseEntered
        tab6.setBackground(new Color(64, 134, 176));
        tab1.setBackground(new Color(149, 189, 219));
        tab2.setBackground(new Color(149, 189, 219));
        tab3.setBackground(new Color(149, 189, 219));
        tab4.setBackground(new Color(149, 189, 219));
        tab5.setBackground(new Color(149, 189, 219));
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
        this.setExtendedState(LibrarianHome.ICONIFIED);
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
        this.setExtendedState(LibrarianHome.ICONIFIED);
    }//GEN-LAST:event_minFrameMouseClicked

    //Border
    private void tab1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MousePressed
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 2);
        tab1.setBorder(b);
    }//GEN-LAST:event_tab1MousePressed

    private void tab1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseReleased
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 0);
        tab1.setBorder(b);
    }//GEN-LAST:event_tab1MouseReleased

    private void tab2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MousePressed
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 2);
        tab2.setBorder(b);
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

    private void tab5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MousePressed
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 2);
        tab5.setBorder(b);
    }//GEN-LAST:event_tab5MousePressed

    private void tab5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseReleased
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 0);
        tab5.setBorder(b);
    }//GEN-LAST:event_tab5MouseReleased

    private void tab6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MousePressed
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 2);
        tab6.setBorder(b);
    }//GEN-LAST:event_tab6MousePressed

    private void tab6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseReleased
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 0);
        tab6.setBorder(b);
    }//GEN-LAST:event_tab6MouseReleased

    private void tab7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MousePressed
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 2);
        tab7.setBorder(b);
        Border c = BorderFactory.createLineBorder(new Color(8, 14, 112), 1);
        tab9.setBorder(c);
        Border d = BorderFactory.createLineBorder(new Color(8, 14, 112), 0);
        tab10.setBorder(d);
        tab11.setBorder(d);
        tab12.setBorder(d);
        tab13.setBorder(d);
    }//GEN-LAST:event_tab7MousePressed

    private void tab7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MouseReleased
        Border b = BorderFactory.createLineBorder(new Color(149, 189, 219), 0);
        tab7.setBorder(b);
    }//GEN-LAST:event_tab7MouseReleased

    private void tab7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MouseEntered
        tab7.setBackground(new Color(64, 134, 176));
        tab1.setBackground(new Color(149, 189, 219));
        tab2.setBackground(new Color(149, 189, 219));
        tab3.setBackground(new Color(149, 189, 219));
        tab4.setBackground(new Color(149, 189, 219));
        tab5.setBackground(new Color(149, 189, 219));
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

    private void hamburgerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hamburgerMousePressed

    }//GEN-LAST:event_hamburgerMousePressed

    private void hamburgerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hamburgerMouseEntered

    }//GEN-LAST:event_hamburgerMouseEntered

    private void tab7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MouseClicked
        mainTab.setSelectedIndex(6);
        reportsTab.setSelectedIndex(0);
        refreshFields();
        changeTableReturn(table_returnedreport, 6);
        changeTableLate(table_lateunreturnedreport, 6);
    }//GEN-LAST:event_tab7MouseClicked

    private void tab8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab8MouseEntered
        tab8.setBackground(new Color(64, 134, 176));
        tab1.setBackground(new Color(149, 189, 219));
        tab2.setBackground(new Color(149, 189, 219));
        tab3.setBackground(new Color(149, 189, 219));
        tab4.setBackground(new Color(149, 189, 219));
        tab5.setBackground(new Color(149, 189, 219));
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

    private void view1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view1MouseEntered
        view1.setForeground(new Color(8, 14, 112));
    }//GEN-LAST:event_view1MouseEntered

    private void view1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view1MouseExited
        view1.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_view1MouseExited

    private void view2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view2MouseEntered
        view2.setForeground(new Color(8, 14, 112));
    }//GEN-LAST:event_view2MouseEntered

    private void view2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view2MouseExited
        view2.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_view2MouseExited

    private void view3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view3MouseEntered
        view3.setForeground(new Color(8, 14, 112));
    }//GEN-LAST:event_view3MouseEntered

    private void view3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view3MouseExited
        view3.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_view3MouseExited


    private void view5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view5MouseEntered
        view5.setForeground(new Color(8, 14, 112));
    }//GEN-LAST:event_view5MouseEntered

    private void view5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view5MouseExited
        view5.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_view5MouseExited

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

    private void view5ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_view5ComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_view5ComponentMoved

    private void textfieldsearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearch2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearch2ActionPerformed

    private void textfieldusername2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldusername2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldusername2ActionPerformed

    private void TitleofbookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TitleofbookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TitleofbookActionPerformed

    private void view1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view1MouseClicked
        mainTab.setSelectedIndex(6);
        reportsTab.setSelectedIndex(0);
    }//GEN-LAST:event_view1MouseClicked

    private void tab10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab10MouseClicked
        reportsTab.setSelectedIndex(1);
        refreshFields();
    }//GEN-LAST:event_tab10MouseClicked

    private void tab9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab9MouseClicked
        reportsTab.setSelectedIndex(0);
        refreshFields();

    }//GEN-LAST:event_tab9MouseClicked

    private void tab11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab11MouseClicked
        reportsTab.setSelectedIndex(2);
        refreshFields();
        changeTableReturn(table_returnedreport, 6);
    }//GEN-LAST:event_tab11MouseClicked

    private void tab12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab12MouseClicked
        reportsTab.setSelectedIndex(3);
        refreshFields();
    }//GEN-LAST:event_tab12MouseClicked

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

    private void tab11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab11MouseEntered
        tab11.setBackground(new Color(64, 134, 176));
        label3.setForeground(new Color(255, 255, 255));
        /*tab9.setLocation(0,11);
        tab10.setLocation(150,11);
        tab11.setLocation(300,5);
        tab12.setLocation(450,11);*/
    }//GEN-LAST:event_tab11MouseEntered

    private void tab11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab11MouseExited
        tab11.setBackground(new Color(149, 189, 219));
        label3.setForeground(new Color(0, 0, 0));
        //tab11.setLocation(300,11);
    }//GEN-LAST:event_tab11MouseExited

    private void tab12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab12MouseEntered
        tab12.setBackground(new Color(64, 134, 176));
        label4.setForeground(new Color(255, 255, 255));
        /*tab9.setLocation(0,11);
        tab10.setLocation(150,11);
        tab11.setLocation(300,11);
        tab12.setLocation(450,5);*/
    }//GEN-LAST:event_tab12MouseEntered

    private void tab12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab12MouseExited
        tab12.setBackground(new Color(149, 189, 219));
        label4.setForeground(new Color(0, 0, 0));
        //tab12.setLocation(450,11);
    }//GEN-LAST:event_tab12MouseExited

    private void view2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view2MouseClicked
        mainTab.setSelectedIndex(6);
        reportsTab.setSelectedIndex(1);
    }//GEN-LAST:event_view2MouseClicked

    private void view3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view3MouseClicked
        mainTab.setSelectedIndex(6);
        reportsTab.setSelectedIndex(2);
    }//GEN-LAST:event_view3MouseClicked

    private void view5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view5MouseClicked
        mainTab.setSelectedIndex(6);
        reportsTab.setSelectedIndex(3);
    }//GEN-LAST:event_view5MouseClicked

    private void tab9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab9MousePressed
        Border b = BorderFactory.createLineBorder(new Color(8, 14, 112), 1);
        tab9.setBorder(b);
        Border c = BorderFactory.createLineBorder(new Color(0, 0, 0), 0);
        tab10.setBorder(c);
        tab11.setBorder(c);
        tab12.setBorder(c);
        tab13.setBorder(c);
    }//GEN-LAST:event_tab9MousePressed

    private void tab10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab10MousePressed
        Border b = BorderFactory.createLineBorder(new Color(8, 14, 112), 1);
        tab10.setBorder(b);
        Border c = BorderFactory.createLineBorder(new Color(0, 0, 0), 0);
        tab9.setBorder(c);
        tab11.setBorder(c);
        tab12.setBorder(c);
        tab13.setBorder(c);
    }//GEN-LAST:event_tab10MousePressed

    private void tab11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab11MousePressed
        Border b = BorderFactory.createLineBorder(new Color(8, 14, 112), 1);
        tab11.setBorder(b);
        Border c = BorderFactory.createLineBorder(new Color(0, 0, 0), 0);
        tab9.setBorder(c);
        tab10.setBorder(c);
        tab12.setBorder(c);
        tab13.setBorder(c);
    }//GEN-LAST:event_tab11MousePressed

    private void tab12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab12MousePressed
        Border b = BorderFactory.createLineBorder(new Color(8, 14, 112), 1);
        tab12.setBorder(b);
        Border c = BorderFactory.createLineBorder(new Color(0, 0, 0), 0);
        tab9.setBorder(c);
        tab10.setBorder(c);
        tab11.setBorder(c);
        tab13.setBorder(c);
    }//GEN-LAST:event_tab12MousePressed

    private void tab9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab9MouseReleased
    }//GEN-LAST:event_tab9MouseReleased

    private void textfieldsearchmathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearchmathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearchmathActionPerformed

    private void exportmath_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportmath_btnActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("MATH");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table_math.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table_math.getColumnName(i));
                }

                for (int j = 0; j < table_math.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < table_math.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table_math.getValueAt(j, k) != null) {
                            cell.setCellValue(table_math.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_exportmath_btnActionPerformed

    private void textfieldsearchengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearchengActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearchengActionPerformed

    private void exporteng_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exporteng_btnActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("ENGLISH");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table_eng.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table_eng.getColumnName(i));
                }

                for (int j = 0; j < table_eng.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < table_eng.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table_eng.getValueAt(j, k) != null) {
                            cell.setCellValue(table_eng.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_exporteng_btnActionPerformed

    private void textfieldsearchsciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearchsciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearchsciActionPerformed

    private void exportsci_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportsci_btnActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("SCIENCE");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table_sci.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table_sci.getColumnName(i));
                }

                for (int j = 0; j < table_sci.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < table_sci.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table_sci.getValueAt(j, k) != null) {
                            cell.setCellValue(table_sci.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_exportsci_btnActionPerformed

    private void textfieldsearchfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearchfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearchfilActionPerformed

    private void exportfil_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportfil_btnActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("FILIPINO");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table_fil.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table_fil.getColumnName(i));
                }

                for (int j = 0; j < table_fil.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < table_fil.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table_fil.getValueAt(j, k) != null) {
                            cell.setCellValue(table_fil.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_exportfil_btnActionPerformed

    private void textfieldsearchmapehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearchmapehActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearchmapehActionPerformed

    private void exportmapeh_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportmapeh_btnActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("MAPEH");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table_mapeh.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table_mapeh.getColumnName(i));
                }

                for (int j = 0; j < table_mapeh.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < table_mapeh.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table_mapeh.getValueAt(j, k) != null) {
                            cell.setCellValue(table_mapeh.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_exportmapeh_btnActionPerformed

    private void textfieldsearchtleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearchtleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearchtleActionPerformed

    private void exporttle_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exporttle_btnActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("TLE");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table_tle.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table_tle.getColumnName(i));
                }

                for (int j = 0; j < table_tle.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < table_tle.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table_tle.getValueAt(j, k) != null) {
                            cell.setCellValue(table_tle.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_exporttle_btnActionPerformed

    private void textfieldsearchespActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearchespActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearchespActionPerformed

    private void exportesp_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportesp_btnActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("ESP");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table_esp.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table_esp.getColumnName(i));
                }

                for (int j = 0; j < table_esp.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < table_esp.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table_esp.getValueAt(j, k) != null) {
                            cell.setCellValue(table_esp.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_exportesp_btnActionPerformed

    private void textfieldsearchapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearchapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearchapActionPerformed

    private void exportap_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportap_btnActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("AP");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table_ap.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table_ap.getColumnName(i));
                }

                for (int j = 0; j < table_ap.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < table_ap.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table_ap.getValueAt(j, k) != null) {
                            cell.setCellValue(table_ap.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_exportap_btnActionPerformed

    private void textfieldsearch4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearch4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearch4ActionPerformed

    private void exportexcel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportexcel_btnActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("BOOKS");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table_bookreport.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table_bookreport.getColumnName(i));
                }

                for (int j = 0; j < table_bookreport.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < table_bookreport.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table_bookreport.getValueAt(j, k) != null) {
                            cell.setCellValue(table_bookreport.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_exportexcel_btnActionPerformed

    private void textfieldsearch5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearch5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearch5ActionPerformed

    private void exportexcel2_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportexcel2_btnActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("BORROWED BOOKS");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table_borrowedreport.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table_borrowedreport.getColumnName(i));
                }

                for (int j = 0; j < table_borrowedreport.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < table_borrowedreport.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table_borrowedreport.getValueAt(j, k) != null) {
                            cell.setCellValue(table_borrowedreport.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_exportexcel2_btnActionPerformed

    private void textfieldsearch6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearch6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearch6ActionPerformed

    private void exportexcel3_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportexcel3_btnActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("RETURNED BOOKS");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table_returnedreport.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table_returnedreport.getColumnName(i));
                }

                for (int j = 0; j < table_returnedreport.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < table_returnedreport.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table_returnedreport.getValueAt(j, k) != null) {
                            cell.setCellValue(table_returnedreport.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_exportexcel3_btnActionPerformed

    private void textfieldsearch7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearch7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearch7ActionPerformed

    private void exportexcel4_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportexcel4_btnActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("STUDENTS");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table_studentreport.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table_studentreport.getColumnName(i));
                }

                for (int j = 0; j < table_studentreport.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < table_studentreport.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table_studentreport.getValueAt(j, k) != null) {
                            cell.setCellValue(table_studentreport.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_exportexcel4_btnActionPerformed

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
        } else if (result == JOptionPane.NO_OPTION) {

        }
    }//GEN-LAST:event_tab8MouseClicked

    private void textfieldsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearchKeyReleased
        MyModel1 table = (MyModel1) table_books.getModel();
        String search = textfieldsearch.getText();
        TableRowSorter<MyModel1> tr = new TableRowSorter<>(table);
        table_books.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));

    }//GEN-LAST:event_textfieldsearchKeyReleased

    private void table_booksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_booksMouseClicked
        int rowIndex = table_books.getSelectedRow();
        textfieldbookid.setText(table_books.getValueAt(rowIndex, 0).toString());
        textfieldtitle.setText(table_books.getValueAt(rowIndex, 1).toString());
        textfieldauthor.setText(table_books.getValueAt(rowIndex, 2).toString());
        comboboxtype.setSelectedItem(table_books.getValueAt(rowIndex, 3));
        comboboxsubject.setSelectedItem(table_books.getValueAt(rowIndex, 4));
        textfieldquantity.setText(table_books.getValueAt(rowIndex, 5).toString());
    }//GEN-LAST:event_table_booksMouseClicked

    private void table_bookreportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_bookreportMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_bookreportMouseClicked

    private void printreport_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printreport_btnActionPerformed

        /* try{
          
           boolean print = table_bookreport.print();
           if (!print){
              JOptionPane.showMessageDialog(null, "Unable to print");
           }
       }
       catch(PrinterException e){
              JOptionPane.showMessageDialog(null, e.getMessage());
               }
      
        try{
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\report1.jrxml");
            String sql = "SELECT `book_id`, `book_title`, `author`, `type`, `subject`, `quantity` FROM `books`";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport js = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(js, null, con);
       
            JasperViewer.viewReport(jp);
       }catch(Exception e){
           JOptionPane.showMessageDialog(rootPane, e);
       }
        try{
            DefaultTableModel tableModel = (DefaultTableModel)table_bookreport.getModel();
            HashMap<String, Object> para = new HashMap<>();
            para.put("tittle", "");
            
            JasperPrint jp = null;
            JasperCompileManager.compileReportToFile("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\report2.jrxml");
            jp = JasperFillManager.fillReport("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\report2.jasper", para, new JRTableModelDataSource(tableModel));
            JasperViewer.viewReport(jp, false);
         }catch(Exception e){
           JOptionPane.showMessageDialog(rootPane, e);
       }    
       try{
            InputStream in = new FileInputStream(new File("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\report2.jrxml"));
            JasperDesign jd = JRXmlLoader.load(in);
            String sql = "SELECT * FROM books";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr =JasperCompileManager.compileReport(jd);
            HashMap para = new HashMap();
            JasperPrint j = JasperFillManager.fillReport(jr,para,con);
            JasperViewer.viewReport(j, false);
            OutputStream os = new FileOutputStream(new File("C:\\Users\\63915\\Documents\\ZIP FILE")); 
            JasperExportManager.exportReportToPdfStream(j, os);
            
         }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       } */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\BookReport.jrxml");
            String sql = "SELECT * FROM books ORDER BY book_title ASC";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }//GEN-LAST:event_printreport_btnActionPerformed

    private void table() {
        try {
            String sql = "SELECT `book_id`, `book_title`, `author`, `type`, `subject`, `quantity` FROM `books`";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            table_bookreport.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }


    private void table_returnedreportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_returnedreportMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_returnedreportMouseClicked

    private void table_studentreportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_studentreportMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_studentreportMouseClicked

    private void table_mathMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_mathMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_mathMouseClicked

    private void table_engMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_engMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_engMouseClicked

    private void table_sciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_sciMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_sciMouseClicked

    private void table_filMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_filMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_filMouseClicked

    private void table_mapehMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_mapehMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_mapehMouseClicked

    private void table_tleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_tleMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_tleMouseClicked

    private void table_espMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_espMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_espMouseClicked

    private void table_apMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_apMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_apMouseClicked

    private void table_studentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_studentsMouseClicked
        int rowIndex = table_students.getSelectedRow();
        textfieldlrn2.setText(table_students.getValueAt(rowIndex, 0).toString());
        textfieldfirstname2.setText(table_students.getValueAt(rowIndex, 1).toString());
        textfieldlastname2.setText(table_students.getValueAt(rowIndex, 2).toString());
        textfieldusername2.setText(table_students.getValueAt(rowIndex, 3).toString());
        textfieldtotalborrow.setText(table_students.getValueAt(rowIndex, 4).toString());
    }//GEN-LAST:event_table_studentsMouseClicked

    private void table_borrowedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_borrowedMouseClicked
        int rowIndex = table_borrowed.getSelectedRow();
        textfieldtitle2.setText(table_borrowed.getValueAt(rowIndex, 0).toString());
        textfieldauthor2.setText(table_borrowed.getValueAt(rowIndex, 1).toString());
        //textfieldquantity2.setText(table_borrowed.getValueAt(rowIndex, 3).toString());
        textfieldborrowdate.setText(table_borrowed.getValueAt(rowIndex, 2).toString());
        textfieldduedate.setText(table_borrowed.getValueAt(rowIndex, 3).toString());
        textfieldlrn.setText(table_borrowed.getValueAt(rowIndex, 4).toString());
        textfieldname.setText(table_borrowed.getValueAt(rowIndex, 5).toString());
    }//GEN-LAST:event_table_borrowedMouseClicked

    private void textfieldsearch2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearch2KeyReleased
        MyModel15 table = (MyModel15) table_borrowed.getModel();
        String search = textfieldsearch2.getText();
        TableRowSorter<MyModel15> tr = new TableRowSorter<>(table);
        table_borrowed.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearch2KeyReleased

    private void textfieldsearch3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearch3KeyReleased
        MyModel12 table = (MyModel12) table_students.getModel();
        String search = textfieldsearch3.getText();
        TableRowSorter<MyModel12> tr = new TableRowSorter<>(table);
        table_students.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearch3KeyReleased

    private void textfieldsearch4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearch4KeyReleased
        MyModel2 table = (MyModel2) table_bookreport.getModel();
        String search = textfieldsearch4.getText();
        TableRowSorter<MyModel2> tr = new TableRowSorter<>(table);
        table_bookreport.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearch4KeyReleased

    private void textfieldsearch5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearch5KeyReleased
        MyModel13 table = (MyModel13) table_borrowedreport.getModel();
        String search = textfieldsearch5.getText();
        TableRowSorter<MyModel13> tr = new TableRowSorter<>(table);
        table_borrowedreport.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearch5KeyReleased

    private void textfieldsearch7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearch7KeyReleased
        MyModel14 table = (MyModel14) table_studentreport.getModel();
        String search = textfieldsearch7.getText();
        TableRowSorter<MyModel14> tr = new TableRowSorter<>(table);
        table_studentreport.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearch7KeyReleased

    private void textfieldsearchmathKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearchmathKeyReleased
        MyModel4 table = (MyModel4) table_math.getModel();
        String search = textfieldsearchmath.getText();
        TableRowSorter<MyModel4> tr = new TableRowSorter<>(table);
        table_math.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearchmathKeyReleased

    private void textfieldsearchengKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearchengKeyReleased
        MyModel5 table = (MyModel5) table_eng.getModel();
        String search = textfieldsearcheng.getText();
        TableRowSorter<MyModel5> tr = new TableRowSorter<>(table);
        table_eng.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearchengKeyReleased

    private void textfieldsearchsciKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearchsciKeyReleased
        MyModel6 table = (MyModel6) table_sci.getModel();
        String search = textfieldsearchsci.getText();
        TableRowSorter<MyModel6> tr = new TableRowSorter<>(table);
        table_sci.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearchsciKeyReleased

    private void textfieldsearchfilKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearchfilKeyReleased
        MyModel7 table = (MyModel7) table_fil.getModel();
        String search = textfieldsearchfil.getText();
        TableRowSorter<MyModel7> tr = new TableRowSorter<>(table);
        table_fil.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearchfilKeyReleased

    private void textfieldsearchmapehKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearchmapehKeyReleased
        MyModel8 table = (MyModel8) table_mapeh.getModel();
        String search = textfieldsearchmapeh.getText();
        TableRowSorter<MyModel8> tr = new TableRowSorter<>(table);
        table_mapeh.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearchmapehKeyReleased

    private void textfieldsearchtleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearchtleKeyReleased
        MyModel9 table = (MyModel9) table_tle.getModel();
        String search = textfieldsearchtle.getText();
        TableRowSorter<MyModel9> tr = new TableRowSorter<>(table);
        table_tle.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearchtleKeyReleased

    private void textfieldsearchespKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearchespKeyReleased
        MyModel10 table = (MyModel10) table_esp.getModel();
        String search = textfieldsearchesp.getText();
        TableRowSorter<MyModel10> tr = new TableRowSorter<>(table);
        table_esp.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearchespKeyReleased

    private void textfieldsearchapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearchapKeyReleased
        MyModel11 table = (MyModel11) table_ap.getModel();
        String search = textfieldsearchap.getText();
        TableRowSorter<MyModel11> tr = new TableRowSorter<>(table);
        table_ap.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearchapKeyReleased

    private void textfieldsearch6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearch6KeyReleased
        MyModel18 table = (MyModel18) table_returnedreport.getModel();
        String search = textfieldsearch6.getText();
        TableRowSorter<MyModel18> tr = new TableRowSorter<>(table);
        table_returnedreport.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearch6KeyReleased

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

    private void printreport2_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printreport2_btnActionPerformed
        /*   try{
          
           boolean print = table_borrowedreport.print();
           if (!print){
              JOptionPane.showMessageDialog(null, "Unable to print");
           }
       }
       catch(PrinterException e){
              JOptionPane.showMessageDialog(null, e.getMessage());
               }*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\BorrowedReport.jrxml");
            String sql = "SELECT borrowed_books.borrowed_id, borrowed_books.book_title, borrowed_books.author, borrowed_books.type,borrowed_books.subject, borrowed_books.borrow_date, borrowed_books.return_date, registration.lrn, CONCAT( registration.first_name, \" \", registration.last_name ) AS name FROM borrowed_books INNER JOIN registration ON borrowed_books.student_id= registration.ID ORDER BY DATE_FORMAT(borrow_date,'%Y-%m-%d') DESC";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }//GEN-LAST:event_printreport2_btnActionPerformed

    private void printreport3_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printreport3_btnActionPerformed
        /*try{
          
           boolean print = table_returnedreport.print();
           if (!print){
              JOptionPane.showMessageDialog(null, "Unable to print");
           }
       }
       catch(PrinterException e){
              JOptionPane.showMessageDialog(null, e.getMessage());
               }*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk_1\\src\\librarymanagementkiosk\\ReturnedReport.jrxml");
            String sql = "SELECT returned_books.returned_id, returned_books.book_title, returned_books.author, returned_books.type,returned_books.subject, returned_books.due_date, returned_books.return_date, registration.lrn, CONCAT(registration.first_name,' ',registration.last_name) AS name, (CASE WHEN datediff(due_date,CURDATE()) >= 0 then ' ' ELSE 'LATE' END) as status FROM returned_books INNER JOIN registration ON returned_books.student_id= registration.ID ORDER BY DATE_FORMAT(return_date,'%Y-%m-%d') DESC";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }//GEN-LAST:event_printreport3_btnActionPerformed

    private void printreport4_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printreport4_btnActionPerformed
        /*         try{
          
           boolean print = table_studentreport.print();
           if (!print){
              JOptionPane.showMessageDialog(null, "Unable to print");
           }
       }
       catch(PrinterException e){
              JOptionPane.showMessageDialog(null, e.getMessage());
               }*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\StudentReport.jrxml");
            String sql = "SELECT ID, lrn, first_name, last_name, username, password FROM registration";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_printreport4_btnActionPerformed

    private void printmath_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printmath_btnActionPerformed
        /* try{
          
           boolean print = table_math.print();
           if (!print){
              JOptionPane.showMessageDialog(null, "Unable to print");
           }
       }
       catch(PrinterException e){
              JOptionPane.showMessageDialog(null, e.getMessage());
               }*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\MathReport.jrxml");
            String sql = "SELECT * FROM books WHERE subject = 'math'";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_printmath_btnActionPerformed

    private void printeng_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printeng_btnActionPerformed
        /* try{
          
           boolean print = table_eng.print();
           if (!print){
              JOptionPane.showMessageDialog(null, "Unable to print");
           }
       }
       catch(PrinterException e){
              JOptionPane.showMessageDialog(null, e.getMessage());
               }*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\EngReport.jrxml");
            String sql = "SELECT * FROM books WHERE subject = 'english'";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_printeng_btnActionPerformed

    private void printsci_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printsci_btnActionPerformed
        /* try{
          
           boolean print = table_sci.print();
           if (!print){
              JOptionPane.showMessageDialog(null, "Unable to print");
           }
       }
       catch(PrinterException e){
              JOptionPane.showMessageDialog(null, e.getMessage());
               }*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\SciReport.jrxml");
            String sql = "SELECT * FROM books WHERE subject = 'science'";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_printsci_btnActionPerformed

    private void printfil_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printfil_btnActionPerformed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\FilReport.jrxml");
            String sql = "SELECT * FROM books WHERE subject = 'filipino'";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_printfil_btnActionPerformed

    private void printmapeh_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printmapeh_btnActionPerformed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\MapehReport.jrxml");
            String sql = "SELECT * FROM books WHERE subject = 'mapeh'";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_printmapeh_btnActionPerformed

    private void printtle_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printtle_btnActionPerformed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\TleReport.jrxml");
            String sql = "SELECT * FROM books WHERE subject = 'tle'";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_printtle_btnActionPerformed

    private void printesp_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printesp_btnActionPerformed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\EspReport.jrxml");
            String sql = "SELECT * FROM books WHERE subject = 'esp'";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_printesp_btnActionPerformed

    private void printap_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printap_btnActionPerformed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\src\\librarymanagementkiosk\\ApReport.jrxml");
            String sql = "SELECT * FROM books WHERE subject = 'ap'";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_printap_btnActionPerformed

    private void comboboxtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxtypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboboxtypeActionPerformed

    private void textfieldfirstnameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldfirstnameMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldfirstname.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        textfieldlastname.setBorder(b);
        textfieldusername.setBorder(b);
        jPasswordField1.setBorder(b);
        jPasswordField2.setBorder(b);
    }//GEN-LAST:event_textfieldfirstnameMousePressed

    private void textfieldlastnameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldlastnameMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldlastnameMouseEntered

    private void textfieldlastnameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldlastnameMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldlastname.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        textfieldfirstname.setBorder(b);
        textfieldusername.setBorder(b);
        jPasswordField1.setBorder(b);
        jPasswordField2.setBorder(b);
    }//GEN-LAST:event_textfieldlastnameMousePressed

    private void textfieldusernameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldusernameMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldusername.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        textfieldfirstname.setBorder(b);
        textfieldlastname.setBorder(b);
        jPasswordField1.setBorder(b);
        jPasswordField2.setBorder(b);
    }//GEN-LAST:event_textfieldusernameMousePressed

    private void jPasswordField1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        jPasswordField1.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        textfieldfirstname.setBorder(b);
        textfieldlastname.setBorder(b);
        textfieldusername.setBorder(b);
        jPasswordField2.setBorder(b);
    }//GEN-LAST:event_jPasswordField1MousePressed

    private void jPasswordField2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField2MousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        jPasswordField2.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        textfieldfirstname.setBorder(b);
        textfieldlastname.setBorder(b);
        textfieldusername.setBorder(b);
        jPasswordField1.setBorder(b);
    }//GEN-LAST:event_jPasswordField2MousePressed

    private void textfieldtitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldtitleMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldtitle.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        //textfieldbookid.setBorder(b);
        textfieldauthor.setBorder(b);
        comboboxtype.setBorder(b);
        comboboxsubject.setBorder(b);
        textfieldquantity.setBorder(b);
        textfieldsearch.setBorder(b);
    }//GEN-LAST:event_textfieldtitleMousePressed

    private void textfieldauthorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldauthorMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldauthor.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        //textfieldbookid.setBorder(b);
        textfieldtitle.setBorder(b);
        comboboxtype.setBorder(b);
        comboboxsubject.setBorder(b);
        textfieldquantity.setBorder(b);
        textfieldsearch.setBorder(b);
    }//GEN-LAST:event_textfieldauthorMousePressed

    private void comboboxtypeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboboxtypeMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        comboboxtype.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        //textfieldbookid.setBorder(b);
        textfieldtitle.setBorder(b);
        textfieldauthor.setBorder(b);
        comboboxsubject.setBorder(b);
        textfieldquantity.setBorder(b);
        textfieldsearch.setBorder(b);
    }//GEN-LAST:event_comboboxtypeMousePressed

    private void comboboxsubjectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboboxsubjectMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        comboboxsubject.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        //textfieldbookid.setBorder(b);
        textfieldtitle.setBorder(b);
        textfieldauthor.setBorder(b);
        comboboxtype.setBorder(b);
        textfieldquantity.setBorder(b);
        textfieldsearch.setBorder(b);
    }//GEN-LAST:event_comboboxsubjectMousePressed

    private void textfieldquantityMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldquantityMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldquantity.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        //textfieldbookid.setBorder(b);
        textfieldtitle.setBorder(b);
        textfieldauthor.setBorder(b);
        comboboxtype.setBorder(b);
        comboboxsubject.setBorder(b);
        textfieldsearch.setBorder(b);
    }//GEN-LAST:event_textfieldquantityMousePressed

    private void textfieldsearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearchMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearch.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        //textfieldbookid.setBorder(b);
        textfieldtitle.setBorder(b);
        textfieldauthor.setBorder(b);
        comboboxtype.setBorder(b);
        comboboxsubject.setBorder(b);
        textfieldquantity.setBorder(b);

    }//GEN-LAST:event_textfieldsearchMousePressed

    private void textfieldsearch2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearch2MousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearch2.setBorder(a);
    }//GEN-LAST:event_textfieldsearch2MousePressed

    private void textfieldsearch3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearch3MousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearch2.setBorder(a);
    }//GEN-LAST:event_textfieldsearch3MousePressed

    private void textfieldsearch3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearch3MouseReleased

    }//GEN-LAST:event_textfieldsearch3MouseReleased

    private void textfieldsearch2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearch2MouseReleased

    }//GEN-LAST:event_textfieldsearch2MouseReleased

    private void TitleofbookMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TitleofbookMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        Titleofbook.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        copies.setBorder(b);
    }//GEN-LAST:event_TitleofbookMousePressed

    private void copiesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_copiesMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        copies.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        Titleofbook.setBorder(b);
    }//GEN-LAST:event_copiesMousePressed

    private void textfieldsearch4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearch4MousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearch4.setBorder(a);
    }//GEN-LAST:event_textfieldsearch4MousePressed

    private void textfieldsearch5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearch5MousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearch5.setBorder(a);
    }//GEN-LAST:event_textfieldsearch5MousePressed

    private void textfieldsearch6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearch6MousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearch6.setBorder(a);
    }//GEN-LAST:event_textfieldsearch6MousePressed

    private void textfieldsearch7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearch7MousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearch7.setBorder(a);
    }//GEN-LAST:event_textfieldsearch7MousePressed

    private void textfieldsearchmathMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearchmathMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearchmath.setBorder(a);
    }//GEN-LAST:event_textfieldsearchmathMousePressed

    private void textfieldsearchengMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearchengMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearcheng.setBorder(a);
    }//GEN-LAST:event_textfieldsearchengMousePressed

    private void textfieldsearchsciMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearchsciMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearchsci.setBorder(a);
    }//GEN-LAST:event_textfieldsearchsciMousePressed

    private void textfieldsearchfilMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearchfilMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearchfil.setBorder(a);
    }//GEN-LAST:event_textfieldsearchfilMousePressed

    private void textfieldsearchmapehMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearchmapehMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearchmapeh.setBorder(a);
    }//GEN-LAST:event_textfieldsearchmapehMousePressed

    private void textfieldsearchtleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearchtleMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearchtle.setBorder(a);
    }//GEN-LAST:event_textfieldsearchtleMousePressed

    private void textfieldsearchespMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearchespMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearchesp.setBorder(a);
    }//GEN-LAST:event_textfieldsearchespMousePressed

    private void textfieldsearchapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearchapMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        textfieldsearchap.setBorder(a);
    }//GEN-LAST:event_textfieldsearchapMousePressed

    private void importbooks_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importbooks_btnActionPerformed
        /*ImportExcel ie = new ImportExcel();
      ie.setVisible(true);
      ie.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);*/

        mainTab.setSelectedIndex(16);
    }//GEN-LAST:event_importbooks_btnActionPerformed

    private void importexcelfile_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importexcelfile_btnActionPerformed
        DefaultTableModel ImportExcelModel = (DefaultTableModel) table_importexcel.getModel();
        ImportExcelModel.setNumRows(0);

        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportWorkbook = null;

        String currentDirectoryPath = "C:\\Users\\63915\\Documents";

        JFileChooser excelFileChooserImport = new JFileChooser(currentDirectoryPath);
        FileNameExtensionFilter excelFileNameExtensionFilter = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooserImport.setFileFilter(excelFileNameExtensionFilter);
        int excelChooser = excelFileChooserImport.showOpenDialog(null);

        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                File excelFile = excelFileChooserImport.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelImportWorkbook = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelImportWorkbook.getSheetAt(0);

                for (int row = 1; row < excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);

                    XSSFCell excelId = excelRow.getCell(0);
                    XSSFCell excelTitle = excelRow.getCell(1);
                    XSSFCell excelAuthor = excelRow.getCell(2);
                    XSSFCell excelType = excelRow.getCell(3);
                    XSSFCell excelSubject = excelRow.getCell(4);
                    XSSFCell excelQuantity = excelRow.getCell(5);
                    System.out.println(excelId);
                    System.out.println(excelTitle);
                    System.out.println(excelAuthor);
                    System.out.println(excelType);
                    System.out.println(excelSubject);
                    System.out.println(excelQuantity);

                    ImportExcelModel.addRow(new Object[]{excelId, excelTitle, excelAuthor, excelType, excelSubject, excelQuantity});
                }
                JOptionPane.showMessageDialog(null, "Imported Successfully!");

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ImportExcel.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (IOException ex) {
                Logger.getLogger(ImportExcel.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_importexcelfile_btnActionPerformed

    private void insertdata_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertdata_btnActionPerformed
        String Id, Title, Author, Type, Subject, Quantity;

        DefaultTableModel tblModel = (DefaultTableModel) table_importexcel.getModel();
        if (tblModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Table is empty!");
            
        //}else if (isImportBookExist()){
            //JOptionPane.showMessageDialog(null, "Book Already Exists!", "Message", JOptionPane.INFORMATION_MESSAGE);
        
            /* } else {
            Connection connection = (Connection) myConnection.getConnection();
            PreparedStatement ppst;
            ResultSet rs;
            try {
                ppst = (PreparedStatement) connection.prepareStatement("SELECT * FROM books");
                rs = ppst.executeQuery();
                if (rs.next()) {
                    String b = rs.getString("book_title");
                    String a = rs.getString("author");
                    String t = rs.getString("type");
                    String s = rs.getString("subject");
                    String q = rs.getString("quantity");

                    
                    for (int i = 0; i < tblModel.getRowCount(); i++) {

                        String b1 = table_importexcel.getValueAt(i, 1).toString();
                        String a1 = table_importexcel.getValueAt(i, 2).toString();
                        String t1 = table_importexcel.getValueAt(i, 3).toString();
                        String s1 = table_importexcel.getValueAt(i, 4).toString();
                        String q1 = table_importexcel.getValueAt(i, 5).toString();
                        
                        if (b.equals(b1) && a.equals(a1) && t.equals(t1) && s.equals(s1)) {
                            String sr = "UPDATE books set quantity = '" + q + "' + '" + q1 + "' WHERE book_title = '" + b1 + "' ";
                            ppst = connection.prepareStatement(sr);
                            ppst.execute();
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Data Insert Successfuly!");
                        refreshJtable();
                        mainTab.setSelectedIndex(2);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }*/
            
        } else {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");

                for (int i = 0; i < tblModel.getRowCount(); i++) {
                    //Id = tblModel.getValueAt(i, 0).toString();
                    Title = tblModel.getValueAt(i, 1).toString();
                    Author = tblModel.getValueAt(i, 2).toString();
                    Type = tblModel.getValueAt(i, 3).toString();
                    Subject = tblModel.getValueAt(i, 4).toString();
                    Quantity = tblModel.getValueAt(i, 5).toString();

                    String query = "INSERT INTO books(book_title, author, type, subject, quantity) VALUES (?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, Title);
                    ps.setString(2, Author);
                    ps.setString(3, Type);
                    ps.setString(4, Subject);
                    ps.setString(5, Quantity);

                    ps.execute();

                }
                JOptionPane.showMessageDialog(this, "Data Insert Successfuly!");
                tblModel.setRowCount(0);
                refreshJtable();
                mainTab.setSelectedIndex(2);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        
    }//GEN-LAST:event_insertdata_btnActionPerformed

    private void table_importexcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_importexcelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_importexcelMouseClicked

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
        mainTab.setSelectedIndex(2);
    }//GEN-LAST:event_back_btnActionPerformed

    private void textfieldsearch8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearch8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearch8MousePressed

    private void textfieldsearch8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearch8MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearch8MouseReleased

    private void textfieldsearch8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearch8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearch8ActionPerformed

    private void textfieldsearch8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearch8KeyReleased
        MyModel20 table = (MyModel20) table_borrowedlate.getModel();
        String search = textfieldsearch8.getText();
        TableRowSorter<MyModel20> tr = new TableRowSorter<>(table);
        table_borrowedlate.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textfieldsearch8KeyReleased

    private void table_borrowedlateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_borrowedlateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_borrowedlateMouseClicked

    private void jLabel87MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel87MouseClicked
        mainTab.setSelectedIndex(17);
        //new Notif(null,true).show();
        // Notifications2 n = new Notifications2();
        //n.setVisible(true);


    }//GEN-LAST:event_jLabel87MouseClicked

    private void notificationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationMouseClicked
        mainTab.setSelectedIndex(17);
        refreshJtable();
        changeTableLate(table_borrowedlate, 6);
    }//GEN-LAST:event_notificationMouseClicked

    private void notificationMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationMousePressed
        // Border b = BorderFactory.createLineBorder(new Color(64,134,176), 2);
        //notification.setBorder(b);
    }//GEN-LAST:event_notificationMousePressed

    private void notificationMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationMouseReleased
        //    Border b = BorderFactory.createLineBorder(new Color(64,134,176), 0);
        //  notification.setBorder(b);
    }//GEN-LAST:event_notificationMouseReleased

    private void notificationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationMouseEntered
//        notification.setBackground(new Color(64, 115, 181));
    }//GEN-LAST:event_notificationMouseEntered

    private void notificationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationMouseExited
        //     notification.setBackground(new Color(64,134,176));
    }//GEN-LAST:event_notificationMouseExited

    private void view2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view2MousePressed
        Border b = BorderFactory.createLineBorder(new Color(8, 14, 112), 1);
        tab10.setBorder(b);
        Border c = BorderFactory.createLineBorder(new Color(0, 0, 0), 0);
        tab9.setBorder(c);
        tab11.setBorder(c);
        tab12.setBorder(c);
        tab13.setBorder(c);
    }//GEN-LAST:event_view2MousePressed

    private void view3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view3MousePressed
        Border b = BorderFactory.createLineBorder(new Color(8, 14, 112), 1);
        tab11.setBorder(b);
        Border c = BorderFactory.createLineBorder(new Color(0, 0, 0), 0);
        tab9.setBorder(c);
        tab10.setBorder(c);
        tab12.setBorder(c);
        tab13.setBorder(c);
    }//GEN-LAST:event_view3MousePressed

    private void view1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view1MousePressed
        Border b = BorderFactory.createLineBorder(new Color(8, 14, 112), 1);
        tab9.setBorder(b);
        Border c = BorderFactory.createLineBorder(new Color(0, 0, 0), 0);
        tab10.setBorder(c);
        tab11.setBorder(c);
        tab12.setBorder(c);
        tab13.setBorder(c);
    }//GEN-LAST:event_view1MousePressed

    private void view5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view5MousePressed
        Border b = BorderFactory.createLineBorder(new Color(8, 14, 112), 1);
        tab12.setBorder(b);
        Border c = BorderFactory.createLineBorder(new Color(0, 0, 0), 0);
        tab9.setBorder(c);
        tab10.setBorder(c);
        tab11.setBorder(c);
        tab13.setBorder(c);
    }//GEN-LAST:event_view5MousePressed

    private void textfieldsearch9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldsearch9MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearch9MousePressed

    private void textfieldsearch9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldsearch9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearch9ActionPerformed

    private void textfieldsearch9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldsearch9KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldsearch9KeyReleased

    private void exportexcel5_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportexcel5_btnActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("LATE UNRETURNED BOOKS");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < table_lateunreturnedreport.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(table_lateunreturnedreport.getColumnName(i));
                }

                for (int j = 0; j < table_lateunreturnedreport.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < table_lateunreturnedreport.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (table_lateunreturnedreport.getValueAt(j, k) != null) {
                            cell.setCellValue(table_lateunreturnedreport.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_exportexcel5_btnActionPerformed

    private void printreport5_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printreport5_btnActionPerformed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagementkiosk", "root", "");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk_1\\src\\librarymanagementkiosk\\LateReturnedReport.jrxml");
            String sql = "SELECT borrowed_books.borrowed_id, borrowed_books.book_title, borrowed_books.author, borrowed_books.type,borrowed_books.subject, borrowed_books.borrow_date, borrowed_books.return_date, registration.lrn, CONCAT( registration.first_name, \" \", registration.last_name ) AS name, (CASE WHEN datediff(return_date,CURDATE()) >= 0 then 'PENDING' ELSE 'LATE' END) as status FROM borrowed_books INNER JOIN registration ON borrowed_books.student_id= registration.ID HAVING status = 'LATE' ORDER BY DATE_FORMAT(borrow_date,'%Y-%m-%d') DESC";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_printreport5_btnActionPerformed

    private void table_lateunreturnedreportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_lateunreturnedreportMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_lateunreturnedreportMouseClicked

    private void tab13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab13MouseClicked
        reportsTab.setSelectedIndex(4);
        refreshFields();

        changeTableLate(table_lateunreturnedreport, 6);
    }//GEN-LAST:event_tab13MouseClicked

    private void tab13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab13MouseEntered
        tab13.setBackground(new Color(64, 134, 176));
        label5.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_tab13MouseEntered

    private void tab13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab13MouseExited
        tab13.setBackground(new Color(149, 189, 219));
        label5.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_tab13MouseExited

    private void tab13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab13MousePressed
        Border b = BorderFactory.createLineBorder(new Color(8, 14, 112), 1);
        tab13.setBorder(b);
        Border c = BorderFactory.createLineBorder(new Color(0, 0, 0), 0);
        tab9.setBorder(c);
        tab10.setBorder(c);
        tab11.setBorder(c);
        tab12.setBorder(c);
    }//GEN-LAST:event_tab13MousePressed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        mainTab.setSelectedIndex(1);
        refreshFields();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void btn_homeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_homeMouseEntered

    }//GEN-LAST:event_btn_homeMouseEntered

    private void btn_homeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_homeMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_homeMouseExited

    private void btn_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_homeActionPerformed
        mainTab.setSelectedIndex(15);
    }//GEN-LAST:event_btn_homeActionPerformed

    private void table_homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_homeMouseClicked
        int rowIndex = table_home.getSelectedRow();
        textfieldhomeid.setText(table_home.getValueAt(rowIndex, 0).toString());
        textfieldeventname.setText(table_home.getValueAt(rowIndex, 1).toString());

        Image pic = ((ImageIcon) table_home.getValueAt(rowIndex, 2)).getImage().getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(pic);
        lbl_image.setIcon(img);
    }//GEN-LAST:event_table_homeMouseClicked

    private void btn_browseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_browseMouseEntered

    }//GEN-LAST:event_btn_browseMouseEntered

    private void btn_browseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_browseMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_browseMouseExited

    private void btn_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_browseActionPerformed
        Myfunc mf = new Myfunc();
        imagePth = mf.browseImage(lbl_image);
        System.out.println(imagePth);
    }//GEN-LAST:event_btn_browseActionPerformed

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
                updateQuery = "UPDATE librarianlogin SET first_name=?, last_name=?, username=?, password=? WHERE ID=?";
                ps = (PreparedStatement) con.prepareStatement(updateQuery);
                ps.setString(1, textfieldfirstname.getText().trim());
                ps.setString(2, textfieldlastname.getText().trim());
                ps.setString(3, textfieldusername.getText().trim());
                ps.setString(4, jPasswordField1.getText().trim());
                ps.setInt(5, myConnection.index);

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

    private void btn_generateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_generateMouseEntered

    }//GEN-LAST:event_btn_generateMouseEntered

    private void btn_generateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_generateMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_generateMouseExited

    private void btn_generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generateActionPerformed
        try {

            ByteArrayOutputStream out = QRCode.from(Titleofbook.getText()).to(ImageType.PNG).stream();

            String title = Titleofbook.getText();
            String filePath = "C:\\Users\\63915\\Documents\\NetBeansProjects\\LibraryManagementKiosk\\QR Codes\\";

            FileOutputStream fout = new FileOutputStream(new File(filePath + (title + ".png")));
            fout.write(out.toByteArray());
            fout.flush();

            JPanel panel = new JPanel();
            panel.setSize(125, 125);
            panel.setLocation(15, 11);
            panel.setBackground(Color.white);
            this.add(panel);
            Titleofbook1.setText(" " + Titleofbook.getText());
            ImageIcon icon = (new ImageIcon(filePath + (title + ".png")));
            Image image = icon.getImage(); // transform it 
            Image newimg = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            icon = new ImageIcon(newimg);  // transform it back
            qrcode.setIcon(icon);
            this.pack();
            this.setVisible(true);

            Titleofbook.setText("");

        } catch (Exception e) {
            System.out.println(e);

        }
    }//GEN-LAST:event_btn_generateActionPerformed

    private void btn_printMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_printMouseEntered

    }//GEN-LAST:event_btn_printMouseEntered

    private void btn_printMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_printMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_printMouseExited

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        String value = copies.getValue().toString();
        if (value.equals("1")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("2")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("3")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("4")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("5")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("6")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("7")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("8")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.jLabel11.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("9")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.jLabel11.setIcon(qrcode.getIcon());
            jf.jLabel12.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("10")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.jLabel11.setIcon(qrcode.getIcon());
            jf.jLabel12.setIcon(qrcode.getIcon());
            jf.jLabel13.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("11")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.jLabel11.setIcon(qrcode.getIcon());
            jf.jLabel12.setIcon(qrcode.getIcon());
            jf.jLabel13.setIcon(qrcode.getIcon());
            jf.jLabel14.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("12")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.jLabel11.setIcon(qrcode.getIcon());
            jf.jLabel12.setIcon(qrcode.getIcon());
            jf.jLabel13.setIcon(qrcode.getIcon());
            jf.jLabel14.setIcon(qrcode.getIcon());
            jf.jLabel15.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("13")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.jLabel11.setIcon(qrcode.getIcon());
            jf.jLabel12.setIcon(qrcode.getIcon());
            jf.jLabel13.setIcon(qrcode.getIcon());
            jf.jLabel14.setIcon(qrcode.getIcon());
            jf.jLabel15.setIcon(qrcode.getIcon());
            jf.jLabel16.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("14")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.jLabel11.setIcon(qrcode.getIcon());
            jf.jLabel12.setIcon(qrcode.getIcon());
            jf.jLabel13.setIcon(qrcode.getIcon());
            jf.jLabel14.setIcon(qrcode.getIcon());
            jf.jLabel15.setIcon(qrcode.getIcon());
            jf.jLabel16.setIcon(qrcode.getIcon());
            jf.jLabel17.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("15")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.jLabel11.setIcon(qrcode.getIcon());
            jf.jLabel12.setIcon(qrcode.getIcon());
            jf.jLabel13.setIcon(qrcode.getIcon());
            jf.jLabel14.setIcon(qrcode.getIcon());
            jf.jLabel15.setIcon(qrcode.getIcon());
            jf.jLabel16.setIcon(qrcode.getIcon());
            jf.jLabel17.setIcon(qrcode.getIcon());
            jf.jLabel18.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("16")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.jLabel11.setIcon(qrcode.getIcon());
            jf.jLabel12.setIcon(qrcode.getIcon());
            jf.jLabel13.setIcon(qrcode.getIcon());
            jf.jLabel14.setIcon(qrcode.getIcon());
            jf.jLabel15.setIcon(qrcode.getIcon());
            jf.jLabel16.setIcon(qrcode.getIcon());
            jf.jLabel17.setIcon(qrcode.getIcon());
            jf.jLabel18.setIcon(qrcode.getIcon());
            jf.jLabel19.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("17")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.jLabel11.setIcon(qrcode.getIcon());
            jf.jLabel12.setIcon(qrcode.getIcon());
            jf.jLabel13.setIcon(qrcode.getIcon());
            jf.jLabel14.setIcon(qrcode.getIcon());
            jf.jLabel15.setIcon(qrcode.getIcon());
            jf.jLabel16.setIcon(qrcode.getIcon());
            jf.jLabel17.setIcon(qrcode.getIcon());
            jf.jLabel18.setIcon(qrcode.getIcon());
            jf.jLabel19.setIcon(qrcode.getIcon());
            jf.jLabel20.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("18")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.jLabel11.setIcon(qrcode.getIcon());
            jf.jLabel12.setIcon(qrcode.getIcon());
            jf.jLabel13.setIcon(qrcode.getIcon());
            jf.jLabel14.setIcon(qrcode.getIcon());
            jf.jLabel15.setIcon(qrcode.getIcon());
            jf.jLabel16.setIcon(qrcode.getIcon());
            jf.jLabel17.setIcon(qrcode.getIcon());
            jf.jLabel18.setIcon(qrcode.getIcon());
            jf.jLabel19.setIcon(qrcode.getIcon());
            jf.jLabel20.setIcon(qrcode.getIcon());
            jf.jLabel21.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("19")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.jLabel11.setIcon(qrcode.getIcon());
            jf.jLabel12.setIcon(qrcode.getIcon());
            jf.jLabel13.setIcon(qrcode.getIcon());
            jf.jLabel14.setIcon(qrcode.getIcon());
            jf.jLabel15.setIcon(qrcode.getIcon());
            jf.jLabel16.setIcon(qrcode.getIcon());
            jf.jLabel17.setIcon(qrcode.getIcon());
            jf.jLabel18.setIcon(qrcode.getIcon());
            jf.jLabel19.setIcon(qrcode.getIcon());
            jf.jLabel20.setIcon(qrcode.getIcon());
            jf.jLabel21.setIcon(qrcode.getIcon());
            jf.jLabel22.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        } else if (value.equals("20")) {
            PrintQr jf = new PrintQr();
            jf.jLabel4.setIcon(qrcode.getIcon());
            jf.jLabel5.setIcon(qrcode.getIcon());
            jf.jLabel6.setIcon(qrcode.getIcon());
            jf.jLabel7.setIcon(qrcode.getIcon());
            jf.jLabel8.setIcon(qrcode.getIcon());
            jf.jLabel9.setIcon(qrcode.getIcon());
            jf.jLabel10.setIcon(qrcode.getIcon());
            jf.jLabel11.setIcon(qrcode.getIcon());
            jf.jLabel12.setIcon(qrcode.getIcon());
            jf.jLabel13.setIcon(qrcode.getIcon());
            jf.jLabel14.setIcon(qrcode.getIcon());
            jf.jLabel15.setIcon(qrcode.getIcon());
            jf.jLabel16.setIcon(qrcode.getIcon());
            jf.jLabel17.setIcon(qrcode.getIcon());
            jf.jLabel18.setIcon(qrcode.getIcon());
            jf.jLabel19.setIcon(qrcode.getIcon());
            jf.jLabel20.setIcon(qrcode.getIcon());
            jf.jLabel21.setIcon(qrcode.getIcon());
            jf.jLabel22.setIcon(qrcode.getIcon());
            jf.jLabel23.setIcon(qrcode.getIcon());
            jf.setVisible(true);
        }
    }//GEN-LAST:event_btn_printActionPerformed

    private void btn_resetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseEntered

    }//GEN-LAST:event_btn_resetMouseEntered

    private void btn_resetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_resetMouseExited

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        Titleofbook1.setText("");
        qrcode.setIcon(null);

        copies.setValue(Integer.parseInt("1"));
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_addMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_addMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        btn_add.setToolTipText("Add Book");
    }//GEN-LAST:event_btn_addMouseEntered

    private void btn_addMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_addMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_addMouseExited

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        if (verifBook()) {
            if (isBookExist(textfieldtitle.getText()) && isBookExist(textfieldauthor.getText())) {
                JOptionPane.showMessageDialog(null, "Book Already Exists!", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {

                String book_title = textfieldtitle.getText();
                String author = textfieldauthor.getText();
                String type = comboboxtype.getSelectedItem().toString();
                String subject = comboboxsubject.getSelectedItem().toString();
                int quantity = Integer.valueOf(textfieldquantity.getText());

                //adding info without image
                LibrarianHomepage l = new LibrarianHomepage(null, book_title, author, type, subject, quantity);
                LibrarianHomepageQuery lq = new LibrarianHomepageQuery();
                lq.insertBook(l);
                refreshJtable();
                clearFields();

            }
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_editMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        btn_edit.setToolTipText("Update Book");
    }//GEN-LAST:event_btn_editMouseEntered

    private void btn_editMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_editMouseExited

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        if (verifBook()) {
            if (!textfieldbookid.getText().equals("")) {
                int book_id = Integer.valueOf(textfieldbookid.getText());
                String book_title = textfieldtitle.getText();
                String author = textfieldauthor.getText();
                String type = comboboxtype.getSelectedItem().toString();
                String subject = comboboxsubject.getSelectedItem().toString();
                int quantity = Integer.valueOf(textfieldquantity.getText());

                // update info and picture
                LibrarianHomepage l = new LibrarianHomepage(book_id, book_title, author, type, subject, quantity);
                LibrarianHomepageQuery lq = new LibrarianHomepageQuery();
                lq.updateBook(l, true);
                refreshJtable();
                clearFields();

            } else {
                JOptionPane.showMessageDialog(null, "Select information from table to edit", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_deleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        btn_delete.setToolTipText("Delete Book");
    }//GEN-LAST:event_btn_deleteMouseEntered

    private void btn_deleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteMouseExited

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        if (verifBook()) {
            // select info in table before deleting
            if (!textfieldbookid.getText().equals("")) {
                int Product_ID = Integer.valueOf(textfieldbookid.getText());
                LibrarianHomepageQuery kq = new LibrarianHomepageQuery();
                kq.deleteBook(Product_ID);
                refreshJtable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Select information from table to delete", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_reset2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reset2MouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        btn_reset2.setToolTipText("Reset");
    }//GEN-LAST:event_btn_reset2MouseEntered

    private void btn_reset2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reset2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_reset2MouseExited

    private void btn_reset2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reset2ActionPerformed
        textfieldbookid.setText("");
        textfieldtitle.setText("");
        textfieldauthor.setText("");
        //comboboxtype.setSelectedItem(null);
        //comboboxsubject.setSelectedItem(null);
        textfieldquantity.setText("");
        comboboxtype.setSelectedIndex(0);
        comboboxsubject.setSelectedIndex(0);
    }//GEN-LAST:event_btn_reset2ActionPerformed

    private void btn_add2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add2MouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        btn_add2.setToolTipText("Add Event");
    }//GEN-LAST:event_btn_add2MouseEntered

    private void btn_add2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_add2MouseExited

    private void btn_add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add2ActionPerformed
        String event_name = textfieldeventname.getText();
        byte[] picture = null;
        if (verifEvent()) {

            //adding info without image
            if (imagePth != null) {

                try {

                    Path pth = Paths.get(imagePth);
                    picture = Files.readAllBytes(pth);
                    LibrarianHomepage4 k = new LibrarianHomepage4(null, picture, event_name);
                    LibrarianHomepageQuery kq = new LibrarianHomepageQuery();
                    kq.insertEvent(k);
                    refreshJtable();
                    clearFields();

                } catch (IOException ex) {
                    Logger.getLogger(LibrarianHome.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "You must select a picture", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_add2ActionPerformed

    private void btn_edit2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit2MouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        btn_edit2.setToolTipText("Update Event");
    }//GEN-LAST:event_btn_edit2MouseEntered

    private void btn_edit2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit2MouseExited

    private void btn_edit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit2ActionPerformed
        // select info in the table before editing
        if (verifEvent()) {

            if (!textfieldhomeid.getText().equals("")) {
                int home_id = Integer.valueOf(textfieldhomeid.getText());
                String event_name = textfieldeventname.getText();

                // update info and picture
                if (imagePth != null) {

                    byte[] picture = null;

                    try {

                        Path pth = Paths.get(imagePth);
                        picture = Files.readAllBytes(pth);

                        LibrarianHomepage4 k = new LibrarianHomepage4(home_id, picture, event_name);
                        LibrarianHomepageQuery kq = new LibrarianHomepageQuery();
                        kq.updateEvent(k, true);
                        refreshJtable();
                        clearFields();

                    } catch (IOException ex) {
                        Logger.getLogger(LibrarianHome.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    LibrarianHomepage4 k = new LibrarianHomepage4(home_id, null, event_name);
                    LibrarianHomepageQuery kq = new LibrarianHomepageQuery();
                    kq.updateEvent(k, false);
                    refreshJtable();
                    clearFields();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Select information from table to edit", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_edit2ActionPerformed

    private void btn_delete2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_delete2MouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        btn_delete2.setToolTipText("Delete Event");
    }//GEN-LAST:event_btn_delete2MouseEntered

    private void btn_delete2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_delete2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_delete2MouseExited

    private void btn_delete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete2ActionPerformed
        if (verifEvent()) {

            if (!textfieldhomeid.getText().equals("")) {
                int home_id = Integer.valueOf(textfieldhomeid.getText());
                LibrarianHomepageQuery kq = new LibrarianHomepageQuery();
                kq.deleteEvent(home_id);
                refreshJtable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Select information from table to delete", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_delete2ActionPerformed

    private void btn_reset3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reset3MouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        btn_reset3.setToolTipText("Reset");
    }//GEN-LAST:event_btn_reset3MouseEntered

    private void btn_reset3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reset3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_reset3MouseExited

    private void btn_reset3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reset3ActionPerformed
        textfieldhomeid.setText(null);
        textfieldeventname.setText(null);
        lbl_image.setIcon(null);
    }//GEN-LAST:event_btn_reset3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mainTab.setSelectedIndex(7);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setRolloverIcon(new ImageIcon("C:\\Users\\63915\\Downloads\\Images\\arrow2.png"));
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setRolloverIcon(new ImageIcon("C:\\Users\\63915\\Downloads\\Images\\arrow2.png"));
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        mainTab.setSelectedIndex(8);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        jButton3.setRolloverIcon(new ImageIcon("C:\\Users\\63915\\Downloads\\Images\\arrow2.png"));
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        mainTab.setSelectedIndex(9);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        jButton4.setRolloverIcon(new ImageIcon("C:\\Users\\63915\\Downloads\\Images\\arrow2.png"));
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        mainTab.setSelectedIndex(10);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        jButton5.setRolloverIcon(new ImageIcon("C:\\Users\\63915\\Downloads\\Images\\arrow2.png"));
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        mainTab.setSelectedIndex(11);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        jButton6.setRolloverIcon(new ImageIcon("C:\\Users\\63915\\Downloads\\Images\\arrow2.png"));
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        mainTab.setSelectedIndex(12);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        jButton7.setRolloverIcon(new ImageIcon("C:\\Users\\63915\\Downloads\\Images\\arrow2.png"));
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        mainTab.setSelectedIndex(13);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        jButton8.setRolloverIcon(new ImageIcon("C:\\Users\\63915\\Downloads\\Images\\arrow2.png"));
    }//GEN-LAST:event_jButton8MouseEntered

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        mainTab.setSelectedIndex(14);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void importbooks_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importbooks_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        importbooks_btn.setToolTipText("Import Excel");
    }//GEN-LAST:event_importbooks_btnMouseEntered

    private void importexcelfile_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importexcelfile_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        importexcelfile_btn.setToolTipText("Import Excel");
    }//GEN-LAST:event_importexcelfile_btnMouseEntered

    private void insertdata_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insertdata_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        insertdata_btn.setToolTipText("Insert Data");
    }//GEN-LAST:event_insertdata_btnMouseEntered

    private void back_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        back_btn.setToolTipText("Back");
    }//GEN-LAST:event_back_btnMouseEntered

    private void printreport_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printreport_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        printreport_btn.setToolTipText("Print Report");
    }//GEN-LAST:event_printreport_btnMouseEntered

    private void exportexcel_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportexcel_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        exportexcel_btn.setToolTipText("Export Excel");
    }//GEN-LAST:event_exportexcel_btnMouseEntered

    private void printreport2_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printreport2_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        printreport2_btn.setToolTipText("Print Report");
    }//GEN-LAST:event_printreport2_btnMouseEntered

    private void exportexcel2_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportexcel2_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        exportexcel2_btn.setToolTipText("Export Excel");
    }//GEN-LAST:event_exportexcel2_btnMouseEntered

    private void printreport3_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printreport3_btnMouseEntered
       UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        printreport3_btn.setToolTipText("Print Report");
    }//GEN-LAST:event_printreport3_btnMouseEntered

    private void exportexcel3_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportexcel3_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        exportexcel3_btn.setToolTipText("Export Excel");
    }//GEN-LAST:event_exportexcel3_btnMouseEntered

    private void printreport4_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printreport4_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        printreport4_btn.setToolTipText("Print Report");
    }//GEN-LAST:event_printreport4_btnMouseEntered

    private void exportexcel4_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportexcel4_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        exportexcel4_btn.setToolTipText("Export Excel");
    }//GEN-LAST:event_exportexcel4_btnMouseEntered

    private void printreport5_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printreport5_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        printreport5_btn.setToolTipText("Print Report");
    }//GEN-LAST:event_printreport5_btnMouseEntered

    private void exportexcel5_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportexcel5_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        exportexcel5_btn.setToolTipText("Export Excel");
    }//GEN-LAST:event_exportexcel5_btnMouseEntered

    private void printmath_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printmath_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        printmath_btn.setToolTipText("Print Report");
    }//GEN-LAST:event_printmath_btnMouseEntered

    private void exportmath_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportmath_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        exportmath_btn.setToolTipText("Export Excel");
    }//GEN-LAST:event_exportmath_btnMouseEntered

    private void printeng_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printeng_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        printeng_btn.setToolTipText("Print Report");
    }//GEN-LAST:event_printeng_btnMouseEntered

    private void exporteng_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exporteng_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        exporteng_btn.setToolTipText("Export Excel");
    }//GEN-LAST:event_exporteng_btnMouseEntered

    private void printsci_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printsci_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        printsci_btn.setToolTipText("Print Report");
    }//GEN-LAST:event_printsci_btnMouseEntered

    private void exportsci_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportsci_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        exportsci_btn.setToolTipText("Export Excel");
    }//GEN-LAST:event_exportsci_btnMouseEntered

    private void printfil_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printfil_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        printfil_btn.setToolTipText("Print Report");
    }//GEN-LAST:event_printfil_btnMouseEntered

    private void exportfil_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportfil_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        exportfil_btn.setToolTipText("Export Excel");
    }//GEN-LAST:event_exportfil_btnMouseEntered

    private void printmapeh_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printmapeh_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        printmapeh_btn.setToolTipText("Print Report");
    }//GEN-LAST:event_printmapeh_btnMouseEntered

    private void exportmapeh_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportmapeh_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        exportmapeh_btn.setToolTipText("Export Excel");
    }//GEN-LAST:event_exportmapeh_btnMouseEntered

    private void printtle_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printtle_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        printtle_btn.setToolTipText("Print Report");
    }//GEN-LAST:event_printtle_btnMouseEntered

    private void exporttle_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exporttle_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        exporttle_btn.setToolTipText("Export Excel");
    }//GEN-LAST:event_exporttle_btnMouseEntered

    private void printesp_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printesp_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        printesp_btn.setToolTipText("Print Report");
    }//GEN-LAST:event_printesp_btnMouseEntered

    private void exportesp_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportesp_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        exportesp_btn.setToolTipText("Export Excel");
    }//GEN-LAST:event_exportesp_btnMouseEntered

    private void printap_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printap_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        printap_btn.setToolTipText("Print Report");
    }//GEN-LAST:event_printap_btnMouseEntered

    private void exportap_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportap_btnMouseEntered
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));

        exportap_btn.setToolTipText("Export Excel");
    }//GEN-LAST:event_exportap_btnMouseEntered

    private void textfieldquantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldquantityKeyPressed
        String lrn = textfieldquantity.getText();
        int length = lrn.length();
        char c = evt.getKeyChar();

        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            if (length < 12) {
                textfieldquantity.setEditable(true);
            } else {
                textfieldquantity.setEditable(false);
            }
        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACKSPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                textfieldquantity.setEditable(true);
            } else {
                textfieldquantity.setEditable(false);
            }
        }
    }//GEN-LAST:event_textfieldquantityKeyPressed

    public void refreshFields() {
        jPasswordField1.setText(null);
        jPasswordField2.setText(null);

        //textfieldbookid.setText(null);
        textfieldtitle.setText(null);
        textfieldauthor.setText(null);
        comboboxtype.setSelectedIndex(0);
        comboboxsubject.setSelectedIndex(0);
        textfieldquantity.setText(null);

        textfieldlrn.setText(null);
        textfieldname.setText(null);
        textfieldtitle2.setText(null);
        textfieldauthor2.setText(null);
        textfieldborrowdate.setText(null);
        textfieldduedate.setText(null);

        textfieldlrn2.setText(null);
        textfieldfirstname2.setText(null);
        textfieldlastname2.setText(null);
        textfieldusername2.setText(null);
        textfieldtotalborrow.setText(null);

        Titleofbook.setText(null);
        Titleofbook1.setText(null);
        qrcode.setIcon(null);
        copies.setValue(Integer.parseInt("1"));

        lbl_image.setIcon(null);
        textfieldhomeid.setText(null);
        textfieldeventname.setText(null);

        textfieldsearch.setText(null);
        textfieldsearch2.setText(null);
        textfieldsearch3.setText(null);
        textfieldsearch4.setText(null);
        textfieldsearch5.setText(null);
        textfieldsearch6.setText(null);
        textfieldsearch7.setText(null);
        textfieldsearchmath.setText(null);
        textfieldsearcheng.setText(null);
        textfieldsearchsci.setText(null);
        textfieldsearchfil.setText(null);
        textfieldsearchmapeh.setText(null);
        textfieldsearchtle.setText(null);
        textfieldsearchesp.setText(null);
        textfieldsearchap.setText(null);

    }

    public void refreshJtable() {
        table_books.setModel(new DefaultTableModel());
        table_home.setModel(new DefaultTableModel());
        table_borrowedlate.setModel(new DefaultTableModel());
        //table_importexcel.setModel(new DefaultTableModel());
        populateJtable();
    }

    public void clearFields() {
        //textfieldbookid.setText(null);
        textfieldtitle.setText(null);
        textfieldauthor.setText(null);
        comboboxtype.setSelectedIndex(0);
        comboboxsubject.setSelectedIndex(0);
        textfieldquantity.setText(null);

        // textfieldfirstname.setText(null);
        // textfieldlastname.setText(null);
        // textfieldusername.setText(null);
        jPasswordField1.setText(null);
        jPasswordField2.setText(null);

        textfieldhomeid.setText(null);
        textfieldeventname.setText(null);
        lbl_image.setIcon(null);
    }

    public boolean isBookExist(String un) {
        boolean uExist = false;
        Connection con = (Connection) myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = (PreparedStatement) con.prepareStatement("SELECT * FROM books WHERE book_title=? and author=? ");
            ps.setString(1, textfieldtitle.getText());
            ps.setString(2, textfieldauthor.getText());

            rs = ps.executeQuery();

            if (rs.next()) {
                uExist = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHome.class.getName()).log(Level.SEVERE, null, ex);
        }

        return uExist;
    }
    
    public boolean isImportBookExist() {
        boolean uExist = false;
        Connection con = (Connection) myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        DefaultTableModel tblModel = (DefaultTableModel) table_importexcel.getModel();
        
        try {
            ps = (PreparedStatement) con.prepareStatement("SELECT * FROM books");
            rs = ps.executeQuery();

            if (rs.next()) {
                uExist = true;
                String b = rs.getString("book_title");
                String a = rs.getString("author");
                
                for (int i = 0; i < tblModel.getColumnCount(); i++) {

                        String b1 = table_importexcel.getValueAt(i, 1).toString();
                        String a1 = table_importexcel.getValueAt(i, 2).toString();
                        
                        if (b.equals(b1) && a.equals(a1)) {
                            JOptionPane.showMessageDialog(this, "Book Already Exists!");
                        }
                }
                //JOptionPane.showMessageDialog(this, "Data Insert Successfuly!");
                //return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianHome.class.getName()).log(Level.SEVERE, null, ex);
        }

        return uExist;
    }

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
            java.util.logging.Logger.getLogger(LibrarianHome.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibrarianHome.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibrarianHome.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibrarianHome.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibrarianHome().setVisible(true);
            }
        });
    }

    public void changeTableLate(JTable table, int column_index) {
        table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //String status = table.getValueAt(row, 7).toString();
                //String status1 = "LATE";
                c.setForeground(Color.RED);
                //if (status.equals(status1)) {
                // c.setForeground(Color.RED);
                // } else {
                //   c.setForeground(new Color(255, 204, 0));
                //}
                return c;
            }
        });
    }

    public void changeTableReturn(JTable table, int column_index) {
        table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = table.getValueAt(row, 6).toString();
                String status1 = "LATE RETURNED";
                //c.setForeground(Color.RED);
                if (status.equals(status1)) {
                    c.setForeground(Color.RED);
                } else {
                    c.setForeground(Color.GREEN);
                }
                return c;
            }
        });

    }

    class MyTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Color getForeground() {
            return super.getForeground();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Titleofbook;
    private javax.swing.JLabel Titleofbook1;
    private javax.swing.JLabel aboutImg;
    private javax.swing.JButton back_btn;
    private javax.swing.JLabel book;
    private javax.swing.JLabel borrow;
    private button.MyButton btn_add;
    private button.MyButton btn_add2;
    private button.MyButton btn_browse;
    private button.MyButton btn_delete;
    private button.MyButton btn_delete2;
    private button.MyButton btn_edit;
    private button.MyButton btn_edit2;
    private button.MyButton btn_generate;
    private button.MyButton btn_home;
    private button.MyButton btn_print;
    private button.MyButton btn_reset;
    private button.MyButton btn_reset2;
    private button.MyButton btn_reset3;
    private javax.swing.JLabel cl;
    private javax.swing.JLabel close;
    private javax.swing.JPanel closeFrame;
    private javax.swing.JComboBox<String> comboboxsubject;
    private javax.swing.JComboBox<String> comboboxtype;
    private javax.swing.JSpinner copies;
    private javax.swing.JLabel dashboard;
    private javax.swing.JLabel dateLab;
    private javax.swing.JLabel dateLab1;
    private javax.swing.JButton exportap_btn;
    private javax.swing.JButton exporteng_btn;
    private javax.swing.JButton exportesp_btn;
    private javax.swing.JButton exportexcel2_btn;
    private javax.swing.JButton exportexcel3_btn;
    private javax.swing.JButton exportexcel4_btn;
    private javax.swing.JButton exportexcel5_btn;
    private javax.swing.JButton exportexcel_btn;
    private javax.swing.JButton exportfil_btn;
    private javax.swing.JButton exportmapeh_btn;
    private javax.swing.JButton exportmath_btn;
    private javax.swing.JButton exportsci_btn;
    private javax.swing.JButton exporttle_btn;
    private javax.swing.JLabel hamburger;
    private javax.swing.JButton importbooks_btn;
    private javax.swing.JButton importexcelfile_btn;
    private javax.swing.JButton insertdata_btn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLabel jLabelLoida;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel101;
    private javax.swing.JPanel jPanel102;
    private javax.swing.JPanel jPanel103;
    private javax.swing.JPanel jPanel104;
    private javax.swing.JPanel jPanel105;
    private javax.swing.JPanel jPanel106;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel109;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel110;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
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
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel86;
    private javax.swing.JPanel jPanel87;
    private javax.swing.JPanel jPanel88;
    private javax.swing.JPanel jPanel89;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel90;
    private javax.swing.JPanel jPanel91;
    private javax.swing.JPanel jPanel92;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JPanel jPanel94;
    private javax.swing.JPanel jPanel95;
    private javax.swing.JPanel jPanel96;
    private javax.swing.JPanel jPanel97;
    private javax.swing.JPanel jPanel98;
    private javax.swing.JPanel jPanel99;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel jTextField1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel lbl_fullname;
    private javax.swing.JLabel lbl_image;
    public javax.swing.JLabel lbl_notif;
    private javax.swing.JLabel lblconfirmpass;
    private javax.swing.JLabel lblfirstname;
    private javax.swing.JLabel lbllastname;
    private javax.swing.JLabel lblpassword;
    private javax.swing.JLabel lblusername;
    private javax.swing.JLabel logout;
    private javax.swing.JPanel lowerPanel;
    public javax.swing.JTabbedPane mainTab;
    private javax.swing.JLabel min;
    private javax.swing.JPanel minFrame;
    private javax.swing.JPanel notification;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel5;
    private javax.swing.JPanel panel6;
    private javax.swing.JPanel panel7;
    private javax.swing.JPanel panel_importexcel;
    public javax.swing.JPanel panel_notification;
    private javax.swing.JPanel panelap;
    private javax.swing.JPanel panelbooks;
    private javax.swing.JPanel panelborrowed;
    private javax.swing.JPanel paneleng;
    private javax.swing.JPanel panelesp;
    private javax.swing.JPanel panelfil;
    private javax.swing.JPanel panelhome;
    private javax.swing.JPanel panellateunreturned;
    private javax.swing.JPanel panelmapeh;
    private javax.swing.JPanel panelmath;
    private javax.swing.JPanel panelreturned;
    private javax.swing.JPanel panelsci;
    private javax.swing.JPanel panelstudents;
    private javax.swing.JPanel paneltle;
    private javax.swing.JButton printap_btn;
    private javax.swing.JButton printeng_btn;
    private javax.swing.JButton printesp_btn;
    private javax.swing.JButton printfil_btn;
    private javax.swing.JButton printmapeh_btn;
    private javax.swing.JButton printmath_btn;
    private javax.swing.JButton printreport2_btn;
    private javax.swing.JButton printreport3_btn;
    private javax.swing.JButton printreport4_btn;
    private javax.swing.JButton printreport5_btn;
    private javax.swing.JButton printreport_btn;
    private javax.swing.JButton printsci_btn;
    private javax.swing.JButton printtle_btn;
    private javax.swing.JLabel qr;
    private javax.swing.JLabel qrcode;
    private javax.swing.JLabel receipt;
    private javax.swing.JTabbedPane reportsTab;
    private javax.swing.JPanel sideNav;
    private javax.swing.JLabel student;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab10;
    private javax.swing.JPanel tab11;
    private javax.swing.JPanel tab12;
    private javax.swing.JPanel tab13;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JPanel tab5;
    private javax.swing.JPanel tab6;
    private javax.swing.JPanel tab7;
    private javax.swing.JPanel tab8;
    private javax.swing.JPanel tab9;
    private javax.swing.JTable table_ap;
    private javax.swing.JTable table_bookreport;
    public javax.swing.JTable table_books;
    private javax.swing.JTable table_borrowed;
    private javax.swing.JTable table_borrowedlate;
    private javax.swing.JTable table_borrowedreport;
    private javax.swing.JTable table_eng;
    private javax.swing.JTable table_esp;
    private javax.swing.JTable table_fil;
    private javax.swing.JTable table_home;
    public javax.swing.JTable table_importexcel;
    private javax.swing.JTable table_lateunreturnedreport;
    private javax.swing.JTable table_mapeh;
    private javax.swing.JTable table_math;
    private javax.swing.JTable table_returnedreport;
    private javax.swing.JTable table_sci;
    private javax.swing.JTable table_studentreport;
    private javax.swing.JTable table_students;
    private javax.swing.JTable table_tle;
    private javax.swing.JTextField textfieldauthor;
    private javax.swing.JTextField textfieldauthor2;
    private javax.swing.JLabel textfieldbookid;
    private javax.swing.JTextField textfieldborrowdate;
    private javax.swing.JTextField textfieldduedate;
    private javax.swing.JTextArea textfieldeventname;
    private javax.swing.JTextField textfieldfirstname;
    private javax.swing.JTextField textfieldfirstname2;
    private javax.swing.JLabel textfieldhomeid;
    private javax.swing.JTextField textfieldlastname;
    private javax.swing.JTextField textfieldlastname2;
    private javax.swing.JTextField textfieldlrn;
    private javax.swing.JTextField textfieldlrn2;
    private javax.swing.JTextField textfieldname;
    private javax.swing.JTextField textfieldquantity;
    private javax.swing.JTextField textfieldsearch;
    private javax.swing.JTextField textfieldsearch2;
    private javax.swing.JTextField textfieldsearch3;
    private javax.swing.JTextField textfieldsearch4;
    private javax.swing.JTextField textfieldsearch5;
    private javax.swing.JTextField textfieldsearch6;
    private javax.swing.JTextField textfieldsearch7;
    private javax.swing.JTextField textfieldsearch8;
    private javax.swing.JTextField textfieldsearch9;
    private javax.swing.JTextField textfieldsearchap;
    private javax.swing.JTextField textfieldsearcheng;
    private javax.swing.JTextField textfieldsearchesp;
    private javax.swing.JTextField textfieldsearchfil;
    private javax.swing.JTextField textfieldsearchmapeh;
    private javax.swing.JTextField textfieldsearchmath;
    private javax.swing.JTextField textfieldsearchsci;
    private javax.swing.JTextField textfieldsearchtle;
    private javax.swing.JTextArea textfieldtitle;
    private javax.swing.JTextArea textfieldtitle2;
    private javax.swing.JTextField textfieldtotalborrow;
    private javax.swing.JTextField textfieldusername;
    private javax.swing.JTextField textfieldusername2;
    private javax.swing.JLabel totalap;
    private javax.swing.JLabel totalbooks;
    private javax.swing.JLabel totalborrow;
    private javax.swing.JLabel totaleng;
    private javax.swing.JLabel totalesp;
    private javax.swing.JLabel totalfil;
    private javax.swing.JLabel totallate;
    private javax.swing.JLabel totalmapeh;
    private javax.swing.JLabel totalmath;
    private javax.swing.JLabel totalsci;
    private javax.swing.JLabel totalstudent;
    private javax.swing.JLabel totaltle;
    private button.MyButton updateinfo_btn;
    private javax.swing.JPanel upperPanel;
    private javax.swing.JLabel user;
    private javax.swing.JLabel view1;
    private javax.swing.JLabel view2;
    private javax.swing.JLabel view3;
    private javax.swing.JLabel view5;
    // End of variables declaration//GEN-END:variables

    class RoundedPanel extends JPanel {

        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }

        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;

        }

        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
//            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
//             
        }
    }

}
