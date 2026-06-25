package PerseritjeTest.TezZysha.Ush3;

public class Liber {
    private String titulli, autori;
    private int id;
    private static int count = 1;

    public Liber(){
        this.titulli = "i panjohur";
        this.autori = "i panjohur";
        this.id = 0 ;
    }
    public Liber(String titulli, String autori){

        this.titulli = titulli;
        this.autori = autori;
        this.id = count++ ;
    }
    public void setTitulli(String titulli){
        this.titulli = titulli;
    }
    public void setAutori(String autori){
        this.autori = autori;
    }
    public String getTitulli(){
        return titulli;
    }
    public String getAutori(){
        return autori;
    }
    public int getId(){
        return id;
    }
    public boolean equals(Liber tjeter){
        if(tjeter == null) return false;
        return this.titulli.equals(tjeter.titulli) && this.autori.equals(tjeter.autori)&& this.id==tjeter.id;
    }

    public String toString(){
        return this.titulli + " " + this.autori + " " + this.id;
    }
    public String getInicialet(){
        if(this.autori.equals("i panjohur")||this.autori.isEmpty()){
            return "i panjohur";
        }
        String [ ] pjeset = this.autori.split(" ");
        String inicialet = " ";

        inicialet += pjeset[0].charAt(0);

        if(pjeset.length > 1){
            inicialet += pjeset[pjeset.length-1].charAt(0);
        }
        return inicialet.toUpperCase();
    }

}
