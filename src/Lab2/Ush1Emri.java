package Lab2;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ush1Emri {
    private JFrame frame;
    private JLabel label;
    
    public Ush1Emri(String emri, String mbiemri) {
        frame = new JFrame("USHTRIMI I PARE ");
        
        frame.setSize(400, 200);
        
        frame.setLocationRelativeTo(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        label = new JLabel("Unë quhem " + emri + " " + mbiemri);
        
        frame.add(label);

        frame.setVisible(true);
    }
}
