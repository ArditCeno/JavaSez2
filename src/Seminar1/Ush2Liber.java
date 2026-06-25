package Seminar1;
import java.time.Year;

public class Ush2Liber {
    private String isbn;
    private String titulli;
    private String autori;
    private int vitiBotimit;

    public Ush2Liber(String isbn, String titulli, String autori, int vitiBotimit) {
        this.isbn = isbn;
        this.titulli = titulli;
        this.autori = autori;
        this.vitiBotimit = vitiBotimit;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulli() {
        return titulli;
    }

    public void setTitulli(String titulli) {
        this.titulli = titulli;
    }

    public String getAutori() {
        return autori;
    }

    public void setAutori(String autori) {
        this.autori = autori;
    }

    public int getVitiBotimit() {
        return vitiBotimit;
    }

    public void setVitiBotimit(int vitiBotimit) {
        this.vitiBotimit = vitiBotimit;
    }

    public boolean botimIRi() {
        int vitiAktual = Year.now().getValue();
        return vitiBotimit >= (vitiAktual - 4);
    }

    @Override
    public String toString() {
        return isbn + ", " + titulli + ", " + autori + ", " + vitiBotimit;
    }
}