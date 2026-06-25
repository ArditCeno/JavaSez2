package Lab1;
import java.util.Scanner;

public class afishoStinet {

    public static String afishoStinet(int n) {
        switch (n) {
            case 1:
                return "Pranvere";
            case 2:
                return "Vere";
            case 3:
                return "Vjeshte";
            case 4:
                return "Dimer";
            default:
                return "Midis 1-4 nese do te jap dicka";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Jep nr ");
        int numri = sc.nextInt();

        String rezultati = afishoStinet(numri);

        System.out.println("Stina " + rezultati);

        sc.close();
    }
}