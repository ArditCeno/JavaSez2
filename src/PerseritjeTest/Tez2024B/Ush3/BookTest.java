package PerseritjeTest.Tez2024B.Ush3;
/*Ndërtoni klasën testuese BookTest e cila do të shtojë vlerësime
për një libër nga përdoruesi derisa përdoruesi të vendosë numrin -1.*/
import java.util.*;

public class BookTest {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);

        Book b = new Book ("Java", "Ardit Ceno", "4444");

        while(true){
            System.out.println("Vleresimi ; ");
            int nr = sc.nextInt();

            if(nr==-1)
                break;
            b.addRating(nr);
        }
        System.out.println("Mesatarja= "+ b.getAverageRating());
    }
}
