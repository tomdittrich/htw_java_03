/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 11.05.17
 */
public class App {

    public static void main(String[] args) {
        Copyshop copy = new Copyshop();

        PrinterDec print1 = new PrinterDec(copy);
        PrinterInc print2 = new PrinterInc(copy);

        Thread k1 = new Thread(print1);
        Thread k2 = new Thread(print2);

        PrinterDec print3 = new PrinterDec(copy);
        PrinterInc print4 = new PrinterInc(copy);

        Thread k3 = new Thread(print3);
        Thread k4 = new Thread(print4);

        k1.start();
        k2.start();
        k3.start();
        k4.start();
    }
}
