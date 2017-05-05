package bankprojekt.verwaltung;

import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Kunde;
import bankprojekt.verarbeitung.Student;

import java.time.LocalDate;
import java.util.List;

/**
 * Testklasse fuer Bank Klasse
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.0
 * @date 05.05.17
 */
public class TomTest {
    public static void main(String[] args) throws GesperrtException {
        Bank banki = new Bank(17055050);
        Kunde kundi1 = new Kunde();
        Kunde kundi2 = new Student("Seppel", "Bohnenstange", "Raum 101", LocalDate.parse("2000-01-01"), "Stardust Academy", "Risikopilot", LocalDate.parse("2145-12-12"), 3);
        Kunde kundi3 = new Kunde("Perry", "Rhodan", "Leipzig", LocalDate.parse("1960-05-05"));

        long ktn1 = banki.girokontoErstellen(kundi1);
        long ktn2 = banki.girokontoErstellen(kundi2);
        long ktn3 = banki.sparbuchErstellen(kundi3);

        //System.out.println("Kontostand Student: "+banki.getKontostand(ktn2));
        //System.out.println("Kontostand Default Kunde: "+banki.getKontostand(ktn1));

        System.out.println(banki.geldUeberweisen(ktn1, ktn2, 400, "Test"));

        System.out.println(banki.getAlleKonten());

        if (banki.kontoLoeschen(ktn3)) {
            System.out.println("Konto3 geloescht");
        }

        List<Long> liste = banki.getAlleKontonummern();
        System.out.println(liste);
    }
}
