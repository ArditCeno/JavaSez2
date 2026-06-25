package PerseritjeTest.Tez2025A.Ush3;

import java.util.*;

class DyqanElektronik {

    ArrayList<PajisjeElektronike> lista = new ArrayList<>();

    void shtoPajisje(PajisjeElektronike p) {
        lista.add(p);
    }

    void afishoPajisjetNeGaranci() {
        for (PajisjeElektronike p : lista)
            if (p.eshteNeGaranci())
                System.out.println(p);
    }

    int numriPajisjeve() {
        return lista.size();
    }

    PajisjeElektronike kerkoPajisje(String id) {
        for (PajisjeElektronike p : lista)
            if (p.getId().equals(id))
                return p;

        return null;
    }
}
