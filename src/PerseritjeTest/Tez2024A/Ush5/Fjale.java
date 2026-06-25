package PerseritjeTest.Tez2024A.Ush5;
/*Ndërtoni një aplikacion që lexon një skedar nga përdoruesi(nëpërmjet tastjerës).
Aplikacioni duhet të ekzaminojë çdo rresht duke kontrolluar për shpeshtësinë e fjalëve që ndodhen në atë rresht.
Nëse gjendet një fjalë që përsëritet më shumë se një herë të afishohet në ekran numri i rreshtit, fjala dhe numri i përsëritjes.
 */
import java.io.*;
import java.util.*;

public class Fjale {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);

        HashMap<String, Integer> map = new HashMap<>();
        while(sc.hasNext()){
            String fjala = sc.next().toLowerCase();

            map.put(fjala, map.getOrDefault(fjala, 0) + 1);
        }
        for(String s : map.keySet()){
            if(map.get(s)>1){}
            System.out.println(s+" "+map.get(s));
        }
    }
}
