// 3.b) (7 pikë) Ndërtoni klasën PunonjesFullTime i cili trashëgon nga klasa Punonjës dhe ka
// atributin bonusVjetor. Ndërtoni konstruktorin, metodat aksesuese dhe ndryshuese si dhe metodën
// llogaritPagen(), e cila llogarit pagën duke shtuar bonusin vjetor në pagën bazë.

package PerseritjeTest.Tez2024Vjesht.Ush3;

public class PunonjesFullTime extends Punonjes {
    private double bonusVjetor;

    public PunonjesFullTime(String emri, String adresa, double pagaBaze, double bonusVjetor) {
        super(emri, adresa, pagaBaze);
        this.bonusVjetor = bonusVjetor;
    }

    public double getBonusVjetor() {
        return bonusVjetor;
    }

    public void setBonusVjetor(double bonusVjetor) {
        this.bonusVjetor = bonusVjetor;
    }

    @Override
    public double llogaritPagen() {
        return getPagaBaze() + bonusVjetor;
    }
}
