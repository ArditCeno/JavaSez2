package PerseritjeTest.Tez2024A.Ush2;
/*Ndërtoni metodën reverseWords që merr si parametër një string "fjali"
dhe kthen një string të ri ku rendi i fjalëve është kthyer mbrapsht.
Për shembull, metoda që merr si parametër string-un "hello world" do të kthejë "world hello".
 */

public class KtheFjalet {
    static String reverseWords(String fjala){
        String[] f=fjala.split(" ");
        String rezultat = "";

        for (int i=f.length-1;i>=0; i--) {
            rezultat += f[i] + "";
        }

        return rezultat.trim();
    }
    public static void main(String[] args) {
        System.out.println(reverseWords("Hello World"));
    }
}
