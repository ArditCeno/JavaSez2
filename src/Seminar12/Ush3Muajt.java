package Seminar12;
import java.util.LinkedList;
import java.util.Collections;

public class Ush3Muajt {
    public static void main(String[] args) {

        LinkedList<String> muajt = new LinkedList<>();

        muajt.add("Janar");
        muajt.add("Shkurt");
        muajt.add("Mars");
        muajt.add("Prill");
        muajt.add("Maj");
        muajt.add("Qershor");
        muajt.add("Korrik");
        muajt.add("Gusht");
        muajt.add("Shtator");
        muajt.add("Tetor");
        muajt.add("Nëntor");
        muajt.add("Dhjetor");

        System.out.println("Muajt e vitit:");
        for (String muaji : muajt) {
            System.out.print(muaji+ " ");
        }

        System.out.println();

        Collections.sort(muajt, Collections.reverseOrder());

        System.out.println("Muajt e vitit te renditur nga shkronja e fundit tek e para: ");
        for (String muaji : muajt) {
            System.out.print(muaji+ " ");
        }
        System.out.println();

        LinkedList<String> muaj = new LinkedList<>();
        muaj.addLast("Dhjetor");
        muaj.addLast("Nentor");
        muaj.addLast("Tetor");
        muaj.addLast("Shtator");
        muaj.addLast("Gusht");
        muaj.addLast("Korrik");
        muaj.addLast("Qershor");
        muaj.addLast("Maj");
        muaj.addLast("Prill");
        muaj.addLast("Mars");
        muaj.addLast("Shkurt");
        muaj.addLast("Janar");

        System.out.println("Renditur sprapsi");
        for (String muaji : muaj) {
            System.out.print(muaji+ " ");
        }
    }
}