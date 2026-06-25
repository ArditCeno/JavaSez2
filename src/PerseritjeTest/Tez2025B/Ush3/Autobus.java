package PerseritjeTest.Tez2025B.Ush3;

public class Autobus extends MjetTransporti {
    private String numriItinerarit;

    public Autobus(String id, String prodhuesi, int kapacitetiPasagjereve, String numriItinerarit) {
        super(id, prodhuesi, kapacitetiPasagjereve);
        this.numriItinerarit = numriItinerarit;
    }
    public String getNumriItinerarit(){
        return numriItinerarit;
    }
    public void setNumriItinerarit(String numriItinerarit){
        this.numriItinerarit = numriItinerarit;
    }
    @Override
    public boolean eshteAktiv() {
        return true;
    }
    @Override
    public String toString() {
        return super.toString() + " Autobus Itinerari  "+numriItinerarit;
    }
}
