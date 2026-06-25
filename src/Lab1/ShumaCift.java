package Lab1;
import java.util.Scanner;

public class ShumaCift {

    public static int shumaShifraveCift(int n) {

        int shuma = 0;
        n = Math.abs(n);

        while (n > 0) {
            int shifra = n % 10;

            if (shifra % 2 == 0) {
                shuma += shifra;
            }
            n = n / 10;
        }

        return shuma;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Jep nje numer: ");
        int numri = scanner.nextInt();

        int rezultati = shumaShifraveCift(numri);

        System.out.println("Shuma " + rezultati);

        scanner.close();
    }
}