package Lab6;
import java.util.ArrayList;
import java.util.List;

public class Biblioteka {
    private String emriBibliotekes;
    private List<Libri> listalibrave;

    public Biblioteka(String emriBibliotekes) {
        this.emriBibliotekes = emriBibliotekes;
        this.listalibrave = new ArrayList<>();
    }

    public void shtoLiber(Libri libri) {
        listalibrave.add(libri);
    }

    public void afishoLibrat() {
        System.out.println("\n Librat ne " + emriBibliotekes );
        if (listalibrave.isEmpty()) {
            System.out.println("Biblioteka nuk ka asnje liber aktualisht.");
        } else {
            for (Libri l : listalibrave) {
                System.out.println("- " + l);
            }
        }
    }
}
