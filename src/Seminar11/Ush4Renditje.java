package Seminar11;
import java.util.Random;
import java.util.Scanner;

public class Ush4Renditje {
    private static final int NUMRI = 1000;
    private static String[] emrat;
    private static String[] numrat;

    public static void main(String[] args) {
        emrat = new String[NUMRI];
        numrat = new String[NUMRI];
        Random rnd = new Random();

        String[] emra = {"Arber", "Blerta", "Driton", "Edona", "Fisnik", "Gresa", "Hysen", "Ilir",
                "Jeta", "Krenar", "Liridona", "Mirjeta", "Naim", "Olta", "Petrit", "Qendrim",
                "Rina", "Shpend", "Teuta", "Ardit", "Valon", "Xhemile", "Zafer", "Ardian",
                "Besa", "Jana", "Dafina", "Ermal", "Fitim", "Gezim", "Haki", "Igballe",
                "Jashar", "Kaltrina", "Luan", "Majlinda", "Naser", "Osman", "Premtim"};

        String[] mbiemra = {"Ahmeti", "Berisha", "Caka", "Demiri", "Elshani", "Fazliu",
                "Gashi", "Haliti", "Ismaili", "Jashari", "Krasniqi", "Llapi", "Mustafa",
                "Nuli", "Osmani", "Peci", "Qorri", "Rexhepi", "Shabani", "Tahiri",
                "Ukshini", "Veseli", "Zogaj", "Latifi", "Morina", "Selimi", "Thaqi",
                "Vitaku", "Berani", "Hoxha"};

        for (int i = 0; i < NUMRI; i++) {
            String emri = emra[rnd.nextInt(emra.length)];
            String mbiemri = mbiemra[rnd.nextInt(mbiemra.length)];
            emrat[i] = emri + " " + mbiemri;
            String prefix = rnd.nextBoolean() ? "069" : "068";
            numrat[i] = String.format("%s-%03d-%03d",
                    prefix, rnd.nextInt(1000), rnd.nextInt(1000));
        }

        System.out.println("Para renditjes");
        afishoTeDhenat();

        renditSipasEmrit();

        System.out.println("\nPas renditjes");
        afishoTeDhenat();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Kerkim sipas emrit");
        System.out.print("Shkruani emrin per te kerkuar: ");
        String emriKerkim = scanner.nextLine();
        int indexEmri = kerkimSipasEmrit(emriKerkim);
        if (indexEmri >= 0) {
            System.out.println("U gjet: " + emrat[indexEmri] + " -> " + numrat[indexEmri]);
        } else {
            System.out.println("Emri nuk u gjet.");
        }

        System.out.println("\nKerkim sipas numrit te telefonit");
        System.out.print("Shkruani numrin per te kerkuar: ");
        String numriKerkim = scanner.nextLine();
        int indexNumri = kerkimSipasNumrit(numriKerkim);
        if (indexNumri >= 0) {
            System.out.println("U gjet: " + numrat[indexNumri] + " -> " + emrat[indexNumri]);
        } else {
            System.out.println("Numri nuk u gjet.");
        }

        scanner.close();
    }

    private static void afishoTeDhenat() {
        for (int i = 0; i < NUMRI; i++) {
            System.out.printf("%-25s -> %s%n", emrat[i], numrat[i]);
        }
    }

    private static void renditSipasEmrit() {
        for (int i = 0; i < NUMRI - 1; i++) {
            int minId = i;
            for (int j = i + 1; j < NUMRI; j++) {
                if (emrat[j].compareToIgnoreCase(emrat[minId]) < 0) {
                    minId = j;
                }
            }
            String tmpEmri = emrat[i];
            emrat[i] = emrat[minId];
            emrat[minId] = tmpEmri;

            String tmpNumri = numrat[i];
            numrat[i] = numrat[minId];
            numrat[minId] = tmpNumri;
        }
    }

    private static int kerkimSipasEmrit(String emri) {
        int majtas = 0, djathtas = NUMRI - 1;
        while (majtas <= djathtas) {
            int mesi = (majtas + djathtas) / 2;
            int krahasimi = emri.compareToIgnoreCase(emrat[mesi]);
            if (krahasimi == 0) return mesi;
            if (krahasimi < 0) djathtas = mesi - 1;
            else majtas = mesi + 1;
        }
        return -1;
    }

    private static int kerkimSipasNumrit(String numri) {
        for (int i = 0; i < NUMRI; i++) {
            if (numrat[i].equals(numri)) {
                return i;
            }
        }
        return -1;
    }
}
