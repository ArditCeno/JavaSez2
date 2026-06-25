package Lab1;

import java.time.LocalDate;
import java.util.ArrayList;

public class Makina {

    private String marka;
    private int vitiProdhimit;
    private ArrayList<String> sherbimet;

    public Makina() {
        this.marka = "";
        this.vitiProdhimit = 0;
        this.sherbimet = new ArrayList<>();
    }

    public Makina(String marka, int vitiProdhimit) {
        this.marka = marka;
        this.vitiProdhimit = vitiProdhimit;
        this.sherbimet = new ArrayList<>();
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public int getVitiProdhimit() {
        return vitiProdhimit;
    }

    public void setVitiProdhimit(int vitiProdhimit) {
        this.vitiProdhimit = vitiProdhimit;
    }

    public ArrayList<String> getSherbimet() {
        return sherbimet;
    }

    public void setSherbimet(ArrayList<String> sherbimet) {
        this.sherbimet = sherbimet;
    }

    public int saVjec() {
        return LocalDate.now().getYear() - vitiProdhimit;
    }

    public void shtoSherbim(String sherbim) {
        sherbimet.add(sherbim);
    }

}