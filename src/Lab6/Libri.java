package Lab6;

public class Libri {
    private String titulli;
    private String autori;

    public Libri(String titulli, String autori) {
        this.titulli = titulli;
        this.autori = autori;
    }

    @Override
    public String toString() {
        return "'" + titulli + "' nga " + autori;
    }
}
