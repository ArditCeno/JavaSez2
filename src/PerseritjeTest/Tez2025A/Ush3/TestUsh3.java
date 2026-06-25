package PerseritjeTest.Tez2025A.Ush3;

import java.util.Scanner;

public class TestUsh3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DyqanElektronik dyqani = new DyqanElektronik();

        System.out.print("Sa pajisje doni te shtoni? ");
        int nr = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < nr; i++) {
            System.out.println("\nPajisja " + (i + 1) + ":");
            System.out.print("ID: ");
            String id = sc.nextLine();
            System.out.print("Marka: ");
            String marka = sc.nextLine();
            System.out.print("Garancia (ne muaj): ");
            int g = sc.nextInt();
            sc.nextLine();
            System.out.print("Modeli i procesorit: ");
            String proc = sc.nextLine();

            dyqani.shtoPajisje(new Laptop(id, marka, g, proc));
        }

        System.out.println("\n--- Pajisjet ne garanci ---");
        dyqani.afishoPajisjetNeGaranci();

        System.out.println("\nTotali i pajisjeve: " + dyqani.numriPajisjeve());

        System.out.print("\nKerko pajisje me ID: ");
        String kerko = sc.nextLine();
        PajisjeElektronike gjetur = dyqani.kerkoPajisje(kerko);
        if (gjetur != null)
            System.out.println("U gjet: " + gjetur);
        else
            System.out.println("Nuk u gjet asnje pajisje me ID: " + kerko);
    }
}
