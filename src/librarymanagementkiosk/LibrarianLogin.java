/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementkiosk;

import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author Clarisse
 */
public class LibrarianLogin extends javax.swing.JFrame {

    /**
     * Creates new form LibrarianLogin
     */
    public LibrarianLogin() {
        initComponents();
        this.setLocationRelativeTo(null); //center screen

        Border emptyBorder = BorderFactory.createEmptyBorder();
        jTextFieldusername.setBorder(emptyBorder);
        jPasswordField1.setBorder(emptyBorder);
        btn_login.setBorder(emptyBorder);
        btn_reset.setBorder(emptyBorder);
        btn_back.setBorder(emptyBorder);
        
        jLabel7.setVisible(true);
        jLabel8.setVisible(false);
        
        btn_login.setFocusPainted(false);
        btn_reset.setFocusPainted(false);
        btn_back.setFocusPainted(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabeladmin1 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelicon = new javax.swing.JLabel();
        jLabelLoida = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        jLabelpassword = new javax.swing.JLabel();
        jLabelusername1 = new javax.swing.JLabel();
        lbl_u = new javax.swing.JLabel();
        lbl_p = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnl_username = new javax.swing.JPanel();
        jTextFieldusername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pnl_password = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        forgot = new javax.swing.JLabel();
        btn_login = new button.MyButton();
        btn_reset = new button.MyButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 70, 56), 5));
        jPanel1.setMinimumSize(new java.awt.Dimension(700, 470));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabeladmin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 237, -1, 97));

        jPanel21.setBackground(new java.awt.Color(151, 205, 205));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 440, 680, -1));

        jPanel2.setBackground(new java.awt.Color(64, 134, 176));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jLabelicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 287, -1, -1));

        jLabelLoida.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabelLoida.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLoida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/era2.png"))); // NOI18N
        jPanel2.add(jLabelLoida, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 268, -1, -1));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 287, -1, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/neweralogo.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 87, 173, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Library Management Kiosk ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 300, -1, -1));

        btn_back.setBackground(new java.awt.Color(64, 134, 176));
        btn_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/backarrow.png"))); // NOI18N
        btn_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_backMouseEntered(evt);
            }
        });
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        jPanel2.add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 220, 440));

        jLabelpassword.setFont(new java.awt.Font("Rockwell Condensed", 0, 18)); // NOI18N
        jLabelpassword.setText("PASSWORD:");
        jPanel1.add(jLabelpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 245, -1, -1));

        jLabelusername1.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabelusername1.setText("USERNAME:");
        jPanel1.add(jLabelusername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

        lbl_u.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_u.setForeground(new java.awt.Color(255, 0, 0));
        lbl_u.setText("*");
        jPanel1.add(lbl_u, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, -1, -1));

        lbl_p.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_p.setForeground(new java.awt.Color(255, 0, 0));
        lbl_p.setText("*");
        jPanel1.add(lbl_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 245, 10, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/lib3.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 35, -1, -1));

        pnl_username.setBackground(new java.awt.Color(255, 255, 255));
        pnl_username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnl_username.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldusername.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextFieldusername.setBorder(null);
        jTextFieldusername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldusernameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextFieldusernameMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldusernameMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldusernameMouseReleased(evt);
            }
        });
        jTextFieldusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldusernameActionPerformed(evt);
            }
        });
        jTextFieldusername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldusernameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldusernameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldusernameKeyTyped(evt);
            }
        });
        pnl_username.add(jTextFieldusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 5, 305, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/un.png"))); // NOI18N
        pnl_username.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 30, 30));

        jPanel1.add(pnl_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 350, 40));

        pnl_password.setBackground(new java.awt.Color(255, 255, 255));
        pnl_password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnl_password.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarymanagementkiosk/pass.png"))); // NOI18N
        pnl_password.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 30, 30));

        jPasswordField1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPasswordField1.setBorder(null);
        jPasswordField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPasswordField1MousePressed(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });
        pnl_password.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 5, 255, 29));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("SHOW");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        pnl_password.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 12, 45, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("HIDE");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        pnl_password.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 12, 45, -1));

        jPanel1.add(pnl_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 350, 40));

        forgot.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        forgot.setForeground(new java.awt.Color(12, 105, 169));
        forgot.setText("Forgot Password?");
        forgot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgotMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forgotMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                forgotMouseExited(evt);
            }
        });
        jPanel1.add(forgot, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 100, -1));

        btn_login.setText("LOGIN");
        btn_login.setBorderColor(new java.awt.Color(64, 134, 176));
        btn_login.setColorClick(new java.awt.Color(102, 153, 255));
        btn_login.setColorOver(new java.awt.Color(64, 134, 176));
        btn_login.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_login.setRadius(30);
        btn_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_loginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_loginMouseExited(evt);
            }
        });
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        jPanel1.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, 150, 35));

        btn_reset.setForeground(new java.awt.Color(255, 255, 255));
        btn_reset.setText("RESET");
        btn_reset.setBorderColor(new java.awt.Color(64, 134, 176));
        btn_reset.setColor(new java.awt.Color(64, 134, 176));
        btn_reset.setColorClick(new java.awt.Color(102, 153, 255));
        btn_reset.setColorOver(new java.awt.Color(64, 134, 176));
        btn_reset.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
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
        jPanel1.add(btn_reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 150, 35));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public boolean verifData() {
        if (jTextFieldusername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username is required", "Message", JOptionPane.ERROR_MESSAGE);
        } else if (jPasswordField1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password is required", "Message", JOptionPane.ERROR_MESSAGE);

            return false;
        } else {
            return true;
        }
        return false;
    }


    private void jTextFieldusernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldusernameMouseClicked


    }//GEN-LAST:event_jTextFieldusernameMouseClicked

    private void jTextFieldusernameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldusernameMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldusernameMouseEntered

    private void jTextFieldusernameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldusernameMousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        pnl_username.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(93, 70, 56), 1);
        pnl_password.setBorder(b);
    }//GEN-LAST:event_jTextFieldusernameMousePressed

    private void jTextFieldusernameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldusernameMouseReleased

    }//GEN-LAST:event_jTextFieldusernameMouseReleased

    private void jPasswordField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MouseClicked

    }//GEN-LAST:event_jPasswordField1MouseClicked

    private void jTextFieldusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldusernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldusernameActionPerformed

    private void jTextFieldusernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldusernameKeyReleased
        lbl_u.setVisible(false);

        if (jTextFieldusername.getText().equals("")) {
            lbl_u.setVisible(true);

        }
    }//GEN-LAST:event_jTextFieldusernameKeyReleased

    private void jPasswordField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyReleased
        lbl_p.setVisible(false);

        if (String.valueOf(jPasswordField1.getPassword()).equals("")) {
            lbl_p.setVisible(true);

        }
    }//GEN-LAST:event_jPasswordField1KeyReleased

    private void jPasswordField1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MousePressed
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        pnl_password.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(0,0,0), 1);
        pnl_username.setBorder(b);
    }//GEN-LAST:event_jPasswordField1MousePressed

    private void jTextFieldusernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldusernameKeyPressed

    }//GEN-LAST:event_jTextFieldusernameKeyPressed

    private void jTextFieldusernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldusernameKeyTyped
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        pnl_username.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(0,0,0), 1);
        pnl_password.setBorder(b);
        
        
        
    }//GEN-LAST:event_jTextFieldusernameKeyTyped

    private void jPasswordField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyTyped
        Border a = BorderFactory.createLineBorder(new Color(64, 134, 176), 2);
        pnl_password.setBorder(a);
        Border b = BorderFactory.createLineBorder(new Color(0,0,0), 1);
        pnl_username.setBorder(b);
    }//GEN-LAST:event_jPasswordField1KeyTyped

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        jPasswordField1.setEchoChar((char) 0);
        jLabel8.setVisible(true);
        jLabel7.setVisible(false);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        jPasswordField1.setEchoChar('•');
        jLabel7.setVisible(true);
        jLabel8.setVisible(false);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void forgotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotMouseClicked
        LibrarianForgotPassword fp = new LibrarianForgotPassword();
        fp.setVisible(true);
        fp.pack();
        fp.setLocationRelativeTo(null);
        fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_forgotMouseClicked

    private void forgotMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotMouseEntered
         forgot.setForeground(new Color (7,74,114));
    }//GEN-LAST:event_forgotMouseEntered

    private void forgotMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotMouseExited
        forgot.setForeground(new Color (12,105,169));
    }//GEN-LAST:event_forgotMouseExited

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
         //  lbl_u.setVisible(false);
        //   lbl_p.setVisible(false);

        if (jTextFieldusername.getText().equals("") && String.valueOf(jPasswordField1.getPassword()).equals("")) {
            //    lbl_u.setVisible(true);
            //    lbl_p.setVisible(true);
            JOptionPane.showMessageDialog(null, "Username and Password are required", "Message", JOptionPane.ERROR_MESSAGE);
        } else if (jTextFieldusername.getText().equals("")) {
            //   lbl_u.setVisible(true);
            JOptionPane.showMessageDialog(null, "Username is required", "Message", JOptionPane.ERROR_MESSAGE);
        } else if (String.valueOf(jPasswordField1.getPassword()).equals("")) {
            //  lbl_p.setVisible(true);
            JOptionPane.showMessageDialog(null, "Password is required", "Message", JOptionPane.ERROR_MESSAGE);
        } else {
            Connection con = (Connection) myConnection.getConnection();
            PreparedStatement ps;
            ResultSet rs;

            try {
                ps = (PreparedStatement) con.prepareStatement("SELECT * FROM librarianlogin WHERE username=? and password=?");
                ps.setString(1, jTextFieldusername.getText());
                ps.setString(2, String.valueOf(jPasswordField1.getPassword()));
                rs = ps.executeQuery();

                if (rs.next()) {
                    myConnection.index = rs.getInt("ID");
                    JOptionPane.showMessageDialog(this, "Login Successful", "Login", JOptionPane.INFORMATION_MESSAGE);
                    LibrarianHome ho = new LibrarianHome();
                    ho.setVisible(true);
                    ho.pack();
                    ho.setLocationRelativeTo(null);
                    ho.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    Notifications n = new Notifications();
                    n.setLocation(1002, 105);
                    n.setVisible(true);
                    String no = ho.lbl_notif.getText();
                    n.lbl_total.setText(no);
                    
                    /*if(no.equals("0")){
                        n.setVisible(false);
                        n.alert();
                    }*/
                    //n.setLocationRelativeTo(null);

                    // show username in the right side of form
                    //ho.labelusername.setText(rs.getString("username"));
                    this.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password");
                }
            } catch (SQLException ex) {
                Logger.getLogger(LibrarianLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_loginActionPerformed

    private void btn_loginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseEntered
        btn_login.setForeground(new Color (255,255,255));
    }//GEN-LAST:event_btn_loginMouseEntered

    private void btn_loginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseExited
        btn_login.setForeground(new Color (0,0,0));
    }//GEN-LAST:event_btn_loginMouseExited

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
         jTextFieldusername.setText(null);
        jPasswordField1.setText(null);
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_resetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseEntered
               
    }//GEN-LAST:event_btn_resetMouseEntered

    private void btn_resetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_resetMouseExited

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
         Users user = new Users();
        user.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_backMouseEntered
        btn_back.setRolloverIcon(new ImageIcon("C:\\Users\\63915\\Downloads\\Images\\backarrow2.png"));
    }//GEN-LAST:event_btn_backMouseEntered

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
            java.util.logging.Logger.getLogger(LibrarianLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibrarianLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibrarianLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibrarianLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibrarianLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private button.MyButton btn_login;
    private button.MyButton btn_reset;
    private javax.swing.JLabel forgot;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelLoida;
    private javax.swing.JLabel jLabeladmin1;
    private javax.swing.JLabel jLabelicon;
    private javax.swing.JLabel jLabelpassword;
    private javax.swing.JLabel jLabelusername1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextFieldusername;
    private javax.swing.JLabel lbl_p;
    private javax.swing.JLabel lbl_u;
    private javax.swing.JPanel pnl_password;
    private javax.swing.JPanel pnl_username;
    // End of variables declaration//GEN-END:variables
}
