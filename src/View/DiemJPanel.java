package View;

import DAO.DatabaseHepler;
import DAO.DiemDAO;
import Model.Diem;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DiemJPanel extends JPanel implements ActionListener, MouseListener {

    private JButton btnFind;
    private  JButton btnEdit;
    private  JButton btnReset;
    private  JButton btnAdd;

    private JTextField tfFind;

    private JPanel panel1, panel2, panel3;

    JScrollPane jScrollPane;
    JTable tableHocVien;
    DefaultTableModel tableModel;
    DiemDAO hocVienDAO = new DiemDAO();
    String selectedRow;
    List<Diem> diemList = new ArrayList<>();
    public DiemJPanel(){
        init();
        initTable();
        loadAllEmployees();
    }
    public void init(){
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        tfFind = new JTextField();

        btnEdit = new JButton("Sửa");
        btnFind = new JButton("Tìm");
        btnReset = new JButton("Reset");
        btnAdd = new JButton("Sắp xếp");

        btnEdit.setForeground(new Color(0xD2FAFB));
        btnReset.setForeground(new Color(0xD2FAFB));
        btnAdd.setForeground(new Color(0xD2FAFB));
        btnFind.setForeground(new Color(0xD2FAFB));


        btnFind.addActionListener(this);
        btnEdit.addActionListener(this);
        btnReset.addActionListener(this);
        btnAdd.addActionListener(this);

        btnEdit.setIcon(new ImageIcon("images2/edit.png"));
        btnFind.setIcon(new ImageIcon("images2/search.png"));
        btnReset.setIcon(new ImageIcon("images2/reset.png"));
        btnAdd.setIcon(new ImageIcon("images2/sort.png"));

        btnEdit.setBounds(25,240,120,40);
        btnAdd.setBounds(25,300,120,40);
        btnReset.setBounds(25,360,120,40);
        btnFind.setBounds(0,0,107,30);
        tfFind.setBounds(107,0,793,30);

        btnEdit.setBackground(new Color(0x126E82));
        btnAdd.setBackground(new Color(0x126E82));
        btnReset.setBackground(new Color(0x126E82));
        btnFind.setBackground(new Color(0x126E82));


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
        add(panel1);
        add(panel2);
        this.setBackground(new Color(208, 140, 175));
        this.setLayout(null);
        this.setVisible(true);

    }

    public void initTable() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Số thứ tự","Tên học viên","Ngày sinh","Trung tâm", "Điểm chuyên cần","Điểm bài tập","Điểm giữa kỳ","Điểm cuối kỳ","Điểm trung bình","Xếp loại"});
        tableHocVien.setModel(tableModel);
        jScrollPane.setViewportView(tableHocVien);
    }
    public  void loadAllEmployees(){
        try{
            Connection connection = DatabaseHepler.openConnection();
            String sql = "select * from DiemHocVien";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            tableModel.setRowCount(0);
            while(rs.next()){
                String[] row = new String[]{
                        String.valueOf(tableModel.getRowCount()+1),rs.getString("fullname"), String.valueOf(rs.getString("birthday")),
                        rs.getString("trungtam"), String.valueOf(rs.getFloat("chuyencan")), String.valueOf(rs.getFloat("baitap")),
                        String.valueOf(rs.getFloat("giuaky")), String.valueOf(rs.getFloat("cuoiky")), String.valueOf(rs.getFloat("diemtb")),
                        rs.getString("xeploai")
                };
                tableModel.addRow(row);
            }
            tableModel.fireTableDataChanged();
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sortByAver(){
        diemList = hocVienDAO.findAll();
        Comparator<Diem> comparator = (o1, o2) -> Math.round(o2.getDiemtb() - o1.getDiemtb());
        tableModel.setRowCount(0);
        Collections.sort(diemList, comparator);
        for(Diem diem : diemList) {
            tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, diem.getFullname(), diem.getBirthday(), diem.getTrungtam(), diem.getChuyencan(), diem.getBaitap(), diem.getGiuaky()
                    , diem.getCuoiky(), diem.getDiemtb(), diem.getXeploai()});
        }
    }

    public void findName(){
        String input = tfFind.getText();
        if(input != null && input.length() > 0){
            diemList = hocVienDAO.findByName(input);
            tableModel.setRowCount(0);
            diemList.forEach((diem) -> {
                tableModel.addRow(new Object[]{tableModel.getRowCount()+1, diem.getFullname(), diem.getBirthday(), diem.getTrungtam(), diem.getChuyencan(), diem.getBaitap(), diem.getGiuaky()
                        , diem.getCuoiky(), diem.getDiemtb(), diem.getXeploai()});
            } );
        }
        else {
            loadAllEmployees();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnEdit) {
            if (selectedRow == null) {
                    JOptionPane.showMessageDialog(this,"Mời bạn chọn hàng muốn sửa");
            } else {
                Diem diem = null;
                diem = hocVienDAO.getHocVienByID(Integer.parseInt(selectedRow));
                new DiemEditForm(this, selectedRow,
                        diem.getFullname(), diem.getBirthday(), diem.getChuyencan(), diem.getBaitap(), diem.getGiuaky(), diem.getCuoiky(), diem.getDiemtb(), diem.getXeploai());
            }
        }
        if(e.getSource()==btnFind){
            findName();
        }
        if(e.getSource()==btnAdd){
            sortByAver();
        }
        if(e.getSource()==btnReset){
            loadAllEmployees();
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
