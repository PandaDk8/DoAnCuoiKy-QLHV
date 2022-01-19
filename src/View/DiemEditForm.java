package View;


import DAO.DiemDAO;
import Model.Diem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DiemEditForm extends JFrame implements ActionListener {
    private JLabel lbFullName, lbBirthday, lbChuyenCan, lbBaiTap, lbGiuaky, lbCuoiKy;
    private JTextField tfID, tfFullName, tfBirthday, tfChuyenCan, tfBaiTap, tfGiuaKy, tfCuoiKy, tfDiemTB, tfXepLoai;
    private JButton ok, cancel;

    DiemJPanel dhj;

    public DiemEditForm(DiemJPanel d, String id, String fullName, String birthday,
                             float chuyencan, float baitap, float giuaky, float cuoiky, float diemtb, String xeploai) {
        dhj = d;

        Container cont = this.getContentPane();
        cont.setLayout(new GridLayout(7, 2));
        lbFullName = new JLabel("Tên học viên");
        lbBirthday = new JLabel("Ngày sinh");
        lbChuyenCan = new JLabel("Điểm chuyên cần");
        lbBaiTap = new JLabel("Điểm bài tập");
        lbGiuaky = new JLabel("Điểm giữa kỳ");
        lbCuoiKy = new JLabel("Điểm cuối kỳ");

        tfID = new JTextField(id);
        tfFullName = new JTextField(fullName);
        tfBirthday = new JTextField(String.valueOf(birthday));
        tfChuyenCan = new JTextField(String.valueOf(chuyencan));
        tfBaiTap = new JTextField(String.valueOf(baitap));
        tfGiuaKy = new JTextField(String.valueOf(giuaky));
        tfCuoiKy = new JTextField(String.valueOf(cuoiky));
        tfDiemTB = new JTextField(String.valueOf(diemtb));
        tfXepLoai = new JTextField(xeploai);

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
        lbChuyenCan.setFont(new Font("Calibri (Body)", Font.BOLD, 12));
        lbBaiTap.setFont(new Font("Calibri (Body)", Font.BOLD, 12));
        lbGiuaky.setFont(new Font("Calibri (Body)", Font.BOLD, 12));
        lbCuoiKy.setFont(new Font("Calibri (Body)", Font.BOLD, 12));

        tfFullName.setBackground(new Color(0xB8E1DD));
        tfBirthday.setBackground(new Color(0xB8E1DD));
        tfChuyenCan.setBackground(new Color(0xB8E1DD));
        tfBaiTap.setBackground(new Color(0xB8E1DD));
        tfGiuaKy.setBackground(new Color(0xB8E1DD));
        tfCuoiKy.setBackground(new Color(0xB8E1DD));

        cont.add(lbFullName);
        cont.add(tfFullName);
        cont.add(lbBirthday);
        cont.add(tfBirthday);
        cont.add(lbChuyenCan);
        cont.add(tfChuyenCan);
        cont.add(lbBaiTap);
        cont.add(tfBaiTap);
        cont.add(lbGiuaky);
        cont.add(tfGiuaKy);
        cont.add(lbCuoiKy);
        cont.add(tfCuoiKy);
        cont.add(ok);
        cont.add(cancel);

        this.setBounds(400, 300, 300, 250);
        cont.setBackground(new Color(0xB9D2D2));
        this.setResizable(false);
        this.setVisible(true);
    }

    public void EditList() {
            if (tfFullName.getText().isEmpty() || tfBirthday.getText().isEmpty() || tfChuyenCan.getText().isEmpty() || tfBaiTap.getText().isEmpty()
                    || tfGiuaKy.getText().isEmpty() || tfCuoiKy.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập đầy đủ thông tin");
            } else {
                try {
                    Diem diem = new Diem();
                    diem.setId(Integer.parseInt(tfID.getText()));
                    diem.setFullname(tfFullName.getText());
                    diem.setBirthday(tfBirthday.getText());
                    diem.setChuyencan(Float.parseFloat(tfChuyenCan.getText()));
                    diem.setBaitap(Float.parseFloat(tfBaiTap.getText()));
                    diem.setGiuaky(Float.parseFloat(tfGiuaKy.getText()));
                    diem.setCuoiky(Float.parseFloat(tfCuoiKy.getText()));
                    diem.setDiemtb(Float.parseFloat(tfDiemTB.getText()));
                    diem.setXeploai(tfXepLoai.getText());
                    DiemDAO dao = new DiemDAO();
                    dao.edit(diem);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi: "+e.getMessage());
                }
            }
        dhj.loadAllEmployees();
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