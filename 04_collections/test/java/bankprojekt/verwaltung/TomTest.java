package bankprojekt.verwaltung;

import bankprojekt.verarbeitung.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 05.05.17
 */
public class TomTest {
    public static void main(String[] args) {
        Bank banki = new Bank(17055050);
        Kunde kundi1 = new Kunde();
        Kunde kundi2 = new Student("Seppel", "Bohnenstange", "Raum 101", LocalDate.parse("2000-01-01"), "Stardust Academy", "Risikopilot", LocalDate.parse("2145-12-12"), 3);
        Kunde kundi3 = new Kunde("Perry", "Rhodan", "Leipzig", LocalDate.parse("1960-05-05"));

        long ktn1 = banki.girokontoErstellen(kundi1);
        long ktn2 = banki.sparbuchErstellen(kundi2);
        long ktn3 = banki.sparbuchErstellen(kundi3);

        //System.out.println("Kontostand Student: "+banki.getKontostand(ktn2));
        //System.out.println("Kontostand Default Kunde: "+banki.getKontostand(ktn1));

        System.out.println(banki.getAlleKonten());

        if(banki.kontoLoeschen(ktn1)){
            System.out.println("Konto1 geloescht");
        }


        List<Long> liste = banki.getAlleKontonummern();
        System.out.println(liste);



    }
}
