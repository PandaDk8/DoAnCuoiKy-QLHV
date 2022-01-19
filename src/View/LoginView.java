package View;

import DAO.TaiKhoanDAO;
import Model.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginView extends JFrame implements ActionListener {
    TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO ();

    public LoginView () {
        initComponents();
        setVisible (true);
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jPanel4 = new JPanel();
        jLabel3 = new JLabel();
        jPanel5 = new JPanel();
        jPanel1 = new JPanel();
        lblRegister = new JLabel();
        btnLogin = new JButton();
        jPanel3 = new JPanel();
        txtPassword = new JPasswordField();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        lblForgotPassword = new JLabel();
        txtUsername = new JTextField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng Nhập Hệ Thống");
        setResizable(false);

        jPanel4.setBackground(new java.awt.Color(87, 184, 70));
        jPanel4.setMaximumSize(new java.awt.Dimension(400, 75));
        jPanel4.setMinimumSize(new java.awt.Dimension(400, 75));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 75));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Đăng Nhập");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jLabel3)
                .addContainerGap(156, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(414, 75));

        lblRegister.setForeground(new java.awt.Color(0, 132, 255));
        lblRegister.setText("Chưa có tài khoản?");

        btnLogin.setBackground(new java.awt.Color(87, 184, 70));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Đăng Nhập");
        btnLogin.addActionListener (this);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRegister)
                    .addComponent(btnLogin))
                .addGap(143, 143, 143))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRegister)
                .addContainerGap())
        );

        jPanel5.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        jPanel3.add(txtPassword, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Username:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        jPanel3.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        jPanel3.add(jLabel2, gridBagConstraints);

        lblForgotPassword.setText("Quên mật khẩu");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(lblForgotPassword, gridBagConstraints);

        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        jPanel3.add(txtUsername, gridBagConstraints);

        jPanel5.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        setBounds (500,200,435,350);
    }

    public void setEvent() {

        try {
            if (txtUsername.getText ().length () == 0
                    || txtPassword.getText ().length () == 0) {
                JOptionPane.showMessageDialog (this,"Vui lòng nhập dữ liệu bắt buộc!");
            } else {
                TaiKhoan taiKhoan = taiKhoanDAO.login (txtUsername.getText (), txtPassword.getText ());
                if (taiKhoan == null) {
                    JOptionPane.showMessageDialog (this,"Tên đăng nhập và mật khẩu không đúng!");
                } else {
                    this.dispose ();
                    MainFrame frame = new MainFrame ();
                    frame.setVisible (true);
                }
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog (this, ex.getMessage ());
        }
    }
    public void actionPerformed (ActionEvent e) {
        if(e.getSource ()==btnLogin){
            setEvent ();
        }
    }

    private JButton btnLogin;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private JLabel lblForgotPassword;
    private JLabel lblRegister;
    private JPasswordField txtPassword;
    private JTextField txtUsername;

}
