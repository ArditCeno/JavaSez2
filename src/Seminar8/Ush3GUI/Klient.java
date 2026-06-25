package Seminar8.Ush3GUI;

public class Klient {
    private String emri;
    private double oferta;

    public Klient(String emri, double oferta) {
        this.emri = emri;
        this.oferta = oferta;
    }

    public String getEmri() {
        return emri;
    }

    public double getOferta() {
        return oferta;
    }

    @Override
    public String toString() {
        return emri + " (oferta: " + oferta + "€)";
    }
}
