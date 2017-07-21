package LambdasNullstellen;

/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 21.07.17
 */
public class MainLambdasNullstellen {
    public static void main(String[] args) {

        try {
            double ergebnis = Nullstelle.suche((a -> (a*a)+1), -30, 30);
            System.out.println("ENDE: " + ergebnis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
