public class ElectricityBillApp {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        // Thêm dữ liệu mẫu
        model.themKhachHang(new CustomerVN("KHVN-00001", "Nguyễn Công Phương"));
        model.themKhachHang(new CustomerVN("KHVN-00002", "Nguyễn Quang Hải"));
        model.themKhachHang(new CustomerNN("KHNN-00001", "Cristiano Ronaldo", "Portugal"));

        controller.chayUngDung();
    }
}