/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Sevice;

import Business.DTO.ChartDTO;
import Business.DTO.StatisticalDTO;
import Utils.JdbcHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minh
 */
public class StatisticalBussinesService {

      private static String driver = JdbcHelper.driver;
        private static String dburl = JdbcHelper.dburl;

      private static String username = JdbcHelper.username;
    private static String password = JdbcHelper.password;
    Connection connection;

    SimpleDateFormat formatter;

    public StatisticalBussinesService() throws SQLException {

        formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dburl, username, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StatisticalBussinesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<StatisticalDTO> getListToDay() {
        String sql = " select  Product.TenSanPham , SUM(Detailed_Invoice.SoLuong)  as N'SoLuong' ,SUM (Detailed_Invoice.DonGia* Detailed_Invoice.SoLuong)  as N'TongTien'  "
                + "  from Detailed_Invoice inner join Product_Detail on Product_Detail.MaSanPhamChiTiet = Detailed_Invoice.MaSanPhamChiTiet "
                + "  inner join Product on Product.MaSanPham = Product_Detail.MaSanPham "
                + "  inner join Invoice on  Invoice.MaHoaDon = Detailed_Invoice.MaHoaDon "
                + "  where  Invoice.NgayBan = ? "
                + "  group by Product.TenSanPham ";
        List<StatisticalDTO> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            Date date = new Date(System.currentTimeMillis());
            ps.setString(1, formatter.format(date));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("TenSanPham");
                int quantity = rs.getInt("SoLuong");
                int sumPrice = rs.getInt("TongTien");
                StatisticalDTO e = new StatisticalDTO(productName, quantity, sumPrice);
                list.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StatisticalBussinesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<StatisticalDTO> getListInterval(String formDate, String todate) {
        String sql = " select  Product.TenSanPham , SUM(Detailed_Invoice.SoLuong)  as N'SoLuong' ,SUM (Detailed_Invoice.DonGia* Detailed_Invoice.SoLuong)  as N'TongTien'  "
                + "  from Detailed_Invoice inner join Product_Detail on Product_Detail.MaSanPhamChiTiet = Detailed_Invoice.MaSanPhamChiTiet "
                + "  inner join Product on Product.MaSanPham = Product_Detail.MaSanPham "
                + "  inner join Invoice on  Invoice.MaHoaDon = Detailed_Invoice.MaHoaDon "
                + "  where  Invoice.NgayBan >= ? and  Invoice.NgayBan <= ? "
                + "  group by Product.TenSanPham ";
        List<StatisticalDTO> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, formDate);
            ps.setString(2, todate);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("TenSanPham");
                int quantity = rs.getInt("SoLuong");
                int sumPrice = rs.getInt("TongTien");
                StatisticalDTO e = new StatisticalDTO(productName, quantity, sumPrice);
                list.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StatisticalBussinesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ChartDTO> getListForChart(int year) {

        String sql = "     select MONTH(Invoice.NgayBan) as 'Thang', SUM (Detailed_Invoice.DonGia* Detailed_Invoice.SoLuong)   "
                + "  as N'TongTien'  "
                + "  from Detailed_Invoice inner join Product_Detail on Product_Detail.MaSanPhamChiTiet = Detailed_Invoice.MaSanPhamChiTiet  "
                + "  inner join Product on Product.MaSanPham = Product_Detail.MaSanPham  "
                + "  inner join Invoice on  Invoice.MaHoaDon = Detailed_Invoice.MaHoaDon  "
                + "  where   YEAR(Invoice.NgayBan) = ?   "
                + "  group by MONTH(Invoice.NgayBan)";
        List<ChartDTO> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, year);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String month = rs.getString("Thang");
                int sumPrice = rs.getInt("TongTien");
                ChartDTO e = new ChartDTO(month, sumPrice);
                list.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StatisticalBussinesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

}
