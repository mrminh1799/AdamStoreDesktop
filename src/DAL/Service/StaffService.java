/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Service;

import DAL.Entity.Product_1;
import DAL.Entity.Staff;
import Utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
 * @author Minh
 */
public class StaffService implements WareHouse<Staff, String> {

    @Override
    public void insert(Staff entity) {
        String sql = "insert into Staff(MaNhanVien,TaiKhoan,TenNhanVien,MatKhau,GioiTinh,DiaChi,DienThoai,Email,NgaySinh,VaiTro,TrangThai,Avatar)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql, entity.getMaNhanVien(),
                entity.getTaiKhoan(),
                entity.getTenNhanVien(),
                entity.getMatKhau(),
                entity.isGioiTinh(),
                entity.getDiaChi(),
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getNgaySinh(),
                entity.isVaiTro(),
                entity.isTrangThai(),
                entity.getAvatar()
        );

    }

    @Override
    public void update(Staff entity) {
        String sql = "update Staff set TaiKhoan=?,TenNhanVien=?,MatKhau=?,GioiTinh=?,DiaChi=?,DienThoai=?,Email=?,NgaySinh=?,VaiTro=?,TrangThai=?,Avatar =? where MaNhanVien =?";
        JdbcHelper.executeUpdate(sql,
                entity.getTaiKhoan(),
                entity.getTenNhanVien(),
                entity.getMatKhau(),
                entity.isGioiTinh(),
                entity.getDiaChi(),
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getNgaySinh(),
                entity.isVaiTro(),
                entity.isTrangThai(),
                entity.getAvatar(),
                entity.getMaNhanVien()
        );

    }

    public void update1(Staff entity) {
        String sql = "update Staff set MatKhau=? where TaiKhoan =?";
        JdbcHelper.executeUpdate(sql,
                entity.getMatKhau(),
                entity.getTaiKhoan()
        );

    }

    @Override
    public void delete(String key) {
        String sql = "update Staff set TrangThai = 0 where MaNhanVien =?";
        JdbcHelper.executeUpdate(sql, key);

    }

    @Override
    public List<Staff> selectALL() {
        return selectbySQL("Select * from Staff where TrangThai = 1");
    }

    public List<Staff> selectALLNVOFF() {
        return selectbySQL("Select * from Staff where TrangThai = 0");
    }

    public void UPDATENVONLINE(String mavn) {
        String sql = "update Staff set TrangThai = 1 where MaNhanVien =?";
        JdbcHelper.executeUpdate(sql, mavn);
    }

    @Override
    public Staff select_by_id(String key) {
        String sql = "select * from Staff where MaNhanVien =?";
        List<Staff> list = selectbySQL(sql, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }

    public List<Staff> Select_By_Name(String name) {
        String sql = "Select * from Staff where TenNhanVien like '%" + name + "%'";
        return selectbySQL(sql);
    }

    @Override
    public List<Staff> selectbySQL(String sql, Object... arg) {
        List<Staff> listStaffs = new ArrayList<>();
        try {

            ResultSet rs = JdbcHelper.executeQuery(sql, arg);
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setMaNhanVien(rs.getString("MaNhanVien"));
                staff.setTaiKhoan(rs.getString("TaiKhoan"));
                staff.setMatKhau(rs.getString("MatKhau"));
                staff.setTenNhanVien(rs.getString("TenNhanVien"));
                staff.setGioiTinh(rs.getBoolean("GioiTinh"));
                staff.setDiaChi(rs.getString("DiaChi"));
                staff.setDienThoai(rs.getString("DienThoai"));
                staff.setEmail(rs.getString("Email"));
                staff.setNgaySinh(rs.getDate("NgaySinh"));
                staff.setVaiTro(rs.getBoolean("VaiTro"));
                staff.setTrangThai(rs.getBoolean("TrangThai"));
                staff.setAvatar(rs.getString("Avatar"));
                staff.setMaNhanVien(rs.getString("MaNhanVien"));
                listStaffs.add(staff);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listStaffs;

    }

    public Staff selectEmail(String email_) {
        String sql = "	Select * from Staff where Email =?";
        List<Staff> list = selectbySQL(sql, email_);
        System.out.println(list);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void UpdatePass(Staff entity) {
        String sql = "update Staff set MatKhau =? where Email=?";
        JdbcHelper.executeUpdate(sql,
                entity.getMatKhau(),
                entity.getEmail()
        );

    }

}
