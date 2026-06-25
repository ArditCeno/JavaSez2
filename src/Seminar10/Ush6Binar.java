package Seminar10;

import java.util.Scanner;

public class Ush6Binar {
    public static String neBinar(int n) {
        if (n == 0) {
            return "0";
        }
        if (n == 1) {
            return "1";
        }
        return neBinar(n / 2) + (n % 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Jepni numrin dhjetor: ");
        int n = sc.nextInt();

        String binar = neBinar(n);
        System.out.println(n + " në binar: " + binar);
    }
}
