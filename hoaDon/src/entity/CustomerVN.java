package entity;
public class CustomerVN extends Customer {
    private double dinhMuc; // định mức tiêu thụ (KW)
    private String loaiKhach;

    public CustomerVN(String maKhachHang, String hoTen, String loaiKhach, double dinhMuc) {
        super(maKhachHang, hoTen);
        this.loaiKhach = loaiKhach; // lưu mã LKH-001, LKH-002, LKH-003
        this.dinhMuc = dinhMuc;
    }

    public String getLoaiKhach() {
        return loaiKhach;
    }

    public void setLoaiKhach(String loaiKhach) {
        this.loaiKhach = loaiKhach;
    }

    public double getDinhMuc() { return dinhMuc; }
    public void setDinhMuc(double dinhMuc) { this.dinhMuc = dinhMuc; }

    @Override
    public double tinhTienDien(double luongTieuThu, double donGia, double sKWDinhMuc) {
        if (luongTieuThu <= sKWDinhMuc)
            return luongTieuThu * donGia;
        else {
            double vuot = luongTieuThu - sKWDinhMuc;
            return sKWDinhMuc * donGia + vuot * donGia * 2.5;
        }
    }

    @Override
    public String toString() {
        return String.format("%-15s %-20s %-10s %-10.0f",
                maKhachHang, hoTen, loaiKhach, dinhMuc);
    }
}
