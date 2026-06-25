package UML;

public class AplikimController {

    public StudentMaster apliko(AplikimPerMaster a) {
        //TODO: Percakto logjiken e aprovimit
        boolean aprovohet = true;

        if (aprovohet) {

            StudentMaster s = new StudentMaster();

            s.setEmri(a.getEmri());
            s.setMbiemri(a.getMbiemri());
            s.setAtesia(a.getAtesia());
            s.setGrupi(a.getGrupi());
            s.setViti(a.getViti());
            s.setEmail(a.getEmail());
            s.setID(a.getID());

            return s;
        }

        return null;
    }
}
