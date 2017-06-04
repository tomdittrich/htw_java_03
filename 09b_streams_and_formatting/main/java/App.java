import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

/**
 * Programm zum Testen von Streams und Formatter
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 1.0
 * @date 02.06.17
 */
public class App {

    public static void main(String[] args) {

        int zahl_ganz = 0;
        double zahl_komma = 0;
        LocalTime jetzt = LocalTime.now();
        LocalDate heute = LocalDate.now();

        // int Zahl von Konsole einlesen
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Bitte ganzzahlige Zahl eingeben: ");
            zahl_ganz = Integer.parseInt(console.readLine()); // bei mehr als 10 Zeichen wirft Exception

            System.out.println("Bitte rationale Zahl eingeben: ");
            zahl_komma = Double.parseDouble(console.readLine()); // bei mehr als 10 Zeichen wirft Exception

        } catch (IOException e) {
            e.printStackTrace();
        }


        // in "out.txt" schreiben
        try (PrintWriter pw = new PrintWriter(new FileWriter("out.txt"))) {
            pw.printf(
                    "%1$d %n" +               //1: Zahl aus Eingabe
                            "%1$010d %n" +    //2: Zahl, mit 10 0en aufgefuellt
                            "%1$+,d %n" +     //3: Vorziechen, mit Tausender-Trennzeichen
                            "%2$f %n" +       //4: double Zahl
                            "%2$+.2f %n" +    //5: doube Zahl, Vorzeichen, 2 Stellen Komma
                            "%2$e %n",        //6: double Zahl, Exponent Darstellung
                    zahl_ganz, zahl_komma);

            pw.printf(Locale.US, "%.3f %n", zahl_komma); //7: double Zahl, US Format
            pw.printf("%% %n"); //8: % Zeichen
            pw.printf("%1$tA %1$te %1$tB %1$tY %n", heute); //9: aktuelles Datum (Sunday 4 June 2017)
            pw.printf(Locale.ITALY, "%1$tA %1$te %1$tB %1$tY %n", heute); //10: aktuelles Datum in ital.
            pw.printf("%1$tr %n", jetzt); //11: aktuelle Uhrzeit im engl. Format (05:12:45 PM)

            pw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
