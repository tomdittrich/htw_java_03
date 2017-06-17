package uhrwerk;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * stellt eine analoge uhr dar
 *
 * @author Doro
 */
public class KreisUhr extends JFrame implements Observer {
    private static final long serialVersionUID = 1L;
    private static final String TITEL = "Kreisuhr";
    private static final String INFO = "Tasten: A(usschalten), E(inschalten)";
    private static final int BREITE = 400;
    private static final int HOEHE = 300;
    private static final int ZENTRUM_X = BREITE / 2;
    private static final int ZENTRUM_Y = HOEHE / 2;
    private static final int RADIUS = 100;
    private static final int DURCHMESSER = 2 * RADIUS;
    private static final int POS_INFO_X = 20;
    private static final int POS_INFO_Y = 40;
    private static final int POS_UHRZEIT = 60;
    private static final int[] ZEIGERLAENGE = new int[]{5 * RADIUS / 10, 6 * RADIUS / 10, 7 * RADIUS / 10}; // L�ngen der 3 Zeiger
    private static final Color[] ZEIGERFARBE = new Color[]{Color.red, Color.green, Color.blue}; // Farben der 3 Zeiger
    private static final Color HINTERGRUND_FARBE = Color.white;
    private static final Color KREIS_FARBE = Color.black;
    private static final int[][] END_X = new int[3][60];
    private static final int[][] END_Y = new int[3][60];
    private static final double ZWEI_PI = 2 * Math.PI;
    private static final double[] KONST = new double[]{ZWEI_PI / 12, ZWEI_PI / 60, ZWEI_PI / 60};

    private boolean uhrAn;
    private Uhr uhrzeit;
    private UhrController uc;

    /**
     * erstellt die analoge Uhr und bringt sie auf den Bildschirm
     */
    public KreisUhr(UhrController u, Uhr uh) {
        this.uc = u;
        uhrAn = true;
        uhrzeit = uh; // notwendig für die Initalisierung, wird beim ersten Update überschriben

        // Erstellen der Oberfl�chenelemente:
        setTitle(TITEL);
        setSize(BREITE, HOEHE);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Einrichten des KeyListeners, d.h. die uhr reagiert auf Tastendruck
        this.addKeyListener(uc); // UhrController
    }

    /**
     * Analoge uhr zeichnen
     */
    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, BREITE, HOEHE);
        g.setColor(HINTERGRUND_FARBE);
        g.fillRect(0, 0, BREITE, HOEHE);
        g.setColor(KREIS_FARBE);
        g.drawOval((BREITE - DURCHMESSER) / 2, (HOEHE - DURCHMESSER) / 2, DURCHMESSER, DURCHMESSER); // Kreis
        final int[] zeit = new int[]{uhrzeit.getStunde(), uhrzeit.getMinute(), uhrzeit.getSekunde()};
        g.drawString(INFO, POS_INFO_X, POS_INFO_Y);
        g.drawString(String.format("%02d:%02d:%02d", zeit[0], zeit[1], zeit[2]), POS_INFO_X, POS_UHRZEIT); // Uhrzeit digital
        for (int i = 0; i < END_X.length; i++) { // f�r jeden Zeiger
            final int z = zeit[i]; // Stunde, Minute oder Sekunde
            if (END_X[i][z] == 0) { // wurde noch nicht berechnet
                final double grad = z * KONST[i];
                END_X[i][z] = (int) (ZENTRUM_X + ZEIGERLAENGE[i] * Math.sin(grad));
                END_Y[i][z] = (int) (ZENTRUM_Y - ZEIGERLAENGE[i] * Math.cos(grad));
            }
            g.setColor(ZEIGERFARBE[i]);
            g.drawLine(ZENTRUM_X, ZENTRUM_Y, END_X[i][zeit[i]], END_Y[i][zeit[i]]);
        }
    }

    /**
     * Schaltet Uhr Boolean ein
     */
    protected void uhrEin() {
        this.uhrAn = true;
        repaint();
    }

    /**
     * Schaltet Uhr Boolean aus
     */
    protected void uhrAus() {
        this.uhrAn = false;
        repaint();
    }

    /**
     * nach Observable Benachrichtigung
     * aktualisiert die Uhranzeige auf die aktuelle Systemzeit
     *
     * @param o   Uhr Objekt
     * @param arg nix
     */
    @Override
    public void update(Observable o, Object arg) {
        this.uhrzeit = (Uhr) o;

        if (uhrAn) repaint();

    }
}