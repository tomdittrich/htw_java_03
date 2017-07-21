package Threads;

/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 11.05.17
 */
public class MainThreads {

    public static void main(String[] args) throws InterruptedException {
        Copyshop copy = new Copyshop();

        PrinterDec print1 = new PrinterDec(copy);
        PrinterInc print2 = new PrinterInc(copy);

        Thread k1 = new Thread(print1,"Threadi 1");
        Thread k2 = new Thread(print2,"Thread 2");

        PrinterDec print3 = new PrinterDec(copy);
        PrinterInc print4 = new PrinterInc(copy);

        Thread k3 = new Thread(print3);
        Thread k4 = new Thread(print4);

        //System.out.println(k1);
        //System.out.println(k3);
        //System.out.println(k4);

        k1.start();
        k2.start();
        k3.start();
        k4.start();
    }
}
