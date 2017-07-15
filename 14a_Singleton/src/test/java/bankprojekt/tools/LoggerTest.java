package bankprojekt.tools;

/**
 * Testprogramm zur Loggerklasse
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.5
 * @date 15.07.17
 */
public class LoggerTest {

    public static void main(String[] args) {
        Logger.getLogger().writeEntry(new IndexOutOfBoundsException());
        Logger.getLogger().writeEntry(new NullPointerException());
        Logger.getLogger().writeEntry(new ArithmeticException("Division by Zero"));

    }


}