package Seminar8.Ush3Console;

public class Main {
    public static void main(String[] args) {
        Prona prona = new Prona("Vila ne Tirane");

        prona.shtoKlient(new Klient("Arben", 50000));
        prona.shtoKlient(new Klient("Drita", 75000));
        prona.shtoKlient(new Klient("Endri", 60000));

        System.out.println(prona);
        System.out.println("Numri i klienteve: " + prona.numeroKliente());
        System.out.println("Oferta me e larte: " + prona.ofertaMaks());
    }
}
