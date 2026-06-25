package Seminar1;
import java.util.ArrayList;

public class Ush2Bibloteke {
    private ArrayList<Ush2Liber> librat;

    public Ush2Bibloteke() {
        librat = new ArrayList<>();
    }

    public void shtoLiber(Ush2Liber l) {
        librat.add(l);
    }

    public void afishoLibrat5VitetEFundit() {
        for (Ush2Liber l : librat) {
            if (l.botimIRi()) {
                System.out.println(l);
            }
        }
    }

    public int numriTotal() {
        return librat.size();
    }

    public Ush2Liber kerkoSipasISBN(String isbn) {
        for (Ush2Liber l : librat) {
            if (l.getIsbn().equals(isbn)) {
                return l;
            }
        }
        return null;
    }
}