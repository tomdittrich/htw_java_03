package bankprojekt.verwaltung;

import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Kunde;
import bankprojekt.verarbeitung.ObserverCLI;
import bankprojekt.verarbeitung.Student;

import java.time.LocalDate;

/**
 * einfaches Test Programm zur Observer Übung
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 15.06.17
 */
public class TestingApp {
    public static void main(String[] args) {

        Bank bank;
        long ktn1, ktn2, ktn3;

        KontoFabrik giroFabrik = new GirokontoFabrik();
        KontoFabrik sparFabrik = new SparbuchFabrik();
        ObserverCLI cliBenachrichtigung = new ObserverCLI();

        Kunde kundi1 = new Kunde();
        Kunde kundi2 = new Student("Seppel", "Bohnenstange", "Raum 101", LocalDate.parse("2000-01-01"), "Stardust Academy", "Risikopilot", LocalDate.parse("2145-12-12"), 3);
        Kunde kundi3 = new Kunde("Hans", "Grubert", "Terrania", LocalDate.parse("1960-05-05"));

        bank = new Bank(17055050);

        ktn1 = bank.kontoErstellen(giroFabrik, kundi1);
        ktn2 = bank.kontoErstellen(giroFabrik, kundi2);
        ktn3 = bank.kontoErstellen(sparFabrik, kundi3);

        bank.addObserver(cliBenachrichtigung, ktn1);
        bank.addObserver(cliBenachrichtigung, ktn2);

        try {
            bank.geldUeberweisen(ktn1, ktn2, 500, "normaler Test");
        } catch (GesperrtException e) {
            e.printStackTrace();
        }

        try {
            bank.geldUeberweisen(ktn2, ktn1, 400, "normaler Test");
        } catch (GesperrtException e) {
            e.printStackTrace();
        }
    }
}
