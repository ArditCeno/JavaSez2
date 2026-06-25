package Seminar9.Ush2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Krijimi i Personit ===");
        System.out.print("Emri: ");
        String emriP = sc.nextLine();
        System.out.print("Mbiemri: ");
        String mbiemriP = sc.nextLine();
        System.out.print("ID: ");
        String idP = sc.nextLine();
        Person person = new Person(emriP, mbiemriP, idP);

        System.out.println("\n=== Krijimi i Punonjesit ===");
        System.out.print("Emri: ");
        String emriPun = sc.nextLine();
        System.out.print("Mbiemri: ");
        String mbiemriPun = sc.nextLine();
        System.out.print("ID: ");
        String idPun = sc.nextLine();
        System.out.print("Pozita: ");
        String pozita = sc.nextLine();
        System.out.print("Paga: ");
        double paga = Double.parseDouble(sc.nextLine());
        Punonjes punonjes = new Punonjes(emriPun, mbiemriPun, idPun, pozita, paga);

        System.out.println("\n=== Krijimi i Menaxherit ===");
        System.out.print("Emri: ");
        String emriM = sc.nextLine();
        System.out.print("Mbiemri: ");
        String mbiemriM = sc.nextLine();
        System.out.print("ID: ");
        String idM = sc.nextLine();
        System.out.print("Pozita: ");
        String pozitaM = sc.nextLine();
        System.out.print("Paga: ");
        double pagaM = Double.parseDouble(sc.nextLine());
        System.out.print("Numer punonjesish: ");
        int numer = Integer.parseInt(sc.nextLine());
        Menaxher menaxher = new Menaxher(emriM, mbiemriM, idM, pozitaM, pagaM, numer);

        System.out.println("\n========== REZULTATET ==========");
        System.out.println(person);
        System.out.println(punonjes);
        System.out.println(menaxher);

        System.out.println("\n=== Verifikime ===");
        System.out.println("punonjes instanceof Person: " + (punonjes instanceof Person));
        System.out.println("menaxher instanceof PerseritjeTest.Tez2024Vjesht.Ush3.Punonjes: " + (menaxher instanceof Punonjes));
        System.out.println("menaxher instanceof Person: " + (menaxher instanceof Person));

        sc.close();
    }
}
