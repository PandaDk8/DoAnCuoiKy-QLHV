package View;


import DAO.DiemDAO;
import DAO.TrungTamDAO;
import Model.TrungTam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TrungTamForm extends JFrame implements ActionListener {
    private JLabel lbName;
    private JTextField tfID, tfName;
    private JButton ok, cancel;

    TrungTamJPanel trungTamJPanel;

    public TrungTamForm(TrungTamJPanel tt,String s, String id, String name) {
        super(s);
        trungTamJPanel = tt;

        Container cont = this.getContentPane();
        cont.setLayout(new GridLayout(2, 2));
        lbName = new JLabel("Tên trung tâm");

        tfID = new JTextField(id);
        tfName = new JTextField(name);

        ok = new JButton("Ok");
        cancel = new JButton("Cancel");

        ok.setForeground(new Color(0xD8D9A8));
        cancel.setForeground(new Color(0xD8D9A8));

        ok.setBackground(new Color(0x044A42));
        cancel.setBackground(new Color(0x044A42));

        ok.addActionListener(this);
        cancel.addActionListener(this);

        lbName.setFont(new Font("Calibri (Body)", Font.BOLD, 12));

        tfName.setBackground(new Color(0xB8E1DD));

        cont.add(lbName);
        cont.add(tfName);
        cont.add(ok);
        cont.add(cancel);

        this.setBounds(600,300, 250, 100);
        cont.setBackground(new Color(0xB9D2D2));
        this.setResizable(false);
        this.setVisible(true);
    }

    public void EditList() {
        if(this.getTitle().equals("Insert Form")){
            if (tfName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập đầy đủ thông tin");
            }
            try {
                TrungTam cp = new TrungTam();
                cp.setName(tfName.getText());
                TrungTamDAO dao = new TrungTamDAO();
                dao.Add(cp);
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                trungTamJPanel.loadAll();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }else {
            if (tfName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập đầy đủ thông tin");
            }
            try {
                TrungTam trungTam = new TrungTam();
                trungTam.setId(Integer.parseInt(tfID.getText()));
                trungTam.setName(tfName.getText());

                TrungTamDAO dao = new TrungTamDAO();
                dao.Edit(trungTam);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            }

        }
        trungTamJPanel.loadAll();
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