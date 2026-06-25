package Seminar10;

import java.util.Scanner;

public class Ush2Interative {
    public static String stringAnasjellte(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            result.append(text.charAt(i));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zgjidhje Iterative");
        System.out.print("Jepni fjalen: ");
        String input = sc.nextLine();
        System.out.println(stringAnasjellte(input));
    }
}