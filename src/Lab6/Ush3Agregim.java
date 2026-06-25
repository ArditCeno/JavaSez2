package Lab6;
public class Ush3Agregim {
    public static void main(String[] args) {

        Libri libri1 = new Libri("Kronike ne gur", "Ismail Kadare");
        Libri libri2 = new Libri("100 Vjet vetmi", "Gabriel Marques" );
        Libri libri3 = new Libri("Zonja Bovari", "Gustav Flober");
        Libri libri4 = new Libri("Devils", "Fyodor Mihallovic Dostoyevski");
        Libri libri5 = new Libri("Shenime nga shtepia e te vdekurve", "Fyodor Mihallovic Dostoyevski");


        Biblioteka biblioteka = new Biblioteka("Biblioteka Kombetare");
        biblioteka.shtoLiber(libri1);
        biblioteka.shtoLiber(libri2);
        biblioteka.shtoLiber(libri3);
        biblioteka.shtoLiber(libri4);
        biblioteka.shtoLiber(libri5);

        biblioteka.afishoLibrat();

        biblioteka = null;

        System.out.println("\n Pas largimit te bibliotekes");
        System.out.println("Librat vazhdojne te ekzistojne ne memorie ");
        System.out.println("- " + libri1);
        System.out.println("- " + libri2);
        System.out.println("- " + libri3);
        System.out.println("- " + libri4);
        System.out.println("- " + libri5);
    }
}
