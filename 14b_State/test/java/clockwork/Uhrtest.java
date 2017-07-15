package clockwork;

/**
 * Eine Uhr wird ausprobiert
 */
public class Uhrtest {

    public static void main(String[] args) {
        Clock uhr = new Clock();

        // Tick Tack!
        uhr.showTime();
        uhr.changeButton();
        uhr.modeButton();
        uhr.changeButton();
        uhr.changeButton();
        uhr.modeButton();
        uhr.changeButton();
        uhr.modeButton();
        uhr.showTime();
    }

}
