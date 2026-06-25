package Seminar1;
import java.util.Scanner;

public class Ush2Main {
    public static void main(String[] args) {
        Ush2Bibloteke b = new Ush2Bibloteke();
        Scanner sc = new Scanner(System.in);

        System.out.println("Sa libra doni te regjistroni?");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Zgjidh tipin (1-Liber, 2-Roman):");
            int tipi = sc.nextInt();
            sc.nextLine();

            System.out.println("Jep ISBN:");
            String isbn = sc.nextLine();

            System.out.println("Jep titullin:");
            String titulli = sc.nextLine();

            System.out.println("Jep autorin/atoret:");
            String autori = sc.nextLine();

            System.out.println("Jep vitin e botimit:");
            int viti = sc.nextInt();
            sc.nextLine();

            if (tipi == 2) {
                System.out.println("Jep zhanrin:");
                String zhanri = sc.nextLine();
                b.shtoLiber(new Ush2Roman(isbn, titulli, autori, viti, zhanri));
            } else {
                b.shtoLiber(new Ush2Liber(isbn, titulli, autori, viti));
            }
        }

        System.out.println("Librat e 5 viteve te fundit:");
        b.afishoLibrat5VitetEFundit();

        System.out.println("Numri total i librave: " + b.numriTotal());

        System.out.println("Kerko liber sipas ISBN:");
        String kerko = sc.nextLine();
        Ush2Liber gjetur = b.kerkoSipasISBN(kerko);

        if (gjetur != null) {
            System.out.println("U gjet: " + gjetur);
        } else {
            System.out.println("Nuk u gjet liber me kete ISBN.");
        }
    }
}