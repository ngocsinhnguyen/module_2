package entity;

public class CustomerNN extends Customer {
    private String quocTich;

    public CustomerNN(String maKhachHang, String hoTen, String quocTich) {
        super(maKhachHang, hoTen);
        this.quocTich = quocTich;
    }



    @Override
    public double tinhTienDien(double luongTieuThu, double donGia, double sKWDinhMuc) {
        return luongTieuThu * donGia;
    }

    public String getQuocTich() { return quocTich; }

    @Override
    public String toString() {
        return String.format("%-15s %-20s %-25s %s", maKhachHang, hoTen, quocTich);
    }
}
