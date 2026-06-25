package PerseritjeTest.Tez2025B.Ush3;

public abstract class MjetTransporti {
    private String id;
    private String prodhuesi;
    private int kapacitetiPasagjereve;

    public MjetTransporti(String id, String prodhuesi, int kapacitetiPasagjereve) {
        this.id = id;
        this.prodhuesi = prodhuesi;
        this.kapacitetiPasagjereve = kapacitetiPasagjereve;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getProdhuesi() {
        return prodhuesi;
    }
    public void setProdhuesi(String prodhuesi) {
        this.prodhuesi = prodhuesi;
    }
    public int getKapacitetiPasagjereve() {
        return kapacitetiPasagjereve;
    }
    public void setKapacitetiPasagjereve(int kapacitetiPasagjereve) {
        this.kapacitetiPasagjereve =  kapacitetiPasagjereve;
    }

    public abstract boolean eshteAktiv();

    @Override
    public String toString() {
        return "Mjet Transporti ID "+id+ " Prodhuesi "+prodhuesi + " Kapaciteti " +kapacitetiPasagjereve;
    }
}
