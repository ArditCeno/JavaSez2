package Lab2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Shkruaj emrin: ");
        String emri = scanner.nextLine();
        
        System.out.print("Shkruaj mbiemrin: ");
        String mbiemri = scanner.nextLine();
        
        scanner.close();
        
        new Ush1Emri(emri, mbiemri);
    }
}
