package Seminar2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush3Numeruesi2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ushtrimi 3 Menyra 2");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 2, 10, 10)); //vendos nje kshu si makina llogaritese

        JLabel label = new JLabel("Vlera:");
        JTextField textField = new JTextField("0", 20);
        //textField.setEditable(false); //e ben te pa editueshem textfieldin
        JButton buttonPlus = new JButton("Rrit +1");
        JButton buttonMinus = new JButton("Zbrit -1");
        JButton buttonKatrori = new JButton("Katrori");
        JButton buttonZero = new JButton("Zero");
        JButton buttonKubi = new JButton("Kubi");
        JButton buttonShtoDhjet = new JButton("Shto +10");
        JButton buttonZbritDhjet = new JButton("Hiq -10");
        JButton buttonNdryshoShenje = new JButton("Ndrysho shenje");
        JButton buttonShto100 = new JButton ("Shto +100");
        JButton buttonPerimetriRrethit  = new JButton ("Perimetri i rrethit");
        JButton buttonSypRrethit = new JButton ("Syprina e rrethit");


        buttonPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numri = Double.parseDouble(textField.getText());
                    numri++;
                    textField.setText(String.valueOf(numri));
                } catch (NumberFormatException ex) {
                    shfaqError(frame);
                }
            }
        });

        buttonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numri = Double.parseDouble(textField.getText());
                    numri--;
                    textField.setText(String.valueOf(numri));
                } catch (NumberFormatException ex) {
                    shfaqError(frame);
                }
            }
        });

        buttonKatrori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numri = Double.parseDouble(textField.getText());
                    numri=numri*numri;
                    textField.setText(String.valueOf(numri));
                } catch (NumberFormatException ex) {
                    shfaqError(frame);
                }
            }
        });

        buttonZero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numri = Double.parseDouble(textField.getText());
                    numri=numri*0;
                    textField.setText(String.valueOf(numri));
                } catch (NumberFormatException ex) {
                    shfaqError(frame);
                }
            }
        });

        buttonKubi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                try{
                    double numri = Double.parseDouble(textField.getText());
                    numri= numri*numri*numri;
                    textField.setText(String.valueOf(numri));
                } catch (NumberFormatException ex) {
                    shfaqError(frame);
                }
            }
        });

        buttonShtoDhjet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numri = Double.parseDouble(textField.getText());
                    numri=numri+10;
                    textField.setText(String.valueOf(numri));
                }catch (NumberFormatException ex) {
                    shfaqError(frame);
                }
            }
        });

        buttonZbritDhjet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numri = Double.parseDouble(textField.getText());
                    numri=numri-10;
                    textField.setText(String.valueOf(numri));
                }catch (NumberFormatException ex) {
                    shfaqError(frame);
                }
            }
        });

        buttonNdryshoShenje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numri = Double.parseDouble(textField.getText());
                    numri=numri*-1;
                    textField.setText(String.valueOf(numri));
                }catch (NumberFormatException ex) {
                    shfaqError(frame);
                }
            }
        });

        buttonShto100.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numri = Double.parseDouble(textField.getText());
                    numri=numri+100;
                    textField.setText(String.valueOf(numri));
                }catch (NumberFormatException ex) {
                    shfaqError(frame);
                }
            }
        });

        buttonPerimetriRrethit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numri = Double.parseDouble(textField.getText());
                     numri=2* 3.14 *numri;
                     textField.setText(String.valueOf(numri));
                }catch (NumberFormatException ex){
                    shfaqError(frame);
                }
            }
        });

        buttonSypRrethit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    double numri = Double.parseDouble(textField.getText());
                    numri=3.14*numri*numri;
                    textField.setText(String.valueOf(numri));
                }catch (NumberFormatException ex){
                    shfaqError(frame);
                }
            }
        });


        frame.add(label);
        frame.add(textField);
        frame.add(buttonPlus);
        frame.add(buttonMinus);
        frame.add(buttonKatrori);
        frame.add(buttonKubi);
        frame.add(buttonShtoDhjet);
        frame.add(buttonZbritDhjet);
        frame.add(buttonShto100);
        frame.add(buttonPerimetriRrethit);
        frame.add(buttonSypRrethit);
        frame.add(buttonNdryshoShenje);
        frame.add(buttonZero);
        //frame.setLayout(new GridLayout(3, 3)); vendos nje kshu si makina llogaritese


        frame.setVisible(true);
    }

    private static void shfaqError(Component parent) {
        JOptionPane.showMessageDialog(parent, "GABIM: Ju lutem vendosni vetem numra!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}