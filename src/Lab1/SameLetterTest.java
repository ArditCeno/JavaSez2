package Lab1;
import java.util.Scanner;

public class SameLetterTest {

    public static boolean sameLetter(String text) {
        if (text == null || text.length() == 0) {
            return false;
        }

        char first = Character.toLowerCase(text.charAt(0));
        char last = Character.toLowerCase(text.charAt(text.length() - 1));

        return first == last;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Shkruaj smth: ");
        String word = input.nextLine();

        boolean result = sameLetter(word);

        if (result) {
            System.out.print("Njesoj ");
        } else {
            System.out.print("Ndryshe ");
        }

        input.close();
    }
}