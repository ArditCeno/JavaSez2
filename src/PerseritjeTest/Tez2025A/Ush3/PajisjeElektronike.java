package PerseritjeTest.Tez2025A.Ush3;

abstract class PajisjeElektronike {
    private String id;
    private String marka;
    protected int garanciaNeMuaj;

    public PajisjeElektronike(String id, String marka, int garanciaNeMuaj) {
        this.id = id;
        this.marka = marka;
        this.garanciaNeMuaj = garanciaNeMuaj;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMarka() { return marka; }
    public void setMarka(String marka) { this.marka = marka; }

    public int getGaranciaNeMuaj() { return garanciaNeMuaj; }
    public void setGaranciaNeMuaj(int g) { garanciaNeMuaj = g; }

    public abstract boolean eshteNeGaranci();

    public String toString() {
        return "ID:" + id + " Marka:" + marka + " Garancia:" + garanciaNeMuaj;
    }
}
