package Seminar9.Ush2;

public class Menaxher extends Punonjes {
    private int numerPunonjesish;

    public Menaxher(String emri, String mbiemri, String ID, String pozita, double paga, int numerPunonjesish) {
        super(emri, mbiemri, ID, pozita, paga);
        this.numerPunonjesish = numerPunonjesish;
    }

    public int getNumerPunonjesish() {
        return numerPunonjesish;
    }

    public void setNumerPunonjesish(int numerPunonjesish) {
        this.numerPunonjesish = numerPunonjesish;
    }

    @Override
    public String toString() {
        return "Menaxher{" +
                "numerPunonjesish=" + numerPunonjesish +
                ", pozita='" + getPozita() + '\'' +
                ", paga=" + getPaga() +
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
        Menaxher menaxher = (Menaxher) o;
        return numerPunonjesish == menaxher.numerPunonjesish;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Integer.hashCode(numerPunonjesish);
        return result;
    }
}
