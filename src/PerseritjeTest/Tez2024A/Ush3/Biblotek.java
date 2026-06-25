package PerseritjeTest.Tez2024A.Ush3;
/*a) (10 pikë) Ndërtoni një klasë Bibliotekë që ka atributet e mëposhtme: emri, adresa dhe lista e librave.
Ndërtoni:1.konstruktorin me parametra dhe metodat aksesuese dhe ndryshuese;
2.metodën shtoLibër që merr si parametër një libër dhe e shton atë në listën e librave.
Libri nuk duhet të shtohet nëse ai ekziston;
3.metodën hiqLibër që merr si parametër një libër dhe e heq atë nga lista e librave;
4.metodën numërLibrash që kthen numrin e librave në bibliotekë.*/

import java.util.*;

class Biblotek{
    private String emri;
    private String adresa;
    private ArrayList<Liber> libra;

    Biblotek(String emri, String adresa){
        this.emri = emri;
        this.adresa = adresa;
        libra = new ArrayList<>();
    }
    public String getEmer(){
        return emri;
    }
    public void setEmer(String emri){
        this.emri = emri;
    }
    public String  getAdresa(){
        return adresa;
    }
    public void setAdresa(String adresa){
        this.adresa = adresa;
    }
    void shtoLiber(Liber l) {
        if (!libra.contains(l)) {
            libra.add(l);
        }
    }
    void hiqLiber(Liber l ){
            libra.remove(l);
    }
    int numriLibrash(){
          return libra.size();
    }
}
