package Seminar10;
import java.util.Scanner;

public class Ush3Tekst {

    public static boolean permbanString(String text, String kerkimi) {
        if (text.length() < kerkimi.length()) {
            return false;
        }
        if (text.startsWith(kerkimi)) {
            return true;
        }
        return permbanString(text.substring(1), kerkimi);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Jepni tekstin ");
        String text = sc.nextLine();
        System.out.print("Jepni stringun per kerkim ");
        String kerkimi = sc.nextLine();

        if (permbanString(text, kerkimi)) {
            System.out.println("Teksti permban stringun.");
        } else {
            System.out.println("Teksti nuk permban stringun.");
        }
    }
}
