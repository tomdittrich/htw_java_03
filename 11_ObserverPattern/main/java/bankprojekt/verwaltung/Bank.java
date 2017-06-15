package bankprojekt.verwaltung;

import bankprojekt.verarbeitung.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Klasse zum Verwalten der Konten
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 1.3
 * @date 15.06.2017
 */
public class Bank extends Observable implements Cloneable, Serializable {

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
     * Erstellt Konten
     *
     * @param fabrik  welche Kontofabrik
     * @param inhaber Kunde
     * @return die erstellte Kontonummer
     */
    public long kontoErstellen(KontoFabrik fabrik, Kunde inhaber) {
        long ktn = groesteKtn + 64;
        this.groesteKtn = ktn;

        Konto tempKonto = fabrik.erstellen(inhaber, ktn);
        kontenliste.put(ktn, tempKonto);

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
        kontenliste.entrySet().parallelStream()
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
        List<Kunde> list = kontenliste.entrySet().parallelStream().
                filter(a -> a.getValue().getKontostand() >= minimum)
                .map(a -> a.getValue().getInhaber())
                .collect(Collectors.toList());
        return list;
    }

    /**
     * erstellt eine Kopie vom Bank Objekt
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Object objDest = null;
        byte[] byteData = null;

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            oos.close();

            byteData = bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(byteData))) {

            objDest = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objDest;
    }

    /**
     * Benachrichtigungen für obs einschalten
     * und einem Konto zuweisen
     *
     * @param obs ObserverKonto Object
     * @param ktn welches Konto soll damit verknüpft werden
     */
    public void addObserver(ObserverKonto obs, long ktn) {
        kontenliste.get(ktn).addObserver(obs);
    }

    /**
     * Benachrichtigungen für obs von einem
     * bestimmten Konto ausschalten
     *
     * @param obs ObserverKonto Object
     * @param ktn welches Konto damit verknüpft ist
     */
    public void deleteObserver(ObserverKonto obs, long ktn) {
        kontenliste.get(ktn).deleteObserver(obs);
    }

}
