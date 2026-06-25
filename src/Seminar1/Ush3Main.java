package Seminar1;

public class Ush3Main {
    public static void main(String[] args) {

       Ush3Person p = new Ush3Person("Ardit");
        Ush3Student s1 = new Ush3Student("Ardit", "123");
        Ush3Student s2 = new Ush3Student("Ardit", "123");
        Ush3Punonjes pun = new Ush3Punonjes("Besa", 500.0);

        p.printo();
        s1.printo();
        pun.printo();

        System.out.println("Emra te njejte: " + p.emerNjejte(s1));
        System.out.println("Studente te njejte: " + s1.teNjejte(s2));
    }
}