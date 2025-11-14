package entity;

public abstract class Customer {
    protected String maKhachHang;
    protected String hoTen;

    public Customer(String maKhachHang, String hoTen) {
        this.maKhachHang = maKhachHang;
        this.hoTen = hoTen;
    }



    public abstract double tinhTienDien(double luongTieuThu, double donGia, double sKWDinhMuc);
    public abstract String toString();

    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String ma) { this.maKhachHang = ma; }
    public String getHoTen() { return hoTen; }
    public void setHoTen(String ten) { this.hoTen = ten; }
}
