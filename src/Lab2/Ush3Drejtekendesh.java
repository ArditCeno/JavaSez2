import javax.swing.*;

public class Ush3Drejtekendesh {
    private double gjatesia;
    private double gjeresia;
    
    public Ush3Drejtekendesh() {

        String gjatesiaStr = JOptionPane.showInputDialog(
            null,
            "Shkruaj gjatësinë e drejtkëndëshit:",
            "Input Gjatësia",
            JOptionPane.QUESTION_MESSAGE
        );
        

        String gjeresiStr = JOptionPane.showInputDialog(
            null,
            "Shkruaj gjerësinë e drejtkëndëshit:",
            "Input Gjerësia",
            JOptionPane.QUESTION_MESSAGE
        );
        
        try {

            this.gjatesia = Double.parseDouble(gjatesiaStr);
            this.gjeresia = Double.parseDouble(gjeresiStr);
            
            if (gjatesia <= 0 || gjeresia <= 0) {
                JOptionPane.showMessageDialog(
                    null,
                    "Gjatesia dhe gjeresia duhet te jene numra poz",
                    "Gabim",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            llogaritDheShfaq();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                null,
                "Duhet të futni numra te vlefshem!",
                "Gabim",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void llogaritDheShfaq() {

        double siperfaqja = gjatesia * gjeresia;
        
        double perimetri = 2 * (gjatesia + gjeresia);
        
        String mesazhi = String.format(
            "Gjatesia: %.2f\nGjeresia: %.2f\n\nSip: %.2f\nPer: %.2f",
            gjatesia,
            gjeresia,
            siperfaqja,
            perimetri
        );
        
        JOptionPane.showMessageDialog(
            null,
            mesazhi,
            "Rezultatet e Drejtkendeshti",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    public static void main(String[] args) {
        new Ush3Drejtekendesh();
    }
}
