package bankprojekt.verarbeitung;

/**
 * ENUM fuer Waehrungen mit festen Umrechnungskurs
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 1.0
 * @date 08.04.2017
 */

public enum Currency {

    EUR(1), BGN(1.95583), LTL(3.4528), KM(1.95583);

    private double rate; // currency rate

    /**
     * Constructor to init. the currency value/rate compared to Euro
     *
     * @param rate the currency rate
     */
    private Currency(double rate) {
        this.rate = rate;
    }

    /**
     * Return the currency rate compared to Euro
     *
     * @return the currency rate
     */
    public double getRate() {
        return this.rate;
    }

    /**
     * Converts Euro to the selected rate
     *
     * @param value amount in Euro
     * @return converted amount
     */
    public double convert(double value) {
        return value * this.rate;
    }
}