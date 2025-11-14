package main;


import controller.BillController;
import repository.CustomerRepository;
import view.BilllView;

public class ElectricityBillApp {
    public static void main(String[] args) {
        CustomerRepository khRepo = new CustomerRepository();
        HoaDonRepository hdRepo = new HoaDonRepository();
        BillView view = new BillView();
        BillController controller = new BillController(khRepo, hdRepo, view);
        controller.run();
    }
}
