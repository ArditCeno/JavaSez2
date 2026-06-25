package PerseritjeTest.Tez2025A.Ush3;

class Laptop extends PajisjeElektronike {

    private String modeliProcesorit;

    public Laptop(String id, String marka, int g, String modeliProcesorit) {
        super(id, marka, g);
        this.modeliProcesorit = modeliProcesorit;
    }

    @Override
    public boolean eshteNeGaranci() {
        return garanciaNeMuaj > 12 && modeliProcesorit != null;
    }
}
