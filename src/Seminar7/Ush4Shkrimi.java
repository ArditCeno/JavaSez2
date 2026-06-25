package Seminar7;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ush4Shkrimi extends JFrame {
    private JTextField textField;
    private JPopupMenu popupMenu;

    public Ush4Shkrimi() {
        setTitle("Menu - Shkruaj tekstin tend");
        setSize(550, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        textField = new JTextField("Shkruaj ketu", 25);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setHorizontalAlignment(JTextField.CENTER);
        add(textField);

        popupMenu = new JPopupMenu();

        JRadioButtonMenuItem boldItem = new JRadioButtonMenuItem("Bold");
        JRadioButtonMenuItem italicItem = new JRadioButtonMenuItem("Italic");
        JRadioButtonMenuItem boldItalicItem = new JRadioButtonMenuItem("Bold Italic");
        JRadioButtonMenuItem plainItem = new JRadioButtonMenuItem("Normal");

        ButtonGroup group = new ButtonGroup();
        group.add(boldItem);
        group.add(italicItem);
        group.add(boldItalicItem);
        group.add(plainItem);

        boldItem.addActionListener(e -> textField.setFont(new Font("Arial", Font.BOLD, 16)));
        italicItem.addActionListener(e -> textField.setFont(new Font("Arial", Font.ITALIC, 16)));
        boldItalicItem.addActionListener(e -> textField.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16)));
        plainItem.addActionListener(e -> textField.setFont(new Font("Arial", Font.PLAIN, 16)));

        popupMenu.add(boldItem);
        popupMenu.add(italicItem);
        popupMenu.add(boldItalicItem);
        popupMenu.addSeparator();
        popupMenu.add(plainItem);

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) { checkForPopup(e); }
            @Override
            public void mousePressed(MouseEvent e) { checkForPopup(e); }

            private void checkForPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        };

        this.addMouseListener(mouseHandler);
        textField.addMouseListener(mouseHandler);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush4Shkrimi());
    }
}