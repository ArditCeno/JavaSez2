//Shembull menu
package Leksione;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Prove {
    public static void main(String[] args) {

        FlatDarkLaf.setup();
        // 1. Krijojmë dritaren kryesore (JFrame)
        JFrame frame = new JFrame("Shembull Menuje Java Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 2. Krijojmë shiritin e menusë (JMenuBar)
        JMenuBar menuBar = new JMenuBar();

        // 3. Krijojmë menunë kryesore "File" (JMenu)
        JMenu fileMenu = new JMenu("File");

        // 4. Krijojmë elementet e thjeshta (JMenuItem)

        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");

        // 5. Krijojmë nën-menunë "Export" (JMenu brenda JMenu-së)

        JMenu exportMenu = new JMenu("Export");
        JMenuItem pdfItem = new JMenuItem("PDF");
        JMenuItem wordItem = new JMenuItem("Word");

        exportMenu.add(pdfItem);
        exportMenu.add(wordItem);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(exportMenu); // Shton nën-menunë Export

        // 7. Shtojmë menunë File në shiritin kryesor
        menuBar.add(fileMenu);

        // 8. Vendosim shiritin e menusë në dritare
        frame.setJMenuBar(menuBar);

        // 9. Shto një veprim (Action) kur klikohet një opsion (p.sh. PDF)
        pdfItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Duke eksportuar në formatin PDF...");
            }
        });

        // E bëjmë dritaren të dukshme
        frame.setVisible(true);
    }
}