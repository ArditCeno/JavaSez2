package PerseritjeTest.Tez2024B.Ush2;
/*Ndërtoni metodën capitalizeWords që merr si parametër një string fjali dhe kthen një
string të ri ku çdo fjalë fillon me një shkronjë të madhe.
Për shembull, metoda që merr si parametër string-un "hello world" do të kthejë "Hello World".
 */

public class ShkronjeMadhe {
    static String capitalizeWords(String fjalia){
        String  [] fjalet = fjalia.split(" ");
        String rezultat = " ";

        for (String s:fjalet){
            rezultat += Character.toUpperCase(s.charAt(0)) + s.substring(1) + " ";
        }
        return rezultat.trim();
    }
    public static void main(String[] args) {
        System.out.print(capitalizeWords("hello world"));
    }
}
