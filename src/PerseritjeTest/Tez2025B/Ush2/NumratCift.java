package PerseritjeTest.Tez2025B.Ush2;

/*Nje numěr i plotě pozitiv thuhet se ka shifra çift vetëm nése tě gjitha shifrat e tij janë çift.
Pěr shembull 280, 246 janë numra me shifra çift, ndërsa 123, 789 nuk janě.
Ndërtoni njö metode statike e cila merr si parametra dy numra pozitivě m dhe n,
ku m<n dhe kthen numrin e numrave me shifra çift qě janě nễ intervalin ndërmjet n dhe m (ku n dhe m perfshihen).
Per shembull nëse n äshte 20 dhe m äshte 40, metoda kthen 6.
Numrat unikë në kétë rast jane 20,22,24,26 28,40.
 */
import java.util.Scanner;
public class NumratCift {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nr i pare: ");
        int n = sc.nextInt();

        System.out.print("Nr i dyte: ");
        int m = sc.nextInt();

        int rezultati  = gjejNumratMeShifraCift(n , m);
        System.out.print("Numri i nr me shifra cift [" +n + m +"] eshte " + rezultati + "\n" );

        sc.close();
    }
    public static int gjejNumratMeShifraCift(int n, int m) {
        int count = 0;

        for (int i = n; i <= m; i++) {
            if(kaVetemShifratCift(i)){
                count++;
            }
        }
        return count;
    }
    public static boolean kaVetemShifratCift(int n){
        if (n == 0){
            return true;
        }
        while (n>0){
            int shifra = n % 10;

            if (shifra % 2 != 0){
                return false;
            }
            n = n/10;
        }
        return true;
    }
}
