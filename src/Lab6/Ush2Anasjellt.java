package Lab6;
import java.util.Scanner;

public class Ush2Anasjellt {

    public static String stringAnasjellte(String text) {
        if (text.isEmpty()) {
            return "";
        }
        return stringAnasjellte(text.substring(1)) + text.charAt(0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zgjidhje rekursive");
        System.out.print("Jepni fjalen: ");
        String input = sc.nextLine();
        System.out.println(stringAnasjellte(input));
    }
}