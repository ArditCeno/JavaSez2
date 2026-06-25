// 3.a) (8 pikë) Ndërtoni një klasë abstrakte Punonjës që ka atributet: emri, adresa dhe paga baze.
// Ndërtoni: konstruktorin me parametra, metodat aksesuese dhe ndryshuese për të gjitha atributet,
// dhe metodën abstrakte llogaritPagen().

package PerseritjeTest.Tez2024Vjesht.Ush3;

public abstract class Punonjes {
    private String emri;
    private String adresa;
    private double pagaBaze;

    public Punonjes(String emri, String adresa, double pagaBaze) {
        this.emri = emri;
        this.adresa = adresa;
        this.pagaBaze = pagaBaze;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public double getPagaBaze() {
        return pagaBaze;
    }

    public void setPagaBaze(double pagaBaze) {
        this.pagaBaze = pagaBaze;
    }

    public abstract double llogaritPagen();
}
