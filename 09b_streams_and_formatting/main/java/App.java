import java.io.*;

/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 02.06.17
 */
public class App {

    public static void main(String[] args) {

        int datei = 0;

        // int Zahl von Konsole einlesen
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Bitte Zahl eingeben: ");
            datei = Integer.parseInt(console.readLine()); // bei mehr als 10 Zeichen wirft Exception

        } catch (IOException e) {
            e.printStackTrace();
        }

        // in "out.txt" schreiben
        try (PrintWriter pw = new PrintWriter(new FileWriter("out.txt"))) {
            pw.printf(
                    "%d%n%" +   //1. Zeile, Zahl aus Eingabe
                    "010d%n" +  //2. Zeile, Zahl, mit 10 0en aufgefuellt
                    "%+,d",     //3. Zeile, Vorziechen, mit Tausender-Trennzeichen
                    datei, datei, datei);

            pw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


        //System.out.println(datei);

    }
}
