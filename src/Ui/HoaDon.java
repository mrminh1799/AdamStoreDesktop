/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ui;

import Business.DTO.OrderFileDTO;
import DAL.Entity.Client;
import DAL.Entity.Invoice;
import DAL.Entity.InvoiceDetail;
import DAL.Service.ClientService;
import DAL.Service.InvoiceDetailService;
import DAL.Service.InvoiceService; 
import Ui.Model.ClientUIMoDel;
import Ui.Model.InvoDtailUI;

import Ui.Model.InvoUI;
import Ui.Model.InvoiceUI;
import Ui.Model.ProductDetailUI;
import Utils.BoxDiaglog;
import Utils.DateHelper;
import Utils.JdbcHelper;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//clearSelection()  bỏ chọn hàng
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class HoaDon extends javax.swing.JFrame {

    /**
     * Creates new form adMin
     */
      private static String driver = JdbcHelper.driver;
        private static String dburl = JdbcHelper.dburl;

      private static String username = JdbcHelper.username;
    private static String password = JdbcHelper.password;
    DefaultTableModel model;
    List<ProductDetailUI> list;
    int index1 = -1;
    DefaultTableModel model2;
    List<ProductDetailUI> listTam = new ArrayList<>();
    InvoiceDetailService srInvD;

    ClientService srClientService;
    InvoiceService srInv;
    DateHelper DATE_FORMATER;
    public List<ClientUIMoDel> list1;
    public List<InvoUI> list2;
    int idValue;
    List<InvoiceUI> list3;
    DefaultTableModel model3;
    int index2 = -1;
    int index3 = -1;
    int index4 = -1;
    List<InvoDtailUI> list4 = new ArrayList<>();
    int a = 0;
    int idValue2;
    float tong = 0;
    public static String taiKhoan, maNV, tenNV, passNV;
    int bienTable3 = -1;
    private JFileChooser chooser;

    public HoaDon(String taiKhoan, String maNV, String tenNV, String passNV) {

        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        initComponents();
        setLocationRelativeTo(null);
        srInvD = new InvoiceDetailService();
        srInv = new InvoiceService();
        srClientService = new ClientService();
        model = (DefaultTableModel) jTable1.getModel();
        model2 = (DefaultTableModel) tb2.getModel();
        model3 = (DefaultTableModel) jTable3.getModel();
        model2.setRowCount(0);
        this.taiKhoan = taiKhoan;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.passNV = passNV;
        tfMaNV.setText(maNV);
        System.out.println(cbb1.getSelectedItem().toString());
        try {
            list = getListSP();
            list3 = getList3();
            lbGiamGia.setText(String.valueOf(list.get(1).getPhanTram()) + " %");
//        model.setColumnIdentifiers(new Object[]{
//            "STT", "ID", "Full Name", "Date of birth", "Address", "Phone number", "Email", "AVG"
//        });
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        showTable();
        showTable2();

    }

//    public List<ProductDetailUI> getListSP() throws ClassNotFoundException, SQLException {
//        Class.forName(driver);
//        Connection conn = DriverManager.getConnection(dburl, username, password);
//        List<ProductDetailUI> list = new ArrayList<>();
//        String sql = "	select e.MaTheLoai, TenTheLoai, a.MaSanPham, a.TenSanPham, MaSanPhamChiTiet, c.maMau, c.temMau, d.maSize, d.TenSize, SoLuong, GiaBan\n"
//                + "			from Product a inner join Product_Detail b on a.MaSanPham = b.MaSanPham\n"
//                + "						   inner join Color c on c.maMau = b.maMau\n"
//                + "						   inner join Size d on d.maSize = b.maSize\n"
//                + "						   inner join Category e on e.MaTheLoai = b.MaTheLoai";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductDetailUI sp = new ProductDetailUI();
//                sp.setMaLoaiSp(rs.getString("MaTheLoai"));
//                sp.setTenLoai(rs.getString("TenTheLoai"));
//                sp.setMaSP(rs.getString("MaSanPham"));
//                sp.setTenSP(rs.getString("TenSanPham"));
//                sp.setMaSPCT(rs.getString("MaSanPhamChiTiet"));
//                sp.setMaMau(rs.getInt("maMau"));
//                sp.setMau(rs.getString("temMau"));
//                sp.setMaSize(rs.getInt("maSize"));
//                sp.setSize(rs.getString("TenSize"));
//                sp.setSoLuong(rs.getInt("SoLuong"));
//                sp.setGiaBan(rs.getDouble("GiaBan"));
//
//                list.add(sp);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    public List<ProductDetailUI> getListSP() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        List<ProductDetailUI> list = new ArrayList<>();
        String sql = "	select e.MaTheLoai, TenTheLoai, a.MaSanPham, a.TenSanPham,"
                + " MaSanPhamChiTiet, c.maMau, c.temMau, d.maSize, d.TenSize, SoLuong, GiaBan,"
                + " a.MaGiamGia, PhanTram,NgayBatDau,NgayKetThuc\n"
                + "			from Product a inner join Product_Detail b on a.MaSanPham = b.MaSanPham\n"
                + "						   inner join Color c on c.maMau = b.maMau\n"
                + "						   inner join Size d on d.maSize = b.maSize\n"
                + "						   inner join Category e on e.MaTheLoai = b.MaTheLoai\n"
                + "						   inner join GiamGia f on f.MaGiamGia = a.MaGiamGia";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDetailUI sp = new ProductDetailUI();
                sp.setMaLoaiSp(rs.getString("MaTheLoai"));
                sp.setTenLoai(rs.getString("TenTheLoai"));
                sp.setMaSP(rs.getString("MaSanPham"));
                sp.setTenSP(rs.getString("TenSanPham"));
                sp.setMaSPCT(rs.getString("MaSanPhamChiTiet"));
                sp.setMaMau(rs.getInt("maMau"));
                sp.setMau(rs.getString("temMau"));
                sp.setMaSize(rs.getInt("maSize"));
                sp.setSize(rs.getString("TenSize"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGiaBan(rs.getDouble("GiaBan"));
                sp.setMaGiamGia(rs.getByte("MaGiamGia"));
                sp.setPhanTram(rs.getFloat("PhanTram"));
                sp.setNgayBatDau(rs.getDate("NgayBatDau"));
                sp.setNgayKetThuc(rs.getDate("NgayKetThuc"));

                list.add(sp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void showTable() {
        model.setRowCount(0);
        for (ProductDetailUI sp : list) {

            model.addRow(new Object[]{
                sp.getMaSP(), sp.getTenSP(), sp.getTenLoai(), sp.getSize(), sp.getMau(), sp.getGiaBan(), sp.getSoLuong(),});
        }
    }

    public void checkChonSp(int index) {
        if (index < 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn sản phẩm cần bán");
            return;
        }

    }

    public void checkHD(int index) {
        if (index < 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn sản phẩm cần bán");
            return;
        }

    }

//    public void getListTam(int index, int sl) {
//
//        ProductDetailUI sp = new ProductDetailUI();
//        sp.setMaLoaiSp(list.get(index).getMaLoaiSp());
//        sp.setTenLoai(list.get(index).getTenLoai());
//        sp.setMaSP(list.get(index).getMaSP());
//        sp.setTenSP(list.get(index).getTenSP());
//        sp.setMaSPCT(list.get(index).getMaSPCT());
//        sp.setMaMau(list.get(index).getMaMau());
//        sp.setMau(list.get(index).getMau());
//        sp.setMaSize(list.get(index).getMaSize());
//        sp.setSize(list.get(index).getSize());
//        sp.setSoLuong(list.get(index).getSoLuong());
//        sp.setGiaBan(list.get(index).getGiaBan());
//        sp.setSoLuongBan(sl);
//        listTam.add(sp);
//    }
    public void getListTam(int index, int sl) {

        ProductDetailUI sp = new ProductDetailUI();
        sp.setMaLoaiSp(list.get(index).getMaLoaiSp());
        sp.setTenLoai(list.get(index).getTenLoai());
        sp.setMaSP(list.get(index).getMaSP());
        sp.setTenSP(list.get(index).getTenSP());
        sp.setMaSPCT(list.get(index).getMaSPCT());
        sp.setMaMau(list.get(index).getMaMau());
        sp.setMau(list.get(index).getMau());
        sp.setMaSize(list.get(index).getMaSize());
        sp.setSize(list.get(index).getSize());
        sp.setSoLuong(list.get(index).getSoLuong());
        sp.setGiaBan(list.get(index).getGiaBan());
        sp.setMaGiamGia(list.get(index).getMaGiamGia());
        sp.setPhanTram(list.get(index).getPhanTram());
        sp.setNgayBatDau(list.get(index).getNgayBatDau());
        sp.setNgayKetThuc(list.get(index).getNgayKetThuc());
        sp.setSoLuongBan(sl);
        listTam.add(sp);

    }
//    public float inHD() {
//
//        int a;
//        float b;
//        float c;
//        float tong = 0;
//        for (int i = 0; i < listTam.size(); i++) {
//            a = (int) listTam.get(i).getGiaBan();
//            b = (float) listTam.get(i).getSoLuongBan();
//            c = a * b;
//            tong = tong + c;
//
//        }
//
//        return tong;
//    }

    public float inHD() {

        int a;
        float b;
        float c;
        float tong = 0;
        for (int i = 0; i < listTam.size(); i++) {
            float d = listTam.get(i).getPhanTram();
            float e = 100 - d;
            if (d == 0) {

                a = (int) listTam.get(i).getGiaBan();
                b = (float) listTam.get(i).getSoLuongBan();
                c = a * b;
                tong = tong + c;
                System.out.println("d = 0");
            } else {
                a = (int) listTam.get(i).getGiaBan();
                b = (float) listTam.get(i).getSoLuongBan();
                c = a * b * e / 100;
                tong = tong + c;
                System.out.println("d >0");
            }

        }

        return tong;
    }

    Client getModel() {
        Client entity = new Client();
//        entity.setMaKhachHang(tfMaKH.getText());
        entity.setTenKhachHang(tfTenKH.getText());
        entity.setDiaChi(tfDC.getText());
        entity.setDienThoai(tfSĐT.getText());

        return entity;

    }

    public float inHD2() {

        int a;
        float b;
        float c;
        float tong = 0;
        for (int i = 0; i < list4.size(); i++) {
            float d = list4.get(i).getPhanTram();
            float e = 100 - d;
            if (d == 0) {

                a = (int) list4.get(i).getDonGia();
                b = (float) list4.get(i).getSoLuong();
                c = a * b;
                tong = tong + c;
                System.out.println("d = 0");
            } else {
                a = (int) list4.get(i).getDonGia();
                b = (float) list4.get(i).getSoLuong();
                c = a * b * e / 100;
                tong = tong + c;
                System.out.println("d >0");
            }

        }

        return tong;
    }

    public void addInvoice() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        String sql = "INSERT INTO Invoice(MaNhanVien, NgayBan, MaKhachHang, TongTien, TrangThai, GhiChu) "
                + "VALUES(?,?,?,?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pstm.setString(1, maNV);
        pstm.setDate(2, new java.sql.Date(System.currentTimeMillis()));
        pstm.setString(3, String.valueOf(idValue2));
        pstm.setFloat(4, inHD());
        pstm.setString(5, "Đã thanh toán");
        pstm.setString(6, tfGhiChu.getText());
        pstm.execute();

        ResultSet rs = pstm.getGeneratedKeys();

        if (rs.next()) {

            idValue = rs.getInt(1);
        }

        System.out.println("ID value: " + idValue);

    }

    public void addInvoice2() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        String sql = "INSERT INTO Invoice(MaNhanVien, NgayBan, MaKhachHang, TongTien, TrangThai, GhiChu) "
                + "VALUES(?,?,?,?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

//        pstm.setString(1, maNV);
//        
//        pstm.setDate(2, new java.sql.Date(System.currentTimeMillis()));
//        pstm.setFloat(3, inHD());
//        pstm.setString(4, "Đã thanh toán");
//        pstm.setString(5, tfGhiChu.getText());
        pstm.setString(1, maNV);
        pstm.setDate(2, new java.sql.Date(System.currentTimeMillis()));
        pstm.setString(3, String.valueOf(idValue2));
        pstm.setFloat(4, inHD());
        pstm.setString(5, "Đang chờ");
        pstm.setString(6, tfGhiChu.getText());
        pstm.execute();

        ResultSet rs = pstm.getGeneratedKeys();

        if (rs.next()) {
            // Giá trị của ID.
            // Chú ý với một số DB, tên cột phân biệt chữ hoa chữ thường.
            // (Ví dụ Postgres, tên cột luôn luôn là chữ thường).
            idValue = rs.getInt(1);
        }

        System.out.println("ID value: " + idValue);
//        System.out.println(listTam.toString());

    }

    Invoice getModel(String x, String z, float k, int y) {
        Invoice entity = new Invoice();
        entity.setTrangThai(x);
        entity.setGhiChu(z);
        entity.setTongTien(k);
        entity.setMaHoaDon(y);
        return entity;
    }

    InvoiceDetail getModel33(String x, String z, int y) {
        InvoiceDetail entity = new InvoiceDetail();
        entity.setTrangThai(x);
        entity.setGhiChu(z);
        entity.setMaHDCT(y);
        return entity;
    }

    public void updateInvoice() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        String sql = "update  Invoice  set MaNhanVien =? ,NgayBan=?, MaKhachHang =?, TongTien=? where MaHoaDon =?";

        PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pstm.setString(1, maNV);
        pstm.setDate(2, new java.sql.Date(System.currentTimeMillis()));
        pstm.setString(3, String.valueOf(idValue2));
        pstm.setFloat(4, inHD());
        pstm.setString(5, "Đã thanh toán");
        pstm.setString(6, tfGhiChu.getText());
        pstm.execute();

        ResultSet rs = pstm.getGeneratedKeys();

        if (rs.next()) {

            idValue = rs.getInt(1);
        }

        System.out.println("ID value: " + idValue);

    }

    public void addClient() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        String sql = "insert into Client( TenKhachHang, DiaChi, DienThoai) values ( ?,?,?);";

        PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pstm.setString(1, tfTenKH.getText());
        pstm.setString(2, tfDC.getText());
        pstm.setString(3, tfSĐT.getText());
        pstm.execute();

        ResultSet rs = pstm.getGeneratedKeys();

        if (rs.next()) {
            // Giá trị của ID.
            // Chú ý với một số DB, tên cột phân biệt chữ hoa chữ thường.
            // (Ví dụ Postgres, tên cột luôn luôn là chữ thường).
            idValue2 = rs.getInt(1);
        }

        System.out.println("ID value2: " + idValue2);
//        System.out.println(listTam.toString());

    }

//    }
    InvoiceDetail getModel2(int index) {
        InvoiceDetail entity = new InvoiceDetail();
        entity.setMaHoaDon(idValue);

        entity.setMaSanPhamChiTiet(listTam.get(index).getMaSPCT());

        entity.setSoLuong(listTam.get(index).getSoLuongBan());

        entity.setDonGia((float) listTam.get(index).getGiaBan());
        entity.setTrangThai("Đã bán");

        entity.setGiamGia(0);

        int a;
        float b;
        float c;

        a = (int) listTam.get(index).getGiaBan();
        b = (float) listTam.get(index).getSoLuongBan();
        c = a * b;
        entity.setTong(c);

        return entity;

    }

    public void showTable2() {
        model3.setRowCount(0);
        for (InvoiceUI sp : list3) {

            model3.addRow(new Object[]{
                sp.getMaHoaDon(), sp.getTenKhachHang(), sp.getDienThoai(), sp.getNgayBan(), sp.getMaNhanVien(), sp.getTrangThai(), sp.getGhiChu(),});
        }
    }

    public void showTable3() {
        model2.setRowCount(0);
        for (ProductDetailUI sp : listTam) {

            model2.addRow(new Object[]{
                sp.getMaSP(), sp.getTenSP(), sp.getTenLoai(), sp.getGiaBan(),
                sp.getSize(), sp.getMau(), sp.getSoLuongBan(),});

        }
    }

    public void showTable33() {
        model2.setRowCount(0);
        for (InvoDtailUI sp : list4) {

            model2.addRow(new Object[]{
                sp.getMaSP(), sp.getTenSP(), sp.getTenTheLoai(), sp.getDonGia(),
                sp.getTenSize(), sp.getTemMau(), sp.getSoLuong(),});

        }
    }

//    public List<InvoiceUI> getList3() throws ClassNotFoundException, SQLException {
//        Class.forName(driver);
//        Connection conn = DriverManager.getConnection(dburl, username, password);
//        List<InvoiceUI> list = new ArrayList<>();
//        String sql = "select MaHoaDon, a.MaNhanVien,b.TenNhanVien,NgayBan, a.MaKhachHang,c.TenKhachHang,c.DiaChi,c.DienThoai, TongTien, a.TrangThai, GhiChu\n"
//                + "from Invoice a inner join Staff b on a.MaNhanVien = b.MaNhanVien \n"
//                + "				inner join Client c on c.MaKhachHang = a.MaKhachHang";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//
//                InvoiceUI x = new InvoiceUI();
//                x.setMaHoaDon(rs.getInt("MaHoaDon"));
//                x.setMaNhanVien(rs.getString("MaNhanVien"));
//                x.setTenNhanVien(rs.getString("TenNhanVien"));
//                x.setMaKhachHang(rs.getString("MaKhachHang"));
//                x.setTenKhachHang(rs.getString("TenKhachHang"));
//                x.setDiaChi(rs.getString("DiaChi"));
//                x.setDienThoai(rs.getString("DienThoai"));
//                x.setNgayBan(rs.getDate("NgayBan"));
//                x.setTongTien(rs.getFloat("TongTien"));
//                x.setTrangThai(rs.getInt("TrangThai"));
//                x.setGhiChu(rs.getString("GhiChu"));
//
//                list.add(x);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    public List<InvoiceUI> getList3() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        List<InvoiceUI> list = new ArrayList<>();
        String sql = "select MaHoaDon, a.MaNhanVien,b.TenNhanVien,NgayBan, a.MaKhachHang,c.TenKhachHang,c.DiaChi,c.DienThoai, TongTien, a.TrangThai, GhiChu\n"
                + "from Invoice a inner join Staff b on a.MaNhanVien = b.MaNhanVien \n"
                + "				inner join Client c on c.MaKhachHang = a.MaKhachHang";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                InvoiceUI x = new InvoiceUI();
                x.setMaHoaDon(rs.getInt("MaHoaDon"));
                x.setMaNhanVien(rs.getString("MaNhanVien"));
                x.setTenNhanVien(rs.getString("TenNhanVien"));
                x.setMaKhachHang(rs.getString("MaKhachHang"));
                x.setTenKhachHang(rs.getString("TenKhachHang"));
                x.setDiaChi(rs.getString("DiaChi"));
                x.setDienThoai(rs.getString("DienThoai"));
                x.setNgayBan(rs.getDate("NgayBan"));
                x.setTongTien(rs.getFloat("TongTien"));
                x.setTrangThai(rs.getString("TrangThai"));
                x.setGhiChu(rs.getString("GhiChu"));

                list.add(x);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<InvoDtailUI> getListTam2(int a, String b) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        List<InvoDtailUI> list = new ArrayList<>();
        String sql = "     select a.MaHoaDon,g.MaTheLoai, g.TenTheLoai, d.MaSanPham, d.TenSanPham, b.SoLuong, a.GhiChu,MaHDCT,\n"
                + "		 e.MaNhanVien,e.TenNhanVien,f.MaKhachHang, f.TenKhachHang,f.DiaChi,f.DienThoai,a.NgayBan,a.TongTien,\n"
                + "		 b.TrangThai, c.MaSanPhamChiTiet, DonGia, c.maMau, h.temMau, c.maSize, i.TenSize, d.MaGiamGia, k.PhanTram\n"
                + "         from Invoice a inner join Detailed_Invoice b on a.MaHoaDon = b.MaHoaDon\n"
                + "						inner join Product_Detail c on c.MaSanPhamChiTiet = b.MaSanPhamChiTiet \n"
                + "						inner join Product d on d.MaSanPham = c.MaSanPham\n"
                + "						inner join Staff e on e.MaNhanVien = a.MaNhanVien\n"
                + "						inner join Client f on f.MaKhachHang = a.MaKhachHang\n"
                + "						inner join Category g on g.MaTheLoai = d.MaTheLoai\n"
                + "						inner join Color h on h.maMau = c.maMau\n"
                + "						inner join Size i on i.maSize = c.maSize\n"
                + "						inner join GiamGia k on k.MaGiamGia = d.MaGiamGia\n"
                + "						where a.MaHoaDon = ? and b.TrangThai = ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, a);
            ps.setString(2, b);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InvoDtailUI sp = new InvoDtailUI();

                sp.setMaHoaDon(rs.getInt("MaHoaDon"));
                sp.setMaTheLoai(rs.getString("MaTheLoai"));
                sp.setTenTheLoai(rs.getString("TenTheLoai"));
                sp.setMaSP(rs.getString("MaSanPham"));
                sp.setTenSP(rs.getString("TenSanPham"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGhiChu(rs.getString("GhiChu"));
                sp.setMaHDCT(rs.getInt("MaHDCT"));
                sp.setMaNhanVien(rs.getString("MaNhanVien"));
                sp.setTenNhanVien(rs.getString("TenNhanVien"));
                sp.setMaKhachHang(rs.getString("MaKhachHang"));
                sp.setTenKhachHang(rs.getString("TenKhachHang"));
                sp.setDiaChi(rs.getString("DiaChi"));
                sp.setDienThoai(rs.getString("DienThoai"));
                sp.setNgayBan(rs.getDate("NgayBan"));
                sp.setTongTien(rs.getFloat("TongTien"));
                sp.setTrangThai(rs.getString("TrangThai"));
                sp.setMaSanPhamChiTiet(rs.getString("MaSanPhamChiTiet"));
                sp.setDonGia(rs.getFloat("DonGia"));
                sp.setMaMau(rs.getInt("maMau"));
                sp.setTemMau(rs.getString("temMau"));
                sp.setMaSize(rs.getInt("maSize"));
                sp.setTenSize(rs.getString("TenSize"));
                sp.setMaGiamGia(rs.getInt("MaGiamGia"));
                sp.setPhanTram(rs.getFloat("PhanTram"));

                list.add(sp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        tfTim = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnTim = new javax.swing.JButton();
        cbb1 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tfSoLuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tfGhiChu = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        dateN = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        tfSĐT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfDC = new javax.swing.JTextField();
        dateN1 = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tfTenKH = new javax.swing.JTextField();
        tfMaKH = new javax.swing.JTextField();
        tfMaNV = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        lbTienCu = new javax.swing.JLabel();
        lbTienCu1 = new javax.swing.JLabel();
        lbVND1 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lbTieMoi = new javax.swing.JLabel();
        lbTienMoi2 = new javax.swing.JLabel();
        lbVND2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btnHoanThanh = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tfTong = new javax.swing.JLabel();
        lbThanhTIen = new javax.swing.JLabel();
        btnUPdate = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        lbGiamGia = new javax.swing.JLabel();
        test = new javax.swing.JButton();

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exits.png"))); // NOI18N
        jButton4.setText("Exit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(74, 31, 61));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 255), null));

        jPanel3.setBackground(new java.awt.Color(74, 31, 61));

        tfTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTimActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Thanh toán bán hàng");

        btnTim.setText("Tìm kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        cbb1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cbb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Đang chờ", "Đã thanh toán", "Đã hủy" }));
        cbb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Tình trạng hóa đơn");

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(44, 44, 44)
                .addComponent(tfTim, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(39, 39, 39)
                .addComponent(cbb1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jButton1)
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jButton1)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(btnTim))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel3.setText("Số lượng bán:");

        tfSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSoLuongActionPerformed(evt);
            }
        });

        jLabel4.setText("Giảm giá thêm:");

        jButton5.setText("Thêm vào hóa đơn");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        tb2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại", "Đơn giá", "Size", "Màu sắc", "Số lượng bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb2);

        tfGhiChu.setColumns(20);
        tfGhiChu.setRows(5);
        jScrollPane3.setViewportView(tfGhiChu);

        jLabel5.setText("Ghi chú");

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        dateN.setDateFormatString("yyyy-MM-dd");

        jLabel6.setText("Địa chỉ :");

        jLabel8.setText("Thời gian : ");
        jLabel8.setEnabled(false);

        jLabel11.setText("SĐT : ");

        dateN1.setDateFormatString("yyyy-MM-dd");
        dateN1.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)))
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateN1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfSĐT, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addComponent(tfDC)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateN, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dateN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(dateN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tfSĐT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("Tên KH : ");

        jLabel12.setText("Mã KH : ");

        jLabel13.setText("Mã NV : ");

        tfMaKH.setEnabled(false);
        tfMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMaKHActionPerformed(evt);
            }
        });

        tfMaNV.setEnabled(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfTenKH)
                    .addComponent(tfMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(tfMaKH))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tfMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(14, 14, 14)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbTienCu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbTienCu.setText("Tiền hóa đơn cũ :");
        lbTienCu.setEnabled(false);

        lbTienCu1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbTienCu1.setText("...................................................");
        lbTienCu1.setEnabled(false);

        lbVND1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbVND1.setText("VNĐ");
        lbVND1.setEnabled(false);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(lbTienCu)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(lbTienCu1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(lbVND1))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTienCu, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTienCu1)
                    .addComponent(lbVND1)))
        );

        lbTieMoi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbTieMoi.setText("Tiền hóa đơn mới :");
        lbTieMoi.setEnabled(false);

        lbTienMoi2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbTienMoi2.setText("..................................................");
        lbTienMoi2.setEnabled(false);

        lbVND2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbVND2.setText("VNĐ");
        lbVND2.setEnabled(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbTieMoi)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbTienMoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(lbVND2))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTieMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbVND2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTienMoi2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        btnHoanThanh.setText("Hoàn thành");
        btnHoanThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanThanhActionPerformed(evt);
            }
        });

        jButton10.setText("Tạo mới");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jButton8.setText("Hủy hóa đơn");
        jButton8.setEnabled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("VNĐ");

        tfTong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfTong.setText("....................................");

        lbThanhTIen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbThanhTIen.setText("Thành tiền : ");

        btnUPdate.setText("Cập nhật");
        btnUPdate.setEnabled(false);
        btnUPdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUPdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(btnXoa))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbThanhTIen)
                        .addGap(18, 18, 18)
                        .addComponent(tfTong, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(118, 118, 118)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnHoanThanh, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnThanhToan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)
                                .addGap(18, 18, 18)
                                .addComponent(btnUPdate)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnThanhToan, btnUPdate});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbThanhTIen)
                            .addComponent(tfTong)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHoanThanh)
                            .addComponent(jButton10)
                            .addComponent(btnThanhToan)
                            .addComponent(jButton8)
                            .addComponent(btnUPdate)
                            .addComponent(btnXoa))
                        .addGap(39, 39, 39)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel5)))
                        .addGap(71, 71, 71))))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại", "Size", "Màu", "Giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên khách", "SĐT", "Ngày tạo", "Mã nhân viên", "Trạng thái", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbGiamGia.setText("giảm giá");

        test.setText("Hoàn thành");
        test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addGap(31, 31, 31)
                .addComponent(jLabel4)
                .addGap(61, 61, 61)
                .addComponent(lbGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(261, 261, 261)
                .addComponent(test, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(440, 440, 440))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(test)
                    .addComponent(lbGiamGia)
                    .addComponent(jLabel4)
                    .addComponent(jButton5)
                    .addComponent(tfSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       this.dispose();
        Home home = new Home(taiKhoan, maNV, tenNV, passNV);
        home.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tfTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTimActionPerformed

    private void tfSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSoLuongActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (index1 == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn sản phẩm!");
            return;
        }
        if (tfSoLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập số lượng!");
            return;
        }
        int a1 = Integer.parseInt(tfSoLuong.getText());

        if (a1 == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập số lượng cần bán!");
            return;
        }
        tfSoLuong.requestFocus();

        checkChonSp(index1);

        int soLuong = Integer.parseInt(tfSoLuong.getText());

        getListTam(index1, soLuong);

        System.out.println(listTam.size());
        showTable3();


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
//        jTable2.clearSelection();
        a = 1;
////        table.getModel().getValueAt(row_index, col_index);
//        tfSoLuong.requestFocus();
        tb2.clearSelection();
//        table.getModel().getValueAt(row_index, col_index);
        index1 = jTable1.getSelectedRow();
        tfSoLuong.requestFocus();
//        for (int i = 0; i < listTam.size(); i++) {
//            if (list.get(index1).getMaSPCT().equalsIgnoreCase(listTam.get(i).getMaSPCT())) {
//
//                listTam.get(i).setSoLuongBan(Integer.parseInt(tfSoLuong.getText()));
//
//                jButton5.setEnabled(false);
//            }
//            break;
//        }


    }//GEN-LAST:event_jTable1MouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:

        listTam.forEach(System.out::println);
        if (a == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn sản phẩm bán!");
            return;
        }
        if (list4.size() <= 0 && listTam.size() <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa có sản phẩm bán!");
            return;
        }
        if (a == 1) {
            if (tfDC.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập địa chỉ");
                return;

            }
            tfDC.requestFocus();
            if (tfTenKH.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên khách hàng");
                return;

            }
            tfTenKH.requestFocus();
            if (tfSĐT.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập SĐT khách");
                return;

            }
            tfSĐT.requestFocus();
        }

        if (a == 1) {

//            Client clnt = getModel();
//            srClientService.insert(clnt);
            try {
                addClient();
                addInvoice();

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(listTam.size());

            // insertSevice thừa "sql"
            for (int i = 0; i < listTam.size(); i++) {
                InvoiceDetail inVDT = getModel2(i);
                srInvD.insert(inVDT);
                System.out.println(listTam.get(i).getMaSPCT());
            }

            try {
                list3 = getList3();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            tong = inHD();
            tfTong.setText(String.valueOf(tong));

            showTable2();
            System.out.println(list3.size());

        }
        if (a == 3) {

//            Client clnt = getModel();
//            srClientService.insert(clnt);
            try {
                addClient();
                addInvoice();

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(listTam.size());

            // insertSevice thừa "sql"
            for (int i = 0; i < listTam.size(); i++) {
                InvoiceDetail inVDT = getModel2(i);
                srInvD.insert(inVDT);
                System.out.println(listTam.get(i).getMaSPCT());
            }

            try {
                list3 = getList3();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            tong = inHD();
            tfTong.setText(String.valueOf(tong));

            showTable2();
            System.out.println(list3.size());

        }
        if (a == 2) {

            tong = inHD2();
            System.out.println("----dây là tiền" + tong);
            tfTong.setText(String.valueOf(tong));
            if (jTable3.getValueAt(index2, 5).equals("Đang chờ")) {
                for (int i = 0; i < list3.size(); i++) {
                    if (jTable3.getValueAt(index2, 0).equals(list3.get(i).getMaHoaDon())) {

                        tfTong.setText(String.valueOf(list3.get(i).getTongTien()));
                        Invoice mod = getModel("Đã thanh toán", "", tong, list3.get(i).getMaHoaDon());
                        srInv.update1(mod);
                    }
                }

            }
            try {
                list3 = getList3();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            showTable2();
            
            
            try {
                exportInvoid();
                // insertSevice thừa "sql"
//            for (int i = 0; i < listTam.size(); i++) {
//                InvoiceDetail inVDT = getModel2(i);
//                srInvD.insert(inVDT);
//                System.out.println(listTam.get(i).getMaSPCT());
//            }

//            try {
//                list3 = getList3();
//
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(HoaDon.class
//                        .getName()).log(Level.SEVERE, null, ex);
//
//            } catch (SQLException ex) {
//                Logger.getLogger(HoaDon.class
//                        .getName()).log(Level.SEVERE, null, ex);
//            }
//            tong = inHD();
//
//            showTable2();
//            System.out.println(list3.size());
//
//            float d = Float.parseFloat(lbTienCu1.getText());
//            lbTienMoi2.setText(String.valueOf(tong));
//            if (tong > d) {
//                lbThanhTIen.setText("Tiền khách phải trả : ");
//                tfTong.setText(String.valueOf(tong - d));
//                System.out.println("Tiền khách phải trả-----------");
//                System.out.println(tong);
//                System.out.println(d);
//                System.out.println("-----------");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btnThanhToanActionPerformed
////

    public void exportInvoid() throws FileNotFoundException, ClassNotFoundException, SQLException, IOException {
           File file = null;
        boolean isCreat = false;
        try{
            file = new File("C:\\Users\\tiennv\\Desktop\\ShopAdam\\src\\Bill\\"+idValue+".txt");
            //Ở đây mình tạo file trong ổ D
            isCreat = file.createNewFile();
            if (isCreat)
                System.out.print("Da tao file thanh cong!");
            else
                System.out.print("Tao file that bai");
            //file.delete();
        }
        catch (Exception e){
            System.out.print(e);
        }
        
        
        FileOutputStream out = new FileOutputStream("C:\\Users\\tiennv\\Desktop\\ShopAdam\\src\\Bill\\"+idValue+".txt");
        System.out.println(createStringContentInvoid());
        out.write(createStringContentInvoid().getBytes());
        out.close();
        BoxDiaglog.alert(this, "Tạo hóa đơn thành công");
    
        

    }

    public String createStringContentInvoid() throws ClassNotFoundException, SQLException {
        StringBuilder listDetail = new StringBuilder();
        List<OrderFileDTO> list =  getListOrderDetail();
        double sum = 0;
        for(OrderFileDTO x: list){
            listDetail.append("\n Ten san pham   : " +x.getProductName());
            listDetail.append("\n So luong       : " +x.getQuantity());
            listDetail.append("\n Don gia        :" +x.getPrice());
            listDetail.append("\n Thanh tien     : " +x.getSumPrice());
            listDetail.append("\n");
            sum+=x.getSumPrice();
        
    }
        
        
        String bill = "---------------ADAMSTORE--------------"+
                "\n Ma Hoa Don       : "+ idValue+
                "\n Ten khach hang   : "+ list.get(0).getCustomerName()+
                "\n Ngay mua         : " + list.get(0).getDateCreate()+
                "\n "+ listDetail.toString() +
                "\n Tong tien        :" + sum+
                "\n NhanVien         :" + list.get(0).getStaffName()+
                "\n -----------------------------------";
        
        
        return bill;
        
                       

    }

    public List<OrderFileDTO> getListOrderDetail() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        List<OrderFileDTO> list = new ArrayList<>();
        String sql = "select Invoice.MaHoaDon, \n" +
"Client.TenKhachHang,\n" +
"Product.TenSanPham,\n" +
"Invoice.NgayBan,\n" +
"Detailed_Invoice.SoLuong,\n" +
"Detailed_Invoice.DonGia,\n" +
"Staff.TenNhanVien,\n" +
"(Detailed_Invoice.SoLuong * Detailed_Invoice.DonGia) as N'TongTien'\n" +
"from Detailed_Invoice\n" +
"inner join Invoice on Invoice.MaHoaDon = Detailed_Invoice.MaHoaDon\n" +
"inner join Product_Detail on Product_Detail.MaSanPhamChiTiet = Detailed_Invoice.MaSanPhamChiTiet\n" +
"inner join Staff on Staff.MaNhanVien = Invoice.MaNhanVien\n" +
"inner join Product on Product.MaSanPham = Product_Detail.MaSanPham\n" +
"inner join Client on Client.MaKhachHang = Invoice.MaKhachHang\n" +
"where Invoice.MaHoaDon =?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idValue);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderFileDTO e = new OrderFileDTO();
                e.setOrderID(rs.getInt("MaHoaDon"));
                e.setCustomerName(rs.getString("TenKhachHang"));
                e.setProductName(rs.getString("TenSanPham"));
                e.setDateCreate(rs.getDate("NgayBan"));
                e.setQuantity(rs.getInt("SoLuong"));
                e.setPrice(rs.getDouble("DonGia"));
                e.setStaffName(rs.getString("TenNhanVien"));
                e.setSumPrice(rs.getDouble("TongTien"));
                list.add(e);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    ////

    private void btnUPdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUPdateActionPerformed
        model2.setRowCount(0);
        tong = inHD2();

        float d = Float.parseFloat(lbTienCu1.getText());
        lbTienMoi2.setText(String.valueOf(tong));
        if (tong > d) {
            lbThanhTIen.setText("Tiền khách phải trả : ");
            tfTong.setText(String.valueOf(tong - d));
            System.out.println("Tiền khách phải trả-----------");
            System.out.println(tong);
            System.out.println(d);
            System.out.println("-----------");
        } else {
            lbThanhTIen.setText("Tiền trả lại khách : ");
            tfTong.setText(String.valueOf(d - tong));
            System.out.println("Tiền trả-----------");
            System.out.println(d - tong);
            System.out.println(tong);
            System.out.println(d);
            System.out.println("-----------");
        }

        Invoice mod = getModel("Đã thanh toán", "", tong, bienTable3);
        srInv.update1(mod);


    }//GEN-LAST:event_btnUPdateActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        System.out.println("-------------:::::");
        System.out.println(index4);
        System.out.println("-------------:::::");

        if (list4.isEmpty() && listTam.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa có sản phẩm để xóa!");
            return;
        }
        if (index4 == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn sản phẩm để xóa!");
            return;
        }
        if (a == 3 && listTam.size() > 0) {
            listTam.remove(index4);
            System.out.println(" Đay là Size" + listTam.size());
            showTable3();
        }
        if (a == 3 && list4.size() > 0) {
            System.out.println("size " + list4.size());
            for (int i = 0; i < list4.size(); i++) {
                if (i == index4) {
                    System.out.println("-----++++++");
                    System.out.println(list4.get(index4).getMaHDCT());

                    InvoiceDetail mod = getModel33("Đã hủy", tfGhiChu.getText(), list4.get(i).getMaHDCT());
                    srInvD.update(mod);
                }
            }

            try {
                list4 = getListTam2((int) jTable3.getValueAt(index2, 0), "Đã bán");
                System.out.println(list4.size());
                model2.setRowCount(0);
                for (InvoDtailUI sp : list4) {

                    model2.addRow(new Object[]{
                        sp.getMaSP(), sp.getTenSP(), sp.getTenTheLoai(), sp.getDonGia(), sp.getTenSize(), sp.getTemMau(), sp.getSoLuong(),});
//                jTable3.clearSelection();
                }
//            jTable3.clearSelection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
//            model2.setColumnIdentifiers(new Object[]{
//                "Mã chi tiết", "Mã sp",  "Tên sp", "Loại", "Đơn giá", "Size", "Màu", "Số lượng"
//            });
//            model2.setRowCount(0);
//            for (InvoDtailUI sp : list4) {
//
//                model2.addRow(new Object[]{
//                    sp.getMaHDCT(),sp.getMaSP(), sp.getTenSP(), sp.getTenTheLoai(), sp.getDonGia(),
//                    sp.getTenSize(), sp.getTemMau(), sp.getSoLuong(),});
//
//            }
//            model2.setColumnIdentifiers(new Object[]{
//                "Mã chi tiết", "Mã sp",  "Tên sp", "Loại", "Đơn giá", "Size", "Màu", "Số lượng"
//            });
//            model2.setRowCount(0);
//            for (InvoDtailUI sp : list4) {
//
//                model2.addRow(new Object[]{
//                    sp.getMaHDCT(),sp.getMaSP(), sp.getTenSP(), sp.getTenTheLoai(), sp.getDonGia(),
//                    sp.getTenSize(), sp.getTemMau(), sp.getSoLuong(),});
//
//            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tfMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMaKHActionPerformed

    public static boolean containsIgnoreCase(String str, String searchStr)     {
    if(str == null || searchStr == null) return false;

    final int length = searchStr.length();
    if (length == 0)
        return true;

    for (int i = str.length() - length; i >= 0; i--) {
        if (str.regionMatches(true, i, searchStr, 0, length))
            return true;
    }
    return false;
}
    
    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
       
        String shr = tfTim.getText();
         model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            if (containsIgnoreCase(list.get(i).getTenSP(), shr) ) {

               

                model.addRow(new Object[]{
                    list.get(i).getMaSP(), list.get(i).getTenSP(), list.get(i).getTenLoai(), list.get(i).getSize(), list.get(i).getMau(), list.get(i).getSoLuong(),});

            }
        }
        tfTim.requestFocus();
    }//GEN-LAST:event_btnTimActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:

        a = 2;
        String jjj;

        index2 = jTable3.getSelectedRow();
        jjj = (String) jTable3.getValueAt(index2, 5);
//        bienTable3 = jTable3.getValueAt(index2, 0)
        System.out.println("------------");
        System.out.println(jjj);
        System.out.println("------------");
        tfTenKH.setEnabled(false);
        tfSĐT.setEnabled(false);
        tfDC.setEnabled(false);
        model2.setRowCount(0);
        if (index2 >= 0 && jjj.equalsIgnoreCase("Đã thanh toán")) {
            bienTable3 = Integer.parseInt(jTable3.getValueAt(index2, 0).toString());
            btnHoanThanh.setEnabled(false);
            btnThanhToan.setEnabled(false);
            btnUPdate.setEnabled(true);
            jButton8.setEnabled(true);
            lbTieMoi.setEnabled(true);
            lbTienCu.setEnabled(true);
            lbTienCu1.setEnabled(true);
            lbTienMoi2.setEnabled(true);
            lbVND1.setEnabled(true);
            lbVND2.setEnabled(true);
            tfTong.setText("----------------------");
            model2.setRowCount(0);
            for (int i = 0; i < list3.size(); i++) {
                if (jTable3.getValueAt(index2, 0).equals(list3.get(i).getMaHoaDon())) {
                    lbTienCu1.setText(String.valueOf(list3.get(i).getTongTien()));
                }
            }

        }

        if (index2 >= 0 && jjj.equalsIgnoreCase("Đang chờ")) {
            bienTable3 = Integer.parseInt(jTable3.getValueAt(index2, 0).toString());
            btnHoanThanh.setEnabled(false);
            btnThanhToan.setEnabled(true);
            btnUPdate.setEnabled(false);

            jButton8.setEnabled(true);
            lbTieMoi.setEnabled(false);
            lbTienCu.setEnabled(false);
            lbTienCu1.setEnabled(false);
            lbTienMoi2.setEnabled(false);
            lbVND1.setEnabled(false);
            lbVND2.setEnabled(false);
            model2.setRowCount(0);
            tfTong.setText("----------------------");
            bienTable3 = Integer.parseInt(jTable3.getValueAt(index2, 0).toString());
            for (int i = 0; i < list3.size(); i++) {
                if (jTable3.getValueAt(index2, 0).equals(list3.get(i).getMaHoaDon())) {
                    tfTong.setText(String.valueOf(list3.get(i).getTongTien()));
                }
            }

        }
        tfGhiChu.setText((String) jTable3.getValueAt(index2, 6));
        try {
            list4 = getListTam2((int) jTable3.getValueAt(index2, 0), "Đã bán");
            System.out.println(list4.size());

            for (InvoDtailUI sp : list4) {

                model2.addRow(new Object[]{
                    sp.getMaSP(), sp.getTenSP(), sp.getTenTheLoai(), sp.getDonGia(), sp.getTenSize(), sp.getTemMau(), sp.getSoLuong(),});
//                jTable3.clearSelection();
            }
//            jTable3.clearSelection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
//        model2.setColumnIdentifiers(new Object[]{
//            "Mã chi tiết", "Mã sp", "Tên sp", "Loại", "Đơn giá", "Size", "Màu", "Số lượng"
//        });
//        model2.setRowCount(0);
//        for (InvoDtailUI sp : list4) {
//
//            model2.addRow(new Object[]{
//                sp.getMaHDCT(), sp.getMaSP(), sp.getTenSP(), sp.getTenTheLoai(), sp.getDonGia(),
//                sp.getTenSize(), sp.getTemMau(), sp.getSoLuong(),});
//
//        }
//        for (int i = 0; i < list4.size(); i++) {
//            System.out.println("++++++++++++++++");
//            System.out.println(list4.get(i).getMaHDCT());
//            System.out.println("++++++++++++++++");
//            
//        }

//        if (index2 >= 0 && jjj.equalsIgnoreCase("Đã thanh toán")) {
//            jButton7.setEnabled(true);
//            jButton8.setEnabled(true);
//            lbTieMoi.setEnabled(true);
//            lbTienCu.setEnabled(true);
//            lbTienCu1.setEnabled(true);
//            lbTienMoi2.setEnabled(true);
//            lbVND1.setEnabled(true);
//            lbVND2.setEnabled(true);
//            model2.setRowCount(0);
//            for (int i = 0; i < list3.size(); i++) {
//                if (jTable3.getValueAt(index2, 0).equals(list3.get(i).getMaHoaDon())) {
//                    lbTienCu1.setText(String.valueOf(list3.get(i).getTongTien()));
//                }
//            }
//            try {
//                list4 = getListTam2((int) jTable3.getValueAt(index2, 0));
//                System.out.println(list4.size());
//
//                for (InvoDtailUI sp : list4) {
//
//                    model2.addRow(new Object[]{
//                        sp.getMaSP(), sp.getTenSP(), sp.getTenTheLoai(), sp.getDonGia(), sp.getTenSize(), sp.getTemMau(), sp.getSoLuong(),});
//                }
//
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        if (index2 >= 0 && jjj.equalsIgnoreCase("Đang chờ")) {
//            a = 3;
//            jButton7.setEnabled(true);
//            jButton8.setEnabled(true);
//            lbTieMoi.setEnabled(false);
//            lbTienCu.setEnabled(false);
//            lbTienCu1.setEnabled(false);
//            lbTienMoi2.setEnabled(false);
//            lbVND1.setEnabled(false);
//            lbVND2.setEnabled(false);
//            model2.setRowCount(0);
//            btnHoanThanh.setEnabled(false);
//            for (int i = 0; i < list3.size(); i++) {
//                if (jTable3.getValueAt(index2, 0).equals(list3.get(i).getMaHoaDon())) {
//                    lbTienCu1.setText(String.valueOf(list3.get(i).getTongTien()));
//                }
//            }
//            try {
//                list4 = getListTam2((int) jTable3.getValueAt(index2, 0));
//                System.out.println(list4.size());
//
//                for (InvoDtailUI sp : list4) {
//
//                    model2.addRow(new Object[]{
//                        sp.getMaSP(), sp.getTenSP(), sp.getTenTheLoai(), sp.getDonGia(), sp.getTenSize(), sp.getTemMau(), sp.getSoLuong(),});
//                }
//
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        model2.setColumnIdentifiers(new Object[]{
//            "Mã chi tiết", "Mã sp", "Tên sp", "Loại", "Đơn giá", "Size", "Màu", "Số lượng"
//        });
//        model2.setRowCount(0);
//        for (InvoDtailUI sp : list4) {
//
//            model2.addRow(new Object[]{
//                sp.getMaHDCT(), sp.getMaSP(), sp.getTenSP(), sp.getTenTheLoai(), sp.getDonGia(),
//                sp.getTenSize(), sp.getTemMau(), sp.getSoLuong(),});
//
//        }
//        for (int i = 0; i < list4.size(); i++) {
//            System.out.println("++++++++++++++++");
//            System.out.println(list4.get(i).getMaHDCT());
//            System.out.println("++++++++++++++++");
//            
//        }
//        if (index2 >= 0 && jjj.equalsIgnoreCase("Đã thanh toán")) {
//            jButton7.setEnabled(true);
//            jButton8.setEnabled(true);
//            lbTieMoi.setEnabled(true);
//            lbTienCu.setEnabled(true);
//            lbTienCu1.setEnabled(true);
//            lbTienMoi2.setEnabled(true);
//            lbVND1.setEnabled(true);
//            lbVND2.setEnabled(true);
//            model2.setRowCount(0);
//            for (int i = 0; i < list3.size(); i++) {
//                if (jTable3.getValueAt(index2, 0).equals(list3.get(i).getMaHoaDon())) {
//                    lbTienCu1.setText(String.valueOf(list3.get(i).getTongTien()));
//                }
//            }
//            try {
//                list4 = getListTam2((int) jTable3.getValueAt(index2, 0));
//                System.out.println(list4.size());
//
//                for (InvoDtailUI sp : list4) {
//
//                    model2.addRow(new Object[]{
//                        sp.getMaSP(), sp.getTenSP(), sp.getTenTheLoai(), sp.getDonGia(), sp.getTenSize(), sp.getTemMau(), sp.getSoLuong(),});
//                }
//
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        if (index2 >= 0 && jjj.equalsIgnoreCase("Đang chờ")) {
//            a = 3;
//            jButton7.setEnabled(true);
//            jButton8.setEnabled(true);
//            lbTieMoi.setEnabled(false);
//            lbTienCu.setEnabled(false);
//            lbTienCu1.setEnabled(false);
//            lbTienMoi2.setEnabled(false);
//            lbVND1.setEnabled(false);
//            lbVND2.setEnabled(false);
//            model2.setRowCount(0);
//            btnHoanThanh.setEnabled(false);
//            for (int i = 0; i < list3.size(); i++) {
//                if (jTable3.getValueAt(index2, 0).equals(list3.get(i).getMaHoaDon())) {
//                    lbTienCu1.setText(String.valueOf(list3.get(i).getTongTien()));
//                }
//            }
//            try {
//                list4 = getListTam2((int) jTable3.getValueAt(index2, 0));
//                System.out.println(list4.size());
//
//                for (InvoDtailUI sp : list4) {
//
//                    model2.addRow(new Object[]{
//                        sp.getMaSP(), sp.getTenSP(), sp.getTenTheLoai(), sp.getDonGia(), sp.getTenSize(), sp.getTemMau(), sp.getSoLuong(),});
//                }
//
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        btnUPdate.setEnabled(false);

        if (index2 < 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn hóa đơn cần hủy");
            return;
        }
        if (tfGhiChu.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập lý do hủy");
            return;
        }
        Date a = new java.sql.Date(System.currentTimeMillis());
        for (int i = 0; i < list3.size(); i++) {
            if (jTable3.getValueAt(index2, 0).equals(list3.get(i).getMaHoaDon())) {
                lbTienMoi2.setText(String.valueOf(list3.get(i).getTongTien() - list3.get(i).getTongTien()));
            }
        }

        lbThanhTIen.setText("Số tiền trả lại cho khách: ");

        for (int i = 0; i < list3.size(); i++) {
            if (jTable3.getValueAt(index2, 0).equals(list3.get(i).getMaHoaDon())) {
                float b = list3.get(i).getTongTien();
                tfTong.setText(String.valueOf(list3.get(i).getTongTien()));
                Invoice mod = getModel("Đã hủy", "Nguyên nhân: " + tfGhiChu.getText() + ", Ngày hủy: " + a + ", Tổng tiền: " + b, 0, list3.get(i).getMaHoaDon());
                srInv.update1(mod);
            }
        }
        try {
            list3 = getList3();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        showTable2();
        tfGhiChu.setText("");

    }//GEN-LAST:event_jButton8ActionPerformed

    private void tb2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb2MouseClicked
        // TODO add your handling code here:
        index4 = tb2.getSelectedRow();
        a = 3;

        if (index4 >= 0) {
            tfSoLuong.setText(String.valueOf(tb2.getValueAt(index4, 6)));
        }

    }//GEN-LAST:event_tb2MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        tfTenKH.setEnabled(true);
        tfSĐT.setEnabled(true);
        tfDC.setEnabled(true);
        btnUPdate.setEnabled(false);
        jButton8.setEnabled(false);
        lbTieMoi.setEnabled(false);
        lbTienCu.setEnabled(false);
        lbTienCu1.setEnabled(false);
        lbTienMoi2.setEnabled(false);
        jButton5.setEnabled(true);
        lbVND1.setEnabled(false);
        lbVND2.setEnabled(false);
        btnHoanThanh.setEnabled(true);
        jTable3.clearSelection();
        model2.setRowCount(0);
        listTam.clear();
        tfSoLuong.setText(String.valueOf(0));
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnHoanThanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanThanhActionPerformed
        // TODO add your handling code here:
        if (listTam.size() <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn sản phẩm bán!");
            return;
        }
        if (tfDC.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập địa chỉ");
            return;

        }
        tfDC.requestFocus();
        if (tfTenKH.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên khách hàng");
            return;

        }
        tfTenKH.requestFocus();
        if (tfSĐT.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập SĐT khách");
            return;

        }
        tfSĐT.requestFocus();

        listTam.forEach(System.out::println);
        if (a == 1) {

//            Client clnt = getModel();
//            srClientService.insert(clnt);
            try {
                addClient();
                addInvoice2();

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(listTam.size());

            // insertSevice thừa "sql"
            for (int i = 0; i < listTam.size(); i++) {
                InvoiceDetail inVDT = getModel2(i);
                srInvD.insert(inVDT);
                System.out.println(listTam.get(i).getMaSPCT());
            }

            try {
                list3 = getList3();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            tong = inHD();
            tfTong.setText(String.valueOf(tong));

            showTable2();
            System.out.println(list3.size());

        }
        if (a == 3) {

//            Client clnt = getModel();
//            srClientService.insert(clnt);
            try {
                addClient();
                addInvoice2();

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(listTam.size());

            // insertSevice thừa "sql"
            for (int i = 0; i < listTam.size(); i++) {
                InvoiceDetail inVDT = getModel2(i);
                srInvD.insert(inVDT);
                System.out.println(listTam.get(i).getMaSPCT());
            }

            try {
                list3 = getList3();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            tong = inHD();
            tfTong.setText(String.valueOf(tong));

            showTable2();
            System.out.println(list3.size());

        }
        if (a == 2) {

            float d = Float.parseFloat(lbTienCu1.getText());
            lbTienMoi2.setText(String.valueOf(tong));
            if (tong > d) {
                lbThanhTIen.setText("Tiền khách phải trả : ");
                tfTong.setText(String.valueOf(tong - d));
            } else {
                lbThanhTIen.setText("Tiền trả lại khách : ");
                tfTong.setText(String.valueOf(d - tong));

            }

//            Client clnt = getModel();
//            srClientService.insert(clnt);
            try {
                addClient();
                addInvoice2();

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(listTam.size());

            // insertSevice thừa "sql"
            for (int i = 0; i < listTam.size(); i++) {
                InvoiceDetail inVDT = getModel2(i);
                srInvD.insert(inVDT);
                System.out.println(listTam.get(i).getMaSPCT());
            }

            try {
                list3 = getList3();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            tong = inHD();

            showTable2();
            System.out.println(list3.size());

        }


    }//GEN-LAST:event_btnHoanThanhActionPerformed

    private void cbb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb1ActionPerformed

        String trangThai = cbb1.getSelectedItem().toString();
        model3.setRowCount(0);
        for (int i = 0; i < list3.size(); i++) {
            if (list3.get(i).getTrangThai().equalsIgnoreCase(trangThai)) {
                model3.addRow(new Object[]{
                    list3.get(i).getMaHoaDon(),
                    list3.get(i).getTenKhachHang(),
                    list3.get(i).getDienThoai(),
                    list3.get(i).getNgayBan(),
                    list3.get(i).getMaNhanVien(),
                    list3.get(i).getTrangThai(),
                    list3.get(i).getGhiChu()
                });
            } else if (trangThai.equalsIgnoreCase("All")) {

                showTable2();
            }
        }

    }//GEN-LAST:event_cbb1ActionPerformed

    private void testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testActionPerformed

        int soLuong = Integer.parseInt(tfSoLuong.getText());

        for (int i = 0; i < listTam.size(); i++) {
            if (listTam.get(i).getMaSPCT().equals(list.get(index1).getMaSPCT())) {
                System.out.println("tìm được sản phẩm giống nhau");
                listTam.get(i).setSoLuongBan(soLuong);
                System.out.println("tìm được sản phẩm giống nhau" + listTam.get(i).getSoLuongBan());

            }
        }

        getListTam(index1, soLuong);

        System.out.println(listTam.size());
        showTable3();

//        System.out.println("Tài khoản: " + bienTable3);
//        System.out.println("Tài khoản: " + list3.get(index2).getTongTien());
//        float c = 0;
//        float f = 0;
//        for(int i = 0; i < list4.size(); i++){
//        int a = list4.get(i).getSoLuong();
//        float b = list4.get(i).getDonGia();
//        f = list4.get(i).getPhanTram();
//        float tong = a * b;
//        c = c + tong;
//           
//        }
//        System.out.println("tong tien " + c);
//        int a;
//        float b;
//        float c;
//        float tong = 0;
//        for (int i = 0; i < list4.size(); i++) {
//            float d = list4.get(i).getPhanTram();
//            float e = 100 - d;
//            if (d == 0) {
//
//                a = (int) list4.get(i).getDonGia();
//                b = (float) list4.get(i).getSoLuong();
//                c = a * b;
//                tong = tong + c;
//                System.out.println("d = 0" + tong);
//            } else {
//                a = (int) list4.get(i).getDonGia();
//                b = (float) list4.get(i).getSoLuong();
//                c = a * b * e / 100;
//                tong = tong + c;
//                System.out.println("d >0" + tong);
//            }
//
//        }

    }//GEN-LAST:event_testActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Home home = new Home(taiKhoan, maNV, tenNV, passNV);
        home.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDon(taiKhoan, maNV, tenNV, passNV).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanThanh;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnUPdate;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbb1;
    private com.toedter.calendar.JDateChooser dateN;
    private com.toedter.calendar.JDateChooser dateN1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lbGiamGia;
    private javax.swing.JLabel lbThanhTIen;
    private javax.swing.JLabel lbTieMoi;
    private javax.swing.JLabel lbTienCu;
    private javax.swing.JLabel lbTienCu1;
    private javax.swing.JLabel lbTienMoi2;
    private javax.swing.JLabel lbVND1;
    private javax.swing.JLabel lbVND2;
    private javax.swing.JTable tb2;
    private javax.swing.JButton test;
    private javax.swing.JTextField tfDC;
    private javax.swing.JTextArea tfGhiChu;
    private javax.swing.JTextField tfMaKH;
    private javax.swing.JTextField tfMaNV;
    private javax.swing.JTextField tfSoLuong;
    private javax.swing.JTextField tfSĐT;
    private javax.swing.JTextField tfTenKH;
    private javax.swing.JTextField tfTim;
    private javax.swing.JLabel tfTong;
    // End of variables declaration//GEN-END:variables

}
