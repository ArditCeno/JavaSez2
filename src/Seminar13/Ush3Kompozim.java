package Seminar13;

import java.io.*;

public class Ush3Kompozim {

    public static String kompreso(String tekst) {
        StringBuilder rezultati = new StringBuilder();
        int i = 0;

        while (i < tekst.length()) {
            char c = tekst.charAt(i);
            int count = 1;

            while (i + count < tekst.length() && tekst.charAt(i + count) == c) {
                count++;
            }

            if (count >= 4 && count <= 9) {
                rezultati.append("\\").append(count).append(c);
            } else {
                for (int j = 0; j < count; j++) {
                    rezultati.append(c);
                }
            }

            i += count;
        }

        return rezultati.toString();
    }

    public static void main(String[] args) {
        String hyrje = "src/Seminar13/pandryshuar.txt";
        String dalje = "src/Seminar13/ndryshuar.txt";

        StringBuilder permbajtja = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(hyrje))) {
            String rresht;
            while ((rresht = br.readLine()) != null) {
                permbajtja.append(rresht).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Gabim gjate leximit: " + e.getMessage());
            return;
        }

        String tekstKompresuar = kompreso(permbajtja.toString());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dalje))) {
            bw.write(tekstKompresuar);
            System.out.println("Skedari u kompresua me sukses ne: " + dalje);
        } catch (IOException e) {
            System.out.println("Gabim gjate shkrimit: " + e.getMessage());
        }
    }
}
