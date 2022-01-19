package View;

import DAO.TrungTamDAO;
import Model.TrungTam;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ThongKeTrungTam extends JPanel {
    public ThongKeTrungTam(){
        createChart();
        initComponents();
    }
    public JFreeChart createChart(){
        TrungTamDAO trungTamDAO = new TrungTamDAO();
        List<TrungTam> list  = new ArrayList<>();
        list = trungTamDAO.getData();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if(list!=null){
            for (TrungTam trungTam:list) {
                dataset.addValue(trungTam.getSoluong(), "Số lượng học viên", trungTam.getName());
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Số lượng sinh viên của mỗi trung tâm",
                "Trung tâm", "Số lượng học viên",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        return barChart;
    }



    public void initComponents(){
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setBounds(10,20,1050,700);
        this.setLayout(null);

        this.add(chartPanel);
        this.setVisible(true);
    }
}
