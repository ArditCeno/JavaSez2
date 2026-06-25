package PerseritjeTest.Tez2024A.Ush3;
/*Ndërtoni klasën testuese BibliotekëTest e cila do të shtojë librat në bibliotekë
nga përdoruesi derisa përdoruesi të vendosë numrin -1.
 */
import java.util.*;

public class BiblotekTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Biblotek b = new Biblotek("ABC", "Tirane");
        while(true){
            System.out.println("Titulli");
            String t=sc.nextLine();

            if(t.equals("-1")){
                break;
            }
            System.out.println("Autori");
            String a=sc.nextLine();

            b.shtoLiber(new Liber(t, a));
        }
        System.out.println(b.numriLibrash());
    }
}
