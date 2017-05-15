package baelle;

import java.util.HashSet;
import java.util.Set;

/**
 * Steuerungsklasse f�r eine Ball-Animation
 *
 * @author Doro
 */
public class Ballspiel {
    private static int zaehler = 0;
    private BallFrame f;
    private Set<Ball> baelleSet;

    /**
     * erstellt die Steuerungsklasse f�r die angegebene Oberfl�che
     *
     * @param f
     */
    public Ballspiel(BallFrame f) {
        this.f = f;
        this.baelleSet = new HashSet<>();
    }

    /**
     * startet einen Ball auf der Oberfl�che und l�sst ihn h�pfen
     */
    public void ballStarten() {
        Ball b = new Ball(f.getZeichenflaeche());
        baelleSet.add(b);

        Thread ballThread = new Thread(b);
        ballThread.start();
    }

    /**
     * h�lt alle B�lle auf der Oberfl�che an, so dass sie an ihrer aktuellen Position
     * stehen bleiben
     */
    public void baelleStoppen() {
        for (Ball b : baelleSet) {
            b.setMoves(false);
        }
    }

    /**
     * l�sst alle angehaltenen B�lle wieder weiter h�pfen
     */
    public void baelleWeiter() {
        for (Ball b : baelleSet) {
            b.setMoves(true);
        }
    }

    /**
     * l�scht alle B�lle von der Oberfl�che
     */
    public void alleLoeschen() {
        for (Ball b : baelleSet) {
            b.loeschen();
        }
        baelleSet.clear();
    }
}




