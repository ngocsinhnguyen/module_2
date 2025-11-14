package repository;

import entity.HoaDon;
import util.FileUtil;

import java.time.LocalDate;
import java.util.*;

public class HoaDonRepository {
    private List<HoaDon> dsHoaDon = new ArrayList<>();
    private static final String FILE_PATH = "data/hoadon.csv";

    public void themHoaDon(HoaDon hd) {
        dsHoaDon.add(hd);
        saveToFile();
    }

    public List<HoaDon> layTatCa() {
        if (dsHoaDon.isEmpty()) loadFromFile();
        return dsHoaDon;
    }

    public int getNextId() {
        List<HoaDon> list = layTatCa(); // Lấy tất cả hóa đơn
        if (list.isEmpty()) return 1;

        int max = list.stream()
                .mapToInt(hd -> Integer.parseInt(hd.getMaHoaDon().substring(4))) // bỏ "MHD-"
                .max()
                .orElse(0);
        return max + 1;
    }

    public HoaDon timTheoMa(String ma) {
        return dsHoaDon.stream()
                .filter(hd -> hd.getMaHoaDon().equals(ma))
                .findFirst().orElse(null);
    }

    public void xoaHoaDon(String ma) {
        dsHoaDon.removeIf(hd -> hd.getMaHoaDon().equals(ma));
        saveToFile();
    }

    // ==== Đọc / Ghi file CSV ====
    private void loadFromFile() {
        List<String> lines = FileUtil.readLines(FILE_PATH);
        for (String line : lines) {
            String[] p = line.split(",");
            if (p.length < 6) continue;
            String maHd = p[0].trim();
            String maKh = p[1].trim();
            LocalDate ngay = LocalDate.parse(p[2].trim());
            double luong = Double.parseDouble(p[3]);
            double donGia = Double.parseDouble(p[4]);
            double thanhTien = Double.parseDouble(p[5]);
            HoaDon hd = new HoaDon(maHd, maKh, ngay, luong, 0, donGia);
            hd.setThanhTien(thanhTien);
            dsHoaDon.add(hd);
        }
    }

    private void saveToFile() {
        List<String> lines = new ArrayList<>();
        for (HoaDon hd : dsHoaDon) {
            lines.add(hd.getMaHoaDon() + "," + hd.getMaKhachHang() + "," +
                    hd.getNgayRaHoaDon() + "," + hd.getLuongTieuThu() + "," +
                    hd.getDonGia() + "," + hd.getThanhTien());
        }
        FileUtil.writeLines(FILE_PATH, lines);
    }
}
