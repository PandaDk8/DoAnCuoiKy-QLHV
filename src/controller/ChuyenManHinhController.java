package controller;

import Bean.DanhMucBean;
import View.DiemJPanel;
import View.HocVienJPanel;
import View.ThongKeTrungTam;
import View.TrungTamJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ChuyenManHinhController {
    private JPanel root;
    private  String kindSelected = "";
    private List<DanhMucBean> listItem=null;
    public ChuyenManHinhController(JPanel jpnRoot){
        this.root = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem){
        kindSelected = "HocVien";
        jpnItem.setBackground(new Color(96,100,91));
        jlbItem.setBackground(new Color(96,100,91));
        root.removeAll();
        root.setLayout((new BorderLayout()));
        root.add(new DiemJPanel());
        root.validate();
        root.repaint();
    }
    public void setEvent(List<DanhMucBean> listItem){
        this.listItem = listItem;
        for (DanhMucBean item:listItem) {
            item.getJlb().addMouseListener((new labelEven(item.getKind(),item.getJpn(),item.getJlb())));
        }
    }
    class labelEven implements MouseListener{

        private JPanel node;
        private  String kind;

        private JPanel jpnItem;
        private JLabel lbItem;

        public labelEven(String kind, JPanel jpnItem, JLabel lbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.lbItem = lbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind){
                case "HocVien":
                    node = new HocVienJPanel(); break;
                case "TrungTam":node = new TrungTamJPanel();break;
                case "ThongKe":node = new ThongKeTrungTam(); break;
                case "Diem":node = new DiemJPanel();break;
                default: break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackGroud(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            jpnItem.setBackground(new Color(255, 239, 239));
            lbItem.setBackground(new Color(255, 239, 239));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(255, 239, 239));
            lbItem.setBackground(new Color(255, 239, 239));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(kindSelected.equalsIgnoreCase(kind)){
                jpnItem.setBackground(new Color(105, 152, 171));
                lbItem.setBackground(new Color(105, 152, 171));
            }
        }
    }
    private void setChangeBackGroud(String kind){
        for(DanhMucBean item : listItem){
            if(item.getKind().equalsIgnoreCase(kind)){
                item.getJpn().setBackground(new Color(0, 242, 201));
                item.getJlb().setBackground(new Color(0, 242, 201));
            }
            else {
                item.getJpn().setBackground(new Color(105, 152, 171));
                item.getJlb().setBackground(new Color(105, 152, 171));
            }
        }
    }
}
