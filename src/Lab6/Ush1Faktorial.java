package Lab6;
import java.util.Scanner;

public class Ush1Faktorial {

    public static long llogaritFaktorialin(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }

        return n * llogaritFaktorialin(n - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Shkruani nje numer te plote pozitiv");

        if (scanner.hasNextInt()) {
            int numri = scanner.nextInt();

            if (numri < 0) {
                System.out.println("Gabim");
            } else {

                long rezultati = llogaritFaktorialin(numri);
                System.out.println("Faktoriali i " + numri + " eshte: " + rezultati);
            }
        } else {
            System.out.println("Gabim");
        }

        scanner.close();
    }
}