package StreamsForCollections;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 21.07.17
 */
public class MainStreams {

    public static void main(String[] args) {
        List<Integer> sL = new LinkedList<>();

        sL.add(3);
        sL.add(1);
        sL.add(2);
        sL.add(4);
        sL.add(5);

        //for(int a:sL){
        //    System.out.println(a);
        //}

        System.out.println("Streams Kram:");

        // alle Werte zu Strings, dann alle Werte ausgeben
        sL.stream().map(x -> x.toString()).forEach(x -> System.out.printf(x));
        System.out.println(System.lineSeparator());

        // alle Werte sortieren, dann ausgeben
        sL.stream().sorted().forEach(x -> System.out.printf("%d", x));
        System.out.println(System.lineSeparator());

        // filtert erst Werte groeßergleich 3 raus und gibt diese aus
        sL.stream().filter(x -> x>=3).forEach(x -> System.out.printf("%d", x));
        System.out.println(System.lineSeparator());

        // addiert alle Werte zu einem zusammen
        int tmp = sL.stream().reduce(0, (a,b) -> a+b);
        System.out.println(tmp);

        // filtert Werter großergleich 3 raus und speichert sie in neuer Liste
        List<Integer> groesserListe = sL.parallelStream().filter(x -> x >=3 ).collect(Collectors.toList());

        for(int i:groesserListe){
            System.out.println(i);
        }

    }


}
