package Seminar13;

import java.io.*;
import java.util.Scanner;

public class Ush1Produkt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Vendosni numrin e produkteve: ");
        int numriProdukteve = scanner.nextInt();
        scanner.nextLine();

        String[] emrat = new String[numriProdukteve];
        String[] kodet = new String[numriProdukteve];
        int[] sasite = new int[numriProdukteve];
        double[] cmimet = new double[numriProdukteve];

        for (int i = 0; i < numriProdukteve; i++) {
            System.out.println("\nProdukti " + (i + 1) + ":");
            System.out.print("Emri: ");
            emrat[i] = scanner.nextLine();
            System.out.print("Kodi: ");
            kodet[i] = scanner.nextLine();
            System.out.print("Sasia: ");
            sasite[i] = scanner.nextInt();
            System.out.print("Cmimi: ");
            cmimet[i] = scanner.nextDouble();
            scanner.nextLine();
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("produktet.bin"))) {
            dos.writeInt(numriProdukteve);
            for (int i = 0; i < numriProdukteve; i++) {
                dos.writeUTF(emrat[i]);
                dos.writeUTF(kodet[i]);
                dos.writeInt(sasite[i]);
                dos.writeDouble(cmimet[i]);
            }
            System.out.println("\nTe dhenat u shkruan me sukses ne skedarin produktet.bin");
        } catch (IOException e) {
            System.out.println("Gabim gjate shkrimit ne skedar: " + e.getMessage());
        }

        scanner.close();
    }
}
