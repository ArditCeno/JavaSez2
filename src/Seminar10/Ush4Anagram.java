package Seminar10;

import java.util.ArrayList;
import java.util.List;

public class Ush4Anagram {
    public static List<String> gjejAnagramet(String str) {
        List<String> rezultatet = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return rezultatet;
        }
        permuto("", str, rezultatet);
        return rezultatet;
    }

    private static void permuto(String prefix, String mbetur, List<String> rezultatet) {
        if (mbetur.length() == 0) {
            rezultatet.add(prefix);
            return;
        }
        for (int i = 0; i < mbetur.length(); i++) {
            String shkronja = String.valueOf(mbetur.charAt(i));
            String para = mbetur.substring(0, i);
            String pas = mbetur.substring(i + 1);
            permuto(prefix + shkronja, para + pas, rezultatet);
        }
    }

    public static void main(String[] args) {
        String str = "abc";
        List<String> anagramet = gjejAnagramet(str);
        System.out.println("Anagramet e \"" + str + "\":");
        for (String anagram : anagramet) {
            System.out.println(anagram);
        }
    }
}
