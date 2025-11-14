package view;

import entity.*;
import repository.CustomerRepository;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class BillView {
    private Scanner sc = new Scanner(System.in);

    public void hienThiMenu() {
        System.out.println("\n========== QUẢN LÝ HÓA ĐƠN TIỀN ĐIỆN ==========");
        System.out.println("1. Thêm khách hàng");
        System.out.println("2. Hiển thị khách hàng");
        System.out.println("3. Tìm kiếm khách hàng");
        System.out.println("4. Thêm hóa đơn");
        System.out.println("5. Chỉnh sửa hóa đơn");
        System.out.println("6. Hiển thị hóa đơn");
        System.out.println("7. Thoát");
        System.out.print("Chọn: ");
    }

    public int nhapLuaChon() {
        try { return Integer.parseInt(sc.nextLine().trim()); }
        catch (Exception e) { return -1; }
    }

    public int chonLoaiKhach() {
        System.out.println("\n1. Việt Nam");
        System.out.println("2. Nước ngoài");
        System.out.print("Lựa chọn: ");
        return Integer.parseInt(sc.nextLine().trim());
    }

    public String nhap(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    public double nhapSo(String msg) {
        System.out.print(msg);
        return Double.parseDouble(sc.nextLine().trim());
    }

    public LocalDate nhapNgay(String msg) {
        System.out.print(msg);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date date = sdf.parse(sc.nextLine().trim());
            return new java.sql.Date(date.getTime()).toLocalDate();
        } catch (Exception e) { return LocalDate.now(); }
    }

    public void hienThiKhachHang(List<Customer> ds) {
        if (ds.isEmpty()) { System.out.println("Không có KH nào!"); return; }
        System.out.println("\n--- Danh sách KH ---");
        for (Customer kh : ds) System.out.println(kh);
    }

    public void hienThiHoaDon(List<HoaDon> ds, CustomerRepository khRepo) {
        if (ds.isEmpty()) {
            System.out.println("Không có hóa đơn!");
            return;
        }

        System.out.println("\n--- Danh sách hóa đơn ---");
        System.out.printf("%-10s %-20s %-12s %-10s %-10s %-10s %-12s\n",
                "Mã HD", "Họ tên KH", "Ngày HD", "Lượng", "Định mức", "Đơn giá", "Thành tiền");

        for (HoaDon hd : ds) {
            Customer kh = khRepo.timTheoMa(hd.getMaKhachHang());
            String hoTen = (kh != null) ? kh.getHoTen() : "Không xác định";

            System.out.printf("%-10s %-20s %-12s %-10.2f %-10.2f %-10.2f %-12.2f\n",
                    hd.getMaHoaDon(),
                    hoTen,
                    hd.getNgayRaHoaDon().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    hd.getLuongTieuThu(),
                    hd.getSoKWDinhMuc(),
                    hd.getDonGia(),
                    hd.getThanhTien());
        }
    }



    public void thongBao(String msg) { System.out.println("\n" + msg); }
    public void loi(String msg) { System.out.println("\n*** LỖI: " + msg + " ***"); }
}
