package Seminar1;

public class Ush3Person {
    protected String emri;

    public Ush3Person(String emri) {
        this.emri = emri;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public void printo() {
        System.out.println("Emri: " + emri);
    }

    public boolean emerNjejte(Ush3Person p) {
        return this.emri.equalsIgnoreCase(p.emri);
    }
}