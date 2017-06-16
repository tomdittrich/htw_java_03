package uhrwerk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.5
 * @date 16.06.17
 */
public class UhrController implements ActionListener, KeyListener{

    private Uhr uhr;
    private KreisUhr kuhr;
    private DigitalUhr duhr;

    public UhrController(){
        uhr = new Uhr();
        duhr = new DigitalUhr(this);
        kuhr = new KreisUhr(this, uhr);

        uhr.addObserver(duhr);
        uhr.addObserver(kuhr);

    }

    /**
     * Wenn Action (EIN AUS) in DigitalUhr stattfindet
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
