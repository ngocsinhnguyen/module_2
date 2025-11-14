import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

// ============== MODEL ==============

// Abstract class Customer
abstract class Customer {
    protected String maKhachHang;
    protected String hoTen;
    protected String loaiKhach;

    public Customer(String maKhachHang, String hoTen) {
        this.maKhachHang = maKhachHang;
        this.hoTen = hoTen;
    }

    public abstract double tinhTienDien(double luongTieuThu, double donGia, double sKWDinhMuc);

    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String ma) { this.maKhachHang = ma; }
    public String getHoTen() { return hoTen; }
    public void setHoTen(String ten) { this.hoTen = ten; }
    public String getLoaiKhach() { return loaiKhach; }
}

// Customer Việt Nam
class CustomerVN extends Customer {
    public CustomerVN(String maKhachHang, String hoTen) {
        super(maKhachHang, hoTen);
        this.loaiKhach = "Khách hàng Việt Nam";
    }

    @Override
    public double tinhTienDien(double luongTieuThu, double donGia, double sKWDinhMuc) {
        if (luongTieuThu <= sKWDinhMuc) {
            return luongTieuThu * donGia;
        } else {
            return luongTieuThu * donGia * sKWDinhMuc * 2.5;
        }
    }

    @Override
    public String toString() {
        return String.format("%-15s %-20s %s", maKhachHang, hoTen, loaiKhach);
    }
}

// Customer Nước ngoài
class CustomerNN extends Customer {
    protected String quocTich;

    public CustomerNN(String maKhachHang, String hoTen, String quocTich) {
        super(maKhachHang, hoTen);
        this.quocTich = quocTich;
        this.loaiKhach = "Khách hàng nước ngoài";
    }

    @Override
    public double tinhTienDien(double luongTieuThu, double donGia, double sKWDinhMuc) {
        return luongTieuThu * donGia;
    }

    public String getQuocTich() { return quocTich; }

    @Override
    public String toString() {
        return String.format("%-15s %-20s %-25s %s", maKhachHang, hoTen, quocTich, loaiKhach);
    }
}

// Class HóaDơn
class HoaDon {
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

// ============== MODEL ==============
class Model {
    private List<Customer> dsKhachHang;
    private List<HoaDon> dsHoaDon;

    public Model() {
        dsKhachHang = new ArrayList<>();
        dsHoaDon = new ArrayList<>();
    }

    // Customer methods
    public void themKhachHang(Customer kh) {
        dsKhachHang.add(kh);
    }

    public List<Customer> layDanhSachKhachHang() {
        return dsKhachHang;
    }

    public Customer timKhachHangTheoMa(String ma) {
        for (Customer kh : dsKhachHang) {
            if (kh.getMaKhachHang().equals(ma)) {
                return kh;
            }
        }
        return null;
    }

    public List<Customer> timKhachHangTheoTen(String ten) {
        List<Customer> kq = new ArrayList<>();
        for (Customer kh : dsKhachHang) {
            if (kh.getHoTen().toLowerCase().contains(ten.toLowerCase())) {
                kq.add(kh);
            }
        }
        return kq;
    }

    // HoaDon methods
    public void themHoaDon(HoaDon hd) {
        dsHoaDon.add(hd);
    }

    public List<HoaDon> layDanhSachHoaDon() {
        return dsHoaDon;
    }

    public HoaDon timHoaDonTheoMa(String ma) {
        for (HoaDon hd : dsHoaDon) {
            if (hd.getMaHoaDon().equals(ma)) {
                return hd;
            }
        }
        return null;
    }

    public List<HoaDon> timHoaDonTheoMaKhachHang(String maKh) {
        List<HoaDon> kq = new ArrayList<>();
        for (HoaDon hd : dsHoaDon) {
            if (hd.getMaKhachHang().equals(maKh)) {
                kq.add(hd);
            }
        }
        return kq;
    }

    public void xoaHoaDon(String ma) {
        dsHoaDon.removeIf(hd -> hd.getMaHoaDon().equals(ma));
    }

    public void xoaKhachHang(String ma) {
        dsKhachHang.removeIf(kh -> kh.getMaKhachHang().equals(ma));
    }
}

// ============== VIEW ==============
class View {
    private Scanner sc;

    public View() {
        sc = new Scanner(System.in);
    }

