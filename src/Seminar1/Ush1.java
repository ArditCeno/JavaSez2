package Seminar1;

import java.util.Scanner;

public class Ush1 {

//    public static boolean eshteTek(int n) {
//
//        n = Math.abs(n);
//        int poz = 1;
//
//        while (n > 0) {
//
//            int shifra = n % 10;
//
//            if (poz % 2 == 1 && shifra % 2 == 0)
//                return false;
//
//            n /= 10;
//            poz++;
//        }
//
//        return true;
//    }
    public static boolean eshteTek2(int n) {
        String numri = Integer.toString(n);

        for (int i = 0; i < numri.length(); i++) {
            if ((i + 1) % 2 == 1) {
                int shifra = numri.charAt(i) - '0';
                if (shifra % 2 == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Shkruani nje numer te plote:");
        int n = sc.nextInt();

//        if (eshteTek(n)) {
//            System.out.println("Shifrat ne pozicionet tek jane numra tek.");
        if (eshteTek2(n)) {
            System.out.println("Shifrat ne pozicionet tek jane numra tek.");
        } else {
            System.out.println("Shifrat ne pozicionet tek NUK jane numra tek.");
        }
    }
}