package util;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileUtil {

    // Ghi danh sách dòng vào file CSV
    public static void writeLines(String filePath, List<String> lines) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    // Đọc toàn bộ dòng từ file CSV
    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null && !line.isBlank()) {
                lines.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Không thể đọc file: " + filePath);
        }
        return lines;
    }
}