    public void hienThiMenu() {
        System.out.println("\n========== CHƯƠNG TRÌNH QUẢN LÝ HÓA ĐƠN TIỀN ĐIỆN ==========");
        System.out.println("1. Thêm mới khách hàng");
        System.out.println("2. Hiển thị thông tin khách hàng");
        System.out.println("3. Tìm kiếm khách hàng");
        System.out.println("4. Thêm mới hóa đơn");
        System.out.println("5. Chỉnh sửa hóa đơn");
        System.out.println("6. Hiển thị chi tiết hóa đơn");
        System.out.println("7. Thoát");
        System.out.print("Chọn chức năng: ");
    }

    public int nhapLuaChon() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int hienThiMenuKhachHang() {
        System.out.println("\n--- Chọn loại khách hàng ---");
        System.out.println("1. Khách hàng Việt Nam");
        System.out.println("2. Khách hàng nước ngoài");
        System.out.print("Lựa chọn: ");
        return Integer.parseInt(sc.nextLine().trim());
    }

    public String nhapMaKhachHang() {
        System.out.print("Nhập mã khách hàng: ");
        return sc.nextLine().trim();
    }

    public String nhapHoTen() {
        System.out.print("Nhập họ tên: ");
        return sc.nextLine().trim();
    }

    public String nhapQuocTich() {
        System.out.print("Nhập quốc tịch: ");
        return sc.nextLine().trim();
    }

    public void hienThiDanhSachKhachHang(List<Customer> dskh) {
        if (dskh.isEmpty()) {
            System.out.println("Không có khách hàng nào!");
            return;
        }
        System.out.println("\n--- Danh sách khách hàng ---");
        System.out.println(String.format("%-15s %-20s %-25s %-25s", "Mã KH", "Họ tên", "Quốc tịch/Loại", "Phân loại"));
        System.out.println("=".repeat(85));
        for (Customer kh : dskh) {
            System.out.println(kh);
        }
    }

    public String nhapMaHoaDon() {
        System.out.print("Nhập mã hóa đơn: ");
        return sc.nextLine().trim();
    }

    public LocalDate nhapNgayRaHoaDon() {
        System.out.print("Nhập ngày ra hóa đơn (dd/MM/yyyy): ");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date date = sdf.parse(sc.nextLine().trim());
            return new java.sql.Date(date.getTime()).toLocalDate();
        } catch (Exception e) {
            return LocalDate.now();
        }
    }

    public double nhapLuongTieuThu() {
        System.out.print("Nhập lượng tiêu thụ (KW): ");
        return Double.parseDouble(sc.nextLine().trim());
    }

    public double nhapSoKWDinhMuc() {
        System.out.print("Nhập số KW định mức: ");
        return Double.parseDouble(sc.nextLine().trim());
    }

    public double nhapDonGia() {
        System.out.print("Nhập đơn giá: ");
        return Double.parseDouble(sc.nextLine().trim());
    }

    public void hienThiDanhSachHoaDon(List<HoaDon> dshd) {
        if (dshd.isEmpty()) {
            System.out.println("Không có hóa đơn nào!");
            return;
        }
        System.out.println("\n--- Danh sách hóa đơn ---");
        System.out.println(String.format("%-12s %-15s %-10s %15s %15s %12s %15s",
                "Mã HD", "Mã KH", "Ngày", "Lượng(KW)", "Định mức", "Đơn giá", "Thành tiền"));
        System.out.println("=".repeat(100));
        for (HoaDon hd : dshd) {
            System.out.println(hd);
        }
    }

    public void hienThiThongBao(String thongbao) {
        System.out.println("\n" + thongbao);
    }

    public void hienThiLoi(String loi) {
        System.out.println("\n*** LỖI: " + loi + " ***");
    }
}

