package View;


import DAO.DatabaseHepler;
import DAO.DiemDAO;
import DAO.HocVienDAO;
import DAO.TrungTamDAO;
import Model.Diem;
import Model.TrungTam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class HocVienForm extends JFrame implements ActionListener {
    private JLabel lbFullName, lbBirthday, lbTrungTam, lbSdt, lbAddress;
    private JTextField tfID, tfFullName, tfBirthday, tfSdt, tfAddress;
    private JButton ok, cancel;
    private JComboBox cbTrungtam;

    HocVienJPanel hocVienJPanel;

    public HocVienForm(HocVienJPanel d,String s, String id, String fullName, String birthday,
                        String trungtam, String std, String address) {
        super(s);
        hocVienJPanel = d;
        initComboBox();
        cbTrungtam.setSelectedItem(trungtam);
        Container cont = this.getContentPane();
        cont.setLayout(new GridLayout(6, 2));
        lbFullName = new JLabel("Tên học viên");
        lbBirthday = new JLabel("Ngày sinh");
        lbTrungTam = new JLabel("Tên trung tâm");
        lbSdt = new JLabel("Số điện thoại");
        lbAddress = new JLabel("Địa chỉ");

        tfID = new JTextField(id);
        tfFullName = new JTextField(fullName);
        tfBirthday = new JTextField(String.valueOf(birthday));
        tfSdt = new JTextField(std);
        tfAddress = new JTextField(address);

        ok = new JButton("Ok");
        cancel = new JButton("Cancel");

        ok.setForeground(new Color(0xD8D9A8));
        cancel.setForeground(new Color(0xD8D9A8));

        ok.setBackground(new Color(0x044A42));
        cancel.setBackground(new Color(0x044A42));

        ok.addActionListener(this);
        cancel.addActionListener(this);

        lbFullName.setFont(new Font("Calibri (Body)", Font.BOLD, 12));
        lbBirthday.setFont(new Font("Calibri (Body)", Font.BOLD, 12));
        lbTrungTam.setFont(new Font("Calibri (Body)", Font.BOLD, 12));
        lbSdt.setFont(new Font("Calibri (Body)", Font.BOLD, 12));
        lbAddress.setFont(new Font("Calibri (Body)", Font.BOLD, 12));

        tfFullName.setBackground(new Color(0xB8E1DD));
        tfBirthday.setBackground(new Color(0xB8E1DD));
        tfAddress.setBackground(new Color(0xB8E1DD));
        tfSdt.setBackground(new Color(0xB8E1DD));

        cont.add(lbFullName);
        cont.add(tfFullName);
        cont.add(lbBirthday);
        cont.add(tfBirthday);
        cont.add(lbTrungTam);
        cont.add(cbTrungtam);
        cont.add(lbSdt);
        cont.add(tfSdt);
        cont.add(lbAddress);
        cont.add(tfAddress);

        cont.add(ok);
        cont.add(cancel);

        this.setBounds(400, 300, 300, 250);
        cont.setBackground(new Color(0xB9D2D2));
        this.setResizable(false);
        this.setVisible(true);
    }
    public void initComboBox(){
        cbTrungtam = new JComboBox<>();
        try{
            Connection connection = DatabaseHepler.openConnection();
            String sql = "select name from TrungTam";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            cbTrungtam.removeAllItems();
            while (rs.next()){
                cbTrungtam.addItem(rs.getString("name"));
            }
            cbTrungtam.addActionListener(this);
            rs.close();
            statement.close();
            connection.close();

        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    public void EditList() {
        if(this.getTitle().equals("Insert Form")){
            if (tfFullName.getText().isEmpty() || tfBirthday.getText().isEmpty() || tfAddress.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập đầy đủ thông tin");
            }
            try {
                Diem hocvien = new Diem();
                hocvien.setFullname(tfFullName.getText());
                hocvien.setBirthday(tfBirthday.getText());
                hocvien.setTrungtam(cbTrungtam.getSelectedItem().toString());
                hocvien.setSdt(tfSdt.getText());
                hocvien.setAddress(tfAddress.getText());
                HocVienDAO dao = new HocVienDAO();
                dao.Add(hocvien);
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                hocVienJPanel.loadAll();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            }
        }else {
            if (tfFullName.getText().isEmpty() || tfBirthday.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập đầy đủ thông tin");
            } else {
                try {
                    Diem hocvien = new Diem();
                    hocvien.setId(Integer.parseInt(tfID.getText()));
                    hocvien.setFullname(tfFullName.getText());
                    hocvien.setBirthday(tfBirthday.getText());
                    hocvien.setTrungtam(cbTrungtam.getSelectedItem().toString());
                    hocvien.setSdt(tfSdt.getText());
                    hocvien.setAddress(tfAddress.getText());
                    HocVienDAO dao = new HocVienDAO();
                    dao.Edit(hocvien);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
                }
            }
        }
        hocVienJPanel.loadAll();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            EditList();
            this.dispose();
        }
        if (e.getSource() == cancel) {
            this.dispose();
        }
    }
}