package Seminar12;

import java.util.LinkedList;

public class Ush4Qytetet {
    public static void main(String[] args) {

        LinkedList<String> qytetet = new LinkedList<>();
        qytetet.add("Tirana");
        qytetet.add("Durresi");
        qytetet.add("Vlora");
        qytetet.add("Shkodra");
        qytetet.add("Berati");
        qytetet.add("Skrapari");
        qytetet.add(":Lushnje");
        qytetet.add("Bajram Curri");
        qytetet.add("Fier");
        qytetet.add("Devoll");
        System.out.println("Lista fillestare: " + qytetet);

        //a
        String qytetiRiA = "Elbasani";

        qytetet.add(3, qytetiRiA);
        System.out.println("a. Pas elementit te tret (" + qytetiRiA + "): " + qytetet);

        //b
        String qytetiRiB = "Korca";
        int gjatesiaEkuivalente = qytetiRiB.length();
        int indeksiShtimit = -1;

        for (int i = 0; i < qytetet.size(); i++) {
            if (qytetet.get(i).length() == gjatesiaEkuivalente) {

                indeksiShtimit = i + 1;
                break;
            }
        }

        if (indeksiShtimit != -1) {
            qytetet.add(indeksiShtimit, qytetiRiB);
        } else {
            qytetet.add(qytetiRiB);
        }

        System.out.println("b. Pas qytetit të pare me gjatësi " + gjatesiaEkuivalente + " (" + qytetiRiB + "): " + qytetet);
    }
}