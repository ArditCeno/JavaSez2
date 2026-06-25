package Seminar9.Ush1;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Emri i punonjesit: ");
        String emri = sc.nextLine();

        System.out.print("ID e punonjesit: ");
        String id = sc.nextLine();

        System.out.print("Data e punesimit : ");
        LocalDate data = LocalDate.parse(sc.nextLine());

        System.out.print("Departamenti: ");
        String dep = sc.nextLine();

        System.out.print("Paga: ");
        double paga = sc.nextDouble();
        sc.nextLine();

        System.out.print("Rruga: ");
        String rruga = sc.nextLine();

        System.out.print("Qyteti: ");
        String qyteti = sc.nextLine();

        Adresa adresa = new Adresa(rruga, qyteti);

        Punonjes punonjes = new Punonjes(emri, id, data, dep, paga, adresa);

        System.out.println("Te dhenat e punonjesit");
        System.out.println(punonjes);
    }
}
