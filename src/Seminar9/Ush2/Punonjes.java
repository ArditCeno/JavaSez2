package Seminar9.Ush2;

public class Punonjes extends Person {
    private String pozita;
    private double paga;

    public Punonjes(String emri, String mbiemri, String ID, String pozita, double paga) {
        super(emri, mbiemri, ID);
        this.pozita = pozita;
        this.paga = paga;
    }

    public String getPozita() {
        return pozita;
    }

    public void setPozita(String pozita) {
        this.pozita = pozita;
    }

    public double getPaga() {
        return paga;
    }

    public void setPaga(double paga) {
        this.paga = paga;
    }

    @Override
    public String toString() {
        return "PerseritjeTest.Tez2024Vjesht.Ush3.Punonjes{" +
                "pozita='" + pozita + '\'' +
                ", paga=" + paga +
                ", emri='" + getEmri() + '\'' +
                ", mbiemri='" + getMbiemri() + '\'' +
                ", ID='" + getID() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Punonjes punonjes = (Punonjes) o;
        return Double.compare(paga, punonjes.paga) == 0 &&
                pozita.equals(punonjes.pozita);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(paga);
        result = 31 * result + pozita.hashCode();
        return result;
    }
}
