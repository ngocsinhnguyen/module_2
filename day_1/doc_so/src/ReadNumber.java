import java.util.Scanner;

public class ReadNumber {

    public static String readOnes(int n) {
        return switch (n) {
            case 0 -> "zero";
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            case 4 -> "four";
            case 5 -> "five";
            case 6 -> "six";
            case 7 -> "seven";
            case 8 -> "eight";
            case 9 -> "nine";
            default -> "";
        };
    }


    public static String readTeens(int n) {
        return switch (n) {
            case 10 -> "ten";
            case 11 -> "eleven";
            case 12 -> "twelve";
            case 13 -> "thirteen";
            case 14 -> "fourteen";
            case 15 -> "fifteen";
            case 16 -> "sixteen";
            case 17 -> "seventeen";
            case 18 -> "eighteen";
            case 19 -> "nineteen";
            default -> "";
        };
    }


    public static String readTens(int n) {
        return switch (n) {
            case 2 -> "twenty";
            case 3 -> "thirty";
            case 4 -> "forty";
            case 5 -> "fifty";
            case 6 -> "sixty";
            case 7 -> "seventy";
            case 8 -> "eighty";
            case 9 -> "ninety";
            default -> "";
        };
    }


    public static String readTwoDigits(int n) {
        if (n < 10) {
            return readOnes(n);
        } else if (n < 20) {
            return readTeens(n);
        } else {
            int tens = n / 10;
            int ones = n % 10;
            if (ones == 0) {
                return readTens(tens);
            } else {
                return readTens(tens) + " " + readOnes(ones);
            }
        }
    }


    public static String readNumber(int num) {
        if (num < 0 || num > 999) {
            return "out of ability";
        } else if (num < 100) {
            return readTwoDigits(num);
        } else {
            int hundreds = num / 100;
            int remainder = num % 100;
            String result = readOnes(hundreds) + " hundred";
            if (remainder != 0) {
                result += " and " + readTwoDigits(remainder);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số cần đọc (0 - 999): ");
        int number = sc.nextInt();

        String result = readNumber(number);
        System.out.println("Kết quả: " + result);
    }
}
