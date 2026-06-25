package PerseritjeTest.Tez2024A.Ush3;
/*Ndërtoni klasën Libër e cila ka atributet titulli dhe autori.
Ndërtoni konstruktorin e kësaj klase si dhe metodat aksesuese për variablat e instancës titulli dhe autori.
Ndërtoni metodën toString() e cila do të afishojë të dhënat e librit.
 */

class Liber {
    private String titulli;
    private String autori;

    Liber(String titulli,String autori){
        this.titulli = titulli;
        this.autori = autori;
    }
    public String getTitulli() {
        return titulli;
    }
    public String getAutor(){
        return autori;
    }
    public String toString(){
        return titulli + " " + autori;
    }
}
