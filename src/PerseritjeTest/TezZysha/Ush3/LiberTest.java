package PerseritjeTest.TezZysha.Ush3;

import java.util.Scanner;
import java.util.ArrayList;

public class LiberTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Liber> liber = new ArrayList<>();

        while (true){
            System.out.println("Fut titullin e librit");
            String titullin = input.nextLine();

            if (titullin.equalsIgnoreCase("mbyll")){
                break;
            }
            System.out.print("Futni autorin e librit");
            String autorin = input.nextLine();

            Liber l = new Liber(titullin,autorin);
            liber.add(l);

            System.out.print("U krijua"+ l);
        }
        if(!liber.isEmpty()){
            Liber iPare = liber.get(0);
            Liber iFundit = liber.get(liber.size()-1);

            System.out.println("--  Rezultatet  --");

            if(iPare.equals(iFundit)){
                System.out.println("Libri 1 dhe i fundit jane njesoj");
            }else{
                System.out.println("Libri i pare dhe i fundit jane ndrsyhe");
            }

            System.out.println("Incialet e autorit dhe librit te fundit" + iFundit.getAutori()+", " + iFundit.getInicialet());
        }else{
            System.out.println("Nuk i rregj asnje liber");
        }
        input.close();
    }
}
