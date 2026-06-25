package Seminar12;

import java.util.HashMap;
import java.util.Scanner;

class Liber {
    private String isbn;
    private String titulli;
    private String autori;

    public Liber(String isbn, String titulli, String autori) {
        this.isbn = isbn;
        this.titulli = titulli;
        this.autori = autori;
    }

    public String getIsbn() { return isbn; }
    public String getTitulli() { return titulli; }
    public String getAutori() { return autori; }

    @Override
    public String toString() {
        return "Titulli: \"" + titulli + "\", Autori: " + autori + " (ISBN: " + isbn + ")";
    }
}

public class Ush6Liber {
    public static void main(String[] args) {

        HashMap<String, Liber> librari = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEMI I MENAXHIMIT TE LIBRARISE ===");

        while (true) {
            System.out.println("\nZgjidhni nje opsion:");
            System.out.println("1. Shto nje liber te ri");
            System.out.println("2. Kerko nje liber me ISBN");
            System.out.println("3. Dil nga programi");
            System.out.print("Zgjedhja juaj: ");

            int zgjedhja = scanner.nextInt();
            scanner.nextLine(); // Konsumon rreshtin e mbetur

            if (zgjedhja == 1) {

                System.out.print("Shkruani ISBN-ne e librit: ");
                String isbn = scanner.nextLine();

                if (librari.containsKey(isbn)) {
                    System.out.println("Gabim: Nje liber me kete ISBN ekziston ne librari!");
                    continue;
                }

                System.out.print("Shkruani titullin e librit: ");
                String titulli = scanner.nextLine();
                System.out.print("Shkruani autorin e librit: ");
                String autori = scanner.nextLine();

                Liber liberIRi = new Liber(isbn, titulli, autori);
                librari.put(isbn, liberIRi);
                System.out.println("Libri u shtua me sukses ne librari!");

            } else if (zgjedhja == 2) {

                System.out.print("Shkruani ISBN-ne e librit qe kerkoni: ");
                String isbnPerKerkim = scanner.nextLine();

                Liber libriGjetur = librari.get(isbnPerKerkim);

                if (libriGjetur != null) {
                    System.out.println("\n[Libri u gjet]: " + libriGjetur);
                } else {
                    System.out.println("\n[Njoftim]: Nuk u gjet asnje liber me kete ISBN.");
                }

            } else if (zgjedhja == 3) {

                System.out.println("Ju faleminderit qe perdoret librarine tone. Mirupafshim!");
                break;
            } else {
                System.out.println("Opsion i pavlefshem! Ju lutem provoni perseri.");
            }
        }

        scanner.close();
    }
}