// ============== CONTROLLER ==============
class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void chayUngDung() {
        while (true) {
            view.hienThiMenu();
            int luaChon = view.nhapLuaChon();

            switch (luaChon) {
                case 1:
                    themKhachHang();
                    break;
                case 2:
                    hienThiKhachHang();
                    break;
                case 3:
                    timKiemKhachHang();
                    break;
                case 4:
                    themHoaDon();
                    break;
                case 5:
                    chinhSuaHoaDon();
                    break;
                case 6:
                    hienThiHoaDon();
                    break;
                case 7:
                    System.out.println("\nKết thúc chương trình!");
                    return;
                default:
                    view.hienThiLoi("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void themKhachHang() {
        try {
            int loai = view.hienThiMenuKhachHang();
            String ma = view.nhapMaKhachHang();

            if (model.timKhachHangTheoMa(ma) != null) {
                view.hienThiLoi("Mã khách hàng đã tồn tại!");
                return;
            }

            String hoTen = view.nhapHoTen();

            Customer kh;
            if (loai == 1) {
                kh = new CustomerVN(ma, hoTen);
            } else if (loai == 2) {
                String quocTich = view.nhapQuocTich();
                kh = new CustomerNN(ma, hoTen, quocTich);
            } else {
                view.hienThiLoi("Loại khách hàng không hợp lệ!");
                return;
            }

            model.themKhachHang(kh);
            view.hienThiThongBao("Thêm khách hàng thành công!");
        } catch (Exception e) {
            view.hienThiLoi("Lỗi nhập dữ liệu!");
        }
    }

    private void hienThiKhachHang() {
        List<Customer> dskh = model.layDanhSachKhachHang();
        view.hienThiDanhSachKhachHang(dskh);
    }

    private void timKiemKhachHang() {
        System.out.println("\n--- Tìm kiếm khách hàng ---");
        System.out.println("1. Tìm theo mã khách hàng");
        System.out.println("2. Tìm theo tên khách hàng");
        System.out.print("Lựa chọn: ");
        int luaChon = view.nhapLuaChon();

        if (luaChon == 1) {
            String ma = view.nhapMaKhachHang();
            Customer kh = model.timKhachHangTheoMa(ma);
            if (kh != null) {
                List<Customer> kq = new ArrayList<>();
                kq.add(kh);
                view.hienThiDanhSachKhachHang(kq);
            } else {
                view.hienThiLoi("Không tìm thấy khách hàng!");
            }
        } else if (luaChon == 2) {
            String ten = view.nhapHoTen();
            List<Customer> kq = model.timKhachHangTheoTen(ten);
            view.hienThiDanhSachKhachHang(kq);
        } else {
            view.hienThiLoi("Lựa chọn không hợp lệ!");
        }
    }

    private void themHoaDon() {
        try {
            view.hienThiDanhSachKhachHang(model.layDanhSachKhachHang());
            String maKh = view.nhapMaKhachHang();
            Customer kh = model.timKhachHangTheoMa(maKh);

            if (kh == null) {
                view.hienThiLoi("Mã khách hàng không tồn tại!");
                return;
            }

            String maHd = view.nhapMaHoaDon();
            if (model.timHoaDonTheoMa(maHd) != null) {
                view.hienThiLoi("Mã hóa đơn đã tồn tại!");
                return;
            }

            LocalDate ngay = view.nhapNgayRaHoaDon();
            double luongTieu = view.nhapLuongTieuThu();
            double kWDinhMuc = view.nhapSoKWDinhMuc();
            double donGia = view.nhapDonGia();

            HoaDon hd = new HoaDon(maHd, maKh, ngay, luongTieu, kWDinhMuc, donGia);
            double thanhTien = kh.tinhTienDien(luongTieu, donGia, kWDinhMuc);
            hd.setThanhTien(thanhTien);

            model.themHoaDon(hd);
            view.hienThiThongBao("Thêm hóa đơn thành công! Thành tiền: " + thanhTien);
        } catch (Exception e) {
            view.hienThiLoi("Lỗi nhập dữ liệu!");
        }
    }

    private void chinhSuaHoaDon() {
        try {
            view.hienThiDanhSachHoaDon(model.layDanhSachHoaDon());
            String ma = view.nhapMaHoaDon();
            HoaDon hd = model.timHoaDonTheoMa(ma);

            if (hd == null) {
                view.hienThiLoi("Mã hóa đơn không tồn tại!");
                return;
            }

            System.out.println("\n--- Chỉnh sửa hóa đơn ---");
            System.out.println("1. Chỉnh sửa lượng tiêu thụ");
            System.out.println("2. Chỉnh sửa đơn giá");
            System.out.print("Lựa chọn: ");
            int luaChon = view.nhapLuaChon();

            if (luaChon == 1) {
                double luong = view.nhapLuongTieuThu();
                hd.setLuongTieuThu(luong);
            } else if (luaChon == 2) {
                double gia = view.nhapDonGia();
                hd.setDonGia(gia);
            } else {
                view.hienThiLoi("Lựa chọn không hợp lệ!");
                return;
            }

            Customer kh = model.timKhachHangTheoMa(hd.getMaKhachHang());
            double thanhTien = kh.tinhTienDien(hd.getLuongTieuThu(), hd.getDonGia(), hd.getSoKWDinhMuc());
            hd.setThanhTien(thanhTien);

            view.hienThiThongBao("Chỉnh sửa hóa đơn thành công!");
        } catch (Exception e) {
            view.hienThiLoi("Lỗi nhập dữ liệu!");
        }
    }

    private void hienThiHoaDon() {
        view.hienThiDanhSachHoaDon(model.layDanhSachHoaDon());
    }
}

// ============== MAIN ==============
