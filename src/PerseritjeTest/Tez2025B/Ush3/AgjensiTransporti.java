package PerseritjeTest.Tez2025B.Ush3;
import java.util.ArrayList;

public class AgjensiTransporti {
    private ArrayList<MjetTransporti> mjetet;

    public AgjensiTransporti() {
        this.mjetet = new ArrayList<>();
    }
    public void shtoMjetet(MjetTransporti mjet) {
        if(mjet != null) {
            mjetet.add(mjet);
            System.out.println("Mjet Transporti me id  "+mjet.getId());
        }
    }
    public void afishoMjetetAktive() {
        System.out.println("Mjetet Aktive");
        boolean kaMjeteAktive = false;
        for (MjetTransporti mjet : mjetet) {
            if(mjet.eshteAktiv()){
                System.out.println(mjet.toString());
                kaMjeteAktive = true;
            }
        }
        if(!kaMjeteAktive){
            System.out.println("Ska mjet aktiv");
        }
    }
    public int numriMjeteve(){
        return mjetet.size();
    }
    public MjetTransporti kerkoSipasId(String id){
        for (MjetTransporti mjet : mjetet){
            if(mjet.getId().equals(id)){
                return mjet;
            }
        }
        return null;
    }
}
