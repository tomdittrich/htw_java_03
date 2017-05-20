package bankprojekt.verwaltung;

import bankprojekt.verarbeitung.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Klasse zum Verwalten der Konten
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 * @date 05.05.2017
 */
public class Bank {

    long bankleitzahl;
    long groesteKtn = 1000000000;

    Map<Long, Konto> kontenliste = new HashMap<>();

    /**
     * Default Konstruktor
     * Erzeugt eine Test BLZ 123456789
     */
    public Bank() {
        this(123456789);
    }

    /**
     * Konstruktor
     *
     * @param bankleitzahl BLZ
     */
    public Bank(long bankleitzahl) {
        this.bankleitzahl = bankleitzahl;
    }

    /**
     * Getter Bankleitzahl
     *
     * @return BLZ
     */
    public long getBankleitzahl() {
        return bankleitzahl;
    }

    /**
     * Getter Konto
     *
     * @param nummer Kontonr
     * @return
     */
    public Konto getKonto(long nummer) {
        return kontenliste.get(nummer);
    }

    /**
     * @param nummer
     * @return
     */
    public double getKontostand(long nummer) {

        // ! Exception bauen

        Konto tempKonto = kontenliste.get(nummer);
        return tempKonto.getKontostand();
    }

    /*public Kunde getInhaberi(long nummer){

        Konto tempKonto = kontenliste.get(nummer);
        return tempKonto.getInhaber();
    }
    */

    /**
     * Erstellt Girkonto
     *
     * @param inhaber Kunde
     * @return die erstellte Kontonummer
     */
    public long girokontoErstellen(Kunde inhaber) {
        // Generiert KTN, in 64er Abstaenden
        // zufaellige KTN moeglich, dann Abfrage mit containsKey ob vorhanden
        long ktn = groesteKtn + 64;
        this.groesteKtn = ktn;

        Konto neuGiro = new Girokonto(inhaber, ktn, 500);
        kontenliste.put(ktn, neuGiro);

        return ktn;
    }

    /**
     * Erstellt Sparbuch
     *
     * @param inhaber Kunde
     * @return die erstellte Kontonummer
     */
    public long sparbuchErstellen(Kunde inhaber) {

        // Generiert KTN, in 64er Abstaenden
        // zufaellige KTN moeglich, dann Abfrage mit containsKey ob vorhanden
        long ktn = groesteKtn + 64;
        this.groesteKtn = ktn;

        Konto neuSpar = new Sparbuch(inhaber, ktn);
        kontenliste.put(ktn, neuSpar);

        return ktn;
    }

    /**
     * Gibt alle Kontonummern und den dazugehörigen Kontostand aus
     *
     * @return KTNs + Betrag
     */
    public String getAlleKonten() {
        String ausgabe = "";

        for (Long nr : kontenliste.keySet()) {
            ausgabe += "Kontonummer: " + nr + " Kontostand: " + getKontostand(nr) + System.getProperty("line.separator");
        }

        return ausgabe;
    }

    /**
     * Gibt alle Kontonummern und den dazugehörigen Kontostand aus
     *
     * @return KTNs + Betrag
     */
    @Override
    public String toString() {
        return getAlleKonten();
    }

    /**
     * Gibt alle Kontonummern zurück
     *
     * @return Kontonummern als ArrayListe
     */
    public List<Long> getAlleKontonummern() {
        List<Long> liste = new ArrayList<>();

        for (Long nr : kontenliste.keySet()) {
            liste.add(nr);
        }

        return liste;

    }

    /**
     * Hebt Geld vom angegebenen Konto ab
     *
     * @param von    welches Konto
     * @param betrag Betrag
     * @return hat es geklappt?
     * @throws GesperrtException falls gesperrt
     */
    public boolean geldAbheben(long von, double betrag) throws GesperrtException {
        return (kontenliste.get(von)).abheben(betrag);
    }

    /**
     * Zahlt Geld auf das angegebene Konto ein
     *
     * @param auf    welches Konto
     * @param betrag Betrag
     */
    public void geldEinzahlen(long auf, double betrag) {
        (kontenliste.get(auf)).einzahlen(betrag);
    }

    /**
     * Loescht ein Konto VOLLSTAENDIG
     *
     * @param nummer welches Konto
     * @return hat es geklappt?
     */
    public boolean kontoLoeschen(long nummer) {
        kontenliste.remove(nummer);
        return !kontenliste.containsKey(nummer);

    }

    /**
     * Ueberweist Geld von einem Girokonto zu anderen Girokonto
     * Bankintern (gleiche BLZ)
     *
     * @param vonKontonr       von welchem Konto
     * @param nachKontonr      zu welchem Konto
     * @param betrag           Betrag
     * @param verwendungszweck Verwendungszweck
     * @return hat es geklappt?
     * @throws GesperrtException falls ein Konto gesperrt
     */
    public boolean geldUeberweisen(long vonKontonr, long nachKontonr, double betrag, String verwendungszweck) throws GesperrtException {

        // ! noch Exceptions bauen (KTN nicht vorhanden, falsche InstanceOf usw)
        // "uberweisungAbsenden" und "ueberweisungEmpfangen" koennte man auch nutzen, umstaendlicher

        boolean ergebnis = false;

        // Konten vorhanden? Prueft damit gleichzeitig ob beide Konten die
        // selbe BLZ haben (bzw. in gleicher Map sind)
        if (kontenliste.containsKey(vonKontonr) && kontenliste.containsKey(nachKontonr)) {

            // Beides Girokonten?
            if (kontenliste.get(vonKontonr) instanceof Girokonto && kontenliste.get(nachKontonr) instanceof Girokonto) {

                // klappt das Abheben? (Dispo?, Kontobetrag, Gesperrt)
                if (geldAbheben(vonKontonr, betrag)) {
                    geldEinzahlen(nachKontonr, betrag);
                    ergebnis = true;
                }

            } else {
                System.out.println("Ueberweisung nur zwischen Girokonten moeglich.");
            }

        } else {
            System.out.println("Mind. eine Kontonummer ist falsch");
        }

        return ergebnis;
    }

    /**
     * die Methode sperrt alle Konten, deren Kontostand im Minus ist
     */
    public void pleitegeierSperren() {
        kontenliste.entrySet().stream()
                .filter(a -> a.getValue().getKontostand() < 0)
                .forEach(a -> a.getValue().sperren());
    }

    /**
     * liefert eine Liste aller Kunden, die auf einem Konto einen Konto-
     * stand haben, der ein Minimum beträgt
     *
     * @param minimum Minimum an Kontostand
     * @return Liste mit den Kunden
     */
    public List<Kunde> getKundenMitVollemKonto(double minimum) {
        List<Kunde> list = kontenliste.entrySet().stream().
                filter(a -> a.getValue().getKontostand() >= minimum)
                .map(a -> a.getValue().getInhaber())
                .collect(Collectors.toList());
        return list;
    }


}
