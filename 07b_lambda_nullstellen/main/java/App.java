/**
 * App zur Nullstelle
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.5
 * @date 21.05.17
 */
public class App {
    public static void main(String[] args) {

        try {
            double ergebnis = Nullstelle.suche((a -> (a*a)-5), -30, 30);
            System.out.println("ENDE: " + ergebnis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
