package seminar4;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;

public class Ushtrimi3 extends JFrame {

    private JTextArea textArea;
    private JLabel labelNumri;

    public Ushtrimi3() {
        setTitle("TextArea me Menu");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        krijoMenuBar();

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);

        labelNumri = new JLabel("Numri i karaktereve eshte 0");

        add(scrollPane, BorderLayout.CENTER);
        add(labelNumri, BorderLayout.SOUTH);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ndryshoLabel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ndryshoLabel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                ndryshoLabel();
            }
        });

        setVisible(true);
    }

    private void krijoMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenu menuFormat = new JMenu("Format");
        JMenu menuLookAndFeel = new JMenu("Look & Feel");

        JMenuItem itemClear = new JMenuItem("Clear");
        JMenuItem itemExit = new JMenuItem("Exit");

        JMenuItem itemUppercase = new JMenuItem("Uppercase");
        JMenuItem itemLowercase = new JMenuItem("Lowercase");

        JMenuItem itemMetal = new JMenuItem("Metal");
        JMenuItem itemSystem = new JMenuItem("System");

        menuFile.add(itemClear);
        menuFile.add(itemExit);

        menuFormat.add(itemUppercase);
        menuFormat.add(itemLowercase);

        menuLookAndFeel.add(itemMetal);
        menuLookAndFeel.add(itemSystem);

        menuBar.add(menuFile);
        menuBar.add(menuFormat);
        menuBar.add(menuLookAndFeel);

        setJMenuBar(menuBar);

        itemClear.addActionListener(e -> textArea.setText(""));

        itemExit.addActionListener(e -> dispose());

        itemUppercase.addActionListener(e -> textArea.setText(textArea.getText().toUpperCase()));

        itemLowercase.addActionListener(e -> textArea.setText(textArea.getText().toLowerCase()));

        itemMetal.addActionListener(e -> ndryshoPamjen("javax.swing.plaf.metal.MetalLookAndFeel"));
        itemSystem.addActionListener(e -> ndryshoPamjen(UIManager.getSystemLookAndFeelClassName()));
    }

    private void ndryshoPamjen(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ndryshoLabel() {
        labelNumri.setText("Numri i karaktereve " + textArea.getText().length());
    }
}
