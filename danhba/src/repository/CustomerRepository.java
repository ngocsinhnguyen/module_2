package repository;


import entity.Customer;
import util.FileUtil;


import java.util.*;

public class CustomerRepository {
    private List<Customer> dsKhachHang = new ArrayList<>();
    private static final String FILE_PATH = "contacts.csv";
    private Map<String, String> mapLoaiKhach = new HashMap<>();

}
