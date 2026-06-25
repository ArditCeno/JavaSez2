package PerseritjeTest.Tez2025B.Ush3;

import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AgjensiTransporti agjencia = new AgjensiTransporti();

        System.out.print("Sa busa doni të regjistroni? ");
        int numriAutobuseve = sc.nextInt();
        sc.nextLine(); // KORRIGJIM 1: Konsumon \n pas nextInt() që cikli të mos kapërcejë ID-në e parë

        for (int i = 1; i <= numriAutobuseve; i++) {
            System.out.println("\n--- Autobusi " + i + " ---");

            System.out.print("ID e mjetit: ");
            String id = sc.nextLine();

            System.out.print("Prodhuesi: ");
            String prodhuesi = sc.nextLine();

            System.out.print("Kapaciteti i pasagjereve: ");
            int kapaciteti = sc.nextInt();
            sc.nextLine(); // Ky është vendosur saktë nga ty!

            System.out.print("Numri i Itinerarit: ");
            String itinerari = sc.nextLine();

            // KORRIGJIM 2: Ndryshuar emri i variablit nga 'autobus i ri' në 'autobusIRi'
            Autobus autobusIRi = new Autobus(id, prodhuesi, kapaciteti, itinerari);

            // KORRIGJIM 3: Përshtatur emri i metodës (shtoMjet në vend të shtoMjetet)
            agjencia.shtoMjetet(autobusIRi);
        }

        System.out.println("\nNumri total i mjeteve të regjistruara: " + agjencia.numriMjeteve());

        agjencia.afishoMjetetAktive();

        System.out.print("\nShkruani ID e mjetit që kerkoni: ");
        String idKerkimi = sc.nextLine();

        MjetTransporti iGjetur = agjencia.kerkoSipasId(idKerkimi);
        if (iGjetur != null) {
            System.out.println("U gjet: " + iGjetur);
        } else {
            System.out.println("Mjeti me ID '" + idKerkimi + "' nuk u gjet!");
        }

        sc.close();
    }
}