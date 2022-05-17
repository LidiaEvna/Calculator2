package com.company;

import java.io.*;
import java.util.regex.Pattern;
/**
 * класс RegExp для общения с пользователем и вывода результата в консоль
 * в классе RegExp проверяются исключения try/catch
 *
 * @author Журавлева Лидия, группа 21ит35
 */
public class Main{
    public static void main(String[] args) throws Exception {
        String regExp = "\\d+\\s[+,\\-, *, %, /, ^]\\s\\d+"; //класс для чтения регулярного выражения
        double result = 0;
        String inputString;
        try (BufferedReader fr = new BufferedReader(new FileReader("src/input.txt"));
             BufferedWriter ad = new BufferedWriter(new FileWriter("src/output.txt"))) {

            while ((inputString = fr.readLine()) != null) {
                if ((inputString.trim().matches(regExp))) {
                    result = split(inputString.split(" "), result);
                    ad.write(result + "\n");
                    System.out.println(result);
                } else {
                    ad.write("Были введены некоректные данные" + "\n");
                    System.out.println("Ввод не корректен!");
                }
            }
        }
    }
    /**
     * метод для работы с введенно пользователем строкой и поиска в нем нужных значений
     *
     * @param array массив для разрыва строки на значения, необходимый для длальнейшей работы с ними
     * @param previousResult переменная проверяющая предыдущий результат
     * @return возвращает значения для возможности их дальнейшего использования методе calculate
     * @throws Exception обрабатывает исключения
     */
    private static double split(String[] array, double previousResult) throws Exception {
        String operand;
        double number1;
        double number2;
        if (array.length == 3) {
            number1 = Double.parseDouble(array[0]);
            operand = array[1];
            number2 = Double.parseDouble(array[2]);
            return calculate(number1, number2, operand);
        } else {
            throw new Exception("Ввод не корректен");
        }
    }
    /**
     * метод для реализации базовых функций калькулятора
     *
     * @param number1, number2 значение 1 ,2
     * @param operand действие, которое должно быть применено к значению
     * @return возвращает результат действия
     * @throws Exception обрабатывает исключения
     */
    private static double calculate(double number1, double number2, String operand) throws Exception {
        switch (operand) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                return number1 / number2;
            case "^":
                return Math.pow(number1, number2);
            case "%":
                return number1 % number2;
            default:
                throw new Exception("Ввод не корректен");
        }
    }
}