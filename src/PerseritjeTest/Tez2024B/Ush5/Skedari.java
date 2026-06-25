package PerseritjeTest.Tez2024B.Ush5;
/*Ndërtoni një aplikacion që lexon të dhëna nga dy skedarë teksti.
Në secilin skedar ruhen vargje të tekstit (një varg për rresht).
Vargjet në secilin skedar janë të parenditura.
Krijoni një skedar output që përmban vargjet e të dy skedarëve të renditur në rend alfabetik.
 */

import java.io.*;
import java.util.*;

public class Skedari {

    public static void main(String[] args) throws Exception {
        ArrayList<String> lista = new ArrayList<>();

        Scanner s1 = new Scanner(new File("file1.txt"));
        Scanner s2 = new Scanner(new File("file2.txt"));

        while (s1.hasNextLine())
            lista.add(s1.nextLine());

        while (s2.hasNextLine())
            lista.add(s2.nextLine());

        Collections.sort(lista);

        PrintWriter pw = new PrintWriter("output.txt");

        for (String s : lista)
            pw.println(s);

        pw.close();
    }
}
