package PerseritjeTest.Tez2025A.Ush4;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class StudentGUI extends JFrame {

    JTextField idF = new JTextField(10);
    JTextField emerF = new JTextField(10);
    JTextField notaF = new JTextField(10);

    JTextArea area = new JTextArea(10,30);

    File file = new File("students.txt");

    public StudentGUI(){

        setLayout(new FlowLayout());

        JButton shto = new JButton("Shto");
        JButton shfaq = new JButton("Shfaq");
        JButton fshi = new JButton("Fshi");

        add(new JLabel("ID"));
        add(idF);

        add(new JLabel("Emri"));
        add(emerF);

        add(new JLabel("Nota"));
        add(notaF);

        add(shto);
        add(shfaq);
        add(fshi);

        add(new JScrollPane(area));

        shto.addActionListener(e->shtoStudent());
        shfaq.addActionListener(e->shfaqStudentet());
        fshi.addActionListener(e->fshiStudent());

        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void shtoStudent(){
        try(PrintWriter pw =
            new PrintWriter(new FileWriter(file,true))){

            pw.println(idF.getText()+","+
                       emerF.getText()+","+
                       notaF.getText());

            shfaqStudentet();
        }
        catch(Exception ex){}
    }

    void shfaqStudentet(){
        area.setText("");

        try(Scanner sc=new Scanner(file)){
            while(sc.hasNextLine())
                area.append(sc.nextLine()+"\n");
        }
        catch(Exception ex){}
    }

    void fshiStudent(){

        String id=idF.getText();

        ArrayList<String> lista=new ArrayList<>();

        try(Scanner sc=new Scanner(file)){
            while(sc.hasNextLine()){
                String r=sc.nextLine();

                if(!r.startsWith(id+","))
                    lista.add(r);
            }
        }catch(Exception ex){}

        try(PrintWriter pw=
            new PrintWriter(file)){

            for(String s:lista)
                pw.println(s);

        }catch(Exception ex){}

        shfaqStudentet();
    }

    public static void main(String[] args){
        new StudentGUI();
    }
}
