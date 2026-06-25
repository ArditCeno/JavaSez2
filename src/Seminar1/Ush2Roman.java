package Seminar1;

public class Ush2Roman extends Ush2Liber {
    private String zhanri;

    public Ush2Roman(String isbn, String titulli, String autori, int vitiBotimit, String zhanri) {
        super(isbn, titulli, autori, vitiBotimit);
        this.zhanri = zhanri;
    }

    public String getZhanri() {
        return zhanri;
    }

    public void setZhanri(String zhanri) {
        this.zhanri = zhanri;
    }

    @Override
    public boolean botimIRi() {
        return getVitiBotimit() > 2020 && !zhanri.equalsIgnoreCase("historik");
    }

    @Override
    public String toString() {
        return super.toString() + ", " + zhanri;
    }
}