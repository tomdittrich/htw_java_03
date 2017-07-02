package bankprojekt.verarbeitung;

import javafx.beans.property.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * stellt ein allgemeines Konto dar
 */
public abstract class Konto implements Serializable {
    /**
     * die Kontonummer
     */
    private final long nummer;

    /**
     * der Kontoinhaber
     */
    private Kunde inhaber;

    /**
     * Wenn das Konto gesperrt ist (gesperrt = true), koennen keine Aktionen daran mehr vorgenommen werden,
     * die zum Schaden des Kontoinhabers w�ren (abheben, Inhaberwechsel)
     */
    private BooleanProperty gesperrt = new SimpleBooleanProperty(false);

    /**
     * Wenn der Kontostand im Minus ist, false
     */
    private BooleanProperty PlusMinus = new SimpleBooleanProperty(false);

    /**
     * die aktuelle Waehrung:
     * EUR, BGN (Bulgarische Leva), LTL (Litauische Litas). KM (Konvertible Mark)
     */
    private Currency valuta;

    /**
     *
     */
    private List<ObserverKonto> observerList = new LinkedList<>();

    /**
     * der Kontostand
     */
    private ReadOnlyDoubleWrapper kontostand = new ReadOnlyDoubleWrapper();


    /**
     * Setzt die beiden Eigenschaften kontoinhaber und kontonummer auf die angegebenen Werte,
     * der anfaengliche Kontostand wird auf 0 gesetzt.
     * Die Waehrung wird standatisiert auf Euro gesetzt.
     *
     * @param inhaber     Kunde
     * @param kontonummer long
     * @throws IllegalArgumentException wenn der Inhaber null
     */
    public Konto(Kunde inhaber, long kontonummer) {
        if (inhaber == null)
            throw new IllegalArgumentException("Inhaber darf nicht null sein!");
        this.inhaber = inhaber;
        this.nummer = kontonummer;
        kontostand.set(0);
        gesperrt.set(false);
        this.valuta = Currency.EUR;
    }

    /**
     * setzt alle Eigenschaften des Kontos auf Standardwerte
     */
    public Konto() {
        //hier ist kein weiterer Code erlaubt
        this(Kunde.MUSTER, 1234567);
    }

    /**
     * liefert den Kontoinhaber zur�ck
     *
     * @return Kunde
     */
    public final Kunde getInhaber() {
        return this.inhaber;
    }

    /**
     * setzt den Kontoinhaber
     *
     * @param kinh neuer Kontoinhaber
     * @throws GesperrtException        wenn das Konto gesperrt ist
     * @throws IllegalArgumentException wenn kinh null ist
     */
    public final void setInhaber(Kunde kinh) throws GesperrtException {
        if (kinh == null)
            throw new IllegalArgumentException("Der Inhaber darf nicht null sein!");
        if (isGesperrt())
            throw new GesperrtException(this.nummer);
        this.inhaber = kinh;

    }

    /**
     * liefert den aktuellen Kontostand
     *
     * @return double
     */
    public final double getKontostand() {
        return kontostand.get();
    }

    /**
     * setzt den aktuellen Kontostand
     *
     * @param kontostand neuer Kontostand
     */
    protected void setKontostand(double kontostand) {
        this.kontostand.set(kontostand);
    }

    public ReadOnlyDoubleProperty kontostandProperty()
    {
        return this.kontostand.getReadOnlyProperty();
    }

    /**
     * liefert die Kontonummer zurueck
     *
     * @return long
     */
    public long getKontonummer() {
        return nummer;
    }

    /**
     * liefert die Waehrung vom DT Currency zurueck
     *
     * @return Waehrung vom DT Currency
     */
    public Currency getAktuelleWaehrung() {
        return this.valuta;
    }

    /**
     * Setter fuer die Waehrung
     *
     * @param neu neue Waehrung
     */
    public void setAktuelleWaehrung(Currency neu) {
        this.valuta = neu;
    }

    public void setGesperrt(boolean gesperrt) {
        this.gesperrt.set(gesperrt);
        System.out.println("Konto Sperre" + this.gesperrt.get()); // Konsolenausgabe zur Funktionskontrolle
    }

    /**
     * liefert zurueck, ob das Konto gesperrt ist oder nicht
     *
     * @return
     */
    public final boolean isGesperrt() {
        //System.out.println("Konto Sperre: " + this.gesperrt.get()); // Konsolenausgabe zur Funktionskontrolle
        return gesperrt.get();
    }

    /**
     * Getter gesperrt Property
     *
     * @return
     */
    public BooleanProperty gesperrtProperty() {
        return gesperrt;
    }

    /**
     * Getter ist Kontostand im Plus oder Minus
     *
     * @return Plus? dann true
     */
    public boolean isPlusMinus() {
        return PlusMinus.get();
    }

    /**
     * Getter plusMinus Property
     * @return
     */
    public BooleanProperty plusMinusProperty() {
        return PlusMinus;
    }

    /**
     * Prüft ob der Kontostand im Plus/Minus ist und setzt den Boolean dafür
     */
    void PlusMinusAnpassen (){
        if(kontostand.get()>=0) {
            PlusMinus.set(true);
        }
        else {
            PlusMinus.set(false);
        }
    }

    /**
     * Erhoeht den Kontostand um den eingezahlten Betrag.
     *
     * @param betrag double
     * @throws IllegalArgumentException wenn der betrag negativ ist
     */
    public void einzahlen(double betrag) throws IllegalArgumentException {
        if (betrag < 0) {
            throw new IllegalArgumentException("Negativer Betrag");
        }
        setKontostand(getKontostand() + betrag);
        PlusMinusAnpassen();
        notifyObservers();
    }

