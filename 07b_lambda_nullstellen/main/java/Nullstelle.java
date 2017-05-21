import java.util.function.Function;

/**
 * Berechnet die Nullstelle nach dem
 * Bisektions Verfahren
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.7
 * @date 21.05.17
 */
public class Nullstelle {

    // Toleranz, ab wann die Nullstelle gefunden ist
    private static final double toleranz = 0.01;
    private static int maxIt = 20; // max. Iterationen
    private static int zaehler = 0; // Zaehler für Iterationen

    /**
     * sucht eine Nullstelle nach BiSektion
     *
     * @param links linke Intervallgrenze (sollte Gegenteil von rechts sein)
     * @param rechts rechte Intervallgrenze (Gegenteil von links)
     * @return die Nullstelle
     * @throws Exception falls zu viele Iterationen
     */
    // !!!
    // links und rechts sollten beim Start das Gegenteil sein
    // z.B. "-10 und 10" oder "-5 und 5"
    // andernfalls kann es zu Problemen kommen
    public static double suche(Function<Double, Double> f, double links, double rechts) throws Exception {
        double half = (links + rechts) / 2;
        double y1 = f.apply(half);

        if (zaehler <= maxIt) {
            System.out.printf("Links %f Rechts %f Y %f Iteration %d\n", links, rechts, y1, zaehler);

            if (Math.abs(links - rechts) < toleranz) {
                System.out.println("Fertig");
            } else {
                if (y1 > 0) {
                    zaehler++;
                    return (suche(f,links, half));
                } else if (y1 < 0) {
                    zaehler++;
                    return (suche(f,half, rechts));
                }
            }

        } else {
            throw new Exception("Zu viele Iterationen");
        }

        return half;
    }
}
