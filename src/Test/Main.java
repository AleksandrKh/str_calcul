package Test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите выражение [\"a\" + \"b\", \"a\" - \"b\", \"a\" * x, \"a\" / x] где a и b - строки, длиной не более 10 символов, а x - число  от 1 до 10 включительно  + Enter");
        String line = in.nextLine();

        String[] str = line.split("\"");
        String result = "";

        if (line.startsWith("\"")) {

            if (str.length == 4) {
                String str2 = str[1];
                String str3 = str[2];
                String str4 = str[3];

                String operation = str3.trim();
                char ch = operation.charAt(0);

                if (str2.length() <= 10 && str4.length() <= 10 && line.endsWith("\"")) {
                    result = calculated(str2, str4, ch);
                } else {
                    System.out.println("Не удовлетворяет условию задачи");
                }

                System.out.println("\"" + result + "\"");
            }
            else if (str.length == 3) {
                String str2 = str[1];
                String str3 = str[2];

                String operation = str3.trim();
                char ch = operation.charAt(0);
                String sNumber = operation.substring(1);
                String number = sNumber.trim();
                int num = Integer.parseInt(number);

                if (str2.length() <= 10 && num <= 10) {
                    String res = calculated(str2, num, ch);
                    if (res.length() > 40) {
                        result = res.substring(0, 40) + "...";
                    } else {
                        result = res;
                    }
                    System.out.println("\"" + result + "\"");
                } else {
                    System.out.println("Строки не должны быть длиной больше 10 символов, а число от 1 до 10 включительно");
                }

            } else {
                System.out.println("Не удовлетворяет условию задачи");
            }

        } else {
            System.out.println("Первым аргументом выражения, подаваемого на вход, должна быть строка");
        }
    }

    public static String calculated(String str1, String str2, char op) {
        var result = "";
        switch (op) {
            case '+':
                result = str1 + str2;
                break;
            case '-':
                if (str1.contains(str2)) {
                    int start = str1.indexOf(str2);
                    int end = start + str2.length();
                    result += str1.substring(0, start);
                    result += str1.substring(end);
                } else {
                    result = str1;
                }
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }

        return result;
    }

    public static String calculated(String str1, int str2, char op) {
        var result = "";
        switch (op) {
            case '*':
                result = str1.repeat(str2);
                break;
            case '/':
                try {
                    result = str1.substring(0, str1.length()/str2);
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Only integer non-zero parameters allowed");
                    break;
                } finally {
                    if (str1.length() < str2) {
                        System.out.println("Делимое меньше делителя");
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }

        return result;
    }
}