package Classes;


public class Innere {

    private int a = 1;

    public class Innere2 {
        int b = 2;

        public void ausgabe2() {
            System.out.println("Innere" + b);
            System.out.println("Aussere" + Innere.this.a); // geht auch ohne Innere.this
        }
    }
}
