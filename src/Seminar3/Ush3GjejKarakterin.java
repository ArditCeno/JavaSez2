package Seminar3;
/*3. Shkruani një aplikacion në JAVA që mer një rresht me tekst dhe një karakter
nëpërmjet dy fushave tekst dhe në klikim të një butoni gjen sa herë ndodhet ky karakter në text.*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush3GjejKarakterin extends JFrame {
    private JTextField textField;  //JTextArea textArea;
    private JTextField charField;
    private JLabel resultLabel;

    public Ush3GjejKarakterin() {
        setTitle("Gjej Karakterin ");
        setSize(400, 180); //setSize (500, 400)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        JLabel textLabel = new JLabel("Rreshti i text");
        textField = new JTextField(25);
        /* textArea = new JTextArea(5, 25);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);*/

        JLabel charLabel = new JLabel("Karakteri qe do te kerkosh");
        charField = new JTextField(10);

        JButton searchButton = new JButton("Gjej");
        resultLabel = new JLabel("Rezultati");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teksti = textField.getText(); //textArea.getText();
                String charStr = charField.getText();

                if (teksti.isEmpty()) {
                    resultLabel.setText("Jepni teks");
                    return;
                }

                if (charStr.isEmpty()) {
                    resultLabel.setText("Jepni nje karakter");
                    return;
                }

                char karakteri = charStr.charAt(0);
                int count = 0;

                for (int i = 0; i < teksti.length(); i++) {
                    if (teksti.charAt(i) == karakteri) {
                        count++;
                    }
                }

                resultLabel.setText("Karakteri '" + karakteri + "' ndodhet " + count + " here");
            }
        });

        add(textLabel);
        add(textField);// add(scrollPane);
        add(charLabel);
        add(charField);
        add(searchButton);
        add(resultLabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush3GjejKarakterin());
    }
}
