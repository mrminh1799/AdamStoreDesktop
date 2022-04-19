/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ui;

import Business.DTO.ChartDTO;
import Business.Sevice.StatisticalBussinesService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger; 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class ChartStatistical {

    public static JFreeChart createChart(int year) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ doanh thu trong năm " + year,
                "Tháng", "VND",
                createDataset(year), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private static CategoryDataset createDataset(int year) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        StatisticalBussinesService service = null;
        try {
            service = new StatisticalBussinesService();

        } catch (SQLException ex) {
            Logger.getLogger(ChartStatistical.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<ChartDTO> list = service.getListForChart(year);
        if(list.size()!=0){
            for(ChartDTO x: list){
            dataset.addValue(x.getSumPrice(), "VND", x.getMonth());
            }
        }
        
        
        //dataset.addValue(1000, "VND", "1990");
        return dataset;
    }

//    public static void main(String[] args) {
//        ChartPanel chartPanel = new ChartPanel(createChart());
//        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
//        JFrame frame = new JFrame();
//        frame.add(chartPanel);
//        frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
//        frame.setSize(600, 400);
//        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);
//        frame.setVisible(true);
//    }
}
