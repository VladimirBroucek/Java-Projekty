/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prostredky;

/**
 *
 * @author Admin
 */
public class NakladniAuto extends DopravniProstredek{
    
    private int nosnost;
    private int pocetNaprav;
    
    public NakladniAuto(float hmotnost, String spz){
        super(ProstredekTyp.NAKLADNI_AUTO, spz, hmotnost);
    }
    
    public NakladniAuto(String spz,float hmotnost, int nosnost, int pocetNaprav){
        this(hmotnost, spz);
        this.nosnost = nosnost;
        this.pocetNaprav = pocetNaprav;
    }

    public int getNosnost() {
        return nosnost;
    }

    public void setNosnost(int nosnost) {
        this.nosnost = nosnost;
    }

    public int getPocetNaprav() {
        return pocetNaprav;
    }

    public void setPocetNaprav(int pocetNaprav) {
        this.pocetNaprav = pocetNaprav;
    }
    
    @Override
    public String toString(){
        return super.toString() + ", Nosnost=" + nosnost + ", pocetNaprav=" + pocetNaprav;
    }
    
}
