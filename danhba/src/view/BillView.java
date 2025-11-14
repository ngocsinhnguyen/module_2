package view;

import entity.*;
import repository.CustomerRepository;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class BilllView {
    private Scanner sc = new Scanner(System.in);

    public void hienThiMenu() {
        System.out.println("\n========== CHƯƠNG TRÌNH QUẢN LÝ==========");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Đọc từ file");
        System.out.println("7. Ghi từ file");
        System.out.println("7. Ghi từ file");
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



    public void thongBao(String msg) { System.out.println("\n" + msg); }
    public void loi(String msg) { System.out.println("\n*** LỖI: " + msg + " ***"); }
}






public void hienThiMenu() {
    System.out.println("\n========== CHƯƠNG TRÌNH QUẢN LÝ==========");
    System.out.println("1. Xem danh sách");
    System.out.println("2. Thêm mới");
    System.out.println("3. Cập nhật");
    System.out.println("4. Xóa");
    System.out.println("5. Tìm kiếm");
    System.out.println("6. Đọc từ file");
    System.out.println("7. Ghi từ file");
    System.out.println("7. Ghi từ file");
    System.out.println("7. Thoát");
    System.out.print("Chọn: ");
}