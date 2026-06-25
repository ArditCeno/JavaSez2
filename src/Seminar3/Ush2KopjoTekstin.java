package Seminar3;
/*2. Krijoni një aplikacion në JAVA që përmban dy textArea dhe një buton. Në klik të
butonit të kopjohet teksti nga një textearea (e pare) tek tjetra (e dyta).*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush2KopjoTekstin extends JFrame {
    private JTextArea textArea1;
    private JTextArea textArea2;

    public Ush2KopjoTekstin() {
        setTitle("Kopjo text");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 5));
        JPanel bottomPanel = new JPanel();

        JLabel label1 = new JLabel("Teksti i shkruar");
        JLabel label2 = new JLabel("Tekst i kopjuar");

        textArea1 = new JTextArea(10, 15);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        JScrollPane scroll1 = new JScrollPane(textArea1);

        textArea2 = new JTextArea(10, 15);
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        textArea2.setEditable(false);
        JScrollPane scroll2 = new JScrollPane(textArea2);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(label1, BorderLayout.NORTH);
        panel1.add(scroll1, BorderLayout.CENTER);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(label2, BorderLayout.NORTH);
        panel2.add(scroll2, BorderLayout.CENTER);

        topPanel.add(panel1);
        topPanel.add(panel2);

        JButton copyButton = new JButton("Kopjo tek e dyta");
        JButton appendButton = new JButton("Kopjo tek e dyta (Append)"); // append

        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teksti = textArea1.getText();
                textArea2.setText(teksti);
            }
        });

        appendButton.addActionListener(new ActionListener() { //append
            @Override
            public void actionPerformed(ActionEvent e) {
                String teksti = textArea1.getText();
                textArea2.append(teksti +"\n" );
            }
        });

        bottomPanel.add(copyButton);
        bottomPanel.add(appendButton);//append button


        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush2KopjoTekstin());
    }
}
