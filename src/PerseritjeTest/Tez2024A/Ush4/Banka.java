package PerseritjeTest.Tez2024A.Ush4;
/*Ndërtoni një program grafik për të paraqitur dhe manipuluar llogari bankare me një emër dhe balance.
Ndërfaqja duhet të ketë fusha për emrin dhe balancën si dhe dy butona për depozitimin dhe tërheqjen e parave.
Kur përdoruesi klikon në ndonjë nga butonat merret informacioni nga përdoruesit nëpërmjet një dialog box-i dhe pasqyrohet ndryshimi në fushën e balancës.
 */
import javax.swing.*;
import java.awt.*;
public class Banka extends JFrame {
    JTextField balance = new JTextField("0", 10);

    double b=0;
    Banka(){
        JButton dep= new JButton("Depozito");
        JButton ter= new JButton("TErhiq");

        setLayout(new FlowLayout());
        add(balance);
        add(dep);
        add(ter);

        dep.addActionListener(e->{String x= JOptionPane.showInputDialog("Shuma");
        b += Double.parseDouble(x);
        balance.setText(b+"");
        });
        ter.addActionListener(e->{String x= JOptionPane.showInputDialog("Shuma");
        b -= Double.parseDouble(x);
        balance.setText(b+"");
        });
        setSize(300,300);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Banka();
    }
}
