package Lab1;

import java.util.ArrayList;

public class LlogariBankare {

    private double bilanc;
    private String numerLlogarie;
    private ArrayList<Double> historikuDepozitave;

    public LlogariBankare() {
        this.bilanc = 0.0;
        this.numerLlogarie = "";
        this.historikuDepozitave = new ArrayList<>();
    }

    public LlogariBankare(double bilanc, String numerLlogarie) {
        this.bilanc = bilanc;
        this.numerLlogarie = numerLlogarie;
        this.historikuDepozitave = new ArrayList<>();
    }

    public double getBilanc() {
        return bilanc;
    }

    public void setBilanc(double bilanc) {
        this.bilanc = bilanc;
    }

    public String getNumerLlogarie() {
        return numerLlogarie;
    }

    public void setNumerLlogarie(String numerLlogarie) {
        this.numerLlogarie = numerLlogarie;
    }

    public ArrayList<Double> getHistorikuDepozitave() {
        return historikuDepozitave;
    }

    public void setHistorikuDepozitave(ArrayList<Double> historikuDepozitave) {
        this.historikuDepozitave = historikuDepozitave;
    }

    public boolean eshteBilancPozitiv() {
        return bilanc > 0;
    }

    public void shtoDepozite(double shuma) {
        bilanc += shuma;
        historikuDepozitave.add(shuma);
    }
}