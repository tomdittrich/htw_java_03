package Lambdas;

/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 21.07.17
 */
public class Berechner {

    public void berechne(ILambdaInterface<Integer> f, int a) {

        System.out.printf("Die Zahl %d berechnet lautet %d" + System.lineSeparator(), a, f.methodeTest(a));
    }
}
