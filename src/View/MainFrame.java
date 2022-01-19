package View;

import Bean.DanhMucBean;
import controller.ChuyenManHinhController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame{
    private JPanel jpnMenu, jpnView;
    private JPanel jpnAcc, jpnDiem,jpnHocVien, jpnTrungTam, jpnThongKe;
    private JLabel lbAcc,lbDiem, lbHocVien, lbTrungTam , lbThongKe;
    public MainFrame(){
        init();

        setTitle("QUẢN LÍ HỌC VIÊN");

        ChuyenManHinhController controller = new ChuyenManHinhController(jpnView);
        controller.setView( jpnDiem,lbDiem);

        List<DanhMucBean> listDanhMuc = new ArrayList<>();
        listDanhMuc.add(new DanhMucBean("Diem", jpnDiem, lbDiem));
        listDanhMuc.add(new DanhMucBean("HocVien", jpnHocVien, lbHocVien));
        listDanhMuc.add(new DanhMucBean("TrungTam", jpnTrungTam, lbTrungTam));
        listDanhMuc.add(new DanhMucBean("ThongKe",jpnThongKe,lbThongKe));

        controller.setEvent(listDanhMuc);


    }
    public void init(){


        jpnMenu = new JPanel();
        jpnView = new JPanel();
        jpnAcc = new JPanel();
        jpnDiem = new JPanel();
        jpnHocVien = new JPanel();
        jpnTrungTam = new JPanel();
        jpnThongKe = new JPanel();

        lbAcc = new JLabel("QUẢN LÍ HỌC VIÊN");
        lbDiem = new JLabel("Quản lí điểm");
        lbHocVien = new JLabel("Học viên");
        lbTrungTam = new JLabel("Trung tâm");
        lbThongKe = new JLabel("Thống kê");


        lbAcc.setFont(new Font("Arial", Font.BOLD, 25));
        lbHocVien.setFont(new Font("Arial", Font.BOLD, 30));
        lbTrungTam.setFont(new Font("Arial", Font.BOLD, 30));
        lbDiem.setFont(new Font("Arial", Font.BOLD, 30));
        lbThongKe.setFont(new Font("Arial", Font.BOLD, 30));


        lbAcc.setForeground(Color.WHITE);
        lbHocVien.setForeground(new Color(0x041C32));
        lbTrungTam.setForeground(new Color(0x041C32));
        lbDiem.setForeground(new Color(0x041C32));
        lbThongKe.setForeground(new Color(0x041C32));

        lbAcc.setIcon(new ImageIcon("images2/acc.png"));
        lbHocVien.setIcon(new ImageIcon("images2/hocvien.png"));
        lbDiem.setIcon(new ImageIcon("images2/diem.png"));
        lbTrungTam.setIcon(new ImageIcon("images2/trungtam.png"));
        lbThongKe.setIcon(new ImageIcon("images2/thongke.png"));


        jpnMenu.setBounds(0,0,400,800);
        jpnView.setBounds(410,10,1200,740);
        jpnAcc.setBounds(0,0,400,140);
        jpnDiem.setBounds(25,160,350,75);
        jpnHocVien.setBounds(25,260,350,75);
        jpnTrungTam.setBounds(25,360,350,75);
        jpnThongKe.setBounds(25,460,350,75);


        jpnMenu.setBackground(new Color(26, 55, 77));
        jpnAcc.setBackground(new Color(64, 104, 130));
        jpnHocVien.setBackground(new Color(105, 152, 171));
        jpnDiem.setBackground(new Color(105, 152, 171));
        jpnTrungTam.setBackground(new Color(105, 152, 171));
        jpnThongKe.setBackground(new Color(105, 152, 171));

        jpnMenu.setLayout(null);
        jpnView.setLayout(null);
        jpnAcc.setLayout(new BorderLayout());
        jpnHocVien.setLayout(new BorderLayout());
        jpnDiem.setLayout(new BorderLayout());
        jpnTrungTam.setLayout(new BorderLayout());
        jpnThongKe.setLayout(new BorderLayout());

        jpnMenu.add(jpnAcc); jpnAcc.add(lbAcc, BorderLayout.CENTER);
        jpnMenu.add(jpnHocVien); jpnHocVien.add(lbHocVien, BorderLayout.CENTER);
        jpnMenu.add(jpnDiem); jpnDiem.add(lbDiem, BorderLayout.CENTER);
        jpnMenu.add(jpnTrungTam); jpnTrungTam.add(lbTrungTam, BorderLayout.CENTER);
        jpnMenu.add(jpnThongKe); jpnThongKe.add(lbThongKe, BorderLayout.CENTER);



        this.add(jpnMenu);
        this.add(jpnView);

        this.setLayout(null);
        this.setSize(1500,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

}
