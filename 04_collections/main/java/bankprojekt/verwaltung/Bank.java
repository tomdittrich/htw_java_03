package bankprojekt.verwaltung;

import bankprojekt.verarbeitung.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BLUB
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 26.04.2017
 */
public class Bank {

    // BLZ
    long bankleitzahl;

    long groesteKtn = 1000000000;

    Map<Long, Konto> Kontenliste = new HashMap<>();

    /**
     * Default Konstruktor
     *
     * @param bankleitzahl BLZ
     */
    public Bank(long bankleitzahl) {
        this.bankleitzahl = bankleitzahl;
    }

    public long getBankleitzahl() {
        return bankleitzahl;
    }

    public double getKontostand(long nummer) {

        // ! Exception bauen

        Konto tempKonto = Kontenliste.get(nummer);
        return tempKonto.getKontostand();
    }

    public long girokontoErstellen(Kunde inhaber) {
        // Generiert KTN, in 64er Abstaenden
        long ktn = groesteKtn + 64;
        this.groesteKtn = ktn;

        Konto neuGiro = new Girokonto(inhaber, ktn, 500);
        Kontenliste.put(ktn, neuGiro);

        return ktn;
    }

    public long sparbuchErstellen(Kunde inhaber) {

        // Generiert KTN, in 64er Abstaenden
        long ktn = groesteKtn + 64;
        this.groesteKtn = ktn;

        Konto neuSpar = new Sparbuch(inhaber, ktn);
        Kontenliste.put(ktn, neuSpar);

        return ktn;
    }

    public String getAlleKonten() {
        String ausgabe = "";

        for (Long nr : Kontenliste.keySet()) {
            ausgabe += "Kontonummer: " + nr + " Kontostand: " + getKontostand(nr) + System.getProperty("line.separator");
        }

        return ausgabe;
    }

    public List<Long> getAlleKontonummern(){
        List<Long> liste = new ArrayList<>();

        for (Long nr : Kontenliste.keySet()){
            liste.add(nr);
        }

        return liste;

    }

    public boolean geldAbheben(long von, double betrag) throws GesperrtException {
        return (Kontenliste.get(von)).abheben(betrag);
    }

    public void geldEinzahlen(long auf, double betrag) {
        (Kontenliste.get(auf)).einzahlen(betrag);
    }

    public boolean kontoLoeschen(long nummer) {
        Kontenliste.remove(nummer);
        return !Kontenliste.containsKey(nummer);

    }

    public boolean geldUeberweisen(long vonKontonr, long nachKontonr, double betrag, String verwendungszweck) {
        return false;

    }

}
