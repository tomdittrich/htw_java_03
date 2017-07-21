package Lambdas;

/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 21.07.17
 */
public class MainLambdas {
    public static void main(String[] args) {

        // ILambdaInterface lambda = (int x) -> {return 3*x;};
        // ILambdaInterface lambda = x -> 3*x;

        Berechner calc = new Berechner();

        int multiplikator = 3;
        calc.berechne(x -> multiplikator * x, 2);
        calc.berechne((Integer x) -> {
            return multiplikator * x;
        }, 3);
    }
}
