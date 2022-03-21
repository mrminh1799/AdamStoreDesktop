/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Entity;

import java.util.Date;

/**
 *
 * @author Minh
 */
public class Invoice {

    private int maHoaDon;
    private String maNhanVien;
    private Date ngayBan;
    private String MaKhachHang;
    private float tongTien;
    private String trangThai;
    private String ghiChu;

    public Invoice() {
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Invoice(int maHoaDon, String maNhanVien, Date ngayBan, String MaKhachHang, float tongTien, String trangThai, String ghiChu) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.ngayBan = ngayBan;
        this.MaKhachHang = MaKhachHang;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public Invoice(int maHoaDon, String maNhanVien, Date ngayBan, String MaKhachHang, float tongTien, String trangThai) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.ngayBan = ngayBan;
        this.MaKhachHang = MaKhachHang;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }


}
