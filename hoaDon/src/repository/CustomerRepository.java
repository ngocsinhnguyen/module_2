package repository;


import entity.Customer;
import entity.CustomerNN;
import entity.CustomerVN;
import util.FileUtil;


import java.util.*;

public class CustomerRepository {
    private List<Customer> dsKhachHang = new ArrayList<>();
    private static final String FILE_PATH = "data/khachhang.csv";
    private static final String FILE_LOAI_KHACH = "data/loaiKhach.csv";
    private Map<String, String> mapLoaiKhach = new HashMap<>();

    public CustomerRepository() {
        loadLoaiKhach();
        loadFromFile();
    }

    // Đọc file loaiKhach.csv
    private void loadLoaiKhach() {
        List<String> lines = FileUtil.readLines(FILE_LOAI_KHACH);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 2) mapLoaiKhach.put(parts[0].trim(), parts[1].trim());
        }
    }

    public String getTenLoaiKhach(String maLoai) {
        return mapLoaiKhach.getOrDefault(maLoai, "Không rõ");
    }

    public void themKhachHang(Customer kh) {
        dsKhachHang.add(kh);
        saveToFile();
    }

    public List<Customer> layTatCa() { return dsKhachHang; }

    public Customer timTheoMa(String ma) {
        return dsKhachHang.stream()
                .filter(kh -> kh.getMaKhachHang().equals(ma))
                .findFirst().orElse(null);
    }

    public List<Customer> timTheoTen(String ten) {
        List<Customer> kq = new ArrayList<>();
        for (Customer kh : dsKhachHang) {
            if (kh.getHoTen().toLowerCase().contains(ten.toLowerCase())) kq.add(kh);
        }
        return kq;
    }

    public int getNextIdByLoai(String prefix) {
        // Lấy tất cả KH có mã bắt đầu bằng prefix
        List<Customer> list = layTatCa().stream()
                .filter(kh -> kh.getMaKhachHang().startsWith(prefix))
                .toList();

        if (list.isEmpty()) return 1;

        // Lấy số lớn nhất hiện có
        int max = list.stream()
                .mapToInt(kh -> Integer.parseInt(kh.getMaKhachHang().substring(prefix.length() + 1)))
                .max().orElse(0);

        return max + 1;
    }

    // Đọc danh sách khách hàng
    private void loadFromFile() {
        List<String> lines = FileUtil.readLines(FILE_PATH);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length < 3) continue;

            String ma = parts[0].trim();
            String ten = parts[1].trim();

            if (ma.startsWith("KHVN")) {
                String loaiMa = parts[2].trim(); // Lưu mã LKH-xxx
                double dinhMuc = Double.parseDouble(parts[3]);
                dsKhachHang.add(new CustomerVN(ma, ten, loaiMa, dinhMuc));
            } else if (ma.startsWith("KHNN")) {
                String quocTich = parts[2].trim();
                dsKhachHang.add(new CustomerNN(ma, ten, quocTich));
            }
        }
    }

    private void saveToFile() {
        List<String> lines = new ArrayList<>();
        for (Customer kh : dsKhachHang) {
            if (kh instanceof CustomerVN vn)
                lines.add(vn.getMaKhachHang() + "," + vn.getHoTen() + "," + vn.getLoaiKhach() + "," + vn.getDinhMuc());
            else if (kh instanceof CustomerNN nn)
                lines.add(nn.getMaKhachHang() + "," + nn.getHoTen() + "," + nn.getQuocTich());
        }
        FileUtil.writeLines(FILE_PATH, lines);
    }

    public Map<String, String> getLoaiKhachMap() { return mapLoaiKhach; }
}
