package Seminar10;

import java.util.Scanner;

public class Ush5Pmp {
    public static int gjejPmp(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gjejPmp(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Jepni numrin e pare ");
        int a = sc.nextInt();
        System.out.print("Jepni numrin e dyte ");
        int b = sc.nextInt();

        int pmp = gjejPmp(a, b);
        System.out.println("PMP e " + a + " dhe " + b + " eshte: " + pmp);
    }
}
