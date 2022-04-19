/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Entity;

/**
 *
 * @author Minh
 */
public class InvoiceDetail {

    private int MaHoaDon; 
    private int MaHDCT;
    private String MaSanPhamChiTiet;
    private int SoLuong;
    private float DonGia;
    private float GiamGia;
    private float Tong;
    private String trangThai;
    private String GhiChu;

    public InvoiceDetail() {
    }

    public InvoiceDetail(int MaHoaDon, int MaHDCT, String MaSanPhamChiTiet, int SoLuong, float DonGia, float GiamGia, float Tong, String trangThai, String GhiChu) {
        this.MaHoaDon = MaHoaDon;
        this.MaHDCT = MaHDCT;
        this.MaSanPhamChiTiet = MaSanPhamChiTiet;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.GiamGia = GiamGia;
        this.Tong = Tong;
        this.trangThai = trangThai;
        this.GhiChu = GhiChu;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public int getMaHDCT() {
        return MaHDCT;
    }

    public void setMaHDCT(int MaHDCT) {
        this.MaHDCT = MaHDCT;
    }

    public String getMaSanPhamChiTiet() {
        return MaSanPhamChiTiet;
    }

    public void setMaSanPhamChiTiet(String MaSanPhamChiTiet) {
        this.MaSanPhamChiTiet = MaSanPhamChiTiet;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public float getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(float GiamGia) {
        this.GiamGia = GiamGia;
    }

    public float getTong() {
        return Tong;
    }

    public void setTong(float Tong) {
        this.Tong = Tong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
     

   

}
