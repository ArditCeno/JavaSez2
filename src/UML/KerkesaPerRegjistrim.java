package UML;

public class KerkesaPerRegjistrim {
    private String departamenti;
    private String dega;
    private String viti;
    private String lenda;
    private StatusiKerkeses statusi;

    public enum StatusiKerkeses {
        NE_PRITJE,
        E_APROVUAR,
        E_REFUZUAR
    }

    public KerkesaPerRegjistrim(String departamenti, String dega, String viti, String lenda) {
        this.departamenti = departamenti;
        this.dega = dega;
        this.viti = viti;
        this.lenda = lenda;
        this.statusi = StatusiKerkeses.NE_PRITJE;
    }

    public String getDepartamenti() { return departamenti; }
    public String getDega() { return dega; }
    public String getViti() { return viti; }
    public String getLenda() { return lenda; }
    public StatusiKerkeses getStatusi() { return statusi; }
    public void setStatusi(StatusiKerkeses statusi) { this.statusi = statusi; }

    @Override
    public String toString() {
        return lenda + " | " + dega + " | " + viti;
    }
}
