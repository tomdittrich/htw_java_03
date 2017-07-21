package Classes;

public class Main {

    public static void main(String[] args) {

        //nicht Oeffentlich
        nichtOeffentlich n1 = new nichtOeffentlich();
        n1.ausgabe1();

        //Innere
        Innere i1 = new Innere();
        Innere.Innere2 i2 = i1.new Innere2();
        i2.ausgabe2();

        // anonyme Klasse
        IAnonymeKlasse anonym = new IAnonymeKlasse() {
            @Override
            public int ausgabe() {
                return 1;
            }
        };

        System.out.println("Anonyme" + anonym.ausgabe());

    }
}
