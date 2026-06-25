package UML;

public class Studenti {
    private String lenda;
    private String dega;
    private String viti;
    private int nota;
    private boolean eshteVlersuar;

    public Studenti(String lenda, String dega, String viti) {
        this.lenda = lenda;
        this.dega = dega;
        this.viti = viti;
        this.nota = 0;
        this.eshteVlersuar = false;
    }

    public String getLenda() { return lenda; }
    public void setLenda(String lenda) { this.lenda = lenda; }
    public String getDega() { return dega; }
    public void setDega(String dega) { this.dega = dega; }
    public String getViti() { return viti; }
    public void setViti(String viti) { this.viti = viti; }
    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; this.eshteVlersuar = true; }
    public boolean eshteVlersuar() { return eshteVlersuar; }

    @Override
    public String toString() {
        return lenda + " | " + dega + " | " + viti;
    }
}
