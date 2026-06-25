// 3.c) (10 pikë) Ndërtoni klasën Kompania e cila përmban një listë të punonjësve. Ndërtoni
// metodat shtoPunonjes(Punonjes punonjes) që shton një punonjës të ri në listë dhe metodën
// llogaritPagat() që llogarit dhe afishon pagat e të gjithë punonjësve në listë.

package PerseritjeTest.Tez2024Vjesht.Ush3;

import java.util.ArrayList;
import java.util.List;

public class Kompania {
    private List<Punonjes> punonjesit;

    public Kompania() {
        punonjesit = new ArrayList<>();
    }

    public void shtoPunonjes(Punonjes punonjes) {
        punonjesit.add(punonjes);
    }

    public void llogaritPagat() {
        for (Punonjes p : punonjesit) {
            System.out.println(p.getEmri() + ": " + p.llogaritPagen());
        }
    }
}
