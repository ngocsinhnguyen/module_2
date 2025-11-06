package mvc.view;

import mvc.common.exception.IdDuplicatedException;
import mvc.common.validate.Validate;
import mvc.controller.MenuBufferController;
import mvc.entity.Dish;

import java.util.List;
import java.util.Scanner;

public class MenuBuffer {

    private static MenuBufferController menuBufferController = new MenuBufferController();

    public static void menuBuffer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Danh sách món");
        System.out.println("2. Đặt món");
        System.out.println("3. Thêm món vào menu");
        int choice = Validate.inputInteger("lựa chọn");
        switch (choice) {
            case 1:
                List<Dish> dishes = menuBufferController.getMenuBuffer();
                display(dishes);
                break;
            case 2:
                break;
            case 3:
                Dish dish = inputDish();
                menuBufferController.add(dish);
                System.out.println("Thêm mới thành công");
                break;
            case 4:

                System.out.println("Nhập y hoạc n");
                String isYes = scanner.nextLine();
                if (isYes.equalsIgnoreCase("y")) {

                } else {

                }
        }
    }

    public static void display(List<Dish> dishes) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s %-20s %-10s %-30s\n", "ID", "Name", "Price", "Description"));
        sb.append("---------------------------------------------------------------------\n");
        for (Dish dish : dishes) {
            sb.append(String.format("%-5d %-20s %-10d %-30s\n",
                    dish.getId(),
                    dish.getName(),
                    dish.getPrice(),
                    dish.getDescription()));
        }
        System.out.println(sb);
    }

    public static Dish inputDish() {
        Scanner scanner = new Scanner(System.in);
        int id;
        while (true) {
            try {
                id = Validate.inputInteger("Id");
                menuBufferController.checkDuplicateId(id);
                break;
            } catch (IdDuplicatedException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("Nhập tên món: ");
        String name = scanner.nextLine();
        int price = Validate.inputInteger("Price");
        System.out.print("Nhập mô tả của món:");
        String description = scanner.nextLine();
        return new Dish(id, name, price, description);
    }
}
