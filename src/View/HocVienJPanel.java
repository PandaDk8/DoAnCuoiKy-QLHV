package View;

import DAO.DatabaseHepler;
import DAO.HocVienDAO;
import DAO.TrungTamDAO;
import Model.Diem;
import Model.TrungTam;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HocVienJPanel extends JPanel implements ActionListener, MouseListener {

    private JButton btnFind;
    private  JButton btnEdit;
    private  JButton btnReset;
    private  JButton btnAdd;
    private JButton btnDelete;

    private JTextField tfFind;

    private JPanel panel1, panel2, panel3;

    JScrollPane jScrollPane;
    JTable tableHocVien;
    DefaultTableModel tableModel;
    HocVienDAO hocVienDAO = new HocVienDAO();
    String selectedRow;
    List<Diem> list = new ArrayList<>();
    public HocVienJPanel(){
        init();
        initTable();
        loadAll();
    }
    public void init(){
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        tfFind = new JTextField();

        btnEdit = new JButton("Sửa");
        btnFind = new JButton("Tìm");
        btnReset = new JButton("Reset");
        btnAdd = new JButton("Thêm");
        btnDelete = new JButton("Xóa");

        btnEdit.setForeground(new Color(0xD2FAFB));
        btnReset.setForeground(new Color(0xD2FAFB));
        btnAdd.setForeground(new Color(0xD2FAFB));
        btnFind.setForeground(new Color(0xD2FAFB));
        btnDelete.setForeground(new Color(0xD2FAFB));


        btnFind.addActionListener(this);
        btnEdit.addActionListener(this);
        btnReset.addActionListener(this);
        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);

        btnEdit.setIcon(new ImageIcon("images2/edit.png"));
        btnFind.setIcon(new ImageIcon("images2/search.png"));
        btnReset.setIcon(new ImageIcon("images2/reset.png"));
        btnAdd.setIcon(new ImageIcon("images2/add.png"));
        btnDelete.setIcon(new ImageIcon("images2/delete.png"));

        btnEdit.setBounds(25,230,120,40);
        btnAdd.setBounds(25,280,120,40);
        btnReset.setBounds(25,330,120,40);
        btnDelete.setBounds(25,380,120,40);
        btnFind.setBounds(0,0,107,30);
        tfFind.setBounds(107,0,793,30);

        btnEdit.setBackground(new Color(0x126E82));
        btnAdd.setBackground(new Color(0x126E82));
        btnReset.setBackground(new Color(0x126E82));
        btnFind.setBackground(new Color(0x126E82));
        btnDelete.setBackground(new Color(0x126E82));

        jScrollPane = new JScrollPane();
        tableHocVien = new JTable();

        jScrollPane.setBounds(0,30,900,710);


        tableHocVien.addMouseListener(this);


        panel1.setBounds(0,0,905,740);
        panel1.setLayout(null);
        panel1.add(jScrollPane);
        panel1.add(btnFind); panel1.add(tfFind);


        panel2.setBounds(905,0,170,740);
        panel2.setLayout(null);
        panel2.setBackground(new Color(0xB1D0E0));
        panel2.add(btnEdit);
        panel2.add(btnReset);
        panel2.add(btnAdd);
        panel2.add(btnDelete);
        add(panel1);
        add(panel2);
        this.setBackground(new Color(208, 140, 175));
        this.setLayout(null);
        this.setVisible(true);

    }

    public void initTable() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Số thứ tự","Tên học viên","Ngày sinh", "Tên trung tâm","Số điện thoại","Địa chỉ"});
        tableHocVien.setModel(tableModel);
        jScrollPane.setViewportView(tableHocVien);
    }
    public  void loadAll(){
        try{
            Connection connection = DatabaseHepler.openConnection();
            String sql = "select id, fullname, birthday, trungtam, sdt, address from DiemHocVien";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            tableModel.setRowCount(0);
            while(rs.next()){
                String[] row = new String[]{
                        String.valueOf(tableModel.getRowCount()+1),rs.getString("fullname"),rs.getString("birthday")
                ,rs.getString("trungtam"), rs.getString("sdt"),rs.getString("address")
                };
                tableModel.addRow(row);
            }
            tableModel.fireTableDataChanged();
            rs.close();
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    public void findName() {
        String input = tfFind.getText();
        if(input != null && input.length() > 0){
            list = hocVienDAO.findByName(input);
            tableModel.setRowCount(0);
            list.forEach((diem) -> {
                tableModel.addRow(new Object[]{tableModel.getRowCount()+1, diem.getFullname(), diem.getBirthday(),diem.getTrungtam(),diem.getSdt(),diem.getAddress()});
            } );
        }
        else {
            loadAll();
        }
    }

    public void deleteList() {
        if(selectedRow.length() > 0){
            int option = JOptionPane.showConfirmDialog(this, "Do you want to delete this item?");
            if(option == 0) {
                hocVienDAO.delete(Integer.parseInt(selectedRow));
                loadAll();
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Mời bạn nhọn hàng muốn xóa");
        }
    }
    public int rowCount(){
        return tableModel.getRowCount() + 1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnAdd){
            new HocVienForm(this,"Insert Form","","","","","","");

        }
        if(e.getSource()==btnEdit) {
            if (selectedRow == null) {
                JOptionPane.showMessageDialog(this,"Mời bạn chọn hàng muốn sửa");
            } else {
                Diem diem  = hocVienDAO.getByID(Integer.parseInt(selectedRow));
                new HocVienForm(this,"Edit Form", selectedRow,diem.getFullname(),diem.getBirthday(),diem.getTrungtam(),diem.getSdt(),diem.getAddress());
            }
        }
        if(e.getSource()==btnFind){
            try {
                findName();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            }
        }
        if(e.getSource()==btnDelete){
            deleteList();
        }
        if(e.getSource()==btnReset){
            loadAll();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = tableHocVien.rowAtPoint(e.getPoint());
        selectedRow = tableHocVien.getValueAt(id, 0).toString();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
