///*MER NGA PERDORUESI DITELINDJEN D.M.V DHE GJININ DHE DO STIMULONI  NR PERSOAL TE KETIJ PERSONI
//NR PERSONAL DITELINDJA-> 12.03.2006 GJINIA-> M/F ME DY TEXT FIELD
//1/2/3/4/5/6/7/8/9/10
//VITI DY TE PARAT DY TE DYTAT MUAJ PER MASHKULL 03/ FEMER ESHTE 53
//5 DHE 6 ESHTE DATA I=1980 J=1990 K=2000 L=2010 M=2020 K6
//789 ESHTE I SATI JE NE RASTIN QE JE FUTUR 10 NE E KEMI DERI TE 6*/
//package PerseritjeArdit;
//import javax.swing.*;
//import java.awt.*;
//
//public class LlogaritNP {
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Gjeneruesi i NP");
//        frame.setSize(400, 250);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new GridLayout(4, 2, 10, 10));
//
//        JLabel lblData = new JLabel("Data Date/Muaj/Vit");
//        JTextField txtData = new JTextField();
//
//        JLabel lblGjinia = new JLabel("Gjinia vetem M dhe F");
//        JTextField txtGjinia = new JTextField();
//
//        JButton btnGjenero = new JButton("Gjenero ID");
//        JTextField txtRezultati = new JTextField();
//        txtRezultati.setEditable(false);
//
//        btnGjenero.addActionListener(e -> {
//            try {
//                String data = txtData.getText();
//                String gjinia = txtGjinia.getText().toUpperCase();
//
//                String[] pjeset = data.split("\\.");
//                String dita = pjeset[0];
//                String muaji = pjeset[1];
//                String vitiFull = pjeset[2];
//
//                String shkronjaFillimi = "";
//                int vitiInt = Integer.parseInt(vitiFull);
//
//                if (vitiInt >= 2020) shkronjaFillimi = "M";
//                else if (vitiInt >= 2010) shkronjaFillimi = "L";
//                else if (vitiInt >= 2000) shkronjaFillimi = "K";
//                else if (vitiInt >= 1990) shkronjaFillimi = "J";
//                else if (vitiInt >= 1980) shkronjaFillimi = "I";
//                else shkronjaFillimi = "H";
//
//                String vitiID = vitiFull.substring(2);
//
//                int muajiInt = Integer.parseInt(muaji);
//                if (gjinia.equals("F")) {
//                    muajiInt += 50;
//                }
//                String muajiID = String.format("%02d", muajiInt);
//
//                String fundi = "001A";
//
//                String nrPersonal = shkronjaFillimi + vitiID + muajiID + dita + fundi;
//                txtRezultati.setText(nrPersonal);
//
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(frame, "Format i gabuar! Përdorni DD.MM.YYYY");
//            }
//        });
//        frame.add(lblData);
//        frame.add(txtData);
//        frame.add(lblGjinia);
//        frame.add(txtGjinia);
//        frame.add(new JLabel("ID e Gjeneruar:"));
//        frame.add(txtRezultati);
//        frame.add(new JLabel(""));
//        frame.add(btnGjenero);
//
//        frame.setVisible(true);
//    }
//}
//
