package Lab2;
import javax.swing.*;

public class Ush2Fshi {
    static void main() {
        String titulli = "Konfirmim i Fshirjes";
        String pyetja = "A deshironi te fshini";

        int rezultati = JOptionPane.showConfirmDialog(
                null,
                pyetja,
                titulli,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (rezultati == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Te dhenat u fshine ", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (rezultati == JOptionPane.NO_OPTION || rezultati == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null, "Veprimi u anullua. Te dhenat jane ok", "Anullim", JOptionPane.WARNING_MESSAGE);
        }
    }
}

