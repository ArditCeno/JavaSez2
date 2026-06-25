package Seminar1;

public class Ush3Punonjes extends Ush3Person {
    private double paga;

    public Ush3Punonjes(String emri, double paga) {
        super(emri);
        this.paga = paga;
    }

    public double getPaga() {
        return paga;
    }

    public void setPaga(double paga) {
        this.paga = paga;
    }

    @Override
    public void printo() {
        System.out.println("Emri: " + emri + ", Paga: " + paga);
    }
}