package Seminar11;
import java.util.Scanner;
import java.lang.Comparable;

class Student implements Comparable<Student> {
    private String emri;

    public Student(String emri) {
        this.emri = emri;
    }

    public String getEmri() {
        return emri;
    }

    @Override
    public int compareTo(Student tjetri) {
        return this.emri.compareTo(tjetri.emri);
    }

    @Override
    public String toString() {
        return emri;
    }
}

public class Ush3Krahasim {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] studentet = new Student[5];

        for (int i = 0; i < 5; i++) {
            System.out.print("Shkr emrat " + (i + 1) + ": ");
            String emri = scanner.nextLine();
            studentet[i] = new Student(emri);
        }

        Student iPari = studentet[0];
        Student iFundit = studentet[0];

        for (int i = 1; i < studentet.length; i++) {
            if (studentet[i].compareTo(iPari) < 0) {
                iPari = studentet[i];
            }
            if (studentet[i].compareTo(iFundit) > 0) {
                iFundit = studentet[i];
            }
        }

        System.out.println("\nStudenti i pare: " + iPari);
        System.out.println("Studenti i fundit: " + iFundit);

        scanner.close();
    }
}
