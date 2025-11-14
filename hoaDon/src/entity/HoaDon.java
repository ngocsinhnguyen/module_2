package entity;

import java.time.LocalDate;

public class HoaDon {
    private String maHoaDon;
    private String maKhachHang;
    private LocalDate ngayRaHoaDon;
    private double luongTieuThu;
    private double soKWDinhMuc;
    private double donGia;
    private double thanhTien;

    public HoaDon(String maHoaDon, String maKhachHang, LocalDate ngayRaHoaDon,
                  double luongTieuThu, double soKWDinhMuc, double donGia) {
        this.maHoaDon = maHoaDon;
        this.maKhachHang = maKhachHang;
        this.ngayRaHoaDon = ngayRaHoaDon;
        this.luongTieuThu = luongTieuThu;
        this.soKWDinhMuc = soKWDinhMuc;
        this.donGia = donGia;
    }

    public String getMaHoaDon() { return maHoaDon; }
    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String ma) { this.maKhachHang = ma; }
    public LocalDate getNgayRaHoaDon() { return ngayRaHoaDon; }
    public void setNgayRaHoaDon(LocalDate ngay) { this.ngayRaHoaDon = ngay; }
    public double getLuongTieuThu() { return luongTieuThu; }
    public void setLuongTieuThu(double luong) { this.luongTieuThu = luong; }
    public double getSoKWDinhMuc() { return soKWDinhMuc; }
    public double getDonGia() { return donGia; }
    public void setDonGia(double gia) { this.donGia = gia; }
    public double getThanhTien() { return thanhTien; }
    public void setThanhTien(double tien) { this.thanhTien = tien; }

    @Override
    public String toString() {
        return String.format("%-12s %-15s %s %15.2f %15.0f %12.0f %15.2f",
                maHoaDon, maKhachHang, ngayRaHoaDon, luongTieuThu, soKWDinhMuc, donGia, thanhTien);
    }
}
