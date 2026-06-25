package Seminar8.Ush3Console;

import java.util.ArrayList;

public class Prona {
    private String emri;
    private ArrayList<Klient> klientet;

    public Prona(String emri) {
        this.emri = emri;
        this.klientet = new ArrayList<>();
    }

    public String getEmri() {
        return emri;
    }

    public void shtoKlient(Klient klient) {
        klientet.add(klient);
    }

    public int numeroKliente() {
        return klientet.size();
    }

    public Klient ofertaMaks() {
        if (klientet.isEmpty()) {
            return null;
        }
        Klient maxKlient = klientet.get(0);
        for (int i = 1; i < klientet.size(); i++) {
            if (klientet.get(i).getOferta() > maxKlient.getOferta()) {
                maxKlient = klientet.get(i);
            }
        }
        return maxKlient;
    }

    @Override
    public String toString() {
        return "Prona: " + emri + ", Klientet: " + numeroKliente();
    }
}
