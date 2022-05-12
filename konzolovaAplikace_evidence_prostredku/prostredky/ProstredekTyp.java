
package prostredky;

/**
 *
 * @author Admin
 */
public enum ProstredekTyp {
    MOTORKA("motorka"),
    OSOBNI_AUTO("osobní auto"),
    AUTOBUS("autobus"),
    NAKLADNI_AUTO("nákladní auto"),
    LETADLO("letadlo");
    
    private final String nazev;
    
    private ProstredekTyp(String nazev){
        this.nazev = nazev;
    }
    
    public String getNazev(){
        return nazev;
    }
    public static Enum[] getProstredky(){
        Enum[] vycet = {MOTORKA, OSOBNI_AUTO, AUTOBUS, NAKLADNI_AUTO, LETADLO};
        return vycet;
    }
    @Override
    public String toString(){
        return nazev;
    }
    
    
}
