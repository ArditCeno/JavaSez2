package Seminar9.Ush1;

import java.time.LocalDate;

public class Punonjes {
    private String emerPunonjesi;
    private String idPunonjesi;
    private LocalDate dataPunesimit;
    private String departamenti;
    private double paga;
    private Adresa adresa;

    public Punonjes(String emerPunonjesi, String idPunonjesi, LocalDate dataPunesimit,
                    String departamenti, double paga, Adresa adresa) {
        this.emerPunonjesi = emerPunonjesi;
        this.idPunonjesi = idPunonjesi;
        this.dataPunesimit = dataPunesimit;
        this.departamenti = departamenti;
        this.paga = paga;
        this.adresa = adresa;
    }

    public String getEmerPunonjesi() {
        return emerPunonjesi;
    }

    public void setEmerPunonjesi(String emerPunonjesi) {
        this.emerPunonjesi = emerPunonjesi;
    }

    public String getIdPunonjesi() {
        return idPunonjesi;
    }

    public void setIdPunonjesi(String idPunonjesi) {
        this.idPunonjesi = idPunonjesi;
    }

    public LocalDate getDataPunesimit() {
        return dataPunesimit;
    }

    public void setDataPunesimit(LocalDate dataPunesimit) {
        this.dataPunesimit = dataPunesimit;
    }

    public String getDepartamenti() {
        return departamenti;
    }

    public void setDepartamenti(String departamenti) {
        this.departamenti = departamenti;
    }

    public double getPaga() {
        return paga;
    }

    public void setPaga(double paga) {
        this.paga = paga;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Emri: " + emerPunonjesi + ", ID: " + idPunonjesi +
                ", Data e punesimit: " + dataPunesimit +
                ", Departamenti: " + departamenti +
                ", Paga: " + paga +
                ", Adresa: " + adresa;
    }
}