    /**
     * Erhoeht den Kontostand um den eingezahlten Betrag in der angegebenen Waehrung
     *
     * @param betrag   einzuzahlender Betrag (in der angegebenen Waehrung)
     * @param currency die Waehrung des Betrags
     */
    public void einzahlen(double betrag, Currency currency) {
        this.einzahlen((betrag / currency.getRate()) * valuta.getRate());
    }

    /**
     * Gibt eine Zeichenkettendarstellung der Kontodaten zurueck.
     */
    @Override
    public String toString() {
        String ausgabe;
        ausgabe = "Kontonummer: " + this.getKontonummerFormatiert()
                + System.getProperty("line.separator");
        ausgabe += "Inhaber: " + this.inhaber;
        ausgabe += "Aktueller Kontostand: " + this.kontostand.get() + " Euro ";
        ausgabe += this.getGesperrtText() + System.getProperty("line.separator");
        return ausgabe;
    }

    /**
     * Template Method
     * Mit dieser Methode wird der geforderte Betrag vom Konto abgehoben, wenn es nicht gesperrt ist.
     *
     * @param betrag double
     * @return true, wenn die Abhebung geklappt hat,
     * false, wenn sie abgelehnt wurde
     * @throws GesperrtException        wenn das Konto gesperrt ist
     * @throws IllegalArgumentException wenn der Betrag negativ ist
     */
    public boolean abheben(double betrag) throws GesperrtException, IllegalArgumentException {
        if (betrag < 0)
            throw new IllegalArgumentException();
        if (this.isGesperrt())
            throw new GesperrtException(this.getKontonummer());
        if (abhebenMoeglich(betrag)) {
            setKontostand(getKontostand() - betrag);
            PlusMinusAnpassen();
            notifyObservers();
            return true;
        }

        return false;
    }

    /**
     * Prueft ob ein Geldabheben moeglich ist
     *
     * @param betrag double
     * @return true, wenn Geldabheben moeglich ist
     */
    protected abstract boolean abhebenMoeglich(double betrag);


    /**
     * Hebt den gewuenschten Betrag in der angegebenen Waehrung ab
     *
     * @param betrag   abzuhebede Betrag (in der angegebenen Waehrung)
     * @param currency die Waehrung des Betrags
     * @return true, wenn geklappt
     * @throws GesperrtException wenn Konto gesperrt
     */
    public boolean abheben(double betrag, Currency currency) throws GesperrtException {
        return this.abheben((betrag / currency.getRate()) * valuta.getRate());
    }

    /**
     * Wechselt die aktuelle Waehrung in die angegebene Waehrung
     *
     * @param neu Waehrung in die gewechselt und umgerechnet werden soll
     */
    public void waehrungswechsel(Currency neu) {
        // konvertiert erst den aktuellen Kontostand in Euro
        // und multipliziert ihn dann mit der neuen Waehrungs Rate auf den neuen Kontostand
        setKontostand((kontostand.get() / valuta.getRate()) * neu.getRate()); // Converts
        setAktuelleWaehrung(neu);
    }

    /**
     * sperrt das Konto, Aktionen zum Schaden des Benutzers sind nicht mehr moeglich.
     */
    public final void sperren() {
        gesperrt.set(true);
        notifyObservers();
    }

    /**
     * entsperrt das Konto, alle Kontoaktionen sind wieder moeglich.
     */
    public final void entsperren() {
        gesperrt.set(false);
        notifyObservers();
    }


    /**
     * liefert eine String-Ausgabe, wenn das Konto gesperrt ist
     *
     * @return "GESPERRT", wenn das Konto gesperrt ist, ansonsten ""
     */
    public final String getGesperrtText() {
        if (isGesperrt()) {
            return "GESPERRT";
        } else {
            return "";
        }
    }

    /**
     * liefert die ordentlich formatierte Kontonummer
     *
     * @return auf 10 Stellen formatierte Kontonummer
     */
    public String getKontonummerFormatiert() {
        return String.format("%10d", this.nummer);
    }

    /**
     * liefert den ordentlich formatierten Kontostand
     *
     * @return formatierter Kontostand mit 2 Nachkommastellen und Waehrungssymbol
     */
    public String getKontostandFormatiert() {
        return String.format("%10.2f Euro", this.getKontostand());
    }

    /**
     * Vergleich von this mit other; Zwei Konten gelten als gleich,
     * wen sie die gleiche Kontonummer haben
     *
     * @param other
     * @return true, wenn beide Konten die gleiche Nummer haben
     */
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (this.getClass() != other.getClass())
            return false;
        if (this.nummer == ((Konto) other).nummer)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return 31 + (int) (this.nummer ^ (this.nummer >>> 32));
    }

    /**
     * schreibt this auf die Konsole
     */
    public void aufDieKonsoleSchreiben() {
        System.out.println(this.toString());
    }

    /**
     * Benachrichtigungen für obs einschalten
     *
     * @param obs observerKonto Object
     */
    public void addObserver(ObserverKonto obs) {
        observerList.add(obs);

    }

    /**
     * Benachrichtigungen für obs ausschalten
     *
     * @param obs observerKonto Object
     */
    public void deleteObserver(ObserverKonto obs) {
        observerList.remove(obs);
    }

    /**
     * Benachrichtigungs Dienste/Observer informieren
     */
    protected void notifyObservers() {
        /* neues temp. Konto, um eine Kopie zu erzuegen
        * soll verhindern, dass Observer im Original Objekt
        * Änderungen vornehmen kann
        * Nachtrag:
        * !! IST UNSINN DA POINTER !!
        */
        Konto tempKonto = this;
        observerList.forEach(a -> a.update(tempKonto));
    }

}
