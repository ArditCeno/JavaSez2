package Seminar12;
import java.util.HashMap;
import java.util.Map;

public class Ush5Map {
    public static void main(String[] args) {

        Map<Integer, String> kodetPostare = new HashMap<>();

        kodetPostare.put(1001, "Tirana 5");
        kodetPostare.put(1002, "Tirana 26");
        kodetPostare.put(1003, "Tirana 13");
        kodetPostare.put(1004, "Tirana 18");

        System.out.println(" Lidhja Kodi Postar - Zona");
        for (Map.Entry<Integer, String> entry : kodetPostare.entrySet()) {
            System.out.println("Kodi: " + entry.getKey() + " -> Zona: " + entry.getValue());
        }

        int kodiPerKerkim = 1003;
        String zona = kodetPostare.get(kodiPerKerkim);
        System.out.println("Zona për kodin " + kodiPerKerkim + " eshte: " + zona);
    }
}