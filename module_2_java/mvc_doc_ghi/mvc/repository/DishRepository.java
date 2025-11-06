package mvc.repository;
import mvc.entity.Dish;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class DishRepository {

    public List<Dish> getDishes() {
        return readFile();
    }

    public void save(Dish dish) {
        List<Dish> dishes1 = new ArrayList<>();
        dishes1.add(dish);
        writeFile(dishes1, true);
    }

    private static List<Dish> readFile() {
        List<Dish> dishes = new ArrayList<>();
        File file = new File("src/mvc/data/dish.csv");

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            Dish dish;
            String[] data;
            while ((line = bufferedReader.readLine()) != null) {
                data = line.split(",");
                dish = new Dish(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), data[3]);
                dishes.add(dish);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File không tìm thấy");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file");
        }
        return dishes;
    }

    private static void writeFile(List<Dish> dishes, boolean append) {
        File file = new File("src/mvc/data/dish.csv");
//        try with resources
        try (FileWriter fileWriter = new FileWriter(file, append);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Dish dish : dishes) {
                bufferedWriter.write(convertDishToString(dish));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi file");
        }
    }

    private static String convertDishToString(Dish dish) {
        return dish.getId()+","+dish.getName()+","+dish.getPrice()+","+dish.getDescription();
    }
}
