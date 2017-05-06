
/**
 * Kind
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 06.05.17
 */
public class Kind extends Person implements Frech<Person>{

    Kuscheltier kuscheltier1;
    Kuscheltier kuscheltier2;

    public void wachsen(boolean schnell){

    }

    @Override
    public boolean aergern(Person p) {
        return false;
    }
    
}
