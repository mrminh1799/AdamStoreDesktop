/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ui;

import Ui.Model.InvoDtailUI;
import Ui.Model.InvoiceUI;
import Utils.BoxDiaglog;
import Utils.JdbcHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class LichSuHoaDon extends javax.swing.JFrame {

    /**
     * Creates new form adMin
     */
      private static String driver = JdbcHelper.driver;
        private static String dburl = JdbcHelper.dburl;

      private static String username = JdbcHelper.username;
    private static String password = JdbcHelper.password;

    DefaultTableModel model1;
    DefaultTableModel model2;
    List<InvoiceUI> list3;
    int index;
    int index2;
    List<InvoDtailUI> list4;
    public static String taiKhoan, maNV, tenNV, passNV;

    public LichSuHoaDon(String taiKhoan, String maNV, String tenNV, String passNV) {
        initComponents();
        setLocationRelativeTo(null);
        model1 = (DefaultTableModel) tb1.getModel();
        model2 = (DefaultTableModel) tb2.getModel();
        date.setDate(new java.sql.Date(System.currentTimeMillis()));
        this.taiKhoan = taiKhoan;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.passNV = passNV;
        try {
            list3 = getList3();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LichSuHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LichSuHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        showTable1();
    }

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

    public List<InvoDtailUI> getListTam2(int a) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        List<InvoDtailUI> list = new ArrayList<>();
        String sql = "     select a.MaHoaDon,g.MaTheLoai, g.TenTheLoai, d.MaSanPham, d.TenSanPham, c.SoLuong, b.GhiChu,MaHDCT,\n"
                + "		 e.MaNhanVien,e.TenNhanVien,f.MaKhachHang, f.TenKhachHang,f.DiaChi,f.DienThoai,a.NgayBan,a.TongTien,\n"
                + "		 b.TrangThai, c.MaSanPhamChiTiet, DonGia, c.maMau, h.temMau, c.maSize, i.TenSize\n"
                + "         from Invoice a inner join Detailed_Invoice b on a.MaHoaDon = b.MaHoaDon\n"
                + "						inner join Product_Detail c on c.MaSanPhamChiTiet = b.MaSanPhamChiTiet \n"
                + "						inner join Product d on d.MaSanPham = c.MaSanPham\n"
                + "						inner join Staff e on e.MaNhanVien = a.MaNhanVien\n"
                + "						inner join Client f on f.MaKhachHang = a.MaKhachHang\n"
                + "						inner join Category g on g.MaTheLoai = d.MaTheLoai\n"
                + "						inner join Color h on h.maMau = c.maMau\n"
                + "						inner join Size i on i.maSize = c.maSize\n"
                + "						where a.MaHoaDon = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, a);
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

                list.add(sp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void showTable1() {
        model1.setRowCount(0);
        for (InvoiceUI sp : list3) {

            model1.addRow(new Object[]{
                sp.getMaHoaDon(), sp.getNgayBan(), sp.getTenNhanVien(), sp.getTenKhachHang(), sp.getTongTien(), sp.getTrangThai(), sp.getGhiChu(),});
        }
    }

    public void showTable2() {
        model2.setRowCount(0);
        for (InvoDtailUI sp : list4) {

            model2.addRow(new Object[]{
                sp.getMaHDCT(), sp.getNgayBan(), sp.getTenKhachHang(), sp.getDienThoai(), sp.getMaNhanVien(), sp.getTrangThai(), sp.getGhiChu(),});
        }
    }

    public void showTable3() {
        model2.setRowCount(0);
        for (InvoDtailUI sp : list4) {

            model2.addRow(new Object[]{
                sp.getMaHDCT(), sp.getNgayBan(), sp.getTenKhachHang(), sp.getDienThoai(), sp.getMaNhanVien(), "", sp.getGhiChu(),});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnThongke = new javax.swing.JButton();
        btnNhanvien = new javax.swing.JButton();
        btnSanpham = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        date = new com.toedter.calendar.JDateChooser();
        btnTim = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb2 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tfGhiChu2 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        tfGhiChu1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(74, 31, 61));

        btnThongke.setBackground(new java.awt.Color(153, 153, 255));
        btnThongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/thongkes.png"))); // NOI18N
        btnThongke.setText("Thống kê");
        btnThongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongkeActionPerformed(evt);
            }
        });

        btnNhanvien.setBackground(new java.awt.Color(153, 153, 255));
        btnNhanvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanviens.png"))); // NOI18N
        btnNhanvien.setText("Nhân viên");
        btnNhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanvienActionPerformed(evt);
            }
        });

        btnSanpham.setBackground(new java.awt.Color(153, 153, 255));
        btnSanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/products.png"))); // NOI18N
        btnSanpham.setText("Sản phẩm");
        btnSanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanphamActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 255), null));

        jPanel3.setBackground(new java.awt.Color(74, 31, 61));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Lịch sử bán hàng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thời gian "));

        jLabel3.setText("Ngày:");

        date.setDateFormatString("yyyy-MM-dd");
        date.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dateAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        date.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateMouseClicked(evt);
            }
        });

        btnTim.setText("Tìm kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTim)
                        .addGap(2, 2, 2))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTim)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(74, 31, 61));

        btnDelete.setBackground(new java.awt.Color(255, 255, 51));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bin.png"))); // NOI18N
        btnDelete.setText("Xóa");

        btnDelete1.setBackground(new java.awt.Color(255, 255, 51));
        btnDelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ins.png"))); // NOI18N
        btnDelete1.setText("In");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete1)
                .addContainerGap(407, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết hóa đơn"));

        tb2.setModel(new javax.swing.table.DefaultTableModel(
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
                "Mã hóa đơn chi tiết", "Ngày bán", "Tên khách", "SĐT", "Mã nhân viên", "Trạng thái", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        jScrollPane4.setViewportView(tb2);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        tb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
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
                "Mã hóa đơn", "Ngày bán", "Tên nhân viên", "Tên khách hàng", "Tổng tiền", "Trạng thái", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb1);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        tfGhiChu2.setColumns(20);
        tfGhiChu2.setRows(5);
        jScrollPane3.setViewportView(tfGhiChu2);

        tfGhiChu1.setColumns(20);
        tfGhiChu1.setRows(5);
        jScrollPane5.setViewportView(tfGhiChu1);

        jLabel4.setText("Ghi chú : ");

        jLabel5.setText("Ghi chú : ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)))
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addContainerGap())
        );

        jPanel3.getAccessibleContext().setAccessibleName("Tìm kiếm");

        btnExit.setBackground(new java.awt.Color(153, 153, 255));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exits.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Admin");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logos.png"))); // NOI18N

        jButton4.setBackground(new java.awt.Color(153, 153, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/history-book.png"))); // NOI18N
        jButton4.setText("Lịch sử bán hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnNhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btnSanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongkeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThongkeActionPerformed

    private void btnNhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanvienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNhanvienActionPerformed

    private void btnSanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanphamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSanphamActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed

        this.dispose();
        Home home = new Home(taiKhoan, maNV, tenNV, passNV);
        home.setVisible(true);
    }//GEN-LAST:event_btnExitActionPerformed

    private void tb2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb2MouseClicked
        // TODO add your handling code here:
        index2 = tb2.getSelectedRow();
        tfGhiChu2.setText((String) tb2.getValueAt(index2, 6));

    }//GEN-LAST:event_tb2MouseClicked

    private void tb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb1MouseClicked
        index = tb1.getSelectedRow();
        int a = (int) tb1.getValueAt(index, 0);
        String b = (String) tb1.getValueAt(index, 5);
        tfGhiChu1.setText((String) tb1.getValueAt(index, 6));
        try {
            list4 = getListTam2(a);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LichSuHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LichSuHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (b.equalsIgnoreCase("Đã hủy")) {
            showTable3();
        } else {
            showTable2();
        }

    }//GEN-LAST:event_tb1MouseClicked

    private void dateAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dateAncestorAdded

    }//GEN-LAST:event_dateAncestorAdded

    private void dateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateMouseClicked


    }//GEN-LAST:event_dateMouseClicked

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        if (date.getDate() == null) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn ngày xem");
            return;
        }
        date.requestFocus();
        long a = date.getDate().getTime();
        System.out.println(a);
        model1.setRowCount(0);
        System.out.println("---------->>>>>");
        for (int i = 0; i < list3.size(); i++) {
            long b = list3.get(i).getNgayBan().getTime();
            System.out.println(b);
            if (a == b) {
                System.out.println("----------");
                model1.addRow(new Object[]{
                    list3.get(i).getMaHoaDon(),
                    list3.get(i).getNgayBan(),
                    list3.get(i).getTenNhanVien(),
                    list3.get(i).getTenKhachHang(),
                    list3.get(i).getTongTien(),
                    list3.get(i).getTrangThai(),});
            }
        }
    }//GEN-LAST:event_btnTimActionPerformed

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
            java.util.logging.Logger.getLogger(LichSuHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LichSuHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LichSuHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LichSuHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new LichSuHoaDon(taiKhoan, maNV, tenNV, passNV).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNhanvien;
    private javax.swing.JButton btnSanpham;
    private javax.swing.JButton btnThongke;
    private javax.swing.JButton btnTim;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tb1;
    private javax.swing.JTable tb2;
    private javax.swing.JTextArea tfGhiChu1;
    private javax.swing.JTextArea tfGhiChu2;
    // End of variables declaration//GEN-END:variables
}
