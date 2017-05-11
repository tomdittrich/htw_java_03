/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 11.05.17
 */
public class PrinterInc implements Runnable {
    Copyshop copy;

    public PrinterInc(Copyshop copy) {
        this.copy = copy;
    }

    @Override
    public void run() {
        int anzahl = 0;
        for (int i = 0; i < 100; i++) {

            synchronized (copy) {


                copy.setPapier(copy.getPapier() + 1);
                anzahl = copy.getPapier();
                System.out.println("Hingestellt: " + anzahl);
                copy.notify();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }


        }


    }
}
