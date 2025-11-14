package controller;


import entity.Customer;
import entity.CustomerNN;
import entity.CustomerVN;
import entity.HoaDon;
import repository.HoaDonRepository;
import repository.CustomerRepository;
import view.BillView;

import java.time.LocalDate;
import java.util.*;

public class BillController {
    private CustomerRepository khRepo;
    private HoaDonRepository hdRepo;
    private BillView view;

    public BillController(CustomerRepository khRepo, HoaDonRepository hdRepo, BillView view) {
        this.khRepo = khRepo;
        this.hdRepo = hdRepo;
        this.view = view;
    }

    public void run() {
        while (true) {
            view.hienThiMenu();
            switch (view.nhapLuaChon()) {
                case 1 -> themKhachHang();
                case 2 -> view.hienThiKhachHang(khRepo.layTatCa());
                case 3 -> timKhachHang();
                case 4 -> themHoaDon();
                case 5 -> suaHoaDon();
                case 6 -> view.hienThiHoaDon(hdRepo.layTatCa(), khRepo);
                case 7 -> { System.out.println("Kết thúc!"); return; }
                default -> view.loi("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void themKhachHang() {
        try {
            int loai = view.chonLoaiKhach();
            String ma = "";

            if (loai == 1) { // Việt Nam
                // Tạo mã tự động
                int nextId = khRepo.getNextIdByLoai("KHVN"); // Khai báo method trong repo
                ma = String.format("KHVN-%05d", nextId);

                String ten = view.nhap("Nhập họ tên: ");

                // Chọn loại khách VN
                Map<String, String> mapLoai = khRepo.getLoaiKhachMap();
                System.out.println("\n--- Loại khách hàng ---");
                mapLoai.forEach((k, v) -> System.out.println(k + " - " + v));

                // Hiển thị danh sách loại khách Việt Nam với số thứ tự
                List<String> keys = new ArrayList<>(mapLoai.keySet());

                System.out.println("\n--- Loại khách hàng ---");
                for (int i = 0; i < keys.size(); i++) {
                    System.out.println((i + 1) + ". " + mapLoai.get(keys.get(i)));
                }

                // Người dùng nhập số thứ tự
                int chon = (int) view.nhapSo("Chọn loại khách (nhập số): ");
                if (chon < 1 || chon > keys.size()) {
                    view.loi("Số thứ tự không hợp lệ!");
                    return;
                }
                String maLoai = keys.get(chon - 1); // Lấy mã loại tương ứng

                double dinhMuc = view.nhapSo("Nhập định mức tiêu thụ: ");
                khRepo.themKhachHang(new CustomerVN(ma, ten, maLoai, dinhMuc));
                view.thongBao("Thêm khách hàng Việt Nam thành công! Mã KH: " + ma);

            } else if (loai == 2) { // Nước ngoài
                // Tạo mã tự động
                int nextId = khRepo.getNextIdByLoai("KHNN");
                ma = String.format("KHNN-%05d", nextId);

                String ten = view.nhap("Nhập họ tên: ");
                String quocTich = view.nhap("Nhập quốc tịch: ");

                khRepo.themKhachHang(new CustomerNN(ma, ten, quocTich));
                view.thongBao("Thêm khách hàng nước ngoài thành công! Mã KH: " + ma);

            } else {
                view.loi("Loại khách hàng không hợp lệ!");
            }

        } catch (Exception e) {
            view.loi("Lỗi nhập dữ liệu!");
        }
    }

    private void timKhachHang() {
        System.out.println("\n1. Theo mã KH\n2. Theo tên KH");
        int c = view.nhapLuaChon();
        if (c == 1) {
            String ma = view.nhap("Nhập mã KH: ");
            Customer kh = khRepo.timTheoMa(ma);
            if (kh != null) view.hienThiKhachHang(List.of(kh));
            else view.loi("Không tìm thấy KH!");
        } else if (c == 2) {
            String ten = view.nhap("Nhập tên KH: ");
            view.hienThiKhachHang(khRepo.timTheoTen(ten));
        } else view.loi("Chọn sai!");
    }

    private void themHoaDon() {
        view.hienThiKhachHang(khRepo.layTatCa());
        String maKh = view.nhap("Nhập mã KH: ");
        Customer kh = khRepo.timTheoMa(maKh);
        if (kh == null) { view.loi("Mã KH không tồn tại!"); return; }

        // Tạo mã hóa đơn tự động
        int nextId = hdRepo.getNextId(); // phương thức lấy số thứ tự tiếp theo
        String maHd = String.format("MHD-%03d", nextId); // MHD-001, MHD-002,...

        LocalDate ngay = view.nhapNgay("Nhập ngày (dd/MM/yyyy): ");
        double luong = view.nhapSo("Nhập lượng tiêu thụ: ");
        double dinhMuc = view.nhapSo("Nhập định mức: ");
        double donGia = view.nhapSo("Nhập đơn giá: ");

        HoaDon hd = new HoaDon(maHd, maKh, ngay, luong, dinhMuc, donGia);
        double thanhTien = kh.tinhTienDien(luong, donGia, dinhMuc);
        hd.setThanhTien(thanhTien);
        hdRepo.themHoaDon(hd);

        view.thongBao("Thêm hóa đơn thành công! Thành tiền: " + thanhTien);
    }

    private void suaHoaDon() {
        view.hienThiHoaDon(hdRepo.layTatCa(), khRepo);
        String ma = view.nhap("Nhập mã HD cần sửa: ");
        HoaDon hd = hdRepo.timTheoMa(ma);
        if (hd == null) { view.loi("Không tồn tại HD!"); return; }

        System.out.println("1. Sửa lượng tiêu thụ\n2. Sửa đơn giá");
        int c = view.nhapLuaChon();
        if (c == 1) hd.setLuongTieuThu(view.nhapSo("Nhập lượng mới: "));
        else if (c == 2) hd.setDonGia(view.nhapSo("Nhập giá mới: "));
        else { view.loi("Sai lựa chọn!"); return; }

        Customer kh = khRepo.timTheoMa(hd.getMaKhachHang());
        double thanhTien = kh.tinhTienDien(hd.getLuongTieuThu(), hd.getDonGia(), hd.getSoKWDinhMuc());
        hd.setThanhTien(thanhTien);
        view.thongBao("Cập nhật thành công!");
    }
    public void hienThiKhachHang(List<Customer> ds, CustomerRepository repo) {
        if (ds.isEmpty()) { System.out.println("Không có khách hàng!"); return; }
        System.out.printf("%-15s %-20s %-15s %-15s%n", "Mã KH", "Họ tên", "Loại KH", "Định mức");
        for (Customer kh : ds) {
            if (kh instanceof CustomerVN vn)
                System.out.printf("%-15s %-20s %-15s %-15.0f%n",
                        vn.getMaKhachHang(), vn.getHoTen(),
                        repo.getTenLoaiKhach(vn.getLoaiKhach()), vn.getDinhMuc());
            else if (kh instanceof CustomerNN nn)
                System.out.printf("%-15s %-20s %-15s %-15s%n",
                        nn.getMaKhachHang(), nn.getHoTen(),
                        "Nước ngoài (" + nn.getQuocTich() + ")", "-");
        }
    }

}
