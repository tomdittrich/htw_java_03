package bankprojekt.verarbeitung;

/**
 * stellt ein allgemeines Konto dar
 */
public abstract class Konto 
{
	/** 
	 * der Kontoinhaber
	 */
	private Kunde inhaber;

	/**
	 * die Kontonummer
	 */
	private final long nummer;

	/**
	 * der aktuelle Kontostand
	 */
	private double kontostand;

	/**
	 * setzt den aktuellen Kontostand
	 * @param kontostand neuer Kontostand
	 */
	protected void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}

	/**
	 * Wenn das Konto gesperrt ist (gesperrt = true), k�nnen keine Aktionen daran mehr vorgenommen werden,
	 * die zum Schaden des Kontoinhabers w�ren (abheben, Inhaberwechsel)
	 */
	private boolean gesperrt;

    /**
     * die aktuelle Waehrung:
     * EUR, BGN (Bulgarische Leva), LTL (Litauische Litas). KM (Konvertible Mark)
     */
	private Currency valuta;

	/**
	 * Setzt die beiden Eigenschaften kontoinhaber und kontonummer auf die angegebenen Werte,
	 * der anfaengliche Kontostand wird auf 0 gesetzt.
     * Die Waehrung wird standatisiert auf Euro gesetzt.
	 *
	 * @param inhaber Kunde
	 * @param kontonummer long
	 * @throws IllegalArgumentException wenn der Inhaber null
	 */
	public Konto(Kunde inhaber, long kontonummer) {
		if(inhaber == null)
			throw new IllegalArgumentException("Inhaber darf nicht null sein!");
		this.inhaber = inhaber;
		this.nummer = kontonummer;
		this.kontostand = 0;
		this.gesperrt = false;
		this.valuta = Currency.EUR;
	}
	
	/**
	 * setzt alle Eigenschaften des Kontos auf Standardwerte
	 */
	public Konto() {
		//hier ist kein weiterer Code erlaubt
		this(Kunde.MUSTERMANN, 1234567);
	}


	/**
	 * liefert den Kontoinhaber zur�ck
	 * @return   Kunde
	 */
	public final Kunde getInhaber() {
		return this.inhaber;
	}
	
	/**
	 * setzt den Kontoinhaber
	 * @param kinh   neuer Kontoinhaber
	 * @throws GesperrtException wenn das Konto gesperrt ist
	 * @throws IllegalArgumentException wenn kinh null ist
	 */
	public final void setInhaber(Kunde kinh) throws GesperrtException{
		if (kinh == null)
			throw new IllegalArgumentException("Der Inhaber darf nicht null sein!");
		if(this.gesperrt)
			throw new GesperrtException(this.nummer);        
		this.inhaber = kinh;

	}
	
	/**
	 * liefert den aktuellen Kontostand
	 * @return   double
	 */
	public final double getKontostand() {
		return kontostand;
	}

	/**
	 * liefert die Kontonummer zurueck
	 * @return   long
	 */
	public final long getKontonummer() {
		return nummer;
	}

    /**
     * liefert die Waehrung vom DT Currency zurueck
     *
     * @return Waehrung vom DT Currency
     *
     */
	public Currency getAktuelleWaehrung(){
	    return this.valuta;
    }

    /**
     * Setter fuer die Waehrung
     *
     * @param neu neue Waehrung
     */
    public void setAktuelleWaehrung(Currency neu){
	    this.valuta = neu;
    }

	/**
	 * liefert zurueck, ob das Konto gesperrt ist oder nicht
	 * @return
	 */
	public final boolean isGesperrt() {
		return gesperrt;
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
	}

	/**
	 * Erhoeht den Kontostand um den eingezahlten Betrag in der angegebenen Waehrung
	 *
	 * @param betrag einzuzahlender Betrag (in der angegebenen Waehrung)
	 * @param currency die Waehrung des Betrags
	 */
	public void einzahlen(double betrag, Currency currency) {
		this.einzahlen((betrag/currency.getRate())* valuta.getRate());
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
		ausgabe += "Aktueller Kontostand: " + this.kontostand + " Euro ";
		ausgabe += this.getGesperrtText() + System.getProperty("line.separator");
		return ausgabe;
	}

	/**
	 * Mit dieser Methode wird der geforderte Betrag vom Konto abgehoben, wenn es nicht gesperrt ist.
	 *
	 * @param betrag double
	 * @throws GesperrtException wenn das Konto gesperrt ist
	 * @throws IllegalArgumentException wenn der betrag negativ ist 
	 * @return true, wenn die Abhebung geklappt hat, 
	 * 		   false, wenn sie abgelehnt wurde
	 */
	public abstract boolean abheben(double betrag) throws GesperrtException;


	/**
	 * Hebt den gewuenschten Betrag in der angegebenen Waehrung ab
	 *
	 * @param betrag abzuhebede Betrag (in der angegebenen Waehrung)
	 * @param currency die Waehrung des Betrags
	 * @return true, wenn geklappt
	 * @throws GesperrtException wenn Konto gesperrt
	 */
    public boolean abheben(double betrag, Currency currency) throws GesperrtException{
        return this.abheben((betrag/currency.getRate())* valuta.getRate());
    }

    /**
     * Wechselt die aktuelle Waehrung in die angegebene Waehrung
     *
     * @param neu Waehrung in die gewechselt und umgerechnet werden soll
     */
    public void waehrungswechsel(Currency neu){
    	// konvertiert erst den aktuellen Kontostand in Euro
		// und multipliziert ihn dann mit der neuen Waehrungs Rate auf den neuen Kontostand
		setKontostand((kontostand/valuta.getRate()) * neu.getRate()); // Converts
		setAktuelleWaehrung(neu);
	}

	/**
	 * sperrt das Konto, Aktionen zum Schaden des Benutzers sind nicht mehr moeglich.
	 */
	public final void sperren() {
		this.gesperrt = true;
	}

	/**
	 * entsperrt das Konto, alle Kontoaktionen sind wieder moeglich.
	 */
	public final void entsperren() {
		this.gesperrt = false;
	}
	
	
	/**
	 * liefert eine String-Ausgabe, wenn das Konto gesperrt ist
	 * @return "GESPERRT", wenn das Konto gesperrt ist, ansonsten ""
	 */
	public final String getGesperrtText()
	{
		if (this.gesperrt)
		{
			return "GESPERRT";
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * liefert die ordentlich formatierte Kontonummer
	 * @return auf 10 Stellen formatierte Kontonummer
	 */
	public String getKontonummerFormatiert()
	{
		return String.format("%10d", this.nummer);
	}
	
	/**
	 * liefert den ordentlich formatierten Kontostand
	 * @return formatierter Kontostand mit 2 Nachkommastellen und Waehrungssymbol
	 */
	public String getKontostandFormatiert()
	{
		return String.format("%10.2f Euro" , this.getKontostand());
	}
	
	/**
	 * Vergleich von this mit other; Zwei Konten gelten als gleich,
	 * wen sie die gleiche Kontonummer haben
	 * @param other
	 * @return true, wenn beide Konten die gleiche Nummer haben
	 */
	@Override
	public boolean equals(Object other)
	{
		if(this == other)
			return true;
		if(other == null)
			return false;
		if(this.getClass() != other.getClass())
			return false;
		if(this.nummer == ((Konto)other).nummer)
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode()
	{
		return 31 + (int) (this.nummer ^ (this.nummer >>> 32));
	}
	
	/**
	 * schreibt this aUF DIE kONSOLE
	 */
	public void aufDieKonsoleSchreiben()
	{
		System.out.println(this.toString());
	}
}
