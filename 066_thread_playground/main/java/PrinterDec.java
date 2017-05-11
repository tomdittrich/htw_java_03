/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 11.05.17
 */
public class PrinterDec implements Runnable {
    Copyshop copy;

    public PrinterDec(Copyshop copy) {
        this.copy = copy;
    }

    @Override
    public void run() {
        int anzahl = 0;
        for (int i = 0; i < 100; i++) {

            synchronized (copy) {
                anzahl = copy.getPapier();
                if (!(anzahl <= 0)) {
                    copy.setPapier(copy.getPapier() - 1);
                    anzahl = copy.getPapier();
                    System.out.println("Entnommen: " + anzahl);
                } else {
                    try {
                        copy.wait();
                        i--;        // nötig damit mir keine Bücher verloren gehen
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }

        }


    }
}
