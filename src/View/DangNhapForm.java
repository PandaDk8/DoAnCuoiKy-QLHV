package View;

import DAO.TaiKhoanDAO;
import Model.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DangNhapForm extends JFrame implements ActionListener {
    private JLabel lbUser, lbPassword, lb1, lb2;
    private JTextField tfUser, tfPassword;
    private JButton btnSignIn, btnSignUp;
    private JPanel pn1, pn2;

    TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO ();

    public DangNhapForm (){
        lbUser = new JLabel ("Tài khoản");
        lbPassword = new JLabel ("Mật khẩu");
        tfUser = new JTextField ();
        tfPassword = new JTextField ();
        btnSignIn = new JButton ("Đăng Nhập");
        btnSignUp = new JButton ("Đăng kí");
        pn1 = new JPanel ();
        pn2 = new JPanel ();

        ImageIcon p1 = new ImageIcon ("images2/pn1.jpg");

        lb1 = new JLabel (p1);

        btnSignUp.addActionListener (this);
        btnSignIn.addActionListener (this);

        lbUser.setBounds (10,10,280,50);
        tfUser.setBounds (10,65,270,30);
        lbPassword.setBounds (10,100,280,50);
        tfPassword.setBounds (10,155,270,30);
        btnSignIn.setBounds (40,215,220,45);
        btnSignUp.setBounds (40,270,220,45);

        pn1.add (lb1);
        pn2.setLayout (null);
        pn2.add (lbUser); pn2.add (tfUser);
        pn2.add (lbPassword); pn2.add (tfPassword);
        pn2.add(btnSignIn); pn2.add(btnSignUp);


        this.add(pn1); this.add(pn2);
        this.setBounds (430,200,600,400);
        this.setLayout (new GridLayout (1,2));
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setVisible (true);
    }

    public void setEvent() {

        try {
            if (tfUser.getText ().length () == 0
                    || tfPassword.getText ().length () == 0) {
                JOptionPane.showMessageDialog (this,"Vui lòng nhập dữ liệu bắt buộc!");
            } else {
                TaiKhoan taiKhoan = taiKhoanDAO.login (tfUser.getText (), tfPassword.getText ());
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
        if(e.getSource ()==btnSignIn){
            setEvent ();
        }
    }
}
