package Seminar9.Ush1;

public class Adresa {
    private String rruga;
    private String qyteti;

    public Adresa(String rruga, String qyteti) {
        this.rruga = rruga;
        this.qyteti = qyteti;
    }

    public String getRruga() {
        return rruga;
    }

    public void setRruga(String rruga) {
        this.rruga = rruga;
    }

    public String getQyteti() {
        return qyteti;
    }

    public void setQyteti(String qyteti) {
        this.qyteti = qyteti;
    }

    @Override
    public String toString() {
        return "Rruga: " + rruga + ", Qyteti: " + qyteti;
    }
}
